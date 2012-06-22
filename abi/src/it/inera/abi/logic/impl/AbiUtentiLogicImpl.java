package it.inera.abi.logic.impl;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.UtentiDao;
import it.inera.abi.logic.AbiUtentiLogic;
import it.inera.abi.logic.UserActionLog;
import it.inera.abi.logic.auth.AuthLogic;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AbiUtentiLogicImpl implements AbiUtentiLogic {
	
	private UtentiDao utentiDao;
	private @Autowired BiblioDao biblioDao;
	private @Autowired AuthLogic authLogic;
	
	@Autowired private UserActionLog userActionLog; // logger per le azioni di salvataggio/modifica dell'utente

	
	@Autowired
	@Required
	public void setUtentiDao(UtentiDao utentiDao) {
		this.utentiDao = utentiDao;
	}

	@Override
	public List<Utenti> getUtenti(HashMap<String, Object> keys, int offset,	int rows, String orderByField, String orderByDir) {
		List<Utenti> utenti = utentiDao.ricercaUtenti(keys, offset, rows, orderByField, orderByDir);
		for (Utenti utente : utenti) {
			utente.setPassword(null); // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional se no resetta tutte le pwd!!!
		}
		return utenti; 
	}

	@Override
	public int countUtenti(HashMap<String, Object> keys) {
		return utentiDao.countUtenti(keys);
	}

	@Override
	public void addUtente(Utenti utente, List<Profili> profili) throws DuplicateEntryException {
		String newPassword = null;
		
		/* calcolo della password in md5 */
		if (StringUtils.isNotBlank(utente.getPassword())) {
			newPassword = utente.getPassword();
			String encoded = DigestUtils.md5Hex(newPassword);
			utente.setPassword(encoded);
		}
		else {
			if (utente.getIdUtenti() == null) {
				newPassword = RandomStringUtils.random(10, true, true);
				String encoded = DigestUtils.md5Hex(newPassword);
				utente.setPassword(encoded);
			}
		}
		
		utentiDao.addUtente(utente, profili);
		
		if(utente.getIdUtenti() == null) {
			createMailForResetPassword(utente, newPassword);
		}
	}
	
	@Override
	public void updateUtenteFromModificaAccount(Utenti utente) {
		String newPassword = null;
		
		/* calcolo della password in md5 */
		if (StringUtils.isNotBlank(utente.getPassword())) {
			newPassword = utente.getPassword();
			String encoded = DigestUtils.md5Hex(newPassword);
			utente.setPassword(encoded);
		}
				
		utentiDao.updateUtenteFromModificaAccount(utente);
		
	}

	@Override
	public List<Profili> getListProfile() {
		return utentiDao.getListProfile();
	}

	@Override
	@Transactional
	public void removeUtente(int id_utenti) throws ConstraintKeyViolationException {
		/* Se l'utente da rimuovere è utenteUltimaModifica per almeno una Biblioteca 
		 * in stato Occupata o Revisionabile verrà lanciata un'eccezione */
		utentiDao.isBiblioModifiedBY(id_utenti);
			
		/* Se l'utente da rimuovere è gestore/creatore per almeno una NuovaBiblioteca 
		 * verrà lanciata un'eccezione */
		utentiDao.isGestoreOrCreatore(id_utenti);
			
		utentiDao.removeUtente(id_utenti);
		
	}

	@Override
	public Utenti getDataUtente(int id_utenti) {
		Utenti utente = utentiDao.getDataUtente(id_utenti);
		utente.setPassword(null);  // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional!
		return utente;
	}
	
	@Override
	public List<Utenti> getListaUtenti() {
		List<Utenti> utenti = utentiDao.getListaUtenti();
		for (Utenti utente : utenti) {
			utente.setPassword(null); // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional!
		}
		return utenti; 
	}

	@Override
	public void changePassword(String password) {
		String encoded = DigestUtils.md5Hex(password);
		
		String username = authLogic.retrieveLoggedUser().getUsername();
		Utenti utente = utentiDao.findByName(username);
		
		utente.setPassword(encoded);
		utentiDao.saveUtente(utente);
	}
	
	@Override
	@Transactional
	public int assegnaBiblioDaCodice(int id_utente, String provincia, String numero) {
		String[] s = new String[1];
		if (numero.length() < 4) {
			if (numero.length() == 1)
				s[0] = new String("IT-".concat(provincia).concat("000").concat(numero));
			else if (numero.length() == 2)
				s[0] = new String("IT-".concat(provincia).concat("00").concat(numero));
			else s[0] = new String("IT-".concat(provincia).concat("0").concat(numero));
		}
		else s[0] = new String("IT-".concat(provincia).concat(numero));
		
		Biblioteca[] biblioteca = biblioDao.getBibliotecheByCodABI(s, 0, 1);
		if (biblioteca.length > 0) {
			return utentiDao.assegnaBiblioDaCodice(id_utente, biblioteca[0]);
		}
		else return 0;
		
	}
	
	@Override
	@Transactional
	public int assegnaBiblioDaUtente(int utenteFrom, int utenteTo) {
		return utentiDao.assegnaBiblioDaUtente(utenteFrom, utenteTo);
	}
	
	@Override
	@Transactional
	public void rimuoviBiblio(int id_utente, boolean all, List<Integer> biblist) {		
		if (!all) {
			List<Biblioteca> bibToRemoveList = new ArrayList<Biblioteca>();
			
			for (Integer entry : biblist) {
				Biblioteca b = biblioDao.getBibliotecaById(entry.intValue());
				bibToRemoveList.add(b);
				
			}
			
			utentiDao.rimuoviBiblio(id_utente, all, bibToRemoveList);
		}
		else utentiDao.rimuoviBiblio(id_utente, all, null);
		
		
	}
	
	@Override
	@Transactional
	public int countUtentiPaginati(String query, int id_utente) {
		return utentiDao.countUtentiPaginati(query, id_utente);
	}
	
	@Override
	@Transactional
	public List<Utenti> getUtentiPaginati(String query, int id_utente, int start, int limit) {
		return utentiDao.getUtentiPaginati(query, id_utente, start, limit);
	}
	
	@Override
	@Transactional
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList) {
		utentiDao.assegnaBibliosAdUtentis(idUsersList, idBibsList);
	}

	private @Value("${email.host.address}") String emailHostAddress;
	private @Value("${email.host.username}") String emailHostUsername;
	private @Value("${email.host.password}") String emailHostPassword;
	private @Value("${email.bounce.address}") String emailBounceAddress;
	
	@Override
	public void resetPassword(int idUser) {
		Utenti utenteToReset = utentiDao.getDataUtente(idUser);
		String newPassword = RandomStringUtils.random(10, true, true);
		String encoded = DigestUtils.md5Hex(newPassword);
		utentiDao.resetPassword(idUser, encoded);
		createMailForResetPassword(utenteToReset, newPassword);
	}

	/**
	 * @param utenteToReset
	 * @param newPassword
	 */
	private void createMailForResetPassword(Utenti utenteToReset, String newPassword) {
		Utenti fromUser =utentiDao.findByName(authLogic.retrieveLoggedUser().getUsername());
		
		String subject = "GENERAZIONE PASSWORD - ANAGRAFE BIBLIOTECHE ITALIANE";
		
		StringBuffer message = new StringBuffer(); 
		message.append("Password generata con successo.\n ");
		message.append("Nuova password: "+newPassword+"\n");
		message.append("Si consiglia di effettuare l'accesso e sostituire la nuova password con una personalizzata diversa da quelle utilizzate precedentemente!\n\n");
				
		String nameTo="";
		if(utenteToReset.getCognome()!=null && utenteToReset.getNome()!=null){
			nameTo= utenteToReset.getNome().concat(" ").concat(utenteToReset.getCognome());
		}
		
		
		String emailFrom = utentiDao.findByName(authLogic.retrieveLoggedUser().getUsername()).getEmail(); 
		String nameFrom="";
		if(fromUser.getCognome()!=null && fromUser.getNome()!=null){
			nameFrom= fromUser.getNome().concat(" ").concat(fromUser.getCognome());
		}
		
		try {
			userActionLog.logActionUtentiLogicDefaultUser("Invio dell'email..");
			Utility.sendEmail( subject,  message.toString(),  utenteToReset.getEmail(), nameTo, emailFrom, nameFrom, emailHostAddress, emailHostUsername,emailHostPassword, emailBounceAddress);
		} catch (EmailException e) {
			userActionLog.logActionUtentiLogicDefaultUser("Errore durante l'invio dell'email..");
		}
	}
	
	@Override
	@Transactional
	public boolean findUtenteByUsername(String username) {
		Utenti user = utentiDao.findByName(username);
		
		if (user != null) {
			return true;
			
		} else {
			return false;
		}
	}
	
	@Override
	@Transactional
	public Utenti saveCodiceValidazione(String username, String code) {
		Utenti user = utentiDao.findByName(username);
		
		user.setCodiceValidazione(code);
		utentiDao.saveUtente(user);
		
		//user.setPassword(null); // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional se no resetta tutte le pwd!!!
		return user;
	}
	
	@Override
	public Utenti findUtenteByValidateCode(String code) {
		Utenti user = utentiDao.findByValidateCode(code);

		if (user != null) {
			user.setPassword(null); // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional se no resetta tutte le pwd!!!
		}
		return user;
	}
	
	@Override
	@Transactional
	public void changePassword(String username, String newPassword) {
		Utenti user = utentiDao.findByName(username);
		String encoded = DigestUtils.md5Hex(newPassword);
		user.setPassword(encoded);
		
		user.setDataModificaPassword(new Date(System.currentTimeMillis()));
		
		user.setCodiceValidazione(null);
		utentiDao.saveUtente(user);
	}
}