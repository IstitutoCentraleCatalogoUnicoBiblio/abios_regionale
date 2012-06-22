package it.inera.abi.test.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

public class TestCastor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File importFile = new File("/home/marina/Scrivania/1309266042222_part0.xml");

		// caricamento delle biblioteche che stanno nel file in un oggetto castor
		Biblioteche biblioteche = null;
		try {
			biblioteche = Biblioteche.unmarshal(new FileReader(importFile));
			biblioteche.marshal(new PrintWriter(System.out));
			System.out.println(biblioteche);
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
