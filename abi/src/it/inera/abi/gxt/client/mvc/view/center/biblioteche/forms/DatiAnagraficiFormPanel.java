package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.ProvinceModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.NumberFieldCustom;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.GoogleGeoLocalizePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaContattiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaDenominazioniAlternativePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaDenominazioniPrecedentiPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.LocationServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
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
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai dati anagrafici della biblioteca (denominazioni, codice isil, località,
 * codice fiscale, ecc...)
 *
 */
public class DatiAnagraficiFormPanel extends ContentPanelForTabItem {

	private int id_biblio;

	private TabelleDinamicheServiceAsync tdsa;

	private TextField<String> denominazioneUfficiale;
	private LayoutContainer codiceIsilTable;
	/*Indirizzo variabili*/
	private TextField<String> viaPiazzaNCivicoField;
	private TextField<String> frazioneField;
	private TextFieldCustom<String> capField;
	private ComboBoxForBeans<ComuniModel> comuneField;
	private ComboBoxForBeans<ProvinceModel> provinciaField;
	private TextField<String> regioneField;
	private TextField<String> statoField; 

	/*Geolocalizzazione variabili*/
	private	GoogleGeoLocalizePanel g;
	private NumberField latField; 
	private NumberField lngField;
	/*Codici variabili*/
	private TextFieldCustom<String> codFiscField;
	private TextFieldCustom<String> pivaField;
	private TextFieldCustom<String> sbnField;
	private TextFieldCustom<String> rismField;
	private TextFieldCustom<String> acnpField;
	private TextFieldCustom<String> ceiField;
	private TextFieldCustom<String> cmbsField;
	/*Stato catalogazione*/
	private ComboBoxForBeans<VoceUnicaModel> statoCatalogazioneField;
	private TextField<String> isilStato;
	private TextFieldCustom<String> isilProvincia;
	private NumberFieldCustom isilNumero;
	/*Liste*/
	private ListaDenominazioniPrecedentiPanel denominazioniPrecedentiPanel;
	private ListaDenominazioniAlternativePanel denominazioniAlternativePanel;
	private	ListaContattiPanel listaContattiPanel;
	//Servizi remoti 
	private LocationServiceAsync locationService;
	private BibliotecheServiceAsync bibliotecheServiceAsync;

	private Button aggiornaIndirizzo = null;
	private Button geolocalizza = null;
	private Button resetIndirizzo = null;

	private Button aggiornaDenominazione = null;
	private Button resetDenominazione = null;

	private Button aggiornaCodFiscPIvaButton = null;
	private Button resetCodFiscPIvaButton = null;

	private Button aggiornaOthersButton;
	private Button resetCodiciOthersButton;

	private Button statoCatalogazioneAggiorna;
	private Button statoCatalogazioneReset;
	/*Labels*/
	private	Text denominazioneUfficialeLabel;
	private	Text viaPiazzaNCivicoLabel;
	private	Text frazioneLabel;
	private	Text capLabel;
	private	Text comuneLabel;
	private	Text provinciaLabel;
	private	Text latLabel;
	private	Text lngLabel;

	private Text codiceFiscLabel;
	private Text pivaLabel;
	private Text sbnLabel;
	private Text rismLabel;
	private Text acnpLabel;
	private Text ceiLabel;
	private Text cmbsLabel;

	private Text codiceIsilLabel;

	private Text statoCatalogazioneLabel;
	
	/*Forms*/
	private FormPanel formDenominazioni;
	private FormPanel formIndirizzo;
	private FormPanel formStatoCatalogazione;
	private FormPanel formCodici;
	private FormPanel formCodiciOthers;
	
	private BaseListLoader<ListLoadResult<ModelData>> loaderprovince;
	
