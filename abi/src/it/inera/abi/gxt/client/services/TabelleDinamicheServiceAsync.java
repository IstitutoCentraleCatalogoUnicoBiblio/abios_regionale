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

import it.inera.abi.gxt.client.mvc.model.CataloghiUrlModel;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PatrimoniCategorieTabelleDinamicheModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.SistemiPrestitoInterbibliotecarioModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.models.CataloghiCollettiviModel;

import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>TabelleDinamicheService</code>
 * 
 */
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
	
	void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(SistemiPrestitoInterbibliotecarioModel modelToSave, boolean modifica,	AsyncCallback<Void> asyncCallback);
	
	void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord, AsyncCallback<Void> asyncCallback);

	public void getSistemiPrestitoInterbibliotecario(AsyncCallback<List<SistemiPrestitoInterbibliotecarioModel>> callback);
	
	void getDescrizioneSistemiPrestitoInterbibliotecarioFiltratePerPaginazioneCombobox(ModelData loadConfig,
			AsyncCallback<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>> callback);
}