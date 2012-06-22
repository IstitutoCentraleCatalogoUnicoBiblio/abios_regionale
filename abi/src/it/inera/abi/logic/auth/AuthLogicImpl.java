package it.inera.abi.logic.auth;

import java.util.List;
import java.util.Set;

import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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
	public Utenti retriveUtente(String username) {
		Utenti utenti = utentiDao.findByName(username);
		return utenti;
	}

}
