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

package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.models;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Classe Model per la rappresentazione dei cataloghi collettivi
 *
 */
public class CataloghiCollettiviModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = -5262312580159445672L;

	public CataloghiCollettiviModel() {

	}

	public CataloghiCollettiviModel(String denominazione,
			String zonaEspansione, String zona) {
		set("denominazione", denominazione);
		set("zonaEspansione", zonaEspansione);
		set("zona", zona);
	}

	public void setIdCatalogo(Integer idCatalogoCollettivo){
		set("idCatalogo",idCatalogoCollettivo);
	}
	
	public Integer getIdCatalogo(){
		return get("idCatalogo");
	}

	
	public String getDenominazione() {
		return get("denominazione");
	}
	public void setDenominazione(String denominazione) {
		set("denominazione",denominazione);
	}

	public void setZonaEspansione(String zonaEspansione) {
		set("zonaEspansione", zonaEspansione);
	}
	public String getZonaEspansione() {
		return get("zonaEspansione");
	}

	public void setIdZonaEspansione(Integer idZonaEspansione) {
		set("idZonaEspansione", idZonaEspansione);
	}
	public Integer getIdZonaEspansione() {
		return get("idZonaEspansione");
	}
	
	public void setZona(String zona) {
		set("zona", zona);
	}
	public String getZona() {
		return get("zona");
	}
	
	

}
