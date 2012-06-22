package it.inera.abi.gxt.client.mvc.model;

import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;

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
