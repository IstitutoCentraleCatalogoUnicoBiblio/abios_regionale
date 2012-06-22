package it.inera.abi.dao;

import java.io.Serializable;
/**Classe per la generazione di eccezioni per entry duplicate sul database
 * */
public class DuplicateEntryException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821038989646538970L;
	
	private String message;
	public DuplicateEntryException(){

	}

	public DuplicateEntryException(String message){
		this.message=message;
	}
	
	@Override
	public String getMessage(){
		return this.message;
	}
}
