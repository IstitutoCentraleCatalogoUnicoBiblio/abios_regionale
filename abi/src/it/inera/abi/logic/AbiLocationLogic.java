/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

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
