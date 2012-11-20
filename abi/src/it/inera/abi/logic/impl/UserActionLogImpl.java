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
