package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.RegioniModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
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
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la ricerca generica delle biblioteche
 *
 */
public class RicercaBiblioGenericaPanel extends FormPanel {
	
	private HashMap<String, Object> keys = new HashMap<String, Object>();

	protected ComboBox<ProvinceModel> provincia;
	protected ComboBox<ComuniModel> comune;
	protected ComboBox<VoceUnicaModel> statoLavorazioneBiblioteca;
	protected ComboBox<VoceUnicaModel> tipFunz;
	protected ComboBox<VoceUnicaModel> tipAmm;
	protected UtentiServiceAsync utentiService = Registry.get(Abi.UTENTI_SERVICE);
	protected ComboBox<UserModel> loginUtente;
	protected CheckBox importate;
	protected UtentiAuthModel utentiAuthModel = UIAuth.getUtentiAuthModel();
	protected Button ricerca;
	private int idRegione;
	private int idProvincia;
	private boolean fromProv = false;
	private boolean fromCom = false;

	private FormData data;
	private FieldSet locGeoFields;
	private FieldSet tipologie_statoFields;
	private FieldSet opzioniFields;
	private TableData formTableData;
	public RicercaBiblioGenericaPanel() {
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);

		formTableData = new TableData();
		formTableData.setWidth("32%");
		formTableData.setMargin(5);
		formTableData.setPadding(10);


		LayoutContainer caratteristicheBiblio = new LayoutContainer();
		caratteristicheBiblio.setLayout(new FlowLayout());
		caratteristicheBiblio.setStyleAttribute("padding", "5px");

		locGeoFields = new FieldSet();
		Utils.setFieldSetProperties(locGeoFields, "Parametri di locazione geografica");

		/* Crea i fields Regione - Provincia - Comune e carica il contenuto corrispondente dal db */
		createLocGeoFields();		
		caratteristicheBiblio.add(locGeoFields);

		tipologie_statoFields = new FieldSet();
		Utils.setFieldSetProperties(tipologie_statoFields, "Tipologie - Stato biblioteca");
		tipologie_statoFields.setExpanded(true);

		/* Crea i fields tipologia amministrativa - tipologia funzionale - stato attuale e carica il
		 * contenuto corrispondente dal db */
		createTipologieStatoFields();		
		caratteristicheBiblio.add(tipologie_statoFields);


		opzioniFields = new FieldSet();
		Utils.setFieldSetProperties(opzioniFields, "Opzioni");
		opzioniFields.setExpanded(true);

		createOpzioni();
		
		caratteristicheBiblio.add(opzioniFields);		

		add(caratteristicheBiblio);

		ricerca = new Button("Ricerca");
		Utils.setStylesButton(ricerca);
		ricerca.setSize(100, 20);
		ricerca.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				keys.clear();

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
				
				if (Roles.isUserInRole("VEDI TUTTE")) {
					if (loginUtente.getValue() != null) {
						keys.put("loginUtenteGestore", loginUtente.getValue().getIdUser());
					}
				}
				if (Roles.isUserInRole("REVISIONE BIBLIOTECA")) {
					if (importate.getValue()) {
						keys.put("importate", "true");
					}
					else {
						keys.put("importate", "false");
					}
				}
				
				AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
				event.setData("parametriRicerca", keys);
				Dispatcher.forwardEvent(event);

			}
		});

		Button reset = new Button("Reset");
		Utils.setStylesButton(reset);
		reset.setSize(100, 20);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				idRegione = 0;
				provincia.clearSelections();
				provincia.clearState();
				provincia.clear();
				idProvincia = 0;
				comune.clearSelections();
				comune.clearState();
				comune.clear();
				tipAmm.clear();
				tipFunz.clear();
				statoLavorazioneBiblioteca.clear();
				if (Roles.isUserInRole("VEDI TUTTE")) {
					loginUtente.clear();
				}
				if (Roles.isUserInRole("REVISIONE BIBLIOTECA")) {
					importate.setValue(false);
				}
			}
		});

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer d = new LayoutContainer(tableLayout);
		d.add(ricerca);
		d.add(reset);

		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setHeight(50);
		c.add(d);

		add(c);	
		FormButtonBinding bindRicerca = new FormButtonBinding(this);
		bindRicerca.addButton(ricerca);

		render(c.getElement());
		render(this.getElement());
	}

	public void createLocGeoFields() {	

		TableLayout locGeoTable= new TableLayout(3);
		locGeoTable.setWidth("100%");
		LayoutContainer locgeo = new LayoutContainer(locGeoTable);



		data = new FormData();
		data.setMargins(new Margins(5));
		FormLayout formLeft = new FormLayout();
		formLeft.setLabelAlign(LabelAlign.TOP);
		LayoutContainer left = new LayoutContainer();
		left.setLayout(formLeft);
		initRegioneProvinciaComune();

		left.add(provincia, data);
		locgeo.add(left, formTableData);

		FormLayout formCenter = new FormLayout();
		formCenter.setLabelAlign(LabelAlign.TOP);

		LayoutContainer center = new LayoutContainer();
		center.setLayout(formCenter);
		// Cambiata per allineamento ricerca regionale
//		center.add(provincia, data);
		center.add(comune, data);
		locgeo.add(center,formTableData);

		FormLayout formRight = new FormLayout();
		formRight.setLabelAlign(LabelAlign.TOP);

		LayoutContainer right = new LayoutContainer();
		right.setLayout(formRight);
		
		// Aggiunta per allineamento ricerca regionale
		right.add(new LayoutContainer(), data);

		locgeo.add(right,formTableData );
		locGeoFields.add(locgeo);

	}

	public void createTipologieStatoFields() {
		TableLayout tipStatoTable= new TableLayout(3);
		tipStatoTable.setWidth("100%");

		LayoutContainer tipStato = new LayoutContainer(tipStatoTable);

		data = new FormData();
		data.setMargins(new Margins(5));
		FormLayout formLeft = new FormLayout();
		formLeft.setLabelAlign(LabelAlign.TOP);

		LayoutContainer left = new LayoutContainer();

		left.setLayout(formLeft);

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

		final	ListStore<VoceUnicaModel> listStoreAmm = new ListStore<VoceUnicaModel>(loaderTipAmm);

		tipAmm = new ComboBox<VoceUnicaModel>();

		tipAmm.setFieldLabel("Tipologia amministrativa");
		tipAmm.setDisplayField("entry");
		tipAmm.setMinListWidth(480);
		tipAmm.setStore(listStoreAmm);
		tipAmm.setFireChangeEventOnSetValue(true);
		tipAmm.setEmptyText("Scegli una tipologia...");
		tipAmm.setLazyRender(false);
		tipAmm.setTriggerAction(TriggerAction.ALL);
		tipAmm.setForceSelection(false);
		tipAmm.setEditable(false);
		tipAmm.setTemplate(getTemplateEmptyRowVoceUnicaModel());

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

		left.add(tipAmm, data);

		tipStato.add(left, formTableData);

		FormLayout formCenter = new FormLayout();
		formCenter.setLabelAlign(LabelAlign.TOP);

		LayoutContainer center = new LayoutContainer();
		center.setLayout(formCenter);

		RpcProxy<List<VoceUnicaModel>> proxyTipologieFunzionali = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				// ID associato tabella tipologie funzionali
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.TIPOLOGIE_FUNZIONALI_INDEX, callback);
			}

		};
		ModelReader tipFunzReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipFunz = new BaseListLoader<ListLoadResult<ModelData>>(proxyTipologieFunzionali, tipFunzReader);

		final		ListStore<VoceUnicaModel> listStoreTipFunz = new ListStore<VoceUnicaModel>(loaderTipFunz);

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
		tipFunz.setMinListWidth(230);
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

		center.add(tipFunz, data);

		tipStato.add(center, formTableData);

		FormLayout formRight = new FormLayout();
		formRight.setLabelAlign(LabelAlign.TOP);

		LayoutContainer right = new LayoutContainer();
		right.setLayout(formRight);

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
		statoLavorazioneBiblioteca.setFieldLabel("Stato di lavorazione");
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

		right.add(statoLavorazioneBiblioteca, data);

		tipStato.add(right, formTableData);

		tipologie_statoFields.add(tipStato);
	}
	
	public void createOpzioni() {
		TableLayout opzioniTable = new TableLayout(3);
		opzioniTable.setWidth("100%");
		LayoutContainer opzioni = new LayoutContainer(opzioniTable);

		data = new FormData();
		data.setMargins(new Margins(5));
		FormLayout formLeft = new FormLayout();
		formLeft.setLabelAlign(LabelAlign.TOP);

		LayoutContainer left = new LayoutContainer();

		left.setLayout(formLeft);
		
		importate = new CheckBox();
		importate.setHideLabel(true);
		importate.setLabelStyle("font-size:14px;");
		importate.setBoxLabel("Importate");

		if (Roles.isUserInRole("VEDI TUTTE") || Roles.isUserInRole(Roles.FORMATO_DI_SCAMBIO)) {
			/* LOGIN UTENTE */
			RpcProxy<PagingLoadResult<UserModel>> utentiProxy = new RpcProxy<PagingLoadResult<UserModel>>() {
	
				@Override
				protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {
					utentiService.getListaUtentiPaginata((ModelData) loadConfig, -1, callback);
				}
			};
	
			ModelReader utentiReader = new ModelReader();
	
			final PagingLoader<PagingLoadResult<UserModel>> utentiLoader = new BasePagingLoader<PagingLoadResult<UserModel>>(
					utentiProxy, utentiReader);
			utentiLoader.setLimit(10);

			final ListStore<UserModel> utentiStore = new ListStore<UserModel>(utentiLoader);
			
			utentiLoader.addListener(Loader.Load, new Listener<LoadEvent>() {
				@Override
				public void handleEvent(LoadEvent be) {
					if(  be.<ModelData> getConfig().get("query")==null || be.<ModelData> getConfig().get("query").equals("")){
						/*Inserisce una voce bianca al caricamento dello store*/
						DeferredCommand.addCommand(new Command() {
							@Override
							public void execute() {
								UserModel emptyUser = new UserModel();
								emptyUser.setUserName("");
								utentiStore.insert(emptyUser, 0);
							}
						});
					}
				}
			});
			
			loginUtente = new ComboBox<UserModel>();
			loginUtente.setFieldLabel("Gestita da");
			loginUtente.setWidth(200);
			loginUtente.setMinListWidth(400);
			loginUtente.setDisplayField("login");
			loginUtente.setFireChangeEventOnSetValue(true);
			loginUtente.setEmptyText("Scegliere Login Utente");
			loginUtente.setForceSelection(true);
			loginUtente.setLazyRender(false);
			loginUtente.setTriggerAction(TriggerAction.ALL);
			loginUtente.setAllowBlank(true);
			loginUtente.setEditable(true);
			loginUtente.setSimpleTemplate("{login}");
			loginUtente.setTypeAhead(false);
			loginUtente.setMinChars(1);
			loginUtente.setPageSize(10);
			loginUtente.setStore(utentiStore);
			loginUtente.setTemplate(getTemplateEmptyRowUserModel());
			
			left.add(loginUtente, data);
		}
		else {
			left.add(importate, data);
			importate.hide();
			if (Roles.isUserInRole(Roles.FORMATO_DI_SCAMBIO) || Roles.isUserInRole("REVISIONE BIBLIOTECA")) {
				importate.show();
			}
		}

		opzioni.add(left, formTableData);

		FormLayout formCenter = new FormLayout();

		LayoutContainer center = new LayoutContainer(new FlowLayout());
		center.setLayout(formCenter);

		if (Roles.isUserInRole("VEDI TUTTE") || Roles.isUserInRole("REVISIONE BIBLIOTECA")) {
			center.add(importate);
		}
		opzioni.add(center,formTableData);
		
		FormLayout formRight = new FormLayout();
		formRight.setLabelAlign(LabelAlign.TOP);

		LayoutContainer right = new LayoutContainer();
		right.setLayout(formRight);

		opzioni.add(right, formTableData);
		opzioniFields.add(opzioni);
		
		if((importate!=null && !importate.isVisible()) && (loginUtente==null)){
			opzioniFields.hide();
		}
	}

	public void resetAll() {
		keys.clear();
		provincia.clearSelections();
		provincia.clearState();
		provincia.clear();
		comune.clearSelections();
		comune.clearState();
		comune.clear();
		tipAmm.clear();
		tipFunz.clear();
		statoLavorazioneBiblioteca.clear();
		if (Roles.isUserInRole("VEDI TUTTE")) {
			loginUtente.clear();
		}
		if (Roles.isUserInRole("REVISIONE BIBLIOTECA")) {
			importate.setValue(false);
		}
	}

	public void initRegioneProvinciaComune() {
		final LocationServiceAsync locationService = Registry.get(Abi.LOCATION_SERVICE);
				
		/* REGIONI */
		RpcProxy<List<RegioniModel>> proxyRegioni = new RpcProxy<List<RegioniModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<RegioniModel>> callback) {
				locationService.getRegioni((ModelData) loadConfig, callback);
			}

		};
		ModelReader dtoReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderRegioni = new BaseListLoader<ListLoadResult<ModelData>>(proxyRegioni, dtoReader);

		final ListStore<RegioniModel> listStoreRegioni = new ListStore<RegioniModel>(loaderRegioni);

		loaderRegioni.addListener(Loader.Load, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				if(  be.<ModelData> getConfig().get("query")==null || be.<ModelData> getConfig().get("query").equals("")){
					/*Inserisce una voce bianca al caricamento dello store*/
					DeferredCommand.addCommand(new Command() {

						@Override
						public void execute() {
							RegioniModel emptyRegione = new RegioniModel();
							emptyRegione.setDenominazione("");
							emptyRegione.setIdRegione(-1);
							listStoreRegioni.insert(emptyRegione, 0);
						}
					});
				}
			}
		});
		/* End REGIONI */

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
		/* End COMUNI */
		
		loaderRegioni.load();
		
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
					if(se.getSelectedItem().getIdProvincia()!=-1){
						idProvincia = se.getSelectedItem().getIdProvincia();

						if (!fromCom) {
							comuniLoader.load();
						}
						
					} else {
						provincia.setValue(null);
						idProvincia=0;
						comune.clearSelections();
						comune.clearState();
						comune.clear();
						
						fromCom = false;
					}
					
				} else {
					provincia.setValue(null);
					idProvincia=0;
					comune.clearSelections();
					comune.clearState();
					comune.clear();
					
					fromCom = false;
				}
			}

		});

		provincia.addListener(Events.TriggerClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				idProvincia=0;
				comune.clearSelections();
				comune.clearState();
				comune.clear();
				
				fromCom = false;
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
					if(se.getSelectedItem().getIdComune()!=-1){
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
			}
		});
		
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
	
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		Utils.addKeyListenerForEnter(ricerca,this); 
	}

	
}
