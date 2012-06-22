package it.inera.abi.dao.jpa;

import it.inera.abi.dao.ContattiDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContattiDaoJpa implements ContattiDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void saveContatti(Contatti nuovoContatto) {
		em.merge(nuovoContatto);
	}
	
	public List<ContattiTipo> getTipologieContatti(){
		StringBuffer sb = new StringBuffer();
		sb.append("FROM ContattiTipo  ");
		Query q = em.createQuery(sb.toString());
		List<ContattiTipo> listaContattiTipo = q.getResultList();
		return listaContattiTipo;
	}

	@Override
	@Transactional
	public List<Contatti> loadContattiByIdBiblioteca(int id_biblio) {
		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setIdBiblioteca(id_biblio);
		StringBuffer sb = new StringBuffer();
		sb.append("FROM Contatti  ");
		sb.append(" WHERE biblioteca = :biblioteca");
		Query q = em.createQuery(sb.toString());
		q.setParameter("biblioteca", biblioteca);

		List<Contatti> listaContatti = q.getResultList();
		return listaContatti;
	}

	@Override
	@Transactional
	public List<Contatti> loadContattiByIdContatti(int id_contatti) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removeContatto(int id_contatto) {
		Contatti contatto = em.find(Contatti.class, id_contatto);
		em.remove(contatto);
	}
	
	

}
