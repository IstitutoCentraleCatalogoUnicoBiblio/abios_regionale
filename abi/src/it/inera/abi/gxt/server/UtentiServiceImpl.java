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

package it.inera.abi.gxt.server;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.UtentiService;
import it.inera.abi.logic.AbiUtentiLogic;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 * Implementazione dei servizi relativi agli utenti (lato server)
 * 
 */
public class UtentiServiceImpl extends AutoinjectingRemoteServiceServlet implements UtentiService {

	private static final long serialVersionUID = -9199572903000227961L;

	private AbiUtentiLogic abiUtentiLogic;

	@Autowired
	@Required
	public void setAbiUtentiService(AbiUtentiLogic abiUtentiLogic) {
		this.abiUtentiLogic = abiUtentiLogic;
	}

	public UtentiServiceImpl() {
	}

	@Override
	public PagingLoadResult<UserModel> getUsers(final PagingLoadConfig config) {
		
		HashMap<String, Object> keys = config.get("keys");

		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir = config.getSortInfo().getSortDir().toString();
		String sortField = config.getSortInfo().getSortField();

		/**
		 * LOAD DATI
		 */
		List<Utenti> results = abiUtentiLogic.getUtenti(keys, start, limit, sortField, sortDir);
		int countAll = abiUtentiLogic.countUtenti(keys);

		/**
		 * mapping...
		 */
		ArrayList<UserModel> sublist = new ArrayList<UserModel>();
		for (int i = 0; i < results.size(); i++) {

			Utenti utente = results.get(i);
			UserModel temp = new UserModel();

			temp.setCognome(utente.getCognome());
			temp.setIdUser((utente.getIdUtenti()));
			temp.setIncarico(utente.getIncarico());
			temp.setNome(utente.getNome());
			temp.setUserName(utente.getLogin());
			temp.setEnabled(utente.getEnabled());
			sublist.add(temp);
		}

		return new BasePagingLoadResult<UserModel>(sublist, config.getOffset(),
				countAll);
	}

