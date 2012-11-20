package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.CheckBoxSelectionModelCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
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
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
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
 * biblioteche assegnate ad un utente
 *
 */
public class ListaBiblioUtentePanel extends ContentPanel {
	 
	private UserModel userFromDB = null;
	private Grid<BiblioModel> grid = null;
	private PagingLoader<PagingLoadResult<ModelData>> loader = null;
	private BibliotecheServiceAsync biblioService = null;
	private PagingToolBar toolBar = new PagingToolBar(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
	private CheckBoxSelectionModelCustom<BiblioModel> sm = null;
	private ListStore<BiblioModel> store = null;
	private Integer idUtente;
	
	private Button selectAll = new Button("Seleziona tutto",Resources.ICONS.selectall());
	private Button rimuoviSelected = new Button("Rimuovi selezionate",Resources.ICONS.removeSelected());
	private Button rimuoviAll = new Button("Rimuovi TUTTE",Resources.ICONS.delete());
	private Button copiaBiblioDaUtenti = new Button("Copia da utente",Resources.ICONS.copyFromUser());
	private Button copiaBiblioDaCodice = new Button("Assegnazione via codice",Resources.ICONS.codeAssign());
	private Button indietro = new Button("Indietro",Resources.ICONS.arrow_left_red());
	
	/* Lista Biblioteche selezionate per la rimozione */
	private List<Integer> biblioSelected = new ArrayList<Integer>();
	
	protected AssegnaBiblioDaCodiceWindow assegnaCodiceWindow = null;
	protected AssegnaBiblioDaUtentiWindow assegnaUtentiWindow = null;
	private UtentiServiceAsync utentiServiceAsync = null;
	PagingLoadConfig config = null;
	
	public ListaBiblioUtentePanel() {
		super();
		assegnaCodiceWindow = new AssegnaBiblioDaCodiceWindow();
		assegnaUtentiWindow = new AssegnaBiblioDaUtentiWindow();
		utentiServiceAsync = Registry.get(Abi.UTENTI_SERVICE);
		setMonitorWindowResize(true);
		setHeading("Lista biblioteche assegnate");
		setHeight(300);
		setLayout(new FitLayout());
		
		setService();
		setGrid();
		setTopToolbar();
		setBottomToolbar();
	}
	
	public Grid<BiblioModel> getGrid() {
		return grid;
	}

	public void setUser(UserModel u) {
		this.userFromDB = u;
	}
	
	public void setGrid() {
		final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
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
		
		sm = new CheckBoxSelectionModelCustom<BiblioModel>();
		sm.setSelectionMode(SelectionMode.SIMPLE);
		configs.add(sm.getColumn());
		
		/* Colonna Codice */
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

		/* Colonna Denominazione */
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

		/* Colonna Indirizzo */
		ColumnConfig indirizzo = new ColumnConfig();
		indirizzo.setId("indirizzo");
		indirizzo.setHeader("Indirizzo");
		indirizzo.setWidth(110);
		
		configs.add(indirizzo);

		/* Colonna Comune */
		ColumnConfig comune = new ColumnConfig();
		comune.setId("comuneDenominazione");
		comune.setHeader("Comune");
		comune.setWidth(110);
		
		configs.add(comune);

		/* Colonna UtenteUltimaModifica */
		ColumnConfig utenteUltimaModifica = new ColumnConfig();
		utenteUltimaModifica.setId("utenteUltimaModifica");
		utenteUltimaModifica.setHeader("Ultima modifica");
		utenteUltimaModifica.setAlignment(HorizontalAlignment.LEFT);
		utenteUltimaModifica.setWidth(100);
		
		configs.add(utenteUltimaModifica);

		/* Colonna StatoCatalogazione */
		ColumnConfig statoCatalogazione = new ColumnConfig();
		statoCatalogazione.setId("statoCatalogazione");
		statoCatalogazione.setHeader("Stato di lavorazione");
		statoCatalogazione.setAlignment(HorizontalAlignment.LEFT);
		statoCatalogazione.setRenderer(statoColorCellRenderer);
		statoCatalogazione.setWidth(130);
		
		configs.add(statoCatalogazione);

		store = new ListStore<BiblioModel>(loader);
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				List<BiblioModel> tmplistbib = (List<BiblioModel>) store.getModels();
				if(tmplistbib.size()>0){
					rimuoviAll.enable();
					selectAll.enable();
					rimuoviSelected.disable();
				}else{
					rimuoviAll.disable();
					selectAll.disable();
					rimuoviSelected.disable();
				}
				if (biblioSelected.size() != 0) {					
					for (Integer entry : biblioSelected) {
						for (BiblioModel bib : tmplistbib) {
							if (bib.getIdBiblio() == entry.intValue())
								sm.select(bib, true);
							
						}				
					}
				}
				if (biblioSelected.size() == 0) {
					rimuoviSelected.disable();
				}else{
					rimuoviSelected.enable();
				}
				Utils.changeSelectAllText(sm, store, selectAll);
			}
		});
		
		grid = new Grid<BiblioModel>(store, new ColumnModel(configs));
		grid.setStripeRows(true);
		grid.setColumnLines(true); 
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.setWidth("100%");
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<BiblioModel>>() {
		
			public void handleEvent(GridEvent<BiblioModel> be) {
				if (config == null) {/* config è null; stiamo quindi inizializzando il pannello 
					per la prima volta */
					config = new BasePagingLoadConfig();
					config.setOffset(0);
					config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
					config.set("id_utente",	new Integer(userFromDB.getIdUser()));
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
					
					loader.load(config);
				}
				else {/* config è già stato inizializzato; la griglia tornerà all'ultimo stato 
				visualizzato */
					loader.load(config);
					
				}
				
			}
		});
		
		grid.setLoadMask(true);
				
		add(grid);
		
		addListener(Events.Activate,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				int i = 0;
			}
		});
	}
	
	public void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		
		/*BOTTONE SELEZIONA TUTTO*/
		
		selectAll.setToolTip(Utils.getTooltip("Seleziona tutto", "Seleziona/deseleziona tutti gli elementi della lista ", "left"));

		selectAll.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (sm.getSelectedItems().size() < store.getCount()){
					sm.selectAll();
					selectAll.setText("Deseleziona tutto");
				}else if (sm.getSelectedItems().size() == store.getCount()){
					sm.deselectAll();
					selectAll.setText("Seleziona tutto");
				}
				sm.fireEvent(Events.HeaderClick);
				grid.fireEvent(Events.OnClick);
			}
		});
		
		/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
		 * selezionare/deselezionare tutti gli utenti. */
		grid.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectAllText(sm,store,selectAll);
				if (sm.getSelectedItems().size() == store.getModels().size()) {				
					/* Sono stati selezionati tutti gli utenti */
					List<BiblioModel> ulist = (List<BiblioModel>) sm.getSelectedItems();
					
					for (BiblioModel entry : ulist) {
						if (!biblioSelected.contains(entry.getIdBiblio()))
							biblioSelected.add(new Integer(entry.getIdBiblio()));
					}
				}
				else if (sm.getSelectedItems().size() == 0) {
					/* Sono stati deselezionati tutti gli utenti */
//					biblioSelected.clear();
					Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
				}
			}
		});
		
		
		sm.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				
				Utils.changeSelectAllText(sm,store,selectAll);
	
			}
		});
		
		/*BOTTONE SELEZIONA TUTTO----FINE*/
		rimuoviSelected.setToolTip(Utils.getTooltip("Rimuovi Selezionate", "Rimuove le biblioteche selezionate dalla lista di quelle assegnate all'utente in modifica", "left"));

		rimuoviSelected.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				/* Gestione pulsante 'Rimuovi  selezionate' */
				final MessageBox confirm = new MessageBox();
				confirm.setIcon(MessageBox.QUESTION);
				confirm.setMessage("Rimuovere selezionate?");
				confirm.setButtons(MessageBox.YESNO);
				confirm.addCallback(new Listener<MessageBoxEvent>() {							
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							/* Clic sul pulsante conferma rimozione */
							utentiServiceAsync.rimuoviBiblio(userFromDB.getIdUser(), false, biblioSelected, new AsyncCallback<Void>() {								
								@Override
								public void onSuccess(Void result) {						
									final MessageBox box = new MessageBox();
					                box.setIcon(MessageBox.INFO);
					                box.setMessage("Biblioteche rimosse.");
					                box.setButtons(MessageBox.OK);	                
					                box.addCallback(new Listener<MessageBoxEvent>() {										
										@Override
										public void handleEvent(MessageBoxEvent be) {
											final PagingLoadConfig config = new BasePagingLoadConfig();
											config.setOffset(0);
											config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
											config.set("id_utente", userFromDB.getIdUser());
											
											grid.getStore().getLoader().load(config);
											
											/**/
											biblioSelected.clear();
//											Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
											sm.deselectAll();
											rimuoviSelected.disable();
											Utils.changeSelectAllText(sm, store, selectAll);
											/**/
										}
									});
					                
									box.show();
								}
								
								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
								}
							});
						}
													
					}
				});
				confirm.show();						
					
			}
			
		});		
		
		/* Listener per la griglia: inserisce le biblioteche selezionate in lista ed
		 * abilita il pulsante 'Rimuovi le biblioteche selezionate' */
		grid.addListener(Events.RowClick, new Listener<GridEvent<BiblioModel>>() {

			public void handleEvent(GridEvent<BiblioModel> be) {
				if (sm.isSelected(be.getModel())) {
					/* La checkbox è abilitata per la riga che è stata cliccata */
					if (!biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
						biblioSelected.add(new Integer(be.getModel().getIdBiblio()));
					
					rimuoviSelected.enable();
				}
				else {
					/* La checkbox è disabilitata per la riga che è stata cliccata */
					if (biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
						biblioSelected.remove((Integer)be.getModel().getIdBiblio());
					
					if (biblioSelected.size() == 0) {
						/* Sono state disabilitate tutte le checkbox; 
						 * la lista è quindi vuota e bisogna disabilitare il pulsante */
						rimuoviSelected.disable();
					}
				}
				
				
			}
		});
		
		rimuoviSelected.disable();
		
		/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
		 * selezionare/deselezionare tutte le biblioteche assegnate all'utente. */
		grid.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectAllText(sm, store, selectAll);
				if (sm.getSelectedItems().size() == store.getModels().size()) {				
					/* Sono state selezionate tutte le biblioteche */
					List<BiblioModel> biblist = (List<BiblioModel>) sm.getSelectedItems();
					
					for (BiblioModel entry : biblist) {
						if (!biblioSelected.contains(entry.getIdBiblio()))
							biblioSelected.add(new Integer(entry.getIdBiblio()));
					}
					rimuoviSelected.enable();
					
				}
				else if (sm.getSelectedItems().size() == 0) {
					/* Sono state deselezionate tutte le biblioteche */
//					biblioSelected.clear();
					Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
					rimuoviSelected.disable();
				}
			
				
			}
		});
		rimuoviAll.setToolTip(Utils.getTooltip("Rimuovi TUTTE", "Rimuove TUTTE le biblioteche dalla lista di quelle assegnate all'utente in modifica", "left"));

		
		rimuoviAll.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				/* Gestione pulsante 'Rimuovi TUTTE le biblioteche' */
				final MessageBox confirm = new MessageBox();
				confirm.setIcon(MessageBox.QUESTION);
				confirm.setMessage("Rimuovere TUTTE le biblioteche?");
				confirm.setButtons(MessageBox.YESNO);
				confirm.addCallback(new Listener<MessageBoxEvent>() {
					
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							/* Clic sul pulsante conferma rimozione */
							utentiServiceAsync.rimuoviBiblio(userFromDB.getIdUser(), true, null, new AsyncCallback<Void>() {							
								@Override
								public void onSuccess(Void result) {	
								/**/
//									biblioSelected.clear();
									Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
									sm.deselectAll();
									rimuoviSelected.disable();
									Utils.changeSelectAllText(sm, store, selectAll);
									/**/
									final MessageBox box = new MessageBox();
					                box.setIcon(MessageBox.INFO);
					                box.setMessage("Biblioteche rimosse.");
					                box.setButtons(MessageBox.OK);	                
					                box.addCallback(new Listener<MessageBoxEvent>() {
										
										@Override
										public void handleEvent(MessageBoxEvent be) {
											final PagingLoadConfig config = new BasePagingLoadConfig();
											config.setOffset(0);
											config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
											config.set("id_utente", userFromDB.getIdUser());
											grid.getStore().getLoader().load(config);
											
										}
									});
					                
									box.show();								
									
								}
								
								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login	
								}
							});
						}
												
					}
				});
				confirm.show();
					
			}
		});
		
		copiaBiblioDaUtenti.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				assegnaUtentiWindow.setIdUtente(userFromDB.getIdUser());
				assegnaUtentiWindow.setConfig(grid);
				assegnaUtentiWindow.show();
				
			}
		});
		copiaBiblioDaUtenti.setToolTip(Utils.getTooltip("Copia biblioteche da altro Utente", "Copia tutte le biblioteche assegnate ad un altro utente e le assegna all'utente in modifica", "left"));
	
		copiaBiblioDaCodice.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				assegnaCodiceWindow.setIdUtente(userFromDB.getIdUser());
				assegnaCodiceWindow.setConfig(grid, biblioSelected);
				assegnaCodiceWindow.show();
				
			}
		});
		copiaBiblioDaCodice.setToolTip(Utils.getTooltip("Assegnazione biblioteca via Codice", "Assegna la biblioteca all'utente tramite il codice", "left"));
		
		indietro.setToolTip(Utils.getTooltip("Indietro", "Torna alla schermata precedente", "left"));

		
		indietro.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				Dispatcher.forwardEvent(AppEvents.IndietroDaModificaModificaUtente);
			}
		});
		topToolBar.add(selectAll);
		topToolBar.add(rimuoviSelected);
		topToolBar.add(rimuoviAll);
		topToolBar.add(copiaBiblioDaUtenti);
		topToolBar.add(copiaBiblioDaCodice);
		topToolBar.add(indietro);

		setTopComponent(topToolBar);
		
	}
	
	public void setBottomToolbar() {

		toolBar.bind(loader);
		
		setBottomComponent(toolBar);
	}
	
	public void setService() {
		
		biblioService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);

		RpcProxy<PagingLoadResult<BiblioModel>> proxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {
			@Override
			public void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<BiblioModel>> callback) {

				/* Servizio bibliotecheUtente */
				biblioService.getBibliotecheUtente((PagingLoadConfig) loadConfig, callback);
								
			}
			
		};
		
		/* Loader */
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);
		loader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				be.<ModelData> getConfig(). set("id_utente", new Integer(userFromDB.getIdUser()));
			}
		});
	}
	/**
	 * @param idUtente the idUtente to set
	 */
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}
	
	public PagingLoader<PagingLoadResult<ModelData>> getLoader(){
		return loader;
	}
	

}
