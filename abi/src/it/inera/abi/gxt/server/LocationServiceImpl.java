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

package it.inera.abi.gxt.server;

import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;
import it.inera.abi.gxt.client.services.LocationService;
import it.inera.abi.logic.AbiLocationLogic;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Stato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 * Implementazione dei servizi relativi alla località: regioni, province e comuni (lato server)
 * 
 */
public class LocationServiceImpl extends AutoinjectingRemoteServiceServlet
		implements LocationService {

	private static final long serialVersionUID = -2490919853033619709L;
	private AbiLocationLogic abiLocationLogic;

	@Autowired
	@Required
	public void setAbiRegioniService(AbiLocationLogic abiLocationLogic) {
		this.abiLocationLogic = abiLocationLogic;
	}

	public LocationServiceImpl() {
	}
	
	@Override
	public List<RegioniModel> getRegioni() {
		/* LOAD */
		List<Regione> regioniDB = abiLocationLogic.getRegioni();
		List<RegioniModel> regioniModel = new ArrayList<RegioniModel>();

		Iterator<Regione> it = regioniDB.iterator();

		/* MAPPING */
		RegioniModel regioniModelTmp = null;
		Regione regione = null;
		while (it.hasNext()) {
			regione = (Regione) it.next();
			regioniModelTmp = new RegioniModel();

			regioniModelTmp.setIdRegione(regione.getIdRegione());
			regioniModelTmp.setIdStato(regione.getStato().getIdStato());
			regioniModelTmp.setStatoDenominazione(regione.getStato().getDenominazione());
			regioniModelTmp.setSigla(regione.getSigla());
			regioniModelTmp.setDenominazione(regione.getDenominazione());

			regioniModel.add(regioniModelTmp);
		}

		// return regioniModel;
		return regioniModel;
	}
	
	
	@Override
	public List<RegioniModel> getRegioni(ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;
		
		String query = (String) m.get("query");	
		/* LOAD */
		List<Regione> regioniDB = abiLocationLogic.getRegioni(query);
		List<RegioniModel> regioniModel = new ArrayList<RegioniModel>();

		Iterator<Regione> it = regioniDB.iterator();

		/* MAPPING */
		RegioniModel regioniModelTmp = null;
		Regione regione = null;
		while (it.hasNext()) {
			regione = (Regione) it.next();
			regioniModelTmp = new RegioniModel();

			regioniModelTmp.setIdRegione(regione.getIdRegione());
			regioniModelTmp.setIdStato(regione.getStato().getIdStato());
			regioniModelTmp.setStatoDenominazione(regione.getStato().getDenominazione());
			regioniModelTmp.setSigla(regione.getSigla());
			regioniModelTmp.setDenominazione(regione.getDenominazione());

			regioniModel.add(regioniModelTmp);
		}

		// return regioniModel;
		return regioniModel;
	}

	@Override
	public List<ProvinceModel> getProvince(Integer id_regione) {

		/* LOAD */
		List<Provincia> provinceDB=null;
		provinceDB = abiLocationLogic.getProvinceByIdRegione(id_regione);
		List<ProvinceModel> provinceModel = new ArrayList<ProvinceModel>();

		Iterator<Provincia> it = provinceDB.iterator();

		/* MAPPING */
		ProvinceModel provinceModelTmp = null;
		Provincia provincia = null;
		while (it.hasNext()) {
			provincia = (Provincia) it.next();
			provinceModelTmp = new ProvinceModel();

			provinceModelTmp.setIdProvincia(provincia.getIdProvincia());
			provinceModelTmp
					.setIdRegione(provincia.getRegione().getIdRegione());
			provinceModelTmp.setSigla(provincia.getSigla());
			provinceModelTmp.setDenominazione(provincia.getDenominazione());
			provinceModelTmp.setCodiceIstat(provincia.getCodiceIstat());
			provinceModelTmp.setRegioneDenominazione(provincia.getRegione().getDenominazione());
			provinceModelTmp.setIdRegione(provincia.getRegione().getIdRegione());
			provinceModel.add(provinceModelTmp);
		}

		// return regioniModel;
		return provinceModel;
	}
	
	@Override
	public List<ProvinceModel> getProvince(Integer id_regione, String ricerca) {

		/* LOAD */
		List<Provincia> provinceDB = null;
		provinceDB = abiLocationLogic.getProvinceFiltered(id_regione, ricerca);
		List<ProvinceModel> provinceModel = new ArrayList<ProvinceModel>();

		Iterator<Provincia> it = provinceDB.iterator();

		/* MAPPING */
		ProvinceModel provinceModelTmp = null;
		Provincia provincia = null;
		while (it.hasNext()) {
			provincia = (Provincia) it.next();
			provinceModelTmp = new ProvinceModel();

			provinceModelTmp.setIdProvincia(provincia.getIdProvincia());
			provinceModelTmp
					.setIdRegione(provincia.getRegione().getIdRegione());
			provinceModelTmp.setSigla(provincia.getSigla());
			provinceModelTmp.setDenominazione(provincia.getDenominazione());
			provinceModelTmp.setCodiceIstat(provincia.getCodiceIstat());
			provinceModelTmp.setRegioneDenominazione(provincia.getRegione().getDenominazione());
			provinceModelTmp.setIdRegione(provincia.getRegione().getIdRegione());
			provinceModel.add(provinceModelTmp);
		}

		// return regioniModel;
		return provinceModel;
	}
	
	@Override
	public List<ComuniModel> getComuni(int id_provOrReg) {
		List<Comune> comuniDB = abiLocationLogic.getComuni(id_provOrReg);
		Iterator<Comune> itDB = comuniDB.iterator();

		Comune comuneDB = null;
		ComuniModel tmpComuniModel = null;

		List<ComuniModel> comuniModel = new ArrayList<ComuniModel>();
		while (itDB.hasNext()) {
			comuneDB = (Comune) itDB.next();
			tmpComuniModel = new ComuniModel();
			tmpComuniModel.setIdComune(comuneDB.getIdComune());
			tmpComuniModel.setDenominazione(comuneDB.getDenominazione());
			tmpComuniModel.setIdProvincia(comuneDB.getProvincia()
					.getIdProvincia());
			tmpComuniModel.setCodiceIstat(comuneDB.getCodiceIstat());
			tmpComuniModel.setCodiceFinanze(comuneDB.getCodiceFinanze());
			comuniModel.add(tmpComuniModel);

		}
		return comuniModel;

	}
	
	@Override
	public PagingLoadResult<ComuniModel> getComuniPaginati(int id_provOrReg, boolean fromProv, ModelData config) {
		ModelData m = (ModelData) config;

		String sigla = (String) m.get("query");		
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		
		PagingLoadConfig loadConfig=(PagingLoadConfig)config;
		 
		String sortDir=(String)(loadConfig.getSortInfo().getSortDir()!=null?loadConfig.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(loadConfig.getSortInfo().getSortField()!=null?loadConfig.getSortInfo().getSortField().toString():null);
		
		/* LOAD DATI */
		List<Comune> comuniDB = abiLocationLogic.getComuniPaginati(id_provOrReg, sigla, fromProv, start, limit,sortField ,sortDir);
		int countAll = abiLocationLogic.getCountComuniPaginati(id_provOrReg, sigla, fromProv);
		
		List<ComuniModel> list = new ArrayList<ComuniModel>();
		
		for (Comune entry : comuniDB) {
			ComuniModel c = new ComuniModel();
			c.setIdComune(entry.getIdComune());
			c.setDenominazione(entry.getDenominazione());
			c.setIdProvincia(entry.getProvincia().getIdProvincia());
			c.setCodiceIstat(entry.getCodiceIstat());
			c.setCodiceFinanze(entry.getCodiceFinanze());
			
			list.add(c);
		}
			
		return new BasePagingLoadResult<ComuniModel>(list, start, countAll);

	}


	@Override
	public PagingLoadResult<ComuniModel> getComuniByDenominazioneProvinciaFiltered(
			String provincia, ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
				
		PagingLoadConfig config=(PagingLoadConfig)loadConfig;
		 
		String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);
		
		
		int countAll = abiLocationLogic.countAllByProvinciaAdnFiltered(provincia, query);
		List<Comune> comuniDB = abiLocationLogic.getComuniByDenominazioneProvinciaFiltered(provincia, query, start, limit,sortField, sortDir);
		Iterator<Comune> itDB = comuniDB.iterator();

		Comune comuneDB = null;
		ComuniModel tmpComuniModel = null;

		List<ComuniModel> sublist = new ArrayList<ComuniModel>();
		while (itDB.hasNext()) {
			comuneDB = (Comune) itDB.next();
			tmpComuniModel = new ComuniModel();
			tmpComuniModel.setIdComune(comuneDB.getIdComune());
			tmpComuniModel.setDenominazione(comuneDB.getDenominazione());
			tmpComuniModel.setIdProvincia(comuneDB.getProvincia()
					.getIdProvincia());
			tmpComuniModel.setCodiceIstat(comuneDB.getCodiceIstat());
			tmpComuniModel.setCodiceFinanze(comuneDB.getCodiceFinanze());

			sublist.add(tmpComuniModel);

		}
		return new BasePagingLoadResult<ComuniModel>(sublist, start, countAll);

	}

	
	@Override
	public PagingLoadResult<ComuniModel> getComuniByIdProvincia(Integer idProvincia, PagingLoadConfig loadConfig) {
		Integer idProvincia2=null;
		try{
		idProvincia2=(Integer)loadConfig.get("idProvincia");
		}catch (Exception e) {
			
		}
		
		String sortDir=(String)(loadConfig.getSortInfo().getSortDir()!=null?loadConfig.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(loadConfig.getSortInfo().getSortField()!=null?loadConfig.getSortInfo().getSortField().toString():null);
		
		int limit = loadConfig.getLimit();
		int start = loadConfig.getOffset();

		int countAll = abiLocationLogic.countAllByIdProvincia(idProvincia2, null);
		
		
		List<Comune> comuniDB = abiLocationLogic.getComuniByIdProvinciaFiltered(idProvincia2, null, start, limit, sortField, sortDir);
	
		Iterator<Comune> itDB = comuniDB.iterator();

		Comune comuneDB = null;
		ComuniModel tmpComuniModel = null;

		List<ComuniModel> sublist = new ArrayList<ComuniModel>();
		while (itDB.hasNext()) {
			comuneDB = (Comune) itDB.next();
			tmpComuniModel = new ComuniModel();
			tmpComuniModel.setIdComune(comuneDB.getIdComune());
			tmpComuniModel.setDenominazione(comuneDB.getDenominazione());
			tmpComuniModel.setIdProvincia(comuneDB.getProvincia().getIdProvincia());
			tmpComuniModel.setDenominazioneProvincia(comuneDB.getProvincia().getDenominazione());
			tmpComuniModel.setCodiceIstat(comuneDB.getCodiceIstat());
			tmpComuniModel.setCodiceFinanze(comuneDB.getCodiceFinanze());

			sublist.add(tmpComuniModel);

		}
		return new BasePagingLoadResult<ComuniModel>(sublist, start, countAll);

	}
	


	@Override
	public PagingLoadResult<StatoModel> getStatiPaginatiFilteredPerCombos(ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		int countAll = abiLocationLogic.countAllStatiFiltered(query);
		List<Stato> stati = abiLocationLogic.getStatiPaginatiFilteredPerCombos(
				query, start, limit, null, null);
		List<StatoModel> sublist = new ArrayList<StatoModel>();
		Iterator<Stato> its = stati.iterator();
		while (its.hasNext()) {
			Stato stato = (Stato) its.next();
			StatoModel statoModel = new StatoModel();

			statoModel.setIdStato(stato.getIdStato());
			statoModel.setDenominazione(stato.getDenominazione());

			statoModel.setSigla(stato.getSigla());
			sublist.add(statoModel);
		}
		
		return new BasePagingLoadResult<StatoModel>(sublist, start, countAll);

	}
	
	@Override
	public PagingLoadResult<StatoModel> getListaStatiPaginati(PagingLoadConfig loadConfig) {
		int limit = (Integer)loadConfig.getLimit();
		int start = (Integer)loadConfig.getOffset();
		
		String sortDir=(String)(loadConfig.getSortInfo().getSortDir()!=null?loadConfig.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(loadConfig.getSortInfo().getSortField()!=null?loadConfig.getSortInfo().getSortField().toString():null);


		int countAll = abiLocationLogic.countAllStatiFiltered(null);
		List<Stato> stati = abiLocationLogic.getStatiPaginatiFilteredPerCombos(null, start, limit, sortField, sortDir);
		List<StatoModel> sublist = new ArrayList<StatoModel>();
		Iterator<Stato> its = stati.iterator();
		while (its.hasNext()) {
			Stato stato = (Stato) its.next();
			StatoModel statoModel = new StatoModel();

			statoModel.setIdStato(stato.getIdStato());
			statoModel.setDenominazione(stato.getDenominazione());

			statoModel.setSigla(stato.getSigla());
			sublist.add(statoModel);
		}
		
		return new BasePagingLoadResult<StatoModel>(sublist, start, countAll);
	}


	@Override
	public int countAllStatiFiltered(String filter) {
		return abiLocationLogic.countAllStatiFiltered(filter);
	}

	@Override
	public void assegnaComuniAProvincia(int idProvincia,List<Integer> comuniSelectedIds) {
		abiLocationLogic.assegnaComuniAProvincia( idProvincia, comuniSelectedIds);
		
	}
	
	@Override
	public ProvinceModel getProvincia(String sigla) {
		ProvinceModel prov = new ProvinceModel();
		
		Provincia p = abiLocationLogic.getProvincia(sigla);
		if (p != null) {
			prov.setIdProvincia(p.getIdProvincia());
			prov.setDenominazione(p.getDenominazione());
			prov.setIdRegione(p.getRegione().getIdRegione());
			prov.setSigla(p.getSigla());
			prov.setCodiceIstat(p.getCodiceIstat());
			prov.setRegioneDenominazione(p.getRegione().getDenominazione());
			prov.setStatoDenominazione(p.getRegione().getStato().getDenominazione());
			
			return prov;
		}
		else return null;
		
	}
	
	@Override
	public ProvinceModel getProvinciaByIdProvincia(int id) {
		ProvinceModel prov = new ProvinceModel();
		
		Provincia p = abiLocationLogic.getProvinciaByIdProvincia(id);
		if (p != null) {
			prov.setIdProvincia(p.getIdProvincia());
			prov.setDenominazione(p.getDenominazione());
			prov.setIdRegione(p.getRegione().getIdRegione());
			prov.setSigla(p.getSigla());
			prov.setCodiceIstat(p.getCodiceIstat());
			prov.setRegioneDenominazione(p.getRegione().getDenominazione());
			prov.setStatoDenominazione(p.getRegione().getStato().getDenominazione());
			
			return prov;
		}
		else return null;
		
	}
	
	@Override
	public RegioniModel getRegione(String sigla) {
		RegioniModel reg = new RegioniModel();
		
		Regione r = abiLocationLogic.getRegione(sigla);
		if (r != null) {
			reg.setIdRegione(r.getIdRegione());
			reg.setDenominazione(r.getDenominazione());
			reg.setIdStato(r.getStato().getIdStato());
			reg.setSigla(r.getSigla());
			
			return reg;
		}
		else return null;
	}
	
	@Override
	public RegioniModel getRegioneByIdProvincia(int idProv) {
		RegioniModel reg = new RegioniModel();
		
		Regione r = abiLocationLogic.getRegioneByIdProvincia(idProv);
		if (r != null) {
			reg.setIdRegione(r.getIdRegione());
			reg.setDenominazione(r.getDenominazione());
			reg.setIdStato(r.getStato().getIdStato());
			reg.setSigla(r.getSigla());
			
			return reg;
		}
		else return null;
	}
	
	@Override
	public PagingLoadResult<ProvinceModel> getProvincePaginate(ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");		
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		
		int countAll = abiLocationLogic.countProvincePaginate(query);

		List<Provincia> sublist = abiLocationLogic.getProvincePaginate(query, start, limit);
		List<ProvinceModel> list = new ArrayList<ProvinceModel>();
		
		for (Provincia entry : sublist) {
			ProvinceModel p = new ProvinceModel();
			p.setIdProvincia(entry.getIdProvincia());
			p.setSigla(entry.getSigla());
			p.setDenominazione(entry.getDenominazione());
			list.add(p);
		}
			
		return new BasePagingLoadResult<ProvinceModel>(list, start, countAll);
	}
}
