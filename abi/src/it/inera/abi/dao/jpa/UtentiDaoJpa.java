package it.inera.abi.dao.jpa;

import it.inera.abi.dao.ConstraintKeyViolationException;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.UtentiDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Profili;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UtentiDaoJpa implements UtentiDao {

	public static final String DUPLICATED_LOGIN_ERROR_MESSAGE="Username già presente sul database.";
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<Utenti> getUsersByRole(Integer idProfilo){
	
		Profili profilo = em.find(Profili.class, idProfilo);
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u ");
		sb.append("FROM Utenti u ");

		if(idProfilo!=null){
			sb.append("WHERE :profilo MEMBER OF u.profilis ");
		}
		
		Query q = em.createQuery(sb.toString());
		if(idProfilo!=null){
			q.setParameter("profilo", profilo);
		}
		
		List<Utenti> utenti  = q.getResultList();
		
		return utenti;
	}
	
	@Override
	@Transactional
	public List<Utenti> ricercaUtenti(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir) {

		String nome = null;
		String cognome = null;
		String incarico = null;
		String login = null;
		String email = null;
		Profili ruolo = null;
		Boolean enabled=null;
		
		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u ");
		sb.append("FROM Utenti u ");
		if (keys != null && keys.size() > 0) {

			if (keys.containsKey("nome")
					&& StringUtils.isNotBlank((String)keys.get("nome"))) {
				nome = (String)keys.get("nome");
			}

			if (keys.containsKey("cognome")
					&& StringUtils.isNotBlank((String)keys.get("cognome"))) {
				cognome = (String)keys.get("cognome");
			}

			if (keys.containsKey("login")
					&& StringUtils.isNotBlank((String)keys.get("login"))) {
				login =(String) keys.get("login");
			}

			if (keys.containsKey("incarico")
					&& StringUtils.isNotBlank((String)keys.get("incarico"))) {
				incarico = (String)keys.get("incarico");
			}
			
			if (keys.containsKey("email")
					&& StringUtils.isNotBlank((String)keys.get("email"))) {
				email = (String)keys.get("email");
			}
				
			if (keys.containsKey("ruolo")
					&& StringUtils.isNotBlank((String)keys.get("ruolo"))) {
				ruolo = em.find(Profili.class, new Integer((String) keys.get("ruolo")));
			}	
			
			if (keys.containsKey("enabled")	&& keys.get("enabled")!=null) {
				enabled=(Boolean)(keys.get("enabled"));
			}	

			if (nome != null) {
				criteria.add("u.nome like :nome");
			}
			if (cognome != null) {
				criteria.add("u.cognome like :cognome");
			}
			if (login != null) {
				criteria.add("u.login like :login");
			}
			if (incarico != null) {
				criteria.add("u.incarico like :incarico");
			}
			if (email != null) {
				criteria.add("u.email like :email");
			}
			if (ruolo != null) {
				criteria.add(":ruolo MEMBER OF u.profilis");
			}
			if (enabled != null) {
				criteria.add("u.enabled= :enabled");
			}
			
			if ((nome != null) || (cognome != null) || (login != null)
					|| (incarico != null) || (email != null) || (ruolo != null) || (enabled!=null))
				sb.append("WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		if (StringUtils.isNotBlank(orderByField)) {
			sb.append(" ORDER BY ").append(orderByField);
			if (StringUtils.isNotBlank(orderByDir)) {
				sb.append(" ").append(orderByDir);
			}
		}

		Query q = em.createQuery(sb.toString());

		if (nome != null) {
			String nome2 = "%".concat(nome).concat("%");
			q.setParameter("nome", nome2);
		}
		if (cognome != null) {
			String cognome2 = "%".concat(cognome).concat("%");
			q.setParameter("cognome", cognome2);
		}
		if (incarico != null) {

			String incarico2 = "%".concat(incarico).concat("%");
			q.setParameter("incarico", incarico2);/* " + incarico + " */
		}
		if (login != null) {
			String login2 = "%".concat(login).concat("%");
			q.setParameter("login", login2);
		}
		if (email != null) {
			String email2 = "%".concat(email).concat("%");
			q.setParameter("email", email2);
		}
		if (ruolo != null) {
			q.setParameter("ruolo", ruolo);
		}
		
		if(enabled!=null){
			q.setParameter("enabled", enabled);
		}

		q.setFirstResult(offset);
		q.setMaxResults(rows);
		List<Utenti> listaUtenti = q.getResultList();
		for (int i = 0; i < listaUtenti.size(); i++) {

			Utenti utente = listaUtenti.get(i);

		}
		return listaUtenti;
	}

	@Override
	@Transactional
	public int countUtenti(HashMap<String, Object> keys) {
		String nome = null;
		String cognome = null;
		String incarico = null;
		String login = null;
		String email = null;
		Boolean enabled=null;
		Profili ruolo = null;
		List<String> criteria = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(u) ");
		sb.append("FROM Utenti u ");
		if (keys != null && keys.size() > 0) {

			if (keys.containsKey("nome") && StringUtils.isNotBlank((String)keys.get("nome"))) {
				nome =(String) keys.get("nome");
			}

			if (keys.containsKey("cognome")	&& StringUtils.isNotBlank((String)keys.get("cognome"))) {
				cognome =(String) keys.get("cognome");
			}

			if (keys.containsKey("login") && StringUtils.isNotBlank((String)keys.get("login"))) {
				login =(String) keys.get("login");
			}

			if (keys.containsKey("incarico") && StringUtils.isNotBlank((String)keys.get("incarico"))) {
				incarico =(String) keys.get("incarico");
			}
			
			if (keys.containsKey("email") && StringUtils.isNotBlank((String) keys.get("email"))) {
				email =(String) keys.get("email");
			}
			
			if (keys.containsKey("ruolo")&& StringUtils.isNotBlank((String)keys.get("ruolo"))) {
				ruolo = em.find(Profili.class, new Integer((String) keys.get("ruolo")));
			}
			
			if (keys.containsKey("enabled")	&& keys.get("enabled")!=null) {
				enabled=(Boolean)(keys.get("enabled"));
			}	

			if (nome != null) {
				criteria.add("u.nome like :nome");
			}
			if (cognome != null) {
				criteria.add("u.cognome like :cognome");
			}
			if (login != null) {
				criteria.add("u.login like :login");
			}
			if (incarico != null) {
				criteria.add("u.incarico like :incarico");
			}
			if (email != null) {
				criteria.add("u.email like :email");
			}
			if (ruolo != null) {
				criteria.add(":ruolo MEMBER OF u.profilis");
			}
			
			if (enabled != null) {
				criteria.add("u.enabled= :enabled");
			}
			
			if ((nome != null) || (cognome != null) || (login != null) || (incarico != null) || (email != null) || (ruolo != null) || (enabled!=null))
				sb.append("WHERE ");
		}

		for (int i = 0; i < criteria.size(); i++) {

			if (i > 0) {
				sb.append(" AND ");
			}
			sb.append(criteria.get(i));
		}

		Query q = em.createQuery(sb.toString());

		if (nome != null) {
			String nome2 = "%".concat(nome).concat("%");
			q.setParameter("nome", nome2);
		}
		if (cognome != null) {
			String cognome2 = "%".concat(cognome).concat("%");
			q.setParameter("cognome", cognome2);
		}
		if (incarico != null) {
			String incarico2 = "%".concat(incarico).concat("%");
			q.setParameter("incarico", incarico2);/* " + incarico + " */
		}
		if (login != null) {
			String login2 = "%".concat(login).concat("%");
			q.setParameter("login", login2);
		}
		if (email != null) {
			String email2 = "%".concat(email).concat("%");
			q.setParameter("email", email2);
		}
		if (ruolo != null) {
			q.setParameter("ruolo", ruolo);
		}		
		
		if(enabled!=null){
			q.setParameter("enabled", enabled);
		}

		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();
	}

	@Override
	@Transactional
	public void addUtente(Utenti utente, List<Profili> profili) throws DuplicateEntryException {
		// controllo se ha l'id settato, altrimenti è un nuovo utente
		if (utente.getIdUtenti() == null) {
			HashMap<String, Object> keys = new HashMap<String, Object>();
			keys.put("login", utente.getLogin());
			if(countUtenti(keys)>0){
				throw new DuplicateEntryException(DUPLICATED_LOGIN_ERROR_MESSAGE);
			}
			utente.setProfilis(profili);
			utente.setDataModificaPassword(new Date(System.currentTimeMillis()));
			utente.setEnabled(true);
			em.merge(utente);
		} else {
			Utenti oldUtente = em.find(Utenti.class, utente.getIdUtenti());
			oldUtente.setCognome(utente.getCognome());
			oldUtente.setEmail(utente.getEmail());
			oldUtente.setFax(utente.getFax());
			oldUtente.setIncarico(utente.getIncarico());
			oldUtente.setNome(utente.getNome());
			oldUtente.setNote(utente.getNote());
			if (StringUtils.isNotBlank(utente.getPassword()) && utente.getPassword()!=null) {
				oldUtente.setPassword(utente.getPassword());
				oldUtente.setDataModificaPassword(new Date(System.currentTimeMillis()));
			}
			oldUtente.setTel(utente.getTel());
			oldUtente.setProfilis(profili);
			oldUtente.setEnabled(true);
			em.merge(oldUtente);
		}
		

	}
	
	
	@Override
	@Transactional
	public void updateUtenteFromModificaAccount(Utenti utente) {
		
		Utenti oldUtente = em.find(Utenti.class, utente.getIdUtenti());
		oldUtente.setEmail(utente.getEmail());
		oldUtente.setFax(utente.getFax());
		if (StringUtils.isNotBlank(utente.getPassword()) && utente.getPassword() != null) {
			oldUtente.setPassword(utente.getPassword());
			oldUtente.setDataModificaPassword(new Date(System.currentTimeMillis()));
		}
		oldUtente.setTel(utente.getTel());
		oldUtente.setEnabled(true);
		em.merge(oldUtente);
		
	}
	

	@Override
	@Transactional
	public List<Profili> getListProfile() {
		StringBuffer sb = new StringBuffer();
		sb.append("FROM Profili");
		Query query = em.createQuery(sb.toString());
		List<Profili> listaProfili = query.getResultList();
		return listaProfili;
	}



	@Override
	@Transactional
	public void removeUtente(int id_utenti) {
		Utenti u = em.find(Utenti.class, id_utenti);
		
		/* Cancello l'associazione dell'utente con Biblioteca */
		Query query = em.createQuery("UPDATE FROM Biblioteca set utenteUltimaModifica = :nullValue WHERE utenteUltimaModifica = :utenteUltimaModifica");
		query.setParameter("nullValue", null);
		query.setParameter("utenteUltimaModifica", u);
		query.executeUpdate();
		
		em.remove(u);

	}

	@Override
	@Transactional
	public Utenti getDataUtente(int id_utenti) {
		Utenti utente = em.find(Utenti.class, id_utenti);
		utente.getProfilis().size();
		return utente;
	}

	// @Override
	// @Transactional
	// public void addProfilo(Profili profilo) {
	// Profili p = em.find(Profili.class, profilo.getIdProfili());
	// p.setDenominazione(profilo.getDenominazione());
	//
	//
	// Iterator<Utenti> utsIt = profilo.getUtentis().iterator();
	// while (utsIt.hasNext()) {
	// Utenti utente = (Utenti) utsIt.next();
	//
	// em.persist(utente);
	//
	//
	// p.getUtentis().add(utente);
	// }
	//
	// em.persist(p);
	//
	//
	// }
	
	
	/* ALTRI CASI */
	/* 1: nuovoutente */
	// Utenti u1 = new Utenti();
	// u1.setNome("Renato");
	// u1.setCognome("ddddddd");
	// u1.setLogin("ddddddd");
	// u1.setPassword("ddddddd");
	// u1.setIncarico("ddddddd");
	// u1.setEmail("ddddddd");
	// u1.setTel("11111");
	// u1.setFax("222222");
	// u1.setNote("note");
	//
	// List<Profili> prs = new ArrayList<Profili>();
	// Profili p1 = em.find(Profili.class, 1);
	// Profili p2 = em.find(Profili.class, 2);
	// prs.add(p1);
	// prs.add(p2);
	//
	//
	// u1.setProfilis(prs);
	// em.persist(u1);

	/* 2: aggiornamento piu' profili */
	/*
	 * int[] idProfs = {1,2}; Utenti u1 = em.find(Utenti.class, 155);
	 * u1.setProfilis(new ArrayList<Profili>()); for (int i = 0; i <
	 * idProfs.length; i++) { Profili p1 = em.find(Profili.class, idProfs[i]);
	 * u1.getProfilis().add(p1); }
	 */
	// em.persist(u1); // <- NON SERVE!!!!!!!! aggiorna in automatico quando fa
	// la commit (esce da lmetodo con il @Transactional

	/* 3: cancellazione di un o piu' profili */
	/*
	 * Utenti u1 = em.find(Utenti.class, 155); Iterator<Profili> profsIt =
	 * u1.getProfilis().iterator(); List<Profili> toDelete = new
	 * ArrayList<Profili>(); while (profsIt.hasNext()) { Profili tempProfilo =
	 * (Profili) profsIt.next(); if (tempProfilo.getIdProfili() == 1 ||
	 * tempProfilo.getIdProfili() == 2 ) { toDelete.add(tempProfilo); }
	 * System.out.println(tempProfilo.getDenominazione()); }
	 * u1.getProfilis().removeAll(toDelete);
	 */
	// em.persist(u1); // <- NON SERVE!!!!!!!! cancella in automatico quando fa
	// la commit (esce da lmetodo con il @Transactional

	/*
	 * 4: rimuove un utente, anche i profili associati Utenti u1 =
	 * em.find(Utenti.class, 155); em.remove(u1);
	 */

	/* DAL FORMATO DI SCAMBIO */
	@Override
	@Transactional
	public boolean isUtenteManageBiblioByCodAbiSenzaStato(String codiceAbi,	String loginUtente) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	@Transactional
	public List<Utenti> getListaUtenti() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("FROM Utenti u order by u.login asc");
		
		Query q = em.createQuery(sb.toString());
		
		List<Utenti> results = q.getResultList();

		return results;
		
	}

	/**
	 * per l'autenticazione
	 * il BINARY si mette per il case insensitive 
	 * @author reschini
	 */
	@Override
	@Transactional
	public Utenti findByName(String username) {
		Query query = em.createQuery("from Utenti u where u.login = BINARY(:username)");
		query.setParameter("username", username);
		List<Utenti> listaUtenti = (List<Utenti>) query.getResultList();
		if (listaUtenti == null || listaUtenti.size() == 0) return null;
		Utenti utente = listaUtenti.get(0);
		utente.getProfilis().size(); // precarico i profili per il lazy
		return utente;
	}

	@Override
	@Transactional
	public void saveUtente(Utenti utente) {
		em.merge(utente);
	}
	
	@Override
	@Transactional
	public int assegnaBiblioDaCodice(int id_utente, Biblioteca b) {
		int assign = -1;
		Utenti utente = em.find(Utenti.class, id_utente);
		List<Biblioteca> biblist = (List<Biblioteca>) utente.getBibliotecasGestite();
		if (!biblist.contains(b)) {
			biblist.add(b);
			assign = 1;
		}
		
		utente.setBibliotecasGestite(biblist);
		em.merge(utente);
		if (assign == 1)
			return b.getIdBiblioteca().intValue();
		else return assign;
		
	}
	
	@Override
	@Transactional
	public int assegnaBiblioDaUtente(int utenteFrom, int utenteTo) {
		Utenti from = em.find(Utenti.class, utenteFrom);
		List<Biblioteca> bibListUtenteFrom = (List<Biblioteca>) from.getBibliotecasGestite();
		
		if (bibListUtenteFrom.size() != 0) {
			Utenti to = em.find(Utenti.class, utenteTo);
			List<Biblioteca> bibListUtenteTo = (List<Biblioteca>) to.getBibliotecasGestite();
			
			for (Biblioteca entry : bibListUtenteFrom) {
				if (!bibListUtenteTo.contains(entry))
					bibListUtenteTo.add(entry);
			}
			
			to.setBibliotecasGestite(bibListUtenteTo);
			em.merge(to);
			return 1;
		}
		else return -1;
		
	}
	
	@Override
	@Transactional
	public void rimuoviBiblio(int id_utente, boolean all, List<Biblioteca> bibToRemovelist) {
		Utenti utente = em.find(Utenti.class, id_utente);
		if (!all) {
			List<Biblioteca> biblist = (List<Biblioteca>) utente.getBibliotecasGestite();
			
			for (Biblioteca entry : bibToRemovelist) {
				if (biblist.contains(entry))
					biblist.remove(entry);
			}
			
			utente.setBibliotecasGestite(biblist);
			em.merge(utente);
		}
		else {
			utente.setBibliotecasGestite(null);
			em.merge(utente);
		}
	}
	
	@Override
	@Transactional
	public int countUtentiPaginati(String searchLogin, int id_utente) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT COUNT(*) ");
		sb.append("FROM Utenti u ");
		sb.append("WHERE u.idUtenti != :id ");
		
		if (searchLogin != null && searchLogin.length() > 0) 
			sb.append("AND u.login LIKE :login");
			
		Query q = em.createQuery(sb.toString());
		
		q.setParameter("id", new Integer(id_utente));
		if (searchLogin != null && searchLogin.length() > 0) {
			String tmpSearchLogin = "%".concat(searchLogin).concat("%");
			q.setParameter("login", tmpSearchLogin);
			
		}
		
		Number countResult = (Number) q.getSingleResult();
		return countResult.intValue();		
		
	}
	
	@Override
	@Transactional
	public List<Utenti> getUtentiPaginati(String searchLogin, int id_utente, int start, int limit) {
		StringBuffer sb = new StringBuffer();

		sb.append("FROM Utenti u ");
		sb.append("WHERE u.idUtenti != :id ");
		
		if (searchLogin != null && searchLogin.length() > 0) {
			sb.append("AND u.login LIKE :login");			
			
		}
		
		Query q = em.createQuery(sb.toString());
		
		q.setParameter("id", new Integer(id_utente));
		if (searchLogin != null && searchLogin.length() > 0) {
			String tmpSearchLogin = "%".concat(searchLogin).concat("%");
			q.setParameter("login", tmpSearchLogin);
			
		}
		
		q.setMaxResults(limit);
		q.setFirstResult(start);

		List result = q.getResultList();
		return result;
		
	}
	
	@Override
	@Transactional
	public void assegnaBibliosAdUtentis(List<Integer> idUsersList, List<Integer> idBibsList) {

		if (idUsersList.size() != 0 && idBibsList.size() != 0) {
			/* Scorro la lista di utenti */
			for (Integer entry : idUsersList) {
				Utenti u = em.find(Utenti.class, entry.intValue());
				List<Biblioteca> userbibs = (List<Biblioteca>) u.getBibliotecasGestite();

				for (Integer bib : idBibsList) {
					/* Scorro la lista di biblioteche da assegnare */
					Biblioteca b = em.find(Biblioteca.class, bib.intValue());

					if (!userbibs.contains(b))
						userbibs.add(b);
				}
				u.setBibliotecasGestite(userbibs);
				em.merge(u);
			}
		}
	}
	
	@Override
	@Transactional
	public void isBiblioModifiedBY(int idUser) throws ConstraintKeyViolationException {
		Utenti u = em.find(Utenti.class, idUser);
		StatoBibliotecaWorkflow occ = em.find(StatoBibliotecaWorkflow.class, 2);
		StatoBibliotecaWorkflow rev = em.find(StatoBibliotecaWorkflow.class, 3);
		
		StringBuffer query = new StringBuffer("select count(b) from Biblioteca b where b.utenteUltimaModifica = :utente and (b.statoBibliotecaWorkflow = :occ or b.statoBibliotecaWorkflow = :rev)");
		Query q = em.createQuery(query.toString());
		q.setParameter("utente", u);
		q.setParameter("occ", occ);
		q.setParameter("rev", rev);
		
		Number countResult = (Number) q.getSingleResult();
		if (countResult.intValue() > 0) {
			throw new ConstraintKeyViolationException("Impossibile rimuovere l'utente <b>"+u.getLogin()+"</b> poiché ha in modifica/revisione " +
					"almeno una biblioteca.<br>Per effettuare questa operazione, rendere definitive le biblioteche in modifica/revisione.");
		}
			
	}
	
	@Override
	@Transactional
	public void isGestoreOrCreatore(int idUser) throws ConstraintKeyViolationException {
		Utenti u = em.find(Utenti.class, idUser);
		
		StringBuffer query = new StringBuffer("select count(b) from NuovaBiblioteca b where b.utenti_gestore = :utente or b.utenti_creatore = :utente");
		Query q = em.createQuery(query.toString());
		q.setParameter("utente", u);
		
		Number countResult = (Number) q.getSingleResult();
		
		query = new StringBuffer("select count(b) from Biblioteca b join b.utentisGestori utenteGestore where utenteGestore = :utente");
		
		q = em.createQuery(query.toString());
		q.setParameter("utente", u);
		
		Number countResult2 = (Number) q.getSingleResult();
		
		int sumBib = countResult.intValue() + countResult2.intValue(); 
		
		
		if (sumBib > 0) {
			throw new ConstraintKeyViolationException("Impossibile rimuovere l'utente <b>" + u.getLogin() + "</b> poiché è creatore/gestore di " +
					"una biblioteca.<br>Per effettuare questa operazione, rimuovere le biblioteche gestite dall'utente selezionato.");
		}
			
	}

	@Override
	@Transactional
	public void resetPassword(int idUser, String encoded) {
		Utenti u = em.find(Utenti.class, idUser);
		u.setPassword(encoded);
		u.setDataModificaPassword(new Date(System.currentTimeMillis()));
		u.setEnabled(true);
		em.merge(u);
	}

	@Override
	@Transactional
	public List<Utenti> getUsersWithElapsedPasswords() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" from Utenti u ");
		sb.append(" where u.dataModificaPassword!=null ");
		sb.append(" and u.enabled !=null ");
		sb.append(" and u.enabled !=false ");
		sb.append(" and DATEDIFF(NOW(),u.dataModificaPassword)>180 ");
		
		Query q = em.createQuery(sb.toString());
		
		List<Utenti> utentis = q.getResultList();
		return utentis;
	}
	
	@Override
	@Transactional
	public List<Utenti> getUsersWithTenDaysRemaningRange() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" from Utenti u ");
		sb.append(" where u.dataModificaPassword!=null ");
		sb.append(" and u.enabled !=null ");
		sb.append(" and u.enabled !=false ");
		sb.append(" and (DATEDIFF(NOW(),u.dataModificaPassword) between 170 AND 180) ");
		
		Query q = em.createQuery(sb.toString());
		
		List<Utenti> utentis = q.getResultList();
		return utentis;
	}

	@Override
	@Transactional
	public void disableAccount(int idUser) {
		
		Utenti utente = em.find(Utenti.class, idUser);
		utente.setEnabled(false);
		em.merge(utente);
		
	}
	
	@Override
	@Transactional
	public Utenti findByValidateCode(String code) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" from Utenti u ");
		sb.append(" where u.codiceValidazione != null and u.codiceValidazione = BINARY(:validateCode) ");
		
		Query q = em.createQuery(sb.toString());
		q.setParameter("validateCode", code);
		
		Utenti u;
		
		try {
			u = (Utenti) q.getSingleResult();
			return u;
			
		} catch (NoResultException nre) {
			return null;
		}
		
	}
}