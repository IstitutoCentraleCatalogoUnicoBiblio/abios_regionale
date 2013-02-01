package it.inera.abi.dao;

import it.inera.abi.persistence.SistemiBiblioteche;

import java.util.List;

/** 
 * Interfaccia DAO per l'entità Sistemi di Biblioteche
 *
 */
public interface SistemiBibliotecheDao {

	public int countAllSistemiBiblioteche();

	public int countAllSistemiBibliotecheFiltered(String filter);

	public List<SistemiBiblioteche> getSistemiBibliotecheFiltered(
			String filter, int rows, int offset);

	public void addSistemiBiblioteche(String denominazione);

	public void removeSistemiBiblioteche(int id_record);
	
}
