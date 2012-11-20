package it.inera.abi.gxt.server;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.dto.PatrimonioSubCategoryDTO;
import it.inera.abi.dto.SistemaPrestitoInterbibliotecarioDTO;
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
import it.inera.abi.gxt.client.services.CostraintKeyViolationClientSideException;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheService;
import it.inera.abi.logic.AbiTabelleDinamicheLogic;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.CataloghiCollettiviMaterialeUrl;
import it.inera.abi.persistence.CataloghiGeneraliUrl;
import it.inera.abi.persistence.CataloghiSpecialiMaterialeUrl;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.PatrimonioSpecializzazioneCategoria;
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 * Implementazione dei servizi relativi alle tabelle dinamiche (lato server)
 * 
 */
public class TabelleDinamicheServiceImpl extends AutoinjectingRemoteServiceServlet implements TabelleDinamicheService {

	private static final long serialVersionUID = 1L;

	private AbiTabelleDinamicheLogic abiTabelleDinamicheLogic;

	public TabelleDinamicheServiceImpl() {

	}

	@Autowired
	@Required
	public void setAbiTabelleDinamiche(
			AbiTabelleDinamicheLogic abiTabelleDinamicheLogic) {
		this.abiTabelleDinamicheLogic = abiTabelleDinamicheLogic;
	}

	@Override
	public PagingLoadResult<VoceUnicaModel> getTabellaDinamicaVoceSingolaList(
			final PagingLoadConfig config, int tipo) {

		int start = config.getOffset();
		int limit = config.getLimit();

		final	String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		final	String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);

		/*
		 * LOAD DATI
		 */
		List<VoceUnicaModel> sublist = new ArrayList<VoceUnicaModel>();
		int countAll = abiTabelleDinamicheLogic.countAllFilteredGenericaVoceSingola(tipo,null);
		List<Object> listDB = (List<Object>) abiTabelleDinamicheLogic.getListaVociFiltratePerPaginazioneCombobox(tipo, null, start, limit, sortField, sortDir);

		Iterator<Object> it = listDB.iterator();
		DynaTabDTO dynaTabDTODB = null;
		VoceUnicaModel voceUnicaTemp = null;
		while (it.hasNext()) {
			// CONVERTO L'OGGETTO IN DTO
			dynaTabDTODB = (DynaTabDTO) DtoJpaMapping.dynaRecord2DTO(it.next());

			voceUnicaTemp = new VoceUnicaModel();

			voceUnicaTemp.setIdRecord(dynaTabDTODB.getId());
			voceUnicaTemp.setEntry(dynaTabDTODB.getValue());
			sublist.add(voceUnicaTemp);

		}

