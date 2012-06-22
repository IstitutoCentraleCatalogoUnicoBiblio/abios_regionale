package it.inera.abi.logic.checkpassword;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Utenti;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CheckPasswordImpl implements CheckPassword{

	@Autowired private UtentiDao utentiDao;
	
	private Log log = LogFactory.getLog(CheckPasswordImpl.class);
	
//	@Autowired
//	@Required
//	public void setUtentiDao(UtentiDao utentiDao) {
//		this.utentiDao = utentiDao;
//	}

	@Override
	public void manageUsersWithElapsedPasswords() {
		List<Utenti> utentis = getUsersWithElapsedPasswords();
		for(Utenti utente: utentis){
			disableAccount(utente.getIdUtenti());
			sendMailForDisable(utente);
		}
		
	}

	@Override
	public void manageUsersWithTenDaysRemaningRange() {
		List<Utenti> utentis = getUsersWithTenDaysRemaningRange();
		for(Utenti utente: utentis){
			sendMailForAlert(utente);
		}
	}
	
	/*-----------VARIABILI AUTOWIRED---------*/
	private @Value("${email.checkpassword.address}") String emailFrom;
	private @Value("${email.checkpassword.name}") String nameFrom;
	
	private @Value("${email.checkpassword.alert.subject}") String alertSubject;
	private @Value("${email.checkpassword.alert.msg}") String alertMsg;
	
	@Override
	public void sendMailForAlert(Utenti utente) {
		String emailTo = utente.getEmail();
		String nameTo= "";
		
		if(utente.getCognome() != null && utente.getNome() != null) {
			nameTo= utente.getNome().concat(" ").concat(utente.getCognome());
		}

		String message = alertMsg;
		message = StringUtils.replace(message, "$$$username$$$", utente.getLogin());
		message = StringUtils.replace(message, "$$$days$$$", Long.valueOf(180-getDayDifference(utente.getDataModificaPassword())).toString());
		
		try {
			log.info("Invio email di avviso per password in scadenza all'utente: " + nameTo);
			Utility.sendEmail(alertSubject, message, emailTo, nameTo, emailFrom, nameFrom, emailHostAddress, emailHostUsername, emailHostPassword, emailBounceAddress);
			log.info("Email di avviso inviata con successo all'untente "+nameTo);
		} catch (EmailException e) {
			log.fatal("Errore durante l'invio dell'email di avviso all'untente "+nameTo);
		}
	}
	
	private @Value("${email.checkpassword.overdue.subject}") String overdueSubject;
	private @Value("${email.checkpassword.overdue.msg}") String overdueMsg;
	@Override
	public void sendMailForDisable(Utenti utente) {
		String emailTo = utente.getEmail();
		String nameTo = "";
		
		if (utente.getCognome() != null && utente.getNome() != null) {
			nameTo= utente.getNome().concat(" ").concat(utente.getCognome());
		}
		
		String message = overdueMsg;
		message = StringUtils.replace(message, "$$$username$$$", utente.getLogin());
		
		try {
			log.info("Invio email di avviso per disabilitazione account all'utente: " + nameTo);
			Utility.sendEmail(overdueSubject, message, emailTo, nameTo, emailFrom, nameFrom, emailHostAddress, emailHostUsername,emailHostPassword, emailBounceAddress);
			log.info("Email di avviso inviata con successo all'utente " + nameTo);
		} catch (EmailException e) {
			log.fatal("Errore durante l'invio dell'email di avviso all'utente " + nameTo);
		}
	}

	@Override
	public void disableAccount(int idUser) {
		utentiDao.disableAccount(idUser);
	}

	@Override
	public List<Utenti> getUsersWithElapsedPasswords() {
		return utentiDao.getUsersWithElapsedPasswords();
	}

	@Override
	public List<Utenti> getUsersWithTenDaysRemaningRange() {
		return utentiDao.getUsersWithTenDaysRemaningRange();
	}
	
	/*-----------VARIABILI AUTOWIRED---------*/
	private @Value("${email.host.address}") String emailHostAddress;
	private @Value("${email.host.username}") String emailHostUsername;
	private @Value("${email.host.password}") String emailHostPassword;
	private @Value("${email.bounce.address}") String emailBounceAddress;

	@Override
	public long getDayDifference(Date passwordDate) {
		long today = System.currentTimeMillis();
		long tmpDay = passwordDate.getTime();
		
		long longDiff = today - tmpDay;
		
		/* Converte la differenza in giorni */
		long diffDays = longDiff / (24 * 60 * 60 * 1000);
		
		return diffDays;
	}

}
