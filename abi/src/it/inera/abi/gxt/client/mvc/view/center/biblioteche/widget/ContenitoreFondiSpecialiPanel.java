package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe widget per la visualizzazione / modifica delle informazioni relative
 * ai fondi speciali
 *
 */
public class ContenitoreFondiSpecialiPanel extends FormPanel {
	
	private int idBiblio;
	private FondiSpecialiModel model;
	private FondiSpecialiModel modelToSave;
	private FondiSpecialiModel modelToUpdate;
	boolean first= true;
	boolean modifica;
	private TestoFondiSpecialiPanel tmpTestoFondiSpecialiPanel;
	private BibliotecheServiceAsync bibliotecheService;
	private TabelleDinamicheServiceAsync tdsa;

	private ComboBox<FondiSpecialiModel> denominazioneFondoField ;
	private	TextField<String> descrizioneFondoField;
	private SimpleComboBox<String> fondoDepositatoField;
	private ComboBox<VoceUnicaModel> catalogoInventarioTipoField;
	private	TextField<String> urlOnline;
	private	ComboBox<SpecializzazioneModel> dewey;
	private ListStore<SpecializzazioneModel> deweyStore;
	private	TextField<String> descrUfficialeDeweyField; 

	private	Button aggiungiButton;
	private	Button aggiornaButton;
//	private	Button rimuoviButton;


	private TableData labelTableData;

	public ContenitoreFondiSpecialiPanel(boolean modifica){
		super();

		this.modifica=modifica;

		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE); 
		setStyleAttribute("marginTop", "5px");
		setStyleAttribute("marginBottom", "5px");
		setHeight(380);
		setWidth(680);
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		if(modifica)
			setBodyBorder(true);
		else
			setBodyBorder(false);
		setLayout(new FitLayout());

