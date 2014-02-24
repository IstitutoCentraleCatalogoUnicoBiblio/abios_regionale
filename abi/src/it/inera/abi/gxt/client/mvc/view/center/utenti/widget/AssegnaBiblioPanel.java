package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.CheckBoxSelectionModelCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
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
import com.extjs.gxt.ui.client.mvc.AppEvent;
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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette di selezionare una o più biblioteche da assegnare 
 * ad uno o più utenti selezionati in precedenza
 *
 */
public class AssegnaBiblioPanel extends ContentPanel {

	private Grid<BiblioModel> grid = null;
	private PagingLoader<PagingLoadResult<ModelData>> loader = null;
	private BibliotecheServiceAsync biblioService = null;
	private PagingToolBar toolBar = new PagingToolBar(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
	private CheckBoxSelectionModelCustom<BiblioModel> sm = null;
	private ListStore<BiblioModel> store = null;
	
	/* Lista Biblioteche selezionate per l'assegnazione */
	private List<Integer> biblioSelected = new ArrayList<Integer>();
	
	/* Lista Utenti selezionati per l'assegnazione */
	private List<Integer> usersSelected = new ArrayList<Integer>();
	
	private UtentiServiceAsync utentiServiceAsync = null;
	protected Button assegna;
	protected Button selectAll = new Button("Seleziona tutto");
	
	private boolean loading = false;
	
	protected PagingLoadConfig config = null;
	protected HashMap<String, Object> keys = new HashMap<String, Object>();
	
	public AssegnaBiblioPanel() {
		super();
		utentiServiceAsync = Registry.get(Abi.UTENTI_SERVICE);
		setMonitorWindowResize(true);
		setHeading("Lista Biblioteche da assegnare");
		setLayout(new FitLayout());
		
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
				+(String)model.get("denominazione")  
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
		statoCatalogazione.setWidth(130);
		statoCatalogazione.setRenderer(statoColorCellRenderer);
		
		configs.add(statoCatalogazione);

		store = new ListStore<BiblioModel>(loader);
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				DeferredCommand.addCommand(new Command() {
					public void execute() {
						if (biblioSelected.size() != 0) {	
							assegna.enable();
							List<BiblioModel> tmplistbib = (List<BiblioModel>) store.getModels();
							for (Integer entry : biblioSelected) {
								for (BiblioModel bib : tmplistbib) {
									if (bib.getIdBiblio() == entry.intValue())
										sm.select(bib, true);
								}				
							}
						}else{
							assegna.disable();	
						}
						Utils.changeSelectAllText(sm, store, selectAll);
					}
				});
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

		grid.addListener(BaseLoader.BeforeLoad, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				loading = true;
			}
		});
		
		grid.setLoadMask(true);
				
