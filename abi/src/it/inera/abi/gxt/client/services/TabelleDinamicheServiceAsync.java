package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.CataloghiUrlModel;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PatrimoniCategorieTabelleDinamicheModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.models.CataloghiCollettiviModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TabelleDinamicheServiceAsync {

	void getTabellaDinamicaVoceSingolaList(PagingLoadConfig config, int tipo,
			AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback);

	void getListaVoci(int id_tabella,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void getListaVociFiltratePerPaginazioneCombobox(int id_tabella,
			ModelData loadConfig,
			AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback);

	void getTipologiePatrimonioFiltratePerPaginazioneCombobox(
			ModelData loadConfig,
			AsyncCallback<PagingLoadResult<PatrimonioSpecializzazioneModel>> callback);
	
	void getPatrimonioLibrarioPaginatoClassificatoPerCategorie(ModelData loadConfig,
			AsyncCallback<PagingLoadResult<PatrimonioSpecializzazioneModel>> callback);

	void getDeweyFiltratePerPaginazioneCombobox(ModelData loadConfig,
			AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback);

	void getListaCataloghiCollettiviPossibiliPerPaginazioneCombobox(
			ModelData loadConfig,
			AsyncCallback<PagingLoadResult<PartecipaCataloghiCollettiviModel>> callback);
	
	void getAllCataloghiColettivi(ModelData loadConfig, AsyncCallback<PagingLoadResult<PartecipaCataloghiCollettiviModel>> callback);
	

	void addCataloghiMaterialeUrl(CataloghiUrlModel newUrl,
			boolean modificaUrl,int tipoCatalogo, AsyncCallback<Void> asyncCallback);

	void removeCataloghiMaterialeUrl(int id_removeUrl,int tipoCatalogo,
			AsyncCallback<Void> asyncCallback);

	void getCataloghiUrls(int tipoCatalogo, int idPartecipaCatalogo,
			AsyncCallback<List<CataloghiUrlModel>> callback);

	void getMaterialiEsclusiByIdPrestitoLocale(Integer idPrestitoLocale,AsyncCallback<List<VoceUnicaModel>> callback);

	void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale,AsyncCallback<Void> asyncCallback);

	void removePrestitoLocaleMaterialeEscluso(int id_remove,Integer idPrestitoLocale, AsyncCallback<Void> asyncCallback);

	void getUtentiAmmessiByIdPrestitoLocale(Integer idPrestitoLocale,AsyncCallback<List<VoceUnicaModel>> callback);

	void addUtenteAmmessoAlPrestitoLocale(Integer idRecord,	Integer idPrestitoLocale, AsyncCallback<Void> asyncCallback);

	void removeUtenteAmmessoAlPrestitoLocale(int id_remove,	Integer idPrestitoLocale, AsyncCallback<Void> asyncCallback);

	void addEntryTabellaDinamicaVoceSingola(VoceUnicaModel nuovaVoce,boolean modifica, AsyncCallback<Void> asyncCallback);

	void removeEntryTabellaDinamicaVoceSingola(int id_toRemove,	int tipoTabella, AsyncCallback<Void> asyncCallback);

	void getListaCataloghiCollettiviGestioneTabelleDinamiche(
			PagingLoadConfig config,
			AsyncCallback<PagingLoadResult<CataloghiCollettiviModel>> callback);

	void addCatalogoCollettivoATabellaDinamica(	CataloghiCollettiviModel newCatalogoCollettiviModel, boolean modifica, AsyncCallback<Void> asyncCallback);

	void removeCatalogoCollettivoATabellaDinamica(int id_toRemove, AsyncCallback<Void> asyncCallback);

	void getDeweyTabelleDinamiche(PagingLoadConfig loadConfig,AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback);

	void addDeweyTabelleDinamiche(SpecializzazioneModel modelToSave, boolean modifica, AsyncCallback<Void> callback);

	void removeDeweyTabelleDinamiche(String idr_removeRecord,	AsyncCallback<Void> asyncCallback);

	void getPatrimoniGrandiVociTabelleDinamiche(PagingLoadConfig loadConfig,AsyncCallback<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>> callback);

	void addPatrimoniGrandiVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave,boolean modifica, AsyncCallback<Void> asyncCallback);

	void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord,AsyncCallback<Void> asyncCallback);

	void getPatrimoniPiccoleVociTabelleDinamiche(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>> callback);

	void addPatrimoniPiccoleVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave, boolean modifica, AsyncCallback<Void> asyncCallback);

	void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord,AsyncCallback<Void> asyncCallback);
	
	void addProvinciaTabelleDinamiche(ProvinceModel modelToSave,boolean modifica, AsyncCallback<Void> asyncCallback);

	void removeProvinciaTabelleDinamiche(int idr_removeRecord,	AsyncCallback<Void> asyncCallback);

	void addComuneTabelleDinamiche(ComuniModel modelToSave, boolean modifica,AsyncCallback<Void> asyncCallback);

	void removeComuneTabelleDinamiche(int idr_removeRecord,	AsyncCallback<Void> asyncCallback);

}