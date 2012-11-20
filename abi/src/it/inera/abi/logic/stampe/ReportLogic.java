package it.inera.abi.logic.stampe;

import java.util.List;
import java.util.Vector;

import it.inera.abi.persistence.Biblioteca;

/**
 * Interfaccia per la generazione del report delle biblioteche 
 *
 */
public interface ReportLogic {
	
	public Vector<BiblioStampe> retrieveBiblioReport(List<Integer> idbibs); 

}