		add(grid);
	}
	
	public void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		assegna = new Button("Assegna le biblioteche selezionate agli utenti", Resources.ICONS.accept());
		assegna.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							/* Richiama il servizio che assegna le biblioteche selezionate agli utenti selezionati */
							utentiServiceAsync.assegnaBibliosAdUtentis(usersSelected, biblioSelected, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									/* Window modale */
									selectAll.setText("Seleziona tutto");
									final MessageBox box = new MessageBox();
									box.setIcon(MessageBox.OK);
									box.setMessage("Alla lista utenti specificata è stata assegnata la lista biblioteche.");
									box.setButtons(MessageBox.OK);
									box.addCallback(new Listener<MessageBoxEvent>() {
										
										@Override
										public void handleEvent(MessageBoxEvent be) {
											if (be.getButtonClicked() != null && Dialog.OK.equals(be.getButtonClicked().getItemId())) {
												AppEvent event = new AppEvent(AppEvents.AssegnaListeAUtenti);
												Dispatcher.forwardEvent(event);
											}
											
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
				};
				final MessageBox question = new MessageBox();
				question.setIcon(MessageBox.QUESTION);
				question.setMessage("Assegnare le biblioteche selezionate alla lista utenti?");
				question.setButtons(MessageBox.YESNO);
				question.addCallback(l/*new Listener<MessageBoxEvent>() {
					
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							selectAll.setText("Seleziona tutto");
							final MessageBox box = new MessageBox();
							box.setIcon(MessageBox.OK);
							box.setMessage("Alla lista utenti specificata è stata assegnata la lista biblioteche.");
							box.setButtons(MessageBox.OK);
							box.addCallback(new Listener<MessageBoxEvent>() {
								
								@Override
								public void handleEvent(MessageBoxEvent be) {
									if (be.getButtonClicked() != null && Dialog.OK.equals(be.getButtonClicked().getItemId())) {
										AppEvent event = new AppEvent(AppEvents.AssegnaListeAUtenti);
										Dispatcher.forwardEvent(event);
									}
									
								}
							});
							box.show();	
						}
						
					}
				}*/);
				question.show();
							
				
				
			}
			
		});
		
		assegna.disable();
		
		/* Listener per la griglia: inserisce le biblioteche selezionate in lista ed
		 * abilita il pulsante 'Assegna le biblioteche selezionate agli utenti' */
		grid.addListener(Events.RowClick, new Listener<GridEvent<BiblioModel>>() {

			public void handleEvent(GridEvent<BiblioModel> be) {
				if (sm.isSelected(be.getModel())) {
					/* La checkbox è abilitata per la riga che è stata cliccata */
					if (!biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
						biblioSelected.add(new Integer(be.getModel().getIdBiblio()));
					
					assegna.enable();
				}
				else {
					/* La checkbox è disabilitata per la riga che è stata cliccata */
					if (biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
						biblioSelected.remove((Integer)be.getModel().getIdBiblio());
					
					if (biblioSelected.size() == 0) {
						/* Sono state disabilitate tutte le checkbox; 
						 * la lista è quindi vuota e bisogna disabilitare il pulsante */
						assegna.disable();
					}
				}
				
				
			}
		});
		
		/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
		 * selezionare/deselezionare tutti le biblioteche. */
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
					assegna.enable();
					
				}
				else if (sm.getSelectedItems().size() == 0) {
					/* Sono state deselezionate tutte le biblioteche */
//					biblioSelected.clear();
					Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
					assegna.disable();
				}
			}
		});
		

		final Button tornaIndietro = new Button("Torna a Lista utenti", Resources.ICONS.group());
		tornaIndietro.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				selectAll.setText("Seleziona tutto");
				AppEvent event = new AppEvent(AppEvents.VisualizzaListaUtentiInModifica);
				Dispatcher.forwardEvent(event);
				
			}
		});
		
		selectAll.setIcon(Resources.ICONS.selectall());
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
		
		final Button tornaAFormRicerca = new Button("Torna a Ricerca", Resources.ICONS.find());
		tornaAFormRicerca.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				selectAll.setText("Seleziona tutto");
				AppEvent event = new AppEvent(AppEvents.RicercaListaBiblioInAssegnaIndietro);
				event.setData("idUsers", usersSelected);
				Dispatcher.forwardEvent(event);
				
			}
		});
		
		
		sm.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				
//				if (sm.getSelectedItems().size() == store.getCount()){
//					selectAll.setText("Deseleziona tutto");
//				}else {
//					selectAll.setText("Seleziona tutto");
//				} 
				Utils.changeSelectAllText(sm, store, selectAll);
			}
		});
		
		topToolBar.add(selectAll);
		topToolBar.add(assegna);
		topToolBar.add(tornaIndietro);
		topToolBar.add(tornaAFormRicerca);
		
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
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
				/* Servizio getBiblioteche */
				if (((PagingLoadConfig) loadConfig).get("keys") == null) {
					((PagingLoadConfig) loadConfig).set("keys",	keys);
				}
				
				biblioService.getBiblioteche((PagingLoadConfig) loadConfig, callback);
								
			}
			
		};
		
		/* Loader */
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
	
	public void setUsersList(List<Integer> idUsers) {
		this.usersSelected = idUsers;
	}
	
	public void clearBib() {
		biblioSelected.clear();
		assegna.disable();
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

		if (!loading) {
			loading = true;
			loader.load(config);
		}
	}
	
}

