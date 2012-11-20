package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.AuthServiceAsync;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette la visualizzazione / modifica delle 
 * informazioni relative agli utenti
 *
 */
public class ListaUtentiPanel extends ContentPanel {

	protected Grid<UserModel> grid = null;
	protected PagingLoader<PagingLoadResult<ModelData>> loader = null;
	protected UtentiServiceAsync utentiService = null;
	protected AuthServiceAsync authServiceAsync = null;
	protected boolean checked;
	protected PagingToolBar toolBar = new PagingToolBar(40);
	protected PagingLoadConfig config = null;
	
	private Button remove = new Button("Rimuovi utente", Resources.ICONS.user_delete());
	private Button modifica = new Button("Modifica utente", Resources.ICONS.user_edit());
	private Button assegnaBiblioAUtente = new Button("Assegna biblioteche",Resources.ICONS.toUser());
	private Button vediBiblio = new Button("Vedi biblioteche", Resources.ICONS.table_multiple());

	private HashMap<String, Object> keys = new HashMap<String, Object>();
	
	public ListaUtentiPanel(boolean checked) {
		super();
		this.checked = checked;
		initPanel();
	}
	
	protected void initPanel() {
		setMonitorWindowResize(true);
		setHeading("Lista utenti");
		setIcon(Resources.ICONS.group());
		setLayout(new FitLayout());
		authServiceAsync = Registry.get(Abi.AUTHSERVICE);
		setService();
		setGrid();
		setTopToolbar();
		setBottomToolbar();
	}

	public Grid<UserModel> getGrid() {
		return grid;

	}

	protected void setGrid() {

		final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		CheckBoxSelectionModel<UserModel> sm = null;
		if (checked) {

			sm = new CheckBoxSelectionModel<UserModel>();
			sm.setSelectionMode(SelectionMode.SIMPLE);
			configs.add(sm.getColumn());

		}
		
		ColumnConfig columnUsername = new ColumnConfig();
		columnUsername.setId("login");
		columnUsername.setHeader("User Name");
		columnUsername.setResizable(false);
		columnUsername.setWidth(130);
		columnUsername.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return "<b>" + (String)model.get("login") + "</b>";  
			}  

		});  
		configs.add(columnUsername);
		
		ColumnConfig columnNome = new ColumnConfig();
		columnNome.setId("nome");
		columnNome.setHeader("Nome");
		columnNome.setWidth(130);
		configs.add(columnNome);

		ColumnConfig columnCognome = new ColumnConfig();
		columnCognome.setId("cognome");
		columnCognome.setHeader("Cognome");
		columnCognome.setWidth(130);
		configs.add(columnCognome);

		ColumnConfig columnIncarico = new ColumnConfig();
		columnIncarico.setId("incarico");
		columnIncarico.setHeader("Incarico");
		columnIncarico.setAlignment(HorizontalAlignment.LEFT);
		columnIncarico.setWidth(320);
		columnIncarico.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<ModelData> store, Grid<ModelData> grid) {  
				return "<b><span style=\"color: #385F95; text-decoration: none;width:300;text-align:justify;\">"  
				+ Utils.insertBRtag((String)model.get("incarico") ,75) 
				+ "</span></b>";  
			}  

		});  
		configs.add(columnIncarico);

		GridCellRenderer<UserModel> statoAccountColorCellRenderer = new GridCellRenderer<UserModel>() {  
			public String render(UserModel model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<UserModel> store, Grid<UserModel> grid) {  
				Boolean enabled = (Boolean)model.get(property);  
				String style =null;
				
				if(enabled==null)
					style="black";
				else if(!enabled)
					style="red";
				else	if(enabled)
					style="green";
					

				return "<span style='color:" + style + "'>" + model.getEnabledDescr() + "</span>";  
			}  
		};  
		
		ColumnConfig columnEnabled = new ColumnConfig();
		columnEnabled.setId("enabled");
		columnEnabled.setHeader("Stato account");
		columnEnabled.setWidth(120);
		columnEnabled.setRenderer(statoAccountColorCellRenderer);
		configs.add(columnEnabled);

		
		ListStore<UserModel> store = new ListStore<UserModel>(loader);

		grid = new Grid<UserModel>(store, new ColumnModel(configs));
		grid.setStripeRows(true);
		grid.setColumnLines(true);
		grid.setWidth("100%");
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);

		if (checked) {
			grid.setSelectionModel(sm);
			grid.addPlugin(sm);
		}

		grid.setLoadMask(true);
		add(grid);

	}

	protected void setBottomToolbar() {

		toolBar.bind(loader);
		setBottomComponent(toolBar);
	}

	protected void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();

		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(final ButtonEvent ce) {
				authServiceAsync.retrieveUser(new AsyncCallback<UtentiAuthModel>() {
					
					@Override
					public void onSuccess(UtentiAuthModel result) {
						if (grid.getSelectionModel().getSelectedItem().getUserName().equalsIgnoreCase(result.getUserLogin())) {
							AbiMessageBox.messageAlertBox("Non è possibile rimuovere l'utente attualmente loggato.", "Avviso");
							modifica.disable();
							remove.disable();
						}
						else {
							final MessageBox box = new MessageBox();
							box.setIcon(MessageBox.QUESTION);
							box.setMessage("Rimuovere l'utente " + grid.getSelectionModel().getSelectedItem().getUserName() + "?");
							box.setButtons(MessageBox.YESNO);
							box.addCallback(new Listener<MessageBoxEvent>() {
								@Override
								public void handleEvent(MessageBoxEvent be) {
									if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
										int id_utenti = grid.getSelectionModel().getSelectedItem().getIdUser();
										utentiService.removeUtente(id_utenti, new AsyncCallback<Boolean>() {
											@Override
											public void onSuccess(Boolean result) {
												grid.getStore().remove(grid.getStore().indexOf(grid.getSelectionModel().getSelectedItem()));
												ce.<Component> getComponent().disable();
												loader.load();
												
												/* Messaggio ridondante */
//												AbiMessageBox.messageSuccessAlertBox("Utente rimosso con successo", AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE);
												
												remove.disable();
												modifica.disable();
											}

											@Override
											public void onFailure(Throwable caught) {
												if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
													if (caught instanceof CostraintKeyViolationClientSideException) {
														AbiMessageBox.messageAlertBox(caught.getMessage(), AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
														
													}
													else {
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
													}
													deselectAllAndDisableButtons();
												}
											}

										});

										if (grid.getStore().getCount() == 0) {
											ce.<Component> getComponent().disable();
										}
									}

								}
							});
							box.show();
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login						
					}
				});
		
				
			}
		});
		remove.disable();


		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final MessageBox box = new MessageBox();
				box.setIcon(MessageBox.QUESTION);
				box.setMessage("Modificare l'utente "+grid.getSelectionModel().getSelectedItem().getUserName()+"?");
				box.setButtons(MessageBox.YESNO);
				box.addCallback(new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							// fireEvent(eventType, be)
							int id_utenti = grid.getSelectionModel().getSelectedItem().getIdUser();

							// chiamare il servizio per il caricamento
							// datidell'uitente
							AppEvent event = new AppEvent(AppEvents.ModificaUtente);
							event.setData("id_utenti", id_utenti);

							Dispatcher.forwardEvent(event);
							modifica.disable();
							remove.disable();
							assegnaBiblioAUtente.disable();
						}
					}
				});
				box.show();

			}
		});
		modifica.disable();
		
		vediBiblio.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
