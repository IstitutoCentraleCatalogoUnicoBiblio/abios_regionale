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

package it.inera.abi.gxt.client.costants;

/**
 * Contiene le costanti relative alle voci contenute nel Menu Utenti e limiti di lunghezza 
 * per alcuni campi (password, username, ecc...)
 *
 */
public class CostantiGestioneUtenti {
	
	public static final int PASSWORD_MIN_LENGTH = 6;
	public static final int PASSWORD_MAX_LENGTH = 12;
	
	public static final int USERNAME_MIN_LENGTH = 2;
	public static final int USERNAME_MAX_LENGTH = 20;
	
	public static final int UTENTI_GRID_LIMIT = 40;

	public static final String NUOVO_UTENTE = "Nuovo utente";
	public static final String GESTIONE_UTENTI="Lista utenti";
	public static final String ASS_LIST_BIB_LIST_UT="Assegna biblioteche";

}
