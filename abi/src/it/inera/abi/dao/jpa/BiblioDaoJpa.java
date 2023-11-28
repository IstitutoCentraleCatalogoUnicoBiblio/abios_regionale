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

package it.inera.abi.dao.jpa;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.EnteDao;
import it.inera.abi.dao.EntryNotFoundException;
import it.inera.abi.dao.StatoDao;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Bibliografia;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.CataloghiCollettiviMaterialeUrl;
import it.inera.abi.persistence.CataloghiGeneraliUrl;
import it.inera.abi.persistence.CataloghiSpecialiMaterialeUrl;
import it.inera.abi.persistence.CatalogoGeneraleTipo;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.CodiciTipo;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Comunicazioni;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DepositiLegaliPK;
import it.inera.abi.persistence.DepositiLegaliTipo;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DestinazioniSocialiPK;
import it.inera.abi.persistence.DestinazioniSocialiTipo;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.DeweyLiberoPK;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiAntichiConsistenza;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario;
import it.inera.abi.persistence.Geolocalizzazione;
import it.inera.abi.persistence.IndicizzazioneClassificata;
import it.inera.abi.persistence.IndicizzazioneSoggetto;
import it.inera.abi.persistence.NormeCatalogazione;
import it.inera.abi.persistence.NuovaBiblioteca;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.PatrimonioPK;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.Photo;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoInterbibliotecarioModo;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.RiproduzioniPK;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.Stato;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.StatoCatalogazione;
import it.inera.abi.persistence.StatoCatalogazionePK;
import it.inera.abi.persistence.TipologiaFunzionale;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione classe DAO per l'entità Biblioteca
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
@Repository
public class BiblioDaoJpa implements BiblioDao {
	public static final String DUPLICATE_ENTRY_ERROR_MESSAGE = "ATTENZIONE: la voce potrebbe essere già presente nel database";

