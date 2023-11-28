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

package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.CheckBoxSelectionModelCustom;
import it.inera.abi.gxt.client.resources.Resources;
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
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
 * Classe che permette di selezionare uno o più utenti ai quali assegnare
 * una o più biblioteche
 *
 */
public class AssegnaUtentiPanel extends ContentPanel {

	private Grid<UserModel> grid = null;
	private PagingLoader<PagingLoadResult<ModelData>> loader = null;
	private UtentiServiceAsync utentiService = null;
	private CheckBoxSelectionModelCustom<UserModel> sm = null;
	private ListStore<UserModel> store = null;
	private PagingToolBar toolBar = new PagingToolBar(CostantiGestioneUtenti.UTENTI_GRID_LIMIT);
	
	private List<Integer> userSelectedList = new ArrayList<Integer>();
	private Button toListaBiblioteche = null;
	protected Button selectAll = new Button("Seleziona tutto",Resources.ICONS.selectall());
	protected Button deselectAll = new Button("Deseleziona tutto",Resources.ICONS.selectall());

	public AssegnaUtentiPanel() {
		super();
		setMonitorWindowResize(true);
		setHeading("Lista utenti");
		setIcon(Resources.ICONS.group());
		setLayout(new FitLayout());		
	}

	public void setGrid() {

		final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();			

		sm = new CheckBoxSelectionModelCustom<UserModel>();
		sm.setSelectionMode(SelectionMode.SIMPLE);
		configs.add(sm.getColumn());
		
		/* Colonna LOGIN */
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
		
		/* Colonna NOME */
		ColumnConfig columnNome = new ColumnConfig();
		columnNome.setId("nome");
		columnNome.setHeader("Nome");
		columnNome.setWidth(130);
		configs.add(columnNome);
		
		/* Colonna COGNOME */
		ColumnConfig columnCognome = new ColumnConfig();
		columnCognome.setId("cognome");
		columnCognome.setHeader("Cognome");
		columnCognome.setWidth(130);
		configs.add(columnCognome);
		
		/* Colonna INCARICO */
		ColumnConfig columnIncarico = new ColumnConfig();
		columnIncarico.setId("incarico");
		columnIncarico.setHeader("Incarico");
		columnIncarico.setAlignment(HorizontalAlignment.LEFT);
		columnIncarico.setWidth(320);
		columnIncarico.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return "<b><span style=\"color: #385F95; text-decoration: none;width:300;text-align:justify;\">"  
				+ Utils.insertBRtag((String)model.get("incarico") ,75) 
				+ "</span></b>";  
			}  

		});  
		
		configs.add(columnIncarico);

		GridCellRenderer<UserModel> statoAccountColorCellRenderer = new GridCellRenderer<UserModel>() {  
			public String render(UserModel model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<UserModel> store, Grid<UserModel> grid) {  
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

		
		store = new ListStore<UserModel>(loader);
		loader.addListener(BaseLoader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (userSelectedList.size() != 0) {					
					List<UserModel> tmplistuser = (List<UserModel>) store.getModels();
					for (Integer entry : userSelectedList) {
						for (UserModel user : tmplistuser) {
							if (user.getIdUser() == entry.intValue())
								sm.select(user, true);
							
						}				
					}
				}
				Utils.changeSelectDeselectAllButton(sm, store, selectAll,deselectAll);
			}
		});

		grid = new Grid<UserModel>(store, new ColumnModel(configs));
		grid.setStripeRows(true);
		grid.setColumnLines(true);
		grid.setSelectionModel(sm);
		grid.addPlugin(sm);
		grid.setWidth("100%");
		grid.getView().setForceFit(true);
		grid.getView().setAutoFill(true);

		grid.addListener(Events.Attach, new Listener<GridEvent<UserModel>>() {
			
			public void handleEvent(GridEvent<UserModel> be) {
				
				PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(CostantiGestioneUtenti.UTENTI_GRID_LIMIT);

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

		grid.setLoadMask(true);
		add(grid);
		
	}	

	public void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		topToolBar.addStyleName("font-weight-style");
		toListaBiblioteche = new Button("Ricerca le biblioteche da assegnare", Resources.ICONS.find());
		
		toListaBiblioteche.addSelectionListener(new SelectionListener<ButtonEvent>() {
				
			@Override
			public void componentSelected(ButtonEvent ce) {					
				AppEvent event = new AppEvent(AppEvents.RicercaListaBiblioInAssegna);
				event.setData("idUsers", userSelectedList);
				event.setData("parametriRicerca", null);
				Dispatcher.forwardEvent(event);
				
			}
			
		});
		
		toListaBiblioteche.disable();
		
		/*GESTIONE BOTTONE SELECTALL DA QUI*/
		
		/*Per aggiungere un bottone che selezioni tutto (come se si cliccasse
		 * nel checkbox dell'header della colonna):
		 * 1 - Sostituire il tipo CheckBoxSelectionModel con CheckBoxSelectionModelCustom
		 * 2 - Creare un Button selectAll ed aggiungergli un listener che modifichi la label
		 *   del bottone e sincronizzi lo stato col CheckBoxSelectionModelCustom 
		 *   come segue 
		 * 3 - Aggiungere un listener al CheckBoxSelectionModelCustom sm che modifichi la label
		 *   del bottone e sincronizzi lo stato col bottone come segue
		 *   
		 * * */
		
//		selectAll.setIcon(Resources.ICONS.selectall());
		deselectAll.addSelectionListener(getSelectDeselectAllListener());
		deselectAll.hide();
		selectAll.addSelectionListener(getSelectDeselectAllListener());
		
		sm.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectDeselectAllButton(sm, store, selectAll,deselectAll);
			}
		});
		
		topToolBar.add(selectAll);
		topToolBar.add(deselectAll);
		
		/* Listener per la griglia: gestione click sulla checkbox presente in colonna per
		 * selezionare/deselezionare tutti gli utenti. */
		grid.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				Utils.changeSelectDeselectAllButton(sm, store, selectAll,deselectAll);
				if (sm.getSelectedItems().size() == store.getModels().size()) {				
					/* Sono stati selezionati tutti gli utenti */
					List<UserModel> ulist = (List<UserModel>) sm.getSelectedItems();
					
					for (UserModel entry : ulist) {
						if (!userSelectedList.contains(entry.getIdUser()))
							userSelectedList.add(new Integer(entry.getIdUser()));
					}
					toListaBiblioteche.enable();
					
				}
				else if (sm.getSelectedItems().size() == 0) {
					/* Sono stati deselezionati tutti gli utenti */
//					userSelectedList.clear();
					removeIdsOfUsersInAuctualListOnDeselectAll();
					
					toListaBiblioteche.disable();
				}
			}
		
		
		});
		/*GESTIONE BOTTONE SELECTALL- FINE*/
		
		
		/* Listener per la griglia: inserisce gli utenti selezionate in lista ed
		 * abilita il pulsante 'Ricerca le biblioteche da assegnare' */
		grid.addListener(Events.RowClick, new Listener<GridEvent<UserModel>>() {
			
			public void handleEvent(GridEvent<UserModel> be) {
				if (sm.isSelected(be.getModel())) {
					/* La checkbox è abilitata per la riga che è stata cliccata */
					if (!userSelectedList.contains(be.getModel().getIdUser()))
						userSelectedList.add(new Integer(be.getModel().getIdUser()));
					
					toListaBiblioteche.enable();
				}
				else {
					/* La checkbox è disabilitata per la riga che è stata cliccata */
					if (userSelectedList.contains(be.getModel().getIdUser()))
						userSelectedList.remove((Integer)be.getModel().getIdUser());
					
					if (userSelectedList.size() == 0) {
						/* Sono state disabilitate tutte le checkbox; 
						 * la lista è quindi vuota e bisogna disabilitare il pulsante */
						toListaBiblioteche.disable();
					}
					
				}
				
			}
		});
		
		
		
		
		topToolBar.add(toListaBiblioteche);	
		setTopComponent(topToolBar);
	}
	
	private SelectionListener<ButtonEvent> getSelectDeselectAllListener() {
		return new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (sm.getSelectedItems().size() < store.getCount()){
					sm.selectAll();
//					selectAll.setText("Deseleziona tutto");
					selectAll.hide();
					deselectAll.show();
				}else if (sm.getSelectedItems().size() == store.getCount()){
					sm.deselectAll();
//					selectAll.setText("Seleziona tutto");
					selectAll.show();
					deselectAll.hide();
				}
				sm.fireEvent(Events.HeaderClick);
				grid.fireEvent(Events.OnClick);
			}
		};
	}

	public void setBottomToolbar() {

		toolBar.bind(loader);
		
		setBottomComponent(toolBar);
	}

	public void setService() {
		utentiService = (UtentiServiceAsync) Registry.get(Abi.UTENTI_SERVICE);

		RpcProxy<PagingLoadResult<UserModel>> proxy = new RpcProxy<PagingLoadResult<UserModel>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {

				utentiService.getUsers((PagingLoadConfig) loadConfig, callback);
			}
		};

		/* Loader */
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);

	}
	
	public Grid<UserModel> getGrid() {
		
		return grid;

	}
	
	public void clearUsersSelected() {
		if (userSelectedList != null && userSelectedList.size() > 0) {
			userSelectedList.clear();
//			loader.load();
			toListaBiblioteche.disable();
		}
		selectAll.show();
		deselectAll.hide();
	}
	
	public void removeIdsOfUsersInAuctualListOnDeselectAll(){
		if (userSelectedList.size() != 0) {			
			Integer foundIdx=null;
			List<UserModel> tmplistuser = (List<UserModel>) store.getModels();
			for (UserModel user : tmplistuser) {

				for (Integer entry : userSelectedList) {
					if (user.getIdUser() == entry.intValue()){
						foundIdx=userSelectedList.indexOf(entry);
					}
				}
				if(foundIdx!=null){
					userSelectedList.remove(foundIdx.intValue());
					foundIdx=null;
				}
			}
		}
	}
}

