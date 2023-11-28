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
 * Modello utilizzato per rappresentare i regolamenti associati alla biblioteca
 *
 */
public class RegolamentoModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 2155928509043707435L;

	public RegolamentoModel() {
		
	}

	public RegolamentoModel(int id_regolamento, int id_biblioteca, String riferimentoNormativa, String url) {
		set("id_regolamento", id_regolamento);
		set("id_biblitoeca", id_biblioteca);
		set("riferimentoNormativa", riferimentoNormativa);
		set("url", url);

	}

	public int getIdRegolamento() {
		return (Integer) get("id_regolamento");
	}

	public void setIdRegolamento(int id_regolamento) {
		set("id_regolamento", id_regolamento);
	}

	public String getRiferimentoNormativa() {
		return get("riferimentoNormativa");
	}

	public void setRiferimentoNormativa(String riferimentoNormativa) {
		set("riferimentoNormativa", riferimentoNormativa);
	}

	public String getUrl() {
		return get("url");
	}

	public void setUrl(String url) {
		set("url", url);
	}

	public int getIdBiblioteca() {
		return (Integer) get("id_biblitoeca");
	}

	public void setIdBiblioteca(int id_biblitoeca) {
		set("id_biblitoeca", id_biblitoeca);
	}

}
