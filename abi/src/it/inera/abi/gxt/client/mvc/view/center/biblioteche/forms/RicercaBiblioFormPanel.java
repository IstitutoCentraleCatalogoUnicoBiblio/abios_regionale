package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class RicercaBiblioFormPanel extends FormPanel {
	private EventType eventToForwardOnSearch = null;
	private LayoutContainer main;
	private LayoutContainer left;
	private LayoutContainer center;
	private LayoutContainer right;
	private FormLayout layout;
	private ComboBox<ProvinceModel> provincia;
	private ComboBox<ComuniModel> comune;
	private ComboBox<VoceUnicaModel> statoLavorazioneBiblioteca;
	private ComboBox<VoceUnicaModel> tipFunz;
	private ComboBox<VoceUnicaModel> tipAmm;
	private ComboBox<PartecipaCataloghiCollettiviModel> denominazioneCatalogoField;
	protected final SimpleComboBox<String> codiciTipo = new SimpleComboBox<String>();
	protected final TextField<String> codice = new TextField<String>();
	
	protected TabelleDinamicheServiceAsync tdsa = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
	
	private int idRegione;
	private int idProvincia;
	private boolean fromProv = false;
	private boolean fromCom = false;
	private Button ricerca;
	private List<Integer> usersSelected = null;

	public RicercaBiblioFormPanel() {
		super();
		setHeading("Ricerca Biblioteche");
		setIcon(Resources.ICONS.find());
		setStyleAttribute("background-color", "white");
		setLayout(new FitLayout());
		codice.setWidth("25%");
		codice.setFieldLabel("Codice");
		codice.disable();
		setTopToolbar();
		createFields();
		createButtons();
		addKeyListenerForEnter();
	}

	private void setTopToolbar() {
		ToolBar toolbar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolbar.addStyleName("font-weight-style");
		final Button tornaAListaUtenti = new Button("Torna a Lista utenti", Resources.ICONS.group());
		tornaAListaUtenti.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				AppEvent event = new AppEvent(AppEvents.VisualizzaListaUtentiInModifica);
				Dispatcher.forwardEvent(event);
				
			}
		});
		toolbar.add(tornaAListaUtenti);
		
		setTopComponent(toolbar);
	}

	/**
	 * 
	 */
	private void createButtons() {
		
		int buttonWidth = 55;
		
		Button reset = new Button("Reset");
		Utils.setStylesButton(reset);
		reset.setWidth(buttonWidth);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				reset();
//				provincia.disable();
//				comune.disable();	
				
				idRegione = 0;
				provincia.clearSelections();
				provincia.clearState();
				provincia.clear();
				idProvincia = 0;
				comune.clearSelections();
				comune.clearState();
				comune.clear();
				denominazioneCatalogoField.clearSelections();
				denominazioneCatalogoField.clearState();
				denominazioneCatalogoField.clear();
				codiciTipo.setRawValue("");
				codice.clearState();
				codice.clear();
				codice.disable();
			}
		});


		ricerca = new Button("Ricerca");
		Utils.setStylesButton(ricerca);
		ricerca.setWidth(buttonWidth);
		ricerca.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				HashMap<String, Object> keys = new HashMap<String, Object>();

				if (provincia.getValue() != null) {
					keys.put("provincia", provincia.getValue().getIdProvincia());
				}
				if (comune.getValue() != null) {
					keys.put("comune", comune.getValue().getIdComune());
				}

				if (tipAmm.getValue() != null) {
					keys.put("tipologiaAmministrativa", tipAmm.getValue().getIdRecord());
				}
				if (tipFunz.getValue() != null) {
					keys.put("tipologiaFunzionale", tipFunz.getValue().getIdRecord());
				}

				if (statoLavorazioneBiblioteca.getValue() != null) {
					keys.put("statoWorkflow", statoLavorazioneBiblioteca.getValue().getIdRecord());
				}
				if (denominazioneCatalogoField.getValue() != null) {
					keys.put("cataloghiCollettivi", denominazioneCatalogoField.getValue().getIdCatalogo());
				}
				if (codiciTipo.getValue() != null && codice.getValue() != null) {
					if (codiciTipo.getValue().getValue().equalsIgnoreCase("Codice ABI (PRxxxx)")) {
						keys.put("codiciTipo", "ABI");
					}
					else {
						keys.put("codiciTipo", codiciTipo.getValue().getValue());
					}
					keys.put("codice", codice.getValue());
				}


				AppEvent event = new AppEvent(eventToForwardOnSearch);
				event.setData("parametriRicerca", keys);
				if (eventToForwardOnSearch == AppEvents.FiltraListaBiblioInAssegnazioneUtentiBiblio)
					event.setData("idUsers", usersSelected);
				Dispatcher.forwardEvent(event);
			}
			
		});		
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer d = new LayoutContainer(tableLayout);
//		d.setStyleAttribute("margin-left", "45%");
		d.setHeight(50);
		d.add(ricerca);
		d.add(reset);
		
		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setHeight(50);
		c.add(d);