	@Autowired
	protected EnteDao enteDao;
	@Autowired
	protected StatoDao statoDao;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Biblioteca getBibliotecaById(int id) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id);
		return biblioteca;
	}

	@Override
	@Transactional
	public void saveBiblioteca(Biblioteca biblioteca) {
		em.persist(biblioteca);
	}

	@Override
	@Transactional
	public void saveChild(Object child) {
		em.persist(child);
	}

	@Override
	@Transactional
	public void updateChild(Object child) {
		em.merge(child);
	}

	@Override
	@Transactional
	public void removeChilds(List<?> childs) {
		for (Iterator<?> iter = childs.iterator(); iter.hasNext();) {
			em.remove(iter.next());
		}
		childs.clear();
	}

	@Override
	@Transactional
	public void removePrestitoLocale(Biblioteca biblioteca) {
		List<PrestitoLocale> prestitoLocales = biblioteca.getPrestitoLocales();
		for (Iterator<PrestitoLocale> iter = prestitoLocales.iterator(); iter
		.hasNext();) {
			PrestitoLocale prestitoLocale = iter.next();

			List<PrestitoLocaleMaterialeEscluso> prestitoLocaleMaterialeEsclusos = prestitoLocale
			.getPrestitoLocaleMaterialeEscluso();
			if (prestitoLocaleMaterialeEsclusos != null) {
				prestitoLocaleMaterialeEsclusos.clear();
			}
			List<PrestitoLocaleUtentiAmmessi> prestitoLocaleUtentiAmmessis = prestitoLocale
			.getPrestitoLocaleUtentiAmmessis();
			if (prestitoLocaleUtentiAmmessis != null) {
				prestitoLocaleUtentiAmmessis.clear();
			}
			em.remove(prestitoLocale);
		}
		prestitoLocales.clear();
	}

	@Override
	@Transactional
	public void updateBiblioteca(Biblioteca biblioteca) {
		em.merge(biblioteca);
	}

	// // SELECT MAX(isil_numero) AS MAX_VAL FROM biblioteca WHERE isil_stato =
	// 'IT' AND isil_provincia = 'MS'
	@Override
	@Transactional
	public int getMaxIsilNumero(String isilStato, String isilProvincia) {
		Query query = em
		.createQuery("select MAX(isilNumero) from Biblioteca where isilStato = :isilStato AND isilProvincia = :isilProvincia");
		query.setParameter("isilStato", isilStato);
		query.setParameter("isilProvincia", isilProvincia);
		int isilNumero = (Integer) query.getSingleResult();
		return isilNumero;
	}

	/* DAL FORMATO DI SCAMBIO */
	@Override
	@Transactional
	public Biblioteca[] getBibliotecheByIdBib(String[] idBib, int firstResult,
			int maxResult) {
		String prepareQuery = "from Biblioteca as b where ";
		for (int i = 0; i < idBib.length; i++) {
			prepareQuery += "b.idBiblioteca = :idBiblioteca" + i + " OR ";
		}
		prepareQuery = prepareQuery.substring(0, prepareQuery.length() - 4);

		Query query = em.createQuery(prepareQuery);

		for (int i = 0; i < idBib.length; i++) {
			query.setParameter("idBiblioteca" + i, Integer.valueOf(idBib[i]));
		}

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List biblios = query.getResultList();
		// tx.commit();
		Biblioteca[] results = new Biblioteca[biblios.size()];
		results = (Biblioteca[]) biblios.toArray(results);
		return results;
	}

	@Override
	@Transactional
	public Biblioteca[] getBibliotecheByCodABI(String[] codABI,
			int firstResult, int maxResult) {

		String prepareQuery = "from Biblioteca as b where ";
		for (int i = 0; i < codABI.length; i++) {
			prepareQuery += "(b.isilProvincia = :isilPr" + i
			+ " AND b.isilNumero = :isilNr" + i + " ) OR ";
		}
		prepareQuery = prepareQuery.substring(0, prepareQuery.length() - 4);

		Query query = em.createQuery(prepareQuery);

		for (int i = 0; i < codABI.length; i++) {
			query.setParameter("isilPr" + i, Utility.getIsilPr(codABI[i]));
			query.setParameter("isilNr" + i, Utility.getIsilNr(codABI[i]));
		}

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List biblios = query.getResultList();

		Biblioteca[] results = new Biblioteca[biblios.size()];
		results = (Biblioteca[]) biblios.toArray(results);
		return results;

	}

	@Override
	@Transactional
	public Biblioteca[] getBibliotecheByCodABI6CharsCode(String[] codABI, int firstResult, int maxResult) {

		String prepareQuery = "from Biblioteca as b where ";
		for (int i = 0; i < codABI.length; i++) {
			prepareQuery += "(b.isilProvincia = :isilPr" + i
			+ " AND b.isilNumero = :isilNr" + i + " ) OR ";
		}
		prepareQuery = prepareQuery.substring(0, prepareQuery.length() - 4);

		Query query = em.createQuery(prepareQuery);

		for (int i = 0; i < codABI.length; i++) {
			query.setParameter("isilPr" + i, Utility.getIsilPr6CharsCode(codABI[i]));
			query.setParameter("isilNr" + i, Utility.getIsilNr6CharsCode(codABI[i]));
		}

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List biblios = query.getResultList();
		// tx.commit();
		Biblioteca[] results = new Biblioteca[biblios.size()];
		results = (Biblioteca[]) biblios.toArray(results);
		return results;

	}

	/* Metodi logica applicazione Biblioteca */
	@Override
	@Transactional
	public int countAll() {
		Query query = em.createQuery("SELECT COUNT(b) FROM Biblioteca b");
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public int countAllBibliotecheFiltered(HashMap<String, Object> keys) {
		int id_regione = 0;
		int id_provincia = 0;
		int id_comune = 0;

		int id_tipologiaAmministrativa = 0;
		int id_tipologiaFunzionale = 0;
		int id_statoWorkflow = 0;
		int id_loginUtenteGestore = 0;

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer(" SELECT COUNT(b) FROM Biblioteca b ");
		if (keys != null && ((keys.containsKey("utenteGestore") && (keys.get("utenteGestore") != null)) || (keys.containsKey("loginUtenteGestore") && (keys.get("loginUtenteGestore") != null)))) {
			sb.append("join b.utentisGestori utenteGestore ");
		}

		if (keys != null && (keys.containsKey("cataloghiCollettivi") && keys.get("cataloghiCollettivi") != null)) {
			sb.append("join b.partecipaCataloghiCollettiviMateriales pcc " + 
			"join pcc.cataloghiCollettivi cc ");
		}
		if (keys != null && (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null)) {
			if (!((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
				sb.append("join b.codicis c ");
			}
		}

		if (keys != null && keys.size() > 0) {
			if (keys.containsKey("comune") && (keys.get("comune") != null)) {

				criteria.add("b.comune = :comune");
			} else if (keys.containsKey("provincia")
					&& (keys.get("provincia") != null)) {
				criteria.add("b.comune.provincia = :provincia");
			} else if (keys.containsKey("regione")
					&& (keys.get("regione") != null)) {
				criteria.add("b.comune.provincia.regione = :regione");
			}

			if (keys.containsKey("tipologiaFunzionale")
					&& (keys.get("tipologiaFunzionale") != null)) {

				criteria.add("b.tipologiaFunzionale = :tipologiaFunzionale");
			}

			if (keys.containsKey("statoWorkflow")
					&& (keys.get("statoWorkflow") != null)) {

				criteria.add("b.statoBibliotecaWorkflow = :statoBibliotecaWorkflow");
			}

			if (keys.containsKey("tipologiaAmministrativa")
					&& (keys.get("tipologiaAmministrativa") != null)) {

				String tipAmm = ((Integer) keys.get("tipologiaAmministrativa")).toString();

				if (tipAmm.length() > 1 && tipAmm.substring((tipAmm.length()-2), tipAmm.length()).equals("00")) {
					criteria.add("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa AND " +
					"b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa + 100)");
				} else {
					criteria.add("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa");
				}
			}

			if (keys.containsKey("importate") && keys.get("importate") != null) {
				if (((String) keys.get("importate")).equals("true")) {
					criteria.add("b.catalogazioneDataImport IS NOT null");
				} else {
					criteria.add("b.catalogazioneDataImport IS null");
				}
			}

			if (keys.containsKey("utenteGestore")
					&& keys.get("utenteGestore") != null) {
				criteria.add("utenteGestore = :utenteGestore");
			}

			if (keys.containsKey("loginUtenteGestore") && keys.get("loginUtenteGestore") != null) {
				criteria.add("utenteGestore = :loginUtenteGestore");
			}

			if (keys.containsKey("cataloghiCollettivi") && keys.get("cataloghiCollettivi") != null) {
				criteria.add("cc.idCataloghiCollettivi = :idCatColl");
			}

			if (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null
					&& keys.containsKey("codice") && keys.get("codice") != null) {
				if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
					criteria.add("b.isilProvincia = :ipr");
					criteria.add("b.isilNumero = :inr");
				} else {
					criteria.add("c.valore like :codice");
					criteria.add("c.codiciTipo = :codiciTipo");
				}
			}

			if (criteria != null && criteria.size() > 0) {
				// if (keys != null && keys.size() > 0) {
				sb.append("WHERE ");
			}

			for (int i = 0; i < criteria.size(); i++) {

				if (i > 0) {
					sb.append(" AND ");
				}
				sb.append(criteria.get(i));
			}

		}
		Query q = em.createQuery(sb.toString());

		if (keys != null && keys.size() > 0) {
			if (keys.get("comune") != null) {
				id_comune = (Integer) keys.get("comune");

				Comune comune = new Comune();
				comune.setIdComune(id_comune);
				q.setParameter("comune", comune);
			} else

				if (keys.get("provincia") != null) {
					id_provincia = (Integer) keys.get("provincia");
					Provincia provincia = new Provincia();
					provincia.setIdProvincia(id_provincia);
					q.setParameter("provincia", provincia);
				} else

					if (keys.get("regione") != null) {
						id_regione = (Integer) keys.get("regione");
						Regione regione = new Regione();
						regione.setIdRegione(id_regione);
						q.setParameter("regione", regione);
					}
			if (keys.get("statoWorkflow") != null) {

				id_statoWorkflow = (Integer) keys.get("statoWorkflow");
				StatoBibliotecaWorkflow statoWorkflow = new StatoBibliotecaWorkflow();
				statoWorkflow.setIdStato(id_statoWorkflow);
				q.setParameter("statoBibliotecaWorkflow", statoWorkflow);
			}

			if (keys.get("tipologiaFunzionale") != null) {
				id_tipologiaFunzionale = (Integer) keys.get("tipologiaFunzionale");
				TipologiaFunzionale tipologiaFunzionale = new TipologiaFunzionale();
				tipologiaFunzionale.setIdTipologiaFunzionale(id_tipologiaFunzionale);
				q.setParameter("tipologiaFunzionale", tipologiaFunzionale);
			}

			if (keys.get("tipologiaAmministrativa") != null) {
				id_tipologiaAmministrativa = (Integer) keys.get("tipologiaAmministrativa");
				EnteTipologiaAmministrativa enteTipologiaAmministrativa = new EnteTipologiaAmministrativa();
				enteTipologiaAmministrativa.setIdEnteTipologiaAmministrativa(id_tipologiaAmministrativa);
				q.setParameter("enteTipologiaAmministrativa", enteTipologiaAmministrativa);
			}

			if (keys.get("utenteGestore") != null) {
				Utenti utenteGestore = (Utenti) keys.get("utenteGestore");
				q.setParameter("utenteGestore", utenteGestore);
			}

			if (keys.get("loginUtenteGestore") != null) {
				id_loginUtenteGestore = (Integer) keys.get("loginUtenteGestore");
				Utenti loginUtenteGestore = new Utenti();
				loginUtenteGestore.setIdUtenti(id_loginUtenteGestore);
				q.setParameter("loginUtenteGestore", loginUtenteGestore);

			}

			if (keys.get("cataloghiCollettivi") != null) {
				q.setParameter("idCatColl", (Integer) keys.get("cataloghiCollettivi"));
			}

			if (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null
					&& keys.containsKey("codice") && keys.get("codice") != null) {
				if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
					String s = (String) keys.get("codice");
					q.setParameter("ipr", s.substring(0, 2));
					q.setParameter("inr", Integer.valueOf((String) s.substring(2, 6)));
				} else {
					String tmpFilter = "%".concat((String) keys.get("codice")).concat("%");
					q.setParameter("codice", tmpFilter);
					
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ACNP")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 1);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("CEI")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 2);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("CMBS")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 3);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("RISM")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 4);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("SBN")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 5);
						q.setParameter("codiciTipo", ct);
					}

				}
			}

		}

		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public List<Biblioteca> ricercaBiblio(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir) {

		int id_regione = 0;
		int id_provincia = 0;
		int id_comune = 0;

		int id_tipologiaAmministrativa = 0;
		int id_tipologiaFunzionale = 0;
		int id_statoWorkflow = 0;
		int id_loginUtenteGestore = 0;

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  b ");
		sb.append("FROM Biblioteca b LEFT JOIN b.utenteUltimaModifica u ");
		if (keys != null && ((keys.containsKey("utenteGestore") && keys.get("utenteGestore") != null) || (keys.containsKey("loginUtenteGestore") && keys.get("loginUtenteGestore") != null))) {
			sb.append("join b.utentisGestori utenteGestore ");
		}
		if (keys != null && (keys.containsKey("cataloghiCollettivi") && keys.get("cataloghiCollettivi") != null)) {
			sb.append("join b.partecipaCataloghiCollettiviMateriales pcc " +
			"join pcc.cataloghiCollettivi cc ");
		}
		if (keys != null && (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null)) {
			if (!((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
				sb.append("join b.codicis c ");
			}
		}

		if (keys != null && keys.size() > 0) {
			if (keys.containsKey("comune") && (keys.get("comune") != null)) {

				criteria.add("b.comune = :comune");
			} else if (keys.containsKey("provincia")
					&& (keys.get("provincia") != null)) {
				criteria.add("b.comune.provincia = :provincia");
			} else if (keys.containsKey("regione")
					&& (keys.get("regione") != null)) {
				criteria.add("b.comune.provincia.regione = :regione");
			}

			if (keys.containsKey("tipologiaFunzionale")
					&& (keys.get("tipologiaFunzionale") != null)) {

				criteria.add("b.tipologiaFunzionale = :tipologiaFunzionale");
			}

			if (keys.containsKey("statoWorkflow")
					&& (keys.get("statoWorkflow") != null)) {
				criteria.add("b.statoBibliotecaWorkflow = :statoBibliotecaWorkflow");
			}

			if (keys.containsKey("tipologiaAmministrativa")
					&& (keys.get("tipologiaAmministrativa") != null)) {
				String tipAmm = ((Integer) keys.get("tipologiaAmministrativa")).toString();

				if (tipAmm.length() > 1 && tipAmm.substring((tipAmm.length()-2), tipAmm.length()).equals("00")) {
					criteria.add("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa AND " + 
					"b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa + 100)");
				} else {
					criteria.add("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa");
				}
			}

			if (keys.containsKey("importate") && keys.get("importate") != null) {
				if (((String) keys.get("importate")).equals("true")) {
					criteria.add("b.catalogazioneDataImport IS NOT null");
				} else {
					criteria.add("b.catalogazioneDataImport IS null");
				}
			}

			if (keys.containsKey("utenteGestore")
					&& (keys.get("utenteGestore") != null)) {
				criteria.add("utenteGestore = :utenteGestore");
			}

			if (keys.containsKey("loginUtenteGestore") &&
					(keys.get("loginUtenteGestore") != null)) {
				criteria.add("utenteGestore = :loginUtenteGestore");
			}

			if (keys.containsKey("cataloghiCollettivi") && keys.get("cataloghiCollettivi") != null) {
				criteria.add("cc.idCataloghiCollettivi = :idCatColl");
			}

			if (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null
					&& keys.containsKey("codice") && keys.get("codice") != null) {
				if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
					criteria.add("b.isilProvincia = :ipr");
					criteria.add("b.isilNumero = :inr");
				} else {
					criteria.add("c.valore like :codice");
					criteria.add("c.codiciTipo = :codiciTipo");
				}
			}

			if (criteria != null && criteria.size() > 0) {
				// if (keys != null && keys.size() > 0) {
				sb.append("WHERE ");
			}

			for (int i = 0; i < criteria.size(); i++) {
				if (i > 0) {
					sb.append(" AND ");
				}
				sb.append(criteria.get(i));
			}

		}

		if (StringUtils.isNotBlank(orderByField) && StringUtils.isNotBlank(orderByDir)) {
			sb.append(" ORDER BY ");
			if (orderByField.equals("codice")) {/* CODICE */
				sb.append("b.isilStato ");
				sb.append(orderByDir);
				sb.append(", b.isilProvincia ");
				sb.append(orderByDir);
				sb.append(", b.isilNumero ");
				sb.append(orderByDir);
			} else if (orderByField.equals("denominazione")) {/* DENOMINAZIONE */
				sb.append("b.").append(orderByField).append("Ufficiale ");
				sb.append(orderByDir);
			} else if (orderByField.equals("comuneDenominazione")) {/* COMUNE */
				sb.append("b.").append("comune.denominazione ");
				sb.append(orderByDir);
			} else if (orderByField.equals("utenteUltimaModifica")) {/* ULTIMA MODIFICA */
				sb.append("u").append(".login ");
				sb.append(orderByDir);
			} else if (orderByField.equals("statoCatalogazione")) {/* STATO ATTUALE */
				sb.append("b.").append("statoBibliotecaWorkflow.label ");
				sb.append(orderByDir);
			} else {/* INDIRIZZO */
				sb.append("b.").append(orderByField);
				sb.append(" ").append(orderByDir);
			}
		}

		Query q = em.createQuery(sb.toString());

		if (keys != null && keys.size() > 0) {
			if (keys.get("comune") != null) {
				id_comune = (Integer) keys.get("comune");

				Comune comune = new Comune();
				comune.setIdComune(id_comune);
				q.setParameter("comune", comune);
			} else

				if (keys.get("provincia") != null) {
					id_provincia = (Integer) keys.get("provincia");
					Provincia provincia = new Provincia();
					provincia.setIdProvincia(id_provincia);
					q.setParameter("provincia", provincia);
				} else

					if (keys.get("regione") != null) {
						id_regione = (Integer) keys.get("regione");
						Regione regione = new Regione();
						regione.setIdRegione(id_regione);
						q.setParameter("regione", regione);
					}
			if (keys.get("statoWorkflow") != null) {

				id_statoWorkflow = (Integer) keys.get("statoWorkflow");
				StatoBibliotecaWorkflow statoWorkflow = new StatoBibliotecaWorkflow();
				statoWorkflow.setIdStato(id_statoWorkflow);
				q.setParameter("statoBibliotecaWorkflow", statoWorkflow);
			}

			if (keys.get("tipologiaFunzionale") != null) {

				id_tipologiaFunzionale = (Integer) keys
				.get("tipologiaFunzionale");
				TipologiaFunzionale tipologiaFunzionale = new TipologiaFunzionale();
				tipologiaFunzionale
				.setIdTipologiaFunzionale(id_tipologiaFunzionale);
				q.setParameter("tipologiaFunzionale", tipologiaFunzionale);
			}

			if (keys.get("tipologiaAmministrativa") != null) {

				id_tipologiaAmministrativa = (Integer) keys
				.get("tipologiaAmministrativa");
				EnteTipologiaAmministrativa enteTipologiaAmministrativa = new EnteTipologiaAmministrativa();
				enteTipologiaAmministrativa
				.setIdEnteTipologiaAmministrativa(id_tipologiaAmministrativa);
				q.setParameter("enteTipologiaAmministrativa",
						enteTipologiaAmministrativa);
			}

			if (keys.get("utenteGestore") != null) {
				Utenti utenteGestore = (Utenti) keys.get("utenteGestore");
				q.setParameter("utenteGestore", utenteGestore);
			}

			if (keys.get("loginUtenteGestore") != null) {
				id_loginUtenteGestore = (Integer) keys.get("loginUtenteGestore");
				Utenti loginUtenteGestore = new Utenti();
				loginUtenteGestore.setIdUtenti(id_loginUtenteGestore);
				q.setParameter("loginUtenteGestore", loginUtenteGestore);
			}

			if (keys.get("cataloghiCollettivi") != null) {
				q.setParameter("idCatColl", (Integer) keys.get("cataloghiCollettivi"));
			}

			if (keys.containsKey("codiciTipo") && keys.get("codiciTipo") != null
					&& keys.containsKey("codice") && keys.get("codice") != null) {
				if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ABI")) {
					String s = (String) keys.get("codice");
					q.setParameter("ipr", s.substring(0, 2));
					q.setParameter("inr", Integer.valueOf((String) s.substring(2, s.length())));
				} else {
					String tmpFilter = "%".concat((String) keys.get("codice")).concat("%");
					q.setParameter("codice", tmpFilter);
					
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("ACNP")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 1);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("CEI")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 2);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("CMBS")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 3);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("RISM")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 4);
						q.setParameter("codiciTipo", ct);
					}
					if (((String) keys.get("codiciTipo")).equalsIgnoreCase("SBN")) {
						CodiciTipo ct = em.find(CodiciTipo.class, 5);
						q.setParameter("codiciTipo", ct);
					}

				}
			}
		}
		if (offset >= 0) {
			q.setFirstResult(offset);
		}
		if (rows != -1)
			q.setMaxResults(rows);

		List<Biblioteca> listaBiblioteche = q.getResultList();

		return listaBiblioteche;

	}

	/*
	 * Modificare:anzichè fare la query su tutte le biblioteche che contengono
	 * quel determinato id padre richiamare il metodo
	 * bibliotecaPadre.getBibliotecasFigli()
	 */
	@Override
	@Transactional
	public List<Biblioteca> getPuntiDiServizioDecentrati(int id_biblioteca) {
		Biblioteca bibliotecaPadre = new Biblioteca();
		bibliotecaPadre.setIdBiblioteca(id_biblioteca);

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  b ");
		sb.append(" FROM Biblioteca b ");
		sb.append(" WHERE b.bibliotecaPadre = :bibliotecaPadre ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("bibliotecaPadre", bibliotecaPadre);
		return q.getResultList();
	}

	/**
	 * Marca una biblioteca come punto di servizio decentrato di un'altra
	 * biblioteca inserendo nel campo id_biblitoeca_padre, l'id della biblioteca
	 * padre relativa
	 * 
	 * @param id_biblioteca_padre
	 * @param id_biblioteca_figlia
	 */

	@Override
	@Transactional
	public void addPuntoDiServizioDecentrato(int id_biblioteca_padre, int id_biblioteca_figlia) {
		Biblioteca bibliotecaPadre = new Biblioteca();
		bibliotecaPadre.setIdBiblioteca(id_biblioteca_padre);

		Biblioteca bibliotecaFiglia = em.find(Biblioteca.class, id_biblioteca_figlia);
		bibliotecaFiglia.setBibliotecaPadre(bibliotecaPadre);

		em.merge(bibliotecaFiglia);
	}

	/**
	 * Restituisce le biblioteche che possonno essere dei punti di servizio
	 * decentrati (ossia che hanno il campo id_biblioteca_padre=null) filtrati
	 * per il codice isil_provincia e per il testo inserito dall'utente nel
	 * campo di ricerca (auto-completion)! Carica i dati secondo gli indici di
	 * paginazione (rows ed offset)
	 * 
	 * @param isil_provincia
	 * @param filter
	 * @param rows
	 * @param offset
	 * @return List<Biblioteca>
	 * */
	@Override
	@Transactional
	public List<Biblioteca> getPuntiDiServizioDecentratiPossibili(
			String isil_provincia, String filter, int rows, int offset) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT  b ");
		sb.append(" FROM Biblioteca b ");
		sb.append(" WHERE b.bibliotecaPadre IS null");
		sb.append(" AND b.isilProvincia = :isilProvincia ");

		if (filter != null && filter.length() > 0) {
			sb.append(" AND b.denominazioneUfficiale like :denominazioneUfficiale ");

		}

		Query q = em.createQuery(sb.toString());
		q.setParameter("isilProvincia", isil_provincia);
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazioneUfficiale", tmpFilter);

		}

		q.setMaxResults(rows);
		q.setFirstResult(offset);

		return q.getResultList();

	}

	/**
	 * Setta a null il campo id_biblitoeca_padre di una certa biblioteca
	 * 
	 * @param id_bibloteca
	 * */
	@Override
	@Transactional
	public void removePuntoDiServizioDecentrato(int id_bibloteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_bibloteca);
		biblioteca.setBibliotecaPadre(null);

		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public int countAllPuntiDecentratiByIsilProvinciaAndFiltered(
			String isil_provincia, String filter) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(b) FROM Biblioteca b ");
		sb.append(" WHERE b.isilProvincia = :isilProvincia ");
		sb.append(" AND b.bibliotecaPadre IS null ");
		if (filter != null && filter.length() > 0) {
			sb.append(" AND b.denominazioneUfficiale like :denominazioneUfficiale ");
		}

		Query q = em.createQuery(sb.toString());
		q.setParameter("isilProvincia", isil_provincia);

		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazioneUfficiale", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public List<SistemiBiblioteche> getSistemiBibliotecheByIdBiblioteca(int id_biblioteca) {
		Biblioteca b = em.find(Biblioteca.class, id_biblioteca);

		List<SistemiBiblioteche> listaSistemiBiblioteche = b.getSistemiBiblioteches();
		if (listaSistemiBiblioteche == null)
			listaSistemiBiblioteche = new ArrayList<SistemiBiblioteche>();
		// listaSistemiBiblioteche.size();

		Iterator<SistemiBiblioteche> its = listaSistemiBiblioteche.iterator();
		while (its.hasNext()) {

			// Iterazione anti-lazy
			SistemiBiblioteche sistemaBiblioteche = (SistemiBiblioteche) its.next();

		}

		return listaSistemiBiblioteche;

	}

	@Override
	@Transactional
	public boolean addSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<SistemiBiblioteche> sistemi = biblioteca.getSistemiBiblioteches();

		SistemiBiblioteche nuovoSistema = em.find(SistemiBiblioteche.class, id_sistema_biblioteche);
		if (!sistemi.contains(nuovoSistema)) {
			sistemi.add(nuovoSistema);
			biblioteca.setSistemiBiblioteches(sistemi);
			em.merge(biblioteca);

			return true;
			
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public void removeSistemaBiblioteca(int id_biblioteca,
			int id_sistema_biblioteche) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<SistemiBiblioteche> sistemiBiblioteches = biblioteca
		.getSistemiBiblioteches();
		SistemiBiblioteche nuovoSistema = em.find(SistemiBiblioteche.class,
				id_sistema_biblioteche);

		if ((sistemiBiblioteches.contains(nuovoSistema))) {

			sistemiBiblioteches.remove(nuovoSistema);

			biblioteca.setSistemiBiblioteches(sistemiBiblioteches);

			em.merge(biblioteca);
		}
	}

	@Override
	@Transactional
	public void setEnte(int id_biblioteca, Ente ente) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setEnte(ente);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<AccessoModalita> getAccessoModalitaByIdBiblioteca(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<AccessoModalita> accessoModalitas = biblioteca.getAccessoModalitas();
		Iterator<AccessoModalita> it = accessoModalitas.iterator();
		while (it.hasNext()) {
			AccessoModalita accessoModalita = (AccessoModalita) it.next();

		}
		return accessoModalitas;

	}

	@Override
	@Transactional
	public void addModalitaAccesso(int id_biblioteca, int id_modalita_accesso) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setIdBiblioteca(id_biblioteca);

		List<AccessoModalita> accessoModalitas = biblioteca
		.getAccessoModalitas();

		AccessoModalita nuovaModalita = em.find(AccessoModalita.class,
				id_modalita_accesso);
		if ((accessoModalitas.contains(nuovaModalita)) == false) {

			accessoModalitas.add(nuovaModalita);

			biblioteca.setAccessoModalitas(accessoModalitas);

			em.merge(biblioteca);
		}
	}

	@Override
	@Transactional
	public void removeModalitaAccesso(int id_biblioteca, int id_modalita_accesso) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setIdBiblioteca(id_biblioteca);

		List<AccessoModalita> accessoModalitas = biblioteca
		.getAccessoModalitas();

		AccessoModalita nuovaModalita = em.find(AccessoModalita.class,
				id_modalita_accesso);
		if (accessoModalitas.contains(nuovaModalita)) {

			accessoModalitas.remove(nuovaModalita);

			biblioteca.setAccessoModalitas(accessoModalitas);

			em.merge(biblioteca);
		}
	}

	@Override
	@Transactional
	public List<DestinazioniSociali> getDestinazioniSocialiByIdBiblioteca(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<DestinazioniSociali> destinazioniSocialis = biblioteca
		.getDestinazioniSocialis();
		Iterator<DestinazioniSociali> it = destinazioniSocialis.iterator();
		while (it.hasNext()) {
			DestinazioniSociali destinazioniSociali = (DestinazioniSociali) it
			.next();
		}
		return destinazioniSocialis;

	}

	@Override
	@Transactional
	public boolean addDestinazioniSociali(int id_biblioteca, boolean modifica,
			int id_nuovaDestinazione, String note) {
		if (!modifica && searchDestinazioneSociale(id_biblioteca, id_nuovaDestinazione) == true) {
			return false;

		} else {
			DestinazioniSocialiPK destinazioniSocialiPK = new DestinazioniSocialiPK();
			destinazioniSocialiPK.setIdBiblioteca(id_biblioteca);
			destinazioniSocialiPK.setIdDestinazioniSocialiTipo(id_nuovaDestinazione);
			
			DestinazioniSociali destinazioniSociali = new DestinazioniSociali();
			destinazioniSociali.setId(destinazioniSocialiPK);
			destinazioniSociali.setNote(note);
			
			em.merge(destinazioniSociali);

			return true;
		}
		
	}
	
	private boolean searchDestinazioneSociale(int idBiblioteca, int idDestSoc) {
		Biblioteca biblioteca = em.find(Biblioteca.class, idBiblioteca);
		List<DestinazioniSociali> destSocs = biblioteca.getDestinazioniSocialis();

		for (DestinazioniSociali entryDestSoc : destSocs) {
			DestinazioniSocialiTipo destSoc = entryDestSoc.getDestinazioniSocialiTipo();
			if (destSoc != null && destSoc.getIdDestinazioniSociali().intValue() == idDestSoc) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void removeDestinazioneSociale(int id_biblioteca,
			int id_rimuoviDestinazione) {
		DestinazioniSocialiPK destinazioniSocialiPK = new DestinazioniSocialiPK();
		destinazioniSocialiPK.setIdBiblioteca(id_biblioteca);
		destinazioniSocialiPK
		.setIdDestinazioniSocialiTipo(id_rimuoviDestinazione);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM DestinazioniSociali d ");
		sb.append(" WHERE d.id = :destinazioniSocialiPK ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("destinazioniSocialiPK", destinazioniSocialiPK);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public void addRegolamento(Regolamento regolamento) {

		em.merge(regolamento);
	}

	@Override
	@Transactional
	public List<OrarioUfficiali> getOrariUfficialiByDay(int id_biblioteca,
			int id_day) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<OrarioUfficiali> orarioUfficialis = biblioteca
		.getOrarioUfficialis();
		List<OrarioUfficiali> orariUfficialisByDay = new ArrayList<OrarioUfficiali>();

		Iterator<OrarioUfficiali> ito = orarioUfficialis.iterator();
		while (ito.hasNext()) {
			// Iterazione anti-lazy
			OrarioUfficiali orarioUfficiali = (OrarioUfficiali) ito.next();
			if (orarioUfficiali.getGiorno() == id_day) {
				/*
				 * Rimuovo dalla lista tutti glio rari non appartenenti al
				 * giorno
				 */
				orariUfficialisByDay.add(orarioUfficiali);
			}
		}
		return orariUfficialisByDay;
	}

	@Override
	@Transactional
	public void addNuovoOrarioGiornaliero(int id_biblioteca,
			OrarioUfficiali orarioUfficiali) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		orarioUfficiali.setBiblioteca(biblioteca);

		em.merge(orarioUfficiali);
	}

	@Override
	@Transactional
	public void addNuovoOrarioGiornalieroCustom(int id_biblio,
			Vector<Integer> id_days, Date dalle, Date alle) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblio);
		for (int i = 0; i < id_days.size(); i++) {
			OrarioUfficiali orarioUfficiali = new OrarioUfficiali();
			orarioUfficiali.setDalle(dalle);
			orarioUfficiali.setAlle(alle);
			orarioUfficiali.setBiblioteca(biblioteca);
			orarioUfficiali.setGiorno(id_days.elementAt(i).intValue());
			em.merge(orarioUfficiali);
		}

	}

	@Override
	@Transactional
	public void removeOrarioUfficiale(int id_orarioToRemove) {
		OrarioUfficiali orarioUfficiale = em.find(OrarioUfficiali.class,
				id_orarioToRemove);
		em.remove(orarioUfficiale);
	}

	@Override
	@Transactional
	public List<OrarioVariazioni> getVariazioniOrari(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<OrarioVariazioni> orarioVariazionis = biblioteca
		.getOrarioVariazionis();
		Iterator<OrarioVariazioni> itv = orarioVariazionis.iterator();
		while (itv.hasNext()) {
			// Iterazione anti-lazy
			OrarioVariazioni orarioVariazioni = (OrarioVariazioni) itv.next();
		}
		return orarioVariazionis;
	}

	@Override
	@Transactional
	public void addNuovaVariazioneOrario(int id_biblioteca,
			OrarioVariazioni orarioVariazioni) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		orarioVariazioni.setBiblioteca(biblioteca);
		em.merge(orarioVariazioni);

	}

	@Override
	@Transactional
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, Date dalle, Date alle, String periodo) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		if (dalle != null && alle != null) {
			for (int i = 0; i < id_days.size(); i++) {
				OrarioVariazioni nuovaVariazione = new OrarioVariazioni();
				nuovaVariazione.setDalle(dalle);
				nuovaVariazione.setAlle(alle);
				nuovaVariazione.setDescrizione(periodo);
				nuovaVariazione.setGiorno(id_days.elementAt(i).intValue());
				nuovaVariazione.setBiblioteca(biblioteca);
				em.persist(nuovaVariazione);
	
			}
			
		} else {
			OrarioVariazioni nuovaVariazione = new OrarioVariazioni();
			nuovaVariazione.setDescrizione(periodo);
			nuovaVariazione.setBiblioteca(biblioteca);
			em.persist(nuovaVariazione);
		}
	}

	@Override
	@Transactional
	public void removeVariazioneOrario(int id_variazioneOrarioToRemove) {
		OrarioVariazioni orarioVariazioni = em.find(OrarioVariazioni.class,
				id_variazioneOrarioToRemove);
		em.remove(orarioVariazioni);
	}

	@Override
	@Transactional
	public List<OrarioChiusure> getPeriodiChiusuraByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<OrarioChiusure> orarioChiusures = biblioteca.getOrarioChiusures();
		Iterator<OrarioChiusure> it = orarioChiusures.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			OrarioChiusure orarioChiusure = (OrarioChiusure) it.next();
		}
		return orarioChiusures;
	}

	@Override
	@Transactional
	public void addNuovoPeriodoChiusura(int id_biblioteca,
			OrarioChiusure nuovoOrarioChiusure) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		nuovoOrarioChiusure.setBiblioteca(biblioteca);
		em.merge(nuovoOrarioChiusure);

	}

	@Override
	@Transactional
	public void removePeriodoChiusura(int id_chiusuraToRemove) {
		OrarioChiusure orarioChiusure = em.find(OrarioChiusure.class,
				id_chiusuraToRemove);
		em.remove(orarioChiusure);
	}

	@Override
	@Transactional
	public List<Patrimonio> getListaPatrimonioSpecializzazione(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<Patrimonio> patrimonios = biblioteca.getPatrimonios();

		Iterator<Patrimonio> itp = patrimonios.iterator();
		while (itp.hasNext()) {
			// Iterazione anti lazy
			Patrimonio patrimonio = (Patrimonio) itp.next();
		}
		return patrimonios;
	}

	@Override
	@Transactional
	public boolean addPatrimonioSpeciale(int id_biblioteca, boolean modifica, int id_nuovoPatr,
			int quantita, int quantitaUltimoAnno) {
		if (!modifica && searchPatrimonioSpeciale(id_biblioteca, id_nuovoPatr) == true) {
			return false;

		} else {
			PatrimonioPK patrimonioPK = new PatrimonioPK();
			patrimonioPK.setIdBiblioteca(id_biblioteca);
			patrimonioPK.setIdPatrimonioSpecializzazione(id_nuovoPatr);

			Patrimonio patrimonio = new Patrimonio();
			patrimonio.setId(patrimonioPK);
			patrimonio.setQuantita(quantita);
			patrimonio.setQuantitaUltimoAnno(quantitaUltimoAnno);

			em.merge(patrimonio);
			
			return true;
		}
	}

	private boolean searchPatrimonioSpeciale(int idBiblioteca, int idPatrimonio) {
		Biblioteca biblioteca = em.find(Biblioteca.class, idBiblioteca);
		List<Patrimonio> patrimonios = biblioteca.getPatrimonios();

		for (Patrimonio entryPatr : patrimonios) {
			PatrimonioSpecializzazione patrSpec = entryPatr.getPatrimonioSpecializzazione();
			if (patrSpec != null && patrSpec.getIdPatrimonioSpecializzazione().intValue() == idPatrimonio) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	@Transactional
	public void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr) {

		PatrimonioPK patrimonioPK = new PatrimonioPK();
		patrimonioPK.setIdBiblioteca(id_biblioteca);
		patrimonioPK.setIdPatrimonioSpecializzazione(id_rimuoviPatr);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM Patrimonio p ");
		sb.append(" WHERE p.id = :patrimonioPK ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("patrimonioPK", patrimonioPK);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public List<DeweyLibero> getSpecializzazioniByIdBiblioteca(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<DeweyLibero> deweyLiberos = biblioteca.getDeweyLiberos();
		Iterator<DeweyLibero> it = deweyLiberos.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			DeweyLibero deweyLibero = (DeweyLibero) it.next();

		}
		return deweyLiberos;
	}

	@Override
	@Transactional
	public void addSpecializzazionePatrimonio(int id_biblioteca, String dewey, String descrizioneLibera) {

		DeweyLiberoPK idDeweyLiberoPK = new DeweyLiberoPK();
		idDeweyLiberoPK.setIdBiblioteca(id_biblioteca);
		idDeweyLiberoPK.setIdDewey(dewey);

		DeweyLibero deweyLibero = new DeweyLibero();
		deweyLibero.setId(idDeweyLiberoPK);
		deweyLibero.setDescrizione(descrizioneLibera);
		deweyLibero.setBiblioteca(em.find(Biblioteca.class, id_biblioteca));
		deweyLibero.setDewey(em.find(Dewey.class, dewey));

		em.merge(deweyLibero);

	}

	@Override
	@Transactional
	public void removeSpecializzazionePatrimonio(int id_biblioteca,
			String idr_removeRecord) {
		DeweyLiberoPK idDeweyLiberoPK = new DeweyLiberoPK();
		idDeweyLiberoPK.setIdBiblioteca(id_biblioteca);
		idDeweyLiberoPK.setIdDewey(idr_removeRecord);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM DeweyLibero d ");
		sb.append(" WHERE d.id = :idDeweyLiberoPK ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("idDeweyLiberoPK", idDeweyLiberoPK);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public List<FondiSpeciali> getFondiSpecialiByIdBiblioteca(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<FondiSpeciali> fondiSpecialis = biblioteca.getFondiSpecialis();
		Iterator<FondiSpeciali> it = fondiSpecialis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			FondiSpeciali fondiSpeciali = (FondiSpeciali) it.next();
		}
		return fondiSpecialis;
	}

	@Override
	@Transactional
	public int countAllDenominazioneFondiSpecialiPossibili(String query) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(f) FROM FondiSpeciali f ");

		if (query != null && query.length() > 0) {
			sb.append(" WHERE f.denominazione like :denominazione ");
		}

		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {
			String tmpFilter = "%".concat(query).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();

	}

	@Override
	@Transactional
	public List<FondiSpeciali> getDenominazioneFondiSpecialiPossibiliFiltered(
			String query, int start, int limit) {

		StringBuffer sb = new StringBuffer();
		sb.append(" FROM FondiSpeciali f ");

		if (query != null && query.length() > 0) {
			sb.append(" WHERE f.denominazione like :denominazione ");
		}

		Query q = em.createQuery(sb.toString());

		if (query != null && query.length() > 0) {
			String tmpFilter = "%".concat(query).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}

		q.setMaxResults(limit);
		q.setFirstResult(start);

		return q.getResultList();

	}

	@Override
	@Transactional
	public int searchFondoSpeciale(FondiSpeciali fondoSpeciale) {

		StringBuffer sb = new StringBuffer();
		sb.append(" FROM FondiSpeciali f ");

		int idFondiSpecialiCatalogazioneInventario = 0;

		String denominazione = new String();
		String descrizione = new String();
		String idDewey = new String();
		String catalogazioneInventarioUrl = new String();
		String citazioneBibliografica = new String();

		boolean fondoDepositato = false;

		List<String> criteria = new ArrayList<String>();

		if (fondoSpeciale.getDenominazione() != null) {
			denominazione = fondoSpeciale.getDenominazione();
			criteria.add(" f.denominazione = :denominazione ");
		}

		if (fondoSpeciale.getDescrizione() != null) {
			descrizione = fondoSpeciale.getDescrizione();
			criteria.add(" f.descrizione = :descrizione ");
		}

		if (fondoSpeciale.getDewey() != null) {
			idDewey = fondoSpeciale.getDewey().getIdDewey();
			criteria.add(" f.dewey.idDewey = :idDewey ");
		}

		if (fondoSpeciale.getFondoDepositato() != null) {
			fondoDepositato = fondoSpeciale.getFondoDepositato();
			criteria.add(" f.fondoDepositato = :fondoDepositato ");
		}

		if (fondoSpeciale.getFondiSpecialiCatalogazioneInventario() != null) {
			idFondiSpecialiCatalogazioneInventario = fondoSpeciale
			.getFondiSpecialiCatalogazioneInventario()
			.getIdFondiSpecialiCatalogazioneInventario();
			criteria.add(" f.fondiSpecialiCatalogazioneInventario.idFondiSpecialiCatalogazioneInventario = :idFondiSpecialiCatalogazioneInventario ");
		}

		if (fondoSpeciale.getCatalogazioneInventarioUrl() != null) {
			catalogazioneInventarioUrl = fondoSpeciale
			.getCatalogazioneInventarioUrl();
			criteria.add(" f.catalogazioneInventarioUrl = :catalogazioneInventarioUrl ");
		}

		if (fondoSpeciale.getCitazioneBibliografica() != null) {
			citazioneBibliografica = fondoSpeciale.getCitazioneBibliografica();
			criteria.add(" f.citazioneBibliografica = :citazioneBibliografica ");
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

		// if(fondoSpeciale.getIdFondiSpeciali()!=null){
		//
		// q.setParameter("idfondiSpeciali", idFondo);
		// }

		if (fondoSpeciale.getDenominazione() != null) {

			q.setParameter("denominazione", denominazione);
		}

		if (fondoSpeciale.getDescrizione() != null) {

			q.setParameter("descrizione", descrizione);
		}

		if (fondoSpeciale.getDewey() != null) {
			idDewey = fondoSpeciale.getDewey().getIdDewey();
			criteria.add(" f.dewey.idDewey = :idDewey ");
			q.setParameter("idDewey", idDewey);
		}

		if (fondoSpeciale.getFondoDepositato() != null) {

			q.setParameter("fondoDepositato", fondoDepositato);
		}

		if (fondoSpeciale.getFondiSpecialiCatalogazioneInventario() != null) {

			q.setParameter("idFondiSpecialiCatalogazioneInventario",
					idFondiSpecialiCatalogazioneInventario);
		}

		if (fondoSpeciale.getCatalogazioneInventarioUrl() != null) {
			q.setParameter("catalogazioneInventarioUrl",
					catalogazioneInventarioUrl);
		}

		if (fondoSpeciale.getCitazioneBibliografica() != null) {

			q.setParameter("citazioneBibliografica", citazioneBibliografica);
		}

		try {
			return ((FondiSpeciali) q.getSingleResult()).getIdFondiSpeciali();
		} catch (NoResultException NRE) {
			return -1;
		} catch (NonUniqueResultException NURE) {
			return -1;
		}

	}

	@Override
	@Transactional
	public FondiSpeciali addFondoSpeciale(int idFondoSpecialeToAdd, int idBiblioteca, boolean modifica) {
		FondiSpeciali newFondo = em.find(FondiSpeciali.class, idFondoSpecialeToAdd);
		Biblioteca biblioteca = em.find(Biblioteca.class, idBiblioteca);
		List<FondiSpeciali> fondiSpecialis = biblioteca.getFondiSpecialis();
		Iterator<FondiSpeciali> it = fondiSpecialis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			FondiSpeciali fondiSpeciali = (FondiSpeciali) it.next();
		}
		fondiSpecialis.add(newFondo);

		biblioteca.setFondiSpecialis(fondiSpecialis);

		em.merge(biblioteca);
		return newFondo;
	}

	@Override
	@Transactional
	public int createFondoSpeciale(FondiSpeciali fondoSpeciale) {
		FondiSpeciali newFondo = new FondiSpeciali();
		newFondo.setDenominazione(fondoSpeciale.getDenominazione());
		if (fondoSpeciale.getDescrizione() != null)
			newFondo.setDescrizione(fondoSpeciale.getDescrizione());
		if (fondoSpeciale.getFondoDepositato() != null)
			newFondo.setFondoDepositato(fondoSpeciale.getFondoDepositato());
		if (fondoSpeciale.getCatalogazioneInventarioUrl() != null)
			newFondo.setCatalogazioneInventarioUrl(fondoSpeciale.getCatalogazioneInventarioUrl());

		if (fondoSpeciale.getFondiSpecialiCatalogazioneInventario() != null) {
			FondiSpecialiCatalogazioneInventario fscu = em.find(FondiSpecialiCatalogazioneInventario.class, fondoSpeciale.getFondiSpecialiCatalogazioneInventario().getIdFondiSpecialiCatalogazioneInventario());
			newFondo.setFondiSpecialiCatalogazioneInventario(fscu);
		}

		if (fondoSpeciale.getDewey() != null) {
			Dewey dewey = em.find(Dewey.class, fondoSpeciale.getDewey().getIdDewey());
			newFondo.setDewey(dewey);
		}

		em.persist(newFondo);
		return newFondo.getIdFondiSpeciali();

	}

	@Override
	@Transactional
	public FondiSpeciali updateFondoSpeciale(FondiSpeciali fondoSpeciale) {
		FondiSpeciali newFondo = em.find(FondiSpeciali.class, fondoSpeciale.getIdFondiSpeciali());
		newFondo.setDenominazione(fondoSpeciale.getDenominazione());
		if (fondoSpeciale.getDescrizione() != null)
			newFondo.setDescrizione(fondoSpeciale.getDescrizione());
		if (fondoSpeciale.getFondoDepositato() != null)
			newFondo.setFondoDepositato(fondoSpeciale.getFondoDepositato());
		if (fondoSpeciale.getCatalogazioneInventarioUrl() != null)
			newFondo.setCatalogazioneInventarioUrl(fondoSpeciale.getCatalogazioneInventarioUrl());

		if (fondoSpeciale.getFondiSpecialiCatalogazioneInventario() != null) {
			FondiSpecialiCatalogazioneInventario fscu = em.find(FondiSpecialiCatalogazioneInventario.class, fondoSpeciale.getFondiSpecialiCatalogazioneInventario().getIdFondiSpecialiCatalogazioneInventario());
			newFondo.setFondiSpecialiCatalogazioneInventario(fscu);
		}

		if (fondoSpeciale.getDewey() != null) {
			Dewey dewey = em.find(Dewey.class, fondoSpeciale.getDewey().getIdDewey());
			newFondo.setDewey(dewey);
		}

		em.merge(newFondo);
		return newFondo;
	}

	@Override
	@Transactional
	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<FondiSpeciali> fondiSpecialis = biblioteca.getFondiSpecialis();

		FondiSpeciali toRemoveFondiSpeciali = em.find(FondiSpeciali.class,
				id_removeRecord);

		if ((fondiSpecialis.contains(toRemoveFondiSpeciali))) {

			fondiSpecialis.remove(toRemoveFondiSpeciali);

			biblioteca.setFondiSpecialis(fondiSpecialis);

			em.merge(biblioteca);
		}

	}

	/**
	 * In base all'id tabella dinamica passato viene caricata la lista dati
	 * relativa della biblioteca avente l'id passato com eparametro
	 * */
	@Override
	@Transactional
	public List<?> getListaVoci(int id_biblioteca, int idTabellaDinamica) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<?> resultList = null;

		switch (idTabellaDinamica) {
		case DtoJpaMapping.CATALOGAZIONE_NORME_INDEX: {
			resultList = (List<?>) biblioteca.getNormeCatalogaziones();
			break;
		}
		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX: {
			resultList = (List<?>) biblioteca.getIndicizzazioneClassificatas();
			break;
		}
		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX: {
			resultList = (List<?>) biblioteca.getIndicizzazioneSoggettos();
			break;
		}

		case DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX: {
			resultList = (List<?>) biblioteca.getSistemiPrestitoInterbibliotecarios();
			break;
		}
		}

		Iterator<?> it = resultList.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			Object object = (Object) it.next();
		}
		return resultList;
	}

	/**
	 * In base all'id tabella dinamica passato viene mappato il dto nel tipo
	 * specificato e aggiunto alla lista specifica delle biblioteca avente l'id
	 * passato com eparametro
	 * @throws DuplicateEntryException
	 * */
	@Override
	@Transactional
	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			DynaTabDTO dynaTabDTODB, int id_biblioteca, int idTabellaDinamica) throws DuplicateEntryException {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List resultList = null;

		switch (idTabellaDinamica) {
		case DtoJpaMapping.CATALOGAZIONE_NORME_INDEX: {// NORME CATALOGAZIONE
			NormeCatalogazione normeCatalogazione = (NormeCatalogazione) DtoJpaMapping
			.dto2DynaRecord(dynaTabDTODB, true);
			resultList = biblioteca.getNormeCatalogaziones();

			// Controllo che la voce sa salvare non esista già
			Iterator<NormeCatalogazione> it = resultList.iterator();
			while (it.hasNext()) {
				NormeCatalogazione tmp = (NormeCatalogazione) it.next();
				if (tmp.getIdNormeCatalogazione().intValue() == normeCatalogazione.getIdNormeCatalogazione().intValue()) {
					// Se esiste già ritorno eccezione con opportuno messaggio
					throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
				}
			}

			resultList.add(normeCatalogazione);
			biblioteca.setNormeCatalogaziones(resultList);
			break;
		}
		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX: {// SISTEMI
			// DI
			// INDICIZZAZIONE
			// CLASSIFICATA
			IndicizzazioneClassificata indicizzazioneClassificata = (IndicizzazioneClassificata) DtoJpaMapping
			.dto2DynaRecord(dynaTabDTODB, true);
			resultList = biblioteca.getIndicizzazioneClassificatas();

			// Controllo che la voce sa salvare non esista già
			Iterator<IndicizzazioneClassificata> it = resultList.iterator();
			while (it.hasNext()) {
				IndicizzazioneClassificata tmp = (IndicizzazioneClassificata) it
				.next();
				if (tmp.getIdIndicizzazioneClassificata().intValue() == indicizzazioneClassificata
						.getIdIndicizzazioneClassificata().intValue()) {
					// Se esiste già ritorno eccezione con opportuno messaggio
					throw new DuplicateEntryException(
							DUPLICATE_ENTRY_ERROR_MESSAGE);
				}
			}

			resultList.add(indicizzazioneClassificata);
			biblioteca.setIndicizzazioneClassificatas(resultList);
			break;
		}

		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX: {// SISTEMI
			// DI
			// INDICIZZAZIONE
			// PER
			// SOGGETTO
			IndicizzazioneSoggetto indicizzazioneSoggetto = (IndicizzazioneSoggetto) DtoJpaMapping
			.dto2DynaRecord(dynaTabDTODB, true);
			resultList = biblioteca.getIndicizzazioneSoggettos();

			// Controllo che la voce sa salvare non esista già
			Iterator<IndicizzazioneSoggetto> it = resultList.iterator();
			while (it.hasNext()) {
				IndicizzazioneSoggetto tmp = (IndicizzazioneSoggetto) it.next();
				if (tmp.getIdIndicizzazioneSoggetto().intValue() == indicizzazioneSoggetto
						.getIdIndicizzazioneSoggetto().intValue()) {
					// Se esiste già ritorno eccezione con opportuno messaggio
					throw new DuplicateEntryException(
							DUPLICATE_ENTRY_ERROR_MESSAGE);
				}
			}

			resultList.add(indicizzazioneSoggetto);
			biblioteca.setIndicizzazioneSoggettos(resultList);
			break;
		}

		case DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX: {// SISTEMI
			// DI
			// PRESTITO
			// INTERBIBLIOTECARIO
			SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecario = (SistemiPrestitoInterbibliotecario) DtoJpaMapping
			.dto2DynaRecord(dynaTabDTODB, true);
			resultList = biblioteca.getSistemiPrestitoInterbibliotecarios();

			// Controllo che la voce sa salvare non esista già
			Iterator<SistemiPrestitoInterbibliotecario> it = resultList
			.iterator();
			while (it.hasNext()) {
				SistemiPrestitoInterbibliotecario tmp = (SistemiPrestitoInterbibliotecario) it
				.next();
				if (tmp.getIdSistemiPrestitoInterbibliotecario().intValue() == sistemiPrestitoInterbibliotecario
						.getIdSistemiPrestitoInterbibliotecario().intValue()) {
					// Se esiste già ritorno eccezione con opportuno messaggio
					throw new DuplicateEntryException(
							DUPLICATE_ENTRY_ERROR_MESSAGE);
				}
			}

			resultList.add(sistemiPrestitoInterbibliotecario);
			biblioteca.setSistemiPrestitoInterbibliotecarios(resultList);
			break;
		}

		}

		em.merge(biblioteca);

	}

	/**
	 * In base all'id tabella dinamica, viene rimossa dalla lista specifica la
	 * entry avente l'id = a id_rimuoviRecord dalla lista specifica delle
	 * biblioteca avente l'id passato come parametro
	 * */
	@Override
	@Transactional
	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviRecord, int idTabellaDinamica) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List resultList = null;
		switch (idTabellaDinamica) {
		case DtoJpaMapping.CATALOGAZIONE_NORME_INDEX: {// NORME CATALOGAZIONE
			NormeCatalogazione normeCatalogazione = em.find(
					NormeCatalogazione.class, id_rimuoviRecord);
			resultList = biblioteca.getNormeCatalogaziones();
			Iterator<?> it = resultList.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy
				Object object = (Object) it.next();
			}
			resultList.remove(normeCatalogazione);
			biblioteca.setNormeCatalogaziones(resultList);
		}
		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX: {// SISTEMI
			// DI
			// INDICIZZAZIONE
			// CLASSIFICATA
			IndicizzazioneClassificata indicizzazioneClassificata = em.find(
					IndicizzazioneClassificata.class, id_rimuoviRecord);
			resultList = biblioteca.getIndicizzazioneClassificatas();
			Iterator<?> it = resultList.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy
				Object object = (Object) it.next();
			}
			resultList.remove(indicizzazioneClassificata);
			biblioteca.setIndicizzazioneClassificatas(resultList);
		}
		case DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX: {// SISTEMI
			// DI
			// INDICIZZAZIONE
			// PER
			// SOGGETTO
			IndicizzazioneSoggetto indicizzazioneSoggetto = em.find(
					IndicizzazioneSoggetto.class, id_rimuoviRecord);
			resultList = biblioteca.getIndicizzazioneSoggettos();
			Iterator<?> it = resultList.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy
				Object object = (Object) it.next();
			}
			resultList.remove(indicizzazioneSoggetto);
			biblioteca.setIndicizzazioneSoggettos(resultList);
		}

		case DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX: {// SISTEMI
			// DI
			// PRESTITO
			// INTERBIBLIOTECARIO
			SistemiPrestitoInterbibliotecario indicizzazioneSoggetto = em.find(
					SistemiPrestitoInterbibliotecario.class, id_rimuoviRecord);
			resultList = biblioteca.getSistemiPrestitoInterbibliotecarios();
			Iterator<?> it = resultList.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy
				Object object = (Object) it.next();
			}
			resultList.remove(indicizzazioneSoggetto);
			biblioteca.setSistemiPrestitoInterbibliotecarios(resultList);
		}

		em.merge(biblioteca);
		}
	}

	@Override
	@Transactional
	public int countAllSpogliMaterialeBibliograficoPossibili(String filter) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(distinct s.descrizioneBibliografica) FROM SpogliBibliografici s ");

		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE s.descrizioneBibliografica like :descrizioneBibliografica ");
		}

		Query q = em.createQuery(sb.toString());

		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("descrizioneBibliografica", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public List<String> getListaSpogliMaterialeBibliograficoPossibiliFiltered(
			int start, int limit, String filter) {

		StringBuffer sb = new StringBuffer();
		sb.append("select DISTINCT s.descrizioneBibliografica FROM SpogliBibliografici s ");

		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE s.descrizioneBibliografica like :descrizioneBibliografica ");
		}

		sb.append(" ORDER BY s.descrizioneBibliografica asc ");

		Query q = em.createQuery(sb.toString());

		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("descrizioneBibliografica", tmpFilter);
		}

		q.setMaxResults(limit);
		q.setFirstResult(start);
		return q.getResultList();
	}

	@Override
	@Transactional
	public List<SpogliBibliografici> getListaSpogliMaterialeBibliograficoByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<SpogliBibliografici> spogliBibliograficis = biblioteca.getSpogliBibliograficis();
		Iterator<SpogliBibliografici> it = spogliBibliograficis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			SpogliBibliografici spogliBibliografici = (SpogliBibliografici) it.next();
		}
		return spogliBibliograficis;
	}

	@Override
	@Transactional
	public void addSpoglioMaterialeBibliografico(SpogliBibliografici nuovoSpoglio, int id_biblioteca, boolean modifica) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		nuovoSpoglio.setBiblioteca(biblioteca);

		em.merge(nuovoSpoglio);
	}

	@Override
	@Transactional
	public void addSpoglioMaterialeBibliograficoRipristino(String descrSpoglio, int id_biblioteca) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		SpogliBibliografici toSave = new SpogliBibliografici();
		toSave.setDescrizioneBibliografica(descrSpoglio);
		toSave.setBiblioteca(biblioteca);
		em.persist(toSave);
	}

	@Override
	@Transactional
	public void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio) {
		SpogliBibliografici spogliBibliografici = em.find(SpogliBibliografici.class, id_rimuoviSpoglio);
		em.remove(spogliBibliografici);
	}

	@Override
	@Transactional
	public List<Pubblicazioni> getlistaPubblicazioniByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<Pubblicazioni> pubblicazionis = biblioteca.getPubblicazionis();

		Iterator<Pubblicazioni> it = pubblicazionis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			Pubblicazioni pubblicazioni = (Pubblicazioni) it.next();

		}
		return pubblicazionis;
	}

	@Override
	@Transactional
	public void addPubblicazioni(Pubblicazioni nuovaPubblicazione,
			int id_biblioteca, boolean modifica) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		nuovaPubblicazione.setBiblioteca(biblioteca);

		em.merge(nuovaPubblicazione);

	}

	@Override
	@Transactional
	public void removePubblicazioni(int id_rimuoviPubblicazione) {
		Pubblicazioni pubblicazioni = em.find(Pubblicazioni.class, id_rimuoviPubblicazione);
		em.remove(pubblicazioni);

	}

	@Override
	@Transactional
	public void inserisciBibliograficaInfoCatalogazione(int id_biblio,
			String value) {
		Bibliografia bibliografia = null;

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblio);
		/*
		 * Modifico solo l'oggeto in posizione 0 dato che nell'interfaccia viene
		 * visualizzata solo un istanza di Bibliografia, mentre sul database è
		 * rappresentata una Lista di bibliografie
		 */

		if (biblioteca.getBibliografias().size() > 0) {
			bibliografia = em.find(Bibliografia.class, biblioteca.getBibliografias().get(0).getIdBibliografia());
			bibliografia.setDescrizione(value);
			em.merge(bibliografia);
		} else {
			bibliografia = new Bibliografia();
			bibliografia.setBiblioteca(biblioteca);
			bibliografia.setDescrizione(value);
			em.persist(bibliografia);
		}

	}

	@Override
	@Transactional
	public List<PartecipaCataloghiCollettiviMateriale> getPartecipaCataloghiCollettiviByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettivis = biblioteca.getPartecipaCataloghiCollettiviMateriales();
		Iterator<PartecipaCataloghiCollettiviMateriale> it = partecipaCataloghiCollettivis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiCollettiviMateriale
			PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = (PartecipaCataloghiCollettiviMateriale) it.next();

		}
		return partecipaCataloghiCollettivis;
	}

	@Override
	@Transactional
	public void addPartecipaCatalogoCollettivo(int id_biblioteca, PartecipaCataloghiCollettiviMateriale tmp, boolean modifica) throws EntryNotFoundException {

		String descrCatalogo = tmp.getCataloghiCollettivi().getDescrizione();
		Integer idZona = tmp.getCataloghiCollettivi().getCataloghiCollettiviZonaTipo().getIdCataloghiCollettiviZonaTipo();
		String dettaglioZona = tmp.getCataloghiCollettivi().getDettaglioZona();
		// Controllo se il catalogo collettivo scelto esiste
		CataloghiCollettivi trovatoCatalogo = existCatalogoCollettivo(descrCatalogo, idZona, dettaglioZona);

		if (modifica) {
			PartecipaCataloghiCollettiviMateriale toSave= em.find(PartecipaCataloghiCollettiviMateriale.class, tmp.getIdCataloghiCollettiviMateriale());
			PatrimonioSpecializzazione tmpPatrimonio=em.find(PatrimonioSpecializzazione.class,tmp.getPatrimonioSpecializzazione().getIdPatrimonioSpecializzazione());

			toSave.setPatrimonioSpecializzazione(tmpPatrimonio);

			toSave.setCataloghiCollettivi(trovatoCatalogo);

			if (tmp.getSchede() != null)
				toSave.setSchede(tmp.getSchede());

			if (tmp.getPercentualeSchede() != null)
				toSave.setPercentualeSchede(tmp.getPercentualeSchede());

			if (tmp.getVolume() != null)
				toSave.setVolume(tmp.getVolume());

			if (tmp.getPercentualeVolume() != null)
				toSave.setPercentualeVolume(tmp.getPercentualeVolume());

			if (tmp.getDescrizioneVolume() != null)
				toSave.setDescrizioneVolume(tmp.getDescrizioneVolume());

			if (tmp.getMicroforme() != null)
				toSave.setMicroforme(tmp.getMicroforme());

			if (tmp.getPercentualeMicroforme() != null)
				toSave.setPercentualeMicroforme(tmp.getPercentualeMicroforme());

			if (tmp.getCataloghiSupportoDigitaleTipo() != null)
				toSave.setCataloghiSupportoDigitaleTipo(tmp.getCataloghiSupportoDigitaleTipo());

			if (tmp.getPercentualeInformatizzata() != null)
				toSave.setPercentualeInformatizzata(tmp.getPercentualeInformatizzata());

			if (tmp.getDaAnno() != null) {
				toSave.setDaAnno(tmp.getDaAnno());
				
			} else {
				toSave.setDaAnno(null);
			}
			
			if (tmp.getAAnno() != null) {
				toSave.setAAnno(tmp.getAAnno());
				
			} else {
				toSave.setAAnno(null);
			}

			em.merge(toSave);

		} else {
			Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

			tmp.setBiblioteca(biblioteca);

			tmp.setCataloghiCollettivi(trovatoCatalogo);

			em.persist(tmp);
			
			/* Caso ripristina con url esistenti -> INIZIO */
			if (tmp.getCataloghiSupportoDigitaleTipo() != null && tmp.getCataloghiSupportoDigitaleTipo().getDescrizione().equalsIgnoreCase("Online")) {
				if (tmp.getCataloghiCollettiviMaterialeUrls() != null && tmp.getCataloghiCollettiviMaterialeUrls().size() > 0) {
					List<CataloghiCollettiviMaterialeUrl> urls = tmp.getCataloghiCollettiviMaterialeUrls();
					
					for (CataloghiCollettiviMaterialeUrl urlEntry : urls) {
						urlEntry.setIdCataloghiCollettiviMaterialeUrl(null);
						urlEntry.setPartecipaCataloghiCollettiviMateriale(tmp);
						saveChild(urlEntry);
					}
				}
			}
			/* Caso ripristina con url esistenti -> FINE */
		}

	}

	@Override
	@Transactional
	public CataloghiCollettivi existCatalogoCollettivo(String descrCatalogo, int idZona, String dettaglioZona) throws EntryNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM CataloghiCollettivi c ");
		sb.append(" WHERE c.descrizione = :descrCatalogo ");
		sb.append(" AND c.cataloghiCollettiviZonaTipo.idCataloghiCollettiviZonaTipo = :idZonaTipo ");

		if (dettaglioZona != null && dettaglioZona.length() > 0) {
			sb.append(" AND c.dettaglioZona = :dettaglioZona ");
		}

		Query q = em.createQuery(sb.toString());

		q.setParameter("descrCatalogo", descrCatalogo);
		q.setParameter("idZonaTipo", idZona);
		if (dettaglioZona != null && dettaglioZona.length() > 0) {
			q.setParameter("dettaglioZona", dettaglioZona);
		}

		try {
			return (CataloghiCollettivi) q.getSingleResult();
		} catch (NoResultException NRE) {
			throw new EntryNotFoundException("Catalogo non trovato!");
		}

	}

	@Override
	@Transactional
	public List<PartecipaCataloghiSpecialiMateriale> getPartecipaCataloghiSpecialiByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PartecipaCataloghiSpecialiMateriale> partecipaCataloghiSpecialis = biblioteca
		.getPartecipaCataloghiSpecialiMateriales();
		Iterator<PartecipaCataloghiSpecialiMateriale> it = partecipaCataloghiSpecialis
		.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiSpecialiMateriale
			PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale = (PartecipaCataloghiSpecialiMateriale) it
			.next();

		}
		return partecipaCataloghiSpecialis;
	}

	@Override
	@Transactional
	public void addPartecipaCatalogoSpeciale(int id_biblioteca,
			PartecipaCataloghiSpecialiMateriale tmp, boolean modifica) {

		if (modifica) {
			PartecipaCataloghiSpecialiMateriale toUpdate = em.find(
					PartecipaCataloghiSpecialiMateriale.class,
					tmp.getIdCataloghiSpecialiMateriale());

			toUpdate.setDenominazione(tmp.getDenominazione());

			PatrimonioSpecializzazione tmpPatrimonio = em.find(
					PatrimonioSpecializzazione.class, tmp
					.getPatrimonioSpecializzazione()
					.getIdPatrimonioSpecializzazione());

			toUpdate.setPatrimonioSpecializzazione(tmpPatrimonio);

			if (tmp.getSchede() != null)
				toUpdate.setSchede(tmp.getSchede());

			if (tmp.getPercentualeSchede() != null)
				toUpdate.setPercentualeSchede(tmp.getPercentualeSchede());

			if (tmp.getVolume() != null)
				toUpdate.setVolume(tmp.getVolume());

			if (tmp.getPercentualeVolume() != null)
				toUpdate.setPercentualeVolume(tmp.getPercentualeVolume());

			if (tmp.getDescrizioneVolume() != null)
				toUpdate.setDescrizioneVolume(tmp.getDescrizioneVolume());

			if (tmp.getMicroforme() != null)
				toUpdate.setMicroforme(tmp.getMicroforme());

			if (tmp.getPercentualeMicroforme() != null)
				toUpdate.setPercentualeMicroforme(tmp
						.getPercentualeMicroforme());

			if (tmp.getCataloghiSupportoDigitaleTipo() != null)
				toUpdate.setCataloghiSupportoDigitaleTipo(tmp
						.getCataloghiSupportoDigitaleTipo());

			if (tmp.getPercentualeInformatizzata() != null)
				toUpdate.setPercentualeInformatizzata(tmp
						.getPercentualeInformatizzata());

			if (tmp.getDaAnno() != null) {
				toUpdate.setDaAnno(tmp.getDaAnno());
				
			} else {
				toUpdate.setDaAnno(null);
			}
			

			if (tmp.getAAnno() != null) {
				toUpdate.setAAnno(tmp.getAAnno());
				
			} else {
				toUpdate.setAAnno(null);
			}

			em.merge(toUpdate);
		} else {
			Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

			tmp.setBiblioteca(biblioteca);

			em.persist(tmp);
			
			/* Caso ripristina con url esistenti -> INIZIO */
			if (tmp.getCataloghiSupportoDigitaleTipo() != null && tmp.getCataloghiSupportoDigitaleTipo().getDescrizione().equalsIgnoreCase("Online")) {
				if (tmp.getCataloghiSpecialiMaterialeUrls() != null && tmp.getCataloghiSpecialiMaterialeUrls().size() > 0) {
					List<CataloghiSpecialiMaterialeUrl> urls = tmp.getCataloghiSpecialiMaterialeUrls();
					
					for (CataloghiSpecialiMaterialeUrl urlEntry : urls) {
						urlEntry.setIdCataloghiSpecialiMaterialeUrl(null);
						urlEntry.setPartecipaCataloghiSpecialiMateriale(tmp);
						saveChild(urlEntry);
					}
				}
			}
			/* Caso ripristina con url esistenti -> FINE */
		}

	}

	@Override
	@Transactional
	public void removePartecipaCatalogoCollettivo(int idRemove,
			int id_biblioteca) {
		// Carico la biblioteca
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		int tmpIndex = 0;
		// Carico l'oggetto PartecipaCataloghiSpecialiMateriale da rimuovere
		PartecipaCataloghiCollettiviMateriale toRemove = em.find(
				PartecipaCataloghiCollettiviMateriale.class, idRemove);

		// Scorro la lista partecipaCataloghiCollettivis per cercare l'oggetto
		List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettivis = biblioteca
		.getPartecipaCataloghiCollettiviMateriales();
		Iterator<PartecipaCataloghiCollettiviMateriale> it = partecipaCataloghiCollettivis
		.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiCollettiviMateriale
			PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = (PartecipaCataloghiCollettiviMateriale) it
			.next();
			if (partecipaCataloghiCollettiviMateriale
					.getIdCataloghiCollettiviMateriale() == toRemove
					.getIdCataloghiCollettiviMateriale())
				tmpIndex = partecipaCataloghiCollettivis
				.indexOf(partecipaCataloghiCollettiviMateriale);
		}
		// Rimuovo l'oggetto dalla lista
		partecipaCataloghiCollettivis.remove(tmpIndex);
		// Setto la nuova lista aggiornata nella biblioteca
		biblioteca
		.setPartecipaCataloghiCollettiviMateriales(partecipaCataloghiCollettivis);
		// Aggiorno la biblioteca
		em.merge(biblioteca);

		// Rimuovo le entry relative alla partecipazione al catalogo nella
		// tabella cataloghi_Collettivi_materiale_url
		removeAllCataloghiCollettiviMaterialeUrlByIdPatecipaCatalogo(idRemove);
		// rimuovo l'associazione tra la biblioteca e la partecipazione al
		// catalogo
		em.remove(toRemove);

		/*
		 * Utilizzo questa modalità per rimuovere prima la chiave esterna che in
		 * biblioteca riferisce l'associazione della partecipazione al catalogo,
		 * e dopo rimuovo la lista degli url che contengono la chiave esterna
		 * della partecipazione al catalogo, dopo rimuovo l'associazione della
		 * tabella Partecipa...
		 */
	}

	@Transactional
	public void removeAllCataloghiCollettiviMaterialeUrlByIdPatecipaCatalogo(
			int idPartecipaCatalogo) {
		PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = em
		.find(PartecipaCataloghiCollettiviMateriale.class,
				idPartecipaCatalogo);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM CataloghiCollettiviMaterialeUrl c ");
		sb.append(" WHERE c.partecipaCataloghiCollettiviMateriale = :partecipaCataloghiCollettiviMateriale ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("partecipaCataloghiCollettiviMateriale",
				partecipaCataloghiCollettiviMateriale);

		q.executeUpdate();
	}

	@Override
	@Transactional
	public void removePartecipaCatalogoSpeciale(int idRemove, int id_biblioteca) {
		// Carico la biblioteca
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		int tmpIndex = 0;
		// Carico l'oggetto PartecipaCataloghiSpecialiMateriale da rimuovere
		PartecipaCataloghiSpecialiMateriale toRemove = em.find(
				PartecipaCataloghiSpecialiMateriale.class, idRemove);

		// Scorro la lista partecipaCataloghiSpecialis per cercare l'oggetto
		List<PartecipaCataloghiSpecialiMateriale> partecipaCataloghiSpecialis = biblioteca
		.getPartecipaCataloghiSpecialiMateriales();
		Iterator<PartecipaCataloghiSpecialiMateriale> it = partecipaCataloghiSpecialis
		.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiSpecialiMateriale
			PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale = (PartecipaCataloghiSpecialiMateriale) it
			.next();
			if (partecipaCataloghiSpecialiMateriale
					.getIdCataloghiSpecialiMateriale() == toRemove
					.getIdCataloghiSpecialiMateriale())
				tmpIndex = partecipaCataloghiSpecialis
				.indexOf(partecipaCataloghiSpecialiMateriale);
		}
		// Rimuovo l'oggetto dalla lista
		partecipaCataloghiSpecialis.remove(tmpIndex);
		// Setto la nuova lista aggiornata nella biblioteca
		biblioteca
		.setPartecipaCataloghiSpecialiMateriales(partecipaCataloghiSpecialis);
		// Aggiorno la biblioteca
		em.merge(biblioteca);

		// Rimuovo le entry relative alla partecipazione al catalogo nella
		// tabella cataloghi_speciali_materiale_url
		removeAllCataloghiSpecialiMaterialeUrlByIdPatecipaCatalogo(idRemove);
		// rimuovo l'associazione tra la biblioteca e la partecipazione al
		// catalogo
		em.remove(toRemove);

		/*
		 * Utilizzo questa modalità per rimuovere prima la chiave esterna che in
		 * biblioteca riferisce l'associazione della partecipazione al catalogo,
		 * e dopo rimuovo la lista degli url che contengono la chiave esterna
		 * della partecipazione al catalogo, dopo rimuovo l'associazione della
		 * tabella Partecipa...
		 */

	}

	@Override
	@Transactional
	public void removePartecipaCatalogoGenerale(int idRemove, int id_biblioteca) {
		// Carico la biblioteca
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		PartecipaCataloghiGenerali toRemove = em.find(
				PartecipaCataloghiGenerali.class, idRemove);
		int tmpIndex = 0;

		// Scorro la lista partecipaCataloghiSpecialis per cercare l'oggetto
		List<PartecipaCataloghiGenerali> partecipaCataloghiGeneralis = biblioteca
		.getPartecipaCataloghiGeneralis();
		Iterator<PartecipaCataloghiGenerali> it = partecipaCataloghiGeneralis
		.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiSpecialiMateriale
			PartecipaCataloghiGenerali partecipaCataloghiGenerali = (PartecipaCataloghiGenerali) it
			.next();
			if (partecipaCataloghiGenerali.getIdCataloghiGenerali() == toRemove
					.getIdCataloghiGenerali())
				tmpIndex = partecipaCataloghiGeneralis
				.indexOf(partecipaCataloghiGenerali);
		}
		// Rimuovo l'oggetto dalla lista
		partecipaCataloghiGeneralis.remove(tmpIndex);
		// Setto la nuova lista aggiornata nella biblioteca
		biblioteca.setPartecipaCataloghiGeneralis(partecipaCataloghiGeneralis);
		// Aggiorno la biblioteca
		em.merge(biblioteca);

		// Rimuovo le entry relative alla partecipazione al catalogo nella
		// tabella cataloghi_speciali_materiale_url
		removeAllCataloghiGeneraliUrlByIdPatecipaCatalogo(idRemove);
		// rimuovo l'associazione tra la biblioteca e la partecipazione al
		// catalogo
		em.remove(toRemove);

		/*
		 * Utilizzo questa modalità per rimuovere prima la chiave esterna che in
		 * biblioteca riferisce l'associazione della partecipazione al catalogo,
		 * e dopo rimuovo la lista degli url che contengono la chiave esterna
		 * della partecipazione al catalogo, dopo rimuovo l'associazione della
		 * tabella Partecipa...
		 */
	}

	@Transactional
	public void removeAllCataloghiSpecialiMaterialeUrlByIdPatecipaCatalogo(
			int idPartecipaCatalogo) {
		PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale = em
		.find(PartecipaCataloghiSpecialiMateriale.class,
				idPartecipaCatalogo);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM CataloghiSpecialiMaterialeUrl c ");
		sb.append(" WHERE c.partecipaCataloghiSpecialiMateriale = :partecipaCataloghiSpecialiMateriale ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("partecipaCataloghiSpecialiMateriale", partecipaCataloghiSpecialiMateriale);

		q.executeUpdate();
	}

	@Transactional
	public void removeAllCataloghiGeneraliUrlByIdPatecipaCatalogo(
			int idPartecipaCatalogo) {
		PartecipaCataloghiGenerali partecipaCataloghiGenerali = em.find(
				PartecipaCataloghiGenerali.class, idPartecipaCatalogo);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM CataloghiGeneraliUrl c ");
		sb.append(" WHERE c.partecipaCataloghiGenerali = :partecipaCataloghiGenerali ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("partecipaCataloghiGenerali", partecipaCataloghiGenerali);

		q.executeUpdate();
	}

	@Override
	@Transactional
	public List<PartecipaCataloghiGenerali> getPartecipaCataloghiGeneraliByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PartecipaCataloghiGenerali> partecipaCataloghiGeneralis = biblioteca
		.getPartecipaCataloghiGeneralis();
		Iterator<PartecipaCataloghiGenerali> it = partecipaCataloghiGeneralis
		.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy PartecipaCataloghiGenerali
			PartecipaCataloghiGenerali partecipaCataloghiGenerali = (PartecipaCataloghiGenerali) it
			.next();

		}
		return partecipaCataloghiGeneralis;
	}

	@Override
	@Transactional
	public void addPartecipaCatalogoGenerale(int id_biblioteca,
			PartecipaCataloghiGenerali tmp, boolean modifica) {

		if (modifica) {
			PartecipaCataloghiGenerali toUpdate = em.find(
					PartecipaCataloghiGenerali.class,
					tmp.getIdCataloghiGenerali());

			CatalogoGeneraleTipo catalogoGeneraleTipo = em.find(
					CatalogoGeneraleTipo.class, tmp.getCatalogoGeneraleTipo()
					.getIdCatalogoGeneraleTipo());

			toUpdate.setCatalogoGeneraleTipo(catalogoGeneraleTipo);

			if (tmp.getSchede() != null)
				toUpdate.setSchede(tmp.getSchede());

			if (tmp.getPercentualeSchede() != null)
				toUpdate.setPercentualeSchede(tmp.getPercentualeSchede());

			if (tmp.getVolume() != null)
				toUpdate.setVolume(tmp.getVolume());

			if (tmp.getPercentualeVolume() != null)
				toUpdate.setPercentualeVolume(tmp.getPercentualeVolume());

			if (tmp.getDescrizioneVolume() != null)
				toUpdate.setDescrizioneVolume(tmp.getDescrizioneVolume());

			if (tmp.getMicroforme() != null)
				toUpdate.setMicroforme(tmp.getMicroforme());

			if (tmp.getPercentualeMicroforme() != null)
				toUpdate.setPercentualeMicroforme(tmp
						.getPercentualeMicroforme());

			if (tmp.getCataloghiSupportoDigitaleTipo() != null)
				toUpdate.setCataloghiSupportoDigitaleTipo(tmp
						.getCataloghiSupportoDigitaleTipo());

			if (tmp.getPercentualeInformatizzata() != null)
				toUpdate.setPercentualeInformatizzata(tmp
						.getPercentualeInformatizzata());

			if (tmp.getDaAnno() != null) {
				toUpdate.setDaAnno(tmp.getDaAnno());
			
			} else {
				toUpdate.setDaAnno(null);
			}

			if (tmp.getAAnno() != null) {
				toUpdate.setAAnno(tmp.getAAnno());

			} else {
				toUpdate.setAAnno(null);
			}
			
			em.merge(toUpdate);
		}

		else {
			Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

			tmp.setBiblioteca(biblioteca);

			em.persist(tmp);
			
			/* Caso ripristina con url esistenti -> INIZIO */
			if (tmp.getCataloghiSupportoDigitaleTipo() != null && tmp.getCataloghiSupportoDigitaleTipo().getDescrizione().equalsIgnoreCase("Online")) {
				if (tmp.getCataloghiGeneraliUrls() != null && tmp.getCataloghiGeneraliUrls().size() > 0) {
					List<CataloghiGeneraliUrl> urls = tmp.getCataloghiGeneraliUrls();
					
					for (CataloghiGeneraliUrl urlEntry : urls) {
						urlEntry.setIdCataloghiGeneraliUrl(null);
						urlEntry.setPartecipaCataloghiGenerali(tmp);
						saveChild(urlEntry);
					}
				}
			}
			/* Caso ripristina con url esistenti -> FINE */
		}

	}

	@Override
	@Transactional
	public List<Riproduzioni> getServiziRiproduzioniFornitureByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<Riproduzioni> riproduzionis = biblioteca.getRiproduzionis();
		Iterator<Riproduzioni> it = riproduzionis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			Riproduzioni riproduzione = (Riproduzioni) it.next();

		}
		return riproduzionis;
	}

	@Override
	@Transactional
	public void addServiziRiproduzioniForniture(int id_biblioteca, Integer idTipo, Boolean hasLocale, Boolean hasNazionale,
			Boolean hasInternazionale) {

		RiproduzioniPK pk = new RiproduzioniPK();
		pk.setIdBiblioteca(id_biblioteca);
		pk.setIdRiproduzioniTipo(idTipo);

		Riproduzioni riproduzioni = new Riproduzioni();
		riproduzioni.setId(pk);
		riproduzioni.setLocale(hasLocale);
		riproduzioni.setNazionale(hasNazionale);
		riproduzioni.setInternazionale(hasInternazionale);

		em.merge(riproduzioni);
	}

	@Override
	@Transactional
	public void removeServiziRiproduzioniForniture(int idRemove,
			int id_biblioteca) {
		RiproduzioniPK riproduzioniPk = new RiproduzioniPK();
		riproduzioniPk.setIdBiblioteca(id_biblioteca);
		riproduzioniPk.setIdRiproduzioniTipo(idRemove);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM Riproduzioni r ");
		sb.append(" WHERE r.id = :riproduzioniPk ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("riproduzioniPk", riproduzioniPk);
		q.executeUpdate();

	}

	/*
	 * Metodi per il conteggio e la restituzione delle biblioteche filtrate per
	 * i parametri inseriti per il report
	 */
	@Override
	@Transactional
	public List<Biblioteca> ricercaBiblioReport(HashMap<String, Object> keys,
			int offset, int rows, String orderByField, String orderByDir) {

		Integer[] id_regioni;
		Integer[] id_province;
		int id_comune = 0;

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT b ");
		sb.append("FROM Biblioteca b LEFT JOIN b.utenteUltimaModifica u ");

		if (keys != null && keys.size() > 0) {
			if (keys.containsKey("comune") && (keys.get("comune") != null)) {
				criteria.add("b.comune = :comune");
			}

			if (keys.containsKey("province") && (keys.get("province") != null)) {
				StringBuffer s = new StringBuffer();
				if (((Integer[]) keys.get("province")).length > 0) {
					if (((Integer[]) keys.get("province")).length == 1) {
						s.append("b.comune.provincia = :provincia0");
					} else {
						for (int i = 0; i < ((Integer[]) keys.get("province")).length; i++) {
							if (i == 0)
								s.append("(b.comune.provincia = :provincia0 OR ");
							else if (i == (((Integer[]) keys.get("province")).length - 1))
								s.append("b.comune.provincia = :provincia" + i
										+ ")");
							else
								s.append("b.comune.provincia = :provincia" + i
										+ " OR ");
						}
					}
				}
				criteria.add(s.toString());
			}

			if (keys.containsKey("regioni") && (keys.get("regioni") != null)) {
				StringBuffer s = new StringBuffer();
				if (((Integer[]) keys.get("regioni")).length > 0) {
					if (((Integer[]) keys.get("regioni")).length == 1) {
						s.append("b.comune.provincia.regione = :regione0");
					} else {
						for (int i = 0; i < ((Integer[]) keys.get("regioni")).length; i++) {
							if (i == 0)
								s.append("(b.comune.provincia.regione = :regione0 OR ");
							else if (i == (((Integer[]) keys.get("regioni")).length - 1))
								s.append("b.comune.provincia.regione = :regione"
										+ i + ")");
							else
								s.append("b.comune.provincia.regione = :regione"
										+ i + " OR ");
						}
					}
				}
				criteria.add(s.toString());
			}

			if (keys.containsKey("tipAmministrativa")
					&& keys.get("tipAmministrativa") != null) {
				Integer[] tmplist = (Integer[]) keys.get("tipAmministrativa");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1) {
						String tipAmm = tmplist[0].toString();
						if (tipAmm.length() > 1
								&& tipAmm.substring((tipAmm.length() - 2),
										tipAmm.length()).equals("00")) {
							s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa0 AND "
									+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa0 + 100) ");
						} else {
							s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa0 ");
						}

					} else {
						String tipAmm = tmplist[0].toString();
						if (tipAmm.length() > 1
								&& tipAmm.substring((tipAmm.length() - 2),
										tipAmm.length()).equals("00")) {
							s.append("((b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa0 AND "
									+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa0 + 100) OR ");

						} else {
							s.append("(b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa0 OR ");
						}

						for (int i = 1; i < tmplist.length; i++) {
							String tipAmm_i = tmplist[i].toString();

							if (i == (tmplist.length - 1)) {/*
							 * Si tratta
							 * dell'ultima
							 * tipologia
							 * selezionata
							 */
								if (tipAmm_i.length() > 1
										&& tipAmm_i.substring(
												(tipAmm_i.length() - 2),
												tipAmm_i.length()).equals("00")) {
									s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa"
											+ i
											+ " AND "
											+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa"
											+ i + " + 100)) ");
								} else {
									s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa"
											+ i + ") ");
								}
							} else {
								if (tipAmm_i.length() > 1
										&& tipAmm_i.substring(
												(tipAmm_i.length() - 2),
												tipAmm_i.length()).equals("00")) {
									s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa"
											+ i
											+ " AND "
											+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa"
											+ i + " + 100) OR ");

								} else {
									s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa"
											+ i + " OR ");
								}
							}
						}
					}
					criteria.add(s.toString());
				}
			}

			if (keys.containsKey("tipFunzionale")
					&& keys.get("tipFunzionale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("tipFunzionale");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						s.append("b.tipologiaFunzionale = :tipologiaFunzionale0 ");
					else if (tmplist.length > 1)
						s.append("(b.tipologiaFunzionale = :tipologiaFunzionale0 OR ");

					for (int i = 1; i < tmplist.length; i++) {
						if (i == (tmplist.length - 1))
							s.append("b.tipologiaFunzionale = :tipologiaFunzionale"
									+ i + ")");
						else
							s.append("b.tipologiaFunzionale = :tipologiaFunzionale"
									+ i + " OR ");

					}

					criteria.add(s.toString());
				}
			}

			if (keys.containsKey("destSociale")
					&& keys.get("destSociale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("destSociale");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {

					sb.append(" LEFT JOIN b.destinazioniSocialis d ");

					if (tmplist.length == 1)
						s.append("d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == tmplist.length - 1)
								s.append(" d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + ")");
							else if (i == 0)
								s.append("(d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + " OR ");
							else
								s.append(" d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + " OR ");

						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("statoCatalogazione")
					&& keys.get("statoCatalogazione") != null) {
				Integer[] tmplist = (Integer[]) keys.get("statoCatalogazione");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					sb.append(" LEFT JOIN b.statoCatalogaziones s ");

					if (tmplist.length == 1)
						s.append("s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length - 1))
								s.append(" s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat"
										+ i + ")");
							else if (i == 0)
								s.append("(s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat"
										+ i + " OR ");
							else
								s.append(" s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat"
										+ i + " OR ");

						}
					}

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("cataloghiCollettivi")
					&& keys.get("cataloghiCollettivi") != null) {
				Integer[] tmplist = (Integer[]) keys.get("cataloghiCollettivi");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					sb.append(" LEFT JOIN b.partecipaCataloghiCollettiviMateriales cc ");

					if (tmplist.length == 1)
						s.append("cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length - 1))
								s.append(" cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll"
										+ i + ")");
							else if (i == 0)
								s.append("(cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll"
										+ i + " OR ");
							else
								s.append(" cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll"
										+ i + " OR ");
						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("sistemiBiblioteche")
					&& keys.get("sistemiBiblioteche") != null) {
				Integer[] tmplist = (Integer[]) keys.get("sistemiBiblioteche");
				StringBuffer s = new StringBuffer();

				if (tmplist != null && tmplist.length != 0) {

					if (tmplist.length == 1)
						s.append(" (:sistemi0 MEMBER OF b.sistemiBiblioteches) ");
					else {

						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length - 1))
								s.append(" :sistemi" + i
										+ " MEMBER OF b.sistemiBiblioteches)");
							else if (i == 0)
								s.append("(:sistemi"
										+ i
										+ " MEMBER OF b.sistemiBiblioteches OR ");
							else
								s.append(" :sistemi"
										+ i
										+ " MEMBER OF b.sistemiBiblioteches OR ");
						}

					}

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("codiciDewey")
					&& keys.get("codiciDewey") != null) {
				String[] tmplist = (String[]) keys.get("codiciDewey");
				StringBuffer s = new StringBuffer();

				if (tmplist != null && tmplist.length != 0) {

					if (tmplist.length == 1)
						s.append(" (:dewey0 MEMBER OF b.deweys) ");
					else {

						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length - 1))
								s.append(" :dewey" + i + " MEMBER OF b.deweys)");
							else if (i == 0)
								s.append("(:dewey" + i
										+ " MEMBER OF b.deweys OR ");
							else
								s.append(" :dewey" + i
										+ " MEMBER OF b.deweys OR ");

						}

					}

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("depositoLegale")
					&& keys.get("depositoLegale") != null) {
				if (keys.get("depositoLegale").equals("true")) {
					if (keys.containsKey("depositoLegaleTipi")
							&& keys.get("depositoLegaleTipi") != null) {
						/*
						 * E' stato specificato un deposito legale e anche uno o
						 * più tipi
						 */
						Integer[] tmplist = (Integer[]) keys
						.get("depositoLegaleTipi");
						StringBuffer s = new StringBuffer();

						if (tmplist.length != 0) {
							sb.append(" LEFT JOIN b.depositiLegalis dep ");

							if (tmplist.length == 1)
								s.append("dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep0");
							else {
								for (int i = 0; i < tmplist.length; i++) {
									if (i == (tmplist.length - 1))
										s.append(" dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep"
												+ i + ")");
									else if (i == 0)
										s.append("(dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep"
												+ i + " OR ");
									else
										s.append(" dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep"
												+ i + " OR ");
								}
							}

							criteria.add(s.toString());

						}

					} else {
						/*
						 * E' stato specificato solo il deposito legale, ma non
						 * il tipo
						 */
						StringBuffer s = new StringBuffer();
						s.append("b.depositiLegalis IS NOT EMPTY");

						criteria.add(s.toString());

					}

				} else if (keys.get("depositoLegale").equals("false")) {
					/*
					 * E' stato specificato il caso in cui NON ci sia alcun
					 * deposito legale
					 */
					StringBuffer s = new StringBuffer();
					s.append("b.depositiLegalis IS EMPTY");

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("edificioMonumentale")
					&& keys.get("edificioMonumentale") != null
					&& !keys.get("edificioMonumentale").equals("null")) {
				if (keys.get("edificioMonumentale").equals("null"))
					criteria.add("b.edificioMonumentale is null");
				else
					criteria.add("b.edificioMonumentale = "
							+ keys.get("edificioMonumentale"));

			}

			if (keys.containsKey("bibliotecheCorrelate")
					&& keys.get("bibliotecheCorrelate") != null) {
				StringBuffer s = new StringBuffer();
				if (keys.get("bibliotecheCorrelate").equals("true")) {
					s.append("b.bibliotecasFigli IS NOT EMPTY OR b.bibliotecaPadre IS NOT NULL");
					criteria.add(s.toString());
				} else {
					s.append("b.bibliotecasFigli IS EMPTY AND b.bibliotecaPadre IS NULL");
					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("denominazioneFondo")
					&& keys.get("denominazioneFondo") != null
					&& keys.containsKey("tipoRicercaDenominazioneFondo")
					&& keys.get("tipoRicercaDenominazioneFondo") != null) {

				StringBuffer s = new StringBuffer();

				sb.append(" LEFT JOIN b.fondiSpecialis f ");

				String[] splitted = ((String) keys.get("denominazioneFondo"))
				.split(" ");
				int type = ((Integer) keys.get("tipoRicercaDenominazioneFondo"))
				.intValue();

				if (type == 1) {
					/* Siamo nel caso 'Tutte le parole' */
					if (splitted.length == 1)
						s.append("f.denominazione LIKE :denfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length - 1)
								s.append(" f.denominazione LIKE :denfondi" + i
										+ ")");
							else if (i == 0)
								s.append("(f.denominazione LIKE :denfondi" + i
										+ " AND ");
							else
								s.append(" f.denominazione LIKE :denfondi" + i
										+ " AND ");

						}
					}

				} else if (type == 2) {
					/* Siamo nel caso 'Qualsiasi parola' */
					if (splitted.length == 1)
						s.append("f.denominazione LIKE :denfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length - 1)
								s.append(" f.denominazione LIKE :denfondi" + i
										+ ")");
							else if (i == 0)
								s.append("(f.denominazione LIKE :denfondi" + i
										+ " OR ");
							else
								s.append(" f.denominazione LIKE :denfondi" + i
										+ " OR ");

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					s.append("f.denominazione LIKE :denfondi");
				}

				criteria.add(s.toString());
			}

			if (keys.containsKey("descrizioneFondo")
					&& keys.get("descrizioneFondo") != null
					&& keys.containsKey("tipoRicercaDescrizioneFondo")
					&& keys.get("tipoRicercaDescrizioneFondo") != null) {

				StringBuffer s = new StringBuffer();

				/*
				 * Controllo se non è già stata inserita la left join per i
				 * fondi speciali
				 */
				if (!keys.containsKey("denominazioneFondo"))
					sb.append(" LEFT JOIN b.fondiSpecialis f ");

				String[] splitted = ((String) keys.get("descrizioneFondo"))
				.split(" ");
				int type = ((Integer) keys.get("tipoRicercaDescrizioneFondo"))
				.intValue();

				if (type == 1) {
					/* Siamo nel caso 'Tutte le parole' */
					if (splitted.length == 1)
						s.append("f.descrizione LIKE :descrfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length - 1)
								s.append(" f.descrizione LIKE :descrfondi" + i
										+ ")");
							else if (i == 0)
								s.append("(f.descrizione LIKE :descrfondi" + i
										+ " AND ");
							else
								s.append(" f.descrizione LIKE :descrfondi" + i
										+ " AND ");

						}
					}

				} else if (type == 2) {
					/* Siamo nel caso 'Qualsiasi parola' */
					if (splitted.length == 1)
						s.append("f.descrizione LIKE :descrfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length - 1)
								s.append(" f.descrizione LIKE :descrfondi" + i
										+ ")");
							else if (i == 0)
								s.append("(f.descrizione LIKE :descrfondi" + i
										+ " OR ");
							else
								s.append(" f.descrizione LIKE :descrfondi" + i
										+ " OR ");

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					s.append("f.descrizione LIKE :descrfondi");
				}

				criteria.add(s.toString());
			}

			if (keys.containsKey("patrimonioLibrario")
					&& keys.get("patrimonioLibrario") != null) {
				HashMap<Integer, Integer> tmplist = (HashMap<Integer, Integer>) keys
				.get("patrimonioLibrario");
				StringBuffer s = new StringBuffer();
				Set entries = tmplist.entrySet();
				Iterator it = entries.iterator();
				Map.Entry patr = null;

				if (tmplist.size() != 0) {
					sb.append(" LEFT JOIN b.patrimonios p ");
					int i = 0;

					while (it.hasNext()) {
						patr = (Map.Entry) it.next();

						if (tmplist.size() == 1) {
							if ((Integer) patr.getValue() == 1
									|| (Integer) patr.getValue() == 2)
								s.append("p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr0");
							else if ((Integer) patr.getValue() == 3)
								s.append("p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr0");
						} else {
							if (i == (tmplist.size() - 1)) {
								if ((Integer) patr.getValue() == 1
										|| (Integer) patr.getValue() == 2)
									s.append(" p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr"
											+ i + ")");
								else if ((Integer) patr.getValue() == 3)
									s.append(" p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr"
											+ i + ")");
							} else if (i == 0) {
								if ((Integer) patr.getValue() == 1
										|| (Integer) patr.getValue() == 2)
									s.append("(p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr"
											+ i + " OR ");
								else if ((Integer) patr.getValue() == 3)
									s.append("(p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr"
											+ i + " OR ");
							} else {
								if ((Integer) patr.getValue() == 1
										|| (Integer) patr.getValue() == 2)
									s.append(" p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr"
											+ i + " OR ");
								else if ((Integer) patr.getValue() == 3)
									s.append(" p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr"
											+ i + " OR ");
							}

							i++;
						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("prestitoLocale")
					&& keys.get("prestitoLocale") != null) {
				criteria.add("b.prestitoLocales IS NOT EMPTY");
			}

			if (keys.containsKey("prestitoNazionale")
					&& keys.get("prestitoNazionale") != null) {
				criteria.add("b.prestitoInterbiblioNazionale = "
						+ keys.get("prestitoNazionale"));
			}

			if (keys.containsKey("prestitoInternazionale")
					&& keys.get("prestitoInternazionale") != null) {
				criteria.add("b.prestitoInterbiblioInternazionale = "
						+ keys.get("prestitoInternazionale"));
			}

			if (keys.containsKey("dateAggiornamento")
					&& keys.get("dateAggiornamento") != null) {
				List<String> tmplist = (List<String>) keys
				.get("dateAggiornamento");

				if (tmplist != null && tmplist.size() != 0) {
					if (tmplist.get(0) != null && tmplist.get(1) != null) {
						criteria.add("(b.catalogazioneDataModifica >= :data0 AND b.catalogazioneDataModifica <= :data1)");
					} else {
						if (tmplist.get(0) != null) {
							/* Si tratta del campo DAL */
							criteria.add("b.catalogazioneDataModifica >= :data0");

						} else {
							/* Si tratta del campo AL */
							criteria.add("b.catalogazioneDataModifica <= :data1");
						}

					}
				}

			}

			if (keys.containsKey("utentiUltimaModifica")
					&& keys.get("utentiUltimaModifica") != null) {
				Integer[] tmplist = (Integer[]) keys
				.get("utentiUltimaModifica");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						criteria.add("b.utenteUltimaModifica.idUtenti = :user");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length - 1))
								s.append("b.utenteUltimaModifica.idUtenti = :user"
										+ i + ")");
							else if (i == 0)
								s.append("(b.utenteUltimaModifica.idUtenti = :user"
										+ i + " OR ");
							else
								s.append("b.utenteUltimaModifica.idUtenti = :user"
										+ i + " OR ");
						}

						criteria.add(s.toString());

					}
				}
			}

			criteria.add("b.statoBibliotecaWorkflow.idStato != 4");

			if ((keys != null && keys.size() > 0) || criteria.size() > 0) {
				sb.append("WHERE ");
			}

			for (int i = 0; i < criteria.size(); i++) {

				if (i > 0) {
					sb.append(" AND ");
				}
				sb.append(criteria.get(i));
			}

		}

		if (StringUtils.isNotBlank(orderByField)
				&& StringUtils.isNotBlank(orderByDir)) {
			sb.append(" ORDER BY ");
			if (orderByField.equals("codice")) {/* CODICE */
				sb.append("b.isilStato ");
				sb.append(orderByDir);
				sb.append(", b.isilProvincia ");
				sb.append(orderByDir);
				sb.append(", b.isilNumero ");
				sb.append(orderByDir);
			} else if (orderByField.equals("denominazione")) {/* DENOMINAZIONE */
				sb.append("b.").append(orderByField).append("Ufficiale ");
				sb.append(orderByDir);
			} else if (orderByField.equals("comuneDenominazione")) {/* COMUNE */
				sb.append("b.").append("comune.denominazione ");
				sb.append(orderByDir);
			} else if (orderByField.equals("utenteUltimaModifica")) {/*
			 * ULTIMA
			 * MODIFICA
			 */
				sb.append("u").append(".login ");
				sb.append(orderByDir);
			} else {/* INDIRIZZO */
				sb.append("b.").append(orderByField);
				sb.append(" ").append(orderByDir);
			}

		}

		Query q = em.createQuery(sb.toString());

		if (keys != null && keys.size() > 0) {

			if (keys.get("comune") != null) {

				id_comune = (Integer) keys.get("comune");
				Comune comune = new Comune();
				comune.setIdComune(id_comune);
				q.setParameter("comune", comune);
			}

			if (keys.get("province") != null) {
				id_province = (Integer[]) keys.get("province");

				for (int i = 0; i < id_province.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append("provincia");
					Provincia provincia = new Provincia();
					provincia.setIdProvincia(id_province[i]);
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), provincia);
				}
			}

			if (keys.get("regioni") != null) {
				id_regioni = (Integer[]) keys.get("regioni");

				for (int i = 0; i < id_regioni.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append("regione");
					Regione regione = new Regione();
					regione.setIdRegione(id_regioni[i]);
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), regione);
				}
			}

			if (keys.get("tipAmministrativa") != null) {

				Integer[] ids = (Integer[]) keys.get("tipAmministrativa");

				String s = "enteTipologiaAmministrativa";

				for (int i = 0; i < ids.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append(s);
					EnteTipologiaAmministrativa enteTipologiaAmministrativa = em
					.find(EnteTipologiaAmministrativa.class,
							ids[i].intValue());
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), enteTipologiaAmministrativa);
				}

			}

			if (keys.get("tipFunzionale") != null) {

				Integer[] ids = (Integer[]) keys.get("tipFunzionale");

				String s = "tipologiaFunzionale";

				for (int i = 0; i < ids.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append(s);
					TipologiaFunzionale tipologiaFunzionale = em.find(
							TipologiaFunzionale.class, ids[i].intValue());
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), tipologiaFunzionale);

				}

			}

			if (keys.get("destSociale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("destSociale");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("iddest" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("statoCatalogazione") != null) {
				Integer[] tmplist = (Integer[]) keys.get("statoCatalogazione");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("idstatcat" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("cataloghiCollettivi") != null) {
				Integer[] tmplist = (Integer[]) keys.get("cataloghiCollettivi");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("idcatcoll" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("sistemiBiblioteche") != null) {
				Integer[] tmplist = (Integer[]) keys.get("sistemiBiblioteche");

				for (int i = 0; i < tmplist.length; i++) {
					SistemiBiblioteche sistemi = em.find(
							SistemiBiblioteche.class, tmplist[i].intValue());
					q.setParameter("sistemi" + i, sistemi);
				}

			}

			if (keys.get("codiciDewey") != null) {
				String[] tmplist = (String[]) keys.get("codiciDewey");

				for (int i = 0; i < tmplist.length; i++) {
					Dewey dewey = em.find(Dewey.class, tmplist[i]);
					q.setParameter("dewey" + i, dewey);

				}

			}

			if (keys.get("depositoLegale") != null) {
				if (keys.get("depositoLegale").equals("true")
						&& keys.get("depositoLegaleTipi") != null) {
					Integer[] tmplist = (Integer[]) keys
					.get("depositoLegaleTipi");

					if (tmplist.length != 0) {
						for (int i = 0; i < tmplist.length; i++) {
							q.setParameter("iddep" + i, tmplist[i]);

						}
					}

				}

			}

			if (keys.get("denominazioneFondo") != null) {
				String[] splitted = ((String) keys.get("denominazioneFondo"))
				.split(" ");
				int type = ((Integer) keys.get("tipoRicercaDenominazioneFondo"))
				.intValue();

				if (type == 1 || type == 2) {
					/* Siamo nel caso 'Tutte le parole' o 'Qualsiasi parola' */
					if (splitted.length != 0) {
						for (int i = 0; i < splitted.length; i++) {
							String parameter = "%".concat(splitted[i]).concat(
							"%");
							q.setParameter("denfondi" + i, parameter);

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					String parameter = "%".concat(
							(String) keys.get("denominazioneFondo"))
							.concat("%");
					q.setParameter("denfondi", parameter);
				}

			}

			if (keys.get("descrizioneFondo") != null) {
				String[] splitted = ((String) keys.get("descrizioneFondo"))
				.split(" ");
				int type = ((Integer) keys.get("tipoRicercaDescrizioneFondo"))
				.intValue();

				if (type == 1 || type == 2) {
					/* Siamo nel caso 'Tutte le parole' o 'Qualsiasi parola' */
					if (splitted.length != 0) {
						for (int i = 0; i < splitted.length; i++) {
							String parameter = "%".concat(splitted[i]).concat(
							"%");
							q.setParameter("descrfondi" + i, parameter);

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					String parameter = "%".concat(
							(String) keys.get("descrizioneFondo")).concat("%");
					q.setParameter("descrfondi", parameter);
				}

			}

			if (keys.get("patrimonioLibrario") != null) {
				HashMap<Integer, Integer> tmplist = (HashMap<Integer, Integer>) keys.get("patrimonioLibrario");
				Set entries = null;
				Map.Entry patr = null;

				if (tmplist.size() != 0) {
					entries = tmplist.entrySet();
					Iterator it = entries.iterator();
					int i = 0;

					while (it.hasNext()) {
						patr = (Map.Entry) it.next();
						q.setParameter("idpatr" + i, (Integer) patr.getKey());
						i++;
					}
				}

			}

			if (keys.get("dateAggiornamento") != null) {
				List<String> tmplist = (List<String>) keys.get("dateAggiornamento");

				if (tmplist != null && tmplist.size() != 0) {

					if (tmplist.get(0) != null) {
						/* Campo DAL valorizzato */
						Calendar d0 = new GregorianCalendar();
						d0.setTimeInMillis((Long.valueOf(tmplist.get(0))).longValue());

						q.setParameter("data0", d0.getTime());
					}

					if (tmplist.get(1) != null) {
						/* Campo AL valorizzato */
						Calendar d1 = new GregorianCalendar();
						d1.setTimeInMillis((Long.valueOf(tmplist.get(1))).longValue());

						q.setParameter("data1", d1.getTime());

					}
				}

			}

			if (keys.get("utentiUltimaModifica") != null) {
				Integer[] tmplist = (Integer[]) keys.get("utentiUltimaModifica");

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						q.setParameter("user", tmplist[0]);
					else {
						for (int i = 0; i < tmplist.length; i++)
							q.setParameter("user" + i, tmplist[i]);

					}
				}
			}

		}

		q.setFirstResult(offset);
		if (rows != -1)
			q.setMaxResults(rows);

		List<Biblioteca> listaBiblioteche = q.getResultList();

		return listaBiblioteche;

	}

	@Override
	@Transactional
	public int countAllBibliotechePerReport(HashMap<String, Object> keys) {

		Integer[] id_regioni = null;
		Integer[] id_province = null;
		int id_comune = 0;

		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer(" SELECT COUNT(distinct b.idBiblioteca) FROM Biblioteca b ");

		if (keys != null && keys.size() > 0) {

			if (keys.containsKey("comune") && (keys.get("comune") != null)) {
				criteria.add("b.comune = :comune");
			}
			if (keys.containsKey("province") && (keys.get("province") != null)) {
				StringBuffer s = new StringBuffer();
				if (((Integer[]) keys.get("province")).length > 0) {
					if (((Integer[]) keys.get("province")).length == 1) {
						s.append("b.comune.provincia = :provincia0");
					} else {
						for (int i = 0; i < ((Integer[]) keys.get("province")).length; i++) {
							if (i == 0)
								s.append("(b.comune.provincia = :provincia0 OR ");
							else if (i == (((Integer[]) keys.get("province")).length - 1))
								s.append("b.comune.provincia = :provincia" + i
										+ ")");
							else
								s.append("b.comune.provincia = :provincia" + i
										+ " OR ");
						}
					}
				}
				criteria.add(s.toString());

			}

			if (keys.containsKey("regioni") && (keys.get("regioni") != null)) {
				StringBuffer s = new StringBuffer();
				if (((Integer[]) keys.get("regioni")).length > 0) {
					if (((Integer[]) keys.get("regioni")).length == 1) {
						s.append("b.comune.provincia.regione = :regione0");
					}
					else {
						for (int i = 0; i < ((Integer[]) keys.get("regioni")).length; i++) {
							if (i == 0)
								s.append("(b.comune.provincia.regione = :regione0 OR ");
							else if (i == (((Integer[]) keys.get("regioni")).length-1))
								s.append("b.comune.provincia.regione = :regione"+i+")");
							else s.append("b.comune.provincia.regione = :regione"+i+" OR ");
						}
					}
				}
				criteria.add(s.toString());

			}

			if (keys.containsKey("tipAmministrativa")
					&& keys.get("tipAmministrativa") != null) {
				Integer[] tmplist = (Integer[]) keys.get("tipAmministrativa");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1) {
						String tipAmm = tmplist[0].toString();
						if (tipAmm.length() > 1
								&& tipAmm.substring((tipAmm.length() - 2),
										tipAmm.length()).equals("00")) {
							s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa0 AND "
									+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa0 + 100) ");
						} else {
							s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa0 ");
						}

					} else {
						String tipAmm = tmplist[0].toString();
						if (tipAmm.length() > 1
								&& tipAmm.substring((tipAmm.length() - 2),
										tipAmm.length()).equals("00")) {
							s.append("((b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa0 AND "
									+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa0 + 100) OR ");
						} else {
							s.append("(b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa0 OR ");
						}

						for (int i = 1; i < tmplist.length; i++) {
							String tipAmm_i = tmplist[i].toString();

							if (i == (tmplist.length - 1)) {/*
							 * Si tratta
							 * dell'ultima
							 * tipologia
							 * selezionata
							 */
								if (tipAmm_i.length() > 1
										&& tipAmm_i.substring(
												(tipAmm_i.length() - 2),
												tipAmm_i.length()).equals("00")) {
									s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa"
											+ i
											+ " AND "
											+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa"
											+ i + " + 100)) ");
								} else {
									s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa"
											+ i + ") ");
								}
							} else {
								if (tipAmm_i.length() > 1
										&& tipAmm_i.substring(
												(tipAmm_i.length() - 2),
												tipAmm_i.length()).equals("00")) {
									s.append("(b.ente.enteTipologiaAmministrativa >= :enteTipologiaAmministrativa"
											+ i
											+ " AND "
											+ "b.ente.enteTipologiaAmministrativa < :enteTipologiaAmministrativa"
											+ i + " + 100) OR ");

								} else {
									s.append("b.ente.enteTipologiaAmministrativa = :enteTipologiaAmministrativa"
											+ i + " OR ");
								}
							}
						}
					}
					criteria.add(s.toString());
				}
			}

			if (keys.containsKey("tipFunzionale")
					&& keys.get("tipFunzionale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("tipFunzionale");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						s.append("b.tipologiaFunzionale = :tipologiaFunzionale0 ");
					else if (tmplist.length > 1)
						s.append("b.tipologiaFunzionale = :tipologiaFunzionale0 OR ");

					for (int i = 1; i < tmplist.length; i++) {
						if (i == (tmplist.length - 1))
							s.append("b.tipologiaFunzionale = :tipologiaFunzionale"
									+ i + ") ");
						else
							s.append("b.tipologiaFunzionale = :tipologiaFunzionale"
									+ i + " OR ");

					}

					criteria.add(s.toString());
				}
			}

			if (keys.containsKey("destSociale")
					&& keys.get("destSociale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("destSociale");
				StringBuffer s = new StringBuffer();

				sb.append("LEFT JOIN b.destinazioniSocialis d ");
				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						s.append("d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == tmplist.length - 1)
								s.append(" d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + ")");
							else if (i == 0)
								s.append("(d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + " OR ");
							else
								s.append(" d.destinazioniSocialiTipo.idDestinazioniSociali = :iddest"
										+ i + " OR ");

						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("statoCatalogazione") && keys.get("statoCatalogazione") != null) {
				Integer[] tmplist = (Integer[]) keys.get("statoCatalogazione");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					sb.append(" LEFT JOIN b.statoCatalogaziones s ");

					if (tmplist.length == 1)
						s.append("s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length-1))
								s.append(" s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat" + i + ")");
							else if (i == 0)
								s.append("(s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat" + i + " OR ");
							else
								s.append(" s.statoCatalogazioneTipo.idStatoCatalogazioneTipo = :idstatcat" + i + " OR ");

						}
					}

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("cataloghiCollettivi") && keys.get("cataloghiCollettivi") != null) {
				Integer[] tmplist = (Integer[]) keys.get("cataloghiCollettivi");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					sb.append(" LEFT JOIN b.partecipaCataloghiCollettiviMateriales cc ");

					if (tmplist.length == 1)
						s.append("cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll0");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length-1))
								s.append(" cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll" + i + ")");
							else if (i == 0)
								s.append("(cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll" + i + " OR ");
							else
								s.append(" cc.cataloghiCollettivi.idCataloghiCollettivi = :idcatcoll" + i + " OR ");
						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("sistemiBiblioteche") && keys.get("sistemiBiblioteche") != null) {
				Integer[] tmplist = (Integer[]) keys.get("sistemiBiblioteche");
				StringBuffer s = new StringBuffer();

				if (tmplist != null && tmplist.length != 0) {

					if (tmplist.length == 1)
						s.append(" (:sistemi0 MEMBER OF b.sistemiBiblioteches) ");
					else {

						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length-1))
								s.append(" :sistemi" + i + " MEMBER OF b.sistemiBiblioteches)");
							else if (i == 0)
								s.append("(:sistemi" + i + " MEMBER OF b.sistemiBiblioteches OR ");
							else
								s.append(" :sistemi" + i + " MEMBER OF b.sistemiBiblioteches OR ");
						}

					}
					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("codiciDewey") && keys.get("codiciDewey") != null) {
				String[] tmplist = (String[]) keys.get("codiciDewey");
				StringBuffer s = new StringBuffer();

				if (tmplist != null && tmplist.length != 0) {

					if (tmplist.length == 1)
						s.append(" (:dewey0 MEMBER OF b.deweys) ");
					else {

						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length-1))
								s.append(" :dewey" + i + " MEMBER OF b.deweys)");
							else if (i == 0)
								s.append("(:dewey" + i + " MEMBER OF b.deweys OR ");
							else
								s.append(" :dewey" + i + " MEMBER OF b.deweys OR ");

						}

					}

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("depositoLegale") && keys.get("depositoLegale") != null) {
				if (keys.get("depositoLegale").equals("true")) {
					if (keys.containsKey("depositoLegaleTipi") && keys.get("depositoLegaleTipi") != null) {
						/* E' stato specificato un deposito legale e anche uno o più tipi */
						Integer[] tmplist = (Integer[]) keys.get("depositoLegaleTipi");
						StringBuffer s = new StringBuffer();

						if (tmplist.length != 0) {
							sb.append(" LEFT JOIN b.depositiLegalis dep ");

							if (tmplist.length == 1)
								s.append("dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep0");
							else {
								for (int i = 0; i < tmplist.length; i++) {
									if (i == (tmplist.length-1))
										s.append(" dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep" + i + ")");
									else if (i == 0)
										s.append("(dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep" + i + " OR ");
									else
										s.append(" dep.depositiLegaliTipo.idDepositiLegaliTipo = :iddep" + i + " OR ");
								}
							}

							criteria.add(s.toString());

						}

					} else {
						/* E' stato specificato solo il deposito legale, ma non il tipo */
						StringBuffer s = new StringBuffer();
						s.append("b.depositiLegalis IS NOT EMPTY");

						criteria.add(s.toString());

					}

				} else if (keys.get("depositoLegale").equals("false")) {
					/* E' stato specificato il caso in cui NON ci sia alcun deposito legale */
					StringBuffer s = new StringBuffer();
					s.append("b.depositiLegalis IS EMPTY");

					criteria.add(s.toString());

				}

			}

			if (keys.containsKey("edificioMonumentale") && keys.get("edificioMonumentale") != null) {
				if (keys.get("edificioMonumentale").equals("null"))
					criteria.add("b.edificioMonumentale is null");
				else
					criteria.add("b.edificioMonumentale = " + keys.get("edificioMonumentale"));

			}

			if (keys.containsKey("bibliotecheCorrelate") && keys.get("bibliotecheCorrelate") != null) {
				StringBuffer s = new StringBuffer();
				if (keys.get("bibliotecheCorrelate").equals("true")) {
					s.append("(b.bibliotecasFigli IS NOT EMPTY OR b.bibliotecaPadre IS NOT NULL)");
					criteria.add(s.toString());
				} else {
					s.append("(b.bibliotecasFigli IS EMPTY AND b.bibliotecaPadre IS NULL)");
					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("denominazioneFondo") && keys.get("denominazioneFondo") != null && keys.containsKey("tipoRicercaDenominazioneFondo") && keys.get("tipoRicercaDenominazioneFondo") != null) {

				StringBuffer s = new StringBuffer();

				sb.append(" LEFT JOIN b.fondiSpecialis f ");

				String[] splitted = ((String) keys.get("denominazioneFondo")).split(" ");
				int type = ((Integer) keys.get("tipoRicercaDenominazioneFondo")).intValue();

				if (type == 1) {
					/* Siamo nel caso 'Tutte le parole' */
					if (splitted.length == 1)
						s.append("f.denominazione LIKE :denfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length-1)
								s.append(" f.denominazione LIKE :denfondi" + i + ")");
							else if (i == 0)
								s.append("(f.denominazione LIKE :denfondi" + i + " AND ");
							else
								s.append(" f.denominazione LIKE :denfondi" + i + " AND ");

						}
					}

				} else if (type == 2) {
					/* Siamo nel caso 'Qualsiasi parola' */
					if (splitted.length == 1)
						s.append("f.denominazione LIKE :denfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length-1)
								s.append(" f.denominazione LIKE :denfondi" + i + ")");
							else if (i == 0)
								s.append("(f.denominazione LIKE :denfondi" + i + " OR ");
							else
								s.append(" f.denominazione LIKE :denfondi" + i + " OR ");

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					s.append("f.denominazione LIKE :denfondi");
				}

				criteria.add(s.toString());
			}

			if (keys.containsKey("descrizioneFondo") && keys.get("descrizioneFondo") != null && keys.containsKey("tipoRicercaDescrizioneFondo") && keys.get("tipoRicercaDescrizioneFondo") != null) {

				StringBuffer s = new StringBuffer();

				/*
				 * Controllo se non è già stata inserita la left join per i
				 * fondi speciali
				 */
				if (!keys.containsKey("denominazioneFondo"))
					sb.append(" LEFT JOIN b.fondiSpecialis f ");

				String[] splitted = ((String) keys.get("descrizioneFondo")).split(" ");
				int type = ((Integer) keys.get("tipoRicercaDescrizioneFondo")).intValue();

				if (type == 1) {
					/* Siamo nel caso 'Tutte le parole' */
					if (splitted.length == 1)
						s.append("f.descrizione LIKE :descrfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length-1)
								s.append(" f.descrizione LIKE :descrfondi" + i + ")");
							else if (i == 0)
								s.append("(f.descrizione LIKE :descrfondi" + i + " AND ");
							else
								s.append(" f.descrizione LIKE :descrfondi" + i + " AND ");

						}
					}

				} else if (type == 2) {
					/* Siamo nel caso 'Qualsiasi parola' */
					if (splitted.length == 1)
						s.append("f.descrizione LIKE :descrfondi0");
					else {

						for (int i = 0; i < splitted.length; i++) {
							if (i == splitted.length-1)
								s.append(" f.descrizione LIKE :descrfondi" + i + ")");
							else if (i == 0)
								s.append("(f.descrizione LIKE :descrfondi" + i + " OR ");
							else
								s.append(" f.descrizione LIKE :descrfondi" + i + " OR ");

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					s.append("f.descrizione LIKE :descrfondi");
				}

				criteria.add(s.toString());
			}

			if (keys.containsKey("patrimonioLibrario") && keys.get("patrimonioLibrario") != null) {
				HashMap<Integer, Integer> tmplist = (HashMap<Integer, Integer>) keys.get("patrimonioLibrario");
				StringBuffer s = new StringBuffer();
				Set entries = tmplist.entrySet();
				Iterator it = entries.iterator();
				Map.Entry patr = null;

				if (tmplist.size() != 0) {
					sb.append(" LEFT JOIN b.patrimonios p ");
					int i = 0;

					while (it.hasNext()) {
						patr = (Map.Entry) it.next();

						if (tmplist.size() == 1) {
							if ((Integer) patr.getValue() == 1 || (Integer) patr.getValue() == 2)
								s.append("p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr0");
							else if ((Integer) patr.getValue() == 3)
								s.append("p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr0");
						} else {
							if (i == (tmplist.size()-1)) {
								if ((Integer) patr.getValue() == 1 || (Integer) patr.getValue() == 2)
									s.append(" p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr" + i + ")");
								else if ((Integer) patr.getValue() == 3)
									s.append(" p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr" + i + ")");
							} else if (i == 0) {
								if ((Integer) patr.getValue() == 1 || (Integer) patr.getValue() == 2)
									s.append("(p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr" + i + " OR ");
								else if ((Integer) patr.getValue() == 3)
									s.append("(p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr" + i + " OR ");
							} else {
								if ((Integer) patr.getValue() == 1 || (Integer) patr.getValue() == 2)
									s.append(" p.patrimonioSpecializzazione.patrimonioSpecializzazioneCategoria.idPatrimonioSpecializzazioneCategoria = :idpatr" + i + " OR ");
								else if ((Integer) patr.getValue() == 3)
									s.append(" p.patrimonioSpecializzazione.idPatrimonioSpecializzazione = :idpatr" + i + " OR ");
							}

							i++;
						}
					}

					criteria.add(s.toString());
				}

			}

			if (keys.containsKey("prestitoLocale") && keys.get("prestitoLocale") != null) {
				criteria.add("b.prestitoLocales IS NOT EMPTY");
			}

			if (keys.containsKey("prestitoNazionale") && keys.get("prestitoNazionale") != null) {
				criteria.add("b.prestitoInterbiblioNazionale = " + keys.get("prestitoNazionale"));
			}

			if (keys.containsKey("prestitoInternazionale") && keys.get("prestitoInternazionale") != null) {
				criteria.add("b.prestitoInterbiblioInternazionale = " + keys.get("prestitoInternazionale"));
			}

			if (keys.containsKey("dateAggiornamento") && keys.get("dateAggiornamento") != null) {
				List<String> tmplist = (List<String>) keys.get("dateAggiornamento");

				if (tmplist != null && tmplist.size() != 0) {
					if (tmplist.get(0) != null && tmplist.get(1) != null) {
						criteria.add("(b.catalogazioneDataModifica >= :data0 AND b.catalogazioneDataModifica <= :data1)");

					} else {
						if (tmplist.get(0) != null) {
							/* Si tratta del campo DAL */
							criteria.add("b.catalogazioneDataModifica >= :data0");

						} else {
							/* Si tratta del campo AL */
							criteria.add("b.catalogazioneDataModifica <= :data1");
						}
					}
				}

			}

			if (keys.containsKey("utentiUltimaModifica") && keys.get("utentiUltimaModifica") != null) {
				Integer[] tmplist = (Integer[]) keys.get("utentiUltimaModifica");
				StringBuffer s = new StringBuffer();

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						criteria.add("b.utenteUltimaModifica.idUtenti = :user");
					else {
						for (int i = 0; i < tmplist.length; i++) {
							if (i == (tmplist.length-1))
								s.append("b.utenteUltimaModifica.idUtenti = :user" + i + ")");
							else if (i == 0)
								s.append("(b.utenteUltimaModifica.idUtenti = :user" + i + " OR ");
							else
								s.append("b.utenteUltimaModifica.idUtenti = :user" + i + " OR ");
						}

						criteria.add(s.toString());

					}
				}
			}

			criteria.add("b.statoBibliotecaWorkflow.idStato != 4");

			if ((keys != null && keys.size() > 0) || criteria.size() > 0) {
				sb.append("WHERE ");
			}
			// else return 0;

			for (int i = 0; i < criteria.size(); i++) {

				if (i > 0) {
					sb.append(" AND ");
				}
				sb.append(criteria.get(i));
			}

		}
		Query q = em.createQuery(sb.toString());

		if (keys != null && keys.size() > 0) {
			if (keys.get("comune") != null) {
				id_comune = (Integer) keys.get("comune");
				Comune comune = new Comune();
				comune.setIdComune(id_comune);
				q.setParameter("comune", comune);
			}

			if (keys.get("province") != null) {
				id_province = (Integer[]) keys.get("province");

				for (int i = 0; i < id_province.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append("provincia");
					Provincia provincia = new Provincia();
					provincia.setIdProvincia(id_province[i]);
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), provincia);
				}
			}

			if (keys.get("regioni") != null) {
				id_regioni = (Integer[]) keys.get("regioni");

				for (int i = 0; i < id_regioni.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append("regione");
					Regione regione = new Regione();
					regione.setIdRegione(id_regioni[i]);
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), regione);
				}
			}

			if (keys.get("tipAmministrativa") != null) {

				Integer[] ids = (Integer[]) keys.get("tipAmministrativa");

				String s = "enteTipologiaAmministrativa";

				for (int i = 0; i < ids.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append(s);
					EnteTipologiaAmministrativa enteTipologiaAmministrativa = em.find(EnteTipologiaAmministrativa.class, ids[i].intValue());
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), enteTipologiaAmministrativa);
				}

			}

			if (keys.get("tipFunzionale") != null) {

				Integer[] ids = (Integer[]) keys.get("tipFunzionale");

				String s = "tipologiaFunzionale";

				for (int i = 0; i < ids.length; i++) {
					StringBuffer tmp = new StringBuffer();
					tmp.append(s);
					TipologiaFunzionale tipologiaFunzionale = em.find(TipologiaFunzionale.class, ids[i].intValue());
					Integer n = new Integer(i);
					tmp.append(n.toString());
					q.setParameter(tmp.toString(), tipologiaFunzionale);

				}

			}

			if (keys.get("destSociale") != null) {
				Integer[] tmplist = (Integer[]) keys.get("destSociale");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("iddest" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("statoCatalogazione") != null) {
				Integer[] tmplist = (Integer[]) keys.get("statoCatalogazione");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("idstatcat" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("cataloghiCollettivi") != null) {
				Integer[] tmplist = (Integer[]) keys.get("cataloghiCollettivi");

				if (tmplist.length != 0) {
					for (int i = 0; i < tmplist.length; i++) {
						q.setParameter("idcatcoll" + i, tmplist[i]);

					}
				}

			}

			if (keys.get("sistemiBiblioteche") != null) {
				Integer[] tmplist = (Integer[]) keys.get("sistemiBiblioteche");

				for (int i = 0; i < tmplist.length; i++) {
					SistemiBiblioteche sistemi = em.find(SistemiBiblioteche.class, tmplist[i].intValue());
					q.setParameter("sistemi" + i, sistemi);
				}

			}

			if (keys.get("codiciDewey") != null) {
				String[] tmplist = (String[]) keys.get("codiciDewey");

				for (int i = 0; i < tmplist.length; i++) {
					Dewey dewey = em.find(Dewey.class, tmplist[i]);
					q.setParameter("dewey" + i, dewey);

				}

			}

			if (keys.get("depositoLegale") != null) {
				if (keys.get("depositoLegale").equals("true") && keys.get("depositoLegaleTipi") != null) {
					Integer[] tmplist = (Integer[]) keys.get("depositoLegaleTipi");

					if (tmplist.length != 0) {
						for (int i = 0; i < tmplist.length; i++) {
							q.setParameter("iddep" + i, tmplist[i]);

						}
					}

				}

			}

			if (keys.get("denominazioneFondo") != null) {
				String[] splitted = ((String) keys.get("denominazioneFondo")).split(" ");
				int type = ((Integer) keys.get("tipoRicercaDenominazioneFondo")).intValue();

				if (type == 1 || type == 2) {
					/* Siamo nel caso 'Tutte le parole' o 'Qualsiasi parola' */
					if (splitted.length != 0) {
						for (int i = 0; i < splitted.length; i++) {
							String parameter = "%".concat(splitted[i]).concat("%");
							q.setParameter("denfondi" + i, parameter);

						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					String parameter = "%".concat((String) keys.get("denominazioneFondo")).concat("%");
					q.setParameter("denfondi", parameter);
				}

			}

			if (keys.get("descrizioneFondo") != null) {
				String[] splitted = ((String) keys.get("descrizioneFondo")).split(" ");
				int type = ((Integer) keys.get("tipoRicercaDescrizioneFondo")).intValue();

				if (type == 1 || type == 2) {
					/* Siamo nel caso 'Tutte le parole' o 'Qualsiasi parola' */
					if (splitted.length != 0) {
						for (int i = 0; i < splitted.length; i++) {
							String parameter = "%".concat(splitted[i]).concat("%");
							q.setParameter("descrfondi" + i, parameter);
						}
					}
				} else if (type == 3) {
					/* Siamo nel caso 'Frase esatta' */
					String parameter = "%".concat((String) keys.get("descrizioneFondo")).concat("%");
					q.setParameter("descrfondi", parameter);
				}

			}

			if (keys.get("patrimonioLibrario") != null) {
				HashMap<Integer, Integer> tmplist = (HashMap<Integer, Integer>) keys.get("patrimonioLibrario");
				Set entries = null;
				Map.Entry patr = null;

				if (tmplist.size() != 0) {
					entries = tmplist.entrySet();
					Iterator it = entries.iterator();
					int i = 0;

					while (it.hasNext()) {
						patr = (Map.Entry) it.next();
						q.setParameter("idpatr" + i, (Integer) patr.getKey());
						i++;
					}
				}

			}

			if (keys.get("dateAggiornamento") != null) {
				List<String> tmplist = (List<String>) keys.get("dateAggiornamento");

				if (tmplist != null && tmplist.size() != 0) {

					if (tmplist.get(0) != null) {
						/* Campo DAL valorizzato */
						Calendar d0 = new GregorianCalendar();
						d0.setTimeInMillis((Long.valueOf(tmplist.get(0))).longValue());

						q.setParameter("data0", d0.getTime());
					}

					if (tmplist.get(1) != null) {
						/* Campo AL valorizzato */
						Calendar d1 = new GregorianCalendar();
						d1.setTimeInMillis((Long.valueOf(tmplist.get(1))).longValue());

						q.setParameter("data1", d1.getTime());

					}
				}

			}

			if (keys.get("utentiUltimaModifica") != null) {
				Integer[] tmplist = (Integer[]) keys.get("utentiUltimaModifica");

				if (tmplist.length != 0) {
					if (tmplist.length == 1)
						q.setParameter("user", tmplist[0]);
					else {
						for (int i = 0; i < tmplist.length; i++)
							q.setParameter("user" + i, tmplist[i]);

					}
				}
			}

		}

		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();

	}

	@Override
	@Transactional
	public void setServizioBibliograficoInternoEsterno(int id_biblioteca, Boolean hasAttivoInformazioniBibliografiche, Boolean hasServizioBibliograficoInterno,
			Boolean hasServizioBibliograficoEsterno) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setAttivoInformazioniBibliografiche(hasAttivoInformazioniBibliografiche);
		biblioteca.setGestisceServizioBibliograficoInterno(hasServizioBibliograficoInterno);
		biblioteca.setGestisceServizioBibliograficoEsterno(hasServizioBibliograficoEsterno);
		if (BooleanUtils.isFalse(hasServizioBibliograficoEsterno)) {
			/* Se non è presente il servizio esterno, annullo tutte le modalità salvate per la biblioteca */
			biblioteca.getServiziInformazioniBibliograficheModalitas().clear();
		}
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<ServiziInformazioniBibliograficheModalita> getModalitaComunicazioniBibliograficheByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<ServiziInformazioniBibliograficheModalita> bibliograficheModalitas = biblioteca.getServiziInformazioniBibliograficheModalitas();
		Iterator<ServiziInformazioniBibliograficheModalita> it = bibliograficheModalitas.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			ServiziInformazioniBibliograficheModalita serviziInformazioniBibliograficheModalita = (ServiziInformazioniBibliograficheModalita) it.next();
		}

		return bibliograficheModalitas;
	}

	@Override
	@Transactional
	public void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, Integer idRecord) throws DuplicateEntryException {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		ServiziInformazioniBibliograficheModalita newModalita = em.find(ServiziInformazioniBibliograficheModalita.class, idRecord);

		List<ServiziInformazioniBibliograficheModalita> bibliograficheModalitas = biblioteca.getServiziInformazioniBibliograficheModalitas();
		Iterator<ServiziInformazioniBibliograficheModalita> it = bibliograficheModalitas.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			ServiziInformazioniBibliograficheModalita serviziInformazioniBibliograficheModalita = (ServiziInformazioniBibliograficheModalita) it.next();
			if (serviziInformazioniBibliograficheModalita.getIdServiziInformazioniBibliograficheModalita().intValue() == idRecord.intValue()) {
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
		}

		bibliograficheModalitas.add(newModalita);
		biblioteca.setServiziInformazioniBibliograficheModalitas(bibliograficheModalitas);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, Integer idRecord) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		Integer tmpIndex = null;
		List<ServiziInformazioniBibliograficheModalita> bibliograficheModalitas = biblioteca.getServiziInformazioniBibliograficheModalitas();
		Iterator<ServiziInformazioniBibliograficheModalita> it = bibliograficheModalitas.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			ServiziInformazioniBibliograficheModalita serviziInformazioniBibliograficheModalita = (ServiziInformazioniBibliograficheModalita) it.next();
			if (serviziInformazioniBibliograficheModalita.getIdServiziInformazioniBibliograficheModalita().intValue() == idRecord.intValue())
				tmpIndex = bibliograficheModalitas.indexOf(serviziInformazioniBibliograficheModalita);
		}
		if (tmpIndex != null)
			bibliograficheModalitas.remove(bibliograficheModalitas.get(tmpIndex));

		biblioteca.setServiziInformazioniBibliograficheModalitas(bibliograficheModalitas);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<SezioniSpeciali> getSezioniSpecialiByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<SezioniSpeciali> sezioniSpecialis = biblioteca.getSezioniSpecialis();
		Iterator<SezioniSpeciali> it = sezioniSpecialis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			SezioniSpeciali sezioniSpeciali = (SezioniSpeciali) it.next();
		}
		return sezioniSpecialis;
	}

	@Override
	@Transactional
	public void removeSezioniSpeciali(int id_biblioteca, int idRemove) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		Integer tmpIndex = null;
		List<SezioniSpeciali> sezioniSpecialis = biblioteca.getSezioniSpecialis();
		Iterator<SezioniSpeciali> it = sezioniSpecialis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			SezioniSpeciali sezioniSpeciali = (SezioniSpeciali) it.next();
			if (sezioniSpeciali.getIdSezioniSpeciali() == idRemove)
				tmpIndex = sezioniSpecialis.indexOf(sezioniSpeciali);
		}
		if (tmpIndex != null)
			sezioniSpecialis.remove(sezioniSpecialis.get(tmpIndex));

		biblioteca.setSezioniSpecialis(sezioniSpecialis);

		em.persist(biblioteca);

	}

	@Override
	@Transactional
	public void addSezioniSpeciali(int id_biblioteca, Integer idRecord) throws DuplicateEntryException {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		SezioniSpeciali newSezioneSpeciale = em.find(SezioniSpeciali.class, idRecord);

		List<SezioniSpeciali> sezioniSpecialis = biblioteca.getSezioniSpecialis();
		Iterator<SezioniSpeciali> it = sezioniSpecialis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			SezioniSpeciali sezioniSpeciali = (SezioniSpeciali) it.next();
			if (sezioniSpeciali.getIdSezioniSpeciali().intValue() == idRecord.intValue())
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
		}
		sezioniSpecialis.add(newSezioneSpeciale);

		biblioteca.setSezioniSpecialis(sezioniSpecialis);

		em.merge(biblioteca);

	}

	@Override
	@Transactional
	public void updateModalitaAccessoInternet(int id_biblioteca, Boolean hasAttivoAccesso, Boolean hasAccessoPagamento, Boolean hasAccessoTempo, Boolean hasAccessoProxy) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setAttivoAccessoInternet(hasAttivoAccesso);
		biblioteca.setAccessoInternetPagamento(hasAccessoPagamento);
		biblioteca.setAccessoInternetTempo(hasAccessoTempo);
		biblioteca.setAccessoInternetProxy(hasAccessoProxy);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<PrestitoLocale> getPrestitiLocaliByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PrestitoLocale> prestitoLocales = biblioteca.getPrestitoLocales();
		Iterator<PrestitoLocale> it = prestitoLocales.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy prestitoLocale
			PrestitoLocale prestitoLocale = (PrestitoLocale) it.next();

		}
		return prestitoLocales;
	}

	@Override
	@Transactional
	public PrestitoLocale addPrestitoLocaleToBiblio(int id_biblioteca, Integer idPrestito, Integer durataGiorni, Boolean automatizzato,
			boolean modifica) {

		if (modifica) {
			PrestitoLocale toUpdate = em.find(PrestitoLocale.class, idPrestito);
			toUpdate.setDurataGiorni(durataGiorni);
			toUpdate.setAutomatizzato(automatizzato);
			em.merge(toUpdate);
			return toUpdate;
		} else {

			Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
			List<PrestitoLocale> prestitoLocales = biblioteca.getPrestitoLocales();
			Iterator<PrestitoLocale> it = prestitoLocales.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy prestitoLocale
				PrestitoLocale prestitoLocale = (PrestitoLocale) it.next();
			}

			PrestitoLocale toSave = new PrestitoLocale();
			toSave.setDurataGiorni(durataGiorni);
			toSave.setAutomatizzato(automatizzato);
			toSave.setBiblioteca(biblioteca);
			em.persist(toSave);

			prestitoLocales.add(toSave);

			biblioteca.setPrestitoLocales(prestitoLocales);

			em.merge(biblioteca);
			return toSave;
		}

	}

	@Transactional
	public void removeAllPrestitoLocaleEntriesFromHasMaterialeEsclusoAndUtentiAmmessi(Integer id_removePrestito) {
		PrestitoLocale prestitoLocale = em.find(PrestitoLocale.class, id_removePrestito);
		prestitoLocale.setPrestitoLocaleMaterialeEscluso(new ArrayList<PrestitoLocaleMaterialeEscluso>());
		prestitoLocale.setPrestitoLocaleUtentiAmmessis(new ArrayList<PrestitoLocaleUtentiAmmessi>());
		em.merge(prestitoLocale);
	}

	@Override
	@Transactional
	public void removePrestitoLocale(int id_biblioteca, int id_removePrestito) {
		Integer removeIndex = null;

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PrestitoLocale> prestitoLocales = biblioteca.getPrestitoLocales();
		Iterator<PrestitoLocale> it = prestitoLocales.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy prestitoLocale
			PrestitoLocale tmpPrestitoLocale = (PrestitoLocale) it.next();
			if (tmpPrestitoLocale.getIdPrestitoLocale() == id_removePrestito) {
				removeIndex = prestitoLocales.indexOf(tmpPrestitoLocale);
			}
		}

		if (removeIndex != null) {
			removeAllPrestitoLocaleEntriesFromHasMaterialeEsclusoAndUtentiAmmessi(id_removePrestito);
			prestitoLocales.remove(removeIndex.intValue());
		}

		biblioteca.setPrestitoLocales(prestitoLocales);

		em.merge(biblioteca);

		em.remove(em.find(PrestitoLocale.class, id_removePrestito));
	}

	@Override
	@Transactional
	public List<PrestitoInterbibliotecario> getPrestitoInterbibliotecarioERuoloByIdBiblio(
			int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<PrestitoInterbibliotecario> prestitoInterbibliotecarios = biblioteca.getPrestitoInterbibliotecarios();
		Iterator<PrestitoInterbibliotecario> it = prestitoInterbibliotecarios.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			PrestitoInterbibliotecario prestitoInterbibliotecario = (PrestitoInterbibliotecario) it
			.next();
		}
		return prestitoInterbibliotecarios;
	}

	@Override
	@Transactional
	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca, Integer idPrestito, Integer idRuolo, Boolean nazionale,
			Boolean internazionale, boolean modifica) throws DuplicateEntryException {
		if (modifica) {
			PrestitoInterbibliotecario toUpdate = em.find(PrestitoInterbibliotecario.class, idPrestito);
			toUpdate.setNazionale(nazionale);
			toUpdate.setInternazionale(internazionale);
			em.merge(toUpdate);
		} else {

			Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

			List<PrestitoInterbibliotecario> prestitoInterbibliotecarios = biblioteca.getPrestitoInterbibliotecarios();
			Iterator<PrestitoInterbibliotecario> it = prestitoInterbibliotecarios.iterator();
			while (it.hasNext()) {
				// Iterazione anti-lazy
				PrestitoInterbibliotecario prestitoInterbibliotecario = (PrestitoInterbibliotecario) it
				.next();
				if (prestitoInterbibliotecario.getPrestitoInterbibliotecarioModo().getIdPrestitoInterbibliotecarioModo().intValue() == idRuolo.intValue()) {
					throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
				}
			}

			PrestitoInterbibliotecario toSave = new PrestitoInterbibliotecario();

			toSave.setNazionale(nazionale);
			toSave.setInternazionale(internazionale);

			PrestitoInterbibliotecarioModo tmpRuolo = em.find(PrestitoInterbibliotecarioModo.class, idRuolo);
			toSave.setPrestitoInterbibliotecarioModo(tmpRuolo);

			em.persist(toSave);

			prestitoInterbibliotecarios.add(toSave);

			biblioteca.setPrestitoInterbibliotecarios(prestitoInterbibliotecarios);

			em.merge(biblioteca);

		}

	}

	@Override
	@Transactional
	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca, int id_removePrestito) {
		// Carico la biblioteca
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		Integer removeIndex = null;

		List<PrestitoInterbibliotecario> interbibliotecarios = biblioteca.getPrestitoInterbibliotecarios();
		Iterator<PrestitoInterbibliotecario> it = interbibliotecarios.iterator();
		while (it.hasNext()) {
			// Scorro la lista prestiti per cercare il prestito da rimuovere
			PrestitoInterbibliotecario prestitoInterbibliotecario = (PrestitoInterbibliotecario) it
			.next();
			if (prestitoInterbibliotecario.getIdPrestitoInterbibliotecario().intValue() == id_removePrestito) {
				removeIndex = interbibliotecarios.indexOf(prestitoInterbibliotecario);
			}

		}
		if (removeIndex != null) {
			// Rimuovo il prestito dalla biblioteca
			interbibliotecarios.remove(removeIndex.intValue());
		}
		biblioteca.setPrestitoInterbibliotecarios(interbibliotecarios);
		em.persist(biblioteca);
		PrestitoInterbibliotecario toRemove = em.find(PrestitoInterbibliotecario.class, id_removePrestito);

		// rimuovo il prestito
		em.remove(toRemove);

	}

	@Override
	@Transactional
	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblioteca, Boolean hasPrestitoNazionale, Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		biblioteca.setPrestitoInterbiblioNazionale(hasPrestitoNazionale);
		biblioteca.setPrestitoInterbiblioInternazionale(hasPrestitoInternazionale);
		biblioteca.setProcedureIllAutomatizzate(hasProcedureAutomatizzate);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void setInfoPersonale(int id_biblioteca, HashMap<String, Object> personaleValues) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		if (personaleValues.containsKey("personaleTotale")) {
			if (personaleValues.get("personaleTotale") != null)
				biblioteca.setPersonaleTotale((Integer) personaleValues.get("personaleTotale"));
			else
				biblioteca.setPersonaleTotale(null);
		}
		if (personaleValues.containsKey("personalePartTime")) {
			if (personaleValues.get("personalePartTime") != null)
				biblioteca.setPersonalePartTime((Integer) personaleValues.get("personalePartTime"));
			else
				biblioteca.setPersonalePartTime(null);
		}
		if (personaleValues.containsKey("personaleEsterno")) {
			if (personaleValues.get("personaleEsterno") != null)
				biblioteca.setPersonaleEsterno((Integer) personaleValues.get("personaleEsterno"));
			else
				biblioteca.setPersonaleEsterno(null);
		}
		if (personaleValues.containsKey("personaleTemp")) {
			if (personaleValues.get("personaleTemp") != null)
				biblioteca.setPersonaleTemporaneo((Integer) personaleValues.get("personaleTemp"));
			else
				biblioteca.setPersonaleTemporaneo(null);
		}

		em.merge(biblioteca);

	}

	@Override
	@Transactional
	public void setInfoUtenti(int id_biblioteca, HashMap<String, Object> utentiValues) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		if (utentiValues.containsKey("iscrittiPrestitoUltimi12Mesi")) {
			if (utentiValues.get("iscrittiPrestitoUltimi12Mesi") != null)
				biblioteca.setUtentiIscrittiPrestitoAnno((Integer) utentiValues.get("iscrittiPrestitoUltimi12Mesi"));
			else
				biblioteca.setUtentiIscrittiPrestitoAnno(null);
		}
		if (utentiValues.containsKey("ingressiUltimi12Mesi")) {
			if (utentiValues.get("ingressiUltimi12Mesi") != null)
				biblioteca.setUtenti((Integer) utentiValues.get("ingressiUltimi12Mesi"));
			else
				biblioteca.setUtenti(null);
		}
		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> INIZIO
