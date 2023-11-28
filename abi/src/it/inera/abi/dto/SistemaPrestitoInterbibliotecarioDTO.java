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
