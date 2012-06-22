package it.inera.abi.gxt.client.mvc.controller;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.RicercaBiblioGenericaView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.AssegnaBiblioUtentiView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.ListaUtentiView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.NuovoUtenteView;
import it.inera.abi.gxt.client.mvc.view.menu.GestioneUtentiMenuView;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GestioneUtentiController extends Controller {

	private GestioneUtentiMenuView menuView;

	private ListaUtentiView listaUtentiView;

	private NuovoUtenteView nuovoUtenteView;
	private AssegnaBiblioUtentiView assegnaBiblioUtentiView;

	public GestioneUtentiController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.NuovoUtente);
		registerEventTypes(AppEvents.ListaUtenti);
		registerEventTypes(AppEvents.FiltraListaUtentiInRicercaUtente);
		registerEventTypes(AppEvents.FiltraListaUtentiInAssegnazioneUtentiBiblio);
		registerEventTypes(AppEvents.AssegnaListeAUtenti);
		registerEventTypes(AppEvents.ModificaUtente);
		registerEventTypes(AppEvents.IndietroDaModificaModificaUtente);
		registerEventTypes(AppEvents.AssegnaBiblioAUtenti);
		registerEventTypes(AppEvents.FiltraListaBiblioInAssegnazioneUtentiBiblio);
		registerEventTypes(AppEvents.VisualizzaListaUtenti);
		registerEventTypes(AppEvents.VisualizzaListaUtentiInModifica);
		registerEventTypes(AppEvents.RicercaListaBiblioInAssegna);
		registerEventTypes(AppEvents.RicercaListaBiblioInAssegnaIndietro);
		registerEventTypes(AppEvents.AssegnaBibliotecheAUtente);
		registerEventTypes(AppEvents.RicercaBiblioGenericaViaUtentiIndietro);
		
	}

	@Override
	public void initialize() {
		menuView = new GestioneUtentiMenuView(this);
		nuovoUtenteView = new NuovoUtenteView(this);
		listaUtentiView = new ListaUtentiView(this);
		assegnaBiblioUtentiView = new AssegnaBiblioUtentiView(this);
	}

	@Override
	public void handleEvent(final AppEvent event) {

		EventType type = event.getType();
		if (type == AppEvents.Init) {
			forwardToView(menuView, event);
		} else if (type == AppEvents.NuovoUtente) {

			UtentiServiceAsync utentiService = (UtentiServiceAsync) Registry.get("utentiService");

			utentiService.getListProfiles(new AsyncCallback<List<ProfiliUtente>>() {

				@Override
				public void onSuccess(List<ProfiliUtente> result) {
					event.setData("profili",result);
					forwardToView(nuovoUtenteView, event);
				}

				@Override
				public void onFailure(Throwable caught) {
					if (UIAuth.checkIsLogin(caught.toString())) {/* controllo se l'errore è dovuto alla richiesta di login */
						AbiMessageBox.messageErrorAlertBox("Impossibilie caricare i profili dal database!", "ESITO CARICAMENTO");
					}
				}
			});

		} else if (type == AppEvents.ListaUtenti) {
			if (event.getData("tree") != null) {
				forwardToView(listaUtentiView, event);
			}
			else {
				if (event.getData("test") != null) 
					forwardToView(listaUtentiView, event);
				else
					forwardToView(menuView, event);
				
			}

		} else if (type == AppEvents.FiltraListaUtentiInRicercaUtente) {
			forwardToView(listaUtentiView, event);

		} 
		else if (type == AppEvents.FiltraListaBiblioInAssegnazioneUtentiBiblio) {
			forwardToView(assegnaBiblioUtentiView, event);
		
		} else if (type == AppEvents.AssegnaListeAUtenti) {
			forwardToView(assegnaBiblioUtentiView, event);
		} else if (type == AppEvents.ModificaUtente) {
			int id_utenti =(Integer) event.getData("id_utenti");
			UtentiServiceAsync utentiService = (UtentiServiceAsync) Registry.get("utentiService");
			utentiService.getDataUtente(id_utenti, new AsyncCallback<UtenteCompletoFormModel>() {
				@Override
				public void onSuccess(UtenteCompletoFormModel result) {
					event.setData("datiUtente", result);
					forwardToView(listaUtentiView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					if (UIAuth.checkIsLogin(caught.toString())) {/* controllo se l'errore è dovuto alla richiesta di login */
						AbiMessageBox.messageErrorAlertBox("Impossibile caricare i dati dell'utente!", "ESITO CARICAMENTO");				
					}
				}
			});
			
			
		} else if (type == AppEvents.IndietroDaModificaModificaUtente) {
			forwardToView(listaUtentiView, event);
		}
		else if(type==AppEvents.AssegnaBibliotecheAUtente || type==AppEvents.RicercaBiblioGenericaViaUtentiIndietro){
			forwardToView(listaUtentiView, event);
		}
		else if (type == AppEvents.AssegnaBiblioAUtenti) {
			forwardToView(assegnaBiblioUtentiView, event);
		}
		else if (type == AppEvents.VisualizzaListaUtenti) {
			forwardToView(assegnaBiblioUtentiView, event);
		}
		else if (type == AppEvents.VisualizzaListaUtentiInModifica) {
			forwardToView(assegnaBiblioUtentiView, event);
		}
		else if (type == AppEvents.RicercaListaBiblioInAssegna || type == AppEvents.RicercaListaBiblioInAssegnaIndietro) {
			forwardToView(assegnaBiblioUtentiView, event);
		}
	}
}
