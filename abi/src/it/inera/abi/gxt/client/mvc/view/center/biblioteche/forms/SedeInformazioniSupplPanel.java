package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * alla sede e ad informazioni supplementari
 *
 */
public class SedeInformazioniSupplPanel extends ContentPanelForTabItem {
	private TableData d3;
	private TableData dataToRight;
	private BibliotecheServiceAsync biblioService;
	/*Forms*/
	private FormPanel formSede;
	private FormPanel formLocali;
	private FormPanel formPostazioni;
	
	/*Bottoni salvataggio*/
	private Button aggiornaProfiloStorico;
	private Button aggiornaLocali;
	private Button aggiornaPostazioni;
	
	public SedeInformazioniSupplPanel() {
		super();

		d3 = new TableData();
		d3.setWidth("25%");
		d3.setMargin(5);
		d3.setPadding(10);

		dataToRight = new TableData();
		dataToRight.setWidth("25%");
		dataToRight.setMargin(5);
		dataToRight.setPadding(10);
		dataToRight.setHorizontalAlign(HorizontalAlignment.RIGHT);

		biblioService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

	}

	public void initService() {

	}
	public void createForms(){

		createFormSede();
		createFormLocali();
		createFormPostazioni();
	}

	private void createFormSede() {
		LayoutContainer sede = new LayoutContainer();
		sede.setStyleAttribute("padding", "5px");

		FieldSet sedeSet = new FieldSet();
		Utils.setFieldSetProperties(sedeSet, "Profilo storico e sede");

		/* SEDE */
		formSede = new FormPanel();
		formSede.setHeaderVisible(false);
		formSede.setBorders(false);
		formSede.setBodyBorder(false);

		FormLayout formLayout = new FormLayout();
		formLayout.setLabelAlign(LabelAlign.TOP);

		formSede.setLayout(formLayout);

		FormData data = new FormData();

		LayoutContainer c = new LayoutContainer(new TableLayout(2));

		TableData d = new TableData();
		d.setWidth("10%");
		d.setMargin(5);
		d.setPadding(10);

		TableData dRightAlign = new TableData();
		dRightAlign.setWidth("10%");
		dRightAlign.setMargin(5);
		dRightAlign.setPadding(10);
		dRightAlign.setHorizontalAlign(HorizontalAlignment.RIGHT);

		TableData d2 = new TableData();
		d2.setWidth("40%");
		d2.setMargin(5);
		d2.setPadding(10);

		final	Text edificioMonumentaleLable = new Text("Edificio monumentale:");
		edificioMonumentaleLable.setStyleAttribute("fontSize", "14px");
		c.add(edificioMonumentaleLable, d);

		HorizontalPanel edificioMonumentaleRadioButton = new HorizontalPanel();

		edificioMonumentaleRadioButton.setAutoWidth(true);

		final RadioGroup edificioMonumentaleGroup = new RadioGroup("edificioMonumentale");
		edificioMonumentaleGroup.setOrientation(Orientation.HORIZONTAL);
		edificioMonumentaleGroup.setStyleAttribute("position", "static");
		
		final Radio edificioMonumentaleSI = new Radio();
		edificioMonumentaleSI.setBoxLabel("SI");

		final Radio edificioMonumentaleNO = new Radio();
		edificioMonumentaleNO.setBoxLabel("NO");

		final Radio edificioMonumentaleNonSpecificato = new Radio();
		edificioMonumentaleNonSpecificato.setBoxLabel("Non specificato");

		edificioMonumentaleGroup.add(edificioMonumentaleSI);
		edificioMonumentaleGroup.add(edificioMonumentaleNO);
		edificioMonumentaleGroup.add(edificioMonumentaleNonSpecificato);

		edificioMonumentaleRadioButton.add(edificioMonumentaleGroup);

		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(edificioMonumentaleGroup, edificioMonumentaleLable);

		c.add(edificioMonumentaleRadioButton, d2);
		UIWorkflow.enableDisable(edificioMonumentaleSI);
		UIWorkflow.enableDisable(edificioMonumentaleNO);
		UIWorkflow.enableDisable(edificioMonumentaleNonSpecificato);

		final	Text denominazioneEdificio = new Text("Denominazione dell'edificio:");
		denominazioneEdificio.setStyleAttribute("fontSize", "14px");
		c.add(denominazioneEdificio, d);

		final TextField<String> denominazioneEdificioField = new TextField<String>();
		denominazioneEdificioField.setWidth(400);
		c.add(denominazioneEdificioField, d2);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(denominazioneEdificioField, denominazioneEdificio);
		UIWorkflow.setReadOnly(denominazioneEdificioField);

		final	Text edificioAppositamenteCostruitoLable = new Text("Edificio appositamente costruito:");
		edificioAppositamenteCostruitoLable.setStyleAttribute("fontSize","12px");
		c.add(edificioAppositamenteCostruitoLable, d);

		HorizontalPanel edificioAppositamenteCostruitoRadioButton = new HorizontalPanel();
		edificioAppositamenteCostruitoRadioButton.setAutoWidth(true);

		final RadioGroup edificioAppositamenteCostruitoGroup = new RadioGroup("appositamenteCostruito");
		edificioAppositamenteCostruitoGroup.setOrientation(Orientation.HORIZONTAL);
		edificioAppositamenteCostruitoGroup.setStyleAttribute("position", "static");
		
		final Radio edificioAppositamenteCostruitoSI = new Radio();
		edificioAppositamenteCostruitoSI.setBoxLabel("SI");

		final Radio edificioAppositamenteCostruitoNO = new Radio();
		edificioAppositamenteCostruitoNO.setBoxLabel("NO");

		final Radio edificioAppositamenteCostruitoNonSpecificato = new Radio();
		edificioAppositamenteCostruitoNonSpecificato.setBoxLabel("Non specificato");

		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(edificioAppositamenteCostruitoGroup, edificioAppositamenteCostruitoLable);

		edificioAppositamenteCostruitoGroup.add(edificioAppositamenteCostruitoSI);
		edificioAppositamenteCostruitoGroup.add(edificioAppositamenteCostruitoNO);
		edificioAppositamenteCostruitoGroup.add(edificioAppositamenteCostruitoNonSpecificato);

		edificioAppositamenteCostruitoRadioButton.add(edificioAppositamenteCostruitoGroup);

		c.add(edificioAppositamenteCostruitoRadioButton, d2);
		UIWorkflow.enableDisable(edificioAppositamenteCostruitoSI);
		UIWorkflow.enableDisable(edificioAppositamenteCostruitoNO);
		UIWorkflow.enableDisable(edificioAppositamenteCostruitoNonSpecificato);

		final	Text dataEdificioLable = new Text("Data dell'edificio:");
		dataEdificioLable.setStyleAttribute("fontSize", "14px");
		c.add(dataEdificioLable, d);

		final TextFieldCustom<String> dataEdificioField = new TextFieldCustom<String>();
		dataEdificioField.setMaxLength(20);
		dataEdificioField.setWidth(100);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(dataEdificioField, dataEdificioLable);

		UIWorkflow.setReadOnly(dataEdificioField);
		c.add(dataEdificioField, d2);

		aggiornaProfiloStorico = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaProfiloStorico);
		
