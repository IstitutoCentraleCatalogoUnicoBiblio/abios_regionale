package it.inera.abi.dao.jpa;

import it.inera.abi.dao.StatiWorkflowDao;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementazione classe DAO per l'entità Stato Biblioteca Workflow
 *
 */
@Repository
public class StatiWorkflowDaoJpa implements StatiWorkflowDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<StatoBibliotecaWorkflow> getAll() {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM StatoBibliotecaWorkflow");
		Query query = em.createQuery(sb.toString());
		List<StatoBibliotecaWorkflow> listaStatoBibliotecaWorkflow = query
				.getResultList();
		return listaStatoBibliotecaWorkflow;
	}
}
