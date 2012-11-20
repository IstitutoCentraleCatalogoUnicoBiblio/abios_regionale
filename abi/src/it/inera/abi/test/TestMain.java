package it.inera.abi.test;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.persistence.Biblioteca;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe di test per il caricamento delle biblioteche in base a criteri di ricerca
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		BiblioDao biblioDao = (BiblioDao)appCtxt.getBean(BiblioDao.class);
		List<Biblioteca> listaBiblioteche = biblioDao.ricercaBiblio(null, 0, -1, "utenteUltimaModifica", "ASC");
		//System.out.println(listaBiblioteche.size());
		
	}

}