//		setBottomComponent(c);
//		setButtonAlign(HorizontalAlignment.CENTER);
//		c.add(d);
//		wrapperMain.add(c);	
//		main.add(new LayoutContainer(),left);
		center.add(c);
		FormButtonBinding binding = new FormButtonBinding(this);
		binding.addButton(ricerca);
//		binding.addButton(reset);
		
	}

	public void setForwardEventType(EventType evt) {
		this.eventToForwardOnSearch = evt;
	}

	public void createFields() {
		
		TableLayout t = new TableLayout(3);

		main = new LayoutContainer();
		main.setWidth(700);
		main.setStyleAttribute("margin-left", "10%");
		main.setStyleAttribute("margin-right", "10%");
		main.setLayout(t);

		TableData dataCaratteristicheTreColonne1 = new TableData();
		//
		dataCaratteristicheTreColonne1.setWidth("30%");
		dataCaratteristicheTreColonne1.setMargin(5);
		dataCaratteristicheTreColonne1.setPadding(10);
		dataCaratteristicheTreColonne1.setVerticalAlign(VerticalAlignment.TOP);

		left = new LayoutContainer();
		left.setStyleAttribute("margin-top", "0");
		layout = new FormLayout();
		layout.setDefaultWidth(180);
		layout.setLabelAlign(LabelAlign.TOP);
		left.setLayout(layout);

		initRegioneProvinciaComune();
		
		
		TableData	dataCaratteristicheTreColonne2 = new TableData();

		dataCaratteristicheTreColonne2.setWidth("30%");
		dataCaratteristicheTreColonne2.setMargin(5);
		dataCaratteristicheTreColonne2.setPadding(10);
		dataCaratteristicheTreColonne2.setVerticalAlign(VerticalAlignment.TOP);
		center = new LayoutContainer();
		center.setStyleAttribute("margin-top", "0");
		layout = new FormLayout();
		layout.setDefaultWidth(180);
		layout.setLabelAlign(LabelAlign.TOP);
		center.setLayout(layout);

		TableData dataCaratteristicheTreColonne3 = new TableData();

		dataCaratteristicheTreColonne3.setWidth("30%");
		dataCaratteristicheTreColonne3.setMargin(5);
		dataCaratteristicheTreColonne3.setPadding(10);
		dataCaratteristicheTreColonne3.setVerticalAlign(VerticalAlignment.TOP);

		right = new LayoutContainer();
		right.setStyleAttribute("margin-top", "42");
		layout = new FormLayout();
		layout.setDefaultWidth(180);

		layout.setLabelAlign(LabelAlign.TOP);
		right.setLayout(layout);

		final TabelleDinamicheServiceAsync tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

		RpcProxy<List<VoceUnicaModel>> proxyTipologieAmministrative = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.ENTI_TIPOLOGIE_AMMINISTRATIVE_INDEX, callback);
			}

		};
		ModelReader tipAmmReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipAmm = 
			new BaseListLoader<ListLoadResult<ModelData>>(proxyTipologieAmministrative, tipAmmReader);

		final ListStore<VoceUnicaModel> listStoreAmm = new ListStore<VoceUnicaModel>(loaderTipAmm);

		loaderTipAmm.addListener(Loader.Load, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				/*Inserisce una voce bianca al caricamento dello store*/
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						VoceUnicaModel emptyTipAmm = new VoceUnicaModel();
						emptyTipAmm.setEntry("");
						listStoreAmm.insert(emptyTipAmm, 0);
					}
				});

			}
		});
		
		tipAmm = new ComboBox<VoceUnicaModel>();

		tipAmm.setFieldLabel("Tipologia amministrativa");
		tipAmm.setDisplayField("entry");
		tipAmm.setStore(listStoreAmm);
		tipAmm.setFireChangeEventOnSetValue(true);
		tipAmm.setEmptyText("Scegli una tipologia...");
		tipAmm.setLazyRender(false);
		tipAmm.setTriggerAction(TriggerAction.ALL);
		tipAmm.setForceSelection(false);
		tipAmm.setEditable(false);
		tipAmm.setTemplate(getTemplateEmptyRowVoceUnicaModel());
		
		/** CATALOGHI COLLETTIVI **/
		RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>> denominazioneCatalogoProxy = new RpcProxy<PagingLoadResult<PartecipaCataloghiCollettiviModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<PartecipaCataloghiCollettiviModel>> callback) {
				((ModelData)loadConfig).set("idZonaEspansione", 0);
				((ModelData)loadConfig).set("dettaglioZona", null);
				tdsa.getListaCataloghiCollettiviPossibiliPerPaginazioneCombobox((ModelData)loadConfig, callback);
			}
		};

		ModelReader denominazioneCatalogoReader = new ModelReader();

		final PagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>> denominazioneCatalogoLoader = new BasePagingLoader<PagingLoadResult<PartecipaCataloghiCollettiviModel>>(
				denominazioneCatalogoProxy, denominazioneCatalogoReader);
		denominazioneCatalogoLoader.setLimit(10);

		final ListStore<PartecipaCataloghiCollettiviModel> denominazioneCatalogoStore = new ListStore<PartecipaCataloghiCollettiviModel>(
				denominazioneCatalogoLoader);
		
		denominazioneCatalogoLoader.addListener(Loader.Load, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				/*Inserisce una voce bianca al caricamento dello store*/
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						PartecipaCataloghiCollettiviModel emptyCatalogo = new PartecipaCataloghiCollettiviModel();
						emptyCatalogo.setDenominazioneCatalogo("");
						emptyCatalogo.setZonaEspansioneDescr("");
						emptyCatalogo.setDettaglioZona("");
						denominazioneCatalogoStore.insert(emptyCatalogo, 0);
					}
				});
			}
		});
		
		/*
		 * ComboBox paginata con autocompletamento per caricamento lista
		 * denominazioni cataloghi collettivi possibili
		 */
		denominazioneCatalogoField = new ComboBox<PartecipaCataloghiCollettiviModel>();
		denominazioneCatalogoField.setFieldLabel("Cataloghi collettivi");
		denominazioneCatalogoField.setDisplayField("denominazioneCatalogo");
		denominazioneCatalogoField.setMinListWidth(500);
		denominazioneCatalogoField.setFireChangeEventOnSetValue(true);
		denominazioneCatalogoField.setEmptyText("Scegli un catalogo collettivo...");
		denominazioneCatalogoField.setForceSelection(true);
		denominazioneCatalogoField.setLazyRender(false);
		denominazioneCatalogoField.setTriggerAction(TriggerAction.ALL);
