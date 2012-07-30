package it.inera.abi.gxt.client.costants;

import java.util.List;

/**
 * Contiene le costanti relative ai nomi delle tabelle dinamiche
 */
public class CostantiTabelleDinamiche {

	/*VOCI*//*ID_MENU*/
	/**/	/* 00 */public static final int STATI_INDEX = 0;
	/**/	/* 01 */public static final int REGIONI_INDEX = 1;
	/**/	/* 02 */public static final int PROVINCE_INDEX = 2;
	/**/	/* 03 */public static final int COMUNI_INDEX = 3;
	/*2*/	/* 04 */public static final int PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX = 4;
	/*2*/	/* 05 */public static final int PATRIMONIO_LIBRARIO_PICCOLE_VOCI_INDEX = 5;
	/*3*/	/* 06 */public static final int CATALOGHI_COLLETTIVI_INDEX = 6;
	/*1*/    /*07 */public static final int DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX  = 7;
	/*1*/	/* 09 */public static final int ACCESSO_MODALITA_INDEX = 9;
	/*1*/	/* 10 */public static final int ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX = 10;
	/*1*/	/* 12 */public static final int TIPOLOGIE_FUNZIONALI_INDEX =12;
	/*1*/	/* 13 */public static final int CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX = 13;
	/*1*/	/* 14 */public static final int THESAURUS_INDEX = 14;
	/*1*/	/* 15 */public static final int DEWEY_INDEX = 15;
	/*1*/	/* 16 */public static final int CONTATTI_TIPI_INDEX = 16;
	/*1*/	/* 17 */public static final int RIPRODUZIONI_TIPI_INDEX =17;
	/*1*/	/* 18 */public static final int SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX = 18;
	/*1*/	/* 19 */public static final int SERVIZI_BIBLIOTECARI_CARTA_SERVIZI_INDEX = 19;
	/*1*/	/* 20 */public static final int FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX = 20;
	/*1*/	/* 21 */public static final int CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX = 21;
	/*1*/	/* 23 */public static final int PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX = 23;
	/*1*/	/* 24 */public static final int PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX = 24;
	/*1*/	/* 25 */public static final int PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX =25;
	/*1*/	/* 26 */public static final int PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX = 26;
	/*1*/	/* 27 */public static final int SEZIONI_SPECIALI_INDEX = 27;
	/*1*/	/* 28 */public static final int DEPOSITI_LEGALI_TIPOLOGIE_INDEX = 28;
	/*1*/	/* 29 */public static final int CATALOGAZIONE_NORME_INDEX = 29;
	/*1*/	/* 30 */public static final int INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX = 30;
	/*1*/	/* 31 */public static final int INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX = 31;
	/*1*/	/* 32 */public static final int SISTEMI_RETI_BIBLITOECHE_INDEX = 32;
	/*1*/	/* 33 */public static final int FONDI_ANTICHI_CONSISTENZA_INDEX = 33;
	/*1*/	/* 34 */public static final int CATALOGO_GENERALE_TIPO_INDEX = 34;
	/*1*/	/* 35 */public static final int CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX = 35;
	//RIMOSSO DOPPIONE TABELLA DINAMICA
//	/*1*/	/* 36*/ public static final int STATO_CATALOGAZIONE_BIBLIOTECHE_TIPO_INDEX = 36;


	/*ETICHETTE TABELLE DINAMICHE*/

