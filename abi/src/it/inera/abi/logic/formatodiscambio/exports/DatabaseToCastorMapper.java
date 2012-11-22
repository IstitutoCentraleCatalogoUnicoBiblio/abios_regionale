package it.inera.abi.logic.formatodiscambio.exports;

import it.inera.abi.commons.Utility;
import it.inera.abi.logic.formatodiscambio.castor.Accesso;
import it.inera.abi.logic.formatodiscambio.castor.Alternativi;
import it.inera.abi.logic.formatodiscambio.castor.Altri;
import it.inera.abi.logic.formatodiscambio.castor.Altro;
import it.inera.abi.logic.formatodiscambio.castor.Amministrativa;
import it.inera.abi.logic.formatodiscambio.castor.Anagrafica;
import it.inera.abi.logic.formatodiscambio.castor.Bilancio;
import it.inera.abi.logic.formatodiscambio.castor.CartaServizi;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecForme;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeCopertura;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriale;
import it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriali;
import it.inera.abi.logic.formatodiscambio.castor.Cataloghi;
import it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali;
import it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale;
import it.inera.abi.logic.formatodiscambio.castor.CatalogoTopografico;
import it.inera.abi.logic.formatodiscambio.castor.Chiusura;
import it.inera.abi.logic.formatodiscambio.castor.Coordinate;
import it.inera.abi.logic.formatodiscambio.castor.Copertura;
import it.inera.abi.logic.formatodiscambio.castor.DataCensimento;
import it.inera.abi.logic.formatodiscambio.castor.DataCostruzione;
import it.inera.abi.logic.formatodiscambio.castor.DataFondazione;
import it.inera.abi.logic.formatodiscambio.castor.DataIstituzione;
import it.inera.abi.logic.formatodiscambio.castor.DepositoLegale;
import it.inera.abi.logic.formatodiscambio.castor.Digitale;
import it.inera.abi.logic.formatodiscambio.castor.DocDelForme;
import it.inera.abi.logic.formatodiscambio.castor.DocumentDelivery;
import it.inera.abi.logic.formatodiscambio.castor.Edificio;
import it.inera.abi.logic.formatodiscambio.castor.FondiAntichi;
import it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale;
import it.inera.abi.logic.formatodiscambio.castor.Forme;
import it.inera.abi.logic.formatodiscambio.castor.Indirizzo;
import it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche;
import it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario;
import it.inera.abi.logic.formatodiscambio.castor.Internet;
import it.inera.abi.logic.formatodiscambio.castor.Istituzione;
import it.inera.abi.logic.formatodiscambio.castor.Locale;
import it.inera.abi.logic.formatodiscambio.castor.Materiale;
import it.inera.abi.logic.formatodiscambio.castor.Materiali;
import it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi;
import it.inera.abi.logic.formatodiscambio.castor.Microforme;
import it.inera.abi.logic.formatodiscambio.castor.ModalitaAccesso;
import it.inera.abi.logic.formatodiscambio.castor.Nomi;
import it.inera.abi.logic.formatodiscambio.castor.Orario;
import it.inera.abi.logic.formatodiscambio.castor.PatrimonioInventario;
import it.inera.abi.logic.formatodiscambio.castor.Personale;
import it.inera.abi.logic.formatodiscambio.castor.Postazioni;
import it.inera.abi.logic.formatodiscambio.castor.Precedenti;
import it.inera.abi.logic.formatodiscambio.castor.Prestito;
import it.inera.abi.logic.formatodiscambio.castor.Reference;
import it.inera.abi.logic.formatodiscambio.castor.Riproduzione;
import it.inera.abi.logic.formatodiscambio.castor.Scaffalature;
import it.inera.abi.logic.formatodiscambio.castor.Schede;
import it.inera.abi.logic.formatodiscambio.castor.Servizi;
import it.inera.abi.logic.formatodiscambio.castor.ServiziOrario;
import it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno;
import it.inera.abi.logic.formatodiscambio.castor.SistemaIll;
import it.inera.abi.logic.formatodiscambio.castor.Sistemi;
import it.inera.abi.logic.formatodiscambio.castor.SistemiItem;
import it.inera.abi.logic.formatodiscambio.castor.Specializzazione;
import it.inera.abi.logic.formatodiscambio.castor.Specializzazioni;
import it.inera.abi.logic.formatodiscambio.castor.Strutture;
import it.inera.abi.logic.formatodiscambio.castor.Superficie;
import it.inera.abi.logic.formatodiscambio.castor.Telefonici;
import it.inera.abi.logic.formatodiscambio.castor.Telefonico;
import it.inera.abi.logic.formatodiscambio.castor.TipoPrestito;
import it.inera.abi.logic.formatodiscambio.castor.Ufficiale;
import it.inera.abi.logic.formatodiscambio.castor.Uscite;
import it.inera.abi.logic.formatodiscambio.castor.Utenti;
import it.inera.abi.logic.formatodiscambio.castor.Variazione;
import it.inera.abi.logic.formatodiscambio.castor.Volume;
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
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario;
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
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziBibliotecariCarta;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe contenente il mapping dei dati presenti nel database in formato castor
 *
 */
public class DatabaseToCastorMapper {

	private static Log log = LogFactory.getLog(DatabaseToCastorMapper.class);

	@Transactional
	public static void doDatabaseToCastorMapper (Biblioteca bibliotecaDb, it.inera.abi.logic.formatodiscambio.castor.Biblioteca bibliotecaCastor) {

		log.info("Start transformation from Hibernate classes to Castor classes for export...");

		log.info("Setting ANAGRAFICA classes");
		Anagrafica anagrafica = DatabaseToCastorMapper.createAnagrafica(bibliotecaDb);
		bibliotecaCastor.setAnagrafica(anagrafica);
		log.info("ANAGRAFICA classes have been setted");

		log.info("Setting AMMMINISTRATIVA classes");
		Amministrativa amministrativa = DatabaseToCastorMapper.createAmministrativa(bibliotecaDb);
		bibliotecaCastor.setAmministrativa(amministrativa);
		log.info("AMMMINISTRATIVA classes have been setted");


		Cataloghi cataloghi = DatabaseToCastorMapper.createCataloghi(bibliotecaDb);

		if ((cataloghi.getCataloghiCollettivi() != null && cataloghi.getCataloghiCollettivi().getCatalogoCollettivoCount() > 0) || 
				(cataloghi.getCataloghiGenerali() != null && cataloghi.getCataloghiGenerali().getCatalogoGeneraleCount() > 0) || 
				(cataloghi.getCataloghiSpeciali() != null && cataloghi.getCataloghiSpeciali().getCatalogoSpecialeCount() > 0)) {
			log.info("Setting CATALOGHI classes");
			bibliotecaCastor.setCataloghi(cataloghi);
			log.info("CATALOGHI classes have been setted");	
		}


		log.info("Setting PATRIMONIO classes");
		it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio = DatabaseToCastorMapper.createPatrimonio(bibliotecaDb);

		boolean testPatrimonio = (patrimonio.getAcquistiUltimiQuindiciAnni() != null);
		testPatrimonio = testPatrimonio || (patrimonio.getCatalogoTopografico() != null);
		testPatrimonio = testPatrimonio || (patrimonio.getFondiAntichi() != null);
		testPatrimonio = testPatrimonio || (patrimonio.getFondiSpeciali() != null && patrimonio.getFondiSpeciali().getFondoSpeciale() != null && patrimonio.getFondiSpeciali().getFondoSpeciale().length > 0);
		testPatrimonio = testPatrimonio || (patrimonio.getMateriali() != null && patrimonio.getMateriali().getMateriale() != null && patrimonio.getMateriali().getMateriale().length > 0);
		testPatrimonio = testPatrimonio || (patrimonio.getPatrimonioInventario() != null);
		if (testPatrimonio) {
			bibliotecaCastor.setPatrimonio(patrimonio);
			log.info("PATRIMONIO classes have been setted");
		}


		log.info("Setting SERVIZI classes");
		Servizi servizi = DatabaseToCastorMapper.createServizi(bibliotecaDb);
		bibliotecaCastor.setServizi(servizi);
		log.info("SERVIZI classes have been setted");

		log.info("Setting SPECIALIZZAZIONI classes");
		Specializzazione[] specializzazioneArray = createSpecializzazioni(bibliotecaDb);
		if (specializzazioneArray != null) {
			Specializzazioni specializzazioni = new Specializzazioni();
			specializzazioni.setSpecializzazione(specializzazioneArray);
			bibliotecaCastor.setSpecializzazioni(specializzazioni);
		}
		log.info("Setting SPECIALIZZAZIONI classes");


	}

	private static Specializzazione[] createSpecializzazioni(Biblioteca bibliotecaDb){
		Specializzazione[] specC = null;
		List<DeweyLibero> specH = bibliotecaDb.getDeweyLiberos();
		ArrayList<Specializzazione> speC = new ArrayList<Specializzazione>();
		for (Iterator<DeweyLibero> i = specH.iterator(); i.hasNext();) {
			DeweyLibero dl = i.next();
			Specializzazione s = null;
			if((dl.getId() != null)&&(dl.getDescrizione()!= null) && (dl.getDescrizione().trim().length()>0)) {
				s = new Specializzazione();
				s.setDescrizioneLibera(dl.getDescrizione().trim());
				String dew = dl.getId().getIdDewey();
				if(dew.length()>3){
					log.debug("Codice dewey dal DB: " + dew);
					dew = dew.substring(0, 3) + "." + dew.substring(3, dew.length());
					log.debug("Codice dewey modificato: " + dew);
				}
				s.setSpecializzazioneCdd(dew);
				//s = new Specializzazione();
			}
			if(s != null)
				speC.add(s);
		}
		if (speC.size() > 0) {
			specC = new Specializzazione[speC.size()];
			specC = (Specializzazione[]) speC.toArray(specC);
			log.debug("Settate specializzazioni dewey nella parte Specializzazioni");
		} else {
			log.debug("Non sono presenti specializzazioni per la biblioteca");
		}
		return specC;
	}

