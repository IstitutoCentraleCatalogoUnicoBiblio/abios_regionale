package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.EntryNotFoundClientSideException;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
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
 * per la visualizzazione / modifica delle informazioni relative ai cataloghi collettivi
 *
 */
public class CreazioneModificaCataloghiCollettiviPanel extends CreazioneModificaCataloghiGenericaPanel{

	/*Label cataloghi collettivi*/
	protected  Text denominazioneCatalogoLabel;
	protected  Text zonaEspansioneTipoLabel;
	protected  Text zonaDettaglioLabel;
	protected  Text denominazioneMaterialeLabel;
	/*Campi cataloghi collettivi*/
	private ComboBox<PartecipaCataloghiCollettiviModel> denominazioneCatalogoField;
	private	ComboBox<VoceUnicaModel> zonaEspansioneTipoField;
	private	TextFieldCustom<String> zonaDettaglioField;
	private ComboBox<PatrimonioSpecializzazioneModel> denominazioneMaterialeField;

	private PartecipaCataloghiCollettiviModel model;
	private ListStore<PatrimonioSpecializzazioneModel> denominazioneMaterialeStore;
	private PagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>> tipologiaPatrimonioLoader;
	/*TabelData per labels e fields per tipologia materiale */
	private TableData materialeLabelData;

	private int materialeLabelWidth=200;	
	private boolean firstLoad=true;
	public CreazioneModificaCataloghiCollettiviPanel(boolean modifica) {
		super();
		this.modifica=modifica;
		//Setto il tipo di catalogo
		this.tipoCatalogo=2;

		createFieldsAndLabelsCatalogoCollettivoForm();
		createNuovoCatalogoCollettivoForm();
		setWidth(600);

		materialeLabelData = new TableData();
		materialeLabelData.setWidth(materialeLabelWidth+"px");
		materialeLabelData.setMargin(5);

	}

