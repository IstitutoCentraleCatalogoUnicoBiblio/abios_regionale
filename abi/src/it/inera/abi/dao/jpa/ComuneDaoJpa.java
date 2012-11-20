package it.inera.abi.dao.jpa;

import it.inera.abi.dao.ComuneDao;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.plaf.synth.Region;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

/**
 * Implementazione classe DAO per l'entità Comune
 *
 */
@Repository
public class ComuneDaoJpa implements ComuneDao {

	@PersistenceContext
	private EntityManager em;

	private Log log = LogFactory.getLog(ComuneDaoJpa.class);

	@Override
	@Transactional
	public void saveComune(Comune comune) {
		em.persist(comune);
	}

	@Override
	@Transactional
	public Comune getComuneByCodIstat(String codIstatComune) {
		log.info("Retrive del comune tramite codIstat " + codIstatComune);
		Comune comune = null;

		Query query = em
		.createQuery("from Comune as c where (c.codiceIstat = :codiceIstat)");
		query.setParameter("codiceIstat", codIstatComune);
		log.info("query " + query.toString());
		List queryList = query.getResultList();
		log.info("queryList " + queryList);
		log.info("queryList.size() " + queryList.size());
		if (queryList != null && queryList.size() > 0) {
			comune = (Comune) queryList.get(0);
		}
		return comune;
	}

	@Override
	@Transactional
	public String getDenominazioneComuneByCodIstat(String codIstatComune) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String getDenominazioneProvinciaByCodIstatComune(
			String codIstatComune) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String getDenominazioneRegioneByCodIstatComune(String codIstatComune) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Comune> getComuniByIdProvincia(int id_provincia) {

		Provincia provincia = null;

		if (id_provincia != 0) {
			provincia = em.find(Provincia.class, id_provincia);
		}

		StringBuffer sb = new StringBuffer();

		sb.append("FROM Comune c ");
		if (provincia != null) {			
			sb.append("WHERE c.provincia = :provincia ");
		}		

		sb.append("ORDER BY c.denominazione ASC");
		Query q = em.createQuery(sb.toString());

		if (provincia != null) {
			q.setParameter("provincia", provincia);
		}

		List<Comune> listaComuni = q.getResultList();
		for (int i = 0; i < listaComuni.size(); i++) {
			// Iterazione anti-lazy
			listaComuni.get(i).getProvincia();

		}
		return listaComuni;

	}


	@Override
	@Transactional
	public List<Comune> getComuniFiltered(Integer id_provOrReg, boolean fromProv, String filter, int offset, int rows, String sortField, String sortDir) {

		Provincia provincia = null;
		Regione regione = null;

		if (id_provOrReg != null) {
			if (fromProv) {
				provincia = em.find(Provincia.class, id_provOrReg);
			}
			else {
				regione = em.find(Regione.class, id_provOrReg);
			}
		}

		StringBuffer sb = new StringBuffer();

		if (provincia != null) {
			sb.append(" FROM Comune c ");
		}
		else if (regione != null) {
			sb.append("select c FROM Comune c ");
			sb.append(" join c.provincia p ");
		}
		else {
			sb.append("FROM Comune c ");
		}

		if ((filter != null && filter.length() > 0) || provincia != null || regione != null) {
			sb.append(" WHERE ");
		}

		if (provincia != null) {
			sb.append(" c.provincia = :provincia ");
		}
		else if (regione != null) {
			sb.append(" p.regione = :regione ");
		}

		if ((filter != null && filter.length() > 0) && (provincia != null || regione != null)) {
			sb.append(" AND c.denominazione like :denominazione ");
		}
		else if((filter != null && filter.length() > 0)) {
			sb.append(" c.denominazione like :denominazione ");
		}

//		sb.append(" ORDER BY c.denominazione ASC");

		if(sortField!=null){
			sb.append(" ORDER BY "+getComuniSortField(sortField)+" ");
			if(sortDir!=null){
				sb.append(" "+sortDir);
			}
		}
		Query q = em.createQuery(sb.toString());
		if (provincia != null) {
			q.setParameter("provincia", provincia);		
		}
		else if (regione != null) {
			q.setParameter("regione", regione);		
		}
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}	
		q.setFirstResult(offset);
		if (rows != -1) {
			q.setMaxResults(rows);
		}
		List<Comune> listaComuni = q.getResultList();
		Iterator<Comune> itc = listaComuni.iterator();
		while (itc.hasNext()) {
			//Iterazione anti-lazy
			Comune comune = (Comune) itc.next();

		}

