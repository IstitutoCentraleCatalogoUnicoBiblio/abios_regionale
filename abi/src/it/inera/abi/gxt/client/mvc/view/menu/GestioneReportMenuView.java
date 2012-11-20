package it.inera.abi.gxt.client.mvc.view.menu;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiMenu;
import it.inera.abi.gxt.client.costants.CostantiReport;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.TreePanelViewCustom;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

/**
 * View utilizzata per l'inizializzazione del menu delle stampe e dei report e
 * la relativa gestione degli eventi associati
 *
 */
public class GestioneReportMenuView extends View {

	private ContentPanel gestioneReport;

	public GestioneReportMenuView(Controller controller) {
		super(controller);
	}

	protected void initUI() {
		
		/* se l'utente non ha ruolo di stampe e report esco */
		if (!UIAuth.isMenuStampeReportEnable()) return; 
		
		ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		gestioneReport = new ContentPanel();
		gestioneReport.setHeading(CostantiMenu.REPORT);
		gestioneReport.setAnimCollapse(false);
		gestioneReport.setScrollMode(Scroll.AUTOY);

		List<MenuItem> model = MenuEntriesFactory.getTreeModelReport();
		TreeStore<MenuItem> store = new TreeStore<MenuItem>();
		store.add(model, true);

		final TreePanel<MenuItem> tree = new TreePanel<MenuItem>(store);
		tree.setView(new TreePanelViewCustom());
		tree.setDisplayProperty("name");
		tree.getStyle().setLeafIcon(Resources.ICONS.report());
		
		tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			@Override
			public void handleEvent(TreePanelEvent<ModelData> tpe) {
				MenuItem m = (MenuItem) tpe.getItem();
				AppEvent evt = null;
				if ((m.getName()).equals(CostantiReport.RICERCA_BIBLIO_CREAZIONE_STAMPE)) {
					evt = new AppEvent(	AppEvents.RicercaBiblioCreazioneReport, m);
					fireEvent(evt);
				}

			}
		});

		gestioneReport.addListener(Events.Expand, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				UIAuth.checkIsLogin(); // esegue una richiesta fasulla asynchrona al server, se va in errore per redirect di login, richiama alla pagina login.jsp
				
				Utils.selectFirstMenuEntry(tree);
				Dispatcher.get().dispatch(	AppEvents.RicercaBiblioCreazioneReport);
			}
		});
		Utils.selectFirstMenuEntry(tree);
		gestioneReport.add(tree);

		west.add(gestioneReport);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}
	
}
