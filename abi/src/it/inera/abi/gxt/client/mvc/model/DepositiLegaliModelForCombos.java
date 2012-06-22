package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

public class DepositiLegaliModelForCombos extends DepositiLegaliModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7743077519290655077L;


	public DepositiLegaliModelForCombos() {
		super();
	}



	public void setVoceCombo(VoceUnicaModel voce_combo) {
		set("voce_combo", voce_combo);
	}

	public VoceUnicaModel getVoceCombo() {
		return get("voce_combo");
	}

}
