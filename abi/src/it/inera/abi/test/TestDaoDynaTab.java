package it.inera.abi.test;

import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.persistence.SistemiBiblioteche;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe di test per il caricamento delle tabelle dinamiche
 *
 */
public class TestDaoDynaTab {

	public static void main(String[] args) {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		DynaTabDao dynaTabDao = appCtxt.getBean(DynaTabDao.class);
		List<?> results = dynaTabDao.listRecordsFilteredForPagination(SistemiBiblioteche.class, "Bibliomisa", 0, 10, null,null);
		System.out.println(results.size());
		
	}
}
