package it.inera.abi.logic.impl;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.dao.ContattiDao;
import it.inera.abi.dao.DuplicateEntryException;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.dto.DynaTabDTO;
import it.inera.abi.logic.AbiBiblioLogic;
import it.inera.abi.logic.AbiTabelleDinamicheLogic;
import it.inera.abi.logic.TrasferimentoBiblioteca;
import it.inera.abi.logic.UserActionLog;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Bibliografia;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.CataloghiGeneraliUrl;
import it.inera.abi.persistence.CataloghiSpecialiMaterialeUrl;
import it.inera.abi.persistence.Codici;
import it.inera.abi.persistence.Comunicazioni;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.FondiSpeciali;
import it.inera.abi.persistence.IndicizzazioneClassificata;
import it.inera.abi.persistence.IndicizzazioneSoggetto;
import it.inera.abi.persistence.NormeCatalogazione;
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
import it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso;
import it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.RiproduzioniTipo;
import it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.StatoCatalogazione;
import it.inera.abi.persistence.Utenti;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe che implementa la logica delle principali operazioni riguardanti
 * il trasferimento di una biblioteca (salva, ripristina, backup xml)
 *
 */
@Service
public class TrasferimentoBiblioImpl implements TrasferimentoBiblioteca {

	@Autowired private UserActionLog userActionLog; // logger per le azioni di salvataggio/modifica dell'utente

	private Log _log = LogFactory.getLog(TrasferimentoBiblioImpl.class);

	private @Value("${abi.saved.dir}") String saveDir;
	private @Value("${abi.backup.dir}") String backupDir;

	@Autowired private AbiBiblioLogic abiBiblioLogic;
	@Autowired private AbiTabelleDinamicheLogic abiTabelleDinamicheLogic;
	@Autowired private BiblioDao biblioDao;
	@Autowired private ContattiDao contattiDao;


	/**
	 * Salva la biblioteca JPA via Castor in formato XML sul filesystem
	 */
	@Override
	public void salva(int idbiblio) throws Exception {
		Biblioteca biblioteca = biblioDao.getBibliotecaByIdForMarshall(idbiblio);
		if (biblioteca == null) return;
		prepareForMarshalling(biblioteca);

		// salvo lo stato iniziale la biblioteca dal database
		String fullFilename = Utility.getSavedFilename(idbiblio, saveDir);

		File toSave = new File(fullFilename);
		// il file non deve esistere, la biblioteca si salva una sola volta 
		if (toSave.exists()) {
			_log.warn("Il file esiste già:" + fullFilename);
			return;
			//throw new Exception("Errore nel salvare la biblioteca in XML, il file esiste già:" + fullFilename);
		}

		try {
			Marshaller marshaller = new Marshaller(new FileWriter(toSave));
			marshaller.marshal(biblioteca);
		} catch (Exception e) {
			_log.error("Errore nel salvare la biblioteca in XML", e);
			throw e;
		}
		toSave = null;
	}



	/**
	 * Ripristina sul DB la biblioteca Castor in formato XML via JPA
	 */
	@Override
	@Transactional
	public void ripristina(int idbiblio) throws Exception {
		String fullFilename = Utility.getSavedFilename(idbiblio, saveDir);
		try {
			

			Biblioteca bibliotecaSalvata = (Biblioteca) Unmarshaller.unmarshal(Biblioteca.class, new FileReader(new File(fullFilename)));

			Biblioteca bibliotecaAttuale = biblioDao.getBibliotecaById(idbiblio);

			userActionLog.logActionCatalogazioneBiblioDefaultUser("INIZIO RIPRISTINO BIBLIOTECA - id_biblioteca="+idbiblio);

			
			// **********denominazione ufficiale**********
			abiBiblioLogic.aggiornaDenominazioneUfficiale(bibliotecaSalvata.getDenominazioneUfficiale(), idbiblio);

			// **********denominazioni precedenti**********
			if (bibliotecaAttuale.getDenominazioniPrecedentis() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getDenominazioniPrecedentis());
			}
			if (bibliotecaSalvata.getDenominazioniPrecedentis() != null) {
				for (DenominazioniPrecedenti denominazioniPrecedenti : bibliotecaSalvata.getDenominazioniPrecedentis()) {
					denominazioniPrecedenti.setIdDenominazioniPrecedenti(null);
					denominazioniPrecedenti.setBiblioteca(bibliotecaAttuale);
					abiBiblioLogic.addDenominazionePrecendente(denominazioniPrecedenti,false);
				}
			}

			// **********denominazioni altenative**********
			if (bibliotecaAttuale.getDenominazioniAlternatives() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getDenominazioniAlternatives());
			}
			if (bibliotecaSalvata.getDenominazioniAlternatives() != null) {
				for (DenominazioniAlternative denominazioniAlternative : bibliotecaSalvata.getDenominazioniAlternatives()) {
					denominazioniAlternative.setIdDenominazioniAlternative(null);
					denominazioniAlternative.setBiblioteca(bibliotecaAttuale);
					abiBiblioLogic.addDenominazioneAlternativa(denominazioniAlternative,false);
				}
			}


			// **********indirizzo********** 
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("indirizzo", bibliotecaSalvata.getIndirizzo());
			param.put("frazione", bibliotecaSalvata.getFrazione());
			param.put("cap", bibliotecaSalvata.getCap());
			param.put("idComune", bibliotecaSalvata.getComune().getIdComune());

			if (bibliotecaSalvata.getGeolocalizzazione() != null) {
				if (bibliotecaSalvata.getGeolocalizzazione().getLatitudine() != null && 
						bibliotecaSalvata.getGeolocalizzazione().getLongitudine() != null) {
					param.put("latitudine", bibliotecaSalvata.getGeolocalizzazione().getLatitudine());
					param.put("longitudine", bibliotecaSalvata.getGeolocalizzazione().getLongitudine());
				}
			}
			abiBiblioLogic.aggiornaIndirizzo(param, idbiblio);



