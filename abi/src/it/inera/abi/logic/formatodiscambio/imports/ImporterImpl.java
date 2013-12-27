package it.inera.abi.logic.formatodiscambio.imports;

import it.inera.abi.commons.Constants;
import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.ComuneDao;
import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.EnteDao;
import it.inera.abi.dao.StatoDao;
import it.inera.abi.logic.AbiBiblioLogic;
import it.inera.abi.logic.formatodiscambio.castor.Altro;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecForme;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeCopertura;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriale;
import it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali;
import it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale;
import it.inera.abi.logic.formatodiscambio.castor.Chiusura;
import it.inera.abi.logic.formatodiscambio.castor.Copertura;
import it.inera.abi.logic.formatodiscambio.castor.DepositoLegale;
import it.inera.abi.logic.formatodiscambio.castor.Destinazione;
import it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale;
import it.inera.abi.logic.formatodiscambio.castor.Forme;
import it.inera.abi.logic.formatodiscambio.castor.Immagine;
import it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario;
import it.inera.abi.logic.formatodiscambio.castor.Internet;
import it.inera.abi.logic.formatodiscambio.castor.Materiale;
import it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi;
import it.inera.abi.logic.formatodiscambio.castor.Orario;
import it.inera.abi.logic.formatodiscambio.castor.Sistemi;
import it.inera.abi.logic.formatodiscambio.castor.SistemiItem;
import it.inera.abi.logic.formatodiscambio.castor.Specializzazione;
import it.inera.abi.logic.formatodiscambio.castor.Specializzazioni;
import it.inera.abi.logic.formatodiscambio.castor.Telefonico;
import it.inera.abi.logic.formatodiscambio.castor.TipoPrestito;
import it.inera.abi.logic.formatodiscambio.castor.Variazione;
import it.inera.abi.logic.formatodiscambio.castor.types.AltroTipoType;
import it.inera.abi.logic.formatodiscambio.castor.types.CatalogoInventarioType;
import it.inera.abi.logic.formatodiscambio.castor.types.FondoAnticoTypeVolumiType;
import it.inera.abi.logic.formatodiscambio.castor.types.OrarioGiornoType;
import it.inera.abi.logic.formatodiscambio.castor.types.RuoloType;
import it.inera.abi.logic.formatodiscambio.castor.types.SiNoType;
import it.inera.abi.logic.formatodiscambio.castor.types.TelefonicoTipoType;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.CataloghiGeneraliUrl;
import it.inera.abi.persistence.CataloghiSupportoDigitaleTipo;
import it.inera.abi.persistence.CatalogoGeneraleTipo;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.CodiciPK;
import it.inera.abi.persistence.CodiciTipo;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DepositiLegaliPK;
import it.inera.abi.persistence.DepositiLegaliTipo;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DestinazioniSocialiPK;
import it.inera.abi.persistence.DestinazioniSocialiTipo;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.DeweyLiberoPK;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiAntichiConsistenza;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario;
import it.inera.abi.persistence.Geolocalizzazione;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.PatrimonioPK;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.Photo;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoInterbibliotecarioModo;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.RiproduzioniPK;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.Stato;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.TipologiaFunzionale;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe che implementa l'import online e differito 
 *
 */
@Service
public class ImporterImpl implements Importer {

	private Log log = LogFactory.getLog(ImporterImpl.class);

	@Autowired private BiblioDao biblioDao;
	@Autowired private ComuneDao comuneDao;
	@Autowired private StatoDao statoDao;
	@Autowired private DynaTabDao dynaTabDao;
	@Autowired private EnteDao enteDao;

