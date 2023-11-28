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

package it.inera.abi.logic.impl;

import it.inera.abi.dao.ComuneDao;
import it.inera.abi.dao.ProvinciaDao;
import it.inera.abi.dao.RegioneDao;
import it.inera.abi.dao.StatoDao;
import it.inera.abi.logic.AbiLocationLogic;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Stato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 * Classe che implementa la logica delle principali operazioni riguardanti
 * Stati, Regioni, Province e Comuni
 *
 */
@Service
public class AbiLocationLogicImpl implements AbiLocationLogic {

	private StatoDao statoDao;

	@Autowired
	@Required
	public void setStatoDao(StatoDao statoDao) {
		this.statoDao = statoDao;
	}

	private RegioneDao regioneDao;

	@Autowired
	@Required
	public void setRegioneDao(RegioneDao regioneDao) {
		this.regioneDao = regioneDao;
	}

	private ProvinciaDao provinciaDao;

	@Autowired
	@Required
	public void setProviciaDao(ProvinciaDao provinciaDao) {
		this.provinciaDao = provinciaDao;
	}

	private ComuneDao comuneDao;

	@Autowired
	@Required
	public void setComuneDao(ComuneDao comuneDao) {
		this.comuneDao = comuneDao;
	}

	@Override
	public List<Regione> getRegioni() {
		return regioneDao.getRegioni();

	}
	
	@Override
	public List<Regione> getRegioni(String ricerca) {
		return regioneDao.getRegioni(ricerca);

	}
	@Override
	public int countAll() {

		return regioneDao.countAll();
	}


	@Override
	public List<Comune> getComuni(int id_provincia) {

		return comuneDao.getComuniByIdProvincia(id_provincia);
	}
	
	@Override
	public List<Comune> getComuniPaginati(int id_provOrReg, String sigla, boolean fromProv, int start, int limit,String sortField,
			String sortDir) {

		return comuneDao.getComuniFiltered(id_provOrReg, fromProv, sigla, start, limit, sortField , sortDir);
	}
	
	@Override
	public int getCountComuniPaginati(int id_provOrReg, String sigla, boolean fromProv) {
		return comuneDao.getCountComuniPaginati(id_provOrReg, sigla, fromProv);
	}

	@Override
	public List<Comune> getComuniByIdProvinciaFiltered(Integer idProvincia,
			String query, int offset, int rows,String sortField,String sortDir) {
		return comuneDao.getComuniFiltered(idProvincia, true, query, offset, rows,sortField , sortDir);
	}
	
	@Override
	public List<Comune> getComuniByDenominazioneProvinciaFiltered(String provincia, String filter, int offset, int rows,String sortField,
			String sortDir) {
		Provincia p = provinciaDao.getProvinciaByDenominazione(provincia);
		if (p != null)
			return comuneDao.getComuniFiltered(p.getIdProvincia(), true, filter, offset, rows,sortField,sortDir);
		else 
			return comuneDao.getComuniFiltered(null, true, filter,offset, rows, sortField , sortDir);
	}

	@Override
	public int countAllByProvinciaAdnFiltered(String provincia, String filter) {
		Provincia p = provinciaDao.getProvinciaByDenominazione(provincia);
		if (p != null)
			return comuneDao.countAllByProvinciaAdnFiltered(p.getIdProvincia(), filter);
		else 
			return comuneDao.countAllByProvinciaAdnFiltered(null, filter);
			
	}
	
	@Override
	public int countAllByIdProvincia(Integer idProvincia, String filter) {
		return comuneDao.countAllByProvinciaAdnFiltered(idProvincia, filter);
	}
	@Override
	public List<Stato> getStatiPaginatiFilteredPerCombos(String filter,
			int offset, int rows, String sortField, String sortDir) {
		return statoDao.getStatiPaginatiFilteredPerCombosFilteredPerCombos(filter, offset, rows,sortField, sortDir);
	}
	@Override
	public int countAllStatiFiltered(String filter) {
		return statoDao.countAllStatiFiltered(filter);
	}

	@Override
	public List<Provincia> getProvinceByIdRegione(Integer id_regione) {
		return provinciaDao.getProvince(id_regione);
	}
	
	@Override
	public List<Provincia> getProvinceFiltered(Integer id_regione, String ricerca) {
		return provinciaDao.getProvince(id_regione, ricerca);
	}
	
	@Override
	public void assegnaComuniAProvincia(int idProvincia, List<Integer> comuniSelectedIds) {
		comuneDao.assegnaComuniAProvincia(idProvincia,comuniSelectedIds);
	}
	
	@Override
	public Provincia getProvincia(String sigla) {
		return provinciaDao.getProvinciaBySigla(sigla);
	}
	
	@Override
	public Provincia getProvinciaByIdProvincia(int id) {
		return provinciaDao.getProvinciaByIdProvincia(id);
	}
	
	@Override
	public Regione getRegione(String sigla) {
		return regioneDao.getRegione(sigla);
	}
	@Override
	public Regione getRegioneByIdProvincia(int idProv) {
		return regioneDao.getRegioneByIdProvincia(idProv);
	}

	@Override
	public int countProvincePaginate(String query) {
		List<Provincia> list = getProvincePaginate(query, 0, -1);
		if (list != null && list.size() > 0) {
			return list.size();
		}
		else return 0;
			
	}
	
	@Override
	public List<Provincia> getProvincePaginate(String query, int start, int limit) {
		return provinciaDao.getProvincePaginate(query, start, limit);
	}
	
}
