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
