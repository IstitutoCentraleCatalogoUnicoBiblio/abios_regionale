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

import it.inera.abi.dao.ProvinciaDao;
import it.inera.abi.dao.RegioneDao;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Utenti;

/**
 * Implementazione classe DAO per l'entità Regione
 *
 */
@Repository
public class RegioneDaoJpa implements RegioneDao {

    @PersistenceContext
    private EntityManager em;

	@Override
	@Transactional
	public String getDenominazioneRegioneByCodIstatProvincia(
			String codIstatProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Regione getRegioneByCodIstatProvincia(String codIstatProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Regione> getRegioni() {
		/*DA ESTENDERE CON PARAMETRI DI RICERCA--COME PER GLI UTENTI*/
		StringBuffer sb=new StringBuffer();
		
		sb.append("FROM Regione r ORDER BY r.denominazione ASC");
		Query q = em.createQuery(sb.toString());
		List<Regione> listaRegioni = q.getResultList();
		for (int i = 0; i < listaRegioni.size(); i++) {

			listaRegioni.get(i).getStato();

		}
		return listaRegioni;
		
	}
	
	@Override
	@Transactional
	public List<Regione> getRegioni(String ricerca) {
		/*DA ESTENDERE CON PARAMETRI DI RICERCA--COME PER GLI UTENTI*/
		StringBuffer sb = new StringBuffer();
		if (ricerca != null && ricerca.length() > 0) {
			sb.append("FROM Regione r where r.denominazione like :ricerca order by r.denominazione ASC ");
		}
		else {
			sb.append("FROM Regione r ORDER BY r.denominazione ASC");
		}
		
		Query q = em.createQuery(sb.toString());
		
		if (ricerca != null && ricerca.length() > 0) {
			String tmpSearchDen = "%".concat(ricerca).concat("%");
			q.setParameter("ricerca", tmpSearchDen);
		}
		
		List<Regione> listaRegioni = q.getResultList();
		for (int i = 0; i < listaRegioni.size(); i++) {

			listaRegioni.get(i).getStato();

		}
		return listaRegioni;
		
	}
	
	@Override
	@Transactional
	public int countAll() {
		Query query = em.createQuery("SELECT COUNT(r) FROM Regione r");
		Number countResult = (Number) query.getSingleResult();
		return countResult.intValue();
	}
	
	@Override
	@Transactional
	public Regione getRegione(String sigla) {
		StringBuffer sb = new StringBuffer();

		if (sigla != null) {
			sb.append("FROM Regione r ");
			sb.append("WHERE r.denominazione = :denominazione");
			Query q = em.createQuery(sb.toString());
			q.setParameter("denominazione", sigla);
			Regione r = (Regione) q.getSingleResult();
			return r;
		}
		else return null;
	}
	
	@Override
	@Transactional
	public Regione getRegioneByIdProvincia(int idProv) {
		StringBuffer sb = new StringBuffer();
		
		if (idProv != 0) {
			sb.append("select r from Provincia p join p.regione r where p.idProvincia = :idProv ");
			Query q = em.createQuery(sb.toString());
			q.setParameter("idProv", idProv);
			
			Regione r = (Regione) q.getSingleResult();
			return r;
		}
		else return null;
	}

}
