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
