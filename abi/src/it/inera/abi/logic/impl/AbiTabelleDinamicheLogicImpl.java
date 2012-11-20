package it.inera.abi.logic.impl;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.jpa.DynaTabJpa;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.dto.PatrimonioSubCategoryDTO;
import it.inera.abi.dto.SistemaPrestitoInterbibliotecarioDTO;
import it.inera.abi.logic.AbiTabelleDinamicheLogic;
import it.inera.abi.logic.UserActionLog;
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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe che implementa la logica delle principali operazioni riguardanti
 * le Tabelle Dinamiche
 *
 */
@Service
public class AbiTabelleDinamicheLogicImpl implements AbiTabelleDinamicheLogic {
	@PersistenceContext
	private EntityManager em;

	private DynaTabDao dynaTabDao;

	@Autowired private UserActionLog userActionLog; // logger per le azioni di salvataggio/modifica dell'utente
	
	@Autowired
	@Required
	public void setDynaTabDao(DynaTabDao dynaTabDao) {
		this.dynaTabDao = dynaTabDao;
	}

	@Override
	public List<?> getListaVoci(int id_tabella) {

		return dynaTabDao.loadAll(DtoJpaMapping.getDynaClass(id_tabella));
	}

	@Override
	public List<?> getListaVociFiltratePerPaginazioneCombobox(int id_tabella, String searchValore, int rows, int offset, String sortField, String sortDir) {
		return dynaTabDao.listRecordsFilteredForPagination(DtoJpaMapping.getDynaClass(id_tabella), searchValore, rows,	offset, sortField, sortDir);
	}

