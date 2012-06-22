package it.inera.abi.gxt.client.mvc.view.menu;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiMenu;
import it.inera.abi.gxt.client.costants.CostantiStatistiche;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.TreePanelViewCustom;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.Iterator;
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

public class StatisticheMenuView extends View {

	private ContentPanel statistiche;

	public StatisticheMenuView(Controller controller) {
		super(controller);
	}

	protected void initUI() {
		 // se l'utente non ha ruolo di statistiche esco
        if (!UIAuth.isMenuStatisticheEnable()) return;
		ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		statistiche = new ContentPanel();
		statistiche.setHeading(CostantiMenu.STAT);
		statistiche.setAnimCollapse(false);
		statistiche.setScrollMode(Scroll.ALWAYS);


		final List<MenuItem> model = MenuEntriesFactory.getTreeModelStatistiche();
		TreeStore<MenuItem> store = new TreeStore<MenuItem>();
		store.add(model, true);

		final TreePanel<MenuItem> tree = new TreePanel<MenuItem>(store);
		tree.setView(new TreePanelViewCustom());
		tree.setWidth(450);
		tree.setDisplayProperty("name");
		tree.getStyle().setLeafIcon(Resources.ICONS.statistic());

		tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			@Override
			public void handleEvent(TreePanelEvent<ModelData> tpe) {
				MenuItem m = (MenuItem) tpe.getItem();
				int idEvent = getIdEvent(m.getName());
				m.set("urlname", getTabellaStatistica(idEvent));
				AppEvent evt = new AppEvent(AppEvents.NavStatItemSelected, m);
				fireEvent(evt);
			}
		});

		statistiche.addListener(Events.Expand, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				UIAuth.checkIsLogin(); // esegue una richiesta fasulla asynchrona al server, se va in errore per redirect di login, richiama alla pagina login.jsp
				
				Utils.selectFirstMenuEntry(tree);
				Dispatcher.get().dispatch(AppEvents.NavStat);
			}
		});
		Utils.selectFirstMenuEntry(tree);
		statistiche.add(tree);
		west.add(statistiche);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}

	public static int getIdEvent(String nameEvent) {
		List<MenuItem> list = MenuEntriesFactory.getTreeModelStatistiche();
		Iterator<MenuItem> it = list.iterator();
		MenuItem tmp;
		while ((it.hasNext())) {
			tmp = it.next();
			if (tmp.getName().equals(nameEvent)) {
				return list.indexOf(tmp);
			}

		}
		return -1;
	}

	public static String getTabellaStatistica(int id) {
		List<String> tmpList = CostantiStatistiche.getListaUrlTabelle();
		return tmpList.get(id);
	}
}
