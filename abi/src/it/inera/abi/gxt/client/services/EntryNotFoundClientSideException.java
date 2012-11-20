package it.inera.abi.gxt.client.services;

import java.io.Serializable;

/**
 * Eccezione custom per istanza non trovata (lato client)
 *
 */
public class EntryNotFoundClientSideException extends Exception implements Serializable{

	private static final long serialVersionUID = -4530580888727894297L;
	
	private String message;
	
	public EntryNotFoundClientSideException() {

	}
	
	public EntryNotFoundClientSideException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
