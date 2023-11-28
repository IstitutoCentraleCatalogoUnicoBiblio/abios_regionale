/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ContattiModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.models.CataloghiCollettiviModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
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
 * della tabella dinamica dei cataloghi collettivi
 * 
 */
public class ListaCataloghiCollettiviTabelleDinamiche extends ContentPanel {

	private Grid<CataloghiCollettiviModel> grid = null;
	private ListStore<CataloghiCollettiviModel> storeGriglia = null;
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private	 BasePagingLoader<PagingLoadResult<ModelData>> cataloghiCollettiviLoader;
	private RowEditorCustom<CataloghiCollettiviModel> re;
	private boolean modifica;
	private int pageSize;
	private TextField<String> denominazione;
	private	ComboBox<VoceUnicaModel> zonaEspansioneTipo;
	List<ColumnConfig> configs = null;

	public ListaCataloghiCollettiviTabelleDinamiche() {
		super();
		setLayout(new FitLayout());
		tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		modifica = false;
		pageSize = TabelleDinamicheView.GRID_ROWS_NUMBER;
	}

	public void setGrid() {
		configs = new ArrayList<ColumnConfig>();

		denominazione = new TextField<String>();
		denominazione.setAllowBlank(false);
		denominazione.setEmptyText("Denominazione...");

		ColumnConfig columnDenominazione = new ColumnConfig();
		columnDenominazione.setId("denominazione");
		columnDenominazione.setHeader("Denominazione");
		columnDenominazione.setWidth(400);
		columnDenominazione.setEditor(new CellEditor(denominazione));

		configs.add(columnDenominazione);

		RpcProxy<List<VoceUnicaModel>> zonaEspansioneProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX, callback);
			}

		};

		ModelReader zonaEspansioneReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> zonaEspansioneLoader  = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				zonaEspansioneProxy, zonaEspansioneReader);

		final ListStore<VoceUnicaModel> zonaEspansioneStore = new ListStore<VoceUnicaModel>(zonaEspansioneLoader);

		//ComboBox per caricamento tipologie zona di espansione
		zonaEspansioneTipo = new ComboBox<VoceUnicaModel>();
		zonaEspansioneTipo.setDisplayField("entry");
		zonaEspansioneTipo.setFieldLabel("entry");
		zonaEspansioneTipo.setFireChangeEventOnSetValue(true);
		zonaEspansioneTipo.setEditable(false);
		zonaEspansioneTipo.setEmptyText("Descrizione...");
		zonaEspansioneTipo.setForceSelection(false);
		zonaEspansioneTipo.setLazyRender(false);
		zonaEspansioneTipo.setAllowBlank(false);
		zonaEspansioneTipo.setTriggerAction(TriggerAction.ALL);
		zonaEspansioneTipo.setStore(zonaEspansioneStore);

		CellEditor zonaEspansioneEditor = new CellEditor(zonaEspansioneTipo) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					CataloghiCollettiviModel tmpModel= grid.getSelectionModel().getSelectedItem();
					VoceUnicaModel tmp = new VoceUnicaModel();
					tmp.setEntry(tmpModel.getZonaEspansione());
					tmp.setIdRecord(tmpModel.getIdZonaEspansione());
					return tmp;

				} else {
					return "Zona tipo...";
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					if (zonaEspansioneTipo.getRawValue() != null) {
						return zonaEspansioneTipo.getRawValue();

					} else {
						return value;
					}
				}

				VoceUnicaModel tmp= (VoceUnicaModel) value;
				return tmp.getEntry();
			}
		};

		// Config per colonna descrizione

		ColumnConfig zonaEspansioneColumn = new ColumnConfig();
		zonaEspansioneColumn.setId("zonaEspansione");
		zonaEspansioneColumn.setHeader("Zona espansione");
		zonaEspansioneColumn.setWidth(150);
		zonaEspansioneColumn.setEditor(zonaEspansioneEditor);
		configs.add(zonaEspansioneColumn);

		zonaEspansioneTipo.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					if (modifica == false) {
						grid.getStore().getAt(0).setIdZonaEspansione(se.getSelectedItem().getIdRecord());

					} else {
						grid.getSelectionModel().getSelectedItem().setIdZonaEspansione(se.getSelectedItem().getIdRecord());
					}
				}
			}
		});

		TextField<String> zona = new TextField<String>();

		ColumnConfig columnZona = new ColumnConfig();
		columnZona.setId("zona");
		columnZona.setHeader("Zona");
		columnZona.setWidth(200);
		columnZona.setEditor(new CellEditor(zona));

		configs.add(columnZona);

		//Caricamento dati griglia
		RpcProxy<PagingLoadResult<CataloghiCollettiviModel>> cataloghiCollettiviProxy = new RpcProxy<PagingLoadResult<CataloghiCollettiviModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<CataloghiCollettiviModel>> callback) {
				tabelleDinamicheService.getListaCataloghiCollettiviGestioneTabelleDinamiche((PagingLoadConfig)loadConfig, callback);
			}
		};

		ModelReader cataloghiCollettiviReader = new ModelReader();

		cataloghiCollettiviLoader = new BasePagingLoader<PagingLoadResult<ModelData>>(cataloghiCollettiviProxy, cataloghiCollettiviReader);
		cataloghiCollettiviLoader.setRemoteSort(true);
		storeGriglia = new ListStore<CataloghiCollettiviModel>(cataloghiCollettiviLoader);
		
		cataloghiCollettiviLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				unmask();
			}
		});

		re = new RowEditorCustom<CataloghiCollettiviModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);

		RowEditor<CataloghiCollettiviModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		grid = new Grid<CataloghiCollettiviModel>(storeGriglia, new ColumnModel(configs));
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<CataloghiCollettiviModel>>() {
			public void handleEvent(GridEvent<CataloghiCollettiviModel> be) {
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
				cataloghiCollettiviLoader.load(config);
			}
		});
		
		ToolBar toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		Button add = new Button("Aggiungi");
		final Button edit = new Button("Modifica");
		final Button remove = new Button("Rimuovi");

		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				CataloghiCollettiviModel newCatalogo = new CataloghiCollettiviModel();
				re.stopEditing(false);
				storeGriglia.insert(newCatalogo, 0);
				re.startEditing(storeGriglia.indexOf(newCatalogo), false);

				denominazione.clearInvalid();
				zonaEspansioneTipo.clearInvalid();
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
					re.startEditing(storeGriglia.indexOf(grid.getSelectionModel().getSelectedItem()), false);

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
					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent ce) {
							Button btn = ce.getButtonClicked();
							
							if (btn.getText().equalsIgnoreCase("Si")) {
								int id_toRemove = grid.getSelectionModel().getSelectedItem().getIdCatalogo();
								
								tabelleDinamicheService.removeCatalogoCollettivoATabellaDinamica(id_toRemove,new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										
										grid.getStore().getLoader().load();																	
									}

									@Override
									public void onFailure(Throwable caught) {
										UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
										
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										grid.getStore().getLoader().load();
									}
								});
							}
						}
					};
					
					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE,AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE,l);

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

		toolBar.add(remove);

		grid.addListener(Events.RowClick, new Listener<GridEvent<ContattiModel>>() {
			public void handleEvent(GridEvent<ContattiModel> be) {
				modifica = false;

			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<ContattiModel>>() {
			public void handleEvent(GridEvent<ContattiModel> be) {
				modifica = true;
			}
		});
		
		
		PagingToolBar toolBarBottom = new PagingToolBar(pageSize);
		toolBarBottom.bind(cataloghiCollettiviLoader);
		toolBarBottom.enable();
		setBottomComponent(toolBarBottom);
		
		add(grid);
		setTopComponent(toolBar);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					storeGriglia.remove(0);

				} else {
					modifica = false;
				}
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
							CataloghiCollettiviModel newCatalogoCollettiviModel = new CataloghiCollettiviModel();

							if (modifica == false) {
								newCatalogoCollettiviModel.setDenominazione(grid.getStore().getAt(0).getDenominazione());
								newCatalogoCollettiviModel.setIdZonaEspansione(grid.getStore().getAt(0).getIdZonaEspansione());
								newCatalogoCollettiviModel.setZona(grid.getStore().getAt(0).getZona());

							} else {
								newCatalogoCollettiviModel.setIdCatalogo(grid.getSelectionModel().getSelectedItem().getIdCatalogo());
								newCatalogoCollettiviModel.setDenominazione(grid.getSelectionModel().getSelectedItem().getDenominazione());
								newCatalogoCollettiviModel.setIdZonaEspansione(grid.getSelectionModel().getSelectedItem().getIdZonaEspansione());
								newCatalogoCollettiviModel.setZona(grid.getSelectionModel().getSelectedItem().getZona());
							}

							tabelleDinamicheService.addCatalogoCollettivoATabellaDinamica(newCatalogoCollettiviModel, modifica, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									
									grid.getStore().getLoader().load();

									modifica = false;
								}

								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login

									AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									grid.getStore().getLoader().load();

									modifica = false;
								}

							});

						} else {
							grid.getStore().getLoader().load();

							modifica = false;
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE,AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE,l);
			}
		});
	}

}
