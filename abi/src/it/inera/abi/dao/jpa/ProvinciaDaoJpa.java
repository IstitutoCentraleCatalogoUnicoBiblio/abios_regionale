package it.inera.abi.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.inera.abi.dao.ProvinciaDao;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;

@Repository
public class ProvinciaDaoJpa implements ProvinciaDao {

	@PersistenceContext
	private EntityManager em;

	/* DAL FORMATO DI SCAMBIO */
	@Override
	@Transactional
	public String getSiglaProvinciaByCodIstat(String codIstatProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String getDescrizioneProvinciaByCodIstat(String codIstatProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Provincia getProvinciaByCodIstat(String codIstatProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Provincia getProvinciaByDenominazione(String denominazione) {
		StringBuffer sb = new StringBuffer();

		if (denominazione != null) {
			sb.append("FROM Provincia p ");
			sb.append("WHERE p.denominazione = :denominazione");
			Query q = em.createQuery(sb.toString());
			q.setParameter("denominazione", denominazione);
			Provincia p =(Provincia)q.getSingleResult();
			return p;
		}
		else return null;
	}
	
	@Override
	@Transactional
	public List<Provincia> getProvince(Integer id_regione) {
		Regione regione = null;
		
		if (id_regione != null) {
			regione = em.find(Regione.class, id_regione);
		}
		
		StringBuffer sb = new StringBuffer();

		sb.append("FROM Provincia p ");
		if (regione != null) {
			sb.append("WHERE p.regione = :regione ORDER BY p.denominazione ASC");
		}
		else{
			sb.append(" ORDER BY p.denominazione ASC ");
		}
		Query q = em.createQuery(sb.toString());
		if (regione != null) {
			q.setParameter("regione", regione);
		}
		List<Provincia> listaProvince = q.getResultList();
		for (int i = 0; i < listaProvince.size(); i++) {
			// Iterazione anti-lazy
			listaProvince.get(i).getRegione();

		}
		return listaProvince;

	}
	
	@Override
	@Transactional
	public List<Provincia> getProvince(Integer id_regione, String ricerca) {
		Regione regione = null;
		
		if (id_regione != null) {
			regione = em.find(Regione.class, id_regione);
		}
		
		StringBuffer sb = new StringBuffer();

		sb.append("FROM Provincia p ");
		if (regione != null) {
			sb.append("WHERE p.regione = :regione ");
			if (ricerca != null) {
				sb.append(" AND p.denominazione like :ricerca ");
			}
		}
		else if (ricerca != null) {
			sb.append("WHERE p.denominazione like :ricerca ");
		}		
		
		sb.append(" ORDER BY p.denominazione ASC ");
		
		Query q = em.createQuery(sb.toString());
		if (regione != null) {
			q.setParameter("regione", regione);
		}
		if (ricerca != null) {
			String tmpSearch = "%".concat(ricerca).concat("%");
			q.setParameter("ricerca", tmpSearch);
		}
		List<Provincia> listaProvince = q.getResultList();
		for (int i = 0; i < listaProvince.size(); i++) {
			// Iterazione anti-lazy
			listaProvince.get(i).getRegione();

		}
		return listaProvince;

	}
	
	@Override
	@Transactional
	public int countAll() {
		Query query = em.createQuery("SELECT COUNT(p) FROM Provincia p");
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public Provincia getProvinciaBySigla(String sigla) {
		StringBuffer sb = new StringBuffer();

		if (sigla != null && sigla.length() == 2) {
			sb.append("FROM Provincia p ");
			sb.append("WHERE p.sigla = :sigla");
			Query q = em.createQuery(sb.toString());

			q.setParameter("sigla", sigla);
			Provincia p = (Provincia) q.getSingleResult();
			return p;
		}
		else return null;
	}
	
	@Override
	@Transactional
	public Provincia getProvinciaByIdProvincia(int id) {
		StringBuffer sb = new StringBuffer();
		
		if (id != 0) {
			sb.append("FROM Provincia p ");
			sb.append("WHERE p.idProvincia = :id");
			Query q = em.createQuery(sb.toString());

			q.setParameter("id", id);
			Provincia p = (Provincia) q.getSingleResult();
			return p;
		}
		else return null;
	}
	
	@Override
	@Transactional
	public List<Provincia> getProvincePaginate(String query, int start, int limit) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("FROM Provincia p ");
		if (query != null && query.length() > 0) {
			sb.append("WHERE p.sigla like :query ");
			sb.append("OR p.denominazione like :query ");
		}
		
		Query q = em.createQuery(sb.toString());
		
		if (query != null && query.length() > 0) {
			String tmp = query.concat("%");
			q.setParameter("query", tmp);
			
		}
		
		if (limit != -1) {
			q.setMaxResults(limit);
		}
		q.setFirstResult(start);

		List<Provincia> result = q.getResultList();
		return result;
		
	}
}
