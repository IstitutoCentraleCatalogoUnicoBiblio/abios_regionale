package it.inera.abi.logic.checkpassword;

import it.inera.abi.persistence.Utenti;

import java.util.Date;
import java.util.List;

/**
 * Classe contenente le operazioni di logica per il controllo e 
 * gestione delle password
 * 
 */
public interface CheckPassword {

	/**
	 * Ritorna la lista degli utenti con password scaduta 
	 * (data_modifica_password>180 giorni)
	 **/
	public List<Utenti> getUsersWithElapsedPasswords();

	/**
	 * Ritorna la lista degli utenti con password in scadenza 
	 * (data_modifica_password <170 e >180 giorni)
	 **/
	public List<Utenti> getUsersWithTenDaysRemaningRange();
	
	/**
	 * Compone l'email con le informazioni relative al'avviso di
	 *  disabilitazione dell'account e richiama il metodo 
	 * Utility.sendEmail(...) per l'invio dell'email
	 * */
	public void sendMailForDisable(Utenti utente);

	/**
	 * Compone l'email con le informazioni relative al'avviso 
	 * di account in scadenza e richiama il metodo 
	 * Utility.sendEmail(...) per l'invio dell'email
	 * */
	public void sendMailForAlert(Utenti utente);

	/**
	 * Richiama il metodo di utentiDao per la isabilitazione dell'account
	 * */
	public void disableAccount(int idUser);

	/**Metodo per la gestione degli utenti con password scadute.*/
	public void manageUsersWithElapsedPasswords();
	
	/**Metodo per la gestione degli utenti con password in scadenza.*/
	public void manageUsersWithTenDaysRemaningRange();
	
	/**
	 * Calcola la differenza in giorni dalla data odierna alla data passata
	 * come paramentro passwordDate (relativa all'ultima modifica della password
	 * dell'utente)
	 * */
	public long getDayDifference(Date passwordDate);
}
