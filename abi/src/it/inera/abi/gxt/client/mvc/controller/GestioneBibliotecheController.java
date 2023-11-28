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

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.ModificaBibliotecaView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.RicercaBiblioGenericaView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.RicercaBiblioViaCodiceView;
import it.inera.abi.gxt.client.mvc.view.menu.GestioneBibliotecheMenuView;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Controller per la gestione delle biblioteche (ricerche, lista biblioteche, 
 * modifica, ecc...)
 *
 */
public class GestioneBibliotecheController extends Controller {

	private GestioneBibliotecheMenuView menuView;
	
	private RicercaBiblioGenericaView ricercaBiblioGenericaView;

	private RicercaBiblioViaCodiceView ricercaBiblioViaCodiceView;
	
	private ModificaBibliotecaView modificaBibliotecaView;
	
	public GestioneBibliotecheController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.ListaBiblioteche);
		registerEventTypes(AppEvents.RicercaBiblioGenerica);
		registerEventTypes(AppEvents.RicercaBiblioCodice);
		registerEventTypes(AppEvents.SelectBiblio);
		registerEventTypes(AppEvents.ReloadBiblioData);
		registerEventTypes(AppEvents.FiltraListaBiblioInRicercaBiblio);
		registerEventTypes(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
		registerEventTypes(AppEvents.VisualizzaRicercaBiblioViaCodice);
		registerEventTypes(AppEvents.VisualizzaRicercaBiblioGenerica);

	}

	@Override
	public void initialize() {
		menuView = new GestioneBibliotecheMenuView(this);
		ricercaBiblioGenericaView = new RicercaBiblioGenericaView(this);
		modificaBibliotecaView = new ModificaBibliotecaView(this);
		ricercaBiblioViaCodiceView = new RicercaBiblioViaCodiceView(this);

	}

	public void handleEvent(final AppEvent event) {
		/** GESTIONE DEL MENU **/
		if (event.getType() == AppEvents.Init) {
			forwardToView(menuView, event);
		}
		
		/** GESTIONE DELLE RICERCHE GENERICHE **/
		else if ((event.getType() == AppEvents.RicercaBiblioGenerica)) { // FORM RICERCA GENERICA
			forwardToView(ricercaBiblioGenericaView, event);
		} 
		else if ((event.getType() == AppEvents.VisualizzaRicercaBiblioGenerica)) { // FORM RICERCA GENERICA, usata nel click indietro
			forwardToView(ricercaBiblioGenericaView, event);
		}
		else if ((event.getType() == AppEvents.FiltraListaBiblioInRicercaBiblio)) {	// CLIC VIA RICERCA, VEDI BIBLIO DALL'UTENTE		
			forwardToView(ricercaBiblioGenericaView, event);
			forwardToView(menuView, event);
		}
		else if ((event.getType() == AppEvents.ListaBiblioteche)) { // ESEGUE LA RICERCA SENZA PARAMETRI: chiamato in apertura dell'appl
			forwardToView(ricercaBiblioGenericaView, event);
		}
		
		
		
		/** GESTIONE DELLE RICERCHE VIA CODICE **/
		else if ((event.getType() == AppEvents.RicercaBiblioCodice)) { // FORM RICERCA VIA COD
			forwardToView(ricercaBiblioViaCodiceView, event);
		}
		else if ((event.getType() == AppEvents.VisualizzaRicercaBiblioViaCodice)) { // FORM RICERCA VIA COD, usata nel click indietro
			forwardToView(ricercaBiblioViaCodiceView, event);
		}
		else if ((event.getType() == AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice)) {  // ESEGUE LA RICERCA
			forwardToView(ricercaBiblioViaCodiceView, event);
		}
		
		/** GESTIONE CATALOGAZIONE **/
		else if ((event.getType() == AppEvents.SelectBiblio)) {
			final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
			int id_biblio =(Integer) event.getData(BiblioModel.IDBIBLIO);
			bibliotecheService.getBiblioteca(id_biblio, new AsyncCallback<BiblioModel>() {
				@Override
				public void onSuccess(BiblioModel result) {
					event.setData("biblioteca", result);
					forwardToView(modificaBibliotecaView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					if (UIAuth.checkIsLogin(caught.toString())) {/* controllo se l'errore è dovuto alla richiesta di login */
						AbiMessageBox.messageErrorAlertBox("Errore durante il caricamento dati della biblioteca", "ESITO CARICAMENTO");
					}
				}
			});

		} else if ((event.getType() == AppEvents.ReloadBiblioData)) {
			final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
			int id_biblio =(Integer) event.getData();
			bibliotecheService.getBiblioteca(id_biblio, new AsyncCallback<BiblioModel>() {
				@Override
				public void onSuccess(BiblioModel result) {
					event.setData("biblioteca", result);
					forwardToView(modificaBibliotecaView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					if (UIAuth.checkIsLogin(caught.toString())) {/* controllo se l'errore è dovuto alla richiesta di login */
						AbiMessageBox.messageErrorAlertBox("Errore durante il caricamento dati della biblioteca", "ESITO CARICAMENTO");
					}
				}
			});
		}
		
	}

}