	public DatiAnagraficiFormPanel() {
		super();
		this.bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);
		this.locationService = Registry.get(Abi.LOCATION_SERVICE);
		this.tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
	}

	public void createFormAnagrafica() {
		LayoutContainer denominazioni = new LayoutContainer();
		denominazioni.setStyleAttribute("padding", "5px");

		FieldSet denominazioniSet = new FieldSet();
		Utils.setFieldSetProperties(denominazioniSet, "Denominazioni");
		/* DENOMINAZIONE UFFICIALE */
		formDenominazioni = new FormPanel();
		Utils.setFormStyleProperties(formDenominazioni);

//		formDenominazioni.setWidth(600);


		FormLayout formLayout = new FormLayout();
		formLayout.setLabelAlign(LabelAlign.TOP);

		formDenominazioni.setLayout(formLayout);

		FormData data = new FormData();
		TableLayout tableDenominazione	=new TableLayout(1);
		tableDenominazione.setWidth("700px");
		LayoutContainer contenitoreTableDenominazioneUfficiale = new LayoutContainer(tableDenominazione);

		denominazioneUfficialeLabel = new Text("Denominazione ufficiale:");
		denominazioneUfficialeLabel.setStyleAttribute("fontSize", "14px");

		denominazioneUfficiale = new TextField<String>();
		denominazioneUfficiale.setWidth(700);
		denominazioneUfficiale.setAllowBlank(false);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(denominazioneUfficiale,denominazioneUfficialeLabel);

		aggiornaDenominazione = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaDenominazione);
		
		aggiornaDenominazione.disable();
		aggiornaDenominazione.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				if ((denominazioneUfficiale != null)&& (denominazioneUfficiale.getValue().length() > 0)) {
					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
						public void handleEvent(MessageBoxEvent ce) {
							Button btn = ce.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {

								bibliotecheServiceAsync	.aggiornaDenominazioneUfficiale(denominazioneUfficiale.getValue(),
										biblioteca.getIdBiblio(),new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										aggiornaDenominazione.disable();
										fireReloadBiblioDataEvent();
										Utils.setFontColorStyleBlack(denominazioneUfficialeLabel);
										denominazioneUfficiale.setOriginalValue(denominazioneUfficiale.getValue());
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											aggiornaDenominazione.disable();
										}
									}
								});
							}

						}
					};

					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
				}
			}
		});

		resetDenominazione = new Button("Reset");
		Utils.setStylesButton(resetDenominazione);
		
		resetDenominazione.disable();
		resetDenominazione.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				//				denominazioneUfficiale.setValue(denominazioneUfficiale.getOriginalValue());
				formDenominazioni.reset();
				Utils.setFontColorStyleBlack(denominazioneUfficialeLabel);
			}
		});



		TableData d = new TableData();
		d.setWidth("20%");
		d.setMargin(5);
		d.setPadding(10);

		TableData d2 = new TableData();
		d2.setWidth("40%");
		d2.setMargin(5);
		d2.setPadding(10);

		d2.setWidth("5%");
		d2.setMargin(5);
		d2.setPadding(10);
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(aggiornaDenominazione);
		buttons.add(resetDenominazione);

		contenitoreTableDenominazioneUfficiale.add(denominazioneUfficialeLabel, d);
		contenitoreTableDenominazioneUfficiale.add(denominazioneUfficiale, d2);
		contenitoreTableDenominazioneUfficiale.add(buttons, d);

		FormButtonBinding binding = new FormButtonBinding(formDenominazioni);
		binding.addButton(aggiornaDenominazione);
		binding.addButton(resetDenominazione);

		formDenominazioni.add(contenitoreTableDenominazioneUfficiale);
		denominazioniSet.add(formDenominazioni, data);
		/* FINE---DENOMINAZIONE UFFICIALE */

		/* ALTRE DENOMINAZIONI "PRECEDENTI" */

		denominazioniPrecedentiPanel = new ListaDenominazioniPrecedentiPanel();
		denominazioniPrecedentiPanel.setGrid();

		denominazioniSet.add(denominazioniPrecedentiPanel);

		/* FINE ALTRE DENOMINAZIONI "PRECEDENTI" */

		/* ALTRE DENOMINAZIONI "ALTERNATIVE" */
		denominazioniAlternativePanel = new ListaDenominazioniAlternativePanel();
		denominazioniAlternativePanel.setGrid();
		denominazioniSet.add(denominazioniAlternativePanel);

		denominazioni.add(denominazioniSet);
		add(denominazioni);
		/* FINE ALTRE DENOMINAZIONI "ANCHE" */

		/* FORM INDIRIZZO */
		LayoutContainer indirizzo = new LayoutContainer();
		indirizzo.setStyleAttribute("padding", "5px");

		FieldSet indirizzoSet = new FieldSet();
		Utils.setFieldSetProperties(indirizzoSet, "Indirizzo");

		formIndirizzo = new FormPanel();
		formIndirizzo.setHeaderVisible(false);
		formIndirizzo.setBorders(false);
		formIndirizzo.setBodyBorder(false);
		formIndirizzo.setWidth(750);

		FormLayout formLayoutindirizzo = new FormLayout();
		formLayoutindirizzo.setLabelAlign(LabelAlign.TOP);

		formIndirizzo.setLayout(formLayoutindirizzo);

		LayoutContainer indirizzoTable = new LayoutContainer(new TableLayout(2));

		TableData indirizzoColonna1 = new TableData();
		indirizzoColonna1.setWidth("20%");
		indirizzoColonna1.setMargin(5);
		indirizzoColonna1.setPadding(10);

		TableData indirizzoColonna2 = new TableData();
		indirizzoColonna2.setWidth("80%");
		indirizzoColonna2.setMargin(5);
		indirizzoColonna2.setPadding(10);

		viaPiazzaNCivicoLabel = new Text("Via/Piazza:");
		viaPiazzaNCivicoLabel.setStyleAttribute("fontSize", "14px");
		viaPiazzaNCivicoField = new TextField<String>();
		viaPiazzaNCivicoField.setWidth(400);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(viaPiazzaNCivicoField, viaPiazzaNCivicoLabel);
		indirizzoTable.add(viaPiazzaNCivicoLabel, indirizzoColonna1);
		indirizzoTable.add(viaPiazzaNCivicoField, indirizzoColonna2);

		frazioneLabel = new Text("Frazione:");
		frazioneLabel.setStyleAttribute("fontSize", "14px");
		frazioneField = new TextField<String>();
		frazioneField.setWidth(400);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(frazioneField,frazioneLabel);
		indirizzoTable.add(frazioneLabel, indirizzoColonna1);
		indirizzoTable.add(frazioneField, indirizzoColonna2);

		capLabel = new Text("C.A.P.:");
		capLabel.setStyleAttribute("fontSize", "14px");
		capField = new TextFieldCustom<String>();
		capField.setWidth(80);
		capField.setMaxLength(5);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(capField,capLabel);
		indirizzoTable.add(capLabel, indirizzoColonna1);
		indirizzoTable.add(capField, indirizzoColonna2);

		comuneLabel = new Text("Comune:");
		comuneLabel.setStyleAttribute("fontSize", "14px");
		RpcProxy<PagingLoadResult<ComuniModel>> proxyComuni = new RpcProxy<PagingLoadResult<ComuniModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<ComuniModel>> callback) {
				locationService.getComuniByDenominazioneProvinciaFiltered(provinciaField.getValue().getDenominazione(), (ModelData) loadConfig, callback);
			}

		};
		ModelReader comuniReader = new ModelReader();

		final PagingLoader<PagingLoadResult<ComuniModel>> loaderComuni = new BasePagingLoader<PagingLoadResult<ComuniModel>>(proxyComuni, comuniReader);
		loaderComuni.setLimit(10);

		ListStore<ComuniModel> listStoreComuni = new ListStore<ComuniModel>(loaderComuni);
		comuneField = new ComboBoxForBeans<ComuniModel>();
		comuneField.setStore(listStoreComuni);
		comuneField.setDisplayField("denominazione");
		comuneField.setFieldLabel("denominazione");
		comuneField.setFireChangeEventOnSetValue(true);
		comuneField.setEmptyText("Digita un comune...");
		comuneField.setLazyRender(false);
		comuneField.setTriggerAction(TriggerAction.ALL);
		comuneField.setForceSelection(true);
		comuneField.setEditable(true);
		comuneField.setAllowBlank(false);
		comuneField.setTypeAhead(false);
		comuneField.setMinChars(1);
		comuneField.setWidth(400);
		comuneField.setPageSize(10);

		/**Aggiunge un listener ad un combobox, e se modificato ne cambia il colore
		 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
		 * torna al colore di non modificato (nero)*/

		comuneField.addListener(Events.Change, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if(comuneField.getValue()!=null && comuneField.getOriginalValue()!=null){
					if(comuneField.getValue().getIdComune()==(comuneField.getOriginalValue().getIdComune())){
						Utils.setFontColorStyleBlack(comuneLabel);
					}else{
						Utils.setFontColorStyleRed(comuneLabel);
					}
				}
			}
		});

		indirizzoTable.add(comuneLabel, indirizzoColonna1);
		indirizzoTable.add(comuneField, indirizzoColonna2);

		provinciaLabel = new Text("Provincia:");
		provinciaLabel.setStyleAttribute("fontSize", "14px");

		RpcProxy<List<ProvinceModel>> proxyProvince = new RpcProxy<List<ProvinceModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProvinceModel>> callback) {
				String query = (String) ((ModelData) loadConfig).get("query");
				if (query != null && query.length() > 0) {
					locationService.getProvince(biblioteca.getProvincia().getIdRegione(), query, callback);
					
				} else {
					locationService.getProvince(biblioteca.getProvincia().getIdRegione(), callback);
				}
			}

		};

		ModelReader provinceReader = new ModelReader();

		loaderprovince = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyProvince, provinceReader);

		final ListStore<ProvinceModel> provinceStore = new ListStore<ProvinceModel>(loaderprovince);
		
		provinciaField = new ComboBoxForBeans<ProvinceModel>();
		provinciaField.setStore(provinceStore);
		provinciaField.setDisplayField("denominazione");
		provinciaField.setFieldLabel("Provincia");
		provinciaField.setFireChangeEventOnSetValue(true);
		provinciaField.setEmptyText("Scegli una provincia...");
		provinciaField.setLazyRender(false);
		provinciaField.setTriggerAction(TriggerAction.ALL);
		provinciaField.setForceSelection(true);
		provinciaField.setEditable(true);
		provinciaField.setAllowBlank(false);
		provinciaField.setTypeAhead(false);
		provinciaField.setMinChars(1);
		provinciaField.setWidth(400);

		provinciaField.addSelectionChangedListener(new SelectionChangedListener<ProvinceModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<ProvinceModel> se) {
				if (se.getSelectedItem() != null) {
					loaderComuni.load();
				}
				
			}
		});
		
		/**Aggiunge un listener ad un combobox, e se modificato ne cambia il colore
		 * della textLabel (rosso), se il valore modificato ritorna uguale al valore originale la label
		 * torna al colore di non modificato (nero)*/
		provinciaField.addListener(Events.Change, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (provinciaField.getValue() != null && provinciaField.getOriginalValue() != null) {
					if (provinciaField.getValue().getIdProvincia() == (provinciaField.getOriginalValue().getIdProvincia())) {
						Utils.setFontColorStyleBlack(provinciaLabel);
						
					} else {
						Utils.setFontColorStyleRed(provinciaLabel);
					}
				}
			}
		});
		
		indirizzoTable.add(provinciaLabel, indirizzoColonna1);
		indirizzoTable.add(provinciaField, indirizzoColonna2);

		Text regioneLabel = new Text("Regione:");
		regioneLabel.setStyleAttribute("fontSize", "14px");

		regioneField = new TextField<String>();
		regioneField.setEnabled(false);
		regioneField.setWidth(400);
		regioneField.setReadOnly(true);
		indirizzoTable.add(regioneLabel, indirizzoColonna1);
		indirizzoTable.add(regioneField, indirizzoColonna2);

		Text statoLabel = new Text("Stato:");
		statoLabel.setStyleAttribute("fontSize", "14px");
		statoField = new TextField<String>();
		statoField.setEnabled(false);
		statoField.setWidth(400);
		statoField.setReadOnly(true);
		indirizzoTable.add(statoLabel, indirizzoColonna1);
		indirizzoTable.add(statoField, indirizzoColonna2);

		Text geolocalizzazione = new Text("Geolocalizzazione:");
		geolocalizzazione.setStyleAttribute("fontSize", "14px");

		/* GEOLOCALIZZAZIONE PARAMETRI X-Y */
		final	LayoutContainer geoxy = new LayoutContainer(new TableLayout(5));

		TableData geoLatLng = new TableData();
		geoLatLng.setWidth("5%");
		geoLatLng.setMargin(5);
		geoLatLng.setPadding(10);

		TableData geoField = new TableData();
		geoField.setWidth("20%");
		geoField.setMargin(5);
		geoField.setPadding(10);

		latLabel = new Text("LAT:");
		latLabel.setStyleAttribute("fontSize", "14px");
		geoxy.add(latLabel, geoLatLng);

		latField = new NumberField();
		latField.setEnabled(false);
		latField.setWidth(80);
		latField.setAllowBlank(false);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldDouble(latField, latLabel);
		geoxy.add(latField, geoField);

		lngLabel = new Text("LNG:");
		lngLabel.setStyleAttribute("fontSize", "14px");
		geoxy.add(lngLabel, geoLatLng);

		lngField = new NumberField();
		lngField.setWidth(80);
		lngField.setEnabled(false);
		lngField.setAllowBlank(false);
		geoxy.add(lngField, geoField);
		//GoogleGeoLocalize

		geolocalizza = new Button("Geolocalizza");		
		Utils.setStylesButton(geolocalizza);
		g = new GoogleGeoLocalizePanel();
		g.layout();
		g.hide();
		geolocalizza.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				// Utilizzo i valori del form dell'indirizzo per la visualizzazione della mappa
				g.setViaPiazza(biblioteca.getIndirizzo() != null ? biblioteca.getIndirizzo() : "");
				g.setLocalita(biblioteca.getFrazione() != null ? biblioteca.getFrazione() : "");
				g.setCap(biblioteca.getCap() != null ? biblioteca.getCap() : "");
				g.setCitta(biblioteca.getComune() != null ? biblioteca.getComune().getDenominazione() : "");
				g.setStato(biblioteca.getStato() != null ? biblioteca.getStato() : "");
				if (biblioteca.getGeoX() != null) {
					g.setLatitudine(biblioteca.getGeoX());
					
				} else g.setLatitudine(null);

				if (biblioteca.getGeoY() != null) {
					g.setLongitudine(biblioteca.getGeoY());
					
				} else g.setLongitudine(null);
				
				g.show();g.hide();g.show();
				
			}
		});
		
		g.addListener(Events.Update, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// Via / Piazza 
				if (viaPiazzaNCivicoField.getValue() == null) {
					if (g.getViaPiazza() != null) {
						viaPiazzaNCivicoField.setValue(g.getViaPiazza());
						Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
					}
					
				} else {
					if (g.getViaPiazza() != null) {
						if (!(viaPiazzaNCivicoField.getValue().equalsIgnoreCase(g.getViaPiazza()))) {
							viaPiazzaNCivicoField.setValue(g.getViaPiazza());
							Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
						}
						
					} else {
						viaPiazzaNCivicoField.setValue(g.getViaPiazza());
						Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
					}
				}
				
				// Frazione 
				if (frazioneField.getValue() == null) {
					if (g.getLocalita() != null) {
						frazioneField.setValue(g.getLocalita());
						Utils.setFontColorStyleRed(frazioneLabel);
					}
					
				} else {
					if (g.getLocalita() != null) {
						if (!(frazioneField.getValue().equalsIgnoreCase(g.getLocalita()))) {
							frazioneField.setValue(g.getLocalita());
							Utils.setFontColorStyleRed(frazioneLabel);
						}
						
					} else {
						frazioneField.setValue(g.getLocalita());
						Utils.setFontColorStyleRed(frazioneLabel);
					}						
				}
				
				// CAP 
				if (capField.getValue() == null) {
					if (g.getCap() != null) {
						capField.setValue(g.getCap());
						Utils.setFontColorStyleRed(capLabel);
					}
					
				} else {
					if (g.getCap() != null) {
						if (!(capField.getValue().equalsIgnoreCase(g.getCap()))) {
							capField.setValue(g.getCap());
							Utils.setFontColorStyleRed(capLabel);
						}
						
					} else {
						capField.setValue(g.getCap());
						Utils.setFontColorStyleRed(capLabel);
					}
				}
				
				// LATITUDINE
				if (latField.getValue() == null) {
					if (g.getLatitudine() != null) {
						latField.setValue(g.getLatitudine());
						Utils.setFontColorStyleRed(latLabel);
					}
					
				} else {
					if (g.getLatitudine() != null) {
						if (latField.getValue().doubleValue() != g.getLatitudine().doubleValue()) {
							latField.setValue(g.getLatitudine());
							Utils.setFontColorStyleRed(latLabel);
						}
					}
				}
				
				// LONGITUDINE
				if (lngField.getValue() == null) {
					if (g.getLongitudine() != null) {
						lngField.setValue(g.getLongitudine());
						Utils.setFontColorStyleRed(lngLabel);
					}
					
				} else {
					if (g.getLongitudine() != null) {
						if (lngField.getValue().doubleValue() != g.getLongitudine().doubleValue()) {
							lngField.setValue(g.getLongitudine());
							Utils.setFontColorStyleRed(lngLabel);
						}
					}
				}
				
				geoxy.layout();
			}
		});			
		
		geoxy.add(geolocalizza, geoField);
		// DISABILITATO PER DEBUG: senza rete non funziona
		/*try {
			g = new GoogleGeoLocalizePanel();
			g.hide();
			geolocalizza.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {

					//		Utilizzo i valori del form dell'indirizzo per la visualizzazione della mappa
					g.setViaPiazza(biblioteca.getIndirizzo()!=null?biblioteca.getIndirizzo():"");
					g.setLocalita(biblioteca.getFrazione()!=null?biblioteca.getFrazione():"");
					g.setCap(biblioteca.getCap()!=null?biblioteca.getCap():"");
					g.setCitta(biblioteca.getComune()!=null?biblioteca.getComune().getDenominazione():"");
					g.setStato(biblioteca.getStato()!=null?biblioteca.getStato():"");
					if (biblioteca.getGeoX() != null) {
						g.setLatitudine(biblioteca.getGeoX());
						
					} else {
						g.setLatitudine(null);
					}
					if (biblioteca.getGeoY() != null) {
						g.setLongitudine(biblioteca.getGeoY());
						
					} else {
						g.setLongitudine(null);
					}

					g.show();
				}
			});
			
			g.addListener(Events.Update, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					// Via / Piazza 
					if (viaPiazzaNCivicoField.getValue() == null) {
						if (g.getViaPiazza() != null) {
							viaPiazzaNCivicoField.setValue(g.getViaPiazza());
							Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
						}
						
					} else {
						if (g.getViaPiazza() != null) {
							if (!(viaPiazzaNCivicoField.getValue().equalsIgnoreCase(g.getViaPiazza()))) {
								viaPiazzaNCivicoField.setValue(g.getViaPiazza());
								Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
							}
							
						} else {
							viaPiazzaNCivicoField.setValue(g.getViaPiazza());
							Utils.setFontColorStyleRed(viaPiazzaNCivicoLabel);
						}
					}
					
					// Frazione 
					if (frazioneField.getValue() == null) {
						if (g.getLocalita() != null) {
							frazioneField.setValue(g.getLocalita());
							Utils.setFontColorStyleRed(frazioneLabel);
						}
						
					} else {
						if (g.getLocalita() != null) {
							if (!(frazioneField.getValue().equalsIgnoreCase(g.getLocalita()))) {
								frazioneField.setValue(g.getLocalita());
								Utils.setFontColorStyleRed(frazioneLabel);
							}
							
						} else {
							frazioneField.setValue(g.getLocalita());
							Utils.setFontColorStyleRed(frazioneLabel);
						}						
					}
					
					// CAP 
					if (capField.getValue() == null) {
						if (g.getCap() != null) {
							capField.setValue(g.getCap());
							Utils.setFontColorStyleRed(capLabel);
						}
						
					} else {
						if (g.getCap() != null) {
							if (!(capField.getValue().equalsIgnoreCase(g.getCap()))) {
								capField.setValue(g.getCap());
								Utils.setFontColorStyleRed(capLabel);
							}
							
						} else {
							capField.setValue(g.getCap());
							Utils.setFontColorStyleRed(capLabel);
						}
					}
					
					latField.setValue(g.getLatitudine());
					lngField.setValue(g.getLongitudine());
					Utils.setFontColorStyleRed(latLabel);
					Utils.setFontColorStyleRed(lngLabel);
					geoxy.layout();
				}
			});

			geoxy.add(geolocalizza, geoField);
		} catch (Throwable th) {
			GWT.log(th.toString());
			geoxy.add(new Label("Geolocalizzatore non disponibile."));
		}*/

		/* FINE---GEOLOCALIZZAZIONE PARAMETRI X-Y */
		/*
		 * Se la variabile biblioteca è diverso da null, setto i valori delle
		 * field create
		 */


		indirizzoTable.add(geolocalizzazione, indirizzoColonna1);
		indirizzoTable.add(geoxy, indirizzoColonna2);

		aggiornaIndirizzo = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaIndirizzo);
		aggiornaIndirizzo.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String, Object> params = new HashMap<String, Object>();

							params.put("indirizzo", viaPiazzaNCivicoField.getValue());
							params.put("cap", capField.getValue());
							params.put("frazione", frazioneField.getValue());
							params.put("idComune", comuneField.getValue().getIdComune());
							params.put("latitudine", latField.getValue());
							params.put("longitudine", lngField.getValue());

							bibliotecheServiceAsync.aggiornaIndirizzo(params, biblioteca.getIdBiblio(),	new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
//									MessageBox.info("ESITO UPDATE",	"Aggiornamento voce effettuato con successo!", null).show();
									viaPiazzaNCivicoField.setOriginalValue( viaPiazzaNCivicoField.getValue());
									capField.setOriginalValue( capField.getValue());
									frazioneField.setOriginalValue(frazioneField.getValue());
									comuneField.setOriginalValue(comuneField.getValue());
									if (latField.getValue() != null) latField.setOriginalValue(latField.getValue().doubleValue());
									if (lngField.getValue() != null) lngField.setOriginalValue(lngField.getValue().doubleValue());
									setBlackLabelsFormIndirizzo();
									g.setModified(true);
									fireReloadBiblioDataEvent();
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetIndirizzo = new Button("Reset");
		Utils.setStylesButton(resetIndirizzo);
		resetIndirizzo.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formIndirizzo.reset();
				setBlackLabelsFormIndirizzo();
				g.setModified(false);
			}
		});

		TableLayout tableLayoutButtons = new TableLayout(2);
		tableLayoutButtons.setCellPadding(5);
		LayoutContainer indirizzoButtons = new LayoutContainer(tableLayoutButtons);
		indirizzoButtons.add(aggiornaIndirizzo);
		indirizzoButtons.add(resetIndirizzo);

		indirizzoTable.add(new LayoutContainer(), indirizzoColonna1);
		indirizzoTable.add(indirizzoButtons, indirizzoColonna2);

		FormButtonBinding bindIndirizzo = new FormButtonBinding(formIndirizzo);
		bindIndirizzo.addButton(aggiornaIndirizzo);
