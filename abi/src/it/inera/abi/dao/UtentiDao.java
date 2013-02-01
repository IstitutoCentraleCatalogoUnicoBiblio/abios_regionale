package it.inera.abi.dao;

import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.HashMap;
import java.util.List;

/** 
 * Interfaccia DAO per l'entità Utenti
 *
 */
public interface UtentiDao {

	public List<Utenti> ricercaUtenti(HashMap<String, Object> parametri,int offset, int rows, String orderByField,
			String orderByDir);

	public int countUtenti(HashMap<String, Object> keys);

	public void addUtente(Utenti utente, List<Profili> profili) throws DuplicateEntryException;

	public void updateUtenteFromModificaAccount(Utenti utente);
	
	public List<Profili> getListProfile();

	public void removeUtente(int id_utenti);
	
	public Utenti getDataUtente(int id_utenti);
	
	/**
	 * Ritorna true se la biblioteca puo essere gestita dal utente
	 * @param codiceAbi Codice ISIL
	 * @param loginUtente Username dell'utente
	 * @return Indica se la biblioteca è gestibile dall'utente
	 * @throws DaoException
	 */
	public boolean isUtenteManageBiblioByCodAbiSenzaStato(String codiceAbi, String loginUtente);
	
	public List<Utenti> getListaUtenti();
	
	/**
	 * Per la sicurezza, cerca un utente tramite username
	 * @param username Da ricercare
	 * @return Utente trovato
	 */
	public Utenti findByName(String username);
	
	public void saveUtente(Utenti utente);
	
	public int assegnaBiblioDaCodice(int id_utente, Biblioteca b);
	
	public int assegnaBiblioDaUtente(int utenteFrom, int utenteTo);
	
	public void rimuoviBiblio(int id_utente, boolean all, List<Biblioteca> biblist);
	
	public int countUtentiPaginati(String searchLogin, int id_utente);
	
	public List<Utenti> getUtentiPaginati(String searchLogin, int id_utente, int start, int limit);
	
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList);
	
	public void isBiblioModifiedBY(int idUser) throws ConstraintKeyViolationException;
	
	public void isGestoreOrCreatore(int idUser) throws ConstraintKeyViolationException;

	public void resetPassword(int idUser, String encoded);

	public List<Utenti> getUsersWithElapsedPasswords();

	public List<Utenti> getUsersWithTenDaysRemaningRange();

	public void disableAccount(int idUser);

	List<Utenti> getUsersByRole(Integer role);
	
	public Utenti findByValidateCode(String code);
	
}