//		denominazioneCatalogoField.setAllowBlank(false);
		denominazioneCatalogoField.setEditable(true);		

		denominazioneCatalogoField.setSimpleTemplate(
				"" +
				"<b>{denominazioneCatalogo}</b><br>&nbsp;" +
				"Zona espansione:&nbsp;{entry}<br>&nbsp;" +
				"Zona dettaglio:&nbsp;{zona}" +
		"");
		
		denominazioneCatalogoField.setTypeAhead(false);
		denominazioneCatalogoField.setMinChars(1);
		denominazioneCatalogoField.setPageSize(10);
		denominazioneCatalogoField.setStore(denominazioneCatalogoStore);
		/** FINE CATALOGHI COLLETTIVI **/
		
		
		codiciTipo.setFieldLabel("Tipo codice");
		codiciTipo.setTriggerAction(TriggerAction.ALL);
		codiciTipo.setEditable(false);
		codiciTipo.setFireChangeEventOnSetValue(true);
		codiciTipo.add("<option class=\"x-combo-list-item\" style=\"height:18px;\">&nbsp;</option>");
		codiciTipo.add("Codice ABI (PRxxxx)");
		codiciTipo.add("ACNP");
		codiciTipo.add("CEI");
		codiciTipo.add("CMBS");
		codiciTipo.add("RISM");
		codiciTipo.add("SBN");
		codiciTipo.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				if (se.getSelectedItem() != null && se.getSelectedItem().getValue() != null) {
					String s = (String) se.getSelectedItem().getValue();
					if (!s.equalsIgnoreCase("Codice ABI (PRxxxx)") && !s.equalsIgnoreCase("ACNP")
							&& !s.equalsIgnoreCase("CEI") && !s.equalsIgnoreCase("CMBS")
							&& !s.equalsIgnoreCase("RISM") && !s.equalsIgnoreCase("SBN")) {
						codiciTipo.setRawValue("");
						codiciTipo.clear();
						codice.disable();
					}
					else {
						if (s.equalsIgnoreCase("Codice ABI (PRxxxx)")) {
							codice.setMinLength(6);
							codice.setMaxLength(6);
						}
						else {
							codice.setAllowBlank(false);
							
						}
						codice.enable();
					}
				}
			}
		});
		
		/* Validazione codice ABI */
		codice.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				String res = null;
				
				if (codiciTipo.getValue().getValue().equalsIgnoreCase("Codice ABI (PRxxxx)")) {
					try {
						if (((String) codice.getValue()).length() > 2) {
							Integer i = Integer.valueOf(((String) codice.getValue()).substring(2, ((String) codice.getValue()).length()));
						}
					}
					catch (Exception e) {
						System.out.println(codice.getValue());
						res = "Inserire un numero isil valido";
						return res;
					}
				}
				
				return res;
			}
		});
		left.add(provincia);
		left.add(tipAmm);
		left.add(denominazioneCatalogoField);

		RpcProxy<List<VoceUnicaModel>> proxyTipologieFunzionali = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				// ID associato tabella tipologie funzionali
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI_INDEX, callback);
			}

		};
		ModelReader tipFunzReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipFunz = new BaseListLoader<ListLoadResult<ModelData>>(proxyTipologieFunzionali, tipFunzReader);

		final ListStore<VoceUnicaModel> listStoreTipFunz = new ListStore<VoceUnicaModel>(loaderTipFunz);
		
		loaderTipFunz.addListener(Loader.Load, new Listener<LoadEvent>() {

			@Override
			public void handleEvent(LoadEvent be) {
				/*Inserisce una voce bianca al caricamento dello store*/
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						VoceUnicaModel emptyTipFunz = new VoceUnicaModel();
						emptyTipFunz.setEntry("");
						listStoreTipFunz.insert(emptyTipFunz, 0);
					}
				});
				
			}
		});

		tipFunz = new ComboBox<VoceUnicaModel>();

		tipFunz.setFieldLabel("Tipologia funzionale");
		tipFunz.setDisplayField("entry");
		tipFunz.setStore(listStoreTipFunz);
		tipFunz.setFireChangeEventOnSetValue(true);
		tipFunz.setEmptyText("Scegli una tipologia...");
		tipFunz.setLazyRender(false);
		tipFunz.setTriggerAction(TriggerAction.ALL);
		tipFunz.setForceSelection(false);
		tipFunz.setEditable(false);
		tipFunz.setTemplate(getTemplateEmptyRowVoceUnicaModel());
		loaderTipFunz.load();


		final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		RpcProxy<List<VoceUnicaModel>> proxyStatoCatalogazione = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				// ID associato tabella tipologie funzionali
				bibliotecheService.getStatiWorkFlow(callback);
			}

		};
		ModelReader statoCatalogazioneReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderStatoCatalogazione = 
			new BaseListLoader<ListLoadResult<ModelData>>(proxyStatoCatalogazione, statoCatalogazioneReader);

		final ListStore<VoceUnicaModel> listStoreStatoCatalogazione = new ListStore<VoceUnicaModel>(loaderStatoCatalogazione);

		loaderStatoCatalogazione.addListener(Loader.Load, new Listener<LoadEvent>() {

			@Override
			public void handleEvent(LoadEvent be) {
				/*Inserisce una voce bianca al caricamento dello store*/
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						VoceUnicaModel emptyStatoCatalogazione = new VoceUnicaModel();
						emptyStatoCatalogazione.setEntry("");
						listStoreStatoCatalogazione.insert(emptyStatoCatalogazione, 0);
					}
				});
				
			}
		});
		
		statoLavorazioneBiblioteca = new ComboBox<VoceUnicaModel>();
		statoLavorazioneBiblioteca.setFieldLabel("Stato attuale");
		statoLavorazioneBiblioteca.setTriggerAction(TriggerAction.ALL);
		statoLavorazioneBiblioteca.setDisplayField("entry");
		statoLavorazioneBiblioteca.setStore(listStoreStatoCatalogazione);
		statoLavorazioneBiblioteca.setFireChangeEventOnSetValue(true);
		statoLavorazioneBiblioteca.setEmptyText("Scegli uno stato...");
		statoLavorazioneBiblioteca.setLazyRender(false);
		statoLavorazioneBiblioteca.setForceSelection(false);
		statoLavorazioneBiblioteca.setEditable(false);
		statoLavorazioneBiblioteca.setTemplate(getTemplateEmptyRowVoceUnicaModel());
		loaderStatoCatalogazione.load();
				
