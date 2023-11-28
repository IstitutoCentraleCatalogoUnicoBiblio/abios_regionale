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

package it.inera.abi.gxt.client.mvc.view.center.formatoscambio;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget.GestioneReportFormFormatoScambioPanel;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportRisultatiPanel;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportStampaPanel;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * View utilizzata per l'inizializzazione dei pannelli riguardanti la ricerca
 * per l'export, la lista dei risultati e l'invio dell'export, e la relativa gestione degli eventi  
 *
 */
public class RicercaBiblioPerExportView extends View {

	private ContentPanel mainPanel;
	private GestioneReportFormFormatoScambioPanel ricercaBiblioPerExportFormPanel;
	private GestioneReportRisultatiPanel risultatiPanel;
	private GestioneReportStampaPanel stampaPanel;
	private CardLayout layout;

	public RicercaBiblioPerExportView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setHeading("Ricerca biblioteche per formato di scambio");
		mainPanel.setIcon(Resources.ICONS.find());
		createFormRicercaBiblioGestioneExport();
	}

	private void createFormRicercaBiblioGestioneExport() {
		layout = new CardLayout();
		
		ricercaBiblioPerExportFormPanel = new GestioneReportFormFormatoScambioPanel();
		
		risultatiPanel = new GestioneReportRisultatiPanel();
		risultatiPanel.setHeading("Lista risultati formato di scambio");
		risultatiPanel.setTipoRicerca(2);
		risultatiPanel.setTopToolbar();
		
		stampaPanel = new GestioneReportStampaPanel();
		stampaPanel.setTipoRicerca(2);
		stampaPanel.setPanel();
		
		mainPanel.add(ricercaBiblioPerExportFormPanel);
		mainPanel.add(risultatiPanel);
		mainPanel.add(stampaPanel);
		
		mainPanel.setLayout(layout);
   
	    layout.setActiveItem(mainPanel.getItem(0));
	
		
	}

	@Override
	protected void handleEvent(AppEvent event) {		
		
		String userEmail = UIAuth.getUtentiAuthModel().getEmail();
		ricercaBiblioPerExportFormPanel.setEmail(userEmail);
		
		if (event.getType() == AppEvents.RicercaPerCreazioneExport) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			ricercaBiblioPerExportFormPanel.resetAll();
			
			layout.setActiveItem(ricercaBiblioPerExportFormPanel);
			wrapper.add(mainPanel);
			wrapper.layout();
			wrapper.unmask();

		} 
		else if (event.getType() == AppEvents.VisualizzaRisultatiFormatoDiScambio) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			
			final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("filtriRisultati"));
			final HashMap<String, Object> labels = (HashMap<String, Object>) (event.getData("filtriRisultatiLabel"));
			
			risultatiPanel.setKeys(keys, labels);
			
			layout.setActiveItem(risultatiPanel);
			wrapper.layout();
			
		}
		else if (event.getType() == AppEvents.VisualizzaFormFormatoDiScambio) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			layout.setActiveItem(ricercaBiblioPerExportFormPanel);
			wrapper.layout();
			
		}
		else if (event.getType() == AppEvents.VisualizzaStampaFormatoDiScambio) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			
			/* Parametri */
			HashMap<String, Object> params = (HashMap<String, Object>) event.getData("parameters");
			HashMap<String, Object> labels = (HashMap<String, Object>) event.getData("filtriRisultatiLabel");
			
			/* Email */
			final String email = (String) labels.get("emailNotifica");
			stampaPanel.setParam(email);
			
			stampaPanel.setLabels(labels);
			
			List<Integer> idBibSelected = (List<Integer>) params.get("idBibs");
			stampaPanel.setBiblioteche(idBibSelected);
			
			/* Differito */
			final String differito = (String) labels.get("differito");
			stampaPanel.setLabel(differito);
			
			layout.setActiveItem(stampaPanel);
			wrapper.layout();
		}
		
	}

}
