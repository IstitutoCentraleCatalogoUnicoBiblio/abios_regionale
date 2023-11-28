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

package it.inera.abi.commons;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Classe che effettua le trasformazioni xsl
 * 
 */
public final class XslTransformer {

	public static String transform(String body, String xsltPath) throws TransformerException  {
		StringWriter ostr = new StringWriter();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(xsltPath));
		transformer.transform(new StreamSource(new StringReader(body)), new StreamResult(ostr));
		return ostr.toString();
	}
	
	public static void main(String[] args) throws Exception {
		String body = FileUtils.readFileToString(new File("/home/marina/test.xml"));
		System.out.println(transform(body, "/home/marina/workspace_abi/abiapplication/statistiche/TAVOLA01b.xsl"));
	}
}
