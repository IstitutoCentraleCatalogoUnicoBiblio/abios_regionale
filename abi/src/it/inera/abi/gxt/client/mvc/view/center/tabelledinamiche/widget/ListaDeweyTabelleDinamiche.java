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
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.resources.Resources;
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
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
 * della tabella dinamica dei dewey
 * 
 */
public class ListaDeweyTabelleDinamiche extends ContentPanel {
	
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private boolean modifica;
	private int pageSize;

	private BasePagingLoader<PagingLoadResult<SpecializzazioneModel>> specializzazioniLoader;

	public ListaDeweyTabelleDinamiche() {
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

		final TextField<String> codiceDewey = new TextField<String>();
		codiceDewey.setMaxLength(7);
		codiceDewey.setEmptyText("Codice Dewey...");
		codiceDewey.setAllowBlank(false);

		ColumnConfig columnCodiceDewey = new ColumnConfig();
		columnCodiceDewey.setId("dewey");
		columnCodiceDewey.setHeader("Codice Dewey");
		columnCodiceDewey.setWidth(400);
		columnCodiceDewey.setEditor(new CellEditor(codiceDewey));
		configs.add(columnCodiceDewey);

		final	TextField<String> descrUfficiale = new TextField<String>();
		descrUfficiale.setEmptyText("Descrizione ufficiale...");
		descrUfficiale.setAllowBlank(false);

		ColumnConfig columnDescrUfficiale = new ColumnConfig();
		columnDescrUfficiale.setId("descrizioneDewey");
		columnDescrUfficiale.setHeader("Descrizione ufficiale");
		columnDescrUfficiale.setWidth(400);
		columnDescrUfficiale.setEditor(new CellEditor(descrUfficiale));
		configs.add(columnDescrUfficiale);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<SpecializzazioneModel> re = new RowEditorCustom<SpecializzazioneModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.enableEvents(true);

		RowEditor<SpecializzazioneModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		RpcProxy<PagingLoadResult<SpecializzazioneModel>> specializzazioniProxy = new RpcProxy<PagingLoadResult<SpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback) {
				tabelleDinamicheService.getDeweyTabelleDinamiche((PagingLoadConfig)loadConfig, callback);

			}

		};

		ModelReader specializzazioniReader = new ModelReader();

		specializzazioniLoader = new BasePagingLoader<PagingLoadResult<SpecializzazioneModel>>(specializzazioniProxy, specializzazioniReader);
		specializzazioniLoader.setRemoteSort(true);
		final ListStore<SpecializzazioneModel> store = new ListStore<SpecializzazioneModel>(specializzazioniLoader);

		specializzazioniLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				unmask();
			}	
		});

		final Grid<SpecializzazioneModel> grid = new Grid<SpecializzazioneModel>(store, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<SpecializzazioneModel>>() {
			public void handleEvent(GridEvent<SpecializzazioneModel> be) {
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
				specializzazioniLoader.load(config);
			}
		});

		ToolBar toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		Button add = new Button("Aggiungi");
		final Button edit = new Button("Modifica");
		final Button remove = new Button("Rimuovi");

		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				SpecializzazioneModel newSpec = new SpecializzazioneModel();
				re.stopEditing(false);
				store.insert(newSpec, 0);
				re.startEditing(store.indexOf(newSpec), false);

				codiceDewey.clearInvalid();
				descrUfficiale.clearInvalid();
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
					codiceDewey.disable();
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
								String idr_removeRecord = grid.getSelectionModel().getSelectedItem().getDewey();
								tabelleDinamicheService.removeDeweyTabelleDinamiche(idr_removeRecord, new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										specializzazioniLoader.load();
										
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											specializzazioniLoader.load();
										}
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

		toolBar.add(remove);


		PagingToolBar toolBarBottom = new PagingToolBar(pageSize);
		toolBarBottom.bind(specializzazioniLoader);
		toolBarBottom.enable();
		setBottomComponent(toolBarBottom);

		grid.addListener(Events.RowClick, new Listener<GridEvent<SpecializzazioneModel>>() {
			public void handleEvent(GridEvent<SpecializzazioneModel> be) {
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<SpecializzazioneModel>>(){
			@Override
			public void handleEvent(GridEvent<SpecializzazioneModel> be) {
				modifica = true;
				codiceDewey.disable();
			}
		});

		add(grid);
		setTopComponent(toolBar);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);

				} else {
					modifica = false;
				}
				specializzazioniLoader.load();
				codiceDewey.enable();
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
							SpecializzazioneModel modelToSave=new SpecializzazioneModel();

							if (modifica == false) {		
								modelToSave.setDewey( grid.getStore().getAt(0).getDewey());
								modelToSave.setDecrizione(grid.getStore().getAt(0).getDecrizione());

							} else {
								modelToSave.setDewey(grid.getSelectionModel().getSelectedItem().getDewey());
								modelToSave.setDecrizione(grid.getSelectionModel().getSelectedItem().getDecrizione());
							} 

							tabelleDinamicheService.addDeweyTabelleDinamiche(modelToSave, modifica, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									specializzazioniLoader.load();
									modifica = false;
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									
									codiceDewey.enable();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException) {
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

										} else {
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}

										specializzazioniLoader.load();
										modifica = false; 
										codiceDewey.enable();
									}
								}
							});

						} else {
							if (modifica == false) {
								store.remove(0);

							} else {
								modifica = false;
							}

							specializzazioniLoader.load();	
							codiceDewey.enable();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}
}