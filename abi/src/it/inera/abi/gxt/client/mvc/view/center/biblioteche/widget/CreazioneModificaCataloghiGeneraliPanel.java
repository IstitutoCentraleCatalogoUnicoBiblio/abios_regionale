package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGeneraliModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Estensione della classe widget <code>CreazioneModificaCataloghiGenericaPanel</code> 
 * per la visualizzazione / modifica delle informazioni relative ai cataloghi generali
 *
 */
public class CreazioneModificaCataloghiGeneraliPanel extends CreazioneModificaCataloghiGenericaPanel {

	/*Label cataloghi generali*/
	private  Text tipoCatalogoLabel;
	private ComboBox<VoceUnicaModel> tipoCatalogoField;
	/*Campi cataloghi generali*/
	private PartecipaCataloghiGeneraliModel tmpCatalogo;
	private PartecipaCataloghiGeneraliModel model;
	PagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>> tipologiaPatrimonioLoader;
	/*TabelData per labels e fields per tipologia materiale */
	private TableData tipoCatalogoTableData;
	private LayoutContainer tmpFieldsContainer;

	private ListStore<VoceUnicaModel> tipologiaCatalogoGeneraleStore;
	private int tipoCatalogoLabelWidth=100;	

	public CreazioneModificaCataloghiGeneraliPanel(boolean modifica) {
		super();
		this.modifica=modifica;
		//Setto il tipo di catalogo
		this.tipoCatalogo=1;

		createFieldsAndLabelsCatalogoGeneraleForm();
		createNuovoCatalogoGeneraleForm();

		tipoCatalogoTableData = new TableData();
		tipoCatalogoTableData.setWidth(tipoCatalogoLabelWidth+"px");
		tipoCatalogoTableData.setMargin(5);
	}

