package it.inera.abi.logic.auth;

import it.inera.abi.persistence.Utenti;

import org.springframework.security.core.userdetails.User;

/**
 * Classe contenente le operazioni di logica per caricare le informazioni 
 * dell'utente loggato
 *
 */
public interface AuthLogic {

	public User retrieveLoggedUser();
	
	public Utenti retrieveUtente(String username);
	
}
