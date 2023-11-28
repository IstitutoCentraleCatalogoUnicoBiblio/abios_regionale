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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.inera.abi.dao.SistemiBibliotecheDao;
import it.inera.abi.persistence.SistemiBiblioteche;

/**
 * Implementazione classe DAO per l'entità Sistemi di Biblioteche
 *
 */
@Repository
public class SistemiBibliotecheDaoJpa implements SistemiBibliotecheDao {

	@PersistenceContext
	private EntityManager em;
	//TODO da migrare nelle tabelle dinamiche generiche DynaTabJpa
	@Override
	@Transactional
	public int countAllSistemiBiblioteche() {

		Query query = em
				.createQuery(" SELECT COUNT(s) FROM SistemiBiblioteche s ");
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public int countAllSistemiBibliotecheFiltered(String filter) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(s) FROM SistemiBiblioteche s ");
		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE s.descrizione like :descrizione ");
		}

		Query q = em.createQuery(sb.toString());
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("s.descrizione", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	//TODO da eliminare, già spostato in DynaTabJpa
	@Override
	@Transactional
	public List<SistemiBiblioteche> getSistemiBibliotecheFiltered(
			String filter, int rows, int offset) {

		StringBuffer sb = new StringBuffer();
		
		sb.append(" FROM SistemiBiblioteche s ");

		if (filter != null && filter.length() > 0) {
			sb.append(" WHERE s.descrizione like :descrizione ");
		}
		Query q = em.createQuery(sb.toString());
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("s.descrizione", tmpFilter);
		}

		q.setMaxResults(rows);
		q.setFirstResult(offset);

		return q.getResultList();
	}

	@Override
	@Transactional
	public void addSistemiBiblioteche(String descrizione) {
		SistemiBiblioteche sistemiBiblioteche = new SistemiBiblioteche();
		sistemiBiblioteche.setDescrizione(descrizione);
		em.persist(sistemiBiblioteche);
	}

	@Override
	@Transactional
	public void removeSistemiBiblioteche(int id_record) {
		SistemiBiblioteche sistemiBiblioteche = em.find(
				SistemiBiblioteche.class, id_record);
		em.remove(sistemiBiblioteche);

	}

}
