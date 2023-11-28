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
