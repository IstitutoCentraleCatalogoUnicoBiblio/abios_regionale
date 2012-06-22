package it.inera.abi.gxt.client.mvc.controller;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.TabelleDinamicheView;
import it.inera.abi.gxt.client.mvc.view.menu.TabelleDinamicheMenuView;

import java.util.List;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class TabelleDinamicheController extends Controller {

	private TabelleDinamicheMenuView menuView;
	private TabelleDinamicheView tabelleDinamicheView;

	public TabelleDinamicheController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.NavTabDin);
		registerEventTypes(AppEvents.MAPPING_TABELLE_DINAMICHE);

	}

	@Override
	public void initialize() {
		menuView = new TabelleDinamicheMenuView(this);
		tabelleDinamicheView = new TabelleDinamicheView(this);
	}

	public void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {

			forwardToView(menuView, event);
		}
		else if (event.getType() == AppEvents.MAPPING_TABELLE_DINAMICHE) {

			forwardToView(tabelleDinamicheView, event);
		}

	}

}
