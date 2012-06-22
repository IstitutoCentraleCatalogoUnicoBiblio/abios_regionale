package it.inera.abi.logic.formatodiscambio.imports;

import java.io.Serializable;
import java.util.Vector;

public class ImportFileBean  implements Serializable  {

	private static final long serialVersionUID = 8824121806793222260L;
	
	// caratteristiche file
    public String esito;
    public String fileName;
    public String utente;
    public String email;
    public String dataUpload;
    public String oraUpload;
    public String dimensione;
    public String nbib;
    public String originalFilename;

    // info biblioteche: le info con indici uguali si riferiscono alla solita biblio
    public Vector<InfoBiblioBean> biblioteche = new Vector<InfoBiblioBean>();

    public boolean isError = false;
}
