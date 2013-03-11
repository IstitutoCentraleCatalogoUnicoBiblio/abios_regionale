package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.EnteModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * alle tipologie: amministrativa e funzionale
 *
 */
public class TipologiaAmministrativaFunzionalePanel  extends ContentPanelForTabItem {

	private boolean firstLoad = true;

	private TableData d;
	private TableData dataToRight;
	private TableData d2;
	private TableData d3;

	private ComboBoxForBeans<EnteModel> denominazioneEnteField;
	private ComboBoxForBeans<VoceUnicaModel> tipologiaAmministrativaField;
	private ComboBoxForBeans<StatoModel> statoAppartenenzaField;

	private	SimpleComboBox<String> autonomiaAmministrativaField;
	private TextField<String> strutturaGerarchicaSovranumerariaField;

	private	ComboBox<VoceUnicaModel> tipFunzField;

	private	TextField<String> dataFondazioneField;
	private TextField<String> istituzioneAttualeField;

	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheService;
	private LocationServiceAsync locationService;

	private Button aggiornaAutonomiaAmministrativa;
	private Button aggiornaEnteAppartenenza;
	private Button tipFunzAggiorna;
	private Button dataFondazioneAggiorna;


	private Text denominazioneEnteLabel;
	private Text tipologiaAmministrativaLabel;
	private Text statoAppartenenzaLabel;
	private Text autonomiaAmministrativaLabel;
	private Text strutturaGerarchicaSovranumerariaLabel;
	private	Text tipFunzLabel;
	private Text dataFondazioneLabel;
	private Text istituzioneAttualeLabel;
	private Button resetFormEnte;
	private Button resetAutonominaAmministrativa;

	private Button resetTipologiaFunzionale;

	private Button resetFondazione;
	
	/*Forms*/
	private FormPanel formEnteAppartenenza;
	private FormPanel formAutonomiaAmministrativa;
	private FormPanel formTipologiaFunzionale;
	private FormPanel fondazioneBibliotecaForm;
	
	public TipologiaAmministrativaFunzionalePanel() {
		super();
		tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

		bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
		locationService = Registry.get(Abi.LOCATION_SERVICE);


		d = new TableData();
		d.setWidth("10%");
		d.setMargin(5);
		d.setPadding(10);

		dataToRight = new TableData();
		dataToRight.setWidth("10%");
		dataToRight.setMargin(5);
		dataToRight.setPadding(10);
		dataToRight.setHorizontalAlign(HorizontalAlignment.RIGHT);

		d2 = new TableData();
		d2.setWidth("50%");
		d2.setMargin(5);
		d2.setPadding(10);
		
		d3 = new TableData();
		d3.setWidth("20%");
		d3.setMargin(5);
		d3.setPadding(10);
		
		createForms();
	}



	public void createForms(){
		createEnteAppartenezaForm();
		autonomiaAmministrativaForm();
		tipologiaFunzionaleForm();
		fondazioneBibliotecaForm();
	}

