package it.inera.abi.gxt.client.mvc.view.center.biblioteche;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.BilancioDepositoLegalePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.CataloghiCollettiviPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.CataloghiGeneraliPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.CataloghiSpecialiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.DatiAnagraficiFormPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.InfoCatalogazionePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.ModalitaAccessoDestinazioneSocialePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.NoteCatalogatorePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.OrarioUfficaleVariazioniPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.PatrimonioLibrarioPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.PersonalePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.PhotoPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.PrestitoPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.SedeInformazioniSupplPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.ServiziSezioniSpecialiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.SistemiDiBibliotechePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.SpecializzazioniFondiSpecialiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.TipologiaAmministrativaFunzionalePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.DefinitivaRevisioneWindow;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.DifferenzeWindow;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.RespingiRevisioneWindow;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.RevisioneWindow;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * View utilizzata per l'inizializzazione del pannello di modifica della biblioteca,
 * comprensiva di tutte le schede specifiche e la relativa gestione degli eventi
 *
 */
public class ModificaBibliotecaView extends View {

	private ContentPanel mainModficaBiblioPanel = null;
	private BiblioModel tmpBiblio;
	private DatiAnagraficiFormPanel datiAnagrafici;
	private	ModalitaAccessoDestinazioneSocialePanel accessoDestinazioneSocialePanel; 
	private OrarioUfficaleVariazioniPanel orarioUfficaleVariazioniPanel;
	private PatrimonioLibrarioPanel patrimonioLibrarioPanel;
	private SpecializzazioniFondiSpecialiPanel specializzazioniFondiSpecialiPanel;
	private InfoCatalogazionePanel infoCatalogazionePanel;
	private CataloghiCollettiviPanel cataloghiCollettiviPanel;
	private CataloghiSpecialiPanel cataloghiSpecialiPanel;
	private CataloghiGeneraliPanel cataloghiGeneraliPanel;
	private ServiziSezioniSpecialiPanel serviziSezioniSpecialiPanel;
	private PrestitoPanel prestitoPanel;
	private PersonalePanel personalePanel;
	private BilancioDepositoLegalePanel bilancioDepositoLegalePanel;
	private NoteCatalogatorePanel noteCatalogatorePanel;

	private TabPanel modificaBiblioTabPanel;

	private ToolBar toolBar;
	private Button definitiva = new Button("Definitiva", Resources.ICONS.accept());
	private Button respingi = new Button("Respingi", Resources.ICONS.exclamation());
	private Button ripristina = new Button("Ripristina", Resources.ICONS.arrow_redo());
	private Button revisionabile = new Button("Revisionabile", Resources.ICONS.eye());
	private Button cancella = new Button("Cancella", Resources.ICONS.delete());
	private Button differenze = new Button("Differenze", Resources.ICONS.table_relationship());
	private Button lista = new Button("Lista", Resources.ICONS.application_view_list());

	private RevisioneWindow definitivaRevisioneWindow = new DefinitivaRevisioneWindow("Rendi definitiva", "Definitiva");
	private RevisioneWindow respingiRevisioneWindow = new RespingiRevisioneWindow("Respingi revisione", "Respingi");
	private DifferenzeWindow differenzeWindow = new DifferenzeWindow();

	private TabPanel subTabBiblioteca;
	private	TabItem datiAnagraficiSistemiBiblioSubTab;
	private TabItem tabBiblioteca;
	
	private boolean ricercaGenerica;
	
