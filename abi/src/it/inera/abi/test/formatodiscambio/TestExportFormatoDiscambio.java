package it.inera.abi.test.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.ExportLogic;
import it.inera.abi.logic.formatodiscambio.ImportLogic;
import it.inera.abi.logic.formatodiscambio.exports.ExportBean;
import it.inera.abi.logic.formatodiscambio.exports.Exporter;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe per il testing dell'export
 *
 */
public class TestExportFormatoDiscambio {
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		ExportLogic exportLogic = (ExportLogic) appCtxt.getBean("exportLogic");
		
//		exportLogic.doExport(new String[] {"8042","8043"}, "reschini","r.eschini@inera.it", true); // <-- OK
		
		ExportBean[] exportBeans = exportLogic.browseScheduledExport(); // <-- OK
//		for (int i = 0; i < exportBeans.length; i++) {
//			System.out.println(exportBeans[i].toString());
//			boolean ok =exportLogic.deleteScheduledFile(exportBeans[i].fileName); // <-- OK
//			System.out.println(ok);
//		}
		
//		exportLogic.doExport(new String[] {"8042","8043"}, "reschini", "r.eschini@inera.it", false); // <-- OK
		
		System.out.println("fine");
	}
}
