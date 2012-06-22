package it.inera.abi.gxt.client.mvc.model.forms;

import java.io.Serializable;

public class ProfiliUtente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8211965180563741614L;
	private String denominazione;
	private int id_profili;
private boolean valore;


	public ProfiliUtente() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id_profili
	 * @param denominazione
	 */
	public ProfiliUtente(int id_profili, String denominazione, boolean val) {
		super();
		this.valore= val;
		this.id_profili = id_profili;
		this.denominazione = denominazione;
	}
	/**
	 * @return the valore
	 */
	public boolean isValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(boolean valore) {
		this.valore = valore;
	}

	/**
	 * @return the id_profili
	 */
	public int getId_profili() {
		return id_profili;
	}

	/**
	 * @param id_profili
	 *            the id_profili to set
	 */
	public void setId_profili(int id_profili) {
		this.id_profili = id_profili;
	}

	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione
	 *            the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

}
