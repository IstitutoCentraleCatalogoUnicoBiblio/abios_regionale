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

package it.inera.abi.commons;

import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.List;


/**
 * Ruoli utenti in ABI
 * 
 * I seguenti servizi sono accedibili da tutti gli utenti autenticati
 * /abi/locationService
 * /abi/auth
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
	 * @param role Ruolo da controllare
	 * @return Indica se l'utente ha il ruolo o meno
	 */
	public static boolean isUserInRole(String role, Utenti utente) {
		
		List<Profili> profiliList = utente.getProfilis();
		if (profiliList == null) return false;
		for (Profili profilo: profiliList) {
			if (role.equalsIgnoreCase(profilo.getDenominazione())) return true; 
		}
		return false;
	}
}
