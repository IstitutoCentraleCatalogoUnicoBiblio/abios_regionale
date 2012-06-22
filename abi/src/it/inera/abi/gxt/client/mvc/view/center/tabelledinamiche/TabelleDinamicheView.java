package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche;

import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaCataloghiCollettiviTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaComuniTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaDeweyTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaPatrimonioGrandiVociTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaPatrimonioPiccoleVociTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaProvinceTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget.ListaVoceSingolaTabelleDinamiche;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class TabelleDinamicheView extends View {

	public static final int GRID_ROWS_NUMBER = 30;

	private BorderLayoutData center;

	private ListaVoceSingolaTabelleDinamiche listaVociSingole;
	private ListaProvinceTabelleDinamiche listaProvince;
	private ListaComuniTabelleDinamiche listaComuni;
	private ListaPatrimonioGrandiVociTabelleDinamiche listaPatrimonioGrandiVoci;
	private ListaPatrimonioPiccoleVociTabelleDinamiche listaPatrimonioPiccoleVoci;
	private ListaCataloghiCollettiviTabelleDinamiche cataloghiCollettivi;
	private ListaDeweyTabelleDinamiche listaDewey;
	
	
	public TabelleDinamicheView(Controller controller) {
		super(controller);
	}

	protected void initialize() {
		center = new BorderLayoutData(LayoutRegion.CENTER);
		
		/* Voce singola -> INIT */
		listaVociSingole = new ListaVoceSingolaTabelleDinamiche();
		listaVociSingole.setTableType(-1);
		listaVociSingole.setGrid();
		listaVociSingole.setTopToolbar();
		listaVociSingole.setBottomToolbar();
		
		/* Voce singola -> END */
		
		/* Province -> INIT */
		listaProvince = new ListaProvinceTabelleDinamiche();
		listaProvince.setHeading(CostantiTabelleDinamiche.PROVINCE);
		listaProvince.setGrid();
		/* Province -> END */
		
		/* Comuni -> INIT */
		listaComuni = new ListaComuniTabelleDinamiche();
		listaComuni.setHeading(CostantiTabelleDinamiche.COMUNI);
		listaComuni.setGrid();
		listaComuni.setTopToolbar();
		/* Comuni -> END */
		
		/* Patrimonio, grandi voci -> INIT */
		listaPatrimonioGrandiVoci = new ListaPatrimonioGrandiVociTabelleDinamiche();
		listaPatrimonioGrandiVoci.setHeading(CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_GRANDI_VOCI);
		listaPatrimonioGrandiVoci.setGrid();
		/* Patrimonio, grandi voci -> END */
		
		/* Patrimonio, piccole voci -> INIT */
		listaPatrimonioPiccoleVoci = new ListaPatrimonioPiccoleVociTabelleDinamiche();
		listaPatrimonioPiccoleVoci.setHeading(CostantiTabelleDinamiche.PATRIMONIO_LIBRARIO_PICCOLE_VOCI);
		listaPatrimonioPiccoleVoci.setGrid();
		/* Patrimonio, piccole voci -> END */
		
		/* Cataloghi collettivi -> INIT */
		cataloghiCollettivi = new ListaCataloghiCollettiviTabelleDinamiche();
		cataloghiCollettivi.setHeading(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI);
		cataloghiCollettivi.setGrid();		
		/* Cataloghi collettivi -> END */
		
		
		/* Dewey -> INIT */
		listaDewey = new ListaDeweyTabelleDinamiche();
		listaDewey.setHeading(CostantiTabelleDinamiche.DEWEY);
		listaDewey.setGrid();
		/* Dewey -> END */
	}

	@Override
	protected void handleEvent(AppEvent event) {

		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);

		wrapper.removeAll();

		int tableId = 2;
		if (event.getData() != null) {
			tableId =(Integer) event.getData();
		}

		switch (tableId) {
		case 0: {/* STATI */	
			break;
		}
		case 1: {/* REGIONI */
			break;
		}
		case 2: {/* PROVINCE */
			listaProvince.layout();

			wrapper.add(listaProvince, center);	
			break;
		}
		case 3: {/* COMUNI */
			listaComuni.layout();

			wrapper.add(listaComuni, center);	
			break; 
		}
		case 4: {/* PATRIMONIO_LIBRARIO_GRANDI_VOCI */
			listaPatrimonioGrandiVoci.layout();

			wrapper.add(listaPatrimonioGrandiVoci, center);	
			break;
		}
		case 5: {/* PATRIMONIO_LIBRARIO_PICCOLE_VOCI */
			listaPatrimonioPiccoleVoci.layout();

			wrapper.add(listaPatrimonioPiccoleVoci, center);
			break;
		}
		case 6: {/* CATALOGHI COLLETTIVI */
			cataloghiCollettivi.layout();

			wrapper.add(cataloghiCollettivi, center);	
			break;
		}
		case 7 :{/* DESTINAZIONI SOCIALI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.DESTINAZIONI_SOCIALI_TIPOLOGIE);
			
			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 8: {
			break;
		}
		case 9: {/* ACCESSO_MODALITA */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.ACCESSO_MODALITA);
			
			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 10: {/* ENTI_TIPOLOGIE_AMMINISTRATIVE */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE);
			
			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 11: {/* ENTI_FUNZIONI_OBIETTIVI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.ENTI_FUNZIONI_OBIETTIVI);
			
			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 12: {/* TIPOLOGIE_FUNZIONALI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI);
			
			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 13: {/* CATALOGAZIONE_STATI_BIBLIOTECHE */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CATALOGAZIONE_STATI_BIBLIOTECHE);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 14: {/* Thesaurus */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.THESAURUS);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 15: {/* DEWEY */ 
			listaDewey.layout();
			
			wrapper.add(listaDewey, center);	
			break;
		}
		case 16: {/* CONTATTI_TIPI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CONTATTI_TIPI);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 17: {/* RIPRODUZIONI_TIPI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.RIPRODUZIONI_TIPI);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 18: {/* SERVIZI_MODALITA_COMUNICAZIONE_INFORMAZIONI_BIBLIOGRAFICHE */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.SERVIZI_MODALITA_COMUNICAZIONE_INFOTMAZIONI_BIBLIOGRAFICHE);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 19: {/* CARTA DEI SERVIZI */
			/* In attesa di decisione per la gestione sul database: rimossa dal menù */
			break;
		}
		case 20: {/* FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.FONDI_SPECIALI_TIPI_CATALOGAZIONE_INVENTARIO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 21: {/* CATALOGHI_TIPI_SUPPORTO_INVENTARIO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CATALOGHI_TIPI_SUPPORTO_INVENTARIO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 22: {
			break;
		}
		case 23: {/* PRESTITO_LOCALE_UTENTI_AMMESSI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.PRESTITO_LOCALE_UTENTI_AMMESSI);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 24: {/* PRESTITO_LOCALE_MATERIALE_ESCLUSO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.PRESTITO_LOCALE_MATERIALE_ESCLUSO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 25: {/* PRESTITO_INTERBIBLIOTECARIO_MODO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_MODO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 26: {/* PRESTITO_INTERBIBLIOTECARIO_SISTEMI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_SISTEMI);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 27: {/* SEZIONI_SPECIALI */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.SEZIONI_SPECIALI);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 28: {/* DEPOSITI_LEGALI_TIPOLOGIE */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.DEPOSITI_LEGALI_TIPOLOGIE);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);	
			break;
		}
		case 29: {/* CATALOGAZIONE_NORME */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CATALOGAZIONE_NORME);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}
		case 30: {/* INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}
		case 31: {/* INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}
		case 32: {/* SISTEMI_RETI_BIBLITOECHE */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.SISTEMI_RETI_BIBLITOECHE);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}
		case 33: {/* FONDI ANTICHI CONSISTENZA */
			/* In attesa di decisione per la gestione sul database: rimossa dal menù */
			break;
		}
		case 34: {/* CATALOGO_GENERALE_TIPO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CATALOGO_GENERALE_TIPO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}
		case 35: {/* /CATALOGHI_COLLETTIVI_ZONA_TIPO */
			listaVociSingole.setTableType(tableId);
			listaVociSingole.setHeading(CostantiTabelleDinamiche.CATALOGHI_COLLETTIVI_ZONA_TIPO);

			listaVociSingole.layout();
			wrapper.add(listaVociSingole, center);
			break;
		}	

		}

		wrapper.layout();
		return;

	}

}
