/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

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
