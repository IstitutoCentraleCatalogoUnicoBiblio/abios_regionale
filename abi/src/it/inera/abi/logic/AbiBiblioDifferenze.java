package it.inera.abi.logic;

import it.inera.abi.logic.impl.Differenze;

import java.util.List;

/**
 * Classe contenente la logica per il calcolo delle differenze di una biblioteca
 *
 */
public interface AbiBiblioDifferenze {

	public List<Differenze> difference(int idbiblio) throws Exception;
	
}
