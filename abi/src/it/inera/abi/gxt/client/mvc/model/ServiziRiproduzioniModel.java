package it.inera.abi.gxt.client.mvc.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare le riproduzioni
 *
 */
public class ServiziRiproduzioniModel extends BaseModel {

	public ServiziRiproduzioniModel() {

	}

	public ServiziRiproduzioniModel(Integer idTipo,String tipoDescr, String locale, String nazionale,
			String internazionale) {
		set("idTipo",idTipo);
		set("tipoDescr", tipoDescr);
		set("locale", locale);
		set("nazionale", nazionale);
		set("internazionale", internazionale);
	}

	public void setTipoDescr(String tipoDescr) {
		set("tipoDescr", tipoDescr);
	}

	public String getTipoDescr() {
		return get("tipoDescr");
	}

	public void setLocale(String locale) {
		set("locale", locale);
	}

	public String getLocale() {
		return get("locale");
	}

	public void setNazionale(String nazionale) {
		set("nazionale", nazionale);
	}

	public String getNazionale() {
		return get("nazionale");
	}
	
	public void setInternazionale(String internazionale) {
		set("internazionale", internazionale);
	}

	public String getInternazionale() {
		return get("internazionale");
	}
	

	
	public void setIdTipo(Integer idTipo) {
		set("idTipo",idTipo);
	}
	
	public Integer getIdTipo() {
		return get("idTipo");
	}
}