	@Override
	public void addEntryTabellaDinamicaVoceSingola(DynaTabDTO newDTo,int idTabella, boolean modifica) throws DuplicateEntryException {

		int trovati=dynaTabDao.existEntryVoceSingola(DtoJpaMapping.getDynaClass(idTabella), newDTo.getValue());
		if (modifica){
			if(trovati>1){
				//Se esiste già oltre se stesso ritorno eccezione con  opportuno messaggio
				throw new DuplicateEntryException(DynaTabJpa.DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
		}
		else{
			if(trovati>0){
				//Se esiste già ritorno eccezione con  opportuno messaggio
				throw new DuplicateEntryException(DynaTabJpa.DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
		}
		Object 	newDynaRecord = DtoJpaMapping.dto2DynaRecord(newDTo, modifica);
		if(modifica){
			dynaTabDao.updateRecord(newDynaRecord);
			userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica entry "+Utility.extractClassName(DtoJpaMapping.getDynaClass(idTabella).getName())+": id_record="+newDTo.getId());
			return;
		}
		dynaTabDao.saveRecord(newDynaRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica entry "+Utility.extractClassName(DtoJpaMapping.getDynaClass(idTabella).getName()));

	}

	@Override
	@Transactional
	public void removeEntryTabellaDinamicaVoceSingola(int id_toRemove,int tipoTabella) {

		Object toRemove = em.find(DtoJpaMapping.getDynaClass(tipoTabella), id_toRemove);
		dynaTabDao.removeRecord(toRemove);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione entry "+Utility.extractClassName(DtoJpaMapping.getDynaClass(tipoTabella).getName())+": id_record="+id_toRemove);
	}

	@Override
	public int countAllFilteredGenericaVoceSingola(int id_tabella, String query) {

		return dynaTabDao.countAllFiltered(DtoJpaMapping.getDynaClass(id_tabella), query);
	}

	@Override
	public int countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(String query) {
		return 	dynaTabDao.countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(query);
	}

	@Override
	public List<PatrimonioSubCategoryDTO> getPatrimoniSpecialiPerCategoriePaginatiPerCombo( String query, int start, int limit) {

		return dynaTabDao.getPatrimoniSpecialiPerCategoriePaginatiPerCombo(query, start, limit);
	}

	@Override
	public int countAllDeweyFiltratePerPaginazioneCombobox(String query) {
		return	dynaTabDao.countAllDeweyFiltratePerPaginazioneCombobox(query);

	}

	@Override
	public List<Dewey> getDeweyFiltratePerPaginazioneCombobox(String query,	int start, int limit, String sortField, String sortDir) {
		return dynaTabDao.getDeweyFiltratePerPaginazioneCombobox(query, start, limit, sortField, sortDir);
	}

	@Override
	public int countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox(String query,Integer idZonaEspansione,String dettaglioZona) {

		return dynaTabDao.countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox( query,idZonaEspansione, dettaglioZona);
	}

	@Override
	public List<CataloghiCollettivi> getCataloghiCollettiviPossibiliFiltered( String query, Integer idZonaEspansione, String dettaglioZona, int start, int limit, String sortField, String sortDir) {
		return	dynaTabDao.getCataloghiCollettiviPossibiliFiltered( query, idZonaEspansione, dettaglioZona,start,limit, sortField, sortDir);

	}

	@Override
	public void addCataloghiMaterialeUrl(Integer idCatalogoMaterialeUrl,  Integer idPartecipaCatalogoMateriale, String url,	String descrizioneUrl, boolean modificaUrl,int tipoCatalogo) {

		dynaTabDao.addCataloghiMaterialeUrl( idCatalogoMaterialeUrl, idPartecipaCatalogoMateriale,  url,descrizioneUrl,  modificaUrl,tipoCatalogo) ;

		if(tipoCatalogo==1){
			userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica url cataloghi generali:"+(modificaUrl?"id_record="+idCatalogoMaterialeUrl:"")+" - id_partecipazione_catalogo="+idPartecipaCatalogoMateriale);
		}
		else if(tipoCatalogo==2){
			userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica url cataloghi collettivi:"+(modificaUrl?"id_record="+idCatalogoMaterialeUrl:"")+" - id_partecipazione_catalogo="+idPartecipaCatalogoMateriale);

		}
		else if(tipoCatalogo==3){
			userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica url cataloghi speciali:"+(modificaUrl?"id_record="+idCatalogoMaterialeUrl:"")+" - id_partecipazione_catalogo="+idPartecipaCatalogoMateriale);
		}
	}

	@Override
	public void removeCataloghiMaterialeUrl(int id_removeUrl,int tipoCatalogo) {

		dynaTabDao.	removeCataloghiMaterialeUrl(id_removeUrl,tipoCatalogo);
		
		if(tipoCatalogo==1){
			userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione url cataloghi generali:id_record="+id_removeUrl);
		}
		else if(tipoCatalogo==2){
			userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione url cataloghi collettivi:id_record="+id_removeUrl);

		}
		else if(tipoCatalogo==3){
			userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione url cataloghi speciali:id_record="+id_removeUrl);
		}
	}


	@Override
	public List<CataloghiGeneraliUrl> getCataloghiGeneraliUrl(int idPartecipaCatalogo) {
		return dynaTabDao.getCataloghiGeneraliUrl(idPartecipaCatalogo);
	}

	@Override
	public List<CataloghiCollettiviMaterialeUrl> getCataloghiCollettiviMaterialeUrl(
			int idPartecipaCatalogo) {
		return dynaTabDao.getCataloghiCollettiviMaterialeUrl(idPartecipaCatalogo);
	}

	@Override
	public List<CataloghiSpecialiMaterialeUrl> getCataloghiSpecialiMaterialeUrl(
			int idPartecipaCatalogo) {
		return dynaTabDao.getCataloghiSpecialiMaterialeUrl(idPartecipaCatalogo);
	}

	@Override
	public String getDescrizionePatrCatById(Integer idCatMadre) {
		return dynaTabDao.getDescrizionePatrCatById(idCatMadre);
	}

	@Override
	public int countAllCataloghiCollettivi(String query) {

		return dynaTabDao.countAllCataloghiCollettivi(query);

	}

	@Override
	public List<CataloghiCollettivi> getAllCataloghiCollettivi(String query, int start, int limit) {

		return dynaTabDao.getAllCataloghiCollettivi(query, start, limit);

	}

	@Override
	public List<PrestitoLocaleMaterialeEscluso> getMaterialiEsclusiByIdPrestitoLocale(
			Integer idPrestitoLocale) {

		return dynaTabDao.getMaterialiEsclusiByIdPrestitoLocale(idPrestitoLocale);
	}

	@Override
	public void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale) {
		dynaTabDao.addMaterialeEscluso(idRecord,idPrestitoLocale);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica materiale escluso al prestito: "+(idRecord!=null?"id_record="+idRecord:"")+" id_presito_locale="+idPrestitoLocale);
	}

	@Override
	public void removePrestitoLocaleMaterialeEscluso(int id_remove,Integer idPrestitoLocale) {
		dynaTabDao.removePrestitoLocaleMaterialeEscluso(id_remove,idPrestitoLocale);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione materiale escluso al prestito: id_record="+id_remove+" - id_remove_prestito_locale="+idPrestitoLocale);
	}

	@Override
	public List<PrestitoLocaleUtentiAmmessi> getUtentiAmmessiByIdPrestitoLocale(Integer idPrestitoLocale) {
		return dynaTabDao.getUtentiAmmessiByIdPrestitoLocale(idPrestitoLocale);

	}

	@Override
	public void addUtenteAmmessoAlPrestitoLocale(Integer idRecord,Integer idPrestitoLocale) {
		dynaTabDao.addUtenteAmmessoAlPrestitoLocale(idRecord,idPrestitoLocale);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica utenti ammessi al prestito: "+(idRecord!=null?"id_record="+idRecord:"")+" id_presito_locale="+idPrestitoLocale);
	}

	@Override
	public void removeUtenteAmmessoAlPrestitoLocale(int id_remove,Integer idPrestitoLocale) {
		dynaTabDao.removeUtenteAmmessoAlPrestitoLocale(id_remove,idPrestitoLocale);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione utenti ammessi al prestito: id_record="+id_remove+" - id_remove_prestito_locale="+idPrestitoLocale);
	}

	@Override
	public void addCatalogoCollettivoATabellaDinamica(Integer idCatalogo, String denominazioneCatalogo, Integer idZonaEspansione,	String zonaDettaglio,boolean modifica) {
		dynaTabDao.addCatalogoCollettivoATabellaDinamica(idCatalogo, denominazioneCatalogo, idZonaEspansione, zonaDettaglio, modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica catalogo collettivo "+(modifica?":id_record="+idCatalogo:""));
	}

	@Override
	public void removeCatalogoCollettivoATabellaDinamica(int id_toRemove) {
		dynaTabDao.removeCatalogoCollettivoATabellaDinamica(id_toRemove);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione catalogo collettivo: id_record="+id_toRemove);
	}

	@Override
	public void addDeweyTabelleDinamiche(String codiceDewey,String descrizione, boolean modifica) throws DuplicateEntryException {
		dynaTabDao.addDeweyTabelleDinamiche(codiceDewey, descrizione, modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica codice Dewey "+(modifica?":id_record="+codiceDewey:""));
	}

	@Override
	public void removeDeweyTabelleDinamiche(String idr_removeRecord) {
		dynaTabDao.removeDeweyTabelleDinamiche(idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione codice Dewey: id_record="+idr_removeRecord);
	}

	@Override
	public int countAllPatrimoniCategorieGrandiVociTabelleDinamiche() {
		return	dynaTabDao.countAllPatrimoniCategorieGrandiVociTabelleDinamiche();
	}

	@Override
	public List<PatrimonioSpecializzazioneCategoria> getListaPatrimoniCategorieGrandiVociTabelleDinamiche(
			int start, int limit) {
		return dynaTabDao.getListaPatrimoniCategorieGrandiVociTabelleDinamiche(start,limit);

	}

	@Override
	public void addPatrimoniGrandiVociTabelleDinamiche(Integer idCategoria,	String descrizioneCategoria, Integer idCategoriaMadre,
			boolean modifica)	throws DuplicateEntryException {
		dynaTabDao.addPatrimoniGrandiVociTabelleDinamiche( idCategoria,	 descrizioneCategoria,  idCategoriaMadre, modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica patrimonio grandi voci "+(modifica?":id_record="+idCategoria:""));
	}

	@Override
	public void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		dynaTabDao.removePatrimoniGrandiVociTabelleDinamiche( idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione patrimonio grandi voci: id_record="+idr_removeRecord);
	}

	@Override
	public int countAllPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche() {

		return dynaTabDao.countAllPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche() ;
	}

	@Override
	public List<PatrimonioSpecializzazione> getListaPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche(int start, int limit, String sortField, String sortDir) {

		return dynaTabDao.getListaPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche( start,  limit,sortField,sortDir);

	}

	@Override
	public void addPatrimoniPiccoleVociTabelleDinamiche(Integer idSpecializzazione, String descrizioneSpecializzazione,
			Integer idCategoria, boolean modifica)	throws DuplicateEntryException {

		dynaTabDao.addPatrimoniPiccoleVociTabelleDinamiche( idSpecializzazione,  descrizioneSpecializzazione, idCategoria,  modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica patrimonio piccole voci "+(modifica?":id_record="+idSpecializzazione:""));
	}

	@Override
	public void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		dynaTabDao.removePatrimoniPiccoleVociTabelleDinamiche(idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione patrimonio grandi voci: id_record="+idr_removeRecord);
	}

	@Override
	public void addProvinciaTabelleDinamiche(Integer idProvincia,String denominazione, String sigla, Integer idRegione,boolean modifica) throws DuplicateEntryException {
		dynaTabDao.addProvinciaTabelleDinamiche( idProvincia, denominazione,  sigla,  idRegione, modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica provincia "+(modifica?":id_record="+idProvincia:""));
	}

	@Override
	public void removeProvinciaTabelleDinamiche(int idr_removeRecord)throws ConstraintKeyViolationException {
		dynaTabDao.removeProvinciaTabelleDinamiche(idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione provincia: id_record="+idr_removeRecord);
	}

	@Override
	public void addComuneTabelleDinamiche(Integer idComune,	String denominazione, String codiceIstat, String codiceFinanze,
			Integer idProvincia, boolean modifica)  throws DuplicateEntryException {
		dynaTabDao.addComuneTabelleDinamiche( idComune,	 denominazione,  codiceIstat, codiceFinanze, idProvincia,  modifica) ;
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica comune "+(modifica?":id_record="+idComune:""));
	}

	@Override
	public void removeComuneTabelleDinamiche(int idr_removeRecord)throws ConstraintKeyViolationException {
		dynaTabDao.removeComuneTabelleDinamiche( idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione comune: id_record="+idr_removeRecord);

	}

	@Override
	public void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(Integer idSistemiPrestitoInterbibliotecario, String descrizione, String url, boolean modifica) throws DuplicateEntryException {
		dynaTabDao.addSistemiPrestitoInterbibliotecarioTabelleDinamiche(idSistemiPrestitoInterbibliotecario, descrizione, url, modifica);
		userActionLog.logActionTabelleDinamicheDefaultUser("Salvataggio/modifica sistemi prestito interbibliotecario "+ (modifica ? ":id_record=" + idSistemiPrestitoInterbibliotecario : ""));

	}
	
	@Override
	public void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		dynaTabDao.removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(idr_removeRecord);
		userActionLog.logActionTabelleDinamicheDefaultUser("Rimozione sistemi prestito interbibliotecario: id_record=" + idr_removeRecord);

	}

	@Override
	public List<SistemiPrestitoInterbibliotecario> getSistemiPrestitoInterbibliotecario() {
		return dynaTabDao.getSistemiPrestitoInterbibliotecario();
		
	}
	
	@Override
	public int countAllSistemiPrestitoInterbibliotecarioPaginatiPerCombo(String query) {
		return 	dynaTabDao.countAllSistemiPrestitoInterbibliotecarioPaginatiPerCombo(query);
	}
	
	@Override
	public List<SistemaPrestitoInterbibliotecarioDTO> getSistemiPrestitoInterbibliotecarioPaginatiPerCombo(String query, int start, int limit) {
		return dynaTabDao.getSistemiPrestitoInterbibliotecarioPaginatiPerCombo(query, start, limit);
	}
}
