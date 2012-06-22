package it.inera.abi.gxt.client.mvc.view.center.formatoscambio;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget.FileCaricatiNonControllatiListPanel;
import it.inera.abi.gxt.client.mvc.view.menu.MenuEntriesFactory;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class FileCaricatiNonControllatiView extends View {
	private ContentPanel mainPanel;
	private FileCaricatiNonControllatiListPanel fileCaricatiNonControllatiListPanel;

	public FileCaricatiNonControllatiView(Controller controller) {
		super(controller);

	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setLayout(new FitLayout());
		mainPanel.setHeading(CostantiFormatoScambio.GESTIONE_FILE_CARICATI_NON_CONTROLLATI);
		fileCaricatiNonControllatiListPanel = new FileCaricatiNonControllatiListPanel();
		mainPanel.add(fileCaricatiNonControllatiListPanel);
	}


	@Override
	protected void handleEvent(AppEvent event) {
		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();

		if (event.getType() == AppEvents.FileCaricatiNonControllati) {
			fileCaricatiNonControllatiListPanel.disableButtons(); // disabilito i pulsanti
			
			// carico i dati dei file presi dall'evento e caricati dal controller
			Grid<FileCaricatiListModel> grid = fileCaricatiNonControllatiListPanel.getGrid();
			ListStore<FileCaricatiListModel> store = grid.getStore();
			
			store.removeAll();
			List<FileCaricatiListModel> dati = event.getData(CostantiFormatoScambio.DATI_FILE);
			if (dati != null && dati.size() > 0) {
//				store.add(dati);
				grid.getStore().add(dati);
			}
		}
		wrapper.add(mainPanel);
		wrapper.layout();
		wrapper.unmask();
	}
}