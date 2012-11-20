package it.inera.abi.logic.formatodiscambio.imports;

import java.io.Serializable;
import java.util.Vector;

/**
 * Classe che rappresenta le informazioni principali di una biblioteca
 * e gli eventuali errori associati
 *
 */
public class InfoBiblioBean implements Serializable {
	
	private static final long serialVersionUID = -9104950074174041194L;
	
	public String nome;
    public String codiceIsil;
    public Vector<String> errori = new Vector<String>();
}