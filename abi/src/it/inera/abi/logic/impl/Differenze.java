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

package it.inera.abi.logic.impl;

import java.util.Vector;

/**
 * Classe per la gestione delle differenze tra una biblioteca 
 * ed una sua precedente versione 
 *
 */
public class Differenze {
	
	private String property;
	private Vector<String> oldNew = null;
	
	public Differenze() {
	}
	
	public Differenze(String property) {
		this.property = property;
	}
	
	public void put(Vector<String> oldNew) {
		this.oldNew = oldNew;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(property);
		sb.append("=Definitivo:[");
		sb.append(oldNew.get(0));
		sb.append("]");
		sb.append(" Modifica:[");
		sb.append(oldNew.get(1));
		sb.append("]");
		return sb.toString();
	}
	
	public String toFormatString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<b>");
		sb.append(property);
		sb.append("</b></br>");
		sb.append("Definitivo:[");
		sb.append(oldNew.get(0));
		sb.append("]</br>");
		sb.append("Modifica:[");
		sb.append(oldNew.get(1));
		sb.append("]</br></br></br>");
		return sb.toString();
	}
}