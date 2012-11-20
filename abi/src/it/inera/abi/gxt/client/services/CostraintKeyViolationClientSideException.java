package it.inera.abi.gxt.client.services;

import java.io.Serializable;

/**
 * Eccezione custom per violazione di vincoli su chiave (lato client)
 *
 */
public class CostraintKeyViolationClientSideException extends Exception implements Serializable{

	private static final long serialVersionUID = -4530580888727894297L;
	
	private String message;

	public CostraintKeyViolationClientSideException() {

	}
	
	public CostraintKeyViolationClientSideException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}