package it.inera.abi.dao;

import java.util.List;

import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.Stato;


public interface EnteDao {
	
	public Ente createEnteIfNotExist(Stato stato, EnteTipologiaAmministrativa tipologiaAmministrativa,
	String denominazione, String asia_asip, String partita_iva, String codice_fiscale);

	public List<Ente> getEntiPaginatiFilteredPerCombos(String filter, int offset,
			int rows);

	public int countAllEntiFiltered(String filter);

	public Ente createEnteIfNotExist2(Stato stato, EnteTipologiaAmministrativa enteTipologiaAmministrativa,
			String denominazione, String asiaAsip, String partitaIva, String codiceFiscale);

	public Ente getEnte(String den, String tipAmm, String stato);
	
	public EnteTipologiaAmministrativa getEnteTipologiaAmministrativa(String descr);

	public Ente retrieveDefaultEnte();
	
}
