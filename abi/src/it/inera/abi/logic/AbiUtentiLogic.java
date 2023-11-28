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

package it.inera.abi.logic;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.HashMap;
import java.util.List;

/**
 * Classe contenente la logica delle principali operazioni riguardanti
 * gli utenti (modifica, gestione password, biblioteche assegnate, ecc...)
 *
 */
public interface AbiUtentiLogic {

	public List<Utenti> getUtenti(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir);

	public int countUtenti(HashMap<String, Object> keys);

	public void addUtente(Utenti utente, List<Profili> profili)  throws  DuplicateEntryException;

	public void updateUtenteFromModificaAccount(Utenti utente);
	
	public List<Profili> getListProfile();

	public void removeUtente(int id_utenti) throws ConstraintKeyViolationException;

	public Utenti getDataUtente(int id_utenti);
	
	public List<Utenti> getListaUtenti();
	
	public void changePassword(String password);
	
	public int assegnaBiblioDaCodice(int id_utente, String provincia, String numero);
	
	public int assegnaBiblioDaUtente(int utenteFrom, int utenteTo);
	
	public void rimuoviBiblio(int id_utente, boolean all, List<Integer> biblist);
	
	public int countUtentiPaginati(String query, int id_utente);
	
	public List<Utenti> getUtentiPaginati(String query, int id_utente, int start, int limit);
	
	/* Metodo per l'assegnamento di una lista di biblioteche ad una lista di utenti */
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList);

	public void resetPassword(int idUser);
	
	public boolean findUtenteByUsername(String username);
	
	public Utenti saveCodiceValidazione(String username, String code);
	
	public Utenti findUtenteByValidateCode(String code);
	
	public void changePassword(String username, String newPassword);
	
}
