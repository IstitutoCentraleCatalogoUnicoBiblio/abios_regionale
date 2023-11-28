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

package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.ContattiModel;
import it.inera.abi.gxt.client.mvc.model.DepositiLegaliModel;
import it.inera.abi.gxt.client.mvc.model.DestinazioneSocialeModel;
import it.inera.abi.gxt.client.mvc.model.EnteModel;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGeneraliModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioLibrarioModel;
import it.inera.abi.gxt.client.mvc.model.PhotoModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoInterbibliotecarioRuoloModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoLocaleModel;
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.mvc.model.SistemiPrestitoInterbibliotecarioModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>BibliotecheService</code>
 * 
 */
public interface BibliotecheServiceAsync {
	/* Servizio per la restituzione delle biblioteche: hashmap keys passate dentro al config */
	void getBiblioteche(final PagingLoadConfig config,
			AsyncCallback<PagingLoadResult<BiblioModel>> callback);
	
	/* Servizio per la restituzione delle biblioteche assegnate ad un utente */
	void getBibliotecheUtente(final PagingLoadConfig config, 
			AsyncCallback<PagingLoadResult<BiblioModel>> callback);

	void getStatiWorkFlow(AsyncCallback<List<VoceUnicaModel>> callback);

	void getBiblioteca(int id_biblio, AsyncCallback<BiblioModel> callback);

	void getContattiBibliotecaById(int id_biblio, AsyncCallback<List<ContattiModel>> callback);

	void getTipologieContatti(AsyncCallback<List<VoceUnicaModel>> callback);

	void saveContatti(ContattiModel nuovoContatto, boolean modifica, AsyncCallback<Void> callback);

	void removeContatto(int id_contatto, AsyncCallback<Void> callback);

	void getDenominazioniPrecedenti(int id_biblioteca, AsyncCallback<List<VoceUnicaModel>> callback);

	void removeDenominazionePrecedente(int id_record, AsyncCallback<Void> callback);

	void saveDenominazionePrecendente(VoceUnicaModel nuovaDenominazione, boolean modifica, AsyncCallback<Void> callback);

	void getDenominazioniAlternative(int id_biblioteca, AsyncCallback<List<VoceUnicaModel>> callback);

	void removeDenominazioneAlternativa(int id_record, AsyncCallback<Void> callback);

	void saveDenominazioneAlternativa(VoceUnicaModel nuovaDenominazione, boolean modifica, AsyncCallback<Void> callback);

	void aggiornaDenominazioneUfficiale(String denominazione, int id_biblioteca, AsyncCallback<Void> callback);

	void aggiornaIndirizzo(HashMap<String, Object> param, int id_biblioteca,
			AsyncCallback<Void> callback);

	void getPuntiDiServizioDecentratiByIdBiblioteca(int id_biblioteca,
			AsyncCallback<List<BiblioModel>> callback);

	void addPuntoDiServizioDecentrato(int idBibliotecaPadre, String isilPrFiglia, String isilNrFiglia, AsyncCallback<Void> callback);

	void removePuntoDiServizioDecentrato(int id_bibloteca,
			AsyncCallback<Void> callback);

	void getPuntiDiServizioDecentratiPossibili(/*String isil_provincia,*/
			ModelData loadConfig,
			AsyncCallback<PagingLoadResult<BiblioModel>> callback);

	void getSistemiBibliotecheByIdBiblioteca(int id_biblioteca,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void addSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche,
			AsyncCallback<Boolean> callback);

	void removeSistemaBiblioteca(int id_biblioteca, int id_sistema_biblioteche,
			AsyncCallback<Void> callback);
	
	void updateInfoLocali(HashMap<String, Object> params, int id_biblioteca,
			AsyncCallback<Void> callback);

	void updateProfiloStoricoSede(HashMap<String, Object> params,
			int id_biblioteca, AsyncCallback<Void> callback);

	void updateInfoPostazioni(HashMap<String, Object> params,
			int id_biblioteca, AsyncCallback<Void> callback);

	void getEntiPaginatiFilteredPerCombos(ModelData loadConfig,
			AsyncCallback<PagingLoadResult<EnteModel>> callback);