	/*VOCI*//*ID_MENU*/
	/**/	/* 00 */public static final String STATI = "Stati";
	/**/	/* 01 */public static final String REGIONI = "Regioni";
	/**/	/* 02 */public static final String PROVINCE = "Province";
	/**/	/* 03 */public static final String COMUNI = "Comuni";
	/*2*/	/* 04 */public static final String PATRIMONIO_LIBRARIO_GRANDI_VOCI = "Patrimonio librario: categorie (grandi voci)";
	/*2*/	/* 05 */public static final String PATRIMONIO_LIBRARIO_PICCOLE_VOCI = "Patrimonio librario: specializzazioni (piccole voci)";
	/*3*/	/* 06 */public static final String CATALOGHI_COLLETTIVI = "Cataloghi: cataloghi collettivi";
	/*1*/    /*07 */public static final String DESTINAZIONI_SOCIALI_TIPOLOGIE  = "Destinazioni sociali";
	/*1*/	/* 09 */public static final String ACCESSO_MODALITA = "Accesso: modalità per l'accesso";
	/*1*/	/* 10 */public static final String ACCESSO_INTERNET_TIPOLOGIE = "Accesso: tipologie di accesso a internet";
	/*1*/	/* 11 */public static final String ENTI_TIPOLOGIE_AMMINISTRATIVE = "Enti: tipologie amministrative";
	/*1*/	/* 13 */public static final String TIPOLOGIE_FUNZIONALI = "Tipologie funzionali";
	/*1*/	/* 14 */public static final String CATALOGAZIONE_STATI_BIBLIOTECHE = "Catalogazione: stato della registrazione";
	/*1*/	/* 16 */public static final String DEWEY = "Dewey";
	/*1*/	/* 17 */public static final String CONTATTI_TIPI = "Contatti: tipi";
	/*1*/	/* 18 */public static final String RIPRODUZIONI_TIPI = "Riproduzione: tipologie";
	/*1*/	/* 19 */public static final String SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE = "Servizi: modalità di comunicazione delle informazioni bibliografiche";
	/*1*/	/* 20 */public static final String SERVIZI_BIBLIOTECARI_CARTA_SERVIZI = "Servizi: servizi bibliotecari Carta dei servizi";
	/*1*/	/* 21 */public static final String FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO = "Fondi speciali: tipi di catalogazione inventario";
	/*1*/	/* 22 */public static final String CATALOGHI_TIPI_SUPPORTO_INVENTARIO = "Cataloghi: tipi di supporto per l'inventario";
	/*1*/	/* 23 */public static final String PRESTITO_LOCALE_UTENTI_AMMESSI = "Prestito locale: utenti ammessi";
	/*1*/	/* 24 */public static final String PRESTITO_LOCALE_MATERIALE_ESCLUSO = "Prestito locale: materiale escluso";
	/*1*/	/* 25 */public static final String PRESTITO_INTERBIBLIOTECARIO_MODO = "Prestito interbibliotecario: modo";
	/*1*/	/* 26 */public static final String PRESTITO_INTERBIBLIOTECARIO_SISTEMI = "Prestito interbibliotecario: sistemi";
	/*1*/	/* 27 */public static final String SEZIONI_SPECIALI = "Sezioni speciali";
	/*1*/	/* 28 */public static final String DEPOSITI_LEGALI_TIPOLOGIE = "Depositi legali: tipologie";
	/*1*/	/* 29 */public static final String CATALOGAZIONE_NORME = "Catalogazione: norme di catalogazione";
	/*1*/	/* 30 */public static final String INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA = "Indicizzazione: sistemi di indicizzazione Classificata";
	/*1*/	/* 31 */public static final String INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO = "Indicizzazione: sistemi di indicizzazione per Soggetto";
	/*1*/	/* 32 */public static final String SISTEMI_RETI_BIBLITOECHE = "Sistemi o reti di biblioteche";
	/*1*/	/* 33 */public static final String FONDI_ANTICHI_CONSISTENZA = "Fondi antichi: tipologia";
	/*1*/	/* 34 */public static final String CATALOGO_GENERALE_TIPO = "Cataloghi: cataloghi generali tipologia";
	/*1*/	/* 35 */public static final String CATALOGHI_COLLETTIVI_ZONA_TIPO = "Cataloghi: cataloghi collettivi zona tipologia";
	//RIMOSSO DOPPIONE TABELLA DINAMICA
//	/*1*/	/* 36*/ public static final String STATO_CATALOGAZIONE_BIBLIOTECHE_TIPO = "Catalogazione: stato biblioteche";


	List<String> listaEtichetteTabelleDinamiche = null;

