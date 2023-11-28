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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Estensione della classe <code>RevisioneWindow</code> che permette di 
 * respingere la revisione di una biblioteca
 *
 */
public class RespingiRevisioneWindow extends RevisioneWindow {

	public RespingiRevisioneWindow(String header, String textButton) {
		super(header, textButton);
	}

	@Override
	public void bind() {
		submit.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(final ComponentEvent be) {
				_instance.hide();
				
				final LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
				wrapper.mask("Respingi Revisione in corso...", "x-mask-loading");
				
				final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
				bibliotecheService.respingiRevisione(biblioModel.getIdBiblio(), messaggio.getValue(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						AbiMessageBox.messageSuccessAlertBox("La revisione della biblioteca è stata respinta", "Esito respingi revisione");
						if (ricercaGenerica) {
							AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
							event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
							event.setData(CostantiGestioneBiblio.ISMASKED, new Boolean(true));
							Dispatcher.forwardEvent(event);
						}							
						else {
							AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
							event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
							event.setData(CostantiGestioneBiblio.ISMASKED, new Boolean(true));
							Dispatcher.forwardEvent(event);
						}
						
					}
					@Override
					public void onFailure(Throwable caught) {
						UIAuth.checkIsLogin(caught.toString()); {// controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel respingere la revisione della biblioteca", "Errore");
						}
						
						wrapper.unmask();
					}
				});
			}
		});
	}

}
