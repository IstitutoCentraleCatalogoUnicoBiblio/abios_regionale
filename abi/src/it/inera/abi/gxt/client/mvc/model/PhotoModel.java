package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class PhotoModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -4822335616896264093L;

	public PhotoModel() {
		
	}
	
	public void setIdBiblioteca(int idBiblioteca) {
		set("idBiblioteca", idBiblioteca);
	}

	public int getIdBiblioteca() {
		return (Integer) get("idBiblioteca");
	}
	
	public void setIdPhoto(int idPhoto) {
		set("idPhoto", idPhoto);
	}

	public int getIdPhoto() {
		return (Integer) get("idPhoto");
	}
	
	public void setCaption(String caption) {
		set("caption", caption);
	}
	
	public String getCaption() {
		return (String) get("caption");
	}
	
	public void setShortName(String shortName) {
		set("shortName", shortName);
	}
	
	public String getShortName() {
		return (String) get("shortName");
	}

	public void setUri(String uri) {
		set("uri", uri);
	}
	
	public String getUri() {
		return (String) get("uri");
	}
	
	public void setIsRemote(Boolean isRemote) {
		set("isRemote", isRemote);
	}
	
	public Boolean isRemote() {
		return (Boolean) get("isRemote");
	}
	
}