//		if (utentiValues.containsKey("utentiIscritti")) {
//			if (utentiValues.get("utentiIscritti") != null)
//				biblioteca.setUtentiIscritti((Integer) utentiValues.get("utentiIscritti"));
//			else
//				biblioteca.setUtentiIscritti(null);
//		}
		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> FINE
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void setInfoBilancio(int id_biblioteca, HashMap<String, Object> bilancioValues) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		if (bilancioValues.containsKey("usciteTotali")) {
			if (bilancioValues.get("usciteTotali") != null)
				biblioteca.setBilancioUscite((Integer) bilancioValues.get("usciteTotali"));
			else
				biblioteca.setBilancioUscite(null);
		}
		if (bilancioValues.containsKey("usciteAltro")) {
			if (bilancioValues.get("usciteAltro") != null)
				biblioteca.setBilancioUsciteVarie((Integer) bilancioValues.get("usciteAltro"));
			else
				biblioteca.setBilancioUsciteVarie(null);
		}
		if (bilancioValues.containsKey("usciteAutomazione")) {
			if (bilancioValues.get("usciteAutomazione") != null)
				biblioteca.setBilancioUsciteAutomazione((Integer) bilancioValues.get("usciteAutomazione"));
			else
				biblioteca.setBilancioUsciteAutomazione(null);
		}
		if (bilancioValues.containsKey("usciteFunzionamento")) {
			if (bilancioValues.get("usciteFunzionamento") != null)
				biblioteca.setBilancioUsciteFunzionamento((Integer) bilancioValues.get("usciteFunzionamento"));
			else
				biblioteca.setBilancioUsciteFunzionamento(null);
		}
		if (bilancioValues.containsKey("usciteIncrementoPatrimonio")) {
			if (bilancioValues.get("usciteIncrementoPatrimonio") != null)
				biblioteca.setBilancioUsciteIncrementoPatrimonio((Integer) bilancioValues.get("usciteIncrementoPatrimonio"));
			else
				biblioteca.setBilancioUsciteIncrementoPatrimonio(null);
		}
		if (bilancioValues.containsKey("uscitePersonale")) {
			if (bilancioValues.get("uscitePersonale") != null)
				biblioteca.setBilancioUscitePersonale((Integer) bilancioValues.get("uscitePersonale"));
			else
				biblioteca.setBilancioUscitePersonale(null);
		}
		if (bilancioValues.containsKey("entrateTotali")) {
			if (bilancioValues.get("entrateTotali") != null)
				biblioteca.setBilancioEntrate((Integer) bilancioValues.get("entrateTotali"));
			else
				biblioteca.setBilancioEntrate(null);
		}
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<DepositiLegali> getDepositiLegaliByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<DepositiLegali> depositiLegalis = biblioteca.getDepositiLegalis();
		Iterator<DepositiLegali> it = depositiLegalis.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			DepositiLegali depositiLegali = (DepositiLegali) it.next();
		}
		return depositiLegalis;
	}

	@Override
	@Transactional
	public boolean addDepositoLegaleToBiblio(int id_biblioteca, boolean modifica, int id_nuovoTipoDeposito, String anno) {
		if (!modifica && searchDepositoLegale(id_biblioteca, id_nuovoTipoDeposito) == true) {
			return false;

		} else {
			DepositiLegaliPK depositiLegaliPK = new DepositiLegaliPK();
			depositiLegaliPK.setIdBiblioteca(id_biblioteca);
			depositiLegaliPK.setIdDepositiLegaliTipo(id_nuovoTipoDeposito);

			DepositiLegali depositiLegali = new DepositiLegali();
			depositiLegali.setId(depositiLegaliPK);
			depositiLegali.setDaAnno(anno);

			em.merge(depositiLegali);
			
			return true;
		}
	}

	private boolean searchDepositoLegale(int id_biblioteca, int id_nuovoTipoDeposito) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<DepositiLegali> depLegs = biblioteca.getDepositiLegalis();

		for (DepositiLegali entryDepLeg : depLegs) {
			DepositiLegaliTipo depLeg = entryDepLeg.getDepositiLegaliTipo();
			if (depLeg != null && depLeg.getIdDepositiLegaliTipo().intValue() == id_nuovoTipoDeposito) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	@Transactional
	public void removeDepositoLegaleFromBiblio(int id_biblioteca, int id_rimuoviDepositoLegale) {

		DepositiLegaliPK depositiLegaliPK = new DepositiLegaliPK();
		depositiLegaliPK.setIdBiblioteca(id_biblioteca);
		depositiLegaliPK.setIdDepositiLegaliTipo(id_rimuoviDepositoLegale);

		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM DepositiLegali d ");
		sb.append(" WHERE d.id = :depositiLegaliPK ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("depositiLegaliPK", depositiLegaliPK);
		q.executeUpdate();

	}

	@Override
	@Transactional
	public void inserisciComunicazioniCatalogazione(int id_biblio, String value) {
		Comunicazioni comunicazioni = null;

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblio);
		/*
		 * Modifico solo l'oggeto in posizione 0 dato che nell'interfaccia viene
		 * visualizzata solo un istanza di Bibliografia, mentre sul database è
		 * rappresentata una Lista di bibliografie
		 */

		if (biblioteca.getComunicazionis().size() > 0) {
			comunicazioni = em.find(Comunicazioni.class, biblioteca.getComunicazionis().get(0).getIdComunicazioni());
			comunicazioni.setDescrizione(value);
			em.merge(comunicazioni);
		} else {
			comunicazioni = new Comunicazioni();
			comunicazioni.setBiblioteca(biblioteca);
			comunicazioni.setDescrizione(value);
			em.persist(comunicazioni);
		}
	}

	@Override
	@Transactional
	public void inserisciNoteCatalogazione(int id_biblio, String value) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblio);
		biblioteca.setCatalogazioneNote(value);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void setNuovoStato(Biblioteca biblioteca, String stato) {
		Query query = em.createQuery("from StatoBibliotecaWorkflow where label = :statoWorkflow");
		query.setParameter("statoWorkflow", stato);
		StatoBibliotecaWorkflow sbw = (StatoBibliotecaWorkflow) query.getSingleResult();
		biblioteca.setStatoBibliotecaWorkflow(sbw);
		em.merge(biblioteca);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void setNuovoStatoNewTx(Biblioteca biblioteca, String stato) {
		Query query = em.createQuery("from StatoBibliotecaWorkflow where label = :statoWorkflow");
		query.setParameter("statoWorkflow", stato);
		StatoBibliotecaWorkflow sbw = (StatoBibliotecaWorkflow) query.getSingleResult();
		biblioteca.setStatoBibliotecaWorkflow(sbw);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public Vector<Biblioteca> getBibliotecheReport(List<Integer> idbibs) {
		Vector<Biblioteca> bibvector = new Vector<Biblioteca>();

		if (idbibs != null && idbibs.size() != 0) {

			for (Integer entry : idbibs) {
				Biblioteca tmp = em.find(Biblioteca.class, entry);
				bibvector.add(tmp);

			}

			return bibvector;
		} else return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Biblioteca getBibliotecaByIdForMarshall(int id) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id);
		if (biblioteca == null)
			return null;
		biblioteca.getAccessoModalitas().size();
		biblioteca.getBibliografias().size();

		biblioteca.getBibliotecaPadre();
		biblioteca.getBibliotecasFigli().size();
		biblioteca.getCodicis().size();
		biblioteca.getComune(); // ?
		biblioteca.getComunicazionis().size();
		biblioteca.getContattis().size();
		biblioteca.getDenominazioniAlternatives().size();
		biblioteca.getDenominazioniPrecedentis().size();
		biblioteca.getDepositiLegalis().size();
		biblioteca.getDestinazioniSocialis().size();
		biblioteca.getDeweyLiberos().size();
		biblioteca.getDeweys().size();
		biblioteca.getFondiAntichiConsistenza(); // ?
		biblioteca.getFondiSpecialis().size();
		biblioteca.getGeolocalizzazione(); // ?
		biblioteca.getIndicizzazioneClassificatas().size();
		biblioteca.getIndicizzazioneSoggettos().size();
		biblioteca.getNormeCatalogaziones().size();
		biblioteca.getOrarioChiusures().size();
		biblioteca.getOrarioUfficialis().size();
		biblioteca.getOrarioVariazionis().size();
		for (PartecipaCataloghiCollettiviMateriale temp : biblioteca.getPartecipaCataloghiCollettiviMateriales()) {
			temp.getCataloghiCollettiviMaterialeUrls().size();
		}
		for (PartecipaCataloghiGenerali temp : biblioteca.getPartecipaCataloghiGeneralis()) {
			temp.getCataloghiGeneraliUrls().size();
		}
		biblioteca.getPartecipaCataloghiSpecialiMateriales().size();
		for (PartecipaCataloghiSpecialiMateriale temp : biblioteca.getPartecipaCataloghiSpecialiMateriales()) {
			temp.getCataloghiSpecialiMaterialeUrls().size();
		}
		biblioteca.getPatrimonios().size();
		biblioteca.getPrestitoInterbibliotecarios().size();
		biblioteca.getPrestitoLocales();
		for (PrestitoLocale temp : biblioteca.getPrestitoLocales()) {
			temp.getPrestitoLocaleUtentiAmmessis().size();
			temp.getPrestitoLocaleMaterialeEscluso().size();
		}
		biblioteca.getPubblicazionis().size();
		biblioteca.getPubblicazionis().size();
		biblioteca.getRegolamentos().size();
		biblioteca.getRiproduzionis().size();
		biblioteca.getServiziBibliotecariCartas().size();
		biblioteca.getServiziInformazioniBibliograficheModalitas().size();
		biblioteca.getSezioniSpecialis().size();
		biblioteca.getSistemiBiblioteches().size();
		biblioteca.getSistemiPrestitoInterbibliotecarios().size();
		biblioteca.getSpogliBibliograficis().size();
		biblioteca.getStatoBibliotecaWorkflow(); // ?
		biblioteca.getStatoCatalogaziones().size();
		biblioteca.getTipologiaFunzionale(); // ?
		biblioteca.getUtenteUltimaModifica(); // *
		biblioteca.getUtentisGestori().size();
		biblioteca.getDocumentDeliveries().size();
		biblioteca.getPhotos().size();
		return biblioteca;
	}

	public Biblioteca[] getBibliotecheViaCodice(String[] codABI, Utenti ugest, int firstResult, int maxResult) {
		String prepareQuery = "select b from Biblioteca b ";

		if (ugest != null)
			prepareQuery += " join b.utentisGestori utenteGestore";

		prepareQuery += " where ";

		for (int i = 0; i < codABI.length; i++) {
			prepareQuery += "(b.isilProvincia = :isilPr" + i
			+ " AND b.isilNumero = :isilNr" + i + " ) OR ";
		}
		prepareQuery = prepareQuery.substring(0, prepareQuery.length() - 4);

		if (ugest != null)
			prepareQuery += " AND utenteGestore = :utenteGestore";

		Query query = em.createQuery(prepareQuery);

		for (int i = 0; i < codABI.length; i++) {
			query.setParameter("isilPr" + i, Utility.getIsilPr(codABI[i]));
			query.setParameter("isilNr" + i, Utility.getIsilNr(codABI[i]));
		}
		if (ugest != null)
			query.setParameter("utenteGestore", ugest);

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<Biblioteca> biblios = query.getResultList();
		Biblioteca[] results = new Biblioteca[biblios.size()];

		int i = 0;
		for (Biblioteca entry : biblios) {
			results[i] = entry;
			i++;
		}
		return results;

	}

	@Override
	@Transactional
	public List<Utenti> ricercaUtentiByIdBiblio(int idbiblio, int offset, int rows, String orderByField, String orderByDir) {

		StringBuffer query = new StringBuffer("select utenteGestore from Biblioteca b join b.utentisGestori utenteGestore");
		query.append(" where b.idBiblioteca = :idBiblioteca");

		if (StringUtils.isNotBlank(orderByField)) {
			query.append(" ORDER BY ").append(orderByField);
			if (StringUtils.isNotBlank(orderByDir)) {
				query.append(" ").append(orderByDir);
			}
		}

		Query q = em.createQuery(query.toString());

		q.setParameter("idBiblioteca", idbiblio);
		q.setFirstResult(offset);
		q.setMaxResults(rows);

		List<Utenti> result = q.getResultList();
		return result;

	}

	@Override
	@Transactional
	public int countUtentiByIdBiblio(int idbiblio) {

		StringBuffer query = new StringBuffer("select count(utenteGestore) from Biblioteca b join b.utentisGestori utenteGestore");
		query.append(" where b.idBiblioteca = :idBiblioteca");

		Query q = em.createQuery(query.toString());
		q.setParameter("idBiblioteca", idbiblio);

		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public CodiciTipo getCodiceTipoById(int idCodice) {
		CodiciTipo codiceTipo = em.find(CodiciTipo.class, idCodice);
		return codiceTipo;
	}

	@Override
	@Transactional
	public void addStatoCatalogazioneViaRipristino(int idbiblio, Integer idStatoCatalogazione, Integer idBibliotecaTarget) {
		Biblioteca biblioteca = getBibliotecaById(idbiblio);

		StatoCatalogazionePK statoCatalogazionePK = new StatoCatalogazionePK();
		statoCatalogazionePK.setIdBiblioteca(idbiblio);
		statoCatalogazionePK.setIdStatoCatalogazione(idStatoCatalogazione);

		StatoCatalogazione statoCatalogazione = new StatoCatalogazione();
		statoCatalogazione.setBiblioteca(biblioteca);
		statoCatalogazione.setId(statoCatalogazionePK);

		if (idStatoCatalogazione.intValue() == 7) {// Stato biblioteca confluita
			if (idBibliotecaTarget != null) {
				Biblioteca bibliotecaTarget = getBibliotecaById(idBibliotecaTarget);
				statoCatalogazione.setBibliotecaTarget(bibliotecaTarget);
			}
		}

		em.merge(statoCatalogazione);

		List<StatoCatalogazione> statoCatalogaziones = new ArrayList<StatoCatalogazione>();

		statoCatalogaziones.add(statoCatalogazione);

		biblioteca.setStatoCatalogaziones(statoCatalogaziones);

		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public Boolean addStatoCatalogazione(HashMap<String, Object> params) {
		Boolean result = false;
		int idbiblio = (Integer) params.get("idBiblioteca");
		Integer idStatoCatalogazione = (Integer) params.get("idStatoCatalogazione");

		Biblioteca biblioteca = getBibliotecaById(idbiblio);

		StatoCatalogazionePK statoCatalogazionePK = new StatoCatalogazionePK();
		statoCatalogazionePK.setIdBiblioteca(idbiblio);
		statoCatalogazionePK.setIdStatoCatalogazione(idStatoCatalogazione);

		StatoCatalogazione statoCatalogazione = new StatoCatalogazione();
		statoCatalogazione.setBiblioteca(biblioteca);
		statoCatalogazione.setId(statoCatalogazionePK);

		if (idStatoCatalogazione.intValue() == 7) {// Stato biblioteca confluita
			String isilSt = (String) params.get("isilStato");
			String isilPr = (String) params.get("isilProvincia");
			String isilNr = params.get("isilNumero") != null ? ((Integer) params.get("isilNumero")).toString() : null;
			
			if (StringUtils.isNotBlank(isilSt) && StringUtils.isNotBlank(isilPr) && StringUtils.isNotBlank(isilNr)) {
				if (isilPr.length() != 2) {
					result = true;

				} else {
					String isilBibliotecaTarget = Utility.buildIsil(isilSt, isilPr, isilNr);
					
					String[] str = new String[1];
					str[0] = isilBibliotecaTarget;
					
					Biblioteca[] bibliotecaTarget = getBibliotecheViaCodice(str, null, 0, 1);
					if (bibliotecaTarget.length > 0) {
						statoCatalogazione.setBibliotecaTarget(bibliotecaTarget[0]);
					} else {
						result = true;
					}
				}
			}
		}

		// Cancello i vecchi stati dalla lista stati catalogazioni
		clearListaStatoCatalogazione(idbiblio);

		List<StatoCatalogazione> statoCatalogaziones = new ArrayList<StatoCatalogazione>();

		// Salvo la biblioteca con i nuovi stati
		em.merge(statoCatalogazione);

		statoCatalogaziones.add(statoCatalogazione);

		biblioteca.setStatoCatalogaziones(statoCatalogaziones);

		em.merge(biblioteca);

		return result;
	}

	@Override
	@Transactional
	public Boolean removeStatoCatalogazione(HashMap<String, Object> params) {
		Boolean result = false;
		int idbiblio = (Integer) params.get("idBiblioteca");
		Integer idStatoCatalogazione = (Integer) params.get("idStatoCatalogazione");

		Biblioteca biblioteca = getBibliotecaById(idbiblio);
		if (idStatoCatalogazione != null && idStatoCatalogazione.intValue() == -1) {
			if (biblioteca.getStatoCatalogaziones() != null && biblioteca.getStatoCatalogaziones().size() > 0) {
				clearListaStatoCatalogazione(idbiblio);

				em.merge(biblioteca);
			}
		}

		return result;
	}

	@Transactional
	public void clearListaStatoCatalogazione(int idbiblio) {
		Biblioteca biblioteca = getBibliotecaById(idbiblio);

		List<StatoCatalogazione> statoCatalogaziones = biblioteca.getStatoCatalogaziones();

		for (StatoCatalogazione sc : statoCatalogaziones) {
			em.remove(sc);
		}
		statoCatalogaziones.clear();

		biblioteca.setStatoCatalogaziones(statoCatalogaziones);
	}

	@Override
	public void removeChild(Object o) {
		em.remove(o);
	}

	@Override
	@Transactional
	public Codici getCodiceByIdCodiceIdBiblio(int idBiblio, int idCodiceTipo) {
		Biblioteca biblioteca = getBibliotecaById(idBiblio);
		CodiciTipo codiceTipo = em.find(CodiciTipo.class, idCodiceTipo);

		StringBuffer sb = new StringBuffer();
		sb.append(" from Codici c");
		sb.append(" where c.biblioteca = :biblioteca ");
		sb.append(" and c.codiciTipo = :codiceTipo ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("biblioteca", biblioteca);
		q.setParameter("codiceTipo", codiceTipo);

		List<Codici> result = q.getResultList();

		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	@Transactional
	public void removePrestitoInterbibliotecarioNotUsedByOtherBibs(List<PrestitoInterbibliotecario> prestList, int idBib) {
		if (prestList != null) {
			for (PrestitoInterbibliotecario prestEntry : prestList) {
				if (!isPrestitoInterbibliotecarioUsedByOtherBibs(prestEntry, idBib)) {
					em.remove(prestEntry);
				}
			}
		}
	}

	private boolean isPrestitoInterbibliotecarioUsedByOtherBibs(PrestitoInterbibliotecario prestito, int idBib) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from biblioteca_has_prestito_interbibliotecario bhp join prestito_interbibliotecario p ");
		sb.append("on bhp.id_prestito_interbibliotecario = p.id_prestito_interbibliotecario ");
		sb.append("where p.id_prestito_interbibliotecario = :idPrestito and bhp.id_biblioteca != :idBib");

		Query q = em.createNativeQuery(sb.toString());

		q.setParameter("idPrestito", prestito.getIdPrestitoInterbibliotecario());
		q.setParameter("idBib", idBib);

		List result = q.getResultList();
		if (result != null && result.size() > 0) {
			return true;

		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public void removeRiproduzioniFromBiblio(Biblioteca biblioteca) {
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM Riproduzioni r ");
		sb.append(" WHERE r.biblioteca = :biblioteca ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public void removePrestitoLocaleFromBiblio(Biblioteca biblioteca) {
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM PrestitoLocale pl ");
		sb.append(" WHERE pl.biblioteca = :biblioteca ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public List<SistemiPrestitoInterbibliotecario> getListaSistemiPrestitoInterbibliotecario(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		List<SistemiPrestitoInterbibliotecario> sistPrestInterbibs = biblioteca.getSistemiPrestitoInterbibliotecarios();

		Iterator<SistemiPrestitoInterbibliotecario> itsist = sistPrestInterbibs.iterator();

		while (itsist.hasNext()) {
			// Iterazione anti lazy
			SistemiPrestitoInterbibliotecario sistPrestInterbib = (SistemiPrestitoInterbibliotecario) itsist.next();
		}

		return sistPrestInterbibs;
	}

	@Override
	@Transactional
	public void removeSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistema) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		Integer tmpIndex = null;

		List<SistemiPrestitoInterbibliotecario> sistPrestInterbibs = biblioteca.getSistemiPrestitoInterbibliotecarios();
		Iterator<SistemiPrestitoInterbibliotecario> it = sistPrestInterbibs.iterator();
		while (it.hasNext()) {
			// Iterazione anti-lazy
			SistemiPrestitoInterbibliotecario sistPrestInterbib = (SistemiPrestitoInterbibliotecario) it.next();

			if (sistPrestInterbib.getIdSistemiPrestitoInterbibliotecario().intValue() == id_sistema) {
				tmpIndex = sistPrestInterbibs.indexOf(sistPrestInterbib);
			}
		}
		if (tmpIndex != null) {
			sistPrestInterbibs.remove(sistPrestInterbibs.get(tmpIndex));
		}

		biblioteca.setSistemiPrestitoInterbibliotecarios(sistPrestInterbibs);

		em.persist(biblioteca);
	}

	@Override
	@Transactional
	public void addSistemaPrestitoInterbibliotecario(int id_biblioteca, Integer id_sistema) throws DuplicateEntryException {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		SistemiPrestitoInterbibliotecario newSistPrestInterbib = em.find(SistemiPrestitoInterbibliotecario.class, id_sistema);

		List<SistemiPrestitoInterbibliotecario> sistPrestInterbibs = biblioteca.getSistemiPrestitoInterbibliotecarios();
		Iterator<SistemiPrestitoInterbibliotecario> it = sistPrestInterbibs.iterator();

		while (it.hasNext()) {
			// Iterazione anti-lazy
			SistemiPrestitoInterbibliotecario sistPrestInterbib = (SistemiPrestitoInterbibliotecario) it.next();
			if (sistPrestInterbib.getIdSistemiPrestitoInterbibliotecario().intValue() == id_sistema.intValue()) {
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
		}

		sistPrestInterbibs.add(newSistPrestInterbib);

		biblioteca.setSistemiPrestitoInterbibliotecarios(sistPrestInterbibs);

		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void setReference(int id_biblio, Boolean hasAttivoReference, Boolean hasReferenceLocale, Boolean hasReferenceOnline) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblio);
		biblioteca.setAttivoReference(hasAttivoReference);
		biblioteca.setReferenceLocale(hasReferenceLocale);
		biblioteca.setReferenceOnline(hasReferenceOnline);

		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public List<RiproduzioniTipo> getDocumentDeliveryByIdBiblio(int id_biblioteca) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		List<RiproduzioniTipo> documentDeliveries = biblioteca.getDocumentDeliveries();
		Iterator<RiproduzioniTipo> it = documentDeliveries.iterator();

		while (it.hasNext()) {
			// Iterazione anti-lazy
			RiproduzioniTipo docDel = (RiproduzioniTipo) it.next();
		}

		return documentDeliveries;
	}

	@Override
	@Transactional
	public void addDocumentDelivery(int id_biblioteca, Integer idDocumentDelivery) throws DuplicateEntryException {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		RiproduzioniTipo newDocDel = em.find(RiproduzioniTipo.class, idDocumentDelivery);

		List<RiproduzioniTipo> documentDeliveries = biblioteca.getDocumentDeliveries();
		Iterator<RiproduzioniTipo> it = documentDeliveries.iterator();

		while (it.hasNext()) {
			// Iterazione anti-lazy
			RiproduzioniTipo documentDelivery = (RiproduzioniTipo) it.next();

			if (documentDelivery.getIdRiproduzioniTipo().intValue() == idDocumentDelivery.intValue()) {
				throw new DuplicateEntryException(DUPLICATE_ENTRY_ERROR_MESSAGE);
			}
		}

		documentDeliveries.add(newDocDel);
		biblioteca.setDocumentDeliveries(documentDeliveries);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void removeDocumentDelivery(int id_biblioteca, Integer idRecord) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		Integer tmpIndex = null;

		List<RiproduzioniTipo> documentDeliveries = biblioteca.getDocumentDeliveries();
		Iterator<RiproduzioniTipo> it = documentDeliveries.iterator();

		while (it.hasNext()) {
			// Iterazione anti-lazy
			RiproduzioniTipo docDel = (RiproduzioniTipo) it.next();
			if (docDel.getIdRiproduzioniTipo().intValue() == idRecord.intValue()) {
				tmpIndex = documentDeliveries.indexOf(docDel);
			}
		}

		if (tmpIndex != null) {
			documentDeliveries.remove(documentDeliveries.get(tmpIndex));
		}

		biblioteca.setDocumentDeliveries(documentDeliveries);
		em.merge(biblioteca);
	}

	@Override
	@Transactional
	public void removeDocumentDeliveryFromBiblio(Biblioteca biblioteca) {
		biblioteca.setDocumentDeliveries(new ArrayList<RiproduzioniTipo>());
		em.merge(biblioteca);

	}

	@Override
	@Transactional
	public void removeDepositiLegaliFromBiblio(Biblioteca biblioteca) {
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM DepositiLegali dl ");
		sb.append(" WHERE dl.biblioteca = :biblioteca ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);
		q.executeUpdate();
	}

	@Override
	@Transactional
	public void removeModalitaAccessoFromBiblio(Biblioteca biblioteca) {
		biblioteca.setAccessoModalitas(new ArrayList<AccessoModalita>());
		em.merge(biblioteca);

	}

	@Override
	@Transactional
	public String getPrimaOccorrenzaFonteValorizzata(String[] idBibs) {
		String prepareQuery = "from Biblioteca as b where ";
		for (int i = 0; i < idBibs.length; i++) {
			if (i == 0) {
				if (i == (idBibs.length-1)) {
					prepareQuery += "(b.idBiblioteca = :idBiblioteca" + i + ") ";

				} else {
					prepareQuery += "(b.idBiblioteca = :idBiblioteca" + i + " OR ";
				}

			} else if (i == (idBibs.length-1)) {
				prepareQuery += "b.idBiblioteca = :idBiblioteca" + i + ") ";

			} else {
				prepareQuery += "b.idBiblioteca = :idBiblioteca" + i + " OR ";
			}

		}

		if (idBibs.length > 0) {
			prepareQuery += " AND b.fonteDescrizione is not null ";

		} else {
			prepareQuery += " b.fonteDescrizione is not null ";
		}

		Query query = em.createQuery(prepareQuery);

		for (int i = 0; i < idBibs.length; i++) {
			query.setParameter("idBiblioteca" + i, Integer.valueOf(idBibs[i]));
		}

		List biblios = query.getResultList();
		Biblioteca[] results = new Biblioteca[biblios.size()];
		results = (Biblioteca[]) biblios.toArray(results);

		if (results.length > 0) {
			return results[0].getFonteDescrizione();

		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void inserisciPhoto(int id_biblioteca, Photo photo) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		photo.setBiblioteca(biblioteca);

		em.merge(photo);

	}

	@Override
	@Transactional
	public List<String> getListaIsilProvincia(String queryString) {
		String prepareQuery = "select distinct b.isilProvincia from Biblioteca as b where b.bibliotecaPadre IS null ";

		if (StringUtils.isNotBlank(queryString)) {
			prepareQuery = prepareQuery.concat(" AND b.isilProvincia like :prov ");
		}

		prepareQuery = prepareQuery.concat(" order by b.isilProvincia asc ");

		Query query = em.createQuery(prepareQuery);

		if (StringUtils.isNotBlank(queryString)) {
			String param = "%".concat(queryString).concat("%");
			query.setParameter("prov", param);
		}

		List<String> isilProvincias = (List<String>) query.getResultList();

		return isilProvincias;
	}

	@Override
	@Transactional
	public List<Photo> getPhotos(int id_biblioteca) {

		//		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		//
		//		List<Photo> photos = biblioteca.getPhotos();
		//		Iterator<Photo> it = photos.iterator();
		//
		//		while (it.hasNext()) {
		//			// Iterazione anti-lazy
		//			Photo ph = (Photo) it.next();
		//		}
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		String prepareQuery = "from Photo p where biblioteca = :biblioteca order by p.ordine asc";

		Query query = em.createQuery(prepareQuery);

		query.setParameter("biblioteca", biblioteca);

		List<Photo> photos = (List<Photo>) query.getResultList();

		return photos;
	}

	@Override
	@Transactional
	public void addPhoto(int id_biblioteca, String caption, String uri) {
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);

		Photo photo = new Photo();
		photo.setBiblioteca(biblioteca);
		photo.setCaption(caption);
		photo.setUri(uri);

		if (biblioteca.getPhotos() == null || (biblioteca.getPhotos() != null && biblioteca.getPhotos().size() == 0)) {
			photo.setOrdine(1);

		} else {
			List<Photo> photos = getPhotos(id_biblioteca);
			Photo ph = photos.get(photos.size()-1);
			photo.setOrdine(ph.getOrdine().intValue()+1);
		}
		em.merge(photo);

	}

	@Override
	@Transactional
	public void updatePhotoCaption(int idPhoto, String caption) {
		Photo photo = em.find(Photo.class, idPhoto);

		photo.setCaption(caption);
		em.merge(photo);

	}

	@Override
	@Transactional
	public void removePhoto(int id_biblioteca, int id_photo) {
		Photo photo = em.find(Photo.class, id_photo);

		/* Riassegno il giusto ordine */
		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		int order = 1;
		for (Photo photoEntry : biblioteca.getPhotos()) {
			if (photoEntry.getIdPhoto().intValue() == id_photo) {
				photoEntry.setOrdine(0);

			} else {
				photoEntry.setOrdine(order);
				order++;
			}

			em.merge(photoEntry);
		}

		/* Elimino la foto */
		em.remove(photo);
	}

	@Override
	@Transactional
	public void updatePhotoOrder(List<Integer> idPhotos) {
		int order = 1;
		for (Integer id : idPhotos) {
			Photo photo = em.find(Photo.class, id);
			photo.setOrdine(order);
			order++;

			em.merge(photo);
		}
	}
}