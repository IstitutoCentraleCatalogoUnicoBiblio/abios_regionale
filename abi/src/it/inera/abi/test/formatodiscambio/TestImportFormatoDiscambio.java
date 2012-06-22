package it.inera.abi.test.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.ImportLogic;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestImportFormatoDiscambio {
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		ImportLogic importLogic = (ImportLogic) appCtxt.getBean("importLogic");
		//ImportFileBean[] importFileBeans = importLogic.browseCheckedFileImport(); // <-- TESTATO OK -----
//		ImportFileBean[] importFileBeans = importLogic.browseScheduledImport(); // <-- TESTATO OK ---
		boolean ok = importLogic.deleteUncheckedFile("iccu-ag_1271949208217_andrea.giuliano@beniculturali.it.xml");
		System.out.println(ok);
		//importFileBeans = importLogic.browseUncheckedFileImport(); // <-- TESTATO OK -----
		//boolean ok = importLogic.deleteFile("ICCU_1286288438066_v.gidaro@iccu.sbn.it.xml"); // <-- TESTATO OK --
		//boolean ok = importLogic.deleteScheduledFile("ICCU_1286288438066_v.gidaro@iccu.sbn.it.xml", false); // <-- TESTATO OK ----
		//importLogic.checkFile("reny_1290079813266_r.eschini@inera.it.xml");  // <-- TESTATO OK ----
		
		
		//ImportFileBean importFileBean = importLogic.dettaglioChecked("ICCU_1286288438066_v.gidaro@iccu.sbn.it.xml");// <-- TESTATO OK
//		importLogic.importFileDifferito("reny_1290079813266_r.eschini@inera.it.xml");  // <-- TESTATO OK
//		importLogic.dettaglioScheduled("ICCU_1286288438066_v.gidaro@iccu.sbn.it.xml"); // <-- TESTATO OK
//		importLogic.importFile("reny_1290079813266_r.eschini@inera.it.xml");
		//importLogic.upload("reny", "r.eschini@inera.it", "/home/reschini/Desktop/toupload.xml"); // <-- TESTATO OK
		System.out.println("fine");
	}
}
