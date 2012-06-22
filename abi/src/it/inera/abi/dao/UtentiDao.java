package it.inera.abi.dao;

import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.HashMap;
import java.util.List;

public interface UtentiDao {

	public List<Utenti> ricercaUtenti(HashMap<String, Object> parametri,int offset, int rows, String orderByField,
			String orderByDir);

	public int countUtenti(HashMap<String, Object> keys);

	public void addUtente(Utenti utente, List<Profili> profili) throws DuplicateEntryException;

	public void updateUtenteFromModificaAccount(Utenti utente);
	
	public List<Profili> getListProfile();

//	public void addProfilo(Profili profilo);

	public void removeUtente(int id_utenti);
	
	public Utenti getDataUtente(int id_utenti);
	
	//public List<Utenti> ricercaUtenti(HashMap<String,String> keys);
	
	/*DAL FORMATO DI SCAMBIO*/
	/**
	 * Ritorna true se la biblioteca puo essere gestita dal utente
	 * @param codiceAbi
	 * @param loginUtente
	 * @return
	 * @throws DaoException
	 */
	public boolean isUtenteManageBiblioByCodAbiSenzaStato(String codiceAbi, String loginUtente);
	
	public List<Utenti> getListaUtenti();
	
	/**
	 * Per la sicurezza
	 * @param username
	 * @return
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
