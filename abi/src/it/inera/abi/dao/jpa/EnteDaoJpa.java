package it.inera.abi.dao.jpa;

import it.inera.abi.dao.EnteDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.Stato;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EnteDaoJpa implements EnteDao {

	@PersistenceContext
	private EntityManager em;

	private Log log = LogFactory.getLog(EnteDaoJpa.class);

	@Override
	@Transactional
	public Ente createEnteIfNotExist(Stato stato,EnteTipologiaAmministrativa enteTipologiaAmministrativa,
			String denominazione, String asiaAsip, String partitaIva, String codiceFiscale) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  e ");
		sb.append("FROM Ente e WHERE ");

		List<String> criteria = new ArrayList<String>();
		if (stato != null)
			criteria.add("e.stato = :stato");
		if (enteTipologiaAmministrativa != null)
			criteria.add("e.enteTipologiaAmministrativa = :enteTipologiaAmministrativa");
		if (denominazione != null)
			criteria.add("e.denominazione = :denominazione");
		else
			criteria.add("e.denominazione IS NULL");

		if (partitaIva != null)
			criteria.add("e.partitaIva = :partitaIva");
		else
			criteria.add("e.partitaIva IS NULL");

		if (codiceFiscale != null)
			criteria.add("e.codiceFiscale = :codiceFiscale");
		else
			criteria.add("e.codiceFiscale IS NULL");

		if (asiaAsip != null)
			criteria.add("e.asiaAsip = :asiaAsip");
		else
			criteria.add("e.asiaAsip IS NULL");

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		Query q = em.createQuery(sb.toString());

		if (stato != null)
			q.setParameter("stato", stato);
		if (enteTipologiaAmministrativa != null)
			q.setParameter("enteTipologiaAmministrativa",
					enteTipologiaAmministrativa);
		if (denominazione != null)
			q.setParameter("denominazione", denominazione);
		if (asiaAsip != null)
			q.setParameter("asiaAsip", asiaAsip);
		if (partitaIva != null)
			q.setParameter("partitaIva", partitaIva);
		if (codiceFiscale != null)
			q.setParameter("codiceFiscale", codiceFiscale);

		Ente ente = null;
		List<Ente> listaEnte = q.getResultList();
		if (listaEnte == null || listaEnte.size() > 0) {
			ente = (Ente) listaEnte.get(0);
		} else {
			ente = new Ente();
			ente.setAsiaAsip(asiaAsip);
			ente.setCodiceFiscale(codiceFiscale);
			ente.setDenominazione(denominazione);
			ente.setPartitaIva(partitaIva);
			ente.setEnteTipologiaAmministrativa(enteTipologiaAmministrativa);
			ente.setStato(stato);
			em.persist(ente);
		}
		return ente;

	}
	@Override
	@Transactional
	public List<Ente> getEntiPaginatiFilteredPerCombos(String filter,int offset, int rows) {
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM Ente e ");
		sb.append(" WHERE e.denominazione IS NOT NULL ");
		if (filter != null && filter.length() > 0) {
			sb.append(" AND e.denominazione like :denominazione ");
		}
		Query q = em.createQuery(sb.toString());
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		q.setFirstResult(offset);
		q.setMaxResults(rows);
		List<Ente> listaEnti = q.getResultList();
		for (int i = 0; i < listaEnti.size(); i++) {
			// Iterazione anti-lazy
			listaEnti.get(i).getEnteTipologiaAmministrativa();

		}

		return listaEnti;

	}
	
	@Override
	@Transactional
	public int countAllEntiFiltered(String filter) {
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(e) FROM Ente e ");
	
		
		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE e.denominazione like :denominazione ");
		}
		
		Query q = em.createQuery(sb.toString());
				
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}
	
	@Override
	@Transactional
	public Ente createEnteIfNotExist2(Stato stato, EnteTipologiaAmministrativa enteTipologiaAmministrativa,
			String denominazione, String asiaAsip, String partitaIva, String codiceFiscale) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT  e ");
		sb.append("FROM Ente e WHERE ");

		List<String> criteria = new ArrayList<String>();
		if (stato != null)
			criteria.add("e.stato = :stato");
		if (enteTipologiaAmministrativa != null)
			criteria.add("e.enteTipologiaAmministrativa = :enteTipologiaAmministrativa");
		if (denominazione != null)
			criteria.add("e.denominazione = :denominazione");
		else
			criteria.add("e.denominazione IS NULL");

		if (partitaIva != null)
			criteria.add("e.partitaIva = :partitaIva");
		else
			criteria.add("e.partitaIva IS NULL");

		if (codiceFiscale != null)
			criteria.add("e.codiceFiscale = :codiceFiscale");
		else
			criteria.add("e.codiceFiscale IS NULL");

		if (asiaAsip != null)
			criteria.add("e.asiaAsip = :asiaAsip");
		else
			criteria.add("e.asiaAsip IS NULL");

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		Query q = em.createQuery(sb.toString());

		if (stato != null)
			q.setParameter("stato", stato);
		if (enteTipologiaAmministrativa != null)
			q.setParameter("enteTipologiaAmministrativa",
					enteTipologiaAmministrativa);
		if (denominazione != null)
			q.setParameter("denominazione", denominazione);
		if (asiaAsip != null)
			q.setParameter("asiaAsip", asiaAsip);
		if (partitaIva != null)
			q.setParameter("partitaIva", partitaIva);
		if (codiceFiscale != null)
			q.setParameter("codiceFiscale", codiceFiscale);

		Ente ente = null;
		List<Ente> listaEnte = q.getResultList();
		if (listaEnte != null && listaEnte.size() > 0) {
			ente = (Ente) listaEnte.get(0);
		} else {
			ente = new Ente();
			ente.setAsiaAsip(asiaAsip);
			ente.setCodiceFiscale(codiceFiscale);
			ente.setDenominazione(denominazione);
			ente.setPartitaIva(partitaIva);
			ente.setEnteTipologiaAmministrativa(enteTipologiaAmministrativa);
			ente.setStato(stato);
			em.persist(ente);
		}
		return ente;

	}
	
	@Override
	@Transactional
	public Ente getEnte(String den, String tipAmm, String stato) {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM Ente e ");
		List<String> criteria = new ArrayList<String>();
		
		if (den != null && den.length() > 0) {
			criteria.add("e.denominazione = :denominazione");
		}
	
		if (tipAmm != null && tipAmm.length() > 0) {
			criteria.add("e.enteTipologiaAmministrativa.descrizione = :tipAmm");
		}
		
		if (stato != null && stato.length() > 0) {
			criteria.add("e.stato.denominazione = :stato");
		}
		
		if (criteria != null && criteria.size() > 0) {
			sb.append("WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}
		
		Query q = em.createQuery(sb.toString());
		
		if (den != null && den.length() > 0) {
			q.setParameter("denominazione", den);
		}
		if (tipAmm != null && tipAmm.length() > 0) {
			q.setParameter("tipAmm", tipAmm);
		}
		if (stato != null && stato.length() > 0) {
			q.setParameter("stato", stato);
		}
		
		Ente eRes = null;
		List<Ente> listaEnte = q.getResultList();
		if (listaEnte != null && listaEnte.size() > 0) {
			eRes = (Ente) listaEnte.get(0);
		} 
		return eRes;
	}

	@Override
	@Transactional
	public EnteTipologiaAmministrativa getEnteTipologiaAmministrativa(String descr) {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM EnteTipologiaAmministrativa ta ");
		
		if (descr != null && descr.length() > 0) {
			sb.append("WHERE ta.descrizione = :descrizione ");
		}
		
		Query q = em.createQuery(sb.toString());
		if (descr != null && descr.length() > 0) {
			q.setParameter("descrizione", descr);
		}
		
		EnteTipologiaAmministrativa taRes = null;
		List<EnteTipologiaAmministrativa> listaTipAmm = q.getResultList();
		if (listaTipAmm != null && listaTipAmm.size() > 0) {
			taRes = (EnteTipologiaAmministrativa) listaTipAmm.get(0);
		}
		return taRes;
		
	}
	
	@Override
	@Transactional
	public Ente retrieveDefaultEnte() {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM Ente e order by e.idEnte ASC ");
		
		Query q = em.createQuery(sb.toString());
		
		List<Ente> entiList = (List<Ente>) q.getResultList();
		
		if (entiList != null && entiList.size() > 0) {
			return entiList.get(0);
			
		} else {
			return null;
		}
	}
}
