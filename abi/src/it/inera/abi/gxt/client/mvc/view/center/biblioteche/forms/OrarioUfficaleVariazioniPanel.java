package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaOrariUfficialiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPeriodiChiusuraPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaVariazioniOrarioPanel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.Vector;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

public class OrarioUfficaleVariazioniPanel extends ContentPanelForTabItem {

	public int id_biblio;
	private ListaOrariUfficialiPanel lun;
	private ListaOrariUfficialiPanel mar;
	private ListaOrariUfficialiPanel mer;
	private ListaOrariUfficialiPanel gio;
	private ListaOrariUfficialiPanel ven;
	private ListaOrariUfficialiPanel sab;
	private ListaOrariUfficialiPanel dom;
	private BibliotecheServiceAsync bibliotecheServiceAsync;
	private Vector<ListaOrariUfficialiPanel> listaGiorni;

	private Vector<CheckBox> daysChecked;

	private ListaVariazioniOrarioPanel listaVariazionePanel;

	private	ListaPeriodiChiusuraPanel listaChiusuraPanel;

	private Button addUff;
	private Button addVar;

	public OrarioUfficaleVariazioniPanel() {
		super();

		this.listaGiorni = new Vector<ListaOrariUfficialiPanel>();
		this.bibliotecheServiceAsync = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
		this.daysChecked=new Vector<CheckBox>();

		createForms();
	}

	public void createForms() {
		createOrariUfficialiForm();
		createVariazioniOrariForm();
		createPeriodiChiusuraForm();
	}

	public void createOrariUfficialiForm() {
		/* ORARIO UFFICIALE */
		LayoutContainer orarioUfficiale = new LayoutContainer();
		orarioUfficiale.setStyleAttribute("padding", "5px");

		FieldSet orarioUfficialeSet = new FieldSet();
		Utils.setFieldSetProperties(orarioUfficialeSet, "Orario ufficiale");

		final Text startOreLabel = new Text("Dalle ore:");
		startOreLabel.setStyleAttribute("fontSize", "12px");


		final SimpleComboBox<String> startOreField = new SimpleComboBox<String>();
		startOreField.setAllowBlank(false);
		startOreField.setEmptyText("hh");
		startOreField.setForceSelection(true);
		startOreField.setWidth(50);
		startOreField.setTriggerAction(TriggerAction.ALL);
		startOreField.setEditable(false);
		startOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if(i<10){
				startOreField.add("0" + i);
			}else{
				startOreField.add("" + i);
			}
		}

