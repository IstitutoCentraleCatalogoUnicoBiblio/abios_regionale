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

package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>LocationService</code>
 * 
 */
public interface LocationServiceAsync {
	
	void getRegioni(AsyncCallback<List<RegioniModel>> callback);
	void getRegioni(ModelData loadConfig, AsyncCallback<List<RegioniModel>> callback);

	void getProvince(Integer id_regione, AsyncCallback<List<ProvinceModel>> callback);
	void getProvince(Integer id_regione, String ricerca, AsyncCallback<List<ProvinceModel>> callback);

	void getComuni(int id_provincia, AsyncCallback<List<ComuniModel>> callback);
	void getComuniPaginati(int id_provOrReg, boolean fromProv, ModelData config, AsyncCallback<PagingLoadResult<ComuniModel>> callback);

	void getComuniByDenominazioneProvinciaFiltered(String value,ModelData loadConfig, AsyncCallback<PagingLoadResult<ComuniModel>> callback);

	void countAllStatiFiltered(String filter, AsyncCallback<Integer> callback);

	void getStatiPaginatiFilteredPerCombos(	ModelData loadConfig, AsyncCallback<PagingLoadResult<StatoModel>> callback);

	void getListaStatiPaginati(PagingLoadConfig loadConfig,	AsyncCallback<PagingLoadResult<StatoModel>> callback);

	void getComuniByIdProvincia(Integer idProvincia, PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<ComuniModel>> callback);

	void assegnaComuniAProvincia(int idProvincia,List<Integer> comuniSelectedIds, AsyncCallback<Void> asyncCallback);

	void getProvincia(String sigla, AsyncCallback<ProvinceModel> callback);
	
	void getProvinciaByIdProvincia(int id, AsyncCallback<ProvinceModel> callback);
	
	void getRegione(String sigla, AsyncCallback<RegioniModel> callback);
	
	void getRegioneByIdProvincia(int idProvincia, AsyncCallback<RegioniModel> callback);
	
	void getProvincePaginate(ModelData loadConfig, AsyncCallback<PagingLoadResult<ProvinceModel>> callback);
}
