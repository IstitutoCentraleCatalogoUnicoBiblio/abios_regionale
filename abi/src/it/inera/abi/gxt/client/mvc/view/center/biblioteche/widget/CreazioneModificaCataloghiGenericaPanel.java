package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.CataloghiUrlModel;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGenericaModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CreazioneModificaCataloghiGenericaPanel extends FormPanel{
	/*Servizi*/
	protected BibliotecheServiceAsync bibliotecheService;
	protected TabelleDinamicheServiceAsync tdsa;

	protected int idBiblio;
	protected FondiSpecialiModel model;
	protected boolean first= true;
	protected	boolean modifica;
	protected boolean modificaUrl;

	/*Label comuni cataloghi*/
	protected  Text schedeLabel;
	protected  Text percentSchedeLabel;
	protected  Text volumeLabel;
	protected  Text percentVolumeLabel;
	protected  Text citazioneBiblioLabel;
	protected  Text microformeLabel;
	protected  Text percentMicroformeLabel;
	protected  Text supportoDigitaleTipoLabel;
	protected  Text percentInformatizzatoLabel;
	protected  Text urlLabel;
	protected  Text annoDaLabel;
	protected  Text annoALabel;

	/*Field comuni ai cataloghi*/
	protected SimpleComboBox<String> schedeField;
	protected NumberField percentSchedeField;
	protected SimpleComboBox<String> volumeField;
	protected NumberField percentVolumeField;
	protected TextField<String> citazioneBiblioField;
	protected SimpleComboBox<String> microformeField;
	protected NumberField percentMicroformeField;
	protected ComboBox<VoceUnicaModel> supportoDigitaleTipoField;
	protected Button urlsButton;
	protected NumberField percentInformatizzatoField;
	protected GridCellRenderer<PartecipaCataloghiGenericaModel> bottoneUrlRenderer;
	protected NumberField annoDaField;
	protected NumberField annoAField;
	/**/
	/*tipoCatalogo: definisce il tipo di catalogo collettivo, generale, speciale
	 * Viene settato nelle rispettive sottoclassi;
	 * tipoCatalogo==1 CataloghiGenerali
	 * tipoCatalogo==2 CataloghiCollettivi
	 * tipoCatalogo==3 CataloghiSpecuali
	 * 	 * */
	protected int tipoCatalogo;

	protected	Button aggiungiButton;
	protected	Button aggiornaButton;
	protected	Button rimuoviButton;

	protected TableData labelFormatiTableData;
	protected TableData labelPercentTableData;
	protected TableData labelCoperturaBibliograficaTableData;

	/*TabelData per labels e fields per combobox(Si/No)*/
	protected final	TableData comboSiNoTabelData;
	/*TabelData per labels e fields per  %*/
	protected final	TableData percentTabelData;
	/*TabelData per labels e fields citazione bibliografica*/
	protected final TableData citazioneTabelData;
	/*TabelData per labels e fields supporto digitale*/
	protected final TableData informatizzatoTabelData;

	/*TabelData per labels e fields anno*/
	protected final TableData annoTabelData;

	/*TabelData per labels e fields bottone url*/
	protected final TableData urlsTableData;

	int comboSiNoWidth = 45;
	int percentWidth = 23;
	int citazioneWidth=80;
	int informatizzatoWidth=65;
	int annoWidth=40;
	int urlsWidth=33;
	String labelFontSize="10px";

	//	protected static final int comboSiNoWidth=
	public CreazioneModificaCataloghiGenericaPanel() {
		super();

		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE); 

		labelFormatiTableData = new TableData();
		labelFormatiTableData.setWidth("20%");
		labelFormatiTableData.setMargin(5);
		labelFormatiTableData.setPadding(10);

		labelPercentTableData= new TableData();
		labelPercentTableData.setWidth("5%");
		labelPercentTableData.setMargin(5);
		labelPercentTableData.setPadding(10);

		labelCoperturaBibliograficaTableData = new TableData();
		labelCoperturaBibliograficaTableData.setWidth("25%");
		labelCoperturaBibliograficaTableData.setMargin(5);
		labelCoperturaBibliograficaTableData.setPadding(10);

		/*Table data per label e field in modifica*/
		comboSiNoTabelData = new TableData();
		comboSiNoTabelData.setWidth(comboSiNoWidth+"px");
		comboSiNoTabelData.setMargin(5);

		/*Table data per label e field in modifica*/
		percentTabelData = new TableData();
		percentTabelData.setWidth(percentWidth+"px");
		percentTabelData.setMargin(5);


		citazioneTabelData = new TableData();
		citazioneTabelData.setWidth(citazioneWidth+"px");
		citazioneTabelData.setMargin(5);

		informatizzatoTabelData = new TableData();
		informatizzatoTabelData.setWidth(informatizzatoWidth+"px");
		informatizzatoTabelData.setMargin(5);

		annoTabelData = new TableData();
		annoTabelData.setWidth(annoWidth+"px");
		annoTabelData.setMargin(5);

		urlsTableData = new TableData();
		urlsTableData.setWidth(urlsWidth+"px");
		urlsTableData.setMargin(5);


		/**/

		aggiungiButton = new Button("Aggiungi");
		//		aggiungiButton.setIcon(Resources.ICONS.add());



		setStyleAttribute("marginTop", "5px");
		setStyleAttribute("marginBottom", "5px");
		setMonitorWindowResize(true);
		setHeaderVisible(false);

		createBaseFieldsAndLabels();
	}

	protected void createBaseFieldsAndLabels(){

		schedeLabel = new Text("Schede:");
		schedeLabel.setStyleAttribute("fontSize", "12px");

		schedeField = new SimpleComboBox<String>();
		schedeField.setForceSelection(true);
		schedeField.setTriggerAction(TriggerAction.ALL);
		schedeField.setEmptyText("Si/No...");
		schedeField.setEditable(false);
		schedeField.setFireChangeEventOnSetValue(true);
		schedeField.add("Si");
		schedeField.add("No");

		schedeField.setSimpleValue("No");

		schedeField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				if(se.getSelectedItem()!=null){
					if(se.getSelectedItem().getValue().equalsIgnoreCase("si")){
						percentSchedeField.enable();
					}
					else{
						percentSchedeField.disable();
						percentSchedeField.setValue(0);
					}
				}
			}
		});

		percentSchedeLabel = new Text("%");
		percentSchedeLabel.setStyleAttribute("fontSize", "12px");

		percentSchedeField = new NumberField();
		percentSchedeField.setEmptyText("0");
		percentSchedeField.setMaxLength(3);
		percentSchedeField.setMaxValue(100);
		percentSchedeField.setPropertyEditorType(Integer.class);
		percentSchedeField.disable();

		volumeLabel = new Text("Volume:");
		volumeLabel.setStyleAttribute("fontSize", "12px");

		volumeField = new SimpleComboBox<String>();
		volumeField.setForceSelection(true);
		volumeField.setEmptyText("Si/No...");
		volumeField.setTriggerAction(TriggerAction.ALL);
		volumeField.setEditable(false);
		volumeField.setFireChangeEventOnSetValue(true);
		volumeField.add("Si");
		volumeField.add("No");

		volumeField.setSimpleValue("No");

		volumeField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				if(se.getSelectedItem()!=null){
					if(se.getSelectedItem().getValue().equalsIgnoreCase("si")){
						percentVolumeField.enable();
						citazioneBiblioField.enable();
					}
					else{
						percentVolumeField.setValue(0);
						percentVolumeField.disable();
						citazioneBiblioField.disable();
						citazioneBiblioField.setValue(null);
					}
				}
			}
		});
		percentVolumeLabel = new Text("%");
		percentVolumeLabel.setStyleAttribute("fontSize", "12px");

		percentVolumeField = new NumberField();
		percentVolumeField.setMaxLength(3);
		percentVolumeField.setMaxValue(100);
		percentVolumeField.disable();
		percentVolumeField	.setEmptyText("0");
		percentVolumeField.setPropertyEditorType(Integer.class);

		citazioneBiblioLabel = new Text("Citazione bibliografica:");
		citazioneBiblioLabel.setStyleAttribute("fontSize", "12px");

		citazioneBiblioField = new TextField<String>();
		citazioneBiblioField.disable();

		microformeLabel = new Text("Microforme:");
		microformeLabel.setStyleAttribute("fontSize", "12px");

		microformeField = new SimpleComboBox<String>();
		microformeField.setForceSelection(true);
		microformeField.setTriggerAction(TriggerAction.ALL);
		microformeField.setEditable(false);
		microformeField.setFireChangeEventOnSetValue(true);
		microformeField	.setEmptyText("Si/No...");
		microformeField.add("Si");
		microformeField.add("No");
		microformeField.setSimpleValue("No");

		microformeField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				if(se.getSelectedItem()!=null){
					if(se.getSelectedItem().getValue().equalsIgnoreCase("si")){
						percentMicroformeField.enable();

					}
					else{
						percentMicroformeField.disable();
						percentMicroformeField.setValue(0);
					}
				}
			}
		});

		percentMicroformeLabel = new Text("%");
		percentMicroformeLabel.setStyleAttribute("fontSize", "12px");

		percentMicroformeField = new NumberField();
		percentMicroformeField.disable();
		percentMicroformeField.setMaxLength(3);
		percentMicroformeField.setMaxValue(100);
		percentMicroformeField	.setEmptyText("0");
		percentMicroformeField	.setPropertyEditorType(Integer.class);

		RpcProxy<List<VoceUnicaModel>> supportoDigitaleTipoProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				tdsa.getListaVoci(CostantiTabelleDinamiche.CATALOGHI_TIPI_SUPPORTO_INVENTARIO_INDEX, callback);
			}
		};

		ModelReader supportoDigitaleTipoReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> supportoDigitaleTipoLoader  = new BaseListLoader<ListLoadResult<VoceUnicaModel>>( supportoDigitaleTipoProxy, supportoDigitaleTipoReader);



		final ListStore<VoceUnicaModel> supportoDigitaleTipoStore = new ListStore<VoceUnicaModel>(
				supportoDigitaleTipoLoader);

		supportoDigitaleTipoLabel = new Text("Informatizzato:");
		supportoDigitaleTipoLabel.setStyleAttribute("fontSize", "12px");

		supportoDigitaleTipoField = new ComboBox<VoceUnicaModel>();
		supportoDigitaleTipoField.setForceSelection(true);
		supportoDigitaleTipoField.setDisplayField("entry");
		supportoDigitaleTipoField.setTriggerAction(TriggerAction.ALL);
		supportoDigitaleTipoField.setEditable(false);
		supportoDigitaleTipoField.setFireChangeEventOnSetValue(true);
		supportoDigitaleTipoField.setEmptyText("Tipo...");
		supportoDigitaleTipoField.setStore(supportoDigitaleTipoStore);

		supportoDigitaleTipoField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null){
					/*
					 * Id 3 = "No", quindi se non ha supporto digitale
					 * disabilito il campo della percentuale
					 * informatizzato
					 */
					if(se.getSelectedItem().getIdRecord()!=3){
						percentInformatizzatoField.enable();
					}
					else{
						percentInformatizzatoField.disable();
						percentInformatizzatoField.setValue(0);
					}
				}
			}
		});

		urlLabel = new Text("Urls:");
		urlLabel.setStyleAttribute("fontSize", "12px");
		urlsButton= new Button("Urls");

		percentInformatizzatoLabel = new Text("%");
		percentInformatizzatoLabel.setStyleAttribute("fontSize", "12px");

		percentInformatizzatoField = new NumberField();
		percentInformatizzatoField.disable();
		percentInformatizzatoField.setMaxLength(3);
		percentInformatizzatoField.setMaxValue(100);
		percentInformatizzatoField.setEmptyText("0");
		percentInformatizzatoField.setPropertyEditorType(Integer.class);

		annoDaLabel = new Text("Anno da:");
		annoDaLabel.setStyleAttribute("fontSize", "12px");
		annoDaField = new NumberField();
		annoDaField.setMaxLength(4);

		annoALabel = new Text("Anno a:");
		annoALabel.setStyleAttribute("fontSize", "12px");
		annoAField = new NumberField();
		annoAField.setMaxLength(4);

	}

	protected LayoutContainer createAggiungiBaseForm(){

		LayoutContainer baseFormContainer = new LayoutContainer(new FitLayout());

		LayoutContainer formatiAccessoTable = new LayoutContainer(new TableLayout(4));

		Text formatiAccessoText = new Text("<b>Formati/Accesso</b>");

		formatiAccessoTable.add(schedeLabel,labelFormatiTableData);
		formatiAccessoTable.add(schedeField);

		formatiAccessoTable.add(percentSchedeLabel,labelPercentTableData);
		formatiAccessoTable.add(percentSchedeField);

		formatiAccessoTable.add(microformeLabel,labelFormatiTableData);
		formatiAccessoTable.add(microformeField);

		formatiAccessoTable.add(percentMicroformeLabel,labelPercentTableData);
		formatiAccessoTable.add(percentMicroformeField);

		formatiAccessoTable.add(supportoDigitaleTipoLabel,labelFormatiTableData);
		formatiAccessoTable.add(supportoDigitaleTipoField);

		formatiAccessoTable.add(percentInformatizzatoLabel,labelPercentTableData);
		formatiAccessoTable.add(percentInformatizzatoField);

		formatiAccessoTable.add(volumeLabel,labelFormatiTableData);
		formatiAccessoTable.add(volumeField);

		formatiAccessoTable.add(percentVolumeLabel,labelPercentTableData);
		formatiAccessoTable.add(percentVolumeField);

		formatiAccessoTable.add(citazioneBiblioLabel,labelFormatiTableData);
		formatiAccessoTable.add(citazioneBiblioField);

		Text coperturaBibliograficaText = new Text("<b>Copertura bibliorafica</b>");

		TableData annoDaTableData = new TableData();
		annoDaTableData= new TableData();
		annoDaTableData.setWidth("103px");
		annoDaTableData.setMargin(5);
		annoDaTableData.setPadding(10);

		TableData annoATableData = new TableData();
		annoATableData= new TableData();
		annoATableData.setWidth("17%");
		annoATableData.setMargin(5);
		annoATableData.setHorizontalAlign(HorizontalAlignment.RIGHT);

		annoATableData.setPadding(10);

		LayoutContainer coperturaBibliograficaTable = new LayoutContainer(new TableLayout(4));
		coperturaBibliograficaTable.add(annoDaLabel,annoDaTableData);
		coperturaBibliograficaTable.add(annoDaField);

		coperturaBibliograficaTable.add(annoALabel,annoATableData);
		coperturaBibliograficaTable.add(annoAField);

		baseFormContainer.add(formatiAccessoText);
		baseFormContainer.add(formatiAccessoTable);
		baseFormContainer.add(coperturaBibliograficaText);
		baseFormContainer.add(coperturaBibliograficaTable);

		return baseFormContainer;
	}

	protected LayoutContainer createModificaBaseFormLabels(){

		LayoutContainer baseLabelTable=null;
		TableLayout t = null;
		if(tipoCatalogo==1){
			t=new TableLayout(13); 
			baseLabelTable = new LayoutContainer(t);
		}
		else	if(tipoCatalogo==2){
			t=new TableLayout(13); 
			baseLabelTable = new LayoutContainer(t);
		}else if(tipoCatalogo==3){
			t=new TableLayout(14); 
			baseLabelTable = new LayoutContainer(t);

		}
		t.setCellHorizontalAlign(HorizontalAlignment.CENTER);

		t.setTableStyle("background:#EAEAEA");

		schedeLabel.setStyleAttribute("fontSize", labelFontSize);
		percentSchedeLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(schedeLabel,comboSiNoTabelData);
		baseLabelTable.add(percentSchedeLabel,percentTabelData);

		volumeLabel.setStyleAttribute("fontSize", labelFontSize);
		percentVolumeLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(volumeLabel,comboSiNoTabelData);
		baseLabelTable.add(percentVolumeLabel,percentTabelData);

		citazioneBiblioLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(citazioneBiblioLabel,citazioneTabelData);

		microformeLabel.setStyleAttribute("fontSize", labelFontSize);
		microformeLabel.setText("Microf.:");
		percentMicroformeLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(microformeLabel,comboSiNoTabelData);
		baseLabelTable.add(percentMicroformeLabel,percentTabelData);

		supportoDigitaleTipoLabel.setStyleAttribute("fontSize", labelFontSize);
		supportoDigitaleTipoLabel.setText("Inform.:");
		percentInformatizzatoLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(supportoDigitaleTipoLabel,informatizzatoTabelData);
		baseLabelTable.add(percentInformatizzatoLabel,percentTabelData);

		urlLabel.setStyleAttribute("fontSize", labelFontSize);
		baseLabelTable.add(urlLabel,urlsTableData);

		annoDaLabel.setStyleAttribute("fontSize", labelFontSize);
		annoDaLabel.setText("dal:");
		annoALabel.setStyleAttribute("fontSize", labelFontSize);
		annoALabel.setText("al:");
		baseLabelTable.add(annoDaLabel,annoTabelData);
		baseLabelTable.add(annoALabel,annoTabelData);

		return baseLabelTable;
	}

	protected LayoutContainer createModificaBaseFormFields(){
		LayoutContainer baseFieldTable = null;
		if(tipoCatalogo==1){
			baseFieldTable = new LayoutContainer(new TableLayout(15));
		}
		else if(tipoCatalogo==2){
			baseFieldTable = new LayoutContainer(new TableLayout(15));
		}else if(tipoCatalogo==3){
			baseFieldTable = new LayoutContainer(new TableLayout(16));
		}

		schedeField.setWidth(comboSiNoWidth+"px");
		percentSchedeField.setWidth(percentWidth+"px");
		baseFieldTable.add(schedeField,comboSiNoTabelData);
		baseFieldTable.add(percentSchedeField,percentTabelData);

		volumeField.setWidth(comboSiNoWidth+"px");
		percentVolumeField.setWidth(percentWidth+"px");
		baseFieldTable.add(volumeField,comboSiNoTabelData);
		baseFieldTable.add(percentVolumeField,percentTabelData);

		citazioneBiblioField.setWidth(citazioneWidth+"px");
		baseFieldTable.add(citazioneBiblioField,citazioneTabelData);

		microformeField.setWidth(comboSiNoWidth+"px");
		percentMicroformeField.setWidth(percentWidth+"px");
		baseFieldTable.add(microformeField,comboSiNoTabelData);
		baseFieldTable.add(percentMicroformeField,percentTabelData);

		supportoDigitaleTipoField.setWidth(informatizzatoWidth+"px");
		percentInformatizzatoField.setWidth(percentWidth+"px");
		baseFieldTable.add(supportoDigitaleTipoField,informatizzatoTabelData);
		baseFieldTable.add(percentInformatizzatoField,percentTabelData);

		baseFieldTable.add(urlsButton,urlsTableData);

		annoDaField.setWidth(annoWidth+"px");
		annoAField.setWidth(annoWidth+"px");
		baseFieldTable.add(annoDaField,annoTabelData);
		baseFieldTable.add(annoAField,annoTabelData);
		render(baseFieldTable.getElement());
		return baseFieldTable;
	}

	private void initUrlButton(final PartecipaCataloghiGenericaModel model) {

		urlsButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {		
				final Window window = new Window();
				window.setSize(600, 300);
				window.setModal(true);
				window.setHeading("AGGIUNGI/MODIFICA URLs");
				window.setLayout(new FitLayout());
				window.setClosable(false);

				//Panel PER CONTENUTO griglia URL
				ContentPanel listaUrlsPanel = new ContentPanel(new TableLayout(4));
				listaUrlsPanel.setBodyStyle("padding-bottom:10px");
				listaUrlsPanel.setBodyBorder(false);
				listaUrlsPanel.setBorders(false);
				listaUrlsPanel.setHeaderVisible(false);
				listaUrlsPanel.setWidth(590);
				listaUrlsPanel.setHeight(200);
				listaUrlsPanel.setScrollMode(Scroll.AUTOY);
				listaUrlsPanel.setLayout(new FitLayout());

				List<ColumnConfig> configs =new ArrayList<ColumnConfig>();

				final	TextField<String> url = new  TextField<String>();
				url.setEmptyText("Inserisci url...");

				ColumnConfig urlColumnConfig = new ColumnConfig();
				urlColumnConfig.setId("url");
				urlColumnConfig.setHeader("Url");
				urlColumnConfig.setWidth(300);
				urlColumnConfig.setEditor(new CellEditor(url));
				configs.add(urlColumnConfig);

				final	TextField<String> descrizioneUrl = new TextField<String>();
				descrizioneUrl.setEmptyText("Inserisci descrizione url...");

				ColumnConfig descrizioneUrlColumnConfig = new ColumnConfig();
				descrizioneUrlColumnConfig.setId("descrizioneUrl");
				descrizioneUrlColumnConfig.setHeader("Descrizione url");
				descrizioneUrlColumnConfig.setWidth(250);
				descrizioneUrlColumnConfig.setEditor(new CellEditor(descrizioneUrl));
				configs.add(descrizioneUrlColumnConfig);

				RpcProxy<List<CataloghiUrlModel>> cataloghiUrlProxy = new RpcProxy<List<CataloghiUrlModel>>() {

					@Override
					protected void load(Object loadConfig, AsyncCallback<List<CataloghiUrlModel>> callback) {
						//tipoCatalogo== tipologia di catalogo in modifica
						/*idPartecipaCatalogo== id dell'associazione Biblioteca --->Cataloghi in modifica
						 * da cui caricare la lista url*/
						int idPartecipaCatalogo=model.getIdPartecipaCatalogo();
						tdsa.getCataloghiUrls(tipoCatalogo,idPartecipaCatalogo, callback);

					}
				};

				ModelReader urlReader = new ModelReader();

				final BaseListLoader<ListLoadResult<CataloghiUrlModel>> urlLoader=new 	BaseListLoader<ListLoadResult<CataloghiUrlModel>>(cataloghiUrlProxy, urlReader);

				final ListStore<CataloghiUrlModel>  cataloghiUrlStore=new ListStore<CataloghiUrlModel>(urlLoader);
				urlLoader.load();

				final ColumnModel cm = new ColumnModel(configs);

				final RowEditorCustom<CataloghiUrlModel> reUrls = new RowEditorCustom<CataloghiUrlModel>();
				reUrls.setErrorSummary(false);
				reUrls.setClicksToEdit(ClicksToEdit.TWO);
				
				RowEditor<CataloghiUrlModel>.RowEditorMessages rowEditorMessages = reUrls.getMessages();
                rowEditorMessages.setCancelText("Annulla");
                rowEditorMessages.setSaveText("Salva");
                reUrls.setMessages(rowEditorMessages);

				final Grid<CataloghiUrlModel> gridUrls = new Grid<CataloghiUrlModel>(cataloghiUrlStore,cm);
				gridUrls.setStripeRows(true);
				gridUrls.addPlugin(reUrls);
				gridUrls.getView().setAutoFill(true);

				/*TOOLBAR*/
				ToolBar windowToolBar = new ToolBar();

				windowToolBar.setWidth(300);
				windowToolBar.setBorders(false);

				windowToolBar.add(new Text("Materiale url "));
				Button add = new Button("Aggiungi");
				add.setIcon(Resources.ICONS.add());
				add.addSelectionListener(new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						CataloghiUrlModel newCatalogo = new CataloghiUrlModel();

						reUrls.stopEditing(false);
						cataloghiUrlStore.insert(newCatalogo, 0);
						reUrls.startEditing(cataloghiUrlStore.indexOf(newCatalogo), false);
					}
				});
				windowToolBar.add(add);

				final Button remove = new Button("Rimuovi");
				remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {

						final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

							@Override
							public void handleEvent(final MessageBoxEvent ce) {

								Button btn = ce.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {

									int	id_removeUrl=gridUrls.getSelectionModel().getSelectedItem().getIdCataloghiUrl();

									tdsa.removeCataloghiMaterialeUrl(id_removeUrl,tipoCatalogo,new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											modificaUrl = false;
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
											urlLoader.load();
											{
												gridUrls.getStore().remove(gridUrls.getStore().indexOf(gridUrls.getSelectionModel().getSelectedItem()));
												ce.<Component> getComponent().disable();
											}
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
												ce.<Component> getComponent().disable();
												urlLoader.load();
												modificaUrl = false;
											}
										}
									});

								} else {
								}
							}
						};
						AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

						if (gridUrls.getStore().getCount() == 0) {
							ce.<Component> getComponent().disable();
						}
						remove.disable();
					}
				});
				remove.disable();

				reUrls.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						if(modificaUrl==false){
							cataloghiUrlStore.remove(0);	
						}
						remove.disable();
					}
				});

				reUrls.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {

						final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

							@Override
							public void handleEvent(MessageBoxEvent ce) {

								Button btn = ce.getButtonClicked();
								if (btn.getText().equalsIgnoreCase("Si")) {
									CataloghiUrlModel newUrl=new CataloghiUrlModel();

									if (modificaUrl == false) {
										newUrl.setIdPartecipa(model.getIdPartecipaCatalogo());
										newUrl.setUrl(url.getValue());
										if(descrizioneUrl.getValue()!=null){
											newUrl.setDescrUrl(descrizioneUrl.getValue());
										}
									}

									else {
										newUrl.setIdCataloghiUrl(gridUrls.getSelectionModel().getSelectedItem().getIdCataloghiUrl());
										newUrl.setIdPartecipa(gridUrls.getSelectionModel().getSelectedItem().getIdPartecipa());
										newUrl.setUrl(url.getValue());
										if(descrizioneUrl.getValue()!=null){
											newUrl.setDescrUrl(descrizioneUrl.getValue());
										}

									}
									tdsa.addCataloghiMaterialeUrl(newUrl,modificaUrl,tipoCatalogo,new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											modificaUrl = false;
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											urlLoader.load();
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
												if (modificaUrl == false) {
													cataloghiUrlStore.remove(0);
												}
												modificaUrl = false;
											}
										}
									});

								} else {
									if (modificaUrl == false) {
										cataloghiUrlStore.remove(0);

									} else {
										modificaUrl = false;
									}
									urlLoader.load();										}
							}
						};
						AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
					}
				});
				gridUrls.addListener(Events.RowClick,
						new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

					public void handleEvent(
							GridEvent<PartecipaCataloghiCollettiviModel> be) {

						remove.enable();
					}
				});
				gridUrls.addListener(Events.RowDoubleClick,
						new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

					public void handleEvent(
							GridEvent<PartecipaCataloghiCollettiviModel> be) {
						modificaUrl=true;
						remove.disable();
					}
				});

				remove.setIcon(Resources.ICONS.delete());
				windowToolBar.add(remove);

				listaUrlsPanel.add(gridUrls);
				listaUrlsPanel.setTopComponent(windowToolBar);
				/*FINE TOOLBAR*/


				final Button save = new Button("Ok");
				save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}

				});
				window.add(listaUrlsPanel);
				window.addButton(save);
				window.show();
			}
		});

		urlsButton.setToolTip("Clicca per aggiungere/modificare <br> la lista url;");
		/*
		 * Se l'oggetto CataloghiSupportoDigitaleTipo è diverso da 4
		 * (online) il bottone Visualizza/Modifica viene disabilitato
		 */

		if(model.getIdInformatizzatoTipo()!=null && model.getIdInformatizzatoTipo()!=4){
			urlsButton.disable();
		}
		else{
			urlsButton.enable();
		}

	}

	protected void setBaseValues(PartecipaCataloghiGenericaModel model){
		/*Schede*/
		if(model.getSchede()!=null)
			schedeField.setSimpleValue(model.getSchede());
		if(model.getPercentSchede()!=null)
			percentSchedeField.setValue(model.getPercentSchede());
		/*Volumi*/
		if(model.getVolume()!=null)
			volumeField.setRawValue(model.getVolume());
		if(model.getPercentVolume()!=null)
			percentVolumeField.setValue(model.getPercentVolume());
		/*Citazione bibliografica*/

		if(volumeField.getRawValue().equalsIgnoreCase("No")){
			citazioneBiblioField.disable();
		}
		else if(volumeField.getRawValue().equalsIgnoreCase("Si")){
			citazioneBiblioField.enable();
			if(model.getCitazioneBiblio()!=null){
				citazioneBiblioField.setValue(model.getCitazioneBiblio());
			}
		}

		/*Microforme*/
		if(model.getMicroforme()!=null)
			microformeField.setSimpleValue(model.getMicroforme());
		if(model.getPercentMicroforme()!=null)
			percentMicroformeField.setValue(model.getPercentMicroforme());
		/*Supporto digitale*/
		if(model.getIdInformatizzatoTipo()!=null)
			supportoDigitaleTipoField.setValue(new VoceUnicaModel(model.getIdInformatizzatoTipo(), model.getInformatizzato()));
		if(model.getPercentInformatizzato()!=null)
			percentInformatizzatoField.setValue(model.getPercentInformatizzato());
		/*UrlButton*/
		initUrlButton(model);

		/*Supporto bibliografico*/
		if(model.getAnnoDa()!=null)
			annoDaField.setValue(model.getAnnoDa());
		if(model.getAnnoA()!=null)
			annoAField.setValue(model.getAnnoA());

		if(UIWorkflow.isReadOnly()==false){
			schedeField.enable();
			volumeField.enable();
			if(volumeField.getRawValue().equalsIgnoreCase("No")){
				citazioneBiblioField.disable();
			}
			else if(volumeField.getRawValue().equalsIgnoreCase("Si")){
				citazioneBiblioField.enable();
				if(model.getCitazioneBiblio()!=null){
					citazioneBiblioField.setValue(model.getCitazioneBiblio());
				}
			}
			microformeField.enable();
			supportoDigitaleTipoField.enable();
			urlsButton.enable();
			annoDaField.enable();
			annoAField.enable();
		}else{
			schedeField.disable();
			percentSchedeField.disable();
			volumeField.disable();
			percentVolumeField.disable();
			citazioneBiblioField.disable();
			microformeField.disable();
			percentMicroformeField.disable();
			supportoDigitaleTipoField.disable();
			percentInformatizzatoField.disable();
			urlsButton.disable();
			annoDaField.disable();
			annoAField.disable();
		}
	}

	public void setIdBiblio(int idBiblio){
		this.idBiblio=idBiblio;
	}

	protected void fireReleoadbiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,this.idBiblio);
	}

	protected void fireAddPanel() {
		fireEvent(Events.Add);		
	}

	public void fireCloseWindow() {
		fireEvent(Events.Close);		
	}

	protected void fireRemovePanel() {

		fireEvent(Events.Remove);		
	}

	protected void fireReleoadPanel() {
		fireEvent(Events.Refresh);		
	}




}
