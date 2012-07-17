package it.inera.abi.logic.formatodiscambio.imports;

import java.io.Serializable;
import java.util.Vector;

public class InfoBiblioBean  implements Serializable  {
	
	private static final long serialVersionUID = -9104950074174041194L;
	
	public String nome;
    public String codiceIsil;
    public Vector<String> errori = new Vector<String>();
}