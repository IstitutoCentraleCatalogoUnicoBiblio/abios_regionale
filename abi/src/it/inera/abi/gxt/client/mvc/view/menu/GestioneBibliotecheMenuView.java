package it.inera.abi.gxt.client.mvc.view.menu;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.costants.CostantiMenu;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.TreePanelViewCustom;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.BaseEvent;
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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * View utilizzata per l'inizializzazione del menu delle biblioteche e
 * la relativa gestione degli eventi associati (ricerche, ecc...)
 *
 */
public class GestioneBibliotecheMenuView extends View {

	private ContentPanel gestioneBiblioteche;

	public GestioneBibliotecheMenuView(Controller controller) {
		super(controller);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}

	protected void initUI() {

		/* se l'utente non ha ruolo di gestion biblioteche esco */
		if (!UIAuth.isMenuBibliotecaEnable()) return; 

		ContentPanel west = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		gestioneBiblioteche = new ContentPanel();
		gestioneBiblioteche.setAnimCollapse(false);
		gestioneBiblioteche.setHeading(CostantiMenu.GES_BIB);

		gestioneBiblioteche.addListener(Events.HeaderClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				MessageBox.info("Selected", "header clicked", null).show();

			}
		});

		gestioneBiblioteche.setScrollMode(Scroll.AUTOY);

		TreeStore<MenuItem> store = new TreeStore<MenuItem>();
		List<MenuItem> items = MenuEntriesFactory.getTreeModelGestBiblio();

		store.add(items, true);

		final TreePanel<MenuItem> tree = new TreePanel<MenuItem>(store);
		tree.setView(new TreePanelViewCustom());
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
				if ((m.getName()).equals(CostantiGestioneBiblio.LISTA_BIB)) {
					evt = new AppEvent(AppEvents.ListaBiblioteche,m);
					//					evt.setData(Constants.FROMMODIFICA, new Boolean(false));
					fireEvent(evt);
				}
				if ((m.getName()).equals(CostantiGestioneBiblio.GEST_BIB)) {
					evt = new AppEvent(AppEvents.RicercaBiblioGenerica,m);
					fireEvent(evt);
				}
				if ((m.getName()).equals(CostantiGestioneBiblio.GEST_BIB_COD)) {
					evt = new AppEvent(AppEvents.RicercaBiblioCodice, m);
					fireEvent(evt);
				}
				
			}
		});

		gestioneBiblioteche.addListener(Events.Expand, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				UIAuth.checkIsLogin(); // esegue una richiesta fasulla asynchrona al server, se va in errore per redirect di login, richiama alla pagina login.jsp 

				Utils.selectFirstMenuEntry(tree);

				AppEvent evt = new AppEvent(AppEvents.ListaBiblioteche);
				Dispatcher.forwardEvent(evt);


			}
		});

		Utils.selectFirstMenuEntry(tree);

		gestioneBiblioteche.add(tree);
		west.add(gestioneBiblioteche);
	}
}
