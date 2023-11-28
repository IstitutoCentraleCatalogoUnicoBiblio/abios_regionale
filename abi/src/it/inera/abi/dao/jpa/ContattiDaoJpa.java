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

/**
 * Implementazione classe DAO per l'entità Contatti
 *
 */
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