	private static Amministrativa createAmministrativa (Biblioteca bibliotecaDb) {

		Amministrativa amministrativa = new Amministrativa();
		if (BooleanUtils.isTrue(bibliotecaDb.getAutonomiaAmministrativa()))
			amministrativa.setAutonoma(SiNoType.S);
		else
			amministrativa.setAutonoma(SiNoType.N);
		if((bibliotecaDb.getCodiceFiscale() != null)&&(bibliotecaDb.getCodiceFiscale().trim().length()>0))
			amministrativa.setCodiceFiscale(bibliotecaDb.getCodiceFiscale().trim());
		if((bibliotecaDb.getPartitaIva() != null)&&(bibliotecaDb.getPartitaIva().trim().length()>0))
			amministrativa.setPartitaIVA(bibliotecaDb.getPartitaIva().trim());

		DatabaseToCastorMapper.Amministrativa_setBilancio(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setCarteServizi(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setDepositoLegale(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setEnte(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setPersonale(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setRegolamento(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setStrutture(amministrativa, bibliotecaDb);
		DatabaseToCastorMapper.Amministrativa_setUtenti(amministrativa, bibliotecaDb);
		return amministrativa;
	}

	private static Anagrafica createAnagrafica(Biblioteca bibliotecaDb) {
		Anagrafica anagrafica = new Anagrafica();
		DatabaseToCastorMapper.Anagrafica_setCodici(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setContatti(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setDataIstituzione(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setEdificio(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setIndirizzo(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setDenominazioni(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setDate(anagrafica, bibliotecaDb);
		DatabaseToCastorMapper.Anagrafica_setFonte(anagrafica, bibliotecaDb);
		return anagrafica;
	}

	private static Cataloghi createCataloghi(Biblioteca bibliotecaDb) {
		Cataloghi cataloghi = new Cataloghi();
		DatabaseToCastorMapper.Cataloghi_setCataloghiCollettivi(cataloghi, bibliotecaDb);
		DatabaseToCastorMapper.Cataloghi_setCataloghiGenerali(cataloghi, bibliotecaDb);
		DatabaseToCastorMapper.Cataloghi_setCataloghiSpeciali(cataloghi, bibliotecaDb);
		return cataloghi;
	}

	private static it.inera.abi.logic.formatodiscambio.castor.Patrimonio createPatrimonio(Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio = new it.inera.abi.logic.formatodiscambio.castor.Patrimonio();

		if(isNullSafeNotZero(bibliotecaDb.getBilancioUsciteAcquistiUltimi15())) {
			patrimonio.setAcquistiUltimiQuindiciAnni(new BigDecimal(bibliotecaDb.getBilancioUsciteAcquistiUltimi15()));
		}

		DatabaseToCastorMapper.Patrimonio_setCatalogoTopografico(patrimonio, bibliotecaDb);
		DatabaseToCastorMapper.Patrimonio_setFondiAntichi(patrimonio, bibliotecaDb);
		DatabaseToCastorMapper.Patrimonio_setFondiSpeciali(patrimonio,	bibliotecaDb);
		DatabaseToCastorMapper.Patrimonio_setMateriale(patrimonio, bibliotecaDb);
		DatabaseToCastorMapper.Patrimonio_setPatrimonioInventario(patrimonio, bibliotecaDb);

		if(isNullSafeNotZero(bibliotecaDb.getBilancioPatrimonialePosseduto()))
			patrimonio.setTotalePosseduto(bibliotecaDb.getBilancioPatrimonialePosseduto());

		if(isNullSafeNotZero(bibliotecaDb.getBilancioPatrimonialePossedutoUnder14()))
			patrimonio.setTotalePossedutoRagazzi(bibliotecaDb.getBilancioPatrimonialePossedutoUnder14());

		return patrimonio;
	}

	private static Servizi createServizi(Biblioteca bibliotecaDb) {
		Servizi servizi = new Servizi();

		DatabaseToCastorMapper.Servizi_setAccesso(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setInformazioniBibliografiche(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setInternet(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setOrario(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setPrestito(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setSezioniSpeciali(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setSistemi(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setRiproduzioni(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setReference(servizi, bibliotecaDb);
		DatabaseToCastorMapper.Servizi_setDocumentDelivery(servizi, bibliotecaDb);

		return servizi;
	}

	private static void Anagrafica_setCodici(Anagrafica anagrafica, Biblioteca bibliotecaDb) {

		/*
		 * ANAGRAFICA: Codici
		 */
		it.inera.abi.logic.formatodiscambio.castor.Codici codici = new it.inera.abi.logic.formatodiscambio.castor.Codici();

		List<Codici> codicis = bibliotecaDb.getCodicis();
		Iterator<Codici> codiciIter = codicis.iterator();
		while (codiciIter.hasNext()) {
			Codici codice = codiciIter.next();
			String codDescr = codice.getCodiciTipo().getDescrizione();

			if ("acnp".equalsIgnoreCase(codDescr)) {
				codici.setAcnp(codice.getValore());
			}
			if ("rism".equalsIgnoreCase(codDescr)) {
				codici.setRism(codice.getValore());
			}
			if ("sbn".equalsIgnoreCase(codDescr)) {
				codici.setSbn(codice.getValore());
			}
			if ("cmb".equalsIgnoreCase(codDescr)) {
				codici.setCmbs(codice.getValore());
			}
			if ("cei".equalsIgnoreCase(codDescr)) {
				codici.setCei(codice.getValore());
			}
		}
		codici.setIsil(Utility.buildIsil(bibliotecaDb));
		//if (codicis != null && codicis.size() > 0){
		anagrafica.setCodici(codici);
		log.debug("Settati codici nella parte Anagrafica");
		//}

	}

	private static void Anagrafica_setContatti(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.Contatti contatti = new it.inera.abi.logic.formatodiscambio.castor.Contatti();

		ArrayList<Telefonico> telefoniC = new ArrayList<Telefonico>();
		ArrayList<Altro> altriC = new ArrayList<Altro>();

		List<Contatti> contattis = bibliotecaDb.getContattis();
		Iterator<Contatti> iteratorContattis = contattis.iterator();
		while (iteratorContattis.hasNext()) {
			Contatti contatto = (Contatti) iteratorContattis.next();
			String contDescr = contatto.getContattiTipo().getDescrizione();
			if ("Telefono".equalsIgnoreCase(contDescr)) {
				Telefonico telefonico = new Telefonico();
				telefonico.setTipo(TelefonicoTipoType.TELEFONO);
				telefonico.setNumero(contatto.getValore());
				telefonico.setPrefisso("");
				log.debug("Aggiunto contatto telefonico: " + telefonico.getNumero() + " (" + telefonico.getTipo().toString() + ")");
				telefoniC.add(telefonico);
			}
			if ("Fax".equalsIgnoreCase(contDescr)) {
				contatto.getValore();
				Telefonico telefonico = new Telefonico();
				telefonico.setTipo(TelefonicoTipoType.FAX);
				telefonico.setNumero(contatto.getValore());
				telefonico.setPrefisso("");
				log.debug("Aggiunto contatto telefonico: " + telefonico.getNumero() + " (" + telefonico.getTipo().toString() + ")");
				telefoniC.add(telefonico);
			}
			if ("Email".equalsIgnoreCase(contDescr)) {
				Altro altro = new Altro();
				altro.setTipo(AltroTipoType.E_MAIL);
				altro.setValore(contatto.getValore());
				log.debug("Aggiunto altro contatto: " + altro.getValore() + " (" + altro.getTipo().toString() + ")");
				altriC.add(altro);
			}
			if ("Telex".equalsIgnoreCase(contDescr)) {
				Altro altro = new Altro();
				altro.setTipo(AltroTipoType.TELEX);
				altro.setValore(contatto.getValore());
				log.debug("Aggiunto altro contatto: " + altro.getValore() + " (" + altro.getTipo().toString() + ")");
				altriC.add(altro);
			}
			if ("Url".equalsIgnoreCase(contDescr)) {
				Altro altro = new Altro();
				altro.setTipo(AltroTipoType.URL);
				altro.setValore(contatto.getValore());
				log.debug("Aggiunto altro contatto: " + altro.getValore() + " (" + altro.getTipo().toString() + ")");
				altriC.add(altro);
			}
		}
		/*
		 * ANAGRAFICA: Contatti Telefonici
		 */
		if (telefoniC.size() > 0) {
			Telefonico[] telC = new Telefonico[telefoniC.size()];
			telC = (Telefonico[]) telefoniC.toArray(telC);
			Telefonici telefonici = new Telefonici();
			telefonici.setTelefonico(telC);
			contatti.setTelefonici(telefonici);
			log.debug("Settati contatti telefonici nella parte Anagrafica");
			
		} else {
			log.debug("Non sono presenti contatti telefonici per la biblioteca");
		}
		
		/*
		 * ANAGRAFICA: Contatti altro
		 */
		if (altriC.size() > 0) {
			Altro[] altC = new Altro[altriC.size()];
			altC = (Altro[]) altriC.toArray(altC);
			Altri altri = new Altri();
			altri.setAltro(altC);
			contatti.setAltri(altri);
			log.debug("Settati altri contatti nella parte Anagrafica");
			
		} else {
			log.debug("Non sono presenti altri contatti per la biblioteca");
		}
		
		if ((telefoniC != null && telefoniC.size() > 0) || (altriC != null && altriC.size() > 0)) {
			anagrafica.setContatti(contatti);
		}

	}

	private static void Anagrafica_setDataIstituzione(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		/*
		 * ANAGRAFICA: Data Istituzione
		 */
		Istituzione istituzioneC = null;
		if (bibliotecaDb.getDataFondazione() != null){
			DataFondazione dataFonC = new DataFondazione();
			//			if ("S".equalsIgnoreCase(bibliotecaDb.getDataFondazione())){
			//				dataFonC.setTipo(AnnoSecoloTypeTipoType.SECOLO);
			//			} else {
			//				if("A".equalsIgnoreCase(bibliotecaDb.getDataFondazione())){
			//					dataFonC.setTipo(AnnoSecoloTypeTipoType.ANNO);
			//				}
			//			}
			if (bibliotecaDb.getDataFondazione().trim().length()>0)
				dataFonC.setContent(bibliotecaDb.getDataFondazione().trim());
			if (istituzioneC == null)
				istituzioneC = new Istituzione();
			istituzioneC.setDataFondazione(dataFonC);
		}
		if (bibliotecaDb.getDataIstituzione() != null){
			DataIstituzione dataIstC = new DataIstituzione();
			//La tipologia della data viene sempre settata a ANNO
			//			dataIstC.setTipo(AnnoSecoloTypeTipoType.ANNO);
			if (bibliotecaDb.getDataIstituzione().trim().length()>0)
				dataIstC.setContent(bibliotecaDb.getDataIstituzione().trim());
			if(istituzioneC == null)
				istituzioneC = new Istituzione();
			istituzioneC.setDataIstituzione(dataIstC);
		}
		/*
		 * CODICE CON IL TIPO DELLA DATA COME ELEMENTO E NON COME ATTRIBUTO!
			if(bibliotecaDb.getAnnoIstituzione() != null)
			dataIstC.setDataIstituzione(bibliotecaDb.getAnnoIstituzione());
			if("A".equalsIgnoreCase(bibliotecaDb.getDataFondazione())){
				dataIstC.setTipoDataFondazione(AnnoSecoloType.ANNO);
				}
			else{
				if("S".equalsIgnoreCase(bibliotecaDb.getDataFondazione())){
					dataIstC.setTipo(AnnoSecoloType.SECOLO);
				}
			}
			dataIstC.setDataFondazione(bibliotecaDb.getFondazione());
		 * 
		 */
		if (istituzioneC != null) {
			anagrafica.setIstituzione(istituzioneC);
			log.debug("Settata data istituzione nella parte Anagrafica");
		}
	}


	private static void Anagrafica_setEdificio(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		boolean init = false;
		/*
		 * ANAGRAFICA: Edificio
		 */
		Edificio edificio = new Edificio();
		//Costruito appositamente?		
		if (BooleanUtils.isTrue(bibliotecaDb.getEdificioAppositamenteCostruito())) {
			edificio.setAppositamenteCostruito(SiNoType.S);
			init = true;
		} else if (BooleanUtils.isFalse(bibliotecaDb.getEdificioAppositamenteCostruito())) {
			edificio.setAppositamenteCostruito(SiNoType.N);
			init = true;
		}

		if (bibliotecaDb.getEdificioDataCostruzione() != null){
			init = true;
			DataCostruzione dataCostrC = new DataCostruzione();
			//			if ("S".equalsIgnoreCase(bibliotecaDb.getDataCostr())){
			//				dataCostrC.setTipo(AnnoSecoloTypeTipoType.SECOLO);
			//			} else {
			//				if ("A".equalsIgnoreCase(bibliotecaDb.getDataCostr())) {
			//					dataCostrC.setTipo(AnnoSecoloTypeTipoType.ANNO);
			//				}
			//			}
			if (bibliotecaDb.getEdificioDataCostruzione().trim().length() > 0)
				dataCostrC.setContent(bibliotecaDb.getEdificioDataCostruzione().trim());
			edificio.setDataCostruzione(dataCostrC);
		}
		/*CODICE CON IL TIPO DELLA DATA COME ELEMENTO E NON COME ATTRIBUTO!
		//Tipo data costruzione (ANNO/SECOLO)
		if("A".equalsIgnoreCase(bibliotecaDb.getDataCostr())){
			edificio.setTipoDataCostruzione(AnnoSecoloType.ANNO);
		}
		else{
			if("S".equalsIgnoreCase(bibliotecaDb.getDataCostr())){
				edificio.setTipoDataCostruzione(AnnoSecoloType.SECOLO);
			}
		}
		//Data costruzione
		if(bibliotecaDb.getCostruzione() != null)
			edificio.setDataCostruzione(bibliotecaDb.getCostruzione());
		 */
		//Denominazione edificio
		if ((bibliotecaDb.getEdificioDenominazione() != null)&&(bibliotecaDb.getEdificioDenominazione().trim().length()>0)) {
			edificio.setDenominazione(bibliotecaDb.getEdificioDenominazione().trim());
			init = true;
		}
		//Edificio monumentale?
		if (BooleanUtils.isTrue(bibliotecaDb.getEdificioMonumentale())){
			init = true;
			edificio.setMonumentale(SiNoType.S);
		} else if (BooleanUtils.isFalse(bibliotecaDb.getEdificioMonumentale())){
			init = true;
			edificio.setMonumentale(SiNoType.N);
		}


		if (init) {
			anagrafica.setEdificio(edificio);
			log.debug("Settato edificio nella parte Anagrafica");
		}

	}

	private static void Anagrafica_setIndirizzo(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		/*
		 * ANAGRAFICA: Indirizzo
		 */
		Indirizzo indirizzo = new Indirizzo();
		if ((bibliotecaDb.getCap() != null)&&(bibliotecaDb.getCap().trim().length()>0))
			indirizzo.setCap(bibliotecaDb.getCap().trim());
		indirizzo.setComune(Utility.zeroFill(bibliotecaDb.getComune().getCodiceIstat(), 6));
		if ((bibliotecaDb.getFrazione() != null)&&(bibliotecaDb.getFrazione().trim().length()>0))
			indirizzo.setFrazione(bibliotecaDb.getFrazione().trim());
		indirizzo.setProvincia(bibliotecaDb.getComune().getProvincia().getCodiceIstat());
		indirizzo.setRegione(bibliotecaDb.getComune().getProvincia().getRegione().getDenominazione());
		indirizzo.setStato(bibliotecaDb.getComune().getProvincia().getRegione().getStato().getSigla());
		if((bibliotecaDb.getIndirizzo() != null)&&(bibliotecaDb.getIndirizzo().trim().length()>0))
			indirizzo.setVia(bibliotecaDb.getIndirizzo().trim());

		if (bibliotecaDb.getGeolocalizzazione() != null) {
			if (bibliotecaDb.getGeolocalizzazione().getLatitudine() != null && 
					bibliotecaDb.getGeolocalizzazione().getLatitudine().doubleValue() != 0.0 &&
					bibliotecaDb.getGeolocalizzazione().getLongitudine() != null && 
					bibliotecaDb.getGeolocalizzazione().getLongitudine().doubleValue() != 0.0) {
				Coordinate coordinate = new Coordinate();
				coordinate.setLatitudine(bibliotecaDb.getGeolocalizzazione().getLatitudine().doubleValue());
				coordinate.setLongitudine(bibliotecaDb.getGeolocalizzazione().getLongitudine().doubleValue());

				indirizzo.setCoordinate(coordinate);
			}
		}

		anagrafica.setIndirizzo(indirizzo);
		log.debug("Settato indirizzo nella parte Anagrafica");
	}

	private static void Anagrafica_setDenominazioni(Anagrafica anagrafica,
			Biblioteca bibliotecaDb) {
		/*
		 * ANAGRAFICA: Denominazioni
		 */
		Nomi nomi = new Nomi();
		// Attuale
		if ((bibliotecaDb.getDenominazioneUfficiale() != null) && (bibliotecaDb.getDenominazioneUfficiale().trim().length() > 0))
			nomi.setAttuale(bibliotecaDb.getDenominazioneUfficiale().trim());

		// Anche
		List<DenominazioniAlternative> denAnche = bibliotecaDb.getDenominazioniAlternatives();
		if (denAnche.size() > 0) {
			ArrayList<String> denAC = new ArrayList<String>();
			for (Iterator<DenominazioniAlternative> i = denAnche.iterator(); i.hasNext();) {
				DenominazioniAlternative denominazioniAlternative = i.next();
				String den = denominazioniAlternative.getDenominazione();
				if ((den != null)&&(den.trim().length()>0)) {
					denAC.add(den.trim());
					log.debug("Aggiunta denominazione alternativa: " + den);
				}
			}
			if (denAC.size() > 0) {
				Alternativi alternativi = new Alternativi();
				alternativi.setAlternativo(denAC.toArray(new String[denAC.size()]));
				nomi.setAlternativi(alternativi);
				log.debug("Settate denominazioni alternative nella parte Anagrafica");
			}

		} else {
			log.debug("Non sono presenti denominazioni alternative per la biblioteca");
		}
		// Già
		List<DenominazioniPrecedenti> denGia = bibliotecaDb.getDenominazioniPrecedentis();
		if (denGia.size() > 0) {
			ArrayList<String> denGC = new ArrayList<String>();
			for (Iterator<DenominazioniPrecedenti> i = denGia.iterator(); i.hasNext();) {
				DenominazioniPrecedenti denominazioniPrecedenti = i.next();
				String den = denominazioniPrecedenti.getDenominazione();
				if ((den != null) && (den.trim().length() > 0)) {
					denGC.add(den.trim());
					log.debug("Aggiunta denominazione precedente: " + den);
				}
			}
			if (denGC.size() > 0) {
				Precedenti precedenti = new Precedenti();
				precedenti.setPrecedente(denGC.toArray(new String[denGC.size()]));
				nomi.setPrecedenti(precedenti);
				log.debug("Settate denominazioni precedenti nella parte Anagrafica");
			}
		} else {
			log.debug("Non sono presenti denominazioni precedenti per la biblioteca");
		}
		anagrafica.setNomi(nomi);
		log.debug("Settate denominazioni (normale, già e anche) nella parte Anagrafica");
	}

	private static void Anagrafica_setDate(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		/*
		 * ANAGRAFICA: Date
		 */
		// Data aggiornamento 
		if (bibliotecaDb.getCatalogazioneDataModifica() != null){
			anagrafica.setDataAggiornamento(bibliotecaDb.getCatalogazioneDataModifica());
			log.debug("Aggiunta data ultima modifica (biblioteca esportante): " + bibliotecaDb.getCatalogazioneDataModifica());
		}
		// Data censimento
		if (bibliotecaDb.getCatalogazioneDataCensimento() != null) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(bibliotecaDb.getCatalogazioneDataCensimento());
			int anno = temp.get(Calendar.YEAR);
			DataCensimento dataCensimento = new DataCensimento();
			//dataCensimento.setTipo(AnnoSecoloTypeTipoType.ANNO);
			dataCensimento.setContent(String.valueOf(anno));
			anagrafica.setDataCensimento(dataCensimento);
			log.debug("Aggiunta data censimento: " + anno);

		}
	}
	
	private static void Anagrafica_setFonte(Anagrafica anagrafica, Biblioteca bibliotecaDb) {
		/*
		 * ANAGRAFICA: Fonte
		 */
		if (bibliotecaDb.getFonte() != null) {
			anagrafica.setFonte(bibliotecaDb.getFonte());
			log.debug("Aggiunta fonte: " + bibliotecaDb.getFonte());
		}
		
	}

	private static void Cataloghi_setCataloghiCollettivi(Cataloghi cataloghi,
			Biblioteca bibliotecaDb) {
		/*
		 * CATALOGHI: catalogo COLLETTIVO
		 */
		ArrayList<CatalogoCollettivo> catalColl = new ArrayList<CatalogoCollettivo>();
		List<PartecipaCataloghiCollettiviMateriale> catCollH = bibliotecaDb.getPartecipaCataloghiCollettiviMateriales();

		Collections.sort(catCollH, new Comparator<PartecipaCataloghiCollettiviMateriale>() {
			@Override
			public int compare(PartecipaCataloghiCollettiviMateriale o1,
					PartecipaCataloghiCollettiviMateriale o2) {
				return (o1.getCataloghiCollettivi().getIdCataloghiCollettivi().compareTo(o2.getCataloghiCollettivi().getIdCataloghiCollettivi()));
			}
		});


		int lastIdCatColl = -1;
		if (catCollH != null && catCollH.size() > 0) {
			lastIdCatColl = catCollH.get(0).getCataloghiCollettivi().getIdCataloghiCollettivi().intValue();
		}

		for (Iterator<PartecipaCataloghiCollettiviMateriale> i = catCollH.iterator(); i.hasNext();) {
			PartecipaCataloghiCollettiviMateriale catH = i.next();
			CatalogoCollettivo catalogoCollettivo = null;
			CatSpecMateriali catSpecMateriali = null;

			if (catCollH.indexOf(catH) == 0) {
				catalogoCollettivo = new CatalogoCollettivo();

				/* NOME (Descrizione catalogo) */
				CataloghiCollettivi ccc = catH.getCataloghiCollettivi();

				if (ccc != null) {
					catalogoCollettivo.setNome(ccc.getDescrizione());
				}

				/* MATERIALI - Materiale */
				ArrayList<CatSpecMateriale> materiali = new ArrayList<CatSpecMateriale>();
				CatSpecMateriale catSpecMateriale = new CatSpecMateriale();
				catSpecMateriale.setNome(catH.getPatrimonioSpecializzazione().getDescrizione());

				/* SUPPORTO (Forme) del catalogo */
				CatSpecForme catSpecForme = null;

				/* Informatizzato */
				if (catH.getCataloghiSupportoDigitaleTipo() != null) {
					CatSpecFormeDigitale catSpecFormeDigitale = new CatSpecFormeDigitale();
					if (isNullSafeNotZero(catH.getPercentualeInformatizzata())){
						catSpecFormeDigitale.setPercentuale(String.valueOf(catH.getPercentualeInformatizzata()));
					}
					if(catSpecForme == null)
						catSpecForme = new CatSpecForme();
					catSpecForme.setCatSpecFormeDigitale(catSpecFormeDigitale);
				}
				//Microforme
				if (catH.getMicroforme() != null && catH.getMicroforme()) {
					CatSpecFormeMicroforme catSpecFormeMicroforme = new CatSpecFormeMicroforme();
					if (isNullSafeNotZero(catH.getPercentualeMicroforme())){
						catSpecFormeMicroforme.setPercentuale(String.valueOf(catH.getPercentualeMicroforme()));
					}
					if(catSpecForme == null)
						catSpecForme = new CatSpecForme();
					catSpecForme.setCatSpecFormeMicroforme(catSpecFormeMicroforme);
				}
				//Schede
				if (catH.getSchede() != null && catH.getSchede()) {
					CatSpecFormeSchede catSpecFormeSchede = new CatSpecFormeSchede();
					if (isNullSafeNotZero(catH.getPercentualeSchede())){
						catSpecFormeSchede.setPercentuale(String.valueOf(catH.getPercentualeSchede()));
					}	
					if(catSpecForme == null)
						catSpecForme = new CatSpecForme();
					catSpecForme.setCatSpecFormeSchede(catSpecFormeSchede);
				}
				//Volumi
				if (catH.getVolume() != null && catH.getVolume()) {
					CatSpecFormeVolume catSpecFormeVolume = new CatSpecFormeVolume();
					if (isNullSafeNotZero(catH.getPercentualeVolume())){
						catSpecFormeVolume.setPercentuale(String.valueOf(catH.getPercentualeVolume()));
					}
					if ((catH.getDescrizioneVolume() != null) && (catH.getDescrizioneVolume().trim().length()>0))
						catSpecFormeVolume.setCitazioneBibliografica(catH.getDescrizioneVolume().trim());
					if(catSpecForme == null)
						catSpecForme = new CatSpecForme();
					catSpecForme.setCatSpecFormeVolume(catSpecFormeVolume);
				}

				if(catSpecForme != null) {
					catSpecMateriale.setCatSpecForme(catSpecForme);
				}

				/* COPERTURA del catalogo */
				if (isNullSafeNotZero(catH.getDaAnno()) && isNullSafeNotZero(catH.getAAnno()) && String.valueOf(catH.getDaAnno()).length() == 4 && String.valueOf(catH.getAAnno()).length() == 4) {
					CatSpecFormeCopertura catSpecFormeCopertura = new CatSpecFormeCopertura();
					catSpecFormeCopertura.setAdAnno(String.valueOf(catH.getAAnno()));
					catSpecFormeCopertura.setDaAnno(String.valueOf(catH.getDaAnno()));
					catSpecMateriale.setCatSpecFormeCopertura(catSpecFormeCopertura);
				}

				materiali.add(catSpecMateriale);

				if (catCollH.indexOf(catH) == 0) {
					catSpecMateriali = new CatSpecMateriali();

				} else {
					if (lastIdCatColl == catH.getCataloghiCollettivi().getIdCataloghiCollettivi().intValue()) {
						catSpecMateriali = catalogoCollettivo.getCatSpecMateriali(); 

					} else {
						catSpecMateriali = new CatSpecMateriali();
					}
				}

				CatSpecMateriale[] catSpecMateriales = new CatSpecMateriale[materiali.size()];
				catSpecMateriales = (CatSpecMateriale[]) materiali.toArray(catSpecMateriales);
				catSpecMateriali.setCatSpecMateriale(catSpecMateriales);

				catalogoCollettivo.setCatSpecMateriali(catSpecMateriali);

			} else {
				if (lastIdCatColl == catH.getCataloghiCollettivi().getIdCataloghiCollettivi().intValue()) {
					catalogoCollettivo = catalColl.get(catalColl.size()-1);

					/* MATERIALI - Materiale */
					CatSpecMateriale catSpecMateriale = new CatSpecMateriale();
					catSpecMateriale.setNome(catH.getPatrimonioSpecializzazione().getDescrizione());

					/* SUPPORTO (Forme) del catalogo */
					CatSpecForme catSpecForme = null;

					/* Informatizzato */
					if (catH.getCataloghiSupportoDigitaleTipo() != null) {
						CatSpecFormeDigitale catSpecFormeDigitale = new CatSpecFormeDigitale();
						if (isNullSafeNotZero(catH.getPercentualeInformatizzata())){
							catSpecFormeDigitale.setPercentuale(String.valueOf(catH.getPercentualeInformatizzata()));
						}
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeDigitale(catSpecFormeDigitale);
					}
					//Microforme
					if (catH.getMicroforme() != null && catH.getMicroforme()) {
						CatSpecFormeMicroforme catSpecFormeMicroforme = new CatSpecFormeMicroforme();
						if (isNullSafeNotZero(catH.getPercentualeMicroforme())){
							catSpecFormeMicroforme.setPercentuale(String.valueOf(catH.getPercentualeMicroforme()));
						}
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeMicroforme(catSpecFormeMicroforme);
					}
					//Schede
					if (catH.getSchede() != null && catH.getSchede()) {
						CatSpecFormeSchede catSpecFormeSchede = new CatSpecFormeSchede();
						if (isNullSafeNotZero(catH.getPercentualeSchede())){
							catSpecFormeSchede.setPercentuale(String.valueOf(catH.getPercentualeSchede()));
						}	
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeSchede(catSpecFormeSchede);
					}
					//Volumi
					if (catH.getVolume() != null && catH.getVolume()) {
						CatSpecFormeVolume catSpecFormeVolume = new CatSpecFormeVolume();
						if (isNullSafeNotZero(catH.getPercentualeVolume())){
							catSpecFormeVolume.setPercentuale(String.valueOf(catH.getPercentualeVolume()));
						}
						if ((catH.getDescrizioneVolume() != null) && (catH.getDescrizioneVolume().trim().length()>0))
							catSpecFormeVolume.setCitazioneBibliografica(catH.getDescrizioneVolume().trim());
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeVolume(catSpecFormeVolume);
					}

					if(catSpecForme != null) {
						catSpecMateriale.setCatSpecForme(catSpecForme);
					}

					/* COPERTURA del catalogo */
					if (isNullSafeNotZero(catH.getDaAnno()) && isNullSafeNotZero(catH.getAAnno()) && String.valueOf(catH.getDaAnno()).length() == 4 && String.valueOf(catH.getAAnno()).length() == 4) {
						CatSpecFormeCopertura catSpecFormeCopertura = new CatSpecFormeCopertura();
						catSpecFormeCopertura.setAdAnno(String.valueOf(catH.getAAnno()));
						catSpecFormeCopertura.setDaAnno(String.valueOf(catH.getDaAnno()));
						catSpecMateriale.setCatSpecFormeCopertura(catSpecFormeCopertura);
					}

					/* Recupero gli altri materiali precedentemente salvati */
					ArrayList<CatSpecMateriale> materiali = new ArrayList<CatSpecMateriale>();
					for (int k = 0; k < catalogoCollettivo.getCatSpecMateriali().getCatSpecMaterialeCount(); k++) {
						CatSpecMateriale cMateriale = catalogoCollettivo.getCatSpecMateriali().getCatSpecMateriale(k);
						materiali.add(cMateriale);
					}

					materiali.add(catSpecMateriale);

					catSpecMateriali = catalogoCollettivo.getCatSpecMateriali();

					CatSpecMateriale[] catSpecMateriales = new CatSpecMateriale[materiali.size()];
					catSpecMateriales = (CatSpecMateriale[]) materiali.toArray(catSpecMateriales);
					catSpecMateriali.setCatSpecMateriale(catSpecMateriales);

					catalogoCollettivo.setCatSpecMateriali(catSpecMateriali);

				} else {
					catalogoCollettivo = new CatalogoCollettivo();

					/* NOME (Descrizione catalogo) */
					CataloghiCollettivi ccc = catH.getCataloghiCollettivi();

					if (ccc != null) {
						catalogoCollettivo.setNome(ccc.getDescrizione());
					}

					/* MATERIALI - Materiale */
					ArrayList<CatSpecMateriale> materiali = new ArrayList<CatSpecMateriale>();
					CatSpecMateriale catSpecMateriale = new CatSpecMateriale();
					catSpecMateriale.setNome(catH.getPatrimonioSpecializzazione().getDescrizione());

					/* SUPPORTO (Forme) del catalogo */
					CatSpecForme catSpecForme = null;

					/* Informatizzato */
					if (catH.getCataloghiSupportoDigitaleTipo() != null) {
						CatSpecFormeDigitale catSpecFormeDigitale = new CatSpecFormeDigitale();
						if (isNullSafeNotZero(catH.getPercentualeInformatizzata())){
							catSpecFormeDigitale.setPercentuale(String.valueOf(catH.getPercentualeInformatizzata()));
						}
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeDigitale(catSpecFormeDigitale);
					}
					//Microforme
					if (catH.getMicroforme() != null && catH.getMicroforme()) {
						CatSpecFormeMicroforme catSpecFormeMicroforme = new CatSpecFormeMicroforme();
						if (isNullSafeNotZero(catH.getPercentualeMicroforme())){
							catSpecFormeMicroforme.setPercentuale(String.valueOf(catH.getPercentualeMicroforme()));
						}
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeMicroforme(catSpecFormeMicroforme);
					}
					//Schede
					if (catH.getSchede() != null && catH.getSchede()) {
						CatSpecFormeSchede catSpecFormeSchede = new CatSpecFormeSchede();
						if (isNullSafeNotZero(catH.getPercentualeSchede())){
							catSpecFormeSchede.setPercentuale(String.valueOf(catH.getPercentualeSchede()));
						}	
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeSchede(catSpecFormeSchede);
					}
					//Volumi
					if (catH.getVolume() != null && catH.getVolume()) {
						CatSpecFormeVolume catSpecFormeVolume = new CatSpecFormeVolume();
						if (isNullSafeNotZero(catH.getPercentualeVolume())){
							catSpecFormeVolume.setPercentuale(String.valueOf(catH.getPercentualeVolume()));
						}
						if ((catH.getDescrizioneVolume() != null) && (catH.getDescrizioneVolume().trim().length()>0))
							catSpecFormeVolume.setCitazioneBibliografica(catH.getDescrizioneVolume().trim());
						if(catSpecForme == null)
							catSpecForme = new CatSpecForme();
						catSpecForme.setCatSpecFormeVolume(catSpecFormeVolume);
					}

					if(catSpecForme != null) {
						catSpecMateriale.setCatSpecForme(catSpecForme);
					}

					/* COPERTURA del catalogo */
					if (isNullSafeNotZero(catH.getDaAnno()) && isNullSafeNotZero(catH.getAAnno()) && String.valueOf(catH.getDaAnno()).length() == 4 && String.valueOf(catH.getAAnno()).length() == 4) {
						CatSpecFormeCopertura catSpecFormeCopertura = new CatSpecFormeCopertura();
						catSpecFormeCopertura.setAdAnno(String.valueOf(catH.getAAnno()));
						catSpecFormeCopertura.setDaAnno(String.valueOf(catH.getDaAnno()));
						catSpecMateriale.setCatSpecFormeCopertura(catSpecFormeCopertura);
					}

					materiali.add(catSpecMateriale);

					if (catCollH.indexOf(catH) == 0) {
						catSpecMateriali = new CatSpecMateriali();

					} else {
						if (lastIdCatColl == catH.getCataloghiCollettivi().getIdCataloghiCollettivi().intValue()) {
							catSpecMateriali = catalogoCollettivo.getCatSpecMateriali(); 

						} else {
							catSpecMateriali = new CatSpecMateriali();
						}
					}

					CatSpecMateriale[] catSpecMateriales = new CatSpecMateriale[materiali.size()];
					catSpecMateriales = (CatSpecMateriale[]) materiali.toArray(catSpecMateriales);
					catSpecMateriali.setCatSpecMateriale(catSpecMateriales);

					catalogoCollettivo.setCatSpecMateriali(catSpecMateriali);
				}
			}

			if (catCollH.indexOf(catH) == 0) {
				log.debug("Aggiunto catalogo collettivo: " + catalogoCollettivo.getNome());
				catalColl.add(catalogoCollettivo);

			} else {
				if (lastIdCatColl == catH.getCataloghiCollettivi().getIdCataloghiCollettivi().intValue()) {
					log.debug("Aggiornato catalogo collettivo: " + catalogoCollettivo.getNome());

				} else {
					log.debug("Aggiunto catalogo collettivo: " + catalogoCollettivo.getNome());
					catalColl.add(catalogoCollettivo);
				}
			}
			lastIdCatColl = catH.getCataloghiCollettivi().getIdCataloghiCollettivi().intValue();
		}

		if (catalColl.size() > 0) {
			CatalogoCollettivo[] cataloghiCollettiviArray = new CatalogoCollettivo[catalColl.size()];
			cataloghiCollettiviArray = (CatalogoCollettivo[]) catalColl.toArray(cataloghiCollettiviArray);
			it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi cataloghiCollettivi = new it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi();
			cataloghiCollettivi.setCatalogoCollettivo(cataloghiCollettiviArray);
			cataloghi.setCataloghiCollettivi(cataloghiCollettivi);
			log.debug("Settati cataloghi collettivi nella parte Cataloghi");
		} else {
			log.debug("Non sono presenti cataloghi collettivi per la biblioteca");
		}
	}

	private static void Cataloghi_setCataloghiGenerali(Cataloghi cataloghi, Biblioteca bibliotecaDb) {
		/*
		 * CATALOGHI: catalogo GENERALE
		 */
		ArrayList<CatalogoGenerale> catalGen = new ArrayList<CatalogoGenerale>();
		List<PartecipaCataloghiGenerali> catalGetH = bibliotecaDb.getPartecipaCataloghiGeneralis();
		for (Iterator<PartecipaCataloghiGenerali> i = catalGetH.iterator(); i.hasNext();) {
			CatalogoGenerale catGen = new CatalogoGenerale();
			PartecipaCataloghiGenerali catGenH = i.next();
			//COPERTURA
			if (isNullSafeNotZero(catGenH.getDaAnno()) && isNullSafeNotZero(catGenH.getAAnno()) && String.valueOf(catGenH.getDaAnno()).length() == 4 && String.valueOf(catGenH.getAAnno()).length() == 4) {
				Copertura copertura = new Copertura();
				copertura.setDaAnno(String.valueOf(catGenH.getDaAnno()));
				copertura.setAdAnno(String.valueOf(catGenH.getAAnno()));
				catGen.setCopertura(copertura);
			}
			//SUPPORTO (Forme) del catalogo
			//Informatizzato
			Forme forme = null;
			if (catGenH.getCataloghiSupportoDigitaleTipo() != null) {// && !"Altro".equalsIgnoreCase(catGenH.getCataloghiSupportoDigitaleTipo().getDescrizione())) {
				Digitale dig = new Digitale();
				if (isNullSafeNotZero(catGenH.getPercentualeInformatizzata())){
					dig.setPercentuale(String.valueOf(catGenH.getPercentualeInformatizzata()));
				}
				/* Supporto */
				dig.setSupporto(catGenH.getCataloghiSupportoDigitaleTipo().getDescrizione());

				/* Se supporto = 'Online', lista url */
				if (catGenH.getCataloghiSupportoDigitaleTipo().getDescrizione().equalsIgnoreCase("Online") 
						&& catGenH.getCataloghiGeneraliUrls() != null && catGenH.getCataloghiGeneraliUrls().size() > 0) {
					String[] surlList = new String[catGenH.getCataloghiGeneraliUrls().size()];

					int curl = 0;
					for (CataloghiGeneraliUrl entry : catGenH.getCataloghiGeneraliUrls()) {
						surlList[curl] = entry.getUrl();
						curl++;
					}

					dig.setUrl(surlList);
				}

				if (forme == null)
					forme = new Forme();
				forme.setDigitale(dig);
			}
			//Microforme
			if (catGenH.getMicroforme() != null && catGenH.getMicroforme()) {
				Microforme mic = new Microforme();
				if (isNullSafeNotZero(catGenH.getPercentualeMicroforme())){
					mic.setPercentuale(String.valueOf(catGenH.getPercentualeMicroforme()));
				}
				if(forme == null)
					forme = new Forme();
				forme.setMicroforme(mic);
			}
			//Schede
			if (catGenH.getSchede() != null && catGenH.getSchede()) {
				Schede sch = new Schede();
				if (isNullSafeNotZero(catGenH.getPercentualeSchede())){
					sch.setPercentuale(String.valueOf(catGenH.getPercentualeSchede()));
				}
				if(forme == null)
					forme = new Forme();
				forme.setSchede(sch);
			}
			//Volumi
			if (catGenH.getVolume() != null && catGenH.getVolume()) {
				Volume vol = new Volume();
				vol.setCitazioneBibliografica(catGenH.getDescrizioneVolume());
				if (isNullSafeNotZero(catGenH.getPercentualeVolume())){
					vol.setPercentuale(String.valueOf(catGenH.getPercentualeVolume()));
				}
				if(forme == null)
					forme = new Forme();
				forme.setVolume(vol);
			}
			if (forme != null)
				catGen.setForme(forme);
			//TIPO CATALOGO
			if (catGenH.getCatalogoGeneraleTipo() != null)
				catGen.setTipo(catGenH.getCatalogoGeneraleTipo().getDescrizione());

			log.debug("Aggiunto catalogo generale");
			catalGen.add(catGen);
		}
		if (catalGen.size() > 0) {
			CatalogoGenerale[] cataloghiGeneraliArray = new CatalogoGenerale[catalGen.size()];
			cataloghiGeneraliArray = (CatalogoGenerale[]) catalGen.toArray(cataloghiGeneraliArray);
			CataloghiGenerali cataloghiGenerali = new CataloghiGenerali();
			cataloghiGenerali.setCatalogoGenerale(cataloghiGeneraliArray);
			cataloghi.setCataloghiGenerali(cataloghiGenerali);
			log.debug("Settati cataloghi generali nella parte Cataloghi");
		} else {
			log.debug("Non sono presenti cataloghi generali per la biblioteca");
		}
	}

	private static void Cataloghi_setCataloghiSpeciali(Cataloghi cataloghi, Biblioteca bibliotecaDb) {
		/*
		 * CATALOGHI: catalogo SPECIALE
		 */
		ArrayList<CatalogoSpeciale> catalSpe = new ArrayList<CatalogoSpeciale>();
		List<PartecipaCataloghiSpecialiMateriale> catalSpeH = bibliotecaDb.getPartecipaCataloghiSpecialiMateriales();

		for (Iterator<PartecipaCataloghiSpecialiMateriale> i = catalSpeH.iterator(); i.hasNext();) {
			CatalogoSpeciale catSpe = new CatalogoSpeciale();
			PartecipaCataloghiSpecialiMateriale catSpeH = i.next();

			/* NOME (Descrizione piccola voce di patrimonio) */
			catSpe.setNome(catSpeH.getDenominazione());

			/* MATERIALI - Materiale (Descrizione grande voce di patrimonio) */
			ArrayList<CatSpecMateriale> materiali = new ArrayList<CatSpecMateriale>();

			CatSpecMateriale catSpecMateriale = new CatSpecMateriale();
			PatrimonioSpecializzazione ps = catSpeH.getPatrimonioSpecializzazione();

			catSpecMateriale.setNome(ps.getDescrizione());

			/* SUPPORTO (Forme) del catalogo */
			CatSpecForme catSpecForme = new CatSpecForme();
			//Informatizzato
			if (catSpeH.getCataloghiSupportoDigitaleTipo() != null && !"Altro".equalsIgnoreCase(catSpeH.getCataloghiSupportoDigitaleTipo().getDescrizione())) {
				CatSpecFormeDigitale catSpecFormeDigitale = new CatSpecFormeDigitale();
				if (isNullSafeNotZero(catSpeH.getPercentualeInformatizzata())){
					catSpecFormeDigitale.setPercentuale(String.valueOf(catSpeH.getPercentualeInformatizzata()));
				}
				catSpecForme.setCatSpecFormeDigitale(catSpecFormeDigitale);
			}
			//Microforme
			if (catSpeH.getMicroforme() != null && catSpeH.getMicroforme()) {
				CatSpecFormeMicroforme catSpecFormeMicroforme = new CatSpecFormeMicroforme();
				if (isNullSafeNotZero(catSpeH.getPercentualeMicroforme())) {
					catSpecFormeMicroforme.setPercentuale(String.valueOf(catSpeH.getPercentualeMicroforme()));
				}	
				catSpecForme.setCatSpecFormeMicroforme(catSpecFormeMicroforme);
			}
			//Schede
			if (catSpeH.getSchede() != null && catSpeH.getSchede()) {
				CatSpecFormeSchede catSpecFormeSchede = new CatSpecFormeSchede();
				if (isNullSafeNotZero(catSpeH.getPercentualeSchede())){
					catSpecFormeSchede.setPercentuale(String.valueOf(catSpeH.getPercentualeSchede()));
				}
				catSpecForme.setCatSpecFormeSchede(catSpecFormeSchede);
			}
			//Volumi
			if (catSpeH.getVolume() != null && catSpeH.getVolume()) {
				CatSpecFormeVolume catSpecFormeVolume = new CatSpecFormeVolume();
				if (isNullSafeNotZero(catSpeH.getPercentualeVolume())){
					catSpecFormeVolume.setPercentuale(String.valueOf(catSpeH.getPercentualeVolume()));
				}
				if (catSpeH.getDescrizioneVolume() != null)
					catSpecFormeVolume.setCitazioneBibliografica(catSpeH.getDescrizioneVolume());
				catSpecForme.setCatSpecFormeVolume(catSpecFormeVolume);
			}

			if (catSpecForme != null) {
				catSpecMateriale.setCatSpecForme(catSpecForme);
			}

			/* COPERTURA */
			if (isNullSafeNotZero(catSpeH.getDaAnno()) && isNullSafeNotZero(catSpeH.getAAnno()) && String.valueOf(catSpeH.getDaAnno()).length() == 4 && String.valueOf(catSpeH.getAAnno()).length() == 4) {
				CatSpecFormeCopertura catSpecFormeCopertura = new CatSpecFormeCopertura();
				catSpecFormeCopertura.setAdAnno(String.valueOf(catSpeH.getAAnno()));
				catSpecFormeCopertura.setDaAnno(String.valueOf(catSpeH.getDaAnno()));
				catSpecMateriale.setCatSpecFormeCopertura(catSpecFormeCopertura);
			}

			materiali.add(catSpecMateriale);

			if (materiali.size() > 0) {
				CatSpecMateriali catSpecMateriali = new CatSpecMateriali();
				CatSpecMateriale[] catSpecMateriales = new CatSpecMateriale[materiali.size()];
				catSpecMateriales = (CatSpecMateriale[]) materiali.toArray(catSpecMateriales);
				catSpecMateriali.setCatSpecMateriale(catSpecMateriales);
				catSpe.setCatSpecMateriali(catSpecMateriali);
			}

			log.debug("Aggiunto catalogo speciale: " + catSpe.getNome());
			catalSpe.add(catSpe);
		}
		if (catalSpe.size() > 0) {
			CatalogoSpeciale[] cataloghiSpecialiArray = new CatalogoSpeciale[catalSpe.size()];
			cataloghiSpecialiArray = (CatalogoSpeciale[]) catalSpe.toArray(cataloghiSpecialiArray);
			CataloghiSpeciali cataloghiSpeciali = new CataloghiSpeciali();
			cataloghiSpeciali.setCatalogoSpeciale(cataloghiSpecialiArray);
			cataloghi.setCataloghiSpeciali(cataloghiSpeciali);
			log.debug("Settati cataloghi speciali nella parte Cataloghi");
		} else {
			log.debug("Non sono presenti cataloghi speciali per la biblioteca");
		}
	}

	private static void Patrimonio_setCatalogoTopografico(it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio, Biblioteca bibliotecaDb) {
		CatalogoTopografico catalogoTopografico = null;

		if (BooleanUtils.isTrue(bibliotecaDb.getCatalogoTopograficoCartaceo())) {
			if (catalogoTopografico == null) catalogoTopografico = new CatalogoTopografico();
			catalogoTopografico.setCartaceo(SiNoType.S);
		} else if (BooleanUtils.isFalse(bibliotecaDb.getCatalogoTopograficoCartaceo())) {
			if (catalogoTopografico == null) catalogoTopografico = new CatalogoTopografico();
			catalogoTopografico.setCartaceo(SiNoType.N);
		}

		if (BooleanUtils.isTrue(bibliotecaDb.getCatalogoTopograficoInformatizzato())) {
			if (catalogoTopografico == null) catalogoTopografico = new CatalogoTopografico();
			catalogoTopografico.setInformatizzato(SiNoType.S);
		} else if (BooleanUtils.isFalse(bibliotecaDb.getCatalogoTopograficoInformatizzato())) {
			if (catalogoTopografico == null) catalogoTopografico = new CatalogoTopografico();
			catalogoTopografico.setInformatizzato(SiNoType.N);
		}


		if (catalogoTopografico != null){
			patrimonio.setCatalogoTopografico(catalogoTopografico);
			log.debug("Settato catalogo topografico nella parte Patrimonio");
		}
	}

	private static void Patrimonio_setFondiAntichi(it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio, Biblioteca bibliotecaDb) {
		FondiAntichi fondiAntichi = new FondiAntichi();
		if (bibliotecaDb.getFondiAntichiConsistenza() != null) {
			if ("1".equals(bibliotecaDb.getFondiAntichiConsistenza().getDescrizione())) {
				fondiAntichi.setVolumi(FondoAnticoTypeVolumiType.VALUE_0);
				patrimonio.setFondiAntichi(fondiAntichi);
			} else if ("2".equals(bibliotecaDb.getFondiAntichiConsistenza().getDescrizione())) {
				fondiAntichi.setVolumi(FondoAnticoTypeVolumiType.VALUE_1);
				patrimonio.setFondiAntichi(fondiAntichi);
			} else if ("3".equals(bibliotecaDb.getFondiAntichiConsistenza().getDescrizione())) {
				fondiAntichi.setVolumi(FondoAnticoTypeVolumiType.VALUE_2);
				patrimonio.setFondiAntichi(fondiAntichi);
			}
		}
		log.debug("Settati fondi antichi nella parte Patrimonio");
	}

	private static void Patrimonio_setFondiSpeciali(it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio, Biblioteca bibliotecaDb) {
		ArrayList<FondoSpeciale> fondiSpe = new ArrayList<FondoSpeciale>();
		for (Iterator<FondiSpeciali> i = bibliotecaDb.getFondiSpecialis().iterator(); i.hasNext();) {
			FondoSpeciale fSpec = new FondoSpeciale();
			FondiSpeciali fSpecH = i.next();

			//NOME
			fSpec.setNome(fSpecH.getDenominazione());
			//DESCRIZIONE
			if((fSpecH.getDescrizione() != null )&& (fSpecH.getDescrizione().trim().length() > 0))
				fSpec.setDescrizione(fSpecH.getDescrizione().trim());
			//DEPOSITATO
			if (BooleanUtils.isTrue(fSpecH.getFondoDepositato()))
				fSpec.setDepositato(SiNoType.S);
			else if (BooleanUtils.isFalse(fSpecH.getFondoDepositato()))
				fSpec.setDepositato(SiNoType.N);


			//SUPPORTO
			//O:Online viene settato il supporto e anche la digitalizzazione
			if (fSpecH.getFondiSpecialiCatalogazioneInventario() != null) {
				FondiSpecialiCatalogazioneInventario  fsci = fSpecH.getFondiSpecialiCatalogazioneInventario();
				if ("Online".equalsIgnoreCase(fsci.getDescrizione())){
					fSpec.setCatalogoInventario(CatalogoInventarioType.O);
					fSpec.setDigitalizzazione(SiNoType.S);
				}
				else{
					fSpec.setDigitalizzazione(SiNoType.N);
				}
				if ("Non presente".equalsIgnoreCase(fsci.getDescrizione()))
					fSpec.setCatalogoInventario(CatalogoInventarioType.N);
				if ("Schede".equalsIgnoreCase(fsci.getDescrizione()))
					fSpec.setCatalogoInventario(CatalogoInventarioType.S);
				if ("Volumi".equalsIgnoreCase(fsci.getDescrizione()))
					fSpec.setCatalogoInventario(CatalogoInventarioType.V);
				//URL		
				if ((fSpecH.getCatalogazioneInventarioUrl() != null) && (fSpecH.getCatalogazioneInventarioUrl().trim().length()>0))
					fSpec.setCatalogoInventarioUrl(fSpecH.getCatalogazioneInventarioUrl().trim());
			}
			//ID DEWEY
			if (fSpecH.getDewey() != null) {
				fSpec.setFondoSpecialeCdd(fSpecH.getDewey().getIdDewey());
			}			
			log.debug("Aggiunto fondo speciale inventario: " + fSpec.getNome());
			fondiSpe.add(fSpec);
		}
		if (fondiSpe.size() > 0) {
			FondoSpeciale[] fondoSpecialeArray = new FondoSpeciale[fondiSpe.size()];
			fondoSpecialeArray = (FondoSpeciale[]) fondiSpe.toArray(fondoSpecialeArray);
			it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali fondiSpeciali = new it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali();
			fondiSpeciali.setFondoSpeciale(fondoSpecialeArray);
			patrimonio.setFondiSpeciali(fondiSpeciali);
			log.debug("Settati fondi speciali nella parte Patrimonio");
		} else {
			log.debug("Non sono presenti fondi speciali per la biblioteca");
		}
	}

	private static void Patrimonio_setMateriale(it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio, Biblioteca bibliotecaDb) {
		ArrayList<Materiale> materiale = new ArrayList<Materiale>();
		for (Iterator<Patrimonio> i = bibliotecaDb.getPatrimonios().iterator(); i.hasNext();) {
			Materiale mat = new Materiale();
			Patrimonio matH = i.next();
			PatrimonioSpecializzazione ps = matH.getPatrimonioSpecializzazione();
			mat.setNome(ps.getDescrizione());
			if (isNullSafeNotZero(matH.getQuantitaUltimoAnno())) {
				mat.setAcquistiUltimoAnno(matH.getQuantitaUltimoAnno());
			}
			if (isNullSafeNotZero(matH.getQuantita())) {
				mat.setPosseduto(matH.getQuantita());
			}
			log.debug("Aggiunto materiale al patrimonio: " + mat.getNome());
			materiale.add(mat);
		}
		if (materiale.size() > 0) {
			Materiale[] materialeArray = new Materiale[materiale.size()];
			materialeArray = (Materiale[]) materiale.toArray(materialeArray);
			Materiali materiali = new Materiali();
			materiali.setMateriale(materialeArray);
			patrimonio.setMateriali(materiali);
			log.debug("Settati materiali nella parte Patrimonio");
		} else {
			log.debug("Non sono presenti materiali per la biblioteca");
		}
	}

	private static void Patrimonio_setPatrimonioInventario(it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio, Biblioteca bibliotecaDb) {
		PatrimonioInventario pi = null;

		if (BooleanUtils.isTrue(bibliotecaDb.getInventarioInformatizzato())) {
			if (pi == null) pi = new PatrimonioInventario();
			pi.setInformatizzato(SiNoType.S);
		} else if (BooleanUtils.isFalse(bibliotecaDb.getInventarioInformatizzato())) {
			if (pi == null) pi = new PatrimonioInventario();
			pi.setInformatizzato(SiNoType.N);
		}

		if (BooleanUtils.isTrue(bibliotecaDb.getInventarioCartaceo())) {
			if (pi == null) pi = new PatrimonioInventario();
			pi.setCartaceo(SiNoType.S);
		} else if (BooleanUtils.isFalse(bibliotecaDb.getInventarioCartaceo())) {
			if (pi == null) pi = new PatrimonioInventario();
			pi.setCartaceo(SiNoType.N);
		}

		if (pi != null){
			log.debug("Settato patrimonio Inventario nella parte Patrimonio");
			patrimonio.setPatrimonioInventario(pi);
		}
	}

	private static void Amministrativa_setBilancio(Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		Bilancio bilancio = new Bilancio();
		boolean settatoE = false;
		if (isNullSafeNotZero(bibliotecaDb.getBilancioEntrate())) {
			bilancio.setEntrate(new BigDecimal(bibliotecaDb.getBilancioEntrate()));
			settatoE = true;
		}
		Uscite uscite = new Uscite();
		boolean settatoU = false;
		if (isNullSafeNotZero(bibliotecaDb.getBilancioUscite())) {
			uscite.setTotale(new BigDecimal(bibliotecaDb.getBilancioUscite()));
			settatoU = true;
		}
		if (isNullSafeNotZero(bibliotecaDb.getBilancioUsciteVarie())) {
			uscite.setAltre(new BigDecimal(bibliotecaDb.getBilancioUsciteVarie()));
			settatoU = true;
		}
		if (isNullSafeNotZero(bibliotecaDb.getBilancioUsciteAutomazione())) {
			uscite.setAutomazione(new BigDecimal(bibliotecaDb.getBilancioUsciteAutomazione()));
			settatoU = true;
		}
		if (isNullSafeNotZero(bibliotecaDb.getBilancioUsciteFunzionamento())) {
			uscite.setFunzionamento(new BigDecimal(bibliotecaDb.getBilancioUsciteFunzionamento()));
			settatoU = true;
		}
		if (isNullSafeNotZero(bibliotecaDb.getBilancioPatrimonialePosseduto())) {
			uscite.setPatrimonio(new BigDecimal(bibliotecaDb.getBilancioPatrimonialePosseduto()));
			settatoU = true;
		}
		if (isNullSafeNotZero(bibliotecaDb.getBilancioUscitePersonale())) {
			uscite.setPersonale(new BigDecimal(bibliotecaDb.getBilancioUscitePersonale()));
			settatoU = true;
		}
		if (settatoU)
			bilancio.setUscite(uscite);
		if (settatoE || settatoU)
			amministrativa.setBilancio(bilancio);
	}

	private static void Amministrativa_setCarteServizi(Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		if (bibliotecaDb.getServiziBibliotecariCartas() != null) {
			CartaServizi cs = new CartaServizi();
			ArrayList<String> servizi = new ArrayList<String>();
			for (Iterator<ServiziBibliotecariCarta> i = bibliotecaDb.getServiziBibliotecariCartas().iterator(); i.hasNext();) {
				ServiziBibliotecariCarta sb = i.next();
				String  descr = sb.getDescrizione();
				if (descr != null) {
					servizi.add(descr);
				}
			}
			if (servizi.size() > 0) {
				cs.setServizio(servizi.toArray(new String[servizi.size()]));
				amministrativa.setCartaServizi(cs);
			}
		}
	}

	private static void Amministrativa_setDepositoLegale(Amministrativa amministrativa, Biblioteca bibliotecaDb) {

		ArrayList<DepositoLegale> depLeg = new ArrayList<DepositoLegale>();

		List<DepositiLegali> depositiLegalis = bibliotecaDb.getDepositiLegalis();

		if (BooleanUtils.isTrue(bibliotecaDb.getAttivoDepositoLegale())) {
			for (Iterator<DepositiLegali> i = depositiLegalis.iterator(); i.hasNext();) { 
				DepositoLegale depositoLegale = new DepositoLegale();
				DepositiLegali dep = i.next(); 
				String descr = dep.getDepositiLegaliTipo().getDescrizione();
				String daAnno  = dep.getDaAnno();
				if (daAnno != null) {
					depositoLegale.setAnnoInizio(daAnno);
				}
				if ((descr != null) && (descr.length() > 0)) {
					depositoLegale.setTipo(descr);
				}
				
				depLeg.add(depositoLegale);
			}
			
			it.inera.abi.logic.formatodiscambio.castor.DepositiLegali deplegs = new it.inera.abi.logic.formatodiscambio.castor.DepositiLegali();
			deplegs.setAttivo(SiNoType.S);
			log.debug("AttivoDepositoLegale: " + true);
			
			if (depLeg.size() > 0) {
				deplegs.setDepositoLegale(depLeg.toArray(new DepositoLegale[depLeg.size()]));
				log.debug("Settati depositi legali nella parte Amministrativa");
				
			} else {
				log.debug("Non sono presenti depositi legali per la biblioteca");
			}
			
			amministrativa.setDepositiLegali(deplegs);
			
		} else if (BooleanUtils.isFalse(bibliotecaDb.getAttivoDepositoLegale())) {
			it.inera.abi.logic.formatodiscambio.castor.DepositiLegali deplegs = new it.inera.abi.logic.formatodiscambio.castor.DepositiLegali();
			deplegs.setAttivo(SiNoType.N);
			log.debug("AttivoDepositoLegale: " + true);
			
			amministrativa.setDepositiLegali(deplegs);
			
		}
		
	}

	private static void Amministrativa_setEnte(Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.Ente ente = new it.inera.abi.logic.formatodiscambio.castor.Ente();
		Ente enteH = bibliotecaDb.getEnte();
		if(enteH.getCodiceFiscale() != null)
			ente.setCodiceFiscale(enteH.getCodiceFiscale());
		if ((enteH.getDenominazione() != null) && (enteH.getDenominazione().trim().length() > 0)) {
			ente.setNome(enteH.getDenominazione().trim());
		} else {
			ente.setNome("");
		}
		if((enteH.getPartitaIva() != null)&&(enteH.getPartitaIva().trim().length()>0))
			ente.setPartitaIVA(enteH.getPartitaIva());
		/* commento codice nuovo: ci va messo la sigla(IT) o la denominazione(Italia) ?
		 if((enteH.getStato() != null)&&
				(enteH.getStato().getDenominazione()!=null)&&
				(enteH.getStato().getDenominazione().trim().length()>0))
			ente.setStato(enteH.getStato().getDenominazione().trim());*/
		if((enteH.getStato() != null)&&
				(enteH.getStato().getSigla()!=null)&&
				(enteH.getStato().getSigla().trim().length()>0))
			ente.setStato(enteH.getStato().getSigla().trim());

		if((enteH.getEnteTipologiaAmministrativa().getDescrizione() != null)&&(enteH.getEnteTipologiaAmministrativa().getDescrizione().trim().length()>0))
			ente.setTipologiaAmministrativa(enteH.getEnteTipologiaAmministrativa().getDescrizione().trim());
		if((bibliotecaDb.getTipologiaFunzionale().getDescrizione() != null)&&(bibliotecaDb.getTipologiaFunzionale().getDescrizione().trim().length()>0))
			ente.setTipologiaFunzionale(bibliotecaDb.getTipologiaFunzionale().getDescrizione().trim());
		amministrativa.setEnte(ente);
	}

	private static void Amministrativa_setPersonale(Amministrativa amministrativa, Biblioteca bibliotecaDb) {

		Personale personale = null;
		if (isNullSafeNotZero(bibliotecaDb.getPersonaleEsterno())){
			if (personale == null)
				personale = new Personale();
			personale.setEsterno(bibliotecaDb.getPersonaleEsterno());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPersonalePartTime())){
			if(personale == null)
				personale = new Personale();
			personale.setPartTime(bibliotecaDb.getPersonalePartTime());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPersonaleTemporaneo())){
			if(personale == null)
				personale = new Personale();
			personale.setTemporaneo(bibliotecaDb.getPersonaleTemporaneo());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPersonaleTotale())){
			if(personale == null)
				personale = new Personale();
			personale.setTotale(bibliotecaDb.getPersonaleTotale());
		}
		if (personale != null)
			amministrativa.setPersonale(personale);
	}

	private static void Amministrativa_setRegolamento(
			Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.Regolamento regolamento = null;
		for (Iterator<Regolamento> i = bibliotecaDb.getRegolamentos().iterator(); i.hasNext();) {
			Regolamento regH = i.next();
			if(regH.getRiferimentoNormativa() != null){
				if(regolamento == null)
					regolamento = new it.inera.abi.logic.formatodiscambio.castor.Regolamento();
				if (regH.getRiferimentoNormativa().trim().length() > 0)
					regolamento.setNorma(regH.getRiferimentoNormativa().trim());
				if ((regH.getUrl() != null) && (regH.getUrl().trim().length() > 0))
					regolamento.setUrl(regH.getUrl().trim());
			}
		}
		if(regolamento != null)
			amministrativa.setRegolamento(regolamento);
	}

	private static void Amministrativa_setStrutture(Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		Strutture strutture = null;
		//Numero postazioni
		Postazioni postazioni = null;
		if (isNullSafeNotZero(bibliotecaDb.getPostiAudio())){
			if(postazioni == null) 
				postazioni = new Postazioni();
			postazioni.setAudio(bibliotecaDb.getPostiAudio());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPostiInternet())){
			if(postazioni == null) 
				postazioni = new Postazioni();
			postazioni.setInternet(bibliotecaDb.getPostiInternet());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPostiLettura())) {
			if(postazioni == null) 
				postazioni = new Postazioni();
			postazioni.setLettura(bibliotecaDb.getPostiLettura());
		}
		if (isNullSafeNotZero(bibliotecaDb.getPostiVideo())){
			if(postazioni == null) 
				postazioni = new Postazioni();
			postazioni.setVideo(bibliotecaDb.getPostiVideo());
		}
		if(postazioni != null){
			if (strutture == null) strutture = new Strutture();
			strutture.setPostazioni(postazioni);
		}
		//Scaffalature
		Scaffalature scaffalature = null;


		BigDecimal bd = null;

		if (isNullSafeNotZero(bibliotecaDb.getMlMagazzini())){
			bd = new BigDecimal(bibliotecaDb.getMlMagazzini());
			if (scaffalature == null) 
				scaffalature = new Scaffalature();
			scaffalature.setMagazzino(bd);
		}

		if (isNullSafeNotZero(bibliotecaDb.getMlAperti())){
			bd = new BigDecimal(bibliotecaDb.getMlAperti());
			if (scaffalature == null) 
				scaffalature = new Scaffalature();
			scaffalature.setPubbliche(bd);
		}

		if(scaffalature != null){
			if(strutture == null) strutture = new Strutture();
			strutture.setScaffalature(scaffalature);
		}

		//Superficie
		Superficie superficie = null;

		if (bibliotecaDb.getMqPubblici() != null) {
			int mqPubblici = bibliotecaDb.getMqPubblici();
			if(isNullSafeNotZero(mqPubblici)){
				bd = new BigDecimal(mqPubblici);
				if(superficie == null) 
					superficie = new Superficie();
				superficie.setAlPubblico(bd);
			}
		}

		if (bibliotecaDb.getMqTotali() != null) {
			int supTotale = bibliotecaDb.getMqTotali();
			if (isNullSafeNotZero(supTotale)) {
				bd = new BigDecimal(supTotale);
				if(superficie == null) 
					superficie = new Superficie();
				superficie.setTotale(bd);
			}
		}

		if(superficie != null) {
			if(strutture == null) strutture = new Strutture();
			strutture.setSuperficie(superficie);
		}

		if(superficie != null || scaffalature != null || postazioni != null)
			amministrativa.setStrutture(strutture);
	}

	private static void Amministrativa_setUtenti(Amministrativa amministrativa, Biblioteca bibliotecaDb) {
		Utenti utenti = null;
		if (isNullSafeNotZero(bibliotecaDb.getUtentiIscritti())){
			if(utenti == null) utenti = new Utenti();
			utenti.setIscrittiPrestito(bibliotecaDb.getUtentiIscritti());
		}
		if (isNullSafeNotZero(bibliotecaDb.getUtentiUnder14())){
			if (utenti == null) utenti = new Utenti();
			utenti.setMinoriQuattordiciAnni(bibliotecaDb.getUtentiUnder14());
		}
		if (isNullSafeNotZero(bibliotecaDb.getUtentiIscrittiPrestitoAnno())) {
			if (utenti == null)	utenti = new Utenti();
			utenti.setUltimoAnno(bibliotecaDb.getUtentiIscrittiPrestitoAnno());
		}
		if (utenti != null)	amministrativa.setUtenti(utenti);
	}

	private static void Servizi_setAccesso(Servizi servizi, Biblioteca bibliotecaDb) {

		boolean init = false;

		Accesso accesso = new Accesso();
		
		if (bibliotecaDb.getAccessoHandicap() != null) {
			if (BooleanUtils.isTrue(bibliotecaDb.getAccessoHandicap())) {
				accesso.setHandicap(SiNoType.S);
				init = true;
				
			} else if (BooleanUtils.isFalse(bibliotecaDb.getAccessoHandicap())) {
				accesso.setHandicap(SiNoType.N);
				init = true;
			}
		}
		
		// Modalità di accesso
		ArrayList<String> modalitaAccessoList = new ArrayList<String>();
		for(Iterator<AccessoModalita> i = bibliotecaDb.getAccessoModalitas().iterator(); i.hasNext();){
			AccessoModalita accessoModalita = i.next();
			modalitaAccessoList.add(accessoModalita.getDescrizione());
			init = true;
		}

		if (modalitaAccessoList.size() > 0) {
			String[] modalitaAccessoArray = new String[modalitaAccessoList.size()];
			modalitaAccessoArray = (String[]) modalitaAccessoList.toArray(modalitaAccessoArray);
			
			ModalitaAccesso modalita = new ModalitaAccesso();
			modalita.setModo(modalitaAccessoArray);
			
			accesso.setModalitaAccesso(modalita);
			init = true;
			log.debug("Settate modalità di accesso nella parte servizi");
		} else {
			log.debug("Non sono presenti condizioni di accesso per la biblioteca");
		}
		
		// Accesso riservato
		if (BooleanUtils.isTrue(bibliotecaDb.getAccessoRiservato())){
			accesso.setAperta(SiNoType.N);
			init = true;
			
		} else if (BooleanUtils.isFalse(bibliotecaDb.getAccessoRiservato())){
			accesso.setAperta(SiNoType.S);
			init = true;
		}

		it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali ds = new it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali();
		for (Iterator<DestinazioniSociali> i = bibliotecaDb.getDestinazioniSocialis().iterator();i.hasNext();) {
			DestinazioniSociali destinazioniSociale = i.next();
			boolean daAggiungere = false;
			it.inera.abi.logic.formatodiscambio.castor.Destinazione d = new it.inera.abi.logic.formatodiscambio.castor.Destinazione();
			String descr = destinazioniSociale.getDestinazioniSocialiTipo().getDescrizione();
			String note = destinazioniSociale.getNote();

			if (descr!= null && !"".equals(descr)) {
				d.setValore(descr);
				daAggiungere = true;
			}
			if (note != null && !"".equals(note)) {
				d.setNote(note);
			}

			if (daAggiungere) {
				ds.addDestinazione(d);
			}
		}
		if (ds.getDestinazioneCount()>0) {
			accesso.setDestinazioniSociali(ds);
			init = true;
		}
		if (init) servizi.setAccesso(accesso);
	}

	private static void Servizi_setInformazioniBibliografiche(Servizi servizi, Biblioteca bibliotecaDb) {
		InformazioniBibliografiche ib = new InformazioniBibliografiche();

		if (bibliotecaDb.getAttivoInformazioniBibliografiche() != null) {
			if (bibliotecaDb.getAttivoInformazioniBibliografiche().booleanValue() == true) {
				ib.setAttivo(SiNoType.S);

				/* Servizio in sede */
				if (BooleanUtils.isTrue(bibliotecaDb.getGestisceServizioBibliograficoInterno())) {
					ib.setServizioInterno(SiNoType.S);

				} else if (BooleanUtils.isFalse(bibliotecaDb.getGestisceServizioBibliograficoInterno())) {
					ib.setServizioInterno(SiNoType.N);
				}

				/* Servizio esterno */
				if (BooleanUtils.isTrue(bibliotecaDb.getGestisceServizioBibliograficoEsterno())) {
					ServizioEsterno servizioEsterno = new ServizioEsterno();
					ArrayList<String> servEstC = new ArrayList<String>();
					for (Iterator<ServiziInformazioniBibliograficheModalita> i = bibliotecaDb.getServiziInformazioniBibliograficheModalitas().iterator(); i.hasNext();){
						ServiziInformazioniBibliograficheModalita sib = i.next();
						servEstC.add(sib.getDescrizione());
					}
					if (servEstC.size() > 0) {
						String[] modoArrayC = new String[servEstC.size()];
						modoArrayC = (String[]) servEstC.toArray(modoArrayC);
						servizioEsterno.setModo(modoArrayC);
						ib.setServizioEsterno(servizioEsterno);
						log.debug("Settati i servizi esterni nella parte servizi");

					} else {
						log.debug("Non sono presenti servizi esterni per la biblioteca");
					}
				}


			} else {
				ib.setAttivo(SiNoType.N);

				/* Servizio in sede */
				if (bibliotecaDb.getGestisceServizioBibliograficoInterno() != null 
						&& bibliotecaDb.getGestisceServizioBibliograficoInterno().booleanValue() == false) {
					ib.setServizioInterno(SiNoType.N);
				}

				/* Servizio esterno */
				if (bibliotecaDb.getGestisceServizioBibliograficoEsterno() != null 
						&& bibliotecaDb.getGestisceServizioBibliograficoEsterno().booleanValue() == false) {
					/* 
					 * Nel formato di scambio non è gestito il fatto che il servizio esterno non sia fornito;
					 * utilizzando però l'attributo delle informazioni bibliografiche è possibile "pilotare"
					 * la corretta valorizzazione sul database del booleano 'gestisce_servizio_bibliografico_esterno'.
					 */
				}

			}

			servizi.setInformazioniBibliografiche(ib);
		}

	}

	private static void Servizi_setInternet(Servizi servizi, Biblioteca bibliotecaDb) {
		Internet internet = new Internet();

		if (bibliotecaDb.getAttivoAccessoInternet() != null) {
			if (BooleanUtils.isTrue(bibliotecaDb.getAttivoAccessoInternet())) {
				internet.setAttivo(SiNoType.S);

				/* A tempo */
				if (bibliotecaDb.getAccessoInternetTempo() != null) {
					if (bibliotecaDb.getAccessoInternetTempo().booleanValue()) {
						internet.setATempo(SiNoType.S);

					} else {
						internet.setATempo(SiNoType.N);
					}

				} else {
					internet.setATempo(null);
				}

				/* A pagamento */
				if (bibliotecaDb.getAccessoInternetPagamento() != null) {
					if (bibliotecaDb.getAccessoInternetPagamento().booleanValue()) {
						internet.setAPagamento(SiNoType.S);

					} else {
						internet.setAPagamento(SiNoType.N);
					}

				} else {
					internet.setAPagamento(null);
				}

				/* Con proxy */
				if (bibliotecaDb.getAccessoInternetProxy() != null) {
					if (bibliotecaDb.getAccessoInternetProxy().booleanValue()) {
						internet.setConProxy(SiNoType.S);

					} else {
						internet.setConProxy(SiNoType.N);
					}

				} else {
					internet.setConProxy(null);
				}

			} else if (BooleanUtils.isFalse(bibliotecaDb.getAttivoAccessoInternet())) {
				internet.setAttivo(SiNoType.N);

				/* A tempo */
				if (bibliotecaDb.getAccessoInternetTempo() != null 
						&& bibliotecaDb.getAccessoInternetTempo().booleanValue() == false) {
					internet.setATempo(SiNoType.N);
				}

				/* A pagamento */
				if (bibliotecaDb.getAccessoInternetPagamento() != null 
						&& bibliotecaDb.getAccessoInternetPagamento().booleanValue() == false) {
					internet.setAPagamento(SiNoType.N);
				}

				/* Con proxy */
				if (bibliotecaDb.getAccessoInternetProxy() != null 
						&& bibliotecaDb.getAccessoInternetProxy().booleanValue() == false) {
					internet.setConProxy(SiNoType.N);
				}

			}

			servizi.setInternet(internet);
		}

	}

	private static void Servizi_setPrestito(Servizi servizi, Biblioteca bibliotecaDb) {

		Prestito prestito = new Prestito();
		boolean settato = false;
		List<PrestitoInterbibliotecario> prestIntH = bibliotecaDb.getPrestitoInterbibliotecarios();
		if (prestIntH != null) {
			//PRESTITI INTERBIBLIOTECARI
			if (prestIntH.size() > 0) {
				Interbibliotecario interbibliotecario = new Interbibliotecario();
				//interbibliotecario.setInternazionale(SiNoType.N);
				//interbibliotecario.setNazionale(SiNoType.N);
				//interbibliotecario.setPrestitoInterbibliotecarioAutomatizzato(SiNoType.N);
				BigDecimal b = new BigDecimal("0");
				interbibliotecario.setTotalePrestiti(b);
				List<TipoPrestito> tipi = new ArrayList<TipoPrestito>();

				for (PrestitoInterbibliotecario pib : prestIntH) {
					PrestitoInterbibliotecarioModo prestitoInterbibliotecarioModo = pib.getPrestitoInterbibliotecarioModo();
					//Procedure automatizzate
					if (BooleanUtils.isTrue(bibliotecaDb.getProcedureIllAutomatizzate())) {
						TipoPrestito tipo = new TipoPrestito();

						if (pib.getInternazionale() != null) {
							if (pib.getInternazionale().booleanValue()) {// internazionale is true
								tipo.setInternazionale(SiNoType.S);

							} else {// internazionale is false
								tipo.setInternazionale(SiNoType.N);
							}
						}

						if (pib.getNazionale() != null) {
							if (pib.getNazionale().booleanValue()) {// nazionale is true
								tipo.setNazionale(SiNoType.S);

							} else {// nazionale is false
								tipo.setNazionale(SiNoType.N);
							}
						}

						// Ruolo
						if (prestitoInterbibliotecarioModo != null) {
							if (prestitoInterbibliotecarioModo.getIdPrestitoInterbibliotecarioModo().intValue() == 1) {// POS
								tipo.setRuolo(RuoloType.POS);
							}							
							if (prestitoInterbibliotecarioModo.getIdPrestitoInterbibliotecarioModo().intValue() == 2) {// DSC
								tipo.setRuolo(RuoloType.DSC);
							}
						}

						tipi.add(tipo);
					}

					//Internazionale
					/*if("S".equalsIgnoreCase(prestInt.getInternazionale()))
						;//interbibliotecario.setInternazionale(SiNoType.S);
					//Nazionale
					if("S".equalsIgnoreCase(prestInt.getNazionale()))
						;//interbibliotecario.setNazionale(SiNoType.S);*/

					//Totale prestiti interbibliotecari ultimo anno
					if (isNullSafeNotZero(bibliotecaDb.getNPrestitiInterbibliotecariAnnuo())) {
						interbibliotecario.setTotalePrestiti(new BigDecimal(bibliotecaDb.getNPrestitiInterbibliotecariAnnuo()));
					} 
				}
				if (tipi.size() > 0) {
					TipoPrestito[] tipiArray = new TipoPrestito[tipi.size()];
					tipiArray = (TipoPrestito[]) tipi.toArray(tipiArray); 
					interbibliotecario.setTipoPrestito(tipiArray);
					log.debug("Settati i ruoli del prestito interbibliotecario");

				} 
				else {
					log.debug("Non sono presenti ruoli per i prestiti interbibliotecari");
				}
				//SISTEMI ILL per il PRESTITO
				ArrayList<SistemaIll> sistIllC = new ArrayList<SistemaIll>(); 
				for (Iterator<SistemiPrestitoInterbibliotecario> ii = bibliotecaDb.getSistemiPrestitoInterbibliotecarios().iterator(); ii.hasNext();){
					SistemiPrestitoInterbibliotecario  rsi = ii.next();
					SistemaIll sIll = new SistemaIll();
					sIll.setNome(rsi.getDescrizione());
					sistIllC.add(sIll);
				}
				if (sistIllC.size() > 0) {
					SistemaIll[] sistemaIllArray = new SistemaIll[sistIllC.size()];
					sistemaIllArray = (SistemaIll[])sistIllC.toArray(sistemaIllArray);
					interbibliotecario.setSistemaIll(sistemaIllArray);
					log.debug("Settati i SISTEMI ILL nella parte servizi");
				} else {
					log.debug("Non sono presenti SISTEMI ILL per la biblioteca");
				}
				prestito.setInterbibliotecario(interbibliotecario);
				settato = true;
			} else {
				log.debug("Non sono presenti prestiti interbibliotecari per la biblioteca");
			}
			
			/* PRESTITO LOCALE */
			if (bibliotecaDb.getAttivoPrestitoLocale() != null) {
				Locale locale = new Locale();

				if (bibliotecaDb.getAttivoPrestitoLocale().booleanValue() == true) {
					locale.setAttivo(SiNoType.S);
					for (Iterator<PrestitoLocale> i = bibliotecaDb.getPrestitoLocales().iterator(); i.hasNext();) {
						PrestitoLocale pl = i.next();

						/* Automatizzato */
						if (BooleanUtils.isTrue(pl.getAutomatizzato())) {
							locale.setPrestitoLocaleAutomatizzato(SiNoType.S);

						} else if (BooleanUtils.isFalse(pl.getAutomatizzato())) {
							locale.setPrestitoLocaleAutomatizzato(SiNoType.N);
						}

						/* Materiali esclusi */
						if (pl.getPrestitoLocaleMaterialeEscluso() != null && pl.getPrestitoLocaleMaterialeEscluso().size() > 0) {
							MaterialiEsclusi materialiEsclusi = new MaterialiEsclusi();
							List<PrestitoLocaleMaterialeEscluso> materialiEsclusiList = pl.getPrestitoLocaleMaterialeEscluso();

							for (PrestitoLocaleMaterialeEscluso entryMatEscl : materialiEsclusiList) {
								materialiEsclusi.addMaterialeEscluso(entryMatEscl.getDescrizione());
							}

							if (materialiEsclusi.getMaterialeEsclusoCount() > 0) {
								locale.setMaterialiEsclusi(materialiEsclusi);
							}
						}

						/* Durata */
						if (isNullSafeNotZero(pl.getDurataGiorni())) {
							locale.setDurata(String.valueOf(pl.getDurataGiorni()));
						}

						/* Utenti ammessi */
						if (pl.getPrestitoLocaleUtentiAmmessis() != null && pl.getPrestitoLocaleUtentiAmmessis().size() > 0) {
							String[] utentiAmmessi = new String[pl.getPrestitoLocaleUtentiAmmessis().size()];
							int counter = 0;
							List<PrestitoLocaleUtentiAmmessi> utentiAmmessiList = pl.getPrestitoLocaleUtentiAmmessis();

							for (PrestitoLocaleUtentiAmmessi entryUtentiAmmessi : utentiAmmessiList) { 
								if (entryUtentiAmmessi.getDescrizione() != null && entryUtentiAmmessi.getDescrizione().length() > 0) {
									utentiAmmessi[counter] = entryUtentiAmmessi.getDescrizione();
									counter++;
								}
							}

							if (utentiAmmessi.length > 0) {
								locale.setUtentiAmmessi(utentiAmmessi);
							}
						}

						/* Totale prestiti locali ultimo anno */
						if (isNullSafeNotZero(bibliotecaDb.getNPrestitiLocaliAnnuo())) {
							locale.setTotalePrestiti(new BigDecimal(bibliotecaDb.getNPrestitiLocaliAnnuo()));
						}
							
					}

				} else if (bibliotecaDb.getAttivoPrestitoLocale().booleanValue() == false) {
					locale.setAttivo(SiNoType.N);
				}
				
				prestito.setLocale(locale);
				settato = true;
			}

		}

		if (settato) {
			servizi.setPrestito(prestito);
		}
	}

	private static void Servizi_setOrario(Servizi servizi, Biblioteca bibliotecaDb) {

		boolean init = false;

		ServiziOrario serviziOrario = new ServiziOrario();

		//Chiusura
		ArrayList<Chiusura> chiusureH = new ArrayList<Chiusura>();
		for (Iterator<OrarioChiusure> i = bibliotecaDb.getOrarioChiusures().iterator(); i.hasNext();) {
			Chiusura chiusura = new Chiusura();
			OrarioChiusure chiusoH = i.next();
			if ((chiusoH.getDescrizione() != null) && (chiusoH.getDescrizione().trim().length() > 0))
				chiusura.setNote(chiusoH.getDescrizione().trim());
			else
				chiusura.setNote("Non presenti");
			if ((chiusoH.getDescrizione() != null) && (chiusoH.getDescrizione().trim().length() > 0)) 
				chiusura.setPeriodo(chiusoH.getDescrizione().trim()); // serve? se descrizione == null senza l'if va in errore
			chiusureH.add(chiusura);
		}
		if (chiusureH.size() > 0) {
			init = true;
			Chiusura[] chiusuraArray = new Chiusura[chiusureH.size()];
			chiusuraArray = (Chiusura[]) chiusureH.toArray(chiusuraArray);
			serviziOrario.setChiusura(chiusuraArray);
		}


		//Totali orari
		if (isNullSafeNotZero(bibliotecaDb.getNOreSettimanali())) {
			init = true;
			serviziOrario.setOreSettimanali(new BigDecimal(bibliotecaDb.getNOreSettimanali()));
		}
		if (isNullSafeNotZero(bibliotecaDb.getNOreSettimanaliPom())){
			init = true;
			serviziOrario.setOreSettimanaliPomeridiane(new BigDecimal(bibliotecaDb.getNOreSettimanaliPom()));
		}
		if (isNullSafeNotZero(bibliotecaDb.getNSettimApertura())){
			init = true;
			serviziOrario.setSettimaneApertura(new BigDecimal(bibliotecaDb.getNSettimApertura()));
		}

		//Orario ufficiale
		ArrayList<Orario> uff = new ArrayList<Orario>();
		for (Iterator<OrarioUfficiali> i = bibliotecaDb.getOrarioUfficialis().iterator(); i.hasNext();) {
			OrarioUfficiali uffH = i.next();
			Orario orario = new Orario();
			orario.setAlle(DateFormatUtils.format(uffH.getAlle(), "HH:mm"));
			orario.setDalle(DateFormatUtils.format(uffH.getDalle(), "HH:mm"));
			if (1 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.LUN);
			if (2 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.MAR);
			if (3 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.MER);
			if (4 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.GIO);
			if (5 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.VEN);
			if (6 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.SAB);
			if (7 == uffH.getGiorno())
				orario.setGiorno(OrarioGiornoType.DOM);
			uff.add(orario);
		}
		if (uff.size() > 0){
			init = true;
			Orario[] orarioArray = new Orario[uff.size()];
			orarioArray = (Orario[])uff.toArray(orarioArray);
			Ufficiale ufficiale = new Ufficiale();
			ufficiale.setOrario(orarioArray);
			serviziOrario.setUfficiale(ufficiale);
		}


		//Variazioni orario
		//		ArrayList<Orario> var = new ArrayList<Orario>();
		//		for (Iterator<OrarioVariazioni> i = bibliotecaDb.getOrarioVariazionis().iterator(); i.hasNext();) {
		//
		//			OrarioVariazioni varH =  i.next();
		//			if (varH != null) {
		//				Orario orario = new Orario();
		//
		//				if (varH.getAlle() != null) orario.setAlle(DateFormatUtils.format(varH.getAlle(), "HH:mm"));
		//
		//				if (varH.getDalle() != null) orario.setDalle(DateFormatUtils.format(varH.getDalle(), "HH:mm"));
		//
		//				if (varH.getGiorno() != null) {
		//					if (1 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.LUN);
		//					if (2 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.MAR);
		//					if (3 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.MER);
		//					if (4 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.GIO);
		//					if (5 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.VEN);
		//					if (6 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.SAB);
		//					if (7 == varH.getGiorno())
		//						orario.setGiorno(OrarioGiornoType.DOM);
		//				}
		//				var.add(orario);
		//			}
		//		}
		//		if (var.size() > 0){
		//			init = true;
		//			Orario[] orarioArray = new Orario[var.size()];
		//			orarioArray = (Orario[])var.toArray(orarioArray);
		//			Variazione variazione = new Variazione();
		//			variazione.setOrario(orarioArray);
		//			serviziOrario.addVariazione(variazione);
		//		}

		ArrayList<Variazione> var = new ArrayList<Variazione>();
		for (Iterator<OrarioVariazioni> i = bibliotecaDb.getOrarioVariazionis().iterator(); i.hasNext();) {

			OrarioVariazioni varH =  i.next();
			if (varH != null) {
				Variazione variazione = new Variazione();
				Orario orario = new Orario();

				//				if (varH.getAlle() != null) orario.setAlle(DateFormatUtils.format(varH.getAlle(), "HH:mm"));
				//
				//				if (varH.getDalle() != null) orario.setDalle(DateFormatUtils.format(varH.getDalle(), "HH:mm"));
				//
				//				if (varH.getGiorno() != null) {
				//					if (1 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.LUN);
				//					if (2 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.MAR);
				//					if (3 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.MER);
				//					if (4 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.GIO);
				//					if (5 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.VEN);
				//					if (6 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.SAB);
				//					if (7 == varH.getGiorno())
				//						orario.setGiorno(OrarioGiornoType.DOM);
				//				}
				//				
				//				variazione.addOrario(orario);
				//				if ((varH.getDescrizione() != null) && (varH.getDescrizione().trim().length() > 0)) {
				//					variazione.setNote(varH.getDescrizione());
				//					variazione.setPeriodo(varH.getDescrizione());
				//				}
				//				else {
				//					variazione.setNote("Non presenti");
				//				}
				//				var.add(variazione);

				if (varH.getDalle() != null && varH.getAlle() != null && varH.getGiorno() != null 
						&& (varH.getDescrizione() != null && varH.getDescrizione().trim().length() > 0)) {
					/* SI EFFETTUA IL CONTROLLO SE TUTTI I CAMPI DELLA VARIAZIONE SONO VALORIZZATI; QUESTO È DOVUTO
					 * AL FATTO CHE I DATI MIGRATI DAL VECCHIO DB POSSONO AVERE I CAMPI GIORNO, DALLE E ALLE NULLI. */

					orario.setDalle(DateFormatUtils.format(varH.getDalle(), "HH:mm"));

					orario.setAlle(DateFormatUtils.format(varH.getAlle(), "HH:mm"));

					if (1 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.LUN);
					if (2 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.MAR);
					if (3 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.MER);
					if (4 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.GIO);
					if (5 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.VEN);
					if (6 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.SAB);
					if (7 == varH.getGiorno())
						orario.setGiorno(OrarioGiornoType.DOM);

					variazione.addOrario(orario);

					variazione.setNote(varH.getDescrizione());
					variazione.setPeriodo(varH.getDescrizione());

					var.add(variazione);
				}
			}
		}
		if (var.size() > 0) {
			init = true;
			Variazione[] variazioneArray = new Variazione[var.size()];
			variazioneArray = (Variazione[]) var.toArray(variazioneArray);
			serviziOrario.setVariazione(variazioneArray);



			Chiusura[] chiusuraArray = new Chiusura[chiusureH.size()];
			chiusuraArray = (Chiusura[]) chiusureH.toArray(chiusuraArray);
			serviziOrario.setChiusura(chiusuraArray);
		}

		if (init) servizi.setServiziOrario(serviziOrario);
	}

	private static void Servizi_setSezioniSpeciali(Servizi servizi, Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali sezioniSpeciali = new it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali();
		boolean modificato = false;
		for (Iterator<SezioniSpeciali> j = bibliotecaDb.getSezioniSpecialis().iterator(); j.hasNext();) {
			SezioniSpeciali sezioneSpec = j.next();
			sezioniSpeciali.addSezione(sezioneSpec.getDescrizione());
			modificato = true;
		}
		if (modificato){
			servizi.setSezioniSpeciali(sezioniSpeciali);
		}
	}

	private static void Servizi_setSistemi(Servizi servizi, Biblioteca bibliotecaDb) {
		//		ArrayList<Sistemi> sist = new ArrayList<Sistemi>();
		//		for (Iterator<SistemiBiblioteche> i = bibliotecaDb.getSistemiBiblioteches().iterator(); i.hasNext();) {
		//			SistemiBiblioteche sistemaH = i.next(); 
		//			Sistemi sistema = new Sistemi();
		//			sistema.setSistema(sistemaH.getDescrizione());
		//			sist.add(sistema);
		//		}
		//		if (sist.size() > 0) {
		//			servizi.setSistemi(sist.toArray(new Sistemi[sist.size()]));
		//		}	

		/* Nuova modifica ticket 0001300 */
		Sistemi sistemi = new Sistemi();
		for (Iterator<SistemiBiblioteche> i = bibliotecaDb.getSistemiBiblioteches().iterator(); i.hasNext();) {
			SistemiBiblioteche sistemaH = i.next();
			SistemiItem sistItem = new SistemiItem();
			sistItem.setSistema(sistemaH.getDescrizione());
			sistemi.addSistemiItem(sistItem);
		}

		if (sistemi.getSistemiItem() != null && sistemi.getSistemiItem().length > 0) {
			servizi.setSistemi(sistemi);
		}
	}

	private static void Servizi_setRiproduzioni(Servizi servizi, Biblioteca bibliotecaDb) {
		it.inera.abi.logic.formatodiscambio.castor.Riproduzioni riproduzioni = new it.inera.abi.logic.formatodiscambio.castor.Riproduzioni();

		if (bibliotecaDb.getAttivoRiproduzioni() != null) {
			if (bibliotecaDb.getAttivoRiproduzioni().booleanValue() == true) {
				riproduzioni.setAttivo(SiNoType.S);

				if (bibliotecaDb.getRiproduzionis() != null && bibliotecaDb.getRiproduzionis().size() > 0) {
					for (Riproduzioni riproduzioneEntry : bibliotecaDb.getRiproduzionis()) {
						Riproduzione riproduzione = new Riproduzione();

						riproduzione.setTipo(riproduzioneEntry.getRiproduzioniTipo().getDescrizione());

						if (riproduzioneEntry.getLocale() != null) {
							if (riproduzioneEntry.getLocale().booleanValue() == true) {
								riproduzione.setLocale(SiNoType.S);

							} else if (riproduzioneEntry.getLocale().booleanValue() == false) {
								riproduzione.setLocale(SiNoType.N);

							}							
						}

						if (riproduzioneEntry.getNazionale() != null) {
							if (riproduzioneEntry.getNazionale().booleanValue() == true) {
								riproduzione.setNazionale(SiNoType.S);

							} else if (riproduzioneEntry.getNazionale().booleanValue() == false) {
								riproduzione.setNazionale(SiNoType.N);

							}							
						}

						if (riproduzioneEntry.getInternazionale() != null) {
							if (riproduzioneEntry.getInternazionale().booleanValue() == true) {
								riproduzione.setInternazionale(SiNoType.S);

							} else if (riproduzioneEntry.getInternazionale().booleanValue() == false) {
								riproduzione.setInternazionale(SiNoType.N);

							}							
						}

						riproduzioni.addRiproduzione(riproduzione);
					}
				}

			} else if (bibliotecaDb.getAttivoRiproduzioni().booleanValue() == false) {
				riproduzioni.setAttivo(SiNoType.N);

			}

			servizi.setRiproduzioni(riproduzioni);

		}
	}
	
	private static void Servizi_setReference(Servizi servizi, Biblioteca bibliotecaDb) {
		Reference ref = new Reference();

		if (bibliotecaDb.getAttivoReference() != null) {
			if (bibliotecaDb.getAttivoReference().booleanValue() == true) {
				ref.setAttivo(SiNoType.S);

				/* Reference locale */
				if (BooleanUtils.isTrue(bibliotecaDb.getReferenceLocale())) {
					ref.setLocale(SiNoType.S);

				} else if (BooleanUtils.isFalse(bibliotecaDb.getReferenceLocale())) {
					ref.setLocale(SiNoType.N);
				}

				/* Reference online */
				if (BooleanUtils.isTrue(bibliotecaDb.getReferenceOnline())) {
					ref.setOnline(SiNoType.S);
					
				} else if (BooleanUtils.isFalse(bibliotecaDb.getReferenceOnline())) {
					ref.setOnline(SiNoType.N);
				}


			} else {
				ref.setAttivo(SiNoType.N);

				/* Reference locale */
				if (bibliotecaDb.getReferenceLocale() != null 
						&& bibliotecaDb.getReferenceLocale().booleanValue() == false) {
					ref.setLocale(SiNoType.N);
				}

				/* Reference online */
				if (bibliotecaDb.getReferenceOnline() != null 
						&& bibliotecaDb.getReferenceOnline().booleanValue() == false) {
					ref.setOnline(SiNoType.N);
				}

			}

			servizi.setReference(ref);
		}

	}
	
	private static void Servizi_setDocumentDelivery(Servizi servizi, Biblioteca bibliotecaDb) {
		DocumentDelivery docDel = new DocumentDelivery();

		if (bibliotecaDb.getAttivoDocumentDelivery() != null) {
			if (BooleanUtils.isTrue(bibliotecaDb.getAttivoDocumentDelivery().booleanValue())) {
				docDel.setAttivo(SiNoType.S);

				/* Tipologie */
				DocDelForme forme = new DocDelForme();
				if (bibliotecaDb.getDocumentDeliveries() != null && bibliotecaDb.getDocumentDeliveries().size() > 0) {
					
					for (RiproduzioniTipo documentDelivery : bibliotecaDb.getDocumentDeliveries()) {
						forme.addForma(documentDelivery.getDescrizione());
						
					}
				}
				
				if (forme.getFormaCount() > 0) {
					docDel.setDocDelForme(forme);
				}

			} else if (BooleanUtils.isFalse(bibliotecaDb.getAttivoDocumentDelivery().booleanValue())) {
				docDel.setAttivo(SiNoType.N);
			}
			
			servizi.setDocumentDelivery(docDel);
		}
	}

	public static boolean isNullSafeNotZero(Integer num) {
		if (num == null) {
			return false;
		}
		return num > 0;
	}

	public static void main(String[] args) {
		Boolean test = null;
		System.out.println(BooleanUtils.isTrue(test));
		System.out.println(BooleanUtils.isFalse(test));
	}
}