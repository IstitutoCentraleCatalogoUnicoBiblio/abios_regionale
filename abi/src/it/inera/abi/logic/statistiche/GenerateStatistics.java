package it.inera.abi.logic.statistiche;

import it.inera.abi.commons.XslTransformer;
import it.inera.abi.logic.statistiche.xml.GenerateGlobalXML;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe per la generazione delle statistiche
 *
 */
public class GenerateStatistics {

	private Log _log = LogFactory.getLog(GenerateStatistics.class);
	
	private GenerateGlobalXML generateGlobalXML = null;
	private String statisticheSourceDir = null;
	private String statisticheTargetDir = null;

	public void setGenerateGlobalXML(GenerateGlobalXML generateGlobalXML) {
		this.generateGlobalXML = generateGlobalXML;
	}
	public void setStatisticheSourceDir(String statisticheSourceDir) {
		this.statisticheSourceDir = statisticheSourceDir;
	}
	public void setStatisticheTargetDir(String statisticheTargetDir) {
		this.statisticheTargetDir = statisticheTargetDir;
	}


	public void generate() throws Exception {
		_log.info("Inizio generazione statistiche");
		try {
			// Genera gli xml dai resultset delle query
			Hashtable<String, Vector<String>> v = generateGlobalXML.generateAllXML();


			// Merge *TUTTI* i risultati in un unico xml per ogni resultset
			Hashtable<String, String> results = new Hashtable<String, String>();
			for (Enumeration<String> e = v.keys(); e.hasMoreElements();) {
				String temp = (String) e.nextElement();
				String[] t = temp.split("_");
				String key = t[0];
				_log.debug("\nTAVOLA PROCESSATA ----->" + key + "\n");
				Vector<String> tempResults = v.get(key);
				String risultato = null;
				if(!temp.equals("GEOREFERENZIAZIONE01")){
					if (tempResults.size() > 1) {
						risultato = mergeQuery(tempResults);
						_log.debug(risultato);
					} else {
						risultato = (String) tempResults.get(0);
						_log.debug(risultato);
					} 
					String xsltFile = key + ".xsl";
					if(risultato.indexOf("&")!=-1)
						risultato = risultato.replaceAll("&", "&amp;");
					String finalXML = XslTransformer.transform(risultato, statisticheSourceDir + "/" + xsltFile);
					
					_log.debug(finalXML);
					if(!key.equals("GEOREFERENZIAZIONE01")){
						results.put(key, finalXML);
					}
				}
			}

			GenerateHtml gh = new GenerateHtml(statisticheSourceDir, statisticheTargetDir);
			gh.generate(results);

			GenerateExcel ge = new GenerateExcel(statisticheTargetDir);
			ge.generate(results);

		} catch (Exception e) {
			_log.error("---",  e);
			throw new Exception(e);
		}	
		_log.debug("--- FINE GENERAZIONE STATISTICHE ---");
	}

	
	public String mergeQuery(Vector<String> q){
		String ris = "";
		String[] t = q.get(0).toString().split("</ROWSET>");
		String[] t1 = t[0].split("<ROWSET>");
		ris = t1[0] + "\n<RESULT>" + "\n<ROWSET>" + t1[1] + "\n</ROWSET>"; //intestazione e risultati prima query
		for (int i = 1; i < q.size(); i++) {
			String s = q.get(i);
			_log.debug(s);
			String[] tok = s.split("<ROWSET>"); //levo l'intestazione della query i-esima
			ris = ris.concat("\n<ROWSET>" +tok[1]);
		}
		return(ris + "\n</RESULT>");
	}
	
	//	public static  void toHTMLFile(String xml, String nome_file){
	//		String xslfile= null; //configurazione Mettere il path
	//		if (xslfile != null) {
	//			//    XslTransformer tr = TransformerFactory.getTransformer();
	//			//    String html = tr.transform(xml, xslfile);
	//			return;
	//		}
	//
	//	}

	public void toXMLFile(String xml, String nome_file){

	}
}