	public void createFieldsAndLabelsCatalogoGeneraleForm(){

		tipoCatalogoLabel = new Text("Tipo catalogo:");
		tipoCatalogoLabel.setStyleAttribute("fontSize", "12px");

		//tipoCatalogoField
		RpcProxy<List<VoceUnicaModel>> tipologiaCatalogoGeneraleProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				tdsa.getListaVoci(CostantiTabelleDinamiche.CATALOGO_GENERALE_TIPO_INDEX, callback);
			}

		};

		ModelReader tipologiaCatalogoGeneraleReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> tipologiaCatalogoGeneraleLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				tipologiaCatalogoGeneraleProxy, tipologiaCatalogoGeneraleReader);

		tipologiaCatalogoGeneraleStore = new ListStore<VoceUnicaModel>(
				tipologiaCatalogoGeneraleLoader);
		/*
		 * ComboBox per caricamento lista
		 * tipologie cataloghi generali possibili
		 */
		tipoCatalogoField = new ComboBox<VoceUnicaModel>();
		tipoCatalogoField.setForceSelection(true);
		tipoCatalogoField.setDisplayField("entry");
		tipoCatalogoField.setTriggerAction(TriggerAction.ALL);
		tipoCatalogoField.setEditable(false);
		tipoCatalogoField.setEmptyText("Tipologia...");
		tipoCatalogoField.setAllowBlank(false);
		tipoCatalogoField.setFireChangeEventOnSetValue(true);
		tipoCatalogoField.setStore(tipologiaCatalogoGeneraleStore);
		tipologiaCatalogoGeneraleLoader.load();
		createBaseFieldsAndLabels();
	}

	public void createNuovoCatalogoGeneraleForm(){

		Text descrizioneCatalogoEstensioneText = new Text("<b>Descrizione catalogo </b>");
		LayoutContainer descrizioneCatalogoEstensioneTable = new LayoutContainer(new TableLayout(2));

		LayoutContainer materialeTable = new LayoutContainer(new TableLayout(2));

		TableData	tipoCatalogoAggiungiTableData = new TableData();
		tipoCatalogoAggiungiTableData.setWidth("103px");
		tipoCatalogoAggiungiTableData.setMargin(5);
		tipoCatalogoAggiungiTableData.setPadding(10);

		materialeTable.add(tipoCatalogoLabel,tipoCatalogoAggiungiTableData);
		materialeTable.add(tipoCatalogoField);

		add(descrizioneCatalogoEstensioneText);
		add(descrizioneCatalogoEstensioneTable);
		add(materialeTable);
		add(createAggiungiBaseForm());

		LayoutContainer tmp = new LayoutContainer();
		tmp.setLayout(new CenterLayout());
		tmp.setHeight(40);

		HorizontalPanel buttonContainer = new HorizontalPanel();
		buttonContainer.setWidth(50);
		if(modifica){
			aggiornaButtonInit();
			FormButtonBinding bind = new FormButtonBinding(this);
			bind.addButton(aggiornaButton);

			buttonContainer.add(aggiornaButton);
		}else{
			aggiungiButtonInit();
			FormButtonBinding bind = new FormButtonBinding(this);
			bind.addButton(aggiungiButton);

			buttonContainer.add(aggiungiButton);
		}
	

		tmp.add(buttonContainer);
		setBottomComponent(tmp);
	}

	public LayoutContainer createModificaCatalogoGeneraleFormLabels(){

		LayoutContainer tmpLabelContainer= createModificaBaseFormLabels();

		tipoCatalogoLabel.setStyleAttribute("fontSize", labelFontSize);
		tipoCatalogoLabel.setWidth(tipoCatalogoLabelWidth);
		tmpLabelContainer.insert(tipoCatalogoLabel, 0,tipoCatalogoTableData);

		return tmpLabelContainer;
	}

	public LayoutContainer createModificaCatalogoGeneraleForm(){
		tmpFieldsContainer= createModificaBaseFormFields();
		tipoCatalogoField.setWidth(tipoCatalogoLabelWidth);
		tmpFieldsContainer.insert(tipoCatalogoField, 0, tipoCatalogoTableData);

		aggiornaButtonInit();
		rimuoviButtonInit();

		tmpFieldsContainer.add(aggiornaButton,annoTabelData);
		tmpFieldsContainer.add(rimuoviButton,annoTabelData);

		if(UIWorkflow.isReadOnly()==false){
			tipoCatalogoField.enable();
			aggiornaButton.show();
			rimuoviButton.show();
		}else{
			tipoCatalogoField.disable();
			aggiornaButton.hide();
			rimuoviButton.hide();
		}
		render(tmpFieldsContainer.getElement());
		return tmpFieldsContainer;
	}

	/**
	 * @return the tmpFieldsContainer
	 */
	public LayoutContainer getTmpFieldsContainer() {
		return tmpFieldsContainer;
	}
	public void setValues(final PartecipaCataloghiGeneraliModel model){
		this.model=model;
		tipologiaCatalogoGeneraleStore.add(new VoceUnicaModel(model.getIdTipoCatalogo(), model.getTipoCatalogoDescr()));
		tipoCatalogoField.setValue(new VoceUnicaModel(model.getIdTipoCatalogo(), model.getTipoCatalogoDescr()));

		/*Richiamo il metodo ereditato che setta i campi comuni a tutti i cataloghi*/
		setBaseValues(model);
	}

	private void aggiornaButtonInit() {
		aggiornaButton = new Button("Aggiorna");
		aggiornaButton.setIcon(Resources.ICONS.arrowsRefresh());
		FormButtonBinding bind = new FormButtonBinding(this);
		bind.addButton(aggiornaButton);

		aggiornaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							tmpCatalogo = new PartecipaCataloghiGeneraliModel();
							tmpCatalogo.setIdPartecipaCatalogo(model.getIdPartecipaCatalogo());
							if(tipoCatalogoField.getValue()!=null){
								tmpCatalogo.setIdTipoCatalogo(tipoCatalogoField.getValue().getIdRecord());
							}
							if(schedeField.getValue()!=null){
								tmpCatalogo.setSchede(schedeField.getValue().getValue());
							}else tmpCatalogo.setSchede("No");

							if(percentSchedeField.getValue()!=null){
								tmpCatalogo.setPercentSchede((Integer) percentSchedeField.getValue());
							}else tmpCatalogo.setPercentSchede(0);

							if(volumeField.getValue()!=null){
								tmpCatalogo.setVolume(volumeField.getValue().getValue());
							}else tmpCatalogo.setVolume("No");

							if(percentVolumeField.getValue()!=null){
								tmpCatalogo.setPercentVolume((Integer) percentVolumeField.getValue());
							}else tmpCatalogo.setPercentVolume(0);

							if(citazioneBiblioField.getValue()!=null)
								tmpCatalogo.setCitazioneBiblio(citazioneBiblioField.getValue());

							if(microformeField.getValue()!=null){
								tmpCatalogo.setMicroforme(microformeField.getValue().getValue());
							}else tmpCatalogo.setMicroforme("No");

							if(percentMicroformeField.getValue()!=null){
								tmpCatalogo.setPercentMicroforme((Integer)percentMicroformeField.getValue());
							}else tmpCatalogo.setPercentMicroforme(0);

							if(percentInformatizzatoField.getValue()!=null)
								tmpCatalogo.setPercentInformatizzato((Integer)percentInformatizzatoField.getValue());

							if(supportoDigitaleTipoField.getValue()!=null){
								tmpCatalogo.setIdInformatizzatoTipo(supportoDigitaleTipoField.getValue().getIdRecord());
								tmpCatalogo.setInformatizzatoDescr(supportoDigitaleTipoField.getValue().getEntry());

							}else{
								tmpCatalogo.setIdInformatizzatoTipo(3);
								tmpCatalogo.setInformatizzatoDescr("No");
							}
							if(percentInformatizzatoField.getValue()!=null){
								tmpCatalogo.setPercentInformatizzato((Integer)percentInformatizzatoField.getValue());
							}else tmpCatalogo.setPercentInformatizzato(0);

							if(annoDaField.getValue()!=null)
								tmpCatalogo.setDaAnno(annoDaField.getValue().intValue());

							if(annoAField.getValue()!=null)
								tmpCatalogo.setAAnno(annoAField.getValue().intValue());

							bibliotecheService.addPartecipaCatalogoGenerale(idBiblio, tmpCatalogo,	true,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadPanel();
									fireCloseWindow();
									reset();

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										reset();
									}
								}

							});
						} else {
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

	}

	private void rimuoviButtonInit() {
		rimuoviButton = new Button("Rimuovi");
		rimuoviButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int idRemove= model.getIdPartecipaCatalogo();
							bibliotecheService.removePartecipaCatalogoGenerale(idRemove,idBiblio,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireRemovePanel();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}
								}
							});
						} else {
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);
			}
		});
	}

	private void aggiungiButtonInit(){
		aggiungiButton.setIcon(Resources.ICONS.add());
		aggiungiButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							tmpCatalogo = new PartecipaCataloghiGeneraliModel();
							if(tipoCatalogoField.getValue()!=null){
								tmpCatalogo.setIdTipoCatalogo(tipoCatalogoField.getValue().getIdRecord());
							}
							if(schedeField.getValue()!=null){
								tmpCatalogo.setSchede(schedeField.getValue().getValue());
							}else tmpCatalogo.setSchede("No");

							if(percentSchedeField.getValue()!=null){
								tmpCatalogo.setPercentSchede((Integer) percentSchedeField.getValue());
							}else tmpCatalogo.setPercentSchede(0);

							if(volumeField.getValue()!=null){
								tmpCatalogo.setVolume(volumeField.getValue().getValue());
							}else tmpCatalogo.setVolume("No");

							if(percentVolumeField.getValue()!=null){
								tmpCatalogo.setPercentVolume((Integer) percentVolumeField.getValue());
							}else tmpCatalogo.setPercentVolume(0);

							if(citazioneBiblioField.getValue()!=null)
								tmpCatalogo.setCitazioneBiblio(citazioneBiblioField.getValue());

							if(microformeField.getValue()!=null){
								tmpCatalogo.setMicroforme(microformeField.getValue().getValue());
							}else tmpCatalogo.setMicroforme("No");

							if(percentMicroformeField.getValue()!=null){
								tmpCatalogo.setPercentMicroforme((Integer)percentMicroformeField.getValue());
							}else tmpCatalogo.setPercentMicroforme(0);

							if(percentInformatizzatoField.getValue()!=null)
								tmpCatalogo.setPercentInformatizzato((Integer)percentInformatizzatoField.getValue());

							if(supportoDigitaleTipoField.getValue()!=null){
								tmpCatalogo.setIdInformatizzatoTipo(supportoDigitaleTipoField.getValue().getIdRecord());
								tmpCatalogo.setInformatizzatoDescr(supportoDigitaleTipoField.getValue().getEntry());

							}else{
								tmpCatalogo.setIdInformatizzatoTipo(3);
								tmpCatalogo.setInformatizzatoDescr("No");
							}
							if(percentInformatizzatoField.getValue()!=null){
								tmpCatalogo.setPercentInformatizzato((Integer)percentInformatizzatoField.getValue());
							}else tmpCatalogo.setPercentInformatizzato(0);

							if(annoDaField.getValue()!=null)
								tmpCatalogo.setDaAnno(annoDaField.getValue().intValue());

							if(annoAField.getValue()!=null)
								tmpCatalogo.setAAnno(annoAField.getValue().intValue());


							bibliotecheService.addPartecipaCatalogoGenerale(idBiblio, tmpCatalogo, false,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadPanel();
									fireCloseWindow();
									reset();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										reset();
									}
								}
							});
						} else {
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	public PartecipaCataloghiGeneraliModel getModel(){
		return this.model;
	}
}