	@Autowired private AbiBiblioLogic abiBiblioLogic;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doImport(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca, Date dataExport, ReportImport reportImport, String basePhotoUrl) throws Exception {
		doImport(biblioteca, dataExport, reportImport, null, false, basePhotoUrl);	
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doImport(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca, Date dataExport, ReportImport reportImport, String username, boolean differito, String basePhotoUrl) throws Exception {

		String codiceAbi = Utility.normalizzaCodiciAbi(biblioteca.getAnagrafica().getCodici().getIsil()); // normalizza il codice abi in IT-AA1234
		log.info("Codice ABI biblioteca: " + codiceAbi);

		String isilSt = Utility.getIsilSt(codiceAbi);
		String isilNr = Utility.getIsilNr(codiceAbi).toString();
		String isilPr = Utility.getIsilPr(codiceAbi);

		if (isilPr == null) {
			String msg = "Impossibile effettuare l'import. Provincia non trovata con ISIL_PR = " + isilPr; 
			log.warn(msg);
			reportImport.addError(msg);
			return;
		}

		log.debug("Inizio procedura di import... ");

		Biblioteca[] biblios = biblioDao.getBibliotecheByCodABI(new String[] {codiceAbi}, 0, 1);
		/*REGIONALE == true  - Versione regionale*/
		/*REGIONALE == false - Versione nazionale*/

		boolean regionale = Constants.REGIONALE;
		if (!regionale) {
			// controllo che il codiceABI passato sia univoco e valido nel database
			if (biblios == null || biblios.length != 1) {
				int found = (biblios == null) ? 0 : biblios.length;
				String msg = "Impossibile effettuare l'import. Trovate " + found + " biblioteche con codice ABI " + codiceAbi;
				log.warn(msg);	
				reportImport.addError(msg);
				return;
			}	
		} else {
			if (biblios == null || biblios.length != 1) {
				biblios = new Biblioteca[1];

				Integer idBiblio = createBibliotecaDefault(biblioteca, isilSt, isilNr, isilPr,reportImport);

				if (idBiblio == null) return;

				biblios = biblioDao.getBibliotecheByCodABI(new String[] {codiceAbi}, 0, 1);
			}
		}

		// Biblioteca trovata attualmente in lavorazione
		Biblioteca bibliotecaDb = biblios[0];


		bibliotecaDb.setIsilStato(isilSt);
		bibliotecaDb.setIsilNumero(Integer.valueOf(isilNr));
		bibliotecaDb.setIsilProvincia(isilPr);


		/** MESSA PRIMA IN OCCUPATA E POI IN REVISIONE **/		
		if (!differito) {
			abiBiblioLogic.setOccupata(bibliotecaDb.getIdBiblioteca());
			abiBiblioLogic.setInRevisione(bibliotecaDb.getIdBiblioteca(), false);	
		} else {
			abiBiblioLogic.setOccupataByUsername(bibliotecaDb.getIdBiblioteca(), username);
			abiBiblioLogic.setInRevisioneByUsername(bibliotecaDb.getIdBiblioteca(), false, username);
		}


		//		COMUNE
		Comune myComune = comuneDao.getComuneByCodIstat(biblioteca.getAnagrafica().getIndirizzo().getComune());
		bibliotecaDb.setComune(myComune);

		// Aggiornamento date
		bibliotecaDb.setCatalogazioneDataImport(dataExport); // ok
		
		// Aggiornamento fonte
		if (biblioteca.getAnagrafica().getFonte() != null) {
			/* Descrizione fonte */
			if (biblioteca.getAnagrafica().getFonte().getDescrizione() != null && biblioteca.getAnagrafica().getFonte().getDescrizione().length() > 0) {
				bibliotecaDb.setFonteDescrizione(biblioteca.getAnagrafica().getFonte().getDescrizione());
			}
			
			/* Url fonte */
			if (biblioteca.getAnagrafica().getFonte().getUrl() != null && biblioteca.getAnagrafica().getFonte().getUrl().length() > 0) {
				bibliotecaDb.setFonteUrl(biblioteca.getAnagrafica().getFonte().getUrl());
			}
		}
		
		if (biblioteca.getAnagrafica().getDataCensimento() != null) {
			String temp = biblioteca.getAnagrafica().getDataCensimento().getContent();
			Date formatted = null;
			try {
				formatted = DateUtils.parseDate(temp, new String[]{"yyyy"});
			} catch (ParseException e) {
				String msg = "Data di censimento non nel formato YYYY:" + temp; 
				log.error(msg, e);
				reportImport.addError(msg);
				throw e;
			}
			bibliotecaDb.setCatalogazioneDataCensimento(formatted); // ok
		}
		if (biblioteca.getAnagrafica().getDataAggiornamento() != null) {
			Date formatted = biblioteca.getAnagrafica().getDataAggiornamento();
			bibliotecaDb.setCatalogazioneDataModificaRemota(formatted); // ok
		} else {
			String msg = "Data di aggiornamento nulla";
			reportImport.addWarn(msg);
			log.warn(msg);
		}

		setDenominazioneUfficiale(biblioteca, reportImport, bibliotecaDb, regionale/*TRUE*/);

		// Nuove denominazioni alternative
		if (biblioteca.getAnagrafica().getNomi() != null) {
			if (biblioteca.getAnagrafica().getNomi().getAlternativi() != null) {
				for (int j = 0; j < biblioteca.getAnagrafica().getNomi().getAlternativi().getAlternativoCount(); j++) {
					String denoAlt = (String) biblioteca.getAnagrafica().getNomi().getAlternativi().getAlternativo(j);
					if (j == 0) {
						//Cancella tutte le denominazioni alternative che verranno sostituite con le nuove 
						if (bibliotecaDb.getDenominazioniAlternatives() != null) {
							biblioDao.removeChilds(bibliotecaDb.getDenominazioniAlternatives());
							log.debug("Cancellate DENOMINAZIONI ALTERNATIVE... ");
						}
					}

					if ((denoAlt != null) && (denoAlt.trim().length() > 0)) {
						DenominazioniAlternative temp = new DenominazioniAlternative();
						temp.setDenominazione(denoAlt.trim());
						temp.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(temp);
						log.debug("Inserita nuova DENOMINAZIONE ALTERNATIVA: " + denoAlt.trim());

					} else {
						String msg = "Denominazione alternativa presente ma vuota";
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}
			}
		}

		//Nuove denominazioni precedenti
		if (biblioteca.getAnagrafica().getNomi() != null) {
			if (biblioteca.getAnagrafica().getNomi().getPrecedenti() != null) {
				for (int j = 0; j < biblioteca.getAnagrafica().getNomi().getPrecedenti().getPrecedenteCount(); j++) {
					String denoPrec = (String)biblioteca.getAnagrafica().getNomi().getPrecedenti().getPrecedente(j);
					if (j == 0) {
						if (bibliotecaDb.getDenominazioniPrecedentis() != null) {
							//Cancella tutte le denominazioni precedenti che verranno sostituite con le nuove
							biblioDao.removeChilds(bibliotecaDb.getDenominazioniPrecedentis());
							log.debug("Cancellate DENOMINAZIONI PRECEDENTI... ");
						}
					}

					if ((denoPrec != null) && (denoPrec.trim().length() > 0)) {
						DenominazioniPrecedenti temp = new DenominazioniPrecedenti();
						temp.setDenominazione(denoPrec.trim());
						temp.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(temp);
						log.debug("Inserita nuova DENOMINAZIONE PRECEDENTE: " + denoPrec.trim());

					} else {
						String msg = "Denominazione precedente presente ma vuota";
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}
			}
		}


		// CONTATTI
		if (biblioteca.getAnagrafica().getContatti() != null) {
			boolean giaCancellati = false;
			//Nuovi contatti telefonici
			if (biblioteca.getAnagrafica().getContatti().getTelefonici() != null) {
				for (int j = 0; j < biblioteca.getAnagrafica().getContatti().getTelefonici().getTelefonicoCount(); j++) {
					Telefonico t = (Telefonico) biblioteca.getAnagrafica().getContatti().getTelefonici().getTelefonico(j);

					if (j == 0) {
						if (bibliotecaDb.getContattis() != null) {
							biblioDao.removeChilds(bibliotecaDb.getContattis());
							giaCancellati = true; // in modo tale che in contatti altri non debba cancellare di nuovo
							log.debug("Cancellati CONTATTI TELEFONICI... ");
						}
					}

					// Carico il TipoContatto dal db
					ContattiTipo contattiTipo = null;
					if (t.getTipo().equals(TelefonicoTipoType.FAX)) {
						contattiTipo = (ContattiTipo) dynaTabDao.searchRecord(ContattiTipo.class, "Fax");
					}

					if (t.getTipo().equals(TelefonicoTipoType.TELEFONO)) {
						contattiTipo = (ContattiTipo) dynaTabDao.searchRecord(ContattiTipo.class, "Telefono");
					}
					if (contattiTipo != null) {
						Contatti temp = new Contatti();
						temp.setValore(t.getPrefisso() + " " +  t.getNumero());
						temp.setContattiTipo(contattiTipo);
						temp.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(temp);
						log.debug("Inserito nuovo CONTATTO TELEFONICO: " + t.getPrefisso() + "-" + t.getNumero());
					}  else {
						String msg = "Tipo di contatto telefonico non trovato: " + t.getTipo();
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}
			}

			//Nuovi altri contatti
			if (biblioteca.getAnagrafica().getContatti().getAltri() != null) {
				for (int j = 0; j < biblioteca.getAnagrafica().getContatti().getAltri().getAltroCount(); j++) {

					if (j == 0 && !giaCancellati) {
						if (bibliotecaDb.getContattis() != null) {
							//Cancella tutti gli altri contatti che verranno sostituiti con i nuovi solo se non li ho gia cancellati prima
							biblioDao.removeChilds(bibliotecaDb.getContattis());
							log.debug("Cancellati ALTRI CONTATTI... ");
						}
					}

					Altro a = (Altro) biblioteca.getAnagrafica().getContatti().getAltri().getAltro(j);

					// Carico il TipoContatto dal db
					ContattiTipo contattiTipo = null;
					if (a.getTipo().equals(AltroTipoType.E_MAIL)) {
						contattiTipo = (ContattiTipo) dynaTabDao.searchRecord(ContattiTipo.class, "E-mail");
					}
					if (a.getTipo().equals(AltroTipoType.TELEX)) {
						contattiTipo = (ContattiTipo) dynaTabDao.searchRecord(ContattiTipo.class, "Telex");
					}
					if (a.getTipo().equals(AltroTipoType.URL)) {
						contattiTipo = (ContattiTipo) dynaTabDao.searchRecord(ContattiTipo.class, "Url");
					}
					if (contattiTipo != null) {
						Contatti temp = new Contatti();
						temp.setValore(a.getValore());
						temp.setNote(a.getNote());
						temp.setContattiTipo(contattiTipo);
						temp.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(temp);
						log.debug("Inserito nuovo ALTRO CONTATTO: " + a.getValore());

					} else {
						String msg = "Tipo di altro contatto non trovato: " + a.getTipo();
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}
			}
		}

		// Gestione codici
		if (biblioteca.getAnagrafica().getCodici() != null) {
			if (bibliotecaDb.getCodicis() != null) {
				biblioDao.removeChilds(bibliotecaDb.getCodicis());
			}

			if (biblioteca.getAnagrafica().getCodici().getSbn() != null) {
				String valore = biblioteca.getAnagrafica().getCodici().getSbn();
				Codici codice = new Codici();
				codice.setValore(valore);
				CodiciTipo codiciTipo = (CodiciTipo) dynaTabDao.searchRecord(CodiciTipo.class, "sbn"); // carico il tipo di codice dal db
				CodiciPK cd = new CodiciPK();
				cd.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				cd.setIdCodici(codiciTipo.getIdCodici());
				codice.setId(cd);
				biblioDao.saveChild(codice);
				log.debug("SBN = " + valore);
			}

			if (biblioteca.getAnagrafica().getCodici().getRism() != null) {
				String valore = biblioteca.getAnagrafica().getCodici().getRism();
				Codici codice = new Codici();
				codice.setValore(valore);
				CodiciTipo codiciTipo = (CodiciTipo) dynaTabDao.searchRecord(CodiciTipo.class, "rism"); // carico il tipo di codice dal db
				CodiciPK cd = new CodiciPK();
				cd.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				cd.setIdCodici(codiciTipo.getIdCodici());
				codice.setId(cd);
				biblioDao.saveChild(codice);
				log.debug("RISM = "  + valore);
			}

			if (biblioteca.getAnagrafica().getCodici().getAcnp() != null) {
				String valore = biblioteca.getAnagrafica().getCodici().getAcnp();
				Codici codice = new Codici();
				codice.setValore(valore);
				CodiciTipo codiciTipo = (CodiciTipo) dynaTabDao.searchRecord(CodiciTipo.class, "acnp"); // carico il tipo di codice dal db
				CodiciPK cd = new CodiciPK();
				cd.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				cd.setIdCodici(codiciTipo.getIdCodici());
				codice.setId(cd);
				biblioDao.saveChild(codice);
				log.debug("ACNP = " + valore);
			}

			if (biblioteca.getAnagrafica().getCodici().getCei() != null) {
				String valore = biblioteca.getAnagrafica().getCodici().getCei();
				Codici codice = new Codici();
				codice.setValore(valore);
				CodiciTipo codiciTipo = (CodiciTipo) dynaTabDao.searchRecord(CodiciTipo.class, "cei"); // carico il tipo di codice dal db
				CodiciPK cd = new CodiciPK();
				cd.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				cd.setIdCodici(codiciTipo.getIdCodici());
				codice.setId(cd);
				biblioDao.saveChild(codice);
				log.debug("CEI = " + valore);
			}

			if (biblioteca.getAnagrafica().getCodici().getCmbs() != null) {
				String valore = biblioteca.getAnagrafica().getCodici().getCmbs();
				Codici codice = new Codici();
				codice.setValore(valore);
				CodiciTipo codiciTipo = (CodiciTipo) dynaTabDao.searchRecord(CodiciTipo.class, "cmbs"); // carico il tipo di codice dal db
				CodiciPK cd = new CodiciPK();
				cd.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				cd.setIdCodici(codiciTipo.getIdCodici());
				codice.setId(cd);
				biblioDao.saveChild(codice);
				log.debug("CMBS = " + valore);
			}
			log.debug("Modificati CODICI: ");
		}

		// Codice fiscale e partita IVA
		if (biblioteca.getAmministrativa().getCodiceFiscale() != null) {
			String codiceFiscale = biblioteca.getAmministrativa().getCodiceFiscale();
			bibliotecaDb.setCodiceFiscale(codiceFiscale);
			log.debug("Modifica CODICE FISCALE: " + codiceFiscale);
		}
		if (biblioteca.getAmministrativa().getPartitaIVA() != null) {
			String partitaIva = biblioteca.getAmministrativa().getPartitaIVA();
			bibliotecaDb.setPartitaIva(partitaIva);
			log.debug("Modifica PARTITA IVA: " + partitaIva);
		}


		//Indirizzo
		if (biblioteca.getAnagrafica().getIndirizzo().getVia() != null) {
			String via = biblioteca.getAnagrafica().getIndirizzo().getVia();
			bibliotecaDb.setIndirizzo(via);
			log.debug("Modifica via: " + via);
		}
		if (biblioteca.getAnagrafica().getIndirizzo().getCap() != null) {
			String cap = biblioteca.getAnagrafica().getIndirizzo().getCap();
			bibliotecaDb.setCap(cap);
			log.debug("Modifica cap: " + cap);
		}
		if (biblioteca.getAnagrafica().getIndirizzo().getFrazione()!= null) {
			String frazione = biblioteca.getAnagrafica().getIndirizzo().getFrazione();
			bibliotecaDb.setFrazione(frazione);
			log.debug("Modifica frazione: " + frazione);
		}

		// Coordinate
		if (biblioteca.getAnagrafica().getIndirizzo().getCoordinate() != null) {
			Double latitudine = Double.valueOf(biblioteca.getAnagrafica().getIndirizzo().getCoordinate().getLatitudine());
			Double longitudine = Double.valueOf(biblioteca.getAnagrafica().getIndirizzo().getCoordinate().getLongitudine());

			if (bibliotecaDb.getGeolocalizzazione() != null) {
				bibliotecaDb.getGeolocalizzazione().setLatitudine(latitudine);
				bibliotecaDb.getGeolocalizzazione().setLongitudine(longitudine);

			} else {
				Geolocalizzazione coordinate = new Geolocalizzazione();
				coordinate.setLatitudine(latitudine);
				coordinate.setLongitudine(longitudine);
				coordinate.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
				bibliotecaDb.setGeolocalizzazione(coordinate);
				biblioDao.saveChild(coordinate);
			}
		}

		//TIPOLOGIA AMMINISTRATIVA E FUNZIONALE
		//Autonomia amministrativa e struttura gerarchica
		if (biblioteca.getAmministrativa().getAutonoma() != null) {
			if (biblioteca.getAmministrativa().getAutonoma() == SiNoType.S) {
				bibliotecaDb.setAutonomiaAmministrativa(true);

			} else {
				bibliotecaDb.setAutonomiaAmministrativa(false);
			}
			log.debug("Modificata AUTONOMIA AMMINISTRATIVA..." + bibliotecaDb.getAutonomiaAmministrativa());
		}

		//Data Fondazione
		if (biblioteca.getAnagrafica().getIstituzione() != null) {
			if (biblioteca.getAnagrafica().getIstituzione().getDataFondazione() != null) {
				if (biblioteca.getAnagrafica().getIstituzione().getDataFondazione().getContent() != null) {
					String dataFondazione = biblioteca.getAnagrafica().getIstituzione().getDataFondazione().getContent();
					bibliotecaDb.setDataFondazione(dataFondazione);
					log.debug("Modificata dataFondazione: " + dataFondazione);
				}
			} 

			if (biblioteca.getAnagrafica().getIstituzione().getDataIstituzione() != null && 
					biblioteca.getAnagrafica().getIstituzione().getDataIstituzione().getContent() != null) {
				String dataIstituzione = biblioteca.getAnagrafica().getIstituzione().getDataIstituzione().getContent();
				bibliotecaDb.setDataIstituzione(dataIstituzione);
				log.debug("Modificata dataIstituzione: " + dataIstituzione);
			}
		}

		//Tipologia Amministrativa (Ente)			
		setEnte(biblioteca, reportImport, bibliotecaDb);

		//PROFILO STORICO - SEDE - SISTEMI DI BIBLIOTECHE
		//Edificio e profilo storico
		if (biblioteca.getAnagrafica().getEdificio() != null) {
			//Denominazione
			if (biblioteca.getAnagrafica().getEdificio().getDenominazione() != null) {
				String edificioDenominazione = biblioteca.getAnagrafica().getEdificio().getDenominazione();
				bibliotecaDb.setEdificioDenominazione(edificioDenominazione);
				log.debug("Modificato edificio Denominazione: " + edificioDenominazione);
			}

			//Appositamente costruito (Si/No)
			if (biblioteca.getAnagrafica().getEdificio().getAppositamenteCostruito() == SiNoType.S) {
				bibliotecaDb.setEdificioAppositamenteCostruito(true);
				log.debug("Modificato edificio appositamente costruito: " + true);

			} else if (biblioteca.getAnagrafica().getEdificio().getAppositamenteCostruito() == SiNoType.N) {
				bibliotecaDb.setEdificioAppositamenteCostruito(false);
				log.debug("Modificato edificio appositamente costruito: " + false);
			}

			//Monumentale (Si/No)
			if (biblioteca.getAnagrafica().getEdificio().getMonumentale() == SiNoType.S) {
				bibliotecaDb.setEdificioMonumentale(true);
				log.debug("Modificato edificio Monumentale: " + true);

			} else if (biblioteca.getAnagrafica().getEdificio().getMonumentale() == SiNoType.N) {
				bibliotecaDb.setEdificioMonumentale(false);
				log.debug("Modificato edificio Monumentale: " + false);
			}

			//Data costruzione
			if (biblioteca.getAnagrafica().getEdificio().getDataCostruzione() != null) {
				String edificioDataCostruzione = biblioteca.getAnagrafica().getEdificio().getDataCostruzione().getContent();
				bibliotecaDb.setEdificioDataCostruzione(edificioDataCostruzione);
				log.debug("Modificato edificio DataCostruzione: " + edificioDataCostruzione);
			}
			
			/* Immagini */
			if (biblioteca.getAnagrafica().getEdificio().getImmagini() != null) {
				for (int i = 0; i < biblioteca.getAnagrafica().getEdificio().getImmagini().getImmagineCount(); i++) {
					
					if (i == 0) {
						if (bibliotecaDb.getPhotos() != null) {
							biblioDao.removeChilds(bibliotecaDb.getPhotos());
							log.debug("Cancellate PHOTO...");
						}
					}
					
					Immagine img = (Immagine) biblioteca.getAnagrafica().getEdificio().getImmagini().getImmagine(i);
					
					if (img != null) {
						Photo photo = new Photo();
						if (img.getUrl().contains(basePhotoUrl)) {
							String filename = FilenameUtils.getName(img.getUrl());
							photo.setUri(filename);
							
						} else {
							photo.setUri(img.getUrl());
						}
						
						photo.setCaption(img.getDidascalia());
						photo.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(photo);
						log.debug("Inserita nuova PHOTO: " + img.getUrl());
					}
					
				}
				
			}
		}

		//Sistemi di biblioteche
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getSistemi() != null) {
			Sistemi sistemi = (Sistemi) biblioteca.getServizi().getSistemi();

			if (bibliotecaDb.getSistemiBiblioteches() != null) {
				bibliotecaDb.getSistemiBiblioteches().clear();
				log.debug("Cancellati SISTEMI DI BIBLIOTECHE...");

			} else {
				bibliotecaDb.setSistemiBiblioteches(new ArrayList<SistemiBiblioteche>());
			}

			for (int j = 0; j < biblioteca.getServizi().getSistemi().getSistemiItemCount(); j++) {	
				SistemiItem s_i = (SistemiItem) sistemi.getSistemiItem(j);

				String searchValore = s_i.getSistema();
				SistemiBiblioteche sistemiBiblioteche = (SistemiBiblioteche) dynaTabDao.searchRecord(SistemiBiblioteche.class, searchValore);

				if (sistemiBiblioteche != null) {
					if (!bibliotecaDb.getSistemiBiblioteches().contains(sistemiBiblioteche)) {
						bibliotecaDb.getSistemiBiblioteches().add(sistemiBiblioteche);
						log.debug("Inserito nuovo SISTEMA: " + s_i.getSistema());

					} else {
						String msg = "Sistemi biblioteche duplicato: " + searchValore;
						reportImport.addWarn(msg);
						log.warn(msg);
					}

				} else {
					String msg = "Sistemi biblioteche non trovato: " + searchValore;
					reportImport.addWarn(msg);
					log.warn(msg);				
				}
			}
		}

		//ACCESSO E DESTINAZIONE SOCIALE
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getAccesso() != null) {
			if (biblioteca.getServizi().getAccesso().getDestinazioniSociali() != null) {
				Destinazione[] dest = biblioteca.getServizi().getAccesso().getDestinazioniSociali().getDestinazione();
				for (int j = 0; j < dest.length; j++) {
					if (j == 0) {
						if (bibliotecaDb.getDestinazioniSocialis() != null) {
							biblioDao.removeChilds(bibliotecaDb.getDestinazioniSocialis());
							log.debug("Cancellate TUTTE LE DESTINAZIONI SOCIALI...");

						} else {
							bibliotecaDb.setDestinazioniSocialis(new ArrayList<DestinazioniSociali>());
						}
					}
					if ((dest[j].getValore() != null) && (dest[j].getValore().trim().length() > 0)) {
						String valore = dest[j].getValore();
						String note = null;
						if ((dest[j].getNote() != null) && (dest[j].getNote().trim().length() > 0)) {
							note = dest[j].getNote().trim();
						}

						DestinazioniSocialiTipo destinazioniSocialiTipo = (DestinazioniSocialiTipo) dynaTabDao.searchRecord(DestinazioniSocialiTipo.class, valore);
						if (destinazioniSocialiTipo != null) {
							DestinazioniSocialiPK destinazioniSocialiPK = new DestinazioniSocialiPK();
							destinazioniSocialiPK.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
							destinazioniSocialiPK.setIdDestinazioniSocialiTipo(destinazioniSocialiTipo.getIdDestinazioniSociali());
							
							DestinazioniSociali destinazioniSociali = new DestinazioniSociali();
							destinazioniSociali.setDestinazioniSocialiTipo(destinazioniSocialiTipo);
							destinazioniSociali.setNote(note);
							destinazioniSociali.setId(destinazioniSocialiPK);
							biblioDao.saveChild(destinazioniSociali);
							log.debug("Inserito DESTINAZIONE SOCIALE valore: " + valore + " note:" + note);

						} else {
							String msg = "Destinazione sociale non trovata: " + valore;
							reportImport.addWarn(msg);
							log.warn(msg);
						}

					}
				}
			}

			if (biblioteca.getServizi().getAccesso().getAperta() != null) {
				if (biblioteca.getServizi().getAccesso().getAperta().equals(SiNoType.S)) {
					bibliotecaDb.setAccessoRiservato(false);

				} else if (biblioteca.getServizi().getAccesso().getAperta().equals(SiNoType.N)) {
					bibliotecaDb.setAccessoRiservato(true);
				}
				log.debug("Modificato accesso riservato: " + bibliotecaDb.getAccessoRiservato());
			}

			//Accesso handicap
			if (biblioteca.getServizi().getAccesso().getHandicap() != null) {
				if (biblioteca.getServizi().getAccesso().getHandicap() == SiNoType.S) {
					bibliotecaDb.setAccessoHandicap(true);
					log.debug("Modificato accesso handicap: " + true);

				} else if (biblioteca.getServizi().getAccesso().getHandicap() == SiNoType.N) {
					bibliotecaDb.setAccessoHandicap(false);
					log.debug("Modificato accesso handicap: " + false);
				}
			}

			// Modalità di accesso
			if (biblioteca.getServizi().getAccesso().getModalitaAccesso() != null) {
				String[] ma = biblioteca.getServizi().getAccesso().getModalitaAccesso().getModo();
				for (int j = 0; j < ma.length; j++) {
					if (j == 0) {
						if (bibliotecaDb.getAccessoModalitas() != null) {
							bibliotecaDb.getAccessoModalitas().clear();
							log.debug("Cancellati TUTTE LE CONDIZIONI DI ACCESSO...");

						} else {
							bibliotecaDb.setAccessoModalitas(new ArrayList<AccessoModalita>());
						}
					}

					AccessoModalita accessoModalita = (AccessoModalita) dynaTabDao.searchRecord(AccessoModalita.class, ma[j]);

					if (accessoModalita != null) {
						if (!bibliotecaDb.getAccessoModalitas().contains(accessoModalita)) {
							bibliotecaDb.getAccessoModalitas().add(accessoModalita);
							log.debug("Inserita CONDIZIONE DI ACCESSO: " + ma[j]);	

						} else {
							String msg = "Accesso Modalita duplicato: " + ma[j];
							reportImport.addWarn(msg);
							log.warn(msg);
						}

					} else {
						String msg = "Condizione di accesso non trovata: " + ma[j];
						reportImport.addWarn(msg);
						log.warn(msg);
					}

				}
			}
		}

		if (biblioteca.getAmministrativa() != null && biblioteca.getAmministrativa().getRegolamento() != null) {
			if (bibliotecaDb.getRegolamentos() != null) {
				biblioDao.removeChilds(bibliotecaDb.getRegolamentos());
				log.debug("Cancellati REGOLAMENTI...");
			}

			Regolamento regolamento = new Regolamento();
			if (biblioteca.getAmministrativa().getRegolamento().getNorma() != null) {
				String riferimentoNormativa = biblioteca.getAmministrativa().getRegolamento().getNorma();
				regolamento.setRiferimentoNormativa(riferimentoNormativa);
				log.debug("Inserita riferimentoNormativa: " + riferimentoNormativa);
			}
			if (biblioteca.getAmministrativa().getRegolamento().getUrl() != null) {
				String url = biblioteca.getAmministrativa().getRegolamento().getUrl();
				regolamento.setUrl(url);
				log.debug("Inserita url: " + url);
			}
			regolamento.setBiblioteca(bibliotecaDb);
			biblioDao.saveChild(regolamento);
		}



		//INFORMAZIONI SUPPLEMENTARI
		if (biblioteca.getAmministrativa() != null && biblioteca.getAmministrativa().getStrutture() != null) {
			if (biblioteca.getAmministrativa().getStrutture().getPostazioni() != null) {
				if (biblioteca.getAmministrativa().getStrutture().getPostazioni().getVideo() > 0) {
					int postiVideo = (int) biblioteca.getAmministrativa().getStrutture().getPostazioni().getVideo();
					bibliotecaDb.setPostiVideo(postiVideo);
					log.debug("postiVideo: " + postiVideo);
				}
				if (biblioteca.getAmministrativa().getStrutture().getPostazioni().getAudio() > 0) {
					int postiAudio = (int) biblioteca.getAmministrativa().getStrutture().getPostazioni().getAudio();
					bibliotecaDb.setPostiAudio(postiAudio);
					log.debug("postiAudio: " + postiAudio);
				}	
				if (biblioteca.getAmministrativa().getStrutture().getPostazioni().getInternet() > 0) {
					int postiInternet = (int) biblioteca.getAmministrativa().getStrutture().getPostazioni().getInternet();
					bibliotecaDb.setPostiInternet(postiInternet);
					log.debug("postiInternet: " + postiInternet);
				}
				if (biblioteca.getAmministrativa().getStrutture().getPostazioni().getLettura() > 0) {
					int postiLettura = (int) biblioteca.getAmministrativa().getStrutture().getPostazioni().getLettura();
					bibliotecaDb.setPostiLettura(postiLettura);
					log.debug("postiLettura: " + postiLettura);
				}
			}

			bibliotecaDb.setMqPubblici(0); // -- resetta la sup. r.eschini@inera.it
			bibliotecaDb.setMqTotali(0); // -- resetta la sup. r.eschini@inera.it
			if (biblioteca.getAmministrativa().getStrutture().getSuperficie() != null) {
				if (biblioteca.getAmministrativa().getStrutture().getSuperficie().getAlPubblico() != null) {
					if (biblioteca.getAmministrativa().getStrutture().getSuperficie().getAlPubblico().intValue() > 0) {
						int mqPubblici = biblioteca.getAmministrativa().getStrutture().getSuperficie().getAlPubblico().intValue();
						bibliotecaDb.setMqPubblici(mqPubblici);
						log.debug("Modificati dati SUPERFICIE MqPubblic:" + mqPubblici);
					}
				}
				if (biblioteca.getAmministrativa().getStrutture().getSuperficie().getTotale() != null) {
					if (biblioteca.getAmministrativa().getStrutture().getSuperficie().getTotale().intValue() > 0) {
						int mqTotali = biblioteca.getAmministrativa().getStrutture().getSuperficie().getTotale().intValue();
						bibliotecaDb.setMqTotali(mqTotali);
						log.debug("Modificati dati SUPERFICIE SEDE MqTotali:" + mqTotali);
					}
				}
			} 

			bibliotecaDb.setMlAperti(0); // -- resetta la sup. r.eschini@inera.it
			bibliotecaDb.setMlMagazzini(0); // -- resetta la sup. r.eschini@inera.it
			if (biblioteca.getAmministrativa().getStrutture().getScaffalature()!= null) {
				if ((biblioteca.getAmministrativa().getStrutture().getScaffalature().getMagazzino() != null) &&
						(biblioteca.getAmministrativa().getStrutture().getScaffalature().getMagazzino().intValue() > 0)) {
					int mlMagazzini = biblioteca.getAmministrativa().getStrutture().getScaffalature().getMagazzino().intValue();
					bibliotecaDb.setMlMagazzini(mlMagazzini);
					log.debug("Modificati dati SUPERFICIE SEDE mlMagazzini:" + mlMagazzini);
				}
				if ((biblioteca.getAmministrativa().getStrutture().getScaffalature().getPubbliche() != null) &&
						(biblioteca.getAmministrativa().getStrutture().getScaffalature().getPubbliche().intValue() > 0)) {
					int mlAperti = biblioteca.getAmministrativa().getStrutture().getScaffalature().getPubbliche().intValue(); 
					bibliotecaDb.setMlAperti(mlAperti);
					log.debug("Modificati dati SUPERFICIE SEDE mlAperti:" + mlAperti);
				}
			}
		}

		// UTENTI
		if (biblioteca.getAmministrativa().getUtenti() != null) {
			if (biblioteca.getAmministrativa().getUtenti().getIscrittiPrestito() > 0) {
				int utentiIscritti = (int) biblioteca.getAmministrativa().getUtenti().getIscrittiPrestito();
				bibliotecaDb.setUtentiIscritti(utentiIscritti);
				log.debug("Modificati NUMERO UTENZE utentiIscritti:" + utentiIscritti);
			}

			if (biblioteca.getAmministrativa().getUtenti().getMinoriQuattordiciAnni() > 0) {
				int utentiUnder14 = (int) biblioteca.getAmministrativa().getUtenti().getMinoriQuattordiciAnni();
				bibliotecaDb.setUtentiUnder14(utentiUnder14);
				log.debug("Modificati NUMERO UTENZE utentiUnder14:" + utentiUnder14);
			}

			if (biblioteca.getAmministrativa().getUtenti().getUltimoAnno()>0) {
				int utentiIscrittiPrestitoAnno = (int) biblioteca.getAmministrativa().getUtenti().getUltimoAnno();
				bibliotecaDb.setUtentiIscrittiPrestitoAnno(utentiIscrittiPrestitoAnno);
				log.debug("Modificati NUMERO UTENZE utentiIscrittiPrestitoAnno:" + utentiIscrittiPrestitoAnno);
			}
		}

		//ORARI
		//Totali orari
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getServiziOrario() != null) {
			if (biblioteca.getServizi().getServiziOrario().getOreSettimanali() != null) {
				int nOreSettimanali = biblioteca.getServizi().getServiziOrario().getOreSettimanali().intValue();
				bibliotecaDb.setNOreSettimanali(nOreSettimanali);
				log.debug("Modificati dati nOreSettimanali: " + nOreSettimanali);
			}
			if (biblioteca.getServizi().getServiziOrario().getOreSettimanaliPomeridiane() != null) {
				int nOreSettimanaliPom = biblioteca.getServizi().getServiziOrario().getOreSettimanaliPomeridiane().intValue();
				bibliotecaDb.setNOreSettimanaliPom(nOreSettimanaliPom);
				log.debug("Modificati dati nOreSettimanaliPom: " + nOreSettimanaliPom);
			}
			if (biblioteca.getServizi().getServiziOrario().getSettimaneApertura() != null) {
				int nSettimApertura = biblioteca.getServizi().getServiziOrario().getSettimaneApertura().intValue();
				bibliotecaDb.setNSettimApertura(nSettimApertura);
				log.debug("Modificati dati nSettimApertura: " + nSettimApertura);
			}


			//Orario ufficiale
			if (biblioteca.getServizi().getServiziOrario().getUfficiale() != null) {
				for (int j = 0; j < biblioteca.getServizi().getServiziOrario().getUfficiale().getOrarioCount(); j++) {
					if (j == 0) {
						if (bibliotecaDb.getOrarioUfficialis() != null) {
							//Cancella tutti gli orari ufficiali che verranno sostituiti con i nuovi
							biblioDao.removeChilds(bibliotecaDb.getOrarioUfficialis());
							log.debug("Cancellati ORARI UFFICIALI... ");
						}
					}

					Orario o = (Orario) biblioteca.getServizi().getServiziOrario().getUfficiale().getOrario(j);
					int giorno = giornoCastorToHibernate(o.getGiorno());
					String fascia = fasciaOraria(o.getDalle(), o.getAlle());
					Date dalle = parseOrario(o.getDalle());
					Date alle = parseOrario(o.getAlle());
					if (giorno != -1) {
						OrarioUfficiali orarioUfficiali = new OrarioUfficiali();
						orarioUfficiali.setGiorno(giorno);
						orarioUfficiali.setDalle(dalle);
						orarioUfficiali.setAlle(alle);
						orarioUfficiali.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(orarioUfficiali);
						log.debug("Inserito orario ufficiale giorno:" + giorno + "dalle:" + dalle + "alle:" + alle + "fascia:" + fascia);

					} else {
						String msg = "Errore nel calcolo del giorno dell' orario ufficiale " + o.getGiorno();
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}
			}

			if (biblioteca.getServizi().getServiziOrario().getVariazione() != null) {
				for (int j = 0; j < biblioteca.getServizi().getServiziOrario().getVariazioneCount(); j++) {
					if (j == 0) {
						if (bibliotecaDb.getOrarioVariazionis() != null) {
							//Cancella tutte le variazione di orari che verranno sostituite con le nuove
							biblioDao.removeChilds(bibliotecaDb.getOrarioVariazionis());
							log.debug("Cancellati VARIAZIONI ORARI... ");
						}
					}

					Variazione v = biblioteca.getServizi().getServiziOrario().getVariazione(j);
					for (int i = 0; i < v.getOrarioCount(); i++) {
						Orario o = v.getOrario(i);
						int giorno = giornoCastorToHibernate(o.getGiorno());
						String fascia = fasciaOraria(o.getDalle(), o.getAlle());
						Date dalle = parseOrario(o.getDalle());
						Date alle = parseOrario(o.getAlle());
						if (giorno != -1) {
							OrarioVariazioni orarioVariazioni = new OrarioVariazioni();
							orarioVariazioni.setGiorno(giorno);
							orarioVariazioni.setDescrizione(v.getNote());
							orarioVariazioni.setDalle(dalle);
							orarioVariazioni.setAlle(alle);
							orarioVariazioni.setBiblioteca(bibliotecaDb);
							biblioDao.saveChild(orarioVariazioni);
							log.debug("Inserito orario ufficiale giorno:" + giorno + "dalle:" + dalle + "alle:" + alle + "fascia:" + fascia);

						} else {
							String msg = "Errore nel calcolo del giorno dell' orario variazione " + o.getGiorno();
							reportImport.addWarn(msg);
							log.warn(msg);
						}
					}
				}
			}

			//Chiusura
			if (biblioteca.getServizi().getServiziOrario().getChiusura() != null) {
				for (int j = 0; j < biblioteca.getServizi().getServiziOrario().getChiusuraCount(); j++) {
					if (j == 0) {
						if (bibliotecaDb.getOrarioChiusures() != null) {
							//Cancella tuttle chiusure presenti che verranno sostituite con le nuove
							biblioDao.removeChilds(bibliotecaDb.getOrarioChiusures());
							log.debug("Cancellate CHIUSURE... ");
						}
					}

					Chiusura chiusura = biblioteca.getServizi().getServiziOrario().getChiusura(j);
					if (chiusura.getNote() != null && !"".equals(chiusura.getNote())) {
						String note =  chiusura.getNote();
						OrarioChiusure chiusure = new OrarioChiusure();
						chiusure.setDescrizione(note);
						chiusure.setBiblioteca(bibliotecaDb);
						biblioDao.saveChild(chiusure);
						log.debug("Aggiunta chiusura " + note);
					}
				}
			}
		}

		//PATRIMONIO LIBRARIO - SPECIALIZZAZIONI - FONDI SPECIALI
		if (biblioteca.getPatrimonio() != null) {
			int totalePosseduto = (int) biblioteca.getPatrimonio().getTotalePosseduto();
			bibliotecaDb.setBilancioPatrimonialePosseduto(totalePosseduto);
			if (biblioteca.getPatrimonio().getAcquistiUltimiQuindiciAnni() != null) {
				int bilancioUsciteAcquistiUltimi15 = biblioteca.getPatrimonio().getAcquistiUltimiQuindiciAnni().intValue();
				bibliotecaDb.setBilancioUsciteAcquistiUltimi15(bilancioUsciteAcquistiUltimi15);
			}

			int bilancioPatrimonialePossedutoUnder14 = (int) biblioteca.getPatrimonio().getTotalePossedutoRagazzi();
			bibliotecaDb.setBilancioPatrimonialePossedutoUnder14(bilancioPatrimonialePossedutoUnder14);

			//FONDI ANTICHI
			setFondiAntichiConsistenza(biblioteca, reportImport, bibliotecaDb);

			//Fondi Speciali
			if (biblioteca.getPatrimonio().getFondiSpeciali() != null) {
				for (int j = 0; j < biblioteca.getPatrimonio().getFondiSpeciali().getFondoSpecialeCount(); j++) {
					FondoSpeciale fs = (FondoSpeciale) biblioteca.getPatrimonio().getFondiSpeciali().getFondoSpeciale(j);
					if (j == 0) {
						if (bibliotecaDb.getFondiSpecialis() != null) {
							bibliotecaDb.getFondiSpecialis().clear();
							log.debug("Cancellati FONDI SPECIALI...");

						} else {
							bibliotecaDb.setFondiSpecialis(new ArrayList<FondiSpeciali>());
						}
					}

					FondiSpeciali fondiSpeciali = new FondiSpeciali();
					if (fs.getNome() != null) {
						String denominazione = fs.getNome().trim();
						fondiSpeciali.setDenominazione(denominazione);
					}
					if (fs.getDescrizione() != null) {
						String descrizione = fs.getDescrizione().trim();
						fondiSpeciali.setDescrizione(descrizione);
					}
					if (fs.getDepositato() == SiNoType.S) {
						fondiSpeciali.setFondoDepositato(true);

					} else if (fs.getDepositato() == SiNoType.N) {
						fondiSpeciali.setFondoDepositato(false);
					}

					String tipo = "NON SPECIFICATO";							
					if (fs.getCatalogoInventario() == CatalogoInventarioType.O) {
						tipo = "Online";
					}
					if (fs.getCatalogoInventario() == CatalogoInventarioType.S) {
						tipo = "Schede";
					}
					if (fs.getCatalogoInventario() == CatalogoInventarioType.V) {
						tipo = "Volumi";
					}
					if (fs.getCatalogoInventario() == CatalogoInventarioType.N) {
						tipo = "Non presente";
					}

					FondiSpecialiCatalogazioneInventario fondiSpecialiCatalogazioneInventario = 
						(FondiSpecialiCatalogazioneInventario) dynaTabDao.searchRecord(FondiSpecialiCatalogazioneInventario.class, tipo);
					if (fondiSpecialiCatalogazioneInventario == null) {
						String msg = "Fondi speciali catalogazione inventario non trovato: " + fs.getCatalogoInventario();
						reportImport.addWarn(msg);
						log.warn(msg);
					}
					fondiSpeciali.setFondiSpecialiCatalogazioneInventario(fondiSpecialiCatalogazioneInventario);

					if (fs.getCatalogoInventarioUrl() != null) {
						String url = fs.getCatalogoInventarioUrl().trim();
						fondiSpeciali.setCatalogazioneInventarioUrl(url);
					}
					if (fs.getFondoSpecialeCdd() != null) {
						String cdd = fs.getFondoSpecialeCdd().trim();
						
						cdd = StringUtils.replace(cdd, ".", "");
						
						Dewey dewey = (Dewey) dynaTabDao.searchRecord(Dewey.class, cdd);
						if (dewey == null) {
							String msg = "Dewey non trovato in fondo speciale:" + cdd;
							reportImport.addWarn(msg);
							log.warn(msg);
						}
						fondiSpeciali.setDewey(dewey);
					}				
					biblioDao.saveChild(fondiSpeciali);
					bibliotecaDb.getFondiSpecialis().add(fondiSpeciali);
				}
			}

			//Patrimonio
			Vector<Integer> checkGiaInserito = new Vector<Integer>();
			if (biblioteca.getPatrimonio().getMateriali() != null) {
				for (int j = 0; j < biblioteca.getPatrimonio().getMateriali().getMaterialeCount(); j++) {
					if (j == 0) {
						if (bibliotecaDb.getPatrimonios() != null) {
							biblioDao.removeChilds(bibliotecaDb.getPatrimonios());
							log.debug("Cancellato PATRIMONIO...");
						}
					}
					Materiale m = (Materiale) biblioteca.getPatrimonio().getMateriali().getMateriale(j);

					int quantita = (int) m.getPosseduto();
					int quantitaUltimoAnno = (int) m.getAcquistiUltimoAnno();

					String descrizioneSpec = m.getNome();
					PatrimonioSpecializzazione patrSpec = (PatrimonioSpecializzazione) dynaTabDao.searchRecord(PatrimonioSpecializzazione.class, descrizioneSpec);
					if (patrSpec == null) {
						String msg = "Patrimonio Specializzazione non trovato:" + descrizioneSpec;
						reportImport.addWarn(msg);
						log.warn(msg);

					} else {
						Patrimonio patrimonio = new Patrimonio();
						patrimonio.setQuantita(quantita);
						patrimonio.setQuantitaUltimoAnno(quantitaUltimoAnno);
						PatrimonioPK id = new PatrimonioPK();
						id.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
						id.setIdPatrimonioSpecializzazione(patrSpec.getIdPatrimonioSpecializzazione());
						patrimonio.setId(id);
						if (!checkGiaInserito.contains(patrSpec.getIdPatrimonioSpecializzazione())) {
							log.debug("Inserito nuovo elemento di PATRIMONIO: " + descrizioneSpec);
							biblioDao.saveChild(patrimonio);
							checkGiaInserito.add(patrSpec.getIdPatrimonioSpecializzazione());

						} else {
							biblioDao.updateChild(patrimonio);
							String msg = "Patrimonio Specializzazione duplicato nel XML, sarà inserito solo l'ultimo:" + descrizioneSpec;
							reportImport.addWarn(msg);
							log.warn(msg);
						}
					}
				}
			}
			//Inventario informatizzato
			if (biblioteca.getPatrimonio().getPatrimonioInventario() != null) {
				if (biblioteca.getPatrimonio().getPatrimonioInventario().getCartaceo() == SiNoType.S) {
					bibliotecaDb.setInventarioCartaceo(true);
					log.debug("Modificato INVENTARIO CARTACEO: "+ true);

				} else if (biblioteca.getPatrimonio().getPatrimonioInventario().getCartaceo() == SiNoType.N) {
					bibliotecaDb.setInventarioCartaceo(false);
					log.debug("Modificato INVENTARIO CARTACEO: "+ false);
				}

				if (biblioteca.getPatrimonio().getPatrimonioInventario().getCartaceo() == SiNoType.S) {
					bibliotecaDb.setInventarioInformatizzato(true);
					log.debug("Modificato INVENTARIO INFORMATIZZATO: "+ true);

				} else if (biblioteca.getPatrimonio().getPatrimonioInventario().getCartaceo() == SiNoType.N) {
					bibliotecaDb.setInventarioInformatizzato(false);
					log.debug("Modificato INVENTARIO INFORMATIZZATO: "+ false);
				}
				log.debug("Modificato INVENTARIO INFORMATIZZATO...");
			}

			//Catalogo topografico
			if (biblioteca.getPatrimonio().getCatalogoTopografico() != null) {
				if (biblioteca.getPatrimonio().getCatalogoTopografico().getCartaceo() == SiNoType.S) {
					bibliotecaDb.setCatalogoTopograficoCartaceo(true);
					log.debug("Modificato CATALOGO TOPOGRAFICO CARTACEO: " + true);

				} else if (biblioteca.getPatrimonio().getCatalogoTopografico().getCartaceo() == SiNoType.N) {
					bibliotecaDb.setCatalogoTopograficoCartaceo(false);
					log.debug("Modificato CATALOGO TOPOGRAFICO CARTACEO: " + false);
				}

				if (biblioteca.getPatrimonio().getCatalogoTopografico().getInformatizzato() == SiNoType.S) {
					bibliotecaDb.setCatalogoTopograficoInformatizzato(true);
					log.debug("Modificato CATALOGO TOPOGRAFICO INFORMATIZZATO: " + true);

				} else if (biblioteca.getPatrimonio().getCatalogoTopografico().getInformatizzato() == SiNoType.N) {
					bibliotecaDb.setCatalogoTopograficoInformatizzato(false);
					log.debug("Modificato CATALOGO TOPOGRAFICO INFORMATIZZATO: " + false);
				}
				log.debug("Modificato CATALOGO TOPOGRAFICO...");
			}
		}


		//Specializzazioni (Dewey)
		if (biblioteca.getSpecializzazioni() != null) {
			Specializzazioni specializzazioni = biblioteca.getSpecializzazioni();
			for (int j = 0; j < specializzazioni.getSpecializzazioneCount(); j++) {
				Specializzazione s = (Specializzazione) specializzazioni.getSpecializzazione(j);
				String cdd = s.getSpecializzazioneCdd();
				String descrizione = s.getDescrizioneLibera();
				if (j == 0) {
					if (bibliotecaDb.getDeweyLiberos() != null) {
						biblioDao.removeChilds(bibliotecaDb.getDeweyLiberos());
						log.debug("Cancellate SPECIALIZZAZIONI...");
					}
				}

				cdd = StringUtils.replace(cdd, ".", "");
				Dewey dewey = (Dewey) dynaTabDao.loadRecord(Dewey.class, cdd);
				if (dewey != null) {
					DeweyLiberoPK id = new DeweyLiberoPK();
					id.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
					id.setIdDewey(dewey.getIdDewey());

					DeweyLibero deweyLibero = new DeweyLibero();
					deweyLibero.setDescrizione(descrizione);
					deweyLibero.setId(id);
					biblioDao.saveChild(deweyLibero);
					log.debug("Inserito nuova SPECIALIZZAZIONE cdd: " + cdd + " descrizione: " + descrizione);

				} else {
					String msg = "Dewey non trovato in patrimonio specializzazione: " + descrizione;
					reportImport.addWarn(msg);
					log.warn(msg);
				}

			}
		}

		//SERVIZI E SEZIONI SPECIALI
		//Accesso Internet
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getInternet() != null) {
			Internet internet = biblioteca.getServizi().getInternet();

			if (internet.getAttivo() != null) {
				if (internet.getAttivo() == SiNoType.S) {
					bibliotecaDb.setAttivoAccessoInternet(true);

					/* A tempo */
					if (internet.getATempo() != null) {
						if (internet.getATempo() == SiNoType.S) {
							bibliotecaDb.setAccessoInternetTempo(true);

						} else if (internet.getATempo() == SiNoType.N) {
							bibliotecaDb.setAccessoInternetTempo(false);
						}

					} else {
						bibliotecaDb.setAccessoInternetTempo(null);
					}

					/* A pagamento */
					if (internet.getAPagamento() != null) {
						if (internet.getAPagamento() == SiNoType.S) {
							bibliotecaDb.setAccessoInternetPagamento(true);

						} else if (internet.getAPagamento() == SiNoType.N) {
							bibliotecaDb.setAccessoInternetPagamento(false);
						}

					} else {
						bibliotecaDb.setAccessoInternetPagamento(null);
					}

					/* Con proxy */
					if (internet.getConProxy() != null) {
						if (internet.getConProxy() == SiNoType.S) {
							bibliotecaDb.setAccessoInternetProxy(true);

						} else if (internet.getConProxy() == SiNoType.N) {
							bibliotecaDb.setAccessoInternetProxy(false);
						}

					} else {
						bibliotecaDb.setAccessoInternetProxy(null);
					}

				} else if (internet.getAttivo() == SiNoType.N) {
					bibliotecaDb.setAttivoAccessoInternet(false);

					bibliotecaDb.setAccessoInternetTempo(false);
					bibliotecaDb.setAccessoInternetPagamento(false);
					bibliotecaDb.setAccessoInternetProxy(false);

				}

				log.debug("Modificata modalità di accesso Internet a tempo in: " + bibliotecaDb.getAccessoInternetTempo());
				log.debug("Modificata modalità di accesso Internet a pagamento in: " + bibliotecaDb.getAccessoInternetPagamento());
				log.debug("Modificata modalità di accesso Internet con proxy in: " + bibliotecaDb.getAccessoInternetProxy());

			}

		}

		if (biblioteca.getServizi() != null && biblioteca.getServizi().getInformazioniBibliografiche() != null) {
			if (biblioteca.getServizi().getInformazioniBibliografiche().getAttivo() == SiNoType.S) {
				bibliotecaDb.setAttivoInformazioniBibliografiche(true);
				log.debug("AttivoInformazioniBibliografiche(: " + true);

				/* Servizio in sede */
				if (biblioteca.getServizi().getInformazioniBibliografiche().getServizioInterno() != null 
						&& biblioteca.getServizi().getInformazioniBibliografiche().getServizioInterno() == SiNoType.S) {
					bibliotecaDb.setGestisceServizioBibliograficoInterno(true);
					log.debug("GestisceServizioBibliograficoInterno: " + true);

				} else if (biblioteca.getServizi().getInformazioniBibliografiche().getServizioInterno() != null 
						&& biblioteca.getServizi().getInformazioniBibliografiche().getServizioInterno() == SiNoType.N) {
					bibliotecaDb.setGestisceServizioBibliograficoInterno(false);
					log.debug("GestisceServizioBibliograficoInterno: " + false);

				} else {
					bibliotecaDb.setGestisceServizioBibliograficoInterno(null);
				}

				/* Servizio esterno */
				if (biblioteca.getServizi().getInformazioniBibliografiche().getServizioEsterno() != null) {
					for (int j = 0; j < biblioteca.getServizi().getInformazioniBibliografiche().getServizioEsterno().getModoCount(); j++) {
						if (j == 0) {
							bibliotecaDb.setGestisceServizioBibliograficoEsterno(true);
							log.debug("GestisceServizioBibliograficoEsterno: " + true);

							if (bibliotecaDb.getServiziInformazioniBibliograficheModalitas() != null 
									&& bibliotecaDb.getServiziInformazioniBibliograficheModalitas().size() > 0) {
								bibliotecaDb.getServiziInformazioniBibliograficheModalitas().clear();
								log.debug("Cancellati SERVIZI ESTERNI...");

							} else {
								bibliotecaDb.setServiziInformazioniBibliograficheModalitas(new ArrayList<ServiziInformazioniBibliograficheModalita>());
							}
						}

						String modo = biblioteca.getServizi().getInformazioniBibliografiche().getServizioEsterno().getModo(j);

						ServiziInformazioniBibliograficheModalita modalita = (ServiziInformazioniBibliograficheModalita) dynaTabDao.searchRecord(ServiziInformazioniBibliograficheModalita.class, modo);

						if (modalita != null) {
							bibliotecaDb.getServiziInformazioniBibliograficheModalitas().add(modalita);
							log.debug("Aggiunto SERVIZIO ESTERNO MODALITÀ: " + modo);

						} else {
							log.debug("SERVIZIO ESTERNO MODALITÀ non trovato: " + modo);
						}
					}

				} else {
					bibliotecaDb.setGestisceServizioBibliograficoEsterno(null);
					biblioDao.removeModalitaAccessoFromBiblio(bibliotecaDb);
				}

			} else if (biblioteca.getServizi().getInformazioniBibliografiche().getAttivo() == SiNoType.N) {
				bibliotecaDb.setAttivoInformazioniBibliografiche(false);
				log.debug("AttivoInformazioniBibliografiche(: " + false);

				/* Servizio in sede */
				bibliotecaDb.setGestisceServizioBibliograficoInterno(false);
				log.debug("GestisceServizioBibliograficoInterno: " + false);

				bibliotecaDb.setGestisceServizioBibliograficoEsterno(false);
				log.debug("GestisceServizioBibliograficoEsterno: " + false);

				if (bibliotecaDb.getServiziInformazioniBibliograficheModalitas() != null 
						&& bibliotecaDb.getServiziInformazioniBibliograficheModalitas().size() > 0) {
					bibliotecaDb.getServiziInformazioniBibliograficheModalitas().clear();
					log.debug("Cancellati SERVIZI ESTERNI...");				
				}
			}

			log.debug("Modificate informazioni SERVIZI INTERNI/ESTERNI...");
		}

		if (biblioteca.getCataloghi() != null) {
			/* CATALOGHI GENERALI */
			if (biblioteca.getCataloghi().getCataloghiGenerali() != null) {
				CataloghiGenerali cataloghiGenerali = biblioteca.getCataloghi().getCataloghiGenerali();

				for (int j = 0; j < cataloghiGenerali.getCatalogoGeneraleCount(); j++) {
					CatalogoGenerale cg = cataloghiGenerali.getCatalogoGenerale(j);
					if (j == 0) {
						if (bibliotecaDb.getPartecipaCataloghiGeneralis() != null) {
							List<PartecipaCataloghiGenerali> pcg = bibliotecaDb.getPartecipaCataloghiGeneralis();
							for (PartecipaCataloghiGenerali entry : pcg) {
								if (entry.getCataloghiGeneraliUrls() != null && entry.getCataloghiGeneraliUrls().size() > 0) {
									biblioDao.removeChilds(entry.getCataloghiGeneraliUrls());
								}
							}
							biblioDao.removeChilds(bibliotecaDb.getPartecipaCataloghiGeneralis());
							log.debug("Cancellati CATALOGHI GENERALI...");
						}
					}

					PartecipaCataloghiGenerali partecipaCataloghiGenerali = new PartecipaCataloghiGenerali();
					boolean isPersistable = true;
					Copertura c = cg.getCopertura();
					if (c != null) {
						if ((c.getDaAnno() != null) && (c.getDaAnno().trim().length() > 0)) {
							String daAnno = c.getDaAnno();
							partecipaCataloghiGenerali.setDaAnno(Integer.parseInt(daAnno));
						}
						if ((c.getAdAnno() != null) && (c.getAdAnno().trim().length() > 0)) {
							String aAnno = c.getAdAnno();
							partecipaCataloghiGenerali.setAAnno(Integer.parseInt(aAnno));
						}
					}
					if ((cg.getTipo() != null) && (cg.getTipo().trim().length() > 0)) {
						String tipoCatalogo = cg.getTipo();
						CatalogoGeneraleTipo catalogoGeneraleTipo = (CatalogoGeneraleTipo) dynaTabDao.searchRecord(CatalogoGeneraleTipo.class, tipoCatalogo);
						if (catalogoGeneraleTipo != null) {
							partecipaCataloghiGenerali.setCatalogoGeneraleTipo(catalogoGeneraleTipo);	

						} else {
							String msg = "Catalogo Generale Tipo non trovato:" + cg.getTipo();
							reportImport.addWarn(msg);
							log.warn(msg);
							isPersistable = false;
						}
					}  else {
						String msg = "Catalogo Generale Tipo non specificato";
						isPersistable = false;
						log.warn(msg);
					}

					CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = null;
					String supportoDigitale = null;
					Forme f = cg.getForme();
					if (f != null && f.getDigitale() != null) {
						if ((f.getDigitale().getSupporto() != null) && (f.getDigitale().getSupporto().trim().length() > 0)) {
							supportoDigitale = f.getDigitale().getSupporto().trim();
							cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
							partecipaCataloghiGenerali.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
						}
						if (f.getDigitale().getUrl() != null && f.getDigitale().getUrl().length > 0) {
							List<CataloghiGeneraliUrl> lcg = new ArrayList<CataloghiGeneraliUrl>();

							for (int iu = 0; iu < f.getDigitale().getUrl().length; iu++) {
								CataloghiGeneraliUrl cgurl = new CataloghiGeneraliUrl();
								cgurl.setUrl(f.getDigitale().getUrl(iu));
								cgurl.setPartecipaCataloghiGenerali(partecipaCataloghiGenerali);
								lcg.add(cgurl);
							}
							partecipaCataloghiGenerali.setCataloghiGeneraliUrls(lcg);
						}
						if ((f.getDigitale().getPercentuale() != null) && (f.getDigitale().getPercentuale().trim().length() > 0)) {
							int percentualeInformatizzata = Integer.parseInt(f.getDigitale().getPercentuale());
							partecipaCataloghiGenerali.setPercentualeInformatizzata(percentualeInformatizzata);
						}
					}
					if (supportoDigitale == null || cataloghiSupportoDigitaleTipo == null) {
						String msg = null;
						if (supportoDigitale == null) {
							msg = "Supporto digitale non specificato in partecipa a cataloghi generali, sarà settato a No";	
						} else {
							msg = "Supporto digitale non trovato in partecipa a cataloghi generali, sarà settato a No: " + supportoDigitale;	
						}

						reportImport.addWarn(msg);
						log.warn(msg);

						supportoDigitale = "No";
						cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
						partecipaCataloghiGenerali.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
					}

					if (f != null) {
						if (f.getMicroforme() != null) {
							partecipaCataloghiGenerali.setMicroforme(true);
							if ((f.getMicroforme().getPercentuale() != null) && (f.getMicroforme().getPercentuale().trim().length() > 0)) {
								int percentualeMicroforme = Integer.parseInt(f.getMicroforme().getPercentuale());
								partecipaCataloghiGenerali.setPercentualeMicroforme(percentualeMicroforme);

							} else {
								partecipaCataloghiGenerali.setPercentualeMicroforme(0);
							}

						} else {
							partecipaCataloghiGenerali.setMicroforme(false);
						}

						if (f.getSchede() != null) {
							partecipaCataloghiGenerali.setSchede(true);
							if ((f.getSchede().getPercentuale() != null) && (f.getSchede().getPercentuale().trim().length() > 0)) {
								int percentualeSchede = Integer.parseInt(f.getSchede().getPercentuale());
								partecipaCataloghiGenerali.setPercentualeSchede(percentualeSchede);

							} else {
								partecipaCataloghiGenerali.setPercentualeSchede(0);
							}

						} else {
							partecipaCataloghiGenerali.setSchede(false);
						}

						if (f.getVolume() != null) {
							partecipaCataloghiGenerali.setVolume(true);
							if ((f.getVolume().getPercentuale() != null) && (f.getVolume().getPercentuale().trim().length() > 0)) {
								int percentualeVolume = Integer.parseInt(f.getVolume().getPercentuale());
								partecipaCataloghiGenerali.setPercentualeVolume(percentualeVolume);

							} else {
								partecipaCataloghiGenerali.setPercentualeVolume(0);
							}

							if ((f.getVolume().getCitazioneBibliografica() != null) && (f.getVolume().getCitazioneBibliografica().trim().length() > 0)) {
								String descrizioneVolume = f.getVolume().getCitazioneBibliografica();
								partecipaCataloghiGenerali.setDescrizioneVolume(descrizioneVolume);
							}

						} else {
							partecipaCataloghiGenerali.setVolume(false);
						}

						if (isPersistable) {
							partecipaCataloghiGenerali.setBiblioteca(bibliotecaDb);
							biblioDao.saveChild(partecipaCataloghiGenerali);
							List<CataloghiGeneraliUrl> ltemp = (List<CataloghiGeneraliUrl>) partecipaCataloghiGenerali.getCataloghiGeneraliUrls();
							if (ltemp != null) {
								for (CataloghiGeneraliUrl entry : ltemp) {
									biblioDao.saveChild(entry);
								}
							}
							log.debug("Inserito nuovo CATALOGO GENERALE");

						} else {
							log.debug("Il CATALOGO GENERALE non è salvabile");
						}
					}
				}
			}

			/* CATALOGHI SPECIALI */
			if (biblioteca.getCataloghi().getCataloghiSpeciali() != null ) {
				CataloghiSpeciali cataloghiSpeciali = biblioteca.getCataloghi().getCataloghiSpeciali();

				for (int j = 0; j < cataloghiSpeciali.getCatalogoSpecialeCount(); j++) {
					CatalogoSpeciale cs = cataloghiSpeciali.getCatalogoSpeciale(j);

					if (cs != null && cs.getCatSpecMateriali() != null) {
						if (j == 0) {
							if (bibliotecaDb.getPartecipaCataloghiSpecialiMateriales() != null) {
								List<PartecipaCataloghiSpecialiMateriale> pcsm = bibliotecaDb.getPartecipaCataloghiSpecialiMateriales();
								for (PartecipaCataloghiSpecialiMateriale entry : pcsm) {
									if (entry.getCataloghiSpecialiMaterialeUrls() != null && entry.getCataloghiSpecialiMaterialeUrls().size() > 0) {
										biblioDao.removeChilds(entry.getCataloghiSpecialiMaterialeUrls());
									}
								}

								biblioDao.removeChilds(bibliotecaDb.getPartecipaCataloghiSpecialiMateriales());
								log.debug("Cancellati CATALOGHI SPECIALI...");
							}
						}

						PartecipaCataloghiSpecialiMateriale cataloghiSpecialiMateriale = new PartecipaCataloghiSpecialiMateriale();

						boolean isPersistable = true;

						CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = null;
						String supportoDigitale = null;
						
						CatSpecForme f = cs.getCatSpecMateriali().getCatSpecMateriale(0).getCatSpecForme();
						
						if (f != null && f.getCatSpecFormeDigitale() != null) {
							if ((f.getCatSpecFormeDigitale().getSupporto() != null) && (f.getCatSpecFormeDigitale().getSupporto().trim().length() > 0)) {
								supportoDigitale = f.getCatSpecFormeDigitale().getSupporto().trim();
								cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
								cataloghiSpecialiMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
							}
							if ((f.getCatSpecFormeDigitale().getPercentuale() != null) && (f.getCatSpecFormeDigitale().getPercentuale().trim().length() > 0)) {
								int percentualeInformatizzata = Integer.parseInt(f.getCatSpecFormeDigitale().getPercentuale());
								cataloghiSpecialiMateriale.setPercentualeInformatizzata(percentualeInformatizzata);
							}
						}
						if (supportoDigitale == null || cataloghiSupportoDigitaleTipo == null) {
							String msg = null;
							if (supportoDigitale == null) {
								msg = "Supporto digitale non specificato in partecipa a cataloghi speciali, sarà settato a No";
							} else {
								msg = "Supporto digitale non trovato in partecipa a cataloghi speciali, sarà settato a No: " + supportoDigitale;	
							}

							reportImport.addWarn(msg);
							log.warn(msg);

							supportoDigitale = "No";
							cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
							cataloghiSpecialiMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
						}

						if (f != null) {
							if (f.getCatSpecFormeMicroforme() != null) {
								cataloghiSpecialiMateriale.setMicroforme(true);
								if ((f.getCatSpecFormeMicroforme().getPercentuale() != null) && (f.getCatSpecFormeMicroforme().getPercentuale().trim().length() > 0)) {
									int percentualeMicroforme = Integer.parseInt(f.getCatSpecFormeMicroforme().getPercentuale());
									cataloghiSpecialiMateriale.setPercentualeMicroforme(percentualeMicroforme);

								} else {
									cataloghiSpecialiMateriale.setPercentualeMicroforme(0);
								}

							} else {
								cataloghiSpecialiMateriale.setMicroforme(false);
							}

							if (f.getCatSpecFormeSchede() != null) {
								cataloghiSpecialiMateriale.setSchede(true);
								if ((f.getCatSpecFormeSchede().getPercentuale() != null) && (f.getCatSpecFormeSchede().getPercentuale().trim().length() > 0)) {
									int percentualeSchede = Integer.parseInt(f.getCatSpecFormeSchede().getPercentuale());
									cataloghiSpecialiMateriale.setPercentualeSchede(percentualeSchede);

								} else {
									cataloghiSpecialiMateriale.setPercentualeSchede(0);
								}

							} else {
								cataloghiSpecialiMateriale.setSchede(false);
							}

							if (f.getCatSpecFormeVolume() != null) {
								cataloghiSpecialiMateriale.setVolume(true);
								if ((f.getCatSpecFormeVolume().getPercentuale() != null) && (f.getCatSpecFormeVolume().getPercentuale().trim().length() > 0)) {
									int percentualeVolume = Integer.parseInt(f.getCatSpecFormeVolume().getPercentuale());
									cataloghiSpecialiMateriale.setPercentualeVolume(percentualeVolume);

								} else {
									cataloghiSpecialiMateriale.setPercentualeVolume(0);
								}

								if ((f.getCatSpecFormeVolume().getCitazioneBibliografica() != null) && (f.getCatSpecFormeVolume().getCitazioneBibliografica().trim().length() > 0)) {
									String volumeDescr = f.getCatSpecFormeVolume().getCitazioneBibliografica();
									cataloghiSpecialiMateriale.setDescrizioneVolume(volumeDescr);
								}

							} else {
								cataloghiSpecialiMateriale.setVolume(false);
							}
						}


						CatSpecFormeCopertura fsp = cs.getCatSpecMateriali().getCatSpecMateriale(0).getCatSpecFormeCopertura();
						if (fsp != null) {
							if ((fsp.getDaAnno() != null) && (fsp.getDaAnno().trim().length() > 0)) {
								int daAnno = Integer.parseInt(fsp.getDaAnno());
								cataloghiSpecialiMateriale.setDaAnno(daAnno);
							}
							if ((fsp.getAdAnno() != null) && (fsp.getAdAnno().trim().length() > 0)) {
								int aAnno = Integer.parseInt(fsp.getAdAnno());
								cataloghiSpecialiMateriale.setAAnno(aAnno);
							}
						}
						if ((cs.getCatSpecMateriali().getCatSpecMaterialeCount() > 0) && (cs.getCatSpecMateriali().getCatSpecMateriale(0).getNome().trim().length() > 0)) {
							String materiale = cs.getCatSpecMateriali().getCatSpecMateriale(0).getNome();
							PatrimonioSpecializzazione patrimonioSpecializzazione = (PatrimonioSpecializzazione) 
							dynaTabDao.searchRecord(PatrimonioSpecializzazione.class, materiale);
							if (patrimonioSpecializzazione != null) {
								cataloghiSpecialiMateriale.setPatrimonioSpecializzazione(patrimonioSpecializzazione);

							} else {
								String msg = "Patrimonio specializzazione non trovato: " + materiale;
								reportImport.addWarn(msg);
								log.warn(msg);
								isPersistable = false;
							}

						}  else {
							String msg = "Patrimonio specializzazione non specificato";
							log.warn(msg);
							isPersistable = false;
						}
						if ((cs.getNome() != null) && (cs.getNome().trim().length() > 0)) {
							String denominazione = cs.getNome();
							cataloghiSpecialiMateriale.setDenominazione(denominazione);
							log.debug("Inserito nuovo CATALOGO SPECIALE denominazione: " + denominazione);
						}
						if (isPersistable) {
							cataloghiSpecialiMateriale.setBiblioteca(bibliotecaDb);
							biblioDao.saveChild(cataloghiSpecialiMateriale);
							log.debug("Inserito nuovo CATALOGO SPECIALE");

						} else {
							log.debug("Il catalogo speciale non è salvabile");
						}
						
					} // <---controllo getCatSpecMateriali != null
					
				}
			}

			/* CATALOGHI COLLETTIVI */
			if (biblioteca.getCataloghi().getCataloghiCollettivi() != null) {
				it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi cataloghiCollettivi = biblioteca.getCataloghi().getCataloghiCollettivi();

				for (int j = 0; j < cataloghiCollettivi.getCatalogoCollettivoCount(); j++) {
					CatalogoCollettivo cc = cataloghiCollettivi.getCatalogoCollettivo(j);

					if (cc != null && cc.getCatSpecMateriali() != null) {

						if (j == 0) {
							if (bibliotecaDb.getPartecipaCataloghiCollettiviMateriales() != null) {
								List<PartecipaCataloghiCollettiviMateriale> pccm = bibliotecaDb.getPartecipaCataloghiCollettiviMateriales();
								for (PartecipaCataloghiCollettiviMateriale entry : pccm) {
									if (entry.getCataloghiCollettiviMaterialeUrls() != null && entry.getCataloghiCollettiviMaterialeUrls().size() > 0) {
										biblioDao.removeChilds(entry.getCataloghiCollettiviMaterialeUrls());
									}
								}
								biblioDao.removeChilds(bibliotecaDb.getPartecipaCataloghiCollettiviMateriales());
								log.debug("Cancellati CATALOGHI COLLETTIVI...");
							}
						}

						boolean isPersistable = true;

						for (int k = 0; k < cc.getCatSpecMateriali().getCatSpecMaterialeCount(); k++) {
							PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale = new PartecipaCataloghiCollettiviMateriale();

							if ((cc.getNome() != null) && (cc.getNome().trim().length() > 0)) {
								String denominazione = cc.getNome();
								CataloghiCollettivi cataloghiCollettiviRecord = (CataloghiCollettivi) dynaTabDao.searchRecord(CataloghiCollettivi.class, denominazione);

								if (cataloghiCollettiviRecord != null) {
									partecipaCataloghiCollettiviMateriale.setCataloghiCollettivi(cataloghiCollettiviRecord);

								} else {
									String msg = "Partecipa cataloghi collettivi materiale non trovato:" + denominazione;
									reportImport.addWarn(msg);
									log.warn(msg);
									isPersistable = false;
								}

							} else {
								String msg = "Partecipa cataloghi collettivi materiale non specificato";
								log.warn(msg);
								isPersistable = false;
							}

							CatSpecMateriale materiale = cc.getCatSpecMateriali().getCatSpecMateriale(k);

							if (materiale != null && materiale.getNome().trim().length() > 0) {
								String materialeDescr = materiale.getNome();
								PatrimonioSpecializzazione patrimonioSpecializzazione = (PatrimonioSpecializzazione) 
								dynaTabDao.searchRecord(PatrimonioSpecializzazione.class, materialeDescr);

								if (patrimonioSpecializzazione != null) {
									partecipaCataloghiCollettiviMateriale.setPatrimonioSpecializzazione(patrimonioSpecializzazione);

								} else {
									String msg = "Patrimonio specializzazione non trovato:" + materiale;
									reportImport.addWarn(msg);
									log.warn(msg);
									isPersistable = false;
								}

							} else {
								String msg = "Patrimonio specializzazione non specificato";
								log.warn(msg);
								isPersistable = false;
							}

							CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo = null;
							String supportoDigitale = null;

							CatSpecForme f = materiale.getCatSpecForme();

							if (f != null && f.getCatSpecFormeDigitale() != null) {
								if ((f.getCatSpecFormeDigitale().getSupporto() != null) && (f.getCatSpecFormeDigitale().getSupporto().trim().length() > 0)) {
									supportoDigitale = f.getCatSpecFormeDigitale().getSupporto().trim();
									cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
									partecipaCataloghiCollettiviMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
								}
								if ((f.getCatSpecFormeDigitale().getPercentuale() != null) && (f.getCatSpecFormeDigitale().getPercentuale().trim().length() > 0)) {
									int percentualeInformatizzata = Integer.parseInt(f.getCatSpecFormeDigitale().getPercentuale());
									partecipaCataloghiCollettiviMateriale.setPercentualeInformatizzata(percentualeInformatizzata);
								}
							}

							if (supportoDigitale == null || cataloghiSupportoDigitaleTipo == null) {
								String msg = null;
								if (supportoDigitale == null) {
									msg = "Supporto digitale non specificato in partecipa a cataloghi collettivi, sarà settato a No";

								} else {
									msg = "Supporto digitale non trovato in partecipa a cataloghi collettivi, sarà settato a No: " + supportoDigitale;	
								}


								reportImport.addWarn(msg);
								log.warn(msg);

								supportoDigitale = "No";
								cataloghiSupportoDigitaleTipo = (CataloghiSupportoDigitaleTipo) dynaTabDao.searchRecord(CataloghiSupportoDigitaleTipo.class, supportoDigitale);
								partecipaCataloghiCollettiviMateriale.setCataloghiSupportoDigitaleTipo(cataloghiSupportoDigitaleTipo);
							}

							if (f != null) {
								if (f.getCatSpecFormeMicroforme() != null) {
									partecipaCataloghiCollettiviMateriale.setMicroforme(true);
									if ((f.getCatSpecFormeMicroforme().getPercentuale() != null) && (f.getCatSpecFormeMicroforme().getPercentuale().trim().length() > 0)) {
										int percentualeMicroforme = Integer.parseInt(f.getCatSpecFormeMicroforme().getPercentuale());
										partecipaCataloghiCollettiviMateriale.setPercentualeMicroforme(percentualeMicroforme);

									} else {
										partecipaCataloghiCollettiviMateriale.setPercentualeMicroforme(0);
									}

								} else {
									partecipaCataloghiCollettiviMateriale.setMicroforme(false);
								}

								if (f.getCatSpecFormeSchede() != null) {
									partecipaCataloghiCollettiviMateriale.setSchede(true);
									if ((f.getCatSpecFormeSchede().getPercentuale() != null) && (f.getCatSpecFormeSchede().getPercentuale().trim().length() > 0)) {
										int percentualeSchede = Integer.parseInt(f.getCatSpecFormeSchede().getPercentuale());
										partecipaCataloghiCollettiviMateriale.setPercentualeSchede(percentualeSchede);

									} else {
										partecipaCataloghiCollettiviMateriale.setPercentualeSchede(0);
									}

								} else {
									partecipaCataloghiCollettiviMateriale.setSchede(false);
								}

								if (f.getCatSpecFormeVolume() != null) {
									partecipaCataloghiCollettiviMateriale.setVolume(true);
									if ((f.getCatSpecFormeVolume().getPercentuale() != null) && (f.getCatSpecFormeVolume().getPercentuale().trim().length() > 0)) {
										int percentualeVolume = Integer.parseInt(f.getCatSpecFormeVolume().getPercentuale());
										partecipaCataloghiCollettiviMateriale.setPercentualeVolume(percentualeVolume);

									} else {
										partecipaCataloghiCollettiviMateriale.setPercentualeVolume(0);
									}

									if ((f.getCatSpecFormeVolume().getCitazioneBibliografica() != null) && (f.getCatSpecFormeVolume().getCitazioneBibliografica().trim().length() > 0)) {
										String descrizioneVolume = f.getCatSpecFormeVolume().getCitazioneBibliografica();
										partecipaCataloghiCollettiviMateriale.setDescrizioneVolume(descrizioneVolume);
									}

								} else {
									partecipaCataloghiCollettiviMateriale.setVolume(false);
								}
							}

							CatSpecFormeCopertura fsp = materiale.getCatSpecFormeCopertura();
							if (fsp != null) {
								if ((fsp.getDaAnno() != null) && (fsp.getDaAnno().trim().length() > 0)) {
									int daAnno = Integer.parseInt(fsp.getDaAnno());
									partecipaCataloghiCollettiviMateriale.setDaAnno(daAnno);
								}

								if ((fsp.getAdAnno() != null) && (fsp.getAdAnno().trim().length() > 0)) {
									int aAnno = Integer.parseInt(fsp.getAdAnno());
									partecipaCataloghiCollettiviMateriale.setAAnno(aAnno);
								}
							}

							if (isPersistable) {
								partecipaCataloghiCollettiviMateriale.setBiblioteca(bibliotecaDb);
								biblioDao.saveChild(partecipaCataloghiCollettiviMateriale);
								log.debug("PARTECIPA CATALOGHI COLLETTIVI MATERIALE salvato");

							} else {
								log.debug("PARTECIPA CATALOGHI COLLETTIVI MATERIALE non è salvabile");
							}
						}
						
					} // <---controllo getCatSpecMateriali != null
				}
			}
		}

		// SEZIONI SPECIALI
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getSezioniSpeciali() != null && biblioteca.getServizi().getSezioniSpeciali().getSezione() != null) {
			String[] sezioniSp = biblioteca.getServizi().getSezioniSpeciali().getSezione();
			for (int j = 0; j < sezioniSp.length; j++) {
				if (j == 0) {
					if (bibliotecaDb.getSezioniSpecialis() != null) {
						bibliotecaDb.getSezioniSpecialis().clear();
						log.debug("Cancellate SEZIONI SPECIALI...");

					} else {
						bibliotecaDb.setSezioniSpecialis(new ArrayList<SezioniSpeciali>());
					}
				}
				if ((sezioniSp[j] != null) && (sezioniSp[j].trim().length() > 0)) {
					String sezione = sezioniSp[j];
					SezioniSpeciali sezioniSpeciali = (SezioniSpeciali) dynaTabDao.searchRecord(SezioniSpeciali.class, sezione);

					if (sezioniSpeciali != null) {
						if (!bibliotecaDb.getSezioniSpecialis().contains(sezioniSpeciali)) {
							bibliotecaDb.getSezioniSpecialis().add(sezioniSpeciali);

						} else {
							String msg = "Sezioni Speciali duplicato: " + sezione;
							reportImport.addWarn(msg);
							log.warn(msg);
						}

					} else {
						String msg = "Sezioni Speciali non trovata:" + sezione;
						reportImport.addWarn(msg);
						log.warn(msg);
					}

					log.debug("Inserita SEZIONE SPECIALE: " + sezione);
				}
			}
		}

		/* Riproduzioni */
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getRiproduzioni() != null) {
			if (biblioteca.getServizi().getRiproduzioni().getAttivo() == SiNoType.S) {
				bibliotecaDb.setAttivoRiproduzioni(true);

				it.inera.abi.logic.formatodiscambio.castor.Riproduzione[] riprods = biblioteca.getServizi().getRiproduzioni().getRiproduzione();

				for (int j = 0; j < riprods.length; j++) {
					if (j == 0) {
						biblioDao.removeRiproduzioniFromBiblio(bibliotecaDb);
						log.debug("Cancellati TUTTI METODI DI RIPRODUZIONE...");
					}

					if ((riprods[j] != null) && (riprods[j].getTipo().trim().length() > 0)) {
						String tipo = riprods[j].getTipo();
						RiproduzioniTipo riproduzioniTipo = (RiproduzioniTipo) dynaTabDao.searchRecord(RiproduzioniTipo.class, tipo);

						if (tipo != null) {
							RiproduzioniPK riproduzioniPK = new RiproduzioniPK();
							riproduzioniPK.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
							riproduzioniPK.setIdRiproduzioniTipo(riproduzioniTipo.getIdRiproduzioniTipo());

							Riproduzioni riproduzione = new Riproduzioni();
							riproduzione.setId(riproduzioniPK);
							log.debug("TIPO DI RIPRODUZIONE: " + tipo);

							if (riprods[j].getLocale() != null) {
								if (riprods[j].getLocale() == SiNoType.S) {
									riproduzione.setLocale(true);

								} else if (riprods[j].getLocale() == SiNoType.N) {
									riproduzione.setLocale(false);

								}
							}

							if (riprods[j].getNazionale() != null) {
								if (riprods[j].getNazionale() == SiNoType.S) {
									riproduzione.setNazionale(true);

								} else if (riprods[j].getNazionale() == SiNoType.N) {
									riproduzione.setNazionale(false);

								}
							}

							if (riprods[j].getInternazionale() != null) {
								if (riprods[j].getInternazionale() == SiNoType.S) {
									riproduzione.setInternazionale(true);

								} else if (riprods[j].getInternazionale() == SiNoType.N) {
									riproduzione.setInternazionale(false);

								}
							}
							biblioDao.saveChild(riproduzione);
							log.debug("Inserito METODO DI RIPRODUZIONE");

						} else {
							String msg = "Riproduzioni Tipo non trovato: " + tipo;
							reportImport.addWarn(msg);
							log.warn(msg);
						}

					}
				}

			} else {
				bibliotecaDb.setAttivoRiproduzioni(false);

				if (bibliotecaDb.getRiproduzionis() != null && bibliotecaDb.getRiproduzionis().size() > 0) {
					bibliotecaDb.setRiproduzionis(new ArrayList<Riproduzioni>());

					log.debug("Cancellati TUTTI METODI DI RIPRODUZIONE...");
				}
			}

		}

		//PRESTITO
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getPrestito() != null) {
			if (biblioteca.getServizi().getPrestito().getInterbibliotecario() != null) {
				Interbibliotecario interbb = biblioteca.getServizi().getPrestito().getInterbibliotecario();
				//Tipi di prestito internazionale
				for (int j = 0; j < interbb.getTipoPrestitoCount(); j++) {
					TipoPrestito tinterbb  = interbb.getTipoPrestito(j);
					if (j == 0) {
						if (bibliotecaDb.getPrestitoInterbibliotecarios() != null) {
							List<PrestitoInterbibliotecario> tmpPrestList = new ArrayList<PrestitoInterbibliotecario>(bibliotecaDb.getPrestitoInterbibliotecarios());
							bibliotecaDb.getPrestitoInterbibliotecarios().clear();
							biblioDao.removePrestitoInterbibliotecarioNotUsedByOtherBibs(tmpPrestList, bibliotecaDb.getIdBiblioteca().intValue());
							log.debug("Cancellazione PRESTITI INTERBIBLIOTECARI...");

						} else {
							bibliotecaDb.setPrestitoInterbibliotecarios(new ArrayList<PrestitoInterbibliotecario>());
						}
					}
					if (tinterbb != null) {
						boolean persistable = true;
						PrestitoInterbibliotecario interbibliotecario = new PrestitoInterbibliotecario();
						interbibliotecario.setInternazionale((tinterbb.getInternazionale() == SiNoType.S));
						interbibliotecario.setNazionale(tinterbb.getNazionale() == SiNoType.S);

						if (tinterbb.getRuolo() == RuoloType.POS) {
							PrestitoInterbibliotecarioModo modo = (PrestitoInterbibliotecarioModo) dynaTabDao.loadRecord(PrestitoInterbibliotecarioModo.class, 2);
							interbibliotecario.setPrestitoInterbibliotecarioModo(modo);
							log.debug("Inserito nuovo MODO PRESTITO: " + tinterbb.getRuolo().toString());

						} else if (tinterbb.getRuolo() == RuoloType.DSC) {
							PrestitoInterbibliotecarioModo modo = (PrestitoInterbibliotecarioModo) dynaTabDao.loadRecord(PrestitoInterbibliotecarioModo.class, 1);
							interbibliotecario.setPrestitoInterbibliotecarioModo(modo);
							log.debug("Inserito nuovo MODO PRESTITO: " + tinterbb.getRuolo().toString());

						} else {
							String msg = "Ruolo prestito interbibliotecario non trovato:" + tinterbb.getRuolo().toString();
							reportImport.addWarn(msg);
							log.warn(msg);
							persistable = false;
						}

						if (persistable) {
							biblioDao.saveChild(interbibliotecario);
							bibliotecaDb.getPrestitoInterbibliotecarios().add(interbibliotecario);
							log.debug("Salvato prestito interbibliotecario");
						}
					}
				}

				//Automatizzato
				if (interbb.getPrestitoInterbibliotecarioAutomatizzato() == SiNoType.S) {
					bibliotecaDb.setProcedureIllAutomatizzate(true);
					log.debug("ProcedureIllAutomatizzate: " + true);

				} else if (interbb.getPrestitoInterbibliotecarioAutomatizzato() == SiNoType.N) {
					bibliotecaDb.setProcedureIllAutomatizzate(false);
					log.debug("ProcedureIllAutomatizzate: " + false);
				}
				log.debug("Modificato prestito INTERBIBLIOTECARIO AUTOMATIZZATO...");

				//Sistema ILL
				for (int j = 0; j < biblioteca.getServizi().getPrestito().getInterbibliotecario().getSistemaIllCount(); j++) {
					if (j == 0) {
						if (bibliotecaDb.getSistemiPrestitoInterbibliotecarios() != null) {
							bibliotecaDb.getSistemiPrestitoInterbibliotecarios().clear();
							log.debug("Cancellati SISTEMI ILL...");

						} else {
							bibliotecaDb.setSistemiPrestitoInterbibliotecarios(new ArrayList<SistemiPrestitoInterbibliotecario>());
						}
					}
					if ((biblioteca.getServizi().getPrestito().getInterbibliotecario().getSistemaIll(j).getNome() != null) &&
							(biblioteca.getServizi().getPrestito().getInterbibliotecario().getSistemaIll(j).getNome().trim().length() > 0)) {
						String nomeSistema = biblioteca.getServizi().getPrestito().getInterbibliotecario().getSistemaIll(j).getNome().trim();
						SistemiPrestitoInterbibliotecario sistema = (SistemiPrestitoInterbibliotecario) dynaTabDao.searchRecord(SistemiPrestitoInterbibliotecario.class, nomeSistema);
						if (sistema != null) {
							if (!bibliotecaDb.getSistemiPrestitoInterbibliotecarios().contains(sistema)) {
								bibliotecaDb.getSistemiPrestitoInterbibliotecarios().add(sistema);

							} else {
								String msg = "Sistemi Prestito Interbibliotecario duplicato: " + nomeSistema;
								reportImport.addWarn(msg);
								log.warn(msg);
							}

						} else {
							String msg = "Sistema prestito interbibliotecario non trovato:" + nomeSistema;
							reportImport.addWarn(msg);
							log.warn(msg);
						}
						log.debug("Inserito SISTEMA ILL...");
					}
				}

				//Totale prestiti interbibliotecari
				if (biblioteca.getServizi().getPrestito().getInterbibliotecario().getTotalePrestiti() != null) {
					int nPrestitiInterbibliotecariAnnuo = biblioteca.getServizi().getPrestito().getInterbibliotecario().getTotalePrestiti().intValue();
					bibliotecaDb.setNPrestitiInterbibliotecariAnnuo(nPrestitiInterbibliotecariAnnuo);
					log.debug("Modificato TOTALE PRESTITI INTERBIBLIOTECARI: " + nPrestitiInterbibliotecariAnnuo);
				}
			}


			/* Prestito Locale */
			if (biblioteca.getServizi().getPrestito().getLocale() != null) {
				if (bibliotecaDb.getPrestitoLocales() != null) {
					biblioDao.removePrestitoLocale(bibliotecaDb);
					log.debug("Cancellato PRESTITO LOCALE...");
				}

				if (biblioteca.getServizi().getPrestito().getLocale().getAttivo() != null 
						&& biblioteca.getServizi().getPrestito().getLocale().getAttivo() == SiNoType.S) {
					bibliotecaDb.setAttivoPrestitoLocale(true);
					log.debug("AttivoPrestitoLocale: " + true);

					if (biblioteca.getServizi().getPrestito().getLocale().getTotalePrestiti() != null) {
						int nPrestitiLocaliAnnuo = biblioteca.getServizi().getPrestito().getLocale().getTotalePrestiti().intValue();
						bibliotecaDb.setNPrestitiLocaliAnnuo(nPrestitiLocaliAnnuo);
						log.debug("Modificato TOTALE PRESTITI LOCALI: " + nPrestitiLocaliAnnuo);
					}

					PrestitoLocale prestitoLocale = new PrestitoLocale();
					prestitoLocale.setPrestitoLocaleMaterialeEscluso(new ArrayList<PrestitoLocaleMaterialeEscluso>());
					prestitoLocale.setPrestitoLocaleUtentiAmmessis(new ArrayList<PrestitoLocaleUtentiAmmessi>());

					/* Automatizzato */
					if (biblioteca.getServizi().getPrestito().getLocale().getPrestitoLocaleAutomatizzato() == SiNoType.S) {
						prestitoLocale.setAutomatizzato(true);
						log.debug("Modificato PRESTITO LOCALE AUTOMATIZZATO: " + true);

					} else if (biblioteca.getServizi().getPrestito().getLocale().getPrestitoLocaleAutomatizzato() == SiNoType.N) {
						prestitoLocale.setAutomatizzato(false);
						log.debug("Modificato PRESTITO LOCALE AUTOMATIZZATO: " + false);
					}

					/* Durata */
					if (biblioteca.getServizi().getPrestito().getLocale().getDurata() != null) {
						int durataGiorni = Integer.valueOf(biblioteca.getServizi().getPrestito().getLocale().getDurata());
						prestitoLocale.setDurataGiorni(durataGiorni);
						log.debug("Modificato DURATA: " + durataGiorni);
					}

					/* Materiali esclusi */
					if (biblioteca.getServizi().getPrestito().getLocale().getMaterialiEsclusi() != null) {
						MaterialiEsclusi materialiEsclusi = biblioteca.getServizi().getPrestito().getLocale().getMaterialiEsclusi();

						for (int j = 0; j < materialiEsclusi.getMaterialeEsclusoCount(); j++) {
							String materiale = materialiEsclusi.getMaterialeEscluso(j);
							if (materiale != null && materiale.trim().length() > 0) {
								PrestitoLocaleMaterialeEscluso escluso = (PrestitoLocaleMaterialeEscluso) dynaTabDao.searchRecord(PrestitoLocaleMaterialeEscluso.class, materiale.trim());
								if (escluso != null) {
									if (!prestitoLocale.getPrestitoLocaleMaterialeEscluso().contains(escluso)) {
										prestitoLocale.getPrestitoLocaleMaterialeEscluso().add(escluso);
										log.debug("Inserito MATERIALE ESCLUSO DAL PRESTITO: " + escluso);

									} else {
										String msg = "Prestito Locale Materiale Escluso duplicato:" + materiale;
										reportImport.addWarn(msg);
										log.warn(msg);
									}

								} else {
									String msg = "Prestito Locale Materiale Escluso non trovato:" + materiale;
									reportImport.addWarn(msg);
									log.warn(msg);
								}
							}
						}
					}

					/* Utenti Ammessi */
					if (biblioteca.getServizi().getPrestito().getLocale().getUtentiAmmessi() != null) {
						for (int i = 0; i < biblioteca.getServizi().getPrestito().getLocale().getUtentiAmmessi().length; i++) {
							String utentiAmmessi = biblioteca.getServizi().getPrestito().getLocale().getUtentiAmmessi(i);
							PrestitoLocaleUtentiAmmessi ammessi = (PrestitoLocaleUtentiAmmessi) dynaTabDao.searchRecord(PrestitoLocaleUtentiAmmessi.class, utentiAmmessi);
							if (ammessi != null) {
								if (!prestitoLocale.getPrestitoLocaleUtentiAmmessis().contains(ammessi)) {
									prestitoLocale.getPrestitoLocaleUtentiAmmessis().add(ammessi);	
									log.debug("Inserito UTENTI AMMESSI PRESTITO:LOCALE " + utentiAmmessi);

								} else {
									String msg = "Prestito Locale Utenti Ammessi duplicato:" + utentiAmmessi;
									reportImport.addWarn(msg);
									log.warn(msg);
								}

							} else {
								String msg = "Prestito Locale Utenti Ammessi non trovato: " + utentiAmmessi;
								reportImport.addWarn(msg);
								log.warn(msg);
							}

						}
					}

					prestitoLocale.setBiblioteca(bibliotecaDb);
					biblioDao.saveChild(prestitoLocale);

				} else if (biblioteca.getServizi().getPrestito().getLocale().getAttivo() != null 
						&& biblioteca.getServizi().getPrestito().getLocale().getAttivo() == SiNoType.N) {
					bibliotecaDb.setAttivoPrestitoLocale(false);
					log.debug("AttivoPrestitoLocale: " + false);
				}

				log.debug("Modificato PRESTITO LOCALE...");
			}			
		}


