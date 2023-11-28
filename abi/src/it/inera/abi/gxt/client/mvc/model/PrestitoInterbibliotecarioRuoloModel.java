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

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare i ruoli all'interno del
 * prestito interbibliotecario
 *
 */
public class PrestitoInterbibliotecarioRuoloModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 968832597251449447L;
	
	public PrestitoInterbibliotecarioRuoloModel() {
	
	}
	
	public void setNazionale(String nazionale) {
		set("nazionale",nazionale);
	}
	public String getNazionale() {
		return get("nazionale");
	}

	public void setInternazionale(String internazionale) {
		set("internazionale",internazionale);
	}
	public String getInternazionale() {
		return get("internazionale");
	}

	public void setIdRuolo(Integer idRuolo) {
		set("idRuolo",idRuolo);	
	}

	public Integer getIdRuolo() {
		return get("idRuolo");	
	}

	public void setRuoloDescr(String ruoloDescr) {
		set("ruoloDescr",ruoloDescr);	
	}

	public String getRuoloDescr() {
		return get("ruoloDescr");	
	}

	public Integer getIdPrestitoInterbibliotecario() {
		return get("idPrestitoPrestitoInterbibliotecario");
	}
	
	public void setIdPrestitoInterbibliotecario(Integer idPrestitoPrestitoInterbibliotecario) {
		set("idPrestitoPrestitoInterbibliotecario",idPrestitoPrestitoInterbibliotecario);
	}
	
	
}
