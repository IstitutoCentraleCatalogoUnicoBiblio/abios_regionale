package it.inera.abi.logic.auth;

import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Utenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe contenente le operazioni di logica per il caricamento dei dati
 * di un utente tramite lo username
 *
 */
@Service("userDetailsLogic") 
public class UserDetailsLogicImpl implements UserDetailsService {

	@Autowired private UtentiDao utentiDao;
	@Autowired private Assembler assembler;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Utenti user = utentiDao.findByName(username);
		if (user == null)
			throw new UsernameNotFoundException("user not found");

		return assembler.buildUserFromUserEntity(user);
	}
}
