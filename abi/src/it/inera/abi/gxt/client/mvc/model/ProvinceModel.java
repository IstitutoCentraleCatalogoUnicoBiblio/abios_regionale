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
 * Modello utilizzato per rappresentare le province
 *
 */
public class ProvinceModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = -2220794827838745903L;

	public ProvinceModel() {

	}

	public ProvinceModel(int id_provincia, int id_regione,
			String denominazione, String codice_istat, String sigla) {
		set("id_provincia", id_provincia);
		set("id_regione", id_regione);
		set("denominazione", denominazione);
		set("codice_istat", codice_istat);
		set("sigla", sigla);

	}

	public void setIdProvincia(int id_provincia) {
		set("id_provincia", id_provincia);
	}

	public void setIdRegione(int id_regione) {
		set("id_regione", id_regione);
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public void setCodiceIstat(String codice_istat) {
		set("codice_istat", codice_istat);
	}

	/**/
	public int getIdRegione() {
		return (Integer) get("id_regione");
	}

	public int getIdProvincia() {
		return (Integer) get("id_provincia");
	}

	public String getDenominazione() {
		return get("denominazione");
	}

	public String getCodiceIstat() {
		return get("codice_istat");
	}

	public void setSigla(String sigla) {
		set("sigla", sigla);

	}

	public String getSigla() {
		return get("sigla");
	}

	public void setRegioneDenominazione(String regioneDenominazione) {
		set("regioneDenominazione", regioneDenominazione);
	}
	
	public String getRegioneDenominazione() {
	return get("regioneDenominazione");
	}
	
	public void setStatoDenominazione(String statoDenominazione) {
		set("statoDenominazione", statoDenominazione);
	}
	
	public String getStatoDenominazione() {
		return get("statoDenominazione");
	}
	
	
}
