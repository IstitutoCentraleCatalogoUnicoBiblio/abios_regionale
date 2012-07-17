package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import java.util.ArrayList;
import java.util.List;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.SistemiPrestitoInterbibliotecarioModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelReader;
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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaSistemiPrestitoInterbibliotecarioTabelleDinamiche extends ContentPanel {
	
	public TabelleDinamicheServiceAsync tabelleDinamicheService;
	private boolean modifica;
	private Grid<SistemiPrestitoInterbibliotecarioModel> grid;
	private BaseListLoader<ListLoadResult<SistemiPrestitoInterbibliotecarioModel>> sistemiLoader;
	
	public ListaSistemiPrestitoInterbibliotecarioTabelleDinamiche() {
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
		
		final TextFieldCustom<String> descrizioneSistemi = new TextFieldCustom<String>();
		descrizioneSistemi.setMaxLength(255);
		descrizioneSistemi.setEmptyText("Descrizione...");
		descrizioneSistemi.setAllowBlank(false);

		ColumnConfig columnDescrizioneSistemi = new ColumnConfig();
		columnDescrizioneSistemi.setId("descrizione");
		columnDescrizioneSistemi.setHeader("Descrizione");
		columnDescrizioneSistemi.setWidth(400);
		columnDescrizioneSistemi.setEditor(new CellEditor(descrizioneSistemi));
		configs.add(columnDescrizioneSistemi);

		final TextFieldCustom<String> urlSistemi = new TextFieldCustom<String>();
		urlSistemi.setEmptyText("Url...");
		urlSistemi.setAllowBlank(true);
		urlSistemi.setMaxLength(255);


		ColumnConfig columnUrlSistemi = new ColumnConfig();
		columnUrlSistemi.setId("url");
		columnUrlSistemi.setHeader("Url");
		columnUrlSistemi.setWidth(400);
		columnUrlSistemi.setEditor(new CellEditor(urlSistemi));
		configs.add(columnUrlSistemi);
		
		ColumnModel cm = new ColumnModel(configs);
	
		final RowEditorCustom<SistemiPrestitoInterbibliotecarioModel> re = new RowEditorCustom<SistemiPrestitoInterbibliotecarioModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.enableEvents(true);

		RowEditor<SistemiPrestitoInterbibliotecarioModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);
		
		RpcProxy<List<SistemiPrestitoInterbibliotecarioModel>> sistemiProxy = new RpcProxy<List<SistemiPrestitoInterbibliotecarioModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<SistemiPrestitoInterbibliotecarioModel>> callback) {
				tabelleDinamicheService.getSistemiPrestitoInterbibliotecario(callback);
			
			}

		};
		ModelReader sistemiReader = new ModelReader();

		sistemiLoader = new BaseListLoader<ListLoadResult<SistemiPrestitoInterbibliotecarioModel>>(sistemiProxy, sistemiReader);


		final ListStore<SistemiPrestitoInterbibliotecarioModel> sistemiStore = new ListStore<SistemiPrestitoInterbibliotecarioModel>(sistemiLoader);
		
		grid = new Grid<SistemiPrestitoInterbibliotecarioModel>(sistemiStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<SistemiPrestitoInterbibliotecarioModel>>() {
			public void handleEvent(GridEvent<SistemiPrestitoInterbibliotecarioModel> be) {
				sistemiLoader.load();
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
				SistemiPrestitoInterbibliotecarioModel newSist = new SistemiPrestitoInterbibliotecarioModel();
				re.stopEditing(false);
				sistemiStore.insert(newSist, 0);
				re.startEditing(sistemiStore.indexOf(newSist), false);

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
					re.startEditing(sistemiStore.indexOf(grid.getSelectionModel().getSelectedItem()), false);

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
								int idr_removeRecord = grid.getSelectionModel().getSelectedItem().getIdSistemiPrestitoInterbibliotecario();
								tabelleDinamicheService.removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(idr_removeRecord, new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										sistemiLoader.load();
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											if (caught instanceof CostraintKeyViolationClientSideException){
												AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											
											} else {
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											}
											
											sistemiLoader.load();
										}
									}
								});
							}
						}
					};
					MessageBox.confirm("Rimozione voce", "La modifica verrà applicata al database! Continuare?", l).show();

					if (grid.getStore().getCount() == 0) {
						ce.<Component> getComponent().disable();
					}

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

		grid.addListener(Events.RowClick, new Listener<GridEvent<SistemiPrestitoInterbibliotecarioModel>>() {
			public void handleEvent(GridEvent<SistemiPrestitoInterbibliotecarioModel> be) {
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<SistemiPrestitoInterbibliotecarioModel>>() {
			@Override
			public void handleEvent(GridEvent<SistemiPrestitoInterbibliotecarioModel> be) {
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
					sistemiStore.remove(0);
					
				} else {
					modifica = false;
				}
				
				sistemiLoader.load();
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

							SistemiPrestitoInterbibliotecarioModel modelToSave = new SistemiPrestitoInterbibliotecarioModel();

							if (modifica == false) {		
								modelToSave.setDescrizione(grid.getStore().getAt(0).getDescrizione());
								modelToSave.setUrl(grid.getStore().getAt(0).getUrl());

							} else {
								modelToSave.setIdSistemiPrestitoInterbibliotecario(grid.getSelectionModel().getSelectedItem().getIdSistemiPrestitoInterbibliotecario());
								modelToSave.setDescrizione(grid.getSelectionModel().getSelectedItem().getDescrizione());
								modelToSave.setUrl(grid.getSelectionModel().getSelectedItem().getUrl());
							} 

							tabelleDinamicheService.addSistemiPrestitoInterbibliotecarioTabelleDinamiche(modelToSave, modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									sistemiLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException) {
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											
										} else {
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										
										sistemiLoader.load();
										modifica = false; 
									}
								}
							});

						} else {
							if (modifica == false) {
								sistemiStore.remove(0);

							} else {
								modifica = false;
							}
							
							sistemiLoader.load();		
						}
					}
				};
				
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}
}
