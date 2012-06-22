package it.inera.abi.test;

import java.util.List;

import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Utenti;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDaoUtenti {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		UtentiDao utentiDao = appCtxt.getBean(UtentiDao.class);
//		System.out.println(utentiDao);
//		utentiDao.removeUtente(7);
//		BiblioDao biblioDao = appCtxt.getBean(BiblioDao.class);
//		int num = biblioDao.getMaxIsilNumero("IT", "MS");
//		System.out.println(num);
//		List<Utenti> utentis =utentiDao.getUsersWithElapsedPasswords();
//		List<Utenti> utentis =utentiDao.getUsersWithTenDaysRemaningRange();
		List<Utenti> utentis =utentiDao.getUsersByRole(5);
	}

}
