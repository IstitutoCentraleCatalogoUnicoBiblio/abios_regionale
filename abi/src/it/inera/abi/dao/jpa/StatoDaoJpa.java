package it.inera.abi.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.inera.abi.dao.StatoDao;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Stato;

@Repository
public class StatoDaoJpa implements StatoDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void saveStato(Stato stato) {
		em.persist(stato);
	}

	@Override
	@Transactional
	public Stato loadStatoById(int id) {
		Object o = em.find(Stato.class, id);
		return (Stato) o;
	}

	@Override
	@Transactional
	public Stato loadStatoBySigla(String sigla) {
		Query query = em.createQuery("from Stato WHERE sigla = :sigla or denominazione = :denominazione");
		query.setParameter("sigla", sigla);
		query.setParameter("denominazione", sigla);
		List<Stato> res = query.getResultList();
		if (res == null || res.size() == 0) {
			return null;
		}
		return res.get(0);
	}

	@Override
	@Transactional
	public Stato loadStatoByDenominazione(String denominazione) {
		Query query = em
				.createQuery("from Stato WHERE denominazione = :denominazione");
		query.setParameter("denominazione", denominazione);
		Object res = query.getSingleResult();
		return res == null ? null : (Stato) res;
	}

	@Override
	@Transactional
	public List<Stato> getStatiPaginatiFilteredPerCombosFilteredPerCombos(String filter, int offset, int rows,String sortField,
			String sortDir) {

		StringBuffer sb = new StringBuffer();

		sb.append("FROM Stato c ");

		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE c.denominazione like :denominazione ");
		}
		
		if(sortField!=null){
			sb.append(" ORDER BY  c."+sortField+" ");
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		
		
		Query q = em.createQuery(sb.toString());

		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		q.setFirstResult(offset);
		q.setMaxResults(rows);
		List<Stato> listaStati = q.getResultList();
		
		return listaStati;
	}

	@Override
	@Transactional
	public int countAllStatiFiltered(String filter) {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(s) FROM Stato s ");

		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE s.denominazione like :denominazione ");
		}

		Query q = em.createQuery(sb.toString());

		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}


}
