package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class UserModel extends BaseModel implements Serializable {

	/**
	 * 
	 */
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
