package it.inera.abi.gxt.client.mvc.view.center.report.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * Classe generica che permette di impostare i parametri per le ricerche 
 * nel formato di scambio
 *
 */
public class GestioneReportFormBasePanel extends ContentPanel {
	
	protected HashMap<String, Object> keys = new HashMap<String, Object>();
	protected HashMap<String, Object> labels = new HashMap<String, Object>();
	protected RadioButton prov;
	protected ListView<ProvinceModel> province;
	protected RadioButton com;
	protected ComboBox<ComuniModel> comune;
	protected ListView<VoceUnicaModel> tipAmministrativa;
	protected ListView<VoceUnicaModel> tipFunzionale;
	protected ListView<VoceUnicaModel> destSociale;
	protected ListView<VoceUnicaModel> statoCatalogazione;
	protected final ComboBox<PartecipaCataloghiCollettiviModel> catalogoCollettivo;
	protected final ListStore<PartecipaCataloghiCollettiviModel> selectedStore;
	protected final ListView<PartecipaCataloghiCollettiviModel> catalogoCollettivoSelected;
	protected final ComboBox<VoceUnicaModel> sistemaBiblioteche;
	protected final ListStore<VoceUnicaModel> sistemaBibliotecheSelectedStore;
	protected final ListView<VoceUnicaModel> sistemaBibliotecheSelected;
	protected final ComboBox<SpecializzazioneModel> codiceDewey;
	protected final ListStore<SpecializzazioneModel> codiceDeweySelectedStore;
	protected final ListView<SpecializzazioneModel> codiceDeweySelected;
	protected RadioButton depositoLegaleNS;
	protected RadioButton depositoLegaleSI;
	protected RadioButton depositoLegaleNO;
	protected final ListView<VoceUnicaModel> tipDeposito;
	protected RadioButton edificioMonumentaleNS;
	protected RadioButton edificioMonumentaleSI;
	protected RadioButton edificioMonumentaleNO;
	protected RadioButton bibliotecheCorrelateNS;
	protected RadioButton bibliotecheCorrelateSI;
	protected RadioButton bibliotecheCorrelateNO;
	protected TextField denominazioneFondoField;
	protected final SimpleComboBox<String> tipoRicercaDenominazione;
	protected TextField descrizioneFondoField;
	protected final SimpleComboBox<String> tipoRicercaDescrizione;
	protected ComboBox<PatrimonioSpecializzazioneModel> patrimonioLibrario;
	protected final ListStore<PatrimonioSpecializzazioneModel> patrimonioLibrarioSelectedStore;
	protected final ListView<PatrimonioSpecializzazioneModel> patrimonioLibrarioSelected;
	protected CheckBox prestitoLocale;
	protected CheckBox prestitoNazionale;
	protected CheckBox prestitoInterNazionale;
	protected DateField dalGiorno;
	protected DateField alGiorno;
	protected ListView<UserModel> utenteUltimaModificaListView;
	
	private FormData data;
	protected FieldSet locGeoFields;
	protected FieldSet tipdeststatFields;
	protected FieldSet catSistDeweyFields;
	protected FieldSet fondiAltroFields;
	protected FieldSet dataaggiornutenteFields;
	
	public GestioneReportFormBasePanel() {
		
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);
	
		LayoutContainer caratteristicheBiblio = new LayoutContainer();
		caratteristicheBiblio.setLayout(new FlowLayout());
		caratteristicheBiblio.setStyleAttribute("padding", "5px");
		
		locGeoFields = new FieldSet();
		Utils.setFieldSetProperties(locGeoFields, "Parametri di locazione geografica");
		
		/* Crea i fields Regione - Provincia - Comune e carica il contenuto corrispondente dal db */
		createLocGeoFields();		
		caratteristicheBiblio.add(locGeoFields);
	
		tipdeststatFields = new FieldSet();
		Utils.setFieldSetProperties(tipdeststatFields, "Tipologie - Destinazione sociale - Stato della registrazione");
		tipdeststatFields.setExpanded(false);
		
		
		FormLayout formCaratteristiche = new FormLayout();
		formCaratteristiche.setLabelAlign(LabelAlign.TOP);

		LayoutContainer caratteristicheBiblioFields = new LayoutContainer(new TableLayout(2));

		TableData dataCaratteristiche1 = new TableData();
		dataCaratteristiche1.setWidth("40%");
		dataCaratteristiche1.setMargin(5);
		dataCaratteristiche1.setPadding(10);

		TableData dataCaratteristiche2 = new TableData();
		dataCaratteristiche2.setWidth("60%");
		dataCaratteristiche2.setMargin(5);
		dataCaratteristiche2.setPadding(10);
		
		final TabelleDinamicheServiceAsync tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		ModelReader dtoReader = new ModelReader();
		
		/* TIPOLOGIA AMMINISTRATIVA */
		Text tipAmmLabel = new Text("Tipologia amministrativa:");
		tipAmmLabel.setStyleAttribute("fontSize", "14px");	
		
