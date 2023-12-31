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

package it.inera.abi.gxt.client.mvc.view.center.utenti;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.FormCompletoUtenteCreazioneModifica;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.ListaBiblioUtentePanel;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.ListaUtentiPanel;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.RicercaUtentiFormPanel;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * View utilizzata per l'inizializzazione del pannello contenente la lista degli
 * utenti e la relativa gestione degli eventi (modifica utente, assegna biblioteche,
 * filtra utenti, ecc...)
 *
 */
public class ListaUtentiView extends View {

	private ContentPanel mainListaUtentiPanel = null;
	
	private CardLayout cardLayout = new CardLayout();
	
	private LayoutContainer mainContainer = null;
	private RicercaUtentiFormPanel formRicercaUtente = null;
	private ListaUtentiPanel listaUtentiPanel = null;
	private FormCompletoUtenteCreazioneModifica formCompletoModificaCreazione = null;	
	private ListaBiblioUtentePanel listaBiblioUtente = null;

	public ListaUtentiView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainListaUtentiPanel = new ContentPanel();
		mainListaUtentiPanel.setLayout(cardLayout);
		mainListaUtentiPanel.setMonitorWindowResize(true);
		mainListaUtentiPanel.setHeading("Ricerca Utenti");
		mainListaUtentiPanel.setIcon(Resources.ICONS.find());
		BorderLayout b = new BorderLayout();

		mainContainer = new LayoutContainer(b);

		BorderLayoutData topData = new BorderLayoutData(LayoutRegion.NORTH, 200);

		formRicercaUtente = new RicercaUtentiFormPanel();
		formRicercaUtente.setForwardEventType(AppEvents.FiltraListaUtentiInRicercaUtente);
		
		mainContainer.add(formRicercaUtente, topData);
		
		
		BorderLayoutData southData = new BorderLayoutData(LayoutRegion.CENTER);

		listaUtentiPanel = new ListaUtentiPanel(false);

		mainContainer.add(listaUtentiPanel, southData);

		mainListaUtentiPanel.add(mainContainer);

		formCompletoModificaCreazione = new FormCompletoUtenteCreazioneModifica();

		mainListaUtentiPanel.add(formCompletoModificaCreazione);
		
		listaBiblioUtente = new ListaBiblioUtentePanel();
		mainListaUtentiPanel.add(listaBiblioUtente);
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.ListaUtenti) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
//			if (event.getData("tree") != null) {
//				Utils.selectFirstMenuEntry((TreePanel) event.getData("tree"));
				formRicercaUtente.reset();
				listaUtentiPanel.setKeys(null);
				listaUtentiPanel.deselectAllAndDisableButtons();
//			}
			
			cardLayout.setActiveItem(mainContainer);
			wrapper.add(mainListaUtentiPanel);
			wrapper.layout();
			return;
		
		}
		if (event.getType()==AppEvents.RicercaBiblioGenericaViaUtentiIndietro) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			cardLayout.setActiveItem(mainContainer);
			wrapper.add(mainListaUtentiPanel);
			wrapper.layout();
			return;
		}

		if (event.getType() == AppEvents.FiltraListaUtentiInRicercaUtente) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();

			HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("parametriRicerca"));
			listaUtentiPanel.setKeys(keys);
			cardLayout.setActiveItem(mainContainer);
			wrapper.add(mainListaUtentiPanel);
			wrapper.layout();
			return;
		}

		if (event.getType() == AppEvents.ModificaUtente) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			formCompletoModificaCreazione.refresh();
			
			UtenteCompletoFormModel utente = (UtenteCompletoFormModel) event.getData("datiUtente");
			formCompletoModificaCreazione.setUtenteModifica(utente);
			formCompletoModificaCreazione.setProfili(utente.getProfili());
			formCompletoModificaCreazione.setModifica(true);
			formCompletoModificaCreazione.riorganizzaFieldPerModificaByAdmin();
			
			cardLayout.setActiveItem(formCompletoModificaCreazione);
			
			wrapper.layout();
		}
		
		if (event.getType() == AppEvents.AssegnaBibliotecheAUtente) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			
			UserModel userModel = (UserModel) event.getData("user");
			listaBiblioUtente.setHeading("Lista biblioteche assegnate all'utente: "+userModel.getUserName());
			listaBiblioUtente.setUser(userModel); 
			if(listaBiblioUtente.getGrid().isAttached()==true){
				listaBiblioUtente.getLoader().load();
			}
			listaBiblioUtente.layout(); 
			cardLayout.setActiveItem(listaBiblioUtente);
			wrapper.layout();
			return;
		}
		
		if (event.getType() == AppEvents.IndietroDaModificaModificaUtente) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			listaUtentiPanel.deselectAllAndDisableButtons();
//			listaUtentiPanel.setKeys(formRicercaUtente.getKeys());
			listaUtentiPanel.getLoader().load();
			cardLayout.setActiveItem(mainContainer);
			wrapper.layout();
		}
	}

}