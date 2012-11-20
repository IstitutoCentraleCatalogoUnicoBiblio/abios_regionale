package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.PatrimoniCategorieTabelleDinamicheModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
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
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette la gestione (modifica, aggiungi, rimuovi) 
 * della tabella dinamica delle specializzazioni del patrimonio
 * 
 */
public class ListaPatrimonioPiccoleVociTabelleDinamiche extends ContentPanel {
	
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private boolean modifica;
	private int pageSize;
	private Grid<PatrimoniCategorieTabelleDinamicheModel> grid;
	private BasePagingLoader<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>> patrimonioLoader;

	public ListaPatrimonioPiccoleVociTabelleDinamiche() {
		tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		modifica = false;
		pageSize = TabelleDinamicheView.GRID_ROWS_NUMBER;
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setWidth(600);
		setHeight(170);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}

	public void setGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final TextField<String> descrizioneVoce = new TextField<String>();
		descrizioneVoce.setEmptyText("Descrizione voce...");
		descrizioneVoce.setAllowBlank(false);

		ColumnConfig columnDescrizione = new ColumnConfig();
		columnDescrizione.setId("categoriaDescrizione");
		columnDescrizione.setHeader("Descrizione voce");
		columnDescrizione.setWidth(400);
		columnDescrizione.setEditor(new CellEditor(descrizioneVoce));
		configs.add(columnDescrizione);

