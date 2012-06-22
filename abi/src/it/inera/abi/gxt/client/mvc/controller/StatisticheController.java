/*
 * Ext GWT 2.2.0 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
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
