package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class StatoModel extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9042969293269705024L;

	public StatoModel() {
	
	}

	public StatoModel(int id_stato, String denominazione, String sigla) {
		set("id_stato", id_stato);
		set("denominazione", denominazione);
		set("sigla", sigla);
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

	/**/


	public int getIdStato() {
		return (Integer) get("id_stato");
	}

	public String getSigla() {
		return get("sigla");
	}

	public String getDenominazione() {
		return get("denominazione");
	}
	
}