		/*descrizioneVoceProxy
		 * RPCProxy,ModelReader e Loader per il caricamento paginato asincrono dei dati della tabella dinamica relativa all'id 
		 * idTabellaDinamica all'interno dello store della Combobox per oggetti di tipo VoceUnicaModel 
		 * */
		RpcProxy<PagingLoadResult<VoceUnicaModel>> categoriaProxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVociFiltratePerPaginazioneCombobox(CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX,(ModelData)loadConfig, callback);
			}

		};

		ModelReader categoriaReader = new ModelReader();

		final PagingLoader<PagingLoadResult<VoceUnicaModel>> categoriaLoader = new BasePagingLoader<PagingLoadResult<VoceUnicaModel>>(
				categoriaProxy, categoriaReader);
		categoriaLoader.setLimit(10);

		final ListStore<VoceUnicaModel> categoriaStore = new ListStore<VoceUnicaModel>(categoriaLoader);

		//ComboBox paginata con autocompletamento 
		final ComboBox<VoceUnicaModel> categoriaField = new ComboBox<VoceUnicaModel>();
		categoriaField.setDisplayField("entry");
		categoriaField.setFieldLabel("entry");
		categoriaField.setFireChangeEventOnSetValue(true);
		categoriaField.setEmptyText("Categoria...");
		categoriaField.setForceSelection(false);
		categoriaField.setLazyRender(false);
		categoriaField.setTriggerAction(TriggerAction.ALL);
		categoriaField.setEditable(true);
		categoriaField.setAllowBlank(false);
		categoriaField.setTypeAhead(true);
		categoriaField.setMinChars(1);
		categoriaField.setPageSize(10);
		categoriaField.setStore(categoriaStore);


		CellEditor descrizioneVoceComboEditor = new CellEditor(categoriaField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					VoceUnicaModel tmpCategoria = new VoceUnicaModel(grid.getSelectionModel().getSelectedItem().getIdCategoriaMadre(), grid.getSelectionModel().getSelectedItem().getCategoriaMadreDescrizione());
					return tmpCategoria;

				} else {
					return "Categoria...";
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					if (categoriaField.getRawValue() != null) {
						return categoriaField.getRawValue();

					} else {
						return value;
					}
				}

				VoceUnicaModel tmp = (VoceUnicaModel) value;
				return tmp.getEntry();
			}
		};

		// Config per colonna descrizione
		ColumnConfig descrizioneColumn = new ColumnConfig();
		descrizioneColumn.setId("categoriaMadreDescrizione");
		descrizioneColumn.setHeader("Categoria a cui appartiene");
		descrizioneColumn.setWidth(300);
		descrizioneColumn.setEditor(descrizioneVoceComboEditor);

		configs.add(descrizioneColumn);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<PatrimoniCategorieTabelleDinamicheModel> re = new RowEditorCustom<PatrimoniCategorieTabelleDinamicheModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		re.enableEvents(true);

		RowEditor<PatrimoniCategorieTabelleDinamicheModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		RpcProxy<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>> patrimonioProxy = new RpcProxy<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>> callback) {
				tabelleDinamicheService.getPatrimoniPiccoleVociTabelleDinamiche((PagingLoadConfig)loadConfig, callback);

			}
		};

		ModelReader patrimonioReader = new ModelReader();

		patrimonioLoader = new BasePagingLoader<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>>(patrimonioProxy, patrimonioReader);
		patrimonioLoader.setRemoteSort(true);
		final ListStore<PatrimoniCategorieTabelleDinamicheModel> patrimonioStore = new ListStore<PatrimoniCategorieTabelleDinamicheModel>(patrimonioLoader);

		patrimonioLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				unmask();
			}
		});
		
		grid = new Grid<PatrimoniCategorieTabelleDinamicheModel>(patrimonioStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<PatrimoniCategorieTabelleDinamicheModel>>() {
			public void handleEvent(GridEvent<PatrimoniCategorieTabelleDinamicheModel> be) {
				mask("Caricamento...", "x-mask-loading");
				final PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(pageSize);

				Map<String, Object> state = grid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					int limit = (Integer) state.get("limit");
					config.setOffset(offset);
					config.setLimit(limit);
				}
				if (state.containsKey("sortField")) {
					config.setSortField((String) state.get("sortField"));
					config.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
				}
				patrimonioLoader.load(config);
			}
		});

		categoriaField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					if (modifica == false) {
						grid.getStore().getAt(0).setIdCategoriaMadre(se.getSelectedItem().getIdRecord());

					} else {
						grid.getSelectionModel().getSelectedItem().setIdCategoriaMadre(se.getSelectedItem().getIdRecord());
					}
				}
			}
		});

		ToolBar toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		final Button add = new Button("Aggiungi");
		final Button edit = new Button("Modifica");
		final Button remove = new Button("Rimuovi");

		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				PatrimoniCategorieTabelleDinamicheModel newSpec = new PatrimoniCategorieTabelleDinamicheModel();
				re.stopEditing(false);
				patrimonioStore.insert(newSpec, 0);
				re.startEditing(patrimonioStore.indexOf(newSpec), false);

				descrizioneVoce.clearInvalid();	
				categoriaField.clearInvalid();
			}

		});

		toolBar.add(add);

		edit.setIcon(Resources.ICONS.edit());
		edit.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() == 1) {
					modifica = true;
					re.stopEditing(false);
					re.startEditing(patrimonioStore.indexOf(grid.getSelectionModel().getSelectedItem()), false);

				} else {
					if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() > 1) {
						/* Selezione multipla */
						AbiMessageBox.messageAlertBox(AbiMessageBox.SELEZIONE_MULTIPLA_TO_MODIFY_MESSAGE, AbiMessageBox.SELEZIONE_MULTIPLA_TITLE);

					} else {/* Mancata selezione */
						AbiMessageBox.messageAlertBox(AbiMessageBox.MANCATA_SELEZIONE_TO_MODIFY_MESSAGE, AbiMessageBox.MANCATA_SELEZIONE_TITLE);
					}
				}

			}
		});
		toolBar.add(edit);

		remove.setIcon(Resources.ICONS.delete());
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() == 1) {
					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){
						@Override
						public void handleEvent(MessageBoxEvent ce) {
							Button btn = ce.getButtonClicked();
							
							if (btn.getText().equalsIgnoreCase("Si")) {
								int idr_removeRecord = grid.getSelectionModel().getSelectedItem().getIdCategoria();
								
								tabelleDinamicheService.removePatrimoniPiccoleVociTabelleDinamiche(idr_removeRecord,new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										patrimonioLoader.load();

										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}

									@Override
									public void onFailure(Throwable caught) {
										UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof CostraintKeyViolationClientSideException) {
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											
										} else {
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}
										
										patrimonioLoader.load();
									}

								});

							}
						}

					};
					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

					if (grid.getStore().getCount() == 0) {
						ce.<Component> getComponent().disable();
					}

				} else {
					if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() > 1) {
						/* Selezione multipla */
						AbiMessageBox.messageAlertBox(AbiMessageBox.SELEZIONE_MULTIPLA_TO_REMOVE_MESSAGE, AbiMessageBox.SELEZIONE_MULTIPLA_TITLE);

					} else {/* Mancata selezione */
						AbiMessageBox.messageAlertBox(AbiMessageBox.MANCATA_SELEZIONE_TO_REMOVE_MESSAGE, AbiMessageBox.MANCATA_SELEZIONE_TITLE);
					}
				}
			}

		});

		PagingToolBar toolBarBottom = new PagingToolBar(pageSize);
		toolBarBottom.bind(patrimonioLoader);
		toolBarBottom.enable();
		setBottomComponent(toolBarBottom);

		grid.addListener(Events.RowClick, new Listener<GridEvent<PatrimoniCategorieTabelleDinamicheModel>>() {
			public void handleEvent(GridEvent<PatrimoniCategorieTabelleDinamicheModel> be) {
				modifica = false;
			}
		});


		grid.addListener(Events.RowDoubleClick,new Listener<GridEvent<PatrimoniCategorieTabelleDinamicheModel>>() {
			@Override
			public void handleEvent(GridEvent<PatrimoniCategorieTabelleDinamicheModel> be) {
				modifica = true;
			}

		});

		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					patrimonioStore.remove(0);
					
				} else {
					modifica = false;
				}
				
				patrimonioLoader.load();
			}
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						
						if (btn.getText().equalsIgnoreCase("Si")) {
							PatrimoniCategorieTabelleDinamicheModel modelToSave = new PatrimoniCategorieTabelleDinamicheModel();

							if (modifica == false) {		
								modelToSave.setCategoriaDescrizione(grid.getStore().getAt(0).getCategoriaDescrizione());
								
								if (grid.getStore().getAt(0).getIdCategoriaMadre() != null) {
									modelToSave.setIdCategoriaMadre(grid.getStore().getAt(0).getIdCategoriaMadre());
								}
							}
							else {
								modelToSave.setIdCategoria(grid.getSelectionModel().getSelectedItem().getIdCategoria());
								modelToSave.setCategoriaDescrizione(grid.getSelectionModel().getSelectedItem().getCategoriaDescrizione());
								
								if (grid.getSelectionModel().getSelectedItem().getIdCategoriaMadre() != null) {
									modelToSave.setIdCategoriaMadre(grid.getSelectionModel().getSelectedItem().getIdCategoriaMadre());
								}
							} 

							tabelleDinamicheService.addPatrimoniPiccoleVociTabelleDinamiche(modelToSave, modifica, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									patrimonioLoader.load();
									modifica = false;
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
									
									if (caught instanceof DuplicatedEntryClientSideException) {
										AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										
									} else {
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}
									
									patrimonioLoader.load();
									modifica = false; 
								}
							});

						} else {
							if (modifica == false) {
								patrimonioStore.remove(0);
								
							} else {
								modifica = false;
							}
							
							patrimonioLoader.load();		
						}

					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

	}

}