package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi relativi alla località: regioni, province e comuni (lato client)
 * 
 */
@RemoteServiceRelativePath(Abi.LOCATION_SERVICE)
public interface LocationService extends RemoteService {

	public List<RegioniModel> getRegioni();
	public List<RegioniModel> getRegioni(ModelData loadConfig);
	
	public List<ProvinceModel> getProvince(Integer id_regione);
	public List<ProvinceModel> getProvince(Integer id_regione, String ricerca);
	
	public List<ComuniModel> getComuni(int id_provincia);
	public PagingLoadResult<ComuniModel> getComuniPaginati(int id_provOrReg, boolean fromProv, ModelData config);

	public PagingLoadResult<ComuniModel> getComuniByDenominazioneProvinciaFiltered(String provincia, ModelData loadConfig);

	public int countAllStatiFiltered(String filter);

	public PagingLoadResult<StatoModel> getStatiPaginatiFilteredPerCombos(ModelData loadConfig);

	PagingLoadResult<StatoModel> getListaStatiPaginati(PagingLoadConfig loadConfig);

	PagingLoadResult<ComuniModel> getComuniByIdProvincia(Integer idProvincia,PagingLoadConfig loadConfig);

	void assegnaComuniAProvincia(int idProvincia,List<Integer> comuniSelectedIds);
	
	public ProvinceModel getProvincia(String sigla);
	
	public ProvinceModel getProvinciaByIdProvincia(int id);
	
	public RegioniModel getRegione(String sigla);
	
	public RegioniModel getRegioneByIdProvincia(int idProvincia);
	
	public PagingLoadResult<ProvinceModel> getProvincePaginate(ModelData loadConfig);
}
