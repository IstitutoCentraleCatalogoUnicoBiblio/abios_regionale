package it.inera.abi.dao;

import java.io.Serializable;
/**Classe per la generazione di eccezioni per entry duplicate sul database
 * */
public class ConstraintKeyViolationException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6545493937242414630L;
	
	private String message;
	
	public ConstraintKeyViolationException(){

	}

	public ConstraintKeyViolationException(String message){
		this.message=message;
	}
	
	@Override
	public String getMessage(){
		return this.message;
	}
}
