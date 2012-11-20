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
