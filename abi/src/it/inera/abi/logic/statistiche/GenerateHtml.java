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

package it.inera.abi.logic.statistiche;

import it.inera.abi.commons.XslTransformer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.transform.TransformerException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe per la generazione dell'html delle statistiche
 * 
 */
public class GenerateHtml {

	private Log _log = LogFactory.getLog(GenerateHtml.class);
	
	private String statisticheSourceDir;
	private String statisticheTargetDir;

	public GenerateHtml(String statisticheSourceDir, String statisticheTargetDir) {
		this.statisticheSourceDir = statisticheSourceDir;
		this.statisticheTargetDir = statisticheTargetDir;
	}

	public void generate(Hashtable<String, String> results) throws TransformerException, IOException {		
		Hashtable<String, String> vXml = new Hashtable<String, String>();
		Hashtable<String, String> vHtml = new Hashtable<String, String>();
		for (Enumeration<String> e = results.keys(); e.hasMoreElements();) {
			String key = e.nextElement().toString();
			String risultato = results.get(key).toString();

			vXml.put((String) key, (String) risultato);
			String xsltFile2 = "html.xsl";
			String html2 = XslTransformer.transform(risultato, statisticheSourceDir + "/" + xsltFile2);
			_log.debug(html2);
			vHtml.put((String) key, (String) html2);
			String filename = FilenameUtils.concat(statisticheTargetDir, key + ".html");
			scriviFile(filename, html2);
		}

	}

	private void scriviFile(String nome_file, String html) throws IOException {
		FileWriter fileout = new FileWriter(nome_file);
		BufferedWriter filebuf = new BufferedWriter(fileout);
		PrintWriter printout = new PrintWriter(filebuf);
		printout.println(html);
		printout.close();
	}

}