	public ModificaBibliotecaView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainModficaBiblioPanel = new ContentPanel();
		mainModficaBiblioPanel.setLayout(new FitLayout());
		toolBar = new ToolBar();
		toolBar.add(lista);
		toolBar.add(definitiva);
		toolBar.add(respingi);
		toolBar.add(ripristina);
		toolBar.add(revisionabile);
		toolBar.add(differenze);
		toolBar.add(cancella); 
		mainModficaBiblioPanel.setTopComponent(toolBar);
		modificaBiblioTabPanel = new TabPanel();
		tabBiblioteca = new TabItem();
		subTabBiblioteca = new TabPanel();
		subTabBiblioteca.addStyleName("custom-tab-panel-text");
		initToolbarListener();
		createModificaBiblioteche();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		if (event.getType() == AppEvents.SelectBiblio) {
			wrapper.removeAll();
			ricercaGenerica = ((Boolean) event.getData("ricercaGenerica")).booleanValue();
			tmpBiblio = (BiblioModel) event.getData("biblioteca");
			Boolean readOnly = (event.getData(BiblioModel.READONLY) == null ? false : (Boolean) event.getData(BiblioModel.READONLY));
			// r.eschini il readOnly serve nei pannelli della biblioteca per sapere se sono in sola lettura (visualizzazione) o modifica
			// lo metto nel registry per far prima, andrebbero modificati i singoli pannelli con il parametro booleano
			// che indica se è in sola lettura
			Registry.register(BiblioModel.READONLY, readOnly);
			// ------------------------------------------------
			Boolean revisione = (event.getData(BiblioModel.REVISIONE) == null ? false : (Boolean) event.getData(BiblioModel.REVISIONE));
			// r.eschini revisione serve per sapere se sono in revisione della biblioteca
			Registry.register(BiblioModel.REVISIONE, revisione);
			// ------------------------------------------------
			if (!tmpBiblio.equals(null)) {

				updateToolbar();

				String heading = null;

				if (revisione) {
					heading = "Revisione biblioteca:  ";
				} else {
					if (readOnly) 
						heading = "Visualizzazione biblioteca:  ";
					else
						heading = "Modifica biblioteca:  ";
				}
				mainModficaBiblioPanel.setHeading(heading	
						+ tmpBiblio.getCodice() + " - "
						+ tmpBiblio.getDenominazione() + " - "
						+ tmpBiblio.getComune().getDenominazione()
				);
				datiAnagrafici.setBiblioteca(tmpBiblio);
				if (readOnly) {
					datiAnagrafici.setGeolocalizzaText("Mappa");					
				}
				else {
					datiAnagrafici.setGeolocalizzaText("Geolocalizza");
				}
				accessoDestinazioneSocialePanel.setIdBiblio(tmpBiblio.getIdBiblio());
				orarioUfficaleVariazioniPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				patrimonioLibrarioPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				specializzazioniFondiSpecialiPanel.setIdBiblio(tmpBiblio.getIdBiblio());
//				specializzazioniFondiSpecialiPanel.createSpecializzazioniEFondiSpecialiPanels();
				infoCatalogazionePanel .setIdBiblio(tmpBiblio.getIdBiblio());
				cataloghiCollettiviPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				cataloghiSpecialiPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				cataloghiGeneraliPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				serviziSezioniSpecialiPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				prestitoPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				personalePanel.setIdBiblio(tmpBiblio.getIdBiblio());
				bilancioDepositoLegalePanel.setIdBiblio(tmpBiblio.getIdBiblio());
				noteCatalogatorePanel.setIdBiblio(tmpBiblio.getIdBiblio());

			}
			modificaBiblioTabPanel.setSelection(tabBiblioteca);
			subTabBiblioteca.setSelection(datiAnagraficiSistemiBiblioSubTab);
			mainModficaBiblioPanel.layout();
			wrapper.add(mainModficaBiblioPanel);
			wrapper.layout();
			wrapper.unmask();
		}

		if (event.getType() == AppEvents.ReloadBiblioData) {
			tmpBiblio = (BiblioModel) event.getData("biblioteca");
			serviziSezioniSpecialiPanel.setBiblioteca(tmpBiblio);
			serviziSezioniSpecialiPanel.setFieldsValues();
			prestitoPanel.setBiblioteca(tmpBiblio);
			prestitoPanel.setFieldsValues();
			bilancioDepositoLegalePanel.setBiblioteca(tmpBiblio);
			bilancioDepositoLegalePanel.setFieldsValues();
		}

