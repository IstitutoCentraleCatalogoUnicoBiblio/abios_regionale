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
 * Modello utilizzato per rappresentare gli utenti
 *
 */
public class UserModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 5308608394761194251L;

	public UserModel() {

	}

	public UserModel(int id_utenti,String nome, String cognome, String login,
			String incarico) {
		 set("id_utenti", id_utenti);
		set("nome", nome);
		set("cognome", cognome);
		set("login", login);
		set("incarico", incarico);
	}

	public UserModel(String nome, String cognome, String login,
			String incarico) {
	//	 set("id_utenti", id_utenti);
		set("nome", nome);
		set("cognome", cognome);
		set("login", login);
		set("incarico", incarico);
	}

	
	public void setIdUser(int id_utenti) {
		set("id_utenti", id_utenti);

	}
	
	public int getIdUser(){
		return (Integer) get("id_utenti");
	}

	public String getNome() {
		return get("nome");
	}

	public String getCognome() {
		return get("cognome");
	}

	public String getUserName() {
		return get("login");
	}

	public String getIncarico() {
		return get("incarico");
	}

	public void setNome(String nome) {
		set("nome", nome);
	}

	public void setCognome(String cognome) {
		set("cognome", cognome);
	}

	public void setUserName(String login) {
		set("login", login);
	}

	public void setIncarico(String incarico) {
		set("incarico", incarico);
	}
	
	public void setEnabled(Boolean enabled) {
		set("enabled", enabled);
		if(enabled==null){
			set("enabledDescr","Non specificato");
		}
		else if(enabled){
			set("enabledDescr","Abilitato");
		}else if (!enabled){
			set("enabledDescr","Disabilitato");
		}
	}

	public Boolean getEnabled() {
		return	get("enabled");
	}
	
	public String getEnabledDescr() {
		return	get("enabledDescr");
	}
}
