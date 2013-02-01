package it.inera.abi.dto;

import java.io.Serializable;

/**
 * Classe DTO che rappresenta il Dewey
 *
 */
public class DeweyDTO implements Serializable{
	
	private static final long serialVersionUID = -5176707244119869249L;
	private String codiceDewey;
	private String descrizioneUfficiale;
	private String descrizioneLibera;
	
	public DeweyDTO() {
		
	}
	
	/**
	 * @param codiceDewey
	 * @param descrizioneUfficiale
	 * @param descrizioneLibera
	 */
	public DeweyDTO(String codiceDewey, String descrizioneUfficiale,
			String descrizioneLibera) {
		this.codiceDewey = codiceDewey;
		this.descrizioneUfficiale = descrizioneUfficiale;
		this.descrizioneLibera = descrizioneLibera;
	}
	
	/**
	 * @return the codiceDewey
	 */
	public String getCodiceDewey() {
		return codiceDewey;
	}
	
	/**
	 * @param codiceDewey the codiceDewey to set
	 */
	public void setCodiceDewey(String codiceDewey) {
		this.codiceDewey = codiceDewey;
	}
	
	/**
	 * @return the descrizioneUfficiale
	 */
	public String getDescrizioneUfficiale() {
		return descrizioneUfficiale;
	}
	
	/**
	 * @param descrizioneUfficiale the descrizioneUfficiale to set
	 */
	public void setDescrizioneUfficiale(String descrizioneUfficiale) {
		this.descrizioneUfficiale = descrizioneUfficiale;
	}

	/**
	 * @return the descrizioneLibera
	 */
	public String getDescrizioneLibera() {
		return descrizioneLibera;
	}
	
	/**
	 * @param descrizioneLibera the descrizioneLibera to set
	 */
	public void setDescrizioneLibera(String descrizioneLibera) {
		this.descrizioneLibera = descrizioneLibera;
	}

}