		return;
	}

	private void createModificaBiblioteche() {

		/* TAB BIBLIOTECA E VARI SOTTO TAB */

		tabBiblioteca.setLayout(new FitLayout());

		tabBiblioteca.setText(CostantiGestioneBiblio.TAB_BIBLIOTECA_GENERALE);


		datiAnagraficiSistemiBiblioSubTab = new TabItem();
		datiAnagraficiSistemiBiblioSubTab.setText(CostantiGestioneBiblio.DATI_ANAGRAFICI);
		datiAnagraficiSistemiBiblioSubTab.setScrollMode(Scroll.AUTOY);

		datiAnagraficiSistemiBiblioSubTab.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				datiAnagrafici.setBiblioteca(tmpBiblio);
				datiAnagrafici.setValueFields();

			}
		});

		datiAnagrafici = new DatiAnagraficiFormPanel();
		datiAnagrafici.createFormAnagrafica();

		datiAnagraficiSistemiBiblioSubTab.add(datiAnagrafici);

		TabItem sistemiBiblitoecheSubTab = new TabItem();
		sistemiBiblitoecheSubTab.setScrollMode(Scroll.AUTOY);
		sistemiBiblitoecheSubTab.setText(CostantiGestioneBiblio.SISTEMI_BIBLITOECHE);

		final SistemiDiBibliotechePanel sistemiDiBibliotechePanel = new SistemiDiBibliotechePanel();
		sistemiBiblitoecheSubTab.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				sistemiDiBibliotechePanel.setBiblioteca(tmpBiblio);
				sistemiDiBibliotechePanel.setValueFields();
			}
		});

		sistemiBiblitoecheSubTab.add(sistemiDiBibliotechePanel);

		TabItem sedeInfoSupplemSubTab = new TabItem();
		sedeInfoSupplemSubTab.setScrollMode(Scroll.AUTOY);
		sedeInfoSupplemSubTab.setText(CostantiGestioneBiblio.SEDE_INFO_SUPPL);

		final SedeInformazioniSupplPanel sedeInformazioniSupplPanel = new SedeInformazioniSupplPanel();
		sedeInfoSupplemSubTab.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				sedeInformazioniSupplPanel.removeAll();
				sedeInformazioniSupplPanel.setBiblioteca(tmpBiblio);
				sedeInformazioniSupplPanel.createForms();
				sedeInformazioniSupplPanel.layout();
			}
		});

		sedeInfoSupplemSubTab.add(sedeInformazioniSupplPanel);
		
		/* Foto */
		TabItem fotoSedeSubTab = new TabItem();
		fotoSedeSubTab.setScrollMode(Scroll.AUTOY);
		fotoSedeSubTab.setText(CostantiGestioneBiblio.FOTO_SEDE);
		
		final PhotoPanel photoPanel = new PhotoPanel();
		fotoSedeSubTab.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				photoPanel.setBiblioteca(tmpBiblio);
				photoPanel.setFieldsValues();
			}
		});
		fotoSedeSubTab.add(photoPanel);

		TabItem tipologiaAmministrativaFunzionaleSubTab = new TabItem();
		tipologiaAmministrativaFunzionaleSubTab.setScrollMode(Scroll.AUTOY);
		tipologiaAmministrativaFunzionaleSubTab.setText(CostantiGestioneBiblio.TIPOLOGIA_AMMINISTRATIVA_FUNZIONALE);

		final TipologiaAmministrativaFunzionalePanel tipologiaAmministrativaFunzionalePanel = new TipologiaAmministrativaFunzionalePanel();
		tipologiaAmministrativaFunzionaleSubTab.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				tipologiaAmministrativaFunzionalePanel.setBiblioteca(tmpBiblio);
				tipologiaAmministrativaFunzionalePanel.setFieldValues();
			}
		});
		tipologiaAmministrativaFunzionaleSubTab.add(tipologiaAmministrativaFunzionalePanel);

		TabItem subTabNoteCatalogatore = new TabItem();
		subTabNoteCatalogatore.setText(CostantiGestioneBiblio.TAB_GESTIONALE);
		subTabNoteCatalogatore.setLayout(new FitLayout());
		subTabNoteCatalogatore.setScrollMode(Scroll.AUTOY);
		noteCatalogatorePanel=	new NoteCatalogatorePanel();
		subTabNoteCatalogatore.add(noteCatalogatorePanel);

		subTabNoteCatalogatore.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				noteCatalogatorePanel.setBiblioteca(tmpBiblio);
				noteCatalogatorePanel.setFieldsValues();
			}
		});

		subTabBiblioteca.add(datiAnagraficiSistemiBiblioSubTab);
		subTabBiblioteca.add(sistemiBiblitoecheSubTab);
		subTabBiblioteca.add(sedeInfoSupplemSubTab);
		subTabBiblioteca.add(fotoSedeSubTab);
		subTabBiblioteca.add(tipologiaAmministrativaFunzionaleSubTab);		
		subTabBiblioteca.add(subTabNoteCatalogatore);

		tabBiblioteca.add(subTabBiblioteca);
		modificaBiblioTabPanel.add(tabBiblioteca);
		/** FINE------------TAB BIBLIOTEA E VARI SOTTO TAB **/

		/** TAB ACCESSO E VARI SOTTO TAB **/
		TabItem tabAccesso = new TabItem();
		tabAccesso.setLayout(new FitLayout());
		tabAccesso.setText(CostantiGestioneBiblio.TAB_ACCESSO);
		TabPanel subTabAccesso = new TabPanel();
		subTabAccesso.addStyleName("custom-tab-panel-text");
		
		TabItem accessoDestinaioneSociale = new TabItem();
		accessoDestinaioneSociale.setText(CostantiGestioneBiblio.ACCESSO_DESTINAZIONE_SOCIALE);
		accessoDestinaioneSociale.setScrollMode(Scroll.AUTOY);

		accessoDestinazioneSocialePanel = new ModalitaAccessoDestinazioneSocialePanel();

		accessoDestinaioneSociale.add(accessoDestinazioneSocialePanel);
		accessoDestinaioneSociale.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				accessoDestinazioneSocialePanel.setBiblioteca(tmpBiblio);
				accessoDestinazioneSocialePanel.setFieldsValues();
			}
		});

		TabItem orariUfficialeVariazione = new TabItem();
		orariUfficialeVariazione.setText(CostantiGestioneBiblio.ORARI_UFFICIALE_VARIAZIONE);

		orariUfficialeVariazione.setScrollMode(Scroll.AUTOY);

		orarioUfficaleVariazioniPanel =new OrarioUfficaleVariazioniPanel();
		orariUfficialeVariazione.add(orarioUfficaleVariazioniPanel);

		orariUfficialeVariazione.addListener(Events.Select,	new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				orarioUfficaleVariazioniPanel.setBiblioteca(tmpBiblio);
				orarioUfficaleVariazioniPanel.setFieldsValues();
			}
		});

		subTabAccesso.add(accessoDestinaioneSociale);
		subTabAccesso.add(orariUfficialeVariazione);

		tabAccesso.add(subTabAccesso);
		modificaBiblioTabPanel.add(tabAccesso);
		/** FINE------TAB ACCESSO E VARI SOTTO TAB **/

		/*
		 * TAB PATRIMONIO SPECIALIZZAZIONI FONDI INFO CATALOGAZIONE E VARI SOTTO
		 * TAB
		 **/
		TabItem tabPatrimonioSpecializzazioniFondiInfoCatalogazione = new TabItem();
		tabPatrimonioSpecializzazioniFondiInfoCatalogazione.setText(CostantiGestioneBiblio.TAB_PATRIMONIO_SPECIALIZZAZIONI_FONDI_INFO_CATALOG);
		tabPatrimonioSpecializzazioniFondiInfoCatalogazione.setLayout(new FitLayout());

		final	TabPanel subTabPatrimonioSpecializzazioniFondiInfoCatalogazione = new TabPanel();
		subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.addStyleName("custom-tab-panel-text");

		final TabItem patrimonioLibrario = new TabItem();
		patrimonioLibrario.setText(CostantiGestioneBiblio.PATROMONIO_LIBRARIO);
		patrimonioLibrario.setScrollMode(Scroll.AUTOY);

		patrimonioLibrarioPanel =new PatrimonioLibrarioPanel();
		patrimonioLibrario.add(patrimonioLibrarioPanel);
		patrimonioLibrario.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				patrimonioLibrarioPanel.setBiblioteca(tmpBiblio);
				patrimonioLibrarioPanel.setFieldsValues();
			}
		});

		TabItem specializzazioniTabItem = new TabItem();
		specializzazioniTabItem.setText(CostantiGestioneBiblio.SPECIALIZZAZIONI_FONDI_SPECIALI);
		specializzazioniTabItem.setScrollMode(Scroll.AUTOY);
		specializzazioniFondiSpecialiPanel =new SpecializzazioniFondiSpecialiPanel();
		specializzazioniTabItem.add(specializzazioniFondiSpecialiPanel);

		specializzazioniTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				specializzazioniFondiSpecialiPanel.setIdBiblio(tmpBiblio.getIdBiblio());
				specializzazioniFondiSpecialiPanel.setBiblioteca(tmpBiblio);
				specializzazioniFondiSpecialiPanel.createSpecializzazioniEFondiSpecialiPanels();
			}
		});

		tabPatrimonioSpecializzazioniFondiInfoCatalogazione.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if(subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.getSelectedItem()!=patrimonioLibrario)
					subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.setSelection(patrimonioLibrario);
			}
		});
		TabItem infoCatalogazioneTabItem = new TabItem();
		infoCatalogazioneTabItem.setText(CostantiGestioneBiblio.INFO_CATALOGAZIONE);
		infoCatalogazioneTabItem.setScrollMode(Scroll.AUTOY);

		infoCatalogazionePanel = new InfoCatalogazionePanel();
		infoCatalogazioneTabItem.add(infoCatalogazionePanel);

		infoCatalogazioneTabItem.addListener(Events.Select,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				infoCatalogazionePanel.setBiblioteca(tmpBiblio);
				infoCatalogazionePanel.setFieldsValues();

			}
		});

		subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.add(patrimonioLibrario);
		subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.add(specializzazioniTabItem);
		subTabPatrimonioSpecializzazioniFondiInfoCatalogazione.add(infoCatalogazioneTabItem);

		tabPatrimonioSpecializzazioniFondiInfoCatalogazione.add(subTabPatrimonioSpecializzazioniFondiInfoCatalogazione);
		modificaBiblioTabPanel.add(tabPatrimonioSpecializzazioniFondiInfoCatalogazione);

		/*
		 * FINE-----TAB PATRIMONIO SPECIALIZZAZIONI FONDI INFO CATALOGAZIONE E
		 * VARI SOTTO TAB
		 **/

		/* TAB CATALOGHI E VARI SOTTO TAB */
		TabItem tabCataloghi = new TabItem();
		tabCataloghi.setText(CostantiGestioneBiblio.TAB_CATALOGHI);
		tabCataloghi.setLayout(new FitLayout());
		TabPanel subTabCataloghi = new TabPanel();
		subTabCataloghi.addStyleName("custom-tab-panel-text");

		TabItem cataloghiGeneraliTabItem = new TabItem();
		cataloghiGeneraliTabItem.setText("Cataloghi generali");
		cataloghiGeneraliTabItem.setLayout(new FitLayout());

		cataloghiGeneraliPanel = new CataloghiGeneraliPanel();
		cataloghiGeneraliTabItem.add(cataloghiGeneraliPanel);
		cataloghiGeneraliTabItem.addListener(Events.Select,new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				cataloghiGeneraliPanel.setBiblioteca(tmpBiblio);
				cataloghiGeneraliPanel.setFieldsValues();
			}
		});
		TabItem cataloghiSpecialiTabItem = new TabItem();
		cataloghiSpecialiTabItem.setText("Cataloghi speciali");
		cataloghiSpecialiTabItem.setLayout(new FitLayout());


		cataloghiSpecialiPanel = new CataloghiSpecialiPanel();
		cataloghiSpecialiTabItem.add(cataloghiSpecialiPanel);


		cataloghiSpecialiTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cataloghiSpecialiPanel.setBiblioteca(tmpBiblio);
				cataloghiSpecialiPanel.setFieldsValues();
			}
		});

		TabItem cataloghiCollettiviTabItem = new TabItem();
		cataloghiCollettiviTabItem.setText("Cataloghi collettivi");
		cataloghiCollettiviTabItem.setLayout(new FitLayout());

		cataloghiCollettiviPanel = new CataloghiCollettiviPanel();
		cataloghiCollettiviTabItem.add(cataloghiCollettiviPanel);

		cataloghiCollettiviTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cataloghiCollettiviPanel.setBiblioteca(tmpBiblio);
				cataloghiCollettiviPanel.setFieldsValues();
			}
		});

		subTabCataloghi.add(cataloghiGeneraliTabItem);
		subTabCataloghi.add(cataloghiSpecialiTabItem);
		subTabCataloghi.add(cataloghiCollettiviTabItem);

		tabCataloghi.add(subTabCataloghi);
		modificaBiblioTabPanel.add(tabCataloghi);
		/* FINE----TAB CATALOGHI E VARI SOTTO TAB */

		/* TAB SERVIZIO E PRESTITO E VARI SOTTO TAB */
		TabItem tabServiziPrestito = new TabItem();
		tabServiziPrestito.setText(CostantiGestioneBiblio.TAB_SERVIZI_PRESTITO_SEZIONISPECIALI);
		tabServiziPrestito.setLayout(new FitLayout());

		TabPanel subTabServiziPrestito = new TabPanel();
		subTabServiziPrestito.addStyleName("custom-tab-panel-text");

		TabItem serviziTabItem = new TabItem();
		serviziTabItem.setText("Servizi - Sezioni speciali");
		serviziTabItem.setScrollMode(Scroll.AUTOY);

		serviziSezioniSpecialiPanel=	new ServiziSezioniSpecialiPanel();
		serviziTabItem.add(serviziSezioniSpecialiPanel);


		serviziTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				serviziSezioniSpecialiPanel.setBiblioteca(tmpBiblio);
				serviziSezioniSpecialiPanel.setFieldsValues();
			}
		});

		TabItem prestitoTabItem = new TabItem();
		prestitoTabItem.setText("Prestito");
		prestitoTabItem	.setScrollMode(Scroll.AUTOY);
		prestitoPanel = new PrestitoPanel();

		prestitoTabItem.add(prestitoPanel);
		prestitoTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				prestitoPanel.setBiblioteca(tmpBiblio);
				prestitoPanel.setFieldsValues();
			}
		});
		subTabServiziPrestito.add(serviziTabItem);
		subTabServiziPrestito.add(prestitoTabItem);
		tabServiziPrestito.add(subTabServiziPrestito);
		modificaBiblioTabPanel.add(tabServiziPrestito);
		/* FINE----TAB SERVIZIO E PRESTITO E VARI SOTTO TAB */

		/* TAB INFO AMMINISTRATIVE E VARI SOTTO TAB */
		TabItem tabInfoAmministrative = new TabItem();
		tabInfoAmministrative.setText(CostantiGestioneBiblio.TAB_INFO_AMMINISTRATIVE);
		tabInfoAmministrative.setLayout(new FitLayout());

		TabPanel subTabInfoAmministrative = new TabPanel();
		subTabInfoAmministrative.addStyleName("custom-tab-panel-text");

		TabItem personaleTabItem = new TabItem();
		personaleTabItem.setScrollMode(Scroll.AUTOY);
		personaleTabItem.setText("Personale");

		personalePanel=new PersonalePanel();
		personaleTabItem.add(personalePanel);

		personaleTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				personalePanel.setBiblioteca(tmpBiblio);
				personalePanel.setFieldsValues();
			}
		});

		TabItem bilancioTabItem = new TabItem();
		bilancioTabItem.setText("Bilancio - Deposito legale");
		bilancioTabItem.setScrollMode(Scroll.AUTOY);
		bilancioDepositoLegalePanel = new BilancioDepositoLegalePanel();
		bilancioTabItem.add(bilancioDepositoLegalePanel);
		bilancioTabItem.addListener(Events.Select,new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				bilancioDepositoLegalePanel.setBiblioteca(tmpBiblio);
				bilancioDepositoLegalePanel.setFieldsValues();
			}
		});


		subTabInfoAmministrative.add(personaleTabItem);
		subTabInfoAmministrative.add(bilancioTabItem);

		tabInfoAmministrative.add(subTabInfoAmministrative);
		modificaBiblioTabPanel.add(tabInfoAmministrative);
		/* FINE------ TAB INFO AMMINISTRATIVE E VARI SOTTO TAB */

		mainModficaBiblioPanel.add(modificaBiblioTabPanel);
	}


	/**
	 * Toolbar per la gestione delle modifiche della biblioteca, segue il flusso del workflow
	 * 
	 */
	protected ToolBar updateToolbar() {
		// Visualizza solo i pulsanti per i ruoli che hanno questi permessi 
		if (Roles.isUserInRole(Roles.REVISIONE) && UIWorkflow.isRevisione()) {
			// Mette in definitiva
			definitiva.show(); // permette la messa in revisione
		} else {
			definitiva.hide();
		}

		if (Roles.isUserInRole(Roles.REVISIONE) && UIWorkflow.isRevisione()) {
			// Respinge
			respingi.show(); // respinge le modifiche

		} else {
			respingi.hide();
		}

		if (Roles.isUserInRole(Roles.CATALOGAZIONE) && !UIWorkflow.isReadOnly() && !UIWorkflow.isRevisione()) {
			// Ripristina
			ripristina.show(); // permette il ripristino

		} else {
			ripristina.hide();
		}

		if (Roles.isUserInRole(Roles.CATALOGAZIONE) && !UIWorkflow.isReadOnly() && !UIWorkflow.isRevisione()) {
			// Mette in revisione
			revisionabile.show(); // permette la messa in revisione
		} else {
			revisionabile.hide();
		}

		if (Roles.isUserInRole(Roles.CANCELLAZIONE) && !UIWorkflow.isReadOnly() && !UIWorkflow.isRevisione()) {
			// Mette in cancellazione la biblioteca
			cancella.show(); // permette la messa in cancellata
		} else {
			cancella.hide();
		}

		if ((Roles.isUserInRole(Roles.REVISIONE) || Roles.isUserInRole(Roles.CATALOGAZIONE)) && 
				(UIWorkflow.isRevisione() || UIWorkflow.isOccupata(tmpBiblio) || UIWorkflow.isRevisione(tmpBiblio))) {
			// Controlla le differenze
			differenze.show(); // permette la visualizzazione delle differenze
		} else {
			differenze.hide();
		}
		return toolBar;
	}

	private void initToolbarListener() {

		definitivaRevisioneWindow.bind();
		respingiRevisioneWindow.bind();

		definitiva.addListener(Events.OnClick, getDefinitivaButtonSelectionListener());
		respingi.addListener(Events.OnClick, getRespingiButtonSelectionListener());
		ripristina.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					AbiMessageBox.messageConfirmOperationAlertBox("Ripristinare la biblioteca in Definitiva?<br />Tutte le modifiche andranno perdute.", "Ripristino", new Listener<MessageBoxEvent>(){
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button btn = be.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {
								final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
								bibliotecheService.ripristina(tmpBiblio.getIdBiblio(), new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										/* Messaggio ridondante */
//										AbiMessageBox.messageSuccessAlertBox("La biblioteca è stata ripristinata", "Esito ripristino");
//										Dispatcher.get().dispatch(AppEvents.RicercaBiblioGenerica);
										if (ricercaGenerica) {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblio' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
										else {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblioViaCodice' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
									}
									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel ripristinare la biblioteca", "Errore");
									}
								});
							}
						}
					});
				}
			}
		});
		revisionabile.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					AbiMessageBox.messageConfirmOperationAlertBox("Mettere la biblioteca in Revisione?", "In revisione", new Listener<MessageBoxEvent>(){
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button btn = be.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {
								final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
								bibliotecheService.setInRevisione(tmpBiblio.getIdBiblio(), new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										AbiMessageBox.messageSuccessAlertBox("La biblioteca è stata messa in revisione", "Esito in revisione");
//										Dispatcher.get().dispatch(AppEvents.RicercaBiblioGenerica);
										if (ricercaGenerica) {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblio' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
										else {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblioViaCodice' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
									}
									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel mettere in revisione la biblioteca", "Errore");
									}
								});

							}
						}
					});
				}
			}
		});
		cancella.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					AbiMessageBox.messageConfirmOperationAlertBox("Mettere la biblioteca in Cancellata?", "Cancella biblioteca", new Listener<MessageBoxEvent>(){
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button btn = be.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {
								final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
								bibliotecheService.setCancellata(tmpBiblio.getIdBiblio(), new AsyncCallback<Void>() {
									@Override
									public void onSuccess(Void result) {
										AbiMessageBox.messageSuccessAlertBox("La biblioteca è stata messa in cancellata", "Esito cancellazione");
//										Dispatcher.get().dispatch(AppEvents.RicercaBiblioGenerica);
										if (ricercaGenerica) {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblio' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
										else {
											AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
											/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblioViaCodice' in modo tale
											 * da distinguere i due casi di ritorno alla lista di biblioteche */
											event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
											Dispatcher.forwardEvent(event);
										}
									}
									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel mettere in cancellata la biblioteca", "Errore");
									}
								});
							}
						}
					});
				}
			}
		});
		differenze.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					AbiMessageBox.messageConfirmOperationAlertBox("Controllare le differenze?", "Differenze", new Listener<MessageBoxEvent>(){
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Button btn = be.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {
								final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
								bibliotecheService.differenze(tmpBiblio.getIdBiblio(), new AsyncCallback<String>() {
									@Override
									public void onSuccess(String result) {
										differenzeWindow.setDifferenze(result);
										if (result != null && result.length() > 0) {
											differenzeWindow.show();
										}
										else {
											AbiMessageBox.messageSuccessAlertBox("Non sono state riscontrate modifiche.", "Differenze", null);
										}
										
									}
									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel generare le differenze", "Errore");
									}
								});
							}
						}
					});
				}
			}
		});
		lista.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				AbiMessageBox.messageConfirmOperationAlertBox("Tornare alla lista biblioteche?", "Torna alla lista", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							if (ricercaGenerica) {
								AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
								/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblio' in modo tale
								 * da distinguere i due casi di ritorno alla lista di biblioteche */
								event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
								Dispatcher.forwardEvent(event);
							}
							else {
								AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
								/* Passo un parametro all'evento 'FiltraListaBiblioInRicercaBiblioViaCodice' in modo tale
								 * da distinguere i due casi di ritorno alla lista di biblioteche */
								event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
								Dispatcher.forwardEvent(event);
							}
						}
					}
				});
			}
		});
	}

	/**
	 * @return
	 */
	private Listener<BaseEvent> getRespingiButtonSelectionListener() {
		return new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					respingiRevisioneWindow.setBiblioModel(tmpBiblio);
					respingiRevisioneWindow.setRicerca(ricercaGenerica);
					respingiRevisioneWindow.show();
				}
			}
		};
	}

	/**
	 * @return
	 */
	private Listener<BaseEvent> getDefinitivaButtonSelectionListener() {
		return new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (tmpBiblio != null) {
					definitivaRevisioneWindow.setBiblioModel(tmpBiblio);
					definitivaRevisioneWindow.setRicerca(ricercaGenerica);
					definitivaRevisioneWindow.show();
				}
			}
		};
	}
}
