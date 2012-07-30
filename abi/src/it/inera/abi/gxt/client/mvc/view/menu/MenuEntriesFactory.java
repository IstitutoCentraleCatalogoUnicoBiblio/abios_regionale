package it.inera.abi.gxt.client.mvc.view.menu;

/**
 * Classe per la creazione delle entry di menu dei vari alberi nel pannello sinistro
 */
import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.costants.CostantiReport;
import it.inera.abi.gxt.client.costants.CostantiStatistiche;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.MenuItem;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class MenuEntriesFactory {

	public static List<MenuItem> getTreeModelTabDin() {
		/**/	List<MenuItem> menuItems = new ArrayList<MenuItem>();
		/*02*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PROVINCE,CostantiTabelleDinamiche.PROVINCE_INDEX));
		/*03*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.COMUNI,CostantiTabelleDinamiche.COMUNI_INDEX));
		/*04*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_GRANDI_VOCI,CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_GRANDI_VOCI_INDEX));
		/*05*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_PICCOLE_VOCI,CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_PICCOLE_VOCI_INDEX));
		/*07*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.DESTINAZIONI_SOCIALI_TIPOLOGIE,CostantiTabelleDinamiche.DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX));
		/*09*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.ACCESSO_MODALITA,CostantiTabelleDinamiche.ACCESSO_MODALITA_INDEX));
		/*10*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE,CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX));
		/*12*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI,CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI_INDEX));
		/*13*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGAZIONE_STATI_BIBLIOTECHE,CostantiTabelleDinamiche.CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX));
		/*15*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.DEWEY,CostantiTabelleDinamiche.DEWEY_INDEX));
		/*16*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CONTATTI_TIPI,CostantiTabelleDinamiche.CONTATTI_TIPI_INDEX));
		/*17*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.RIPRODUZIONI_TIPI,CostantiTabelleDinamiche.RIPRODUZIONI_TIPI_INDEX));
		/*18*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE,CostantiTabelleDinamiche.SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE_INDEX));
		/* SERVIZI_BIBLIOTECARI_CARTA_SERVIZI:in attesa di decisione per la gestione sul database:rimossa dal menù*/
		//      /*19*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.SERVIZI_BIBLIOTECARI_CARTA_SERVIZI,CostantiTabelleDinamiche.SERVIZI_BIBLIOTECARI_CARTA_SERVIZI_INDEX));
		/*20*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO,CostantiTabelleDinamiche.FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO_INDEX));
		/*21*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGHI_TIPI_SUPPORTO_INVENTARIO,CostantiTabelleDinamiche.CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX));
		/*22*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI,CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_INDEX));
		/*23*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PRESTITO_LOCALE_UTENTI_AMMESSI,CostantiTabelleDinamiche.PRESTITO_LOCALE_UTENTI_AMMESSI_INDEX));
		/*24*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PRESTITO_LOCALE_MATERIALE_ESCLUSO,CostantiTabelleDinamiche.PRESTITO_LOCALE_MATERIALE_ESCLUSO_INDEX));
		/*25*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_MODO,CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX));
		/*26*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_SISTEMI,CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX));
		/*27*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.SEZIONI_SPECIALI,CostantiTabelleDinamiche.SEZIONI_SPECIALI_INDEX));
		/*28*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.DEPOSITI_LEGALI_TIPOLOGIE,CostantiTabelleDinamiche.DEPOSITI_LEGALI_TIPOLOGIE_INDEX));
		/*29*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGAZIONE_NORME,CostantiTabelleDinamiche.CATALOGAZIONE_NORME_INDEX));
		/*30*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA,CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX));
		/*31*/	    menuItems.add(new MenuItem(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO,CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX));
		/*32*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.SISTEMI_RETI_BIBLITOECHE,CostantiTabelleDinamiche.SISTEMI_RETI_BIBLITOECHE_INDEX));
		/* FONDI_ANTICHI_CONSISTENZA:in attesa di decisione per la gestione sul database:rimossa dal menù*/
		//		/*33*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.FONDI_ANTICHI_CONSISTENZA,CostantiTabelleDinamiche.FONDI_ANTICHI_CONSISTENZA_INDEX));
		/*34*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGO_GENERALE_TIPO,CostantiTabelleDinamiche.CATALOGO_GENERALE_TIPO_INDEX));
		/*35*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_ZONA_TIPO,CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_ZONA_TIPO_INDEX));
		//RIMOSSO DOPPIONE TABELLA DINAMICA
		//		/*36*/		menuItems.add(new MenuItem(CostantiTabelleDinamiche.STATO_CATALOGAZIONE_BIBLIOTECHE_TIPO,CostantiTabelleDinamiche.STATO_CATALOGAZIONE_BIBLIOTECHE_TIPO_INDEX));
		return menuItems;
	}

	public static List<MenuItem> getTreeModelGestBiblio() {
		List<MenuItem> items = new ArrayList<MenuItem>();
		if (Roles.isUserInRole(Roles.CATALOGAZIONE) 
				|| Roles.isUserInRole(Roles.REVISIONE) 
				|| Roles.isUserInRole(Roles.CANCELLAZIONE)){
			items.add(new MenuItem(CostantiGestioneBiblio.LISTA_BIB, "icon-find"));
			items.add(new MenuItem(CostantiGestioneBiblio.GEST_BIB, "icon-find"));
			items.add(new MenuItem(CostantiGestioneBiblio.GEST_BIB_COD, "icon-find"));
		}
//		if (Roles.isUserInRole(Roles.VEDI_TUTTE))
//			items.add(new MenuItem(CostantiGestioneBiblio.GEST_BIB_COD, "icon-find"));
		
		return items;
	}

	public static List<MenuItem> getTreeModelGestUtenti() {
		List<MenuItem> items = new ArrayList<MenuItem>();
		items.add(new MenuItem(CostantiGestioneUtenti.GESTIONE_UTENTI, "icon-users"));
		items.add(new MenuItem(CostantiGestioneUtenti.NUOVO_UTENTE,	"icon-user-add"));
		items.add(new MenuItem(CostantiGestioneUtenti.ASS_LIST_BIB_LIST_UT, "tag"));
		return items;
	}

	public static List<MenuItem> getTreeModelStatistiche() {
		List<MenuItem> modelStatistiche = new ArrayList<MenuItem>();
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_01));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_01a));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_01b));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_01c));

		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_02));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_02a));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_02b));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_02c));

		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_03));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_04));

		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_05));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_05a));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_05b));

		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_06));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_07));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_08));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_09));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.TAV_10));

		modelStatistiche.add(new MenuItem(CostantiStatistiche.GRAF_01));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.GRAF_02));
		modelStatistiche.add(new MenuItem(CostantiStatistiche.GRAF_03));
		return modelStatistiche;
	}

	public static List<MenuItem> getTreeModelReport() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(	CostantiReport.RICERCA_BIBLIO_CREAZIONE_STAMPE));
		return menuItems;
	}

	public static List<MenuItem> getTreeModelFormatoScambio() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(CostantiFormatoScambio.FORM_UPLOAD, "icon-upload"));
		menuItems.add(new MenuItem(CostantiFormatoScambio.GESTIONE_FILE_CARICATI_NON_CONTROLLATI, "icon-folder-error"));
		menuItems.add(new MenuItem(CostantiFormatoScambio.GESTIONE_FILE_CONTROLLATI, "icon-folder-go"));
		menuItems.add(new MenuItem(CostantiFormatoScambio.GESTIONE_IMPORT_SCHEDULATI, "icon-folder-bell"));
		menuItems.add(new MenuItem(CostantiFormatoScambio.RICERCA_BIBLIO_CREAZIONE_EXPORT, "icon-download"));
		menuItems.add(new MenuItem(CostantiFormatoScambio.GESTIONE_EXPORT_SCHEDULATI, "icon-folder-bell"));
		return menuItems;
	}
}