
package it.inera.abi.dao;

import it.inera.abi.persistence.Regione;

import java.util.List;


public interface RegioneDao {
	
	/*DAL FORMATO DI SCAMBIO*/
	
	public String getDenominazioneRegioneByCodIstatProvincia(String codIstatProvincia);

	public Regione getRegioneByCodIstatProvincia(String codIstatProvincia);
	
	public List<Regione> getRegioni();
	public List<Regione> getRegioni(String ricerca);
	
	public int countAll();
	
	public Regione getRegione(String sigla);
	
	public Regione getRegioneByIdProvincia(int idProv);
}
