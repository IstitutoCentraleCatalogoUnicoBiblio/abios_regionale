package it.inera.abi.logic;

import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.EntryNotFoundException;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.Photo;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.EnteTipologiaAmministrativa;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.Stato;
import it.inera.abi.persistence.StatoBibliotecaWorkflow;
import it.inera.abi.persistence.TipologiaFunzionale;
import it.inera.abi.persistence.Utenti;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Classe contenente la logica delle principali operazioni di ricerca/modifica delle biblioteche
 *
 */
public interface AbiBiblioLogic {
	
	public List<Biblioteca> ricercaBiblio(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir);
	
	public int countAllBibliotecheFiltered(HashMap<String, Object> keys);
	
	/* Conteggio e restituzione biblioteche assegnate ad un utente */
	public List<Biblioteca> ricercaBibliotecheUtente(int id_utente, int offset, int rows, String orderByField, String orderByDir);
	
	public int countAllBibliotecheUtente(int id_utente);
	
	public int countAll();

	public List<StatoBibliotecaWorkflow> getListaStatiWorkFlow();

	public Biblioteca getBibliotecaById(int id_biblioteca);

	public List<Contatti> getListaContattiBibliotecaById(int id_biblioteca);

	public void saveContatti(Contatti nuovoContatto, boolean modifica);

	public List<ContattiTipo> getTipologieContatti();

	public void removeContatto(int id_contatto);

	public List<DenominazioniPrecedenti> getDenominazioniPrecedenti(int id_biblioteca);

	public void removeDenominazionePrecedente(int id_record);

	public void addDenominazionePrecendente(DenominazioniPrecedenti denominazione, boolean modifica);

	public List<DenominazioniAlternative> getDenominazioniAlternative(int id_biblioteca);

	public void removeDenominazioneAlternativa(int id_record);

	public void addDenominazioneAlternativa(DenominazioniAlternative denominazione, boolean modifica);

	public void aggiornaDenominazioneUfficiale(String denominazione, int id_biblioteca);

	public void aggiornaIndirizzo(HashMap<String, Object> param, int id_biblioteca);

	public List<Biblioteca> getPuntiDiServizioDecentrati(int id_biblioteca);

	public void addPuntoDiServizioDecentrato(int idBibliotecaPadre, String isilPrFiglia, String isilNrFiglia);

	public List<Biblioteca> getPuntiDiServizioDecentratiPossibili(
			String isil_provincia, String filter, int rows, int offset);

	public void removePuntoDiServizioDecentrato(int id_bibloteca);

	public int countAllByIsilProvinciaAndFiltered(String isil_provincia,
			String filter);

	public List<SistemiBiblioteche> getSistemiBibliotecheByIdBiblioteca(
			int id_biblioteca);

