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
import it.inera.abi.persistence.FondiSpeciali;
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
import it.inera.abi.persistence.Utenti;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Interfaccia DAO per l'entità Biblioteca
 *
 */
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
	 * @param idBib Array di id delle biblioteche
	 * @param firstResult
	 * @param maxResult
	 * @return Array di biblioteche
	 * @throws DaoException
	 */
	public Biblioteca[] getBibliotecheByIdBib(String[] idBib, int firstResult, int maxResult);

	/**
	 * Seleziona un'array di biblioteca tramite codice ABI delle biblioteche
	 * @param codABI Array di codici isil delle biblioteche
	 * @param firstResult
	 * @param maxResult
	 * @return Array di biblioteche
	 * @throws DaoException
	 */
	public Biblioteca[] getBibliotecheByCodABI(String[] codABI, int firstResult, int maxResult);

	

	public List<Biblioteca> getPuntiDiServizioDecentrati(int id_biblioteca);

	public void addPuntoDiServizioDecentrato(int idBibliotecaPadre, int idBibliotecaFiglia);

	public List<Biblioteca> getPuntiDiServizioDecentratiPossibili(String isil_provincia,
			String filter, int rows, int offset);

	public void removePuntoDiServizioDecentrato(int id_bibloteca);

	public int countAllPuntiDecentratiByIsilProvinciaAndFiltered(String isil_provincia, String filter);
	//public int countAllSistemiDiBibliotecheFiltered(String filter);

	public List<SistemiBiblioteche> getSistemiBibliotecheByIdBiblioteca(
			int id_biblioteca);

	public boolean addSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public	void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche);

	public void setEnte(int id_biblioteca, Ente ente);

	public List<AccessoModalita> getAccessoModalitaByIdBiblioteca(
			int id_biblioteca);

	public void removeModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public void addModalitaAccesso(int id_biblioteca, int id_modalita_accesso);

	public List<DestinazioniSociali> getDestinazioniSocialiByIdBiblioteca(
			int id_biblioteca);

	public boolean addDestinazioniSociali(int id_biblioteca, boolean modifica,
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

	public boolean addPatrimonioSpeciale(int id_biblioteca, boolean modifica, int id_nuovoPatr,
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

	public List<?> getListaVoci(int id_biblioteca, int idTabellaDinamica);

	public void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			DynaTabDTO dynaTabDTODB, int id_biblioteca, int idTabellaDinamica) throws DuplicateEntryException;

	public void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica);

	public FondiSpeciali updateFondoSpeciale(FondiSpeciali fondoSpeciale);

	public int countAllSpogliMaterialeBibliograficoPossibili(String filter);

	public List<String> getListaSpogliMaterialeBibliograficoPossibiliFiltered(
		 int start, int limit,String filter);

	public List<SpogliBibliografici> getListaSpogliMaterialeBibliograficoByIdBiblio(
			int id_biblioteca);

	public void addSpoglioMaterialeBibliografico(SpogliBibliografici nuovoSpoglio, int id_biblioteca, boolean modifica);

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
	
	public void setServizioBibliograficoInternoEsterno(int id_biblioteca, Boolean hasAttivoInformazioniBibliografiche, Boolean hasServizioBibliograficoInterno,
			Boolean hasServizioBibliograficoEsterno);

	public List<ServiziInformazioniBibliograficheModalita> getModalitaComunicazioniBibliograficheByIdBiblio(
			int id_biblioteca);

	public void addModalitaComunicazioneInformazioneBibliografica(	int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public	void removeModalitaComunicazioneInformazioneBibliografica(
			int id_biblioteca, Integer idRecord);

	public List<SezioniSpeciali> getSezioniSpecialiByIdBiblio(int id_biblioteca);

	public void removeSezioniSpeciali(int id_biblioteca, int idRemove);

	public void addSezioniSpeciali(int id_biblioteca, Integer idRecord) throws DuplicateEntryException;

	public void updateModalitaAccessoInternet(int id_biblioteca, Boolean hasAttivoAccesso, Boolean hasAccessoPagamento, Boolean hasAccessoTempo,
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

	public boolean addDepositoLegaleToBiblio(int id_biblioteca, boolean modifica, int id_nuovoTipoDeposito, String anno);

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

	public void addStatoCatalogazioneViaRipristino(int idbiblio, Integer idStatoCatalogazione, Integer idBibliotecaTarget);

	public Boolean addStatoCatalogazione(HashMap<String, Object> params);
	
	public Boolean removeStatoCatalogazione(HashMap<String, Object> params);

	public void removeChild(Object o);

	public Codici getCodiceByIdCodiceIdBiblio(int idBiblio, int idCodiceTipo);

	Biblioteca[] getBibliotecheByCodABI6CharsCode(String[] codABI, int firstResult,int maxResult);
	
	public void removePrestitoInterbibliotecarioNotUsedByOtherBibs(List<PrestitoInterbibliotecario> prestList, int idBib);
	
	public void removeRiproduzioniFromBiblio(Biblioteca biblioteca);
	
	public void removePrestitoLocaleFromBiblio(Biblioteca biblioteca);
	
	public List<SistemiPrestitoInterbibliotecario> getListaSistemiPrestitoInterbibliotecario(int id_biblioteca);
	
	public void removeSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistema);
	
	public void addSistemaPrestitoInterbibliotecario(int id_biblioteca, Integer id_sistema) throws DuplicateEntryException;
	
	public void setReference(int id_biblio, Boolean hasAttivoReference, Boolean hasReferenceLocale, Boolean hasReferenceOnline);
	
	public List<RiproduzioniTipo> getDocumentDeliveryByIdBiblio(int id_biblioteca);

	public void addDocumentDelivery(int id_biblioteca, Integer idDocumentDelivery) throws DuplicateEntryException;

	public void removeDocumentDelivery(int id_biblioteca, Integer idRecord);
	
	public void removeDocumentDeliveryFromBiblio(Biblioteca id_biblioteca);
	
	public void removeDepositiLegaliFromBiblio(Biblioteca biblioteca);
	
	public void removeModalitaAccessoFromBiblio(Biblioteca biblioteca);
	
	public String getPrimaOccorrenzaFonteValorizzata(String[] idBibs);
	
	public void inserisciPhoto(int id_biblioteca, Photo photo);
	
	public List<String> getListaIsilProvincia(String query);
	
	public List<Photo> getPhotos(int id_biblioteca);
	
	public void addPhoto(int id_biblioteca, String caption, String uri);
	
	public void updatePhotoCaption(int idPhoto, String caption);
	
	public void removePhoto(int id_biblioteca, int id_photo);
	
	public void updatePhotoOrder(List<Integer> idPhotos);
}