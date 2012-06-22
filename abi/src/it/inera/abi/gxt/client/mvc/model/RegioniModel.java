package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

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
