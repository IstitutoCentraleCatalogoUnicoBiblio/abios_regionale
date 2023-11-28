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

package it.inera.abi.logic.formatodiscambio.exports;

import it.inera.abi.logic.formatodiscambio.imports.InfoBiblioBean;

import java.util.Vector;

/**
 * Classe che rappresenta l'export: nome del file, login utente, biblioteche esportate
 *
 */
public class ExportBean {
	
	public String fileName;
	public String data;
	public String ora;
	public int nBib;
	public String email;
	public String username;
	public Vector<InfoBiblioBean> biblioteche = new Vector<InfoBiblioBean>();

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExportBean [fileName=");
		builder.append(fileName);
		builder.append(", data=");
		builder.append(data);
		builder.append(", ora=");
		builder.append(ora);
		builder.append(", nBib=");
		builder.append(nBib);
		builder.append(", email=");
		builder.append(email);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	
	
}
