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

import it.inera.abi.gxt.client.Abi;
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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi relativi alle tabelle dinamiche (lato client)
 * 
 */
@RemoteServiceRelativePath(Abi.TABELLE_DINAMICHE_SERVICE)
public interface TabelleDinamicheService extends RemoteService{
	
	public PagingLoadResult<VoceUnicaModel> getTabellaDinamicaVoceSingolaList(PagingLoadConfig config, int tipo);
	
	public List<VoceUnicaModel> getListaVoci(int id_tabella);

	public PagingLoadResult<VoceUnicaModel> getListaVociFiltratePerPaginazioneCombobox(int id_tabella,
			ModelData loadConfig);

	public PagingLoadResult<PatrimonioSpecializzazioneModel> getTipologiePatrimonioFiltratePerPaginazioneCombobox(
			ModelData loadConfig);
	
	public PagingLoadResult<PatrimonioSpecializzazioneModel> getPatrimonioLibrarioPaginatoClassificatoPerCategorie(
			ModelData loadConfig);
	
	public PagingLoadResult<SpecializzazioneModel> getDeweyFiltratePerPaginazioneCombobox(
			ModelData loadConfig);

	public PagingLoadResult<PartecipaCataloghiCollettiviModel> getListaCataloghiCollettiviPossibiliPerPaginazioneCombobox(
			ModelData loadConfig);
	
	public PagingLoadResult<PartecipaCataloghiCollettiviModel> getAllCataloghiColettivi(ModelData loadConfig);
	
	public 	void addCataloghiMaterialeUrl(CataloghiUrlModel newUrl, boolean modificaUrl,int tipoCatalogo);

	public 	void removeCataloghiMaterialeUrl(int id_removeUrl,int tipoCatalogo);

	public 	List<CataloghiUrlModel> getCataloghiUrls(int tipoCatalogo,
			int idPartecipaCatalogo);

	public 	List<VoceUnicaModel> getMaterialiEsclusiByIdPrestitoLocale(
			Integer idPrestitoLocale);

	public 	void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale);

	void removePrestitoLocaleMaterialeEscluso(int id_remove,Integer idPrestitoLocale);

	List<VoceUnicaModel> getUtentiAmmessiByIdPrestitoLocale(
			Integer idPrestitoLocale);

	void addUtenteAmmessoAlPrestitoLocale(Integer idRecord,
			Integer idPrestitoLocale);

	void removeUtenteAmmessoAlPrestitoLocale(int id_remove,
			Integer idPrestitoLocale);
	void addEntryTabellaDinamicaVoceSingola(VoceUnicaModel nuovaVoce, boolean modifica) throws DuplicatedEntryClientSideException;
	void removeEntryTabellaDinamicaVoceSingola(int id_toRemove, int tipoTabella) throws CostraintKeyViolationClientSideException, Exception;

	PagingLoadResult<CataloghiCollettiviModel> getListaCataloghiCollettiviGestioneTabelleDinamiche(PagingLoadConfig config);

	void addCatalogoCollettivoATabellaDinamica(	CataloghiCollettiviModel newCatalogoCollettiviModel, boolean modifica);

	void removeCatalogoCollettivoATabellaDinamica(int id_toRemove);

	PagingLoadResult<SpecializzazioneModel> getDeweyTabelleDinamiche(PagingLoadConfig loadConfig);

	void addDeweyTabelleDinamiche(SpecializzazioneModel modelToSave, boolean modifica) throws DuplicatedEntryClientSideException;

	void removeDeweyTabelleDinamiche(String idr_removeRecord);

	PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel> getPatrimoniGrandiVociTabelleDinamiche(
			PagingLoadConfig loadConfig);

	void addPatrimoniGrandiVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave,boolean modifica) throws DuplicatedEntryClientSideException;

	void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException;

	PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel> getPatrimoniPiccoleVociTabelleDinamiche(
			PagingLoadConfig loadConfig);

	void addPatrimoniPiccoleVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave,boolean modifica) throws DuplicatedEntryClientSideException;

	void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException;

	void addProvinciaTabelleDinamiche(ProvinceModel modelToSave,boolean modifica)throws DuplicatedEntryClientSideException;

	void removeProvinciaTabelleDinamiche(int idr_removeRecord)throws CostraintKeyViolationClientSideException;

	void addComuneTabelleDinamiche(ComuniModel modelToSave, boolean modifica) throws DuplicatedEntryClientSideException;

	void removeComuneTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException;

	void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(SistemiPrestitoInterbibliotecarioModel modelToSave, boolean modifica) throws DuplicatedEntryClientSideException;
	
	void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException;
	
	public List<SistemiPrestitoInterbibliotecarioModel> getSistemiPrestitoInterbibliotecario();
	
	public PagingLoadResult<SistemiPrestitoInterbibliotecarioModel> getDescrizioneSistemiPrestitoInterbibliotecarioFiltratePerPaginazioneCombobox(
			ModelData loadConfig);

}