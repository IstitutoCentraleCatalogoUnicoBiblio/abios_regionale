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