		FormButtonBinding bindIndirizzo = new FormButtonBinding(formSede);
		bindIndirizzo.addButton(aggiornaProfiloStorico);

		aggiornaProfiloStorico.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();

							if(edificioMonumentaleNonSpecificato.getValue()==false){
								params.put("edificioMonumentale",edificioMonumentaleSI.getValue() == true ? true: false);
							}else {
								params.put("edificioMonumentale",null);
							}
							if(edificioAppositamenteCostruitoNonSpecificato.getValue()==false){
								params.put("appositamenteCostruito",edificioAppositamenteCostruitoSI.getValue() == true ? true: false);
							}else 	{
								params.put("appositamenteCostruito",null);
							}

							params.put("denominazioneEdificio",denominazioneEdificioField.getValue());
							params.put("dataCostruzione",dataEdificioField.getValue());

							biblioService.updateProfiloStoricoSede(	params, biblioteca.getIdBiblio(),new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReloadBiblioDataEvent();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									/*Resetto le labels al  colore nero*/
									setBlackLabelsInfoEdificioForm(edificioMonumentaleLable,denominazioneEdificio, edificioAppositamenteCostruitoLable,	dataEdificioLable);

									/*Setto i nuovi valori come originalValue() ai campi*/
									edificioMonumentaleSI.setOriginalValue(edificioMonumentaleSI.getValue());
									edificioMonumentaleNO.setOriginalValue(edificioMonumentaleNO.getValue());
									edificioMonumentaleNonSpecificato.setOriginalValue(edificioMonumentaleNonSpecificato.getValue());

									edificioAppositamenteCostruitoSI.setOriginalValue(edificioAppositamenteCostruitoSI.getValue());
									edificioAppositamenteCostruitoNO.setOriginalValue(edificioAppositamenteCostruitoNO.getValue());
									edificioAppositamenteCostruitoNonSpecificato.setOriginalValue(edificioAppositamenteCostruitoNonSpecificato.getValue());
									
									denominazioneEdificioField.setOriginalValue(denominazioneEdificioField.getValue());
									dataEdificioField.setOriginalValue(dataEdificioField.getValue());

									/*Setto i nuovi valori dei radiogroup ai campi*/
									if(edificioMonumentaleNonSpecificato.getValue()==false){

										if(edificioMonumentaleSI.getValue() == true){
											edificioMonumentaleGroup.setOriginalValue(edificioMonumentaleSI);
										}else if (edificioMonumentaleNO.getValue() == true){
											edificioMonumentaleGroup.setOriginalValue(edificioMonumentaleNO);
										}
									}else {
										edificioMonumentaleGroup.setOriginalValue(edificioMonumentaleNonSpecificato);
									}

									if(edificioAppositamenteCostruitoNonSpecificato.getValue()==false){
										if(edificioAppositamenteCostruitoSI.getValue() == true){
											edificioAppositamenteCostruitoGroup.setOriginalValue(edificioAppositamenteCostruitoSI);
										}else if (edificioAppositamenteCostruitoNO.getValue() == true){
											edificioAppositamenteCostruitoGroup.setOriginalValue(edificioAppositamenteCostruitoNO);
										}							
									}else 	{
										edificioAppositamenteCostruitoGroup.setOriginalValue(edificioAppositamenteCostruitoNonSpecificato);
									}
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		Button	resetProfiloStorico = new Button("Reset");
		Utils.setStylesButton(resetProfiloStorico);
		
		bindIndirizzo.addButton(resetProfiloStorico);

		resetProfiloStorico.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				edificioMonumentaleGroup.reset();
				formSede.reset();
				setBlackLabelsInfoEdificioForm(edificioMonumentaleLable, denominazioneEdificio, edificioAppositamenteCostruitoLable, dataEdificioLable);
			}
		});

		TableLayout tableLayoutProfilo = new TableLayout(2);
		tableLayoutProfilo.setCellPadding(5);
		LayoutContainer profiloButtons = new LayoutContainer(tableLayoutProfilo);
		profiloButtons.add(aggiornaProfiloStorico);
		profiloButtons.add(resetProfiloStorico);
		
		c.add(new LayoutContainer(), d);
		c.add(profiloButtons, d2);
		UIWorkflow.hideView(aggiornaProfiloStorico);
		UIWorkflow.hideView(resetProfiloStorico);

		formSede.add(c);
		sedeSet.add(formSede);

		sede.add(sedeSet);
		add(sede);
		/* Setto i valori dei campi */
		if (biblioteca != null) {
			if(biblioteca.getIsEdificioMonumentale()!=null){
				edificioMonumentaleSI.setValue(biblioteca.getIsEdificioMonumentale());
				edificioMonumentaleNO.setValue(!biblioteca.getIsEdificioMonumentale());
				edificioMonumentaleNonSpecificato.setValue(false);

				edificioMonumentaleSI.setOriginalValue(biblioteca.getIsEdificioMonumentale());
				edificioMonumentaleNO.setOriginalValue(!biblioteca.getIsEdificioMonumentale());
				edificioMonumentaleNonSpecificato.setOriginalValue(false);
				
				edificioMonumentaleGroup.setOriginalValue(biblioteca.getIsEdificioMonumentale()?edificioMonumentaleSI:edificioMonumentaleNO);
			}else{
				edificioMonumentaleSI.setValue(false);
				edificioMonumentaleNO.setValue(false);
				edificioMonumentaleNonSpecificato.setValue(true);

				edificioMonumentaleSI.setOriginalValue(false);
				edificioMonumentaleNO.setOriginalValue(false);
				edificioMonumentaleNonSpecificato.setOriginalValue(true);
				edificioMonumentaleGroup.setOriginalValue(edificioMonumentaleNonSpecificato);
			}

			setBlackLabelsInfoEdificioForm(edificioMonumentaleLable,denominazioneEdificio, edificioAppositamenteCostruitoLable,	dataEdificioLable);

			denominazioneEdificioField.setValue(biblioteca.getdenominazioneEdificio());
			denominazioneEdificioField.setOriginalValue(biblioteca.getdenominazioneEdificio());

			if(biblioteca.getIsAppositamenteCostruito()!=null){
				edificioAppositamenteCostruitoSI.setValue(biblioteca.getIsAppositamenteCostruito());
				edificioAppositamenteCostruitoNO.setValue(!biblioteca.getIsAppositamenteCostruito());
				edificioAppositamenteCostruitoNonSpecificato.setValue(false);

				edificioAppositamenteCostruitoSI.setOriginalValue(biblioteca.getIsAppositamenteCostruito());
				edificioAppositamenteCostruitoNO.setOriginalValue(!biblioteca.getIsAppositamenteCostruito());
				edificioAppositamenteCostruitoNonSpecificato.setOriginalValue(false);
				edificioAppositamenteCostruitoGroup.setOriginalValue(biblioteca.getIsAppositamenteCostruito()==true?edificioAppositamenteCostruitoSI:edificioAppositamenteCostruitoNO);
			}else{
				edificioAppositamenteCostruitoSI.setValue(false);
				edificioAppositamenteCostruitoNO.setValue(false);
				edificioAppositamenteCostruitoNonSpecificato.setValue(true);
				edificioAppositamenteCostruitoGroup.setOriginalValue(edificioAppositamenteCostruitoNonSpecificato);
			}

			dataEdificioField.setValue(biblioteca.getEdificioDataCostruzione());

			Utils.setFontColorStyleBlack(edificioMonumentaleLable);
		}
		/* FINE---SEDE */
	}

	/**
	 * Setta tutte le lable del form edificio di colore nero
	 * @param edificioMonumentaleLable
	 * @param denominazioneEdificio
	 * @param edificioAppositamenteCostruitoLable
	 * @param dataEdificioLable
	 */
	private void setBlackLabelsInfoEdificioForm(final Text edificioMonumentaleLable, Text denominazioneEdificio,
			Text edificioAppositamenteCostruitoLable, Text dataEdificioLable) {
		Utils.setFontColorStyleBlack(edificioMonumentaleLable);
		Utils.setFontColorStyleBlack(edificioAppositamenteCostruitoLable);
		Utils.setFontColorStyleBlack(dataEdificioLable);
		Utils.setFontColorStyleBlack(denominazioneEdificio);
	}

	private void createFormLocali() {
		/* LOCALI */
		LayoutContainer localiContainer = new LayoutContainer();
		localiContainer.setStyleAttribute("padding", "5px");

		FieldSet localiSet = new FieldSet();
		Utils.setFieldSetProperties(localiSet, "Locali");

		LayoutContainer locali = new LayoutContainer(new TableLayout(4));

		final Text mqSupBiblioLabel = new Text("Mq. superficie biblioteca: ");
		mqSupBiblioLabel.setStyleAttribute("fontSize", "14px");
		final NumberField mqSupBiblioField = new NumberField();
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(mqSupBiblioField, mqSupBiblioLabel);

		locali.add(mqSupBiblioLabel, d3);
		locali.add(mqSupBiblioField, d3);
		UIWorkflow.setReadOnly(mqSupBiblioField);

		final Text mqServiziLabel = new Text("Mq. servizi / sale al pubblico: ");
		mqServiziLabel.setStyleAttribute("fontSize", "14px");
		final NumberField mqServiziField = new NumberField();
		UIWorkflow.setReadOnly(mqServiziField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(mqServiziField, mqServiziLabel);

		locali.add(mqServiziLabel, d3);
		locali.add(mqServiziField, d3);

		final Text metriLineariScaffaliMagazzinoLabel = new Text(
		"Metri lineari scaffali magazzini: ");
		metriLineariScaffaliMagazzinoLabel
		.setStyleAttribute("fontSize", "14px");
		final NumberField mqLineariScaffaliMagazzinoField = new NumberField();
		UIWorkflow.setReadOnly(mqLineariScaffaliMagazzinoField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(mqLineariScaffaliMagazzinoField, metriLineariScaffaliMagazzinoLabel);


		locali.add(metriLineariScaffaliMagazzinoLabel, d3);
		locali.add(mqLineariScaffaliMagazzinoField, d3);

		final Text metriLineariScaffaliApertiLabel = new Text(
		"Metri lineari scaffali aperti: ");
		metriLineariScaffaliApertiLabel.setStyleAttribute("fontSize", "14px");
		final NumberField metriLineariScaffaliApertiField = new NumberField();
		UIWorkflow.setReadOnly(metriLineariScaffaliApertiField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(metriLineariScaffaliApertiField, metriLineariScaffaliApertiLabel);

		locali.add(metriLineariScaffaliApertiLabel, d3);
		locali.add(metriLineariScaffaliApertiField, d3);

		formLocali = new FormPanel();
		formLocali.setHeaderVisible(false);
		formLocali.setBorders(false);
		formLocali.setBodyBorder(false);

		FormLayout formLayoutLocali = new FormLayout();
		formLayoutLocali.setLabelAlign(LabelAlign.TOP);

		formLocali.setLayout(formLayoutLocali);

		aggiornaLocali = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaLocali);

		
		FormButtonBinding aggiornaLocaliBinding = new FormButtonBinding(formLocali);
		aggiornaLocaliBinding.addButton(aggiornaLocali);

		aggiornaLocali.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();
							if(mqSupBiblioField.getValue()!=null)
								params.put("mqtot",mqSupBiblioField.getValue().intValue());
							if(mqServiziField.getValue()!=null)
								params.put("mqpubblici",mqServiziField.getValue().intValue());
							if(mqLineariScaffaliMagazzinoField.getValue()!=null)
								params.put("mqmagazzini",mqLineariScaffaliMagazzinoField.getValue().intValue());
							if(metriLineariScaffaliApertiField.getValue()!=null)
								params.put("mqaperti",metriLineariScaffaliApertiField.getValue().intValue());

							biblioService.updateInfoLocali(params,biblioteca.getIdBiblio(),	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReloadBiblioDataEvent();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									/*Resetto le labels al  colore nero*/
									setBlackLabelsInfoLocaliForm(mqSupBiblioLabel, mqServiziLabel, metriLineariScaffaliMagazzinoLabel, metriLineariScaffaliApertiLabel);

									/*Setto i nuovi valori come originalValue() ai campi*/
									mqSupBiblioField.setOriginalValue((mqSupBiblioField.getValue()!=null?mqSupBiblioField.getValue().intValue():0));
									mqServiziField.setOriginalValue((mqServiziField.getValue()!=null?mqServiziField.getValue().intValue():0));
									mqLineariScaffaliMagazzinoField.setOriginalValue((mqLineariScaffaliMagazzinoField.getValue()!=null?mqLineariScaffaliMagazzinoField.getValue().intValue():0));
									metriLineariScaffaliApertiField.setOriginalValue((metriLineariScaffaliApertiField.getValue()!=null?metriLineariScaffaliApertiField.getValue().intValue():0));
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
		Button resetFormLocali = new Button("Reset");
		Utils.setStylesButton(resetFormLocali);


		resetFormLocali.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				formLocali.reset();
				setBlackLabelsInfoLocaliForm(mqSupBiblioLabel, mqServiziLabel, metriLineariScaffaliMagazzinoLabel, metriLineariScaffaliApertiLabel)	;
			}
		});

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(aggiornaLocali);
		buttons.add(resetFormLocali);
		
		locali.add(new LayoutContainer(), d3);
		locali.add(buttons, d3);

		UIWorkflow.hideView(aggiornaLocali);
		UIWorkflow.hideView(resetFormLocali);
		
		formLocali.add(locali);
		localiSet.add(formLocali);
		localiContainer.add(localiSet);
		add(localiContainer);

		if (biblioteca != null) {
			mqSupBiblioField.setValue(biblioteca.getMqSupBiblio());
			mqSupBiblioField.setOriginalValue(biblioteca.getMqSupBiblio());

			mqServiziField.setValue(biblioteca.getMqPubblici());
			mqServiziField.setOriginalValue(biblioteca.getMqPubblici());

			mqLineariScaffaliMagazzinoField.setValue(biblioteca.getMqMagazzini());
			mqLineariScaffaliMagazzinoField.setOriginalValue(biblioteca.getMqMagazzini());

			metriLineariScaffaliApertiField.setValue(biblioteca.getMqAperti());
			metriLineariScaffaliApertiField.setOriginalValue(biblioteca.getMqAperti());

			setBlackLabelsInfoLocaliForm(mqSupBiblioLabel, mqServiziLabel,metriLineariScaffaliMagazzinoLabel,metriLineariScaffaliApertiLabel);
		}
		/* FINE----LOCALI */
	}

	/**
	 * @param mqSupBiblioLabel
	 * @param mqServiziLabel
	 * @param metriLineariScaffaliMagazzinoLabel
	 * @param metriLineariScaffaliApertiLabel
	 */
	private void setBlackLabelsInfoLocaliForm(Text mqSupBiblioLabel, Text mqServiziLabel, Text metriLineariScaffaliMagazzinoLabel, Text metriLineariScaffaliApertiLabel) {
		Utils.setFontColorStyleBlack(metriLineariScaffaliApertiLabel);
		Utils.setFontColorStyleBlack(metriLineariScaffaliMagazzinoLabel);
		Utils.setFontColorStyleBlack(mqServiziLabel);
		Utils.setFontColorStyleBlack(mqSupBiblioLabel);
	}

	private void createFormPostazioni() {

		/* POSTAZIONI */

		LayoutContainer postazioniContainer = new LayoutContainer();
		postazioniContainer.setStyleAttribute("padding", "5px");

		FieldSet postazioniSet = new FieldSet();
		Utils.setFieldSetProperties(postazioniSet, "Postazioni");
		LayoutContainer postazioni = new LayoutContainer(new TableLayout(4));

		final Text postazioniLetturaLabel = new Text("Posti di lettura: ");
		postazioniLetturaLabel.setStyleAttribute("fontSize", "14px");
		final NumberField postazioniLetturaField = new NumberField();
		UIWorkflow.setReadOnly(postazioniLetturaField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(postazioniLetturaField, postazioniLetturaLabel);

		postazioni.add(postazioniLetturaLabel, d3);
		postazioni.add(postazioniLetturaField, d3);

		final Text postiVideoLabel = new Text("Posti attrezzati video: ");
		postiVideoLabel.setStyleAttribute("fontSize", "14px");
		final NumberField postiVideoField = new NumberField();
		UIWorkflow.setReadOnly(postiVideoField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(postiVideoField, postiVideoLabel);

		postazioni.add(postiVideoLabel, d3);
		postazioni.add(postiVideoField, d3);

		final Text postiAscoltoLabel = new Text("Posti attrezzati ascolto: ");
		postiAscoltoLabel.setStyleAttribute("fontSize", "14px");
		final NumberField postiAscoltoField = new NumberField();
		UIWorkflow.setReadOnly(postiAscoltoField);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(postiAscoltoField, postiAscoltoLabel);

		postazioni.add(postiAscoltoLabel, d3);
		postazioni.add(postiAscoltoField, d3);

		final Text postiInternetLabel = new Text("Posti attrezzati internet: ");
		postiInternetLabel.setStyleAttribute("fontSize", "14px");
		final NumberField postiInternetField = new NumberField();
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(postiInternetField, postiInternetLabel);
		UIWorkflow.setReadOnly(postiInternetField);

		postazioni.add(postiInternetLabel, d3);
		postazioni.add(postiInternetField, d3);

		formPostazioni = new FormPanel();
		formPostazioni.setHeaderVisible(false);
		formPostazioni.setBorders(false);
		formPostazioni.setBodyBorder(false);

		FormLayout formLayoutPostazioni = new FormLayout();
		formLayoutPostazioni.setLabelAlign(LabelAlign.TOP);

		formPostazioni.setLayout(formLayoutPostazioni);

		aggiornaPostazioni = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaPostazioni);

		FormButtonBinding aggiornaPostazioniBinding = new FormButtonBinding(formPostazioni);
		aggiornaPostazioniBinding.addButton(aggiornaPostazioni);

		aggiornaPostazioni.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();
							if( postazioniLetturaField.getValue()!=null)
								params.put("postiLettura", postazioniLetturaField.getValue().intValue());
							if( postiVideoField.getValue()!=null)				
								params.put("postiVideo", postiVideoField.getValue().intValue());
							if( postiAscoltoField.getValue()!=null)
								params.put("postiAscolto", postiAscoltoField.getValue().intValue());
							if( postiInternetField.getValue()!=null)	
								params.put("postiInternet",	postiInternetField.getValue().intValue());

							biblioService.updateInfoPostazioni(params, biblioteca.getIdBiblio(), new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReloadBiblioDataEvent();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									/*Resetto le labels al  colore nero*/
									setBlackLabelsFormPostazioni(postazioniLetturaLabel, postiVideoLabel, postiAscoltoLabel, postiInternetLabel);
									/*Setto i nuovi valori come originalValue() ai campi*/
									postazioniLetturaField.setOriginalValue((postazioniLetturaField.getValue()!=null?postazioniLetturaField.getValue().intValue():0));
									postiVideoField.setOriginalValue((postiVideoField.getValue()!=null?postiVideoField.getValue().intValue():0));
									postiAscoltoField.setOriginalValue((postiAscoltoField.getValue()!=null?postiAscoltoField.getValue().intValue():0));
									postiInternetField.setOriginalValue((postiInternetField.getValue()!=null?postiInternetField.getValue().intValue():0));
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		Button resetFormPostazioni = new Button("Reset");
		Utils.setStylesButton(resetFormPostazioni);

		aggiornaPostazioniBinding.addButton(resetFormPostazioni);

		resetFormPostazioni.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				formPostazioni.reset();
				setBlackLabelsFormPostazioni(postazioniLetturaLabel, postiVideoLabel, postiAscoltoLabel, postiInternetLabel);
			}
		});
		
		TableLayout tableLayoutPostazioni = new TableLayout(2);
		tableLayoutPostazioni.setCellPadding(5);
		LayoutContainer postazioniButtons = new LayoutContainer(tableLayoutPostazioni);
		postazioniButtons.add(aggiornaPostazioni);
		postazioniButtons.add(resetFormPostazioni);

		postazioni.add(new LayoutContainer(), d3);
		postazioni.add(postazioniButtons, d3);

		UIWorkflow.hideView(aggiornaPostazioni);
		UIWorkflow.hideView(resetFormPostazioni);
		
		formPostazioni.add(postazioni);

		postazioniSet.add(formPostazioni);
		postazioniContainer.add(postazioniSet);
		add(postazioniContainer);
		/* Setto i valori dei campi */
		if (biblioteca != null) {
			postazioniLetturaField.setValue(biblioteca.getPostiLettura());
			postazioniLetturaField.setOriginalValue(biblioteca.getPostiLettura());

			postiAscoltoField.setValue(biblioteca.getPostiAscolto());
			postiAscoltoField.setOriginalValue(biblioteca.getPostiAscolto());

			postiVideoField.setValue(biblioteca.getPostiVideo());
			postiVideoField.setOriginalValue(biblioteca.getPostiVideo());

			postiInternetField.setValue(biblioteca.getPostiInternet());
			postiInternetField.setOriginalValue(biblioteca.getPostiInternet());

			setBlackLabelsFormPostazioni(postazioniLetturaLabel, postiVideoLabel,postiAscoltoLabel, postiInternetLabel);
		}
		/* FINE---POSTAZIONI */
	
		if(UIWorkflow.isReadOnly()==false){
			addKeyListenerForEnter();
		}else{
			removeKeyListenerForEnter();
		}
	}

	/**
	 * @param postazioniLetturaLabel
	 * @param postiVideoLabel
	 * @param postiAscoltoLabel
	 * @param postiInternetLabel
	 */
	private void setBlackLabelsFormPostazioni(Text postazioniLetturaLabel,
			Text postiVideoLabel, Text postiAscoltoLabel,
			Text postiInternetLabel) {
		Utils.setFontColorStyleBlack(postiInternetLabel);
		Utils.setFontColorStyleBlack(postiAscoltoLabel);
		Utils.setFontColorStyleBlack(postiVideoLabel);
		Utils.setFontColorStyleBlack(postazioniLetturaLabel);
	}


	public void fireReloadBiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,biblioteca.getIdBiblio());
	}
	
	
	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(aggiornaProfiloStorico, formSede);
		Utils.addKeyListenerForEnter(aggiornaLocali, formLocali);
		Utils.addKeyListenerForEnter(aggiornaPostazioni, formPostazioni);
	}
	
	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( formSede);
		Utils.removeKeyListenerForEnter( formLocali);
		Utils.removeKeyListenerForEnter( formPostazioni);
	}
}