	@Override
	public void saveUtente(UtenteCompletoFormModel nuovoUtente, boolean modifica) throws DuplicatedEntryClientSideException {
		Utenti utente = new Utenti();
		/**/
		if (modifica == true) {
			utente.setIdUtenti(nuovoUtente.getIdUser());
		}
		/**/
		utente.setNome(nuovoUtente.getNome());
		utente.setCognome(nuovoUtente.getCognome());
		utente.setLogin(nuovoUtente.getUserName());
		utente.setPassword(nuovoUtente.getPassword());
		utente.setIncarico(nuovoUtente.getIncarico());
		utente.setEmail(nuovoUtente.getEmail());
		utente.setTel(nuovoUtente.getTelefono());
		utente.setFax(nuovoUtente.getFax());
		utente.setNote(nuovoUtente.getNote());

		List<Profili> s = new ArrayList<Profili>();
		List<ProfiliUtente> l = nuovoUtente.getProfili();
		Iterator<ProfiliUtente> it = l.iterator();
		while (it.hasNext()) {
			Profili p = new Profili();

			ProfiliUtente tmp = it.next();

			p.setIdProfili(tmp.getId_profili());
			// p.setDenominazione(tmp.getDenominazione());
			s.add(p);
		}
		try{
			abiUtentiLogic.addUtente(utente, s);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}
	
	@Override
	public void saveUtenteFromModificaAccount(UtenteCompletoFormModel nuovoUtente) {
		Utenti utente = new Utenti();
		utente.setIdUtenti(nuovoUtente.getIdUser());
		utente.setPassword(nuovoUtente.getPassword());
		utente.setEmail(nuovoUtente.getEmail());
		utente.setTel(nuovoUtente.getTelefono());
		utente.setFax(nuovoUtente.getFax());

		abiUtentiLogic.updateUtenteFromModificaAccount(utente);
	}

	@Override
	public List<ProfiliUtente> getListProfiles() {
		List<Profili> listProfili = abiUtentiLogic.getListProfile();
		Iterator<Profili> it = listProfili.iterator();

		List<ProfiliUtente> profiliDenominazioni = new ArrayList<ProfiliUtente>();
		while (it.hasNext()) {

			ProfiliUtente p = new ProfiliUtente();
			Profili tmp = it.next();
			p.setDenominazione(tmp.getDenominazione());
			p.setId_profili(tmp.getIdProfili());
			profiliDenominazioni.add(p);

		}
		return profiliDenominazioni;
	}

	@Override
	public void removeUtente(int id_utenti) throws CostraintKeyViolationClientSideException {
		try {
			abiUtentiLogic.removeUtente(id_utenti);
		}
		catch(ConstraintKeyViolationException e) {
			throw new CostraintKeyViolationClientSideException(e.getMessage());
		}
	}

	@Override
	public UtenteCompletoFormModel getDataUtente(int id_utenti) {
		UtenteCompletoFormModel u = new UtenteCompletoFormModel();
		Utenti uDb = abiUtentiLogic.getDataUtente(id_utenti);

		u.setIdUser(id_utenti);
		u.setNome(uDb.getNome());
		u.setCognome(uDb.getCognome());
		u.setUserName(uDb.getLogin());
		u.setIncarico(uDb.getIncarico());
		u.setPassword(uDb.getPassword());
		u.setEmail(uDb.getEmail());
		u.setFax(uDb.getFax());
		u.setTelefono(uDb.getTel());
		u.setNote(uDb.getNote());
		u.setEnabled(uDb.getEnabled());
		
		List<ProfiliUtente> profiliUtenteAttivi = new ArrayList<ProfiliUtente>();
		List<ProfiliUtente> genericsProfiliUtente = new ArrayList<ProfiliUtente>();

		List<Profili> profiliAttivi = uDb.getProfilis();// Carico i profili
														// attivi
		List<Profili> generics = abiUtentiLogic.getListProfile();// carico i
																	// profili
																	// generici
		// Controllo se l'utente ha profili attivi
		if (profiliAttivi.size() == 0) {
			/*
			 * se la diemnsione della lsita dei profili attivi ==0 itero
			 * direttamente la lista dei profili generici del db,
			 */
			Iterator<Profili> genericsIterator = generics.iterator();
			while (genericsIterator.hasNext()) {
				// Converto Profili in ProfiliUtente per il lato client
				Profili tmp = genericsIterator.next();
				profiliUtenteAttivi.add(new ProfiliUtente(tmp.getIdProfili(),
						tmp.getDenominazione(), false));
			}
			/* Setto la nuova lista profili all'utente */
			u.setProfili(profiliUtenteAttivi);
		} else {/* Se l'utente ha gia profili attivi (profiliAttivi.size>0) */
			/* Converto i profili attivi in ProfiliUtente */
			HashMap<Integer, Boolean> marked = new HashMap<Integer, Boolean>();
			Iterator<Profili> it = profiliAttivi.iterator();
			while (it.hasNext()) {
				Profili tmp = it.next();
				// marco il profilo attivo per l'utente
				marked.put(tmp.getIdProfili(), true);
			}
			/*
			 * Converto i profili generici in ProfiliUtente e marco quelli
			 * attivi
			 */

			Iterator<Profili> genericsIt = generics.iterator();
			while (genericsIt.hasNext()) {
				ProfiliUtente p = new ProfiliUtente();

				Profili tmp = genericsIt.next();

				p.setId_profili(tmp.getIdProfili());
				p.setDenominazione(tmp.getDenominazione());

				p.setValore(marked.get(tmp.getIdProfili()) == null ? false
						: true);

				genericsProfiliUtente.add(p);
			}
			/*
			 * Confronto i profili attivi con i generici e setto il risultato
			 * nella lista si appoggio
			 */

			/* Setto la lista profili attivi e non attivi all'utente */
			u.setProfili(genericsProfiliUtente);
		}

		return u;
	}
	
	@Override
	public List<UserModel> getListaUtenti() {
				
		List<UserModel> sublist = new ArrayList<UserModel>();
		
		List<Utenti> listDB = (List<Utenti>) abiUtentiLogic.getListaUtenti();
		
		for (Utenti user : listDB) {
			UserModel tmpuser = new UserModel();
			tmpuser.setIdUser(user.getIdUtenti());
			tmpuser.setNome(user.getNome());
			tmpuser.setCognome(user.getCognome());
			tmpuser.setUserName(user.getLogin());
			tmpuser.setEnabled(user.getEnabled());
			sublist.add(tmpuser);
		}
		
		return sublist;
		
	}

	@Override
	public void changePassword(String password) {
		abiUtentiLogic.changePassword(password);
	}
	
	@Override
	/* Assegna la biblioteca selezionata all'utente */
	public Integer assegnaBiblioDaCodice(int id_utente, String provincia, String numero) {
		return new Integer(abiUtentiLogic.assegnaBiblioDaCodice(id_utente, provincia, numero));
	}
	
	@Override
	/* Assegna un gruppo di biblioteche di un altro utente */
	public Integer assegnaBiblioDaUtente(int utenteFrom, int utenteTo) {
		return new Integer(abiUtentiLogic.assegnaBiblioDaUtente(utenteFrom, utenteTo));
	}
	
	@Override
	/* Rimuove un insieme di biblioteche */
	public void rimuoviBiblio(int id_utente, boolean all, List<Integer> biblist) {
		abiUtentiLogic.rimuoviBiblio(id_utente, all, biblist);
	}
	
	@Override
	public PagingLoadResult<UserModel> getListaUtentiPaginata(ModelData loadConfig, int id_utente) {
		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");		
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		
		int countAll = abiUtentiLogic.countUtentiPaginati(query, id_utente);

		List<Utenti> sublist = abiUtentiLogic.getUtentiPaginati(query, id_utente, start, limit);
		List<UserModel> list = new ArrayList<UserModel>();
		
		for (Utenti entry : sublist) {
			UserModel u = new UserModel();
			u.setIdUser(entry.getIdUtenti());
			u.setUserName(entry.getLogin());
			u.setEnabled(entry.getEnabled());
			list.add(u);
		}
			
		return new BasePagingLoadResult<UserModel>(list, start, countAll);
	}
	
	@Override
	/* Servizio per assegnare le biblioteche selezionate agli utenti selezionati */
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList) {
		abiUtentiLogic.assegnaBibliosAdUtentis(idUsersList, idBibsList);
	}

	@Override
	public List<ProfiliModel> getRuoli() {
		List<Profili> listProfili = abiUtentiLogic.getListProfile();
		Iterator<Profili> it = listProfili.iterator();

		List<ProfiliModel> ruoli = new ArrayList<ProfiliModel>();
		while (it.hasNext()) {
			Profili tmp = it.next();
			ProfiliModel p = new ProfiliModel(tmp.getIdProfili(), tmp.getDenominazione());			
			
			ruoli.add(p);

		}
		return ruoli;
	}

	@Override
	public void resetPassword(int idUser) {
		abiUtentiLogic.resetPassword(idUser);
	}
}
