package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Estensione della classe widget <code>CreazioneModificaCataloghiGenericaPanel</code> 
 * per la visualizzazione / modifica delle informazioni relative ai cataloghi speciali
 *
 */
public class CreazioneModificaCataloghiSpecialiPanel extends CreazioneModificaCataloghiGenericaPanel{

	/*Label cataloghi collettivi*/
	protected  Text denominazioneCatalogoLabel;
	protected  Text denominazioneMaterialeLabel;
	/*Campi cataloghi collettivi*/
	private TextField<String> denominazioneCatalogoField;
	private ComboBox<PatrimonioSpecializzazioneModel> denominazioneMaterialeField;
	private PartecipaCataloghiSpecialiModel tmpCatalogo;
	private PartecipaCataloghiSpecialiModel model;
	private PagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>> tipologiaPatrimonioLoader;
	private ListStore<PatrimonioSpecializzazioneModel> denominazioneMaterialeStore;
	/*TabelData per labels e fields per tipologia materiale */
	private TableData denominazioneTableData;
	private TableData materialeTableData;

	private int denominazioneLabelWidth=85;
	private int materialeLabelWidth=150;	

	private boolean firstLoad=true;
	public CreazioneModificaCataloghiSpecialiPanel(boolean modifica) {
		super();
		this.modifica=modifica;
		//Setto il tipo di catalogo
		this.tipoCatalogo=3;

		createFieldsAndLabelsCatalogoSpecialeForm();
		createNuovoCatalogoSpecialeForm();

		materialeTableData = new TableData();
		materialeTableData.setWidth(materialeLabelWidth+"px");
		materialeTableData.setMargin(5);

		denominazioneTableData = new TableData();
		denominazioneTableData.setWidth(denominazioneLabelWidth+"px");
		denominazioneTableData.setMargin(5);
	}

	public void createFieldsAndLabelsCatalogoSpecialeForm(){

		denominazioneCatalogoLabel = new Text("Denominazione:");
		denominazioneCatalogoLabel.setStyleAttribute("fontSize", "12px");

		denominazioneMaterialeLabel = new Text("Tipo Materiale:");
		denominazioneMaterialeLabel.setStyleAttribute("fontSize", "12px");

		denominazioneCatalogoField = new TextField<String>();
		denominazioneCatalogoField.setWidth(500);
		denominazioneCatalogoField.setEmptyText("Descrizione...");
		denominazioneCatalogoField.setAllowBlank(true);

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

	public void createNuovoCatalogoSpecialeForm(){

		Text descrizioneCatalogoEstensioneText = new Text("<b>Descrizione catalogo </b>");
		LayoutContainer descrizioneCatalogoEstensioneTable = new LayoutContainer(new TableLayout(2));
		descrizioneCatalogoEstensioneTable.add(denominazioneCatalogoLabel,labelFormatiTableData);
		descrizioneCatalogoEstensioneTable.add(denominazioneCatalogoField);

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
		}else{
			aggiungiButtonInit();
			FormButtonBinding bind = new FormButtonBinding(this);
			bind.addButton(aggiungiButton);
			buttonContainer.add(aggiungiButton);
		}
		tmp.add(buttonContainer);
		setBottomComponent(tmp);
	}

	public LayoutContainer createModificaCatalogoFormLabels(){

		LayoutContainer tmpLabelContainer= createModificaBaseFormLabels();

		denominazioneCatalogoLabel.setStyleAttribute("fontSize", labelFontSize);
		denominazioneCatalogoLabel.setWidth(denominazioneLabelWidth);
		tmpLabelContainer.insert(denominazioneCatalogoLabel, 0,denominazioneTableData);

		denominazioneMaterialeLabel.setStyleAttribute("fontSize", labelFontSize);
		denominazioneMaterialeLabel.setWidth(materialeLabelWidth);
		tmpLabelContainer.insert(denominazioneMaterialeLabel, 1,materialeTableData);

		return tmpLabelContainer;
	}

	public LayoutContainer createModificaCatalogoSpecialeForm(){
		LayoutContainer tmpFieldsContainer= createModificaBaseFormFields();

		denominazioneCatalogoField.setWidth(denominazioneLabelWidth);
		tmpFieldsContainer.insert(denominazioneCatalogoField, 0, denominazioneTableData);

		denominazioneMaterialeField.setWidth(materialeLabelWidth);
		tmpFieldsContainer.insert(denominazioneMaterialeField, 1, materialeTableData);

		aggiornaButtonInit();
		rimuoviButtonInit();

		tmpFieldsContainer.add(aggiornaButton,annoTabelData);
		tmpFieldsContainer.add(rimuoviButton,annoTabelData);

		if(UIWorkflow.isReadOnly()==false){
			denominazioneCatalogoField.enable();
			denominazioneMaterialeField.enable();
			aggiornaButton.show();
			rimuoviButton.show();
		}else{
			denominazioneCatalogoField.disable();
			denominazioneMaterialeField.disable();
			aggiornaButton.hide();
			rimuoviButton.hide();
		}

		return tmpFieldsContainer;
	}


	public void setValues(final PartecipaCataloghiSpecialiModel model){
		this.model=model;

		denominazioneCatalogoField.setValue(model.getDenominazione());

		//		tipologiaPatrimonioLoader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {  
		//			public void handleEvent(LoadEvent be) {  
		//			
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

							tmpCatalogo = new PartecipaCataloghiSpecialiModel();
							tmpCatalogo.setIdPartecipaCatalogo(model.getIdPartecipaCatalogo());
							if(denominazioneCatalogoField.getValue()!=null)
								tmpCatalogo.setDenominazione(denominazioneCatalogoField.getValue());
							if(denominazioneMaterialeField.getValue()!=null){
								tmpCatalogo.setIdMateriale(denominazioneMaterialeField.getValue().getIdRecord());
							}
							else {
								tmpCatalogo.setIdMateriale(model.getIdMateriale());
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

							if (annoDaField.getValue() != null) {
								tmpCatalogo.setDaAnno(annoDaField.getValue().intValue());
								
							} else {
								tmpCatalogo.setDaAnno(null);
							}
							
							if (annoAField.getValue() != null) {
								tmpCatalogo.setAAnno(annoAField.getValue().intValue());
								
							} else {
								tmpCatalogo.setAAnno(null);
							}

							bibliotecheService.addPartecipaCatalogoSpeciale(idBiblio, tmpCatalogo,	true,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
//									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									denominazioneCatalogoField.enable();
									fireReleoadPanel();
									fireCloseWindow();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
//										modifica = false;
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
							bibliotecheService.removePartecipaCatalogoSpeciale(idRemove,idBiblio,	new AsyncCallback<Void>() {

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

							PartecipaCataloghiSpecialiModel tmpCatalogo = new PartecipaCataloghiSpecialiModel();
							if(denominazioneCatalogoField.getValue()!=null)
								tmpCatalogo.setDenominazione(denominazioneCatalogoField.getValue());
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


							bibliotecheService.addPartecipaCatalogoSpeciale(idBiblio, tmpCatalogo, false,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
//									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									denominazioneCatalogoField.enable();
									fireReleoadPanel();
									fireCloseWindow();
									reset();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
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

	public PartecipaCataloghiSpecialiModel getModel(){
		return this.model;
	}
}
