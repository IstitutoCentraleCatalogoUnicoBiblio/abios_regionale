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

package it.inera.abi.logic;

/**
 * Interfaccia per il log delle operazioni eseguite dall'utente di backend
 *
 */
public interface UserActionLog {

	public abstract void logAction(String user, String actionDescription);

	public abstract void logActionCatalogazione(String user, String actionDescription);
	public abstract void logActionCatalogazioneBiblioDefaultUser(String actionDescription);

	public abstract	void logActionStatoBiblioteca(String user, String actionDescription);
	public abstract void logActionStatoBibliotecaDefaultUser(String actionDescription);
	public abstract void logActionStatoBibliotecaDefaultUser(String actionDescription, String username);
	
	public abstract	void logActionNuovaBiblioteca(String user, String actionDescription);
	public abstract void logActionNuovaBibliotecaDefaultUser(String actionDescription);
	
	public abstract	void logActionTabelleDinamiche(String user, String actionDescription);
	public abstract void logActionTabelleDinamicheDefaultUser(String actionDescription);
	
	public abstract	void logActionUtentiLogic(String user, String actionDescription);
	public abstract void logActionUtentiLogicDefaultUser(String actionDescription);

}