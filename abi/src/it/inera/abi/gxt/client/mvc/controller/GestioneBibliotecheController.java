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
