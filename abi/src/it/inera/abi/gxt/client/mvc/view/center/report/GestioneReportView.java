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

package it.inera.abi.gxt.client.mvc.view.center.report;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiReport;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportFormTipoStampaPanel;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportRisultatiPanel;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportStampaPanel;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * View utilizzata per l'inizializzazione dei pannelli riguardanti la ricerca,
 * la lista dei risultati ed il resoconto delle stampe (pdf e/o excel), e la 
 * relativa gestione degli eventi  
 *
 */
public class GestioneReportView extends View {
	
	private ContentPanel mainPanel;
	private GestioneReportFormTipoStampaPanel formPanel;
	private GestioneReportRisultatiPanel risultatiPanel;
	private GestioneReportStampaPanel stampaPanel;
	private CardLayout layout;
	

	public GestioneReportView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setHeading(CostantiReport.RICERCA_BIBLIO_CREAZIONE_STAMPE);
		mainPanel.setIcon(Resources.ICONS.find());
		mainPanel.setScrollMode(Scroll.AUTO);
		createFormRicercaBiblioGestioneExport();
	}

	private void createFormRicercaBiblioGestioneExport() {
		layout = new CardLayout();
	    
		formPanel = new GestioneReportFormTipoStampaPanel();
		
		risultatiPanel = new GestioneReportRisultatiPanel();
		risultatiPanel.setHeading("Lista risultati report");
		risultatiPanel.setTipoRicerca(1);
		risultatiPanel.setTopToolbar();
		
		
		stampaPanel = new GestioneReportStampaPanel();
		stampaPanel.setTipoRicerca(1);
		stampaPanel.setPanel();
		
		mainPanel.add(formPanel);
		mainPanel.add(risultatiPanel);
		mainPanel.add(stampaPanel);
		
		mainPanel.setLayout(layout);
   
	    layout.setActiveItem(mainPanel.getItem(0));
	
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.RicercaBiblioCreazioneReport) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			formPanel.resetAll();
			layout.setActiveItem(formPanel);
			wrapper.add(mainPanel);
			wrapper.layout();
			
		}
		else if (event.getType() == AppEvents.VisualizzaRisultatiReport) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);		

			final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("filtriRisultati"));
			final HashMap<String, Object> labels = (HashMap<String, Object>) (event.getData("filtriRisultatiLabel"));
			
			risultatiPanel.setKeys(keys, labels);
			
			layout.setActiveItem(risultatiPanel);
			wrapper.layout();
		
		}
		else if (event.getType() == AppEvents.VisualizzaFormReport) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			layout.setActiveItem(formPanel);
			wrapper.layout();
			
		}
		else if (event.getType() == AppEvents.VisualizzaStampaReport) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);	
			
			/* Parametri */
			HashMap<String, Object> params = (HashMap<String, Object>) event.getData("parameters");
			HashMap<String, Object> labels = (HashMap<String, Object>) event.getData("filtriRisultatiLabel");
			
			stampaPanel.setLabels(labels);
			
			List<Integer> idBibSelected = (List<Integer>) params.get("idBibs");
			stampaPanel.setBiblioteche(idBibSelected);

			/* Lista o etichetta */
			final String tipoDiStampa = (String) labels.get("tipoDiStampa");
			stampaPanel.setLabel(tipoDiStampa);
			
			layout.setActiveItem(stampaPanel);
			wrapper.layout();
			
		}
	}

}
