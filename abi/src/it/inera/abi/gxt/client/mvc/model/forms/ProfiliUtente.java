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
