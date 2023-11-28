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
 * Modello utilizzato per rappresentare le regioni
 *
 */
public class RegioniModel extends BaseModel implements Serializable {
	public RegioniModel() {

	}

	public RegioniModel(int id_regione, int id_stato, String denominazione,
			String sigla) {
		set("id_regione", id_regione);
		set("id_stato", id_stato);
		set("denominazione", denominazione);
		set("sigla", sigla);
	}

	public void setIdRegione(int id_regione) {
		set("id_regione", id_regione);
	}

	public void setIdStato(int id_stato) {
		set("id_stato", id_stato);
	}

	public void setSigla(String sigla) {
		set("sigla", sigla);
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	
	public void setStatoDenominazione(String statoDenominazione) {
		 set("statoDenominazione",statoDenominazione);
	}
	/**/

	public int getIdRegione() {
		return (Integer) get("id_regione");
	}

	public int getIdStato() {
		return (Integer) get("id_stato");
	}

	public String getSigla() {
		return get("sigla");
	}

	public String getDenominazione() {
		return get("denominazione");
	}
	
	public String getStatoDenominazione() {
		return get("statoDenominazione");
	}
	

}
