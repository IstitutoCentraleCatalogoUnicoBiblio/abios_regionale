package it.inera.abi.gxt.client.services;


import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi di autenticazione (lato client)
 * 
 */
@RemoteServiceRelativePath("auth")
public interface AuthService extends RemoteService {
	
	public void checkLogin();
	
	public UtentiAuthModel retrieveUser();
	
}
