package it.inera.abi.gxt.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.EventType;

public class AppEvents {

	public static final EventType AuthRequest = new EventType(); // Sicurezza
	
	public static final EventType Init = new EventType();

	public static final EventType Error = new EventType();

	public static final EventType NavStat = new EventType(); // Evento apertura pannello Statistiche
	
	public static final EventType NavStatItemSelected = new EventType(); // Evento selezione elemento pannello Statistiche
	
	public static final EventType NavTabDin = new EventType(); // Evento apertura pannello tabelle dinamiche
	
	public static final EventType NuovoUtente = new EventType(); // Evento selezione icona Creazione nuovo utente
	
	public static final EventType ListaUtenti = new EventType(); // Evento selezione icona Lista utenti
	
	public static final EventType AssegnaListeAUtenti = new EventType();// Evento selezione icona assegnazione lista biblioteche a lista utenti

	
	/** RICERCHE GENERICHE **/
	public static final EventType RicercaBiblioGenerica = new EventType();
	public static final EventType VisualizzaRicercaBiblioGenerica = new EventType();
	public static final EventType FiltraListaBiblioInRicercaBiblio = new EventType();
	public static final EventType ListaBiblioteche = new EventType(); // Evento selezione icona lista biblioteche
	
	/** RICERCHE VIA CODICE **/
	public static final EventType RicercaBiblioCodice = new EventType();	
	public static final EventType FiltraListaBiblioInRicercaBiblioViaCodice = new EventType();
	public static final EventType VisualizzaRicercaBiblioViaCodice = new EventType();
	
	public static final EventType RicercaBiblioGenericaViaUtentiIndietro =  new EventType();
	
	public static final EventType SelectBiblio = new EventType(); // Evento selezione icona lista biblioteche
	public static final EventType RicercaBiblioCreazioneReport = new EventType(); // Evento selezione icona di menù ricerca biblioteche per creazione stampe

	/** EVENTI TABELLE DINAMICHE **/
	public static final EventType MAPPING_TABELLE_DINAMICHE = new EventType();
	
	/* 00 */public static final EventType Stati = new EventType();
	/* 01 */public static final EventType Regioni = new EventType();
	/* 02 */public static final EventType Provincie = new EventType();
	/* 03 */public static final EventType Comuni = new EventType();
	/* 04 */public static final EventType PatrimonioLibrarioGrandiVoci = new EventType();
	/* 05 */public static final EventType PatrimonioLibrarioPicoleVoci = new EventType();
	/* 06 */public static final EventType CataloghiCollettiviTipiZoneEspansione = new EventType();
	/* 07 */public static final EventType DestinazioneSocialeTipologie = new EventType();
	/* 08 */public static final EventType AccessoUtentiAmmessi = new EventType();
	/* 09 */public static final EventType AccessoModalita = new EventType();
	/* 10 */public static final EventType AccessoInternetTipologie = new EventType();
	/* 11 */public static final EventType EntiTipologieAmministrative = new EventType();
	/* 13 */public static final EventType TipologieFunzionali = new EventType();
	/* 14 */public static final EventType CatalogazioneStatiBiblioteche = new EventType();
	/* 16 */public static final EventType Dewey = new EventType();
	/* 17 */public static final EventType ContattiTipi = new EventType();
	/* 18 */public static final EventType RiproduzioniTipi = new EventType();
	/* 19 */public static final EventType ServiziModalitaComunicazioneInfromazioni = new EventType();
	/* 20 */public static final EventType ServiziBibliotecariCartaServizi = new EventType();
	/* 21 */public static final EventType FondiSpecialiTipiCatalogazioneInventario = new EventType();
	/* 22 */public static final EventType CataloghiTipiSupportoInventario = new EventType();
	/* 23 */public static final EventType CataloghiCollettivi = new EventType();
	/* 24 */public static final EventType PrestitoLocaleUtentiAmmessi = new EventType();
	/* 25 */public static final EventType PrestitoLocaleMaterialeEscluso = new EventType();
	/* 36 */public static final EventType PrestitoInterbibliotecarioModo = new EventType();
	/* 27 */public static final EventType PrestitoInterbibliotecarioSistemi = new EventType();
	/* 28 */public static final EventType SezioniSpeciali = new EventType();
	/* 29 */public static final EventType DepositiegaliTipologie = new EventType();
	/* 30 */public static final EventType CatalogazioneNorme = new EventType();
	/* 31 */public static final EventType IndicizzazioneClassificata = new EventType();
	/* 32 */public static final EventType IndicizzazioneSoggetto = new EventType();
	/* 33 */public static final EventType SistemiRetiBiblioteche = new EventType();
	/* 34 */public static final EventType FondiAntichiTipologia = new EventType();
	/* 35 */public static final EventType CatalogoGeneraleTipo = new EventType();
	/* 36 */public static final EventType CataloghiCollettiviZonaTipo = new EventType();

	public static List<EventType> tabDinEvents = new ArrayList<EventType>();
	

