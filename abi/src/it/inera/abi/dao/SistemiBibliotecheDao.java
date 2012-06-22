package it.inera.abi.dao;

import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.SistemiBiblioteche;

import java.util.List;

public interface SistemiBibliotecheDao {

	public int countAllSistemiBiblioteche();

	public int countAllSistemiBibliotecheFiltered(String filter);

	public List<SistemiBiblioteche> getSistemiBibliotecheFiltered(
			String filter, int rows, int offset);


	public void addSistemiBiblioteche(String denominazione);

	public void removeSistemiBiblioteche(int id_record);

	
}
