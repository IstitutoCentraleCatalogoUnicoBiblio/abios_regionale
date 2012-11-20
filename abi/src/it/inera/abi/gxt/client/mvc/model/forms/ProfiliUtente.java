package it.inera.abi.gxt.client.mvc.model.forms;

import java.io.Serializable;

/**
 * Classe che rappresenta i profili da associare all'utente
 *
 */
public class ProfiliUtente implements Serializable {

	private static final long serialVersionUID = -8211965180563741614L;
	private String denominazione;
	private int id_profili;
	private boolean valore;


	public ProfiliUtente() {

	}

	/**
	 * @param id_profili
	 * @param denominazione
	 * 
	 */
	public ProfiliUtente(int id_profili, String denominazione, boolean val) {
		super();
		this.valore = val;
		this.id_profili = id_profili;
		this.denominazione = denominazione;
	}
	
	public boolean isValore() {
		return valore;
	}

	/**
	 * @param valore
	 * 
	 */
	public void setValore(boolean valore) {
		this.valore = valore;
	}

	public int getId_profili() {
		return id_profili;
	}

	/**
	 * @param id_profili
	 * 
	 */
	public void setId_profili(int id_profili) {
		this.id_profili = id_profili;
	}

	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione
	 * 
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

}