		final SimpleComboBox<String> startMinField = new SimpleComboBox<String>();
		startMinField.setAllowBlank(false);
		startMinField.setEmptyText("mm");
		startMinField.setWidth(50);
		startMinField.setForceSelection(true);
		startMinField.setTriggerAction(TriggerAction.ALL);
		startMinField.setEditable(false);
		startMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				startMinField.add("" + i + j);
			}

		}

		startOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				startMinField.focus();
			}
		});
		
		final	Text stopOreLabel = new Text("Alle ore:");
		stopOreLabel.setStyleAttribute("fontSize", "12px");

		final SimpleComboBox<String> stopOreField = new SimpleComboBox<String>();
		stopOreField.setAllowBlank(false);
		stopOreField.setEmptyText("hh");
		stopOreField.setForceSelection(true);
		stopOreField.setWidth(50);
		stopOreField.setTriggerAction(TriggerAction.ALL);
		stopOreField.setEditable(false);
		stopOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if(i<10){
				stopOreField.add("0" + i);
			}else{
				stopOreField.add("" + i);
			}
		}

		startMinField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopOreField.focus();
			}
		});
		
		final SimpleComboBox<String> stopMinField = new SimpleComboBox<String>();
		stopMinField.setAllowBlank(false);
		stopMinField.setEmptyText("mm");
		stopMinField.setWidth(50);
		stopMinField.setForceSelection(true);
		stopMinField.setTriggerAction(TriggerAction.ALL);
		stopMinField.setEditable(false);
		stopMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				stopMinField.add("" + i + j);
			}

		}

		stopOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopMinField.focus();
			}
		});

		addUff = new Button("Aggiungi orario settimanale");
		Utils.setStylesButton(addUff);
		addUff.setSize(180, 30);
		addUff.setIcon(Resources.ICONS.add());
		addUff.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();

				window.setSize(350, 300);
				window.setModal(true);
				window.setHeading("Inserimento nuovo orario");
				window.setLayout(new FitLayout());
				window.setClosable(false);

				final FormPanel variazioniFormPanel = new FormPanel();
				variazioniFormPanel.setBodyBorder(false);
				variazioniFormPanel.setBorders(false);
				variazioniFormPanel.setHeaderVisible(false);
				/* CONTENUTO FINESTRA */
				LayoutContainer windowContainer = new LayoutContainer();
				/* TIME PICKER */
				LayoutContainer timePicker = new LayoutContainer(
						new TableLayout(2));
				TableData d = new TableData();
				d.setWidth("15%");
				d.setMargin(5);
				d.setPadding(10);

				TableData d2 = new TableData();
				d2.setWidth("30%");
				d2.setMargin(5);
				d2.setPadding(10);

				timePicker.add(startOreLabel, d);

				HorizontalPanel h = new HorizontalPanel();

				h.add(startOreField);

				h.add(startMinField);

				timePicker.add(h, d2);

				timePicker.add(stopOreLabel, d);

				HorizontalPanel h2 = new HorizontalPanel();

				h2.add(stopOreField);

				h2.add(stopMinField);

				timePicker.add(h2, d2);

				/* FINE TIME PICKER */

				/* ESTENSIONE AI GIORNI: */
				final HorizontalPanel dayBox = new HorizontalPanel();
				dayBox.setStyleAttribute("paddingLeft", "50px");
				TableData d3 = new TableData();
				d3.setWidth("15%");
				d3.setMargin(5);
				d3.setPadding(10);

				Text tuttiGiorniLabel = new Text("Tutti i giorni:");
				tuttiGiorniLabel.setStyleAttribute("fontSize", "12px");
				timePicker.add(tuttiGiorniLabel, d3);

				final RadioButton tuttiGiorniRadio = new RadioButton("estensione");
				tuttiGiorniRadio.setValue(true);
				tuttiGiorniRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						dayBox.hide();

					}
				});
				timePicker.add(tuttiGiorniRadio, d3);

				Text giorniCustomLabel = new Text("Personalizzati:");
				giorniCustomLabel.setStyleAttribute("fontSize", "12px");
				timePicker.add(giorniCustomLabel, d3);

				final	RadioButton giorniCustomRadio = new RadioButton("estensione");

				giorniCustomRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						dayBox.show();

					}
				});
				timePicker.add(giorniCustomRadio, d3);

				Text lunLabel = new Text("L");
				lunLabel.setStyleAttribute("fontSize", "12px");
				lunLabel.setStyleAttribute("margin", "5px");

				final CheckBox lun = new CheckBox();
				daysChecked.add(lun);
				dayBox.add(lunLabel);
				dayBox.add(lun);

				Text marLabel = new Text("M");
				marLabel.setStyleAttribute("fontSize", "12px");
				marLabel.setStyleAttribute("margin", "5px");

				final CheckBox mar = new CheckBox();
				daysChecked.add(mar);
				dayBox.add(marLabel);
				dayBox.add(mar);

				Text merLabel = new Text("M");
				merLabel.setStyleAttribute("fontSize", "12px");
				merLabel.setStyleAttribute("margin", "5px");

				final CheckBox mer = new CheckBox();
				daysChecked.add(mer);
				dayBox.add(merLabel);
				dayBox.add(mer);

				Text gioLabel = new Text("G");
				gioLabel.setStyleAttribute("fontSize", "12px");
				gioLabel.setStyleAttribute("margin", "5px");

				final CheckBox gio = new CheckBox();
				daysChecked.add(gio);
				dayBox.add(gioLabel);
				dayBox.add(gio);

				Text venLabel = new Text("V");
				venLabel.setStyleAttribute("fontSize", "12px");
				venLabel.setStyleAttribute("margin", "5px");

				final CheckBox ven = new CheckBox();
				daysChecked.add(ven);
				dayBox.add(venLabel);
				dayBox.add(ven);

				Text sabLabel = new Text("S");
				sabLabel.setStyleAttribute("fontSize", "12px");
				sabLabel.setStyleAttribute("margin", "5px");

				final CheckBox sab = new CheckBox();
				daysChecked.add(sab);
				dayBox.add(sabLabel);
				dayBox.add(sab);

				Text domLabel = new Text("D");
				domLabel.setStyleAttribute("fontSize", "12px");
				domLabel.setStyleAttribute("margin", "5px");

				final CheckBox dom = new CheckBox();
				daysChecked.add(dom);
				dayBox.add(domLabel);
				dayBox.add(dom);
				dayBox.hide();

				windowContainer.add(timePicker);
				windowContainer.add(dayBox);
				/* FINE ESTENSIONE AI GIORNI: */
				variazioniFormPanel.add(windowContainer);
				window.add(variazioniFormPanel);

				final Button save = new Button("Salva");
				save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						final	Vector<Integer> id_days=new Vector<Integer>();
						final	OrariModel toSave = new OrariModel();

						toSave.setStartOre(startOreField.getSimpleValue());

						toSave.setStartMin(startMinField.getSimpleValue());

						toSave.setStopOre(stopOreField.getSimpleValue());

						toSave.setStopMin(stopMinField.getSimpleValue());

						if (checkOrarioFormat(toSave)){

							window.close();
							final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

								@Override
								public void handleEvent(MessageBoxEvent ce) {

									Button btn = ce.getButtonClicked();
									if (btn.getText().equalsIgnoreCase("Si")) {


										if (tuttiGiorniRadio.getValue()){
											for (int i = 0; i < 7; i++) 
												id_days.add(i+1);			
										}else{
											if (lun.getValue()){
												id_days.add(1);
											}

											if (mar.getValue()){
												id_days.add(2);
											}
											if (mer.getValue()){
												id_days.add(3);
											}
											if (gio.getValue()){
												id_days.add(4);
											}
											if (ven.getValue()){
												id_days.add(5);
											}
											if (sab.getValue()){
												id_days.add(6);
											}
											if (dom.getValue()){
												id_days.add(7);
											}


										}
										bibliotecheServiceAsync.addNuovoOrarioGiornalieroCustom(id_biblio, id_days, toSave,
												new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												loadLoaders();;

												AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

											}

											@Override
											public void onFailure(Throwable caught) {
												if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
													AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											}

										});
									} 
									stopMinField.clear();
									stopOreField.clear();
									startMinField.clear();
									startOreField.clear();

								}

							};
							AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
						}
					}
				});

				final Button cancel = new Button("Annulla");
				cancel.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						stopMinField.clear();
						stopOreField.clear();
						startMinField.clear();
						startOreField.clear();
						window.close();
					}
				});

				FormButtonBinding buttonBinding = new FormButtonBinding(variazioniFormPanel);
				buttonBinding.addButton(save);

				window.addButton(save);
				window.addButton(cancel);
				window.show();


			}
		});

		add(addUff);

		LayoutContainer h = new LayoutContainer(new TableLayout(7));
		h.setStyleAttribute("marginTop", "20px");

		lun = new ListaOrariUfficialiPanel(1);
		lun.setGrid();
		listaGiorni.add(lun);
		h.add(lun);

		mar = new ListaOrariUfficialiPanel(2);
		mar.setGrid();
		listaGiorni.add(mar);
		h.add(mar);

		mer = new ListaOrariUfficialiPanel(3);
		mer.setGrid();
		listaGiorni.add(mer);
		h.add(mer);

		gio = new ListaOrariUfficialiPanel(4);
		gio.setGrid();
		listaGiorni.add(gio);
		h.add(gio);

		ven = new ListaOrariUfficialiPanel(5);
		ven.setGrid();
		listaGiorni.add(ven);
		h.add(ven);

		sab = new ListaOrariUfficialiPanel(6);
		sab.setGrid();
		listaGiorni.add(sab);
		h.add(sab);

		dom = new ListaOrariUfficialiPanel(7);
		dom.setGrid();
		listaGiorni.add(dom);
		h.add(dom);

		orarioUfficialeSet.add(addUff);

		orarioUfficialeSet.add(h);
		orarioUfficiale.add(orarioUfficialeSet);
		add(orarioUfficiale);

		/* FINE---ORARIO UFFICALE */
	}

	public void createVariazioniOrariForm() {
		/* VARIAZIONI ORARI */
		LayoutContainer variazioneOrario = new LayoutContainer();
		variazioneOrario.setStyleAttribute("padding", "5px");

		FieldSet variazioneOrarioSet = new FieldSet();
		Utils.setFieldSetProperties(variazioneOrarioSet, "Periodo di variazione");

		final FormPanel variazioniFormPanel = new FormPanel();
		variazioniFormPanel.setBodyBorder(false);
		variazioniFormPanel.setBorders(false);
		variazioniFormPanel.setHeaderVisible(false);

		/* PROVA */
		final Text periodoVarLabel = new Text("Periodo variazione:");
		periodoVarLabel.setStyleAttribute("fontSize", "12px");

		final TextField<String> periodoVarField = new TextField<String>();
		periodoVarField.setWidth(150);
		periodoVarField.setMinLength(1);
		periodoVarField.setAllowBlank(false);

		final Text startOreLabel = new Text("Dalle ore:");
		startOreLabel.setStyleAttribute("fontSize", "12px");

		final SimpleComboBox<String> startOreField = new SimpleComboBox<String>();
		startOreField.setEmptyText("hh");
		startOreField.setAllowBlank(false);
		startOreField.setForceSelection(true);
		startOreField.setWidth(50);
		startOreField.setTriggerAction(TriggerAction.ALL);
		startOreField.setEditable(false);
		startOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if(i<10){
				startOreField.add("0" + i);
			}else{
				startOreField.add("" + i);
			}
		}

		final SimpleComboBox<String> startMinField = new SimpleComboBox<String>();
		startMinField.setEmptyText("mm");
		startMinField.setAllowBlank(false);
		startMinField.setWidth(50);
		startMinField.setForceSelection(true);
		startMinField.setTriggerAction(TriggerAction.ALL);
		startMinField.setEditable(false);
		startMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				startMinField.add("" + i + j);
			}

		}

		startOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				startMinField.focus();
			}
		});
		
		final	Text stopOreLabel = new Text("Alle ore:");
		stopOreLabel.setStyleAttribute("fontSize", "12px");

		final SimpleComboBox<String> stopOreField = new SimpleComboBox<String>();
		stopOreField.setEmptyText("hh");
		stopOreField.setAllowBlank(false);
		stopOreField.setForceSelection(true);
		stopOreField.setWidth(50);
		stopOreField.setTriggerAction(TriggerAction.ALL);
		stopOreField.setEditable(false);
		stopOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if(i<10){
				stopOreField.add("0" + i);
			}else{
				stopOreField.add("" + i);
			}
		}
		
		startMinField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopOreField.focus();
			}
		});
		final SimpleComboBox<String> stopMinField = new SimpleComboBox<String>();
		stopMinField.setEmptyText("mm");
		stopMinField.setAllowBlank(false);
		stopMinField.setWidth(50);
		stopMinField.setForceSelection(true);
		stopMinField.setTriggerAction(TriggerAction.ALL);
		stopMinField.setEditable(false);
		stopMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				stopMinField.add("" + i + j);
			}

		}
		
		stopOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopMinField.focus();
			}
		});
		
		addVar = new Button("Aggiungi variazione orario");
		Utils.setStylesButton(addVar);
		addVar.setSize(180, 30);
		addVar.setIcon(Resources.ICONS.add());
		addVar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();

				window.setSize(350, 350);
				window.setModal(true);
				window.setHeading("Inserimento variazione orario");
				window.setLayout(new FitLayout());
				window.setClosable(false);

				periodoVarField.clear();

				/* CONTENUTO FINESTRA */
				LayoutContainer windowContainer = new LayoutContainer();
				/* TIME PICKER */
				LayoutContainer timePicker = new LayoutContainer(
						new TableLayout(2));
				TableData d = new TableData();
				d.setWidth("15%");
				d.setMargin(5);
				d.setPadding(10);

				TableData d2 = new TableData();
				d2.setWidth("30%");
				d2.setMargin(5);
				d2.setPadding(10);

				timePicker.add(periodoVarLabel, d);
				timePicker.add(periodoVarField, d2);

				timePicker.add(startOreLabel, d);

				HorizontalPanel h = new HorizontalPanel();

				h.add(startOreField);

				h.add(startMinField);

				timePicker.add(h, d2);

				timePicker.add(stopOreLabel, d);

				HorizontalPanel h2 = new HorizontalPanel();

				h2.add(stopOreField);

				h2.add(stopMinField);

				timePicker.add(h2, d2);

				/* FINE TIME PICKER */

				/* ESTENSIONE AI GIORNI: */
				final HorizontalPanel dayBox = new HorizontalPanel();
				dayBox.setStyleAttribute("paddingLeft", "50px");
				TableData d3 = new TableData();
				d3.setWidth("15%");
				d3.setMargin(5);
				d3.setPadding(10);

				Text tuttiGiorniLabel = new Text("Tutti i giorni:");
				tuttiGiorniLabel.setStyleAttribute("fontSize", "12px");
				timePicker.add(tuttiGiorniLabel, d3);

				final RadioButton tuttiGiorniRadio = new RadioButton("estensione");
				tuttiGiorniRadio.setValue(true);
				tuttiGiorniRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						dayBox.hide();

					}
				});
				timePicker.add(tuttiGiorniRadio, d3);

				Text giorniCustomLabel = new Text("Personalizzati:");
				giorniCustomLabel.setStyleAttribute("fontSize", "12px");
				timePicker.add(giorniCustomLabel, d3);

				final RadioButton giorniCustomRadio = new RadioButton("estensione");

				giorniCustomRadio.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						dayBox.show();

					}
				});
				timePicker.add(giorniCustomRadio, d3);

				Text lunLabel = new Text("L");
				lunLabel.setStyleAttribute("fontSize", "12px");
				lunLabel.setStyleAttribute("margin", "5px");

				final CheckBox lun = new CheckBox();
				daysChecked.add(lun);
				dayBox.add(lunLabel);
				dayBox.add(lun);

				Text marLabel = new Text("M");
				marLabel.setStyleAttribute("fontSize", "12px");
				marLabel.setStyleAttribute("margin", "5px");

				final CheckBox mar = new CheckBox();
				daysChecked.add(mar);
				dayBox.add(marLabel);
				dayBox.add(mar);

				Text merLabel = new Text("M");
				merLabel.setStyleAttribute("fontSize", "12px");
				merLabel.setStyleAttribute("margin", "5px");

				final CheckBox mer = new CheckBox();
				daysChecked.add(mer);
				dayBox.add(merLabel);
				dayBox.add(mer);

				Text gioLabel = new Text("G");
				gioLabel.setStyleAttribute("fontSize", "12px");
				gioLabel.setStyleAttribute("margin", "5px");

				final CheckBox gio = new CheckBox();
				daysChecked.add(gio);
				dayBox.add(gioLabel);
				dayBox.add(gio);

				Text venLabel = new Text("V");
				venLabel.setStyleAttribute("fontSize", "12px");
				venLabel.setStyleAttribute("margin", "5px");

				final CheckBox ven = new CheckBox();
				daysChecked.add(ven);
				dayBox.add(venLabel);
				dayBox.add(ven);

				Text sabLabel = new Text("S");
				sabLabel.setStyleAttribute("fontSize", "12px");
				sabLabel.setStyleAttribute("margin", "5px");

				final CheckBox sab = new CheckBox();
				daysChecked.add(sab);
				dayBox.add(sabLabel);
				dayBox.add(sab);

				Text domLabel = new Text("D");
				domLabel.setStyleAttribute("fontSize", "12px");
				domLabel.setStyleAttribute("margin", "5px");

				final CheckBox dom = new CheckBox();
				daysChecked.add(dom);
				dayBox.add(domLabel);
				dayBox.add(dom);
				dayBox.hide();

				windowContainer.add(timePicker);
				windowContainer.add(dayBox);
				/* FINE ESTENSIONE AI GIORNI: */
				variazioniFormPanel.add(windowContainer);
				window.add(variazioniFormPanel);

				final Button save = new Button("Salva");
				save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						final Vector<Integer> id_days=new Vector<Integer>();
						final OrariModel toSave = new OrariModel();

						/* Verifico che sia stato specificato un periodo variazione */
						if (periodoVarField.getValue() != null && periodoVarField.getValue().length() > 0) {
							toSave.setPeriodo(periodoVarField.getValue());
						}

						toSave.setStartOre(startOreField.getSimpleValue());

						toSave.setStartMin(startMinField.getSimpleValue());

						toSave.setStopOre(stopOreField.getSimpleValue());

						toSave.setStopMin(stopMinField.getSimpleValue());

						if (checkOrarioFormat(toSave)){

							window.close();
							final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

								@Override
								public void handleEvent(MessageBoxEvent ce) {

									Button btn = ce.getButtonClicked();
									if (btn.getText().equalsIgnoreCase("Si")) {


										if (tuttiGiorniRadio.getValue()){
											for (int i = 0; i < 7; i++) 
												id_days.add(i+1);			
										}else{
											if (lun.getValue()){
												id_days.add(1);
											}

											if (mar.getValue()){
												id_days.add(2);
											}
											if (mer.getValue()){
												id_days.add(3);
											}
											if (gio.getValue()){
												id_days.add(4);
											}
											if (ven.getValue()){
												id_days.add(5);
											}
											if (sab.getValue()){
												id_days.add(6);
											}
											if (dom.getValue()){
												id_days.add(7);
											}


										}
										bibliotecheServiceAsync.addNuovaVariazioneOrarioCustom(id_biblio, id_days, toSave, new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												loadLoaders();

												AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

											}

											@Override
											public void onFailure(Throwable caught) {
												if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
													AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											}

										});
									} 
									stopMinField.clear();
									stopOreField.clear();
									startMinField.clear();
									startOreField.clear();
								}

							};
							AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
						}
					}
				});

				final Button cancel = new Button("Annulla");
				cancel.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						stopMinField.clear();
						stopOreField.clear();
						startMinField.clear();
						startOreField.clear();
						window.close();
					}
				});
				FormButtonBinding buttonBinding = new FormButtonBinding(variazioniFormPanel);
				buttonBinding.addButton(save);

				window.addButton(save);
				window.addButton(cancel);
				window.show();
			}
		});

		variazioneOrarioSet.add(addVar);
		/* PROVA */
		listaVariazionePanel = new ListaVariazioniOrarioPanel();
		listaVariazionePanel.setGrid();
		variazioneOrarioSet.add(listaVariazionePanel);

		variazioneOrario.add(variazioneOrarioSet);
		add(variazioneOrario);
		/* FINE---VARIAZIONI ORARI */
	}

	public void createPeriodiChiusuraForm() {
		/* PERIODI CHIUSURA */
		LayoutContainer periodiChiusura = new LayoutContainer();
		periodiChiusura.setStyleAttribute("padding", "5px");

		FieldSet periodiChiusuraSet = new FieldSet();
		Utils.setFieldSetProperties(periodiChiusuraSet, "Periodo di chiusura");

		listaChiusuraPanel = new ListaPeriodiChiusuraPanel();
		listaChiusuraPanel.setGrid();
		periodiChiusuraSet.add(listaChiusuraPanel);

		periodiChiusura.add(periodiChiusuraSet);
		add(periodiChiusura);
		/* FINE---PERIODI CHIUSURA */
	}

	public void setFieldsValues() {

		//ORARI UFFICIALI
		//Setto l'id della biblioteca alle liste orari
		for (int i = 0; i < listaGiorni.size(); i++) {
			listaGiorni.elementAt(i).setIdBiblioteca(this.id_biblio);
		}
		listaVariazionePanel.setIdBiblioteca(this.id_biblio);
		listaChiusuraPanel.setIdBiblioteca(this.id_biblio);
		//Eseguo la load sui loader delle liste
		loadLoaders();


		// controllo readOnly
		UIWorkflow.hideView(addUff);
		UIWorkflow.hideView(addVar);
		
	}

	public void loadLoaders() {
		for (int j = 0; j < listaGiorni.size(); j++) {
			listaGiorni.elementAt(j).getLoader().load();
		}
		listaVariazionePanel.getLoader().load();
		listaChiusuraPanel.getLoader().load();
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}

	public static boolean checkOrarioFormat(OrariModel tmpSave){
		int startOreInt= Integer.parseInt(tmpSave.getStartOre());
		int startMinInt= Integer.parseInt(tmpSave.getStartMin());

		int stopOreInt= Integer.parseInt(tmpSave.getStopOre());
		int stopMinInt= Integer.parseInt(tmpSave.getStopMin());

		if (startOreInt>stopOreInt || (((startOreInt>=stopOreInt)||(startOreInt==stopOreInt))&&(startMinInt>=stopMinInt))){
			AbiMessageBox.messageErrorAlertBox("L'orario di apertura deve essere precedente all'orario di chiusura!", "ERRORE FORMATO ORARIO");
			return false;
		}
		return true;
	}


}
