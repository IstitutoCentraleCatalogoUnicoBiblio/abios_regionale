package it.inera.abi.dao.jpa;

import it.inera.abi.dao.DenominazioniPrecedentiDao;
import it.inera.abi.dao.DenominazioniPrecedentiDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.DenominazioniPrecedenti;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DenominazioniPrecedentiJpa implements DenominazioniPrecedentiDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<DenominazioniPrecedenti> getDenominazioniPrecedenti(
			int id_biblioteca) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM DenominazioniPrecedenti ");
		sb.append(" WHERE biblioteca = :biblioteca ");
		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);
		List<DenominazioniPrecedenti> listaDenominazioniPrecedenti = q.getResultList();
		return listaDenominazioniPrecedenti;
	}

	@Override
	@Transactional
	public void setListaDenominazioniPrecedenti(int id_biblioteca, List<DenominazioniPrecedenti> listaDenominazioni) {
		// TODO

	}

	@Override
	@Transactional
	public void addDenominazionePrecendente(
			DenominazioniPrecedenti denominazione) {
		if (denominazione.getIdDenominazioniPrecedenti() == null) {
			em.persist(denominazione);	
		} else {
			em.merge(denominazione);
		}
		

	}

	@Override
	@Transactional
	public void removeDenominazionePrecedente(int id_record) {
		DenominazioniPrecedenti d = em.find(DenominazioniPrecedenti.class,
				id_record);
		em.remove(d);

	}

}