//				final MessageBox box = new MessageBox();
//				box.setIcon(MessageBox.QUESTION);
//				box.setMessage("Vai a lista biblioteche gestite dall'utente <b>"+grid.getSelectionModel().getSelectedItem().getUserName()+"</b>?");
//				box.setButtons(MessageBox.YESNO);
//				box.addCallback(new Listener<MessageBoxEvent>() {
//					@Override
//					public void handleEvent(MessageBoxEvent be) {
//						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							// fireEvent(eventType, be)
							int id_utenti = grid.getSelectionModel().getSelectedItem().getIdUser();
							HashMap<String, Object> key = new HashMap<String, Object>();
							key.put("loginUtenteGestore", id_utenti);
							
							AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
							event.setData("parametriRicerca", key);
							event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(false));
							Dispatcher.forwardEvent(event);
//						}
						
//					}
//				});
//				box.show();
			}
		});
		vediBiblio.disable();
		
		assegnaBiblioAUtente.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				AppEvent event = new AppEvent(AppEvents.AssegnaBibliotecheAUtente);
				event.setData("user", grid.getSelectionModel().getSelectedItem());
				Dispatcher.forwardEvent(event);
				remove.disable();
				modifica.disable();
				assegnaBiblioAUtente.disable();
			}
		});
		
		assegnaBiblioAUtente.disable();
		
		grid.addListener(Events.RowClick, new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.enable();
				modifica.enable();
				vediBiblio.enable();
				assegnaBiblioAUtente.enable();
			}
		});
		
		topToolBar.add(vediBiblio);
		topToolBar.add(assegnaBiblioAUtente);
		topToolBar.add(modifica);
		topToolBar.add(remove);
		
		setTopComponent(topToolBar);
	}
	
	public void setKeys(HashMap<String, Object> parametriRicerca) {
		config = new BasePagingLoadConfig();
		
		/** PARAMETRI RICERCA **/
		keys = parametriRicerca;
		config.set("keys", keys);
		
		config.setOffset(0);
		config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);

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

	protected void setService() {
		utentiService = (UtentiServiceAsync) Registry.get(Abi.UTENTI_SERVICE);

		RpcProxy<PagingLoadResult<UserModel>> proxy = new RpcProxy<PagingLoadResult<UserModel>>() {
			@Override
			public void load(Object loadConfig,	AsyncCallback<PagingLoadResult<UserModel>> callback) {
				
				if (((PagingLoadConfig) loadConfig).get("keys") != null) {
					utentiService.getUsers((PagingLoadConfig) loadConfig, callback);
				}
				else {
					((PagingLoadConfig) loadConfig).set("keys",	keys);
					utentiService.getUsers((PagingLoadConfig) loadConfig, callback);
				}
			}
		};

		// loader

		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);
		
	}

	public  PagingLoader<PagingLoadResult<ModelData>> getLoader(){
		return this.loader;
	}

	public void deselectAllAndDisableButtons() {
		grid.getSelectionModel().deselectAll();
		vediBiblio.disable();
		assegnaBiblioAUtente.disable();
		modifica.disable();
		remove.disable();
	}
	

}