	void setEnte(int id_biblioteca, HashMap<String, Object> params,
			AsyncCallback<Void> callback);

	void setAutonomiaAmministrativa(int idBiblio,
			HashMap<String, Object> params, AsyncCallback<Void> asyncCallback);

	void setTipologiaFunzionale(int idBiblio, VoceUnicaModel value,
			AsyncCallback<Void> asyncCallback);

	void setInfoFondazione(int idBiblio, HashMap<String, Object> params,
			AsyncCallback<Void> asyncCallback);

	void setModalitaAccesso(int idBiblio, HashMap<String, Object> params,	AsyncCallback<Void> asyncCallback);

	void getModalitaAccessoByIdBiblioteca(int id_biblioteca,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void addModalitaAccesso(int id_biblioteca, int id_nuovaModalita,
			AsyncCallback<Void> asyncCallback);

	void removeModalitaAccesso(int id_biblioteca, int id_removeModalita,
			AsyncCallback<Void> callback);

	void getDestinazioniSociali(int id_biblioteca,
			AsyncCallback<List<DestinazioneSocialeModel>> callback);

	void addDestinazioneSociale(int id_biblioteca, boolean modifica, int id_nuovaDestinazione,
			String note, AsyncCallback<Boolean> asyncCallback);

	void removeDestinazioneSociale(int id_biblioteca, int id_rimuoviModalita,
			AsyncCallback<Void> asyncCallback);

	void setAccessoHandicap(int idBiblio, Boolean b,AsyncCallback<Void> asyncCallback);

	void setRegolamento(HashMap<String, String> params, int idBiblio,
			AsyncCallback<Void> asyncCallback);

	void getOrariUfficialiByDay(int id_biblioteca,int id_day,
			AsyncCallback<List<OrariModel>> callback);

	void addNuovoOrarioGiornaliero(int id_biblioteca, int id_day,
			OrariModel toSave, boolean modifica, AsyncCallback<Void> asyncCallback);

	void addNuovoOrarioGiornalieroCustom(int id_biblio, Vector<Integer> id_days,
			OrariModel toSave, AsyncCallback<Void> asyncCallback);

	void removeOrarioUfficiale(int id_orarioToRemove,
			AsyncCallback<Void> asyncCallback);

	void getVariazioniOrari(int id_biblioteca,
			AsyncCallback<List<OrariModel>> callback);

	void addNuovaVariazioneOrario(int id_biblioteca, OrariModel toSave,boolean modifica,
			AsyncCallback<Void> asyncCallback);
	
	void addNuovaVariazioneOrarioCustom(int id_biblioteca, Vector<Integer> id_days, OrariModel toSave, AsyncCallback<Void> callback);
	
	void removeVariazioneOrario(int id_variazioneOrarioToRemove,
			AsyncCallback<Void> asyncCallback);

	void getPeriodiChiusuraByIdBiblio(int id_biblioteca,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void addNuovoPeriodoChiusura(int id_biblioteca, VoceUnicaModel tmpSave,
			boolean modifica, AsyncCallback<Void> asyncCallback);

	void removePeriodoChiusura(int id_chiusuraToRemove,
			AsyncCallback<Void> asyncCallback);

	void setConsistenzaFondiAntichi1830(int idBiblio, int id_consistenza,
			AsyncCallback<Void> asyncCallback);

	void getListaPatrimonioSpecializzazione(int id_biblioteca,
			AsyncCallback<List<PatrimonioLibrarioModel>> callback);

	void addPatrimonioSpeciale(int id_biblioteca, boolean modifica, int id_nuovoPatr,
			int quantita, int quantitaUltimoAnno, AsyncCallback<Boolean> asyncCallback);

	void removePatrimonioSpeciale(int id_biblioteca, int id_rimuoviPatr,
			AsyncCallback<Void> asyncCallback);

	void getSpecializzazioniByIdBiblioteca(int id_biblioteca,
			AsyncCallback<List<SpecializzazioneModel>> callback);

	void addSpecializzazionePatrimonio(SpecializzazioneModel modelToSave,
			AsyncCallback<Void> asyncCallback);

	void removeSpecializzazionePatrimonio(int id_biblioteca,
			String idr_removeRecord, AsyncCallback<Void> asyncCallback);

	void getFondiSpecialiByIdBiblioteca(int id_biblioteca,
			AsyncCallback<List<FondiSpecialiModel>> callback);

	void getDenominazioneFondiSpecialiPossibiliFilteredPerCombos(ModelData loadConfig,
			AsyncCallback<PagingLoadResult<FondiSpecialiModel>> callback);

	void addFondoSpeciale(FondiSpecialiModel modelToSave,int idBiblioteca, boolean modifica,
			AsyncCallback<FondiSpecialiModel> asyncCallback);

	void removeFondiSpeciali(int id_biblioteca, int id_removeRecord,
			AsyncCallback<Void> asyncCallback);

	void getEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int idTabellaDinamica,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(int idToSave, int id_biblioteca,
			int idTabellaDinamica, AsyncCallback<Void> asyncCallback);

	void removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(
			int id_biblioteca, int id_rimuoviModalita, int idTabellaDinamica,
			AsyncCallback<Void> asyncCallback);

	void getSpogliMaterialBibliograficoPerPaginazioneCombobox(
			int id_biblioteca, ModelData loadConfig,
			AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback);

	void getListaSpogliMaterialeBibliograficoByIdBiblio(int id_biblioteca,
			AsyncCallback<List<VoceUnicaModel>> callback);

	void addSpoglioMaterialeBibliografico(VoceUnicaModel modelToSave, int id_biblioteca, boolean modifica, AsyncCallback<Void> asyncCallback);

	void removeSpogliMaterialeBibliografico(int id_rimuoviSpoglio, AsyncCallback<Void> asyncCallback);

	void getlistaPubblicazioniByIdBiblio(int id_biblioteca,	AsyncCallback<List<VoceUnicaModel>> callback);

	void addPubblicazioni(VoceUnicaModel modelToSave, int id_biblioteca,boolean modifica, AsyncCallback<Void> asyncCallback);

	void removePubblicazioni(int id_rimuoviPubblicazione, AsyncCallback<Void> asyncCallback);

	void inserisciBibliograficaInfoCatalogazione(int id_biblio, String value,
			AsyncCallback<Void> asyncCallback);

	void getPartecipaCataloghiCollettiviByIdBiblio(int id_biblioteca,
			AsyncCallback<List<PartecipaCataloghiCollettiviModel>> callback);

	void addPartecipaCatalogoCollettivo(int id_biblioteca,
			PartecipaCataloghiCollettiviModel tmpCatalogo,
			boolean modifica, AsyncCallback<Void> asyncCallback);

	void getPartecipaCataloghiSpecialiByIdBiblio(int id_biblioteca,
			AsyncCallback<List<PartecipaCataloghiSpecialiModel>> callback);

	void addPartecipaCatalogoSpeciale(int id_biblioteca,PartecipaCataloghiSpecialiModel tmpCatalogo, 
			boolean modifica,AsyncCallback<Void> asyncCallback);

	void removePartecipaCatalogoSpeciale(int idRemove,int id_biblioteca,AsyncCallback<Void> asyncCallback);

	void removePartecipaCatalogoCollettivo(int idRemove,int id_biblioteca,AsyncCallback<Void> callback);

	void removePartecipaCatalogoGenerale(int idRemove,int id_biblioteca,AsyncCallback<Void> callback);

	void getPartecipaCataloghiGeneraliByIdBiblio(int id_biblioteca,
			AsyncCallback<List<PartecipaCataloghiGeneraliModel>> callback);

	void addPartecipaCatalogoGenerale(int id_biblioteca,
			PartecipaCataloghiGeneraliModel tmpCatalogo, boolean modifica,
			AsyncCallback<Void> asyncCallback);

	void getBibliotecheReport(final PagingLoadConfig config,
			AsyncCallback<PagingLoadResult<BiblioModel>> callback);

	void getServiziRiproduzioniFinitureByIdBiblio(int id_biblioteca,
			AsyncCallback<List<ServiziRiproduzioniModel>> callback);

	void addServiziRiproduzioniForniture(int id_biblioteca,
			ServiziRiproduzioniModel tmpServizio, boolean modifica,
			AsyncCallback<Void> asyncCallback);

	void removeServiziRiproduzioniForniture(int idRemove, int id_biblioteca,
			AsyncCallback<Void> asyncCallback);

	void setServizioBibliograficoInternoEsterno(int id_biblio, Boolean hasAttivoInformazioniBibliografiche, Boolean hasServizioBibliograficoInterno, Boolean hasServizioBibliograficoEsterno, 
			AsyncCallback<Void> asyncCallback);

	void getModalitaComunicazioniBibliograficheByIdBiblio(int id_biblioteca,AsyncCallback<List<VoceUnicaModel>> callback);

	void addModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idTipoComunicazione, AsyncCallback<Void> asyncCallback);

	void removeModalitaComunicazioneInformazioneBibliografica(int id_biblioteca, int idRemove, AsyncCallback<Void> asyncCallback);

	void getSezioniSpecialiByIdBiblio(int id_biblioteca,AsyncCallback<List<VoceUnicaModel>> callback);

	void removeSezioniSpeciali(int id_biblioteca, int idRemove,	AsyncCallback<Void> asyncCallback);

	void addSezioniSpeciali(int id_biblioteca, int idSezioneSpeciale, AsyncCallback<Void> asyncCallback);

	void updateModalitaAccessoInternet(int id_biblio,HashMap<String, String> keys, AsyncCallback<Void> asyncCallback);

	void getPrestitiLocaliByIdBiblio(int id_biblioteca,
			AsyncCallback<List<PrestitoLocaleModel>> callback);

	void addPrestitoLocaleToBiblio(int id_biblioteca,PrestitoLocaleModel tmpPrestito, boolean modifica,
			AsyncCallback<Void> asyncCallback);

	void removePrestitoLocaleFromBiblio(int id_biblioteca,int id_removePrestito, AsyncCallback<Void> asyncCallback);

	void getPrestitoInterbibliotecarioERuoloByIdBiblio(int id_biblioteca,
			AsyncCallback<List<PrestitoInterbibliotecarioRuoloModel>> callback);

	void addPrestitoInterbibliotecarioToBiblio(int id_biblioteca,PrestitoInterbibliotecarioRuoloModel tmpPrestito, boolean modifica,
			AsyncCallback<Void> asyncCallback);

	void removePrestitoInterbibliotecarioFromBiblio(int id_biblioteca,
			int id_removePrestito, AsyncCallback<Void> asyncCallback);

	void setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(
			int id_biblio, Boolean hasPrestitoNazionale,
			Boolean hasPrestitoInternazionale,
			Boolean hasProcedureAutomatizzate, AsyncCallback<Void> asyncCallback);

	void setInfoPersonale(int id_biblio,HashMap<String, Object> personaleValues,AsyncCallback<Void> asyncCallback);

	void setInfoUtenti(int id_biblio, HashMap<String, Object> utentiValues,	AsyncCallback<Void> asyncCallback);

	void setInfoBilancio(int id_biblio, HashMap<String, Object> bilancioValues,
			AsyncCallback<Void> asyncCallback);

	void getDepositiLegaliByIdBiblio(int id_biblioteca,	AsyncCallback<List<DepositiLegaliModel>> callback);

	void addDepositoLegaleToBiblio(int id_biblioteca, boolean modifica, int id_nuovoTipoDeposito,
			String anno, AsyncCallback<Boolean> asyncCallback);

	void removeDepositoLegaleFromBiblio(int id_biblioteca,
			int id_rimuoviDepositoLegale, AsyncCallback<Void> asyncCallback);

	void inserisciNoteCatalogazione(int id_biblio, String value,AsyncCallback<Void> asyncCallback);

	void inserisciComunicazioniCatalogazione(int id_biblio, String value,	AsyncCallback<Void> asyncCallback);

	/**
	 * ---------------------- CHIAMATE PER GESTIONE WORKFLOW BIBLIOTECA 
	 */
	void setOccupata(int id, AsyncCallback<Void> callback);
	
	void setCancellata(int id, AsyncCallback<Void> callback);
	
	void setInRevisione(int id, AsyncCallback<Void> callback);
	
	void setDefinitiva(int id, String messaggio, AsyncCallback<Void> callback);
	
	void ripristina(int id, AsyncCallback<Void> callback);
	
	void respingiRevisione(int id_biblio, String messaggio, AsyncCallback<Void> callback);

	void differenze(int id_biblio, AsyncCallback<String> callback);

	void setInventarioCartaceo(int idBiblio, Boolean b, AsyncCallback<Void> callback);

	void setInventarioInformatizzato(int idBiblio, Boolean b, AsyncCallback<Void> callback);

	void setCatalogoTopograficoCartaceo(int idBiblio, Boolean b, AsyncCallback<Void> callback);

	void setCatalogoTopograficoInformatizzato(int idBiblio, Boolean b, AsyncCallback<Void> callback);
	
	void setCatInvFondi(int idBib, HashMap<String, Object> params, AsyncCallback<Void> callback);
	
	void getUsersByBiblio(PagingLoadConfig config, AsyncCallback<PagingLoadResult<UserModel>> callback);

	void setDefinitiva(List<Integer> bibliotecheSelectedIds, AsyncCallback<Void> asyncCallback);
	
	void ripristina(List<Integer> bibliotecheSelectedIds, AsyncCallback<Integer> asyncCallback);

	void respingiRevisione(List<Integer> bibliotecheSelectedIds, AsyncCallback<Void> asyncCallback);

	void aggiornaCodici(HashMap<String, Object> params, int idBiblio,AsyncCallback<Void> asyncCallback);

	void aggiornaCodiciOthers(HashMap<String, Object> params, int idBiblio,	AsyncCallback<Void> asyncCallback);

	void setStatoCatalogazione(HashMap<String, Object> params, AsyncCallback<Boolean> asyncCallback);
	
	void setAttivoRiproduzioni(int idbib, Boolean attivoRiproduzioni, AsyncCallback<Void> callback);
	
	void setAttivoPrestitoLocale(int idbib, Boolean attivoPrestitoLocale, AsyncCallback<Void> callback);
	
	void getListaSistemiPrestitoInterbibliotecario(int id_biblioteca, AsyncCallback<List<SistemiPrestitoInterbibliotecarioModel>> callback);
	
	void removeSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistemaPrestitoInterbibliotecario, AsyncCallback<Void> callback);
	
