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

package it.inera.abi.gxt.client.mvc.view.center.statistiche;

import it.inera.abi.gxt.client.costants.CostantiStatistiche;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.AppView;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.core.client.GWT;

/**
 * View utilizzata per l'inizializzazione delle tavole delle statistiche e la 
 * relativa gestione degli eventi  
 *
 */
public class StatisticheView extends View {

	private AppEvent event = null;
	private boolean onInit = true;
	
	public StatisticheView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();
		wrapper.setLayout(new FillLayout());
		ContentPanel mainPanel = new ContentPanel();
		mainPanel.addListener(Events.Resize, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (!onInit) {
					Dispatcher.get().dispatch(event);
				}
			}
		});
		wrapper.add(mainPanel);
		wrapper.layout();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		MenuItem tmpItem = (MenuItem) event.getData();
		String urlTmp = (String) tmpItem.get("urlname");
		this.event = event;
		onInit = false;
		relayout(urlTmp);
	}
	
	private void relayout(String urlTmp) {
		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();
		wrapper.layout();
		
		wrapper.setLayout(new FillLayout());

		
		HtmlContainer tavola = new HtmlContainer();
		tavola.setHeight(660);
	
		tavola.removeAll();ContentPanel mainPanel = new ContentPanel();
		mainPanel.removeAll();
		mainPanel.layout();
		
		mainPanel.setHeading(CostantiStatistiche.getListaNomiTabelle().get(((CostantiStatistiche.getListaUrlTabelle()).indexOf(urlTmp))));
		mainPanel.setScrollMode(Scroll.ALWAYS);
		tavola.setUrl(GWT.getHostPageBaseURL() + "statistiche/" + urlTmp);

		mainPanel.add(tavola);
		mainPanel.layout();
		//
		wrapper.add(mainPanel);
		wrapper.layout();
	}
}
