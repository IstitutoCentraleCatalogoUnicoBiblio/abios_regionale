package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.models;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Classe Model per la rappresentazione dei cataloghi collettivi
 *
 */
public class CataloghiCollettiviModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = -5262312580159445672L;

	public CataloghiCollettiviModel() {

	}

	public CataloghiCollettiviModel(String denominazione,
			String zonaEspansione, String zona) {
		set("denominazione", denominazione);
		set("zonaEspansione", zonaEspansione);
		set("zona", zona);
	}

	public void setIdCatalogo(Integer idCatalogoCollettivo){
		set("idCatalogo",idCatalogoCollettivo);
	}
	
	public Integer getIdCatalogo(){
		return get("idCatalogo");
	}

	
	public String getDenominazione() {
		return get("denominazione");
	}
	public void setDenominazione(String denominazione) {
		set("denominazione",denominazione);
	}

	public void setZonaEspansione(String zonaEspansione) {
		set("zonaEspansione", zonaEspansione);
	}
	public String getZonaEspansione() {
		return get("zonaEspansione");
	}

	public void setIdZonaEspansione(Integer idZonaEspansione) {
		set("idZonaEspansione", idZonaEspansione);
	}
	public Integer getIdZonaEspansione() {
		return get("idZonaEspansione");
	}
	
	public void setZona(String zona) {
		set("zona", zona);
	}
	public String getZona() {
		return get("zona");
	}
	
	

}