		createSpecializzazione();
		addKeyListenerForEnter();
	}
	
	protected void addKeyListenerForEnter() {
		if(modifica)
			Utils.addKeyListenerForEnter(aggiornaButton, this);
		else
			Utils.addKeyListenerForEnter(aggiungiButton, this);
	}

	public void createSpecializzazione(){
		labelTableData = new TableData();
		labelTableData.setWidth("10%");
		labelTableData.setMargin(5);
		labelTableData.setPadding(10);


		LayoutContainer tableContainer= new LayoutContainer(new TableLayout(2));
		tableContainer.setBorders(false);

		Text denominazioneFondoLabel = new Text("Denominazione:");
		denominazioneFondoLabel.setStyleAttribute("fontSize", "12px");


		RpcProxy<PagingLoadResult<FondiSpecialiModel>> denominazioniFondiSpecialiProxy = new RpcProxy<PagingLoadResult<FondiSpecialiModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<FondiSpecialiModel>> callback) {

				bibliotecheService.getDenominazioneFondiSpecialiPossibiliFilteredPerCombos((ModelData) loadConfig, callback);
			}
		};

		ModelReader denominazioniFondiSpecialiReader = new ModelReader();

		final PagingLoader<PagingLoadResult<FondiSpecialiModel>> denominazioniFondiSpecialiLoader = new BasePagingLoader<PagingLoadResult<FondiSpecialiModel>>(
				denominazioniFondiSpecialiProxy, denominazioniFondiSpecialiReader);
		denominazioniFondiSpecialiLoader.setLimit(10);

		final ListStore<FondiSpecialiModel> denominazioniFondiSpecialiStore = new ListStore<FondiSpecialiModel>(
				denominazioniFondiSpecialiLoader);

		denominazioneFondoField = new  ComboBox<FondiSpecialiModel>();
		denominazioneFondoField.setWidth(500);
		denominazioneFondoField.setDisplayField("denominazione");
		denominazioneFondoField.setFieldLabel("denominazione");
		denominazioneFondoField.setFireChangeEventOnSetValue(true);
		denominazioneFondoField.setEmptyText("Denominazione...");
		denominazioneFondoField.setForceSelection(false);
		denominazioneFondoField.setLazyRender(false);
		denominazioneFondoField.setTriggerAction(TriggerAction.ALL);
		denominazioneFondoField.setAllowBlank(false);
		denominazioneFondoField.setEditable(true);
		denominazioneFondoField.setTypeAhead(true);
		denominazioneFondoField.setMinChars(4);
		denominazioneFondoField.setPageSize(10);
		denominazioneFondoField.setStore(denominazioniFondiSpecialiStore);
		denominazioneFondoField.setSimpleTemplate("{denominazione} - {descrizione}");

		Text descrizioneFondoLabel = new Text("Descrizione:");
		descrizioneFondoLabel.setStyleAttribute("fontSize", "12px");

		descrizioneFondoField = new TextField<String>();
		descrizioneFondoField.setWidth(500);
		descrizioneFondoField.setEmptyText("Descrizione...");

		Text fondoDepositatoLabel = new Text("Fondo depositato:");
		fondoDepositatoLabel.setStyleAttribute("fontSize", "12px");

		fondoDepositatoField = new SimpleComboBox<String>();
		fondoDepositatoField.setEmptyText("Fondo depositato...");
		fondoDepositatoField.setForceSelection(true);
		fondoDepositatoField.setTriggerAction(TriggerAction.ALL);
		fondoDepositatoField.setEditable(false);
		fondoDepositatoField.setFireChangeEventOnSetValue(true);

		fondoDepositatoField.add("Si");
		fondoDepositatoField.add("No");
		fondoDepositatoField.add("Non specificato");

		fondoDepositatoField.setSimpleValue("Non specificato");
		RpcProxy<List<VoceUnicaModel>> catalogoInventarioTipoProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {

				tdsa.getListaVoci(CostantiTabelleDinamiche.FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX, callback);
			}
		};
		ModelReader catalogoInventarioTipoReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> catalogoInventarioTipoReaderLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				catalogoInventarioTipoProxy, catalogoInventarioTipoReader);

		final	ListStore<VoceUnicaModel> catalogoInventarioTipoListStore = new ListStore<VoceUnicaModel>(
				catalogoInventarioTipoReaderLoader);

		Text catalogoInventarioTipoLabel = new Text("Catalogo inventario:");
		catalogoInventarioTipoLabel.setStyleAttribute("fontSize", "12px");

		catalogoInventarioTipoField = new ComboBox<VoceUnicaModel>();
		catalogoInventarioTipoField.setWidth(200);
		catalogoInventarioTipoField.setDisplayField("entry");
		catalogoInventarioTipoField.setEmptyText("Tipo...");
		catalogoInventarioTipoField.setTriggerAction(TriggerAction.ALL);
		catalogoInventarioTipoField.setEditable(false);
		catalogoInventarioTipoField.setFireChangeEventOnSetValue(true);
		catalogoInventarioTipoField.setStore(catalogoInventarioTipoListStore);
		catalogoInventarioTipoField.setAllowBlank(false);
		catalogoInventarioTipoField.setEmptyText("Tipo catalogo");
		catalogoInventarioTipoField.setLazyRender(false);

		catalogoInventarioTipoField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null){
					String tipo = se.getSelectedItem().getEntry();
					if(tipo.equalsIgnoreCase("Online") || tipo.equalsIgnoreCase("Volumi")){
						urlOnline.enable();
					}else {
						urlOnline.setValue(null);
						urlOnline.disable();
					}
				}else {
					urlOnline.disable();
				}
			}
		});

		catalogoInventarioTipoReaderLoader.load();

		Text urlOnlineLabel = new Text("Url/Citazione biblio:");
		urlOnlineLabel.setStyleAttribute("fontSize", "12px");

		urlOnline = new TextField<String>();
		urlOnline.setWidth(500);
		urlOnline.setEmptyText("Url...");
		urlOnline.disable();

		denominazioneFondoField.addSelectionChangedListener(new SelectionChangedListener<FondiSpecialiModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<FondiSpecialiModel> se) {
				if (se.getSelectedItem()!=null){
					descrizioneFondoField.setValue(se.getSelectedItem().getDescrizione());
					fondoDepositatoField.setRawValue(se.getSelectedItem().getFondoDepositato());
					if(se.getSelectedItem().getCatalogoInventarioDescr()!=null){
						catalogoInventarioTipoField.setValue(new VoceUnicaModel(se.getSelectedItem().getIdCatalogoInventario(), se.getSelectedItem().getCatalogoInventarioDescr()));
					}
					urlOnline.setValue(se.getSelectedItem().getUrlOnline());
				}
			}
		});

		RpcProxy<PagingLoadResult<SpecializzazioneModel>> deweyProxy = new RpcProxy<PagingLoadResult<SpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback) {

				tdsa.getDeweyFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);
			}
		};
		ModelReader deweyReader = new ModelReader();

		final PagingLoader<PagingLoadResult<SpecializzazioneModel>> deweyLoader = new BasePagingLoader<PagingLoadResult<SpecializzazioneModel>>(
				deweyProxy, deweyReader);
		deweyLoader.setLimit(10);

		deweyStore = new ListStore<SpecializzazioneModel>(deweyLoader);

		Text deweyLabel = new Text("Dewey:");
		deweyLabel.setStyleAttribute("fontSize", "12px");

		dewey = new ComboBox<SpecializzazioneModel>();
		dewey.setWidth(500);
		dewey.setDisplayField("dewey");
		dewey.setFieldLabel("dewey");
		dewey.setFireChangeEventOnSetValue(true);
		dewey.setEmptyText("Codice Dewey...");
		dewey.setForceSelection(true);
		dewey.setLazyRender(false);
		dewey.setTriggerAction(TriggerAction.ALL);
		dewey.setEditable(true);
		dewey.setTypeAhead(false);
		dewey.setMinChars(1);
		dewey.setPageSize(10);
		dewey.setStore(deweyStore);
		dewey.setSimpleTemplate("{dewey} - {descrizioneDewey}");

		deweyLoader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {  
			public void handleEvent(LoadEvent be) {  
				if(modifica){
					if(first){
						if(model.getDewey()!=null){
							be.<ModelData> getConfig().set("query", model.getDewey());  
						}
					}
					first=false;
				}
			}  
		});  

		Text descrUfficialeDeweyLabel = new Text("Descrizione Dewey:");
		descrUfficialeDeweyLabel.setStyleAttribute("fontSize", "12px");

		descrUfficialeDeweyField = new TextField<String>();
		descrUfficialeDeweyField.setWidth(500);
		descrUfficialeDeweyField.setEnabled(false);
		descrUfficialeDeweyField.setReadOnly(true);
		descrUfficialeDeweyField.setEmptyText("Descr. Dewey...");
		descrUfficialeDeweyField.disable();
		dewey.addSelectionChangedListener(new SelectionChangedListener<SpecializzazioneModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SpecializzazioneModel> se) {
				if (se.getSelectedItem()!=null){
					descrUfficialeDeweyField.setValue(se.getSelectedItem().getDecrizione());
				}
			}
		});

		aggiungiButton = new Button("Aggiungi");
		aggiungiButton.setIcon(Resources.ICONS.add());
		aggiungiButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							 modelToSave=new FondiSpecialiModel();

							if(denominazioneFondoField.getRawValue()!=null)
								modelToSave.setDenominazione(denominazioneFondoField.getRawValue());
							if(descrizioneFondoField.getValue()!=null)
								modelToSave.setDescrizione(descrizioneFondoField.getValue());
							if(	fondoDepositatoField.getValue()!=null)
								modelToSave.setFondoDepositato(fondoDepositatoField.getValue().getValue());
							if(catalogoInventarioTipoField.getValue()!=null)
								modelToSave.setIdCatalogoInventario(catalogoInventarioTipoField.getValue().getIdRecord());
							if(urlOnline.getValue()!=null)
								modelToSave.setUrlOnline(urlOnline.getValue());

							if(dewey.getValue()!=null){
								modelToSave.setDewey(dewey.getValue().getDewey());
							}
							bibliotecheService.addFondoSpeciale(modelToSave, idBiblio, false, new AsyncCallback<FondiSpecialiModel>() {							
								@Override
								public void onSuccess(FondiSpecialiModel result) {
									modelToSave=result;
									fireAddPanel();
									fireCloseWindow();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}										modifica = false;
										fireCloseWindow();
									}
								}
							});
						}  
						else{
							fireCloseWindow();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		aggiornaButton = new Button("Aggiorna");
		aggiornaButton.setIcon(Resources.ICONS.arrowsRefresh());
		aggiornaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

						    modelToUpdate=new FondiSpecialiModel();
							modelToUpdate.setIdFondiSpeciali(model.getIdFondiSpeciali());
							if(denominazioneFondoField.getRawValue()!=null)
								modelToUpdate.setDenominazione(denominazioneFondoField.getRawValue());
							if(descrizioneFondoField.getValue()!=null)
								modelToUpdate.setDescrizione(descrizioneFondoField.getValue());
							if(	fondoDepositatoField.getValue().getValue()!=null)
								modelToUpdate.setFondoDepositato(fondoDepositatoField.getValue().getValue());
							if(catalogoInventarioTipoField.getValue()!=null){
								modelToUpdate.setIdCatalogoInventario(catalogoInventarioTipoField.getValue().getIdRecord());
							}
							if(urlOnline.getValue()!=null){
								modelToUpdate.setUrlOnline(urlOnline.getValue());
							}else 	modelToUpdate.setUrlOnline(null);
							if(dewey.getValue()!=null){
								modelToUpdate.setDewey(dewey.getValue().getDewey());
							}
							bibliotecheService.addFondoSpeciale(modelToUpdate, idBiblio, true, new AsyncCallback<FondiSpecialiModel>() {							
								@Override
								public void onSuccess(FondiSpecialiModel result) {
									modelToUpdate=result;
									fireReloadPanel();
									fireCloseWindow();
									//									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}									
										fireCloseWindow();
									}
								}
							});
						} 
						else{
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

//		rimuoviButton = new Button("Rimuovi");
//		rimuoviButton.setIcon(Resources.ICONS.delete());
//		rimuoviButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
//			@Override
//			public void componentSelected(ButtonEvent ce) {
//				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){
//
//					@Override
//					public void handleEvent(MessageBoxEvent ce) {
//
//						Button btn = ce.getButtonClicked();
//						if (btn.getText().equalsIgnoreCase("Si")) {
//							bibliotecheService.removeFondiSpeciali(idBiblio, model.getIdFondiSpeciali(), new AsyncCallback<Void>() {
//
//								@Override
//								public void onSuccess(Void result) {
//									fireCloseWindow();
////									fireReleoadPanel();
//									fireRemovePanel();
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
//								}
//
//								@Override
//								public void onFailure(Throwable caught) {
//									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
//										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
//										fireCloseWindow();
//									}
//								}
//							});
//						}
//					}
//				};
//				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);
//			}
//		});

		/*AGGIUNGO I FIELD*/

		tableContainer.add(denominazioneFondoLabel,labelTableData);
		tableContainer.add(denominazioneFondoField,labelTableData);

		tableContainer.add(descrizioneFondoLabel,labelTableData);
		tableContainer.add(descrizioneFondoField,labelTableData);

		tableContainer.add(fondoDepositatoLabel,labelTableData);
		tableContainer.add(fondoDepositatoField,labelTableData);

		tableContainer.add(catalogoInventarioTipoLabel,labelTableData);
		tableContainer.add(catalogoInventarioTipoField,labelTableData);

		tableContainer.add(urlOnlineLabel,labelTableData);
		tableContainer.add(urlOnline,labelTableData);

		tableContainer.add(deweyLabel,labelTableData);
		tableContainer.add(dewey,labelTableData);

		tableContainer.add(descrUfficialeDeweyLabel,labelTableData);
		tableContainer.add(descrUfficialeDeweyField,labelTableData);

		/**/

		if(modifica==false){
			LayoutContainer tmp = new LayoutContainer();
			tmp.setLayout(new CenterLayout());
			tmp.setWidth(640);
			tmp.setHeight(40);

			HorizontalPanel addButtons = new HorizontalPanel();
			addButtons.add(aggiungiButton);

			tmp.add(addButtons);
			setBottomComponent(tmp);

		}else{
			tableContainer.add(aggiornaButton,labelTableData);
//			tableContainer.add(rimuoviButton,labelTableData);
		}

		FormButtonBinding buttonBind = new FormButtonBinding(this);
		buttonBind.addButton(aggiungiButton);
		buttonBind.addButton(aggiornaButton);

		add(tableContainer);
	}

	public void setValues(FondiSpecialiModel model){
		this.model=model;

		denominazioneFondoField.setValue(model);
		if(model.getDescrizione()!=null)
			descrizioneFondoField.setValue(model.getDescrizione());
		else descrizioneFondoField.setValue(null);

		if(model.getFondoDepositato()!=null)
			fondoDepositatoField.setSimpleValue(model.getFondoDepositato().compareToIgnoreCase("Si")==0?"Si":"No");
		else fondoDepositatoField.setRawValue("Non specificato");

		if(model.getCatalogoInventarioDescr()!=null){
			catalogoInventarioTipoField.setValue(new VoceUnicaModel(model.getIdCatalogoInventario(),model.getCatalogoInventarioDescr()));
			if(model.getCatalogoInventarioDescr().equalsIgnoreCase("Online") || model.getCatalogoInventarioDescr().equalsIgnoreCase("Volumi")){
				urlOnline.setValue(model.getUrlOnline());
				urlOnline.enable();
			}else {
				urlOnline.setValue(null);
				urlOnline.disable();
			}
		}else {
			catalogoInventarioTipoField.setValue(null);
			urlOnline.setValue(null);
			urlOnline.disable();
		};

		if(model.getDewey()!=null){
			if(model.getDeweyDescr()!=null){
				deweyStore.add(new SpecializzazioneModel(model.getDewey(), model.getDeweyDescr()));
				dewey.setValue(new SpecializzazioneModel(model.getDewey(), model.getDeweyDescr()));
			}
			else {dewey.setValue(new SpecializzazioneModel(model.getDewey(), null));}
		}


		if(UIWorkflow.isReadOnly()==false){
			denominazioneFondoField.enable();
			descrizioneFondoField.enable();
			fondoDepositatoField.enable();
			catalogoInventarioTipoField.enable();
			dewey.enable();

			aggiornaButton.show();
			aggiungiButton.show();
//			rimuoviButton.show();
		}else{
			denominazioneFondoField.disable();
			descrizioneFondoField.disable();
			fondoDepositatoField.disable();
			urlOnline.disable();
			catalogoInventarioTipoField.disable();
			dewey.disable();
			descrUfficialeDeweyField.disable();

			aggiornaButton.hide();
			aggiungiButton.hide();
//			rimuoviButton.hide();
		}
	}

	public void setIdBiblio(int idBiblio){
		this.idBiblio=idBiblio;
	}

	public void fireReleoadbiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,this.idBiblio);
	}

	public void fireReloadPanel() {
		tmpTestoFondiSpecialiPanel.setModel(modelToUpdate);
		tmpTestoFondiSpecialiPanel.fireRefreshMeEvent();
	}

	public void fireCloseWindow() {

		fireEvent(Events.Close);		
	}

	public void resetForm(){
		reset();
	}
	protected void fireRemovePanel() {
		
		tmpTestoFondiSpecialiPanel.fireRemoveMeEvent();
	}
	protected void fireAddPanel() {
		fireEvent(Events.Submit);		
	}

	public void setPaneltToModifyUpdate(TestoFondiSpecialiPanel testoFondiSpecialiPanel) {
		tmpTestoFondiSpecialiPanel=testoFondiSpecialiPanel;
	}


	public FondiSpecialiModel getModelToAdd(){
		return modelToSave;
	}
	
	public FondiSpecialiModel getModelToUpdate(){
		return modelToUpdate;
	}
}
