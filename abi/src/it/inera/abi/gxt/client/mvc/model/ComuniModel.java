package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per i dati di un singolo comune
 *
 */
public class ComuniModel extends BaseModel implements Serializable {

	public ComuniModel() {
	}

	public ComuniModel(int id_comune, int id_provincia, String denominazione,
			String codice_istat, String codice_finanze) {
		set("id_comune", id_comune);
		set("id_provincia", id_provincia);
		set("denominazione", denominazione);
		set("codice_istat", codice_istat);
		set("codice_finanze", codice_finanze);
	}

	public void setIdComune(int id_comune) {
		set("id_comune", id_comune);
	}

	public void setIdProvincia(int id_provincia) {
		set("id_provincia", id_provincia);
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public void setCodiceIstat(String codice_istat) {
		set("codice_istat", codice_istat);
	}

	public void setCodiceFinanze(String codice_finanze) {
		set("codice_finanze", codice_finanze);
	}
	
	public void setDenominazioneProvincia(String denominazioneProvincia) {
		set("denominazioneProvincia",denominazioneProvincia);
	}

	public int getIdComune() {
		return (Integer) get("id_comune");
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

	public String getCodiceFinanze() {
		return get("codice_finanze");
	}
	 
	public String getDenominazioneProvincia() {
		return get("denominazioneProvincia");
	}
	
}
