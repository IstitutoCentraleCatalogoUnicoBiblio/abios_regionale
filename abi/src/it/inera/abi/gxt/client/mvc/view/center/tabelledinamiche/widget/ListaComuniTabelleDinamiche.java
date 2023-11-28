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
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.ComboBoxForBeans;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
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
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette la gestione (modifica, aggiungi, rimuovi) 
 * della tabella dinamica dei comuni
 * 
 */
public class ListaComuniTabelleDinamiche extends ContentPanel {

	private LocationServiceAsync locationService;
	public TabelleDinamicheServiceAsync tabelleDinamicheService;
	private Button assegnaButton;
	private boolean modifica;
	private int pageSize;
	private Integer idProvincia;
	private BasePagingLoader<PagingLoadResult<ComuniModel>> comuniLoader;
	private Grid<ComuniModel> grid;
	private ListStore<ComuniModel> comuniStore;
	private RowEditorCustom<ComuniModel> re; 
	private ListStore<ProvinceModel> provinceStore;
	private Button edit;
	private Button remove;

	private	CheckBoxSelectionModelCustom<ComuniModel> checkPlugin;

	private ComboBoxForBeans<ProvinceModel> provinceAssegna;
	private BaseListLoader<ListLoadResult<ProvinceModel>> provinceLoader;
	private List<Integer> comuniSelectedIds;
	private ComboBoxForBeans<ProvinceModel>	provinceFiltro;
	private Button selectAll;

	private TextFieldCustom<String> denominazioneComune;
	private	TextFieldCustom<String> codiceIstatComune;
	private	TextFieldCustom<String> codiceFinanzeField;
	private ComboBoxForBeans<ProvinceModel>	provinceField;

