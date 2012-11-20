package it.inera.abi.test;

import it.inera.abi.logic.statistiche.GenerateStatistics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Classe di test per la generazione delle statistiche
 *
 */
public class TestStatistiche {

	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new FileSystemXmlApplicationContext("file:///media/dati/Project_Inera/workspace_abi/abi/war/WEB-INF/applicationContext.xml");
		GenerateStatistics generateStatistics = (GenerateStatistics)appCtxt.getBean("generateStatistics");
		generateStatistics.generate();
		System.out.println();
	}
	
}
