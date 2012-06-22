package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
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
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaBibliotechePanel extends ContentPanel {

	private BasePagingLoader<PagingLoadResult<ModelData>> loader = null;
	private BibliotecheServiceAsync bibliotecheService = null;
	private Grid<BiblioModel> grid = null;
	protected List<Integer> idUsers = null;
	private boolean viaRicerca;
	private boolean ricercaGenerica;
	private boolean viaUtenti;
	private ListStore<BiblioModel> store;

	private boolean loading = false;

	private PagingToolBar toolBar = new PagingToolBar(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);


	private Button visualizza = new Button("Visualizza", Resources.ICONS.table_multiple());
	private Button revisiona = new Button("Revisiona", Resources.ICONS.eye());
	private Button occupa = new Button("Modifica", Resources.ICONS.table_edit());
	private Button gestori = new Button("Gestori", Resources.ICONS.group());
	private Button indietro = new Button("Indietro",Resources.ICONS.arrow_left_red());

	private Boolean directList = new Boolean(false);
	private ListaUtentiGestoriWindow  listaUtentiGestoriWindow;
	PagingLoadConfig config = null;
	private HashMap<String, Object> keys = new HashMap<String, Object>();
	private Boolean viacodice = new Boolean(false);
	private List<ColumnConfig> configs;
	public ListaBibliotechePanel() {
		super();
		setMonitorWindowResize(true);
		setHeading("Lista Biblioteche");
		setIcon(Resources.ICONS.table());
		setLayout(new BorderLayout());
		setBorders(false);

		listaUtentiGestoriWindow = new ListaUtentiGestoriWindow();

		setBorders(false);
		idUsers = new ArrayList<Integer>();

		setService();
		setGrid();
		setTopToolbar();
		setBottomToolbar();

	}

	private void setGrid() {
		List<ColumnConfig> colonne = getBaseColumnConfig();

		store = new ListStore<BiblioModel>(loader);

		grid = new Grid<BiblioModel>(store, new ColumnModel(colonne));
		grid.setStripeRows(true);
		grid.setColumnLines(true); 
		grid.setWidth("100%");
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);
		grid.setLoadMask(true);

		grid.addListener(BaseLoader.BeforeLoad, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				loading = true;
			}
		});
		add(grid, new BorderLayoutData(LayoutRegion.CENTER));

	}

	/**
	 * 
	 */
	private List<ColumnConfig> getBaseColumnConfig() {
		configs = new ArrayList<ColumnConfig>();

		GridCellRenderer<BiblioModel> statoColorCellRenderer = new GridCellRenderer<BiblioModel>() {  
			public String render(BiblioModel model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<BiblioModel> store, Grid<BiblioModel> grid) {  
				String stato = (String)model.get(property);  
				String style =null;
				if(stato.compareToIgnoreCase("Occupata")==0)
					style="red";
				else	if(stato.compareToIgnoreCase("Definitiva")==0)
					style="green";
				else	if(stato.compareToIgnoreCase("Revisione")==0)
					style="blue";
				else	if(stato.compareToIgnoreCase("Cancellata")==0)
					style="black";

				return "<span style='color:" + style + "'>" + stato + "</span>";  
			}  
		};  

		ColumnConfig columnCodice = new ColumnConfig();
		columnCodice.setId("codice");
		columnCodice.setHeader("Codice");
		columnCodice.setWidth(70);
		columnCodice.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return "<b>" + (String)model.get("codice") + "</b>";  
			}  

		});  

		configs.add(columnCodice);

		ColumnConfig denominazione = new ColumnConfig();
		denominazione.setId("denominazione");
		denominazione.setHeader("Denominazione");
		denominazione.setWidth(330);
		denominazione.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return "<b><span style=\"color: #385F95; text-decoration: none;width:300;text-align:justify;\">"  
				+ (String)model.get("denominazione")
				+ "</span></b>";  
			}  

		});  

		configs.add(denominazione);

		ColumnConfig indirizzo = new ColumnConfig();
		indirizzo.setId("indirizzo");
		indirizzo.setHeader("Indirizzo");
		indirizzo.setWidth(110);
		configs.add(indirizzo);

		ColumnConfig comune = new ColumnConfig();
		comune.setId("comuneDenominazione");
		comune.setHeader("Comune");
		comune.setWidth(110);
		configs.add(comune);

		ColumnConfig utenteUltimaModifica = new ColumnConfig();
		utenteUltimaModifica.setId("utenteUltimaModifica");
		utenteUltimaModifica.setHeader("Ultima modifica");
		utenteUltimaModifica.setAlignment(HorizontalAlignment.LEFT);
		utenteUltimaModifica.setWidth(100);
		configs.add(utenteUltimaModifica);

		ColumnConfig statoCatalogazione = new ColumnConfig();
		statoCatalogazione.setId("statoCatalogazione");
		statoCatalogazione.setHeader("Stato di lavorazione");
		statoCatalogazione.setAlignment(HorizontalAlignment.LEFT);
		statoCatalogazione.setWidth(130);
		statoCatalogazione.setRenderer(statoColorCellRenderer);
		configs.add(statoCatalogazione);
		return configs;
	}

	public void disableButtons() {
		visualizza.disable();
		revisiona.disable();
		occupa.disable();
		gestori.disable();
	}
	public void enableButtons() {
		visualizza.enable();
		revisiona.enable();
		occupa.enable();
		gestori.enable();
	}

	private void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		disableButtons();

		grid.addListener(Events.RowClick, new Listener<GridEvent<BiblioModel>>() {
			public void handleEvent(GridEvent<BiblioModel> se) {
				enableButtons();
				if(grid.getSelectionModel().getSelectedItems()!=null){
					if(grid.getSelectionModel().getSelectedItems().size()==1){
						enableButtons();
					}else{
						disableButtons();		
					}
				}
			}
		});


		/**
		 * Visualizza le schede della biblioteca in readOnly
		 */
		visualizza.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				BiblioModel biblioModel = (BiblioModel) grid.getSelectionModel().getSelectedItem();
				if (biblioModel == null) {
					AbiMessageBox.messageAlertBox("Selezionare una biblioteca.", "Avviso");
				} else {
					LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
					
					wrapper.mask("Caricamento...", "x-mask-loading");
					AppEvent appEvent = new AppEvent(AppEvents.SelectBiblio);
					appEvent.setData(BiblioModel.IDBIBLIO, biblioModel.getIdBiblio());
					appEvent.setData(BiblioModel.READONLY, true);
					if (ricercaGenerica)
						appEvent.setData("ricercaGenerica", new Boolean(true));
					else appEvent.setData("ricercaGenerica", new Boolean(false));
					Dispatcher.get().dispatch(appEvent);
					disableButtons();
				}
			}
		});
		/**
		 * si revisiona solo se la biblioteca è revisionabile
		 */
		revisiona.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				BiblioModel biblioModel = (BiblioModel) grid.getSelectionModel().getSelectedItem();
				if (biblioModel == null) {
					AbiMessageBox.messageAlertBox("Selezionare una biblioteca.", "Avviso");
				} else {
					if (UIWorkflow.checkIsRevisionabile(biblioModel)) {
						// chiedo conferma alla revisione
						final int idBiblio = biblioModel.getIdBiblio();
						AbiMessageBox.messageConfirmOperationAlertBox("Revisionare la biblioteca?", "Revisiona biblioteca", new Listener<MessageBoxEvent>(){
							@Override
							public void handleEvent(MessageBoxEvent be) {
								Button btn = be.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {
									LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
									
									wrapper.mask("Caricamento...", "x-mask-loading");
									AppEvent appEvent = new AppEvent(AppEvents.SelectBiblio);
									appEvent.setData(BiblioModel.IDBIBLIO, idBiblio);
									appEvent.setData(BiblioModel.REVISIONE, true);
									if (ricercaGenerica)
										appEvent.setData("ricercaGenerica", new Boolean(true));
									else appEvent.setData("ricercaGenerica", new Boolean(false));
									Dispatcher.get().dispatch(appEvent);
									disableButtons();
								}
							}
						});
					} else {
						// la biblioteca non è occupabile da questo utente, avviso...
						AbiMessageBox.messageAlertBox("La biblioteca non è revisionabile.", "Avviso");
					}
				}
			}
		});

		/*
		 * Va in modifica solo se è o definitiva o occupata dall'utente stesso
		 */
		occupa.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				BiblioModel biblioModel = (BiblioModel) grid.getSelectionModel().getSelectedItem();
				if (biblioModel == null) {
					AbiMessageBox.messageAlertBox("Selezionare una biblioteca.", "Avviso");
				} else {
					if (UIWorkflow.checkIsOccupabile(biblioModel)) {
						// chiedo conferma alla messa in modifica
						final int idBiblio = biblioModel.getIdBiblio();
						AbiMessageBox.messageConfirmOperationAlertBox("Mettere la biblioteca in modifica?", "Modifica biblioteca", new Listener<MessageBoxEvent>(){
							@Override
							public void handleEvent(MessageBoxEvent be) {
								Button btn = be.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {
									LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
									
									wrapper.mask("Caricamento...", "x-mask-loading");
									final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
									bibliotecheService.setOccupata(idBiblio, new AsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											AppEvent appEvent = new AppEvent(AppEvents.SelectBiblio);
											appEvent.setData(BiblioModel.IDBIBLIO, idBiblio);
											if (ricercaGenerica)
												appEvent.setData("ricercaGenerica", new Boolean(true));
											else appEvent.setData("ricercaGenerica", new Boolean(false));
											Dispatcher.get().dispatch(appEvent);
											disableButtons();
										}
										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel mettere in occupata la biblioteca", "Errore");
										}
									});
								}
							}
						});
					} else {
						// la biblioteca non è occupabile da questo utente, avviso...
						StringBuffer message = new StringBuffer();
						message.append("La biblioteca stata messa in stato ");
						message.append(biblioModel.getStatoCatalogazione());
						message.append(" dall'utente ");
						message.append(biblioModel.getUtenteUltimaModifica());
						message.append(" il ");
						DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
						message.append(fmt.format(biblioModel.getCatalogazioneDataModifica()));
						message.append(" alle ");
						fmt = DateTimeFormat.getFormat("HH:mm:ss");
						message.append(fmt.format(biblioModel.getCatalogazioneDataModifica()));
						AbiMessageBox.messageAlertBox(message.toString(), "Biblioteca non modificabile");
					}
				}
			}
		});
		gestori.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				BiblioModel biblioModel = (BiblioModel) grid.getSelectionModel().getSelectedItem();
				if (biblioModel == null) {
					AbiMessageBox.messageAlertBox("Selezionare una biblioteca", "Avviso");
				} else {
					listaUtentiGestoriWindow.setBiblioModel(biblioModel);
					listaUtentiGestoriWindow.show();
				}
			}
		});

		if (Roles.isUserInRole(Roles.REVISIONE)) 
			topToolBar.add(revisiona);

		topToolBar.add(visualizza);
		if (Roles.isUserInRole(Roles.CATALOGAZIONE))
			topToolBar.add(occupa);

		topToolBar.add(gestori);


		/* BOTTONE SELEZIONA TUTTO----FINE */

		indietro.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				checkFromWhereIncomingAndFireEvent();
			}
		});
		topToolBar.add(indietro);

		setTopComponent(topToolBar);
	}

	private void setBottomToolbar() {
		toolBar.bind(loader);
		setBottomComponent(toolBar);
	}

	public void setKeys(Boolean viaCodice, HashMap<String, Object> parametriRicerca) {

		config = new BasePagingLoadConfig();

		/** PARAMETRI RICERCA **/
		keys = parametriRicerca;
		config.set("keys", keys);

		/** TIPO RICERCA **/
		viacodice = viaCodice;
		config.set("viacodice", viacodice);

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
		if (!loading) {
			loading = true;
			loader.load(config);
		}
	}

	private void setService() {
		bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
		RpcProxy<PagingLoadResult<BiblioModel>> proxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {
			@Override
			public void load(Object loadConfig,	AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
				if (((PagingLoadConfig) loadConfig).get("keys") != null && ((PagingLoadConfig) loadConfig).get("viacodice") != null) {
					bibliotecheService.getBiblioteche((PagingLoadConfig) loadConfig, callback);
				}
				else {
					((PagingLoadConfig) loadConfig).set("keys",	keys);
					((PagingLoadConfig) loadConfig).set("viacodice", viacodice);
					bibliotecheService.getBiblioteche((PagingLoadConfig) loadConfig, callback);
				}
			}
		};

		// loader
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				DeferredCommand.addCommand(new Command() {
					public void execute() {
						loading = false;
					}
				});
			}
		});
		loader.setRemoteSort(true);

	}

	public Grid<BiblioModel> getGrid() {
		return grid;
	}

	public void setIdUsers(List<Integer> idUsers) {
		this.idUsers = idUsers;
	}

	public void setDirectList(Boolean b) {
		this.directList = b;
	}

	public Listener<MessageBoxEvent> getListenerForNoEntryFound() {
		Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
			@Override
			public void handleEvent(MessageBoxEvent be) {
				checkFromWhereIncomingAndFireEvent();

			}
		};
		return l;
	}

	public void checkIndietroButton() {
		if (directList) {
			indietro.hide();
		}
		else if ((this.viaRicerca || this.viaUtenti) && !directList) {
			indietro.show();
		}
	}
	public void setRicerca(boolean viaRicerca, boolean generica) {
		this.viaRicerca = viaRicerca;
		this.ricercaGenerica = generica;
	}

	public void setViaUtenti(boolean viaUtenti) {
		this.viaUtenti = viaUtenti;
	}
	/**
	 * Controlla qual'è la view di provenienza e lancia il relativo evento
	 */
	public void checkFromWhereIncomingAndFireEvent() {
		if (ricercaGenerica) {
			AppEvent event = null;
			if (viaUtenti) {
				event = new AppEvent(AppEvents.RicercaBiblioGenericaViaUtentiIndietro);
				viaUtenti = false;
			}
			else {
				event = new AppEvent(AppEvents.VisualizzaRicercaBiblioGenerica);
			}
			Dispatcher.forwardEvent(event);
		}
		else {
			AppEvent event = new AppEvent(AppEvents.VisualizzaRicercaBiblioViaCodice);
			Dispatcher.forwardEvent(event);
		}
	}

	public HashMap<String, Object> getKeys(){
		return this.keys;
	}

}