		RpcProxy<List<VoceUnicaModel>> proxyTipAmm = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX, callback);
				
			}

		};
		

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipAmm = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyTipAmm, dtoReader);

		ListStore<VoceUnicaModel> tipAmministrativaStore = new ListStore<VoceUnicaModel>(loaderTipAmm);
		loaderTipAmm.load();

		tipAmministrativa = new ListView<VoceUnicaModel>();
		tipAmministrativa.setAutoWidth(true);
		tipAmministrativa.setStore(tipAmministrativaStore);
				
		tipAmministrativa.setSimpleTemplate("{entry}");
		tipAmministrativa.setAutoWidth(true);
		tipAmministrativa.setHeight(70);

		caratteristicheBiblioFields.add(tipAmmLabel, dataCaratteristiche1);
		caratteristicheBiblioFields.add(tipAmministrativa, dataCaratteristiche2);
		
		
		/* TIPOLOGIA FUNZIONALE */
		Text tipFunzLabel = new Text("Tipologia funzionale:");
		tipFunzLabel.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<List<VoceUnicaModel>> proxyTipFunz = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI_INDEX, callback);
				
			}

		};
		
		final BaseListLoader<ListLoadResult<ModelData>> loaderTipFunz = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyTipFunz, dtoReader);

		ListStore<VoceUnicaModel> tipFunzionaleStore = new ListStore<VoceUnicaModel>(loaderTipFunz);
		loaderTipFunz.load();
		
		tipFunzionale = new ListView<VoceUnicaModel>();
		tipFunzionale.setAutoWidth(true);
		tipFunzionale.setStore(tipFunzionaleStore);
		

		tipFunzionale.setSimpleTemplate("{entry}");
		tipFunzionale.setAutoWidth(true);
		tipFunzionale.setHeight(70);

		caratteristicheBiblioFields.add(tipFunzLabel, dataCaratteristiche1);
		caratteristicheBiblioFields.add(tipFunzionale, dataCaratteristiche2);
		
		
		/* DESTINAZIONE SOCIALE */
		Text destSoc = new Text("Destinazione sociale:");
		destSoc.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<List<VoceUnicaModel>> proxyDestSoc = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX, callback);
				
			}

		};
		
		final BaseListLoader<ListLoadResult<ModelData>> loaderDestSoc = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyDestSoc, dtoReader);

		ListStore<VoceUnicaModel> destSocialeStore = new ListStore<VoceUnicaModel>(loaderDestSoc);
		loaderDestSoc.load();
		
		destSociale = new ListView<VoceUnicaModel>();
		destSociale.setAutoWidth(true);
		destSociale.setStore(destSocialeStore);

		destSociale.setSimpleTemplate("{entry}");
		destSociale.setAutoWidth(true);
		destSociale.setHeight(70);

		caratteristicheBiblioFields.add(destSoc, dataCaratteristiche1);
		caratteristicheBiblioFields.add(destSociale, dataCaratteristiche2);
		
		
		/* STATO CATALOGAZIONE */
		Text statoCatalog = new Text("Stato registrazione:");
		statoCatalog.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<List<VoceUnicaModel>> proxyStatoCatalogazione = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX, callback);
				
			}

		};
		
		final BaseListLoader<ListLoadResult<ModelData>> loaderStatoCat = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyStatoCatalogazione, dtoReader);

		ListStore<VoceUnicaModel> statoCatalogazioneStore = new ListStore<VoceUnicaModel>(loaderStatoCat);
		loaderStatoCat.load();
		
		statoCatalogazione = new ListView<VoceUnicaModel>();
		statoCatalogazione.setStore(statoCatalogazioneStore);

		statoCatalogazione.setSimpleTemplate("{entry}");
		statoCatalogazione.setAutoWidth(true);
		statoCatalogazione.setHeight(70);

		caratteristicheBiblioFields.add(statoCatalog, dataCaratteristiche1);
		caratteristicheBiblioFields.add(statoCatalogazione, dataCaratteristiche2);
		
		tipdeststatFields.add(caratteristicheBiblioFields);
		caratteristicheBiblio.add(tipdeststatFields);

		
		/* new TableLayout a 3 colonne */
		LayoutContainer caratteristicheBiblioFieldsTreColonne = new LayoutContainer(
				new TableLayout(3));

		//caratteristicheBiblioFieldsTreColonne.setStyleAttribute("padding",
		//		"5px");
		//caratteristicheBiblioFieldsTreColonne.setBorders(true);

		TableData dataCaratteristicheTreColonne1 = new TableData();
		//
		dataCaratteristicheTreColonne1.setWidth("10%");
		dataCaratteristicheTreColonne1.setMargin(5);
		dataCaratteristicheTreColonne1.setPadding(10);

		TableData dataCaratteristicheTreColonne2 = new TableData();
		dataCaratteristicheTreColonne2.setWidth("20%");
		dataCaratteristicheTreColonne2.setMargin(5);
		dataCaratteristicheTreColonne2.setPadding(10);

		TableData dataCaratteristicheTreColonne3 = new TableData();
		dataCaratteristicheTreColonne3.setWidth("20%");
		dataCaratteristicheTreColonne3.setMargin(5);
		dataCaratteristicheTreColonne3.setPadding(10);

		catSistDeweyFields = new FieldSet();
		Utils.setFieldSetProperties(catSistDeweyFields, "Catalogo collettivo - Sistemi biblioteche - Dewey");
		catSistDeweyFields.setExpanded(false);
		
		/* CATALOGO COLLETTIVO */
		Text catalogoColletivoLabel = new Text("Catalogo collettivo:");
		catalogoColletivoLabel.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>> catalogoCollettivoProxy = new RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<PartecipaCataloghiCollettiviModel>> callback) {
				
				tabelleDinamicheService.getAllCataloghiColettivi((ModelData) loadConfig, callback);
				
			}

		};

		ModelReader denominazioneCatalogoReader = new ModelReader();

		final PagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>> denominazioneCatalogoLoader = new BasePagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>>(
				catalogoCollettivoProxy, denominazioneCatalogoReader);
		denominazioneCatalogoLoader.setLimit(10);

		final ListStore<PartecipaCataloghiCollettiviModel> catalogoCollettivoStore = new ListStore<PartecipaCataloghiCollettiviModel>(
				denominazioneCatalogoLoader);
		
		catalogoCollettivo = new ComboBox<PartecipaCataloghiCollettiviModel>();
		catalogoCollettivo.setWidth(400);
		catalogoCollettivo.setDisplayField("denominazioneCatalogo");
		catalogoCollettivo.setFireChangeEventOnSetValue(true);
		catalogoCollettivo.setEmptyText("Descrizione...");
		catalogoCollettivo.setForceSelection(false);
		catalogoCollettivo.setLazyRender(false);
		catalogoCollettivo.setTriggerAction(TriggerAction.ALL);
		catalogoCollettivo.setAllowBlank(true);
		catalogoCollettivo.setEditable(true);
		catalogoCollettivo.setSimpleTemplate("{denominazioneCatalogo}");
		catalogoCollettivo.setTypeAhead(false);
		catalogoCollettivo.setMinChars(1);
		catalogoCollettivo.setPageSize(10);
		catalogoCollettivo.setStore(catalogoCollettivoStore);	
		
		
		LayoutContainer catCollitemsSelected = new LayoutContainer(new FlowLayout());
		catalogoCollettivoSelected = new ListView<PartecipaCataloghiCollettiviModel>();
		selectedStore = new ListStore<PartecipaCataloghiCollettiviModel>();
		catalogoCollettivoSelected.setStore(selectedStore);

		catalogoCollettivo.addSelectionChangedListener(new SelectionChangedListener<PartecipaCataloghiCollettiviModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<PartecipaCataloghiCollettiviModel> se) {
				
				selectedStore.add(se.getSelectedItem());
				catalogoCollettivoSelected.refresh();
								
			}
			
		});
		
		catalogoCollettivoSelected.setSimpleTemplate("{denominazioneCatalogo}");
		catalogoCollettivoSelected.setWidth(210);
		catalogoCollettivoSelected.setHeight(100);
		catalogoCollettivoSelected.setStyleAttribute("marginBottom", "5px");

		Button svuotaCataloghiSelezionati = new Button("Svuota");
		Utils.setStylesButton(svuotaCataloghiSelezionati);

		svuotaCataloghiSelezionati.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {

						if (catalogoCollettivoSelected.getItemCount() > 0) {
						
							catalogoCollettivoSelected.clearState();
							selectedStore.removeAll();
							catalogoCollettivoSelected.refresh();
							
						}
						
					}
				});

		catCollitemsSelected.add(catalogoCollettivoSelected);
		catCollitemsSelected.add(svuotaCataloghiSelezionati);

		caratteristicheBiblioFieldsTreColonne.add(catalogoColletivoLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonne.add(catalogoCollettivo, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonne.add(catCollitemsSelected, dataCaratteristicheTreColonne3);
		

		/* SISTEMA DI BIBLIOTECHE */
		Text sistemaBibliotecheLabel = new Text("Sistema di biblioteche:");
		sistemaBibliotecheLabel.setStyleAttribute("fontSize", "14px");
		
		
		RpcProxy<PagingLoadResult<VoceUnicaModel>> sistemiBibliotecheProxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVociFiltratePerPaginazioneCombobox(CostantiTabelleDinamiche.SISTEMI_RETI_BIBLITOECHE_INDEX, (ModelData) loadConfig, callback);				
				
			}

		};

		ModelReader sistemiBibliotecheReader = new ModelReader();

		final PagingLoader<PagingLoadResult<VoceUnicaModel>> sistemiBibliotecheLoader = new BasePagingLoader<PagingLoadResult<VoceUnicaModel>>(
				sistemiBibliotecheProxy, sistemiBibliotecheReader);
		sistemiBibliotecheLoader.setLimit(10);

		final ListStore<VoceUnicaModel> sistemiBibliotecheStore = new ListStore<VoceUnicaModel>(sistemiBibliotecheLoader);
		

		sistemaBiblioteche = new ComboBox<VoceUnicaModel>();
		sistemaBiblioteche.setWidth(400);
		sistemaBiblioteche.setDisplayField("entry");
		sistemaBiblioteche.setFireChangeEventOnSetValue(true);
		sistemaBiblioteche.setEmptyText("Descrizione...");
		sistemaBiblioteche.setForceSelection(false);
		sistemaBiblioteche.setLazyRender(false);
		sistemaBiblioteche.setTriggerAction(TriggerAction.ALL);
		sistemaBiblioteche.setAllowBlank(true);
		sistemaBiblioteche.setEditable(true);
		sistemaBiblioteche.setSimpleTemplate("{entry}");
		sistemaBiblioteche.setTypeAhead(false);
		sistemaBiblioteche.setMinChars(1);
		sistemaBiblioteche.setPageSize(10);
		sistemaBiblioteche.setStore(sistemiBibliotecheStore);	
		
		
		LayoutContainer sistemaBibliotecheitemsSelected = new LayoutContainer(new FlowLayout());
		sistemaBibliotecheSelected = new ListView<VoceUnicaModel>();
		sistemaBibliotecheSelectedStore = new ListStore<VoceUnicaModel>();
		sistemaBibliotecheSelected.setStore(sistemaBibliotecheSelectedStore);

		sistemaBiblioteche.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				
				sistemaBibliotecheSelectedStore.add(se.getSelectedItem());
				sistemaBibliotecheSelected.refresh();
				
			}
			
		});
		
		sistemaBibliotecheSelected.setSimpleTemplate("{entry}");
		sistemaBibliotecheSelected.setWidth(210);
		sistemaBibliotecheSelected.setHeight(100);
		sistemaBibliotecheSelected.setStyleAttribute("marginBottom", "5px");

		Button svuotasistemaBibliotecheSelezionati = new Button("Svuota");
		Utils.setStylesButton(svuotasistemaBibliotecheSelezionati);

		svuotasistemaBibliotecheSelezionati.addListener(Events.OnMouseUp,
				new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {

						if (sistemaBibliotecheSelected.getItemCount() > 0) {
							
							sistemaBibliotecheSelected.clearState();
							sistemaBibliotecheSelectedStore.removeAll();
							sistemaBibliotecheSelected.refresh();
						
						}
						
					}
				});

		sistemaBibliotecheitemsSelected.add(sistemaBibliotecheSelected);
		sistemaBibliotecheitemsSelected.add(svuotasistemaBibliotecheSelezionati);

		caratteristicheBiblioFieldsTreColonne.add(sistemaBibliotecheLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonne.add(sistemaBiblioteche, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonne.add(sistemaBibliotecheitemsSelected, dataCaratteristicheTreColonne3);
		

		/* CODICE DEWEY */
		Text codiceDeweyLabel = new Text("Codice Dewey:");
		codiceDeweyLabel.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<PagingLoadResult<SpecializzazioneModel>> codiceDeweyProxy = new RpcProxy<PagingLoadResult<SpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback) {
				
				tabelleDinamicheService.getDeweyFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);				
				
			}

		};

		ModelReader codiceDeweyReader = new ModelReader();

		final PagingLoader<PagingLoadResult<SpecializzazioneModel>> codiceDeweyLoader = new BasePagingLoader<PagingLoadResult<SpecializzazioneModel>>(
				codiceDeweyProxy, codiceDeweyReader);
		codiceDeweyLoader.setLimit(10);

		final ListStore<SpecializzazioneModel> codiceDeweyStore = new ListStore<SpecializzazioneModel>(codiceDeweyLoader);
		
		codiceDewey = new ComboBox<SpecializzazioneModel>();
		codiceDewey.setWidth(400);
		codiceDewey.setDisplayField("descrizioneDewey");
		codiceDewey.setFireChangeEventOnSetValue(true);
		codiceDewey.setEmptyText("Descrizione...");
		codiceDewey.setForceSelection(false);
		codiceDewey.setLazyRender(false);
		codiceDewey.setTriggerAction(TriggerAction.ALL);
		codiceDewey.setAllowBlank(true);
		codiceDewey.setEditable(true);
		codiceDewey.setSimpleTemplate("{dewey}&nbsp;{descrizioneDewey}");
		codiceDewey.setTypeAhead(false);
		codiceDewey.setMinChars(1);
		codiceDewey.setPageSize(10);
		codiceDewey.setStore(codiceDeweyStore);

		LayoutContainer codiceDeweyitemsSelected = new LayoutContainer(new FlowLayout());
		
		codiceDeweySelected = new ListView<SpecializzazioneModel>();
		codiceDeweySelected.setWidth(210);
		codiceDeweySelected.setHeight(100);
		codiceDeweySelectedStore = new ListStore<SpecializzazioneModel>();
		codiceDeweySelected.setStore(codiceDeweySelectedStore);
		
		codiceDewey.addSelectionChangedListener(new SelectionChangedListener<SpecializzazioneModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<SpecializzazioneModel> se) {
				
				codiceDeweySelectedStore.add(se.getSelectedItem());
				codiceDeweySelected.refresh();
				
			}
			
		});

		codiceDeweySelected.setSimpleTemplate("{dewey}&nbsp;{descrizioneDewey}");
		codiceDeweySelected.setStyleAttribute("marginBottom", "5px");

		Button svuotacodiceDeweySelezionati = new Button("Svuota");
		Utils.setStylesButton(svuotacodiceDeweySelezionati);

		svuotacodiceDeweySelezionati.addListener(Events.OnMouseUp,
				new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {

						if (codiceDeweySelected.getItemCount() > 0) {
						
							codiceDeweySelected.clearState();
							codiceDeweySelectedStore.removeAll();
							codiceDeweySelected.refresh();
						}
					}
				});

		codiceDeweyitemsSelected.add(codiceDeweySelected);
		codiceDeweyitemsSelected.add(svuotacodiceDeweySelezionati);

		caratteristicheBiblioFieldsTreColonne.add(codiceDeweyLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonne.add(codiceDewey, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonne.add(codiceDeweyitemsSelected, dataCaratteristicheTreColonne3);
		
		catSistDeweyFields.add(caratteristicheBiblioFieldsTreColonne);
		caratteristicheBiblio.add(catSistDeweyFields);
		
		LayoutContainer caratteristicheBiblioFieldsTreColonneAltro = new LayoutContainer(new TableLayout(3));
		fondiAltroFields = new FieldSet();
		Utils.setFieldSetProperties(fondiAltroFields, "Fondi - Prestito - Altro");
		fondiAltroFields.setExpanded(false);

		/* DENOMINAZIONE FONDO */
		Text denominazioneFondoLabel = new Text("Denominazione fondo:");
		denominazioneFondoLabel.setStyleAttribute("fontSize", "14px");
		denominazioneFondoField = new TextField();
		denominazioneFondoField.setWidth(300);

		tipoRicercaDenominazione = new SimpleComboBox<String>();

		tipoRicercaDenominazione.setTriggerAction(TriggerAction.ALL);
		tipoRicercaDenominazione.setEditable(false);
		tipoRicercaDenominazione.setFireChangeEventOnSetValue(true);
		tipoRicercaDenominazione.setWidth(300);
		tipoRicercaDenominazione.add("Tutte le parole");
		tipoRicercaDenominazione.add("Qualsiasi parola");
		tipoRicercaDenominazione.add("Frase esatta");
		tipoRicercaDenominazione.setSimpleValue("Tutte le parole");

		caratteristicheBiblioFieldsTreColonneAltro.add(denominazioneFondoLabel,
				dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(denominazioneFondoField,
				dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(tipoRicercaDenominazione,
				dataCaratteristicheTreColonne3);

		
		/* DESCRIZIONE FONDO */
		Text descrizioneFondoLabel = new Text("Descrizione fondo:");
		descrizioneFondoLabel.setStyleAttribute("fontSize", "14px");
		descrizioneFondoField = new TextField();
		descrizioneFondoField.setWidth(300);

		tipoRicercaDescrizione = new SimpleComboBox<String>();

		tipoRicercaDescrizione.setTriggerAction(TriggerAction.ALL);
		tipoRicercaDescrizione.setEditable(false);
		tipoRicercaDescrizione.setFireChangeEventOnSetValue(true);
		tipoRicercaDescrizione.setWidth(300);
		tipoRicercaDescrizione.add("Tutte le parole");
		tipoRicercaDescrizione.add("Qualsiasi parola");
		tipoRicercaDescrizione.add("Frase esatta");
		tipoRicercaDescrizione.setSimpleValue("Tutte le parole");

		caratteristicheBiblioFieldsTreColonneAltro.add(descrizioneFondoLabel,
				dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(descrizioneFondoField,
				dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(tipoRicercaDescrizione,
				dataCaratteristicheTreColonne3);
		/**/
		/* PATRIMONIO LIBRARIO */
		Text patrimonioLibrarioLabel = new Text("Patrimonio librario:");
		patrimonioLibrarioLabel.setStyleAttribute("fontSize", "14px");
		
		RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>> patrimonioProxy = new RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<PatrimonioSpecializzazioneModel>> callback) {
				
				tabelleDinamicheService.getPatrimonioLibrarioPaginatoClassificatoPerCategorie((ModelData) loadConfig, callback);			
				
			}

		};

		ModelReader patrimoniReader = new ModelReader();

		final PagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>> patrimoniLoader = new BasePagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>>(
				patrimonioProxy, patrimoniReader);
		patrimoniLoader.setLimit(10);

		final ListStore<PatrimonioSpecializzazioneModel> patrimonioLibrarioStore = new ListStore<PatrimonioSpecializzazioneModel>(patrimoniLoader);
		
		patrimonioLibrario = new ComboBox<PatrimonioSpecializzazioneModel>();
		patrimonioLibrario.setWidth(300);
		patrimonioLibrario.setDisplayField("denominazioneMateriale");
		patrimonioLibrario.setFireChangeEventOnSetValue(true);
		patrimonioLibrario.setEmptyText("Descrizione...");
		patrimonioLibrario.setForceSelection(false);
		patrimonioLibrario.setLazyRender(false);
		patrimonioLibrario.setTriggerAction(TriggerAction.ALL);
		patrimonioLibrario.setAllowBlank(true);
		patrimonioLibrario.setEditable(true);
		patrimonioLibrario.setSimpleTemplate(PatrimonioSpecializzazioneModel.getTemplateMateriali());
		patrimonioLibrario.setTypeAhead(false);
		patrimonioLibrario.setMinChars(1);
		patrimonioLibrario.setPageSize(10);
		patrimonioLibrario.setStore(patrimonioLibrarioStore);

		LayoutContainer patrimonioLibrarioitemsSelected = new LayoutContainer(new FlowLayout());
		
		patrimonioLibrarioSelected = new ListView<PatrimonioSpecializzazioneModel>();
		patrimonioLibrarioSelectedStore = new ListStore<PatrimonioSpecializzazioneModel>();
		patrimonioLibrarioSelected.setStore(patrimonioLibrarioSelectedStore);

		patrimonioLibrario.addSelectionChangedListener(new SelectionChangedListener<PatrimonioSpecializzazioneModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<PatrimonioSpecializzazioneModel> se) {
			
				patrimonioLibrarioSelectedStore.add(se.getSelectedItem());
				patrimonioLibrarioSelected.refresh();				
				
			}
		});
		
		patrimonioLibrarioSelected.setSimpleTemplate("{denominazioneMateriale}");
		patrimonioLibrarioSelected.setHeight(100);
		patrimonioLibrarioSelected.setWidth(210);
		patrimonioLibrarioSelected.setAutoWidth(true);
		patrimonioLibrarioSelected.setStyleAttribute("marginBottom", "5px");

		Button svuotapatrimonioLibrarioSelezionati = new Button("Svuota");
		Utils.setStylesButton(svuotapatrimonioLibrarioSelezionati);

		svuotapatrimonioLibrarioSelezionati.addListener(Events.OnMouseUp,
				new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {

						if (patrimonioLibrarioSelected.getItemCount() > 0) {
						
							patrimonioLibrarioSelected.clearState();
							patrimonioLibrarioSelectedStore.removeAll();
							patrimonioLibrarioSelected.refresh();
							
						}
					}
				});

		patrimonioLibrarioitemsSelected.add(patrimonioLibrarioSelected);
		patrimonioLibrarioitemsSelected.add(svuotapatrimonioLibrarioSelezionati);

		caratteristicheBiblioFieldsTreColonneAltro.add(patrimonioLibrarioLabel,
				dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(patrimonioLibrario,
				dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(patrimonioLibrarioitemsSelected,
						dataCaratteristicheTreColonne3);
		
		/* PRESTITO */
		Text prestitoLabel = new Text("Prestito:");
		prestitoLabel.setStyleAttribute("fontSize", "14px");
		LayoutContainer prestitoCheckBox = new LayoutContainer(new FlowLayout());
		// prestitoCheckBox.setAutoWidth(true);
		prestitoCheckBox.setWidth(300);
		prestitoLocale = new CheckBox();
		prestitoLocale.setBoxLabel("Locale");
		
		prestitoNazionale = new CheckBox();
		prestitoNazionale.setBoxLabel("Nazionale");
		
		prestitoInterNazionale = new CheckBox();
		prestitoInterNazionale.setBoxLabel("Internazionale");

		prestitoCheckBox.add(prestitoLocale, data);
		prestitoCheckBox.add(prestitoNazionale, data);
		prestitoCheckBox.add(prestitoInterNazionale, data);

		caratteristicheBiblioFieldsTreColonneAltro.add(prestitoLabel,
				dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(prestitoCheckBox,
				dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(new LayoutContainer(),
				dataCaratteristicheTreColonne3);
		
		/* PRESENZA DEPOSITO LEGALE */
		Text depositoLegaleLabel = new Text("Presenza di deposito legale:");
		depositoLegaleLabel.setStyleAttribute("fontSize", "14px");
		tipDeposito = new ListView<VoceUnicaModel>();

		LayoutContainer depositoLegaleRadioButton = new LayoutContainer(new FlowLayout());
		depositoLegaleRadioButton.setAutoWidth(true);
		
		depositoLegaleNS = new RadioButton("depositoLegale");
		
		depositoLegaleNS.setHTML("<label style=\"font-size:12px;\"> Non specificato <label>");
		depositoLegaleNS.setValue(true);
		depositoLegaleNS.setFocus(true);
		depositoLegaleNS.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tipDeposito.disable();

			}
			
		});

		depositoLegaleSI = new RadioButton("depositoLegale");
		
		depositoLegaleSI.setHTML("<label style=\"font-size:12px;\"> Si <label>");
		depositoLegaleSI.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tipDeposito.enable();

			}
			
		});

		depositoLegaleNO = new RadioButton("depositoLegale");
		
		depositoLegaleNO.setHTML("<label style=\"font-size:12px;\"> No <label>");

		depositoLegaleNO.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				tipDeposito.disable();

			}
			
		});
		
		depositoLegaleRadioButton.add(depositoLegaleNS);
		depositoLegaleRadioButton.add(depositoLegaleSI);
		depositoLegaleRadioButton.add(depositoLegaleNO);

		
		RpcProxy<List<VoceUnicaModel>> tipiDepositoProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.DEPOSITI_LEGALI_TIPOLOGIE_INDEX, callback);
				
			}

		};
		
		final BaseListLoader<ListLoadResult<ModelData>> tipiDepositoLoader = new BaseListLoader<ListLoadResult<ModelData>>(
				tipiDepositoProxy, dtoReader);

		ListStore<VoceUnicaModel> tipiDepositoStore = new ListStore<VoceUnicaModel>(tipiDepositoLoader);
		tipiDepositoLoader.load();
		
		tipDeposito.setStore(tipiDepositoStore);

		tipDeposito.setSimpleTemplate("{entry}");
		tipDeposito.setAutoWidth(true);
		tipDeposito.setHeight(60);
		// impostare selezione di un unico elemento
		tipDeposito.disable();

		caratteristicheBiblioFieldsTreColonneAltro.add(depositoLegaleLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(depositoLegaleRadioButton, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(tipDeposito, dataCaratteristicheTreColonne3);
		
		
		/* EDIFICIO MONUMENTALE */
		Text edificioMonumentaleLabel = new Text("Edificio monumentale:");
		edificioMonumentaleLabel.setStyleAttribute("fontSize", "14px");
		LayoutContainer edificioMonumentaleRadioButton = new LayoutContainer(
				new FlowLayout());
		edificioMonumentaleRadioButton.setAutoWidth(true);
		edificioMonumentaleNS = new RadioButton("edificioMonumentale");

		edificioMonumentaleNS.setHTML("<label style=\"font-size:12px;\"> Non specificato <label>");
		edificioMonumentaleNS.setFocus(true);
		edificioMonumentaleNS.setValue(true);
		
		edificioMonumentaleSI = new RadioButton("edificioMonumentale");
		edificioMonumentaleSI.setHTML("<label style=\"font-size:12px;\"> Si <label>");

		edificioMonumentaleNO = new RadioButton("edificioMonumentale");
		edificioMonumentaleNO.setHTML("<label style=\"font-size:12px;\"> No <label>");
		
		edificioMonumentaleRadioButton.add(edificioMonumentaleNS);
		edificioMonumentaleRadioButton.add(edificioMonumentaleSI);
		edificioMonumentaleRadioButton.add(edificioMonumentaleNO);

		caratteristicheBiblioFieldsTreColonneAltro.add(edificioMonumentaleLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(edificioMonumentaleRadioButton, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(new LayoutContainer(), dataCaratteristicheTreColonne3);
		
		/**/
		/* BIBLIOTECHE CORRELATE */
		Text bibliotecheCorrelateLabel = new Text("Biblioteche correlate:");
		bibliotecheCorrelateLabel.setStyleAttribute("fontSize", "14px");
		LayoutContainer bibliotecheCorrelateRadioButton = new LayoutContainer(
				new FlowLayout());
		bibliotecheCorrelateRadioButton.setAutoWidth(true);
		
		bibliotecheCorrelateNS = new RadioButton("bibliotecheCorrelate");
		bibliotecheCorrelateNS.setFocus(true);
		bibliotecheCorrelateNS.setValue(true);		
		bibliotecheCorrelateNS.setHTML("<label style=\"font-size:12px;\"> Non specificato <label>");
		
		bibliotecheCorrelateSI = new RadioButton("bibliotecheCorrelate");
		bibliotecheCorrelateSI.setHTML("<label style=\"font-size:12px;\"> Si <label>");
		
		bibliotecheCorrelateNO = new RadioButton("bibliotecheCorrelate");
		bibliotecheCorrelateNO.setHTML("<label style=\"font-size:12px;\"> No <label>");

		


		bibliotecheCorrelateRadioButton.add(bibliotecheCorrelateNS);
		bibliotecheCorrelateRadioButton.add(bibliotecheCorrelateSI);
		bibliotecheCorrelateRadioButton.add(bibliotecheCorrelateNO);

		caratteristicheBiblioFieldsTreColonneAltro.add(bibliotecheCorrelateLabel, dataCaratteristicheTreColonne1);
		caratteristicheBiblioFieldsTreColonneAltro.add(bibliotecheCorrelateRadioButton, dataCaratteristicheTreColonne2);
		caratteristicheBiblioFieldsTreColonneAltro.add(new LayoutContainer(), dataCaratteristicheTreColonne3);
		
		/*--*/
		fondiAltroFields.add(caratteristicheBiblioFieldsTreColonneAltro);
		caratteristicheBiblio.add(fondiAltroFields);
		

		/* TABLE LAYOUT 4 COLONNE */
		LayoutContainer caratteristicheBiblioFields5Colonne = new LayoutContainer(
				new TableLayout(5));		
		
		TableData dataCaratteristiche5Colonne1 = new TableData();
		dataCaratteristiche5Colonne1.setWidth("auto");
		dataCaratteristiche5Colonne1.setMargin(5);
		dataCaratteristiche5Colonne1.setPadding(10);

		TableData dataCaratteristiche5Colonne2 = new TableData();
		dataCaratteristiche5Colonne2.setWidth("auto");
		dataCaratteristiche5Colonne2.setMargin(5);
		dataCaratteristiche5Colonne2.setPadding(10);

		TableData dataCaratteristiche5Colonne3 = new TableData();
		dataCaratteristiche5Colonne3.setWidth("auto");
		dataCaratteristiche5Colonne3.setMargin(5);
		dataCaratteristiche5Colonne3.setPadding(10);

		TableData dataCaratteristiche5Colonne4 = new TableData();
		dataCaratteristiche5Colonne4.setWidth("auto");
		dataCaratteristiche5Colonne4.setMargin(5);
		dataCaratteristiche5Colonne4.setPadding(10);
		
		TableData dataCaratteristiche5Colonne5 = new TableData();
		dataCaratteristiche5Colonne5.setWidth("auto");
		dataCaratteristiche5Colonne5.setMargin(5);
		dataCaratteristiche5Colonne5.setPadding(10);
		
		dataaggiornutenteFields = new FieldSet();
		Utils.setFieldSetProperties(dataaggiornutenteFields, "Data aggiornamento"); 
		dataaggiornutenteFields.setExpanded(false);
		/**/

		/* DATA AGGIORNAMENTO */
		Text dataAggiornamentoLabel = new Text("Data aggiornamento:");
		dataAggiornamentoLabel.setStyleAttribute("fontSize", "14px");
		Text daUtenteLabel = new Text("dall'utente:");
		daUtenteLabel.setStyleAttribute("fontSize", "14px");
		
	//	dataAggiornamentoLabel.setWidth(width)
		FormData formData1 = new FormData("-10");
		FormLayout dateLayout = new FormLayout();
		dateLayout.setLabelAlign(LabelAlign.TOP);

		LayoutContainer dalGiornoContainer = new LayoutContainer(dateLayout);
		dalGiorno = new DateField();
		dalGiorno.setFieldLabel("dal giorno");

		dalGiorno.setData("text", "data di inizio range di ricerca");
		dalGiornoContainer.add(dalGiorno, formData1);

		FormData formData2 = new FormData("-10");
		FormLayout dateLayout2 = new FormLayout();
		dateLayout2.setLabelAlign(LabelAlign.TOP);

		LayoutContainer alGiornoContainer = new LayoutContainer(dateLayout2);
		alGiorno = new DateField();
		alGiorno.setFieldLabel("al giorno");

		alGiorno.setData("text", "data di fine range di ricerca");
		alGiornoContainer.add(alGiorno, formData2);
		
		final UtentiServiceAsync utentiService = Registry.get(Abi.UTENTI_SERVICE);
		
		RpcProxy<List<UserModel>> utentiProxy = new RpcProxy<List<UserModel>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<UserModel>> callback) {
			
				utentiService.getListaUtenti(callback);
			}
		};

		ModelReader utentiReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> utentiLoader = new BaseListLoader<ListLoadResult<ModelData>>(utentiProxy, utentiReader);
//		final BaseListLoader<List<UserModel>> utentiLoader = new BasePagingLoader<List<UserModel>>(
//				utentiProxy, utentiReader);
//		utentiLoader.setLimit(10);

		final ListStore<UserModel> utentiStore = new ListStore<UserModel>(utentiLoader);
		utentiLoader.load();

		utenteUltimaModificaListView = new ListView<UserModel>();
		utenteUltimaModificaListView.setStore(utentiStore);
		utenteUltimaModificaListView.setSimpleTemplate("{cognome}&nbsp;{nome}&nbsp;</br>({login}) ");
		utenteUltimaModificaListView.setWidth(180);
		utenteUltimaModificaListView.setHeight(100);
		

		caratteristicheBiblioFields5Colonne.add(dataAggiornamentoLabel, dataCaratteristiche5Colonne1);
		caratteristicheBiblioFields5Colonne.add(dalGiornoContainer, dataCaratteristiche5Colonne2);
		caratteristicheBiblioFields5Colonne.add(alGiornoContainer, dataCaratteristiche5Colonne3);
		caratteristicheBiblioFields5Colonne.add(daUtenteLabel, dataCaratteristiche5Colonne4);
		caratteristicheBiblioFields5Colonne.add(utenteUltimaModificaListView, dataCaratteristiche5Colonne5);

		/*--*/
		dataaggiornutenteFields.add(caratteristicheBiblioFields5Colonne);
		caratteristicheBiblio.add(dataaggiornutenteFields);
		add(caratteristicheBiblio);

	}
	
	public void createLocGeoFields() {
		LayoutContainer locgeo = new LayoutContainer(new TableLayout(2));
		
		TableData locgeoColonna = new TableData();
		
		locgeoColonna.setWidth("33%");
		locgeoColonna.setMargin(5);
		locgeoColonna.setPadding(10);
		
		data = new FormData();
		data.setMargins(new Margins(5));
		FormLayout formLeft = new FormLayout();
		formLeft.setLabelAlign(LabelAlign.TOP);

		LayoutContainer left = new LayoutContainer();

		left.setLayout(formLeft);
		
		final LocationServiceAsync locationService = Registry.get(Abi.LOCATION_SERVICE);
		
		/* PROVINCE */
		prov = new RadioButton("geo");
		prov.setHTML("<label style=\"font-size:14px;\"> Provincia <label>");
		prov.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				province.enable();
				comune.disable();
				comune.setSelection(new ArrayList<ComuniModel>());
				
			}
		});
		
		prov.setValue(true);
		
		RpcProxy<List<ProvinceModel>> proxyProvince = new RpcProxy<List<ProvinceModel>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProvinceModel>> callback) {
		
				locationService.getProvince(null, callback);
			}
		
		};
		
		ModelReader provinceReader = new ModelReader();
	
		final BaseListLoader<ListLoadResult<ModelData>> loaderProv = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyProvince, provinceReader);

		ListStore<ProvinceModel> provStore = new ListStore<ProvinceModel>(loaderProv);
		loaderProv.load();

		province = new ListView<ProvinceModel>();
		province.setAutoWidth(true);
		province.setStore(provStore);				
		province.setSimpleTemplate("{denominazione}");
		province.setAutoWidth(true);
		province.setHeight(70);
		province.enable();
		/*END PROVINCE */
		
		/* COMUNE */
		com = new RadioButton("geo");
		com.setHTML("<label style=\"font-size:14px;\"> Comune <label>");
		com.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				province.disable();
				province.getSelectionModel().setSelection(new ArrayList<ProvinceModel>());
				comune.enable();
				
			}
		});
		
		RpcProxy<PagingLoadResult<ComuniModel>> proxyComuni = new RpcProxy<PagingLoadResult<ComuniModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<ComuniModel>> callback) {
				locationService.getComuniByDenominazioneProvinciaFiltered(null, (ModelData) loadConfig, callback);
			}

		};
		ModelReader comuniReader = new ModelReader();

		PagingLoader<PagingLoadResult<ComuniModel>> loaderComuni = new BasePagingLoader<PagingLoadResult<ComuniModel>>(proxyComuni, comuniReader);
		loaderComuni.setLimit(10);

		ListStore<ComuniModel> listStoreComuni = new ListStore<ComuniModel>(loaderComuni);
		comune = new ComboBox<ComuniModel>();
		comune.setStore(listStoreComuni);
		comune.setDisplayField("denominazione");
		comune.setHideLabel(true);
		comune.setFireChangeEventOnSetValue(true);
		comune.setEmptyText("Scegliere un comune...");
		comune.setForceSelection(true);
		comune.setLazyRender(false);
		comune.setTriggerAction(TriggerAction.ALL);
		comune.setForceSelection(false);
		comune.setEditable(true);
		comune.setAllowBlank(false);
		comune.setMinChars(1);
		comune.setWidth(350);
		comune.setPageSize(10);
		comune.disable();
		/* END COMUNE */
		
		locgeo.add(prov, locgeoColonna);
		locgeo.add(com, locgeoColonna);
		
		locgeo.add(province, locgeoColonna);
		locgeo.add(comune, locgeoColonna);
				
		locGeoFields.add(locgeo);
		
		
	}
	
	public void setSelectedParameters() {
		
		keys.clear();
		labels.clear();
		
		if (prov.getValue() && province.getSelectionModel().getSelectedItems().size() > 0) {
			Integer[] plist = new Integer[province.getSelectionModel().getSelectedItems().size()];
			List<String> pdlist = new ArrayList<String>();
			
			List<ProvinceModel> tmplist = (List<ProvinceModel>) province.getSelectionModel().getSelectedItems();
			
			int i = 0;
			for (ProvinceModel entry : tmplist) {
				plist[i] = entry.getIdProvincia();
				pdlist.add(entry.getDenominazione());
				i++;
			}
			
			keys.put("province", plist);
			labels.put("province", pdlist);
		}
		
		if (comune.getValue() != null) {
			keys.put("comune", comune.getValue().getIdComune());
			labels.put("comune", comune.getValue().getDenominazione());
		}
		
		if (tipAmministrativa.getSelectionModel().getSelectedItems().size() != 0) {
			Integer[] tipa = new Integer[tipAmministrativa.getSelectionModel().getSelectedItems().size()];
			List<String> tipas = new ArrayList<String>();
			List<VoceUnicaModel> tmplist = tipAmministrativa.getSelectionModel().getSelectedItems();
			
			int i = 0;
			
			for (VoceUnicaModel entry : tmplist) {
				tipa[i] = entry.getIdRecord();
				tipas.add(entry.getEntry());
				i++;
				
			}
			
			keys.put("tipAmministrativa", tipa);
			labels.put("tipAmministrativa", tipas);
		}
		
		if (tipFunzionale.getSelectionModel().getSelectedItems().size() != 0) {
			Integer[] tipf = new Integer[tipFunzionale.getSelectionModel().getSelectedItems().size()];
			List<String> tipfs = new ArrayList<String>();
			List<VoceUnicaModel> tmplist = tipFunzionale.getSelectionModel().getSelectedItems();
			
			int i = 0;
			
			for (VoceUnicaModel entry : tmplist) {
				tipf[i] = entry.getIdRecord();
				tipfs.add(entry.getEntry());
				i++;
				
			}
			
			keys.put("tipFunzionale", tipf);
			labels.put("tipFunzionale", tipfs);
		}
		
		if (destSociale.getSelectionModel().getSelectedItems().size() != 0) {
			Integer[] dest = new Integer[destSociale.getSelectionModel().getSelectedItems().size()];
			List<String> dests = new ArrayList<String>();
			List<VoceUnicaModel> tmplist = destSociale.getSelectionModel().getSelectedItems();
			
			int i = 0;
			
			for (VoceUnicaModel entry : tmplist) {
				dest[i] = entry.getIdRecord();
				dests.add(entry.getEntry());
				i++;
				
			}
			
			keys.put("destSociale", dest);
			labels.put("destSociale", dests);
		}
		
		if (statoCatalogazione.getSelectionModel().getSelectedItems().size() != 0) {
			Integer[] statocat = new Integer[statoCatalogazione.getSelectionModel().getSelectedItems().size()];
			List<String> statocats = new ArrayList<String>();
			List<VoceUnicaModel> tmplist = statoCatalogazione.getSelectionModel().getSelectedItems();
			
			int i = 0;
			
			for (VoceUnicaModel entry : tmplist) {
				statocat[i] = entry.getIdRecord();
				statocats.add(entry.getEntry());
				i++;
				
			}
			
			keys.put("statoCatalogazione", statocat);
			labels.put("statoCatalogazione", statocats);
		}
		
		if (catalogoCollettivoSelected.getStore().getModels().size() != 0) {
			Integer[] catColl = new Integer[catalogoCollettivoSelected.getStore().getModels().size()];
			List<String> catColls = new ArrayList<String>();
			List<PartecipaCataloghiCollettiviModel> tmplist = catalogoCollettivoSelected.getStore().getModels();
			
			int i = 0;
			
			for (PartecipaCataloghiCollettiviModel entry : tmplist) {
				catColl[i] = entry.getIdCatalogo();
				catColls.add(entry.getDenominazioneCatalogo());
				i++;
			}
			
			keys.put("cataloghiCollettivi", catColl);
			labels.put("cataloghiCollettivi", catColls);
		}
		
		if (sistemaBibliotecheSelected.getStore().getModels().size() != 0) {
			Integer[] sistBibl = new Integer[sistemaBibliotecheSelected.getStore().getModels().size()];
			List<String> sistBibls = new ArrayList<String>();
			List<VoceUnicaModel> tmplist = sistemaBibliotecheSelected.getStore().getModels();
			
			int i = 0;
			
			for (VoceUnicaModel entry : tmplist) {
				sistBibl[i] = entry.getIdRecord();
				sistBibls.add(entry.getEntry());
				i++;
				
			}
			
			keys.put("sistemiBiblioteche", sistBibl);
			labels.put("sistemiBiblioteche", sistBibls);
			
		}
		
		if (codiceDeweySelected.getStore().getModels().size() != 0) {
			String[] dewBibl = new String[codiceDeweySelected.getStore().getModels().size()];
			List<String> dewBibls = new ArrayList<String>();
			List<SpecializzazioneModel> tmplist = codiceDeweySelected.getStore().getModels();
			
			int i = 0;
			
			for (SpecializzazioneModel entry : tmplist) {
				dewBibl[i] = entry.getDewey();
				dewBibls.add(entry.getDecrizione());
				i++;
				
			}
			
			keys.put("codiciDewey", dewBibl);
			labels.put("codiciDewey", dewBibls);
		}
		
		/* CONTROLLO DEPOSITO LEGALE */
		if (depositoLegaleNS.getValue()) {
			/* Radio Button 'Non specificato' cliccato */
//			keys.remove("depositoLegale");
		}
		else if (depositoLegaleSI.getValue()) {
			/* Radio Button 'Si' cliccato */
			keys.put("depositoLegale", "true");
			labels.put("depositoLegale", "true");
			
			if (tipDeposito.getSelectionModel().getSelectedItems().size() != 0) {
				/* E' stato cliccato 'Si' ed inoltre è stato specificato uno (o più) tipi di deposito legale */
				Integer[] deposTipi = new Integer[tipDeposito.getSelectionModel().getSelectedItems().size()];
				List<String> deposTipis = new ArrayList<String>();
				List<VoceUnicaModel> tmplist = tipDeposito.getSelectionModel().getSelectedItems();
				
				
				int i = 0;
				
				for (VoceUnicaModel entry : tmplist) {
					deposTipi[i] = entry.getIdRecord();
					deposTipis.add(entry.getEntry());
					i++;
				}
				
				keys.put("depositoLegaleTipi", deposTipi);
				labels.put("depositoLegaleTipi", deposTipis);
				
			}
			
			
		}
		else if (depositoLegaleNO.getValue()) {
			/* Radio Button 'No' cliccato */
			keys.put("depositoLegale", "false");
			labels.put("depositoLegale", "false");
		}
		
		/* CONTROLLO EDIFICIO MONUMENTALE */
		if (edificioMonumentaleNS.getValue()) {
			/* Radio Button 'Non specificato' cliccato */
//			keys.remove("edificioMonumentale");
			
		}
		else if (edificioMonumentaleSI.getValue()) {
			/* Radio Button 'Si' cliccato */
			keys.put("edificioMonumentale", "true");
			labels.put("edificioMonumentale", "true");
			
		}
		else if (edificioMonumentaleNO.getValue()) {
			/* Radio Button 'No' cliccato */
			keys.put("edificioMonumentale", "false");
			labels.put("edificioMonumentale", "false");
			
		}
		
		/* CONTROLLO BIBLIOTECHE CORRELATE */
		if (bibliotecheCorrelateNS.getValue()) {
			/* Radio Button 'Non specificato' cliccato */
//			keys.remove("bibliotecheCorrelate");
		}
		else if (bibliotecheCorrelateSI.getValue()) {
			/* Radio Button 'Si' cliccato */
			keys.put("bibliotecheCorrelate", "true");
			labels.put("bibliotecheCorrelate", "true");
		}
		else if (bibliotecheCorrelateNO.getValue()) {
			/* Radio Button 'No' cliccato */
			keys.put("bibliotecheCorrelate", "false");
			labels.put("bibliotecheCorrelate", "false");
		}
		
		
		/* CONTROLLO FONDI SPECIALI: DENOMINAZIONE */
		if (denominazioneFondoField.getValue() != null) {
			keys.put("denominazioneFondo", (String)denominazioneFondoField.getValue());
			labels.put("denominazioneFondo", (String)denominazioneFondoField.getValue());
			
			if (tipoRicercaDenominazione.getValue().getValue().equals("Tutte le parole")) {
				keys.put("tipoRicercaDenominazioneFondo", 1);
				labels.put("tipoRicercaDenominazioneFondo", "Tutte le parole");
			}
			else if (tipoRicercaDenominazione.getValue().getValue().equals("Qualsiasi parola")) {
				keys.put("tipoRicercaDenominazioneFondo", 2);
				labels.put("tipoRicercaDenominazioneFondo", "Qualsiasi parola");
			}
			else if (tipoRicercaDenominazione.getValue().getValue().equals("Frase esatta")) {
				keys.put("tipoRicercaDenominazioneFondo", 3);		
				labels.put("tipoRicercaDenominazioneFondo", "Frase esatta");
			}
			
		}
		
		/* CONTROLLO FONDI SPECIALI: DESCRIZIONE */
		if (descrizioneFondoField.getValue() != null) {
			keys.put("descrizioneFondo", (String)descrizioneFondoField.getValue());
			labels.put("descrizioneFondo", (String)descrizioneFondoField.getValue());
			
			if (tipoRicercaDescrizione.getValue().getValue().equals("Tutte le parole")) {
				keys.put("tipoRicercaDescrizioneFondo", 1);
				labels.put("tipoRicercaDescrizioneFondo", "Tutte le parole");
			}
			else if (tipoRicercaDescrizione.getValue().getValue().equals("Qualsiasi parola")) {
				keys.put("tipoRicercaDescrizioneFondo", 2);
				labels.put("tipoRicercaDescrizioneFondo", "Qualsiasi parola");
			}
			else if (tipoRicercaDescrizione.getValue().getValue().equals("Frase esatta")) {
				keys.put("tipoRicercaDescrizioneFondo", 3);
				labels.put("tipoRicercaDescrizioneFondo", "Frase esatta");
			}
			
		}
		
		/* CONTROLLO PATRIMONIO LIBRARIO */
		if (patrimonioLibrarioSelected.getStore().getModels().size() != 0) {
			HashMap<Integer, Integer> patrlib = new HashMap<Integer, Integer>();
			List<String> patrlibs = new ArrayList<String>();
			List<PatrimonioSpecializzazioneModel> tmplist = patrimonioLibrarioSelected.getStore().getModels();
					
			
			for (PatrimonioSpecializzazioneModel entry : tmplist) {
				patrlib.put(entry.getIdRecord(), entry.getCondition());
				patrlibs.add(entry.getEntry());
				
			}
			
			keys.put("patrimonioLibrario", patrlib);
			labels.put("patrimonioLibrario", patrlibs);
			
		}
		
		/* CONTROLLO PRESTITO (LOCALE, NAZIONALE, INTERNAZIONALE) */
		if (prestitoLocale.getValue()) {
			/* CheckBox 'Locale' spuntata */
			keys.put("prestitoLocale", "true");
			labels.put("prestitoLocale", "true");
		}
		if (prestitoNazionale.getValue()) {
			/* CheckBox 'Nazionale' spuntata */
			keys.put("prestitoNazionale", "true");
			labels.put("prestitoNazionale", "true");
		}
		
		if (prestitoInterNazionale.getValue()) {
			/* CheckBox 'Internazionale' spuntata */
			keys.put("prestitoInternazionale", "true");
			labels.put("prestitoInternazionale", "true");
		}
		
		/* CONTROLLO DAL GIORNO - AL GIORNO */
		if (dalGiorno.getValue() != null && alGiorno.getValue() != null) {
			/* Entrambi i campi data sono stati riempiti */
			List<String> dates = new ArrayList<String>(2);
			dates.add(0, String.valueOf(((Date) dalGiorno.getValue()).getTime()));
			dates.add(1, String.valueOf(((Date) alGiorno.getValue()).getTime()));
			
			keys.put("dateAggiornamento", dates);
			labels.put("dateAggiornamento", dates);
			
		}
		else if (dalGiorno.getValue() != null && alGiorno.getValue() == null) {
			/* E' stato riempito solo il campo 'dal giorno' */
			List<String> dates = new ArrayList<String>(2);
			dates.add(0, String.valueOf(((Date) dalGiorno.getValue()).getTime()));
			dates.add(1, null);
			
			keys.put("dateAggiornamento", dates);
			labels.put("dateAggiornamento", dates);
			
		}
		else if (dalGiorno.getValue() == null && alGiorno.getValue() != null) {
			/* E' stato riempito solo il campo 'al giorno' */
			List<String> dates = new ArrayList<String>(2);
			dates.add(0, null);
			dates.add(1, String.valueOf(((Date) alGiorno.getValue()).getTime()));
			
			keys.put("dateAggiornamento", dates);
			labels.put("dateAggiornamento", dates);
			
		}
		
		/* CONTROLLO UTENTI ULTIMA MODIFICA */
		if (utenteUltimaModificaListView.getSelectionModel().getSelectedItems().size() != 0) {
			Integer[] users = new Integer[utenteUltimaModificaListView.getSelectionModel().getSelectedItems().size()];
			List<String> userss = new ArrayList<String>();
			List<UserModel> tmplist = (List<UserModel>) utenteUltimaModificaListView.getSelectionModel().getSelectedItems();
			
			int i = 0;
			
			for (UserModel entry : tmplist) {
				users[i] = entry.getIdUser();
				userss.add(entry.getUserName());				
				i++;
			}
			
			keys.put("utentiUltimaModifica", users);
			labels.put("utentiUltimaModifica", userss);
			
		}
	}
	
	public void resetForms() {
		/* Parametri locazione geografica */
		prov.setValue(false);
		province.disable();
		province.getSelectionModel().setSelection(new ArrayList<ProvinceModel>());
		com.setValue(false);
		comune.disable();
		comune.setSelection(new ArrayList<ComuniModel>());
		if (locGeoFields != null)
			locGeoFields.setExpanded(true);
		
		/* Parametri tipologie - destinazione sociale - stati catalogazione */
		tipAmministrativa.getSelectionModel().setSelection(new ArrayList<VoceUnicaModel>());
		tipFunzionale.getSelectionModel().setSelection(new ArrayList<VoceUnicaModel>());
		destSociale.getSelectionModel().setSelection(new ArrayList<VoceUnicaModel>());
		statoCatalogazione.getSelectionModel().setSelection(new ArrayList<VoceUnicaModel>());
		if (tipdeststatFields != null)
			tipdeststatFields.setExpanded(false);
		
		/* Parametri catalogo collettivo - sistemi biblioteche - dewey - altro */
		catalogoCollettivo.setSelection(new ArrayList<PartecipaCataloghiCollettiviModel>());
		selectedStore.removeAll();
		catalogoCollettivoSelected.refresh();
		sistemaBiblioteche.setSelection(new ArrayList<VoceUnicaModel>());
		sistemaBibliotecheSelectedStore.removeAll();
		sistemaBibliotecheSelected.refresh();
		codiceDewey.setSelection(new ArrayList<SpecializzazioneModel>());
		codiceDeweySelectedStore.removeAll();
		codiceDeweySelected.refresh();
		depositoLegaleNS.setValue(true);
		depositoLegaleSI.setValue(false);
		depositoLegaleNO.setValue(false);
		tipDeposito.disable();
		tipDeposito.getSelectionModel().setSelection(new ArrayList<VoceUnicaModel>());
		edificioMonumentaleNS.setValue(true);
		edificioMonumentaleSI.setValue(false);
		edificioMonumentaleNO.setValue(false);
		bibliotecheCorrelateNS.setValue(true);
		bibliotecheCorrelateSI.setValue(false);
		bibliotecheCorrelateNO.setValue(false);
		denominazioneFondoField.clear();
		tipoRicercaDenominazione.setSimpleValue("Tutte le parole");
		descrizioneFondoField.clear();
		tipoRicercaDescrizione.setSimpleValue("Tutte le parole");
		patrimonioLibrario.setSelection(new ArrayList<PatrimonioSpecializzazioneModel>());
		patrimonioLibrarioSelectedStore.removeAll();
		patrimonioLibrarioSelected.refresh();
		prestitoLocale.setValue(false);
		prestitoNazionale.setValue(false);
		prestitoInterNazionale.setValue(false);
		if (catSistDeweyFields != null)
			catSistDeweyFields.setExpanded(false);
		
		/* Parametri data aggiornamento - utente ultima modifica */
		dalGiorno.clear();
		alGiorno.clear();
		utenteUltimaModificaListView.getSelectionModel().setSelection(new ArrayList<UserModel>());
		if (dataaggiornutenteFields != null)
			dataaggiornutenteFields.setExpanded(false);
	}	

}
