package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
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
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath(Abi.BIBLIOTECHE_SERVICE)
public interface BibliotecheService extends RemoteService {

	/* Servizio per la restituzione delle biblioteche: hashmap keys passate dentro al config */
	public PagingLoadResult<BiblioModel> getBiblioteche(final PagingLoadConfig config);
	
	
	/* Servizio per la restituzione delle biblioteche assegnate ad un utente */
	public PagingLoadResult<BiblioModel> getBibliotecheUtente(final PagingLoadConfig config);

	public List<VoceUnicaModel> getStatiWorkFlow();

	public BiblioModel getBiblioteca(int id_biblio);

	public List<ContattiModel> getContattiBibliotecaById(int id_biblio);

	public List<VoceUnicaModel> getTipologieContatti();

	public void saveContatti(ContattiModel nuovoContatto, boolean modifica);

	public void removeContatto(int id_contatto);

	public void aggiornaDenominazioneUfficiale(String denominazione, int id_biblioteca);

	public List<VoceUnicaModel> getDenominazioniPrecedenti(int id_biblioteca);

	public void removeDenominazionePrecedente(int id_record);

	public void saveDenominazionePrecendente(VoceUnicaModel nuovaDenominazione, 	boolean modifica);

	public List<VoceUnicaModel> getDenominazioniAlternative(int id_biblioteca);

	public void removeDenominazioneAlternativa(int id_record);

	public void saveDenominazioneAlternativa(VoceUnicaModel nuovaDenominazione,	boolean modifica);

	public void aggiornaIndirizzo(HashMap<String, Object> param, int id_biblioteca);

	public List<BiblioModel> getPuntiDiServizioDecentratiByIdBiblioteca(int id_biblioteca);

	public void addPuntoDiServizioDecentrato(int id_bibloteca_padre, int id_biblioteca_figlio);

	public void removePuntoDiServizioDecentrato(int id_bibloteca);

	public PagingLoadResult<BiblioModel> getPuntiDiServizioDecentratiPossibili(ModelData loadConfig);

	public List<VoceUnicaModel> getSistemiBibliotecheByIdBiblioteca(int id_biblioteca);

	public void addSistemaBiblioteca(int id_biblioteca,	int id_sistema_biblioteche);

	public void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public void updateProfiloStoricoSede(HashMap<String, Object> params, int id_biblioteca);

	public void updateInfoLocali(HashMap<String, Object> params, int id_biblioteca);

	public void updateInfoPostazioni(HashMap<String, Object> params, int id_biblioteca);

	public PagingLoadResult<EnteModel> getEntiPaginatiFilteredPerCombos(ModelData loadConfig);

	public void setEnte(int id_biblioteca, HashMap<String, Object> params);

	public	void setAutonomiaAmministrativa(int idBiblio, HashMap<String, Object> params);

	public void setTipologiaFunzionale(int idBiblio, VoceUnicaModel value);

	public	void setInfoFondazione(int idBiblio, HashMap<String, Object> params);

	public	void setModalitaAccesso(int idBiblio, HashMap<String, Object> params);
	
	public	void setLimiteEtaAccesso(int idBiblio, HashMap<String, Object> params);
	
	public List<VoceUnicaModel> getModalitaAccessoByIdBiblioteca(int id_biblioteca);

	public void addModalitaAccesso(int id_biblioteca, int id_nuovaModalita);

	public void removeModalitaAccesso(int id_biblioteca, int id_removeModalita);

	public List<DestinazioneSocialeModel> getDestinazioniSociali(int id_biblioteca);

	public void addDestinazioneSociale(int id_biblioteca, int id_nuovaDestinazione, String note);

	public void removeDestinazioneSociale(int id_biblioteca, int id_rimuoviModalita);

	public void setAccessoHandicap(int idBiblio, Boolean b);

	public	void setRegolamento(HashMap<String, String> params, int idBiblio);

	public List<OrariModel> getOrariUfficialiByDay(int id_biblioteca, int id_day);

	public void addNuovoOrarioGiornalieroCustom(int id_biblio, Vector<Integer> id_days, OrariModel toSave);

	public void removeOrarioUfficiale(int id_orarioToRemove);

	public	List<OrariModel> getVariazioniOrari(int id_biblioteca);

