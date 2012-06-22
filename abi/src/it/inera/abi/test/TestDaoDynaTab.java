package it.inera.abi.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.UtentiDao;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.Stato;

public class TestDaoDynaTab {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		DynaTabDao dynaTabDao = appCtxt.getBean(DynaTabDao.class);
		List<?> results = dynaTabDao.listRecordsFilteredForPagination(SistemiBiblioteche.class, "Bibliomisa", 0, 10, null,null);
		System.out.println(results.size());
		
	}

}
