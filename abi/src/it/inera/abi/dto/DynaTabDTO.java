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
 * Classe DTO che rappresenta una generica Tabella Dinamica
 *
 */
public class DynaTabDTO implements Serializable {
	
	private static final long serialVersionUID = -9170184921370355285L;
	
	private int idTabella; 
	
	/**
	 * identificano il record
	 */
	private int id;
	private String value;
	
	public DynaTabDTO() {
		
	}
	
	public DynaTabDTO(int idTabella, int id, String value) {
		this.idTabella = idTabella;
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public int getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(int idTabella) {
		this.idTabella = idTabella;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynaTabDTO [idTabella=");
		builder.append(idTabella);
		builder.append(", id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
}
