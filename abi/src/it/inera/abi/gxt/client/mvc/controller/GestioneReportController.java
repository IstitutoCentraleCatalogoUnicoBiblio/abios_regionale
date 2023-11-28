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
import it.inera.abi.gxt.client.mvc.view.center.report.GestioneReportView;
import it.inera.abi.gxt.client.mvc.view.menu.GestioneReportMenuView;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

/**
 * Controller per la gestione dei report (ricerca, lista risultati, stampe)
 *
 */
public class GestioneReportController extends Controller {

	private GestioneReportMenuView menuView;
	private GestioneReportView gestioneReportView;

	public GestioneReportController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.RicercaBiblioCreazioneReport);
		registerEventTypes(AppEvents.VisualizzaRisultatiReport);
		registerEventTypes(AppEvents.VisualizzaFormReport);
		registerEventTypes(AppEvents.VisualizzaStampaReport);
	}

	@Override
	public void initialize() {
		menuView = new GestioneReportMenuView(this);
		gestioneReportView = new GestioneReportView(this);
	}

	@Override
	public void handleEvent(AppEvent event) {
		
		EventType type = event.getType();
		
		if (type == AppEvents.Init) {
			forwardToView(menuView, event);
			
		} 
		else if (type == AppEvents.RicercaBiblioCreazioneReport) {
			forwardToView(gestioneReportView, event);
			
		}
		else if (type == AppEvents.VisualizzaRisultatiReport) {
			forwardToView(gestioneReportView, event);
			
		}
		else if (type == AppEvents.VisualizzaFormReport) {
			forwardToView(gestioneReportView, event);
		}
		else if (type == AppEvents.VisualizzaStampaReport) {
			forwardToView(gestioneReportView, event);
		}
	}
}