		//BILANCIO
		if (biblioteca.getAmministrativa() != null && biblioteca.getAmministrativa().getBilancio() != null) {
			if (biblioteca.getAmministrativa().getBilancio().getEntrate() != null) {
				int bilancioEntrate = biblioteca.getAmministrativa().getBilancio().getEntrate().intValue();
				bibliotecaDb.setBilancioEntrate(bilancioEntrate);
				log.debug("Modificati dati di bilancioEntrate: " + bilancioEntrate);

			} else {
				bibliotecaDb.setBilancioEntrate(0);
			}

			if (biblioteca.getAmministrativa().getBilancio().getUscite() != null) {
				if (biblioteca.getAmministrativa().getBilancio().getUscite().getTotale() != null) {
					int bilancioUscite = biblioteca.getAmministrativa().getBilancio().getUscite().getTotale().intValue();
					bibliotecaDb.setBilancioUscite(bilancioUscite);
					log.debug("Modificati dati di bilancioUscite: " + bilancioUscite);

				} else {
					bibliotecaDb.setBilancioUscite(0);
				}

				if (biblioteca.getAmministrativa().getBilancio().getUscite().getPersonale() != null) {
					int bilancioUscitePersonale = biblioteca.getAmministrativa().getBilancio().getUscite().getPersonale().intValue();
					bibliotecaDb.setBilancioUscitePersonale(bilancioUscitePersonale);
					log.debug("Modificati dati di bilancioUscitePersonale: " + bilancioUscitePersonale);

				} else {
					bibliotecaDb.setBilancioUscitePersonale(0);
				}

				if (biblioteca.getAmministrativa().getBilancio().getUscite().getFunzionamento() != null) {
					int bilancioUsciteFunzionamento = biblioteca.getAmministrativa().getBilancio().getUscite().getFunzionamento().intValue();
					bibliotecaDb.setBilancioUsciteFunzionamento(bilancioUsciteFunzionamento);
					log.debug("Modificati dati di bilancioUsciteFunzionamento: " + bilancioUsciteFunzionamento);

				} else {
					bibliotecaDb.setBilancioUsciteFunzionamento(0);
				}

				if (biblioteca.getAmministrativa().getBilancio().getUscite().getPatrimonio() != null) {
					int bilancioUsciteAcquistiAnno = biblioteca.getAmministrativa().getBilancio().getUscite().getPatrimonio().intValue();
					bibliotecaDb.setBilancioUsciteAcquistiAnno(bilancioUsciteAcquistiAnno);
					log.debug("Modificati dati di bilancioUsciteAcquistiAnno: " + bilancioUsciteAcquistiAnno);
				}

				if (biblioteca.getAmministrativa().getBilancio().getUscite().getAltre() != null) {
					int bilancioUsciteVarie = biblioteca.getAmministrativa().getBilancio().getUscite().getAltre().intValue();
					bibliotecaDb.setBilancioUsciteVarie(bilancioUsciteVarie);
					log.debug("Modificati dati di bilancioUsciteVarie: " + bilancioUsciteVarie);
				}

				if (biblioteca.getAmministrativa().getBilancio().getUscite().getAutomazione() != null) {
					int bilancioUsciteAutomazione = biblioteca.getAmministrativa().getBilancio().getUscite().getAutomazione().intValue();
					bibliotecaDb.setBilancioUsciteAutomazione(bilancioUsciteAutomazione);
					log.debug("Modificati dati di bilancioUsciteAutomazione: " + bilancioUsciteAutomazione);
				}

			} else {
				bibliotecaDb.setBilancioUscite(0);
				bibliotecaDb.setBilancioUscitePersonale(0);
				bibliotecaDb.setBilancioUscitePersonale(0);
				bibliotecaDb.setBilancioUsciteAcquistiAnno(0);
				bibliotecaDb.setBilancioUsciteVarie(0);
				bibliotecaDb.setBilancioUsciteAutomazione(0);
				log.debug("Modificati dati di bilancio uscite a 0");
			}

		}

