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
