package it.inera.abi.gxt.client.auth;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.services.AuthServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la gestione dell'autenticazione da parte degli utenti e per
 * abilitare / oscurare i menu dell'applicativo ABI in base ai ruoli attivi
 *
 */
public class UIAuth {
	
	public static UtentiAuthModel getUtentiAuthModel() {
		UtentiAuthModel utentiAuthModel = (UtentiAuthModel) Registry.get(Abi.USERLOGGED);
		return utentiAuthModel;
	}
	
	/**
	 * Analizza una risposta RPC e decide se c'e' l'utente è ancora loggato.
	 * Se non lo è forza il logout sulla URL
	 * @param response
	 */
	public static boolean checkIsLogin(String response) {
		if (response != null && response.indexOf("j_username") != -1) {
			//Window.Location.assign(GWT.getHostPageBaseURL() + "j_spring_security_logout");
			Window.Location.assign(GWT.getHostPageBaseURL() + "login.jsp?timeout=1");
			return false;
		}
		return true;
	}
	
	public static void checkIsLogin() {
		 AuthServiceAsync authService = Registry.get(Abi.AUTHSERVICE);
		 authService.checkLogin(new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				// do nothing...
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.Location.assign(GWT.getHostPageBaseURL() + "login.jsp?timeout=1");
			}
		});
	}
	
	
	public static boolean isMenuBibliotecaEnable() {
		return (Roles.isUserInRole(Roles.CATALOGAZIONE) 
				|| Roles.isUserInRole(Roles.REVISIONE) 
				|| Roles.isUserInRole(Roles.CREAZIONE) 
				|| Roles.isUserInRole(Roles.CANCELLAZIONE)
				/*Agginto ruolo formato di scambio per gestione checkbox importate
				 * in ricerca generica*/
//				|| Roles.isUserInRole(Roles.FORMATO_DI_SCAMBIO)
				);
	}
	public static boolean isMenuUtentiEnable() {
		return (Roles.isUserInRole(Roles.GESTIONE_UTENTI));
	}
	public static boolean isMenuTabelleDinamicheEnable() {
		return (Roles.isUserInRole(Roles.TABELLE_DINAMICHE));
	}
	public static boolean isMenuStatisticheEnable() {
		return (Roles.isUserInRole(Roles.STATISTICHE));
	}
	public static boolean isMenuStampeReportEnable() {
		return (Roles.isUserInRole(Roles.STAMPE_E_REPORT));
	}
	public static boolean isMenuFormatoScambioEnable() {
		return (Roles.isUserInRole(Roles.FORMATO_DI_SCAMBIO));
	}
	
	/**
	 *  Dopo la init esegue il dispatch in base al ruolo dell'utente loggato
	 */
	public static void dispatchByRoles() {
		if (isMenuBibliotecaEnable()) {
			Dispatcher.forwardEvent(AppEvents.ListaBiblioteche);
			return;
		}
		if (isMenuUtentiEnable()) {
			Dispatcher.forwardEvent(AppEvents.ListaUtenti);
			return;
		}
		if (isMenuTabelleDinamicheEnable()) {
			Dispatcher.forwardEvent(AppEvents.MAPPING_TABELLE_DINAMICHE);
			return;
		}
		if (isMenuStatisticheEnable()) {
			Dispatcher.forwardEvent(AppEvents.NavStat);
			return;
		}
		if (isMenuStampeReportEnable()) {
			Dispatcher.forwardEvent(AppEvents.RicercaBiblioCreazioneReport);
			return;
		}
		if (isMenuFormatoScambioEnable()) {
			Dispatcher.forwardEvent(AppEvents.UploadFileDiScambio);
			return;
		}
	}
	
	
}
