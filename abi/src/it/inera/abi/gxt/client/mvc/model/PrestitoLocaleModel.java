package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare il prestito locale
 *
 */
public class PrestitoLocaleModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 968832597251449447L;
	
	public PrestitoLocaleModel() {
	
	}
	
	public void setProcedureAuto(String procedureAuto) {
		set("procedureAuto",procedureAuto);
	}
	public String getProcedureAuto() {
		return get("procedureAuto");
	}
	
	public void setDurataGiorni(Integer durataGiorni) {
	set("durataGiorni",durataGiorni);	
	}
	
	public Integer getDurataGiorni() {
		return get("durataGiorni");	
	}
	
	public void setMaterialeEsclusoButton() {
		set("materialeEscluso","Materiale escluso");
	}
	
	public String getMaterialeEscluso() {
		return get("materialeEscluso");
	}
	
	public void setUtentiAmmessiButton() {
		set("utentiAmmessi","Utenti ammessi");
	}
	
	public String getUtentiAmmessiButton() {
		return get("utentiAmmessi");
	}

	public Integer getIdPrestitoLocale() {
		return get("idPrestitoLocale");
	}
	
	public void setIdPrestitoLocale(Integer idPrestitoLocale) {
		set("idPrestitoLocale",idPrestitoLocale);
	}
	
}
