package it.inera.abi.gxt.client.mvc.model.auth;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * Modello utilizzato per rappresentare l'utente autenticato
 *
 */
public class UtentiAuthModel extends BaseModelData {
	
	private static final long serialVersionUID = -1251863221688807749L;
	
	ArrayList<ProfiliModel> profili;
	
	public UtentiAuthModel() {
	}
	
	public UtentiAuthModel(String userLogin) {
		set("userLogin", userLogin);
	}
	
	public void setUserId(Integer userId) {
		set("userId", userId);
	}
	
	public Integer getUserId() {
		return get("userId");
	}
	
	public void setUserLogin(String userLogin) {
		set("userLogin", userLogin);
	}
	
	public String getUserLogin() {
		return get("userLogin");
	}
	
	public void setNome(String nome) {
		set("nome", nome);
	}
	
	public void setCognome(String cognome) {
		set("cognome", cognome);
	}
	
	public String getNome() {
		return get("nome");
	}
	
	public String getCognome() {
		return get("cognome");
	}
	
	public void setProfili(List<ProfiliModel> profiliModels) {
		set("profili", profiliModels);
	}
	
	public List<ProfiliModel> getProfili() {
		return get("profili");
	}
	
	public void setEmail(String email) {
		set("email", email);
	}
	
	public String getEmail() {
		return get("email");
	}
}
