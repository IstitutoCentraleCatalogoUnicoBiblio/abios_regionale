package it.inera.abi.logic.checkpassword;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe contenente il job per il controllo della scadenza delle password
 *
 */
public class CheckPasswordJob {

	private @Autowired CheckPassword checkPassword;
	
	private Log log = LogFactory.getLog(CheckPasswordJob.class);
	
	public void execute() throws Exception {
		log.info("Start job per check password utenti in data "+new Date(System.currentTimeMillis()));
		
		checkPassword.manageUsersWithTenDaysRemaningRange();
		
		checkPassword.manageUsersWithElapsedPasswords();
		
		log.info("Check password utenti completato!");
	}
}
