package it.inera.abi.gxt.client;

import it.inera.abi.gxt.client.mvc.controller.AppController;
import it.inera.abi.gxt.client.mvc.controller.FormatoScambioController;
import it.inera.abi.gxt.client.mvc.controller.GestioneBibliotecheController;
import it.inera.abi.gxt.client.mvc.controller.GestioneReportController;
import it.inera.abi.gxt.client.mvc.controller.GestioneUtentiController;
import it.inera.abi.gxt.client.mvc.controller.StatisticheController;
import it.inera.abi.gxt.client.mvc.controller.TabelleDinamicheController;
import it.inera.abi.gxt.client.services.AuthService;
import it.inera.abi.gxt.client.services.AuthServiceAsync;
import it.inera.abi.gxt.client.services.BibliotecheService;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.FormatoScambioService;
import it.inera.abi.gxt.client.services.FormatoScambioServiceAsync;
import it.inera.abi.gxt.client.services.LocationService;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.ReportService;
import it.inera.abi.gxt.client.services.ReportServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheService;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.services.UtentiService;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class Abi implements EntryPoint {

	public static final String USERLOGGED = "userlogged"; // key per utente loggato nel registry
	
	public static final String BIBLIOTECHE_SERVICE = "bibliotecheService";
	public static final String TABELLE_DINAMICHE_SERVICE = "tabelleDinamicheService";
	public static final String UTENTI_SERVICE = "utentiService";
	public static final String LOCATION_SERVICE = "locationService";
	public static final String FORMATO_SCAMBIO = "formatoScambio";
	
	public static final String AUTHSERVICE = "authservice"; // servizio per l'autenticazione
	/* Servizio per i dati del report */
	public static final String REPORTSERVICE = "reportservice"; 
	
	public void onModuleLoad() {
		GXT.setDefaultTheme(Theme.GRAY, true);

		BibliotecheServiceAsync bibliotecheServiceAsync = (BibliotecheServiceAsync) GWT.create(BibliotecheService.class);
		
		TabelleDinamicheServiceAsync tabelleDinamicheService = (TabelleDinamicheServiceAsync) GWT.create(TabelleDinamicheService.class);
		UtentiServiceAsync utentiService = (UtentiServiceAsync) GWT.create(UtentiService.class);
		LocationServiceAsync locationService =(LocationServiceAsync) GWT.create(LocationService.class);
		FormatoScambioServiceAsync formatoScambio = (FormatoScambioServiceAsync) GWT.create(FormatoScambioService.class);
		AuthServiceAsync authService = GWT.create(AuthService.class); // autenticazione
		/* Report */
		ReportServiceAsync reportService = (ReportServiceAsync) GWT.create(ReportService.class);
		
		
		Registry.register(BIBLIOTECHE_SERVICE, bibliotecheServiceAsync);
		Registry.register(TABELLE_DINAMICHE_SERVICE, tabelleDinamicheService);
		Registry.register(UTENTI_SERVICE, utentiService); 
		Registry.register(LOCATION_SERVICE, locationService); 
		Registry.register(FORMATO_SCAMBIO, formatoScambio); 
		Registry.register(AUTHSERVICE, authService); // autenticazione
		/* Report */
		Registry.register(REPORTSERVICE, reportService);
		
		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new GestioneBibliotecheController());
		dispatcher.addController(new GestioneUtentiController());
		dispatcher.addController(new TabelleDinamicheController());
		dispatcher.addController(new StatisticheController());
		dispatcher.addController(new GestioneReportController());
		dispatcher.addController(new FormatoScambioController());
		
		
		//dispatcher.dispatch(AppEvents.Init);
		dispatcher.dispatch(AppEvents.AuthRequest); // invio al caricamento dei dati utente dopo autenticazione
		
		GXT.hideLoadingPanel("loading");
	}

}
