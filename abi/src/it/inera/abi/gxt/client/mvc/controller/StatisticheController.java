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

package it.inera.abi.gxt.client.mvc.controller;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiStatistiche;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.mvc.view.center.statistiche.StatisticheView;
import it.inera.abi.gxt.client.mvc.view.menu.StatisticheMenuView;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

/**
 * Controller per la gestione delle statistiche
 *
 */
public class StatisticheController extends Controller {

	private StatisticheMenuView menuView;
	private StatisticheView statisticheView;

	public StatisticheController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.NavStat);
		registerEventTypes(AppEvents.NavStatItemSelected);
	}

	@Override
	public void initialize() {
		menuView = new StatisticheMenuView(this);
		statisticheView = new StatisticheView(this);
	}

	@Override
	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Init) {
			forwardToView(menuView, event);
		} else if (type == AppEvents.NavStat) {
			MenuItem tmpItem = new MenuItem();
			tmpItem.set("urlname", CostantiStatistiche.TAVOLA01);
			event.setData(tmpItem);
			forwardToView(statisticheView, event);
		} else if (type == AppEvents.NavStatItemSelected) {
			forwardToView(statisticheView, event);
		}
	}

}