		/* DEPOSITI LEGALI */
		if (biblioteca.getAmministrativa().getDepositiLegali() != null) {
			if (biblioteca.getAmministrativa().getDepositiLegali().getAttivo() == SiNoType.S) {
				bibliotecaDb.setAttivoDepositoLegale(true);
				log.debug("AttivoDepositoLegale: " + true);

				for (int j = 0; j < biblioteca.getAmministrativa().getDepositiLegali().getDepositoLegaleCount(); j++) {
					DepositoLegale dl = (DepositoLegale) biblioteca.getAmministrativa().getDepositiLegali().getDepositoLegale(j);
					if (j == 0) {
						if (bibliotecaDb.getDepositiLegalis() != null) {
							biblioDao.removeChilds(bibliotecaDb.getDepositiLegalis());
							log.debug("Cancellati DEPOSITI LEGALI...");
						}
					}
					String tipo = dl.getTipo();
					String annoInizio = dl.getAnnoInizio();
					DepositiLegaliTipo depositiLegaliTipo = (DepositiLegaliTipo) dynaTabDao.searchRecord(DepositiLegaliTipo.class, tipo);

					if (depositiLegaliTipo != null) {
						DepositiLegaliPK id = new DepositiLegaliPK();
						id.setIdBiblioteca(bibliotecaDb.getIdBiblioteca());
						id.setIdDepositiLegaliTipo(depositiLegaliTipo.getIdDepositiLegaliTipo());

						DepositiLegali depositiLegali = new DepositiLegali();
						depositiLegali.setDaAnno(annoInizio);
						depositiLegali.setDepositiLegaliTipo(depositiLegaliTipo);
						depositiLegali.setId(id);
						biblioDao.saveChild(depositiLegali);
						log.debug("Inserito DEPOSITO LEGALE tipo: " + dl.getTipo() + " daAnno: " + annoInizio);

					} else {
						String msg = "Depositi Legali non trovato: " + tipo;
						reportImport.addWarn(msg);
						log.warn(msg);
					}
				}

			} else {
				bibliotecaDb.setAttivoDepositoLegale(false);
				log.debug("AttivoDepositoLegale: " + false);

				if (bibliotecaDb.getDepositiLegalis() != null && bibliotecaDb.getDepositiLegalis().size() > 0) {
					biblioDao.removeChilds(bibliotecaDb.getDepositiLegalis());
					log.debug("Cancellati DEPOSITI LEGALI...");
				}
			}
		}