	public void createFieldsAndLabelsCatalogoCollettivoForm(){

		denominazioneCatalogoLabel = new Text("Denominazione:");
		denominazioneCatalogoLabel.setStyleAttribute("fontSize", "12px");

		zonaEspansioneTipoLabel = new Text("Zona espansione:");
		zonaEspansioneTipoLabel.setStyleAttribute("fontSize", "12px");

		zonaDettaglioLabel = new Text("Dettaglio zona:");
		zonaDettaglioLabel.setStyleAttribute("fontSize", "12px");

		denominazioneMaterialeLabel = new Text("Tipo Materiale:");
		denominazioneMaterialeLabel.setStyleAttribute("fontSize", "12px");

		RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>> denominazioneCatalogoProxy = new RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<PartecipaCataloghiCollettiviModel>> callback) {
				tdsa.getListaCataloghiCollettiviPossibiliPerPaginazioneCombobox((ModelData)loadConfig, callback);
			}
		};

		ModelReader denominazioneCatalogoReader = new ModelReader();

		final PagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>> denominazioneCatalogoLoader = new BasePagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>>(
				denominazioneCatalogoProxy, denominazioneCatalogoReader);
		denominazioneCatalogoLoader.setLimit(10);

		final ListStore<PartecipaCataloghiCollettiviModel> denominazioneCatalogoStore = new ListStore<PartecipaCataloghiCollettiviModel>(
				denominazioneCatalogoLoader);
		/*
		 * ComboBox paginata con autocompletamento per caricamento lista
		 * denominazioni cataloghi collettivi possibili
		 */
		denominazioneCatalogoField = new ComboBox<PartecipaCataloghiCollettiviModel>();
		denominazioneCatalogoField.setDisplayField("denominazioneCatalogo");
		denominazioneCatalogoField.setWidth(500);
		denominazioneCatalogoField.setFireChangeEventOnSetValue(true);
		denominazioneCatalogoField.setEmptyText("Descrizione...");
		denominazioneCatalogoField.setForceSelection(true);
		denominazioneCatalogoField.setLazyRender(false);
		denominazioneCatalogoField.setTriggerAction(TriggerAction.ALL);
		denominazioneCatalogoField.setAllowBlank(false);
		denominazioneCatalogoField.setEditable(true);		

		denominazioneCatalogoField.setSimpleTemplate(
				"" +
				"<b>{denominazioneCatalogo}</b><br>&nbsp;" +
				"Zona espansione:&nbsp;{entry}<br>&nbsp;" +
				"Zona dettaglio:&nbsp;{zona}" +
		"");

		denominazioneCatalogoField.setTypeAhead(false);
		denominazioneCatalogoField.setMinChars(1);
		denominazioneCatalogoField.setPageSize(10);
		denominazioneCatalogoField.setStore(denominazioneCatalogoStore);


		RpcProxy<List<VoceUnicaModel>> zonaEspansioneProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				//Tabella dinamica: cataloghi_collettivi_zona_tipo id=36
				tdsa.getListaVoci(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX,callback);
			}

		};

		ModelReader zonaEspansioneReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> zonaEspansioneLoader  = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				zonaEspansioneProxy, zonaEspansioneReader);

		final ListStore<VoceUnicaModel> zonaEspansioneStore = new ListStore<VoceUnicaModel>(
				zonaEspansioneLoader);
		zonaEspansioneLoader.load();
		//ComboBox per caricamento tipologie zona di espansione
		zonaEspansioneTipoField = new ComboBox<VoceUnicaModel>();
		zonaEspansioneTipoField.setDisplayField("entry");
		zonaEspansioneTipoField.setFieldLabel("entry");
		zonaEspansioneTipoField.setFireChangeEventOnSetValue(true);
		zonaEspansioneTipoField.setEditable(false);
		zonaEspansioneTipoField.setWidth(200);
		zonaEspansioneTipoField.setEmptyText("Descrizione...");
		zonaEspansioneTipoField.setForceSelection(false);
		zonaEspansioneTipoField.setLazyRender(false);
		zonaEspansioneTipoField.setTriggerAction(TriggerAction.ALL);
		zonaEspansioneTipoField.setAllowBlank(false);
		zonaEspansioneTipoField.setStore(zonaEspansioneStore);

		zonaDettaglioField = new TextFieldCustom<String>();
		zonaDettaglioField.setEmptyText("Dettaglio...");
		zonaDettaglioField.setMaxLength(50);
		zonaDettaglioField.setWidth(500);

		/*
		 * Aggiungo un listener al Loader dei cataloghi possibili, per filtrare
		 * gli elementi caricati in base ai parametri id zona di espansione e
		 * dettaglio zona di espansione. Questi vengono aggiunti automaticamente
		 * all'hashmap contenuta nell'oggetto (ModelData)loadconfig passato come
		 * parametro al metodo load() dell' oggetto proxy che richiama il servizio.
		 */
		denominazioneCatalogoLoader.addListener(Loader.BeforeLoad,new Listener<LoadEvent>() {
			public void handleEvent(LoadEvent be) {  
				int tmpIdZonaTipo=0;
				String tmpDettaglioZona=null;
				if(zonaEspansioneTipoField.getValue()!=null){
					tmpIdZonaTipo=zonaEspansioneTipoField.getValue().getIdRecord();
				}
				if(zonaDettaglioField.getValue()!=null){
					tmpDettaglioZona=zonaDettaglioField.getValue();
				}
				be.<ModelData> getConfig().set("idZonaEspansione", tmpIdZonaTipo);
				be.<ModelData> getConfig().set("dettaglioZona", tmpDettaglioZona);
			}  
		});  


		/*
		 * Quando viene selezionato un oggetto dalla lista dei cataloghi
		 * possibili vengono settati automaticamente anche i campi relativi al
		 * tipo di zona di espansione e al dettaglio della zona con i valori
		 * dell'oggetto selezionato nella combobox
		 */
		denominazioneCatalogoField.addSelectionChangedListener(new SelectionChangedListener<PartecipaCataloghiCollettiviModel>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<PartecipaCataloghiCollettiviModel> se) {
				if(se.getSelectedItem()!=null){
					PartecipaCataloghiCollettiviModel tmp = se.getSelectedItem();
					/*
					 * Assegno i valori dell'elemento selezionato dalla
					 * combobox della denominazione, ai due campi per il
					 * tipo zona e dettaglio zona
					 */
					if(tmp.getZonaEspansione()!=null){
						zonaEspansioneTipoField.setValue(tmp.getZonaEspansione());
					}
					if (tmp.getDettaglioZona()!=null){
						zonaDettaglioField.setValue(tmp.getDettaglioZona());
					}
				}
			}
		});

		//denominazioneMateriale
		RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>> denominazioneMaterialeProxy = new RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<PatrimonioSpecializzazioneModel>> callback) {
				tdsa.getTipologiePatrimonioFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);

			}

		};

		ModelReader denominazioneMaterialeReader = new ModelReader();

		tipologiaPatrimonioLoader = new BasePagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>>(
				denominazioneMaterialeProxy, denominazioneMaterialeReader);
		tipologiaPatrimonioLoader.setLimit(10);
		tipologiaPatrimonioLoader.load();
		denominazioneMaterialeStore = new ListStore<PatrimonioSpecializzazioneModel>(
				tipologiaPatrimonioLoader);

		denominazioneMaterialeField = new ComboBox<PatrimonioSpecializzazioneModel>();
		denominazioneMaterialeField.setMinListWidth(400);
		denominazioneMaterialeField.setDisplayField("denominazioneMateriale");
		denominazioneMaterialeField.setFieldLabel("denominazioneMateriale");
		denominazioneMaterialeField.setFireChangeEventOnSetValue(true);
		denominazioneMaterialeField.setEmptyText("Materiale...");
		denominazioneMaterialeField.setWidth(500);
		denominazioneMaterialeField.setLazyRender(false);
		denominazioneMaterialeField.setTriggerAction(TriggerAction.ALL);
		denominazioneMaterialeField.setAllowBlank(modifica);
		denominazioneMaterialeField.setForceSelection(true);
		denominazioneMaterialeField.setEditable(true);
		denominazioneMaterialeField.setTypeAhead(false);
		denominazioneMaterialeField.setMinChars(1);
		denominazioneMaterialeField.setPageSize(10);
		denominazioneMaterialeField.setSimpleTemplate(PatrimonioSpecializzazioneModel.getTemplateMateriali());
		denominazioneMaterialeField.setStore(denominazioneMaterialeStore);

		denominazioneMaterialeField.addSelectionChangedListener(new SelectionChangedListener<PatrimonioSpecializzazioneModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<PatrimonioSpecializzazioneModel> se) {

				if (se.getSelectedItem() != null) {
					if (se.getSelectedItem().getIdRecord() == -1) {
						se.setCancelled(true);
						MessageBox.info("INFO SELEZIONE","Le voci in grassetto sono relative ad una categoria."
								+ "Selezionare una voce relativa ad un elemento "
								+ "non in grassetto per continuare con l'operazione",null).show();
						denominazioneMaterialeField.reset();
					} else {
					}
				}
			}
		});



		createBaseFieldsAndLabels();
	}

	public void createNuovoCatalogoCollettivoForm(){

		Text descrizioneCatalogoEstensioneText = new Text("<b>Descrizione catalogo ed estensione</b>");
		LayoutContainer descrizioneCatalogoEstensioneTable = new LayoutContainer(new TableLayout(2));
		descrizioneCatalogoEstensioneTable.add(denominazioneCatalogoLabel,labelFormatiTableData);
		descrizioneCatalogoEstensioneTable.add(denominazioneCatalogoField);
		descrizioneCatalogoEstensioneTable.add(zonaEspansioneTipoLabel,labelFormatiTableData);
		descrizioneCatalogoEstensioneTable.add(zonaEspansioneTipoField);
		descrizioneCatalogoEstensioneTable.add(zonaDettaglioLabel,labelFormatiTableData);
		descrizioneCatalogoEstensioneTable.add(zonaDettaglioField);

		LayoutContainer materialeTable = new LayoutContainer(new TableLayout(2));

		Text materialeText = new Text("<b>Materiale</b>");

		materialeTable.add(denominazioneMaterialeLabel,labelFormatiTableData);
		materialeTable.add(denominazioneMaterialeField);

		add(descrizioneCatalogoEstensioneText);
		add(descrizioneCatalogoEstensioneTable);
		add(materialeText);
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

			tmp.add(buttonContainer);
			setBottomComponent(tmp);
		}else{
			aggiungiButtonInit();
			FormButtonBinding bind = new FormButtonBinding(this);
			bind.addButton(aggiungiButton);

			buttonContainer.add(aggiungiButton);

			tmp.add(buttonContainer);
			setBottomComponent(tmp);
		}
	}

	public LayoutContainer createModificaCatalogoFormLabels(){

		LayoutContainer tmpLabelContainer= createModificaBaseFormLabels();
		denominazioneMaterialeLabel.setStyleAttribute("fontSize", labelFontSize);
		denominazioneMaterialeLabel.setWidth(materialeLabelWidth);
		tmpLabelContainer.insert(denominazioneMaterialeLabel, 0,materialeLabelData);

		return tmpLabelContainer;
	}

	public LayoutContainer createModificaCatalogoFormFields(){
		LayoutContainer tmpFieldsContainer= createModificaBaseFormFields();
		denominazioneMaterialeField.setWidth(materialeLabelWidth);
		tmpFieldsContainer.insert(denominazioneMaterialeField, 0, materialeLabelData);

		aggiornaButtonInit();
		rimuoviButtonInit();

		tmpFieldsContainer.add(aggiornaButton,annoTabelData);
		tmpFieldsContainer.add(rimuoviButton,annoTabelData);

		if(UIWorkflow.isReadOnly()==false){
			aggiornaButton.show();
			rimuoviButton.show();
		}else{
			aggiornaButton.hide();
			rimuoviButton.hide();
		}

		return tmpFieldsContainer;
	}


	public void setValues(final PartecipaCataloghiCollettiviModel model){
		this.model=model;

		denominazioneCatalogoField.setValue(model);

		if(model.getIdZonaEspansione()!=null)
			zonaEspansioneTipoField.setValue(new VoceUnicaModel(model.getIdZonaEspansione(),model.getZonaEspansioneDescr()));

		if(model.getDettaglioZona()!=null)
			zonaDettaglioField.setValue(model.getDettaglioZona());

		//		tipologiaPatrimonioLoader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {  
		//			public void handleEvent(LoadEvent be) {  
		//				if(firstLoad){
		//					be.<ModelData> getConfig().set("query", model.getDenominazioneMateriale());  
		//					firstLoad=false;
		//				}
		//			}  
		//		}); 

		tipologiaPatrimonioLoader.addListener(Loader.Load, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				denominazioneMaterialeStore.add(new PatrimonioSpecializzazioneModel(model.getIdMateriale(), model.getDenominazioneMateriale(), model.getCondition()));
				denominazioneMaterialeField.setValue(new PatrimonioSpecializzazioneModel(model.getIdMateriale(), model.getDenominazioneMateriale(), model.getCondition()));
			}
		});
		tipologiaPatrimonioLoader.load();
		/*Richiamo il metodo ereditato che setta i campi comuni a tutti i cataloghi*/
		setBaseValues(model);

		if(UIWorkflow.isReadOnly()==false){
			denominazioneCatalogoField.enable();
			zonaEspansioneTipoField.enable();
			zonaDettaglioField.enable();
			denominazioneMaterialeField.enable();
		}else{
			denominazioneCatalogoField.disable();
			zonaEspansioneTipoField.disable();
			zonaDettaglioField.disable();
			denominazioneMaterialeField.disable();
		}

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

							PartecipaCataloghiCollettiviModel tmpCatalogo = new PartecipaCataloghiCollettiviModel();
							tmpCatalogo.setIdPartecipaCatalogo(model.getIdPartecipaCatalogo());
							tmpCatalogo.setDenominazioneCatalogo(model.getDenominazioneCatalogo());
							tmpCatalogo.setIdZonaEspansione(model.getIdZonaEspansione());
							tmpCatalogo.setZonaEspansioneDescr(model.getZonaEspansioneDescr());
							tmpCatalogo.setIdMateriale(denominazioneMaterialeField.getValue().getIdRecord());
							if(model.getDettaglioZona()!=null)
								tmpCatalogo.setDettaglioZona(model.getDettaglioZona());
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


							bibliotecheService.addPartecipaCatalogoCollettivo(
									idBiblio, tmpCatalogo,	true,	new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											modifica = false;
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											denominazioneCatalogoField.enable();
											fireCloseWindow();
											fireReleoadPanel();										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
												modifica = false;
												denominazioneCatalogoField.enable();
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
							bibliotecheService.removePartecipaCatalogoCollettivo(idRemove,idBiblio,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReleoadPanel();
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

							PartecipaCataloghiCollettiviModel tmpCatalogo = new PartecipaCataloghiCollettiviModel();
							tmpCatalogo.setDenominazioneCatalogo(denominazioneCatalogoField.getValue().getDenominazioneCatalogo());
							tmpCatalogo.setIdZonaEspansione(zonaEspansioneTipoField.getValue().getIdRecord());
							if(zonaDettaglioField.getValue()!=null)
								tmpCatalogo.setDettaglioZona(zonaDettaglioField.getValue());
							tmpCatalogo.setIdMateriale(denominazioneMaterialeField.getValue().getIdRecord());
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


							bibliotecheService.addPartecipaCatalogoCollettivo(idBiblio, tmpCatalogo, false,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									denominazioneCatalogoField.enable();
									fireCloseWindow();
									fireReleoadPanel();
									reset();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof EntryNotFoundClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										fireCloseWindow();
										denominazioneCatalogoField.enable();
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

	public PartecipaCataloghiCollettiviModel getModel(){
		return this.model;
	}
	
	public void disableDenominazione(){
		denominazioneCatalogoField.disable();
		zonaEspansioneTipoField.disable();
		zonaDettaglioField.disable();
	}
}