	public void addSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public	void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);
	
	public void updateBiblioteca(Biblioteca biblioteca);
	
	public void updateProfiloStoricoSede(HashMap<String, Object> params,
			int id_biblioteca);

	public void updateInfoLocali(HashMap<String, Object> params,
			int id_biblioteca);

	public void updateInfoPostazioni(HashMap<String, Object> params,
			int id_biblioteca);

	public  List<Ente> getEntiPaginatiFilteredPerCombos(String filter, int offset,
			int rows);
	public int countAllEntiFiltered(String filter);

	public	void setEnte(int id_biblioteca,Stato stato,
			EnteTipologiaAmministrativa enteTipologiaAmministrativa,
			String denominazione);

	public void setAutonomiaAmministrativa(int idBiblio,
			HashMap<String, Object> params);

	

	public void setTipologiaFunzionale(int idBiblio, TipologiaFunzionale tf);

	public void setInfoFondazione(int idBiblio, HashMap<String, Object> params);

	public void setModalitaAccesso(int idBiblio, HashMap<String, Object> params);
	
	public List<AccessoModalita> getAccessoModalitaByIdBiblioteca(
			int id_biblioteca);

	public	void removeModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public	void addModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public List<DestinazioniSociali> getDestinazioniSocialiByIdBiblioteca(
			int id_biblioteca);

	
	public	void addDestinazioniSociali(int id_biblioteca, int id_nuovaDestinazione,
			String note);

	public void removeDestinazioneSociale(int id_biblioteca,int id_rimuoviDestinazione);

	public void setAccessoHandicap(int idBiblio, Boolean value);

	public	Biblioteca getBibliotecaByIdWithAntiLazyIteration(int id_biblioteca);

	public void setRegolamento(HashMap<String, String> params, int idBiblio);

	public	List<OrarioUfficiali> getOrariUfficialiByDay(int id_biblioteca, int id_day);

	
	public void addNuovoOrarioGiornalieroCustom(int id_biblio,
			Vector<Integer> id_days, Date dalle, Date alle);

	public void removeOrarioUfficiale(int id_orarioToRemove);

	public List<OrarioVariazioni> getVariazioniOrari(int id_biblioteca);

	public void addNuovaVariazioneOrario(int id_biblioteca,
			OrarioVariazioni orarioVariazioni);
	
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, Date dalle, 
			Date alle, String periodo);

	public void removeVariazioneOrario(int id_variazioneOrarioToRemove);

	public void addNuovoOrarioGiornaliero(int id_biblioteca,
			OrarioUfficiali orarioUfficiali);

	public List<OrarioChiusure> getPeriodiChiusuraByIdBiblio(int id_biblioteca);

	public void addNuovoPeriodoChiusura(int id_biblioteca,
			OrarioChiusure nuovoOrarioChiusure);

	public void removePeriodoChiusura(int id_chiusuraToRemove);

	public void setConsistenzaFondiAntichi1830(int idBiblio, int id_consistenza);

	public List<Patrimonio> getListaPatrimonioSpecializzazione(
			int id_biblioteca);

	public void addPatrimonioSpeciale(int id_biblioteca, int id_nuovoPatr,
			int quantita, int quantitaUltimoAnno);

	public void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr);

	public List<DeweyLibero> getSpecializzazioniByIdBiblioteca(int id_biblioteca);

	public void addSpecializzazionePatrimonio(int id_biblioteca, String dewey,
			String descrizioneLibera);

	public void removeSpecializzazionePatrimonio(int id_biblioteca,
			String idr_removeRecord);

	public List<FondiSpeciali> getFondiSpecialiByIdBiblioteca(int id_biblioteca);

	public int countAllDenominazioneFondiSpecialiPossibili(String query);

	public List<FondiSpeciali> getDenominazioneFondiSpecialiPossibiliFiltered(
			String query, int start, int limit);

	public FondiSpeciali addFondoSpeciale(int idFondoToAdd, int idBiblioteca, boolean modifica);

	public int searchFondoSpeciale(FondiSpeciali fondoSpeciale);

	public int createFondoSpeciale(FondiSpeciali fondoSpeciale);

	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord);

	public List<?> getListaVoci(int id_biblioteca, int idTabellaDinamica);

	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			DynaTabDTO dynaTabDTODB, int id_biblioteca, int idTabellaDinamica) throws DuplicateEntryException;

	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica);

	public FondiSpeciali updateFondoSpeciale(FondiSpeciali fondoSpeciale);

	public int countAllSpogliMaterialBibliograficoPossibili(String filter);

	public List<SpogliBibliografici> getSpogliMaterialBibliograficoPerPaginazioneCombobox(
		 int start, int limit, String query);

	public  List<SpogliBibliografici>  getListaSpogliMarerialeBibliograficoByIdBiblio(int id_biblioteca);


	public 	void addSpoglioMaterialeBibliografico(String descrSpoglio,
			int id_biblioteca) throws DuplicateEntryException;

	public	void addSpoglioMaterialeBibliograficoRipristino(String descrSpoglio,int id_biblioteca) throws DuplicateEntryException;
	
	public void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio);

	public List<Pubblicazioni> getlistaPubblicazioniByIdBiblio(int id_biblioteca);

	public void addPubblicazioni(Pubblicazioni nuovaPubblicazione,
			int id_biblioteca, boolean modifica);

	public void removePubblicazioni(int id_rimuoviPubblicazione);

	public void inserisciBibliograficaInfoCatalogazione(int id_biblio,
			String value);

	public List<PartecipaCataloghiCollettiviMateriale> getPartecipaCataloghiCollettiviByIdBiblio(
			int id_biblioteca);

	public void addPartecipaCatalogoCollettivo(int id_biblioteca,PartecipaCataloghiCollettiviMateriale partecipaCataloghoCollettivoMateriale,
			boolean modifica) throws EntryNotFoundException;

	public List<PartecipaCataloghiSpecialiMateriale> getPartecipaCataloghiSpecialiByIdBiblio(
			int id_biblioteca);

	public void addPartecipaCatalogoSpeciale(int id_biblioteca, 	PartecipaCataloghiSpecialiMateriale partecipaCataloghoSpecialeMateriale,
			boolean modifica);

	public void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca);
	public void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca);

	public void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca);


	public List<PartecipaCataloghiGenerali> getPartecipaCataloghiGeneraliByIdBiblio(
			int id_biblioteca);

	public void addPartecipaCatalogoGenerale(int id_biblioteca,
			PartecipaCataloghiGenerali partecipaCataloghiGenerali,
			boolean modifica);

	public List<Riproduzioni> getServiziRiproduzioniFornitureByIdBiblio(
			int id_biblioteca);

	public void addServiziRiproduzioniForniture(int id_biblioteca,
			Integer idTipo, Boolean hasLocale, Boolean hasNazionale,
			Boolean hasInternazionale);

	public void removeServiziRiproduzioniForniture(int idRemove,
			int id_biblioteca);
	
	/* 
	 * Logica per la restituzione ed il conteggio delle biblioteche 
	 * filtrate per i parametri inseriti per il report 
	 * 
	 */
	public List<Biblioteca> ricercaBiblioReport(HashMap<String, Object> keys, int offset, int rows, String orderByField, String orderByDir);
	
	public int countAllBibliotechePerReport(HashMap<String, Object> keys);

	public void setServizioBibliograficoInternoEsterno(int id_biblioteca, Boolean hasAttivoInformazioniBibliografiche, Boolean hasServizioBibliograficoInterno, 
			Boolean hasServizioBibliograficoEsterno);

	public List<ServiziInformazioniBibliograficheModalita> getModalitaComunicazioniBibliograficheByIdBiblio(
			int id_biblioteca);

	public void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idRemove);

	public List<SezioniSpeciali> getSezioniSpecialiByIdBiblio(int id_biblioteca);

	public void removeSezioniSpeciali(int id_biblioteca, int idRemove);

	public void addSezioniSpeciali(int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public void updateModalitaAccessoInternet(int id_biblioteca, Boolean hasAttivoAccesso, Boolean hasAccessoPagamento, Boolean hasAccessoTempo, Boolean hasAccessoProxy);

	public List<PrestitoLocale> getPrestitiLocaliByIdBiblio(int id_biblioteca);

	public PrestitoLocale addPrestitoLocaleToBiblio(int id_biblioteca,Integer idPrestito, Integer durataGiorni, Boolean automatizzato,
											boolean modifica);

	public void removePrestitoLocaleFromBiblio(int id_biblioteca,int id_removePrestito);

	public List<PrestitoInterbibliotecario> getPrestitoInterbibliotecarioERuoloByIdBiblio(int id_biblioteca);

	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca,Integer idPrestito, Integer idRuolo, Boolean nazionale,
			Boolean internazionale, boolean modifica) throws DuplicateEntryException;

	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca,int id_removePrestito);

	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblio, Boolean hasPrestitoNazionale,Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate);

	public void setInfoPersonale(int id_biblio,	HashMap<String, Object> personaleValues);

	public void setInfoUtenti(int id_biblio,HashMap<String, Object> utentiValues);

	public void setInfoBilancio(int id_biblio,HashMap<String, Object> bilancioValues);

	public List<DepositiLegali> getDepositiLegaliByIdBiblio(int id_biblioteca);

	public void addDepositoLegaleToBiblio(int id_biblioteca,int id_nuovoTipoDeposito, String anno,boolean modifica);

	public void removeDepositoLegaleFromBiblio(int id_biblioteca,int id_rimuoviDepositoLegale);

	public void inserisciNoteCatalogazione(int id_biblio, String value);

	public void inserisciComunicazioniCatalogazione(int id_biblio, String value);
	
	public void inserisciPhoto(int id_biblioteca, Photo photo);

	/**
	 * Mette in occupata la biblioteca
	 */
	public void setOccupata(int id);
	
	/**
	 * Mette in occupata la biblioteca
	 */
	public void setOccupataByUsername(int id, String username);
	
	/**
	 * Mette in cancellata la biblioteca
	 */
	public void setCancellata(int id_biblio);
	/**
	 * Mette in revisione la biblioteca
	 */
	public void setInRevisione(int id_biblio, boolean sendEmailToRevisori);
	
	/**
	 * Mette in revisione la biblioteca
	 */
	public void setInRevisioneByUsername(int id_biblio, boolean sendEmailToRevisori, String username);
	
	/**
	 * Mette in definitiva la biblioteca
	 */
	public void setDefinitiva(int id_biblio, String messaggio);
	/**
	 * Riprististina la biblioteca prima della messa in modifica
	 */
	public void ripristina(int id_biblio);
	/**
	 * Respinge la richiesta di revisione
	 */
	public void respingiRevisione(int id_biblio, String messaggio);
	
	/**
	 * Ritorna le differenze tra la versione salvata e quella attuale 
	 */
	public String differenze(int idbiblio);
	
	/* Metodo di ricerca biblioteche tramite codice */
	public Biblioteca ricercaBiblioViaCodice(String stato, String provincia, String numero);
	
	public void setInventarioCartaceo(int idBiblio, Boolean b);
	
	public void setInventarioInformatizzato(int idBiblio, Boolean b);
	
	public void setCatalogoTopograficoCartaceo(int idBiblio, Boolean b);
	
	public void setCatalogoTopograficoInformatizzato(int idBiblio, Boolean b);
	
	public void setCatInvFondi(int idBiblio, HashMap<String, Object> params);
	
	public List<Utenti> ricercaUtentiByIdBiblio(int idbiblio, int offset, int rows, String orderByField, String orderByDir);
	
	public int countUtentiByIdBiblio(int idbiblio);
	
	void setDefinitiva(int id);
	
	void respingiRevisione(int id_biblio);
	
	public void setDefinitiveImportate(List<Integer> bibliotecheSelectedIds);
	
	public int ripristinaImportate(List<Integer> bibliotecheSelectedIds);
	
	public void aggiornaCodici(HashMap<String, Object> params, int idBiblio);
	
	public void aggiornaCodiciOthers(HashMap<String, Object> params, int idBiblio);
	
	public void addStatoCatalogazione(int idbiblio,	Integer idStatoCatalogazione, Integer idBibliotecaTarget);
	
	public Boolean addStatoCatalogazione(HashMap<String, Object> params);
	
	public Boolean removeStatoCatalogazione(HashMap<String, Object> params);
	
	public void setAttivoRiproduzioni(int idbib, Boolean attivoRiproduzioni);
	
	public void setAttivoPrestitoLocale(int idbib, Boolean attivoPrestitoLocale);
	
	public List<SistemiPrestitoInterbibliotecario> getListaSistemiPrestitoInterbibliotecario(int id_biblioteca);
	
	public void removeSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistema);
	
	public void addSistemaPrestitoInterbibliotecario(int id_biblioteca, Integer id_sistema) throws DuplicateEntryException;
	
	public void setReference(int id_biblio, Boolean hasAttivoReference, Boolean hasReferenceLocale, Boolean hasReferenceOnline);
	
	public void setAttivoDocumentDelivery(int idbib, Boolean attivoDocumentDelivery);
	
	public List<RiproduzioniTipo> getDocumentDeliveryByIdBiblio(int id_biblioteca);
	
	public void addDocumentDelivery(int id_biblioteca, Integer idDocumentDelivery) throws DuplicateEntryException;

	public void removeDocumentDelivery(int id_biblioteca, int idRemove);
	
	public void setAttivoDepositoLegale(int idbib, Boolean attivoDepositoLegale);
	
	public void updateCensimento(int id_biblioteca, Integer anno);
	
	public void addPadreServizioDecentrato(int idBiblioFiglio, String isilProvinciaPadre, String isilNumeroPadre);
	
	public List<String> getListaIsilProvincia(String query);
	
	public void removePadreServizioDecentrato(int idBiblioFiglio);
	
	public List<Photo> getPhotos(int id_biblioteca);
	
	public void addPhoto(int id_biblioteca, String caption, String uri);
	
	public void updatePhotoCaption(int idPhoto, String caption);
	
	public void removePhoto(int id_biblioteca, int id_photo);
	
	public void updatePhotoOrder(List<Integer> idPhotos);
} 
