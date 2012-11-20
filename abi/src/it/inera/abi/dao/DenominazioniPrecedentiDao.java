package it.inera.abi.dao;

import it.inera.abi.persistence.DenominazioniPrecedenti;

import java.util.List;

/**
 * Interfaccia DAO per l'entità Denominazioni Precedenti
 *
 */
public interface DenominazioniPrecedentiDao {

	public List<DenominazioniPrecedenti> getDenominazioniPrecedenti(int id_biblioteca);
	
	public void setListaDenominazioniPrecedenti(int id_biblioteca,List<DenominazioniPrecedenti> listaDenominazioni);
	
	public void addDenominazionePrecendente(DenominazioniPrecedenti denominazione);

	public void removeDenominazionePrecedente(int id_record);
}
