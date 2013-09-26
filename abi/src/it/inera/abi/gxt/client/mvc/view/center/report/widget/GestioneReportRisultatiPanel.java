package it.inera.abi.gxt.client.mvc.view.center.report.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.CheckBoxSelectionModelCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.ReportServiceAsync;

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
 * Classe per la visualizzazione della lista di risultati
 * delle ricerche nel formato di scambio
 *
 */
public class GestioneReportRisultatiPanel extends ContentPanel {
	
	private PagingLoader<PagingLoadResult<ModelData>> loader = null;
	private BibliotecheServiceAsync bibliotecheService = null;
	private Grid<BiblioModel> grid = null;
	private PagingToolBar toolBar = new PagingToolBar(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
	private int tipoRicerca = 0;
	
	private List<Integer> biblioSelected = new ArrayList<Integer>();
	private CheckBoxSelectionModelCustom<BiblioModel> sm = null;
	private ListStore<BiblioModel> store = null;
	
	private HashMap<String, Object> params = new HashMap<String, Object>();
	
	private HashMap<String, Object> keys = new HashMap<String, Object>();
	private HashMap<String, Object> labels = new HashMap<String, Object>();
	
	PagingLoadConfig config = null;
	
	private boolean onBottonClick = false;
	private Button selectAll = new Button("Seleziona tutto", Resources.ICONS.selectall());
	private Button reportSelected = new Button("Report delle biblioteche selezionate", Resources.ICONS.table_lightning());
	private Button exportSelected = new Button("Export delle biblioteche selezionate", Resources.ICONS.table_lightning());
	
	ReportServiceAsync reportService = Registry.get(Abi.REPORTSERVICE);
	
	public GestioneReportRisultatiPanel() {
		
		super();
		setLayout(new FitLayout());
		
		setService();
		setGrid();
		setBottomToolbar();
		
	}
	
	public void setGrid() {
		
		final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		sm = new CheckBoxSelectionModelCustom<BiblioModel>();
		sm.setSelectionMode(SelectionMode.SIMPLE);
		configs.add(sm.getColumn());

		/* Colonna CODICE */
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

		/* Colonna DENOMINAZIONE */
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
		
		/* Colonna INDIRIZZO */
		ColumnConfig indirizzo = new ColumnConfig();
		indirizzo.setId("indirizzo");
		indirizzo.setHeader("Indirizzo");
		indirizzo.setWidth(110);
		
		configs.add(indirizzo);		

		/* Colonna DENOMINAZIONE COMUNE */
		ColumnConfig comune = new ColumnConfig();
		comune.setId("comuneDenominazione");
		comune.setHeader("Comune");		
		comune.setWidth(110);
		configs.add(comune);

		/* Colonna UTENTE ULTIMA MODIFICA */
		ColumnConfig utenteUltimaModifica = new ColumnConfig();
		utenteUltimaModifica.setId("utenteUltimaModifica");
		utenteUltimaModifica.setHeader("Ultima modifica");
		utenteUltimaModifica.setAlignment(HorizontalAlignment.LEFT);
		utenteUltimaModifica.setWidth(100);
		
		configs.add(utenteUltimaModifica);
		
		store = new ListStore<BiblioModel>(loader);
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				DeferredCommand.addCommand(new Command() {

					@Override
					public void execute() {
						Utils.changeSelectAllText(sm, store, selectAll);
						//Recupero la lista dedelle biblioteche dallo store
						List<BiblioModel> tmpBiblioteche =	(List<BiblioModel>) store.getModels();
						if (tmpBiblioteche.size() <= 0) {
							showNoEntryFoundMessage();
						}
					}
				});
				if (biblioSelected.size() != 0) {					
					List<BiblioModel> tmplistbib = (List<BiblioModel>) store.getModels();
					for (Integer entry : biblioSelected) {
						for (BiblioModel bib : tmplistbib) {
							if (bib.getIdBiblio() == entry.intValue())
								sm.select(bib, true);
						}				
					}
				}
			}
		});
		
		
		grid = new Grid<BiblioModel>(store, new ColumnModel(configs));
		grid.setStripeRows(true);
		grid.setColumnLines(true); 
		grid.setWidth("100%");
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);
		
		grid.setLoadMask(true);
				
		add(grid);
	}
	
	
	public void setTopToolbar() {
		
			ToolBar topToolBar = new ToolBar();

			/*BOTTONE SELEZIONA TUTTO*/
		
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
			
			topToolBar.add(selectAll);
			
			sm.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					
					if (sm.getSelectedItems().size() == store.getCount()){
						selectAll.setText("Deseleziona tutto");
					}else {
						selectAll.setText("Seleziona tutto");
					} 
				}
			});
			/*BOTTONE SELEZIONA TUTTO----FINE*/
			
			if (tipoRicerca == 1) {
				reportSelected.addSelectionListener(new SelectionListener<ButtonEvent>() {
	
					@Override
					public void componentSelected(ButtonEvent ce) {
						params.clear();
						
						reportService.retrieveBiblioReport(biblioSelected, labels, new AsyncCallback<Void>() {
							
							@Override
							public void onSuccess(Void result) {
								AppEvent event = new AppEvent(AppEvents.VisualizzaStampaReport);
								params.put("idBibs", biblioSelected);
								event.setData("parameters", params);
								event.setData("filtriRisultatiLabel", labels);
								Dispatcher.forwardEvent(event);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
									AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nella generazione " +
											"del file pdf per il report.", "Generazione Pdf Report");
								}
								
							}
						});
						
//						AppEvent event = new AppEvent(AppEvents.VisualizzaStampaReport);
//						params.put("idBibs", biblioSelected);
//						event.setData("parameters", params);
//						event.setData("filtriRisultatiLabel", labels);
//						Dispatcher.forwardEvent(event);
					}
				});
				
				reportSelected.disable();
				
				/* Listener per la griglia: inserisce le biblioteche selezionate in lista ed
				 * abilita il pulsante 'report delle biblioteche selezionate' */
				grid.addListener(Events.RowClick, new Listener<GridEvent<BiblioModel>>() {
	
					public void handleEvent(GridEvent<BiblioModel> be) {
						if (sm.isSelected(be.getModel())) {
							/* La checkbox è abilitata per la riga che è stata cliccata */
							if (!biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
								biblioSelected.add(new Integer(be.getModel().getIdBiblio()));
							
							reportSelected.enable();
						}
						else {
							/* La checkbox è disabilitata per la riga che è stata cliccata */
							if (biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
								biblioSelected.remove((Integer)be.getModel().getIdBiblio());
							
							if (biblioSelected.size() == 0) {
								/* Sono state disabilitate tutte le checkbox; 
								 * la lista è quindi vuota e bisogna disabilitare il pulsante */
								reportSelected.disable();
							}
						}						
						
					}
				});		
				
				/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
				 * selezionare/deselezionare tutte le biblioteche. */
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
							reportSelected.enable();
							
						}
						else if (sm.getSelectedItems().size() == 0) {
							/* Sono state deselezionate tutte le biblioteche */
							Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
							reportSelected.disable();
						}
					}
				});
				
				
				final Button reportAll = new Button("Report di tutte le biblioteche", Resources.ICONS.table_multiple());
				reportAll.addSelectionListener(new SelectionListener<ButtonEvent>() {
					
					@Override
					public void componentSelected(ButtonEvent ce) {
						onBottonClick = true;
						/* Gestione pulsante 'Report di TUTTE le biblioteche' */
						AbiMessageBox.messageConfirmOperationAlertBox("Sono state selezionate  tutte le biblioteche, continuare?", "Avviso", new Listener<MessageBoxEvent>() {
							@Override
							public void handleEvent(MessageBoxEvent be) {
								Button btn = be.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {
									config = new BasePagingLoadConfig();
									
									config.setOffset(0);
									config.setLimit(-1);
									
//									store = new ListStore<BiblioModel>(loader);
									loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
										@Override
										public void handleEvent(BaseEvent be) {
											if (!onBottonClick) return;
											/* Passaggio di tutte le biblioteche trovate */
											List<BiblioModel> tmplist = (List<BiblioModel>) store.getModels();
											final List<Integer> idbiblist = new ArrayList<Integer>();
											
											params.clear();
																		
											for (BiblioModel entry : tmplist) {
												idbiblist.add(new Integer(entry.getIdBiblio()));
												
											}
											
											reportService.retrieveBiblioReport(idbiblist, labels, new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													AppEvent event = new AppEvent(AppEvents.VisualizzaStampaReport);
													params.put("tipoDiStampa", (String) keys.get("tipoDiStampa"));
													params.put("idBibs", idbiblist);
													event.setData("parameters", params);
													event.setData("filtriRisultatiLabel", labels);
													onBottonClick = false;
													Dispatcher.forwardEvent(event);
													
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nella generazione " +
																"del file excel per il report.", "Generazione Excel Report");
													}

												}
											});
																						
//											AppEvent event = new AppEvent(AppEvents.VisualizzaStampaReport);
//											params.put("tipoDiStampa", (String) keys.get("tipoDiStampa"));
//											params.put("idBibs", idbiblist);
//											event.setData("parameters", params);
//											event.setData("filtriRisultatiLabel", labels);
//											onBottonClick = false;
//											Dispatcher.forwardEvent(event);
																						
										}
										
									});
									loader.load(config);
								}
							}
						});
					}
					
				});
				
				topToolBar.add(reportSelected);
				topToolBar.add(reportAll);
				
			}
			else if (tipoRicerca == 2) {
				exportSelected.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						params.clear();
												
						AppEvent event = new AppEvent(AppEvents.VisualizzaStampaFormatoDiScambio);
						params.put("idBibs", biblioSelected);
						event.setData("parameters", params);
						event.setData("filtriRisultatiLabel", labels);
						Dispatcher.forwardEvent(event);
						
						
					}
					
				});
				
				exportSelected.disable();
				
				/* Listener per la griglia: inserisce le biblioteche selezionate in lista ed
				 * abilita il pulsante 'report delle biblioteche selezionate' */
				grid.addListener(Events.RowClick, new Listener<GridEvent<BiblioModel>>() {
	
					public void handleEvent(GridEvent<BiblioModel> be) {
						if (sm.isSelected(be.getModel())) {
							/* La checkbox è abilitata per la riga che è stata cliccata */
							if (!biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
								biblioSelected.add(new Integer(be.getModel().getIdBiblio()));
							
							exportSelected.enable();
						}
						else {
							/* La checkbox è disabilitata per la riga che è stata cliccata */
							if (biblioSelected.contains((Integer)be.getModel().getIdBiblio()))
								biblioSelected.remove((Integer)be.getModel().getIdBiblio());
							
							if (biblioSelected.size() == 0) {
								/* Sono state disabilitate tutte le checkbox; 
								 * la lista è quindi vuota e bisogna disabilitare il pulsante */
								exportSelected.disable();
							}
						}
						
						
					}
				});	
				
				/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
				 * selezionare/deselezionare tutte le biblioteche. */
				grid.addListener(Events.OnClick, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						Utils.changeSelectAllText(sm,store,selectAll);
						if (sm.getSelectedItems().size() == store.getModels().size()) {				
							/* Sono state selezionate tutte le biblioteche */
							List<BiblioModel> biblist = (List<BiblioModel>) sm.getSelectedItems();
							
							for (BiblioModel entry : biblist) {
								if (!biblioSelected.contains(entry.getIdBiblio()))
									biblioSelected.add(new Integer(entry.getIdBiblio()));
							}
							exportSelected.enable();
							
						}
						else if (sm.getSelectedItems().size() == 0) {
							/* Sono state deselezionate tutte le biblioteche */
							Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
							exportSelected.disable();
						}
					}
				
				
				});
				
				
				final Button exportAll = new Button("Export di tutte le biblioteche", Resources.ICONS.table_multiple());
				exportAll.addSelectionListener(new SelectionListener<ButtonEvent>() {
					
					@Override
					public void componentSelected(ButtonEvent ce) {
						onBottonClick = true;
						/* Gestione pulsante 'Export di TUTTE le biblioteche' */
						AbiMessageBox.messageConfirmOperationAlertBox("Sono state selezionate tutte le biblioteche, continuare?", "Avviso", new Listener<MessageBoxEvent>() {
							
							@Override
							public void handleEvent(MessageBoxEvent be) {
								Button btn = be.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {
									config = new BasePagingLoadConfig();
									
									config.setOffset(0);
									config.setLimit(-1);
									
//									store = new ListStore<BiblioModel>(loader);
									loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
										@Override
										public void handleEvent(BaseEvent be) {
											if (!onBottonClick) return;
											/* Passaggio di tutte le biblioteche trovate */
											List<BiblioModel> tmplist = (List<BiblioModel>) store.getModels();
											List<Integer> idbiblist = new ArrayList<Integer>();
											
											params.clear();
																		
											for (BiblioModel entry : tmplist) {
												idbiblist.add(new Integer(entry.getIdBiblio()));
												
											}								
											
											AppEvent event = new AppEvent(AppEvents.VisualizzaStampaFormatoDiScambio);
											params.put("idBibs", idbiblist);
											event.setData("parameters", params);
											event.setData("filtriRisultatiLabel", labels);
											onBottonClick = false;
											Dispatcher.forwardEvent(event);								
											
										}
										
									});
									
									loader.load(config);
								}
								
							}
						});
					}
					
				});
				
				topToolBar.add(exportSelected);
				topToolBar.add(exportAll);
			}
			
			
			final Button tornaIndietro = new Button("Torna alla ricerca", Resources.ICONS.find());
			tornaIndietro.addSelectionListener(new SelectionListener<ButtonEvent>() {
				
				@Override
				public void componentSelected(ButtonEvent ce) {
					checkFromWhereIncomingAndFireEvent();
				}
				
			});
					
			topToolBar.add(tornaIndietro);

			setTopComponent(topToolBar);
			
	}
	
	

	private void setBottomToolbar() {
		toolBar.bind(loader);		
		setBottomComponent(toolBar);
	}

	
	private void setService() {
		bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);

		RpcProxy<PagingLoadResult<BiblioModel>> proxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
				if (((PagingLoadConfig) loadConfig).get("keys") == null) {
					((PagingLoadConfig) loadConfig).set("keys",	keys);
				}

				bibliotecheService.getBibliotecheReport((PagingLoadConfig) loadConfig, callback);
			}
		};
		
		/* Loader */
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);
		
		
	}

	public Grid<BiblioModel> getGrid() {
		return grid;

	}
	
	public void setTipoRicerca(int tipo) {
		tipoRicerca = tipo;
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
	
	/**
	 * Controlla qual'è la view di provenienza e lancia il relativo evento
	 */
	public void checkFromWhereIncomingAndFireEvent() {
		if (tipoRicerca == 1) {
			AppEvent event = new AppEvent(AppEvents.VisualizzaFormReport);
			Dispatcher.forwardEvent(event);
		}
		else if (tipoRicerca == 2) {
			AppEvent event = new AppEvent(AppEvents.VisualizzaFormFormatoDiScambio);
			Dispatcher.forwardEvent(event);
		}
	}
	
	public void showNoEntryFoundMessage(){
		MessageBox.info("ATTENZIONE", "Nessun risultato trovato.", getListenerForNoEntryFound()).show();
	}
	
	public void setKeys(HashMap<String, Object> chiaviRicerca, HashMap<String, Object> chiaviRicercaLabels) {
		config = new BasePagingLoadConfig();
		
		/** PARAMETRI RICERCA **/
		this.keys = chiaviRicerca;
		
		/** PARAMETRI RICERCA LABEL **/
		this.labels = chiaviRicercaLabels;
		
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
		Utils.removeIdsOfBibliosInAuctualListOnDeselectAll(biblioSelected, store);
		loader.load(config);
		
		reportSelected.disable();
		exportSelected.disable();
	}
	
}
