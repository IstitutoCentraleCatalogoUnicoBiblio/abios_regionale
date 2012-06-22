package it.inera.abi.dao;

import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.CodiciTipo;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.FondiDigitali;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.NuovaBiblioteca;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.PartecipaCataloghiCollettiviMateriale;
import it.inera.abi.persistence.PartecipaCataloghiGenerali;
import it.inera.abi.persistence.PartecipaCataloghiSpecialiMateriale;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.PrestitoInterbibliotecario;
import it.inera.abi.persistence.PrestitoLocale;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.Utenti;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public interface BiblioDao {
		
	public Biblioteca getBibliotecaById(int id);
	
	public void saveBiblioteca(Biblioteca biblioteca);
	
	public void updateBiblioteca(Biblioteca biblioteca);
	
	public void saveChild(Object child);
	
	public void updateChild(Object child);
	
	public void removeChilds(List<?> childs);
	
	public void removePrestitoLocale(Biblioteca biblioteca);
	
	public void setNuovoStatoNewTx(Biblioteca biblioteca, String stato);
	public void setNuovoStato(Biblioteca biblioteca, String stato);
	
	// SELECT MAX(isil_numero) AS MAX_VAL FROM biblioteca WHERE isil_stato = 'IT' AND isil_provincia = 'MS'
	public int getMaxIsilNumero(String isil_stato, String isil_provincia);
	
	public List<Biblioteca> ricercaBiblio(HashMap<String, Object> keys, int offset,
			int rows, String orderByField, String orderByDir);

	public int countAll();
	
	
	/*DAL FORMATO DI SCAMBIO */
	/**
	 * Seleziona un'array di biblioteca tramite l'id(db) delle biblioteche
	 * @param id Array di id delle biblioteche
	 * @return Array di biblioteche
	 * @throws DaoException
	 */
	public Biblioteca[] getBibliotecheByIdBib(String[] idBib, int firstResult, int maxResult);

	/**
	 * Seleziona un'array di biblioteca tramite codice ABI delle biblioteche
	 * @param id Array di id delle biblioteche
	 * @return Array di biblioteche
	 * @throws DaoException
	 */
	public Biblioteca[] getBibliotecheByCodABI(String[] codABI, int firstResult, int maxResult);

	

	public List<Biblioteca> getPuntiDiServizioDecentrati(int id_biblioteca);

	public void addPuntoDiServizioDecentrato(int id_bibloteca_padre,
			int id_biblioteca_figlio);

	public List<Biblioteca> getPuntiDiServizioDecentratiPossibili(String isil_provincia,
			String filter, int rows, int offset);

	public void removePuntoDiServizioDecentrato(int id_bibloteca);

	public int countAllPuntiDecentratiByIsilProvinciaAndFiltered(String isil_provincia, String filter);
	//public int countAllSistemiDiBibliotecheFiltered(String filter);

	public List<SistemiBiblioteche> getSistemiBibliotecheByIdBiblioteca(
			int id_biblioteca);

	public void addSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public	void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public void setEnte(int id_biblioteca, Ente ente);

	public List<AccessoModalita> getAccessoModalitaByIdBiblioteca(
			int id_biblioteca);

	public void removeModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public void addModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public List<DestinazioniSociali> getDestinazioniSocialiByIdBiblioteca(
			int id_biblioteca);

	public void addDestinazioniSociali(int id_biblioteca,
			int id_nuovaDestinazione, String note);

	public void removeDestinazioneSociale(int id_biblioteca,
			int id_rimuoviDestinazione);

	public void addRegolamento(Regolamento regolamento);

	

	public List<OrarioUfficiali> getOrariUfficialiByDay(int id_biblioteca, int id_day);

	

	public void addNuovoOrarioGiornalieroCustom(int id_biblio,
			Vector<Integer> id_days, Date dalle, Date alle);

	public void removeOrarioUfficiale(int id_orarioToRemove);

	public List<OrarioVariazioni> getVariazioniOrari(int id_biblioteca);

	public void addNuovaVariazioneOrario(int id_biblioteca,
			OrarioVariazioni orarioVariazioni);
	
	public void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, Date dalle, Date alle, String Periodo);

	public void removeVariazioneOrario(int id_variazioneOrarioToRemove);

	public void addNuovoOrarioGiornaliero(int id_biblioteca,
			OrarioUfficiali orarioUfficiali);

	public List<OrarioChiusure> getPeriodiChiusuraByIdBiblio(int id_biblioteca);

	public void addNuovoPeriodoChiusura(int id_biblioteca,
			OrarioChiusure nuovoOrarioChiusure);

	public void removePeriodoChiusura(int id_chiusuraToRemove);

	public List<Patrimonio> getListaPatrimonioSpecializzazione(int id_biblioteca);

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

	public FondiSpeciali addFondoSpeciale(int idFondoSpecialeToAdd,int idBiblioteca, boolean modifica);

	public int searchFondoSpeciale(FondiSpeciali fondoSpeciale);

	public int createFondoSpeciale(FondiSpeciali fondoSpeciale);

	public void removeFondiSpeciali(int id_biblioteca, int id_removeRecord);

	public List<FondiDigitali> getDigitalizzazioneFondiByIdBiblio(
			int id_biblioteca);

	public void addDigitalizzazioneFondo(int id_biblioteca, int id_newRecord,
			String derscrizione, boolean modifica);

	public void removeFondiDigitali(int id_rimuoviFondo);

	public List<?> getListaVoci(int id_biblioteca, int idTabellaDinamica);

	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			DynaTabDTO dynaTabDTODB, int id_biblioteca, int idTabellaDinamica) throws DuplicateEntryException;

	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica);

	public FondiSpeciali updateFondoSpeciale(FondiSpeciali fondoSpeciale);

	public int countAllSpogliMaterialBibliograficoPossibili(String filter);

	public List<SpogliBibliografici> getListaSpogliMaterialBibliograficoPossibiliFiltered(
		 int start, int limit,String filter);

	public List<SpogliBibliografici> getListaSpogliMarerialeBibliograficoByIdBiblio(
			int id_biblioteca);

	public void addSpoglioMaterialeBibliografico(
			String descrSpoglio, int id_biblioteca) throws DuplicateEntryException;

	public void addSpoglioMaterialeBibliograficoRipristino(String descrSpoglio,	int id_biblioteca);
	
	public void removeSpogliMaterialeBibliografico(
			int id_rimuoviSpoglio);

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
	
	public CataloghiCollettivi existCatalogoCollettivo(String descrCatalogo, int idZona, String dettaglioZona) throws EntryNotFoundException;

	public List<PartecipaCataloghiSpecialiMateriale> getPartecipaCataloghiSpecialiByIdBiblio(
		
			int id_biblioteca);

	public void addPartecipaCatalogoSpeciale(int id_biblioteca,PartecipaCataloghiSpecialiMateriale partecipaCataloghoSpecialeMateriale, 
			boolean modifica);

	public void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca);

	public	void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca);

	public	void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca);

	public int countAllBibliotecheFiltered(HashMap<String, Object> keys);

	public List<PartecipaCataloghiGenerali> getPartecipaCataloghiGeneraliByIdBiblio(
			int id_biblioteca);

	public void addPartecipaCatalogoGenerale(int id_biblioteca,PartecipaCataloghiGenerali partecipaCataloghiGenerali,
			boolean modifica);
	
	public List<Riproduzioni> getServiziRiproduzioniFornitureByIdBiblio(
			int id_biblioteca);

	public void addServiziRiproduzioniForniture(int id_biblioteca,Integer idTipo, Boolean hasLocale, Boolean hasNazionale,
			Boolean hasInternazionale);

	public void removeServiziRiproduzioniForniture(int idRemove,int id_biblioteca);
	
	/* 
	 * Metodi per il conteggio e la restituzione delle biblioteche 
	 * filtrate per i parametri inseriti per il report
	 *  
	 */
	public List<Biblioteca> ricercaBiblioReport(HashMap<String, Object> keys, int offset,
			int rows, String orderByField, String orderByDir);
	
	public int countAllBibliotechePerReport(HashMap<String, Object> keys);
	
	public void setServizioBibliogrficoInternoEsterno(int id_biblioteca,Boolean hasServizioBibliograficoInterno,
			Boolean hasServizioBibliograficoEsterno);

	public List<ServiziInformazioniBibliograficheModalita> getModalitaComunicazioniBibliograficheByIdBiblio(
			int id_biblioteca);

	public void addModalitaComunicazioneInformazioneBibliografica(	int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public	void removeModalitaComunicazioneInformazioneBibliografica(
			int id_biblioteca, Integer idRecord);

	public List<SezioniSpeciali> getSezioniSpecialiByIdBiblio(int id_biblioteca);

	public void removeSezioniSpeciali(int id_biblioteca, int idRemove);

	public void addSezioniSpeciali(int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public void updateModalitaAccessoInternet(int id_biblioteca,Boolean hasAccessoPagamento, Boolean hasAccessoTempo,
			Boolean hasAccessoProxy);

	public List<PrestitoLocale> getPrestitiLocaliByIdBiblio(int id_biblioteca);

	public PrestitoLocale addPrestitoLocaleToBiblio(int id_biblioteca,Integer idPrestito, Integer durataGiorni, Boolean automatizzato,
											boolean modifica);

	public void removePrestitoLocale(int id_biblioteca, int id_removePrestito);

	public List<PrestitoInterbibliotecario> getPrestitoInterbibliotecarioERuoloByIdBiblio(int id_biblioteca);

	public void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca,Integer idPrestito, Integer idRuolo, Boolean nazionale,
			Boolean internazionale, boolean modifica) throws DuplicateEntryException;

	public void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca,int id_removePrestito);

	public void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblio, Boolean hasPrestitoNazionale,Boolean hasPrestitoInternazionale, Boolean hasProcedureAutomatizzate);

	public void setInfoPersonale(int id_biblio,	HashMap<String, Object> personaleValues);

	public void setInfoUtenti(int id_biblio,HashMap<String, Object> utentiValues);

	public void setInfoBilancio(int id_biblio,	HashMap<String, Object> bilancioValues);

	public List<DepositiLegali> getDepositiLegaliByIdBiblio(int id_biblioteca);

	public void addDepositoLegaleToBiblio(int id_biblioteca,int id_nuovoTipoDeposito, String anno,boolean modifica);

	public void removeDepositoLegaleFromBiblio(int id_biblioteca,int id_rimuoviDepositoLegale);

	public void inserisciComunicazioniCatalogazione(int id_biblio, String value);
	
	public void inserisciNoteCatalogazione(int id_biblio, String value);
	
	/* Metodo per la restituzione delle biblioteche identificate da idbibs[i] */
	public Vector<Biblioteca> getBibliotecheReport(List<Integer> idbibs);	
	
	public Biblioteca getBibliotecaByIdForMarshall(int id);
	
	public Biblioteca[] getBibliotecheViaCodice(String[] codABI, Utenti ugest, int firstResult, int maxResult);
	
	public List<Utenti> ricercaUtentiByIdBiblio(int idbiblio, int offset, int rows, String orderByField, String orderByDir);
	
	public int countUtentiByIdBiblio(int idbiblio);

	public CodiciTipo getCodiceTipoById(int idCodice);

	public void addStatoCatalogazioneViaRipristino(int idbiblio,	Integer idStatoCatalogazione, Integer idBibliotecaTarget);

	Boolean addStatoCatalogazione(HashMap<String, Object> params);

	public void removeChild(Object o);

	public Codici getCodiceByIdCodiceIdBiblio(int idBiblio, int idCodiceTipo);

	Biblioteca[] getBibliotecheByCodABI6CharsCode(String[] codABI, int firstResult,int maxResult);
	
	public void removePrestitoInterbibliotecarioNotUsedByOtherBibs(List<PrestitoInterbibliotecario> prestList, int idBib);
}
 

