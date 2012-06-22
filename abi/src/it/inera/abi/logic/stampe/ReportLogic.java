package it.inera.abi.logic.stampe;

import java.util.List;
import java.util.Vector;

import it.inera.abi.persistence.Biblioteca;

public interface ReportLogic {
	
	public Vector<BiblioStampe> retrieveBiblioReport(List<Integer> idbibs); 

}