		return new BasePagingLoadResult<VoceUnicaModel>(sublist,
				config.getOffset(), countAll);
	}

	@Override
	public PagingLoadResult<CataloghiCollettiviModel> getListaCataloghiCollettiviGestioneTabelleDinamiche(
			final PagingLoadConfig config) {
		List<CataloghiCollettiviModel> sublist = new ArrayList<CataloghiCollettiviModel>(); 

		int start = config.getOffset();
		int limit = config.getLimit();

		final	String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		final	String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);

		int countAll =abiTabelleDinamicheLogic.countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox(null,null,null);

		List<CataloghiCollettivi> cataloghiCollettivis = abiTabelleDinamicheLogic.getCataloghiCollettiviPossibiliFiltered(null,null,null,start,limit, sortField, sortDir);

		Iterator<CataloghiCollettivi> it = cataloghiCollettivis.iterator();

		while (it.hasNext()) {
			//Converto gli oggetti in DTO
			CataloghiCollettivi cataloghiCollettivi = (CataloghiCollettivi) it
			.next();
			CataloghiCollettiviModel model = new CataloghiCollettiviModel();

			model.setIdCatalogo(cataloghiCollettivi.getIdCataloghiCollettivi());
			model.setDenominazione(cataloghiCollettivi.getDescrizione());
			model.setZona(cataloghiCollettivi.getDettaglioZona());
			model.setIdZonaEspansione(cataloghiCollettivi.getCataloghiCollettiviZonaTipo().getIdCataloghiCollettiviZonaTipo());
			model.setZonaEspansione(cataloghiCollettivi.getCataloghiCollettiviZonaTipo().getDescrizione());
			sublist.add(model);
		}
		return new BasePagingLoadResult<CataloghiCollettiviModel>(sublist, config.getOffset(),	countAll);
	}


	@Override
	public List<VoceUnicaModel> getListaVoci(int id_tabella) {
		List<VoceUnicaModel> listaVoceUnicaModel = new ArrayList<VoceUnicaModel>();

		List<Object> listDB = (List<Object>) abiTabelleDinamicheLogic
		.getListaVoci(id_tabella);

		Iterator<Object> it = listDB.iterator();
		DynaTabDTO dynaTabDTODB = null;
		VoceUnicaModel voceUnicaTemp = null;
		while (it.hasNext()) {
			// CONVERTO L'OGGETTO IN DTO
			dynaTabDTODB = (DynaTabDTO) DtoJpaMapping.dynaRecord2DTO(it.next());

			voceUnicaTemp = new VoceUnicaModel();

			voceUnicaTemp.setIdRecord(dynaTabDTODB.getId());
			voceUnicaTemp.setEntry(dynaTabDTODB.getValue());
			listaVoceUnicaModel.add(voceUnicaTemp);

		}
		return listaVoceUnicaModel;
	}

	@Override
	public void addEntryTabellaDinamicaVoceSingola(VoceUnicaModel nuovaVoce, boolean modifica) throws DuplicatedEntryClientSideException {
		DynaTabDTO tmpDto = new DynaTabDTO();
		if(modifica)
			tmpDto.setId(nuovaVoce.getIdRecord());
		tmpDto.setValue(nuovaVoce.getEntry());
		tmpDto.setIdTabella(nuovaVoce.getIdTabella());


		try {
			abiTabelleDinamicheLogic.addEntryTabellaDinamicaVoceSingola(tmpDto,nuovaVoce.getIdTabella(),modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}

	@Override
	public void removeEntryTabellaDinamicaVoceSingola(int id_toRemove, int tipoTabella) {

		abiTabelleDinamicheLogic.removeEntryTabellaDinamicaVoceSingola(id_toRemove, tipoTabella);

	}

	/**
	 * Metodo per caricamento paginato per inserimento in combo-box con
	 * funzionalità di auto-completion dei campi delle tabelle dinamiche aventi 
	 * come valori un id e un valore stringa
	 */
	@Override
	public PagingLoadResult<VoceUnicaModel> getListaVociFiltratePerPaginazioneCombobox(
			int id_tabella, ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		int countAll = abiTabelleDinamicheLogic.countAllFilteredGenericaVoceSingola(id_tabella,
				query);

		List<VoceUnicaModel> sublist = new ArrayList<VoceUnicaModel>();

		List<Object> listDB = (List<Object>) abiTabelleDinamicheLogic
		.getListaVociFiltratePerPaginazioneCombobox(id_tabella, query,start, limit, null, null);

		Iterator<Object> it = listDB.iterator();
		DynaTabDTO dynaTabDTODB = null;
		VoceUnicaModel voceUnicaTemp = null;
		while (it.hasNext()) {
			// CONVERTO L'OGGETTO IN DTO
			dynaTabDTODB = (DynaTabDTO) DtoJpaMapping.dynaRecord2DTO(it.next());

			voceUnicaTemp = new VoceUnicaModel();

			voceUnicaTemp.setIdRecord(dynaTabDTODB.getId());
			voceUnicaTemp.setEntry(dynaTabDTODB.getValue());
			sublist.add(voceUnicaTemp);

		}
		return new BasePagingLoadResult<VoceUnicaModel>(sublist, start,
				countAll);
	}



	@Override
	public PagingLoadResult<PatrimonioSpecializzazioneModel> getTipologiePatrimonioFiltratePerPaginazioneCombobox(
			ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");

		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		int countAll = abiTabelleDinamicheLogic.countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(query);

		List<PatrimonioSpecializzazioneModel> sublist = new ArrayList<PatrimonioSpecializzazioneModel>();

		List<PatrimonioSubCategoryDTO> listDB =abiTabelleDinamicheLogic.getPatrimoniSpecialiPerCategoriePaginatiPerCombo(query,start,limit);
		Iterator<PatrimonioSubCategoryDTO> it = listDB.iterator();

		while (it.hasNext()) {
			PatrimonioSubCategoryDTO DTO = (PatrimonioSubCategoryDTO) it.next();
			//Caso inizio lista	
			if (sublist.size() == 0) {

				PatrimonioSpecializzazioneModel modelCatMadre = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel modelCat = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();

				modelCat.setIdRecord(-1);
				modelCat.setEntry(DTO.getDescrizioneCat());

				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());

				if (DTO.getIdCatMadre() != null) {

					modelCatMadre.setIdRecord(-1);
					modelCatMadre.setEntry(abiTabelleDinamicheLogic.getDescrizionePatrCatById(DTO.getIdCatMadre()));
					modelCatMadre.setCondition(1);
					sublist.add(modelCatMadre);
					modelCat.setCondition(2);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}
				else {

					modelCat.setCondition(1);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}	

			}
			//Caso oggetto a metà lista e categoria diversa dall'elemento precedente
			else if (sublist.size() > 0 && (listDB.get(listDB.indexOf(DTO)-1).getId_cat() != listDB.get(listDB.indexOf(DTO)).getId_cat())) {

				PatrimonioSpecializzazioneModel modelCatMadre = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel modelCat = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();

				modelCat.setIdRecord(-1);
				modelCat.setEntry(DTO.getDescrizioneCat());				

				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());

				if (DTO.getIdCatMadre() != null) {

					modelCatMadre.setIdRecord(-1);
					modelCatMadre.setEntry(abiTabelleDinamicheLogic.getDescrizionePatrCatById(DTO.getIdCatMadre()));
					modelCatMadre.setCondition(1);
					sublist.add(modelCatMadre);
					modelCat.setCondition(2);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}
				else {

					modelCat.setCondition(1);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}


			}
			//caso in cui la categoria è uguale all'elemento precedente e non siamo ad inizio lista
			else {

				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();
				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());
				model.setCondition(3);

				sublist.add(model);
			}

		}


		return new BasePagingLoadResult<PatrimonioSpecializzazioneModel>(sublist, start,
				countAll);
	}




	@Override
	public PagingLoadResult<SpecializzazioneModel> getDeweyFiltratePerPaginazioneCombobox(
			ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");

		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		PagingLoadConfig config = (PagingLoadConfig)loadConfig;
		final	String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		final	String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);

		int countAll =abiTabelleDinamicheLogic.countAllDeweyFiltratePerPaginazioneCombobox(query);

		List<Dewey>	deweys =abiTabelleDinamicheLogic.getDeweyFiltratePerPaginazioneCombobox(query,start,limit, sortField, sortDir);
		List<SpecializzazioneModel> sublist = new ArrayList<SpecializzazioneModel>();

		Iterator<Dewey> it = deweys.iterator();

		while (it.hasNext()) {
			Dewey dewey = (Dewey) it.next();
			SpecializzazioneModel model = new SpecializzazioneModel();
			String codicePuntato = creaCodiceDeweyPuntato(dewey.getIdDewey());
			model.setDewey(codicePuntato);
			model.setDecrizione(dewey.getDescrizione());
			sublist.add(model);
		}

		return new BasePagingLoadResult<SpecializzazioneModel>(sublist, start,
				countAll);
	}

	@Override
	public PagingLoadResult<PartecipaCataloghiCollettiviModel> getListaCataloghiCollettiviPossibiliPerPaginazioneCombobox(
			ModelData loadConfig) {
		List<PartecipaCataloghiCollettiviModel> sublist = new ArrayList<PartecipaCataloghiCollettiviModel>(); 

		ModelData m = (ModelData) loadConfig;
		/*Parametri per il filtraggio dei dati*/
		String query = (String) m.get("query");

		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		Integer idZonaEspansione=(((Integer) m.get("idZonaEspansione"))==0?null:(Integer) m.get("idZonaEspansione"));
		String dettaglioZona=(String) m.get("dettaglioZona");

		int countAll =abiTabelleDinamicheLogic.countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox(query,idZonaEspansione,dettaglioZona);

		List<CataloghiCollettivi> cataloghiCollettivis = abiTabelleDinamicheLogic.getCataloghiCollettiviPossibiliFiltered(query,idZonaEspansione,dettaglioZona,start,limit, null, null);

		Iterator<CataloghiCollettivi> it = cataloghiCollettivis.iterator();

		while (it.hasNext()) {
			//Converto gli oggetti in DTO
			CataloghiCollettivi cataloghiCollettivi = (CataloghiCollettivi) it
			.next();
			PartecipaCataloghiCollettiviModel partecipaCataloghiCollettiviModel = new PartecipaCataloghiCollettiviModel();
			//setto denominazione catalogo
			partecipaCataloghiCollettiviModel.setDenominazioneCatalogo(cataloghiCollettivi.getDescrizione());

			//setto id tipologia zona di espansione e descrizione zona espansione
			int idZonaEspansioneTmp=cataloghiCollettivi.getCataloghiCollettiviZonaTipo().getIdCataloghiCollettiviZonaTipo();
			String descrZonaEspansioneTmp=cataloghiCollettivi.getCataloghiCollettiviZonaTipo().getDescrizione();
			partecipaCataloghiCollettiviModel.setZonaEspansione(new VoceUnicaModel(idZonaEspansioneTmp,descrZonaEspansioneTmp));
			partecipaCataloghiCollettiviModel.setIdZonaEspansione(idZonaEspansioneTmp);
			partecipaCataloghiCollettiviModel.setZonaEspansioneDescr(descrZonaEspansioneTmp);
			//setto il dettaglio zona
			partecipaCataloghiCollettiviModel.setDettaglioZona(cataloghiCollettivi.getDettaglioZona());

			/* Aggiunta per form ricerca biblio in assegna biblio a utenti */
			partecipaCataloghiCollettiviModel.setIdCatalogo(cataloghiCollettivi.getIdCataloghiCollettivi());

			sublist.add(partecipaCataloghiCollettiviModel);
		}
		return new BasePagingLoadResult<PartecipaCataloghiCollettiviModel>(sublist, start,	countAll);
	}



	@Override
	public PagingLoadResult<PartecipaCataloghiCollettiviModel> getAllCataloghiColettivi(ModelData loadConfig) {

		List<PartecipaCataloghiCollettiviModel> sublist = new ArrayList<PartecipaCataloghiCollettiviModel>(); 

		ModelData m = (ModelData) loadConfig;
		/*Parametri per il filtraggio dei dati*/
		String query = (String) m.get("query");

		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		int countAll = abiTabelleDinamicheLogic.countAllCataloghiCollettivi(query);

		List<CataloghiCollettivi> cataloghiCollettiviList = abiTabelleDinamicheLogic.getAllCataloghiCollettivi(query, start, limit);

		for (Iterator<CataloghiCollettivi> it = cataloghiCollettiviList.iterator(); it.hasNext();) {

			/* Converto gli oggetti in DTO */
			CataloghiCollettivi cataloghiCollettivi = (CataloghiCollettivi) it.next();

			PartecipaCataloghiCollettiviModel partecipaCataloghiCollettiviModel = new PartecipaCataloghiCollettiviModel();

			/* Setto denominazione catalogo */
			partecipaCataloghiCollettiviModel.setDenominazioneCatalogo(cataloghiCollettivi.getDescrizione());

			/* IdCataloghiCollettivi aggiunto per la gestione dei cataloghi selezionati per il report */
			partecipaCataloghiCollettiviModel.setIdCatalogo(cataloghiCollettivi.getIdCataloghiCollettivi());

			sublist.add(partecipaCataloghiCollettiviModel);


		}
		return new BasePagingLoadResult<PartecipaCataloghiCollettiviModel>(sublist, start, countAll);
	}


	@Override
	public void addCataloghiMaterialeUrl(CataloghiUrlModel newUrl,
			boolean modificaUrl,int tipoCatalogo) {
		Integer idCatalogoMaterialeUrl=null;
		if(modificaUrl){
			idCatalogoMaterialeUrl=newUrl.getIdCataloghiUrl();
		}
		Integer idPartecipaCatalogoMateriale=newUrl.getIdPartecipa();

		String url=newUrl.getUrl();
		String descrizioneUrl=newUrl.getDescr();
		abiTabelleDinamicheLogic.addCataloghiMaterialeUrl(idCatalogoMaterialeUrl,idPartecipaCatalogoMateriale,url,descrizioneUrl,modificaUrl,tipoCatalogo);

	}

	@Override
	public void removeCataloghiMaterialeUrl(int id_removeUrl,int tipoCatalogo) {
		abiTabelleDinamicheLogic.removeCataloghiMaterialeUrl(id_removeUrl,tipoCatalogo); 

	}
	@Override
	public List<CataloghiUrlModel> getCataloghiUrls(int tipoCatalogo,int idPartecipaCatalogo){
		List<CataloghiUrlModel> listaUrlsModels = new ArrayList<CataloghiUrlModel>();

		if(tipoCatalogo==1){
			List<CataloghiGeneraliUrl> cataloghiGeneraliUrls = abiTabelleDinamicheLogic.getCataloghiGeneraliUrl(idPartecipaCatalogo);
			Iterator<CataloghiGeneraliUrl> iturls = cataloghiGeneraliUrls.iterator();
			while (iturls.hasNext()) {
				//Mapping CataloghiGeneraliUrl ---> CataloghiUrlModel
				CataloghiGeneraliUrl tmpCatalogoUrl = (CataloghiGeneraliUrl) iturls
				.next();
				CataloghiUrlModel urlModel = new CataloghiUrlModel();
				urlModel.setIdCataloghiUrl(tmpCatalogoUrl.getIdCataloghiGeneraliUrl());
				urlModel.setIdPartecipa(tmpCatalogoUrl.getPartecipaCataloghiGenerali().getIdCataloghiGenerali());

				urlModel.setUrl(tmpCatalogoUrl.getUrl());
				urlModel.setDescrUrl(tmpCatalogoUrl.getDescrizione());
				listaUrlsModels.add(urlModel);
			}
			return listaUrlsModels;
		}
		if(tipoCatalogo==2){
			List<CataloghiCollettiviMaterialeUrl> cataloghiCollettiviMaterialeUrls = abiTabelleDinamicheLogic.getCataloghiCollettiviMaterialeUrl(idPartecipaCatalogo);
			Iterator<CataloghiCollettiviMaterialeUrl> iturls = cataloghiCollettiviMaterialeUrls.iterator();
			while (iturls.hasNext()) {
				//Mapping CataloghiCollettiviMaterialeUrl ---> CataloghiUrlModel
				CataloghiCollettiviMaterialeUrl tmpCatalogoUrl = (CataloghiCollettiviMaterialeUrl) iturls
				.next();
				CataloghiUrlModel urlModel = new CataloghiUrlModel();
				urlModel.setIdCataloghiUrl(tmpCatalogoUrl.getIdCataloghiCollettiviMaterialeUrl());
				urlModel.setIdPartecipa(tmpCatalogoUrl.getPartecipaCataloghiCollettiviMateriale().getIdCataloghiCollettiviMateriale());

				urlModel.setUrl(tmpCatalogoUrl.getUrl());
				urlModel.setDescrUrl(tmpCatalogoUrl.getDescrizione());
				listaUrlsModels.add(urlModel);
			}
			return listaUrlsModels;

		}
		if(tipoCatalogo==3){
			List<CataloghiSpecialiMaterialeUrl> cataloghiSpecialiMaterialeUrls = abiTabelleDinamicheLogic.getCataloghiSpecialiMaterialeUrl(idPartecipaCatalogo);
			Iterator<CataloghiSpecialiMaterialeUrl> iturls = cataloghiSpecialiMaterialeUrls.iterator();
			while (iturls.hasNext()) {
				//Mapping CataloghiSpecialiMaterialeUrl ---> CataloghiUrlModel
				CataloghiSpecialiMaterialeUrl tmpCatalogoUrl = (CataloghiSpecialiMaterialeUrl) iturls
				.next();
				CataloghiUrlModel urlModel = new CataloghiUrlModel();
				urlModel.setIdCataloghiUrl(tmpCatalogoUrl.getIdCataloghiSpecialiMaterialeUrl());
				urlModel.setIdPartecipa(tmpCatalogoUrl.getPartecipaCataloghiSpecialiMateriale().getIdCataloghiSpecialiMateriale());

				urlModel.setUrl(tmpCatalogoUrl.getUrl());
				urlModel.setDescrUrl(tmpCatalogoUrl.getDescrizione());
				listaUrlsModels.add(urlModel);
			}
		}
		return listaUrlsModels;
	}


	@Override
	public PagingLoadResult<PatrimonioSpecializzazioneModel> getPatrimonioLibrarioPaginatoClassificatoPerCategorie(ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");		
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		int countAll = abiTabelleDinamicheLogic.countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(query);

		List<PatrimonioSpecializzazioneModel> sublist = new ArrayList<PatrimonioSpecializzazioneModel>();

		List<PatrimonioSubCategoryDTO> listDB =abiTabelleDinamicheLogic.getPatrimoniSpecialiPerCategoriePaginatiPerCombo(query,start,limit);

		for (Iterator<PatrimonioSubCategoryDTO> it = listDB.iterator(); it.hasNext();) {

			PatrimonioSubCategoryDTO DTO = (PatrimonioSubCategoryDTO) it.next();

			if (sublist.size() == 0) {

				PatrimonioSpecializzazioneModel modelCatMadre = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel modelCat = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();

				modelCat.setIdRecord(DTO.getId_cat());
				modelCat.setEntry(DTO.getDescrizioneCat());

				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());

				if (DTO.getIdCatMadre() != null) {

					modelCatMadre.setIdRecord(DTO.getIdCatMadre());
					modelCatMadre.setEntry(abiTabelleDinamicheLogic.getDescrizionePatrCatById(DTO.getIdCatMadre()));
					modelCatMadre.setCondition(1);
					sublist.add(modelCatMadre);
					modelCat.setCondition(2);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}
				else {

					modelCat.setCondition(1);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}				

			}
			else if (sublist.size() > 0 && (listDB.get(listDB.indexOf(DTO)-1).getId_cat() != listDB.get(listDB.indexOf(DTO)).getId_cat())) {

				PatrimonioSpecializzazioneModel modelCatMadre = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel modelCat = new PatrimonioSpecializzazioneModel();
				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();

				modelCat.setIdRecord(DTO.getId_cat());
				modelCat.setEntry(DTO.getDescrizioneCat());

				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());

				if (DTO.getIdCatMadre() != null) {

					modelCatMadre.setIdRecord(DTO.getIdCatMadre());
					modelCatMadre.setEntry(abiTabelleDinamicheLogic.getDescrizionePatrCatById(DTO.getIdCatMadre()));
					modelCatMadre.setCondition(1);
					sublist.add(modelCatMadre);
					modelCat.setCondition(2);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}
				else {

					modelCat.setCondition(1);
					sublist.add(modelCat);
					model.setCondition(3);
					sublist.add(model);	

				}

			}
			else {

				PatrimonioSpecializzazioneModel model = new PatrimonioSpecializzazioneModel();
				model.setIdRecord(DTO.getId_patrimonio_specializzazione());
				model.setEntry(DTO.getDescrizioneTipologia());
				model.setCondition(3);			

				sublist.add(model);

			}

		}

		return new BasePagingLoadResult<PatrimonioSpecializzazioneModel>(sublist, start, countAll);
	}

	@Override
	public List<VoceUnicaModel> getMaterialiEsclusiByIdPrestitoLocale(Integer idPrestitoLocale) {
		List<VoceUnicaModel> materialiEsclusiModels = new ArrayList<VoceUnicaModel>();

		List<PrestitoLocaleMaterialeEscluso> materialeEsclusos = abiTabelleDinamicheLogic.getMaterialiEsclusiByIdPrestitoLocale(idPrestitoLocale);

		Iterator<PrestitoLocaleMaterialeEscluso> it = materialeEsclusos.iterator();
		while (it.hasNext()) {

			PrestitoLocaleMaterialeEscluso tmpMateriale = (PrestitoLocaleMaterialeEscluso) it.next();	
			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(tmpMateriale.getIdPrestitoLocaleMaterialeEscluso());
			model.setEntry(tmpMateriale.getDescrizione());

			materialiEsclusiModels.add(model);
		}
		return materialiEsclusiModels;

	}

	@Override
	public void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale) {
		abiTabelleDinamicheLogic.addMaterialeEscluso( idRecord,idPrestitoLocale);
	}

	@Override
	public void removePrestitoLocaleMaterialeEscluso(int id_remove,	Integer idPrestitoLocale) {
		abiTabelleDinamicheLogic.removePrestitoLocaleMaterialeEscluso(id_remove,idPrestitoLocale);
	}

	@Override
	public List<VoceUnicaModel> getUtentiAmmessiByIdPrestitoLocale(
			Integer idPrestitoLocale) {
		List<VoceUnicaModel> utentiAmmessiModel = new ArrayList<VoceUnicaModel>();

		List<PrestitoLocaleUtentiAmmessi> prestitoLocaleUtentiAmmessis =abiTabelleDinamicheLogic.getUtentiAmmessiByIdPrestitoLocale(idPrestitoLocale);
		Iterator<PrestitoLocaleUtentiAmmessi> it = prestitoLocaleUtentiAmmessis.iterator();
		while (it.hasNext()) {

			PrestitoLocaleUtentiAmmessi tmpUtente = (PrestitoLocaleUtentiAmmessi) it.next();	
			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(tmpUtente.getIdPrestitoLocaleUtentiAmmessi());
			model.setEntry(tmpUtente.getDescrizione());

			utentiAmmessiModel.add(model);
		}
		return utentiAmmessiModel;

	}

	@Override
	public void addUtenteAmmessoAlPrestitoLocale(Integer idRecord,Integer idPrestitoLocale) {
		abiTabelleDinamicheLogic.addUtenteAmmessoAlPrestitoLocale(idRecord,idPrestitoLocale);
	}

	@Override
	public void removeUtenteAmmessoAlPrestitoLocale(int id_remove,	Integer idPrestitoLocale) {
		abiTabelleDinamicheLogic.removeUtenteAmmessoAlPrestitoLocale(id_remove,	idPrestitoLocale);
	}

	@Override
	public void addCatalogoCollettivoATabellaDinamica(
			CataloghiCollettiviModel newCatalogoCollettiviModel,boolean modifica) {
		Integer idCatalogo=null;
		Integer idZonaEspansione=null;
		String denominazioneCatalogo=null;
		String zonaDettaglio=null;

		if( modifica ){
			idCatalogo=newCatalogoCollettiviModel.getIdCatalogo();
		}
		if(newCatalogoCollettiviModel.getDenominazione()!=null){
			denominazioneCatalogo=newCatalogoCollettiviModel.getDenominazione();
		}
		if(newCatalogoCollettiviModel.getIdZonaEspansione()!=null){
			idZonaEspansione=newCatalogoCollettiviModel.getIdZonaEspansione();
		}
		if(newCatalogoCollettiviModel.getZona()!=null)
			zonaDettaglio=newCatalogoCollettiviModel.getZona();

		abiTabelleDinamicheLogic.addCatalogoCollettivoATabellaDinamica(idCatalogo, denominazioneCatalogo, idZonaEspansione, zonaDettaglio, modifica);
	}

	@Override
	public void removeCatalogoCollettivoATabellaDinamica(int id_toRemove) {
		abiTabelleDinamicheLogic.removeCatalogoCollettivoATabellaDinamica(id_toRemove);
	}

	@Override
	public void addDeweyTabelleDinamiche(SpecializzazioneModel modelToSave,	 boolean modifica) throws DuplicatedEntryClientSideException {
		String codiceDewey=modelToSave.getDewey();
		String descrizione=modelToSave.getDecrizione();
		try {
			abiTabelleDinamicheLogic.addDeweyTabelleDinamiche(codiceDewey, descrizione, modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}


	}

	@Override
	public void removeDeweyTabelleDinamiche(String idr_removeRecord) {
		if(idr_removeRecord.length()>3){
			String tmpStr = rimuoviPuntoDalCodiceDewey(idr_removeRecord);
			abiTabelleDinamicheLogic.removeDeweyTabelleDinamiche(tmpStr);
		}else
			abiTabelleDinamicheLogic.removeDeweyTabelleDinamiche(idr_removeRecord);



	}

	/**
	 * Rimuove il punto dal codice dewey 
	 * @param idr_removeRecord
	 * @return String
	 */
	public static String rimuoviPuntoDalCodiceDewey(String idr_removeRecord) {
		String tmpStr=new String();
		StringTokenizer tokenizer = new StringTokenizer(idr_removeRecord, ".");
		while (tokenizer.hasMoreElements()) {
			tmpStr+=(String)tokenizer.nextElement();
		}
		return tmpStr;
	}

	/**
	 * Inserisce nel codice dewey il punto
	 * @param senzaPunto
	 * @return String
	 */
	public static String creaCodiceDeweyPuntato(String senzaPunto) {
		if(senzaPunto.length()>3){
			String primaDelPunto= senzaPunto.substring(0,3);
			String dopoIlPunto= senzaPunto.substring(3);
			String codicePuntato=primaDelPunto.concat(".").concat(dopoIlPunto);
			return codicePuntato;
		}return senzaPunto;
	}

	@Override
	public PagingLoadResult<SpecializzazioneModel> getDeweyTabelleDinamiche(
			PagingLoadConfig config) {
		List<SpecializzazioneModel> sublist = new ArrayList<SpecializzazioneModel>(); 

		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);


		int countAll =abiTabelleDinamicheLogic.countAllDeweyFiltratePerPaginazioneCombobox(null);

		List<Dewey>	deweys =abiTabelleDinamicheLogic.getDeweyFiltratePerPaginazioneCombobox(null,start,limit, sortField, sortDir);

		Iterator<Dewey> it = deweys.iterator();

		while (it.hasNext()) {
			Dewey dewey = (Dewey) it.next();
			SpecializzazioneModel model = new SpecializzazioneModel();

			String codicePuntato = creaCodiceDeweyPuntato(dewey.getIdDewey());
			model.setDewey(codicePuntato);
			model.setDecrizione(dewey.getDescrizione());
			sublist.add(model);
		}

		return new BasePagingLoadResult<SpecializzazioneModel>(sublist, start,countAll);
	}


	@Override
	public PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel> getPatrimoniGrandiVociTabelleDinamiche(
			PagingLoadConfig config) {

		List<PatrimoniCategorieTabelleDinamicheModel> sublist = new ArrayList<PatrimoniCategorieTabelleDinamicheModel>(); 

		int start = config.getOffset();
		int limit = config.getLimit();
		int countAll = abiTabelleDinamicheLogic.countAllPatrimoniCategorieGrandiVociTabelleDinamiche();


		List<PatrimonioSpecializzazioneCategoria> listDB =abiTabelleDinamicheLogic.getListaPatrimoniCategorieGrandiVociTabelleDinamiche(start, limit);
		Iterator<PatrimonioSpecializzazioneCategoria> it = listDB.iterator();
		while (it.hasNext()) {
			PatrimonioSpecializzazioneCategoria patrimonio = (PatrimonioSpecializzazioneCategoria) it
			.next();

			PatrimoniCategorieTabelleDinamicheModel model=new PatrimoniCategorieTabelleDinamicheModel();

			model.setIdCategoria(patrimonio.getIdPatrimonioSpecializzazioneCategoria());
			model.setCategoriaDescrizione(patrimonio.getDescrizione());
			if(patrimonio.getPatrimonioSpecializzazioneCategoria()!=null){
				model.setIdCategoriaMadre(patrimonio.getPatrimonioSpecializzazioneCategoria().getIdPatrimonioSpecializzazioneCategoria());
				model.setCategoriaMadreDescrizione(patrimonio.getPatrimonioSpecializzazioneCategoria().getDescrizione());
			}
			sublist.add(model);
		}
		return new BasePagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>(sublist, start,countAll);
	}

	@Override
	public PagingLoadResult<PatrimoniCategorieTabelleDinamicheModel> getPatrimoniPiccoleVociTabelleDinamiche(
			PagingLoadConfig config) {

		List<PatrimoniCategorieTabelleDinamicheModel> sublist = new ArrayList<PatrimoniCategorieTabelleDinamicheModel>(); 

		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir=(String)(config.getSortInfo().getSortDir()!=null?config.getSortInfo().getSortDir().toString():null);
		String sortField=(String)(config.getSortInfo().getSortField()!=null?config.getSortInfo().getSortField().toString():null);


		int countAll = abiTabelleDinamicheLogic.countAllPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche();
		List<PatrimonioSpecializzazione> listDB =abiTabelleDinamicheLogic.getListaPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche(start, limit, sortField, sortDir);
		Iterator<PatrimonioSpecializzazione> it = listDB.iterator();
		while (it.hasNext()) {
			PatrimonioSpecializzazione patrimonio = (PatrimonioSpecializzazione) it
			.next();

			PatrimoniCategorieTabelleDinamicheModel model=new PatrimoniCategorieTabelleDinamicheModel();

			model.setIdCategoria(patrimonio.getIdPatrimonioSpecializzazione());
			model.setCategoriaDescrizione(patrimonio.getDescrizione());
			if(patrimonio.getPatrimonioSpecializzazioneCategoria()!=null){
				model.setIdCategoriaMadre(patrimonio.getPatrimonioSpecializzazioneCategoria().getIdPatrimonioSpecializzazioneCategoria());
				model.setCategoriaMadreDescrizione(patrimonio.getPatrimonioSpecializzazioneCategoria().getDescrizione());
			}
			sublist.add(model);
		}
		return new BasePagingLoadResult<PatrimoniCategorieTabelleDinamicheModel>(sublist, start,countAll);
	}


	@Override
	public void addPatrimoniGrandiVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave,	boolean modifica) throws DuplicatedEntryClientSideException {
		Integer idCategoria=null;
		String descrizioneCategoria=null;
		Integer idCategoriaMadre =null;
		if(modifica)
			idCategoria=modelToSave.getIdCategoria();
		descrizioneCategoria=modelToSave.getCategoriaDescrizione();
		if(modelToSave.getIdCategoriaMadre()!=null)
			idCategoriaMadre=modelToSave.getIdCategoriaMadre();
		try {
			abiTabelleDinamicheLogic.addPatrimoniGrandiVociTabelleDinamiche(idCategoria,descrizioneCategoria,idCategoriaMadre,modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}

	@Override
	public void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException {
		try{
			abiTabelleDinamicheLogic.removePatrimoniGrandiVociTabelleDinamiche(idr_removeRecord);
		} catch (ConstraintKeyViolationException e) {
			throw new	CostraintKeyViolationClientSideException(e.getMessage());
		}
	}

	@Override
	public void addPatrimoniPiccoleVociTabelleDinamiche(PatrimoniCategorieTabelleDinamicheModel modelToSave,
			boolean modifica) throws DuplicatedEntryClientSideException {
		Integer idSpecializzazione=null;
		String descrizioneSpecializzazione=null;
		Integer idCategoria =null;
		if(modifica)
			idSpecializzazione=modelToSave.getIdCategoria();
		descrizioneSpecializzazione=modelToSave.getCategoriaDescrizione();
		if(modelToSave.getIdCategoriaMadre()!=null)
			idCategoria=modelToSave.getIdCategoriaMadre();
		try {
			abiTabelleDinamicheLogic.addPatrimoniPiccoleVociTabelleDinamiche(idSpecializzazione,descrizioneSpecializzazione,idCategoria,modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}

	}

	@Override
	public void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException {
		try {
			abiTabelleDinamicheLogic.removePatrimoniPiccoleVociTabelleDinamiche(idr_removeRecord);
		} catch (ConstraintKeyViolationException e) {
			throw new	CostraintKeyViolationClientSideException(e.getMessage());
		}

	}

	@Override
	public void addProvinciaTabelleDinamiche(ProvinceModel modelToSave,	boolean modifica) throws DuplicatedEntryClientSideException {
		Integer idProvincia=null;
		String denominazione=null;
		String sigla=null;
		Integer idRegione=null;
		if(modifica){
			idProvincia=modelToSave.getIdProvincia();
		}
		denominazione=modelToSave.getDenominazione();
		if(modelToSave.getSigla() != null){
			sigla=modelToSave.getSigla();
		}
		idRegione=modelToSave.getIdRegione();
		try {
			abiTabelleDinamicheLogic.addProvinciaTabelleDinamiche(idProvincia, denominazione, sigla, idRegione, modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage()); 
		}

	}

	@Override
	public void removeProvinciaTabelleDinamiche(int idr_removeRecord)throws CostraintKeyViolationClientSideException {
		try {
			abiTabelleDinamicheLogic.removeProvinciaTabelleDinamiche(idr_removeRecord);
		} catch (ConstraintKeyViolationException e) {
			throw new CostraintKeyViolationClientSideException(e.getMessage());
		}
	}

	@Override
	public void addComuneTabelleDinamiche(ComuniModel modelToSave,boolean modifica) throws DuplicatedEntryClientSideException{
		Integer idComune=null;
		String denominazione=null;
		String codiceIstat=null;
		String codiceFinanze=null;
		Integer idProvincia=null;

		if(modifica){
			idComune=modelToSave.getIdComune();
		}
		denominazione=modelToSave.getDenominazione();
		codiceIstat=modelToSave.getCodiceIstat();
		codiceFinanze=modelToSave.getCodiceFinanze();
		idProvincia=modelToSave.getIdProvincia();
		try {
			abiTabelleDinamicheLogic.addComuneTabelleDinamiche(idComune,denominazione,codiceIstat,codiceFinanze,idProvincia,modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}

	@Override
	public void removeComuneTabelleDinamiche(int idr_removeRecord)throws CostraintKeyViolationClientSideException {
		try{
			abiTabelleDinamicheLogic.removeComuneTabelleDinamiche(idr_removeRecord);
		} catch (ConstraintKeyViolationException e) {
			throw new CostraintKeyViolationClientSideException(e.getMessage());
		}
	}
	
	@Override
	public void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(SistemiPrestitoInterbibliotecarioModel modelToSave, boolean modifica) throws DuplicatedEntryClientSideException {
		Integer idSistemiPrestitoInterbibliotecario = null;
		String descrizione = null;
		String url = null;
		
		if (modifica) {
			idSistemiPrestitoInterbibliotecario = modelToSave.getIdSistemiPrestitoInterbibliotecario();
		}
		
		descrizione = modelToSave.getDescrizione();
		
		if (modelToSave.getUrl() != null) {
			url = modelToSave.getUrl();
		}
		
		try {
			abiTabelleDinamicheLogic.addSistemiPrestitoInterbibliotecarioTabelleDinamiche(idSistemiPrestitoInterbibliotecario, descrizione, url, modifica);
			
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage()); 
		}
	}
	
	@Override
	public void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord) throws CostraintKeyViolationClientSideException {
		try {
			abiTabelleDinamicheLogic.removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(idr_removeRecord);
			
		} catch (ConstraintKeyViolationException e) {
			throw new CostraintKeyViolationClientSideException(e.getMessage());
		}
	}
	
	@Override
	public List<SistemiPrestitoInterbibliotecarioModel> getSistemiPrestitoInterbibliotecario() {
		/* LOAD */
		List<SistemiPrestitoInterbibliotecario> sistemiDB = abiTabelleDinamicheLogic.getSistemiPrestitoInterbibliotecario();
		List<SistemiPrestitoInterbibliotecarioModel> sistemiModel = new ArrayList<SistemiPrestitoInterbibliotecarioModel>();

		/* MAPPING */
		SistemiPrestitoInterbibliotecarioModel sistemiModelTmp = null;
		
		for (SistemiPrestitoInterbibliotecario sistemi : sistemiDB) {
			sistemiModelTmp = new SistemiPrestitoInterbibliotecarioModel();
			sistemiModelTmp.setIdSistemiPrestitoInterbibliotecario(sistemi.getIdSistemiPrestitoInterbibliotecario());
			sistemiModelTmp.setDescrizione(sistemi.getDescrizione());
			if (sistemi.getUrl() != null) {
				sistemiModelTmp.setUrl(sistemi.getUrl());
			}
			
			sistemiModel.add(sistemiModelTmp);
		}

		return sistemiModel;
	}
	
	@Override
	public PagingLoadResult<SistemiPrestitoInterbibliotecarioModel> getDescrizioneSistemiPrestitoInterbibliotecarioFiltratePerPaginazioneCombobox(
			ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");

		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		int countAll = abiTabelleDinamicheLogic.countAllSistemiPrestitoInterbibliotecarioPaginatiPerCombo(query);

		List<SistemiPrestitoInterbibliotecarioModel> sublist = new ArrayList<SistemiPrestitoInterbibliotecarioModel>();

		List<SistemaPrestitoInterbibliotecarioDTO> listDB = abiTabelleDinamicheLogic.getSistemiPrestitoInterbibliotecarioPaginatiPerCombo(query, start, limit);
		Iterator<SistemaPrestitoInterbibliotecarioDTO> it = listDB.iterator();

		while (it.hasNext()) {
			SistemaPrestitoInterbibliotecarioDTO DTO = (SistemaPrestitoInterbibliotecarioDTO) it.next();

			SistemiPrestitoInterbibliotecarioModel model = new SistemiPrestitoInterbibliotecarioModel();

			model.setIdSistemiPrestitoInterbibliotecario(DTO.getId());
			model.setDescrizione(DTO.getDescrizione());
			model.setUrl(DTO.getUrl());
			
			sublist.add(model);

		}

		return new BasePagingLoadResult<SistemiPrestitoInterbibliotecarioModel>(sublist, start, countAll);
	}
}
