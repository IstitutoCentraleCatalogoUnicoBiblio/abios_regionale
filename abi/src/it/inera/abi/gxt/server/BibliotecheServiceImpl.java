package it.inera.abi.gxt.server;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.EntryNotFoundException;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ContattiModel;
import it.inera.abi.gxt.client.mvc.model.DepositiLegaliModel;
import it.inera.abi.gxt.client.mvc.model.DestinazioneSocialeModel;
import it.inera.abi.gxt.client.mvc.model.EnteModel;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.NuovaBiblioModel;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGeneraliModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioLibrarioModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoInterbibliotecarioRuoloModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoLocaleModel;
import it.inera.abi.gxt.client.mvc.model.RegolamentoModel;
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.StatoModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.services.BibliotecheService;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.EntryNotFoundClientSideException;
import it.inera.abi.logic.AbiBiblioLogic;
import it.inera.abi.logic.TrasferimentoBiblioteca;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.CataloghiCollettiviZonaTipo;
import it.inera.abi.persistence.CataloghiSupportoDigitaleTipo;
import it.inera.abi.persistence.CatalogoGeneraleTipo;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiDigitali;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario;
import it.inera.abi.persistence.NuovaBiblioteca;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoInterbibliotecarioModo;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.Stato;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.StatoCatalogazione;
import it.inera.abi.persistence.TipologiaFunzionale;
import it.inera.abi.persistence.Utenti;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class BibliotecheServiceImpl extends AutoinjectingRemoteServiceServlet implements BibliotecheService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5047407610235114598L;

	private AbiBiblioLogic abiBiblioLogic;
	@Autowired private TrasferimentoBiblioteca trasferimentoBiblioteca;

	@Autowired
	@Required
	public void setAbiBiblioService(AbiBiblioLogic abiBiblioLogic) {
		this.abiBiblioLogic = abiBiblioLogic;
	}


	public BibliotecheServiceImpl() {
	}

	@Override
	public PagingLoadResult<BiblioModel> getBiblioteche(final PagingLoadConfig config) {
		HashMap<String, Object> keys = config.get("keys");
		if (config.get("viacodice") != null && ((Boolean) config.get("viacodice"))) {
			return ricercaBiblioViaCodice((String) keys.get("codiceStato"), (String) keys.get("codiceProvincia"), (String) keys.get("codiceNumero"));			
		} else {
			return getBiblioteche(keys, config);
		}
	}

	
	public PagingLoadResult<BiblioModel> getBiblioteche(HashMap<String, Object> keys, final PagingLoadConfig config) {
		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir = config.getSortInfo().getSortDir().toString();
		String sortField = config.getSortInfo().getSortField();
		/*
		 * LOAD DATI
		 */

		List<Biblioteca> biblioteche = abiBiblioLogic.ricercaBiblio(keys, start, limit, sortField, sortDir);
		int countAll = abiBiblioLogic.countAllBibliotecheFiltered(keys);


		ArrayList<BiblioModel> sublist = new ArrayList<BiblioModel>();

		Iterator<Biblioteca> itBiblio = biblioteche.iterator();

		while (itBiblio.hasNext()) {

			Biblioteca biblioteca = (Biblioteca) itBiblio.next();

			BiblioModel biblioModel = new BiblioModel();
			// Id biblioteca
			biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());
			// Codice iccu
			biblioModel.setCodice(Utility.createIccuCode(biblioteca.getIsilStato(), biblioteca.getIsilProvincia(), "" + biblioteca.getIsilNumero()));
			biblioModel.setDenominazione(biblioteca.getDenominazioneUfficiale());
			biblioModel.setIndirizzo(biblioteca.getIndirizzo());
			// Comune
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(biblioteca.getComune().getDenominazione());
			tmpComune.setIdComune(biblioteca.getComune().getIdComune());
			tmpComune.setIdProvincia(biblioteca.getComune().getProvincia().getIdProvincia());
			biblioModel.setComune(tmpComune);

			biblioModel.setComuneDenominazione(tmpComune);
			// Utente ultima modifica
			biblioModel.setUtenteUltimaModifica((biblioteca.getUtenteUltimaModifica() == null ? "" : biblioteca.getUtenteUltimaModifica().getLogin()));
			// data ultima modifica catalogazione
			biblioModel.setCatalogazioneDataModifica(biblioteca.getCatalogazioneDataModifica() != null ? biblioteca.getCatalogazioneDataModifica() : null);
			// Stato catalogazione
			biblioModel.setStatoCatalogazione(biblioteca.getStatoBibliotecaWorkflow().getLabel());

			sublist.add(biblioModel);
		}

		return new BasePagingLoadResult<BiblioModel>(sublist, config.getOffset(), countAll);
	}
	
	/* Servizio ricerca biblioteca via codice */
	public PagingLoadResult<BiblioModel> ricercaBiblioViaCodice(String stato, String provincia, String numero) {
		Biblioteca biblioteca = abiBiblioLogic.ricercaBiblioViaCodice(stato, provincia, numero);

		ArrayList<BiblioModel> sublist = new ArrayList<BiblioModel>();
		if (biblioteca != null) {
			BiblioModel biblioModel = new BiblioModel();
			// Id biblioteca
			biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());
			// Codice iccu
			biblioModel.setCodice(Utility.createIccuCode(biblioteca.getIsilStato(), biblioteca.getIsilProvincia(), "" + biblioteca.getIsilNumero()));
			biblioModel.setDenominazione(biblioteca.getDenominazioneUfficiale());
			biblioModel.setIndirizzo(biblioteca.getIndirizzo());
			// Comune
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(biblioteca.getComune().getDenominazione());
			tmpComune.setIdComune(biblioteca.getComune().getIdComune());
			tmpComune.setIdProvincia(biblioteca.getComune().getProvincia().getIdProvincia());
			biblioModel.setComune(tmpComune);

			biblioModel.setComuneDenominazione(tmpComune);
			// Utente ultima modifica
			biblioModel.setUtenteUltimaModifica((biblioteca.getUtenteUltimaModifica() == null ? "" : biblioteca.getUtenteUltimaModifica().getLogin()));
			// data ultima modifica catalogazione
			biblioModel.setCatalogazioneDataModifica(biblioteca.getCatalogazioneDataModifica() != null ? biblioteca.getCatalogazioneDataModifica() : null);
			// Stato catalogazione
			biblioModel.setStatoCatalogazione(biblioteca.getStatoBibliotecaWorkflow().getLabel());

			sublist.add(biblioModel);
			return new BasePagingLoadResult<BiblioModel>(sublist, 0, 1);
		}
		else return new BasePagingLoadResult<BiblioModel>(sublist, 0, 0);

	}

	@Override
	/* Servizio per la restituzione delle biblioteche assegnate ad un utente */
	public PagingLoadResult<BiblioModel> getBibliotecheUtente(final PagingLoadConfig config) {
		int start = config.getOffset();
		int limit = config.getLimit();
		int id_utente = 0;
		if (config.get("id_utente") != null)
			id_utente = ((Integer) config.get("id_utente")).intValue();

		String sortDir = config.getSortInfo().getSortDir().toString();
		String sortField = config.getSortInfo().getSortField();

		/* Load dati */		
		int countAll = abiBiblioLogic.countAllBibliotecheUtente(id_utente);
		if (countAll <= 0)
			return new BasePagingLoadResult<BiblioModel>(new ArrayList<BiblioModel>(), config.getOffset(), countAll);
		List<Biblioteca> biblioteche = abiBiblioLogic.ricercaBibliotecheUtente(id_utente, start, limit, sortField, sortDir);

		ArrayList<BiblioModel> sublist = new ArrayList<BiblioModel>();

		for (Biblioteca entry : biblioteche) {
			BiblioModel biblioModel = new BiblioModel();

			/* Id biblioteca */
			biblioModel.setIdBiblio(entry.getIdBiblioteca());

			/* Codice iccu */
			biblioModel.setCodice(Utility.createIccuCode(entry.getIsilStato(), entry.getIsilProvincia(), "" + entry.getIsilNumero()));
			biblioModel.setDenominazione(entry.getDenominazioneUfficiale());
			biblioModel.setIndirizzo(entry.getIndirizzo());

			/* Comune */
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(entry.getComune().getDenominazione());
			tmpComune.setIdComune(entry.getComune().getIdComune());
			tmpComune.setIdProvincia(entry.getComune().getProvincia().getIdProvincia());
			biblioModel.setComune(tmpComune);
			biblioModel.setComuneDenominazione(tmpComune);

			/* Utente ultima modifica */
			biblioModel.setUtenteUltimaModifica((entry.getUtenteUltimaModifica() == null ? "" : entry.getUtenteUltimaModifica().getLogin()));

			/* Data ultima modifica catalogazione */
			biblioModel.setCatalogazioneDataModifica(entry.getCatalogazioneDataModifica() != null ? entry.getCatalogazioneDataModifica() : null);

			/* Stato catalogazione */
			biblioModel.setStatoCatalogazione(entry.getStatoBibliotecaWorkflow().getLabel());

			sublist.add(biblioModel);
		}

		return new BasePagingLoadResult<BiblioModel>(sublist, config.getOffset(), countAll);
	}

	@Override
	public List<VoceUnicaModel> getStatiWorkFlow() {
		List<StatoBibliotecaWorkflow> listaBiblio = abiBiblioLogic
		.getListaStatiWorkFlow();
		List<VoceUnicaModel> tmp = new ArrayList<VoceUnicaModel>();

		Iterator<StatoBibliotecaWorkflow> it = listaBiblio.iterator();

		VoceUnicaModel voceUnicaModelTmp = null;
		while (it.hasNext()) {
			StatoBibliotecaWorkflow statoBibliotecaWorkflowTmp = (StatoBibliotecaWorkflow) it
			.next();

			voceUnicaModelTmp = new VoceUnicaModel();
			voceUnicaModelTmp.setIdRecord(statoBibliotecaWorkflowTmp
					.getIdStato());
			voceUnicaModelTmp.setEntry(statoBibliotecaWorkflowTmp.getLabel());

			tmp.add(voceUnicaModelTmp);
		}

		return tmp;
	}

	@Override
	public List<ContattiModel> getContattiBibliotecaById(int id_biblio) {
		/**
		 * LOAD CONTATTI
		 * */
		List<Contatti> contatti = abiBiblioLogic
		.getListaContattiBibliotecaById(id_biblio);
		List<ContattiModel> listRecapiti = new ArrayList<ContattiModel>();

		Iterator<Contatti> itContatti = contatti.iterator();
		while (itContatti.hasNext()) {
			/**
			 * MAPPING
			 * */
			Contatti contatto = (Contatti) itContatti.next();
			ContattiModel contattiModel = new ContattiModel();

			contattiModel.setIdContatti(contatto.getIdContatti());
			contattiModel.setIdBiblioteca(contatto.getBiblioteca()
					.getIdBiblioteca());
			contattiModel.setContattiTipoLabel(contatto.getContattiTipo()
					.getDescrizione());
			contattiModel.setDescr(contatto.getNote());
			contattiModel.setValore(contatto.getValore());
			listRecapiti.add(contattiModel);
		}

		return listRecapiti;

	}

	@Override
	public BiblioModel getBiblioteca(int id_biblio) {
		/**
		 * LOAD BIBLIOTECA
		 * */
		Biblioteca biblioteca = abiBiblioLogic.getBibliotecaByIdWithAntiLazyIteration(id_biblio);
		/**
		 * MAPPING
		 * */
		BiblioModel biblioModel = bean2DtoBiblioMapping(biblioteca);

		return biblioModel;
	}

	@Override
	public void saveContatti(ContattiModel nuovoContatto, boolean modifica) {
		/**
		 * MAPPING ContattiModel -> Contatti
		 * */
		Contatti contatti = new Contatti();
		if (modifica == true) {
			contatti.setIdContatti(nuovoContatto.getIdContatti());
		}
		Biblioteca b = new Biblioteca();
		b.setIdBiblioteca(nuovoContatto.getIdBiblioteca());
		contatti.setBiblioteca(b);

		contatti.setNote(nuovoContatto.getDescr());
		contatti.setValore(nuovoContatto.getValore());

		ContattiTipo ctipo = new ContattiTipo();
		// ctipo.setDescrizione(nuovoContatto.getContattiTipoLabel());
		ctipo.setIdContattiTipo(nuovoContatto.getContattiTipo());
		contatti.setContattiTipo(ctipo);

		abiBiblioLogic.saveContatti(contatti, modifica);
		
	}
 
	@Override
	public List<VoceUnicaModel> getTipologieContatti() {

		List<VoceUnicaModel> contattiTipoModels = new ArrayList<VoceUnicaModel>();

		/**
		 * LOAD ContattiTipo
		 * */
		List<ContattiTipo> listaContattiTipo = abiBiblioLogic.getTipologieContatti();

		/**
		 * MAPPING ContattiTipo -> VoceUnicaModel
		 * */
		Iterator<ContattiTipo> it = listaContattiTipo.iterator();
		while (it.hasNext()) {
			ContattiTipo contattiTipo = (ContattiTipo) it.next();

			VoceUnicaModel voce = new VoceUnicaModel();
			voce.setIdRecord(contattiTipo.getIdContattiTipo());
			voce.setEntry(contattiTipo.getDescrizione());

			contattiTipoModels.add(voce);
		}
		return contattiTipoModels;
	}

	@Override
	public void removeContatto(int id_contatto) {
		abiBiblioLogic.removeContatto(id_contatto);

	}

	@Override
	public List<VoceUnicaModel> getDenominazioniPrecedenti(int id_biblioteca) {

		List<DenominazioniPrecedenti> denominazioniPrecedentis = abiBiblioLogic
		.getDenominazioniPrecedenti(id_biblioteca);
		List<VoceUnicaModel> voceUnicaModels = new ArrayList<VoceUnicaModel>();
		Iterator<DenominazioniPrecedenti> it = denominazioniPrecedentis
		.iterator();
		while (it.hasNext()) {
			DenominazioniPrecedenti denominazioniPrecedenti = (DenominazioniPrecedenti) it
			.next();
			VoceUnicaModel voceUnicaModel = new VoceUnicaModel();
			voceUnicaModel.setEntry(denominazioniPrecedenti.getDenominazione());
			voceUnicaModel.setIdBiblioteca(denominazioniPrecedenti
					.getBiblioteca().getIdBiblioteca());
			voceUnicaModel.setIdRecord(denominazioniPrecedenti
					.getIdDenominazioniPrecedenti());

			voceUnicaModels.add(voceUnicaModel);
		}

		return voceUnicaModels;
	}

	@Override
	public void removeDenominazionePrecedente(int id_record) {

		abiBiblioLogic.removeDenominazionePrecedente(id_record);

	}

	public void saveDenominazionePrecendente(VoceUnicaModel nuovaDenominazione,	boolean modifica) {

		DenominazioniPrecedenti denominazioniPrecedenti = new DenominazioniPrecedenti();
		if (modifica == true) {
			denominazioniPrecedenti.setIdDenominazioniPrecedenti(nuovaDenominazione.getIdRecord());
		}

		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setIdBiblioteca(nuovaDenominazione.getIdBiblioteca());
		denominazioniPrecedenti.setBiblioteca(biblioteca);

		denominazioniPrecedenti.setDenominazione(nuovaDenominazione.getEntry());

		abiBiblioLogic.addDenominazionePrecendente(denominazioniPrecedenti, modifica);
	}

	@Override
	public List<VoceUnicaModel> getDenominazioniAlternative(int id_biblioteca) {

		List<DenominazioniAlternative> denominazioniAlternatives = abiBiblioLogic
		.getDenominazioniAlternative(id_biblioteca);
		List<VoceUnicaModel> voceUnicaModels = new ArrayList<VoceUnicaModel>();
		Iterator<DenominazioniAlternative> it = denominazioniAlternatives
		.iterator();
		while (it.hasNext()) {
			DenominazioniAlternative denominazioniAlternative = (DenominazioniAlternative) it
			.next();
			VoceUnicaModel voceUnicaModel = new VoceUnicaModel();
			voceUnicaModel
			.setEntry(denominazioniAlternative.getDenominazione());
			voceUnicaModel.setIdBiblioteca(denominazioniAlternative
					.getBiblioteca().getIdBiblioteca());
			voceUnicaModel.setIdRecord(denominazioniAlternative
					.getIdDenominazioniAlternative());

			voceUnicaModels.add(voceUnicaModel);
		}

		return voceUnicaModels;
	}

	@Override
	public void removeDenominazioneAlternativa(int id_record) {

		abiBiblioLogic.removeDenominazioneAlternativa(id_record);
	}

	@Override
	public void saveDenominazioneAlternativa(VoceUnicaModel nuovaDenominazione,	boolean modifica) {
		DenominazioniAlternative denominazioniAlternative = new DenominazioniAlternative();
		if (modifica == true) {

			denominazioniAlternative
			.setIdDenominazioniAlternative(nuovaDenominazione
					.getIdRecord());
		}

		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setIdBiblioteca(nuovaDenominazione.getIdBiblioteca());
		denominazioniAlternative.setBiblioteca(biblioteca);

		denominazioniAlternative
		.setDenominazione(nuovaDenominazione.getEntry());

		abiBiblioLogic.addDenominazioneAlternativa(denominazioniAlternative, modifica);

	}

	@Override
	public void aggiornaDenominazioneUfficiale(String denominazione,
			int id_biblioteca) {

		abiBiblioLogic.aggiornaDenominazioneUfficiale(denominazione,
				id_biblioteca);
	}

	@Override
	public void aggiornaIndirizzo(HashMap<String, Object> param,
			int id_biblioteca) {
		abiBiblioLogic.aggiornaIndirizzo(param, id_biblioteca);
	}

	@Override
	public List<BiblioModel> getPuntiDiServizioDecentratiByIdBiblioteca(
			int id_biblioteca) {

		List<Biblioteca> puntiDecentratDB = abiBiblioLogic
		.getPuntiDiServizioDecentrati(id_biblioteca);
		List<BiblioModel> puntiDecentratiModel = new ArrayList<BiblioModel>();

		Iterator<Biblioteca> itb = puntiDecentratDB.iterator();
		while (itb.hasNext()) {
			Biblioteca biblioteca = (Biblioteca) itb.next();
			BiblioModel biblioModel = new BiblioModel();
			biblioModel
			.setDenominazione(biblioteca.getDenominazioneUfficiale());
			biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(biblioteca.getComune()
					.getDenominazione());
			tmpComune.setIdComune(biblioteca.getComune().getIdComune());
			tmpComune.setIdProvincia(biblioteca.getComune().getProvincia()
					.getIdProvincia());
			biblioModel.setComune(tmpComune);

			biblioModel.setComuneDenominazione(tmpComune);

			biblioModel.setIsilStato(biblioteca.getIsilStato());
			biblioModel.setIsilProvincia(biblioteca.getIsilProvincia());

			biblioModel.setIsilNumero(
					/* Aggiungo gli zeri ad Isil Numero fino a 4 cifre */
					Utility.zeroFill("" + biblioteca.getIsilNumero(), 4));

			puntiDecentratiModel.add(biblioModel);
		}
		return puntiDecentratiModel;
	}

	@Override
	public void addPuntoDiServizioDecentrato(int id_bibloteca_padre, int id_biblioteca_figlio) {
		
		abiBiblioLogic.addPuntoDiServizioDecentrato(id_bibloteca_padre,	id_biblioteca_figlio);

	}

	@Override
	public PagingLoadResult<BiblioModel> getPuntiDiServizioDecentratiPossibili( ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;

		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		String isil_provincia = (String) m.get("isil_provincia");

		int countAll = abiBiblioLogic.countAllByIsilProvinciaAndFiltered(
				isil_provincia, query);
		List<Biblioteca> puntiDecentratDB = abiBiblioLogic
		.getPuntiDiServizioDecentratiPossibili(isil_provincia, query,
				limit, start);
		List<BiblioModel> sublist = new ArrayList<BiblioModel>();

		Iterator<Biblioteca> itb = puntiDecentratDB.iterator();
		while (itb.hasNext()) {
			Biblioteca biblioteca = (Biblioteca) itb.next();
			BiblioModel biblioModel = new BiblioModel();
			biblioModel
			.setDenominazione(biblioteca.getDenominazioneUfficiale());
			biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(biblioteca.getComune()
					.getDenominazione());
			tmpComune.setIdComune(biblioteca.getComune().getIdComune());
			tmpComune.setIdProvincia(biblioteca.getComune().getProvincia()
					.getIdProvincia());
			biblioModel.setComune(tmpComune);

			biblioModel.setComuneDenominazione(tmpComune);

			biblioModel.setIsilStato(biblioteca.getIsilStato());
			biblioModel.setIsilProvincia(biblioteca.getIsilProvincia());

			biblioModel.setIsilNumero(
					/* Aggiungo gli zeri ad Isil Numero fino a 4 cifre */
					Utility.zeroFill("" + biblioteca.getIsilNumero(), 4));

			sublist.add(biblioModel);
		}
		return new BasePagingLoadResult<BiblioModel>(sublist, start, countAll);

	}

	@Override
	public PagingLoadResult<EnteModel> getEntiPaginatiFilteredPerCombos(
			ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");
		int countAll = abiBiblioLogic.countAllEntiFiltered(query);
		List<Ente> enti = abiBiblioLogic.getEntiPaginatiFilteredPerCombos(
				query, start, limit);
		List<EnteModel> sublist = new ArrayList<EnteModel>();
		Iterator<Ente> ite = enti.iterator();
		while (ite.hasNext()) {
			Ente ente = (Ente) ite.next();

			EnteModel tmpEnte = new EnteModel();

			tmpEnte.setIdEnte(ente.getIdEnte());
			tmpEnte.setAsiaAsip(ente.getAsiaAsip());
			tmpEnte.setCodiceFiscale(ente.getCodiceFiscale());
			tmpEnte.setDenominazione(ente.getDenominazione());
			tmpEnte.setPartitaIva(ente.getPartitaIva());
			tmpEnte.setEnteObiettivo(new VoceUnicaModel(ente.getEnteObiettivo()
					.getIdEnteObiettivo(), ente.getEnteObiettivo()
					.getDescrizione()));
			tmpEnte.setEnteTipologiaAmministrativa(new VoceUnicaModel(ente
					.getEnteTipologiaAmministrativa()
					.getIdEnteTipologiaAmministrativa(), ente
					.getEnteTipologiaAmministrativa().getDescrizione()));
			tmpEnte.setStato(new StatoModel(ente.getStato().getIdStato(), ente
					.getStato().getDenominazione(), ente.getStato().getSigla()));

			sublist.add(tmpEnte);
		}
		return new BasePagingLoadResult<EnteModel>(sublist, start, countAll);
	}

	@Override
	public PagingLoadResult<FondiSpecialiModel> getDenominazioneFondiSpecialiPossibiliFilteredPerCombos(ModelData loadConfig) {
		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		List<FondiSpecialiModel> sublist = new ArrayList<FondiSpecialiModel>();

		int countAll = abiBiblioLogic.countAllDenominazioneFondiSpecialiPossibili(query);

		List<FondiSpeciali> fondiSpecialis =abiBiblioLogic.getDenominazioneFondiSpecialiPossibiliFiltered(query, start, limit);
		Iterator<FondiSpeciali> it =fondiSpecialis.iterator();
		while (it.hasNext()) {
			FondiSpeciali fondiSpeciali = (FondiSpeciali) it.next();

			FondiSpecialiModel fondiSpecialiModel = new FondiSpecialiModel();

			fondiSpecialiModel.setIdFondiSpeciali(fondiSpeciali.getIdFondiSpeciali());
			fondiSpecialiModel.setDenominazione(fondiSpeciali.getDenominazione());

			if(fondiSpeciali.getDescrizione()!=null){
				fondiSpecialiModel.setDescrizione(fondiSpeciali.getDescrizione());	
			}

			if (fondiSpeciali.getFondoDepositato()!=null){
				fondiSpecialiModel.setFondoDepositato(fondiSpeciali.getFondoDepositato()?"Si":"No");
			}else 	fondiSpecialiModel.setFondoDepositato("Non specificato");

			if(fondiSpeciali.getFondiSpecialiCatalogazioneInventario()!=null){
				fondiSpecialiModel.setIdCatalogoInventario(fondiSpeciali.getFondiSpecialiCatalogazioneInventario().getIdFondiSpecialiCatalogazioneInventario());
				fondiSpecialiModel.setCatalogoInventarioDescr(fondiSpeciali.getFondiSpecialiCatalogazioneInventario().getDescrizione());
			}

			if(fondiSpeciali.getCatalogazioneInventarioUrl()!=null){
				fondiSpecialiModel.setUrlOnline(fondiSpeciali.getCatalogazioneInventarioUrl());
			}

			sublist.add(fondiSpecialiModel);
		}
		return new BasePagingLoadResult<FondiSpecialiModel>(sublist, start, countAll);
	}

	@Override
	public void removePuntoDiServizioDecentrato(int id_bibloteca) {
		abiBiblioLogic.removePuntoDiServizioDecentrato(id_bibloteca);

	}

	@Override
	public List<VoceUnicaModel> getSistemiBibliotecheByIdBiblioteca(
			int id_biblioteca) {

		List<SistemiBiblioteche> biblioteches = abiBiblioLogic
		.getSistemiBibliotecheByIdBiblioteca(id_biblioteca);

		List<VoceUnicaModel> voceUnicaModels = new ArrayList<VoceUnicaModel>();
		Iterator<SistemiBiblioteche> it = biblioteches.iterator();
		while (it.hasNext()) {
			SistemiBiblioteche sistemiBiblioteche = (SistemiBiblioteche) it
			.next();
			VoceUnicaModel voceUnicaModel = new VoceUnicaModel();
			voceUnicaModel.setIdRecord(sistemiBiblioteche
					.getIdSistemiBiblioteche());
			voceUnicaModel.setEntry(sistemiBiblioteche.getDescrizione());
			voceUnicaModel.setIdBiblioteca(id_biblioteca);

			voceUnicaModels.add(voceUnicaModel);
		}
		return voceUnicaModels;
	}

	@Override
	public void addSistemaBiblioteca(int id_biblioteca,
			int id_sistema_biblioteche) {
		abiBiblioLogic.addSistemaBiblioteca(id_biblioteca,
				id_sistema_biblioteche);
	}

	@Override
	public void removeSistemaBiblioteca(int id_biblioteca,
			int id_sistema_biblioteche) {
		abiBiblioLogic.removeSistemaBiblioteca(id_biblioteca,
				id_sistema_biblioteche);
	}

	private BiblioModel bean2DtoBiblioMapping(Biblioteca biblioteca) {
		BiblioModel biblioModel = new BiblioModel();
		// Id biblioteca
		biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());
		// Codice iccu
		biblioModel.setCodice(Utility.createIccuCode(biblioteca.getIsilStato(),	biblioteca.getIsilProvincia(), "" + biblioteca.getIsilNumero()));
		biblioModel.setDenominazione(biblioteca.getDenominazioneUfficiale());
		biblioModel.setIndirizzo(biblioteca.getIndirizzo());
		// Comune
		ComuniModel tmpComune = new ComuniModel();
		tmpComune.setDenominazione(biblioteca.getComune().getDenominazione());
		tmpComune.setIdComune(biblioteca.getComune().getIdComune());
		tmpComune.setIdProvincia(biblioteca.getComune().getProvincia().getIdProvincia());
		biblioModel.setComune(tmpComune);

		biblioModel.setComuneDenominazione(tmpComune);
		// Utente ultima modifica
		biblioModel.setUtenteUltimaModifica((biblioteca.getUtenteUltimaModifica() == null ? null : biblioteca.getUtenteUltimaModifica().getLogin()));
		// data ultima modifica catalogazione
		biblioModel.setCatalogazioneDataModifica(biblioteca.getCatalogazioneDataModifica() != null ? biblioteca.getCatalogazioneDataModifica() : null);

		// Stato catalogazione
		biblioModel.setStatoCatalogazione(biblioteca.getStatoBibliotecaWorkflow().getLabel());
		// Profilo storico e sede
		biblioModel.setdenominazioneEdificio(biblioteca.getEdificioDenominazione() == null ? "" : biblioteca.getEdificioDenominazione());
		if(biblioteca.getEdificioMonumentale() != null){
			biblioModel.setIsEdificioMonumentale(biblioteca.getEdificioMonumentale());
		}else 	biblioModel.setIsEdificioMonumentale(null);
		if(biblioteca.getEdificioAppositamenteCostruito() != null){
			biblioModel.setIsAppositamenteCostruito(biblioteca.getEdificioAppositamenteCostruito());
		}else 	biblioModel.setIsAppositamenteCostruito(null);
		biblioModel.setEdificioDataCostruzione(biblioteca.getEdificioDataCostruzione() == null ? "" : biblioteca.getEdificioDataCostruzione());
		// LOCALI
		biblioModel.setMqAperti(biblioteca.getMlAperti() == null ? 0 : biblioteca.getMlAperti());
		biblioModel.setMqMagazzini(biblioteca.getMlMagazzini() == null ? 0 : biblioteca.getMlMagazzini());
		biblioModel.setMqPubblici(biblioteca.getMqPubblici() == null ? 0 : biblioteca.getMqPubblici());
		biblioModel.setMqSupBilbio(biblioteca.getMqTotali() == null ? 0	: biblioteca.getMqTotali());
		// Postazioni
		biblioModel.setPostiAscolto(biblioteca.getPostiAudio() == null ? 0 : biblioteca.getPostiAudio());
		biblioModel.setPostiInternet(biblioteca.getPostiInternet() == null ? 0 : biblioteca.getPostiInternet());
		biblioModel.setPostiLettura(biblioteca.getPostiLettura() == null ? 0 : biblioteca.getPostiLettura());
		biblioModel.setPostiVideo(biblioteca.getPostiVideo() == null ? 0 : biblioteca.getPostiVideo());
		// Indirizzo
		biblioModel.setFrazione(biblioteca.getFrazione());
		biblioModel.setCap(biblioteca.getCap());
		biblioModel.setProvincia(biblioteca.getComune().getProvincia().getDenominazione());
		biblioModel.setRegione(biblioteca.getComune().getProvincia().getRegione().getDenominazione());
		biblioModel.setStato(biblioteca.getComune().getProvincia().getRegione().getStato().getDenominazione());
		// Geo
		if (biblioteca.getGeolocalizzazione() != null) {
			biblioModel.setGeoX(biblioteca.getGeolocalizzazione().getLatitudine());
			biblioModel.setGeoY(biblioteca.getGeolocalizzazione().getLongitudine());
		}
		// Ente
		EnteModel tmpEnte = new EnteModel();

		tmpEnte.setIdEnte(biblioteca.getEnte().getIdEnte());
		tmpEnte.setAsiaAsip(biblioteca.getEnte().getAsiaAsip());
		tmpEnte.setCodiceFiscale(biblioteca.getEnte().getCodiceFiscale());
		tmpEnte.setDenominazione(biblioteca.getEnte().getDenominazione());
		tmpEnte.setPartitaIva(biblioteca.getEnte().getPartitaIva());
		tmpEnte.setStato(new StatoModel(biblioteca.getEnte().getStato().getIdStato(), biblioteca.getEnte().getStato().getDenominazione(), biblioteca.getEnte().getStato().getSigla()));

		tmpEnte.setEnteObiettivo(new VoceUnicaModel(biblioteca.getEnte().getEnteObiettivo().getIdEnteObiettivo(), biblioteca.getEnte().getEnteObiettivo().getDescrizione()));
		tmpEnte.setEnteTipologiaAmministrativa(new VoceUnicaModel(biblioteca.getEnte().getEnteTipologiaAmministrativa().getIdEnteTipologiaAmministrativa(), biblioteca.getEnte().getEnteTipologiaAmministrativa().getDescrizione()));

		biblioModel.setEnte(tmpEnte);
		// Autonomia amministrativa
		if ((Boolean) biblioteca.getAutonomiaAmministrativa() != null) {
			biblioModel.setAutonomiaAmministrativa(biblioteca.getAutonomiaAmministrativa());
		}
		biblioModel.setStruttura_gerarchica_sovraordinata(biblioteca.getStrutturaGerarchicaSovraordinata());
		// Autonomia amministrativa
		VoceUnicaModel tipologiaFunzionale = new VoceUnicaModel();
		tipologiaFunzionale.setIdRecord(biblioteca.getTipologiaFunzionale().getIdTipologiaFunzionale());
		tipologiaFunzionale.setEntry(biblioteca.getTipologiaFunzionale().getDescrizione());
		biblioModel.setTipologiaFunzionale(tipologiaFunzionale);
		// Info fondazione
		biblioModel.setDataFondazione(biblioteca.getDataFondazione());
		biblioModel.setDataIstituzione(biblioteca.getDataIstituzione());
		// Modalità accesso
		if ((Boolean) biblioteca.getAccessoRiservato() != null) {
			biblioModel.setAccessoRiservato(biblioteca.getAccessoRiservato());
		}
		if (biblioteca.getAccessoLimiteEtaMin() != null)
			biblioModel.setLimiteEtaMin(biblioteca.getAccessoLimiteEtaMin());
		if (biblioteca.getAccessoLimiteEtaMax() != null)
			biblioModel.setLimiteEtaMax(biblioteca.getAccessoLimiteEtaMax());

		// Accesso portatori handicap
		if(biblioteca.getAccessoHandicap() != null){
			biblioModel.setAccessoHandicap(biblioteca.getAccessoHandicap());
		}else 	biblioModel.setAccessoHandicap(null);
		// Regolemento
		/* Nel mapping da bean---->dto viene recuperato solo l'ultimo
		 * elemento della lista (size-1) che è quello aggiornato, poichè  nella
		 * grafica viene visualizzato solo un elemento mentre sul db si ha una
		 * lista di elementi
		 */
		Regolamento regolamento = null;
		if (biblioteca.getRegolamentos().size() > 0) {
			regolamento = biblioteca.getRegolamentos().get(biblioteca.getRegolamentos().size() - 1);

			RegolamentoModel regolamentoModel = new RegolamentoModel();
			regolamentoModel.setIdRegolamento(regolamento.getIdRegolamento());
			regolamentoModel.setIdBiblioteca(regolamento.getBiblioteca().getIdBiblioteca());
			regolamentoModel.setRiferimentoNormativa(regolamento.getRiferimentoNormativa());
			regolamentoModel.setUrl(regolamento.getUrl());
			biblioModel.setRegolamento(regolamentoModel);
		}
		//Patrimonio librario
		if (biblioteca.getInventarioCartaceo()!=null){
			biblioModel.setInventarioCartaceo(biblioteca.getInventarioCartaceo());
		}else biblioModel.setInventarioCartaceo(null);
		if(biblioteca.getInventarioInformatizzato()!=null){
			biblioModel.setInventarioInformatizzato(biblioteca.getInventarioInformatizzato());		
		} else biblioModel.setInventarioInformatizzato(null);
		if(biblioteca.getCatalogoTopograficoCartaceo()!=null){
			biblioModel.setCatalogoCartaceo(biblioteca.getCatalogoTopograficoCartaceo());
		} else biblioModel.setCatalogoCartaceo(null);
		if(biblioteca.getCatalogoTopograficoInformatizzato()!=null){
			biblioModel.setCatalogoInformatizzato(biblioteca.getCatalogoTopograficoInformatizzato());
		} else 	biblioModel.setCatalogoInformatizzato(null);

		biblioModel.setFondiAntichiFinoAl1830(biblioteca.getFondiAntichiConsistenza().getIdFondiAntichiConsistenza());
		//BIBLIOGRAFIA
		if(biblioteca.getBibliografias().size()>0) {
			biblioModel.setBibliografia(biblioteca.getBibliografias().get(0).getDescrizione());
		}

		//INFORMAZIONI BIBLIOGRAFICHE
		if(	biblioteca.getGestisceServizioBibliograficoEsterno()!=null){
			biblioModel.setServizioBibliograficoEsterno(biblioteca.getGestisceServizioBibliograficoEsterno());
		}else  biblioModel.setServizioBibliograficoEsterno(null);

		if(	biblioteca.getGestisceServizioBibliograficoInterno()!=null){
			biblioModel.setServizioBibliograficoInterno(biblioteca.getGestisceServizioBibliograficoInterno());
		} else biblioModel.setServizioBibliograficoInterno(null);

		//ACCESSO INTERNET
		if (biblioteca.getAccessoInternetPagamento()==null)
			biblioModel.setAccessoInternetAPagamento("Non specificato");
		else
			biblioModel.setAccessoInternetAPagamento(biblioteca.getAccessoInternetPagamento()==true?"Si":"No");

		if (biblioteca.getAccessoInternetTempo()==null)
			biblioModel.setAccessoInternetATempo("Non specificato");
		else 	
			biblioModel.setAccessoInternetATempo(biblioteca.getAccessoInternetTempo()==true?"Si":"No");

		if (biblioteca.getAccessoInternetProxy()==null)
			biblioModel.setAccessoInternetProxy("Non specificato");
		else 	
			biblioModel.setAccessoInternetProxy(biblioteca.getAccessoInternetProxy()==true?"Si":"No");

		//PRESTITO INTERBIBLIOTECARIO E PROCEDURE
		if (biblioteca.getPrestitoInterbiblioNazionale()==null) {
			biblioModel.setPrestitoNazionale(null);
		} else biblioModel.setPrestitoNazionale(biblioteca.getPrestitoInterbiblioNazionale()?true:false);

		if (biblioteca.getPrestitoInterbiblioInternazionale()==null) {
			biblioModel.setPrestitoInternazionale(null);
		} else biblioModel.setPrestitoInternazionale(biblioteca.getPrestitoInterbiblioInternazionale()?true:false);

		if (biblioteca.getProcedureIllAutomatizzate()==null){
			biblioModel.setProcedureAutomatizzate(null);
		} else biblioModel.setProcedureAutomatizzate(biblioteca.getProcedureIllAutomatizzate()?true:false);
		//Informazioni personale

		biblioModel.setPersonaleTotale(biblioteca.getPersonaleTotale());
		biblioModel.setPersonaleEsterno(biblioteca.getPersonaleEsterno());
		biblioModel.setPersonalePartTime(biblioteca.getPersonalePartTime());
		biblioModel.setPersonaleTemporaneo(biblioteca.getPersonaleTemporaneo());
		//Informazioni supplementari utenti

		biblioModel.setIscrittiPrestitoUltimi12Mesi(biblioteca.getUtentiIscrittiPrestitoAnno());
		biblioModel.setIngressiUltimi12Mesi(biblioteca.getUtenti());
		biblioModel.setUtentiIscritti(biblioteca.getUtentiIscritti());
		//BILANCIO
		biblioModel.setUsciteTotali(biblioteca.getBilancioUscite());
		biblioModel.setUscitePersonale(biblioteca.getBilancioUscitePersonale());
		biblioModel.setUsciteAcquistoPatrimonio(biblioteca.getBilancioUsciteIncrementoPatrimonio());
		biblioModel.setUsciteFunzionamento(biblioteca.getBilancioUsciteFunzionamento());
		biblioModel.setUsciteAutomazione(biblioteca.getBilancioUsciteAutomazione());
		biblioModel.setUsciteAltro(biblioteca.getBilancioUsciteVarie());
		biblioModel.setEntrateTotali(biblioteca.getBilancioEntrate());

		//INFORMAZIONI CATALOGATORE
		biblioModel.setNoteCatalogatore(biblioteca.getCatalogazioneNote());
		//prendo l'ultimo elemento della lista
		if(biblioteca.getComunicazionis().size()>0){
			biblioModel.setComunicazioni(biblioteca.getComunicazionis().get(biblioteca.getComunicazionis().size()-1).getDescrizione());
		}

		Calendar dataCensimentoCalendarTmp = Calendar.getInstance();

		if(biblioteca.getCatalogazioneDataCensimento()!=null){
			dataCensimentoCalendarTmp.setTime(biblioteca.getCatalogazioneDataCensimento());
			biblioModel.setDataCensimento(""+dataCensimentoCalendarTmp.get(Calendar.DAY_OF_MONTH)+"/"+(dataCensimentoCalendarTmp.get(Calendar.MONTH)+1)+"/"+dataCensimentoCalendarTmp.get(Calendar.YEAR));
			dataCensimentoCalendarTmp.clear();
		}
		if(biblioteca.getCatalogazioneDataImport()!=null){
			dataCensimentoCalendarTmp.setTime(biblioteca.getCatalogazioneDataImport());
			biblioModel.setDataImport(""+dataCensimentoCalendarTmp.get(Calendar.DAY_OF_MONTH)+"/"+(dataCensimentoCalendarTmp.get(Calendar.MONTH)+1)+"/"+dataCensimentoCalendarTmp.get(Calendar.YEAR));
			dataCensimentoCalendarTmp.clear();
		}
		if(biblioteca.getCatalogazioneDataModificaRemota()!=null){
			dataCensimentoCalendarTmp.setTime(biblioteca.getCatalogazioneDataModificaRemota());
			biblioModel.setDataModificaRemota(""+dataCensimentoCalendarTmp.get(Calendar.DAY_OF_MONTH)+"/"+(dataCensimentoCalendarTmp.get(Calendar.MONTH)+1)+"/"+dataCensimentoCalendarTmp.get(Calendar.YEAR));
			dataCensimentoCalendarTmp.clear();
		}
		if(biblioteca.getCatalogazioneDataModifica()!=null){
			dataCensimentoCalendarTmp.setTime(biblioteca.getCatalogazioneDataModifica());
			biblioModel.setDataModifica(""+dataCensimentoCalendarTmp.get(Calendar.DAY_OF_MONTH)+"/"+(dataCensimentoCalendarTmp.get(Calendar.MONTH)+1)+"/"+dataCensimentoCalendarTmp.get(Calendar.YEAR));
		}
		
		//CODICI (Dati anagrafici)
		biblioModel.setCodiceFiscale(biblioteca.getCodiceFiscale()!=null?biblioteca.getCodiceFiscale():null);
		biblioModel.setPartitaIva(biblioteca.getPartitaIva()!=null?biblioteca.getPartitaIva():null);
		
		//Itero la lista codici per valorizzare i campi del model
		for(Codici c:biblioteca.getCodicis()){
			switch (c.getCodiciTipo().getIdCodici().intValue()) {
			
			case 1: 
				biblioModel.setACNP(c.getValore()!=null?c.getValore():null);
				break;
			case 2:
				biblioModel.setCEI(c.getValore()!=null?c.getValore():null);
				break;
			case 3:
				biblioModel.setCMBS(c.getValore()!=null?c.getValore():null);
				break;
			case 4:
				biblioModel.setRISM(c.getValore()!=null?c.getValore():null);
				break;
			case 5:
				biblioModel.setSBN(c.getValore()!=null?c.getValore():null);
				break;
			default:
				break;
			}
		}
		
		//STATO CATALOGAZIONE BIBLIOTECA
		List<StatoCatalogazione> catalogaziones = biblioteca.getStatoCatalogaziones();
		if(catalogaziones.size()>0){
			StatoCatalogazione statoCatalogazione = catalogaziones.get(0);

			VoceUnicaModel statoCatalogazioneModel = new VoceUnicaModel();
			statoCatalogazioneModel.setIdRecord(statoCatalogazione.getId().getIdStatoCatalogazione());
			statoCatalogazioneModel.setEntry(statoCatalogazione.getStatoCatalogazioneTipo().getDescrizione());
			biblioModel.setStatoCatalogazioneModel(statoCatalogazioneModel);
		}else{
			biblioModel.setStatoCatalogazioneModel(null);
		}
		return biblioModel;
	}

	@Override
	// @Transactional
	public void updateProfiloStoricoSede(HashMap<String, Object> params,int id_biblioteca) {

		abiBiblioLogic.updateProfiloStoricoSede(params, id_biblioteca);
	}

	@Override
	public void updateInfoLocali(HashMap<String, Object> params,
			int id_biblioteca) {

		abiBiblioLogic.updateInfoLocali(params, id_biblioteca);
	}

	@Override
	public void updateInfoPostazioni(HashMap<String, Object> params,
			int id_biblioteca) {

		abiBiblioLogic.updateInfoPostazioni(params, id_biblioteca);

	}

	@Override
	public void setEnte(int id_biblioteca, HashMap<String, Object> params) {

		VoceUnicaModel eta = (VoceUnicaModel) params.get("enteTipologiaAmministrativa");
		EnteTipologiaAmministrativa enteTipologiaAmministrativa = new EnteTipologiaAmministrativa();
		enteTipologiaAmministrativa.setIdEnteTipologiaAmministrativa(eta.getIdRecord());
		enteTipologiaAmministrativa.setDescrizione(eta.getEntry());

		StatoModel sm = (StatoModel) params.get("stato");
		Stato stato = new Stato();
		stato.setIdStato(sm.getIdStato());
		stato.setDenominazione(sm.getDenominazione());
		stato.setSigla(sm.getSigla());

		abiBiblioLogic.setEnte(id_biblioteca, stato, enteTipologiaAmministrativa, (String) params.get("denominazione"));
	}

	@Override
	public void setAutonomiaAmministrativa(int idBiblio,
			HashMap<String, Object> params) {

		abiBiblioLogic.setAutonomiaAmministrativa(idBiblio, params);
	}

	@Override
	public void setTipologiaFunzionale(int idBiblio, VoceUnicaModel value) {
		TipologiaFunzionale tf = new TipologiaFunzionale();
		tf.setDescrizione(value.getEntry());
		tf.setIdTipologiaFunzionale(value.getIdRecord());

		abiBiblioLogic.setTipologiaFunzionale(idBiblio, tf);
	}

	@Override
	public void setInfoFondazione(int idBiblio, HashMap<String, Object> params) {
		abiBiblioLogic.setInfoFondazione(idBiblio, params);

	}

	@Override
	public void setModalitaAccesso(int idBiblio, HashMap<String, Object> params) {
		abiBiblioLogic.setModalitaAccesso(idBiblio, params);

	}
	
	@Override
	public void setLimiteEtaAccesso(int idBiblio, HashMap<String, Object> params) {
		abiBiblioLogic.setLimiteEtaAccesso(idBiblio, params);

	}

	@Override
	public List<VoceUnicaModel> getModalitaAccessoByIdBiblioteca(
			int id_biblioteca) {

		List<AccessoModalita> accessoModalitas = abiBiblioLogic
		.getAccessoModalitaByIdBiblioteca(id_biblioteca);

		List<VoceUnicaModel> voceUnicaModels = new ArrayList<VoceUnicaModel>();
		Iterator<AccessoModalita> it = accessoModalitas.iterator();
		while (it.hasNext()) {
			AccessoModalita accessoModalita = (AccessoModalita) it.next();
			VoceUnicaModel voceUnicaModel = new VoceUnicaModel();
			voceUnicaModel.setIdRecord(accessoModalita.getIdAccessoModalita());
			voceUnicaModel.setEntry(accessoModalita.getDescrizione());
			voceUnicaModel.setIdBiblioteca(id_biblioteca);

			voceUnicaModels.add(voceUnicaModel);
		}
		return voceUnicaModels;

	}

	@Override
	public void addModalitaAccesso(int id_biblioteca, int id_nuovaModalita) {
		abiBiblioLogic.addModalitaAccesso(id_biblioteca, id_nuovaModalita);
	}

	@Override
	public void removeModalitaAccesso(int id_biblioteca, int id_removeModalita) {
		abiBiblioLogic.removeModalitaAccesso(id_biblioteca, id_removeModalita);
	}

	@Override
	public List<DestinazioneSocialeModel> getDestinazioniSociali(
			int id_biblioteca) {
		List<DestinazioniSociali> destinazioniSocialis = abiBiblioLogic
		.getDestinazioniSocialiByIdBiblioteca(id_biblioteca);
		List<DestinazioneSocialeModel> destinazioniSocialiModels = new ArrayList<DestinazioneSocialeModel>();
		Iterator<DestinazioniSociali> it = destinazioniSocialis.iterator();
		while (it.hasNext()) {
			DestinazioniSociali destinazioniSociali = (DestinazioniSociali) it
			.next();
			DestinazioneSocialeModel destinazioneSocialeModel = new DestinazioneSocialeModel();
			destinazioneSocialeModel.setIdRecord(destinazioniSociali
					.getDestinazioniSocialiTipo().getIdDestinazioniSociali());
			destinazioneSocialeModel.setDescrizione(destinazioniSociali
					.getDestinazioniSocialiTipo().getDescrizione());
			destinazioneSocialeModel.setNote(destinazioniSociali.getNote());

			destinazioniSocialiModels.add(destinazioneSocialeModel);
		}
		return destinazioniSocialiModels;

	}

	@Override
	public void addDestinazioneSociale(int id_biblioteca,
			int id_nuovaDestinazione, String note) {
		abiBiblioLogic.addDestinazioniSociali(id_biblioteca,
				id_nuovaDestinazione, note);

	}

	@Override
	public void removeDestinazioneSociale(int id_biblioteca,
			int id_rimuoviDestinazione) {
		abiBiblioLogic.removeDestinazioneSociale(id_biblioteca,
				id_rimuoviDestinazione);

	}

	@Override
	public void setAccessoHandicap(int idBiblio, Boolean value) {
		abiBiblioLogic.setAccessoHandicap(idBiblio, value);
	}

	@Override
	public void setRegolamento(HashMap<String, String> params, int idBiblio) {
		abiBiblioLogic.setRegolamento(params, idBiblio);

	}

	@Override
	public List<OrariModel> getOrariUfficialiByDay(int id_biblioteca, int id_day) {

		List<OrariModel> orariModels = new ArrayList<OrariModel>();

		List<OrarioUfficiali> orarioUfficialis = abiBiblioLogic
		.getOrariUfficialiByDay(id_biblioteca, id_day);
		Iterator<OrarioUfficiali> ito = orarioUfficialis.iterator();
		while (ito.hasNext()) {
			OrarioUfficiali orarioUfficiali = (OrarioUfficiali) ito.next();

			Calendar tmpCalDalle = Calendar.getInstance();
			tmpCalDalle.setTime(orarioUfficiali.getDalle());

			OrariModel orarioModel = new OrariModel();
			orarioModel.setIdOrario(orarioUfficiali.getIdOrarioUfficiali());
			orarioModel.setStartOre(tmpCalDalle.get(Calendar.HOUR_OF_DAY)<10?"0" + tmpCalDalle.get(Calendar.HOUR_OF_DAY):"" + tmpCalDalle.get(Calendar.HOUR_OF_DAY));
			orarioModel.setStartMin(((tmpCalDalle.get(Calendar.MINUTE) < 10)
					&& ((""+tmpCalDalle.get(Calendar.MINUTE)).length()<2)&&(tmpCalDalle.get(Calendar.MINUTE) > 0) ? "0" : "")+ tmpCalDalle.get(Calendar.MINUTE)+ (((""+tmpCalDalle.get(Calendar.MINUTE)).length()<2)&&(tmpCalDalle.get(Calendar.MINUTE)) == 0 ? "0" : ""));

			Calendar tmpCalAlle = Calendar.getInstance();
			tmpCalAlle.setTime(orarioUfficiali.getAlle());

			orarioModel.setStopOre(tmpCalAlle.get(Calendar.HOUR_OF_DAY)<10?"0" + tmpCalAlle.get(Calendar.HOUR_OF_DAY):"" + tmpCalAlle.get(Calendar.HOUR_OF_DAY));
			orarioModel.setStopMin(((tmpCalAlle.get(Calendar.MINUTE) < 10)&&((""+tmpCalAlle.get(Calendar.MINUTE)).length()<2)&& (tmpCalAlle.get(Calendar.MINUTE) > 0) ? "0" : "")+ tmpCalAlle.get(Calendar.MINUTE)+ (((""+tmpCalAlle.get(Calendar.MINUTE)).length()<2)&&(tmpCalAlle.get(Calendar.MINUTE)) == 0 ? "0" : ""));

			orariModels.add(orarioModel);
		}

		return orariModels;
	}

	@Override
	public void addNuovoOrarioGiornaliero(int id_biblioteca, int id_day,
			OrariModel toSave,boolean modifica) { 

		Date dalle = null;
		Date alle = null;

		String dalleOreString = toSave.getStartOre() + ":"+ toSave.getStartMin() + ":00";

		String alleOreString = toSave.getStopOre() + ":" + toSave.getStopMin() + ":00";

		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		try {

			dalle = (Date) df.parse(dalleOreString);
			alle = (Date) df.parse(alleOreString);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		OrarioUfficiali orarioUfficiali = new OrarioUfficiali();
		if(modifica==true){	
			orarioUfficiali.setIdOrarioUfficiali(toSave.getIdOrario());
		}
		orarioUfficiali.setDalle(dalle);
		orarioUfficiali.setAlle(alle);

		orarioUfficiali.setGiorno(id_day);


		abiBiblioLogic.addNuovoOrarioGiornaliero(id_biblioteca,orarioUfficiali);

	}

	@Override
	public void addNuovoOrarioGiornalieroCustom(int id_biblio,Vector<Integer> id_days, OrariModel toSave) {
		Date dalle = null;
		Date alle = null;
		String dalleOreString = toSave.getStartOre() + ":"+ toSave.getStartMin() + ":00";

		String alleOreString = toSave.getStopOre() + ":" + toSave.getStopMin() + ":00";

		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		try {

			dalle = (Date) df.parse(dalleOreString);
			alle = (Date) df.parse(alleOreString);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		abiBiblioLogic.addNuovoOrarioGiornalieroCustom(id_biblio,id_days,dalle,alle);

	}



	@Override
	public void removeOrarioUfficiale(int id_orarioToRemove) {
		abiBiblioLogic.removeOrarioUfficiale(id_orarioToRemove);
	}

	@Override
	public List<OrariModel> getVariazioniOrari(int id_biblioteca) {
		List<OrariModel> orariModels = new ArrayList<OrariModel>();

		List<OrarioVariazioni> orarioVariazionis=abiBiblioLogic.getVariazioniOrari(id_biblioteca);

		Iterator<OrarioVariazioni> itv = orarioVariazionis.iterator();
		while (itv.hasNext()) {
			OrarioVariazioni orarioVariazioni = (OrarioVariazioni) itv.next();
			OrariModel orarioModel = new OrariModel();
			orarioModel.setIdOrario(orarioVariazioni.getIdOrarioVariazioni());
			orarioModel.setPeriodo(orarioVariazioni.getDescrizione());
			if (orarioVariazioni.getGiorno()!=null){
				orarioModel.setGiorno(orarioVariazioni.getGiorno());
				orarioModel.setNomeGiorno();
			}


			if(orarioVariazioni.getDalle()!=null){

				Calendar tmpCalDalle = Calendar.getInstance();
				tmpCalDalle.setTime(orarioVariazioni.getDalle());

				orarioModel.setStartOre(tmpCalDalle.get(Calendar.HOUR_OF_DAY)<10?"0" + tmpCalDalle.get(Calendar.HOUR_OF_DAY):"" + tmpCalDalle.get(Calendar.HOUR_OF_DAY));
				orarioModel.setStartMin(((tmpCalDalle.get(Calendar.MINUTE) < 10)&& ((""+tmpCalDalle.get(Calendar.MINUTE)).length()<2)&&(tmpCalDalle.get(Calendar.MINUTE) > 0) ? "0" : "")+ tmpCalDalle.get(Calendar.MINUTE)+ (((""+tmpCalDalle.get(Calendar.MINUTE)).length()<2)&&(tmpCalDalle.get(Calendar.MINUTE)) == 0 ? "0" : ""));
			}

			if(orarioVariazioni.getAlle()!=null){
				Calendar tmpCalAlle = Calendar.getInstance();
				tmpCalAlle.setTime(orarioVariazioni.getAlle());

				orarioModel.setStopOre(tmpCalAlle.get(Calendar.HOUR_OF_DAY)<10?"0" + tmpCalAlle.get(Calendar.HOUR_OF_DAY):"" + tmpCalAlle.get(Calendar.HOUR_OF_DAY));
				orarioModel.setStopMin(((tmpCalAlle.get(Calendar.MINUTE) < 10)&&((""+tmpCalAlle.get(Calendar.MINUTE)).length()<2)&& (tmpCalAlle.get(Calendar.MINUTE) > 0) ? "0" : "")+ tmpCalAlle.get(Calendar.MINUTE)+ (((""+tmpCalAlle.get(Calendar.MINUTE)).length()<2)&&(tmpCalAlle.get(Calendar.MINUTE)) == 0 ? "0" : ""));
			}
			orariModels.add(orarioModel);
		}

		return orariModels;
	}

	@Override
	public void addNuovaVariazioneOrario(int id_biblioteca, OrariModel toSave, boolean modifica) {
		OrarioVariazioni orarioVariazioni = new OrarioVariazioni();
		if(modifica){
			orarioVariazioni.setIdOrarioVariazioni(toSave.getIdOrario());
		}
		orarioVariazioni.setDalle(convertString2Date(toSave.getStartOre() + ":"+ toSave.getStartMin() + ":00"));
		orarioVariazioni.setAlle(convertString2Date(toSave.getStopOre() + ":"+ toSave.getStopMin() + ":00"));

		orarioVariazioni.setGiorno(toSave.getGiorno());
		orarioVariazioni.setDescrizione(toSave.getPeriodo());
		abiBiblioLogic.addNuovaVariazioneOrario(id_biblioteca,  orarioVariazioni);

	}
	
	@Override
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, OrariModel toSave) {
		Date dalle = null;
		Date alle = null;
		String dalleOreString = toSave.getStartOre() + ":"+ toSave.getStartMin() + ":00";

		String alleOreString = toSave.getStopOre() + ":" + toSave.getStopMin() + ":00";

		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		try {

			dalle = (Date) df.parse(dalleOreString);
			alle = (Date) df.parse(alleOreString);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String periodo = (String) toSave.getPeriodo();
		
		abiBiblioLogic.addNuovaVariazioneOrarioCustom(id_biblioteca, id_days, dalle, alle, periodo); 
	}

	public Date convertString2Date(String dataString){
		Date date = null;
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			date = (Date) df.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	@Override
	public void removeVariazioneOrario(int id_variazioneOrarioToRemove) {

		abiBiblioLogic.removeVariazioneOrario( id_variazioneOrarioToRemove);
	}

	@Override
	public List<VoceUnicaModel> getPeriodiChiusuraByIdBiblio(int id_biblioteca) {
		List<OrarioChiusure> orarioChiusures =abiBiblioLogic.getPeriodiChiusuraByIdBiblio(id_biblioteca);
		List<VoceUnicaModel> orarioChiusureModels= new ArrayList<VoceUnicaModel>();
		Iterator<OrarioChiusure> it = orarioChiusures.iterator();
		while (it.hasNext()) {
			OrarioChiusure orarioChiusure = (OrarioChiusure) it.next();

			VoceUnicaModel orarioModel = new VoceUnicaModel();

			orarioModel.setIdRecord(orarioChiusure.getIdOrarioChiusure());
			orarioModel.setEntry(orarioChiusure.getDescrizione());

			orarioChiusureModels.add(orarioModel);
		}
		return orarioChiusureModels;
	}

	@Override
	public void addNuovoPeriodoChiusura(int id_biblioteca,
			VoceUnicaModel tmpSave, boolean modifica) {
		OrarioChiusure nuovoOrarioChiusure = new OrarioChiusure();
		nuovoOrarioChiusure.setDescrizione(tmpSave.getEntry());
		if(modifica){
			nuovoOrarioChiusure.setIdOrarioChiusure(tmpSave.getIdRecord());
		}

		abiBiblioLogic.addNuovoPeriodoChiusura(id_biblioteca,
				nuovoOrarioChiusure);

	}

	@Override
	public void removePeriodoChiusura(int id_chiusuraToRemove) {
		abiBiblioLogic.removePeriodoChiusura(id_chiusuraToRemove);

	}

	@Override
	public void setInventarioCartaceo(int idBiblio, Boolean b) {
		abiBiblioLogic.setInventarioCartaceo(idBiblio,b);

	}
	
	@Override
	public void setInventarioInformatizzato(int idBiblio, Boolean b) {
		abiBiblioLogic.setInventarioInformatizzato(idBiblio,b);

	}

	@Override
	public void setCatalogoTopograficoCartaceo(int idBiblio, Boolean b) {
		abiBiblioLogic.setCatalogoTopograficoCartaceo(idBiblio, b);

	}
	
	@Override
	public void setCatalogoTopograficoInformatizzato(int idBiblio, Boolean b) {
		abiBiblioLogic.setCatalogoTopograficoInformatizzato(idBiblio, b);

	}


	@Override
	public void setConsistenzaFondiAntichi1830(int idBiblio, int id_consistenza) {
		abiBiblioLogic.setConsistenzaFondiAntichi1830(idBiblio,id_consistenza);

	}

	@Override

	public List<PatrimonioLibrarioModel> getListaPatrimonioSpecializzazione(
			int id_biblioteca) {


		List<Patrimonio> patrimonios = abiBiblioLogic.getListaPatrimonioSpecializzazione(id_biblioteca);
		List<PatrimonioLibrarioModel> patrimonioLibrarioModels = new ArrayList<PatrimonioLibrarioModel>();
		Iterator<Patrimonio> itp = patrimonios.iterator();
		while (itp.hasNext()) {

			Patrimonio patrimonio = (Patrimonio) itp.next();
			PatrimonioLibrarioModel patrimonioModel = new PatrimonioLibrarioModel();
			patrimonioModel.setIdBiblioteca(patrimonio.getBiblioteca().getIdBiblioteca());
			patrimonioModel.setEntry(patrimonio.getPatrimonioSpecializzazione().getDescrizione());
			patrimonioModel.setIdRecord(patrimonio.getPatrimonioSpecializzazione().getIdPatrimonioSpecializzazione());
			patrimonioModel.setQuantita(patrimonio.getQuantita());	
			patrimonioModel.setQuantitaUltimoAnno(patrimonio.getQuantitaUltimoAnno());
			patrimonioLibrarioModels.add(patrimonioModel);
		}
		return patrimonioLibrarioModels;
	}

	@Override
	public void addPatrimonioSpeciale(int id_biblioteca, int id_nuovoPatr,
			int quantita,int quantitaUltimoAnno) {
		abiBiblioLogic.addPatrimonioSpeciale( id_biblioteca, id_nuovoPatr,
				quantita,quantitaUltimoAnno);

	}

	@Override
	public void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr) {

		abiBiblioLogic.removePatrimonioSpeciale(id_biblioteca, id_rimuoviPatr);
	}

	@Override
	public List<SpecializzazioneModel> getSpecializzazioniByIdBiblioteca(
			int id_biblioteca) {

		List<SpecializzazioneModel> specializzazioniModels = new ArrayList<SpecializzazioneModel>();
		List<DeweyLibero> deweyLiberos=	abiBiblioLogic.getSpecializzazioniByIdBiblioteca(id_biblioteca);
		Iterator<DeweyLibero> it=deweyLiberos.iterator();
		while( it.hasNext()){
			DeweyLibero deweyLibero =  (DeweyLibero) it.next();

			SpecializzazioneModel model = new SpecializzazioneModel();
			model.setDewey(TabelleDinamicheServiceImpl.creaCodiceDeweyPuntato(deweyLibero.getDewey().getIdDewey()));
			model.setDecrizione(deweyLibero.getDewey().getDescrizione());
			model.setDecrLibera(deweyLibero.getDescrizione());


			specializzazioniModels.add(model);
		}

		return specializzazioniModels;
	}

	@Override
	public void addSpecializzazionePatrimonio(SpecializzazioneModel modelToSave) {

		String dewey= TabelleDinamicheServiceImpl.rimuoviPuntoDalCodiceDewey(modelToSave.getDewey());

		String descrizioneLibera=null;
		if (modelToSave.getDecrLibera()!=null){
			descrizioneLibera = modelToSave.getDecrLibera(); 
		}

		int id_biblioteca =modelToSave.getIdBiblioteca();

		abiBiblioLogic.addSpecializzazionePatrimonio(id_biblioteca,dewey,descrizioneLibera);
	}

	@Override
	public void removeSpecializzazionePatrimonio(int id_biblioteca,	String idr_removeRecord) {
		abiBiblioLogic.removeSpecializzazionePatrimonio(id_biblioteca,TabelleDinamicheServiceImpl.rimuoviPuntoDalCodiceDewey(idr_removeRecord));
	}

	@Override
	public List<FondiSpecialiModel> getFondiSpecialiByIdBiblioteca(
			int id_biblioteca) {

		List<FondiSpecialiModel> fondiSpecialiModels = new ArrayList<FondiSpecialiModel>();
		List<FondiSpeciali>  fondiSpecialis = abiBiblioLogic. getFondiSpecialiByIdBiblioteca(id_biblioteca);
		Iterator<FondiSpeciali> it =fondiSpecialis.iterator();
		while (it.hasNext()) {
			FondiSpeciali tmpFondo=it.next();
			FondiSpecialiModel fondiSpecialiModel = mappinfFondiSpeciali(tmpFondo);

			fondiSpecialiModels.add(fondiSpecialiModel);
		}
		return fondiSpecialiModels;
	}


	/**
	 * @param it
	 * @return
	 */
	private FondiSpecialiModel mappinfFondiSpeciali(FondiSpeciali fondiSpeciali) {

		FondiSpecialiModel fondiSpecialiModel = new FondiSpecialiModel();

		fondiSpecialiModel.setIdFondiSpeciali(fondiSpeciali.getIdFondiSpeciali());
		fondiSpecialiModel.setDenominazione(fondiSpeciali.getDenominazione());
		if (fondiSpeciali.getCatalogazioneInventarioUrl()!=null){
			fondiSpecialiModel.setUrlOnline(fondiSpeciali.getCatalogazioneInventarioUrl());
		}
		if (fondiSpeciali.getCitazioneBibliografica()!=null){
			fondiSpecialiModel.setCitazioneBibliografica(fondiSpeciali.getCitazioneBibliografica());
		}
		if(fondiSpeciali.getDescrizione()!=null){
			fondiSpecialiModel.setDescrizione(fondiSpeciali.getDescrizione());	
		}
		if (fondiSpeciali.getDewey()!=null){
			fondiSpecialiModel.setDewey(TabelleDinamicheServiceImpl.creaCodiceDeweyPuntato(fondiSpeciali.getDewey().getIdDewey()));
			fondiSpecialiModel.setDeweyDescr(fondiSpeciali.getDewey().getDescrizione());
		}

		fondiSpecialiModel.setCatalogoInventarioDescr(fondiSpeciali.getFondiSpecialiCatalogazioneInventario().getDescrizione());
		fondiSpecialiModel.setIdCatalogoInventario(fondiSpeciali.getFondiSpecialiCatalogazioneInventario().getIdFondiSpecialiCatalogazioneInventario());

		if(fondiSpeciali.getFondoDepositato()!=null){
			fondiSpecialiModel.setFondoDepositato(fondiSpeciali.getFondoDepositato()?"Si":"No");
		}
		else fondiSpecialiModel.setFondoDepositato("Non specificato");
		return fondiSpecialiModel;
	}

	@Override

	public FondiSpecialiModel addFondoSpeciale(FondiSpecialiModel modelToSave,int idBiblioteca,boolean modifica) {
		FondiSpeciali fondoSpeciale = new FondiSpeciali();


		fondoSpeciale.setDenominazione(	modelToSave.getDenominazione());
		if(modelToSave.getDescrizione()!=null)
			fondoSpeciale.setDescrizione(	modelToSave.getDescrizione());
		if(modelToSave.getFondoDepositato()!=null){
			if(modelToSave.getFondoDepositato().equalsIgnoreCase("Si")){
				fondoSpeciale.setFondoDepositato(true);
			}
		}else{
			fondoSpeciale.setFondoDepositato(false);
		}
		if(modelToSave.getIdCatalogoInventario()!=null) {
			FondiSpecialiCatalogazioneInventario fondiSpecialiCatalogazioneInventario = new FondiSpecialiCatalogazioneInventario();
			fondiSpecialiCatalogazioneInventario.setIdFondiSpecialiCatalogazioneInventario(modelToSave.getIdCatalogoInventario().intValue());
			fondoSpeciale.setFondiSpecialiCatalogazioneInventario(fondiSpecialiCatalogazioneInventario);
		}
		if (modelToSave.getUrlOnline()!=null){
			fondoSpeciale.setCatalogazioneInventarioUrl(modelToSave.getUrlOnline())	;
		}
		if(modelToSave.getCitazioneBibliografica()!=null){
			fondoSpeciale.setCitazioneBibliografica(modelToSave.getCitazioneBibliografica());
		}
		if(modelToSave.getDewey()!=null){
			Dewey dewey = new Dewey();
			dewey.setIdDewey(TabelleDinamicheServiceImpl.rimuoviPuntoDalCodiceDewey(modelToSave.getDewey()));
			fondoSpeciale.setDewey(dewey);
		}

		if (modifica ==false){

			int trovatoFondiSpeciali =abiBiblioLogic.searchFondoSpeciale(fondoSpeciale);


			if (trovatoFondiSpeciali>0){
			return	 mappinfFondiSpeciali(abiBiblioLogic.addFondoSpeciale(trovatoFondiSpeciali, idBiblioteca,modifica));
			}
			else{
				fondoSpeciale.setIdFondiSpeciali(null);
			return	mappinfFondiSpeciali(abiBiblioLogic.addFondoSpeciale(abiBiblioLogic.createFondoSpeciale(fondoSpeciale), idBiblioteca,modifica));
			}
		}else {
			fondoSpeciale.setIdFondiSpeciali(modelToSave.getIdFondiSpeciali());
		return	mappinfFondiSpeciali(abiBiblioLogic.updateFondoSpeciale(fondoSpeciale));
		}


	}

	@Override
	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord) {
		abiBiblioLogic.removeFondiSpeciali(id_biblioteca,id_removeRecord);

	}

	@Override
	public List<VoceUnicaModel> getDigitalizzazioneFondiByIdBiblio(
			int id_biblioteca) {
		List<VoceUnicaModel> fondiDigitaliModel = new ArrayList<VoceUnicaModel>();

		List<FondiDigitali> fondiDigitalis= abiBiblioLogic.getDigitalizzazioneFondiByIdBiblio(id_biblioteca);

		Iterator<FondiDigitali> it = fondiDigitalis.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			FondiDigitali fondiDigitali = (FondiDigitali) it.next();	

			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(fondiDigitali.getIdFondiDigitali());
			model.setEntry(fondiDigitali.getDescrizione());

			fondiDigitaliModel.add(model);
		}
		return fondiDigitaliModel;
	}

	@Override
	public void addDigitalizzazioneFondo(int id_biblioteca, int id_newRecord,
			String derscrizione, boolean modifica) {

		abiBiblioLogic.addDigitalizzazioneFondo( id_biblioteca,  id_newRecord, derscrizione,  modifica);
	}

	@Override
	public void removeFondiDigitali(int id_rimuoviFondo) {
		abiBiblioLogic.removeFondiDigitali(id_rimuoviFondo);


	}

	@Override
	public List<VoceUnicaModel> getEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int idTabellaDinamica) {

		List<VoceUnicaModel> listaVoceUnicaModel = new ArrayList<VoceUnicaModel>();
		/*Carico la lisa deglio oggetti in base all'id tabella e all'id biblioteca*/
		List<Object> listDB	=(List<Object>) abiBiblioLogic.getListaVoci(id_biblioteca,idTabellaDinamica);

		Iterator<Object> it = listDB.iterator();
		DynaTabDTO dynaTabDTODB = null;
		VoceUnicaModel voceUnicaTemp = null;
		while (it.hasNext()) {
			// CONVERTO L'OGGETTO IN DTO
			dynaTabDTODB = (DynaTabDTO) DtoJpaMapping.dynaRecord2DTO(it.next());

			voceUnicaTemp = new VoceUnicaModel();

			voceUnicaTemp.setIdRecord(dynaTabDTODB.getId());
			voceUnicaTemp.setEntry(dynaTabDTODB.getValue());
			listaVoceUnicaModel.add(voceUnicaTemp);

		}
		return listaVoceUnicaModel;


	}

	@Override
	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int idToSave, int id_biblioteca, int idTabellaDinamica) throws DuplicatedEntryClientSideException {

		DynaTabDTO dynaTabDTODB = new DynaTabDTO();
		dynaTabDTODB.setId(idToSave);
		dynaTabDTODB.setIdTabella(idTabellaDinamica);
		try {
			abiBiblioLogic.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(dynaTabDTODB, id_biblioteca,idTabellaDinamica) ;
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage()); 
		}
	}

	@Override
	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica) {
		abiBiblioLogic.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
				id_biblioteca,id_rimuoviModalita,idTabellaDinamica);

	}

	@Override
	public PagingLoadResult<VoceUnicaModel> getSpogliMaterialBibliograficoPerPaginazioneCombobox(int id_biblioteca, ModelData loadConfig) {

		ModelData m = (ModelData) loadConfig;
		String query = (String) m.get("query");
		int limit = (Integer) m.get("limit");
		int start = (Integer) m.get("offset");

		List<VoceUnicaModel> sublist = new ArrayList<VoceUnicaModel>();
		int countAll=abiBiblioLogic.countAllSpogliMaterialBibliograficoPossibili(query);

		List<SpogliBibliografici> spogliBibliograficis =abiBiblioLogic.getSpogliMaterialBibliograficoPerPaginazioneCombobox(start,limit,query);
		Iterator<SpogliBibliografici> iterator = spogliBibliograficis.iterator();
		while (iterator.hasNext()) {
			SpogliBibliografici spogliBibliografici = (SpogliBibliografici) iterator
			.next();
			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(spogliBibliografici.getIdSpogliBibliografici());
			model.setEntry(spogliBibliografici.getDescrizioneBibliografica());

			sublist.add(model);
		}

		return new BasePagingLoadResult<VoceUnicaModel>(sublist, start, countAll);
	}

	@Override
	public List<VoceUnicaModel> getListaSpogliMarerialeBibliograficoByIdBiblio(int id_biblioteca) {
		List<SpogliBibliografici> spogliBibliograficis =abiBiblioLogic.getListaSpogliMarerialeBibliograficoByIdBiblio(
				id_biblioteca);

		List<VoceUnicaModel> spogliBibliograficiModel= new ArrayList<VoceUnicaModel>();
		Iterator<SpogliBibliografici> it = spogliBibliograficis.iterator();
		while (it.hasNext()) {

			SpogliBibliografici spogliBibliografici = (SpogliBibliografici) it
			.next();
			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(spogliBibliografici.getIdSpogliBibliografici());
			model.setEntry(spogliBibliografici.getDescrizioneBibliografica());

			spogliBibliograficiModel.add(model);
		}
		return spogliBibliograficiModel;
	}

	@Override
	public void addSpoglioMaterialeBibliografico(String descrSpoglio,int id_biblioteca) throws DuplicatedEntryClientSideException {

		try{
			abiBiblioLogic.addSpoglioMaterialeBibliografico(descrSpoglio, id_biblioteca);
		}catch(DuplicateEntryException e){
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	} 

	@Override
	public void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio) {
		abiBiblioLogic. removeSpogliMaterialeBibliografico(
				id_rimuoviSpoglio);

	}

	@Override
	public List<VoceUnicaModel> getlistaPubblicazioniByIdBiblio(int id_biblioteca) {
		List<VoceUnicaModel> pubblicazioniModels = new ArrayList<VoceUnicaModel>();

		List<Pubblicazioni> pubblicazionis=	abiBiblioLogic.getlistaPubblicazioniByIdBiblio(id_biblioteca);
		Iterator<Pubblicazioni> it = pubblicazionis.iterator();
		while (it.hasNext()) {
			Pubblicazioni pubblicazioni = (Pubblicazioni) it.next();

			VoceUnicaModel pubblicazioniModel = new VoceUnicaModel();

			pubblicazioniModel.setIdRecord(pubblicazioni.getIdPubblicazioni());
			pubblicazioniModel.setEntry(pubblicazioni.getDescrizione());

			pubblicazioniModels.add(pubblicazioniModel);
		}
		return pubblicazioniModels;
	}

	@Override
	public void addPubblicazioni(VoceUnicaModel modelToSave, int id_biblioteca,boolean modifica) {

		Pubblicazioni nuovaPubblicazione = new Pubblicazioni();
		if (modifica){
			nuovaPubblicazione.setIdPubblicazioni(modelToSave.getIdRecord());
		}
		nuovaPubblicazione.setDescrizione(modelToSave.getEntry());

		abiBiblioLogic.addPubblicazioni(nuovaPubblicazione, id_biblioteca,modifica);

	}

	@Override
	public void removePubblicazioni(int id_rimuoviPubblicazione) {
		abiBiblioLogic.removePubblicazioni(id_rimuoviPubblicazione);

	}

	@Override
	public void inserisciBibliograficaInfoCatalogazione(int id_biblio,
			String value) {
		abiBiblioLogic.inserisciBibliograficaInfoCatalogazione(id_biblio,value);

	}

	@Override
	public List<PartecipaCataloghiCollettiviModel> getPartecipaCataloghiCollettiviByIdBiblio(int id_biblioteca) {

		List<PartecipaCataloghiCollettiviModel> partecipaCataloghiCollettivimodels= new ArrayList<PartecipaCataloghiCollettiviModel>();

		List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettivis =abiBiblioLogic.getPartecipaCataloghiCollettiviByIdBiblio(id_biblioteca);
		Iterator<PartecipaCataloghiCollettiviMateriale> it= partecipaCataloghiCollettivis.iterator();
		while (it.hasNext()) {
			// MAPPING PartecipaCataloghiCollettiviMateriale ---> PartecipaCataloghiCollettiviModel
			PartecipaCataloghiCollettiviMateriale tmpCatalogo = (PartecipaCataloghiCollettiviMateriale) it
			.next();
			PartecipaCataloghiCollettiviModel tmpModel = new PartecipaCataloghiCollettiviModel();

			tmpModel.setIdPartecipaCatalogo(tmpCatalogo.getIdCataloghiCollettiviMateriale());
			//Catalogo Collettivo
			tmpModel.setIdCatalogo(tmpCatalogo.getCataloghiCollettivi().getIdCataloghiCollettivi());
			tmpModel.setDenominazioneCatalogo(tmpCatalogo.getCataloghiCollettivi().getDescrizione());
			//Zona Espansione
			//			VoceUnicaModel zonaEspansioneTipo = new VoceUnicaModel();
			//			zonaEspansioneTipo.setIdRecord(tmp.getCataloghiCollettivi().getCataloghiCollettiviZonaTipo().getIdCataloghiCollettiviZonaTipo());
			//			zonaEspansioneTipo.setEntry(tmp.getCataloghiCollettivi().getCataloghiCollettiviZonaTipo().getDescrizione());
			//			
			tmpModel.setIdZonaEspansione(tmpCatalogo.getCataloghiCollettivi().getCataloghiCollettiviZonaTipo().getIdCataloghiCollettiviZonaTipo());
			tmpModel.setZonaEspansioneDescr(tmpCatalogo.getCataloghiCollettivi().getCataloghiCollettiviZonaTipo().getDescrizione());

			//	model.setZonaEspansione(zonaEspansioneTipo);
			//Dettaglio zona
			tmpModel.setDettaglioZona(tmpCatalogo.getCataloghiCollettivi().getDettaglioZona());
			//Costruisce l'intestazione composta per la visualizzazione del grouping nella griglia
			tmpModel.setIntestazioneComposta();
			//Materiale
			tmpModel.setIdMateriale(tmpCatalogo.getPatrimonioSpecializzazione().getIdPatrimonioSpecializzazione());
			tmpModel.setDenominazioneMateriale(tmpCatalogo.getPatrimonioSpecializzazione().getDescrizione());
			tmpModel.setCondition(3);
			//Schede
			if(tmpCatalogo.getSchede()!=null){
				tmpModel.setSchede(tmpCatalogo.getSchede()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeSchede()!=null){
				tmpModel.setPercentSchede(tmpCatalogo.getPercentualeSchede());	
			}
			//Volume
			if(tmpCatalogo.getVolume()!=null){
				tmpModel.setVolume(tmpCatalogo.getVolume()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeVolume()!=null){
				tmpModel.setPercentVolume(tmpCatalogo.getPercentualeVolume());	
			}
			//Citazione bibliografica
			if(tmpCatalogo.getDescrizioneVolume()!=null){
				tmpModel.setCitazioneBiblio(tmpCatalogo.getDescrizioneVolume());
			}
			//Microforme
			if(tmpCatalogo.getMicroforme()!=null){
				tmpModel.setMicroforme(tmpCatalogo.getMicroforme()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeMicroforme()!=null){
				tmpModel.setPercentMicroforme(tmpCatalogo.getPercentualeMicroforme());	
			}
			//Supporto digitale
			tmpModel.setUrl();
			if(tmpCatalogo.getCataloghiSupportoDigitaleTipo()!=null){
				tmpModel.setIdInformatizzatoTipo(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getIdCataloghiSupportoDigitaleTipo());
				tmpModel.setInformatizzatoDescr(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getDescrizione());
			}
			if(tmpCatalogo.getPercentualeInformatizzata()!=null){
				tmpModel.setPercentInformatizzato(tmpCatalogo.getPercentualeInformatizzata());
			}
			if(tmpCatalogo.getDaAnno()!=null){
				tmpModel.setDaAnno(tmpCatalogo.getDaAnno());
			}
			if(tmpCatalogo.getAAnno()!=null){
				tmpModel.setAAnno(tmpCatalogo.getAAnno());
			}
			//Aggiungo il dto alla lista
			partecipaCataloghiCollettivimodels.add(tmpModel);

		}
		//Ritorno la lista dto
		return partecipaCataloghiCollettivimodels;
	}

	@Override
	public void addPartecipaCatalogoCollettivo(int id_biblioteca,
			PartecipaCataloghiCollettiviModel tmpCatalogo,boolean modifica) throws EntryNotFoundClientSideException {

		PartecipaCataloghiCollettiviMateriale partecipaCataloghoCollettivoMateriale = new PartecipaCataloghiCollettiviMateriale();
		//Setto l'id del record da modificare
		if(modifica)
			partecipaCataloghoCollettivoMateriale.setIdCataloghiCollettiviMateriale(tmpCatalogo.getIdPartecipaCatalogo());

		//Setto il catalogo collettivo,idzona e dettaglio zona
		CataloghiCollettivi cataloghiCollettivi = new CataloghiCollettivi();
		cataloghiCollettivi.setIdCataloghiCollettivi(tmpCatalogo.getIdCatalogo());
		cataloghiCollettivi.setDescrizione(tmpCatalogo.getDenominazioneCatalogo());
		cataloghiCollettivi.setDettaglioZona(tmpCatalogo.getDettaglioZona());
		CataloghiCollettiviZonaTipo zonaTipo = new CataloghiCollettiviZonaTipo();
		zonaTipo.setIdCataloghiCollettiviZonaTipo(tmpCatalogo.getIdZonaEspansione());
		zonaTipo.setDescrizione(tmpCatalogo.getZonaEspansioneDescr());
		cataloghiCollettivi.setCataloghiCollettiviZonaTipo(zonaTipo);

		partecipaCataloghoCollettivoMateriale.setCataloghiCollettivi(cataloghiCollettivi);
		//Setto il materiale di PatrimonioSpecializzazione
		PatrimonioSpecializzazione materiale= new PatrimonioSpecializzazione();
		materiale.setIdPatrimonioSpecializzazione(tmpCatalogo.getIdMateriale());
		materiale.setDescrizione(tmpCatalogo.getDenominazioneMateriale());
		partecipaCataloghoCollettivoMateriale.setPatrimonioSpecializzazione(materiale);

		//Setto le schede
		if(tmpCatalogo.getSchede()!=null){
			partecipaCataloghoCollettivoMateriale.setSchede(tmpCatalogo.getSchede().equalsIgnoreCase("Si")==true?true:false);
		}

		if(tmpCatalogo.getPercentSchede()!=null){
			partecipaCataloghoCollettivoMateriale.setPercentualeSchede(tmpCatalogo.getPercentSchede());
		}
		//setto il volume
		if(tmpCatalogo.getVolume()!=null){
			partecipaCataloghoCollettivoMateriale.setVolume(tmpCatalogo.getVolume().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentVolume()!=null){
			partecipaCataloghoCollettivoMateriale.setPercentualeVolume(tmpCatalogo.getPercentVolume());
		}
		//setto Citazione bibliografica
		if(tmpCatalogo.getCitazioneBiblio()!=null)
		{
			partecipaCataloghoCollettivoMateriale.setDescrizioneVolume(tmpCatalogo.getCitazioneBiblio());
		}

		if(tmpCatalogo.getMicroforme()!=null){
			partecipaCataloghoCollettivoMateriale.setMicroforme(tmpCatalogo.getMicroforme().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentMicroforme()!=null){
			partecipaCataloghoCollettivoMateriale.setPercentualeMicroforme(tmpCatalogo.getPercentMicroforme());
		}
		//supporto digitale tipo
		if(tmpCatalogo.getIdInformatizzatoTipo()!=null){
			CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = new CataloghiSupportoDigitaleTipo();
			cataloghiSupportoDigitaleTipo.setIdCataloghiSupportoDigitaleTipo(tmpCatalogo.getIdInformatizzatoTipo());
			cataloghiSupportoDigitaleTipo.setDescrizione(tmpCatalogo.getInformatizzato());
			partecipaCataloghoCollettivoMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
		}

		if((Integer)tmpCatalogo.getPercentInformatizzato()!=null){
			partecipaCataloghoCollettivoMateriale.setPercentualeInformatizzata(tmpCatalogo.getPercentInformatizzato());
		}

		if((Integer)tmpCatalogo.getAnnoDa()!=null){
			partecipaCataloghoCollettivoMateriale.setDaAnno(tmpCatalogo.getAnnoDa());
		}
		if((Integer)tmpCatalogo.getAnnoA()!=null){
			partecipaCataloghoCollettivoMateriale.setAAnno(tmpCatalogo.getAnnoA());
		}

		try {
			abiBiblioLogic.addPartecipaCatalogoCollettivo(id_biblioteca, partecipaCataloghoCollettivoMateriale,modifica);
		} catch (EntryNotFoundException e) {
		throw new EntryNotFoundClientSideException(e.getMessage());
		}
	}

	@Override
	public List<PartecipaCataloghiSpecialiModel> getPartecipaCataloghiSpecialiByIdBiblio(
			int id_biblioteca) {

		List<PartecipaCataloghiSpecialiModel> specialiModels = new ArrayList<PartecipaCataloghiSpecialiModel>();

		List<PartecipaCataloghiSpecialiMateriale> partecipaCataloghiSpecialis =abiBiblioLogic.getPartecipaCataloghiSpecialiByIdBiblio(id_biblioteca);
		Iterator<PartecipaCataloghiSpecialiMateriale> it= partecipaCataloghiSpecialis.iterator();
		while (it.hasNext()) {
			// MAPPING PartecipaCataloghiSpecialiMateriale ---> PartecipaCataloghiSpecialiModel
			PartecipaCataloghiSpecialiMateriale tmpCatalogo = (PartecipaCataloghiSpecialiMateriale) it.next();

			PartecipaCataloghiSpecialiModel tmpModel = new PartecipaCataloghiSpecialiModel();
			//Setto l'id dell'PartecipaCataloghiSpecialiMateriale
			tmpModel.setIdPartecipaCatalogo(tmpCatalogo.getIdCataloghiSpecialiMateriale());

			//Setto la descrizione
			tmpModel.setDenominazione(tmpCatalogo.getDenominazione());
			//setto il tipo di materiale
			tmpModel.setIdMateriale(tmpCatalogo.getPatrimonioSpecializzazione().getIdPatrimonioSpecializzazione());
			tmpModel.setDenominazioneMateriale(tmpCatalogo.getPatrimonioSpecializzazione().getDescrizione());
			tmpModel.setCondition(3);
			//Schede
			if(tmpCatalogo.getSchede()!=null){
				tmpModel.setSchede(tmpCatalogo.getSchede()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeSchede()!=null){
				tmpModel.setPercentSchede(tmpCatalogo.getPercentualeSchede());	
			}
			//Volume
			if(tmpCatalogo.getVolume()!=null){
				tmpModel.setVolume(tmpCatalogo.getVolume()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeVolume()!=null){
				tmpModel.setPercentVolume(tmpCatalogo.getPercentualeVolume());	
			}
			//Citazione bibliografica
			if(tmpCatalogo.getDescrizioneVolume()!=null){
				tmpModel.setCitazioneBiblio(tmpCatalogo.getDescrizioneVolume());
			}
			//Microforme
			if(tmpCatalogo.getMicroforme()!=null){
				tmpModel.setMicroforme(tmpCatalogo.getMicroforme()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeMicroforme()!=null){
				tmpModel.setPercentMicroforme(tmpCatalogo.getPercentualeMicroforme());	
			}
			//Supporto digitale
			tmpModel.setUrl();
			if(tmpCatalogo.getCataloghiSupportoDigitaleTipo()!=null){
				tmpModel.setIdInformatizzatoTipo(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getIdCataloghiSupportoDigitaleTipo());
				tmpModel.setInformatizzatoDescr(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getDescrizione());
			}
			if(tmpCatalogo.getPercentualeInformatizzata()!=null){
				tmpModel.setPercentInformatizzato(tmpCatalogo.getPercentualeInformatizzata());
			}
			if(tmpCatalogo.getDaAnno()!=null){
				tmpModel.setDaAnno(tmpCatalogo.getDaAnno());
			}
			if(tmpCatalogo.getAAnno()!=null){
				tmpModel.setAAnno(tmpCatalogo.getAAnno());
			}

			specialiModels.add(tmpModel);

		}
		return specialiModels;

	}

	@Override
	public void addPartecipaCatalogoSpeciale(int id_biblioteca,
			PartecipaCataloghiSpecialiModel tmpCatalogo, boolean modifica) {

		PartecipaCataloghiSpecialiMateriale  partecipaCataloghoSpecialeMateriale= new PartecipaCataloghiSpecialiMateriale();
		//Setto l'id del record da modificare
		if(modifica)
			partecipaCataloghoSpecialeMateriale.setIdCataloghiSpecialiMateriale(tmpCatalogo.getIdPartecipaCatalogo());
		//Setto la denominazione
		partecipaCataloghoSpecialeMateriale.setDenominazione(tmpCatalogo.getDenominazione());

		//Setto il materiale di PatrimonioSpecializzazione
		PatrimonioSpecializzazione materiale= new PatrimonioSpecializzazione();
		materiale.setIdPatrimonioSpecializzazione(tmpCatalogo.getIdMateriale());
		materiale.setDescrizione(tmpCatalogo.getDenominazioneMateriale());
		partecipaCataloghoSpecialeMateriale.setPatrimonioSpecializzazione(materiale);

		//Setto le schede
		if(tmpCatalogo.getSchede()!=null){
			partecipaCataloghoSpecialeMateriale.setSchede(tmpCatalogo.getSchede().equalsIgnoreCase("Si")==true?true:false);
		}

		if(tmpCatalogo.getPercentSchede()!=null){
			partecipaCataloghoSpecialeMateriale.setPercentualeSchede(tmpCatalogo.getPercentSchede());
		}
		//setto il volume
		if(tmpCatalogo.getVolume()!=null){
			partecipaCataloghoSpecialeMateriale.setVolume(tmpCatalogo.getVolume().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentVolume()!=null){
			partecipaCataloghoSpecialeMateriale.setPercentualeVolume(tmpCatalogo.getPercentVolume());
		}
		//setto Citazione bibliografica
		if(tmpCatalogo.getCitazioneBiblio()!=null)
		{
			partecipaCataloghoSpecialeMateriale.setDescrizioneVolume(tmpCatalogo.getCitazioneBiblio());
		}

		if(tmpCatalogo.getMicroforme()!=null){
			partecipaCataloghoSpecialeMateriale.setMicroforme(tmpCatalogo.getMicroforme().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentMicroforme()!=null){
			partecipaCataloghoSpecialeMateriale.setPercentualeMicroforme(tmpCatalogo.getPercentMicroforme());
		}
		//supporto digitale tipo
		if(tmpCatalogo.getIdInformatizzatoTipo()!=null){
			CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = new CataloghiSupportoDigitaleTipo();
			cataloghiSupportoDigitaleTipo.setIdCataloghiSupportoDigitaleTipo(tmpCatalogo.getIdInformatizzatoTipo());
			cataloghiSupportoDigitaleTipo.setDescrizione(tmpCatalogo.getInformatizzato());
			partecipaCataloghoSpecialeMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
		}

		if((Integer)tmpCatalogo.getPercentInformatizzato()!=null){
			partecipaCataloghoSpecialeMateriale.setPercentualeInformatizzata(tmpCatalogo.getPercentInformatizzato());
		}

		if((Integer)tmpCatalogo.getAnnoDa()!=null){
			partecipaCataloghoSpecialeMateriale.setDaAnno(tmpCatalogo.getAnnoDa());
		}
		if((Integer)tmpCatalogo.getAnnoA()!=null){
			partecipaCataloghoSpecialeMateriale.setAAnno(tmpCatalogo.getAnnoA());
		}

		abiBiblioLogic.addPartecipaCatalogoSpeciale(id_biblioteca, partecipaCataloghoSpecialeMateriale,modifica);
	}

	@Override
	public void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca) {
		abiBiblioLogic.removePartecipaCatalogoSpeciale(idRemove,id_biblioteca);

	}

	@Override
	public void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca) {
		abiBiblioLogic.removePartecipaCatalogoCollettivo(idRemove,id_biblioteca);

	}

	@Override
	public void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca) {
		abiBiblioLogic.removePartecipaCatalogoGenerale(idRemove,id_biblioteca);

	}

	@Override
	public List<PartecipaCataloghiGeneraliModel> getPartecipaCataloghiGeneraliByIdBiblio(
			int id_biblioteca) {
		List<PartecipaCataloghiGeneraliModel> cataloghiGeneraliModels = new ArrayList<PartecipaCataloghiGeneraliModel>();

		List<PartecipaCataloghiGenerali>partecipaCataloghiGeneralis= abiBiblioLogic.getPartecipaCataloghiGeneraliByIdBiblio(id_biblioteca);

		Iterator<PartecipaCataloghiGenerali> it= partecipaCataloghiGeneralis.iterator();
		while (it.hasNext()) {
			// MAPPING PartecipaCataloghiGenerali ---> PartecipaCataloghiGeneraliModel
			PartecipaCataloghiGenerali tmpCatalogo = (PartecipaCataloghiGenerali) it.next();
			PartecipaCataloghiGeneraliModel tmpModel = new PartecipaCataloghiGeneraliModel();
			//Setto l'id della partecipazione al catalogo
			tmpModel.setIdPartecipaCatalogo(tmpCatalogo.getIdCataloghiGenerali());
			//Setto l'id tipologia e la descrizione tipologia
			tmpModel.setIdTipoCatalogo((tmpCatalogo.getCatalogoGeneraleTipo().getIdCatalogoGeneraleTipo()));
			tmpModel.setTipoCatalogoDescr(tmpCatalogo.getCatalogoGeneraleTipo().getDescrizione());

			//Schede
			if(tmpCatalogo.getSchede()!=null){
				tmpModel.setSchede(tmpCatalogo.getSchede()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeSchede()!=null){
				tmpModel.setPercentSchede(tmpCatalogo.getPercentualeSchede());	
			}
			//Volume
			if(tmpCatalogo.getVolume()!=null){
				tmpModel.setVolume(tmpCatalogo.getVolume()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeVolume()!=null){
				tmpModel.setPercentVolume(tmpCatalogo.getPercentualeVolume());	
			}
			//Citazione bibliografica
			if(tmpCatalogo.getDescrizioneVolume()!=null){
				tmpModel.setCitazioneBiblio(tmpCatalogo.getDescrizioneVolume());
			}
			//Microforme
			if(tmpCatalogo.getMicroforme()!=null){
				tmpModel.setMicroforme(tmpCatalogo.getMicroforme()==true?"Si":"No");
			}
			if(tmpCatalogo.getPercentualeMicroforme()!=null){
				tmpModel.setPercentMicroforme(tmpCatalogo.getPercentualeMicroforme());	
			}
			//Supporto digitale
			tmpModel.setUrl();
			if(tmpCatalogo.getCataloghiSupportoDigitaleTipo()!=null){
				tmpModel.setIdInformatizzatoTipo(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getIdCataloghiSupportoDigitaleTipo());
				tmpModel.setInformatizzatoDescr(tmpCatalogo.getCataloghiSupportoDigitaleTipo().getDescrizione());
			}
			if(tmpCatalogo.getPercentualeInformatizzata()!=null){
				tmpModel.setPercentInformatizzato(tmpCatalogo.getPercentualeInformatizzata());
			}
			if(tmpCatalogo.getDaAnno()!=null){
				tmpModel.setDaAnno(tmpCatalogo.getDaAnno());
			}
			if(tmpCatalogo.getAAnno()!=null){
				tmpModel.setAAnno(tmpCatalogo.getAAnno());
			}

			cataloghiGeneraliModels.add(tmpModel);
		}
		return cataloghiGeneraliModels;

	}

	@Override
	public void addPartecipaCatalogoGenerale(int id_biblioteca,
			PartecipaCataloghiGeneraliModel tmpCatalogo, boolean modifica) {
		PartecipaCataloghiGenerali partecipaCataloghiGenerali = new PartecipaCataloghiGenerali();

		if(modifica){
			//Setto l'id del record da modificare
			partecipaCataloghiGenerali.setIdCataloghiGenerali(tmpCatalogo.getIdPartecipaCatalogo());
		}

		//Setto la tipologia di catalogo
		CatalogoGeneraleTipo catalogoGeneraleTipo = new CatalogoGeneraleTipo();
		catalogoGeneraleTipo.setIdCatalogoGeneraleTipo(tmpCatalogo.getIdTipoCatalogo());
		catalogoGeneraleTipo.setDescrizione(tmpCatalogo.getTipoCatalogoDescr());
		partecipaCataloghiGenerali.setCatalogoGeneraleTipo(catalogoGeneraleTipo);

		//Setto le schede
		if(tmpCatalogo.getSchede()!=null){
			partecipaCataloghiGenerali.setSchede(tmpCatalogo.getSchede().equalsIgnoreCase("Si")==true?true:false);
		}

		if(tmpCatalogo.getPercentSchede()!=null){
			partecipaCataloghiGenerali.setPercentualeSchede(tmpCatalogo.getPercentSchede());
		}
		//setto il volume
		if(tmpCatalogo.getVolume()!=null){
			partecipaCataloghiGenerali.setVolume(tmpCatalogo.getVolume().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentVolume()!=null){
			partecipaCataloghiGenerali.setPercentualeVolume(tmpCatalogo.getPercentVolume());
		}
		//setto Citazione bibliografica
		if(tmpCatalogo.getCitazioneBiblio()!=null)
		{
			partecipaCataloghiGenerali.setDescrizioneVolume(tmpCatalogo.getCitazioneBiblio());
		}

		if(tmpCatalogo.getMicroforme()!=null){
			partecipaCataloghiGenerali.setMicroforme(tmpCatalogo.getMicroforme().equalsIgnoreCase("Si")==true?true:false);
		}

		if((Integer)tmpCatalogo.getPercentMicroforme()!=null){
			partecipaCataloghiGenerali.setPercentualeMicroforme(tmpCatalogo.getPercentMicroforme());
		}
		//supporto digitale tipo
		if(tmpCatalogo.getIdInformatizzatoTipo()!=null){
			CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = new CataloghiSupportoDigitaleTipo();
			cataloghiSupportoDigitaleTipo.setIdCataloghiSupportoDigitaleTipo(tmpCatalogo.getIdInformatizzatoTipo());
			cataloghiSupportoDigitaleTipo.setDescrizione(tmpCatalogo.getInformatizzato());
			partecipaCataloghiGenerali.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
		}

		if((Integer)tmpCatalogo.getPercentInformatizzato()!=null){
			partecipaCataloghiGenerali.setPercentualeInformatizzata(tmpCatalogo.getPercentInformatizzato());
		}

		if((Integer)tmpCatalogo.getAnnoDa()!=null){
			partecipaCataloghiGenerali.setDaAnno(tmpCatalogo.getAnnoDa());
		}
		if((Integer)tmpCatalogo.getAnnoA()!=null){
			partecipaCataloghiGenerali.setAAnno(tmpCatalogo.getAnnoA());
		}

		abiBiblioLogic.addPartecipaCatalogoGenerale(id_biblioteca,partecipaCataloghiGenerali, modifica);
	}

	@Override
	public List<ServiziRiproduzioniModel> getServiziRiproduzioniFinitureByIdBiblio(
			int id_biblioteca) {

		List<ServiziRiproduzioniModel> riproduzioniModels= new ArrayList<ServiziRiproduzioniModel>();
		List<Riproduzioni> riproduzionis = abiBiblioLogic.getServiziRiproduzioniFornitureByIdBiblio(id_biblioteca);
		Iterator<Riproduzioni> it =riproduzionis.iterator();
		while (it.hasNext()) {
			Riproduzioni riproduzione = (Riproduzioni) it.next();

			ServiziRiproduzioniModel model = new ServiziRiproduzioniModel();
			model.setIdTipo(riproduzione.getId().getIdRiproduzioniTipo());
			model.setTipoDescr(riproduzione.getRiproduzioniTipo().getDescrizione());

			if(riproduzione.getLocale()!=null){
				model.setLocale(riproduzione.getLocale()==true?"Si":"No");
			}
			if(riproduzione.getNazionale()!=null){
				model.setNazionale(riproduzione.getNazionale()==true?"Si":"No");
			}
			if(riproduzione.getInternazionale()!=null){
				model.setInternazionale(riproduzione.getInternazionale()==true?"Si":"No");
			}
			riproduzioniModels.add(model);

		}
		return riproduzioniModels;
	}

	@Override
	public void addServiziRiproduzioniForniture(int id_biblioteca,
			ServiziRiproduzioniModel tmpServizio, boolean modifica) {

		Integer idTipo=tmpServizio.getIdTipo();

		Boolean hasLocale=null;
		Boolean hasNazionale=null;
		Boolean hasInternazionale=null;
		if(tmpServizio.getLocale()!=null){
			hasLocale=(tmpServizio.getLocale().equalsIgnoreCase("Si")?true:false);
		}

		if(tmpServizio.getNazionale()!=null){
			hasNazionale=(tmpServizio.getNazionale().equalsIgnoreCase("Si")?true:false);
		}

		if(tmpServizio.getInternazionale()!=null){
			hasInternazionale=(tmpServizio.getInternazionale().equalsIgnoreCase("Si")?true:false);
		}

		abiBiblioLogic.addServiziRiproduzioniForniture(id_biblioteca,idTipo,hasLocale,hasNazionale,hasInternazionale);
	}

	@Override
	public void removeServiziRiproduzioniForniture(int idRemove,int id_biblioteca) {
		abiBiblioLogic.removeServiziRiproduzioniForniture(idRemove,id_biblioteca);

	}


	/* Metodo per la restituzione delle biblioteche per il report */
	public PagingLoadResult<BiblioModel> getBibliotecheReport(final PagingLoadConfig config) {

		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir = config.getSortInfo().getSortDir().toString();
		String sortField = config.getSortInfo().getSortField();

		HashMap<String, Object> keys = config.get("keys");
		/*
		 * LOAD DATI
		 */
		int countAll = abiBiblioLogic.countAllBibliotechePerReport(keys);
		if (countAll <= 0)
			return new BasePagingLoadResult<BiblioModel>(new ArrayList<BiblioModel>(), config.getOffset(), countAll);
		List<Biblioteca> biblioteche = abiBiblioLogic.ricercaBiblioReport(keys, start, limit, sortField, sortDir);

		ArrayList<BiblioModel> sublist = new ArrayList<BiblioModel>();

		Iterator<Biblioteca> itBiblio = biblioteche.iterator();
		while (itBiblio.hasNext()) {

			Biblioteca biblioteca = (Biblioteca) itBiblio.next();

			BiblioModel biblioModel = new BiblioModel();

			// Id biblioteca
			biblioModel.setIdBiblio(biblioteca.getIdBiblioteca());

			// Codice iccu
			biblioModel.setCodice(Utility.createIccuCode(
					biblioteca.getIsilStato(), biblioteca.getIsilProvincia(),
					"" + biblioteca.getIsilNumero()));
			biblioModel
			.setDenominazione(biblioteca.getDenominazioneUfficiale());
			biblioModel.setIndirizzo(biblioteca.getIndirizzo());

			// Comune
			ComuniModel tmpComune = new ComuniModel();
			tmpComune.setDenominazione(biblioteca.getComune()
					.getDenominazione());
			tmpComune.setIdComune(biblioteca.getComune().getIdComune());
			tmpComune.setIdProvincia(biblioteca.getComune().getProvincia()
					.getIdProvincia());
			biblioModel.setComune(tmpComune);
			biblioModel.setComuneDenominazione(tmpComune);

			// Utente ultima modifica
			biblioModel.setUtenteUltimaModifica((biblioteca
					.getUtenteUltimaModifica() == null ? "" : biblioteca
							.getUtenteUltimaModifica().getLogin()));

			// Stato catalogazione
			biblioModel.setStatoCatalogazione(biblioteca
					.getStatoBibliotecaWorkflow().getLabel());

			sublist.add(biblioModel);
		}

		return new BasePagingLoadResult<BiblioModel>(sublist, config.getOffset(), countAll);
	}

	@Override
	public void setServizioBibliogrficoInternoEsterno(int id_biblioteca,Boolean hasServizioBibliograficoInterno,Boolean hasServizioBibliograficoEsterno) {
		abiBiblioLogic.setServizioBibliogrficoInternoEsterno(id_biblioteca,hasServizioBibliograficoInterno,hasServizioBibliograficoEsterno);

	}

	@Override
	public List<VoceUnicaModel> getModalitaComunicazioniBibliograficheByIdBiblio(
			int id_biblioteca) {
		List<VoceUnicaModel> modalitaModels = new ArrayList<VoceUnicaModel>();

		List<ServiziInformazioniBibliograficheModalita> bibliograficheModalitas = abiBiblioLogic.getModalitaComunicazioniBibliograficheByIdBiblio(id_biblioteca);
		Iterator<ServiziInformazioniBibliograficheModalita> it=bibliograficheModalitas.iterator();
		while (it.hasNext()) {
			//MAPPING ServiziInformazioniBibliograficheModalita --->VoceUnicaModel
			ServiziInformazioniBibliograficheModalita modalita = (ServiziInformazioniBibliograficheModalita) it
			.next();	
			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(modalita.getIdServiziInformazioniBibliograficheModalita());
			model.setEntry(modalita.getDescrizione());

			modalitaModels.add(model);
		}

		return modalitaModels;
	}

	@Override
	public void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca,int idTipoComunicazione) throws DuplicatedEntryClientSideException {

		try {
			abiBiblioLogic.addModalitaComunicazioneInformazioneBibliografica(id_biblioteca,idTipoComunicazione);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}

	@Override
	public void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idRemove) {
		abiBiblioLogic.removeModalitaComunicazioneInformazioneBibliografica(id_biblioteca,idRemove);
	}

	@Override
	public List<VoceUnicaModel> getSezioniSpecialiByIdBiblio(int id_biblioteca) {
		List<VoceUnicaModel> sezioniModels = new ArrayList<VoceUnicaModel>();

		List<SezioniSpeciali> sezioniSpecialis =abiBiblioLogic.getSezioniSpecialiByIdBiblio( id_biblioteca);
		Iterator<SezioniSpeciali> it = sezioniSpecialis.iterator();
		while (it.hasNext()) {

			SezioniSpeciali sezioniSpeciali = (SezioniSpeciali) it.next();

			VoceUnicaModel model = new VoceUnicaModel();
			model.setIdRecord(sezioniSpeciali.getIdSezioniSpeciali());
			model.setEntry(sezioniSpeciali.getDescrizione());
			sezioniModels.add(model);
		}	

		return sezioniModels;
	}

	@Override
	public void removeSezioniSpeciali(int id_biblioteca, int idRemove) {
		abiBiblioLogic.removeSezioniSpeciali( id_biblioteca, idRemove);

	}

	@Override
	public void addSezioniSpeciali(int id_biblioteca, int idSezioneSpeciale) throws DuplicatedEntryClientSideException {
		try {
			abiBiblioLogic.addSezioniSpeciali(id_biblioteca, idSezioneSpeciale);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}
	}

	@Override
	public void updateModalitaAccessoInternet(int id_biblioteca,HashMap<String, String> keys) {
		Boolean hasAccessoPagamento=null;
		Boolean hasAccessoTempo=null;
		Boolean hasAccessoProxy=null;


		if(keys.get("accessoPagamento").equalsIgnoreCase("Non specificato"))
			hasAccessoPagamento=null;
		else hasAccessoPagamento=keys.get("accessoPagamento").equalsIgnoreCase("Si")?true:false;
		if(keys.get("accessoTempo").equalsIgnoreCase("Non specificato"))
			hasAccessoTempo=null;
		else hasAccessoTempo=keys.get("accessoTempo").equalsIgnoreCase("Si")?true:false;
		if(keys.get("accessoProxy").equalsIgnoreCase("Non specificato"))
			hasAccessoProxy=null;
		else hasAccessoProxy=keys.get("accessoProxy").equalsIgnoreCase("Si")?true:false;

		abiBiblioLogic. updateModalitaAccessoInternet(id_biblioteca,hasAccessoPagamento,hasAccessoTempo,hasAccessoProxy);
	}

	@Override
	public List<PrestitoLocaleModel> getPrestitiLocaliByIdBiblio(int id_biblioteca) {
		List<PrestitoLocaleModel> models = new ArrayList<PrestitoLocaleModel>();

		List<PrestitoLocale> prestitoLocales = abiBiblioLogic.getPrestitiLocaliByIdBiblio(id_biblioteca);
		//Mapping PrestitoLocale--->PrestitoLocaleModel
		Iterator<PrestitoLocale> it= prestitoLocales.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy PrestitoLocale
			PrestitoLocale prestitoLocale = (PrestitoLocale) it.next();

			PrestitoLocaleModel model = new PrestitoLocaleModel();
			if(prestitoLocale.getAutomatizzato()!=null){
				model.setProcedureAuto(prestitoLocale.getAutomatizzato()==true?"Si":"No");
			}
			model.setIdPrestitoLocale(prestitoLocale.getIdPrestitoLocale());
			if(prestitoLocale.getDurataGiorni()!=null){
				model.setDurataGiorni(prestitoLocale.getDurataGiorni());
			}
			model.setMaterialeEsclusoButton();

			model.setUtentiAmmessiButton();
			models.add(model);
		}
		return models;
	}

	@Override
	public void addPrestitoLocaleToBiblio(int id_biblioteca,PrestitoLocaleModel tmpPrestito, boolean modifica) {

		Integer idPrestito=null;
		Integer durataGiorni=null;
		Boolean automatizzato=null;
		if(modifica)
			idPrestito=tmpPrestito.getIdPrestitoLocale();

		durataGiorni=tmpPrestito.getDurataGiorni();
		if(tmpPrestito.getProcedureAuto()== null || tmpPrestito.getProcedureAuto().equalsIgnoreCase("Non specificato"))
			automatizzato=null;
		else automatizzato=tmpPrestito.getProcedureAuto().equalsIgnoreCase("Si")?true:false;
		abiBiblioLogic.addPrestitoLocaleToBiblio(id_biblioteca,idPrestito,durataGiorni,automatizzato, modifica);

	}

	@Override
	public void removePrestitoLocaleFromBiblio(int id_biblioteca,int id_removePrestito) {
		abiBiblioLogic.removePrestitoLocaleFromBiblio(id_biblioteca,id_removePrestito);

	}

	@Override
	public List<PrestitoInterbibliotecarioRuoloModel> getPrestitoInterbibliotecarioERuoloByIdBiblio(
			int id_biblioteca) {
		List<PrestitoInterbibliotecarioRuoloModel> interbibliotecarioRuoloModels = new ArrayList<PrestitoInterbibliotecarioRuoloModel>();

		List<PrestitoInterbibliotecario> prestitoInterbibliotecarios = abiBiblioLogic.getPrestitoInterbibliotecarioERuoloByIdBiblio(id_biblioteca);
		Iterator<PrestitoInterbibliotecario> it =prestitoInterbibliotecarios.iterator();
		while (it.hasNext()) {
			//Iterazione anti-lazy
			PrestitoInterbibliotecario prestitoInterbibliotecario = (PrestitoInterbibliotecario) it
			.next();

			PrestitoInterbibliotecarioRuoloModel model = new PrestitoInterbibliotecarioRuoloModel();

			model.setIdPrestitoInterbibliotecario(prestitoInterbibliotecario.getIdPrestitoInterbibliotecario());
			model.setNazionale(prestitoInterbibliotecario.getNazionale()?"Si":"No");
			model.setInternazionale(prestitoInterbibliotecario.getInternazionale()?"Si":"No");

			PrestitoInterbibliotecarioModo tmpRuolo = prestitoInterbibliotecario.getPrestitoInterbibliotecarioModo();

			model.setIdRuolo(tmpRuolo.getIdPrestitoInterbibliotecarioModo());
			model.setRuoloDescr(tmpRuolo.getDescrizione());

			interbibliotecarioRuoloModels.add(model);
		}	
		return interbibliotecarioRuoloModels;
	}

	@Override
	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca,
			PrestitoInterbibliotecarioRuoloModel tmpPrestito, boolean modifica) throws DuplicatedEntryClientSideException {
		Integer idPrestito=null;
		Boolean nazionale=null;
		Boolean internazionale;
		Integer idRuolo=null;

		if(modifica)
			idPrestito=tmpPrestito.getIdPrestitoInterbibliotecario();

		nazionale =tmpPrestito.getNazionale().equalsIgnoreCase("Si")?true:false;
		internazionale =tmpPrestito.getInternazionale().equalsIgnoreCase("Si")?true:false;
		idRuolo=tmpPrestito.getIdRuolo();

		try {
			abiBiblioLogic.addPrestitoInterbibliotecarioToBiblio(id_biblioteca,idPrestito,idRuolo,nazionale,internazionale,modifica);
		} catch (DuplicateEntryException e) {
			throw new DuplicatedEntryClientSideException(e.getMessage());
		}

	}

	@Override
	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca,int id_removePrestito) {
		abiBiblioLogic.removePrestitoInterbibliotecarioFromBiblio(id_biblioteca,id_removePrestito);

	}

	@Override
	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblio, Boolean hasPrestitoNazionale,Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate) {

		abiBiblioLogic.setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
				id_biblio,  hasPrestitoNazionale, hasPrestitoInternazionale,  hasProcedureAutomatizzate);
	}

	@Override
	public void setInfoPersonale(int id_biblio,	HashMap<String, Object> personaleValues) {
		abiBiblioLogic.setInfoPersonale(id_biblio,personaleValues);

	}

	@Override
	public void setInfoUtenti(int id_biblio,HashMap<String, Object> utentiValues) {
		abiBiblioLogic.setInfoUtenti( id_biblio, utentiValues);
	}

	@Override
	public void setInfoBilancio(int id_biblio,	HashMap<String, Object> bilancioValues) {
		abiBiblioLogic.setInfoBilancio(id_biblio,bilancioValues);

	}

	@Override
	public List<DepositiLegaliModel> getDepositiLegaliByIdBiblio(int id_biblioteca) {	

		List<DepositiLegaliModel> depositiLegaliModels = new ArrayList<DepositiLegaliModel>();
		List<DepositiLegali> depositiLegalis =abiBiblioLogic.getDepositiLegaliByIdBiblio(id_biblioteca);

		Iterator<DepositiLegali> it = depositiLegalis.iterator();
		while (it.hasNext()) {

			DepositiLegali depositiLegali = (DepositiLegali) it.next();

			DepositiLegaliModel depositiLegaliModel = new DepositiLegaliModel();
			depositiLegaliModel.setIdDepositoTipo(depositiLegali.getId().getIdDepositiLegaliTipo());
			depositiLegaliModel.setDepositoDescr(depositiLegali.getDepositiLegaliTipo().getDescrizione());
			depositiLegaliModel.setDepositoAnno(depositiLegali.getDaAnno());
			depositiLegaliModels.add(depositiLegaliModel);
		}
		return depositiLegaliModels;
	}

	@Override
	public void addDepositoLegaleToBiblio(int id_biblioteca,int id_nuovoTipoDeposito, String anno,boolean modifica ) {
		abiBiblioLogic.addDepositoLegaleToBiblio( id_biblioteca, id_nuovoTipoDeposito,  anno,modifica);
	}

	@Override
	public void removeDepositoLegaleFromBiblio(int id_biblioteca,	int id_rimuoviDepositoLegale) {
		abiBiblioLogic.removeDepositoLegaleFromBiblio(id_biblioteca,id_rimuoviDepositoLegale);
	}

	@Override
	public void inserisciNoteCatalogazione(int id_biblio, String value) {
		abiBiblioLogic.inserisciNoteCatalogazione( id_biblio,  value);

	}

	@Override
	public void inserisciComunicazioniCatalogazione(int id_biblio, String value) {
		abiBiblioLogic.inserisciComunicazioniCatalogazione( id_biblio,  value);
	}


	/**
	 * Mette in cancellata la biblioteca
	 */
	@Override
	public void setOccupata(int id_biblio) {
		abiBiblioLogic.setOccupata(id_biblio);
	}
	/**
	 * Mette in cancellata la biblioteca
	 */
	@Override
	public void setCancellata(int id_biblio) {
		abiBiblioLogic.setCancellata(id_biblio);
	}
	/**
	 * Mette in revisione la biblioteca
	 */
	@Override
	public void setInRevisione(int id_biblio) {
		abiBiblioLogic.setInRevisione(id_biblio, true);
	}
	/**
	 * Mette in definitiva la biblioteca
	 */
	@Override
	public void setDefinitiva(int id_biblio, String messaggio) {
		abiBiblioLogic.setDefinitiva(id_biblio, messaggio);
	}

	@Override
	public void ripristina(int id_biblio) {
		abiBiblioLogic.ripristina(id_biblio);
		try {
			trasferimentoBiblioteca.backupXml(id_biblio);
		} catch (Exception e) {
			throw new RuntimeException(new Error(e.getMessage()));
		}
	}
	@Override
	public void respingiRevisione(int id_biblio, String messaggio) {
		abiBiblioLogic.respingiRevisione(id_biblio, messaggio);
	}

	@Override
	public String differenze(int id_biblio) {
		String diffs = abiBiblioLogic.differenze(id_biblio);
		return diffs;
	}

	public void setCatInvFondi(int idBib, HashMap<String, Object> params) {
		abiBiblioLogic.setCatInvFondi(idBib, params);
	}
	
	/* Servizio per reperire gli utenti gestori di una biblioteca */
	public PagingLoadResult<UserModel> getUsersByBiblio(PagingLoadConfig config) {
		int start = config.getOffset();
		int limit = config.getLimit();

		String sortDir = config.getSortInfo().getSortDir().toString();
		String sortField = config.getSortInfo().getSortField();

		/**
		 * LOAD DATI
		 */
		int idbiblio = Integer.valueOf(config.get("idBiblio").toString());
		List<Utenti> results = abiBiblioLogic.ricercaUtentiByIdBiblio(idbiblio, start, limit, sortField, sortDir);
		int countAll = abiBiblioLogic.countUtentiByIdBiblio(idbiblio);

		/**
		 * mapping...
		 */
		ArrayList<UserModel> sublist = new ArrayList<UserModel>();
		for (int i = 0; i < results.size(); i++) {

			Utenti utente = results.get(i);
			UserModel temp = new UserModel();

			temp.setCognome(utente.getCognome());
			temp.setIdUser((utente.getIdUtenti()));
			temp.setIncarico(utente.getIncarico());
			temp.setNome(utente.getNome());
			temp.setUserName(utente.getLogin());
			sublist.add(temp);
		}
		return new BasePagingLoadResult<UserModel>(sublist, config.getOffset(),	countAll);
	}

	@Override
	public void setDefinitiva(List<Integer> bibliotecheSelectedIds) {
		abiBiblioLogic.setDefinitiveImportate(bibliotecheSelectedIds);
	}

	@Override
	public void ripristina(List<Integer> bibliotecheSelectedIds) {
		abiBiblioLogic.ripristinaImportate(bibliotecheSelectedIds);
		
		for (Integer entry : bibliotecheSelectedIds) {
			try {
				trasferimentoBiblioteca.backupXml(entry);
			} catch (Exception e) {
				throw new RuntimeException(new Error(e.getMessage()));
			}
		}
	}

	@Override
	public void respingiRevisione(List<Integer> bibliotecheSelectedIds) {
		for(Integer idBiblio:bibliotecheSelectedIds){
			abiBiblioLogic.setDefinitiva(idBiblio);
		}
	}


	@Override
	public void aggiornaCodici(HashMap<String, Object> params, int idBiblio) {
		abiBiblioLogic.aggiornaCodici(params, idBiblio);
	}


	@Override
	public void aggiornaCodiciOthers(HashMap<String, Object> params, int idBiblio) {
		abiBiblioLogic.aggiornaCodiciOthers(params, idBiblio);		
	}


	@Override
	public Boolean setStatoCatalogazione(HashMap<String, Object> params) {
		return abiBiblioLogic.addStatoCatalogazione(params);
	}

}
