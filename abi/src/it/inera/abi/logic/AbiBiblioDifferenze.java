package it.inera.abi.logic;

import it.inera.abi.logic.impl.Differenze;

import java.util.List;

public interface AbiBiblioDifferenze {

	public List<Differenze> difference(int idbiblio) throws Exception;
	
}
