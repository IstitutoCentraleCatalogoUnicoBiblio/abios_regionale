package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare i ruoli all'interno del
 * prestito interbibliotecario
 *
 */
public class PrestitoInterbibliotecarioRuoloModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 968832597251449447L;
	
	public PrestitoInterbibliotecarioRuoloModel() {
	
	}
	
	public void setNazionale(String nazionale) {
		set("nazionale",nazionale);
	}
	public String getNazionale() {
		return get("nazionale");
	}

	public void setInternazionale(String internazionale) {
		set("internazionale",internazionale);
	}
	public String getInternazionale() {
		return get("internazionale");
	}

	public void setIdRuolo(Integer idRuolo) {
		set("idRuolo",idRuolo);	
	}

	public Integer getIdRuolo() {
		return get("idRuolo");	
	}

	public void setRuoloDescr(String ruoloDescr) {
		set("ruoloDescr",ruoloDescr);	
	}

	public String getRuoloDescr() {
		return get("ruoloDescr");	
	}

	public Integer getIdPrestitoInterbibliotecario() {
		return get("idPrestitoPrestitoInterbibliotecario");
	}
	
	public void setIdPrestitoInterbibliotecario(Integer idPrestitoPrestitoInterbibliotecario) {
		set("idPrestitoPrestitoInterbibliotecario",idPrestitoPrestitoInterbibliotecario);
	}
	
	
}
