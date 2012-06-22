package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class ProvinceModel extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2220794827838745903L;

	public ProvinceModel() {
		// TODO Auto-generated constructor stub
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
