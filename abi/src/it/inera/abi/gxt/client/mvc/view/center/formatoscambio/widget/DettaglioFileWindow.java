package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public abstract class DettaglioFileWindow extends Window {

	protected String filename = null;
	protected Grid<BiblioModel> grid;
	protected Window _instance;
	
	protected ErroreFileControllatoWindow erroreFileControllatoWindow = null;
	
	protected RpcProxy<PagingLoadResult<BiblioModel>> proxy;
	
	public DettaglioFileWindow() {
		
		setSize(600, 525);  
		setFrame(true);  
		setModal(true);  
		setLayout(new FitLayout());  
		setHeading("Dettaglio file");
		setStyleAttribute("background-color", "white");
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		setScrollMode(Scroll.AUTOY);
		setResizable(false);

		_instance = this;
		
		erroreFileControllatoWindow = new ErroreFileControllatoWindow();
		
		setProxy(); // inizializza il proxy
		
		// loader  
		PagingLoader<PagingLoadResult<BiblioModel>> loader = new BasePagingLoader<PagingLoadResult<BiblioModel>>(proxy);  
		loader.setRemoteSort(true);  

		ListStore<BiblioModel> store = new ListStore<BiblioModel>(loader);  

		final PagingToolBar toolBar = new PagingToolBar(20);  
		toolBar.bind(loader);  

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  

		//{codice=IT-MT0001, denominazione=Biblioteca comunale}
		ColumnConfig column = new ColumnConfig();  
		column.setId("codice");  
		column.setHeader("Codice ICCU");  
		column.setWidth(90);  
		configs.add(column);  

		column = new ColumnConfig();  
		column.setId("denominazione");  
		column.setHeader("Denominazione");  
		column.setWidth(200);  
		configs.add(column);  

		setBottomComponent(toolBar); 
		ColumnModel cm = new ColumnModel(configs);  

		grid = new Grid<BiblioModel>(store, cm);
		grid.setBorders(true);  
		grid.setAutoExpandColumn("denominazione");  
		grid.setAutoExpandMax(30000);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		add(grid);
		
		// controllo se il risultato va visualizzato nella griglia (nessun errore XML), oppure in una finestra a parte (errore XML)
		loader.addLoadListener(new LoadListener() {
			@Override
			public void loaderLoad(LoadEvent le) {
				Object o = le.getData();
				if (o != null && o instanceof BasePagingLoadResult<?>) {
					List<BiblioModel> obj = (List<BiblioModel>)((BasePagingLoadResult<?>) o).getData();
					if (obj.size() == 1 && "Errore".equals(obj.get(0).getCodice())) {
						_instance.hide();
						erroreFileControllatoWindow.setError(obj.get(0).getDenominazione());
						erroreFileControllatoWindow.show();
					} else {
						show();
					}
				}
				
			}
		});

		
	}
	

	protected void loadAndView() {
		grid.getStore().removeAll();
		((PagingLoader<PagingLoadResult<BiblioModel>>) (grid.getStore().getLoader())).load(0, 20);
	}
	
	public abstract void setProxy();
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return filename;
	}
	
}
