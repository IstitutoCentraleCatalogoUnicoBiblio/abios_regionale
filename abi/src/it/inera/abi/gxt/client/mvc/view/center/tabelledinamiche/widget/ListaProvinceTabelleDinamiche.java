package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.ComboBoxForBeans;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
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
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette la gestione (modifica, aggiungi, rimuovi) 
 * della tabella dinamica delle province
 * 
 */
public class ListaProvinceTabelleDinamiche extends ContentPanel {
	
	public TabelleDinamicheServiceAsync tabelleDinamicheService;
	private boolean modifica;
	private Grid<ProvinceModel> grid;
	private BaseListLoader<ListLoadResult<ProvinceModel>> provinceLoader;

	public ListaProvinceTabelleDinamiche() {
		tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		modifica = false;
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

		final TextFieldCustom<String> denominazioneProvincia = new TextFieldCustom<String>();
		denominazioneProvincia.setMaxLength(100);
		denominazioneProvincia.setEmptyText("Denominazione...");
		denominazioneProvincia.setAllowBlank(false);

		ColumnConfig columnDenominazioneProvincia = new ColumnConfig();
		columnDenominazioneProvincia.setId("denominazione");
		columnDenominazioneProvincia.setHeader("Denominazione");
		columnDenominazioneProvincia.setWidth(380);
		columnDenominazioneProvincia.setEditor(new CellEditor(denominazioneProvincia));
		configs.add(columnDenominazioneProvincia);

		final TextFieldCustom<String> siglaProvincia = new TextFieldCustom<String>();
		siglaProvincia.setEmptyText("Sigla...");
		siglaProvincia.setAllowBlank(false);
		siglaProvincia.setMinLength(2);
		siglaProvincia.setMaxLength(2);


		ColumnConfig columnSiglaProvincia = new ColumnConfig();
		columnSiglaProvincia.setId("sigla");
		columnSiglaProvincia.setHeader("Sigla");
		columnSiglaProvincia.setWidth(400);
		columnSiglaProvincia.setEditor(new CellEditor(siglaProvincia));
		configs.add(columnSiglaProvincia);

		final LocationServiceAsync locationService = Registry.get(Abi.LOCATION_SERVICE);
		/* STATI */
		RpcProxy<List<RegioniModel>> regioniProxy = new RpcProxy<List<RegioniModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<RegioniModel>> callback) {
				// ID associato tabella tipologie funzionali
				locationService.getRegioni(callback);

			}

		};

		ModelReader regioniReader = new ModelReader();

		BaseListLoader<ListLoadResult<RegioniModel>> regioniLoader = new BaseListLoader<ListLoadResult<RegioniModel>>(
				regioniProxy, regioniReader);
		ListStore<RegioniModel> regioniStore = new ListStore<RegioniModel>(regioniLoader);

		final ComboBoxForBeans<RegioniModel> regioneField = new ComboBoxForBeans<RegioniModel>();
		regioneField.setWidth(400);
		regioneField.setDisplayField("denominazione");
		regioneField.setStore(regioniStore);
		regioneField.setFireChangeEventOnSetValue(true);
		regioneField.setEmptyText("Scegli una regione...");
		regioneField.setForceSelection(false);
		regioneField.setLazyRender(false);
		regioneField.setAllowBlank(false);
		regioneField.setTriggerAction(TriggerAction.ALL);


		CellEditor statoComboEditor = new CellEditor(regioneField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					RegioniModel tmpRegione = new RegioniModel();
					tmpRegione.setIdRegione(grid.getSelectionModel().getSelectedItem().getIdRegione());
					tmpRegione.setDenominazione(grid.getSelectionModel().getSelectedItem().getRegioneDenominazione());
					return tmpRegione;
				} else
					return "Regione...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				RegioniModel tmp=(RegioniModel) value;
				return tmp.getDenominazione();
			}
		};

		// Config per colonna descrizione
		ColumnConfig statoColumn = new ColumnConfig();
		statoColumn.setId("regioneDenominazione");
		statoColumn.setHeader("Regione di appartenenza");
		statoColumn.setWidth(300);
		statoColumn.setEditor(statoComboEditor);

		configs.add(statoColumn);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<ProvinceModel> re = new RowEditorCustom<ProvinceModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.enableEvents(true);

		RowEditor<ProvinceModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		RpcProxy<List<ProvinceModel>> provinceProxy = new RpcProxy<List<ProvinceModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProvinceModel>> callback) {
				locationService.getProvince(null,callback);

			}

		};
		ModelReader provinceReader = new ModelReader();

		provinceLoader = new BaseListLoader<ListLoadResult<ProvinceModel>>(provinceProxy, provinceReader);

		final ListStore<ProvinceModel> provinceStore = new ListStore<ProvinceModel>(provinceLoader);

		provinceLoader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				unmask();
			}
		});
		
		grid = new Grid<ProvinceModel>(provinceStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<ProvinceModel>>() {
			public void handleEvent(GridEvent<ProvinceModel> be) {
				mask("Caricamento...", "x-mask-loading");				
				provinceLoader.load();
			}
		});

		regioneField.addSelectionChangedListener(new SelectionChangedListener<RegioniModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<RegioniModel> se) {
				if (se.getSelectedItem() != null) {
					if (modifica == false) {
						grid.getStore().getAt(0).setIdRegione(se.getSelectedItem().getIdRegione());

					} else {
						grid.getSelectionModel().getSelectedItem().setIdRegione(se.getSelectedItem().getIdRegione());
					}
				}
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
				ProvinceModel newSpec = new ProvinceModel();
				re.stopEditing(false);
				provinceStore.insert(newSpec, 0);
				re.startEditing(provinceStore.indexOf(newSpec), false);

				denominazioneProvincia.clearInvalid();	
				siglaProvincia.clearInvalid();	
				regioneField.clearInvalid();
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
					re.startEditing(provinceStore.indexOf(grid.getSelectionModel().getSelectedItem()), false);

				} else {
					if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() > 1) {
						/* Selezione multipla */
						AbiMessageBox.messageAlertBox(AbiMessageBox.SELEZIONE_MULTIPLA_TO_MODIFY_MESSAGE, AbiMessageBox.SELEZIONE_MULTIPLA_TITLE);
						
						
					} else {/* Nessuna selezione */
						AbiMessageBox.messageAlertBox(AbiMessageBox.MANCATA_SELEZIONE_TO_MODIFY_MESSAGE, AbiMessageBox.MANCATA_SELEZIONE_TITLE);
						
					}
				}

			}
		});
		
		toolBar.add(edit);


		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItems() != null && grid.getSelectionModel().getSelectedItems().size() == 1) {

					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

						@Override
						public void handleEvent(MessageBoxEvent ce) {

							Button btn = ce.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {
								int idr_removeRecord =grid.getSelectionModel().getSelectedItem().getIdProvincia();
								tabelleDinamicheService.removeProvinciaTabelleDinamiche(idr_removeRecord,new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										provinceLoader.load();
										
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											if (caught instanceof CostraintKeyViolationClientSideException){
												AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											}else{
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											}
											provinceLoader.load();
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
						
					} else {/* Nessuna selezione */
						AbiMessageBox.messageAlertBox(AbiMessageBox.MANCATA_SELEZIONE_TO_REMOVE_MESSAGE, AbiMessageBox.MANCATA_SELEZIONE_TITLE);
					}
				} 
			}
		});


		grid.addListener(Events.RowClick, new Listener<GridEvent<ProvinceModel>>() {
			public void handleEvent(GridEvent<ProvinceModel> be) {
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick,new Listener<GridEvent<ProvinceModel>>() {
			@Override
			public void handleEvent(GridEvent<ProvinceModel> be) {
				modifica = true;
			}

		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);

		setTopComponent(toolBar);
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					provinceStore.remove(0);
					
				} else {
					modifica = false;
				}
				
				provinceLoader.load();
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

							ProvinceModel modelToSave=new ProvinceModel();

							if (modifica == false) {		
								modelToSave.setDenominazione( grid.getStore().getAt(0).getDenominazione());
								modelToSave.setSigla(grid.getStore().getAt(0).getSigla().toUpperCase());
								modelToSave.setIdRegione(grid.getStore().getAt(0).getIdRegione());

							} else {
								modelToSave.setIdProvincia(grid.getSelectionModel().getSelectedItem().getIdProvincia());
								modelToSave.setDenominazione(grid.getSelectionModel().getSelectedItem().getDenominazione());
								modelToSave.setSigla(grid.getSelectionModel().getSelectedItem().getSigla().toUpperCase());
								modelToSave.setIdRegione(grid.getSelectionModel().getSelectedItem().getIdRegione());
							} 

							tabelleDinamicheService.addProvinciaTabelleDinamiche(modelToSave,modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									provinceLoader.load();
									modifica = false;
									
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
										provinceLoader.load();
										modifica = false; 
									}
								}
							});

						} 
						else{
							if (modifica == false) {
								provinceStore.remove(0);

							} else {
								modifica = false;
							}
							
							provinceLoader.load();		
						}


					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

	}



}

