package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>UtentiService</code>
 * 
 */
public interface UtentiServiceAsync {

	void saveUtente(UtenteCompletoFormModel nuovoUtente,boolean modifica, AsyncCallback callback);
	
	void saveUtenteFromModificaAccount(UtenteCompletoFormModel nuovoUtente, AsyncCallback<Void> callback);

	void getUsers(PagingLoadConfig config, AsyncCallback<PagingLoadResult<UserModel>> callback);

	void getListProfiles(AsyncCallback<List<ProfiliUtente>> callback);

	void removeUtente(int id_utenti, AsyncCallback callback);

	void getDataUtente(int id_utenti, AsyncCallback<UtenteCompletoFormModel> asyncCallback);
	
	void getListaUtenti(AsyncCallback<List<UserModel>> callback);

	void changePassword(String password, AsyncCallback<Void> callback);
	
	void assegnaBiblioDaCodice(int id_utente, String provincia, String numero, AsyncCallback<Integer> callback);
	
	void assegnaBiblioDaUtente(int utenteFrom, int utenteTo, AsyncCallback<Integer> callback);
	
	void rimuoviBiblio(int id_utente, boolean all, List<Integer> biblist, AsyncCallback<Void> callback);
	
	void getListaUtentiPaginata(ModelData loadConfig, int id_utente, AsyncCallback<PagingLoadResult<UserModel>> callback);
	
	void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList, AsyncCallback<Void> callback);
	
	void getRuoli(AsyncCallback<List<ProfiliModel>> callback);
	
	void resetPassword(int idUser, AsyncCallback<Void> callback);
	
}
