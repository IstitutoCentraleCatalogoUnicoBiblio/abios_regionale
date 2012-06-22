package it.inera.abi.dao;

import java.util.List;

import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;

public interface ComuneDao {
	
	public void saveComune(Comune comune);
	
	/*DAL FORMATO DI SCAMBIO*/
	public Comune getComuneByCodIstat(String codIstatComune);

	public String getDenominazioneComuneByCodIstat(String codIstatComune);
	
	public String getDenominazioneProvinciaByCodIstatComune(String codIstatComune);
	
	public String getDenominazioneRegioneByCodIstatComune(String codIstatComune);

	public List<Comune> getComuniByIdProvincia(int id_provincia);

	public List<Comune> getComuniFiltered(Integer id_provOrReg, boolean fromProv, String filter, int offset, int rows, String sortField,
			String sortDir);
	
	public int getCountComuniPaginati(int idProvOrReg, String sigla, boolean fromProv);

	public int countAllByProvinciaAdnFiltered(Integer idProvincia, String filter);

	void assegnaComuniAProvincia(int idProvincia, List<Integer> idComunis);

	
}
