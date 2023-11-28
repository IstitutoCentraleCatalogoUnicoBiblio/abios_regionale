/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

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