			// **********recapiti**********
			if (bibliotecaAttuale.getContattis() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getContattis());
			}
			if (bibliotecaSalvata.getContattis() != null) {
				for (Contatti contatto: bibliotecaSalvata.getContattis()) {
					contatto.setIdContatti(null);
					contatto.setBiblioteca(bibliotecaAttuale);
					contattiDao.saveContatti(contatto);	
				}
			}



			// **********punti decentrati**********
			bibliotecaAttuale.setBibliotecaPadre(bibliotecaSalvata.getBibliotecaPadre());
			if (bibliotecaAttuale.getBibliotecasFigli() != null) {
				for (Biblioteca figlio: bibliotecaAttuale.getBibliotecasFigli()) {
					biblioDao.removePuntoDiServizioDecentrato(figlio.getIdBiblioteca());	
				}
			}
			if (bibliotecaSalvata.getBibliotecasFigli() != null) {
				for (Biblioteca figlio: bibliotecaSalvata.getBibliotecasFigli()) {
					biblioDao.addPuntoDiServizioDecentrato(idbiblio, figlio.getIdBiblioteca());	
				}
			}


			// **********sistemi biblioteche**********
			Vector<Integer> toRemoveId = new Vector<Integer>();
			if (bibliotecaAttuale.getSistemiBiblioteches() != null) {

				for (SistemiBiblioteche sistemiBiblioteche: bibliotecaAttuale.getSistemiBiblioteches()) {
					toRemoveId.add(sistemiBiblioteche.getIdSistemiBiblioteche());
				}
				for (Integer id: toRemoveId) {
					biblioDao.removeSistemaBiblioteca(idbiblio, id);
				}
			}
			if (bibliotecaSalvata.getSistemiBiblioteches() != null) {
				for (SistemiBiblioteche sistemiBiblioteche: bibliotecaSalvata.getSistemiBiblioteches()) {
					biblioDao.addSistemaBiblioteca(idbiblio, sistemiBiblioteche.getIdSistemiBiblioteche());
				}
			}

			// **********profilo storico e sede**********
			bibliotecaAttuale.setEdificioMonumentale(bibliotecaSalvata.getEdificioMonumentale());
			bibliotecaAttuale.setEdificioDenominazione(bibliotecaSalvata.getEdificioDenominazione());
			bibliotecaAttuale.setEdificioAppositamenteCostruito(bibliotecaSalvata.getEdificioAppositamenteCostruito());
			bibliotecaAttuale.setEdificioDataCostruzione(bibliotecaSalvata.getEdificioDataCostruzione());

			// **********locali**********
			bibliotecaAttuale.setMlAperti(bibliotecaSalvata.getMlAperti());
			bibliotecaAttuale.setMlMagazzini(bibliotecaSalvata.getMlMagazzini());
			bibliotecaAttuale.setMqPubblici(bibliotecaSalvata.getMqPubblici());
			bibliotecaAttuale.setMqTotali(bibliotecaSalvata.getMqTotali());

			// **********postazioni**********
			bibliotecaAttuale.setPostiAudio(bibliotecaSalvata.getPostiAudio());
			bibliotecaAttuale.setPostiInternet(bibliotecaSalvata.getPostiInternet());
			bibliotecaAttuale.setPostiLettura(bibliotecaSalvata.getPostiLettura());
			bibliotecaAttuale.setPostiVideo(bibliotecaSalvata.getPostiVideo());


			// **********Ente di appartenenza**********
			Ente ente = bibliotecaSalvata.getEnte();
			abiBiblioLogic.setEnte(idbiblio, ente.getStato(), ente.getEnteTipologiaAmministrativa(), ente.getDenominazione());


			// **********Autonomia amministrativa della biblioteca**********
			bibliotecaAttuale.setAutonomiaAmministrativa(bibliotecaSalvata.getAutonomiaAmministrativa());
			bibliotecaAttuale.setStrutturaGerarchicaSovraordinata(bibliotecaSalvata.getStrutturaGerarchicaSovraordinata());


			// **********Tipologia funzionale**********
			abiBiblioLogic.setTipologiaFunzionale(idbiblio, bibliotecaSalvata.getTipologiaFunzionale());

			// **********Fondazione**********
			bibliotecaAttuale.setDataFondazione(bibliotecaSalvata.getDataFondazione());
			bibliotecaAttuale.setDataIstituzione(bibliotecaSalvata.getDataIstituzione());


			// **********info accesso**********
			bibliotecaAttuale.setAccessoRiservato(bibliotecaSalvata.getAccessoRiservato());

			// **********Accesso modalità**********
			if (bibliotecaAttuale.getAccessoModalitas() != null) {
				Vector<Integer> idsModalita = new Vector<Integer>();
				for (AccessoModalita accessoModalita : bibliotecaAttuale.getAccessoModalitas()) {
					idsModalita.add( accessoModalita.getIdAccessoModalita());
				}
				for(Integer idmodalita :idsModalita){
					abiBiblioLogic.removeModalitaAccesso(idbiblio, idmodalita);
				}
			}
			if (bibliotecaSalvata.getAccessoModalitas() != null) {
				for (AccessoModalita accessoModalita : bibliotecaSalvata.getAccessoModalitas()) {
					abiBiblioLogic.addModalitaAccesso(idbiblio, accessoModalita.getIdAccessoModalita());
				}
			}
			// ********* dest sociali *************
			if (bibliotecaAttuale.getDestinazioniSocialis() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getDestinazioniSocialis());
			} 
			if (bibliotecaSalvata.getDestinazioniSocialis() != null) {
				for (DestinazioniSociali destinazioniSociali : 	bibliotecaSalvata.getDestinazioniSocialis()) {
					abiBiblioLogic.addDestinazioniSociali(idbiblio, destinazioniSociali.getId().getIdDestinazioniSocialiTipo(), destinazioniSociali.getNote());
				}
			}
			bibliotecaAttuale.setAccessoHandicap(bibliotecaSalvata.getAccessoHandicap());

			// ********* regolamento **************
			if (bibliotecaAttuale.getRegolamentos() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getRegolamentos());
			}
			if( bibliotecaSalvata.getRegolamentos()!=null){
				for (Regolamento regolamento : bibliotecaSalvata.getRegolamentos()) {
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("url", regolamento.getUrl());
					params.put("riferimentoNormativo", regolamento.getRiferimentoNormativa());
					abiBiblioLogic.setRegolamento(params, idbiblio);
				}
			}

			// ********* orario ufficiale ************** 
			if(bibliotecaAttuale.getOrarioUfficialis() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getOrarioUfficialis());
			}
			if( bibliotecaSalvata.getOrarioUfficialis()!=null){
				for (OrarioUfficiali orarioUfficiali : bibliotecaSalvata.getOrarioUfficialis()) {
					orarioUfficiali.setIdOrarioUfficiali(null);
					abiBiblioLogic.addNuovoOrarioGiornaliero(idbiblio, orarioUfficiali);
				}
			}

			// ********* variazioni d'orario************** 
			if(bibliotecaAttuale.getOrarioVariazionis() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getOrarioVariazionis());
			}
			if( bibliotecaSalvata.getOrarioVariazionis()!=null){
				for (OrarioVariazioni orarioVariazioni : bibliotecaSalvata.getOrarioVariazionis()) {
					orarioVariazioni.setIdOrarioVariazioni(null);
					abiBiblioLogic.addNuovaVariazioneOrario(idbiblio, orarioVariazioni);
				}
			}
			// ********* chiusure *********
			if(bibliotecaAttuale.getOrarioChiusures() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getOrarioChiusures());
			}
			if( bibliotecaSalvata.getOrarioChiusures()!=null){
				for (OrarioChiusure orarioChiusure : bibliotecaSalvata.getOrarioChiusures()) {
					orarioChiusure.setIdOrarioChiusure(null);
					abiBiblioLogic.addNuovoPeriodoChiusura(idbiblio, orarioChiusure);
				}
			}

			// ********* patrimonio librario ************** 
			if(bibliotecaAttuale.getPatrimonios() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getPatrimonios());
			} 
			if( bibliotecaSalvata.getPatrimonios()!=null){
				for (Patrimonio patrimonio : bibliotecaSalvata.getPatrimonios()) {
					abiBiblioLogic.addPatrimonioSpeciale(
							idbiblio, 
							patrimonio.getId().getIdPatrimonioSpecializzazione(), 
							patrimonio.getQuantita(), 
							patrimonio.getQuantitaUltimoAnno()
					);
				}
			}

			// ******** Inventario - Catalogo topografico - Fondi antichi (fino al 1830) ********
			abiBiblioLogic.setConsistenzaFondiAntichi1830(idbiblio,  bibliotecaSalvata.getFondiAntichiConsistenza().getIdFondiAntichiConsistenza());
			bibliotecaAttuale.setInventarioCartaceo(bibliotecaSalvata.getInventarioCartaceo());
			bibliotecaAttuale.setInventarioInformatizzato(bibliotecaSalvata.getInventarioInformatizzato());
			bibliotecaAttuale.setCatalogoTopograficoCartaceo(bibliotecaSalvata.getCatalogoTopograficoCartaceo());
			bibliotecaAttuale.setCatalogoTopograficoInformatizzato(bibliotecaSalvata.getCatalogoTopograficoInformatizzato());


			
			// ******* Specializzazioni *******
			if(bibliotecaAttuale.getDeweyLiberos() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getDeweyLiberos());
			} 
			if( bibliotecaSalvata.getDeweyLiberos()!=null){
				for (DeweyLibero deweyLibero : bibliotecaSalvata.getDeweyLiberos()) {
					abiBiblioLogic.addSpecializzazionePatrimonio(idbiblio, deweyLibero.getId().getIdDewey(), deweyLibero.getDescrizione());
				}
			}
			
			// ******* fondi speciali *******
			if(bibliotecaAttuale.getFondiSpecialis() != null){
				bibliotecaAttuale.getFondiSpecialis().clear();
			} 
			
			if( bibliotecaSalvata.getFondiSpecialis()!=null){
				for (FondiSpeciali fondiSpeciali : bibliotecaSalvata.getFondiSpecialis()) {
					abiBiblioLogic.addFondoSpeciale(fondiSpeciali.getIdFondiSpeciali(), idbiblio, false);
				}
			}

			// ******** sistemi di indicizzazione Classificata *******
			if(bibliotecaAttuale.getIndicizzazioneClassificatas() != null){
				Vector<Integer> idsIndicizzazioneClassificatas = new Vector<Integer>();
				for (IndicizzazioneClassificata indicizzazioneClassificata : bibliotecaAttuale.getIndicizzazioneClassificatas()) {
					idsIndicizzazioneClassificatas.add(indicizzazioneClassificata.getIdIndicizzazioneClassificata());
				}
				for(Integer idIndicizzazioneClassificata:idsIndicizzazioneClassificatas){
					abiBiblioLogic.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(idbiblio, idIndicizzazioneClassificata, DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX);
				}
			}
			if( bibliotecaSalvata.getIndicizzazioneClassificatas()!=null){
				for (IndicizzazioneClassificata indicizzazioneClassificata : bibliotecaSalvata.getIndicizzazioneClassificatas()) {
					DynaTabDTO dynaTabDTODB = new DynaTabDTO();
					dynaTabDTODB.setId(indicizzazioneClassificata.getIdIndicizzazioneClassificata());
					dynaTabDTODB.setValue(indicizzazioneClassificata.getDescrizione());
					dynaTabDTODB.setIdTabella(DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX);
					abiBiblioLogic.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(dynaTabDTODB,	idbiblio,DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX);
				}
			}

			// ******** sistemi di indicizzazione per Soggetto *******
			if(bibliotecaAttuale.getIndicizzazioneSoggettos() != null){
				Vector<Integer> idsIndicizzazioneSoggettos = new Vector<Integer>();

				for (IndicizzazioneSoggetto indicizzazioneSoggetto : bibliotecaAttuale.getIndicizzazioneSoggettos()) {
					idsIndicizzazioneSoggettos.add(indicizzazioneSoggetto.getIdIndicizzazioneSoggetto());
				}

				for (Integer idIndicizzazioneSoggetto:idsIndicizzazioneSoggettos){
					abiBiblioLogic.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(idbiblio,idIndicizzazioneSoggetto, DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX);
				}
			}
			if( bibliotecaSalvata.getIndicizzazioneSoggettos()!=null){
				for (IndicizzazioneSoggetto indicizzazioneSoggetto : bibliotecaSalvata.getIndicizzazioneSoggettos()) {
					DynaTabDTO dynaTabDTODB = new DynaTabDTO();
					dynaTabDTODB.setId(indicizzazioneSoggetto.getIdIndicizzazioneSoggetto());
					dynaTabDTODB.setValue(indicizzazioneSoggetto.getDescrizione());
					dynaTabDTODB.setIdTabella(DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX);
					abiBiblioLogic.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica( dynaTabDTODB, idbiblio, DtoJpaMapping.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX);
				}
			}
			// ******** norme di catalogazione *******
			if(bibliotecaAttuale.getNormeCatalogaziones() != null){
				Vector<Integer> idsNormeCatalogaziones = new Vector<Integer>();
				for (NormeCatalogazione normeCatalogazione : bibliotecaAttuale.getNormeCatalogaziones()) {
					idsNormeCatalogaziones.add(normeCatalogazione.getIdNormeCatalogazione());
				}

				for(Integer idNormeCatalogazione:idsNormeCatalogaziones){
					abiBiblioLogic.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(idbiblio, idNormeCatalogazione, DtoJpaMapping.CATALOGAZIONE_NORME_INDEX);
				}
			}
			if( bibliotecaSalvata.getNormeCatalogaziones()!=null){
				for (NormeCatalogazione normeCatalogazione : bibliotecaSalvata.getNormeCatalogaziones()) {
					DynaTabDTO dynaTabDTODB = new DynaTabDTO();
					dynaTabDTODB.setId(normeCatalogazione.getIdNormeCatalogazione());
					dynaTabDTODB.setValue(normeCatalogazione.getDescrizione());
					dynaTabDTODB.setIdTabella(DtoJpaMapping.CATALOGAZIONE_NORME_INDEX);
					abiBiblioLogic.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica( dynaTabDTODB, idbiblio, DtoJpaMapping.CATALOGAZIONE_NORME_INDEX);
				}
			}
			
			// ******** Spogli materiale bibliografico *******
			if(bibliotecaAttuale.getSpogliBibliograficis() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getSpogliBibliograficis());
			}
			if( bibliotecaSalvata.getSpogliBibliograficis()!=null){
				for (SpogliBibliografici spogliBibliografici : bibliotecaSalvata.getSpogliBibliograficis()) {
					abiBiblioLogic.addSpoglioMaterialeBibliograficoRipristino(spogliBibliografici.getDescrizioneBibliografica(), idbiblio);
				}
			}
			// ******** Pubblicazioni *******
			if(bibliotecaAttuale.getPubblicazionis() != null){
				biblioDao.removeChilds(bibliotecaAttuale.getPubblicazionis());
			}
			if( bibliotecaSalvata.getPubblicazionis()!=null){

				for (Pubblicazioni pubblicazioni : bibliotecaSalvata.getPubblicazionis()) {
					pubblicazioni.setIdPubblicazioni(null);
					abiBiblioLogic.addPubblicazioni(pubblicazioni, idbiblio, false);
				}
			}

			// ******** Bibliografia *******
			if (bibliotecaAttuale.getBibliografias() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getBibliografias());
			}
			if( bibliotecaSalvata.getBibliografias()!=null){
				for (Bibliografia bibliografia : bibliotecaSalvata.getBibliografias()) {
					abiBiblioLogic.inserisciBibliograficaInfoCatalogazione(idbiblio, bibliografia.getDescrizione());
				}
			}
		
			// ******** Cataloghi generali *******
			if (bibliotecaAttuale.getPartecipaCataloghiGeneralis() != null) {
				Vector<Integer> ids = new Vector<Integer>();
				for (PartecipaCataloghiGenerali p :bibliotecaAttuale.getPartecipaCataloghiGeneralis()) {
					ids.add(p.getIdCataloghiGenerali());
				}
				for(Integer id :ids){
					abiBiblioLogic.removePartecipaCatalogoGenerale(id, idbiblio);
				}
			}
			if( bibliotecaSalvata.getPartecipaCataloghiGeneralis()!=null){
				for (PartecipaCataloghiGenerali cat : bibliotecaSalvata.getPartecipaCataloghiGeneralis()) {
					cat.setBiblioteca(bibliotecaAttuale);
					cat.setIdCataloghiGenerali(null);
					abiBiblioLogic.addPartecipaCatalogoGenerale(idbiblio, cat, false);
				}
			}
		
			// ******** Cataloghi Speciali *******
			if (bibliotecaAttuale.getPartecipaCataloghiSpecialiMateriales() != null) {
				Vector<Integer> ids = new Vector<Integer>();
				for (PartecipaCataloghiSpecialiMateriale p :bibliotecaAttuale.getPartecipaCataloghiSpecialiMateriales()) {
					ids.add(p.getIdCataloghiSpecialiMateriale());
				}
				for(Integer id :ids){
					abiBiblioLogic.removePartecipaCatalogoSpeciale(id, idbiblio);
				}
			}
			if( bibliotecaSalvata.getPartecipaCataloghiSpecialiMateriales()!=null){
				for (PartecipaCataloghiSpecialiMateriale cat : bibliotecaSalvata.getPartecipaCataloghiSpecialiMateriales()) {
					cat.setBiblioteca(bibliotecaAttuale);
					cat.setIdCataloghiSpecialiMateriale(null);
					abiBiblioLogic.addPartecipaCatalogoSpeciale(idbiblio, cat, false);
				}
			}
		
			// ******** Cataloghi Collettivi ******* 
			if (bibliotecaAttuale.getPartecipaCataloghiCollettiviMateriales() != null) {
				Vector<Integer> ids = new Vector<Integer>();
				for (PartecipaCataloghiCollettiviMateriale p :bibliotecaAttuale.getPartecipaCataloghiCollettiviMateriales()) {
					ids.add(p.getIdCataloghiCollettiviMateriale());
				}
				for(Integer id :ids){
					abiBiblioLogic.removePartecipaCatalogoCollettivo(id, idbiblio);
				}
			}
			if( bibliotecaSalvata.getPartecipaCataloghiCollettiviMateriales()!=null){
				for (PartecipaCataloghiCollettiviMateriale cat : bibliotecaSalvata.getPartecipaCataloghiCollettiviMateriales()) {
					cat.setBiblioteca(bibliotecaAttuale);
					cat.setIdCataloghiCollettiviMateriale(null);
					abiBiblioLogic.addPartecipaCatalogoCollettivo(idbiblio, cat, false);
				}
			}
			 
			// ******** Servizi *******
			if( bibliotecaAttuale.getRiproduzionis()!=null){
				biblioDao.removeChilds(bibliotecaAttuale.getRiproduzionis());
			}
			
			/* Riproduzioni */
			bibliotecaAttuale.setAttivoRiproduzioni(bibliotecaSalvata.getAttivoRiproduzioni());
			if (bibliotecaSalvata.getRiproduzionis() != null) {
				for (Riproduzioni riproduzioni : bibliotecaSalvata.getRiproduzionis()) {
					biblioDao.saveChild(riproduzioni);
				}
			}
			// ******** Informazioni bibliografiche ***************** 
			abiBiblioLogic.setServizioBibliograficoInternoEsterno(idbiblio, bibliotecaSalvata.getAttivoInformazioniBibliografiche(), bibliotecaSalvata.getGestisceServizioBibliograficoInterno(), bibliotecaSalvata.getGestisceServizioBibliograficoEsterno());

			// ******* Tipologie comunicazione bibliografica *******
			if(bibliotecaAttuale.getServiziInformazioniBibliograficheModalitas()!=null){
				Vector<Integer> idsServiziInformazioniBibliograficheModalitas= new Vector<Integer>();
				for (ServiziInformazioniBibliograficheModalita modalita : bibliotecaAttuale.getServiziInformazioniBibliograficheModalitas()) {
					idsServiziInformazioniBibliograficheModalitas.add(modalita.getIdServiziInformazioniBibliograficheModalita());
				} 
				for (Integer idServiziInformazioniBibliograficheModalita:idsServiziInformazioniBibliograficheModalitas){
					abiBiblioLogic.removeModalitaComunicazioneInformazioneBibliografica(idbiblio, idServiziInformazioniBibliograficheModalita);
				}
			}
			if (bibliotecaSalvata.getServiziInformazioniBibliograficheModalitas() != null) {
				for (ServiziInformazioniBibliograficheModalita modalita : bibliotecaSalvata.getServiziInformazioniBibliograficheModalitas()) {
					abiBiblioLogic.addModalitaComunicazioneInformazioneBibliografica(idbiblio, modalita.getIdServiziInformazioniBibliograficheModalita().intValue());
				}
			}

			// ******** Sezioni speciali ***************** 
			if(bibliotecaAttuale.getSezioniSpecialis()!=null){
				Vector<Integer> idsSezioniSpecialis= new Vector<Integer>();
				for (SezioniSpeciali sezioniSpeciali : bibliotecaAttuale.getSezioniSpecialis()) {
					idsSezioniSpecialis.add(sezioniSpeciali.getIdSezioniSpeciali());
				}
				for(Integer idSezioniSpeciali :idsSezioniSpecialis){
					abiBiblioLogic.removeSezioniSpeciali(idbiblio, idSezioniSpeciali);
				} 
			}
			if (bibliotecaSalvata.getSezioniSpecialis() != null) {
				for (SezioniSpeciali sezioniSpeciali : bibliotecaSalvata.getSezioniSpecialis()) {
					abiBiblioLogic.addSezioniSpeciali(idbiblio, sezioniSpeciali.getIdSezioniSpeciali());
				}
			}

			// ******** Accesso internet *******
			bibliotecaAttuale.setAttivoAccessoInternet(bibliotecaSalvata.getAttivoAccessoInternet());
			bibliotecaAttuale.setAccessoInternetPagamento(bibliotecaSalvata.getAccessoInternetPagamento());
			bibliotecaAttuale.setAccessoInternetProxy(bibliotecaSalvata.getAccessoInternetProxy());
			bibliotecaAttuale.setAccessoInternetTempo(bibliotecaSalvata.getAccessoInternetTempo());


			// ******** PrestitoLocale ***************** 
			bibliotecaAttuale.setAttivoPrestitoLocale(bibliotecaSalvata.getAttivoPrestitoLocale());
			if(bibliotecaAttuale.getPrestitoLocales()!=null){
				Vector<Integer> idsPrestitoLocales= new Vector<Integer>();

				for (PrestitoLocale prest : bibliotecaAttuale.getPrestitoLocales()) {
					idsPrestitoLocales.add(prest.getIdPrestitoLocale());
				}

				for(Integer idPrestitoLocale:idsPrestitoLocales){
					abiBiblioLogic.removePrestitoLocaleFromBiblio(idbiblio, idPrestitoLocale);
				}
			}
			if (bibliotecaSalvata.getPrestitoLocales() != null) {
				for (PrestitoLocale prest : bibliotecaSalvata.getPrestitoLocales()) {
					PrestitoLocale prestitoLocale = abiBiblioLogic.addPrestitoLocaleToBiblio(idbiblio, null, prest.getDurataGiorni(), prest.getAutomatizzato(), false);

					if (prest.getPrestitoLocaleMaterialeEscluso() != null) {
						for (PrestitoLocaleMaterialeEscluso matEsc : prest.getPrestitoLocaleMaterialeEscluso()) {
							abiTabelleDinamicheLogic.addMaterialeEscluso(matEsc.getIdPrestitoLocaleMaterialeEscluso(), prestitoLocale.getIdPrestitoLocale());
						}
					}
					if (prest.getPrestitoLocaleUtentiAmmessis() != null) {
						for (PrestitoLocaleUtentiAmmessi utAmm : prest.getPrestitoLocaleUtentiAmmessis()) {
							abiTabelleDinamicheLogic.addUtenteAmmessoAlPrestitoLocale(utAmm.getIdPrestitoLocaleUtentiAmmessi(), prestitoLocale.getIdPrestitoLocale());
						}
					}
				}
			}


			// ******** Prestito interbibliotecario ***************** 
			if(bibliotecaSalvata.getPrestitoInterbiblioInternazionale()!=null)
				bibliotecaAttuale.setPrestitoInterbiblioInternazionale(bibliotecaSalvata.getPrestitoInterbiblioInternazionale());
			else bibliotecaAttuale.setPrestitoInterbiblioInternazionale(null);
			if(bibliotecaSalvata.getPrestitoInterbiblioNazionale()!=null)
				bibliotecaAttuale.setPrestitoInterbiblioNazionale(bibliotecaSalvata.getPrestitoInterbiblioNazionale());
			else bibliotecaAttuale.setPrestitoInterbiblioNazionale(null);
			if(bibliotecaSalvata.getProcedureIllAutomatizzate()!=null)
				bibliotecaAttuale.setProcedureIllAutomatizzate(bibliotecaSalvata.getProcedureIllAutomatizzate());
			else bibliotecaAttuale.setProcedureIllAutomatizzate(null);

			// ******** Prestito interbibliotecario: RUOLO ***************** 
			if(bibliotecaAttuale.getPrestitoInterbibliotecarios()!=null){
				bibliotecaAttuale.getPrestitoInterbibliotecarios().clear();
			}
			if (bibliotecaSalvata.getPrestitoInterbibliotecarios() != null) {

				for (PrestitoInterbibliotecario prest : bibliotecaSalvata.getPrestitoInterbibliotecarios()) {
					try {
						abiBiblioLogic.addPrestitoInterbibliotecarioToBiblio(idbiblio, 	null, prest.getPrestitoInterbibliotecarioModo().getIdPrestitoInterbibliotecarioModo(), 
								prest.getNazionale(), prest.getInternazionale(), false);
					} catch (DuplicateEntryException e) {
						throw new Exception(e);
					}
				}
			}

			// ******** Sistemi di prestito interbibliotecario ***************** 
			if(bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios()!=null){
				Vector<Integer> idsSistemiPrestitoInterbibliotecarios= new Vector<Integer>();
				for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecario : bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios()) {
					idsSistemiPrestitoInterbibliotecarios.add(sistemiPrestitoInterbibliotecario.getIdSistemiPrestitoInterbibliotecario());
				}

				for(Integer idSistemiPrestitoInterbibliotecario:idsSistemiPrestitoInterbibliotecarios){
					abiBiblioLogic.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(idbiblio, 	idSistemiPrestitoInterbibliotecario, DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX);
				}
			}

			if (bibliotecaSalvata.getSistemiPrestitoInterbibliotecarios() != null) {
				for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecario : bibliotecaSalvata.getSistemiPrestitoInterbibliotecarios()) {
					DynaTabDTO dynaTabDTODB = new DynaTabDTO();
					dynaTabDTODB.setId(sistemiPrestitoInterbibliotecario.getIdSistemiPrestitoInterbibliotecario());
					dynaTabDTODB.setValue(sistemiPrestitoInterbibliotecario.getDescrizione());
					dynaTabDTODB.setIdTabella(DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX);
					abiBiblioLogic.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(dynaTabDTODB, idbiblio, DtoJpaMapping.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX);
				}
			}

			// ******** Personale ***************** 
			bibliotecaAttuale.setPersonaleTotale(bibliotecaSalvata.getPersonaleTotale());
			bibliotecaAttuale.setPersonaleEsterno(bibliotecaSalvata.getPersonaleEsterno());
			bibliotecaAttuale.setPersonalePartTime(bibliotecaSalvata.getPersonalePartTime());
			bibliotecaAttuale.setPersonaleTemporaneo(bibliotecaSalvata.getPersonaleTemporaneo());

			// ******** Informazioni supplementari utenti ***************** 
			bibliotecaAttuale.setUtentiIscrittiPrestitoAnno(bibliotecaSalvata.getUtentiIscrittiPrestitoAnno());
			bibliotecaAttuale.setUtenti(bibliotecaSalvata.getUtenti());
			bibliotecaAttuale.setUtentiIscritti(bibliotecaSalvata.getUtentiIscritti());

			// ******** Bilancio *******
			bibliotecaAttuale.setBilancioUscite(bibliotecaSalvata.getBilancioUscite());
			bibliotecaAttuale.setBilancioUscitePersonale(bibliotecaSalvata.getBilancioUscitePersonale());
			bibliotecaAttuale.setBilancioUsciteIncrementoPatrimonio(bibliotecaSalvata.getBilancioUsciteIncrementoPatrimonio());
			bibliotecaAttuale.setBilancioUsciteFunzionamento(bibliotecaSalvata.getBilancioUsciteFunzionamento());
			bibliotecaAttuale.setBilancioUsciteAutomazione(bibliotecaSalvata.getBilancioUsciteAutomazione());
			bibliotecaAttuale.setBilancioUsciteVarie(bibliotecaSalvata.getBilancioUsciteVarie());
			bibliotecaAttuale.setBilancioEntrate(bibliotecaSalvata.getBilancioEntrate());

			// ******** Deposito legale *******
			bibliotecaAttuale.setAttivoDepositoLegale(bibliotecaSalvata.getAttivoDepositoLegale());
			
			if (bibliotecaAttuale.getDepositiLegalis() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getDepositiLegalis());
			}
			if (bibliotecaSalvata.getDepositiLegalis() != null) {
				for (DepositiLegali depositiLegali : bibliotecaSalvata.getDepositiLegalis()) {
					biblioDao.saveChild(depositiLegali);
				}
			}
			
			/* Anno di rilevamento dati */
			bibliotecaAttuale.setCatalogazioneDataCensimento(bibliotecaSalvata.getCatalogazioneDataCensimento());
			
			// ******** Note catalogatore *****************
			bibliotecaAttuale.setCatalogazioneNote(bibliotecaSalvata.getCatalogazioneNote());

			// ******** Comunicazioni ********
			if (bibliotecaAttuale.getComunicazionis() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getComunicazionis());
			}
			if (bibliotecaSalvata.getComunicazionis() != null) {
				for (Comunicazioni comunicazioni : bibliotecaSalvata.getComunicazionis()) {
					abiBiblioLogic.inserisciComunicazioniCatalogazione(idbiblio, comunicazioni.getDescrizione());
				}
			}
			
			//************* Dati utente e catalogazione***************
			if(bibliotecaSalvata.getCatalogazioneDataCensimento()!=null){
				bibliotecaAttuale.setCatalogazioneDataCensimento(bibliotecaSalvata.getCatalogazioneDataCensimento());
			}
			else bibliotecaAttuale.setCatalogazioneDataCensimento(null);
		
			if(bibliotecaSalvata.getCatalogazioneDataImport()!=null){
				bibliotecaAttuale.setCatalogazioneDataImport(bibliotecaSalvata.getCatalogazioneDataImport());
			}
			else bibliotecaAttuale.setCatalogazioneDataImport(null);
		
			if(bibliotecaSalvata.getCatalogazioneDataModificaRemota()!=null){
				bibliotecaAttuale.setCatalogazioneDataModificaRemota(bibliotecaSalvata.getCatalogazioneDataModificaRemota());
			}
			else bibliotecaAttuale.setCatalogazioneDataModificaRemota(null);
			
			if(bibliotecaSalvata.getCatalogazioneDataModifica()!=null){
				bibliotecaAttuale.setCatalogazioneDataModifica(bibliotecaSalvata.getCatalogazioneDataModifica());
			}
			else bibliotecaAttuale.setCatalogazioneDataModifica(null);
			
			if(bibliotecaSalvata.getUtenteUltimaModifica()!=null){
				bibliotecaAttuale.setUtenteUltimaModifica(bibliotecaSalvata.getUtenteUltimaModifica());
			} 
			else bibliotecaAttuale.setUtenteUltimaModifica(null);

			// ******** Stato catalogazione ********
			if (bibliotecaAttuale.getStatoCatalogaziones() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getStatoCatalogaziones());
			}
			if (bibliotecaSalvata.getStatoCatalogaziones() != null) {
				for (StatoCatalogazione statoCatalogaziones : bibliotecaSalvata.getStatoCatalogaziones()) {
						abiBiblioLogic.addStatoCatalogazione(idbiblio,statoCatalogaziones.getId().getIdStatoCatalogazione(),statoCatalogaziones.getBibliotecaTarget()!=null?statoCatalogaziones.getBibliotecaTarget().getIdBiblioteca():null);
				}
			}

			/* Reference */
			abiBiblioLogic.setReference(idbiblio, bibliotecaSalvata.getAttivoReference(), bibliotecaSalvata.getReferenceLocale(), bibliotecaSalvata.getReferenceOnline());

			
			/* Document Delivery */
			bibliotecaAttuale.setAttivoDocumentDelivery(bibliotecaSalvata.getAttivoDocumentDelivery());
			
			if (bibliotecaAttuale.getDocumentDeliveries() != null) {
				bibliotecaAttuale.getDocumentDeliveries().clear();
			}
			
			if (bibliotecaSalvata.getDocumentDeliveries() != null) {
				for (RiproduzioniTipo entryDocDelSalvata : bibliotecaSalvata.getDocumentDeliveries()) {
					bibliotecaAttuale.getDocumentDeliveries().add(entryDocDelSalvata);
				}
			}
			
			/* Fonte: descrizione e url */
			bibliotecaAttuale.setFonteDescrizione(bibliotecaSalvata.getFonteDescrizione());
			bibliotecaAttuale.setFonteUrl(bibliotecaSalvata.getFonteUrl());
			
			/* Photo */
			if (bibliotecaAttuale.getPhotos() != null) {
				biblioDao.removeChilds(bibliotecaAttuale.getPhotos());
			}
			if (bibliotecaSalvata.getPhotos() != null) {
				for (Photo photo : bibliotecaSalvata.getPhotos()) {
					photo.setIdPhoto(null);
					abiBiblioLogic.inserisciPhoto(idbiblio, photo);
				}
			}
			
			userActionLog.logActionCatalogazioneBiblioDefaultUser("RIPRISTINO BIBLIOTECA COMPLETATO - id_biblioteca="+idbiblio);

		} catch (Exception e) {
			_log.error("Errore nel ripristinare la biblioteca dall'XML", e);
			throw e;
		}
	}

	/**
	 * Esegue il backup del file di salvataggio della biblioteca id_timecurrentmils.xml
	 */
	@Override
	public void backupXml(int idbiblio) throws Exception {
		String fullSavedFilename = Utility.getSavedFilename(idbiblio, saveDir);
		String fullBackupFilename = Utility.getBackupFilename(idbiblio, backupDir);

		File file = new File(fullSavedFilename);
		boolean ok = file.renameTo(new File(fullBackupFilename));
		if (!ok) throw new Exception("Impossibile copiare il file di salvataggio in backup");
	}

	/**
	 * Rimuove tutte le entry non necessarie che stanno in lazy
	 * @param biblioteca
	 * @throws Exception
	 */
	public void prepareForMarshalling(Biblioteca biblioteca) throws Exception {
		for (Bibliografia temp: biblioteca.getBibliografias()) {
			temp.setBiblioteca(null);
		}
		if (biblioteca.getBibliotecaPadre() != null) {
			Biblioteca bibliotecaPadre = new Biblioteca();
			bibliotecaPadre.setIdBiblioteca(biblioteca.getBibliotecaPadre().getIdBiblioteca());
			biblioteca.setBibliotecaPadre(bibliotecaPadre);
		}
		if (biblioteca.getBibliotecasFigli() != null && biblioteca.getBibliotecasFigli().size() > 0) {
			List<Biblioteca> bibliotecasFigli = new ArrayList<Biblioteca>();
			for (Biblioteca figlio: biblioteca.getBibliotecasFigli()) {
				Biblioteca bibliotecaFiglia = new Biblioteca();
				bibliotecaFiglia.setIdBiblioteca(figlio.getIdBiblioteca());
				bibliotecasFigli.add(bibliotecaFiglia);
				biblioteca.setBibliotecasFigli(bibliotecasFigli);
			}
		}
		for (Codici codici:biblioteca.getCodicis()) {
			codici.setBiblioteca(null);
			codici.getCodiciTipo().setCodicis(null);
		}
		for (Comunicazioni comunicazioni: biblioteca.getComunicazionis()) {
			comunicazioni.setBiblioteca(null);
		}
		for (Contatti contatti: biblioteca.getContattis()) {
			contatti.setBiblioteca(null);
		}
		for (DenominazioniAlternative denominazioniAlternative: biblioteca.getDenominazioniAlternatives()) {
			denominazioniAlternative.setBiblioteca(null);
		}
		for (DenominazioniPrecedenti denominazioniPrecedenti: biblioteca.getDenominazioniPrecedentis()) {
			denominazioniPrecedenti.setBiblioteca(null);
		}
		for (DepositiLegali depositiLegali: biblioteca.getDepositiLegalis()) {
			depositiLegali.setBiblioteca(null);
		}
		for (DestinazioniSociali destinazioniSociali: biblioteca.getDestinazioniSocialis()) {
			destinazioniSociali.setBiblioteca(null);
		}
		for (DeweyLibero deweyLibero: biblioteca.getDeweyLiberos()) {
			deweyLibero.setBiblioteca(null);
		}

		for (FondiSpeciali fondiSpeciali: biblioteca.getFondiSpecialis()) {
			fondiSpeciali.getFondiSpecialiCatalogazioneInventario().setFondiSpecialis(null);
		}
		for (OrarioChiusure orarioChiusure: biblioteca.getOrarioChiusures()) {
			orarioChiusure.setBiblioteca(null);
		}
		for (OrarioUfficiali orarioUfficiali: biblioteca.getOrarioUfficialis()) {
			orarioUfficiali.setBiblioteca(null);
		}
		for (OrarioVariazioni orarioVariazioni: biblioteca.getOrarioVariazionis()) {
			orarioVariazioni.setBiblioteca(null);
		}
		for (PartecipaCataloghiCollettiviMateriale temp: biblioteca.getPartecipaCataloghiCollettiviMateriales()) {
			temp.setBiblioteca(null);
			temp.getCataloghiCollettivi().setPartecipaCataloghiCollettiviMateriales(null);
		}

		for (PartecipaCataloghiGenerali temp : biblioteca.getPartecipaCataloghiGeneralis()) {
			for (CataloghiGeneraliUrl temp2 : temp.getCataloghiGeneraliUrls()) {
				temp2.setPartecipaCataloghiGenerali(null);
			}
		}
		for (PartecipaCataloghiSpecialiMateriale temp : biblioteca.getPartecipaCataloghiSpecialiMateriales()) {
			temp.setBiblioteca(null);
			for (CataloghiSpecialiMaterialeUrl temp2 : temp.getCataloghiSpecialiMaterialeUrls()) {
				temp2.setPartecipaCataloghiSpecialiMateriale(null);
			}
		}
		for (Patrimonio patrimonio: biblioteca.getPatrimonios()) {
			patrimonio.setBiblioteca(null);
		}
		for (PrestitoLocale prestitoLocale: biblioteca.getPrestitoLocales()) {
			prestitoLocale.setBiblioteca(null);
		}
		for (Pubblicazioni pubblicazioni: biblioteca.getPubblicazionis()) {
			pubblicazioni.setBiblioteca(null);
		}
		for (Regolamento regolamento: biblioteca.getRegolamentos()) {
			regolamento.setBiblioteca(null);
		}
		for (Riproduzioni riproduzioni: biblioteca.getRiproduzionis()) {
			riproduzioni.setBiblioteca(null);
		}

		for (SpogliBibliografici spogliBibliografici: biblioteca.getSpogliBibliograficis()) {
			spogliBibliografici.setBiblioteca(null);
		}
		if (biblioteca.getUtenteUltimaModifica() != null) {
			biblioteca.getUtenteUltimaModifica().setProfilis(null);
			biblioteca.getUtenteUltimaModifica().setBibliotecasGestite(null);
			biblioteca.getUtenteUltimaModifica().setNuovaBibliotecas_creatore(null);
			biblioteca.getUtenteUltimaModifica().setNuovaBibliotecas_gestore(null);
		}
		for (Utenti temp: biblioteca.getUtentisGestori()) {
			temp.setProfilis(null);
			temp.setBibliotecasGestite(null);
			temp.setNuovaBibliotecas_creatore(null);
			temp.setNuovaBibliotecas_gestore(null);
		}

		if (biblioteca.getStatoCatalogaziones() != null && biblioteca.getStatoCatalogaziones().size() > 0) {
			List<StatoCatalogazione> bibliotecasStatoCatalogazione = new ArrayList<StatoCatalogazione>();
			for (StatoCatalogazione statoCatalogazione: biblioteca.getStatoCatalogaziones()) {
				statoCatalogazione.setBiblioteca(null);
				statoCatalogazione.setStatoCatalogazioneTipo(null);
				if (statoCatalogazione.getBibliotecaTarget() != null) {
					Biblioteca bibliotecaTarget = new Biblioteca();
					bibliotecaTarget.setIdBiblioteca(statoCatalogazione.getBibliotecaTarget().getIdBiblioteca());
					statoCatalogazione.setBibliotecaTarget(bibliotecaTarget);
				}
				bibliotecasStatoCatalogazione.add(statoCatalogazione);
			}
			biblioteca.setStatoCatalogaziones(bibliotecasStatoCatalogazione);
		}
		
		/* Photo */
		for (Photo photo: biblioteca.getPhotos()) {
			photo.setBiblioteca(null);
		}
		
	}
}
