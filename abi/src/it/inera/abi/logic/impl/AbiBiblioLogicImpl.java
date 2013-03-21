package it.inera.abi.logic.impl;

import it.inera.abi.commons.Roles;
import it.inera.abi.commons.StatiBiblioteca;
import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.ContattiDao;
import it.inera.abi.dao.DenominazioniAlternativeDao;
import it.inera.abi.dao.DenominazioniPrecedentiDao;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.EnteDao;
import it.inera.abi.dao.EntryNotFoundException;
import it.inera.abi.dao.StatiWorkflowDao;
import it.inera.abi.dao.UtentiDao;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.logic.AbiBiblioDifferenze;
import it.inera.abi.logic.AbiBiblioLogic;
import it.inera.abi.logic.TrasferimentoBiblioteca;
import it.inera.abi.logic.UserActionLog;
import it.inera.abi.logic.auth.AuthLogic;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Bibliografia;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.CodiciPK;
import it.inera.abi.persistence.CodiciTipo;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Comunicazioni;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiAntichiConsistenza;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.Geolocalizzazione;
import it.inera.abi.persistence.NuovaBiblioteca;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.Photo;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.Stato;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.StatoCatalogazione;
import it.inera.abi.persistence.TipologiaFunzionale;
import it.inera.abi.persistence.Utenti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe che implementa la logica delle principali operazioni di ricerca/modifica delle biblioteche
 *
 */
@Service
public class AbiBiblioLogicImpl implements AbiBiblioLogic {

	private BiblioDao biblioDao;
	private StatiWorkflowDao statiWorkflowDao;
	private ContattiDao contattiDao;
	private DenominazioniPrecedentiDao denominazioniPrecedentiDao;
	private DenominazioniAlternativeDao denominazioniAlternativeDao;
	private EnteDao enteDao;

	// per prendere le biblio del utente
	@Autowired protected AuthLogic authLogic;
	@Autowired private UtentiDao utentiDao;

	@Autowired private DynaTabDao dynaTabDao;

	@Autowired private TrasferimentoBiblioteca trasferimentoBiblioteca;
	@Autowired private AbiBiblioDifferenze abiBiblioDifferenze;

	@Autowired private UserActionLog userActionLog; // logger per le azioni di salvataggio/modifica dell'utente


	private Log _log = LogFactory.getLog(AbiBiblioLogicImpl.class);

	@Autowired
	@Required
	public void setEnteDao(EnteDao enteDao) {
		this.enteDao = enteDao;
	}

	@Autowired
	@Required
	public void setBiblioDao(BiblioDao biblioDao) {
		this.biblioDao = biblioDao;
	}

	@Autowired
	@Required
	public void setStatiWorkflowDao(StatiWorkflowDao statiWorkflowDao) {
		this.statiWorkflowDao = statiWorkflowDao;
	}

	@Autowired
	@Required
	public void setContattiDao(ContattiDao contattiDao) {
		this.contattiDao = contattiDao;
	}

	@Autowired
	@Required
	public void setDenominazioniPrecedentiDao(
			DenominazioniPrecedentiDao denominazioniPrecedentiDao) {
		this.denominazioniPrecedentiDao = denominazioniPrecedentiDao;
	}

	@Autowired
	@Required
	public void setDenominazioniAlternativeDao(
			DenominazioniAlternativeDao denominazioniAlternativeDao) {
		this.denominazioniAlternativeDao = denominazioniAlternativeDao;
	}

	@Override
	public List<Biblioteca> ricercaBiblio(HashMap<String, Object> keys,	int offset, int rows, String orderByField, String orderByDir) {
		/* Se si tratta dell'utente con ruolo VEDI_TUTTE vengono selezionate tutte le biblioteche */
		User user = authLogic.retrieveLoggedUser();
		String username = user.getUsername();
		Utenti utenteGestore = utentiDao.findByName(username);
		if (!Roles.isUserInRole(Roles.VEDI_TUTTE, utenteGestore) && (keys == null || (keys != null && !keys.containsKey("loginUtenteGestore")))) {
			if (keys == null) keys = new HashMap<String, Object>();
			keys.put("utenteGestore", utenteGestore);
		}

		return biblioDao.ricercaBiblio(keys, offset, rows, orderByField, orderByDir);
	}

	@Override
	public int countAllBibliotecheFiltered(HashMap<String, Object> keys) {
		/* Se si tratta dell'utente con ruolo VEDI_TUTTE vengono selezionate tutte le biblioteche */
		User user = authLogic.retrieveLoggedUser();
		String username = user.getUsername();
		Utenti utenteGestore = utentiDao.findByName(username);
		if (!Roles.isUserInRole(Roles.VEDI_TUTTE, utenteGestore) && (keys == null || (keys != null && !keys.containsKey("loginUtenteGestore")))) {
			if (keys == null) keys = new HashMap<String, Object>();
			keys.put("utenteGestore", utenteGestore);
		}

		return biblioDao.countAllBibliotecheFiltered( keys);
	}

	@Override
	/* Restituzione biblioteche assegnate ad un utente */
	public List<Biblioteca> ricercaBibliotecheUtente(int id_utente, int offset, int rows, String orderByField, String orderByDir) {
		Utenti utente = new Utenti();
		utente.setIdUtenti(new Integer(id_utente));
		HashMap<String, Object> key = new HashMap<String, Object>();
		key.put("utenteGestore", utente);

		return biblioDao.ricercaBiblio(key, offset, rows, orderByField, orderByDir);
	}

	@Override
	/* Conteggio per le biblioteche assegnate ad un utente */
	public int countAllBibliotecheUtente(int id_utente) {		
		Utenti utente = new Utenti();
		utente.setIdUtenti(new Integer(id_utente));
		HashMap<String, Object> key = new HashMap<String, Object>();
		key.put("utenteGestore", utente);

		return biblioDao.countAllBibliotecheFiltered(key);
	}

