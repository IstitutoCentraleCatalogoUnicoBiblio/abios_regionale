package it.inera.abi.dto;

import java.io.Serializable;

/**
 * Classe DTO che rappresenta il Sistema del Prestito Interbibliotecario  
 *
 */
public class SistemaPrestitoInterbibliotecarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1911083792779703877L;
	private int id;
	private String descrizione;
	private String url;
	
	public SistemaPrestitoInterbibliotecarioDTO() {
		
	}
	
	/**
	 * @param id
	 * @param descrizione
	 * @param url
	 */	
	public SistemaPrestitoInterbibliotecarioDTO(int id, String descrizione, String url) {
		this.id = id;
		this.descrizione = descrizione;
		this.url = url;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return descrizione
	 */
	public String getDescrizione() {
		return this.descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return url
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