	/** EVENTI REPORT **/
	public static final EventType RicercaPerCreazioneExport = new EventType();
	public static final EventType UploadFileDiScambio = new EventType();
	public static final EventType FileCaricatiNonControllati = new EventType();
	public static final EventType FileCaricatiControllati = new EventType();
	public static final EventType ExportSchedulati = new EventType();
	public static final EventType ImportSchedulati = new EventType();

	public static final EventType FiltraListaUtenti = new EventType();
	public static final EventType FiltraListaUtentiInAssegnazioneUtentiBiblio = new EventType();
	public static final EventType FiltraListaUtentiInRicercaUtente = new EventType();
	public static final EventType ModificaUtente = new EventType();

	public static final EventType IndietroDaModificaModificaUtente = new EventType();

	public static final EventType FiltraListaBiblioInAssegnazioneUtentiBiblio = new EventType();

	public static final EventType ReloadBiblioData = new EventType();

	public static final EventType CaricamentoSpecializzazioniCompletato = new EventType();
	public static final EventType CaricamentoFondiSpecialiCompletato = new EventType();

	public static final EventType CaricamentoCataloghiCollettivi = new EventType();

	public static final EventType RicercaListaBiblioInAssegnaIndietro = new EventType();

	public static final EventType AssegnaBibliotecheAUtente = new EventType();


	public static List<EventType> getTabDinEvents() {
		/* 00 */tabDinEvents.add(Stati);
		/* 01 */tabDinEvents.add(Regioni);
		/* 02 */tabDinEvents.add(Provincie);
		/* 03 */tabDinEvents.add(Comuni);
		/* 04 */tabDinEvents.add(PatrimonioLibrarioGrandiVoci);
		/* 05 */tabDinEvents.add(PatrimonioLibrarioPicoleVoci);
		/* 06 */tabDinEvents.add(CataloghiCollettiviTipiZoneEspansione);
		/* 07 */tabDinEvents.add(DestinazioneSocialeTipologie);
		/* 08 */tabDinEvents.add(AccessoUtentiAmmessi);
		/* 09 */tabDinEvents.add(AccessoModalita);
		/* 10 */tabDinEvents.add(AccessoInternetTipologie);
		/* 11 */tabDinEvents.add(EntiTipologieAmministrative);
		/* 13 */tabDinEvents.add(TipologieFunzionali);
		/* 14 */tabDinEvents.add(CatalogazioneStatiBiblioteche);
		/* 16 */tabDinEvents.add(Dewey);
		/* 17 */tabDinEvents.add(ContattiTipi);
		/* 18 */tabDinEvents.add(RiproduzioniTipi);
		/* 19 */tabDinEvents.add(ServiziModalitaComunicazioneInfromazioni);
		/* 20 */tabDinEvents.add(ServiziBibliotecariCartaServizi);
		/* 21 */tabDinEvents.add(FondiSpecialiTipiCatalogazioneInventario);
		/* 22 */tabDinEvents.add(CataloghiCollettivi);
		/* 23 */tabDinEvents.add(PrestitoLocaleUtentiAmmessi);
		/* 24 */tabDinEvents.add(PrestitoLocaleMaterialeEscluso);
		/* 25 */tabDinEvents.add(PrestitoInterbibliotecarioModo);
		/* 26 */tabDinEvents.add(PrestitoInterbibliotecarioSistemi);
		/* 27 */tabDinEvents.add(SezioniSpeciali);
		/* 28 */tabDinEvents.add(DepositiegaliTipologie);
		/* 29 */tabDinEvents.add(CatalogazioneNorme);
		/* 31 */tabDinEvents.add(IndicizzazioneClassificata);
		/* 32 */tabDinEvents.add(IndicizzazioneSoggetto);
		/* 33 */tabDinEvents.add(SistemiRetiBiblioteche);
		/* 34 */tabDinEvents.add(FondiAntichiTipologia);
		/* 35 */tabDinEvents.add(CatalogoGeneraleTipo);
		/* 36 */tabDinEvents.add(CataloghiCollettiviZonaTipo);
		return tabDinEvents;
	}
	
	/** EVENTI REPORT **/
	public static EventType VisualizzaRisultatiReport = new EventType();
	public static EventType VisualizzaFormReport = new EventType();
	public static EventType VisualizzaStampaReport = new EventType();
	
	/** EVENTI FORMATO DI SCAMBIO **/
	public static EventType VisualizzaRisultatiFormatoDiScambio = new EventType();
	public static EventType VisualizzaFormFormatoDiScambio = new EventType();
	public static EventType VisualizzaStampaFormatoDiScambio = new EventType();
	
	/** EVENTI GESTIONE UTENTI **/
	public static EventType AssegnaBiblioAUtenti = new EventType();
	public static EventType VisualizzaListaUtenti = new EventType();
	public static EventType VisualizzaListaUtentiInModifica = new EventType();
	public static EventType RicercaListaBiblioInAssegna = new EventType();

	public static EventType rimuoviPannello = new EventType();

}