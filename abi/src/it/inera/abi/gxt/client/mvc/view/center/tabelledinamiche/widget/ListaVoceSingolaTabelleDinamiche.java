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
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
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
 * della tabella dinamica generica con struttura (id_record, descrizione)
 * 
 */
public class ListaVoceSingolaTabelleDinamiche extends ContentPanel {
	
	private boolean modifica;
	private Grid<VoceUnicaModel> grid = null;
	private RowEditorCustom<VoceUnicaModel> re = null;
	private ListStore<VoceUnicaModel> store = null;
	private PagingLoader<PagingLoadResult<ModelData>> loader = null;
	private PagingToolBar toolBarBottom;

	private int tipoTabella = -1;
	private TabelleDinamicheServiceAsync tabelleDinamicheService;

	private TextField<String> descrizioneVoce; 

	public ListaVoceSingolaTabelleDinamiche() {
		setLayout(new FitLayout());
		modifica = false;
		tabelleDinamicheService = (TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
	}

	public void setTitleHead(String heading) {
		setHeading(heading);
	}

	public void setTableType(int tableType) {
		tipoTabella = tableType;
	}


	public void setGrid() {

		final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		descrizioneVoce = new TextField<String>();
		descrizioneVoce.setAllowBlank(false);

		ColumnConfig columnDenominazione = new ColumnConfig();
		columnDenominazione.setId("entry");
		columnDenominazione.setHeader("Denominazione voce");
		columnDenominazione.setWidth(400);
		columnDenominazione.setEditor(new CellEditor(descrizioneVoce));

		configs.add(columnDenominazione);

		RpcProxy<PagingLoadResult<VoceUnicaModel>> proxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {
			@Override
			public void load(Object loadConfig,	AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getTabellaDinamicaVoceSingolaList((PagingLoadConfig) loadConfig, tipoTabella, callback);
			}
		};
		
		// loader
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<VoceUnicaModel>(loader);
		re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);

		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);
		
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				unmask();
			}
		});

		grid = new Grid<VoceUnicaModel>(store, new ColumnModel(configs));
		grid.setAutoExpandColumn("entry");
		grid.setAutoExpandMax(1000);
		grid.setStripeRows(true);
		grid.setColumnLines(true); 
		grid.setAutoWidth(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		//Listener per paginazione
		grid.addListener(Events.Attach, new Listener<GridEvent<VoceUnicaModel>>() {
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				mask("Caricamento...", "x-mask-loading");
				final PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(TabelleDinamicheView.GRID_ROWS_NUMBER);

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
				loader.load(config);
			}
		});
		
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);
					
				} else {
					modifica = false;
				}
			}
		});

		add(grid);
		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						
						if (btn.getText().equalsIgnoreCase("Si")) {
							VoceUnicaModel nuovaVoce = new VoceUnicaModel();
							nuovaVoce.setIdTabella(tipoTabella);
							
							if (modifica == false) {
								nuovaVoce.setEntry(grid.getStore().getAt(0).getEntry());
								
							} else {
								nuovaVoce.setIdRecord(grid.getSelectionModel().getSelectedItem().getIdRecord());
								nuovaVoce.setEntry(grid.getSelectionModel().getSelectedItem().getEntry());
							}

							tabelleDinamicheService.addEntryTabellaDinamicaVoceSingola(nuovaVoce, modifica, new AsyncCallback<Void>() {
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
									if (caught instanceof DuplicatedEntryClientSideException) {
										AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										
									} else {
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}
									
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
				modifica = false;
				VoceUnicaModel newVoce = new VoceUnicaModel();
				re.stopEditing(false);
				store.insert(newVoce, 0);
				re.startEditing(store.indexOf(newVoce), false);

				descrizioneVoce.clearInvalid();
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
					re.startEditing(store.indexOf(grid.getSelectionModel().getSelectedItem()), false);

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
								int id_toRemove = grid.getSelectionModel().getSelectedItem().getIdRecord();								
								tabelleDinamicheService.removeEntryTabellaDinamicaVoceSingola(id_toRemove,tipoTabella,new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										
										loader.load();
									}
	
									@Override
									public void onFailure(Throwable caught) {
										UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_COSTRAINT_KEY_VOCE_MESSAGE_GENERICO, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										loader.load();
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
		setTopComponent(toolBar);

		grid.addListener(Events.RowClick, new Listener<GridEvent<VoceUnicaModel>>() {
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<VoceUnicaModel>>() {
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				modifica = true;
			}
		});
		
		toolBarBottom = new PagingToolBar(TabelleDinamicheView.GRID_ROWS_NUMBER);
		toolBarBottom.bind(loader);
		toolBarBottom.enable();
		setBottomComponent(toolBarBottom);
	}

	public Grid<VoceUnicaModel> getGrid() {
		return grid;
	}

}
