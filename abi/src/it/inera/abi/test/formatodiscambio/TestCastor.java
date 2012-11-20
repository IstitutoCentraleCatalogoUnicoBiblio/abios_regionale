package it.inera.abi.test.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.CharSetUtils;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Classe per il testing delle classi di castor (import)  
 *
 */
public class TestCastor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File importFile = new File("/home/reschini/Desktop/export-campania-converito/test.xml");
			
			File out = new File("/home/reschini/Desktop/export-campania-converito/test_out.xml");
			
			
			String test = FileUtils.readFileToString(importFile, "ISO-8859-1");
			StringReader sr = new StringReader(test);
			// caricamento delle biblioteche che stanno nel file in un oggetto castor

			Biblioteche biblioteche = null;

			biblioteche = Biblioteche.unmarshal(sr);
			String nome = biblioteche.getBiblioteca(0).getAnagrafica().getNomi().getAttuale();
			System.out.println(nome);
			StringWriter sw = new StringWriter();
			biblioteche.marshal(sw);
			
			FileUtils.writeStringToFile(out, sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
