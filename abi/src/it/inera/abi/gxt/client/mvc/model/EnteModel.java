package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelComparer;
import com.extjs.gxt.ui.client.data.ModelData;

public class EnteModel extends BaseModel implements Serializable,ModelComparer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8064344644011832222L;

	public EnteModel() {

	}

	VoceUnicaModel v;
	StatoModel s;

	public EnteModel(int id_ente, String denominazione, String partitaIva,
			String asiaAsip, String codiceFiscale,
			VoceUnicaModel enteTipologiaAmministrativa, StatoModel stato) {
		set("id_ente", id_ente);
		set("denominazione", denominazione);
		set("partitaIva", partitaIva);
		set("asiaAsip", asiaAsip);
		set("codiceFiscale", codiceFiscale);
		set("enteTipologiaAmministrativa", enteTipologiaAmministrativa);
		set("stato", stato);

	}

	public void setIdEnte(int id_ente) {
		set("id_ente", id_ente);
	}

	public int getIdEnte() {
		return (Integer) get("id_ente");
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public void setPartitaIva(String partitaIva) {
		set("partitaIva", partitaIva);
	}

	public void setAsiaAsip(String asiaAsip) {
		set("asiaAsip", asiaAsip);
	}

	public void setCodiceFiscale(String codiceFiscale) {
		set("codiceFiscale", codiceFiscale);
	}

	/**/
	public String getDenominazione() {
		return get("denominazione");
	}

	public String getPartitaIva() {
		return get("partitaIva");
	}

	public String getAsiaAsip() {
		return get("asiaAsip");
	}

	public String getCodiceFiscale() {
		return get("codiceFiscale");
	}

	public void setEnteTipologiaAmministrativa(
			VoceUnicaModel enteTipologiaAmministrativa) {
		set("enteTipologiaAmministrativa", enteTipologiaAmministrativa);
	}

	public void setStato(StatoModel stato) {
		set("stato", stato);
	}

	public StatoModel getStato() {
		return get("stato");
	}

	public VoceUnicaModel getEnteTipologiaAmministrativa() {
		return get("enteTipologiaAmministrativa");
	}
//	//EnteModel ente1,
//	public boolean equals( EnteModel ente2) {
//		if (this.getIdEnte() == ente2.getIdEnte())
//			return true;
//		return false;
//	}

	@Override
	public boolean equals(ModelData m1, ModelData m2) {
		 EnteModel ente1=(EnteModel)m1;
		 EnteModel ente2=(EnteModel)m2;
		 if (ente1.getIdEnte() == ente2.getIdEnte())
				return true;
			return false;
		
	}

}
