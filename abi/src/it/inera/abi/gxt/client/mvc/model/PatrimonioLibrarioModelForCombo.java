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

/**
 * Modello utilizzato in combobox per rappresentare il patrimonio librario
 *
 */
public class PatrimonioLibrarioModelForCombo extends PatrimonioLibrarioModel implements
Serializable {

	private static final long serialVersionUID = 4112933911080102308L;

	public PatrimonioLibrarioModelForCombo() {

	}

	public PatrimonioLibrarioModelForCombo(int id_tipologia,String tipologia, int quantita) {
		super(id_tipologia, tipologia, quantita);
	}

	public PatrimonioLibrarioModelForCombo(VoceUnicaModel voceCombo, int id_tipologia,String tipologia, int quantita) {
		super(id_tipologia, tipologia, quantita);
		set("voceCombo", voceCombo);
	}
	public void setVoceCombo(VoceUnicaModel voceCombo) {
		set("voceCombo", voceCombo);
	}

	public VoceUnicaModel getVoceCombo() {
		return get("voceCombo");
	}
}
