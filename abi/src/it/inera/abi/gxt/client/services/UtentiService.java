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
