package it.inera.abi.logic;

import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Stato;

import java.util.List;

/**
 * Classe contenente la logica delle principali operazioni riguardanti
 * Stati, Regioni, Province e Comuni
 *
 */
public interface AbiLocationLogic {

	/** Servizi Regioni **/
	public List<Regione> getRegioni();
	public List<Regione> getRegioni(String ricerca);
	
	public int countAll();

	/*******************/
	/** Servizi Province **/
	public List<Provincia> getProvinceByIdRegione(Integer id_regione);
	public List<Provincia> getProvinceFiltered(Integer id_regione, String ricerca);
	/********************/

	/*******************/
	/** Servizi COMuNi **/
	public List<Comune> getComuni(int id_provOrReg);
	public List<Comune> getComuniPaginati(int id_provOrReg, String sigla, boolean fromProv, int start, int limit,String sortField,String sortDir);
	public int getCountComuniPaginati(int id_provOrReg, String sigla, boolean fromProv);
	
	public List<Comune> getComuniByDenominazioneProvinciaFiltered(String provincia, String query,int offset, int rows,String sortField,
			String sortDir);
	
	public List<Comune> getComuniByIdProvinciaFiltered(Integer provincia, String query,int offset, int rows,String sortField,
			String sortDir);
	
	public int countAllByProvinciaAdnFiltered(String provincia, String filter);
	
	public int countAllByIdProvincia(Integer idProvincia, String filter);
	
	public void assegnaComuniAProvincia(int idProvincia,List<Integer> comuniSelectedIds);

	/********************/
	/** Servizi STATI **/
	public int countAllStatiFiltered(String filter);

	public List<Stato> getStatiPaginatiFilteredPerCombos(String filter, int offset,
			int rows, String sortField, String sortDir);
	/********************/

	public Provincia getProvincia(String sigla);
	
	public Provincia getProvinciaByIdProvincia(int id);
	
	public Regione getRegione(String sigla);
	
	public Regione getRegioneByIdProvincia(int idProv);
	
	public int countProvincePaginate(String sigla);
	
	public List<Provincia> getProvincePaginate(String sigla, int start, int limit);
	
	
}