	public void addNuovaVariazioneOrario(int id_biblioteca, OrariModel toSave, boolean modifica);
	
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, OrariModel toSave);

	public void removeVariazioneOrario(int id_variazioneOrarioToRemove);

	public void addNuovoOrarioGiornaliero(int id_biblioteca, int id_day, OrariModel toSave, boolean modifica);

	public List<VoceUnicaModel> getPeriodiChiusuraByIdBiblio(int id_biblioteca);

	public void addNuovoPeriodoChiusura(int id_biblioteca, VoceUnicaModel tmpSave,	boolean modifica);

	public void removePeriodoChiusura(int id_chiusuraToRemove);

	public void setConsistenzaFondiAntichi1830(int idBiblio, int id_consistenza);

	public List<PatrimonioLibrarioModel> getListaPatrimonioSpecializzazione(int id_biblioteca);
	
	public void addPatrimonioSpeciale(int id_biblioteca, int id_nuovoPatr, int quantita, int quantitaUltimoAnno);

	public void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr);

	public List<SpecializzazioneModel> getSpecializzazioniByIdBiblioteca(int id_biblioteca);

	public void addSpecializzazionePatrimonio(SpecializzazioneModel modelToSave);

	public void removeSpecializzazionePatrimonio(int id_biblioteca, String idr_removeRecord);

	public List<FondiSpecialiModel> getFondiSpecialiByIdBiblioteca(int id_biblioteca);

	public PagingLoadResult<FondiSpecialiModel> getDenominazioneFondiSpecialiPossibiliFilteredPerCombos(ModelData loadConfig);

	public FondiSpecialiModel addFondoSpeciale(FondiSpecialiModel modelToSave, int idBiblioteca, boolean modifica);

	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord);

//	public List<VoceUnicaModel> getDigitalizzazioneFondiByIdBiblio(int id_biblioteca);
//
//	public void addDigitalizzazioneFondo(int id_biblioteca, int id_newRecord, String derscrizione, boolean modifica);
//
//	public void removeFondiDigitali(int id_rimuoviFondo);

	public List<VoceUnicaModel> getEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(int id_biblioteca, int idTabellaDinamica);

	void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(int idToSave,int id_biblioteca, int idTabellaDinamica)	throws DuplicatedEntryClientSideException;

	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica);

	public PagingLoadResult<VoceUnicaModel> getSpogliMaterialBibliograficoPerPaginazioneCombobox(int id_biblioteca, ModelData loadConfig);

	public List<VoceUnicaModel> getListaSpogliMarerialeBibliograficoByIdBiblio(int id_biblioteca);

	public void addSpoglioMaterialeBibliografico(String descrSpoglio, int id_biblioteca) throws DuplicatedEntryClientSideException;

	public void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio);

	public List<VoceUnicaModel> getlistaPubblicazioniByIdBiblio(int id_biblioteca);

	public void addPubblicazioni(VoceUnicaModel modelToSave, int id_biblioteca,boolean modifica);

	public void removePubblicazioni(int id_rimuoviPubblicazione);

	public void inserisciBibliograficaInfoCatalogazione(int id_biblio, String value);

	public List<PartecipaCataloghiCollettiviModel> getPartecipaCataloghiCollettiviByIdBiblio(int id_biblioteca);

	public void addPartecipaCatalogoCollettivo(int id_biblioteca, PartecipaCataloghiCollettiviModel tmpCatalogo, boolean modifica) throws EntryNotFoundClientSideException;

	public List<PartecipaCataloghiSpecialiModel> getPartecipaCataloghiSpecialiByIdBiblio(int id_biblioteca);

	public void addPartecipaCatalogoSpeciale(int id_biblioteca, PartecipaCataloghiSpecialiModel tmpCatalogo, boolean modifica);

	public void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca);

	public void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca);

	public void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca);

	public List<PartecipaCataloghiGeneraliModel> getPartecipaCataloghiGeneraliByIdBiblio(int id_biblioteca);

	public void addPartecipaCatalogoGenerale(int id_biblioteca, PartecipaCataloghiGeneraliModel tmpCatalogo, boolean modifica);

	public List<ServiziRiproduzioniModel> getServiziRiproduzioniFinitureByIdBiblio(int id_biblioteca);

	public void addServiziRiproduzioniForniture(int id_biblioteca,	ServiziRiproduzioniModel tmpServizio, boolean modifica);

	public void removeServiziRiproduzioniForniture(int idRemove, int id_biblioteca);

	/* Metodo per la restituzione delle biblioteche per il report */
