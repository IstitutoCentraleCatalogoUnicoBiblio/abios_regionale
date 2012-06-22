package it.inera.abi.gxt.client.mvc.view.menu;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiMenu;
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

public class TabelleDinamicheMenuView extends View {

	private ContentPanel tabelleDinamiche;

	public TabelleDinamicheMenuView(Controller controller) {
		super(controller);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}

	private void initUI() {
		
		// se l'utente non ha ruolo di tabelle dinamiche esco
		if (!UIAuth.isMenuTabelleDinamicheEnable()) return; 
		
		ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		tabelleDinamiche = new ContentPanel();
		tabelleDinamiche.setAnimCollapse(false);
		tabelleDinamiche.setHeading(CostantiMenu.GES_TAB_DIN);
		tabelleDinamiche.setScrollMode(Scroll.ALWAYS);

		final List<MenuItem> items = MenuEntriesFactory.getTreeModelTabDin();
		TreeStore<MenuItem> store = new TreeStore<MenuItem>();
		store.add(items, true);

		final TreePanel<MenuItem> tree = new TreePanel<MenuItem>(store);
		tree.setView(new TreePanelViewCustom());
		tree.setWidth(450);
		tree.setDisplayProperty("name");
		tree.getStyle().setLeafIcon(Resources.ICONS.table_db());
		tree.setAutoSelect(true);

		tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			@Override
			public void handleEvent(TreePanelEvent<ModelData> tpe) {
				MenuItem m = (MenuItem) tpe.getItem();
				AppEvent evt = null;
				evt = new AppEvent(AppEvents.MAPPING_TABELLE_DINAMICHE, m.getIdTabDin().intValue());
				fireEvent(evt);
			}

		});

		tabelleDinamiche.addListener(Events.Expand, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				UIAuth.checkIsLogin(); // esegue una richiesta fasulla asynchrona al server, se va in errore per redirect di login, richiama alla pagina login.jsp
				
				Utils.selectFirstMenuEntry(tree);
				Dispatcher.get().dispatch(AppEvents.MAPPING_TABELLE_DINAMICHE);

			}
		});
		Utils.selectFirstMenuEntry(tree);
		tabelleDinamiche.add(tree);

		west.add(tabelleDinamiche);

	}

	//	public static int getIdEvent(List<MenuItem> list, String nameEvent) {
	//		Iterator<MenuItem> it = list.iterator();
	//		MenuItem tmp;
	//		while ((it.hasNext())) {
	//			tmp = it.next();
	//			if (tmp.getName().equals(nameEvent)) {
	//				return list.indexOf(tmp);
	//			}
	//
	//		}
	//		return -1;
	//	}

	//	public static EventType getEvent(int id) {
	//	//	AppEvents.getTabDinEvents().get(id);
	//		return AppEvents.getTabDinEvents().get(id);
	//	}
	//	
	//	public static int getIdEvent(AppEvent ev){
	//		return AppEvents.getTabDinEvents().indexOf(ev);
	//	}


}
