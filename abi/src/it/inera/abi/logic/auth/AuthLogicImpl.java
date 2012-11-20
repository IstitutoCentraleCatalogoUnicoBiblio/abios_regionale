package it.inera.abi.logic.auth;


import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Utenti;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Classe contenente le implementazioni delle operazioni di logica per caricare 
 * le informazioni dell'utente loggato
 *
 */
@Service
public class AuthLogicImpl implements AuthLogic {

	protected @Autowired UtentiDao utentiDao;
	
	protected Log _log = LogFactory.getLog(AuthLogicImpl.class);
	
	@Override
	public User retrieveLoggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		if (authentication == null) {
			if (_log.isFatalEnabled()) _log.fatal("Not logged in");
			return null;
		} else {
			User user = (User) authentication.getPrincipal();
			return user; 
		}
	}

	@Override
	public Utenti retrieveUtente(String username) {
		Utenti utenti = utentiDao.findByName(username);
		return utenti;
	}

}
