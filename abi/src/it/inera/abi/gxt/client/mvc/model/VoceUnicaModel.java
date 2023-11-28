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
 * Modello generico utilizzato per rappresentare le tabelle dinamiche costituite da
 * (id_record, descrizione)
 *
 */
public class VoceUnicaModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public VoceUnicaModel() {

	}

	public VoceUnicaModel(Integer id_record,String entry) {
		set("id_record", id_record);
		set("entry", entry);
	}

	public VoceUnicaModel(Integer id_record,Integer id_biblio,String entry) {
		set("id_record", id_record);
		set("id_biblio", id_biblio);
		set("entry", entry);
	}

	public VoceUnicaModel(String entry) {
		set("entry", entry);
	}


	public String getEntry() {
		return get("entry");
	}

	public void setEntry(String entry) {
		set("entry", entry);
	}

	public void setIdRecord(Integer id_record) {
		set("id_record", id_record);
	}

	public Integer getIdRecord() {
		return get("id_record");
	}

	public void setIdBiblioteca(Integer id_biblio) {
		set("id_biblio", id_biblio);
	}

	public Integer getIdBiblioteca() {
		return get("id_biblio");
	}

	/*Utilizzati nella differenziazione delle tabelle dinamiche nella lista 
	 * generica a voce singola*/
	public void setIdTabella(int tipoTabella) {
		set("tipoTabella",tipoTabella);	
	}

	public int getIdTabella() {
		return (Integer) get("tipoTabella");	
	}
	//******fine******//

	/*Utilizzati nello stato catalogazione*/
	public String getIsilProvincia() {
		return get("isilProvincia");
	}
	
	public void setIsilProvincia(String isilProvincia) {
		set("isilProvincia", isilProvincia);
	}
	
	public Integer getIsilNumero() {
		return get("isilNumero");
	}

	public void setIsilNumero(Integer isilNumero) {
		set("isilNumero", isilNumero);
	}
	/*fine --Utilizzati nello stato catalogazione*/
}
