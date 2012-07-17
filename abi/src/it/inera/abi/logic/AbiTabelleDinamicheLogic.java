package it.inera.abi.logic;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.dto.PatrimonioSubCategoryDTO;
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

public interface AbiTabelleDinamicheLogic {

	public List<?> getListaVoci(int id_tabella);

	public List<?> getListaVociFiltratePerPaginazioneCombobox(int id_tabella,
			String searchValore, int rows, int offset, String sortField, String sortDir);

	public int countAllFilteredGenericaVoceSingola(int id_tabella, String query);

	public int countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(
			String query);

	public List<PatrimonioSubCategoryDTO> getPatrimoniSpecialiPerCategoriePaginatiPerCombo(
			String query, int start, int limit);
	
	public int countAllDeweyFiltratePerPaginazioneCombobox(String query);

	public List<Dewey> getDeweyFiltratePerPaginazioneCombobox(String query,
			int start, int limit, String sortField, String sortDir);

	public int countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox(
			String query, Integer idZonaEspansione, String dettaglioZona);

	public List<CataloghiCollettivi> getCataloghiCollettiviPossibiliFiltered(
			String query, Integer idZonaEspansione, String dettaglioZona,
			int start, int limit, String sortField, String sortDir);


	public void addCataloghiMaterialeUrl(Integer idCatalogoMaterialeUrl,
			Integer idPartecipaCatalogoMateriale, String url,
			String descrizioneUrl, boolean modificaUrl,int tipoCatalogo);

	public void removeCataloghiMaterialeUrl(int id_removeUrl, int tipoCatalogo);

	List<CataloghiGeneraliUrl> getCataloghiGeneraliUrl(int idPartecipaCatalogo);

	public List<CataloghiCollettiviMaterialeUrl> getCataloghiCollettiviMaterialeUrl(
			int idPartecipaCatalogo);

	public List<CataloghiSpecialiMaterialeUrl> getCataloghiSpecialiMaterialeUrl(
			int idPartecipaCatalogo);

	public String getDescrizionePatrCatById(Integer idCatMadre);


	public int countAllCataloghiCollettivi(String query);
	
	public List<CataloghiCollettivi> getAllCataloghiCollettivi(String query, int start, int limit);

	public List<PrestitoLocaleMaterialeEscluso> getMaterialiEsclusiByIdPrestitoLocale(Integer idPrestitoLocale);

	public void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale);

	public void removePrestitoLocaleMaterialeEscluso(int id_remove,Integer idPrestitoLocale);

	public List<PrestitoLocaleUtentiAmmessi> getUtentiAmmessiByIdPrestitoLocale(Integer idPrestitoLocale);

	public void addUtenteAmmessoAlPrestitoLocale(Integer idRecord,Integer idPrestitoLocale);

	public void removeUtenteAmmessoAlPrestitoLocale(int id_remove,Integer idPrestitoLocale);

	public void addEntryTabellaDinamicaVoceSingola(DynaTabDTO newDTo,int idTabella, boolean modifica) throws DuplicateEntryException;
	
	public void removeEntryTabellaDinamicaVoceSingola(int id_toRemove,int tipoTabella) ;

	public void addCatalogoCollettivoATabellaDinamica(Integer idCatalogo, String denominazioneCatalogo, Integer idZonaEspansione,	String zonaDettaglio,boolean modifica);

	public void removeCatalogoCollettivoATabellaDinamica(int id_toRemove);

	public void addDeweyTabelleDinamiche(String codiceDewey,String descrizione, boolean modifica) throws  DuplicateEntryException;

	public void removeDeweyTabelleDinamiche(String idr_removeRecord);

	public int countAllPatrimoniCategorieGrandiVociTabelleDinamiche();

	public List<PatrimonioSpecializzazioneCategoria> getListaPatrimoniCategorieGrandiVociTabelleDinamiche(
			int start, int limit);

	public void addPatrimoniGrandiVociTabelleDinamiche(Integer idCategoria,	String descrizioneCategoria, Integer idCategoriaMadre,boolean modifica) throws  DuplicateEntryException;

	public void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException;

	public int countAllPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche();

	public List<PatrimonioSpecializzazione> getListaPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche(int start, int limit, String sortField, String sortDir);

	public void addPatrimoniPiccoleVociTabelleDinamiche(Integer idSpecializzazione, String descrizioneSpecializzazione,	Integer idCategoria, boolean modifica)throws  DuplicateEntryException;

	public void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException;
	
	public void addProvinciaTabelleDinamiche(Integer idProvincia,String denominazione, String sigla, Integer idRegione,	boolean modifica)throws DuplicateEntryException;
 
	public void removeProvinciaTabelleDinamiche(int idr_removeRecord)throws ConstraintKeyViolationException;

	public void addComuneTabelleDinamiche(Integer idComune,	String denominazione, String codiceIstat, String codiceFinanze, Integer idProvincia, boolean modifica) throws DuplicateEntryException;

	public void removeComuneTabelleDinamiche(int idr_removeRecord)throws ConstraintKeyViolationException;
	
	public void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(Integer idSistemiPrestitoInterbibliotecario, String descrizione, String url, boolean modifica) throws DuplicateEntryException;
	
	public void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException;
	
	public List<SistemiPrestitoInterbibliotecario> getSistemiPrestitoInterbibliotecario();
} 

