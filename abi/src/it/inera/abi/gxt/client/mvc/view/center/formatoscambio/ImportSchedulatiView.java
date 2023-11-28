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

package it.inera.abi.gxt.client.mvc.view.center.formatoscambio;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget.ImportSchedulatiListPanel;

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
 * View utilizzata per l'inizializzazione del pannello contenente la lista degli
 * import in schedulazione e la relativa gestione degli eventi  
 *
 */
public class ImportSchedulatiView extends View {
	
	private ContentPanel mainPanel;
	private ImportSchedulatiListPanel importSchedulatiListPanel;

	public ImportSchedulatiView(Controller controller) {
		super(controller);
		mainPanel = new ContentPanel();
		mainPanel.setLayout(new FitLayout());
		mainPanel.setHeading(CostantiFormatoScambio.GESTIONE_IMPORT_SCHEDULATI_HEADER);
		importSchedulatiListPanel = new ImportSchedulatiListPanel();
		mainPanel.add(importSchedulatiListPanel);
		
	}


	@Override
	protected void handleEvent(AppEvent event) {

		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();

		if (event.getType() == AppEvents.ImportSchedulati) {
			
			importSchedulatiListPanel.disableButtons();  // disabilito i pulsanti
			
			// carico i dati dei file presi dall'evento e caricati dal controller
			Grid<FileCaricatiListModel> grid = importSchedulatiListPanel.getGrid();
			ListStore<FileCaricatiListModel> store = grid.getStore();
			store.removeAll();
			List<FileCaricatiListModel> dati = event.getData(CostantiFormatoScambio.DATI_FILE);
			if (dati != null && dati.size() > 0) {
				store.add(dati);	
			}
		}
		wrapper.add(mainPanel);
		wrapper.layout();
		wrapper.unmask();
	}

}