		return listaComuni;
	}

	public String getComuniSortField(String modelField){
		if(modelField.equals("denominazione")){
			return "c.denominazione";
		}else if(modelField.equals("denominazioneProvincia")){
			return "c.provincia.denominazione";
		}else if(modelField.equals("codice_istat")){
			return "c.codiceIstat";
		}else {//if(modelField.equals("codice_finanze"))
			return "c.codiceFinanze";
		}
	}

	@Override
	@Transactional
	public int getCountComuniPaginati(int id_provOrReg, String sigla, boolean fromProv) {
		Provincia provincia = null;
		Regione regione = null;

		if (id_provOrReg != 0) {
			if (fromProv) {
				provincia = em.find(Provincia.class, id_provOrReg);
			}
			else {
				regione = em.find(Regione.class, id_provOrReg);
			}
		}

		StringBuffer sb = new StringBuffer();

		if (provincia != null) {
			sb.append(" FROM Comune c ");
		}
		else if (regione != null) {
			sb.append("select c FROM Comune c ");
			sb.append(" join c.provincia p ");
		}
		else {
			sb.append(" FROM Comune c ");
		}

		if ((sigla != null && sigla.length() > 0) || provincia != null || regione != null) {
			sb.append(" WHERE ");
		}

		if (provincia != null) {
			sb.append(" c.provincia = :provincia ");
		}
		else if (regione != null) {
			sb.append(" p.regione = :regione ");
		}

		if ((sigla != null && sigla.length() > 0) && (provincia != null || regione != null)) {
			sb.append(" AND c.denominazione like :denominazione ");
		}
		else if((sigla != null && sigla.length() > 0)) {
			sb.append(" c.denominazione like :denominazione ");
		}

		sb.append(" ORDER BY c.denominazione ASC");
		Query q = em.createQuery(sb.toString());
		if (provincia != null) {
			q.setParameter("provincia", provincia);		
		}
		else if (regione != null) {
			q.setParameter("regione", regione);		
		}
		if (sigla != null && sigla.length() > 0) {
			String tmpFilter = "%".concat(sigla).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}	


		List<Comune> listaComuni = q.getResultList();
		Iterator<Comune> itc = listaComuni.iterator();
		while (itc.hasNext()) {
			//Iterazione anti-lazy
			Comune comune = (Comune) itc.next();

		}

		return listaComuni.size();
	}

	@Override
	@Transactional
	public int countAllByProvinciaAdnFiltered(Integer idProvincia,String filter) {
		Provincia provincia=null;
		if(idProvincia!=null)
			provincia = em.find(Provincia.class, idProvincia);

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(c) FROM Comune c ");

		if((filter != null  && filter.length() > 0)|| idProvincia!=null)
			sb.append(" WHERE ");

		if(idProvincia!=null){
			sb.append(" c.provincia = :provincia ");
		}
		if ((filter != null && filter.length() > 0) && idProvincia!=null) {
			sb.append(" AND c.denominazione like :denominazione ");
		}else if((filter != null && filter.length() > 0) ){
			sb.append(" c.denominazione like :denominazione ");
		}

		Query q = em.createQuery(sb.toString());
		if(idProvincia!=null){
			q.setParameter("provincia", provincia);
		}
		if (filter != null && filter.length() > 0) {
			String tmpFilter = "%".concat(filter).concat("%");
			q.setParameter("denominazione", tmpFilter);
		}
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}
	@Override
	@Transactional
	public void assegnaComuniAProvincia(int idProvincia, List<Integer> idComunis){
		Provincia provincia = em.find(Provincia.class, idProvincia);
		Iterator<Integer> it = idComunis.iterator();
		while (it.hasNext()) {
			Integer idComune = (Integer) it.next();
			Comune comuneTmp = em.find(Comune.class, idComune);
			comuneTmp.setProvincia(provincia);
			em.merge(comuneTmp);
		}
	}

}