	void addSistemaPrestitoInterbibliotecario(int id_biblioteca, int id_sistemaPrestitoInterbibliotecario, AsyncCallback<Void> callback);
	
	void setReference(int id_biblio, Boolean hasAttivoReference, Boolean hasReferenceLocale, Boolean hasReferenceOnline, AsyncCallback<Void> callback);
	
	void setAttivoDocumentDelivery(int idbib, Boolean attivoDocumentDelivery, AsyncCallback<Void> callback);
	
	void getDocumentDeliveryByIdBiblio(int id_biblioteca, AsyncCallback<List<VoceUnicaModel>> callback);

	void addDocumentDelivery(int id_biblioteca, int idDocumentDelivery, AsyncCallback<Void> callback);

	void removeDocumentDelivery(int id_biblioteca, int idRemove, AsyncCallback<Void> callback);
	
	void setAttivoDepositoLegale(int idbib, Boolean attivoDepositoLegale, AsyncCallback<Void> callback);
	
	void updateCensimento(int id_biblioteca, Integer anno, AsyncCallback<Void> callback);
	
	void getPadriPossibiliServizioDecentrato(ModelData loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback);
	
	void addPadreServizioDecentrato(int idBiblioFiglio, String isilProvinciaPadre, String isilNumeroPadre, AsyncCallback<Void> callback);
	
	void getListaIsilProvincia(ModelData loadConfig, AsyncCallback<List<VoceUnicaModel>> callback);
	
	void removePadreServizioDecentrato(int idBiblioFiglio, AsyncCallback<Void> callback);
	
	void getPhotos(int id_biblioteca, AsyncCallback<List<PhotoModel>> callback);
	
	void addPhoto(int id_biblioteca, String caption, String uri, AsyncCallback<Void> callback);
	
	void updatePhotoCaption(int id_photo, String caption, AsyncCallback<Void> callback);
	
	void removePhoto(int id_biblioteca, int id_photo, AsyncCallback<Void> callback);
	
	void updatePhotoOrder(List<PhotoModel> photo, AsyncCallback<Void> callback);

}