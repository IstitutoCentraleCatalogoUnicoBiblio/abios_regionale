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

package it.inera.abi.test.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.ImportLogic;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe per il testing dell'import
 *
 */
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
