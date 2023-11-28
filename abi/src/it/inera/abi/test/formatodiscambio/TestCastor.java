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