	public List<String> getListaEtichetteTabelleDinamiche() {

		/*00*/	listaEtichetteTabelleDinamiche.add(STATI);
		/*01*/	listaEtichetteTabelleDinamiche.add(REGIONI);
		/*02*/	listaEtichetteTabelleDinamiche.add(PROVINCE);
		/*03*/	listaEtichetteTabelleDinamiche.add(COMUNI);
		/*04*/	listaEtichetteTabelleDinamiche.add(PATRIMONIO_LIBRARIO_GRANDI_VOCI);
		/*05*/	listaEtichetteTabelleDinamiche.add(PATRIMONIO_LIBRARIO_PICCOLE_VOCI);
		/*07*/	listaEtichetteTabelleDinamiche.add(DESTINAZIONI_SOCIALI_TIPOLOGIE );
		/*08*/	listaEtichetteTabelleDinamiche.add(ACCESSO_MODALITA);
		/*9*/	listaEtichetteTabelleDinamiche.add(ACCESSO_INTERNET_TIPOLOGIE);
		/*10*/  listaEtichetteTabelleDinamiche.add(ENTI_TIPOLOGIE_AMMINISTRATIVE);
		/*12*/  listaEtichetteTabelleDinamiche.add(TIPOLOGIE_FUNZIONALI);
		/*13*/  listaEtichetteTabelleDinamiche.add(CATALOGAZIONE_STATI_BIBLIOTECHE);
		/*15*/  listaEtichetteTabelleDinamiche.add(DEWEY);
		/*16*/  listaEtichetteTabelleDinamiche.add(CONTATTI_TIPI);
		/*17*/  listaEtichetteTabelleDinamiche.add(RIPRODUZIONI_TIPI);
		/*18*/  listaEtichetteTabelleDinamiche.add(SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE);
		/*19*/  listaEtichetteTabelleDinamiche.add(SERVIZI_BIBLIOTECARI_CARTA_SERVIZI);
		/*20*/  listaEtichetteTabelleDinamiche.add(FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO);
		/*21*/  listaEtichetteTabelleDinamiche.add(CATALOGHI_TIPI_SUPPORTO_INVENTARIO);
		/*22*/ 	listaEtichetteTabelleDinamiche.add(CATALOGHI_COLLETTIVI);
		/*23*/  listaEtichetteTabelleDinamiche.add(PRESTITO_LOCALE_UTENTI_AMMESSI);
		/*24*/	listaEtichetteTabelleDinamiche.add(PRESTITO_LOCALE_MATERIALE_ESCLUSO);
		/*25*/  listaEtichetteTabelleDinamiche.add(PRESTITO_INTERBIBLIOTECARIO_MODO);
		/*26*/  listaEtichetteTabelleDinamiche.add(PRESTITO_INTERBIBLIOTECARIO_SISTEMI);
		/*27*/  listaEtichetteTabelleDinamiche.add(SEZIONI_SPECIALI);
		/*28*/  listaEtichetteTabelleDinamiche.add(DEPOSITI_LEGALI_TIPOLOGIE);
		/*29*/  listaEtichetteTabelleDinamiche.add(CATALOGAZIONE_NORME);
		/*30*/  listaEtichetteTabelleDinamiche.add(INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA);
		/*31*/  listaEtichetteTabelleDinamiche.add(INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO);
		/*32*/  listaEtichetteTabelleDinamiche.add(SISTEMI_RETI_BIBLITOECHE);
		/*33*/  listaEtichetteTabelleDinamiche.add(FONDI_ANTICHI_CONSISTENZA);
		/*34*/  listaEtichetteTabelleDinamiche.add(CATALOGO_GENERALE_TIPO);
		/*35*/  listaEtichetteTabelleDinamiche.add(CATALOGHI_COLLETTIVI_ZONA_TIPO);
		//RIMOSSO DOPPIONE TABELLA DINAMICA
		//		/*35*/  listaEtichetteTabelleDinamiche.add(STATO_CATALOGAZIONE_BIBLIOTECHE_TIPO);



		return listaEtichetteTabelleDinamiche;
	}


