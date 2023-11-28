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

import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per i file controllati nel formato di scambio
 *
 */
public class FileCaricatiListModel extends BaseModel {

	public FileCaricatiListModel() {
		set("error", false);
	}
	
	public void setBiblios(List biblios) {
		set("biblios", biblios);
	}

	public List getBiblios() {
		return get("biblios");
	}
	
	public void setEsito(String esito) {
		set("esito", esito);
	}

	public String getEsito() {
		return get("esito");
	}
	
	public void setError(boolean error) {
		set("error", error);
	}

	public boolean getError() {
		return (Boolean) get("error");
	}


	public void setUserName(String userName) {
		set("userName", userName);
	}

	public void setEmail(String email) {
		set("email", email);
	}

	public void setDataUpload(Date dataUpload) {
		set("dataUpload", dataUpload);
	}

	public void setOraUpload(String oraUpload) {
		set("oraUpload", oraUpload);
	}

	public void setDimensione(String dimensione) {
		set("dimensione", dimensione);
	}

	public String getUserName() {
		return get("userName");
	}

	public String getEmail() {
		return get("email");
	}

	public String getDataUpload() {
		return get("dataUpload");
	}

	public String getOraUpload() {
		return get("oraUpload");
	}

	public String getDimensione() {
		return get("dimensione");
	}

	public String getControllo() {
		return get("controllo");
	}
	
	public void setNbib(String nBib) {
		set("nBib", nBib);
	}
	
	public String getNbib() {
		return get("nBib");
	}
	
	public void setFilename(String filename) {
		set("filename", filename);
	}
	
	public String getFilename() {
		return get("filename");
	}
	
	public void setOriginalFilename(String originalFilename) {
		set("originalFilename", originalFilename);
	}
	
	public String getOriginalFilename() {
		return get("originalFilename");
	}
}
