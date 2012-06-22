package it.inera.abi.logic.auth;

import it.inera.abi.persistence.Utenti;

import org.springframework.security.core.userdetails.User;

public interface AuthLogic {

	public User retrieveLoggedUser();
	
	public Utenti retriveUtente(String username);
	
}
