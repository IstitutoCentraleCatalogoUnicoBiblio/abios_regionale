package it.inera.abi.test;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.logic.TrasferimentoBiblioteca;
import it.inera.abi.logic.formatodiscambio.ImportLogic;
import it.inera.abi.persistence.Biblioteca;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;;

public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new FileSystemXmlApplicationContext("file:///media/dati/Project_Inera/workspace_abi/abi/war/WEB-INF/applicationContext.xml");
		ImportLogic importLogic = (ImportLogic)appCtxt.getBean(ImportLogic.class);
		importLogic.browseCheckedFileImport();
		System.out.println();
//		List<Biblioteca> listaBiblioteche = biblioDao.ricercaBiblio(null, 0, -1, "utenteUltimaModifica", "ASC");
		//System.out.println(listaBiblioteche.size());
		
	}

}
