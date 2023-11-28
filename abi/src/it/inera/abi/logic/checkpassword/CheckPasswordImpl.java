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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Classe contenente le implementazioni delle operazioni di logica 
 * per il controllo e gestione delle password
 * 
 */
@Service
public class CheckPasswordImpl implements CheckPassword {

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
