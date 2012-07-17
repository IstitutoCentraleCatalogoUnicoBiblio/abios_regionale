package it.inera.abi.test.exportdyntab;

import it.inera.abi.logic.exportdynatabs.ExportDynaTabs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestExport {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new FileSystemXmlApplicationContext("file:///media/dati/Project_Inera/workspace_abi/abiregionale/war/WEB-INF/applicationContext.xml");
//		ExportDynaTabs exportDynaTabs = (ExportDynaTabs) appCtxt.getBean(ExportDynaTabs.class);
//		exportDynaTabs.doExport();
//		System.exit(0);
		System.out.println("wait...");
	}


	

}
