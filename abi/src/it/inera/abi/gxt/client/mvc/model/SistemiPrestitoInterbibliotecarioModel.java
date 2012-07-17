package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class SistemiPrestitoInterbibliotecarioModel extends BaseModel implements Serializable {
	
	public SistemiPrestitoInterbibliotecarioModel() {
		
	}
	
	public SistemiPrestitoInterbibliotecarioModel(int id_sistemi_prestito_interbibliotecario, String descrizione, String url) {
		set("id_sistemi_prestito_interbibliotecario", id_sistemi_prestito_interbibliotecario);
		set("descrizione", descrizione);
		set("url", url);
	}

	public void setIdSistemiPrestitoInterbibliotecario(int id_sistemi_prestito_interbibliotecario) {
		set("id_sistemi_prestito_interbibliotecario", id_sistemi_prestito_interbibliotecario);
	}
	
	public void setDescrizione(String descrizione) {
		set("descrizione", descrizione);
	}
	
	public void setUrl(String url) {
		set("url", url);
	}
	
	public int getIdSistemiPrestitoInterbibliotecario() {
		return (Integer) get("id_sistemi_prestito_interbibliotecario");
	}
	
	public String getDescrizione() {
		return get("descrizione");
	}
	
	public String getUrl() {
		return get("url");
	}
}
