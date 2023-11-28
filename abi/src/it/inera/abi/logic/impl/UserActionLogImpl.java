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

package it.inera.abi.logic.impl;

import it.inera.abi.logic.UserActionLog;
import it.inera.abi.logic.auth.AuthLogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementazione per il log delle operazioni eseguite dall'utente di backend
 *
 */
@Repository
public class UserActionLogImpl implements UserActionLog {
	
	private final static String USERACTIONLOG = "useractionlogger";
	
	private static Log _log = LogFactory.getLog(USERACTIONLOG);

	@Autowired protected AuthLogic authLogic;
	
	@Override
	public void logAction(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	
	}
	
	@Override
	public void logActionCatalogazione(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("[CATALOGAZIONE BIBLIO] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	
	}
 
	@Override
	public void logActionCatalogazioneBiblioDefaultUser(String actionDescription) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		logActionCatalogazione(username, actionDescription);
	}
	
	@Override
	public void logActionStatoBiblioteca(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("[STATO BIBLIO] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	
	}
	
	@Override
	public void logActionStatoBibliotecaDefaultUser(String actionDescription) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		logActionStatoBiblioteca(username, actionDescription);
	}
	@Override
	public void logActionStatoBibliotecaDefaultUser(String actionDescription, String username) {
		logActionStatoBiblioteca(username, actionDescription);
	}
	
	@Override
	public void logActionNuovaBiblioteca(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("[NUOVE BIBLIO] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	
	}
	
	@Override
	public void logActionNuovaBibliotecaDefaultUser(String actionDescription) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		logActionNuovaBiblioteca(username, actionDescription);
	}
	
	@Override
	public void logActionTabelleDinamiche(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("[TABELLE DINAMICHE] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	
	}
	
	@Override
	public void logActionTabelleDinamicheDefaultUser(String actionDescription) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		logActionTabelleDinamiche(username, actionDescription);
	}

	@Override
	public void logActionUtentiLogic(String user, String actionDescription) {
		StringBuffer message = new StringBuffer();
		message.append("user:[");
		message.append(user);
		message.append("] ");
		message.append("[UTENTI] ");
		message.append("azione:[");
		message.append(actionDescription);
		message.append("] ");
		_log.debug(message.toString());
	}

	@Override
	public void logActionUtentiLogicDefaultUser(String actionDescription) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		logActionTabelleDinamiche(username, actionDescription);		
	}
	
}
