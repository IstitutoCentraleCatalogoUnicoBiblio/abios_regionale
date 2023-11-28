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

package it.inera.abi.gxt.client.mvc.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare le riproduzioni
 *
 */
public class ServiziRiproduzioniModel extends BaseModel {

	public ServiziRiproduzioniModel() {

	}

	public ServiziRiproduzioniModel(Integer idTipo,String tipoDescr, String locale, String nazionale,
			String internazionale) {
		set("idTipo",idTipo);
		set("tipoDescr", tipoDescr);
		set("locale", locale);
		set("nazionale", nazionale);
		set("internazionale", internazionale);
	}

	public void setTipoDescr(String tipoDescr) {
		set("tipoDescr", tipoDescr);
	}

	public String getTipoDescr() {
		return get("tipoDescr");
	}

	public void setLocale(String locale) {
		set("locale", locale);
	}

	public String getLocale() {
		return get("locale");
	}

	public void setNazionale(String nazionale) {
		set("nazionale", nazionale);
	}

	public String getNazionale() {
		return get("nazionale");
	}
	
	public void setInternazionale(String internazionale) {
		set("internazionale", internazionale);
	}

	public String getInternazionale() {
		return get("internazionale");
	}
	

	
	public void setIdTipo(Integer idTipo) {
		set("idTipo",idTipo);
	}
	
	public Integer getIdTipo() {
		return get("idTipo");
	}
}
