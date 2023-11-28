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
