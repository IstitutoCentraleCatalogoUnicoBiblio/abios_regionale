package it.inera.abi.gxt.client.mvc.view.center.formatoscambio;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget.FileCaricatiControllatiListPanel;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * View utilizzata per l'inizializzazione del pannello contenente la lista dei
 * file caricati e controllati e la relativa gestione degli eventi  
 *
 */
public class FileCaricatiControllatiView extends View {
	
	private ContentPanel mainPanel;
	private FileCaricatiControllatiListPanel fileCaricatiControllatiListPanel;
	
	public FileCaricatiControllatiView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		fileCaricatiControllatiListPanel = new FileCaricatiControllatiListPanel();
		mainPanel.setLayout(new FitLayout());
		mainPanel.setHeading(CostantiFormatoScambio.GESTIONE_FILE_CONTROLLATI);
		mainPanel.add(fileCaricatiControllatiListPanel);
	}

	@Override
	protected void handleEvent(AppEvent event) {

		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();
		
		if (event.getType() == AppEvents.FileCaricatiControllati) {
			
			fileCaricatiControllatiListPanel.disableButtons(); // disabilito i pulsanti
			
			// carico i dati dei file presi dall'evento e caricati dal controller
			Grid<FileCaricatiListModel> grid = fileCaricatiControllatiListPanel.getGrid();
			ListStore<FileCaricatiListModel> store = grid.getStore();
			
			store.removeAll();
			List<FileCaricatiListModel> dati = event.getData(CostantiFormatoScambio.DATI_FILE);
			if (dati != null && dati.size() > 0) {
				grid.getStore().add(dati);	
			}
		}
		
		wrapper.add(mainPanel);
		wrapper.layout();
		wrapper.unmask();
	}

}