//		right.add(comune);
		right.add(statoLavorazioneBiblioteca);
		right.add(codice);

//		center.add(provincia);
		// Aggiunta per allineamento ricerca regionale
		center.add(comune);
		center.add(tipFunz);
		center.add(codiciTipo);
		
		main.add(left, dataCaratteristicheTreColonne1);
		main.add(center, dataCaratteristicheTreColonne2);
		main.add(right, dataCaratteristicheTreColonne3);
//		wrapperMain.add(main);
//		add(wrapperMain);
		add(main);
		
	}
	
	public void setIdUsers(List<Integer> idusers) {
		this.usersSelected = idusers;
	}
	
	public void initRegioneProvinciaComune() {
		final LocationServiceAsync locationService = Registry.get(Abi.LOCATION_SERVICE);

		/* PROVINCE */		
		provincia = new ComboBox<ProvinceModel>();

		RpcProxy<List<ProvinceModel>> proxyProvince = new RpcProxy<List<ProvinceModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProvinceModel>> callback) {
				String query = (String) ((ModelData) loadConfig).get("query");
				if (query != null && query.length() > 0) {
					locationService.getProvince(idRegione, query, callback);
				}
				else {
					locationService.getProvince(idRegione, callback);
				}
			}

		};
		ModelReader provinceReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderprovince = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyProvince, provinceReader);

		final ListStore<ProvinceModel> provinceStore = new ListStore<ProvinceModel>(loaderprovince);		
		/* End PROVINCE */

		/* COMUNI */
		comune = new ComboBox<ComuniModel>();
		
		RpcProxy<PagingLoadResult<ComuniModel>> comuniProxy = new RpcProxy<PagingLoadResult<ComuniModel>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<ComuniModel>> callback) {
				if (idProvincia != 0) {
					locationService.getComuniPaginati(idProvincia, true, (ModelData) loadConfig, callback);
				}
				else {
					locationService.getComuniPaginati(idRegione, false, (ModelData) loadConfig, callback);
				}
			}
		};

		ModelReader comuniReader = new ModelReader();

		final PagingLoader<PagingLoadResult<ComuniModel>> comuniLoader = new BasePagingLoader<PagingLoadResult<ComuniModel>>(
				comuniProxy, comuniReader);
		comuniLoader.setLimit(20);

		final ListStore<ComuniModel> comuniStore = new ListStore<ComuniModel>(comuniLoader);
		/* End COMUNI */
		
		provincia.setFieldLabel("Provincia");
		provincia.setDisplayField("denominazione");
		provincia.setUseQueryCache(false);
		provincia.setStore(provinceStore);
		provincia.setEmptyText("Scegli una provincia...");
		provincia.setLazyRender(false);
		provincia.setTriggerAction(TriggerAction.ALL);
		provincia.setForceSelection(true);
		provincia.setTypeAhead(false);
		provincia.setEditable(true);
		provincia.setMinChars(1);
		provincia.setTemplate(getTemplateEmptyRowLocation());
		
		provincia.addSelectionChangedListener(new SelectionChangedListener<ProvinceModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<ProvinceModel> se) {
				if (se.getSelectedItem() != null) {
					idProvincia = se.getSelectedItem().getIdProvincia();
										
					if (!fromCom) {
						comuniLoader.load();
					}
				}
			}
		});

		provincia.addListener(Events.TriggerClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				comune.clearSelections();
				comune.clearState();
				comune.clear();
				
				fromCom = false;
			}
		});
		
		loaderprovince.addListener(Loader.Load, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				if(  be.<ModelData> getConfig().get("query")==null || be.<ModelData> getConfig().get("query").equals("")){
					/*Inserisce una voce bianca al caricamento dello store*/
					DeferredCommand.addCommand(new Command() {

						@Override
						public void execute() {
							ProvinceModel emptyProvicia = new ProvinceModel();
							emptyProvicia.setDenominazione("");
							emptyProvicia.setIdProvincia(-1);
							provinceStore.insert(emptyProvicia, 0);
						}
					});
				}
			}
		});
		
		comune.setFieldLabel("Comune");
		comune.setWidth(200);
		comune.setMinListWidth(360);
		comune.setDisplayField("denominazione");
		comune.setEmptyText("Scegli un comune...");
		comune.setForceSelection(true);
		comune.setLazyRender(false);
		comune.setTriggerAction(TriggerAction.ALL);
		comune.setEditable(true);
		comune.setSimpleTemplate("{denominazione}");
		comune.setTypeAhead(false);
		comune.setMinChars(1);
		comune.setPageSize(50);
		comune.setStore(comuniStore);
		comune.setUseQueryCache(false);
		comune.setTemplate(getTemplateEmptyRowLocation());
		
		comune.addSelectionChangedListener(new SelectionChangedListener<ComuniModel>() {
			@Override
			public void selectionChanged(final SelectionChangedEvent<ComuniModel> se) {
				if (se.getSelectedItem() != null) {
					if (provincia.getValue() == null) {
						fromCom = true;
						idProvincia = se.getSelectedItem().getIdProvincia();
						locationService.getProvinciaByIdProvincia(se.getSelectedItem().getIdProvincia(), new AsyncCallback<ProvinceModel>() {
							
							@Override
							public void onSuccess(ProvinceModel result) {
								provinceStore.add(result);
								provincia.setValue(result);								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
							}
						});
					}
					
				}
			}
		});
		
		comuniLoader.addListener(Loader.Load, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				if(  be.<ModelData> getConfig().get("query")==null || be.<ModelData> getConfig().get("query").equals("")){
					/*Inserisce una voce bianca al caricamento dello store*/
					DeferredCommand.addCommand(new Command() {
						@Override
						public void execute() {
							ComuniModel emptyComune = new ComuniModel();
							emptyComune.setDenominazione("");
							emptyComune.setIdComune(-1);
							comuniStore.insert(emptyComune, 0);
						}
					});
				}
			}
		});
		
	}
	
	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(ricerca, this);
	}
	
	private native String getTemplateEmptyRowLocation() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{denominazione}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/; 
	
	private native String getTemplateEmptyRowVoceUnicaModel() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{entry}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/; 
	
	private native String getTemplateEmptyRowUserModel() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{login}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/; 
	
//	private native String getTemplateEmptyRowPartecipaCataloghiCollettiviModel() /*-{ 
//	return [ 
//	'<tpl for=".">', 
//	'<div class="x-combo-list-item" style="height:18px;">{denominazioneCatalogo}</div>', 
//	'</tpl>' 
//	].join(""); 
//	}-*/; 

}
