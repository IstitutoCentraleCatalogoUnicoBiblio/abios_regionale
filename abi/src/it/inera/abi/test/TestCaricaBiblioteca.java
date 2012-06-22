package it.inera.abi.test;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.logic.AbiBiblioLogic;
import it.inera.abi.persistence.Biblioteca;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCaricaBiblioteca {
	
	public static void main(String[] args) {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		AbiBiblioLogic abiBiblioLogic = appCtxt.getBean(AbiBiblioLogic.class);
		abiBiblioLogic.setInRevisione(896, false);
		System.out.println();
	}
	
}
