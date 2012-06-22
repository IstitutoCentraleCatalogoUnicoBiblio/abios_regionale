package it.inera.abi.dao;

import java.util.List;

import it.inera.abi.persistence.Provincia;


public interface ProvinciaDao {
		
		/*DAL FORMATO DI SCAMBIO*/
	public String getSiglaProvinciaByCodIstat(String codIstatProvincia);
	
	public String getDescrizioneProvinciaByCodIstat(String codIstatProvincia);
	
	public Provincia getProvinciaByCodIstat(String codIstatProvincia);

	public List<Provincia> getProvince(Integer id_regione);
	public List<Provincia> getProvince(Integer id_regione, String ricerca);

	public int countAll();

	public Provincia getProvinciaByDenominazione(String denominazione);
	
	public Provincia getProvinciaBySigla(String sigla);
	
	public Provincia getProvinciaByIdProvincia(int id);
	
	public List<Provincia> getProvincePaginate(String sigla, int start, int limit);
	
}