	@Override
	@Transactional
	public Biblioteca getBibliotecaByIdWithAntiLazyIteration(int id_biblioteca) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);
		List<Regolamento> regolamenti=biblioteca.getRegolamentos();

		Iterator<Regolamento> itRegolamenti= regolamenti.iterator();
		while (itRegolamenti.hasNext()) {
			//Iterazione anti-lazy
			Regolamento regolamento = (Regolamento) itRegolamenti.next();
		}
		List<Bibliografia> bibliografias = biblioteca.getBibliografias();
		Iterator<Bibliografia> itBibliografia = bibliografias.iterator();
		while (itBibliografia.hasNext()) {
			//Iterazione anti-lazy
			Bibliografia bibliografia = (Bibliografia) itBibliografia.next();
		}

		List<Comunicazioni> comunicazionis = biblioteca.getComunicazionis();
		Iterator<Comunicazioni> itComunicazioni = comunicazionis.iterator();
		while (itComunicazioni.hasNext()) {
			//Iterazione anti-lazy
			Comunicazioni comunicazioni = (Comunicazioni) itComunicazioni.next();
		}

		List<Codici> codicis = biblioteca.getCodicis();
		Iterator<Codici> itCodici =codicis.iterator();
		while (itCodici.hasNext()) {
			//Iterazione anti-lazy
			Codici codici = (Codici) itCodici.next();
		}

		List<StatoCatalogazione> catalogaziones = biblioteca.getStatoCatalogaziones();
		Iterator<StatoCatalogazione> itStati =catalogaziones.iterator();
		while (itStati.hasNext()) {
			//Iterazione anti-lazy
			StatoCatalogazione stato = (StatoCatalogazione) itStati.next();
		}

		List<Biblioteca> figli = biblioteca.getBibliotecasFigli();
		Iterator<Biblioteca> itFigli = figli.iterator();
		while (itFigli.hasNext()) {
			//Iterazione anti-lazy
			Biblioteca figlia = (Biblioteca) itFigli.next();
		}

		return biblioteca;
	}

	@Override
	@Transactional
	public Biblioteca getBibliotecaById(int id_biblioteca) {

		return biblioDao.getBibliotecaById(id_biblioteca);
	}

	@Override
	@Transactional
	public List<Contatti> getListaContattiBibliotecaById(int id_biblioteca) {

		return contattiDao.loadContattiByIdBiblioteca(id_biblioteca);

	}

	public List<ContattiTipo> getTipologieContatti() {
		return contattiDao.getTipologieContatti();
	}

	@Override
	public List<StatoBibliotecaWorkflow> getListaStatiWorkFlow() {

		return statiWorkflowDao.getAll();
	}

	@Override
	public int countAll() {

		return biblioDao.countAll();
	}

	@Override
	public void saveContatti(Contatti nuovoContatto, boolean modifica) {
		contattiDao.saveContatti(nuovoContatto);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica contatto: valore="+nuovoContatto.getValore()+(modifica?" id_record="+nuovoContatto.getIdContatti():"")+" - id_biblioteca="+nuovoContatto.getBiblioteca().getIdBiblioteca());
	}

	@Override
	public void removeContatto(int id_contatto) {
		contattiDao.removeContatto(id_contatto);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione contatto : id="+id_contatto+" - id_biblioteca=N/A");
	}

	@Override
	@Transactional
	public void aggiornaDenominazioneUfficiale(String denominazione, int id_biblioteca) {

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);
		// biblioteca.setIdBiblioteca(id_biblioteca);
		biblioteca.setDenominazioneUfficiale(denominazione);
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica denominazione ufficiale: valore="+denominazione +" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public List<DenominazioniPrecedenti> getDenominazioniPrecedenti(
			int id_biblioteca) {
		return denominazioniPrecedentiDao.getDenominazioniPrecedenti(id_biblioteca);

	}

	@Override
	public void removeDenominazionePrecedente(int id_record) {
		denominazioniPrecedentiDao.removeDenominazionePrecedente(id_record);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione denominazione precedente : id="+id_record+" - id_biblioteca=N/A");

	}

	@Override
	public void addDenominazionePrecendente(DenominazioniPrecedenti denominazione, boolean modifica) {

		denominazioniPrecedentiDao.addDenominazionePrecendente(denominazione);


		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica denominazione precedente: valore="+denominazione.getDenominazione() +((modifica?"- id_record="+denominazione.getIdDenominazioniPrecedenti():""))+" - id_biblioteca="+denominazione.getBiblioteca().getIdBiblioteca());

	}

	@Override
	public List<DenominazioniAlternative> getDenominazioniAlternative(
			int id_biblioteca) {
		return denominazioniAlternativeDao.getDenominazioniAlternative(id_biblioteca);

	}

	@Override
	public void removeDenominazioneAlternativa(int id_record) {
		denominazioniAlternativeDao.removeDenominazioneAlternativa(id_record);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione denominazione alternativa : id="+id_record+" - id_biblioteca=N/A");
	}

	@Override
	public void addDenominazioneAlternativa(DenominazioniAlternative denominazione ,boolean modifica) {

		denominazioniAlternativeDao.addDenominazioneAlternativa(denominazione);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica denominazione alternativa: valore="+denominazione.getDenominazione() +((modifica?"- id_record="+denominazione.getIdDenominazioniAlternative():""))+" - id_biblioteca="+denominazione.getBiblioteca().getIdBiblioteca());
	}

	@Override
	@Transactional
	public void aggiornaIndirizzo(HashMap<String, Object> param, int id_biblioteca) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);

		biblioteca.setIndirizzo((String) param.get("indirizzo"));
		biblioteca.setFrazione((String) param.get("frazione"));
		biblioteca.setCap(((String) param.get("cap")));

		Comune c = new Comune();
		c.setIdComune((Integer) param.get("idComune"));
		biblioteca.setComune(c);

		Geolocalizzazione geolocalizzazione = biblioteca.getGeolocalizzazione();
		if(geolocalizzazione==null){
			geolocalizzazione= new Geolocalizzazione();
			geolocalizzazione.setIdBiblioteca(id_biblioteca);
		}
		Double latitudine=(Double)param.get("latitudine");
		if(latitudine!=null){
			geolocalizzazione.setLatitudine((latitudine));
		}
		Double longitudine=(Double) param.get("longitudine");
		if(longitudine!=null){
			geolocalizzazione.setLongitudine(longitudine);
		}

		biblioDao.saveChild(geolocalizzazione);
		biblioteca.setGeolocalizzazione(geolocalizzazione);

		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica indirizzo:" 
				+"- Via-Piazza:"+((String) param.get("indirizzo")!=null?(String) param.get("indirizzo"):"")
				+"- Frazione:"+((String) param.get("frazione")!=null?(String) param.get("frazione"):"")
				+"- Cap:"+((String) param.get("cap")!=null?(String) param.get("cap"):"")
				+"- Comune:"+(c!=null?c.getDenominazione():"")
				+"- Latitudine:"+((Double) param.get("latitudine")!=null?(Double) param.get("latitudine"):"")
				+"- Longitudine:"+((Double) param.get("longitudine")!=null?(Double) param.get("longitudine"):"")
				+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<Biblioteca> getPuntiDiServizioDecentrati(int id_biblioteca) {

		return biblioDao.getPuntiDiServizioDecentrati(id_biblioteca);
	}

	@Override
	public void addPuntoDiServizioDecentrato(int idBibliotecaPadre, String isilPrFiglia, String isilNrFiglia) {
		String isilFiglia = isilPrFiglia.concat(Utility.zeroFill(isilNrFiglia, 4));
		String[] bibs = new String[]{isilFiglia};
		Biblioteca[] bibFiglia = biblioDao.getBibliotecheByCodABI6CharsCode(bibs, 0, 1);
		
		biblioDao.addPuntoDiServizioDecentrato(idBibliotecaPadre, bibFiglia[0].getIdBiblioteca());

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica punto di servizio decentrato: id_record="+idBibliotecaPadre+" - id_biblioteca="+bibFiglia[0].getIdBiblioteca());
	}

	@Override
	public List<Biblioteca> getPuntiDiServizioDecentratiPossibili( String isil_provincia, String filter, int rows, int offset) {

		return biblioDao.getPuntiDiServizioDecentratiPossibili(isil_provincia, filter, rows, offset);

	}

	@Override
	public void removePuntoDiServizioDecentrato(int id_bibloteca) {
		biblioDao.removePuntoDiServizioDecentrato(id_bibloteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione punto di servizio decentrato: id_record="+id_bibloteca+" - id_biblioteca=N/A");

	}

	@Override
	public int countAllByIsilProvinciaAndFiltered(String isil_provincia, String filter) {
		return biblioDao.countAllPuntiDecentratiByIsilProvinciaAndFiltered(isil_provincia, filter);
	}

	@Override
	public List<SistemiBiblioteche> getSistemiBibliotecheByIdBiblioteca(int id_biblioteca) {
		return biblioDao.getSistemiBibliotecheByIdBiblioteca(id_biblioteca);
	}

	@Override
	public void addSistemaBiblioteca(int id_biblioteca,	int id_sistema_biblioteche) {
		biblioDao.addSistemaBiblioteca(id_biblioteca, id_sistema_biblioteche);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica sistema di biblitoeche: id_record="+id_sistema_biblioteche+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche) {
		biblioDao.removeSistemaBiblioteca(id_biblioteca, id_sistema_biblioteche);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione sistema di biblitoeche: id_record="+id_sistema_biblioteche+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void updateBiblioteca(Biblioteca biblioteca) {
		biblioDao.saveBiblioteca(biblioteca);
	}

	@Override
	@Transactional
	public void updateProfiloStoricoSede(HashMap<String, Object> params, int id_biblioteca) {

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);

		biblioteca.setEdificioAppositamenteCostruito((Boolean) params.get("appositamenteCostruito"));
		biblioteca.setEdificioMonumentale((Boolean) params.get("edificioMonumentale"));
		biblioteca.setEdificioDenominazione((String) params.get("denominazioneEdificio"));
		biblioteca.setEdificioDataCostruzione((String) params.get("dataCostruzione"));

		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica profilo storico e sede:" 
				+" Edificio appositamente costruito:"+((Boolean) params.get("appositamenteCostruito")!=null?(Boolean) params.get("appositamenteCostruito")+"":"")
				+"- Edificio monumentale:"+((Boolean) params.get("edificioMonumentale")!=null?(Boolean) params.get("edificioMonumentale")+"":"")
				+"- Denominazione edificio:"+((String) params.get("denominazioneEdificio")!=null?(String) params.get("denominazioneEdificio"):"")
				+"- Data costruzione:"+((String) params.get("dataCostruzione")!=null?(String) params.get("dataCostruzione"):"")
				+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	@Transactional
	public void updateInfoLocali(HashMap<String, Object> params,
			int id_biblioteca) {

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);
		if(params.get("mqtot")!=null)
			biblioteca.setMqTotali((Integer) params.get("mqtot"));
		else biblioteca.setMqTotali(null);

		if(params.get("mqpubblici")!=null)
			biblioteca.setMqPubblici((Integer) params.get("mqpubblici"));
		else biblioteca.setMqPubblici(null);

		if(params.get("mqmagazzini")!=null)
			biblioteca.setMlMagazzini((Integer)params.get("mqmagazzini"));
		else biblioteca.setMlMagazzini(null);

		if(params.get("mqaperti")!=null)
			biblioteca.setMlAperti((Integer) params.get("mqaperti"));
		else biblioteca.setMlAperti(null);

		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica informazioni locali:" 
				+" Metri quadri totali:"+(params.get("mqtot")!=null?(Integer) params.get("mqtot")+"":"")
				+" - Metri quadri pubblici:"+(params.get("mqpubblici")!=null?(Integer) params.get("mqpubblici")+"":"")
				+" - Metri quadri magazzini:"+(params.get("mqmagazzini")!=null?(Integer)params.get("mqmagazzini")+"":"")
				+" - Metri quadri aperti:"+(params.get("mqaperti")!=null?(Integer) params.get("mqaperti")+"":"")
				+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	@Transactional
	public void updateInfoPostazioni(HashMap<String, Object> params,
			int id_biblioteca) {

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);

		if(params.get("postiLettura")!=null)
			biblioteca.setPostiLettura((Integer) params.get("postiLettura"));
		else biblioteca.setPostiLettura(null);
		if(params.get("postiVideo")!=null)
			biblioteca.setPostiVideo((Integer) params.get("postiVideo"));
		else biblioteca.setPostiVideo(null);

		if(params.get("postiAscolto")!=null)
			biblioteca.setPostiAudio((Integer) params.get("postiAscolto"));
		else biblioteca.setPostiAudio(null);

		if(params.get("postiInternet")!=null)
			biblioteca.setPostiInternet((Integer) params.get("postiInternet"));
		else biblioteca.setPostiInternet(null);

		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica informazioni postazioni:" 
				+" Posti lettura:"+(params.get("postiLettura")!=null?(Integer) params.get("postiLettura")+"":"")
				+" - Posti video:"+(params.get("postiVideo")!=null?(Integer) params.get("postiVideo")+"":"")
				+" - Posti ascolto:"+(params.get("postiAscolto")!=null?(Integer) params.get("postiAscolto")+"":"")
				+" - Posti internet:"+(params.get("postiInternet")!=null?(Integer) params.get("postiInternet")+"":"")
				+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<Ente> getEntiPaginatiFilteredPerCombos(String filter,
			int offset, int rows) {

		return enteDao.getEntiPaginatiFilteredPerCombos(filter, offset, rows);


	}

	@Override
	public int countAllEntiFiltered(String filter) {

		return enteDao.countAllEntiFiltered(filter);
	}



	@Override
	@Transactional
	public void setEnte(int id_biblioteca, String denominazione,
			Integer idEnteTipologiaAmministrativa, Integer idStato) {

//		Ente ente = enteDao.createEnteIfNotExist2(stato, enteTipologiaAmministrativa, denominazione, null, null, null);
		Ente ente = enteDao.createEnteIfNotExist(denominazione, idEnteTipologiaAmministrativa, idStato, null, null, null);

		biblioDao.setEnte(id_biblioteca, ente);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica EnteTipologiaAmministrativa: id_record="+ente.getIdEnte()+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	@Transactional
	public void setAutonomiaAmministrativa(int idBiblio, HashMap<String, Object> params) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setAutonomiaAmministrativa((Boolean) params.get("autonomiaAmministrativa"));
		biblioteca.setStrutturaGerarchicaSovraordinata((String) params.get("strutturaGerarchicaSovranumeraria"));
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica tipologia amministrativa: " +
				"tipologia_amministrativa="+ (params.get("autonomiaAmministrativa")!=null?(Boolean) params.get("autonomiaAmministrativa")+"":"")+
				" - struttura gerarchica sovraordinata="+ (params.get("strutturaGerarchicaSovranumeraria")!=null?(String) params.get("strutturaGerarchicaSovranumeraria"):"")+" - id_biblioteca="+idBiblio);

	}

	@Override
	@Transactional
	public void setTipologiaFunzionale(int idBiblio, TipologiaFunzionale tipologiaFunzionale) {

		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setTipologiaFunzionale(tipologiaFunzionale);
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica tipologia funzionale: id_record=" +tipologiaFunzionale.getIdTipologiaFunzionale()+" - id_biblioteca="+idBiblio);
	}

	@Override
	public void setInfoFondazione(int idBiblio, HashMap<String, Object> params) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setDataFondazione((String) params.get("dataFondazione"));
		biblioteca.setDataIstituzione((String) params.get("dataIstituzione"));
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica informazioni fondazione:" 
				+ (params.get("dataFondazione")!=null?" - data_fondazione="+(String) params.get("dataFondazione"):" ")
				+ (params.get("dataIstituzione")!=null?" - data_istituzione="+(String) params.get("dataIstituzione"):" ")
				+" - id_biblioteca="+idBiblio);
	}

	@Override
	@Transactional
	public void setModalitaAccesso(int idBiblio, HashMap<String, Object> params) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setAccessoRiservato((Boolean) params.get("accessoRiservato"));

		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica informazioni accesso:"
				+( params.get("accessoRiservato")!=null?" accesso_riservato="+(Boolean) params.get("accessoRiservato"):"")
				+" - id_biblioteca="+idBiblio);

	}

	@Override
	public List<AccessoModalita> getAccessoModalitaByIdBiblioteca(int id_biblioteca) {
		return biblioDao.getAccessoModalitaByIdBiblioteca(id_biblioteca);
	}
	@Override
	public void removeModalitaAccesso(int id_biblioteca, int id_modalita_accesso){
		biblioDao.removeModalitaAccesso(id_biblioteca, id_modalita_accesso);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione modalità accesso: id_record="+id_modalita_accesso+" - id_biblioteca="+id_biblioteca);

	}
	@Override
	public void addModalitaAccesso(int id_biblioteca, int id_modalita_accesso){
		biblioDao.addModalitaAccesso(id_biblioteca, id_modalita_accesso);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica modalità accesso: id_record="+id_modalita_accesso+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<DestinazioniSociali> getDestinazioniSocialiByIdBiblioteca(int id_biblioteca) {

		return biblioDao.getDestinazioniSocialiByIdBiblioteca(id_biblioteca);

	}

	@Override
	public void addDestinazioniSociali(int id_biblioteca, int id_nuovaDestinazione,	String note) {
		biblioDao.addDestinazioniSociali(id_biblioteca, id_nuovaDestinazione,note);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica destinazione sociale: id_record="+id_nuovaDestinazione+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removeDestinazioneSociale(int id_biblioteca,int id_rimuoviDestinazione) {
		biblioDao.removeDestinazioneSociale(id_biblioteca,id_rimuoviDestinazione);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione destinazione sociale: id_record="+id_rimuoviDestinazione+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	@Transactional
	public void setAccessoHandicap(int idBiblio, Boolean value) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setAccessoHandicap(value);
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica accesso handicap: ="+(value!=null?(value?"Si":"No"):"Non specificato")+" - id_biblioteca="+idBiblio);

	}

	@Override
	@Transactional
	public void setRegolamento(HashMap<String, String> params, int idBiblio) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);
		Regolamento regolamento = new Regolamento();
		regolamento.setBiblioteca(biblioteca);
		regolamento.setUrl(params.get("url"));
		regolamento.setRiferimentoNormativa(params.get("riferimentoNormativo"));
		biblioDao.addRegolamento(regolamento);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica regolamento normativo - id_biblioteca="+idBiblio);
	}

	@Override
	public List<OrarioUfficiali> getOrariUfficialiByDay(int id_biblioteca,int id_day) {
		return	biblioDao.getOrariUfficialiByDay(id_biblioteca, id_day);	

	}

	@Override
	public void addNuovoOrarioGiornaliero(int id_biblioteca, OrarioUfficiali orarioUfficiali) {
		biblioDao.addNuovoOrarioGiornaliero(id_biblioteca,orarioUfficiali);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica orario ufficiale giornaliero - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void addNuovoOrarioGiornalieroCustom(int id_biblio, Vector<Integer> id_days, Date dalle, Date alle) {
		biblioDao.addNuovoOrarioGiornalieroCustom(id_biblio,id_days, dalle, alle);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica orario ufficiale personalizzato - id_biblioteca="+id_biblio);
	}

	@Override
	public void removeOrarioUfficiale(int id_orarioToRemove) {
		biblioDao.removeOrarioUfficiale(id_orarioToRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione orario ufficiale giornaliero - id_biblioteca=N/A");
	}

	@Override
	public List<OrarioVariazioni> getVariazioniOrari(int id_biblioteca) {

		return biblioDao.getVariazioniOrari(id_biblioteca);
	}

	@Override
	public void addNuovaVariazioneOrario(int id_biblioteca,	OrarioVariazioni orarioVariazioni) {
		biblioDao.addNuovaVariazioneOrario(id_biblioteca,orarioVariazioni);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica variazione orario: "+(orarioVariazioni.getIdOrarioVariazioni()!=null?" id_record="+orarioVariazioni.getIdOrarioVariazioni():"")+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, Date dalle, Date alle, String Periodo) {
		biblioDao.addNuovaVariazioneOrarioCustom(id_biblioteca, id_days, dalle, alle, Periodo);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio nuova variazione orario: id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removeVariazioneOrario(int id_variazioneOrarioToRemove) {
		biblioDao. removeVariazioneOrario(id_variazioneOrarioToRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione variazione orario: id_record="+id_variazioneOrarioToRemove+" - id_biblioteca=N/A");
	}

	@Override
	public List<OrarioChiusure> getPeriodiChiusuraByIdBiblio(int id_biblioteca) {

		return biblioDao.getPeriodiChiusuraByIdBiblio(id_biblioteca);
	}

	@Override
	public void addNuovoPeriodoChiusura(int id_biblioteca,	OrarioChiusure nuovoOrarioChiusure) {
		biblioDao.addNuovoPeriodoChiusura(id_biblioteca,nuovoOrarioChiusure);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica periodo chiusura: "+(nuovoOrarioChiusure.getIdOrarioChiusure()!=null?" id_record="+nuovoOrarioChiusure.getIdOrarioChiusure():"")+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removePeriodoChiusura(int id_chiusuraToRemove) {
		biblioDao.removePeriodoChiusura(id_chiusuraToRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione periodo chiusura: id_record="+id_chiusuraToRemove+" - id_biblioteca=N/A");
	}

	@Override
	@Transactional
	public void setConsistenzaFondiAntichi1830(int idBiblio, int id_consistenza) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);
		FondiAntichiConsistenza f = new FondiAntichiConsistenza();
		f.setIdFondiAntichiConsistenza(id_consistenza);
		biblioteca.setFondiAntichiConsistenza(f);
		biblioDao.updateBiblioteca(biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica fondi antichi consistenza: id_record="+id_consistenza+"- id_biblioteca="+idBiblio);

	}

	@Override
	public List<Patrimonio> getListaPatrimonioSpecializzazione(int id_biblioteca) {

		return biblioDao.getListaPatrimonioSpecializzazione(id_biblioteca);
	}

	@Override
	public void addPatrimonioSpeciale(int id_biblioteca, int id_nuovoPatr,	int quantita, int quantitaUltimoAnno) {
		biblioDao.addPatrimonioSpeciale( id_biblioteca, id_nuovoPatr, quantita,quantitaUltimoAnno);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica patrimonio speciale: id_record="+id_nuovoPatr+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr) {
		biblioDao.removePatrimonioSpeciale(id_biblioteca, id_rimuoviPatr);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione patrimonio speciale: id_record="+id_rimuoviPatr+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<DeweyLibero> getSpecializzazioniByIdBiblioteca(int id_biblioteca) {
		return biblioDao.getSpecializzazioniByIdBiblioteca(id_biblioteca);

	}

	@Override
	public void addSpecializzazionePatrimonio(int id_biblioteca, String dewey,	String descrizioneLibera) {

		biblioDao.addSpecializzazionePatrimonio(id_biblioteca, dewey,descrizioneLibera);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica specializzazione: id_record="+dewey+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removeSpecializzazionePatrimonio(int id_biblioteca,	String idr_removeRecord) {
		biblioDao.removeSpecializzazionePatrimonio(id_biblioteca, idr_removeRecord);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione specializzazione: id_record="+idr_removeRecord+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<FondiSpeciali> getFondiSpecialiByIdBiblioteca(int id_biblioteca) {

		return biblioDao.getFondiSpecialiByIdBiblioteca(id_biblioteca);
	}

	@Override
	public int countAllDenominazioneFondiSpecialiPossibili(String query) {

		return biblioDao.countAllDenominazioneFondiSpecialiPossibili(query);
	}

	@Override
	public List<FondiSpeciali> getDenominazioneFondiSpecialiPossibiliFiltered(
			String query, int start, int limit) {

		return biblioDao.getDenominazioneFondiSpecialiPossibiliFiltered(query, start, limit);
	}

	@Override
	public FondiSpeciali addFondoSpeciale(int idFondoSpecialeToAdd,int idBiblioteca, boolean modifica) {

		FondiSpeciali tmp=	biblioDao.addFondoSpeciale(idFondoSpecialeToAdd, idBiblioteca ,modifica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica fondo speciale: "+(modifica?"id_record="+idFondoSpecialeToAdd:"")+"- id_biblioteca="+idBiblioteca);
		return tmp;
	}

	@Override
	public int searchFondoSpeciale(FondiSpeciali fondoSpeciale) {

		return biblioDao.searchFondoSpeciale(fondoSpeciale);
	}

	@Override
	public int createFondoSpeciale(FondiSpeciali fondoSpeciale) {
		return biblioDao.createFondoSpeciale( fondoSpeciale);

	}

	@Override
	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord) {
		biblioDao.removeFondiSpeciali(id_biblioteca, id_removeRecord);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione fondo speciale: id_record="+id_removeRecord+"- id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<?> getListaVoci(int id_biblioteca, int idTabellaDinamica) {
		return biblioDao.getListaVoci( id_biblioteca, idTabellaDinamica);
	}

	@Override
	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(DynaTabDTO dynaTabDTODB, int id_biblioteca, int idTabellaDinamica) throws DuplicateEntryException {
		biblioDao.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(dynaTabDTODB,id_biblioteca,idTabellaDinamica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica "+Utility.extractClassName(DtoJpaMapping.getDynaClass(idTabellaDinamica).getName())+": id_record="+dynaTabDTODB.getId()+" - id_biblioteca="+id_biblioteca);
	}


	@Override
	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica) {

		biblioDao.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(id_biblioteca,id_rimuoviModalita,idTabellaDinamica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione "+Utility.extractClassName(DtoJpaMapping.getDynaClass(idTabellaDinamica).getName())+": id_record="+id_rimuoviModalita+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public FondiSpeciali updateFondoSpeciale(FondiSpeciali fondoSpeciale) {
		FondiSpeciali tmp =biblioDao.updateFondoSpeciale(fondoSpeciale);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica fondo speciale: id_record="+ fondoSpeciale.getIdFondiSpeciali()+" - id_biblioteca=N/A");
		return tmp;
	}

	@Override
	public int countAllSpogliMaterialBibliograficoPossibili(String filter) {
		return biblioDao.countAllSpogliMaterialBibliograficoPossibili(filter);
	}

	@Override
	public List<String> getSpogliMaterialBibliograficoPerPaginazioneCombobox(int start, int limit, String query) {
		return biblioDao.getListaSpogliMaterialBibliograficoPossibiliFiltered(start, limit, query);

	}

	@Override
	public List<SpogliBibliografici> getListaSpogliMarerialeBibliograficoByIdBiblio(int id_biblioteca) {
		return biblioDao.getListaSpogliMarerialeBibliograficoByIdBiblio(
				id_biblioteca);

	}

	@Override
	public void addSpoglioMaterialeBibliografico(String descrSpoglio,int id_biblioteca) throws DuplicateEntryException {
		biblioDao.addSpoglioMaterialeBibliografico( descrSpoglio,id_biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica fondo speciale: id_biblioteca=N/A");
	}

	@Override
	public void addSpoglioMaterialeBibliograficoRipristino(String descrSpoglio,int id_biblioteca) throws DuplicateEntryException {
		biblioDao.addSpoglioMaterialeBibliograficoRipristino( descrSpoglio,id_biblioteca);

	}

	@Override
	public void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio) {
		biblioDao.removeSpogliMaterialeBibliografico(id_rimuoviSpoglio);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione spoglio materiale bibliografico : id_record="+id_rimuoviSpoglio+" - id_biblioteca=N/A");
	}

	@Override
	public List<Pubblicazioni> getlistaPubblicazioniByIdBiblio(int id_biblioteca) {
		return biblioDao.getlistaPubblicazioniByIdBiblio(id_biblioteca);

	}

	@Override
	public void addPubblicazioni(Pubblicazioni nuovaPubblicazione, int id_biblioteca, boolean modifica) {
		biblioDao.addPubblicazioni(nuovaPubblicazione, id_biblioteca,modifica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica fondo speciale:"+(modifica?" id_record="+nuovaPubblicazione.getIdPubblicazioni():"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removePubblicazioni(int id_rimuoviPubblicazione) {
		biblioDao.removePubblicazioni( id_rimuoviPubblicazione);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione fondo speciale: id_record="+id_rimuoviPubblicazione+" - id_biblioteca=N/A");
	}

	@Override
	public void inserisciBibliograficaInfoCatalogazione(int id_biblio, String value) {
		biblioDao.inserisciBibliograficaInfoCatalogazione(id_biblio,value); 

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica informazioni bibliografiche: id_biblioteca="+id_biblio);

	}

	@Override
	public List<PartecipaCataloghiCollettiviMateriale> getPartecipaCataloghiCollettiviByIdBiblio(int id_biblioteca) {
		return biblioDao.getPartecipaCataloghiCollettiviByIdBiblio(id_biblioteca);

	}

	@Override
	public void addPartecipaCatalogoCollettivo(int id_biblioteca, PartecipaCataloghiCollettiviMateriale partecipaCataloghoCollettivoMateriale, boolean modifica) throws EntryNotFoundException {

		biblioDao.addPartecipaCatalogoCollettivo(id_biblioteca,partecipaCataloghoCollettivoMateriale,modifica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica partecipazione catalogo collettivo: "+(modifica?"id_record="+partecipaCataloghoCollettivoMateriale.getIdCataloghiCollettiviMateriale():"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public List<PartecipaCataloghiSpecialiMateriale> getPartecipaCataloghiSpecialiByIdBiblio( int id_biblioteca) {
		return biblioDao.getPartecipaCataloghiSpecialiByIdBiblio(id_biblioteca);
	}

	@Override
	public void addPartecipaCatalogoSpeciale(int id_biblioteca,	PartecipaCataloghiSpecialiMateriale partecipaCataloghoSpecialeMateriale, boolean modifica) {
		biblioDao.addPartecipaCatalogoSpeciale(id_biblioteca,partecipaCataloghoSpecialeMateriale,modifica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica partecipazione catalogo speciale: "+(modifica?"id_record="+partecipaCataloghoSpecialeMateriale.getIdCataloghiSpecialiMateriale():"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca) {
		biblioDao.removePartecipaCatalogoCollettivo(idRemove, id_biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione partecipazione catalogo collettivo: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);
	}
	@Override
	public void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca) {
		biblioDao.removePartecipaCatalogoSpeciale(idRemove, id_biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione partecipazione catalogo speciale: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca) {
		biblioDao.removePartecipaCatalogoGenerale(idRemove, id_biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione partecipazione catalogo generale: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<PartecipaCataloghiGenerali> getPartecipaCataloghiGeneraliByIdBiblio(int id_biblioteca) {
		return biblioDao.getPartecipaCataloghiGeneraliByIdBiblio(id_biblioteca);
	}

	@Override
	public void addPartecipaCatalogoGenerale(int id_biblioteca,	PartecipaCataloghiGenerali partecipaCataloghiGenerali, boolean modifica) {
		biblioDao.addPartecipaCatalogoGenerale(id_biblioteca,partecipaCataloghiGenerali,modifica);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica partecipazione catalogo generale: "+(modifica?"id_record="+partecipaCataloghiGenerali.getIdCataloghiGenerali():"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public List<Riproduzioni> getServiziRiproduzioniFornitureByIdBiblio(
			int id_biblioteca) {

		return biblioDao.getServiziRiproduzioniFornitureByIdBiblio(id_biblioteca);
	}

	@Override
	public void addServiziRiproduzioniForniture(int id_biblioteca, Integer idTipo, Boolean hasLocale, Boolean hasNazionale, Boolean hasInternazionale) {
		biblioDao.addServiziRiproduzioniForniture(id_biblioteca,idTipo,hasLocale,hasNazionale,hasInternazionale);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica riproduzioni forniture: id_record="+idTipo+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removeServiziRiproduzioniForniture(int idRemove,int id_biblioteca) {
		biblioDao.removeServiziRiproduzioniForniture(idRemove, id_biblioteca);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione riproduzioni forniture: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);

	}

	/* 
	 * Implementazione della logica per la restituzione ed il conteggio delle biblioteche 
	 * filtrate per i parametri inseriti per il report 
	 * 
	 */
	@Override
	public List<Biblioteca> ricercaBiblioReport(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir) {

		return biblioDao.ricercaBiblioReport(keys, offset, rows, orderByField, orderByDir);

	}

	@Override
	public int countAllBibliotechePerReport(HashMap<String, Object> keys) {

		return biblioDao.countAllBibliotechePerReport(keys);

	}

	@Override
	public void setServizioBibliograficoInternoEsterno(int id_biblioteca, Boolean hasAttivoInformazioniBibliografiche, Boolean hasServizioBibliograficoInterno, Boolean hasServizioBibliograficoEsterno) {
		biblioDao.setServizioBibliograficoInternoEsterno(id_biblioteca, hasAttivoInformazioniBibliografiche, hasServizioBibliograficoInterno, hasServizioBibliograficoEsterno);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica servizio bibliografico interno/esterno: - id_biblioteca="+id_biblioteca);

	}

	@Override
	public List<ServiziInformazioniBibliograficheModalita> getModalitaComunicazioniBibliograficheByIdBiblio( int id_biblioteca) {

		return biblioDao.getModalitaComunicazioniBibliograficheByIdBiblio(id_biblioteca);
	}

	@Override
	public void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, Integer idRecord) throws DuplicateEntryException {

		biblioDao.addModalitaComunicazioneInformazioneBibliografica( id_biblioteca,  idRecord);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica modalità comunicazione bibliografica: "+(idRecord!=null?"id_record="+idRecord:"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idRemove) {
		biblioDao.removeModalitaComunicazioneInformazioneBibliografica(id_biblioteca, idRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione modalità comunicazione bibliografica: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<SezioniSpeciali> getSezioniSpecialiByIdBiblio(int id_biblioteca) {

		return biblioDao.getSezioniSpecialiByIdBiblio(id_biblioteca);
	}

	@Override
	public void removeSezioniSpeciali(int id_biblioteca, int idRemove) {
		biblioDao.removeSezioniSpeciali( id_biblioteca, idRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione sezioni speciali: id_record="+idRemove+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void addSezioniSpeciali(int id_biblioteca, Integer idRecord) throws DuplicateEntryException {
		biblioDao.addSezioniSpeciali(id_biblioteca,idRecord);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica sezioni speciali: "+(idRecord!=null?"id_record="+idRecord:"")+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	@Transactional
	public void updateModalitaAccessoInternet(int id_biblioteca, Boolean hasAttivoAccesso, Boolean hasAccessoPagamento, Boolean hasAccessoTempo, Boolean hasAccessoProxy) {
		biblioDao.updateModalitaAccessoInternet(id_biblioteca, hasAttivoAccesso, hasAccessoPagamento, hasAccessoTempo, hasAccessoProxy);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica modalità accesso internet: id_biblioteca="+id_biblioteca);
	}

	@Override
	public List<PrestitoLocale> getPrestitiLocaliByIdBiblio(int id_biblioteca) {
		return biblioDao.getPrestitiLocaliByIdBiblio(id_biblioteca);
	}

	@Override
	public PrestitoLocale addPrestitoLocaleToBiblio(int id_biblioteca,Integer idPrestito, Integer durataGiorni, Boolean automatizzato, boolean modifica) {
		return biblioDao.addPrestitoLocaleToBiblio(id_biblioteca,idPrestito, durataGiorni, automatizzato,modifica);
	}

	@Override
	public void removePrestitoLocaleFromBiblio(int id_biblioteca, int id_removePrestito) {
		biblioDao.removePrestitoLocale(id_biblioteca,id_removePrestito);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione prestito locale: id_record="+id_removePrestito+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public List<PrestitoInterbibliotecario> getPrestitoInterbibliotecarioERuoloByIdBiblio(
			int id_biblioteca) {

		return biblioDao.getPrestitoInterbibliotecarioERuoloByIdBiblio(id_biblioteca);
	}

	@Override
	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca,Integer idPrestito, Integer idRuolo, Boolean nazionale,
			Boolean internazionale, boolean modifica) throws DuplicateEntryException {
		biblioDao.addPrestitoInterbibliotecarioToBiblio( id_biblioteca, idPrestito,  idRuolo,  nazionale, internazionale,  modifica);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica prestito interbibliotecario:" +(modifica?"id_record="+idPrestito:"")+ "id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca,int id_removePrestito) {

		biblioDao.removePrestitoInterbibliotecarioFromBiblio(id_biblioteca, id_removePrestito);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione prestito interbibliotecario: id_record="+id_removePrestito+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblio, Boolean hasPrestitoNazionale,Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate) {

		biblioDao.setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(	id_biblio,  hasPrestitoNazionale, hasPrestitoInternazionale,  hasProcedureAutomatizzate);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica prestito nazionale/internazionale: id_biblioteca="+id_biblio);
	}

	@Override
	public void setInfoPersonale(int id_biblio,	HashMap<String, Object> personaleValues) {
		biblioDao.setInfoPersonale( id_biblio,	 personaleValues);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica info personale: id_biblioteca="+id_biblio);
	}

	@Override
	public void setInfoUtenti(int id_biblio,HashMap<String, Object> utentiValues) {
		biblioDao.setInfoUtenti( id_biblio,utentiValues);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica info utenti: id_biblioteca="+id_biblio);
	}

	@Override
	public void setInfoBilancio(int id_biblio,	HashMap<String, Object> bilancioValues) {
		biblioDao. setInfoBilancio( id_biblio,	bilancioValues);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica info bilancio: id_biblioteca="+id_biblio);
	}

	@Override
	public List<DepositiLegali> getDepositiLegaliByIdBiblio(int id_biblioteca) {

		return biblioDao.getDepositiLegaliByIdBiblio(id_biblioteca) ;
	}

	@Override
	public void addDepositoLegaleToBiblio(int id_biblioteca,int id_nuovoTipoDeposito, String anno,boolean modifica) {

		biblioDao.addDepositoLegaleToBiblio( id_biblioteca, id_nuovoTipoDeposito,  anno, modifica);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica deposito legale: "+(modifica?"id_record="+id_nuovoTipoDeposito:"")+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void removeDepositoLegaleFromBiblio(int id_biblioteca,int id_rimuoviDepositoLegale) {
		biblioDao.removeDepositoLegaleFromBiblio(id_biblioteca,id_rimuoviDepositoLegale);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione deposito legale: id_record="+id_rimuoviDepositoLegale+" - id_biblioteca="+id_biblioteca);
	}

	@Override
	public void inserisciNoteCatalogazione(int id_biblio, String value) {
		biblioDao.inserisciNoteCatalogazione( id_biblio,  value);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica note catalogazione: id_biblioteca="+id_biblio);
	}

	@Override
	public void inserisciComunicazioniCatalogazione(int id_biblio, String value) {
		biblioDao.inserisciComunicazioniCatalogazione( id_biblio,  value);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica comunicazioni catalogazione: id_biblioteca="+id_biblio);
	}
	
	@Override
	public void inserisciPhoto(int id_biblioteca, Photo photo) {
		biblioDao.inserisciPhoto(id_biblioteca, photo);
		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica foto: id_biblioteca="+id_biblioteca);
	}
	
	@Override
	@Transactional
	public void setOccupata(int id) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		setOccupataByUsername(id, username);
	}

	@Override
	@Transactional
	public void setOccupataByUsername(int id, String username) {
		// se era già occupata esco subito
		Biblioteca biblioteca = biblioDao.getBibliotecaById(id);

		try {
			trasferimentoBiblioteca.salva(id); // se esiste non lo sovrascrive
		} catch (Exception e) {
			_log.error("Errore nel eseguire il salvataggio in XML della biblioteca", e);
			throw new RuntimeException(new Error(e.getMessage()));
		}

		if (StatiBiblioteca.OCCUPATA.equalsIgnoreCase(biblioteca.getStatoBibliotecaWorkflow().getLabel())) return;

		// carico utente ultima modifica
		Utenti utenteUltimaModifica = utentiDao.findByName(username);


		biblioteca.setUtenteUltimaModifica(utenteUltimaModifica);

		Date catalogazioneDataModifica = new Date(); // now
		biblioteca.setCatalogazioneDataModifica(catalogazioneDataModifica);

		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.OCCUPATA);
		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.OCCUPATA, username);
	}

	@Override
	@Transactional
	public void setInRevisione(int id, boolean sendEmailToRevisori) {
		String username = authLogic.retrieveLoggedUser().getUsername();
		setInRevisioneByUsername(id, sendEmailToRevisori, username);
	}

	@Override
	@Transactional
	public void setInRevisioneByUsername(int id, boolean sendEmailToRevisori, String username) {
		// carico utente ultima modifica
		Utenti utenteUltimaModifica = utentiDao.findByName(username);

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id);
		biblioteca.setUtenteUltimaModifica(utenteUltimaModifica);

		Date catalogazioneDataModifica = new Date(); // now
		biblioteca.setCatalogazioneDataModifica(catalogazioneDataModifica);

		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.REVISIONE);
		if ((emailRevisoriEnabled != null && emailRevisoriEnabled) && sendEmailToRevisori) {//Invia un email di avviso a tutti gli utenti con ruolo "Revisore"
			sendMailBibliotecainRevisione(id);
		}

		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.REVISIONE, username);
	}


	@Override
	@Transactional
	public void setCancellata(int id) {
		// carico utente ultima modifica
		String username = authLogic.retrieveLoggedUser().getUsername();
		Utenti utenteUltimaModifica = utentiDao.findByName(username);

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id);
		biblioteca.setUtenteUltimaModifica(utenteUltimaModifica);

		Date catalogazioneDataModifica = new Date(); // now
		biblioteca.setCatalogazioneDataModifica(catalogazioneDataModifica);

		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.CANCELLATA);
		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.CANCELLATA );
	}

	@Override
	@Transactional
	public void setDefinitiva(int id) {
		// Metto il file xml di salvataggio della biblioteca nella cartella di backup chiamato idbiblio_timemills.xml
		try {
			trasferimentoBiblioteca.backupXml(id);
		} catch (Exception e) {
			_log.warn("Errore nel eseguire il backup del file di salvataggio della biblioteca: " + e.getMessage());
			//throw new RuntimeException(new Error(e.getMessage()));
		}

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id);

		Date catalogazioneDataModifica = new Date(); // now
		biblioteca.setCatalogazioneDataModifica(catalogazioneDataModifica);
		biblioteca.setCatalogazioneDataImport(null);
		biblioteca.setCatalogazioneNote(null);
		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.DEFINITIVA);
		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.DEFINITIVA+" per revisione APPROVATA" );
	}

	@Transactional
	@Override
	public void setDefinitiveImportate(List<Integer> bibliotecheSelectedIds) {
		Vector<Integer> ok = new Vector<Integer>();
		HashMap<Integer,String> ko = new HashMap<Integer,String>();
		for(Integer idBiblio : bibliotecheSelectedIds) {
			try {
				setDefinitiva(idBiblio);
				ok.add(idBiblio);
			} catch (Throwable th) {
				th.getMessage();
				ko.put(idBiblio, th.getMessage());
			}
		}
		sendMailReportBibliotecheImportate(ok, ko);
	}

	@Override
	@Transactional
	public int ripristinaImportate(List<Integer> bibliotecheSelectedIds) {
		int numBibInError = 0;
		for(Integer idBiblio : bibliotecheSelectedIds) {
			try {
				trasferimentoBiblioteca.ripristina(idBiblio);
				Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
				biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.DEFINITIVA);
				userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.DEFINITIVA+" per RIPRISTINO");
				
			} catch (Exception e) {
				_log.warn("Errore nel ripristinare la biblioteca con id " + idBiblio + ", errore: " + e.getMessage());
				numBibInError++;
				//throw new RuntimeException(new Error(e.getMessage()));
			}
			
		}
		
		return numBibInError;
	}

	@Transactional
	public void sendMailReportBibliotecheImportate(Vector<Integer> ok,HashMap<Integer,String> ko) {
		// utente revisore
		String usernameRevisore = authLogic.retrieveLoggedUser().getUsername();
		Utenti utenteRevisore = utentiDao.findByName(usernameRevisore);
		String emailRevisore = utenteRevisore.getEmail();

		StringBuffer reportMessage = new StringBuffer();

		reportMessage.append("**********Report biblioteche importate**********\n");
		reportMessage.append("\n\n");
		reportMessage.append("Importate con successo:"+ok.size() +" di "+(ok.size()+ko.keySet().size())+"\n");
		for(Integer id:ok){
			Biblioteca biblioteca = biblioDao.getBibliotecaById(id);
			reportMessage.append("-"+Utility.buildIsil(biblioteca)+"\n");
		}

		reportMessage.append("\n\n");

		reportMessage.append("Errore in import:"+ko.size() +" di "+(ok.size()+ko.keySet().size())+"\n");
		Set<Integer> keySet = ko.keySet();
		for(Integer key:keySet){
			String value = ko.get(key);
			Biblioteca biblioteca = biblioDao.getBibliotecaById(key);
			reportMessage.append("-"+Utility.buildIsil(biblioteca)+" - ");
			reportMessage.append("Errore:"+value);
		}

		// invio la mail all'utente revisore
		String loginRevisore = utenteRevisore.getLogin();

		// invio email
		try {
			String subject = "Esito import biblioteche";
			sendEmail(subject, reportMessage.toString(), emailRevisore, loginRevisore, emailRevisore, usernameRevisore);
		} catch (Exception e) {
			_log.fatal("Errore nell'invio della email", e);
			//throw new RuntimeException(e.getMessage());
		}
	}


	@Override
	@Transactional
	public void setDefinitiva(int idBiblioteca, String messaggio) {

		setDefinitiva(idBiblioteca);

		sendMailBibliotecaResaDefinitiva(idBiblioteca, messaggio);
	}

	/**
	 * @param idBiblioteca
	 * @param messaggio
	 */
	@Transactional
	private void sendMailBibliotecaResaDefinitiva(int idBiblioteca,	String messaggio) {
		// utente revisore
		String usernameRevisiore = authLogic.retrieveLoggedUser().getUsername();
		Utenti utenteRevisiore = utentiDao.findByName(usernameRevisiore);
		String emailRevisore = utenteRevisiore.getEmail();

		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblioteca);
		Utenti utenteUltimaModifica = biblioteca.getUtenteUltimaModifica();

		// invio la mail all'utente
		String email = utenteUltimaModifica.getEmail();
		String username = utenteUltimaModifica.getLogin();

		// invio email
		try {
			String definitivaMessage = createDefinitivaMessage(messaggio, usernameRevisiore, biblioteca);
			String subject = "Revisione biblioteca ".concat(Utility.buildIsil(biblioteca)).concat(" accettata");
			sendEmail(subject, definitivaMessage, email, username, emailRevisore, usernameRevisiore);
		} catch (Exception e) {
			_log.fatal("Errore nell'invio della email", e);
			//throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void ripristina(int id) {

		try {
			trasferimentoBiblioteca.ripristina(id);
		} catch (Exception e) {
			_log.error("Errore nel ripristinare la biblioteca", e);
			throw new RuntimeException(new Error(e.getMessage()));
		}

		Biblioteca biblioteca = biblioDao.getBibliotecaById(id);

		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.DEFINITIVA);
		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.DEFINITIVA+" per RIPRISTINO");
	}

	@Override
	@Transactional
	public void respingiRevisione(int id_biblio) {
		// rimetto in occupata
		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblio);
		biblioDao.setNuovoStato(biblioteca, StatiBiblioteca.OCCUPATA);
		userActionLog.logActionStatoBibliotecaDefaultUser("Stato biblioteca con id="+biblioteca.getIdBiblioteca()+" impostato a: "+StatiBiblioteca.OCCUPATA+" per revisione RESPINTA" );
	}

	@Override
	@Transactional
	public void respingiRevisione(int idBiblioteca, String messaggio) {

		sendMailBibliotecaRespinta(idBiblioteca, messaggio);

		// rimetto in occupata
		respingiRevisione(idBiblioteca);
	}

	/**
	 * @param idBiblioteca
	 * @param messaggio
	 */
	private void sendMailBibliotecaRespinta(int idBiblioteca, String messaggio) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblioteca);
		// utente revisore
		String usernameRevisore = authLogic.retrieveLoggedUser().getUsername();
		Utenti utenteRevisore = utentiDao.findByName(usernameRevisore);
		String emailRevisore = utenteRevisore.getEmail();

		Utenti utenteUltimaModifica = biblioteca.getUtenteUltimaModifica();

		// invio la mail all'utente
		String email = utenteUltimaModifica.getEmail();
		String username = utenteUltimaModifica.getLogin();

		// invio email
		try {
			String respingiMessage = createRespingiMessage(messaggio, usernameRevisore, biblioteca);
			String subject = "Revisione biblioteca ".concat(Utility.buildIsil(biblioteca)).concat(" respinta");
			sendEmail(subject, respingiMessage, email, username, emailRevisore, usernameRevisore);
		} catch (Exception e) {
			_log.fatal("Errore nell'invio della email", e);
			//throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String differenze(int idbiblio) {
		try {
			StringBuffer result = new StringBuffer();
			List<Differenze> diffs = abiBiblioDifferenze.difference(idbiblio);
			if (diffs != null) {
				for (Differenze differenze : diffs) {
					result.append(differenze.toFormatString());
				}

			}
			return result.toString();
		} catch (Exception e) {
			_log.error("Errore nella generazione delle differenze");
			throw new RuntimeException(e.getMessage());
		}
	}

	protected String createDefinitivaMessage(String message, String username, Biblioteca biblioteca) {
		String iccuCode = Utility.buildIsil(biblioteca);
		StringBuffer sb = new StringBuffer();
		sb.append("La biblioteca ");
		sb.append(iccuCode);
		sb.append(" è stata resa definitiva dall'utente revisore ");
		sb.append(username);
		if (StringUtils.isBlank(message)) {
			sb.append(".");
		} else {
			sb.append(" con la seguente motivazione:");
			sb.append("\n\n");
			sb.append(message);
		}

		return sb.toString();
	}

	/**
	 * @param idBiblioteca
	 * @param messaggio
	 */
	private void sendMailBibliotecainRevisione(int idBiblioteca) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblioteca);

		Utenti utenteUltimaModifica = biblioteca.getUtenteUltimaModifica();

		String username = utenteUltimaModifica.getLogin();

		// invio email
		try {
			String revisioneMessage = createBibliotecaInRevisioneMessage(username, biblioteca);
			String subject = "Revisione biblioteca ".concat(Utility.buildIsil(biblioteca)).concat(".");
			//CLICLO LA LISTA DEGLI UTENTI REVISORI
			for(Utenti utente:utentiDao.getUsersByRole(5)){
				sendEmail(subject, revisioneMessage, utente.getEmail(), username, "anagrafe@iccu.isbn.it", "ANAGRAFE BIBLIOTECHE ITALIANE");
			}


		} catch (Exception e) {
			_log.fatal("Errore nell'invio della email", e);
			//throw new RuntimeException(e.getMessage());
		}
	}


	protected String createRespingiMessage(String message, String username, Biblioteca biblioteca) {
		String iccuCode = Utility.buildIsil(biblioteca);
		StringBuffer sb = new StringBuffer();
		sb.append("Le modifiche alla biblioteca ");
		sb.append(iccuCode);
		sb.append(" non sono state accettate dall'utente revisore ");
		sb.append(username);
		if (StringUtils.isBlank(message)) {
			sb.append(".");
		} else {
			sb.append(" ,la motivazione è la seguente:");
			sb.append("\n\n");
			sb.append(message);
		}
		return sb.toString();
	}

	protected String createNuovaBibliotecaRespingiMessage(String message, String username, NuovaBiblioteca nuovaBiblioteca) {
		StringBuffer sb = new StringBuffer();
		sb.append("Le modifiche alla biblioteca ");
		if(nuovaBiblioteca.getDenominazioneUfficiale()!=null)
			sb.append("\"".concat(nuovaBiblioteca.getDenominazioneUfficiale()).concat("\" "));
		sb.append("non sono state accettate dall'utente revisore ");
		sb.append(username);
		if (StringUtils.isBlank(message)) {
			sb.append(".");
		} else {
			sb.append(" ,la motivazione è la seguente:");
			sb.append("\n\n");
			sb.append(message);
		}
		return sb.toString();
	}

	protected String createNuovaBibliotecaInRevisioneMessage(String username, NuovaBiblioteca nuovaBiblioteca) {
		StringBuffer sb = new StringBuffer();
		sb.append("La nuova biblioteca ");
		if(nuovaBiblioteca.getDenominazioneUfficiale()!=null)
			sb.append("\"".concat(nuovaBiblioteca.getDenominazioneUfficiale()).concat("\" "));
		if(nuovaBiblioteca.getComune().getDenominazione()!=null)
			sb.append("di ".concat(nuovaBiblioteca.getComune().getDenominazione()).concat(" "));
		if(nuovaBiblioteca.getComune().getProvincia().getSigla()!=null)
			sb.append("(".concat(nuovaBiblioteca.getComune().getProvincia().getSigla()).concat(") "));
		sb.append("è stata messa in revisione dall'utente ");
		sb.append(username);
		sb.append(".");
		return sb.toString();
	}

	protected String createBibliotecaInRevisioneMessage(String username, Biblioteca biblioteca) {
		String iccuCode = Utility.buildIsil(biblioteca);
		StringBuffer sb = new StringBuffer();
		sb.append("La biblioteca ");
		sb.append(iccuCode.concat(" "));
		if(biblioteca.getDenominazioneUfficiale()!=null)
			sb.append("\"".concat(biblioteca.getDenominazioneUfficiale()).concat("\" "));
		if(biblioteca.getComune().getDenominazione()!=null)
			sb.append("di ".concat(biblioteca.getComune().getDenominazione()).concat(" "));
		if(biblioteca.getComune().getProvincia().getSigla()!=null)
			sb.append("(".concat(biblioteca.getComune().getProvincia().getSigla()).concat(") "));
		sb.append("è stata messa in revisione dall'utente ");
		sb.append(username);
		sb.append(".");

		return sb.toString();
	}

	/* Metodo di ricerca biblioteche tramite codice */
	public Biblioteca ricercaBiblioViaCodice(String stato, String provincia, String numero) {
		String[] s = new String[1];
		if (numero.length() < 4) {
			if (numero.length() == 1)
				s[0] = new String(stato.concat("-").concat(provincia).concat("000").concat(numero));
			else if (numero.length() == 2)
				s[0] = new String(stato.concat("-").concat(provincia).concat("00").concat(numero));
			else s[0] = new String(stato.concat("-").concat(provincia).concat("0").concat(numero));
		}
		else s[0] = new String(stato.concat("-").concat(provincia).concat(numero));

		User user = authLogic.retrieveLoggedUser();
		String username = user.getUsername();
		Utenti utenteGestore = utentiDao.findByName(username);


		Biblioteca[] list = (Biblioteca[]) biblioDao.getBibliotecheViaCodice(s, !Roles.isUserInRole(Roles.VEDI_TUTTE, utenteGestore) ? utenteGestore : null, 0, 1);

		if (list != null && list.length > 0)
			return list[0];
		else return null;
	}

	@Override
	@Transactional
	public void setInventarioCartaceo(int idBiblio, Boolean b) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setInventarioCartaceo(b);
		biblioDao.updateBiblioteca(biblioteca);

	}

	@Override
	@Transactional
	public void setInventarioInformatizzato(int idBiblio, Boolean b) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setInventarioInformatizzato(b);
		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	@Transactional
	public void setCatalogoTopograficoCartaceo(int idBiblio, Boolean b) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setCatalogoTopograficoCartaceo(b);
		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	@Transactional
	public void setCatalogoTopograficoInformatizzato(int idBiblio, Boolean b) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);
		biblioteca.setCatalogoTopograficoInformatizzato(b);
		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	@Transactional
	public void setCatInvFondi(int idBiblio, HashMap<String, Object> params) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idBiblio);

		if (params != null && !params.isEmpty()) {
			biblioteca.setInventarioCartaceo((Boolean) params.get("invCartaceo"));
			biblioteca.setInventarioInformatizzato((Boolean) params.get("invInform"));
			biblioteca.setCatalogoTopograficoCartaceo((Boolean) params.get("catCartaceo"));
			biblioteca.setCatalogoTopograficoInformatizzato((Boolean) params.get("catInform"));

			Integer idFondi = (Integer) params.get("fondi");
			FondiAntichiConsistenza f =	dynaTabDao.getFondiAntichiById(idFondi.intValue());
			biblioteca.setFondiAntichiConsistenza(f);
		}

		biblioDao.updateBiblioteca(biblioteca);
	}

	private @Value("${email.host.address}") String emailHostAddress;
	private @Value("${email.host.username}") String emailHostUsername;
	private @Value("${email.host.password}") String emailHostPassword;
	private @Value("${email.bounce.address}") String emailBounceAddress;
	private @Value("${email.revisori.enabled}") Boolean emailRevisoriEnabled;

	public void sendEmail(String subject, String message, String emailTo, String nameTo, String emailFrom, String nameFrom) throws EmailException {
		// Email start export
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setHostName(emailHostAddress);
		if (StringUtils.isNotBlank(emailHostUsername)) simpleEmail.setAuthentication(emailHostUsername, emailHostPassword);
		simpleEmail.addTo(emailTo, nameTo);
		simpleEmail.setFrom(emailFrom, nameFrom);
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(message);				
		simpleEmail.setBounceAddress(emailBounceAddress);
		_log.info("Invio email.");
		simpleEmail.setCharset("UTF-8");
		simpleEmail.send();
		_log.debug("Inviata...");
	}

	@Override
	public List<Utenti> ricercaUtentiByIdBiblio(int idbiblio, int offset, int rows, String orderByField, String orderByDir) {
		List<Utenti> utenti = biblioDao.ricercaUtentiByIdBiblio(idbiblio, offset, rows, orderByField, orderByDir);
		for (Utenti utente : utenti) {
			utente.setPassword(null); // la password non deve uscire da dal server...anche xche è md5 !non mettere il @Transactional!
		}
		return utenti; 
	}

	@Override
	public int countUtentiByIdBiblio(int idbiblio) {
		return biblioDao.countUtentiByIdBiblio(idbiblio);
	}

	@Override
	@Transactional
	public void aggiornaCodici(HashMap<String, Object> params, int idBiblio) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);

		if (params != null && !params.isEmpty()) {
			biblioteca.setCodiceFiscale(params.get("codiceFiscale")!=null?(String)params.get("codiceFiscale"):null);
			biblioteca.setPartitaIva(params.get("partitaIva")!=null?(String)params.get("partitaIva"):null);
		}

		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	@Transactional
	public void aggiornaCodiciOthers(HashMap<String, Object> params, int idBiblio) {
		Biblioteca biblioteca= biblioDao.getBibliotecaById(idBiblio);

		List<Codici> codicis = new ArrayList<Codici>();
		if (params != null && !params.isEmpty()) {
			if(params.get("acnp")!=null){
				Codici tmp = new Codici();

				CodiciTipo codiciTipoTmp =biblioDao.getCodiceTipoById(1);

				CodiciPK codiciPK = new CodiciPK();
				codiciPK.setIdBiblioteca(idBiblio);
				codiciPK.setIdCodici(codiciTipoTmp.getIdCodici());

				tmp.setId(codiciPK);
				tmp.setBiblioteca(biblioteca);
				tmp.setValore((String)params.get("acnp"));
				tmp.setCodiciTipo(codiciTipoTmp);

				biblioDao.updateChild(tmp);

				codicis.add(tmp);
			}else{
				Codici codice =biblioDao.getCodiceByIdCodiceIdBiblio(idBiblio,1);
				if(codice!=null){
					biblioDao.removeChild(codice);
				}
			}

			if(params.get("cei")!=null){
				Codici tmp = new Codici();

				CodiciTipo codiciTipoTmp =biblioDao.getCodiceTipoById(2);

				CodiciPK codiciPK = new CodiciPK();
				codiciPK.setIdBiblioteca(idBiblio);
				codiciPK.setIdCodici(codiciTipoTmp.getIdCodici());

				tmp.setId(codiciPK);
				tmp.setBiblioteca(biblioteca);
				tmp.setValore((String)params.get("cei"));
				tmp.setCodiciTipo(codiciTipoTmp);

				biblioDao.updateChild(tmp);

				codicis.add(tmp);
			}else{
				Codici codice =biblioDao.getCodiceByIdCodiceIdBiblio(idBiblio,2);
				if(codice!=null){
					biblioDao.removeChild(codice);
				}
			}

			if(params.get("cbms")!=null){
				Codici tmp = new Codici();

				CodiciTipo codiciTipoTmp =biblioDao.getCodiceTipoById(3);

				CodiciPK codiciPK = new CodiciPK();
				codiciPK.setIdBiblioteca(idBiblio);
				codiciPK.setIdCodici(codiciTipoTmp.getIdCodici());

				tmp.setId(codiciPK);
				tmp.setBiblioteca(biblioteca);
				tmp.setValore((String)params.get("cbms"));
				tmp.setCodiciTipo(codiciTipoTmp);

				biblioDao.updateChild(tmp);

				codicis.add(tmp);
			}else{
				Codici codice =biblioDao.getCodiceByIdCodiceIdBiblio(idBiblio,3);
				if(codice!=null){
					biblioDao.removeChild(codice);
				}
			}

			if(params.get("rism")!=null){
				Codici tmp = new Codici();

				CodiciTipo codiciTipoTmp =biblioDao.getCodiceTipoById(4);

				CodiciPK codiciPK = new CodiciPK();
				codiciPK.setIdBiblioteca(idBiblio);
				codiciPK.setIdCodici(codiciTipoTmp.getIdCodici());

				tmp.setId(codiciPK);
				tmp.setBiblioteca(biblioteca);
				tmp.setValore((String)params.get("rism"));
				tmp.setCodiciTipo(codiciTipoTmp);

				biblioDao.updateChild(tmp);

				codicis.add(tmp);
			}else{
				Codici codice =biblioDao.getCodiceByIdCodiceIdBiblio(idBiblio,4);
				if(codice!=null){
					biblioDao.removeChild(codice);
				}
			}

			if(params.get("sbn")!=null){
				Codici tmp = new Codici();

				CodiciTipo codiciTipoTmp =biblioDao.getCodiceTipoById(5);

				CodiciPK codiciPK = new CodiciPK();
				codiciPK.setIdBiblioteca(idBiblio);
				codiciPK.setIdCodici(codiciTipoTmp.getIdCodici());

				tmp.setId(codiciPK);
				tmp.setBiblioteca(biblioteca);
				tmp.setValore((String)params.get("sbn"));
				tmp.setCodiciTipo(codiciTipoTmp);

				biblioDao.updateChild(tmp);

				codicis.add(tmp);
			}else{
				Codici codice =biblioDao.getCodiceByIdCodiceIdBiblio(idBiblio,5);
				if(codice!=null){
					biblioDao.removeChild(codice);
				}
			}
		}

		biblioteca.setCodicis(codicis);
		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	public void addStatoCatalogazione(int idbiblio,	Integer idStatoCatalogazione, Integer idBibliotecaTarget) {
		biblioDao.addStatoCatalogazioneViaRipristino(idbiblio, idStatoCatalogazione, idBibliotecaTarget);
	}

	@Override
	public Boolean addStatoCatalogazione(HashMap<String, Object> params) {
		return biblioDao.addStatoCatalogazione(params);		
	}
	
	@Override
	public Boolean removeStatoCatalogazione(HashMap<String, Object> params) {
		return biblioDao.removeStatoCatalogazione(params);
	}
	
	@Override
	public void setAttivoRiproduzioni(int idbib, Boolean attivoRiproduzioni) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idbib);
		biblioteca.setAttivoRiproduzioni(attivoRiproduzioni);

		if (attivoRiproduzioni == null || (attivoRiproduzioni != null && attivoRiproduzioni.booleanValue() == false)) {
			biblioDao.removeRiproduzioniFromBiblio(biblioteca);
		}

		biblioDao.updateBiblioteca(biblioteca);
	}

	@Override
	public void setAttivoPrestitoLocale(int idbib, Boolean attivoPrestitoLocale) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idbib);
		biblioteca.setAttivoPrestitoLocale(attivoPrestitoLocale);

		if (attivoPrestitoLocale == null || (attivoPrestitoLocale != null && attivoPrestitoLocale.booleanValue() == false)) {
			biblioDao.removePrestitoLocaleFromBiblio(biblioteca);
		}
		biblioDao.updateBiblioteca(biblioteca);		
	}

	@Override
	public List<SistemiPrestitoInterbibliotecario> getListaSistemiPrestitoInterbibliotecario(int id_biblioteca) {
		return biblioDao.getListaSistemiPrestitoInterbibliotecario(id_biblioteca);
	}

	@Override
	public void removeSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistema) {
		biblioDao.removeSistemaPrestitoInterbibliotecario(id_biblioteca, id_sistema);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione sistema prestito interbibliotecario: id_record="+id_sistema+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void addSistemaPrestitoInterbibliotecario(int id_biblioteca, Integer id_sistema) throws DuplicateEntryException {
		biblioDao.addSistemaPrestitoInterbibliotecario(id_biblioteca, id_sistema);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica sistema prestito interbibliotecario: " + 
				(id_sistema != null ? "id_sistema = " + id_sistema + " - " : "") + "id_biblioteca = " + id_biblioteca);
	}

	@Override
	public void setReference(int id_biblio, Boolean hasAttivoReference, Boolean hasReferenceLocale, Boolean hasReferenceOnline) {
		biblioDao.setReference(id_biblio, hasAttivoReference, hasReferenceLocale, hasReferenceOnline);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica reference: - id_biblioteca="+id_biblio);
	}
	
	@Override
	public void setAttivoDocumentDelivery(int idbib, Boolean attivoDocumentDelivery) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idbib);
		biblioteca.setAttivoDocumentDelivery(attivoDocumentDelivery);

		if (attivoDocumentDelivery == null || (attivoDocumentDelivery != null && attivoDocumentDelivery.booleanValue() == false)) {
			biblioDao.removeDocumentDeliveryFromBiblio(biblioteca);
		}

		biblioDao.updateBiblioteca(biblioteca);
	}
	
	@Override
	public List<RiproduzioniTipo> getDocumentDeliveryByIdBiblio( int id_biblioteca) {
		return biblioDao.getDocumentDeliveryByIdBiblio(id_biblioteca);
	}

	@Override
	public void addDocumentDelivery(int id_biblioteca, Integer idDocumentDelivery) throws DuplicateEntryException {
		biblioDao.addDocumentDelivery(id_biblioteca, idDocumentDelivery);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio/modifica document delivery: "+(idDocumentDelivery!=null?"id_document_delivery="+idDocumentDelivery:"")+" - id_biblioteca="+id_biblioteca);

	}

	@Override
	public void removeDocumentDelivery(int id_biblioteca, int idRemove) {
		biblioDao.removeDocumentDelivery(id_biblioteca, idRemove);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione document delivery: id_document_delivery="+idRemove+" - id_biblioteca="+id_biblioteca);
	}
	
	@Override
	public void setAttivoDepositoLegale(int idbib, Boolean attivoDepositoLegale) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(idbib);
		biblioteca.setAttivoDepositoLegale(attivoDepositoLegale);

		if (attivoDepositoLegale == null || (attivoDepositoLegale != null && attivoDepositoLegale.booleanValue() == false)) {
			biblioDao.removeDepositiLegaliFromBiblio(biblioteca);
		}

		biblioDao.updateBiblioteca(biblioteca);
	}
	
	@Override
	public void updateCensimento(int id_biblioteca, Integer anno) {
		Biblioteca biblioteca = biblioDao.getBibliotecaById(id_biblioteca);
		
		if (anno != null) {
			Calendar c = GregorianCalendar.getInstance();
			c.set(anno, 0, 1, 5, 0, 0);
			biblioteca.setCatalogazioneDataCensimento(c.getTime());
			
		} else {
			biblioteca.setCatalogazioneDataCensimento(null);
		}
		biblioDao.updateBiblioteca(biblioteca);
	}
	
	@Override
	public void addPadreServizioDecentrato(int idBiblioFiglio, String isilProvinciaPadre, String isilNumeroPadre) {
		String isilPadre = isilProvinciaPadre.concat(Utility.zeroFill(isilNumeroPadre, 4));
		String[] bibs = new String[]{isilPadre}; 
		Biblioteca[] bibPadre = biblioDao.getBibliotecheByCodABI6CharsCode(bibs, 0, 1);
		
		if (bibPadre != null && bibPadre.length == 1) {
			Biblioteca figlio = biblioDao.getBibliotecaById(idBiblioFiglio);
			figlio.setBibliotecaPadre(bibPadre[0]);
			
			biblioDao.updateBiblioteca(figlio);
		}
	}
	
	@Override
	public List<String> getListaIsilProvincia(String query) {
		return biblioDao.getListaIsilProvincia(query);
	}
	
	@Override
	public void removePadreServizioDecentrato(int idBiblioFiglio) {
		Biblioteca bib = biblioDao.getBibliotecaById(idBiblioFiglio);
		bib.setBibliotecaPadre(null);
		
		biblioDao.updateBiblioteca(bib);
	}
	
	@Override
	public List<Photo> getPhotos(int id_biblioteca) {
		return biblioDao.getPhotos(id_biblioteca);
		
	}
	
	@Override
	@Transactional
	public void addPhoto(int id_biblioteca, String caption, String uri) {
		biblioDao.addPhoto(id_biblioteca, caption, uri);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Salvataggio nuova foto: idBiblioteca="+id_biblioteca+", caption:"+caption);
	}
	
	@Override
	public void updatePhotoCaption(int idPhoto, String caption) {
		biblioDao.updatePhotoCaption(idPhoto, caption);
		
	}
	
	@Override
	public void removePhoto(int id_biblioteca, int id_photo) {
		biblioDao.removePhoto(id_biblioteca, id_photo);

		userActionLog.logActionCatalogazioneBiblioDefaultUser("Rimozione photo: id_photo="+id_photo);
	}
	
	@Override
	public void updatePhotoOrder(List<Integer> idPhotos) {
		biblioDao.updatePhotoOrder(idPhotos);
	}
}
