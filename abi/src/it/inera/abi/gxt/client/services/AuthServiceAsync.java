package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>AuthService</code>
 * 
 */
public interface AuthServiceAsync {
	
	void retrieveUser(AsyncCallback<UtentiAuthModel> callback);

	void checkLogin(AsyncCallback<Void> callback);
	
}