	/*NOMI TABELLE DATABASE TABELLE DINAMICHE*/
//
//	/* 00 */public static final String TABLE_STATI = "stato";
//	/* 01 */public static final String TABLE_REGIONI = "regione";
//	/* 02 */public static final String TABLE_PROVINCE = "provincia";
//	/* 03 */public static final String TABLE_COMUNI = "comune";
//	/* 04 */public static final String TABLE_PATRIMONIO_LIBRARIO_GRANDI_VOCI = "patrimonio_categoria";
//	/* 05 */public static final String TABLE_PATRIMONIO_LIBRARIO_PICCOLE_VOCI = "patrimonio_specializzazioni";
//	/* 06 */public static final String TABLE_CATALOGHI_COLLETTIVI = "cataloghi_collettivi";
//	/* 07 */public static final String TABLE_DESTINAZIONI_SOCIALI_TIPOLOGIE = "destinazioni_sociali_tipo";
//	/* 08 */public static final String TABLE_ACCESSO_MODALITA = "accesso_modalita";
//	/* 09 */public static final String TABLE_ACCESSO_INTERNET_TIPOLOGIE = "accesso_internet";
//	/* 10 */public static final String TABLE_ENTI_TIPOLOGIE_AMMINISTRATIVE = "ente_tipologia_amministrativa";
//	/* 12 */public static final String TABLE_TIPOLOGIE_FUNZIONALI = "tipologia_funzionale";
//	/* 13 */public static final String TABLE_CATALOGAZIONE_STATI_BIBLIOTECHE = "stato_catalogazione_tipo";
//	/* 15 */public static final String TABLE_DEWEY = "dewey";
//	/* 16 */public static final String TABLE_CONTATTI_TIPI = "contatti_tipo";
//	/* 17 */public static final String TABLE_RIPRODUZIONI_TIPI = "riproduzioni_tipo";
//	/* 18 */public static final String TABLE_SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE = "servizi_informazioni_bibliografiche_modalita";
//	/* 19 */public static final String TABLE_SERVIZI_BIBLIOTECARI_CARTA_SERVIZI = "servizi_bibliotecari_carta";
//	/* 20 */public static final String TABLE_FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO = "fondi_speciali_catalogazione_inventario";
//	/* 21 */public static final String TABLE_CATALOGHI_TIPI_SUPPORTO_INVENTARIO = "cataloghi_supporto_digitale_tipo";
//	/* 22 */public static final String TABLE_PRESTITO_LOCALE_UTENTI_AMMESSI = "prestito_locale_utenti_ammessi";
//	/* 23 */public static final String TABLE_PRESTITO_LOCALE_MATERIALE_ESCLUSO = "prestito_locale_materiale_escluso";
//	/* 24 */public static final String TABLE_PRESTITO_INTERBIBLIOTECARIO_MODO = "prestito_interbibliotecario_modo";
//	/* 25 */public static final String TABLE_PRESTITO_INTERBIBLIOTECARIO_SISTEMI = "sistemi_prestito_interbibliotecario";
//	/* 26 */public static final String TABLE_SEZIONI_SPECIALI = "sezioni_speciali";
//	/* 27 */public static final String TABLE_DEPOSITI_LEGALI_TIPOLOGIE = "depositi_legali_tipo";
//	/* 28 */public static final String TABLE_CATALOGAZIONE_NORME = "norme_catalogazione";
//	/* 29 */public static final String TABLE_INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA = "indicizzazione_classificata";
//	/* 30 */public static final String TABLE_INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO = "indicizzazione_soggetto";
//	/* 31 */public static final String TABLE_SISTEMI_RETI_BIBLITOECHE = "sistemi_biblioteche";
//	/* 32 */public static final String TABLE_FONDI_ANTICHI_CONSISTENZA = "fondi_antichi_consistenza";
//	/* 33 */public static final String TABLE_CATALOGO_GENERALE_TIPO= "catalogo_generale_tipo";
//	/* 34*/public static final String TABLE_CATALOGHI_COLLETTIVI_ZONA_TIPO= "cataloghi_collettivi_zona_tipo";


	List<String> listaTabelleDataBaseTabelleDinamiche = null;


//	public List<String> getListaTabelleDataBaseTabelleDinamiche(){
//
//		/*00*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_STATI);
//		/*01*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_REGIONI);
//		/*02*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_PROVINCE);
//		/*03*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_COMUNI);
//		/*04*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_PATRIMONIO_LIBRARIO_GRANDI_VOCI);
//		/*05*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_PATRIMONIO_LIBRARIO_PICCOLE_VOCI);
//		/*07*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_DESTINAZIONI_SOCIALI_TIPOLOGIE );
//		/*09*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_ACCESSO_MODALITA);
//		/*10*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_ACCESSO_INTERNET_TIPOLOGIE);
//		/*11*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_ENTI_TIPOLOGIE_AMMINISTRATIVE);
//		/*13*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_TIPOLOGIE_FUNZIONALI);
//		/*14*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGAZIONE_STATI_BIBLIOTECHE);
//		/*16*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_DEWEY);
//		/*17*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CONTATTI_TIPI);
//		/*18*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_RIPRODUZIONI_TIPI);
//		/*19*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE);
//		/*20*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_SERVIZI_BIBLIOTECARI_CARTA_SERVIZI);
//		/*21*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO);
//		/*22*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGHI_TIPI_SUPPORTO_INVENTARIO);
//		/*23*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGHI_COLLETTIVI);
//		/*24*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_PRESTITO_LOCALE_UTENTI_AMMESSI);
//		/*25*/	listaTabelleDataBaseTabelleDinamiche.add(TABLE_PRESTITO_LOCALE_MATERIALE_ESCLUSO);
//		/*26*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_PRESTITO_INTERBIBLIOTECARIO_MODO);
//		/*27*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_PRESTITO_INTERBIBLIOTECARIO_SISTEMI);
//		/*28*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_SEZIONI_SPECIALI);
//		/*29*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_DEPOSITI_LEGALI_TIPOLOGIE);
//		/*30*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGAZIONE_NORME);
//		/*31*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA);
//		/*32*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO);
//		/*33*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_SISTEMI_RETI_BIBLITOECHE);
//		/*34*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_FONDI_ANTICHI_CONSISTENZA);
//		/*35*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGO_GENERALE_TIPO);
//		/*36*/  listaTabelleDataBaseTabelleDinamiche.add(TABLE_CATALOGHI_COLLETTIVI_ZONA_TIPO);
//
//		return listaTabelleDataBaseTabelleDinamiche;
//	}
}