//		bindIndirizzo.addButton(resetIndirizzo);

		formIndirizzo.add(indirizzoTable, data);
		indirizzoSet.add(formIndirizzo);

		indirizzo.add(indirizzoSet);
		add(indirizzo);
		/* FINE---FORM INDIRIZZO */

		/* FORM RECAPITI */
		LayoutContainer recapiti = new LayoutContainer();
		recapiti.setStyleAttribute("padding", "5px");

		final FieldSet recapitiSet = new FieldSet();
		Utils.setFieldSetProperties(recapitiSet, "Recapiti");
		// tel fax telex email url
		listaContattiPanel = new ListaContattiPanel();
		listaContattiPanel.setGrid();
		recapitiSet.add(listaContattiPanel);

		recapiti.add(recapitiSet);
		add(recapiti);
		/* FINE---- FORM RECAPITI */

		/* SET CODICI */
		LayoutContainer codici = new LayoutContainer();
		codici.setStyleAttribute("padding", "5px");

		final FieldSet codiciSet = new FieldSet();
		Utils.setFieldSetProperties(codiciSet, "Stato della registrazione - Codici");

		TableData colonnaLabel = new TableData();
		colonnaLabel.setWidth("20%");
		colonnaLabel.setMargin(5);
		colonnaLabel.setPadding(10);

		TableData colonnaCodice = new TableData();
		colonnaCodice.setWidth("10%");
		colonnaCodice.setMargin(5);
		colonnaCodice.setPadding(10);

		TableData colonnaButton = new TableData();
		colonnaButton.setWidth("10%");
		colonnaButton.setMargin(5);
		colonnaButton.setPadding(10);

		/*FORM STATO CATALOGAZIONE*/
		formStatoCatalogazione = new FormPanel();
		formStatoCatalogazione.setWidth(750);
		Utils.setFormStyleProperties(formStatoCatalogazione);

		FormLayout formLayoutStatoCatalogazione = new FormLayout();
		formLayoutStatoCatalogazione.setLabelAlign(LabelAlign.TOP);

		formStatoCatalogazione.setLayout(formLayoutStatoCatalogazione);

		LayoutContainer statoCatalogazioneTable = new LayoutContainer(new TableLayout(6));

		/*********/
		statoCatalogazioneLabel = new Text("Stato registrazione:");
		statoCatalogazioneLabel.setStyleAttribute("fontSize", "14px");
		statoCatalogazioneTable.add(statoCatalogazioneLabel, colonnaLabel);

		RpcProxy<List<VoceUnicaModel>> statoCatalogazioneProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				tdsa.getListaVoci(CostantiTabelleDinamiche.CATALOGAZIONE_STATI_BIBLIOTECHE_INDEX, callback);
			}

		};
		ModelReader statoCatalogazioneReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> statoCatalogazioneLoader = new BaseListLoader<ListLoadResult<ModelData>>(
				statoCatalogazioneProxy, statoCatalogazioneReader);

		final ListStore<VoceUnicaModel> statoCatalogazioneStore = new ListStore<VoceUnicaModel>(statoCatalogazioneLoader);

		statoCatalogazioneLoader.addListener(Loader.Load, new Listener<LoadEvent>() {
			@Override
			public void handleEvent(LoadEvent be) {
				if(be.<ModelData> getConfig().get("query")==null || be.<ModelData> getConfig().get("query").equals("")) {
					/*Inserisce una voce bianca al caricamento dello store*/
					DeferredCommand.addCommand(new Command() {

						@Override
						public void execute() {
							VoceUnicaModel emptyStatoCatalogazione = new VoceUnicaModel();
							emptyStatoCatalogazione.setEntry("Nessuno stato di registrazione");
							emptyStatoCatalogazione.setIdRecord(-1);
							statoCatalogazioneStore.insert(emptyStatoCatalogazione, 0);
						}
					});
				}
			}
		});
		
		statoCatalogazioneField = new ComboBoxForBeans<VoceUnicaModel>();

		statoCatalogazioneField.setFieldLabel("Stato registrazione");
		statoCatalogazioneField.setWidth(250);
		statoCatalogazioneField.setDisplayField("entry");
		statoCatalogazioneField.setStore(statoCatalogazioneStore);
		statoCatalogazioneField.setFireChangeEventOnSetValue(true);
		statoCatalogazioneField.setLazyRender(false);
		statoCatalogazioneField.setTriggerAction(TriggerAction.ALL);
		statoCatalogazioneField.setForceSelection(false);
		statoCatalogazioneField.setEditable(false);
		statoCatalogazioneField.setTemplate(getTemplateEmptyRowLocation());
		statoCatalogazioneLoader.load();
		codiceIsilLabel = new Text("Codice Isil:");

		statoCatalogazioneField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null){
					if(statoCatalogazioneField.getValue()!=null && statoCatalogazioneField.getOriginalValue()!=null) {

						if(statoCatalogazioneField.getValue().getIdRecord()==statoCatalogazioneField.getOriginalValue().getIdRecord()) {
							Utils.setFontColorStyleBlack(statoCatalogazioneLabel);
						}else{
							Utils.setFontColorStyleRed(statoCatalogazioneLabel);
						}
						if(se.getSelectedItem().getIdRecord().intValue()==7){
							showIsilComponents();
						}else{
							hideIsilComponents();
						}
					}else if(statoCatalogazioneField.getValue()!=null && statoCatalogazioneField.getOriginalValue()==null){
						Utils.setFontColorStyleRed(statoCatalogazioneLabel);
						if(se.getSelectedItem().getIdRecord().intValue()==7){
							showIsilComponents();
						}else{
							hideIsilComponents();
						}
					}
				}
			}
		});

		statoCatalogazioneTable.add(new LayoutContainer());
		statoCatalogazioneTable.add(statoCatalogazioneField, colonnaCodice);

		statoCatalogazioneAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(statoCatalogazioneAggiorna);
		statoCatalogazioneAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> params = new HashMap<String, Object>();
							params.put("idBiblioteca", biblioteca.getIdBiblio());
							params.put("idStatoCatalogazione", statoCatalogazioneField.getValue().getIdRecord());
							
							if (statoCatalogazioneField.getValue() != null) {
								if (statoCatalogazioneField.getValue().getIdRecord().intValue() == 7) {
									if (isilProvincia.getValue() != null && isilNumero.getValue() != null) {
										params.put("isilStato", isilStato.getValue());
										params.put("isilProvincia", isilProvincia.getValue());
										params.put("isilNumero", isilNumero.getValue().intValue());
										
									}
								}
							}

							bibliotecheServiceAsync.setStatoCatalogazione(params, new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									if (result.booleanValue()) {
										AbiMessageBox.messageAlertBox("Inserire un codice isil valido!", "ATTENZIONE");
										
									} else {
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										fireReloadBiblioDataEvent();
									}
									
									/*Setto le label di colore nero*/
									Utils.setFontColorStyleBlack(statoCatalogazioneLabel);

									/*Setto gli originalValue() con i nuovi valori*/ 
									statoCatalogazioneField.setOriginalValue(statoCatalogazioneField.getValue());
									
									if (statoCatalogazioneField.getValue() != null && statoCatalogazioneField.getValue().getIdRecord().intValue() == 7) {
										if (!result.booleanValue()) {
											Utils.setFontColorStyleBlack(codiceIsilLabel);
											
											if (isilProvincia.getValue() != null && isilNumero.getValue() != null) {
												isilProvincia.setOriginalValue(isilProvincia.getValue());
												isilNumero.setOriginalValue(isilNumero.getValue());
												
											} else {
												if (biblioteca.getStatoCatalogazioneModel().getIsilProvincia() != null && biblioteca.getStatoCatalogazioneModel().getIsilNumero() != null) {
													isilProvincia.setOriginalValue(biblioteca.getStatoCatalogazioneModel().getIsilProvincia());
													isilNumero.setOriginalValue(biblioteca.getStatoCatalogazioneModel().getIsilNumero());
													
													isilProvincia.setValue(biblioteca.getStatoCatalogazioneModel().getIsilProvincia());
													isilNumero.setValue(biblioteca.getStatoCatalogazioneModel().getIsilNumero());
													
												} else {
													isilProvincia.setOriginalValue(null);
													isilNumero.setOriginalValue(null);
													
													isilProvincia.setValue(null);
													isilNumero.setValue(null);
												}
											}
										}
									}
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
								
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});

		statoCatalogazioneReset = new Button("Reset");
		Utils.setStylesButton(statoCatalogazioneReset);
		statoCatalogazioneReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formStatoCatalogazione.reset();
				Utils.setFontColorStyleBlack(statoCatalogazioneLabel);
				Utils.setFontColorStyleBlack(codiceIsilLabel);
			}
		});

		FormButtonBinding bindStatoCatalogazione = new FormButtonBinding(formStatoCatalogazione);
		bindStatoCatalogazione.addButton(statoCatalogazioneAggiorna);

		statoCatalogazioneTable.add(new LayoutContainer());
		statoCatalogazioneTable.add(statoCatalogazioneAggiorna, colonnaButton);
		statoCatalogazioneTable.add(statoCatalogazioneReset, colonnaButton);

		formStatoCatalogazione.add(statoCatalogazioneTable);

		codiceIsilTable = new LayoutContainer(new TableLayout(4));
		codiceIsilTable.setWidth(400);


		codiceIsilLabel.setStyleAttribute("fontSize", "14px");

		isilStato = new TextField<String>();
		HorizontalPanel isilContainer = new HorizontalPanel();

		isilStato.setWidth("40px");
		isilStato.setStyleAttribute("marginLeft", "28px");
		isilStato.disable();
		isilProvincia = new TextFieldCustom<String>();
		isilProvincia.setWidth("40px");
		isilProvincia.setMaxLength(2);

		isilNumero = new NumberFieldCustom();
		isilNumero.setFormat(NumberFormat.getFormat("0"));
		isilNumero.setMaxLength(4);
		isilNumero.setWidth("40px");

		isilContainer.add(isilStato);
		isilContainer.add(isilProvincia);
		isilContainer.add(isilNumero);

		Utils.addListenerToChangeLabelColorIfModifiedTextField(isilProvincia, codiceIsilLabel);
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(isilNumero, codiceIsilLabel);
		
		codiceIsilTable.add(codiceIsilLabel, colonnaLabel);
		codiceIsilTable.add(isilContainer, colonnaLabel);

		formStatoCatalogazione.add(codiceIsilTable);

		add(formStatoCatalogazione);
		/*FINE FORM STATO CATALOGAZIONE*/

		/*FORM CODICI*/
		formCodici = new FormPanel();
		formCodici.setWidth(750);
		Utils.setFormStyleProperties(formCodici);

		FormLayout formLayoutCodici = new FormLayout();
		formLayoutCodici.setLabelAlign(LabelAlign.TOP);

		formCodici.setLayout(formLayoutCodici);

		LayoutContainer codFiscPIvaTable = new LayoutContainer(new TableLayout(6));

		codiceFiscLabel = new Text("Codice fiscale:");
		codFiscField = new TextFieldCustom<String>();
		codFiscField.setMinLength(11);
		codFiscField.setMaxLength(11);
		codiceFiscLabel.setStyleAttribute("fontSize", "14px");
		Utils.addListenerToChangeLabelColorIfModifiedTextField(codFiscField,codiceFiscLabel);

		pivaLabel = new Text("Partita iva:");
		pivaField = new TextFieldCustom<String>();
		pivaField.setMinLength(11);
		pivaField.setMaxLength(11);
		pivaLabel.setStyleAttribute("fontSize", "14px");
		Utils.addListenerToChangeLabelColorIfModifiedTextField(pivaField,pivaLabel);

		aggiornaCodFiscPIvaButton = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaCodFiscPIvaButton);
		
		resetCodFiscPIvaButton = new Button("Reset");
		Utils.setStylesButton(resetCodFiscPIvaButton);
		
		FormButtonBinding bind = new FormButtonBinding(formCodici);
		bind.addButton(aggiornaCodFiscPIvaButton);

		codFiscPIvaTable.add(codiceFiscLabel,colonnaLabel);
		codFiscPIvaTable.add(codFiscField,colonnaCodice);

		codFiscPIvaTable.add(pivaLabel,colonnaLabel);
		codFiscPIvaTable.add(pivaField,colonnaCodice);

		codFiscPIvaTable.add(aggiornaCodFiscPIvaButton,colonnaButton);
		codFiscPIvaTable.add(resetCodFiscPIvaButton,colonnaButton);

		aggiornaCodFiscPIvaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String, Object> params = new HashMap<String, Object>();

							params.put("codiceFiscale", codFiscField.getValue());
							params.put("partitaIva", pivaField.getValue());

							bibliotecheServiceAsync.aggiornaCodici(params, biblioteca.getIdBiblio(),	new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									setBlackLabelsFormCodFiscPIva();
									codFiscField.setOriginalValue( codFiscField.getValue());
									pivaField.setOriginalValue( pivaField.getValue());

//									MessageBox.info("ESITO UPDATE",	"Aggiornamento voce effettuato con successo!", null).show();
									fireReloadBiblioDataEvent();
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});


		resetCodFiscPIvaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formCodici.reset();
				setBlackLabelsFormCodFiscPIva();
			}
		});


		formCodici.add(codFiscPIvaTable);
		/*FINE FORM CODICI*/

		/*FINE FORM CODICI OTHERS*/
		formCodiciOthers = new FormPanel();
		formCodiciOthers.setWidth(700);
		Utils.setFormStyleProperties(formCodiciOthers);

		FormLayout formLayoutCodiciOthers = new FormLayout();
		formLayoutCodiciOthers.setLabelAlign(LabelAlign.TOP);

		formCodiciOthers.setLayout(formLayoutCodiciOthers);

		LayoutContainer codiciOthersTable = new LayoutContainer(new TableLayout(6));

		sbnLabel = new Text("SBN:");
		rismLabel = new Text("RISM:");
		acnpLabel = new Text("ACNP");
		ceiLabel = new Text("CEI:");
		cmbsLabel = new Text("CMBS:");

		sbnLabel.setStyleAttribute("fontSize", "14px");
		rismLabel.setStyleAttribute("fontSize", "14px");
		acnpLabel.setStyleAttribute("fontSize", "14px");
		ceiLabel.setStyleAttribute("fontSize", "14px");
		cmbsLabel.setStyleAttribute("fontSize", "14px");

		sbnField = new TextFieldCustom<String>();
		sbnField.setMaxLength(5);
		

		rismField = new TextFieldCustom<String>();
		rismField.setMaxLength(50);

		acnpField = new TextFieldCustom<String>();
		acnpField.setMaxLength(8);

		ceiField = new TextFieldCustom<String>();
		ceiField.setMaxLength(20);

		cmbsField = new TextFieldCustom<String>();
		cmbsField.setMaxLength(20);

		Utils.addListenerToChangeLabelColorIfModifiedTextField(sbnField,sbnLabel);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(rismField,rismLabel);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(acnpField,acnpLabel);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(ceiField,ceiLabel);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(cmbsField,cmbsLabel);

		aggiornaOthersButton = new Button("Aggiorna");
		aggiornaOthersButton.setStyleAttribute("marginRight", "20px");
		Utils.setStylesButton(aggiornaOthersButton);
		
		resetCodiciOthersButton = new Button("Reset");
		Utils.setStylesButton(resetCodiciOthersButton);
		
		FormButtonBinding bindOthers = new FormButtonBinding(formCodiciOthers);
		bindOthers.addButton(aggiornaOthersButton);

		aggiornaOthersButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String, Object> params = new HashMap<String, Object>();

							params.put("sbn", sbnField.getValue());
							params.put("rism", rismField.getValue());
							params.put("acnp", acnpField.getValue());
							params.put("cei", ceiField.getValue());
							params.put("cbms", cmbsField.getValue());

							bibliotecheServiceAsync.aggiornaCodiciOthers(params, biblioteca.getIdBiblio(),	new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									setBlackLabelsFormCodiciOthers();

									sbnField.setOriginalValue( sbnField.getValue());
									rismField.setOriginalValue( rismField.getValue());
									acnpField.setOriginalValue( acnpField.getValue());
									ceiField.setOriginalValue( ceiField.getValue());
									cmbsField.setOriginalValue( cmbsField.getValue());

//									MessageBox.info("ESITO UPDATE",	"Aggiornamento voce effettuato con successo!", null).show();
									fireReloadBiblioDataEvent();
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetCodiciOthersButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				formCodiciOthers.reset();
				setBlackLabelsFormCodiciOthers();
			}
		});

		codiciOthersTable.add(sbnLabel, colonnaLabel);
		codiciOthersTable.add(sbnField, colonnaCodice);

		codiciOthersTable.add(rismLabel, colonnaLabel);
		codiciOthersTable.add(rismField, colonnaCodice);

		codiciOthersTable.add(acnpLabel, colonnaLabel);
		codiciOthersTable.add(acnpField, colonnaCodice);

		codiciOthersTable.add(cmbsLabel, colonnaLabel);
		codiciOthersTable.add(cmbsField, colonnaCodice);

		codiciOthersTable.add(ceiLabel, colonnaLabel);
		codiciOthersTable.add(ceiField, colonnaCodice);

		codiciOthersTable.add(cmbsLabel, colonnaLabel);
		codiciOthersTable.add(cmbsField, colonnaCodice);

		codiciOthersTable.add(new LayoutContainer(), colonnaLabel);


		HorizontalPanel buttonPanel = new HorizontalPanel();

		buttonPanel.add(aggiornaOthersButton);
		buttonPanel.add(resetCodiciOthersButton);
		codiciOthersTable.add(buttonPanel, colonnaCodice);

		formCodiciOthers.add(codiciOthersTable);
		/*FINE FORM CODICI OTHERS*/

		codiciSet.add(formStatoCatalogazione);
		codiciSet.add(formCodici);
		codiciSet.add(formCodiciOthers);
		codici.add(codiciSet);
		add(codici);
		/* FINE---- SET CODICI */
	}


	public void hideIsilComponents() {
		codiceIsilLabel.hide();
		isilNumero.hide();
		isilProvincia.hide();
		isilStato.hide();
	}

	public void showIsilComponents() {
		codiceIsilLabel.show();
		isilNumero.show();
		isilProvincia.show();
		isilStato.show();
	}

	public void setValueFields(){
		//Setto le variabili dei campi dei form
		if (biblioteca != null) {
			denominazioneUfficiale.setValue(biblioteca.getDenominazione());
			denominazioneUfficiale.setOriginalValue(biblioteca.getDenominazione());
			viaPiazzaNCivicoField.setValue(biblioteca.getIndirizzo());
			viaPiazzaNCivicoField.setOriginalValue(biblioteca.getIndirizzo());
			frazioneField.setValue(biblioteca.getFrazione());
			frazioneField.setOriginalValue(biblioteca.getFrazione());
			if (biblioteca.getCap() != null) {
				capField.setValue(biblioteca.getCap());
				capField.setOriginalValue(biblioteca.getCap());
			} else {
				capField.setValue(null);
				capField.setOriginalValue(null);
			}
			comuneField.setValue(biblioteca.getComune());
			comuneField.setOriginalValue(biblioteca.getComune());

			provinciaField.setValue(biblioteca.getProvincia());
			regioneField.setValue(biblioteca.getRegione());
			statoField.setValue(biblioteca.getStato());

			provinciaField.setOriginalValue(biblioteca.getProvincia());
			regioneField.setOriginalValue(biblioteca.getRegione());
			statoField.setOriginalValue(biblioteca.getStato());

			if (loaderprovince != null) loaderprovince.load();
			
			if (biblioteca.getGeoX() != null) {
				latField.setValue(biblioteca.getGeoX());
				latField.setOriginalValue(biblioteca.getGeoX());
				
			} else {
				latField.setValue(null);
				latField.setValue(null);
			}
			
			if (biblioteca.getGeoY() != null) {
				lngField.setValue(biblioteca.getGeoY());
				lngField.setOriginalValue(biblioteca.getGeoY());
				
			} else {
				lngField.setValue(null);
				lngField.setOriginalValue(null);
			}

			//Setto le variabili dei campi del form codici
			if(biblioteca.getCodiceFiscale()!=null){
				codFiscField.setValue(biblioteca.getCodiceFiscale());
				codFiscField.setOriginalValue(biblioteca.getCodiceFiscale());
			}else{
				codFiscField.setValue(null);
				codFiscField.setOriginalValue(null);
			}

			if(biblioteca.getPartitaIva()!=null){
				pivaField.setValue(biblioteca.getPartitaIva());
				pivaField.setOriginalValue(biblioteca.getPartitaIva());
			}else{
				pivaField.setValue(null);
				pivaField.setOriginalValue(null);
			}

			if(biblioteca.getSBN()!=null){
				sbnField.setValue(biblioteca.getSBN());
				sbnField.setOriginalValue(biblioteca.getSBN());
			}else{
				sbnField.setValue(null);
				sbnField.setOriginalValue(null);
			}

			if(biblioteca.getRISM()!=null){
				rismField.setValue(biblioteca.getRISM());
				rismField.setOriginalValue(biblioteca.getRISM());
			}else{
				rismField.setValue(null);
				rismField.setOriginalValue(null);
			}

			if(biblioteca.getACNP()!=null){
				acnpField.setValue(biblioteca.getACNP());
				acnpField.setOriginalValue(biblioteca.getACNP());
			}else{
				acnpField.setValue(null);
				acnpField.setOriginalValue(null);
			}

			if(biblioteca.getCEI()!=null){
				ceiField.setValue(biblioteca.getCEI());
				ceiField.setOriginalValue(biblioteca.getCEI());
			}else{
				ceiField.setValue(null);
				ceiField.setOriginalValue(null);
			}

			if(biblioteca.getCMBS()!=null){
				cmbsField.setValue(biblioteca.getCMBS());
				cmbsField.setOriginalValue(biblioteca.getCMBS());
			}else{
				cmbsField.setValue(null);
				cmbsField.setOriginalValue(null);
			}

			isilStato.setOriginalValue("IT");
			isilStato.setValue("IT");
			if (biblioteca.getStatoCatalogazioneModel() != null) {
				statoCatalogazioneField.setValue(biblioteca.getStatoCatalogazioneModel());
				statoCatalogazioneField.setOriginalValue(biblioteca.getStatoCatalogazioneModel());
				
				if (biblioteca.getStatoCatalogazioneModel().getIdRecord().intValue() == 7) {
					showIsilComponents();
					if (biblioteca.getStatoCatalogazioneModel().getIsilProvincia() != null && biblioteca.getStatoCatalogazioneModel().getIsilNumero() != null) {
						isilProvincia.setOriginalValue(biblioteca.getStatoCatalogazioneModel().getIsilProvincia());
						isilNumero.setOriginalValue(biblioteca.getStatoCatalogazioneModel().getIsilNumero());

						isilProvincia.setValue(biblioteca.getStatoCatalogazioneModel().getIsilProvincia());
						isilNumero.setValue(biblioteca.getStatoCatalogazioneModel().getIsilNumero());
					}
					
				} else {
					if (biblioteca.getStatoCatalogazioneModel().getIdRecord().intValue() == -1) {
						isilProvincia.setValue(null);
						isilNumero.setValue(null);

						isilProvincia.setOriginalValue(null);
						isilNumero.setOriginalValue(null);
						
					}
					
					hideIsilComponents();
				}
			}
			
			/*Setto le label di colore nero in caso fossero state modificate precedentemente*/
			Utils.setFontColorStyleBlack(denominazioneUfficialeLabel);

			Utils.setFontColorStyleBlack(statoCatalogazioneLabel);
			Utils.setFontColorStyleBlack(codiceIsilLabel);

			setBlackLabelsFormIndirizzo();
			setBlackLabelsFormCodFiscPIva();
			setBlackLabelsFormCodiciOthers();

			//setto le variabili delle griglie
			denominazioniPrecedentiPanel.setIdBiblioteca(biblioteca.getIdBiblio());
			denominazioniPrecedentiPanel.getLoader().load();

			denominazioniAlternativePanel.setIdBiblioteca(biblioteca.getIdBiblio());
			denominazioniAlternativePanel.getLoader().load();

			listaContattiPanel.setIdBiblioteca(biblioteca.getIdBiblio());
			listaContattiPanel.getLoader().load();

			/*controllo readOnly fiedl form indirizzo*/
			UIWorkflow.setReadOnly(denominazioneUfficiale); 
			UIWorkflow.hideView(aggiornaDenominazione);  
			UIWorkflow.hideView(resetDenominazione);
			UIWorkflow.setReadOnly(viaPiazzaNCivicoField);
			UIWorkflow.setReadOnly(frazioneField);
			UIWorkflow.setReadOnly(capField);
			UIWorkflow.setReadOnly(comuneField);
			UIWorkflow.setReadOnly(provinciaField);
			UIWorkflow.setReadOnly(regioneField);
			UIWorkflow.setReadOnly(statoField);
			/*controllo readOnly fiedl form codici*/

			UIWorkflow.setReadOnly(codFiscField);
			UIWorkflow.setReadOnly(pivaField);
			UIWorkflow.setReadOnly(sbnField);
			UIWorkflow.setReadOnly(rismField);
			UIWorkflow.setReadOnly(acnpField);
			UIWorkflow.setReadOnly(ceiField);
			UIWorkflow.setReadOnly(cmbsField);

			/*controllo readOnly fiedl form stato catalogazione*/

			UIWorkflow.setReadOnly(statoCatalogazioneField);
			UIWorkflow.setReadOnly(isilStato);
			UIWorkflow.setReadOnly(isilNumero);
			UIWorkflow.setReadOnly(isilProvincia);

			geolocalizza.show();

			UIWorkflow.hideView(aggiornaIndirizzo);
			UIWorkflow.hideView(resetIndirizzo);

			UIWorkflow.hideView(aggiornaCodFiscPIvaButton);
			UIWorkflow.hideView(aggiornaOthersButton);

			UIWorkflow.hideView(resetCodFiscPIvaButton);
			UIWorkflow.hideView(resetCodiciOthersButton);

			UIWorkflow.hideView(statoCatalogazioneAggiorna);
			UIWorkflow.hideView(statoCatalogazioneReset);
			
			if(UIWorkflow.isReadOnly()==false){
				addKeyListenerForEnter();
			}else{
				removeKeyListenerForEnter();
			}

		}
	}

	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( formDenominazioni);
		Utils.removeKeyListenerForEnter( formIndirizzo);
		Utils.removeKeyListenerForEnter( formStatoCatalogazione);
		Utils.removeKeyListenerForEnter( formCodici);
		Utils.removeKeyListenerForEnter( formCodiciOthers);
	}

	private void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(aggiornaDenominazione, formDenominazioni);
		Utils.addKeyListenerForEnter(aggiornaIndirizzo, formIndirizzo);
		Utils.addKeyListenerForEnter(statoCatalogazioneAggiorna, formStatoCatalogazione);
		Utils.addKeyListenerForEnter(aggiornaCodFiscPIvaButton, formCodici);
		Utils.addKeyListenerForEnter(aggiornaOthersButton, formCodiciOthers);
	}

	/**
	 * 
	 */
	private void setBlackLabelsFormIndirizzo() {

		/*label form indirizzo*/
		Utils.setFontColorStyleBlack(viaPiazzaNCivicoLabel);
		Utils.setFontColorStyleBlack(frazioneLabel);
		Utils.setFontColorStyleBlack(capLabel);
		Utils.setFontColorStyleBlack(comuneLabel);
		Utils.setFontColorStyleBlack(provinciaLabel);
		Utils.setFontColorStyleBlack(latLabel);
		Utils.setFontColorStyleBlack(lngLabel);

	}

	/**
	 * 
	 */
	private void setBlackLabelsFormCodFiscPIva() {
		/*label form codici*/
		Utils.setFontColorStyleBlack(codiceFiscLabel);
		Utils.setFontColorStyleBlack(pivaLabel);
	}

	/**
	 * 
	 */
	private void setBlackLabelsFormCodiciOthers() {
		Utils.setFontColorStyleBlack(sbnLabel);
		Utils.setFontColorStyleBlack(rismLabel);
		Utils.setFontColorStyleBlack(acnpLabel);
		Utils.setFontColorStyleBlack(ceiLabel);
		Utils.setFontColorStyleBlack(cmbsLabel);
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}

	public void setGeolocalizzaText(String text) {
		geolocalizza.setText(text);
	}
	
	private native String getTemplateEmptyRowLocation() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{entry}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/;
}
