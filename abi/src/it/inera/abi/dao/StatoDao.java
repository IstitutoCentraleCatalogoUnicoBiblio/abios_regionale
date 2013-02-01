package it.inera.abi.dao;

import java.util.List;

import it.inera.abi.persistence.Stato;

/** 
 * Interfaccia DAO per l'entità Stato
 *
 */
public interface StatoDao {

	public void saveStato(Stato stato);

	public Stato loadStatoById(int id);

	public Stato loadStatoBySigla(String sigla);

	public Stato loadStatoByDenominazione(String denominazione);

	public List<Stato> getStatiPaginatiFilteredPerCombosFilteredPerCombos(String filter, int offset, int rows, String sortField, String sortDir);

	public int countAllStatiFiltered(String filter);
}
