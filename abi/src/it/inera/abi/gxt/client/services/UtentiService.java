package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi relativi agli utenti (lato client)
 * 
 */
@RemoteServiceRelativePath(Abi.UTENTI_SERVICE)
public interface UtentiService extends RemoteService {

	public PagingLoadResult<UserModel> getUsers(PagingLoadConfig config);
	
	public void saveUtente(UtenteCompletoFormModel nuovoUtente, boolean modifica) throws DuplicatedEntryClientSideException;
	
	public void saveUtenteFromModificaAccount(UtenteCompletoFormModel nuovoUtente);
	
	public List<ProfiliUtente> getListProfiles();
	
	public void removeUtente(int id_utenti) throws CostraintKeyViolationClientSideException;

	public UtenteCompletoFormModel getDataUtente(int id_utenti);
	
	public List<UserModel> getListaUtenti();
	
	/**
	 * Cambio della password da parte dell'utente loggato.
	 * L'utente viene riconosciuto in automatico dal sistema tramite le API di sicurezza
	 * @param password
	 */
	public void changePassword(String password);
	
	/* Assegna la biblioteca selezionata all'utente */
	public Integer assegnaBiblioDaCodice(int id_utente, String provincia, String numero);
	
	/* Assegna un gruppo di biblioteche di un altro utente */
	public Integer assegnaBiblioDaUtente(int utenteFrom, int utenteTo);
	
	/* Rimuove un insieme di biblioteche */
	public void rimuoviBiblio(int id_utente, boolean all, List<Integer> biblist);
	
	public PagingLoadResult<UserModel> getListaUtentiPaginata(ModelData loadConfig, int id_utente);
	
	/* Servizio per assegnare le biblioteche selezionate agli utenti selezionati */
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList);

	public List<ProfiliModel> getRuoli();

	void resetPassword(int idUser);

}