//	public PagingLoadResult<BiblioModel> getBibliotecheReport(HashMap<String, Object> keys,	final PagingLoadConfig config);
	public PagingLoadResult<BiblioModel> getBibliotecheReport(final PagingLoadConfig config);
	
	public void setServizioBibliogrficoInternoEsterno(int id_biblio, Boolean hasServizioBibliograficoInterno, Boolean hasServizioBibliograficoEsterno);

	public List<VoceUnicaModel> getModalitaComunicazioniBibliograficheByIdBiblio(int id_biblioteca);

	public void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idTipoComunicazione) throws DuplicatedEntryClientSideException;

	public void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idRemove);

	public List<VoceUnicaModel> getSezioniSpecialiByIdBiblio(int id_biblioteca);

	public void removeSezioniSpeciali(int id_biblioteca, int idRemove);

	public void addSezioniSpeciali(int id_biblioteca, int idSezioneSpeciale) throws DuplicatedEntryClientSideException;

	public void updateModalitaAccessoInternet(int id_biblio, HashMap<String, String> keys);

	public List<PrestitoLocaleModel> getPrestitiLocaliByIdBiblio(int id_biblioteca);

	public void addPrestitoLocaleToBiblio(int id_biblioteca,PrestitoLocaleModel tmpPrestito, boolean modifica);

	public 	void removePrestitoLocaleFromBiblio(int id_biblioteca, int id_removePrestito);

	public List<PrestitoInterbibliotecarioRuoloModel> getPrestitoInterbibliotecarioERuoloByIdBiblio(int id_biblioteca);

	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca, PrestitoInterbibliotecarioRuoloModel tmpPrestito, boolean modifica) throws DuplicatedEntryClientSideException;

	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca, int id_removePrestito);

	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(int id_biblio, Boolean hasPrestitoNazionale, Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate);

	public void setInfoPersonale(int id_biblio, HashMap<String, Object> personaleValues);

	public void setInfoUtenti(int id_biblio, HashMap<String, Object> utentiValues);

	public void setInfoBilancio(int id_biblio, HashMap<String, Object> bilancioValues);

	
	public List<DepositiLegaliModel> getDepositiLegaliByIdBiblio(int id_biblioteca);

	public void addDepositoLegaleToBiblio(int id_biblioteca, int id_nuovoTipoDeposito, String anno, boolean modifica);

	public 	void removeDepositoLegaleFromBiblio(int id_biblioteca, int id_rimuoviDepositoLegale);

	public void inserisciNoteCatalogazione(int id_biblio, String value);

	public void inserisciComunicazioniCatalogazione(int id_biblio, String value);

	Boolean setStatoCatalogazione(HashMap<String, Object> params);
	
	/**
	 * ----------------------------- CHIAMATE PER WORKFLOW BIBLIOTECHE
	 */
	
	/**
	 * Mette in occupata la biblioteca
	 */
	public void setOccupata(int id);
	/**
	 * Mette in cancellata la biblioteca
	 */
	public void setCancellata(int id);
	/**
	 * Mette in revisione la biblioteca
	 */
	public void setInRevisione(int id);
	/**
	 * Mette in definitiva la biblioteca
	 */
	public void setDefinitiva(int id, String messaggio);
	
	/**
	 * Ripristina la biblioteca prima delle messa in modifica 
	 */
	public void ripristina(int id);

	/**
	 * Respinge la revisione
	 */
	public void respingiRevisione(int id_biblio, String messaggio);
	
	/**
	 * Ritorna la lista di differenze tra la versione salvata e quella attuale
	 */
	public String differenze(int id_biblio);
	
	void setInventarioCartaceo(int idBiblio, Boolean b);

	void setInventarioInformatizzato(int idBiblio, Boolean b);

	void setCatalogoTopograficoCartaceo(int idBiblio, Boolean b);

	void setCatalogoTopograficoInformatizzato(int idBiblio, Boolean b);
	
	public void setCatInvFondi(int idBib, HashMap<String, Object> params);

	/* Servizio per reperire gli utenti gestori di una biblioteca */
	public PagingLoadResult<UserModel> getUsersByBiblio(PagingLoadConfig config);

	void setDefinitiva(List<Integer> bibliotecheSelectedIds);
	
	void ripristina(List<Integer> bibliotecheSelectedIds);

	void respingiRevisione(List<Integer> bibliotecheSelectedIds);

	void aggiornaCodici(HashMap<String, Object> params, int idBiblio);

	void aggiornaCodiciOthers(HashMap<String, Object> params, int idBiblio);

}
