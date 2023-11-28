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
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.FormCompletoUtenteCreazioneModifica;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * View utilizzata per l'inizializzazione del pannello di creazione
 * di un nuovo utente e la relativa gestione degli eventi
 *
 */
public class NuovoUtenteView extends View {

	private LayoutContainer nuovoUtentePanel = null;
	private FormCompletoUtenteCreazioneModifica nuovoUtenteFormPanel = null;

	public NuovoUtenteView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		nuovoUtentePanel = new LayoutContainer();
		nuovoUtentePanel.setLayout(new FitLayout());
		createNuovoUtenteForm();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.NuovoUtente) {
			nuovoUtenteFormPanel.refresh();
			nuovoUtenteFormPanel.setProfili((ArrayList<ProfiliUtente>) event.getData("profili"));
			nuovoUtenteFormPanel.setModifica(false);
			nuovoUtenteFormPanel.riorganizzaFieldPerModificaByAdmin();
			
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			nuovoUtenteFormPanel.layout();
			wrapper.add(nuovoUtentePanel);
			wrapper.layout();
			return;
		}
	}

	private void createNuovoUtenteForm() {

		nuovoUtenteFormPanel = new FormCompletoUtenteCreazioneModifica();
		nuovoUtenteFormPanel.setStyleAttribute("marginTop", "0px");
		nuovoUtentePanel.add(nuovoUtenteFormPanel);
	}
}
