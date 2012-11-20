package it.inera.abi.gxt.client.auth;

import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;

import java.util.List;

/**
 * Ruoli utenti in ABI
 * 
 * I seguenti servizi sono accedibili da tutti gli utenti autenticati
 * /abi/locationService
 * /abi/auth
 * @author reschini
 *
 */
public class Roles {
	
	/** ruolo gestione utenti **/
	//@Deprecated
	//public static String ADMIN = "ADMIN";
	
	/** ruolo gestione utenti **/
	//@Deprecated
	//public static String BIBILIO = "BIBILIO";

	/** 
	 * ruolo gestione utenti 
	 * /abi/utentiService 
	 **/
	public static String GESTIONE_UTENTI = "GESTIONE UTENTI";
	
	/** 
	 * ruolo gestione utenti 
	 * /abi/bibliotecheService
	 **/
	public static String CREAZIONE = "CREAZIONE BIBLIOTECA";
	public static String CANCELLAZIONE = "CANCELLAZIONE BIBLIOTECA";
	public static String CATALOGAZIONE = "CATALOGAZIONE BIBLIOTECA";
	public static String REVISIONE = "REVISIONE BIBLIOTECA";
	public static String VEDI_TUTTE = "VEDI TUTTE"; // permette di vedere la lista di tutte le biblioteche
	
	
	/** 
	 * ruolo gestione utenti 
	 * /abi/tabelleDinamicheService 
	 **/
	public static String TABELLE_DINAMICHE = "TABELLE DINAMICHE";
	
	/** 
	 * ruolo gestione utenti 
	 **/
	public static String STATISTICHE = "STATISTICHE";
	
	/** 
	 * ruolo gestione utenti 
	 * /abi/stampe.pdf
	 * /abi/stampe.xls
	 **/
	public static String STAMPE_E_REPORT = "STAMPE E REPORT";
	
	/** 
	 * ruolo gestione utenti 
	 * /abi/formatoScambio
	 * /abi/uploadservlet
	 **/
	public static String FORMATO_DI_SCAMBIO = "FORMATO DI SCAMBIO";
	
	/**
	 * Controlla se un utente ha il ruolo specificato
	 * @param role
	 * @return boolean
	 */
	public static boolean isUserInRole(String role) {
		UtentiAuthModel utentiAuthModel = UIAuth.getUtentiAuthModel();
		List<ProfiliModel> profiliModelList = utentiAuthModel.getProfili();
		for (ProfiliModel model: profiliModelList) {
			if (role.equalsIgnoreCase(model.getDenominazione())) return true; 
		}
		return false;
	}
}
