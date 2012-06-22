package it.inera.abi.gxt.client.services;

import java.io.Serializable;

public class DuplicatedEntryClientSideException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4530580888727894297L;
	
	private String message;
	public DuplicatedEntryClientSideException(){

	}
	public DuplicatedEntryClientSideException(String message){
		this.message=message;
	}

	
	@Override
	public String getMessage(){
		return this.message;
	}
}
