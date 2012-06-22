package it.inera.abi.tools;

import it.inera.abi.logic.statistiche.GenerateStatistics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class GeneraStatistiche {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new FileSystemXmlApplicationContext("file:///" + args[0] + "/WEB-INF/applicationContext.xml");
		GenerateStatistics generateStatistics = (GenerateStatistics) appCtxt.getBean("generateStatistics");
		generateStatistics.generate();
		System.exit(0);
	}

}
