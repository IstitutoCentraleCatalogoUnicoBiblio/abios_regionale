package it.inera.abi.dao;

import java.io.Serializable;

/**
 * Classe per la generazione di eccezioni per entry non trovata
 *
 */
public class EntryNotFoundException extends Exception implements Serializable{

	private static final long serialVersionUID = 4821038989646538970L;
	
	private String message;
	public EntryNotFoundException() {

	}

	public EntryNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
