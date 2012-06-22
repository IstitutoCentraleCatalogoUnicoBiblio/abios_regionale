package it.inera.abi.gxt.client.mvc.view.menu;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.costants.CostantiMenu;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.TreePanelViewCustom;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class GestioneUtentiMenuView extends View {

	private TreePanel<MenuItem> tree;
	
	public GestioneUtentiMenuView(Controller controller) {
		super(controller);
	}


	protected void initUI() {
		
		// se l'utente non ha ruolo di gestione utenti esco
		if (!UIAuth.isMenuUtentiEnable()) return; 
		
		ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		ContentPanel gestioneUtenti = new ContentPanel();
		gestioneUtenti.setHeading(CostantiMenu.GES_UT);
		gestioneUtenti.setAnimCollapse(false);
//		gestioneUtenti.setScrollMode(Scroll.ALWAYS);
		gestioneUtenti.setScrollMode(Scroll.AUTOY);

		TreeStore<MenuItem> store = new TreeStore<MenuItem>();

		List<MenuItem> items = MenuEntriesFactory.getTreeModelGestUtenti();

		store.add(items, true);

		tree = new TreePanel<MenuItem>(store);
		tree.setView(new TreePanelViewCustom());
//		tree.setWidth(300);
		tree.setDisplayProperty("name");
		tree.setAutoSelect(true);
		tree.setIconProvider(new ModelIconProvider<MenuItem>() {
			@Override
			public AbstractImagePrototype getIcon(MenuItem model) {
				if (model.get("icon") != null) {
					return IconHelper.createStyle((String) model.get("icon"));
				} else {
					return null;
				}
			}
		});

		tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			@Override
			public void handleEvent(TreePanelEvent<ModelData> tpe) {
				MenuItem m = (MenuItem) tpe.getItem();
				AppEvent evt = null;
				if ((m.getName()).equals(CostantiGestioneUtenti.NUOVO_UTENTE)) {
					evt = new AppEvent(AppEvents.NuovoUtente, m);
				} else if ((m.getName()).equals(CostantiGestioneUtenti.GESTIONE_UTENTI)) {
					evt = new AppEvent(AppEvents.ListaUtenti, m);
				} else if ((m.getName()).equals(CostantiGestioneUtenti.ASS_LIST_BIB_LIST_UT)) {
					evt = new AppEvent(AppEvents.AssegnaListeAUtenti, m);
				}
				fireEvent(evt);
			}
		});

		gestioneUtenti.addListener(Events.Expand, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				UIAuth.checkIsLogin(); // esegue una richiesta fasulla asynchrona al server, se va in errore per redirect di login, richiama alla pagina login.jsp
				
				Utils.selectFirstMenuEntry(tree);
				Dispatcher.get().dispatch(AppEvents.ListaUtenti);
			}
		});
		Utils.selectFirstMenuEntry(tree);
		gestioneUtenti.add(tree);
		west.add(gestioneUtenti);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
		if (event.getType() == AppEvents.ListaUtenti) {
//			event.setData("tree", tree);
			event.setData("test", new Boolean(true));
			Dispatcher.forwardEvent(event);
		}
	}

}
