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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista degli utenti gestori
 *
 */
public class ListaUtentiGestoriPanel extends ContentPanel {
	
	protected Grid<UserModel> grid = null;
	protected PagingLoader<PagingLoadResult<ModelData>> loader = null;
	
	private BibliotecheServiceAsync bibliotecheService = null;
	private Integer idBib = null;
	
	protected PagingToolBar toolBar = new PagingToolBar(40);
	protected PagingLoadConfig config = null;
	
	public void resetPaging() {
		if (config != null) {
			config.setOffset(0);
			config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
		}
	}

	public ListaUtentiGestoriPanel() {
		super();
		initPanel();
	}

	protected void initPanel() {
		setMonitorWindowResize(true);
		setHeading("Lista utenti gestori");
		setIcon(Resources.ICONS.group());
		setLayout(new FitLayout());
		setService();
		setGrid();
		setBottomToolbar();
	}

	public Grid<UserModel> getGrid() {
		return grid;

	}

	protected void setGrid() {
	
			final List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			CheckBoxSelectionModel<UserModel> sm = null;
	
			ColumnConfig columnUsername = new ColumnConfig();
			columnUsername.setId("login");
			columnUsername.setHeader("User Name");
			columnUsername.setResizable(false);
			columnUsername.setWidth(140);
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
			columnNome.setWidth(140);
			configs.add(columnNome);
	
			ColumnConfig columnCognome = new ColumnConfig();
			columnCognome.setId("cognome");
			columnCognome.setHeader("Cognome");
			columnCognome.setWidth(140);
			configs.add(columnCognome);
	
			ColumnConfig columnIncarico = new ColumnConfig();
			columnIncarico.setId("incarico");
			columnIncarico.setHeader("Incarico");
			columnIncarico.setAlignment(HorizontalAlignment.LEFT);
			columnIncarico.setWidth(350);
			columnIncarico.setRenderer(new GridCellRenderer<ModelData>() {  
	
				public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
						ListStore<ModelData> store, Grid<ModelData> grid) {  
					return "<b><span style=\"color: #385F95; text-decoration: none;width:300;text-align:justify;\">"  
					+ Utils.insertBRtag((String)model.get("incarico") ,75) 
					+ "</span></b>";  
				}  
	
			});  
			configs.add(columnIncarico);
	
			ListStore<UserModel> store = new ListStore<UserModel>(loader);
	
			grid = new Grid<UserModel>(store, new ColumnModel(configs));
			grid.setStateful(true);
			grid.setStripeRows(true);
			grid.setColumnLines(true);
			grid.getView().setAutoFill(true);
	
			grid.setAutoWidth(true);
			grid.setAutoExpandColumn("incarico");
			grid.setAutoExpandMax(3000);
			grid.setLoadMask(true);
			add(grid);
		}

	protected void setBottomToolbar() {

		toolBar.bind(loader);
		setBottomComponent(toolBar);
	}

	public void setKeys(int idbib) {
		config = new BasePagingLoadConfig();

		/** PARAMETRO ID BIBLIOTECA **/
		idBib = new Integer(idbib);
		config.set("idBiblio", idBib);

		config.setOffset(0);
		config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);

		loader.load(config);
	}

	protected void setService() {
		this.bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
		RpcProxy<PagingLoadResult<UserModel>> proxy = new RpcProxy<PagingLoadResult<UserModel>>() {
			@Override
			public void load(Object loadConfig,	AsyncCallback<PagingLoadResult<UserModel>> callback) {
				((PagingLoadConfig)loadConfig).set("idBiblio", idBib);
				bibliotecheService.getUsersByBiblio((PagingLoadConfig) loadConfig, callback);
			}
		};

		// loader
		loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
		loader.setRemoteSort(true);

	}

	public  PagingLoader<PagingLoadResult<ModelData>> getLoader(){
		return this.loader;
	}

	

	

}