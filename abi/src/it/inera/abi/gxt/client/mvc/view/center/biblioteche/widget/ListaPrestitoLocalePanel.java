package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoLocaleModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista dei prestiti locali
 *
 */
public class ListaPrestitoLocalePanel extends ContentPanel {
	/**
	 * Variabile per il servizio di operazioni delle tabelle dinamiche
	 * */
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	/**
	 * Variabile per il servizio di operazioni della biblioteca
	 * */
	private BibliotecheServiceAsync bibliotecheService ;
	/**
	 *  Id della bibliteca in modifica
	 * */
	private int id_biblioteca;
	/**
	 * Loader per il caricamento dei dati nello store della griglia
	 * */
	private BaseListLoader<ListLoadResult<PrestitoLocaleModel>> prestitiLocaliLoader;
	/**
	 * Flag che indica se si sta modificando una voce o se ne sta creando una nuova
	 * */
	private boolean modifica;

	private Grid<PrestitoLocaleModel> grid;
	private ToolBar windowToolBar;
	private Button add;
	private Button remove;

	public ListaPrestitoLocalePanel(){
		bibliotecheService=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService=(TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(true);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

	}
	/**
	 * Crea gli oggetti per la costruzione grafica della Grid e carica i dati
	 * */
	public void setGrid(){
		//  Lista configurazioni colonne Grid

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final	SimpleComboBox<String> procedureAutomatizzateField = new SimpleComboBox<String>();

		procedureAutomatizzateField.setForceSelection(true);
		procedureAutomatizzateField.setTriggerAction(TriggerAction.ALL);
		procedureAutomatizzateField.setEmptyText("Si/No...");
		procedureAutomatizzateField.setEditable(false);
		procedureAutomatizzateField.setAllowBlank(false);
		procedureAutomatizzateField.setFireChangeEventOnSetValue(true);
		procedureAutomatizzateField.add("Si");
		procedureAutomatizzateField.add("No");
		procedureAutomatizzateField.add("Non specificato");

		procedureAutomatizzateField.setSimpleValue("No");

		CellEditor procedureAutomatizzateEditor = new CellEditor(procedureAutomatizzateField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return procedureAutomatizzateField.findModel(value.toString());

			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig procedureAutomatizzateColumn = new ColumnConfig();

		procedureAutomatizzateColumn.setId("procedureAuto");

		procedureAutomatizzateColumn.setHeader("Automatizzate");
		procedureAutomatizzateColumn.setWidth(100);
		procedureAutomatizzateColumn.setEditor(procedureAutomatizzateEditor);
		procedureAutomatizzateColumn.setAlignment(HorizontalAlignment.CENTER);
		configs.add(procedureAutomatizzateColumn);

		final	NumberField durataGiorniField = new NumberField();
		durataGiorniField.setPropertyEditorType(Integer.class);


		ColumnConfig durataGiorniColumn = new ColumnConfig();
		durataGiorniColumn.setId("durataGiorni");
		durataGiorniColumn.setHeader("Durata giorni");
		durataGiorniColumn.setWidth(90);
		durataGiorniColumn.setAlignment(HorizontalAlignment.CENTER);
		durataGiorniColumn.setEditor(new CellEditor(durataGiorniField));
		configs.add(durataGiorniColumn);

		ColumnConfig materialeEsclusoColumn = new ColumnConfig();
		materialeEsclusoColumn.setId("materialeEscluso");
		materialeEsclusoColumn.setHeader("Materiale escluso");
		materialeEsclusoColumn.setWidth(120);
		materialeEsclusoColumn.setRenderer(getRendererMaterialeEsclusoButton());

		configs.add(materialeEsclusoColumn);


		ColumnConfig utentiAmmessiColumn = new ColumnConfig();
		utentiAmmessiColumn.setId("utentiAmmessi");
		utentiAmmessiColumn.setHeader("Utenti ammessi");
		utentiAmmessiColumn.setWidth(120);
		utentiAmmessiColumn.setRenderer(getRendererUtentiAmmessiButton());

		configs.add(utentiAmmessiColumn);

		RpcProxy<List<PrestitoLocaleModel>> prestitiLocaliProxy= new RpcProxy<List<PrestitoLocaleModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<PrestitoLocaleModel>> callback) {
				bibliotecheService.getPrestitiLocaliByIdBiblio(id_biblioteca,callback);

			}
		};

		ModelReader prestitiLocaliReader = new ModelReader();

		prestitiLocaliLoader=new 	BaseListLoader<ListLoadResult<PrestitoLocaleModel>>(
				prestitiLocaliProxy, prestitiLocaliReader);

		final ListStore<PrestitoLocaleModel>  prestitiLocaliStore=new ListStore<PrestitoLocaleModel>(prestitiLocaliLoader);

		final ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<PrestitoLocaleModel>	prestitiLocaliRe = new RowEditorCustom<PrestitoLocaleModel>();
		prestitiLocaliRe.setClicksToEdit(ClicksToEdit.TWO);
		prestitiLocaliRe.setErrorSummary(false);
		
		RowEditor<PrestitoLocaleModel>.RowEditorMessages rowEditorMessages = prestitiLocaliRe.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        prestitiLocaliRe.setMessages(rowEditorMessages);

		grid = new Grid<PrestitoLocaleModel>(prestitiLocaliStore,cm);
		grid.addPlugin(prestitiLocaliRe);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		
		/*TOOLBAR*/
		windowToolBar = new ToolBar();

		windowToolBar.setWidth(300);
		windowToolBar.setBorders(false);

		windowToolBar.add(new Text("Prestiti locali "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				PrestitoLocaleModel newPrestito = new PrestitoLocaleModel();
				prestitiLocaliRe.stopEditing(false);
				prestitiLocaliStore.insert(newPrestito, 0);
				newPrestito.setMaterialeEsclusoButton();
				newPrestito.setUtentiAmmessiButton();
				prestitiLocaliRe.startEditing(prestitiLocaliStore.indexOf(newPrestito), false);
			
				procedureAutomatizzateField.clearInvalid();
			}
		});
		windowToolBar.add(add);

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(final MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {


							int	id_removePrestito=grid.getSelectionModel().getSelectedItem().getIdPrestitoLocale();

							bibliotecheService.removePrestitoLocaleFromBiblio(id_biblioteca,id_removePrestito,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									prestitiLocaliLoader.load();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										ce.<Component> getComponent().disable();
										prestitiLocaliLoader.load();
									}
								}
							});

						} else {
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

				if (grid.getStore().getCount() == 0) {
					ce.<Component> getComponent().disable();
				}
				remove.disable();
			}

		});
		remove.disable();		


		remove.setIcon(Resources.ICONS.delete());
		windowToolBar.add(remove);
		add(grid);
		setTopComponent(windowToolBar);
		/*FINE TOOLBAR*/

		grid.addListener(Events.RowClick,
				new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

			public void handleEvent(
					GridEvent<PartecipaCataloghiCollettiviModel> be) {
				modifica=false;
				remove.enable();
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

			public void handleEvent(
					GridEvent<PartecipaCataloghiCollettiviModel> be) {
				modifica=true;
				remove.disable();
			}
		});

		prestitiLocaliRe.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				remove.disable();
				
				if (modifica == false) {
					prestitiLocaliStore.remove(0);	
				}
				
				prestitiLocaliLoader.load();
			}
		});

		prestitiLocaliRe.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						remove.disable();
						PrestitoLocaleModel tmpPrestito = new PrestitoLocaleModel();
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							if (modifica) {
								tmpPrestito.setIdPrestitoLocale(grid.getSelectionModel().getSelectedItem().getIdPrestitoLocale());
								tmpPrestito.setProcedureAuto(grid.getSelectionModel().getSelectedItem().getProcedureAuto());
								tmpPrestito.setDurataGiorni(grid.getSelectionModel().getSelectedItem().getDurataGiorni());
								
							} else {
								tmpPrestito.setProcedureAuto(procedureAutomatizzateField.getValue().getValue());
								if (durataGiorniField.getValue() != null) {
									tmpPrestito.setDurataGiorni(durataGiorniField.getValue().intValue());
								}
							}
							
							bibliotecheService.addPrestitoLocaleToBiblio(id_biblioteca, tmpPrestito, modifica,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									prestitiLocaliLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										prestitiLocaliLoader.load();
										modifica = false;
									}
								}
							});
						} else {
							if (modifica == false) {
								prestitiLocaliStore.remove(0);
							} else {
								modifica = false;
							}
							prestitiLocaliLoader.load();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

	}


	private GridCellRenderer getRendererMaterialeEsclusoButton() {
		GridCellRenderer<PrestitoLocaleModel> bottoneUtentiAmmessiRenderer = new GridCellRenderer<PrestitoLocaleModel>() {

			private boolean init;
			public Object render(final PrestitoLocaleModel model,
					String property, ColumnData config, final int rowIndex,
					final int colIndex, ListStore<PrestitoLocaleModel> store,
					final Grid<PrestitoLocaleModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<PrestitoLocaleModel>>() {

						public void handleEvent(
								GridEvent<PrestitoLocaleModel> be) {
							for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
								if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
										&& be.getGrid().getView().getWidget(i,be.getColIndex()) instanceof BoxComponent) {
									((BoxComponent) be.getGrid().getView().getWidget(i,be.getColIndex())).setWidth(be.getWidth() - 10);
								}
							}
						}
					});
				}

				final	Button aggiungiModificaButton = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {		

						final Window window = new Window();
						window.setSize(350, 300);
						window.setModal(true);
						window.setHeading("AGGIUNGI/MODIFICA MATERIALE ESCLUSO");
						window.setLayout(new FitLayout());
						window.setClosable(false);



						//Panel PER CONTENUTO griglia  MATERIALE ESCLUSO
						ContentPanel materialiEsclusiPanel = new ContentPanel(new TableLayout(4));
						materialiEsclusiPanel.setBodyStyle("padding-bottom:10px");
						materialiEsclusiPanel.setBodyBorder(false);
						materialiEsclusiPanel.setBorders(false);
						materialiEsclusiPanel.setHeaderVisible(false);
						materialiEsclusiPanel.setWidth(590);
						materialiEsclusiPanel.setHeight(200);
						materialiEsclusiPanel.setScrollMode(Scroll.AUTOY);
						materialiEsclusiPanel.setLayout(new FitLayout());

						List<ColumnConfig> configs =new ArrayList<ColumnConfig>();

						RpcProxy<List<VoceUnicaModel>> materialeEsclusoProxy = new RpcProxy<List<VoceUnicaModel>>() {

							@Override
							protected void load(Object loadConfig,
									AsyncCallback<List<VoceUnicaModel>> callback) {

								tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX, callback);
							}

						};
						ModelReader materialeEsclusoReader = new ModelReader();

						final BaseListLoader<ListLoadResult<ModelData>> materialeEsclusoLoader = new BaseListLoader<ListLoadResult<ModelData>>(
								materialeEsclusoProxy, materialeEsclusoReader);

						final	ListStore<VoceUnicaModel> materialeEsclusoStore = new ListStore<VoceUnicaModel>(
								materialeEsclusoLoader);
						final ComboBox<VoceUnicaModel> materialeEsclusoField = new ComboBox<VoceUnicaModel>();
						materialeEsclusoField.setDisplayField("entry");
						materialeEsclusoField.setFieldLabel("Materiale escluso");
						materialeEsclusoField.setFireChangeEventOnSetValue(true);
						materialeEsclusoField.setWidth(300);
						materialeEsclusoField.setStore(materialeEsclusoStore);
						materialeEsclusoField.setAllowBlank(false);
						materialeEsclusoField.setEmptyText("Materiale...");
						materialeEsclusoField.setForceSelection(false);
						materialeEsclusoField.setLazyRender(false);
						materialeEsclusoField.setTriggerAction(TriggerAction.ALL);
						materialeEsclusoField.setEditable(false);

						CellEditor editor = new CellEditor(materialeEsclusoField) {
							@Override
							public Object preProcessValue(Object value) {
								if (value == null) {
									return value;
								}

								return materialeEsclusoField.getStore().findModel("entry",
										value.toString());
							}

							@Override
							public Object postProcessValue(Object value) {
								if (value == null) {
									return value;
								}
								return ((ModelData) value).get("entry");
							}
						};
						ColumnConfig materialeEsclusoColumn = new ColumnConfig();

						materialeEsclusoColumn.setId("entry");
						materialeEsclusoColumn.setHeader("Materiale...");
						materialeEsclusoColumn.setWidth(300);
						materialeEsclusoColumn.setEditor(editor);
						configs.add(materialeEsclusoColumn);

						RpcProxy<List<VoceUnicaModel>> materialiEsclusiGrigliaProxy = new RpcProxy<List<VoceUnicaModel>>() {

							@Override
							protected void load(
									Object loadConfig,
									AsyncCallback<List<VoceUnicaModel>> callback) {

								tabelleDinamicheService.getMaterialiEsclusiByIdPrestitoLocale(model.getIdPrestitoLocale(), callback);

							}
						};

						ModelReader materialiEsclusiReaderGriglia = new ModelReader();

						final			BaseListLoader<ListLoadResult<VoceUnicaModel>> materialeEsclusoLoaderGriglia=new 	BaseListLoader<ListLoadResult<VoceUnicaModel>>(
								materialiEsclusiGrigliaProxy, materialiEsclusiReaderGriglia);

						final		ListStore<VoceUnicaModel>  materialeEsclusoStoreGriglia=new ListStore<VoceUnicaModel>(materialeEsclusoLoaderGriglia);
						materialeEsclusoLoaderGriglia.load();

						materialeEsclusoField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

							@Override
							public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
								if (se.getSelectedItem() != null) {
									materialeEsclusoStoreGriglia.getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
								}
							}
						});

						final	ColumnModel cm = new ColumnModel(configs);

						final RowEditorCustom<VoceUnicaModel>	materialiRe = new RowEditorCustom<VoceUnicaModel>();
						materialiRe.disable();
						materialiRe.setErrorSummary(false);
						
						RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = materialiRe.getMessages();
				        rowEditorMessages.setCancelText("Annulla");
				        rowEditorMessages.setSaveText("Salva");
				        materialiRe.setMessages(rowEditorMessages);
						
						final Grid<VoceUnicaModel> 	gridMaterialiEsclusi= new Grid<VoceUnicaModel>(materialeEsclusoStoreGriglia,cm);
						gridMaterialiEsclusi.addPlugin(materialiRe);
						gridMaterialiEsclusi.setStripeRows(true);
						gridMaterialiEsclusi.getView().setAutoFill(true);

						/*TOOLBAR*/
						ToolBar windowToolBar = new ToolBar();

						windowToolBar.setWidth(300);
						windowToolBar.setBorders(false);

						windowToolBar.add(new Text("Materiale escluso"));
						final 	Button add = new Button("Aggiungi");
						final Button remove = new Button("Rimuovi");

						add.setIcon(Resources.ICONS.add());
						add.addSelectionListener(new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								remove.disable();
								materialiRe.enable();

								VoceUnicaModel newMateriale = new VoceUnicaModel();

								materialiRe.stopEditing(false);
								materialeEsclusoStoreGriglia.insert(newMateriale, 0);
								materialiRe.startEditing(materialeEsclusoStoreGriglia.indexOf(newMateriale), false);
								remove.disable();
								
								materialeEsclusoField.clearInvalid();
							}


						});
						UIWorkflow.addOrRemoveFromToolbar(windowToolBar, add);

						remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								remove.disable();
								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(final MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {


											int	id_remove=gridMaterialiEsclusi.getSelectionModel().getSelectedItem().getIdRecord();

											tabelleDinamicheService.removePrestitoLocaleMaterialeEscluso(id_remove,model.getIdPrestitoLocale(),new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													materialiRe.disable();
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
													grid.getStore().getLoader().load();
													materialeEsclusoLoaderGriglia.load();
													{
														gridMaterialiEsclusi.getStore().remove(gridMaterialiEsclusi.getStore().indexOf(gridMaterialiEsclusi.getSelectionModel().getSelectedItem()));
														ce.<Component> getComponent().disable();
													}
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														materialiRe.disable();
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
														ce.<Component> getComponent().disable();
														materialeEsclusoLoaderGriglia.load();
													}
												}

											});

										} else {
										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

								if (gridMaterialiEsclusi.getStore().getCount() == 0) {
									ce.<Component> getComponent().disable();
								}
								remove.disable();
							}

						});
						remove.disable();

						materialiRe.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								materialiRe.disable();
								materialeEsclusoStoreGriglia.remove(0);	

								remove.disable();

							}
						});

						materialiRe.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {

								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {

											tabelleDinamicheService.addMaterialeEscluso(materialeEsclusoField.getValue().getIdRecord(),model.getIdPrestitoLocale(),new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													materialiRe.disable();
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
													materialeEsclusoLoaderGriglia.load();
													grid.getStore().getLoader().load();
													prestitiLocaliLoader.load();
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														materialiRe.disable();
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
														materialeEsclusoStoreGriglia.remove(0);
														prestitiLocaliLoader.load();
													}
												}

											});

										} else {
											materialeEsclusoLoaderGriglia.load();
											materialiRe.disable();										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
							}
						});
						gridMaterialiEsclusi.addListener(Events.RowClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {

								remove.enable();
							}
						});
						gridMaterialiEsclusi.addListener(Events.RowDoubleClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {

								remove.disable();
							}
						});

						remove.setIcon(Resources.ICONS.delete());
						UIWorkflow.addOrRemoveFromToolbar(windowToolBar, remove);

						materialiEsclusiPanel.add(gridMaterialiEsclusi);
						materialiEsclusiPanel.setTopComponent(windowToolBar);
						/*FINE TOOLBAR*/


						final Button save = new Button("Ok");
						save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								final	Vector<Integer> id_days=new Vector<Integer>();
								final	OrariModel toSave = new OrariModel();
								window.close();

							}

						});


						window.add(materialiEsclusiPanel);

						window.addButton(save);

						window.show();


					}
				});

				aggiungiModificaButton.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				aggiungiModificaButton.setToolTip("Clicca per aggiungere/rimuovere <br> le categorie dei materiali esclusi dal prestito;");

				return aggiungiModificaButton;
			}


		};


		return bottoneUtentiAmmessiRenderer;
	}
	private GridCellRenderer getRendererUtentiAmmessiButton() {
		GridCellRenderer<PrestitoLocaleModel> bottoneUtentiAmmessiRenderer = new GridCellRenderer<PrestitoLocaleModel>() {

			private boolean init;
			public Object render(final PrestitoLocaleModel model,
					String property, ColumnData config, final int rowIndex,
					final int colIndex, ListStore<PrestitoLocaleModel> store,
					final Grid<PrestitoLocaleModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<PrestitoLocaleModel>>() {

						public void handleEvent(
								GridEvent<PrestitoLocaleModel> be) {
							for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
								if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
										&& be.getGrid().getView().getWidget(i,be.getColIndex()) instanceof BoxComponent) {
									((BoxComponent) be.getGrid().getView().getWidget(i,be.getColIndex())).setWidth(be.getWidth() - 10);
								}
							}
						}
					});
				}

				final	Button aggiungiModificaButton = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {		

						final Window window = new Window();
						window.setSize(350, 300);
						window.setModal(true);
						window.setHeading("AGGIUNGI/MODIFICA UTENTI AMMESSI");
						window.setLayout(new FitLayout());
						window.setClosable(false);



						//Panel PER CONTENUTO griglia  MATERIALE ESCLUSO
						ContentPanel utentiAmmessiPanel = new ContentPanel(new TableLayout(4));
						utentiAmmessiPanel.setBodyStyle("padding-bottom:10px");
						utentiAmmessiPanel.setBodyBorder(false);
						utentiAmmessiPanel.setBorders(false);
						utentiAmmessiPanel.setHeaderVisible(false);
						utentiAmmessiPanel.setWidth(590);
						utentiAmmessiPanel.setHeight(200);
						utentiAmmessiPanel.setScrollMode(Scroll.AUTOY);
						utentiAmmessiPanel.setLayout(new FitLayout());

						List<ColumnConfig> configs =new ArrayList<ColumnConfig>();

						RpcProxy<List<VoceUnicaModel>> utenteAmmessoProxy = new RpcProxy<List<VoceUnicaModel>>() {

							@Override
							protected void load(Object loadConfig,
									AsyncCallback<List<VoceUnicaModel>> callback) {

								tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX, callback);
							}

						};
						ModelReader utenteAmmessoReader = new ModelReader();

						final BaseListLoader<ListLoadResult<ModelData>> utenteAmmessoLoader = new BaseListLoader<ListLoadResult<ModelData>>(
								utenteAmmessoProxy, utenteAmmessoReader);

						final	ListStore<VoceUnicaModel> utenteAmmessoStore = new ListStore<VoceUnicaModel>(
								utenteAmmessoLoader);
						final ComboBox<VoceUnicaModel> utenteAmmessoField = new ComboBox<VoceUnicaModel>();
						utenteAmmessoField.setDisplayField("entry");
						utenteAmmessoField.setFieldLabel("Utente ammesso");
						utenteAmmessoField.setFireChangeEventOnSetValue(true);
						utenteAmmessoField.setWidth(300);
						utenteAmmessoField.setStore(utenteAmmessoStore);
						utenteAmmessoField.setAllowBlank(false);
						utenteAmmessoField.setEmptyText("Utenti ammessi...");
						utenteAmmessoField.setForceSelection(false);
						utenteAmmessoField.setLazyRender(false);
						utenteAmmessoField.setTriggerAction(TriggerAction.ALL);
						utenteAmmessoField.setEditable(false);

						CellEditor editor = new CellEditor(utenteAmmessoField) {
							@Override
							public Object preProcessValue(Object value) {
								if (value == null) {
									return value;
								}

								return utenteAmmessoField.getStore().findModel("entry",
										value.toString());
							}

							@Override
							public Object postProcessValue(Object value) {
								if (value == null) {
									return value;
								}
								return ((ModelData) value).get("entry");
							}
						};
						ColumnConfig utenteAmmessoColumn = new ColumnConfig();

						utenteAmmessoColumn.setId("entry");
						utenteAmmessoColumn.setHeader("Utente...");
						utenteAmmessoColumn.setWidth(300);
						utenteAmmessoColumn.setEditor(editor);
						configs.add(utenteAmmessoColumn);

						RpcProxy<List<VoceUnicaModel>> utentiAmmessiGrigliaProxy = new RpcProxy<List<VoceUnicaModel>>() {

							@Override
							protected void load(
									Object loadConfig,
									AsyncCallback<List<VoceUnicaModel>> callback) {

								tabelleDinamicheService.getUtentiAmmessiByIdPrestitoLocale(model.getIdPrestitoLocale(), callback);

							}
						};

						ModelReader utentiAmmessiReaderGriglia = new ModelReader();

						final			BaseListLoader<ListLoadResult<VoceUnicaModel>> utenteAmmessoLoaderGriglia=new 	BaseListLoader<ListLoadResult<VoceUnicaModel>>(
								utentiAmmessiGrigliaProxy, utentiAmmessiReaderGriglia);

						final		ListStore<VoceUnicaModel>  utenteAmmessoStoreGriglia=new ListStore<VoceUnicaModel>(utenteAmmessoLoaderGriglia);
						utenteAmmessoLoaderGriglia.load();
						
						utenteAmmessoField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

							@Override
							public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
								if (se.getSelectedItem() != null) {
									utenteAmmessoStoreGriglia.getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
								}
							}
						});

						final	ColumnModel cm = new ColumnModel(configs);

						final RowEditorCustom<VoceUnicaModel> utentiAmmessiRe = new RowEditorCustom<VoceUnicaModel>();
						utentiAmmessiRe.disable();
						utentiAmmessiRe.setErrorSummary(false);
						
						RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = utentiAmmessiRe.getMessages();
				        rowEditorMessages.setCancelText("Annulla");
				        rowEditorMessages.setSaveText("Salva");
				        utentiAmmessiRe.setMessages(rowEditorMessages);
						
						final Grid<VoceUnicaModel> 	gridutentiAmmessi= new Grid<VoceUnicaModel>(utenteAmmessoStoreGriglia,cm);
						gridutentiAmmessi.addPlugin(utentiAmmessiRe);
						gridutentiAmmessi.setStripeRows(true);
						gridutentiAmmessi.getView().setAutoFill(true);

						/*TOOLBAR*/
						ToolBar windowToolBar = new ToolBar();

						windowToolBar.setWidth(300);
						windowToolBar.setBorders(false);

						windowToolBar.add(new Text("Utente ammesso"));
						final 	Button add = new Button("Aggiungi");
						final Button remove = new Button("Rimuovi");

						add.setIcon(Resources.ICONS.add());
						add.addSelectionListener(new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								utentiAmmessiRe.enable();

								VoceUnicaModel newMateriale = new VoceUnicaModel();

								utentiAmmessiRe.stopEditing(false);
								utenteAmmessoStoreGriglia.insert(newMateriale, 0);
								utentiAmmessiRe.startEditing(utenteAmmessoStoreGriglia.indexOf(newMateriale), false);
								remove.disable();
								
								utenteAmmessoField.clearInvalid();
							}


						});
						UIWorkflow.addOrRemoveFromToolbar(windowToolBar, add);
						remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {

								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(final MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {
											int	id_remove=gridutentiAmmessi.getSelectionModel().getSelectedItem().getIdRecord();

											tabelleDinamicheService.removeUtenteAmmessoAlPrestitoLocale(id_remove,model.getIdPrestitoLocale(),new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													utentiAmmessiRe.disable();
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
													grid.getStore().getLoader().load();
													utenteAmmessoLoaderGriglia.load();
													ce.<Component> getComponent().disable();
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														utentiAmmessiRe.disable();
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
														ce.<Component> getComponent().disable();
														utenteAmmessoLoaderGriglia.load();
													}
												}
											});
										} else {
										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

								if (gridutentiAmmessi.getStore().getCount() == 0) {
									ce.<Component> getComponent().disable();
								}
								remove.disable();
							}

						});
						remove.disable();

						utentiAmmessiRe.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								utentiAmmessiRe.disable();
								utenteAmmessoStoreGriglia.remove(0);	
								remove.disable();
							}
						});

						utentiAmmessiRe.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {

								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {

											tabelleDinamicheService.addUtenteAmmessoAlPrestitoLocale(utenteAmmessoField.getValue().getIdRecord(),model.getIdPrestitoLocale(),new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													utentiAmmessiRe.disable();
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
													utenteAmmessoLoaderGriglia.load();
													grid.getStore().getLoader().load();
													prestitiLocaliLoader.load();
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														utentiAmmessiRe.disable();
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
														utenteAmmessoStoreGriglia.remove(0);
														prestitiLocaliLoader.load();
													}
												}

											});

										} else {
											utenteAmmessoLoaderGriglia.load();
											utentiAmmessiRe.disable();										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
							}
						});
						gridutentiAmmessi.addListener(Events.RowClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {

								remove.enable();
							}
						});
						gridutentiAmmessi.addListener(Events.RowDoubleClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {

								remove.disable();
							}
						});

						remove.setIcon(Resources.ICONS.delete());
						UIWorkflow.addOrRemoveFromToolbar(windowToolBar, remove);

						utentiAmmessiPanel.add(gridutentiAmmessi);
						utentiAmmessiPanel.setTopComponent(windowToolBar);
						/*FINE TOOLBAR*/


						final Button save = new Button("Ok");
						save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								final	Vector<Integer> id_days=new Vector<Integer>();
								final	OrariModel toSave = new OrariModel();
								window.close();

							}

						});


						window.add(utentiAmmessiPanel);

						window.addButton(save);

						window.show();


					}
				});

				aggiungiModificaButton.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				aggiungiModificaButton.setToolTip("Clicca per aggiungere/rimuovere <br> le categorie degli utenti ammessi dal prestito;");

				return aggiungiModificaButton;
			}


		};
		return bottoneUtentiAmmessiRenderer;
	}

	/**
	 * Setta va variabile id_biblioteca
	 * */
	public void setIdBiblioteca(int idBiblioteca){
		this.id_biblioteca=idBiblioteca;
	}

	/**
	 * Ritorna il l'oggeto di tipo loader per il caricamento dei dati nello store della Grid 
	 * */
	public BaseListLoader<ListLoadResult<PrestitoLocaleModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(windowToolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(windowToolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.prestitiLocaliLoader;	
	}
}
