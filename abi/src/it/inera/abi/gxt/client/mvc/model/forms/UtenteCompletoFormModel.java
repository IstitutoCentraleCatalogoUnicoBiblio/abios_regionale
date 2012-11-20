package it.inera.abi.gxt.client.mvc.model.forms;

import it.inera.abi.gxt.client.mvc.model.UserModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modello utilizzato all'interno della form di creazione / modifica dell'utente
 * per rappresentare l'utente e i profili associati
 *
 */
public class UtenteCompletoFormModel extends UserModel implements Serializable {

	private static final long serialVersionUID = 1178016121040961460L;

	ArrayList<ProfiliUtente> a;
	public UtenteCompletoFormModel() {

	}

	public UtenteCompletoFormModel(String id_utenti, String nome, String cognome,
			String password, String password2, String login, String incarico,
			String telefono, String fax, String email, String note , List<ProfiliUtente> profili) {

		super(/* id_utenti, */nome, cognome, login, incarico);
		
		//set("id_utenti", -1);
		set("password2", password2);
		set("tel", telefono);
		set("fax", fax);
		set("email", email);
		set("note", note);
		set("profili", profili);

	}

	public void setPassword(String password) {
		set("password", password);
	}

	public String getPassword() {
		return get("password");
	}

	public void setPassword2(String password2) {
		set("password2", password2);
	}

	public String getPassword2() {
		return get("password2");
	}

	public void setTelefono(String telefono) {
		set("tel", telefono);
	}

	public String getTelefono() {
		return get("tel");
	}

	public void setFax(String fax) {
		set("fax", fax);
	}

	public String getFax() {
		return get("fax");
	}

	public void setEmail(String email) {
		set("email", email);
	}

	public String getEmail() {
		return get("email");
	}

	public void setNote(String note) {
		set("note", note);
	}

	public String getNote() {
		return get("note");
	}
	
	public void setProfili(List<ProfiliUtente> profili) {
		set("profili", profili);
	}
	
	public List<ProfiliUtente> getProfili() {
		return get("profili");
	}
}