	public void createEnteAppartenezaForm() {

		/* ENTE DI APPARTENENZA */
		LayoutContainer enteAppartenenza = new LayoutContainer();
		enteAppartenenza.setStyleAttribute("padding", "5px");

		FieldSet enteAppartenenzaSet = new FieldSet();
		Utils.setFieldSetProperties(enteAppartenenzaSet, "Ente di appartenenza");

		formEnteAppartenenza = new FormPanel();
		formEnteAppartenenza.setHeaderVisible(false);
		formEnteAppartenenza.setBorders(false);
		formEnteAppartenenza.setBodyBorder(false);

		FormLayout formLayoutEnteAppartenenza = new FormLayout();
		formLayoutEnteAppartenenza.setLabelAlign(LabelAlign.TOP);

		formEnteAppartenenza.setLayout(formLayoutEnteAppartenenza);
		TableLayout	enteAppartenezaTableLayout =new TableLayout(2);
		enteAppartenezaTableLayout.setWidth("750px");
		LayoutContainer enteAppartenezaTable = new LayoutContainer(enteAppartenezaTableLayout);

		denominazioneEnteLabel = new Text("Denominazione:");

		denominazioneEnteLabel.setStyleAttribute("fontSize", "14px");
		enteAppartenezaTable.add(denominazioneEnteLabel, d);


		RpcProxy<PagingLoadResult<EnteModel>> proxyEnte = new RpcProxy<PagingLoadResult<EnteModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<EnteModel>> callback) {
				// ID associato tabella tipologie funzionali
				bibliotecheService.getEntiPaginatiFilteredPerCombos(
						(ModelData) loadConfig, callback);
			}

		};

		ModelReader enteReader = new ModelReader();

		final PagingLoader<PagingLoadResult<EnteModel>> enteLoader = new BasePagingLoader<PagingLoadResult<EnteModel>>(
				proxyEnte, enteReader);
		enteLoader.load();
		final	ListStore<EnteModel> listStoreEnte = new ListStore<EnteModel>(enteLoader);

		denominazioneEnteField = new ComboBoxForBeans<EnteModel>();
		denominazioneEnteField.setWidth(550);
		denominazioneEnteField.setDisplayField("denominazione");
		denominazioneEnteField.setAllowBlank(false);
		denominazioneEnteField.setStore(listStoreEnte);
		denominazioneEnteField.setFireChangeEventOnSetValue(true);
		denominazioneEnteField.setEmptyText("Scegli una denominazione...");
		denominazioneEnteField.setLazyRender(false);
		denominazioneEnteField.setTriggerAction(TriggerAction.ALL);
		denominazioneEnteField.setEditable(true);
		denominazioneEnteField.setForceSelection(true);
		denominazioneEnteField.setPageSize(10);
		denominazioneEnteField.setTypeAhead(false);
		denominazioneEnteField.setMinChars(1);

		//		enteLoader.addListener(Loader.Load, new Listener<BaseEvent>() {
		//
		//			@Override
		//			public void handleEvent(BaseEvent be) {
		//				final List<EnteModel> tmpStore = new ArrayList<EnteModel>();
		//
		//				for(EnteModel e :listStoreEnte.getModels()){
		//					EnteModel tmpEnteModel = e;
		//					tmpEnteModel.setDenominazione(Utils.insertBRtag(e.getDenominazione(),30));
		//					tmpStore.add(tmpEnteModel);					
		//				}
		//
		//				listStoreEnte.removeAll();
		//				listStoreEnte.add(tmpStore);
		//
		//			
		//
		//			}
		//		});

		//		denominazioneEnteField.setPropertyEditor(new ListModelPropertyEditor<EnteModel>() {
		//
		//			@Override
		//			public String getStringValue(EnteModel value) {
		//				
		//				return value.getDenominazione();
		//			}
		//
		//			@Override
		//			public EnteModel convertStringValue(String value) {
		//				enteLoader.load();
		//				for(EnteModel e :listStoreEnte.getModels()){
		//					if(e.getDenominazione().equalsIgnoreCase(value)){
		//						EnteModel tmpEnteModel = e;
		//						tmpEnteModel.setDenominazione(Utils.removeBRtag(e.getDenominazione()));
		//						return tmpEnteModel;
		//					}
		//				}
		//				return biblioteca.getEnte();
		//			}
		//		});

		denominazioneEnteField.addSelectionChangedListener(new SelectionChangedListener<EnteModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<EnteModel> se) {
				if(se.getSelectedItem()!=null){
					if(denominazioneEnteField.getValue()!=null && denominazioneEnteField.getOriginalValue()!=null) {

						if(denominazioneEnteField.getValue().getIdEnte()==denominazioneEnteField.getOriginalValue().getIdEnte()) {
							Utils.setFontColorStyleBlack(denominazioneEnteLabel);
						}else{
							Utils.setFontColorStyleRed(denominazioneEnteLabel);
						}
					}
				}
			}
		});

		enteAppartenezaTable.add(denominazioneEnteField, d2);

		tipologiaAmministrativaLabel = new Text("Tipologia amministrativa:");
		// Inserire auto completamento
		tipologiaAmministrativaLabel.setStyleAttribute("fontSize", "14px");
		enteAppartenezaTable.add(tipologiaAmministrativaLabel, d);

		RpcProxy<PagingLoadResult<VoceUnicaModel>> proxyTipologieAmministrative = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				// ID associato tabella tipologie funzionali
				tabelleDinamicheService
				.getListaVociFiltratePerPaginazioneCombobox(CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX,
						(ModelData) loadConfig, callback);
			}

		};
		ModelReader tipAmmReader = new ModelReader();

		PagingLoader<PagingLoadResult<VoceUnicaModel>> loaderTipAmm = new BasePagingLoader<PagingLoadResult<VoceUnicaModel>>(
				proxyTipologieAmministrative, tipAmmReader);
		loaderTipAmm.setLimit(10);
		ListStore<VoceUnicaModel> listStoreTipAmm = new ListStore<VoceUnicaModel>(
				loaderTipAmm);

		tipologiaAmministrativaField = new ComboBoxForBeans<VoceUnicaModel>();
		tipologiaAmministrativaField.setWidth(400);
		tipologiaAmministrativaField.setFieldLabel("Tipologia amministrativa");
		tipologiaAmministrativaField.setDisplayField("entry");
		tipologiaAmministrativaField.setStore(listStoreTipAmm);
		tipologiaAmministrativaField.setFireChangeEventOnSetValue(true);
		tipologiaAmministrativaField.setEmptyText("Scegli una tipologia...");
		tipologiaAmministrativaField.setForceSelection(true);
		tipologiaAmministrativaField.setLazyRender(false);
		tipologiaAmministrativaField.setTriggerAction(TriggerAction.ALL);
		tipologiaAmministrativaField.setAllowBlank(false);
		tipologiaAmministrativaField.setPageSize(10);
		tipologiaAmministrativaField.setEditable(true);
		tipologiaAmministrativaField.setTypeAhead(false);
		tipologiaAmministrativaField.setMinChars(1);


		tipologiaAmministrativaField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null){
					if(tipologiaAmministrativaField.getValue()!=null && tipologiaAmministrativaField.getOriginalValue()!=null) {

						if(tipologiaAmministrativaField.getValue().getIdRecord()==tipologiaAmministrativaField.getOriginalValue().getIdRecord()) {
							Utils.setFontColorStyleBlack(tipologiaAmministrativaLabel);
						}else{
							Utils.setFontColorStyleRed(tipologiaAmministrativaLabel);
						}
					}
				}
			}
		});

		enteAppartenezaTable.add(tipologiaAmministrativaField, d2);


		statoAppartenenzaLabel = new Text("Eventuale stato estero di appartenenza:");
		// Inserire auto completamento
		statoAppartenenzaLabel.setStyleAttribute("fontSize", "14px");
		enteAppartenezaTable.add(statoAppartenenzaLabel, d);

		RpcProxy<PagingLoadResult<StatoModel>> proxyStato = new RpcProxy<PagingLoadResult<StatoModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<StatoModel>> callback) {
				// ID associato tabella tipologie funzionali
				locationService.getStatiPaginatiFilteredPerCombos(
						(ModelData) loadConfig, callback);

			}

		};

		ModelReader statoReader = new ModelReader();

		PagingLoader<PagingLoadResult<StatoModel>> statoLoader = new BasePagingLoader<PagingLoadResult<StatoModel>>(
				proxyStato, statoReader);
		loaderTipAmm.setLimit(10);
		ListStore<StatoModel> listStoreStato = new ListStore<StatoModel>(
				statoLoader);

		statoAppartenenzaField = new ComboBoxForBeans<StatoModel>();
		statoAppartenenzaField.setWidth(400);
		statoAppartenenzaField.setFieldLabel("Stato di appartenenza");
		statoAppartenenzaField.setDisplayField("denominazione");
		statoAppartenenzaField.setStore(listStoreStato);
		statoAppartenenzaField.setFireChangeEventOnSetValue(true);
		statoAppartenenzaField.setEmptyText("Scegli uno stato...");
		statoAppartenenzaField.setForceSelection(true);
		statoAppartenenzaField.setLazyRender(false);
		statoAppartenenzaField.setTriggerAction(TriggerAction.ALL);
		statoAppartenenzaField.setPageSize(10);
		statoAppartenenzaField.setEditable(true);
		statoAppartenenzaField.setAllowBlank(false);
		statoAppartenenzaField.setTypeAhead(false);
		statoAppartenenzaField.setMinChars(1);

		enteAppartenezaTable.add(statoAppartenenzaField, d2);

		statoAppartenenzaField.addSelectionChangedListener(new SelectionChangedListener<StatoModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<StatoModel> se) {
				if(se.getSelectedItem()!=null){
					if(statoAppartenenzaField.getValue()!=null && statoAppartenenzaField.getOriginalValue()!=null) {

						if(statoAppartenenzaField.getValue().getIdStato()==statoAppartenenzaField.getOriginalValue().getIdStato()) {
							Utils.setFontColorStyleBlack(statoAppartenenzaLabel);
						}else{
							Utils.setFontColorStyleRed(statoAppartenenzaLabel);
						}
					}
				}
			}
		});

		denominazioneEnteField.addSelectionChangedListener(new SelectionChangedListener<EnteModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<EnteModel> se) {
				/*
				 * Setto i campi tipologia amministrativa e stato in
				 * base all'ente selezionato
				 */
				if(se.getSelectedItem()!=null){
					if(se.getSelectedItem().getEnteTipologiaAmministrativa()!=null){
						tipologiaAmministrativaField.setValue(se.getSelectedItem().getEnteTipologiaAmministrativa());
					}
					if(se.getSelectedItem().getStato()!=null)
						statoAppartenenzaField.setValue(se.getSelectedItem().getStato());
				}
			}
		});
		aggiornaEnteAppartenenza = new Button("Crea l'ente di appartenenza");
		Utils.setStylesButton(aggiornaEnteAppartenenza);

		
		FormButtonBinding bindEnteAppartenenza = new FormButtonBinding(formEnteAppartenenza);
		bindEnteAppartenenza.addButton(aggiornaEnteAppartenenza);

		aggiornaEnteAppartenenza.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();

							String denominazioneTmp=null;
							if(denominazioneEnteField.getValue()==null){
								denominazioneTmp=denominazioneEnteField.getRawValue();
							}else{
								denominazioneTmp=denominazioneEnteField.getValue().getDenominazione();
							}
							params.put("denominazione",denominazioneTmp);
							if(tipologiaAmministrativaField.getValue()==null){
								AbiMessageBox.messageAlertBox(AbiMessageBox.INVALID_PARAMETER_MESSAGE+" Tipologia amministrativa", AbiMessageBox.INVALID_PARAMETER_TITLE);
								return;
							}
							params.put("enteTipologiaAmministrativa",tipologiaAmministrativaField.getValue());
							if(statoAppartenenzaField.getValue()==null){
								AbiMessageBox.messageAlertBox(AbiMessageBox.INVALID_PARAMETER_MESSAGE+" Stato", AbiMessageBox.INVALID_PARAMETER_TITLE);
								return;
							}
							params.put("stato",statoAppartenenzaField.getValue());

							bibliotecheService.setEnte(biblioteca.getIdBiblio(), params, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									fireReloadbiblioDataEvent();
									/*Setto le labels di colore nero al form ente*/
									setBlackLabelsFormEnte();
									/*Setto i nuovi parametri di originalValue()*/
									denominazioneEnteField.setOriginalValue((denominazioneEnteField.getValue()!=null?denominazioneEnteField.getValue():null));
									tipologiaAmministrativaField.setOriginalValue((tipologiaAmministrativaField.getValue()!=null?tipologiaAmministrativaField.getValue():null));
									statoAppartenenzaField.setOriginalValue((statoAppartenenzaField.getValue()!=null?statoAppartenenzaField.getValue():null));
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});

		resetFormEnte = new Button("Reset");
		Utils.setStylesButton(resetFormEnte);

		bindEnteAppartenenza.addButton(resetFormEnte);
		resetFormEnte.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formEnteAppartenenza.reset();
				setBlackLabelsFormEnte();
			}
		});
		
		TableLayout tableLayoutEnte = new TableLayout(2);
		tableLayoutEnte.setCellPadding(5);
		LayoutContainer layoutEnteButtons = new LayoutContainer(tableLayoutEnte);
		layoutEnteButtons.add(aggiornaEnteAppartenenza);
		layoutEnteButtons.add(resetFormEnte);

		enteAppartenezaTable.add(new LayoutContainer(), d);
		enteAppartenezaTable.add(layoutEnteButtons, d2);

		formEnteAppartenenza.add(enteAppartenezaTable);
		enteAppartenenzaSet.add(formEnteAppartenenza);
		enteAppartenenza.add(enteAppartenenzaSet);
		add(enteAppartenenza);

		/* FINE----ENTE DI APPARTENENZA */
		/* Setto i valori dei campi */


	}



	/**
	 * @param denominazioneEnteLabel
	 * @param tipologiaAmministrativaLabel
	 * @param statoAppartenenzaLabel
	 */
	private void setBlackLabelsFormEnte() {
		Utils.setFontColorStyleBlack(denominazioneEnteLabel);
		Utils.setFontColorStyleBlack(statoAppartenenzaLabel);
		Utils.setFontColorStyleBlack(tipologiaAmministrativaLabel);
	}

	public void autonomiaAmministrativaForm() {

		/* AUTONOMIA AMMINISTRATIVA DELLA BIBLIOTECA */
		LayoutContainer autonomiaAmministrativa = new LayoutContainer();
		autonomiaAmministrativa.setStyleAttribute("padding", "5px");

		FieldSet autonomiaAmministrativaSet = new FieldSet();
		Utils.setFieldSetProperties(autonomiaAmministrativaSet, "Autonomia amministrativa della biblioteca");

		formAutonomiaAmministrativa = new FormPanel();
		formAutonomiaAmministrativa.setHeaderVisible(false);
		formAutonomiaAmministrativa.setBorders(false);
		formAutonomiaAmministrativa.setBodyBorder(false);

		FormLayout formLayoutAutonomiaAmministrativa = new FormLayout();
		formLayoutAutonomiaAmministrativa.setLabelAlign(LabelAlign.TOP);

		formAutonomiaAmministrativa.setLayout(formLayoutAutonomiaAmministrativa);

		LayoutContainer autonomiaAmministrativaTable = new LayoutContainer(
				new TableLayout(2));

		autonomiaAmministrativaLabel = new Text("Autonomia amministrativa:");
		// Inserire auto completamento
		autonomiaAmministrativaLabel.setStyleAttribute("fontSize", "14px");
		autonomiaAmministrativaTable.add(autonomiaAmministrativaLabel, d);

		autonomiaAmministrativaField = new SimpleComboBox<String>();
		autonomiaAmministrativaField.setTriggerAction(TriggerAction.ALL);
		autonomiaAmministrativaField.setEditable(false);
		autonomiaAmministrativaField.setFireChangeEventOnSetValue(true);

		autonomiaAmministrativaField.add("Non specificata");
		autonomiaAmministrativaField.add("Si");
		autonomiaAmministrativaField.add("No");

		autonomiaAmministrativaField.setSimpleValue("Non specificata");

		Utils.addListenerToChangeLabelColorIfModifiedSimpleComboboxString(autonomiaAmministrativaField,autonomiaAmministrativaLabel);
		
		autonomiaAmministrativaTable.add(autonomiaAmministrativaField, d);
		strutturaGerarchicaSovranumerariaLabel = new Text("Struttura gerarchica sovraordinata:");

		strutturaGerarchicaSovranumerariaLabel.setStyleAttribute("fontSize", "14px");
		autonomiaAmministrativaTable.add(strutturaGerarchicaSovranumerariaLabel, d);

		strutturaGerarchicaSovranumerariaField = new TextField<String>();
		strutturaGerarchicaSovranumerariaField.setWidth(400);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(strutturaGerarchicaSovranumerariaField,strutturaGerarchicaSovranumerariaLabel );

		autonomiaAmministrativaTable.add(strutturaGerarchicaSovranumerariaField, d2);

		aggiornaAutonomiaAmministrativa = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaAutonomiaAmministrativa);
		aggiornaAutonomiaAmministrativa.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();

							if((( autonomiaAmministrativaField.getRawValue()).compareToIgnoreCase("Non specificata")==0)){
								params.put("autonomiaAmministrativa",null);
							}else if((( autonomiaAmministrativaField.getRawValue()).compareToIgnoreCase("Si")==0)){
								params.put("autonomiaAmministrativa",true);
							}else if((( autonomiaAmministrativaField.getRawValue()).compareToIgnoreCase("No")==0)){
								params.put("autonomiaAmministrativa",false);
							}
							params.put("strutturaGerarchicaSovranumeraria",strutturaGerarchicaSovranumerariaField.getValue());


							bibliotecheService.setAutonomiaAmministrativa(biblioteca.getIdBiblio(),	params, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									fireReloadbiblioDataEvent();
									/*Setto le label di colore nero*/
									setBlackLabelsFormAutonomiaAmministrativa();
									/*Setto gli original value con i nuovi valori*/ 
									autonomiaAmministrativaField.setOriginalValue(autonomiaAmministrativaField.getValue());
									strutturaGerarchicaSovranumerariaField.setOriginalValue(strutturaGerarchicaSovranumerariaField.getValue());
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetAutonominaAmministrativa = new Button("Reset");
		Utils.setStylesButton(resetAutonominaAmministrativa);
		resetAutonominaAmministrativa.disable();
		resetAutonominaAmministrativa.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formAutonomiaAmministrativa.reset();
				setBlackLabelsFormAutonomiaAmministrativa();
			}
		});

		FormButtonBinding bindAutonomiaAmministrativa = new FormButtonBinding(formAutonomiaAmministrativa);
		bindAutonomiaAmministrativa.addButton(aggiornaAutonomiaAmministrativa);
		bindAutonomiaAmministrativa.addButton(resetAutonominaAmministrativa);

		TableLayout tableLayoutAutAmm = new TableLayout(2);
		tableLayoutAutAmm.setCellPadding(5);
		LayoutContainer layoutAutAmmButtons = new LayoutContainer(tableLayoutAutAmm);
		layoutAutAmmButtons.add(aggiornaAutonomiaAmministrativa);
		layoutAutAmmButtons.add(resetAutonominaAmministrativa);
		
		autonomiaAmministrativaTable.add(new LayoutContainer(), d);
		autonomiaAmministrativaTable.add(layoutAutAmmButtons, d2);

		formAutonomiaAmministrativa.add(autonomiaAmministrativaTable);
		autonomiaAmministrativaSet.add(formAutonomiaAmministrativa);
		autonomiaAmministrativa.add(autonomiaAmministrativaSet);
		add(autonomiaAmministrativa);
		/* FINE---AUTONOMIA AMMINISTRATIVA DELLA BIBLIOTECA */


	}

	public void setBlackLabelsFormAutonomiaAmministrativa() {
		Utils.setFontColorStyleBlack(autonomiaAmministrativaLabel);
		Utils.setFontColorStyleBlack(strutturaGerarchicaSovranumerariaLabel);
	}

	public void tipologiaFunzionaleForm() {
		/* TIPOLOGIA FUNZIONALE */
		LayoutContainer tipologiaFunzionale = new LayoutContainer();
		tipologiaFunzionale.setStyleAttribute("padding", "5px");

		FieldSet tipologiaFunzionaleSet = new FieldSet();
		Utils.setFieldSetProperties(tipologiaFunzionaleSet, "Tipologia funzionale");

		formTipologiaFunzionale = new FormPanel();
		formTipologiaFunzionale.setHeaderVisible(false);
		formTipologiaFunzionale.setBorders(false);
		formTipologiaFunzionale.setBodyBorder(false);

		FormLayout formLayoutTipologiaFunzionale = new FormLayout();
		formLayoutTipologiaFunzionale.setLabelAlign(LabelAlign.TOP);

		formTipologiaFunzionale.setLayout(formLayoutTipologiaFunzionale);
		TableLayout t =	new TableLayout(3);
		t.setWidth("600");
		LayoutContainer tipologiaFunzionaleTable = new LayoutContainer(t);

		tipFunzLabel = new Text("Tipologia funzionale:");
		tipFunzLabel.setStyleAttribute("fontSize", "14px");
		tipologiaFunzionaleTable.add(tipFunzLabel, new TableData("160px","30px"));

		RpcProxy<List<VoceUnicaModel>> proxyTipologieFunzionali = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI_INDEX, callback);
			}

		};
		ModelReader tipFunzReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipFunz = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyTipologieFunzionali, tipFunzReader);

		ListStore<VoceUnicaModel> listStoreTipFunz = new ListStore<VoceUnicaModel>(
				loaderTipFunz);

		tipFunzField = new ComboBox<VoceUnicaModel>();

		tipFunzField.setFieldLabel("Tipologia funzionale");
		tipFunzField.setWidth(250);
		tipFunzField.setDisplayField("entry");
		tipFunzField.setStore(listStoreTipFunz);
		tipFunzField.setAllowBlank(false);
		tipFunzField.setFireChangeEventOnSetValue(true);
		tipFunzField.setEmptyText("Scegli una tipologia...");
		tipFunzField.setLazyRender(false);
		tipFunzField.setTriggerAction(TriggerAction.ALL);
		tipFunzField.setForceSelection(false);
		tipFunzField.setEditable(false);
		loaderTipFunz.load();

		tipFunzField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null){
					if(tipFunzField.getValue()!=null && tipFunzField.getOriginalValue()!=null) {

						if(tipFunzField.getValue().getIdRecord()==tipFunzField.getOriginalValue().getIdRecord()) {
							Utils.setFontColorStyleBlack(tipFunzLabel);
						}else{
							Utils.setFontColorStyleRed(tipFunzLabel);
						}
					}
				}
			}
		});

		tipologiaFunzionaleTable.add(tipFunzField, d);

		tipFunzAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(tipFunzAggiorna);
		tipFunzAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							bibliotecheService.setTipologiaFunzionale(biblioteca.getIdBiblio(),
									tipFunzField.getValue(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									fireReloadbiblioDataEvent();
									/*Setto le label di colore nero*/
									Utils.setFontColorStyleBlack(tipFunzLabel);
									/*Setto gli originalValue() con i nuovi valori*/ 
									tipFunzField.setOriginalValue(tipFunzField.getValue());
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});


		resetTipologiaFunzionale = new Button("Reset");
		Utils.setStylesButton(resetTipologiaFunzionale);
		resetTipologiaFunzionale.disable();
		resetTipologiaFunzionale.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formTipologiaFunzionale.reset();
				Utils.setFontColorStyleBlack(tipFunzLabel);
			}
		});
		
		FormButtonBinding bindTipFunz = new FormButtonBinding(formTipologiaFunzionale);
		bindTipFunz.addButton(tipFunzAggiorna);
		bindTipFunz.addButton(resetTipologiaFunzionale);
		
		TableLayout tableLayoutTipFunz = new TableLayout(2);
		tableLayoutTipFunz.setCellPadding(5);
		LayoutContainer layoutTipFunzButtons = new LayoutContainer(tableLayoutTipFunz);
		layoutTipFunzButtons.add(tipFunzAggiorna);
		layoutTipFunzButtons.add(resetTipologiaFunzionale);		

		tipologiaFunzionaleTable.add(layoutTipFunzButtons, d);
		
		formTipologiaFunzionale.add(tipologiaFunzionaleTable);
		tipologiaFunzionaleSet.add(formTipologiaFunzionale);
		tipologiaFunzionale.add(tipologiaFunzionaleSet);
		add(tipologiaFunzionale);
		/* FINE---TIPOLOGIA FUNZIONALE */
	}

	public void fondazioneBibliotecaForm() {
		/* FONDAZIONE DELLA BIBLIOTECA */
		LayoutContainer fondazioneBiblioteca = new LayoutContainer();
		fondazioneBiblioteca.setStyleAttribute("padding", "5px");

		FieldSet fondazioneBibliotecaSet = new FieldSet();
		Utils.setFieldSetProperties(fondazioneBibliotecaSet, "Fondazione");

		fondazioneBibliotecaForm = new FormPanel();
		fondazioneBibliotecaForm.setHeaderVisible(false);
		fondazioneBibliotecaForm.setBorders(false);
		fondazioneBibliotecaForm.setBodyBorder(false);

		FormLayout fondazioneBibliotecaFormLayout = new FormLayout();
		fondazioneBibliotecaFormLayout.setLabelAlign(LabelAlign.TOP);
		
		TableLayout tableFondazione = new TableLayout(5);
		tableFondazione.setWidth("700px");
		
		LayoutContainer dataFondazioneTable = new LayoutContainer();
		dataFondazioneTable.setLayout(tableFondazione);
		
		dataFondazioneLabel = new Text("Data dell'edificio:");
		dataFondazioneLabel.setStyleAttribute("fontSize", "14px");
		dataFondazioneTable.add(dataFondazioneLabel, new TableData("160px","30px"));

		dataFondazioneField = new TextField<String>();
		dataFondazioneField.setWidth(100);
		dataFondazioneTable.add(dataFondazioneField, d3);

		Utils.addListenerToChangeLabelColorIfModifiedTextField(dataFondazioneField, dataFondazioneLabel);

		istituzioneAttualeLabel = new Text("Data istituzione:");
		istituzioneAttualeLabel.setStyleAttribute("fontSize", "14px");
		dataFondazioneTable.add(istituzioneAttualeLabel, new TableData("160px","30px"));

		istituzioneAttualeField = new TextField<String>();
		istituzioneAttualeField.setWidth(100);
	
		Utils.addListenerToChangeLabelColorIfModifiedTextField(istituzioneAttualeField, istituzioneAttualeLabel);
		dataFondazioneTable.add(istituzioneAttualeField, d3);

		dataFondazioneAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(dataFondazioneAggiorna);
		dataFondazioneAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();

							params.put("dataFondazione",dataFondazioneField.getValue());
							params.put("dataIstituzione",istituzioneAttualeField.getValue());



							bibliotecheService.setInfoFondazione(biblioteca.getIdBiblio(),
									params, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									fireReloadbiblioDataEvent();

									setBlackLabelsFondazioneForm();
									
									dataFondazioneField.setOriginalValue(dataFondazioneField.getOriginalValue());
									istituzioneAttualeField.setOriginalValue(istituzioneAttualeField.getOriginalValue());
								
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetFondazione = new Button("Reset");
		Utils.setStylesButton(resetFondazione);
		resetFondazione.disable();
		resetFondazione.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				fondazioneBibliotecaForm.reset();
				setBlackLabelsFondazioneForm();
			}
		});

		FormButtonBinding bindDataFormazione = new FormButtonBinding(fondazioneBibliotecaForm);
		bindDataFormazione.addButton(dataFondazioneAggiorna);
		bindDataFormazione.addButton(resetFondazione);
		
		TableLayout tableLayoutFondaz = new TableLayout(2);
		tableLayoutFondaz.setCellPadding(5);
		LayoutContainer layoutFondazButtons = new LayoutContainer(tableLayoutFondaz);
		layoutFondazButtons.add(dataFondazioneAggiorna);
		layoutFondazButtons.add(resetFondazione);

		dataFondazioneTable.add(layoutFondazButtons, d3);

		fondazioneBibliotecaForm.add(dataFondazioneTable);
		fondazioneBibliotecaSet.add(fondazioneBibliotecaForm);
		fondazioneBiblioteca.add(fondazioneBibliotecaSet);
		add(fondazioneBiblioteca);
		/* FINE FONDAZIONE DELLA BIBLIOTECA */
		
	}

	/**
	 * 
	 */
	public void setBlackLabelsFondazioneForm() {
		Utils.setFontColorStyleBlack(dataFondazioneLabel);
		Utils.setFontColorStyleBlack(istituzioneAttualeLabel);
	}

	public void setFieldValues() {
		if (biblioteca != null) {
			// Ente
			denominazioneEnteField.setValue(biblioteca.getEnte());
			denominazioneEnteField.setOriginalValue(biblioteca.getEnte());

			if(biblioteca.getEnte().getEnteTipologiaAmministrativa()!=null){
				tipologiaAmministrativaField.setValue(biblioteca.getEnte().getEnteTipologiaAmministrativa());
				tipologiaAmministrativaField.setOriginalValue(biblioteca.getEnte().getEnteTipologiaAmministrativa());
			}
			if(biblioteca.getEnte().getStato()!=null){
				statoAppartenenzaField.setValue(biblioteca.getEnte().getStato());
				statoAppartenenzaField.setOriginalValue(biblioteca.getEnte().getStato());
			}
			setBlackLabelsFormEnte();

			// Autonomia ammisistrativa
			if (biblioteca.getAutonomiaAmministrativa() == null) {
				autonomiaAmministrativaField.setSimpleValue("Non specificata");
			} else if (biblioteca.getAutonomiaAmministrativa() == true) {
				autonomiaAmministrativaField.setSimpleValue("Si");
			} else if (biblioteca.getAutonomiaAmministrativa() == false) {
				autonomiaAmministrativaField.setSimpleValue("No");
			}
			strutturaGerarchicaSovranumerariaField.setValue(biblioteca.getStruttura_gerarchica_sovraordinata());
			strutturaGerarchicaSovranumerariaField.setOriginalValue(biblioteca.getStruttura_gerarchica_sovraordinata());
			setBlackLabelsFormAutonomiaAmministrativa();
			// TIPOLOGIA FUNZIONALE
			tipFunzField.setValue(biblioteca.getTipologiaFunzionale());
			tipFunzField.setOriginalValue(biblioteca.getTipologiaFunzionale());
			Utils.setFontColorStyleBlack(tipFunzLabel);
			// FONDAZIONE DELLA BIBLIOTECA
			dataFondazioneField.setValue(biblioteca.getDataFondazione());
			dataFondazioneField.setOriginalValue(biblioteca.getDataFondazione());
			
			istituzioneAttualeField.setValue(biblioteca.getDataIstituzione());
			istituzioneAttualeField.setOriginalValue(biblioteca.getDataIstituzione());
		}

		UIWorkflow.setReadOnly(denominazioneEnteField);
		UIWorkflow.setReadOnly(tipologiaAmministrativaField);
		UIWorkflow.setReadOnly(statoAppartenenzaField);
		UIWorkflow.setReadOnly(autonomiaAmministrativaField);
		UIWorkflow.setReadOnly(strutturaGerarchicaSovranumerariaField);

		UIWorkflow.setReadOnly(tipFunzField);

		UIWorkflow.setReadOnly(dataFondazioneField);
		UIWorkflow.setReadOnly(istituzioneAttualeField);

		UIWorkflow.hideView(aggiornaEnteAppartenenza);
		UIWorkflow.hideView(resetFormEnte);
		UIWorkflow.hideView(aggiornaAutonomiaAmministrativa);
		UIWorkflow.hideView(resetAutonominaAmministrativa);		
		UIWorkflow.hideView(tipFunzAggiorna);
		UIWorkflow.hideView(resetTipologiaFunzionale);
		UIWorkflow.hideView(dataFondazioneAggiorna);
		UIWorkflow.hideView(resetFondazione);
		
		if(UIWorkflow.isReadOnly()==false){
			addKeyListenerForEnter();
		}else{
			removeKeyListenerForEnter();
		}
	}

	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(aggiornaEnteAppartenenza, formEnteAppartenenza);
		Utils.addKeyListenerForEnter(aggiornaAutonomiaAmministrativa, formAutonomiaAmministrativa);
		Utils.addKeyListenerForEnter(tipFunzAggiorna, formTipologiaFunzionale);
		Utils.addKeyListenerForEnter(dataFondazioneAggiorna, fondazioneBibliotecaForm);
	}
	
	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( formEnteAppartenenza);
		Utils.removeKeyListenerForEnter( formAutonomiaAmministrativa);
		Utils.removeKeyListenerForEnter( formTipologiaFunzionale);
		Utils.removeKeyListenerForEnter( fondazioneBibliotecaForm);

		
	}
}
