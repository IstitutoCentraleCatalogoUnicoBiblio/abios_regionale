package it.inera.abi.logic;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.HashMap;
import java.util.List;

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
