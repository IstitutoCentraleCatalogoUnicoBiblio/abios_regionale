package it.inera.abi.dao.jpa;

import it.inera.abi.dao.DenominazioniAlternativeDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.DenominazioniAlternative;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DenominazioniAlternativeJpa implements DenominazioniAlternativeDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<DenominazioniAlternative> getDenominazioniAlternative(int id_biblioteca) {

		Biblioteca biblioteca = em.find(Biblioteca.class, id_biblioteca);
		StringBuffer sb = new StringBuffer();	
		sb.append(" FROM DenominazioniAlternative ");
		sb.append(" WHERE biblioteca = :biblioteca ");
		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);
		List<DenominazioniAlternative> listaDenominazioniAlternative = q.getResultList();
		return listaDenominazioniAlternative;
	}

	@Override
	@Transactional
	public void setListaDenominazioniAlternative(int id_biblioteca,
			List<DenominazioniAlternative> listaDenominazioni) {
		// TODO

	}

	@Override
	@Transactional
	public void addDenominazioneAlternativa(
			DenominazioniAlternative denominazione) {
		if (denominazione.getIdDenominazioniAlternative() == null) {
			em.persist(denominazione);	
		} else {
			em.merge(denominazione);
		}
	}

	@Override
	@Transactional
	public void removeDenominazioneAlternativa(int id_record) {
		DenominazioniAlternative d = em.find(DenominazioniAlternative.class, id_record);
		em.remove(d);
	}

}
