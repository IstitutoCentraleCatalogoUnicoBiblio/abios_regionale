package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per i dati relativi ad un singolo fondo speciale
 *
 */
public class FondiSpecialiModel extends BaseModel  implements Serializable{

	private static final long serialVersionUID = 2894339042127124027L;

	public FondiSpecialiModel() {

	}

	public FondiSpecialiModel(int idFondoSpeciale,String denominazione, String descrizione) {
		set("idFondoSpeciale",idFondoSpeciale);
		set("denominazione", denominazione);
		set("descrizione", descrizione);
	}
	public FondiSpecialiModel(int idFondoSpeciale,String denominazione, String descrizione,
			String fondoDepositato, String catalogoInventario, String urlOnline,String citazioneBibliografica,String dewey,String deweyDescr,int idCatalogoInventario) {
		set("idFondoSpeciale",idFondoSpeciale);
		set("denominazione", denominazione);
		set("descrizione", descrizione);
		set("fondoDepositato", fondoDepositato);
		set("entry", catalogoInventario);
		set("urlOnline", urlOnline);
		set("citazioneBibliografica", citazioneBibliografica);
		set("idCatalogoInventario",idCatalogoInventario);
	}

	public void setIdFondiSpeciali(int idFondoSpeciale) {
		set("idFondoSpeciale",idFondoSpeciale);
	}
	
	public int getIdFondiSpeciali(){
		return (Integer) get("idFondoSpeciale");
	}
	
	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public void setDescrizione(String descrizione) {
		set("descrizione", descrizione);
	}

	public void setFondoDepositato(String fondoDepositato) {
		set("fondoDepositato", fondoDepositato);
	}

	public void setCatalogoInventarioDescr(String catalogoInventario) {
		set("entry", catalogoInventario);
	}

	public void setUrlOnline(String urlOnline) {
		set("urlOnline", urlOnline);
	}
	
	public void setCitazioneBibliografica(String citazioneBibliografica) {
		set("citazioneBibliografica", citazioneBibliografica);
	}
	
	public String getCitazioneBibliografica() {
		return get("citazioneBibliografica");
	}

	public String getDenominazione() {
		return get("denominazione");
	}

	public String getDescrizione() {
		return get("descrizione");
	}

	public String getFondoDepositato() {
		return get("fondoDepositato");
	}

	public void setIdCatalogoInventario(Integer idCatalogoInventario) {
		set("idCatalogoInventario",idCatalogoInventario);
	}
	
	public Integer getIdCatalogoInventario() {
		return get("idCatalogoInventario");
	}
	
	public String getCatalogoInventarioDescr() {
		return get("entry");
	}

	public String getUrlOnline() {
		return get("urlOnline");
	}
	
	public String getDewey() {
		return get("dewey");
	}

	public void setDewey(String dewey) {
		set("dewey",dewey);
	}
	
	public String getDeweyDescr() {
		return get("descrizioneDewey");
	}

	public void setDeweyDescr(String deweyDescr) {
		set("descrizioneDewey",deweyDescr);
	}

}