	public ListaComuniTabelleDinamiche() {
		locationService= Registry.get(Abi.LOCATION_SERVICE);
		tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		modifica = false;

		comuniSelectedIds = new ArrayList<Integer>();
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
		re = new RowEditorCustom<ComuniModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.enableEvents(true);
		RowEditor<ComuniModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		checkPlugin = new CheckBoxSelectionModelCustom<ComuniModel>();
		checkPlugin.setSelectionMode(SelectionMode.SIMPLE);
		configs.add(checkPlugin.getColumn());

		denominazioneComune = new TextFieldCustom<String>();
		denominazioneComune.setMaxLength(100);
		denominazioneComune.setEmptyText("Denominazione...");
		denominazioneComune.setAllowBlank(false);

		ColumnConfig columnDenominazioneComune = new ColumnConfig();
		columnDenominazioneComune.setId("denominazione");
		columnDenominazioneComune.setHeader("Denominazione");
		columnDenominazioneComune.setWidth(300);
		columnDenominazioneComune.setEditor(new CellEditor(denominazioneComune));
		configs.add(columnDenominazioneComune);


		codiceIstatComune = new TextFieldCustom<String>();
		codiceIstatComune.setMinLength(6);
		codiceIstatComune.setMaxLength(6);
		codiceIstatComune.setEmptyText("Codice...");
		codiceIstatComune.setAllowBlank(false);

		ColumnConfig codiceIstatColumn = new ColumnConfig();
		codiceIstatColumn.setId("codice_istat");
		codiceIstatColumn.setHeader("Codice ISTAT");
		codiceIstatColumn.setWidth(150);
		codiceIstatColumn.setEditor(new CellEditor(codiceIstatComune));
		configs.add(codiceIstatColumn);

		codiceFinanzeField = new TextFieldCustom<String>();
		codiceFinanzeField.setMinLength(4);
		codiceFinanzeField.setMaxLength(4);
		codiceFinanzeField.setEmptyText("Codice...");
		codiceFinanzeField.setAllowBlank(false);

		ColumnConfig codiceFinanzeColumn = new ColumnConfig();
		codiceFinanzeColumn.setId("codice_finanze");
		codiceFinanzeColumn.setHeader("Codice finanze");
		codiceFinanzeColumn.setWidth(150);
		codiceFinanzeColumn.setEditor(new CellEditor(codiceFinanzeField));
		configs.add(codiceFinanzeColumn);



		locationService = Registry.get(Abi.LOCATION_SERVICE);
		/* Province */
		RpcProxy<List<ProvinceModel>> provinceProxy = new RpcProxy<List<ProvinceModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProvinceModel>> callback) {
				locationService.getProvince(null, callback);
			}
		};

		ModelReader provinceReader = new ModelReader();

		provinceLoader = new BaseListLoader<ListLoadResult<ProvinceModel>>(provinceProxy, provinceReader);
		provinceStore = new ListStore<ProvinceModel>(provinceLoader);

		provinceLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				ProvinceModel  model = new ProvinceModel();
				model.setDenominazione("Tutte le province");
				model.setIdProvincia(-1);
				provinceStore.insert(model, 0);
			}
		});

		provinceField = new ComboBoxForBeans<ProvinceModel>();
		provinceField.setWidth(200);
		provinceField.setFieldLabel("Province di appartenenza");
		provinceField.setDisplayField("denominazione");
		provinceField.setStore(provinceStore);
		provinceField.setFireChangeEventOnSetValue(true);
		provinceField.setEmptyText("Scegli una provincia...");
		provinceField.setForceSelection(false);
		provinceField.setLazyRender(false);
		provinceField.setAllowBlank(false);
		provinceField.setTriggerAction(TriggerAction.ALL);

		CellEditor provinceFieldEditor = new CellEditor(provinceField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					ComuniModel tmp= grid.getStore().findModel("denominazioneProvincia", value.toString());
					ProvinceModel tmpProvincia = new ProvinceModel();
					tmpProvincia.setIdProvincia(tmp.getIdProvincia());
					tmpProvincia.setDenominazione(tmp.getDenominazioneProvincia());
					return tmpProvincia;

				} else {
					return "Provincia...";
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				
				ProvinceModel tmp = (ProvinceModel) value;
				return tmp.getDenominazione();
			}
		};
		
		// Config per colonna descrizione
		ColumnConfig provinciaColumn = new ColumnConfig();
		provinciaColumn.setId("denominazioneProvincia");
		provinciaColumn.setHeader("Provincia di appartenenza");
		provinciaColumn.setWidth(300);
		provinciaColumn.setEditor(provinceFieldEditor);

		configs.add(provinciaColumn);

		ColumnModel cm = new ColumnModel(configs);

		RpcProxy<PagingLoadResult<ComuniModel>> comuniProxy = new RpcProxy<PagingLoadResult<ComuniModel>>() {
			@Override
			protected void load(Object loadConfig,AsyncCallback<PagingLoadResult<ComuniModel>> callback) {
				locationService.getComuniByIdProvincia(idProvincia,(PagingLoadConfig) loadConfig,callback);
			}
		};
		
		ModelReader comuniReader = new ModelReader();

		comuniLoader = new BasePagingLoader<PagingLoadResult<ComuniModel>>(comuniProxy, comuniReader);
		comuniLoader.setRemoteSort(true);
		comuniStore = new ListStore<ComuniModel>(comuniLoader);

		comuniLoader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				if (provinceFiltro.getValue() != null) {
					if (provinceFiltro.getValue().getIdProvincia() != -1) {
						be.<ModelData> getConfig().set("idProvincia", provinceFiltro.getValue().getIdProvincia());
					}
				}
			}
		});

		comuniLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (comuniSelectedIds.size() > 0) {
					//Recupero la lista dei comuni dallo store
					List<ComuniModel> tmpComuni = (List<ComuniModel>) comuniStore.getModels();
					
					Iterator<ComuniModel> it = tmpComuni.iterator();
					while (it.hasNext()) {
						ComuniModel comuniModel = (ComuniModel) it.next();
						//Itero la lista degli id dei comuni di cui bisogna ripristinare la selezione
						Iterator<Integer> iti = comuniSelectedIds.iterator();
						while (iti.hasNext()) {	
							Integer tmpSelected = iti.next();
							
							//se l'id comune è contenuto tra gli id da ripristinare
							if (comuniModel.getIdComune() == tmpSelected.intValue()) {
								//setto il comune come selezionato
								checkPlugin.select(comuniModel, true);
							}
						}
					}
				}

				Utils.changeSelectAllText(checkPlugin, comuniStore, selectAll);
				unmask();
			}

		});

		grid = new Grid<ComuniModel>(comuniStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setAutoExpandColumn("denominazioneProvincia");
		grid.setSelectionModel(checkPlugin);

		grid.addPlugin(checkPlugin);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<ComuniModel>>() {
			public void handleEvent(GridEvent<ComuniModel> be) {
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
					config.setSortDir(SortDir.valueOf((String) state
							.get("sortDir")));
				}
				comuniLoader.load(config);
			}
		});

		provinceField.addSelectionChangedListener(new SelectionChangedListener<ProvinceModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ProvinceModel> se) {
				if (se.getSelectedItem() != null) {
					if (modifica == false) {
						grid.getStore().getAt(0).setIdProvincia(se.getSelectedItem().getIdProvincia());
						
					} else {
						grid.getSelectionModel().getSelectedItem().setIdProvincia(se.getSelectedItem().getIdProvincia());
					}
				}
			}
		});

		add(grid);

		PagingToolBar toolBarBottom = new PagingToolBar(pageSize);
		toolBarBottom.bind(comuniLoader);
		toolBarBottom.enable();
		setBottomComponent(toolBarBottom);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					comuniStore.remove(0);

				} else {
					modifica = false;
				}

				checkPlugin.deselectAll();
				comuniSelectedIds.clear();
				provinceAssegna.disable();
				assegnaButton.disable();
				comuniLoader.load();
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
							ComuniModel modelToSave=new ComuniModel();

							if (modifica == false) {		
								modelToSave.setDenominazione(grid.getStore().getAt(0).getDenominazione());
								modelToSave.setCodiceIstat(grid.getStore().getAt(0).getCodiceIstat());
								modelToSave.setCodiceFinanze(grid.getStore().getAt(0).getCodiceFinanze());
								modelToSave.setIdProvincia(grid.getStore().getAt(0).getIdProvincia());

							} else {
								modelToSave.setIdComune(grid.getSelectionModel().getSelectedItem().getIdComune());
								modelToSave.setDenominazione(grid.getSelectionModel().getSelectedItem().getDenominazione());
								modelToSave.setCodiceIstat(grid.getSelectionModel().getSelectedItem().getCodiceIstat());
								modelToSave.setCodiceFinanze(grid.getSelectionModel().getSelectedItem().getCodiceFinanze());
								modelToSave.setIdProvincia(grid.getSelectionModel().getSelectedItem().getIdProvincia());
							} 

							tabelleDinamicheService.addComuneTabelleDinamiche(modelToSave, modifica, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									modifica = false;
									checkPlugin.deselectAll();
									comuniSelectedIds.clear();
									provinceAssegna.disable();
									assegnaButton.disable();
									comuniLoader.load();
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException) {
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											
										} else {
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										
										modifica = false; 
										checkPlugin.deselectAll();
										comuniSelectedIds.clear();
										provinceAssegna.disable();
										assegnaButton.disable();
										comuniLoader.load();
									}
								}
							});

						} else {
							if (modifica == false) {
								comuniStore.remove(0);

							} else {
								modifica = false;
							}

							comuniLoader.load();
							assegnaButton.disable();
						}


					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

	}

	public void setTopToolbar() {
		ToolBar toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		/* BOTTONE SELEZIONA TUTTO */
		selectAll = new Button("Seleziona tutto", Resources.ICONS.selectall());
		selectAll.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (checkPlugin.getSelectedItems().size() < comuniStore.getCount()) {
					checkPlugin.selectAll();
					selectAll.setText("Deseleziona tutto");

				} else if (checkPlugin.getSelectedItems().size() == comuniStore.getCount()) {
					checkPlugin.deselectAll();
					selectAll.setText("Seleziona tutto");

				}

				checkPlugin.fireEvent(Events.HeaderClick);
				grid.fireEvent(Events.OnClick);
			}
		});


		/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
		 * selezionare/deselezionare tutti i comuni. */
		grid.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectAllText(checkPlugin, comuniStore, selectAll);
				
				if (checkPlugin.getSelectedItems().size() == comuniStore.getModels().size()) {				
					/* Sono stati selezionati tutti i comuni */
					List<ComuniModel> ulist = (List<ComuniModel>) checkPlugin.getSelectedItems();

					for (ComuniModel entry : ulist) {
						if (!comuniSelectedIds.contains(entry.getIdComune()))
							comuniSelectedIds.add(new Integer(entry.getIdComune()));
					}
				}
				else if (checkPlugin.getSelectedItems().size() == 0) {
					/* Sono stati deselezionati tutti i comuni */
					Utils.removeIdsOfComunisInAuctualListOnDeselectAll(comuniSelectedIds, comuniStore);

				}
			}
		});

		/*FINE----BOTTONE SELEZIONA TUTTO*/

		Button add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ComuniModel newSpec = new ComuniModel();
				re.stopEditing(false);
				comuniStore.insert(newSpec, 0);
				re.startEditing(comuniStore.indexOf(newSpec), false);

				denominazioneComune.clearInvalid();
				codiceIstatComune.clearInvalid();
				codiceFinanzeField.clearInvalid();
				provinceField.clearInvalid();
			}
		});

		edit = new Button("Modifica");
		edit.setIcon(Resources.ICONS.edit());
		edit.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() == 1) {
					modifica = true;
					re.stopEditing(false);
					re.startEditing(comuniStore.indexOf(grid.getSelectionModel().getSelectedItem()), false);

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

		remove = new Button("Rimuovi");
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
								int idr_removeRecord = grid.getSelectionModel().getSelectedItem().getIdComune();

								tabelleDinamicheService.removeComuneTabelleDinamiche(idr_removeRecord,new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										
										comuniSelectedIds.clear();
										checkPlugin.deselectAll();
										comuniLoader.load();
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											if (caught instanceof CostraintKeyViolationClientSideException) {
												AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
												
											} else {
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											}
											
											comuniSelectedIds.clear();
											checkPlugin.deselectAll();	
											comuniLoader.load();
										}
									}
								});
							}
						}

					};
					
					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE,AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

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

		grid.addListener(Events.RowClick, new Listener<GridEvent<ComuniModel>>() {
			public void handleEvent(GridEvent<ComuniModel> se) {
				if (checkPlugin.isSelected(se.getModel())) {
					checkPlugin.select(se.getModel(), true);

					/*se non è già contenuto tra gli id selezionati*/
					if(!comuniSelectedIds.contains(se.getModel().getIdComune())){
						comuniSelectedIds.add(se.getModel().getIdComune());
					}
					provinceAssegna.enable();

				} else {
					checkPlugin.deselect(se.getModel());
					comuniSelectedIds.remove((Integer)se.getModel().getIdComune());

					if ((comuniSelectedIds.size() == 0)) {
						provinceAssegna.disable();
						provinceAssegna.clear();
						assegnaButton.disable();
					}
				}

			}
		});

		//Caso Select/Deselect ALL
		checkPlugin.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectAllText(checkPlugin, comuniStore, selectAll);

				//prendo la lista dei comuni selezionati
				List<ComuniModel> allSelected = grid.getSelectionModel().getSelectedItems();

				//Controllo che sia > 0, significa che sono selezionati tutti
				if (allSelected.size() > 0) {
					Iterator<ComuniModel> it = allSelected.iterator();
					while (it.hasNext()) {
						//se non sono già contenuti nella lista dei comuni selezionai aggiungo i loro idComune
						ComuniModel comuniModel = (ComuniModel) it.next();
						
						if (!comuniSelectedIds.contains(comuniModel.getIdComune())) {
							comuniSelectedIds.add(comuniModel.getIdComune());
						}

					}
					provinceAssegna.enable();

				} else {
					//Se la lista di comuni selezionati < 0
					List<ComuniModel> allDeSelected  =grid.getStore().getModels();
					Iterator<ComuniModel> it = allDeSelected.iterator();
					Integer tmpIndex = null;

					while (it.hasNext()) {
						//elimino gli idComune da comuniSelectedIds
						ComuniModel comuniModel = (ComuniModel) it.next();						
						comuniSelectedIds.remove((Integer) comuniModel.getIdComune());

						Iterator<Integer> iti = comuniSelectedIds.iterator();

						while (iti.hasNext()) {
							Integer integer = (Integer) iti.next();

							if (comuniModel.getIdComune() == integer.intValue()) {
								tmpIndex = comuniSelectedIds.indexOf(integer);
							}
						}
						if (tmpIndex != null) {
							comuniSelectedIds.remove(tmpIndex.intValue());
						}

					}

					if ((comuniSelectedIds.size() == 0)) {
						provinceAssegna.disable();
						assegnaButton.disable();
					}
				}
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<ComuniModel>>() {
			@Override
			public void handleEvent(GridEvent<ComuniModel> be) {
				modifica = true;
			}
		});

		Text provinceFiltroLabel = new Text("Filtra per provincia:&nbsp;");

		provinceFiltro = new ComboBoxForBeans<ProvinceModel>();
		provinceFiltro.setWidth(200);
		provinceFiltro.setDisplayField("denominazione");
		provinceFiltro.setStore(provinceStore);
		provinceFiltro.setFireChangeEventOnSetValue(true);
		provinceFiltro.setEmptyText("Filtra province...");
		provinceFiltro.setForceSelection(false);
		provinceFiltro.setLazyRender(false);
		provinceFiltro.setTriggerAction(TriggerAction.ALL);
		provinceFiltro.setEditable(false);

		Text provinceAssegnaLabel = new Text("Assegna&nbsp;");

		provinceFiltro.addSelectionChangedListener(new SelectionChangedListener<ProvinceModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ProvinceModel> se) {
				final PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(pageSize);
				if (se.getSelectedItem() != null) {
					if (se.getSelectedItem().getIdProvincia() != -1) {
						config.set("idProvincia", se.getSelectedItem().getIdProvincia());
						
					}
				}
				
				Map<String, Object> state = grid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					int limit = (Integer) state.get("limit");
					config.setOffset(offset);
					config.setLimit(limit);
				}
				if (state.containsKey("sortField")) {
					config.setSortField((String) state.get("sortField"));
					config.setSortDir(SortDir.valueOf((String) state
							.get("sortDir")));
				}
				mask("Caricamento...", "x-mask-loading");
				comuniLoader.load(config);
				
			}

		});

		ListStore<ProvinceModel> provinceAssegnaStore = new ListStore<ProvinceModel>(provinceLoader);
		provinceAssegna = new ComboBoxForBeans<ProvinceModel>();
		provinceAssegna.setWidth(200);
		provinceAssegna.setDisplayField("denominazione");
		provinceAssegna.setStore(provinceAssegnaStore);
		provinceAssegna.setFireChangeEventOnSetValue(true);
		provinceAssegna.setEmptyText("Assegna a provincia...");
		provinceAssegna.setForceSelection(false);
		provinceAssegna.setLazyRender(false);
		provinceAssegna.setTriggerAction(TriggerAction.ALL);
		provinceAssegna.setEditable(false);
		provinceAssegna.disable();

		provinceAssegna.addSelectionChangedListener(new SelectionChangedListener<ProvinceModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ProvinceModel> se) {
				assegnaButton.enable();
			}
		});

		assegnaButton = new Button("Assegna");
		assegnaButton.setIcon(Resources.ICONS.assegna());
		assegnaButton.disable();
		assegnaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int idProvincia = provinceAssegna.getValue().getIdProvincia();

							locationService.assegnaComuniAProvincia(idProvincia,comuniSelectedIds, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									
									comuniSelectedIds.clear();
									comuniLoader.load();
									provinceAssegna.clear();
									assegnaButton.disable();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

										comuniSelectedIds.clear();
										provinceAssegna.clear();
										assegnaButton.disable();
									}
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox("Assegnare i comuni selezionati alla provincia di "+provinceAssegna.getValue().getDenominazione(), AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, l);

				provinceAssegna.disable();
				assegnaButton.disable();	

				checkPlugin.deselectAll();
			}
		});

		toolBar.add(selectAll);
		toolBar.add(add);
		toolBar.add(edit);
		toolBar.add(remove);

		toolBar.add(provinceFiltroLabel);
		toolBar.add(provinceFiltro);

		toolBar.add(new SeparatorToolItem()); 
		toolBar.add(provinceAssegnaLabel);
		toolBar.add(provinceAssegna);
		toolBar.add(assegnaButton);

		setTopComponent(toolBar);
	}
}

