package it.inera.abi.dao.jpa;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dto.PatrimonioSubCategoryDTO;
import it.inera.abi.dto.SistemaPrestitoInterbibliotecarioDTO;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.CataloghiCollettiviMaterialeUrl;
import it.inera.abi.persistence.CataloghiCollettiviZonaTipo;
import it.inera.abi.persistence.CataloghiGeneraliUrl;
import it.inera.abi.persistence.CataloghiSpecialiMaterialeUrl;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.DatiRegioniIstat;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.FondiAntichiConsistenza;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.PatrimonioSpecializzazioneCategoria;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.Stato;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DynaTabJpa implements DynaTabDao {

	public static final String DUPLICATE_ENTRY_ERROR_MESSAGE="ATTENZIONE: la voce potrebbe essere già presente nel database";

	public static final String DUPLICATE_MULTIPLE_ENTRY_ERROR_MESSAGE="ATTENZIONE: una o più voci potrebbe essere già presente nel database";

	public static final String CONSTRAINT_KEY_VIOLATION_MESSAGE="ATTENZIONE: impossibile rimuovere la voce poichè potrebbe essere in uso in altre relazioni";

	@PersistenceContext
	private EntityManager em;

		@Override
	@Transactional
	public void saveRecord(Object obj) {
		em.persist(obj);
	}

		@Override
	@Transactional
	public void updateRecord(Object obj) {
		em.merge(obj);
	}

		@Override
	@Transactional
	public void removeRecord(Object obj) {
		em.remove(obj);
	}

	@Override
	@Transactional
	public Object loadRecord(Class<?> test, int id) {
		return em.find(test, id);
	}

	@Override
	@Transactional
	public Object loadRecord(Class<?> test, String id) {
		return em.find(test, id);
	}

	@Override
	@Transactional
	public Object searchRecord(Class<?> test, String searchValore) {
		Query query = null;
		if (test.getName().equalsIgnoreCase("it.inera.abi.persistence.Dewey")) {
			query = em.createQuery("from " + test.getName()
					+ " WHERE idDewey = :descrizione");
		} else {
			query = em.createQuery("from " + test.getName()
					+ " WHERE descrizione = :descrizione");
		}
		
		query.setParameter("descrizione", searchValore);
		List<?> results = query.getResultList();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public int countAllFiltered(Class<?> test, String searchValore) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(t) FROM " + test.getName() + " t ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" WHERE t.descrizione like :descrizione ");
		}
		Query query = em.createQuery(sb.toString());
		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			query.setParameter("descrizione", tmpSearchValore);
		}
		Number countResult = (Number) query.getSingleResult();

		return countResult.intValue();

	}

	@Override
	@Transactional
	public int existEntryVoceSingola(Class<?> test, String descrizione) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(t) FROM " + test.getName() + " t ");

		if (descrizione != null && descrizione.length() > 0) {
			sb.append(" WHERE t.descrizione = :descrizione ");
		}
		Query query = em.createQuery(sb.toString());
		if (descrizione != null && descrizione.length() > 0) {

			query.setParameter("descrizione", descrizione);
		}
		Number countResult = (Number) query.getSingleResult();

		return countResult.intValue();

	}



	/**
	 * Metodo per il caricamento delle voci delle tabelle dinamiche in maniera
	 * paginata per l'inserimento in combobox con paginazione
	 * */
	@Override
	@Transactional
	public List<?> listRecordsFilteredForPagination(Class<?> test, String searchValore, int offset, int rows, String sortField, String sortDir) {
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM " + test.getName() + " t ");
		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" WHERE t.descrizione like :descrizione ");
		}

		if(sortField!=null){
			if(sortField.equals("entry")){
				sb.append(" ORDER BY t.descrizione");
			}
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		
		Query query = em.createQuery(sb.toString());
		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			query.setParameter("descrizione", tmpSearchValore);
		}
		query.setMaxResults(rows);
		query.setFirstResult(offset);
		List<?> results = query.getResultList();

		return results;
	}

	@Override
	@Transactional
	public Object searchRecordByIdValore(Class<?> test, int idBiblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, idBiblioteca);
		Query query = em.createQuery("from " + test.getName()
				+ " WHERE biblioteca = :biblioteca");
		query.setParameter("biblioteca", biblioteca);
		List<?> results = query.getResultList();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteRecord(int id) {
		em.remove(id);
	}

	@Override
	@Transactional
	public List<?> loadAll(Class<?> test) {
		return em.createQuery("from " + test.getName()).getResultList();
	}

	@Override
	@Transactional
	public List<PatrimonioSubCategoryDTO> getPatrimoniSpecialiPerCategoriePaginatiPerCombo(String searchValore, int offset, int limit) {
		List<PatrimonioSubCategoryDTO> dtos = new ArrayList<PatrimonioSubCategoryDTO>();

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT psc.idPatrimonioSpecializzazioneCategoria, psc.descrizione, ps.idPatrimonioSpecializzazione, ps.descrizione, psc.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria ");
		sb.append(" FROM PatrimonioSpecializzazioneCategoria as psc, PatrimonioSpecializzazione as ps ");
		sb.append(" WHERE psc.idPatrimonioSpecializzazioneCategoria = ps.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" AND (psc.descrizione LIKE :searchValore  ");
			sb.append(" OR ps.descrizione LIKE :searchValore) ");
			sb.append(" AND psc.idPatrimonioSpecializzazioneCategoria != 0 ");
		}

		sb.append(" ORDER BY psc.descrizione, ps.descrizione ASC");


		Query q = em.createQuery(sb.toString());

		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}

		q.setMaxResults(limit);
		q.setFirstResult(offset);

		List result= q.getResultList();

		Iterator it = result.iterator();
		while (it.hasNext()) {
			Object [] tmpRecord = (Object []) it.next();

			PatrimonioSubCategoryDTO dto = new PatrimonioSubCategoryDTO();
			dto.setId_patrimonio_specializzazione((Integer)tmpRecord[2]);
			dto.setDescrizioneTipologia(""+tmpRecord[3]);
			dto.setId_cat((Integer)tmpRecord[0]);
			dto.setDescrizioneCat(""+tmpRecord[1]);
			if (tmpRecord[4] != null)
				dto.setIdCatMadre((Integer) tmpRecord[4]);

			dtos.add(dto);
		}
		return dtos;
	}



	@Override
	@Transactional
	public int countAallPatrimoniSpecialiPerCategoriePaginatiPerCombo(String searchValore){

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazioneCategoria as psc, PatrimonioSpecializzazione as ps ");
		sb.append(" WHERE psc.idPatrimonioSpecializzazioneCategoria = ps.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" AND (psc.descrizione LIKE :searchValore  ");
			sb.append(" OR ps.descrizione LIKE :searchValore) ");
			sb.append(" AND psc.idPatrimonioSpecializzazioneCategoria != 0 ");
		}

		Query q = em.createQuery(sb.toString());

		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}
		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();
	}
	
	@Override
	@Transactional
	public List<PatrimonioSpecializzazioneCategoria> getListaPatrimoniCategorieGrandiVociTabelleDinamiche(int offset, int limit){
		StringBuffer sb = new StringBuffer();

		sb.append(" FROM PatrimonioSpecializzazioneCategoria ");
		Query q = em.createQuery(sb.toString());

		q.setMaxResults(limit);
		q.setFirstResult(offset);

		List<PatrimonioSpecializzazioneCategoria> result= q.getResultList();

		return result;
	}
	@Override
	@Transactional
	public int countAllPatrimoniCategorieGrandiVociTabelleDinamiche(){
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazioneCategoria ");

		Query q = em.createQuery(sb.toString());

		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();
	}



	@Override
	@Transactional
	public String getDescrizionePatrCatById(Integer id) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT psc.descrizione ");
		sb.append(" FROM PatrimonioSpecializzazioneCategoria as psc ");
		sb.append(" WHERE ");

		if (id != null)
			sb.append(" psc.idPatrimonioSpecializzazioneCategoria = :id ");

		Query q = em.createQuery(sb.toString());		

		if (id != null) {

			int tmpid = id.intValue();
			q.setParameter("id", tmpid);

		}

		String result = (String) q.getSingleResult();

		return result;
	}



	/**Conta i Dewey filtrati con i parametri passati per argomento
	 * Utilizzato nella paginazione e autocompletamento delle combobox
	 * */
	@Override
	@Transactional
	public int countAllDeweyFiltratePerPaginazioneCombobox(String searchValore){

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM Dewey as d ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" WHERE d.idDewey LIKE :searchValore");
		}

		Query q = em.createQuery(sb.toString());

		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}
		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();
	}
	/**Conta i Dewey filtrati con i parametri passati per argomento
	 * Utilizzato nella paginazione e autocompletamento delle combobox
	 * */
	@Override
	@Transactional
	public List<Dewey> getDeweyFiltratePerPaginazioneCombobox(String searchValore, int offset, int limit, String sortField, String sortDir){
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM Dewey as d ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" WHERE d.idDewey LIKE :searchValore ");
		}

		if(sortField!=null){
			if(sortField.equals("dewey")){
				sb.append(" ORDER BY d.idDewey ");
			}else if(sortField.equals("descrizioneDewey")){
				sb.append(" ORDER BY d.descrizione ");
			}
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		
		Query q = em.createQuery(sb.toString());

		
		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}
		q.setMaxResults(limit);
		q.setFirstResult(offset);

		List<Dewey> result= q.getResultList();
		return result;
	}
	/**Conta i Cataloghi collettivi filtrati con i parametri passati per argomento
	 * Utilizzato nella paginazione e autocompletamento delle combobox
	 * */
	@Override
	@Transactional
	public int countAllCataloghiCollettiviPossibiliPerPaginazioneCombobox(
			String query, Integer idZonaEspansione, String dettaglioZona) {

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(c) ");
		sb.append(" FROM CataloghiCollettivi as c ");


		if (query != null && query.length() > 0) {

			criteria.add(" c.descrizione LIKE :descrizione ");
		}

		if ((Integer)idZonaEspansione != null /*&& query.length() > 0*/) {

			criteria.add(" c.cataloghiCollettiviZonaTipo.idCataloghiCollettiviZonaTipo = :idZonaEspansione ");
		}

		if (dettaglioZona != null && dettaglioZona.length() > 0) {

			criteria.add(" c.dettaglioZona LIKE :dettaglioZona ");
		}

		if (criteria.size() > 0) {
			sb.append(" WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {
			String tmpSearchValore = "%".concat(query).concat("%");
			q.setParameter("descrizione",tmpSearchValore );
		}

		if ((Integer)idZonaEspansione != null /*&& query.length() > 0*/) {
			q.setParameter("idZonaEspansione",idZonaEspansione );
		}

		if (dettaglioZona != null && dettaglioZona.length() > 0) {
			String tmpSearchValore = "%".concat(dettaglioZona).concat("%");
			q.setParameter("dettaglioZona",tmpSearchValore );
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}
	/**Carica la lista dei Cataloghi collettivi filtrati con i parametri passati per argomento
	 * Utilizzato nella paginazione e autocompletamento delle combobox
	 * */
	@Override
	@Transactional
	public List<CataloghiCollettivi> getCataloghiCollettiviPossibiliFiltered(
			String query, Integer idZonaEspansione, String dettaglioZona,
			int start, int limit, String sortField, String sortDir) {

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();


		sb.append(" FROM CataloghiCollettivi as c ");


		if (query != null && query.length() > 0) {

			criteria.add(" c.descrizione LIKE :descrizione ");
		}

		if ((Integer)idZonaEspansione != null /*&& query.length() > 0*/) {

			criteria.add(" c.cataloghiCollettiviZonaTipo.idCataloghiCollettiviZonaTipo = :idZonaEspansione ");
		}

		if (dettaglioZona != null && dettaglioZona.length() > 0) {

			criteria.add(" c.dettaglioZona LIKE :dettaglioZona ");
		}

		if (criteria.size() > 0) {
			sb.append(" WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		if(sortField!=null){
			if(sortField.equals("denominazione")){
				sb.append(" ORDER BY c.descrizione ");	
			}else if(sortField.equals("zonaEspansione")){
				sb.append(" ORDER BY c.cataloghiCollettiviZonaTipo.descrizione ");
			}else if(sortField.equals("zona")){
				sb.append(" ORDER BY c.dettaglioZona ");
			}
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		
		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {
			String tmpSearchValore = "%".concat(query).concat("%");
			q.setParameter("descrizione",tmpSearchValore );
		}

		if ((Integer)idZonaEspansione != null /*&& query.length() > 0*/) {
			q.setParameter("idZonaEspansione",idZonaEspansione );
		}

		if (dettaglioZona != null && dettaglioZona.length() > 0) {
			String tmpSearchValore = "%".concat(dettaglioZona).concat("%");
			q.setParameter("dettaglioZona",tmpSearchValore );
		}

		q.setMaxResults(limit);
		q.setFirstResult(start);

		List<CataloghiCollettivi> result= q.getResultList();
		return result;
	}

	@Override
	@Transactional
	public void addCataloghiMaterialeUrl(Integer idCatalogoMaterialeUrl,Integer idPartecipaCatalogoMateriale, String url,String descrizioneUrl, boolean modificaUrl,int tipoCatalogo) {


		if(tipoCatalogo==1){
			PartecipaCataloghiGenerali partecipaCataloghiGeneraliMateriale = em.find(PartecipaCataloghiGenerali.class,idPartecipaCatalogoMateriale);
			if(modificaUrl==false){
				CataloghiGeneraliUrl cataloghiGeneraliUrl = new CataloghiGeneraliUrl();
				cataloghiGeneraliUrl.setPartecipaCataloghiGenerali(partecipaCataloghiGeneraliMateriale);
				cataloghiGeneraliUrl.setUrl(url);
				cataloghiGeneraliUrl.setDescrizione(descrizioneUrl);
				em.persist(cataloghiGeneraliUrl);
			}else{
				CataloghiGeneraliUrl cataloghiGeneraliUrl = em.find(CataloghiGeneraliUrl.class, idCatalogoMaterialeUrl);
				cataloghiGeneraliUrl.setUrl(url);
				cataloghiGeneraliUrl.setDescrizione(descrizioneUrl);

				em.merge(cataloghiGeneraliUrl);
			}
		}

		if(tipoCatalogo==2){
			if(modificaUrl==false){
				PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = em.find(PartecipaCataloghiCollettiviMateriale.class,idPartecipaCatalogoMateriale);
				CataloghiCollettiviMaterialeUrl cataloghiCollettiviMaterialeUrl = new CataloghiCollettiviMaterialeUrl();
				cataloghiCollettiviMaterialeUrl.setPartecipaCataloghiCollettiviMateriale(partecipaCataloghiCollettiviMateriale);
				cataloghiCollettiviMaterialeUrl.setUrl(url);
				cataloghiCollettiviMaterialeUrl.setDescrizione(descrizioneUrl);
				em.persist(cataloghiCollettiviMaterialeUrl);
			}else{
				CataloghiCollettiviMaterialeUrl cataloghiCollettiviMaterialeUrl = em.find(CataloghiCollettiviMaterialeUrl.class, idCatalogoMaterialeUrl);
				cataloghiCollettiviMaterialeUrl.setUrl(url);
				cataloghiCollettiviMaterialeUrl.setDescrizione(descrizioneUrl);

				em.merge(cataloghiCollettiviMaterialeUrl);
			}
		}
		if(tipoCatalogo==3){
			PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale = em.find(PartecipaCataloghiSpecialiMateriale.class,idPartecipaCatalogoMateriale);
			if(modificaUrl==false){
				CataloghiSpecialiMaterialeUrl cataloghiSpecialiMaterialeUrl = new CataloghiSpecialiMaterialeUrl();
				cataloghiSpecialiMaterialeUrl.setPartecipaCataloghiSpecialiMateriale(partecipaCataloghiSpecialiMateriale);
				cataloghiSpecialiMaterialeUrl.setUrl(url);
				cataloghiSpecialiMaterialeUrl.setDescrizione(descrizioneUrl);
				em.persist(cataloghiSpecialiMaterialeUrl);
			}else{
				CataloghiSpecialiMaterialeUrl cataloghiSpecialiMaterialeUrl = em.find(CataloghiSpecialiMaterialeUrl.class, idCatalogoMaterialeUrl);
				cataloghiSpecialiMaterialeUrl.setUrl(url);
				cataloghiSpecialiMaterialeUrl.setDescrizione(descrizioneUrl);

				em.merge(cataloghiSpecialiMaterialeUrl);
			}
		}
	}

	@Override
	@Transactional
	public void removeCataloghiMaterialeUrl(int id_removeUrl,int tipoCatalogo) {
		if(tipoCatalogo==1){
			CataloghiGeneraliUrl cataloghiGeneraliUrl = em.find(CataloghiGeneraliUrl.class, id_removeUrl);
			em.remove(cataloghiGeneraliUrl);
		}
		if(tipoCatalogo==2){
			CataloghiCollettiviMaterialeUrl cataloghiCollettiviMaterialeUrl = em.find(CataloghiCollettiviMaterialeUrl.class, id_removeUrl);
			em.remove(cataloghiCollettiviMaterialeUrl);
		}
		if(tipoCatalogo==3){
			CataloghiSpecialiMaterialeUrl cataloghiSpecialiMaterialeUrl = em.find(CataloghiSpecialiMaterialeUrl.class, id_removeUrl);
			em.remove(cataloghiSpecialiMaterialeUrl);
		}
	}

	@Override
	@Transactional
	public List<CataloghiGeneraliUrl> getCataloghiGeneraliUrl(int idPartecipaCatalogo) {
		PartecipaCataloghiGenerali partecipaCataloghiGenerali = em.find(PartecipaCataloghiGenerali.class, idPartecipaCatalogo);

		List<CataloghiGeneraliUrl> cataloghiGeneraliUrls = partecipaCataloghiGenerali.getCataloghiGeneraliUrls();
		Iterator<CataloghiGeneraliUrl> iturls = cataloghiGeneraliUrls.iterator();
		while (iturls.hasNext()) {
			CataloghiGeneraliUrl cataloghiGeneraliUrl = (CataloghiGeneraliUrl) iturls
			.next();
			//Iterazione anti-lazy CataloghiGeneraliUrl

		}
		return cataloghiGeneraliUrls;
	}

	@Override
	@Transactional
	public List<CataloghiCollettiviMaterialeUrl> getCataloghiCollettiviMaterialeUrl(
			int idPartecipaCatalogo) {

		PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = em.find(PartecipaCataloghiCollettiviMateriale.class, idPartecipaCatalogo);

		List<CataloghiCollettiviMaterialeUrl> cataloghiCollettiviMaterialeUrls = partecipaCataloghiCollettiviMateriale.getCataloghiCollettiviMaterialeUrls();
		Iterator<CataloghiCollettiviMaterialeUrl> iturls = cataloghiCollettiviMaterialeUrls.iterator();
		while (iturls.hasNext()) {
			CataloghiCollettiviMaterialeUrl cataloghiCollettiviMaterialeUrl = (CataloghiCollettiviMaterialeUrl) iturls
			.next();
			//Iterazione anti-lazy CataloghiCollettiviMaterialeUrl
		}
		return cataloghiCollettiviMaterialeUrls;
	}
	@Override
	@Transactional
	public List<CataloghiSpecialiMaterialeUrl> getCataloghiSpecialiMaterialeUrl(
			int idPartecipaCatalogo) {

		PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale = em.find(PartecipaCataloghiSpecialiMateriale.class, idPartecipaCatalogo);

		List<CataloghiSpecialiMaterialeUrl> cataloghiSpecialiMaterialeUrls = partecipaCataloghiSpecialiMateriale.getCataloghiSpecialiMaterialeUrls();
		Iterator<CataloghiSpecialiMaterialeUrl> iturls = cataloghiSpecialiMaterialeUrls.iterator();
		while (iturls.hasNext()) {
			CataloghiSpecialiMaterialeUrl cataloghiSpecialiMaterialeUrl = (CataloghiSpecialiMaterialeUrl) iturls
			.next();
			//Iterazione anti-lazy CataloghiSpecialiMaterialeUrl
		}
		return cataloghiSpecialiMaterialeUrls;
	}

	@Override
	@Transactional
	public int countAllCataloghiCollettivi(String query) {

		List<String> criteria = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(c) ");
		sb.append(" FROM CataloghiCollettivi as c ");


		if (query != null && query.length() > 0) {

			criteria.add(" c.descrizione LIKE :descrizione ");

		}

		if (criteria.size() > 0) {

			sb.append(" WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {

				sb.append(" AND ");

			}
			sb.append(criteria.get(i));
		}

		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {

			String tmpSearchValore = "%".concat(query).concat("%");
			q.setParameter("descrizione", tmpSearchValore);

		}

		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();

	}


	@Override
	@Transactional
	public List<CataloghiCollettivi> getAllCataloghiCollettivi(String query, int start, int limit) {

		List<String> criteria = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();

		sb.append(" FROM CataloghiCollettivi as c ");

		if (query != null && query.length() > 0) {

			criteria.add(" c.descrizione LIKE :descrizione ");

		}

		if (criteria.size() > 0) {

			sb.append(" WHERE ");

		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {

				sb.append(" AND ");

			}

			sb.append(criteria.get(i));

		}

		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {

			String tmpSearchValore = "%".concat(query).concat("%");
			q.setParameter("descrizione", tmpSearchValore);

		}

		q.setMaxResults(limit);
		q.setFirstResult(start);

		List<CataloghiCollettivi> result= q.getResultList();
		return result;

	}

	@Override
	@Transactional
	public List<PrestitoLocaleMaterialeEscluso> getMaterialiEsclusiByIdPrestitoLocale(
			Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		List<PrestitoLocaleMaterialeEscluso> materialeEsclusos = prestitoLocale.getPrestitoLocaleMaterialeEscluso();
		Iterator<PrestitoLocaleMaterialeEscluso> it = materialeEsclusos.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			PrestitoLocaleMaterialeEscluso prestitoLocaleMaterialeEscluso = (PrestitoLocaleMaterialeEscluso) it
			.next();	
		}
		return materialeEsclusos;

	}

	@Override
	@Transactional
	public void addMaterialeEscluso(Integer idRecord, Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		PrestitoLocaleMaterialeEscluso materialeEscluso = em.find(PrestitoLocaleMaterialeEscluso.class, idRecord);

		List<PrestitoLocaleMaterialeEscluso> materialeEsclusos = prestitoLocale.getPrestitoLocaleMaterialeEscluso();
		if (materialeEsclusos == null) materialeEsclusos = new ArrayList<PrestitoLocaleMaterialeEscluso>();
		Iterator<PrestitoLocaleMaterialeEscluso> it = materialeEsclusos.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			PrestitoLocaleMaterialeEscluso prestitoLocaleMaterialeEscluso = (PrestitoLocaleMaterialeEscluso) it
			.next();	
		}

		materialeEsclusos.add(materialeEscluso);

		prestitoLocale.setPrestitoLocaleMaterialeEscluso(materialeEsclusos);

		em.merge(prestitoLocale);
	}

	@Override
	@Transactional
	public void removePrestitoLocaleMaterialeEscluso(int id_remove,
			Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		Integer removeIndex=null;

		List<PrestitoLocaleMaterialeEscluso> materialeEsclusos = prestitoLocale.getPrestitoLocaleMaterialeEscluso();
		Iterator<PrestitoLocaleMaterialeEscluso> it = materialeEsclusos.iterator();
		while (it.hasNext()) {

			PrestitoLocaleMaterialeEscluso prestitoLocaleMaterialeEscluso = (PrestitoLocaleMaterialeEscluso) it
			.next();	

			if(prestitoLocaleMaterialeEscluso.getIdPrestitoLocaleMaterialeEscluso()==id_remove){
				removeIndex=materialeEsclusos.indexOf(prestitoLocaleMaterialeEscluso);
			}
		}

		if (removeIndex!=null){
			materialeEsclusos.remove(removeIndex.intValue());
		}

		prestitoLocale.setPrestitoLocaleMaterialeEscluso(materialeEsclusos);

		em.merge(prestitoLocale);
	}

	@Override
	@Transactional
	public List<PrestitoLocaleUtentiAmmessi> getUtentiAmmessiByIdPrestitoLocale(Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		List<PrestitoLocaleUtentiAmmessi> utentiAmmessis = prestitoLocale.getPrestitoLocaleUtentiAmmessis();
		Iterator<PrestitoLocaleUtentiAmmessi> it = utentiAmmessis.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			PrestitoLocaleUtentiAmmessi utenteAmmesso = (PrestitoLocaleUtentiAmmessi) it.next();	
		}
		return utentiAmmessis;

	}

	@Override
	@Transactional
	public void addUtenteAmmessoAlPrestitoLocale(Integer idRecord, Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		PrestitoLocaleUtentiAmmessi newUtenteAmmesso = em.find(PrestitoLocaleUtentiAmmessi.class, idRecord);

		List<PrestitoLocaleUtentiAmmessi> utentiAmmessis = prestitoLocale.getPrestitoLocaleUtentiAmmessis();
		if (utentiAmmessis == null) utentiAmmessis = new ArrayList<PrestitoLocaleUtentiAmmessi>();
		Iterator<PrestitoLocaleUtentiAmmessi> it = utentiAmmessis.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			PrestitoLocaleUtentiAmmessi tmpUtenteAmmesso = (PrestitoLocaleUtentiAmmessi) it.next();	
		}

		utentiAmmessis.add(newUtenteAmmesso);

		prestitoLocale.setPrestitoLocaleUtentiAmmessis(utentiAmmessis);

		em.merge(prestitoLocale);
	}

	@Override
	@Transactional
	public void removeUtenteAmmessoAlPrestitoLocale(int id_remove,Integer idPrestitoLocale) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class,idPrestitoLocale);

		Integer removeIndex=null;

		List<PrestitoLocaleUtentiAmmessi> utentiAmmessis = prestitoLocale.getPrestitoLocaleUtentiAmmessis();
		Iterator<PrestitoLocaleUtentiAmmessi> it = utentiAmmessis.iterator();
		while (it.hasNext()) {

			PrestitoLocaleUtentiAmmessi utenteAmmesso = (PrestitoLocaleUtentiAmmessi) it
			.next();	

			if(utenteAmmesso.getIdPrestitoLocaleUtentiAmmessi()==id_remove){
				removeIndex=utentiAmmessis.indexOf(utenteAmmesso);
			}
		}

		if (removeIndex!=null){
			utentiAmmessis.remove(removeIndex.intValue());
		}

		prestitoLocale.setPrestitoLocaleUtentiAmmessis(utentiAmmessis);

		em.merge(prestitoLocale);
	}

	@Override
	@Transactional
	public void addCatalogoCollettivoATabellaDinamica(Integer idCatalogo,String denominazioneCatalogo,
			Integer idZonaEspansione,String zonaDettaglio,boolean modifica) {

		if (modifica) {
			CataloghiCollettivi toUpdate = em.find(CataloghiCollettivi.class, idCatalogo);
			toUpdate.setDescrizione(denominazioneCatalogo);
			toUpdate.setDettaglioZona(zonaDettaglio);
			if(idZonaEspansione!=null){
				CataloghiCollettiviZonaTipo tmpZonaEspansione = em.find(CataloghiCollettiviZonaTipo.class, idZonaEspansione);
				toUpdate.setCataloghiCollettiviZonaTipo(tmpZonaEspansione);
			} 
			em.merge(toUpdate);
		}
		else{
			CataloghiCollettivi toSave=new CataloghiCollettivi();
			toSave.setDescrizione(denominazioneCatalogo);
			if(idZonaEspansione!=null){
				CataloghiCollettiviZonaTipo tmpZona = em.find(CataloghiCollettiviZonaTipo.class, idZonaEspansione);
				toSave.setCataloghiCollettiviZonaTipo(tmpZona);
			} 
			toSave.setDettaglioZona(zonaDettaglio);
			em.persist(toSave);
		}

	}

	@Override
	@Transactional
	public void removeCatalogoCollettivoATabellaDinamica(int idRemove){
		CataloghiCollettivi toRemove =em.find(CataloghiCollettivi.class, idRemove);
		List<PartecipaCataloghiCollettiviMateriale> tmpList = toRemove.getPartecipaCataloghiCollettiviMateriales();
		Iterator<PartecipaCataloghiCollettiviMateriale>	 ti= tmpList.iterator();
		while (ti.hasNext()) {
			PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = (PartecipaCataloghiCollettiviMateriale) ti.next();
			List<CataloghiCollettiviMaterialeUrl> tmpUrlList= partecipaCataloghiCollettiviMateriale.getCataloghiCollettiviMaterialeUrls();
			Iterator<CataloghiCollettiviMaterialeUrl> itUrls = tmpUrlList.iterator();
			while (itUrls.hasNext()) {
				CataloghiCollettiviMaterialeUrl cataloghiCollettiviMaterialeUrl = (CataloghiCollettiviMaterialeUrl) itUrls
				.next();
				//Cancello le dipendenze di PartecipaCataloghiCollettiviMateriale con CataloghiCollettiviMaterialeUrl
				em.remove(cataloghiCollettiviMaterialeUrl);		
			}
			//Cancello le dipendenze di CataloghiCollettivi con PartecipaCataloghiCollettiviMateriale

			em.remove(partecipaCataloghiCollettiviMateriale);
		}
		//Rimuovo l'oggetto CataloghiCollettivi
		em.remove(toRemove);
	}

	@Override
	@Transactional
	public void addDeweyTabelleDinamiche(String codiceDewey,String descrizione, boolean modifica) throws DuplicateEntryException{

		if(modifica){
			Dewey toUpdate = em.find(Dewey.class, codiceDewey);
			toUpdate.setDescrizione(descrizione);
			em.merge(toUpdate);
		}else{
			existDewey(codiceDewey);
			Dewey toSave = new Dewey();
			toSave.setIdDewey(codiceDewey);
			toSave.setDescrizione(descrizione);
			em.persist(toSave);
		}
	}

	/**
	 * @param codiceDewey
	 * @throws DuplicateEntryException
	 */
	@Transactional
	private void existDewey(String codiceDewey) throws DuplicateEntryException {
		//Controllo se sul db esiste un Dewey con lo stesso codice
		Dewey checkDewey = em.find(Dewey.class, codiceDewey);
		if(checkDewey!=null){
			//Se esiste già ritorno eccezione con  opportuno messaggio
			throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
		}
	}

	@Override
	@Transactional
	public void removeDeweyTabelleDinamiche(String id_removeRecord) {
		Dewey toRemove = em.find(Dewey.class, id_removeRecord);

		StringBuffer sbr = new StringBuffer();
		sbr.append(" FROM FondiSpeciali f ");
		sbr.append(" WHERE f.dewey = :dewey ");
		//Seleziono tutti i fondi speciali che contenono il dewey da eliminare
		Query query = em.createQuery(sbr.toString());
		query.setParameter("dewey", toRemove);

		List<FondiSpeciali> fondiSpecialis =query.getResultList();
		Iterator<FondiSpeciali> itf = fondiSpecialis.iterator();
		while (itf.hasNext()) {
			FondiSpeciali fondiSpeciali = (FondiSpeciali) itf.next();
			//Elimino le dipendenze tra Dewey e fondi speciali
			fondiSpeciali.setDewey(null);
			em.merge(fondiSpeciali);
		}

		//Elimino le dipendenze tra Dewey e DeweyLibero
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM DeweyLibero d ");
		sb.append(" WHERE d.id.idDewey = :idDewey ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("idDewey", id_removeRecord);
		q.executeUpdate();
		//Elimino il Dewey
		em.remove(toRemove); 
	}

	@Override
	@Transactional
	public void addPatrimoniGrandiVociTabelleDinamiche(Integer idCategoria,	String descrizioneCategoria, Integer idCategoriaMadre, boolean modifica)
	throws DuplicateEntryException {
		if(modifica){
			//Carico l'oggetto PatrimonioSpecializzazioneCategoria da modificare
			PatrimonioSpecializzazioneCategoria toUpdate = em.find(PatrimonioSpecializzazioneCategoria.class, idCategoria);

			Number countResult = existPatrimonioSpecializzazioneCategoria(descrizioneCategoria);
			if(countResult.intValue()>1){
				//Se esiste già ritorno eccezione con  opportuno messaggio
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}

			toUpdate.setDescrizione(descrizioneCategoria);
			if(idCategoriaMadre!=null){
				PatrimonioSpecializzazioneCategoria categoriaMadreCategoria=em.find(PatrimonioSpecializzazioneCategoria.class, idCategoriaMadre);
				toUpdate.setPatrimonioSpecializzazioneCategoria(categoriaMadreCategoria);
			}
			//Salvo la modifica
			em.merge(toUpdate);
		}else{
			Number countResult = existPatrimonioSpecializzazioneCategoria(descrizioneCategoria);
			if(countResult.intValue()>0){
				//Se esiste già ritorno eccezione con  opportuno messaggio
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
			//ELSE creo il nuovo oggetto PatrimonioSpecializzazioneCategoria
			PatrimonioSpecializzazioneCategoria toSave = new PatrimonioSpecializzazioneCategoria();
			toSave.setDescrizione(descrizioneCategoria);
			if(idCategoriaMadre!=null){
				PatrimonioSpecializzazioneCategoria categoriaMadreCategoria=em.find(PatrimonioSpecializzazioneCategoria.class, idCategoriaMadre);
				toSave.setPatrimonioSpecializzazioneCategoria(categoriaMadreCategoria);
			}
			//Salvo il nuovo oggetto
			em.persist(toSave);
		}


	}

	/**
	 * @param descrizioneCategoria
	 * @return
	 */
	@Transactional
	private Number existPatrimonioSpecializzazioneCategoria(String descrizioneCategoria) {
		//Controllo se sul db esiste un  PatrimonioSpecializzazione con la stessa descrizione
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazioneCategoria p");
		if(descrizioneCategoria!=null){
			sb.append(" WHERE p.descrizione = :descrizione ");		
		}
		Query q = em.createQuery(sb.toString());
		if(descrizioneCategoria!=null){
			q.setParameter("descrizione", descrizioneCategoria);
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult;
	}

	@Override
	@Transactional
	public void removePatrimoniGrandiVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		PatrimonioSpecializzazioneCategoria toRemove = em.find(PatrimonioSpecializzazioneCategoria.class, idr_removeRecord);

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazione p");
		sb.append(" WHERE p.patrimonioSpecializzazioneCategoria = :patrimonioSpecializzazioneCategoria ");	

		Query q = em.createQuery(sb.toString());
		q.setParameter("patrimonioSpecializzazioneCategoria", toRemove);

		Number countResult = (Number) q.getSingleResult();

		if(countResult.intValue()>0 || countResult.intValue()>0){
			throw new ConstraintKeyViolationException("Impossibile rimuovere la voce poichè è collegata a: <br>" +
					(countResult.intValue()>0?countResult.intValue() + " specializzazioni (piccole voci);<br>":""));
		}

		em.remove(toRemove);
	}

	@Override
	@Transactional
	public int countAllPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazione ");

		Query q = em.createQuery(sb.toString());

		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();
	}

	@Override
	@Transactional
	public List<PatrimonioSpecializzazione> getListaPatrimoniSpecializzazioniPiccoleVociTabelleDinamiche(
			int start, int limit, String sortField, String sortDir) {
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM PatrimonioSpecializzazione p");

		if(sortField!=null){
			if(sortField.equals("categoriaDescrizione")){
				sb.append(" ORDER BY p.descrizione");
			}else if(sortField.equals("categoriaMadreDescrizione")){
				sb.append(" ORDER BY p.patrimonioSpecializzazioneCategoria.descrizione");
			}
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		Query q = em.createQuery(sb.toString());

		q.setMaxResults(limit);
		q.setFirstResult(start);

		List<PatrimonioSpecializzazione> result= q.getResultList();

		return result;
	}

	@Override
	@Transactional
	public void addPatrimoniPiccoleVociTabelleDinamiche(Integer idSpecializzazione,	String descrizioneSpecializzazione, Integer idCategoria, boolean modifica)
	throws DuplicateEntryException {
		if(modifica){
			//Carico l'oggetto PatrimonioSpecializzazioneCategoria da modificare
			PatrimonioSpecializzazione toUpdate = em.find(PatrimonioSpecializzazione.class, idSpecializzazione);

			toUpdate.setDescrizione(descrizioneSpecializzazione);
			if(idCategoria!=null){
				PatrimonioSpecializzazioneCategoria categoria=em.find(PatrimonioSpecializzazioneCategoria.class, idCategoria);
				toUpdate.setPatrimonioSpecializzazioneCategoria(categoria);
			}
			//Salvo la modifica
			em.merge(toUpdate);
		}else{
			Number countResult = existPatrimonioSpecializzazione(descrizioneSpecializzazione);
			if(countResult.intValue()>0){
				//Se esiste già ritorno eccezione con  opportuno messaggio
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
			//ELSE creo il nuovo oggetto PatrimonioSpecializzazione
			PatrimonioSpecializzazione toSave = new PatrimonioSpecializzazione();
			toSave.setDescrizione(descrizioneSpecializzazione);
			if(idCategoria!=null){
				PatrimonioSpecializzazioneCategoria categoria=em.find(PatrimonioSpecializzazioneCategoria.class, idCategoria);
				toSave.setPatrimonioSpecializzazioneCategoria(categoria);
			}
			//Salvo il nuovo oggetto
			em.persist(toSave);
		}
	}

	/**
	 * @param descrizioneSpecializzazione
	 * @return
	 */
	@Transactional
	private Number existPatrimonioSpecializzazione(
			String descrizioneSpecializzazione) {
		//Controllo se sul db esiste un PatrimonioSpecializzazione con la stessa descrizione
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PatrimonioSpecializzazione p");
		if(descrizioneSpecializzazione!=null){
			sb.append(" WHERE p.descrizione = :descrizione ");		
		}
		Query q = em.createQuery(sb.toString());
		if(descrizioneSpecializzazione!=null){
			q.setParameter("descrizione", descrizioneSpecializzazione);
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult;
	}

	@Override
	@Transactional
	public void removePatrimoniPiccoleVociTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		PatrimonioSpecializzazione toRemove = em.find(PatrimonioSpecializzazione.class, idr_removeRecord);
		/*Seleziono tutti i PartecipaCataloghiCollettiviMateriale in relazione con l'oggetto da rimuovere*/

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM PartecipaCataloghiCollettiviMateriale p");
		sb.append(" WHERE p.patrimonioSpecializzazione = :patrimonioSpecializzazione ");	

		Query q = em.createQuery(sb.toString());
		q.setParameter("patrimonioSpecializzazione", toRemove);
		Number countResultPartecipaPartecipaCataloghiCollettiviMateriale = (Number) q.getSingleResult();

		/*Seleziono tutti i PartecipaCataloghiSpecialiMateriale in relazione con l'oggetto da rimuovere*/
		StringBuffer sb2 = new StringBuffer();
		sb2.append(" SELECT COUNT(*) "); 
		sb2.append(" FROM PartecipaCataloghiSpecialiMateriale p");
		sb2.append(" WHERE p.patrimonioSpecializzazione = :patrimonioSpecializzazione ");	

		Query q2 = em.createQuery(sb2.toString());
		q2.setParameter("patrimonioSpecializzazione", toRemove);
		Number countResultPartecipaPartecipaCataloghiSpecialiMateriale = (Number) q2.getSingleResult();

		/*Lancio eccezione e specifico nel messaggio le dipendenze che l'hanno scatenata*/
		if(countResultPartecipaPartecipaCataloghiCollettiviMateriale.intValue()>0 || countResultPartecipaPartecipaCataloghiSpecialiMateriale.intValue()>0){
			throw new ConstraintKeyViolationException("Impossibile rimuovere la voce poichè è collegata a: <br>" +
					(countResultPartecipaPartecipaCataloghiCollettiviMateriale.intValue()>0?countResultPartecipaPartecipaCataloghiCollettiviMateriale.intValue() + " partecipazioni a cataloghi collettivi;<br>":"")+
					(countResultPartecipaPartecipaCataloghiSpecialiMateriale.intValue()>0?countResultPartecipaPartecipaCataloghiSpecialiMateriale.intValue()+ " partecipazioni a cataloghi speciali;":""));
		}

		/*Seleziono tutti i Patrimonio in relazione con l'oggetto da rimuovere*/
		StringBuffer sb3 = new StringBuffer();
		sb3.append(" FROM Patrimonio p");
		sb3.append(" WHERE p.patrimonioSpecializzazione = :patrimonioSpecializzazione ");	

		Query q3 = em.createQuery(sb3.toString());
		q3.setParameter("patrimonioSpecializzazione", toRemove);

		List<Patrimonio> patrimonios =q3.getResultList();
		Iterator<Patrimonio> it3 = patrimonios.iterator();
		while (it3.hasNext()) {
			Patrimonio patriomonio = (Patrimonio) it3.next();
			/*Elimino le dipendenze*/
			em.remove(patriomonio);
		}
		//Rimuovo l'oggetto PatrimonioSpecializzazione
		em.remove(toRemove);
	}	

	@Override
	@Transactional
	public void addProvinciaTabelleDinamiche(Integer idProvincia,String denominazione, String sigla, Integer idRegione,
			boolean modifica) throws DuplicateEntryException {
		//Controllo se sul db esiste una Provincia con la stessa descrizione e sigla
		Number countResult = existProvincia(denominazione, sigla, idRegione,idProvincia,modifica);
		if(countResult.intValue()>0){
			//Se esiste già ritorno eccezione con  opportuno messaggio
			throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
		}

		Regione regione = em.find(Regione.class,idRegione);

		if (modifica) {

			Provincia toUpdate = em.find(Provincia.class, idProvincia);			
			toUpdate.setDenominazione(denominazione);
			toUpdate.setSigla(sigla);
			toUpdate.setRegione(regione);

			em.merge(toUpdate);
			
		} else {
			Provincia toSave = new Provincia();
			toSave.setDenominazione(denominazione);
			toSave.setSigla(sigla);
			toSave.setRegione(regione);
			em.persist(toSave);	
		}

	}

	/**
	 * Metodo per il controllo dell'esistenza di una regione avente stessa denominazione, sigla e stato di quella che si vorrebbe creare
	 * @param denominazione
	 * @param sigla
	 * @param idRegione
	 * @return
	 */
	@Transactional
	private Number existProvincia(String denominazione, String sigla,Integer idRegione,Integer idProvincia,boolean modifica) {
		Regione regione = em.find(Regione.class, idRegione);
		Stato stato =regione.getStato();;

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM Provincia r");
		sb.append(" WHERE ");

		if(modifica){
			sb.append(" r.idProvincia != :idProvincia ");
			sb.append(" AND r.regione.stato = :stato ");
		}else{
			if(idRegione!=null){
				sb.append(" r.regione.stato = :stato ");
			}
		}

		if(denominazione!=null && sigla!=null){
			sb.append(" AND ( r.denominazione = :denominazione OR r.sigla = :sigla ) ");	
		}else
			if(denominazione!=null && sigla==null){
				sb.append(" AND r.denominazione = :denominazione ");		
			}else if(sigla!=null && denominazione==null){
				sb.append(" AND r.sigla = :sigla ");		
			}

		Query q = em.createQuery(sb.toString());
		if(modifica){
			q.setParameter("idProvincia", idProvincia);
		}
		if(stato!=null){
			q.setParameter("stato", stato);
		}

		if(denominazione!=null){
			q.setParameter("denominazione", denominazione);
		}

		if(sigla!=null){
			q.setParameter("sigla", sigla);
		}


		Number countResult = (Number) q.getSingleResult();
		return countResult;
	}

	@Override
	@Transactional
	public void removeProvinciaTabelleDinamiche(int idr_removeRecord)	throws ConstraintKeyViolationException {

		Provincia toRemove = em.find(Provincia.class, idr_removeRecord);

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM Comune p");
		sb.append(" WHERE p.provincia = :provincia ");	

		Query q = em.createQuery(sb.toString());
		q.setParameter("provincia", toRemove);
		Number countResultComuniCollegati = (Number) q.getSingleResult();

		StringBuffer sb2 = new StringBuffer();
		sb2.append(" SELECT COUNT(*) ");
		sb2.append(" FROM DatiProvinceIstat d");
		sb2.append(" WHERE d.idProvincia = :idProvincia ");	

		Query q2 = em.createQuery(sb2.toString());
		q2.setParameter("idProvincia", idr_removeRecord);
		Number countResultDatiProvinceIstatCollegati = (Number) q2.getSingleResult();


		/*Lancio eccezione e specifico nel messaggio le dipendenze per cui è stata scatenata*/
		if(countResultComuniCollegati.intValue()>0 || countResultDatiProvinceIstatCollegati.intValue()>0){
			throw new ConstraintKeyViolationException("Impossibile rimuovere la voce poichè è collegata a: <br>" +
					(countResultComuniCollegati.intValue()>0?countResultComuniCollegati.intValue() + " comuni;<br>":"")+
					(countResultDatiProvinceIstatCollegati.intValue()>0?countResultDatiProvinceIstatCollegati.intValue()+ " dati province istat;":""));
		}

		//Altrimenti rimuovo l'oggetto Regione
		em.remove(toRemove);
	}

	@Override
	@Transactional
	public void addComuneTabelleDinamiche(Integer idComune,	String denominazione, String codiceIstat, String codiceFinanze,
			Integer idProvincia, boolean modifica) throws DuplicateEntryException {
		//Controllo se sul db esiste una comune con la stessa codiceIstat e codiceFinanze
		Number countResult = existComune(idComune, codiceIstat,  codiceFinanze, idProvincia,  modifica);
		if(countResult.intValue()>0){
			//Se esiste già ritorno eccezione con  opportuno messaggio
			throw new DuplicateEntryException(DUPLICATE_MULTIPLE_ENTRY_ERROR_MESSAGE);
		}
		Provincia provincia = em.find(Provincia.class, idProvincia);
		if(modifica){

			Comune toUpdate = em.find(Comune.class, idComune);
			toUpdate.setDenominazione(denominazione);
			toUpdate.setCodiceIstat(codiceIstat);
			toUpdate.setCodiceFinanze(codiceFinanze);
			toUpdate.setProvincia(provincia);
			em.merge(toUpdate);
		}else{
			Comune toSave =new Comune();
			toSave.setDenominazione(denominazione);
			toSave.setCodiceIstat(codiceIstat);
			toSave.setCodiceFinanze(codiceFinanze);
			toSave.setProvincia(provincia);
			em.persist(toSave);
		}

	}

	@Transactional
	public  Number existComune(Integer idComune,String codiceIstat, String codiceFinanze,
			Integer idProvincia, boolean modifica) {
		Provincia provincia=null;

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM Comune c");
		sb.append(" WHERE ");
		List<String> criteria = new ArrayList<String>();



		if(modifica && idComune!=null){
			sb.append(" c.idComune != :idComune AND (");
		}

		if(codiceIstat!=null && codiceIstat.length()>0){
			criteria.add(" c.codiceIstat = :codiceIstat ");
		}

		if(codiceFinanze!=null && codiceFinanze.length()>0){
			criteria.add(" c.codiceFinanze = :codiceFinanze ");
		}

		if(idProvincia!=null && codiceFinanze.length()>0){
			criteria.add(" c.provincia = :provincia ");
		}


		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" OR ");
			}
			sb.append(criteria.get(i));
		}
		sb.append(" )");
		Query q = em.createQuery(sb.toString());

		if(codiceIstat!=null && codiceIstat.length()>0){
			q.setParameter("codiceIstat", codiceIstat);
		}

		if(codiceFinanze!=null && codiceFinanze.length()>0){
			q.setParameter("codiceFinanze", codiceFinanze);
		}

		if(idProvincia!=null && codiceFinanze.length()>0){
			q.setParameter("provincia", provincia);		
		}

		if(modifica && idComune!=null){
			q.setParameter("idComune", idComune);	
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult;
	}

	@Override
	@Transactional
	public void removeComuneTabelleDinamiche(int idr_removeRecord)	throws ConstraintKeyViolationException {
		Comune toRemove = em.find(Comune.class, idr_removeRecord);
		Biblioteca b;
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM Biblioteca b");
		sb.append(" WHERE b.comune = :comune ");	

		Query q = em.createQuery(sb.toString());
		q.setParameter("comune", toRemove);
		Number countResultBibliotecheCollegate = (Number) q.getSingleResult();
		/*Lancio eccezione e specifico nel messaggio le dipendenze per cui è stata scatenata*/
		if(countResultBibliotecheCollegate.intValue()>0 ){
			throw new ConstraintKeyViolationException("Impossibile rimuovere la voce \""+toRemove.getDenominazione()+"\" poichè è collegata a: <br>" +
					(countResultBibliotecheCollegate.intValue()>0?countResultBibliotecheCollegate.intValue() + " biblitoeche;<br>":""));
		}
		//Altrimenti rimuovo l'oggetto comune
		em.remove(toRemove);
	}

	@Override
	@Transactional
	public FondiAntichiConsistenza getFondiAntichiById(int idFondi) {
		return em.find(FondiAntichiConsistenza.class, idFondi);
	}
	
	@Override
	@Transactional
	public void addSistemiPrestitoInterbibliotecarioTabelleDinamiche(Integer idSistemiPrestitoInterbibliotecario, String descrizione, String url, boolean modifica) throws DuplicateEntryException {
		// Controllo se sul db esiste un sistema di prestito interbibliotecario con la stessa descrizione e url
		Number countResult = existSistemiPrestitoInterbibliotecario(idSistemiPrestitoInterbibliotecario, descrizione, url, modifica);
		
		if (countResult.intValue() > 0) {
			// Se esiste già ritorno eccezione con un opportuno messaggio
			throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
		}

		if (modifica) {
			SistemiPrestitoInterbibliotecario toUpdate = em.find(SistemiPrestitoInterbibliotecario.class, idSistemiPrestitoInterbibliotecario);			
			toUpdate.setDescrizione(descrizione);
			toUpdate.setUrl(url);

			em.merge(toUpdate);
			
		} else {
			SistemiPrestitoInterbibliotecario toSave = new SistemiPrestitoInterbibliotecario();
			toSave.setDescrizione(descrizione);
			toSave.setUrl(url);
			
			em.persist(toSave);	
		}
	}

	/**
	 * Metodo per il controllo dell'esistenza di un sistema di prestito interbibliotecario avente stessa descrizione ed url di quello che si vorrebbe creare
	 * @param descrizione
	 * @param url
	 * @return
	 */
	@Transactional
	private Number existSistemiPrestitoInterbibliotecario(Integer idSistemiPrestitoInterbibliotecario, String descrizione, String url, boolean modifica) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) ");
		sb.append("FROM SistemiPrestitoInterbibliotecario s ");
		sb.append("WHERE ");

		if (modifica) {
			sb.append("s.idSistemiPrestitoInterbibliotecario != :idSistemiPrestitoInterbibliotecario AND ");

		} 
		
		if(descrizione != null && url != null) {
			sb.append("( s.descrizione = :descrizione OR s.url = :url ) ");
			
		} else {
			if(descrizione != null && url == null) {
				sb.append(" s.descrizione = :descrizione ");
				
			} else if (url != null && descrizione == null) {
				sb.append(" s.url = :url ");		
			}
		}
		
		Query q = em.createQuery(sb.toString());
		if (modifica) {
			q.setParameter("idSistemiPrestitoInterbibliotecario", idSistemiPrestitoInterbibliotecario);
		}

		if(descrizione != null) {
			q.setParameter("descrizione", descrizione);
		}

		if (url != null) {
			q.setParameter("url", url);
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult;
	}

	@Override
	@Transactional
	public void removeSistemiPrestitoInterbibliotecarioTabelleDinamiche(int idr_removeRecord) throws ConstraintKeyViolationException {
		SistemiPrestitoInterbibliotecario toRemove = em.find(SistemiPrestitoInterbibliotecario.class, idr_removeRecord);

		// Rimuovo l'oggetto SistemiPrestitoInterbibliotecario
		em.remove(toRemove);
	}
	
	@Override
	@Transactional
	public List<SistemiPrestitoInterbibliotecario> getSistemiPrestitoInterbibliotecario() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("FROM SistemiPrestitoInterbibliotecario s");
		Query q = em.createQuery(sb.toString());
		
		List<SistemiPrestitoInterbibliotecario> listaSistemi = q.getResultList();
		for (int i = 0; i < listaSistemi.size(); i++) {

			listaSistemi.get(i).getDescrizione();

		}
		return listaSistemi;
	}
	
	@Override
	@Transactional
	public int countAllSistemiPrestitoInterbibliotecarioPaginatiPerCombo(String searchValore) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(*) ");
		sb.append(" FROM SistemiPrestitoInterbibliotecario as sist ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append(" WHERE sist.descrizione LIKE :searchValore  ");
		}

		Query q = em.createQuery(sb.toString());

		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}
		Number countResult = (Number) q.getSingleResult();

		return countResult.intValue();
	}
	
	@Override
	@Transactional
	public List<SistemaPrestitoInterbibliotecarioDTO> getSistemiPrestitoInterbibliotecarioPaginatiPerCombo(String searchValore, int start, int limit) {
		List<SistemaPrestitoInterbibliotecarioDTO> dtos = new ArrayList<SistemaPrestitoInterbibliotecarioDTO>();
		
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT sist.idSistemiPrestitoInterbibliotecario, sist.descrizione, sist.url ");
		sb.append(" FROM SistemiPrestitoInterbibliotecario as sist ");

		if (searchValore != null && searchValore.length() > 0) {
			sb.append("WHERE sist.descrizione LIKE :searchValore ");
		}

		Query q = em.createQuery(sb.toString());

		if (searchValore != null && searchValore.length() > 0) {
			String tmpSearchValore = "%".concat(searchValore).concat("%");
			q.setParameter("searchValore", tmpSearchValore);
		}

		q.setMaxResults(limit);
		q.setFirstResult(start);

		List result = q.getResultList();

		Iterator it = result.iterator();
		while (it.hasNext()) {
			Object [] tmpRecord = (Object []) it.next();

			SistemaPrestitoInterbibliotecarioDTO dto = new SistemaPrestitoInterbibliotecarioDTO();
			dto.setId((Integer) tmpRecord[0]);
			dto.setDescrizione(""+tmpRecord[1]);
			dto.setUrl(""+tmpRecord[2]);

			dtos.add(dto);
		}
		return dtos;
	}
}