		// PERSONALE
		if (biblioteca.getAmministrativa().getPersonale() != null) {
			bibliotecaDb.setPersonaleEsterno((int) biblioteca.getAmministrativa().getPersonale().getEsterno());
			bibliotecaDb.setPersonalePartTime((int) biblioteca.getAmministrativa().getPersonale().getPartTime());
			bibliotecaDb.setPersonaleTemporaneo((int) biblioteca.getAmministrativa().getPersonale().getTemporaneo());
			bibliotecaDb.setPersonaleTotale((int) biblioteca.getAmministrativa().getPersonale().getTotale());
			log.debug("Modificati dati PERSONALE Esterno:" + biblioteca.getAmministrativa().getPersonale().getEsterno());
			log.debug("Modificati dati PERSONALE PartTime:" + biblioteca.getAmministrativa().getPersonale().getPartTime());
			log.debug("Modificati dati PERSONALE Temporaneo:" + biblioteca.getAmministrativa().getPersonale().getTemporaneo());
			log.debug("Modificati dati PERSONALE Totale:" + biblioteca.getAmministrativa().getPersonale().getTotale());
		}

		/* REFERENCE */
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getReference() != null) {
			if (biblioteca.getServizi().getReference().getAttivo() == SiNoType.S) {
				bibliotecaDb.setAttivoReference(true);
				log.debug("AttivoReference(: " + true);

				/* Reference locale */
				if (biblioteca.getServizi().getReference().getLocale() != null 
						&& biblioteca.getServizi().getReference().getLocale() == SiNoType.S) {
					bibliotecaDb.setReferenceLocale(true);
					log.debug("ReferenceLocale: " + true);

				} else if (biblioteca.getServizi().getReference().getLocale() != null 
						&& biblioteca.getServizi().getReference().getLocale() == SiNoType.N) {
					bibliotecaDb.setReferenceLocale(false);
					log.debug("ReferenceLocale: " + false);

				} else {
					bibliotecaDb.setReferenceLocale(null);
				}

				/* Reference online */
				if (biblioteca.getServizi().getReference().getOnline() != null
						&& biblioteca.getServizi().getReference().getOnline() == SiNoType.S) {
					bibliotecaDb.setReferenceOnline(true);
					log.debug("ReferenceOnline: " + true);

				} else if (biblioteca.getServizi().getReference().getOnline() != null 
						&& biblioteca.getServizi().getReference().getOnline() == SiNoType.N) {
					bibliotecaDb.setReferenceOnline(false);
					log.debug("ReferenceOnline: " + false);

				} else {
					bibliotecaDb.setReferenceOnline(null);
				}

			} else if (biblioteca.getServizi().getReference().getAttivo() == SiNoType.N) {
				bibliotecaDb.setAttivoReference(false);
				log.debug("AttivoReference: " + false);

				/* Reference locale */
				bibliotecaDb.setReferenceLocale(false);
				log.debug("ReferenceLocale: " + false);

				/* Reference online */
				bibliotecaDb.setReferenceOnline(false);
				log.debug("ReferenceOnline: " + false);

			}

			log.debug("Modificate informazioni SERVIZI REFERENCE...");
		}

		/* DOCUMENT DELIVERY */
		if (biblioteca.getServizi() != null && biblioteca.getServizi().getDocumentDelivery() != null) {
			if (biblioteca.getServizi().getDocumentDelivery().getAttivo() == SiNoType.S) {
				bibliotecaDb.setAttivoDocumentDelivery(true);
				log.debug("AttivoDocumentDelivery: " + true);

				/* Tipologie */
				if (biblioteca.getServizi().getDocumentDelivery().getDocDelForme() != null) {
					for (int i = 0; i < biblioteca.getServizi().getDocumentDelivery().getDocDelForme().getFormaCount(); i++) {
						if (i == 0) {
							if (bibliotecaDb.getDocumentDeliveries() != null) {
								bibliotecaDb.getDocumentDeliveries().clear();
								log.debug("CANCELLATE Document Delivery...");

							} else {
								bibliotecaDb.setDocumentDeliveries(new ArrayList<RiproduzioniTipo>());
							}
						}

						String forma = (String) biblioteca.getServizi().getDocumentDelivery().getDocDelForme().getForma(i);


						if (forma != null && forma.length() > 0) {
							RiproduzioniTipo riproTipo = (RiproduzioniTipo) dynaTabDao.searchRecord(RiproduzioniTipo.class, forma);

							if (riproTipo != null) {
								if (!bibliotecaDb.getDocumentDeliveries().contains(riproTipo)) {
									bibliotecaDb.getDocumentDeliveries().add(riproTipo);

								} else {
									String msg = "Document Delivery duplicata: " + forma;
									reportImport.addWarn(msg);
									log.warn(msg);
								}

							} else {
								String msg = "Document Delivery non trovata:" + forma;
								reportImport.addWarn(msg);
								log.warn(msg);
							}

							log.debug("Inserita DOCUMENT DELIVERY: " + forma);
						}

					}

				}

			} else if (biblioteca.getServizi().getDocumentDelivery().getAttivo() == SiNoType.N) {
				bibliotecaDb.setAttivoDocumentDelivery(false);
				log.debug("AttivoDocumentDelivery: " + false);

				if (bibliotecaDb.getDocumentDeliveries() != null && bibliotecaDb.getDocumentDeliveries().size() > 0) {
					bibliotecaDb.getDocumentDeliveries().clear();
					log.debug("CANCELLATE Document Delivery...");
				}
			}

			log.debug("Modificate informazioni SERVIZI DOCUMENT DELIVERY...");
		}

		log.info("Mapping terminato");

	}

	/**
	 * Crea la nuova biblioteca sul database con i parametri passati per argomento
	 * verificando che vengano valorizzati i valori obbligatori sul database
	 * 
	 * @param biblioteca
	 * @param isilSt
	 * @param isilNr
	 * @param isilPr
	 * @param reportImport
	 * @return id della biblioteca creata, null se la procedura fallisce
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private Integer createBibliotecaDefault(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca, String isilSt, String isilNr, String isilPr, ReportImport reportImport) {
		Biblioteca nuovaBiblio = new Biblioteca();

		//CODICI
		nuovaBiblio.setIsilStato(isilSt);
		nuovaBiblio.setIsilNumero(Integer.valueOf(isilNr));
		nuovaBiblio.setIsilProvincia(isilPr);

		//DENOMINAZIONE
		boolean denominazioneUfficialeIsSet = setDenominazioneUfficiale(biblioteca, reportImport, nuovaBiblio, true);

		//COMUNE
		boolean comuneIsSet = setComune(biblioteca, reportImport, nuovaBiblio);

		//FONDI ANTICHI
		setFondiAntichiConsistenza(biblioteca, reportImport, nuovaBiblio);

		//ENTE e TIPOLOGIA FUNZIONALE			
		boolean enteIsSet = setEnte(biblioteca, reportImport, nuovaBiblio);		

		//STATO BIBLIOTECA WORKFLOW
		StatoBibliotecaWorkflow statoBibliotecaWorkflow =  (StatoBibliotecaWorkflow) dynaTabDao.loadRecord(StatoBibliotecaWorkflow.class, 3);
		nuovaBiblio.setStatoBibliotecaWorkflow(statoBibliotecaWorkflow);

		if (denominazioneUfficialeIsSet && comuneIsSet && enteIsSet) {
			biblioDao.saveBiblioteca(nuovaBiblio);
			return nuovaBiblio.getIdBiblioteca();
		} else {
			return null;
		}
	}


	/**
	 * @param biblioteca
	 * @param reportImport
	 * @param bibliotecaDb
	 * @param regionale 
	 */
	private boolean setDenominazioneUfficiale(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca,
			ReportImport reportImport, Biblioteca bibliotecaDb, boolean regionale) {
		// Denominazione ufficiale
		if (biblioteca.getAnagrafica().getNomi() != null && biblioteca.getAnagrafica().getNomi().getAttuale() != null) {
			String denominazioneUfficiale = biblioteca.getAnagrafica().getNomi().getAttuale();
			//Modifica la denominazione pre-esistente
			bibliotecaDb.setDenominazioneUfficiale(denominazioneUfficiale);
			log.debug("Modificata DENOMINAZIONE UFFICIALE:" + denominazioneUfficiale);
			return true;
		} else {
			String msg = "Denominazione ufficiale nulla";
			if (regionale){
				reportImport.addError(msg);
			} else {
				reportImport.addWarn(msg);
			}
			log.warn(msg);

			return false;
		}
	}

	/**
	 * @param biblioteca
	 * @param reportImport
	 * @param bibliotecaDb
	 */
	@Transactional
	private void setFondiAntichiConsistenza(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca, ReportImport reportImport, Biblioteca bibliotecaDb) {
		//Fondi antichi
		if (biblioteca.getPatrimonio() !=null && biblioteca.getPatrimonio().getFondiAntichi() != null) {
			String tipo = "";
			if (biblioteca.getPatrimonio().getFondiAntichi().getVolumi() == FondoAnticoTypeVolumiType.VALUE_0) {
				tipo = String.valueOf(1);
			}
			if (biblioteca.getPatrimonio().getFondiAntichi().getVolumi() == FondoAnticoTypeVolumiType.VALUE_1) {
				tipo = String.valueOf(2);
			}
			if (biblioteca.getPatrimonio().getFondiAntichi().getVolumi() == FondoAnticoTypeVolumiType.VALUE_2) {
				tipo = String.valueOf(3);
			}
			FondiAntichiConsistenza fondiAntichiConsistenza = (FondiAntichiConsistenza) dynaTabDao.searchRecord(FondiAntichiConsistenza.class, tipo);
			if (fondiAntichiConsistenza == null) {
				String msg = "Fondi antichi consistenza non trovati:" + biblioteca.getPatrimonio().getFondiAntichi().getVolumi();
				reportImport.addWarn(msg);
				log.warn(msg);
				//Setto i fondi antichi con valore non specificato di default
				fondiAntichiConsistenza = (FondiAntichiConsistenza) dynaTabDao.loadRecord(FondiAntichiConsistenza.class, 1);
			}
			bibliotecaDb.setFondiAntichiConsistenza(fondiAntichiConsistenza);
			log.debug("Modificato FONDO ANTICO...");
		} else {
			//Setto i fondi antichi con valore non specificato di default
			FondiAntichiConsistenza fondiAntichiConsistenza = (FondiAntichiConsistenza) dynaTabDao.loadRecord(FondiAntichiConsistenza.class, 1);
			bibliotecaDb.setFondiAntichiConsistenza(fondiAntichiConsistenza);
		}
	}


	/**
	 * @param biblioteca
	 * @param reportImport
	 * @param bibliotecaDb
	 */
	@Transactional
	private boolean setEnte(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca, ReportImport reportImport, Biblioteca bibliotecaDb) {
		if (biblioteca.getAmministrativa() != null && biblioteca.getAmministrativa().getEnte() != null) {
			/* ENTE: denominazione */
			String denominazione = null;
			if (biblioteca.getAmministrativa().getEnte().getNome() != null) {
				denominazione = biblioteca.getAmministrativa().getEnte().getNome();
				log.debug("ente denominazione: " + denominazione);
			}
			
			/* ENTE: tipologia amministrativa */
			Integer idTipologiaAmministrativa = null;
			if (biblioteca.getAmministrativa().getEnte().getTipologiaAmministrativa() != null) {
				String enteTipologiaAmministrativaDenom = biblioteca.getAmministrativa().getEnte().getTipologiaAmministrativa();
				EnteTipologiaAmministrativa enteTipologiaAmministrativa = (EnteTipologiaAmministrativa) dynaTabDao.searchRecord(EnteTipologiaAmministrativa.class, enteTipologiaAmministrativaDenom);
				if (enteTipologiaAmministrativa == null) {
					String msg = "Ente tipologia amministrativa non trovata: " + enteTipologiaAmministrativaDenom;
					reportImport.addWarn(msg);
					log.warn(msg);
					
				} else {
					idTipologiaAmministrativa = enteTipologiaAmministrativa.getIdEnteTipologiaAmministrativa();
					log.debug("Modificata ente enteTipologiaAmministrativa: " + enteTipologiaAmministrativa);
				}				
			}
			
			/* ENTE:  stato appartenenza */
			Integer idStato = null;
			if (biblioteca.getAmministrativa().getEnte().getStato() != null) {
				String statoEnteDenom = biblioteca.getAmministrativa().getEnte().getStato();
				Stato statoEnte = statoDao.loadStatoBySigla(statoEnteDenom);
				if (statoEnte == null) {
					String msg = "Ente stato non trovato: " + statoEnteDenom;
					reportImport.addWarn(msg);
					log.warn(msg);
					
				} else {
					idStato = statoEnte.getIdStato();
					log.debug("statoEnte: " + statoEnte);
				}
			}
			
			/* ENTE: asia asip */
			String asiaAsip = null;

			/* ENTE: partita iva */
			String partitaIva = null;
			if (biblioteca.getAmministrativa().getEnte().getPartitaIVA() != null) {
				partitaIva = biblioteca.getAmministrativa().getEnte().getPartitaIVA();
				log.debug("Modifica ente partitaIva: " + partitaIva);
			}
			
			/* ENTE: codice fiscale */
			String codiceFiscale = null;
			if (biblioteca.getAmministrativa().getEnte().getCodiceFiscale() != null) {
				codiceFiscale = biblioteca.getAmministrativa().getEnte().getCodiceFiscale();
				log.debug("Ente codiceFiscale: " + codiceFiscale);
			}
			
			// cerco l'ente con i dati dal XML, se lo trovo aggiungo quello altrimenti lo creo nuovo
			Ente enteJpa = enteDao.createEnteIfNotExist(denominazione, idTipologiaAmministrativa, idStato, asiaAsip, partitaIva, codiceFiscale);
			bibliotecaDb.setEnte(enteJpa);


			//Tipologia Funzionale
			if (biblioteca.getAmministrativa().getEnte().getTipologiaFunzionale() != null) {
				String tipologiaFunzionale = biblioteca.getAmministrativa().getEnte().getTipologiaFunzionale();
				TipologiaFunzionale temp = (TipologiaFunzionale) dynaTabDao.searchRecord(TipologiaFunzionale.class, tipologiaFunzionale);
				if (temp == null) {
					String msg = "Tipologia funzionale non trovata: " + tipologiaFunzionale;
					reportImport.addWarn(msg);
					log.warn(msg);
				}
				bibliotecaDb.setTipologiaFunzionale(temp);
				log.debug("Modifica tipologiaFunzionale: " + tipologiaFunzionale);
			}

			return true;
			
		} else {
			/* Non essendo specificato un ente, viene assegnato quello di default (il primo della lista) */
			Ente enteJpa = enteDao.retrieveDefaultEnte();
			if (enteJpa != null) {
				bibliotecaDb.setEnte(enteJpa);
				return true;

			} else {
				String msg = "Impossibile effettuare l'import della biblioteca poichè il valore Ente è nullo";
				reportImport.addError(msg);
				log.warn(msg);
				return false;
			}
		}
	}


	/**
	 * @param biblioteca
	 * @param reportImport
	 * @param nuovaBiblio
	 */
	private boolean setComune(it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca,
			ReportImport reportImport, Biblioteca nuovaBiblio) {

		if (biblioteca.getAnagrafica().getIndirizzo() != null && biblioteca.getAnagrafica().getIndirizzo().getComune() != null) {
			Comune myComune = comuneDao.getComuneByCodIstat(biblioteca.getAnagrafica().getIndirizzo().getComune());
			nuovaBiblio.setComune(myComune);
			return true;

		} else {

			String msg = "Impossibile effettuare l'import della biblioteca poichè il valore Comune è nullo";
			reportImport.addError(msg);
			log.warn(msg);
			return false;

		}
	}

	public Date parseOrario(String ora) {
		try {
			Date temp = DateUtils.parseDate(ora, new String[] {"HH:mm"});
			return new Date(temp.getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public String parteIntera(String p) {
		if (p.indexOf(".") != -1) {
			String tmp[] = p.split("\\.");
			p = tmp[0];
		} else {
			if (p.indexOf(",") != -1) {
				String tmp[] = p.split(",");
				p = tmp[0];
			}
		}
		return p;
	}

	public String getIsilNR(String msg, String siglaProvincia) {
		String n = null;
		if (msg.indexOf("Alla biblioteca è stato assegnato il codice:")!=-1) {
			int init = msg.indexOf(siglaProvincia);
			n = msg.substring(init+1, init+5);
		}
		return n;
	}

	public static int giornoCastorToHibernate(OrarioGiornoType og) {
		if (og.equals(OrarioGiornoType.LUN))
			return 1;
		if (og.equals(OrarioGiornoType.MAR))
			return 2;
		if (og.equals(OrarioGiornoType.MER))
			return 3;
		if (og.equals(OrarioGiornoType.GIO))
			return 4;
		if (og.equals(OrarioGiornoType.VEN))
			return 5;
		if (og.equals(OrarioGiornoType.SAB))
			return 6;
		if (og.equals(OrarioGiornoType.DOM))
			return 7;
		return -1;
	}

	public static String fasciaOraria(String dalle, String alle) {
		String token = "";
		if (dalle.indexOf(":") != -1)
			token = ":";
		else
			if (dalle.indexOf(",") != -1)
				token = ",";
			else
				if (dalle.indexOf(".") != -1)
					token = ".";
		String[] oD = dalle.split(token);
		token = "";
		if (alle.indexOf(":") != -1)
			token = ":";
		else
			if (alle.indexOf(",") != -1)
				token = ",";
			else
				if (alle.indexOf(".") != -1)
					token = ".";
		String[] oA = alle.split(token);
		Integer dalleO = new Integer(oD[0]);
		//Integer dalleM = new Integer(oD[1]);
		Integer alleO = new Integer(oA[0]);
		Integer alleM = new Integer(oA[1]);

		if (dalleO.intValue()>=8 && dalleO.intValue()<14   && 
				alleO.intValue() >= 8 && 
				(alleO.intValue()<14 || (alleO.intValue()==14 && alleM.intValue()==0)) && 
				dalleO.intValue() < alleO.intValue())
			return "M";
		else
			if (dalleO.intValue()>=14 && dalleO.intValue()<20 && 
					alleO.intValue()>=14 && (alleO.intValue()<20 || (alleO.intValue()==20 && alleM.intValue()==0)) && 
					dalleO.intValue()<alleO.intValue())
				return "P";
			else
				if (dalleO.intValue()>=20 && dalleO.intValue()<24 && 
						alleO.intValue()>=20 && alleO.intValue()<24 && 
						dalleO.intValue()<alleO.intValue())
					return "S";
		return "ERRORE: fascia oraria errata! Dalle "+ dalle + " alle " + alle;
	}




}