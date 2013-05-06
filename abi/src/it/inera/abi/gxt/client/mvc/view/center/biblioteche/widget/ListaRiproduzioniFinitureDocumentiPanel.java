package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista delle riproduzioni
 *
 */
public class ListaRiproduzioniFinitureDocumentiPanel extends ContentPanel {
	
	private int id_biblioteca;
	private boolean modifica;
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheService ;

	private Grid<ServiziRiproduzioniModel> grid;
	private ToolBar toolBar;
	private Button add;
	private Button remove;

	private	ListStore<ServiziRiproduzioniModel> serviziRiproduzioniStore ;
	private  BaseListLoader<ListLoadResult<ServiziRiproduzioniModel>> serviziRiproduzioniLoader;

	public ListaRiproduzioniFinitureDocumentiPanel() {
		bibliotecheService=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService=(TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		modifica=false;
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}

	public void setGrid(){

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RpcProxy<List<VoceUnicaModel>> tipoRiproduzioneProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.RIPRODUZIONI_TIPI_INDEX, callback);
			}

		};

		ModelReader tipoRiproduzioneReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> tipoRiproduzioneLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				tipoRiproduzioneProxy, tipoRiproduzioneReader);

		final ListStore<VoceUnicaModel> tipoRiproduzioneStore = new ListStore<VoceUnicaModel>(
				tipoRiproduzioneLoader);

		/*
		 * ComboBox per caricamento lista
		 * tipologie cataloghi generali possibili
		 */
		final ComboBox<VoceUnicaModel> tipoRiproduzioneField = new ComboBox<VoceUnicaModel>();
		tipoRiproduzioneField.setForceSelection(true);
		tipoRiproduzioneField.setDisplayField("entry");
		tipoRiproduzioneField.setTriggerAction(TriggerAction.ALL);
		tipoRiproduzioneField.setEditable(false);
		tipoRiproduzioneField.setEmptyText("Tipologia...");
		tipoRiproduzioneField.setAllowBlank(false);
		tipoRiproduzioneField.setFireChangeEventOnSetValue(true);
		tipoRiproduzioneField.setStore(tipoRiproduzioneStore);


		CellEditor tipoRiproduzioneEditor = new CellEditor(tipoRiproduzioneField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}

				if(modifica){
					ServiziRiproduzioniModel tmp=grid.getStore().findModel("tipoDescr",value.toString());
					VoceUnicaModel tmpPatr =new VoceUnicaModel(tmp.getIdTipo(),tmp.getTipoDescr());
					tipoRiproduzioneField.getStore().add(tmpPatr);
					return tmpPatr;
				}else return "Tipologia...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}

				VoceUnicaModel tmp=(VoceUnicaModel) value;
				return tmp.getEntry();
			}
		};

		ColumnConfig tipoRiproduzioneColumn = new ColumnConfig();

		tipoRiproduzioneColumn.setId("tipoDescr");
		tipoRiproduzioneColumn.setHeader("Riproduzione");
		tipoRiproduzioneColumn.setWidth(150);
		tipoRiproduzioneColumn.setEditor(tipoRiproduzioneEditor);

		configs.add(tipoRiproduzioneColumn);

		tipoRiproduzioneField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {

				if(se.getSelectedItem()!=null){
					if (modifica==false){
						ServiziRiproduzioniModel tmpModel =new ServiziRiproduzioniModel();
						tmpModel.setIdTipo(se.getSelectedItem().getIdRecord());
						tmpModel.setTipoDescr(se.getSelectedItem().getEntry());
						grid.getStore().getAt(0).setIdTipo(se.getSelectedItem().getIdRecord());

					}else{
						grid.getSelectionModel().getSelectedItem().setIdTipo(se.getSelectedItem().getIdRecord());
					}
				}
			}
		});

		final SimpleComboBox<String> servizioLocale = new SimpleComboBox<String>();
		servizioLocale.setForceSelection(true);
		servizioLocale.setTriggerAction(TriggerAction.ALL);
		servizioLocale.setEditable(false);
		servizioLocale.setFireChangeEventOnSetValue(true);
		servizioLocale.add("Si");
		servizioLocale.add("No");
		servizioLocale.setSimpleValue("No");

		CellEditor editorLocale = new CellEditor(servizioLocale) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return servizioLocale.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig columnServizioLocale = new ColumnConfig();

		columnServizioLocale.setId("locale");
		columnServizioLocale.setHeader("Locale");
		columnServizioLocale.setWidth(130);
		columnServizioLocale.setEditor(editorLocale);

		configs.add(columnServizioLocale);

		final SimpleComboBox<String> servizioNazionale = new SimpleComboBox<String>();
		servizioNazionale.setForceSelection(true);
		servizioNazionale.setTriggerAction(TriggerAction.ALL);
		servizioNazionale.setEditable(false);
		servizioNazionale.setFireChangeEventOnSetValue(true);
		servizioNazionale.add("Si");
		servizioNazionale.add("No");
		servizioNazionale.setSimpleValue("No");

		CellEditor editorNazionale = new CellEditor(servizioNazionale) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return servizioLocale.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig columnServizioNazionale = new ColumnConfig();

		columnServizioNazionale.setId("nazionale");
		columnServizioNazionale.setHeader("Nazionale");
		columnServizioNazionale.setWidth(130);
		columnServizioNazionale.setEditor(editorNazionale);

		configs.add(columnServizioNazionale);


		final SimpleComboBox<String> servizioInternazionale = new SimpleComboBox<String>();
		servizioInternazionale.setForceSelection(true);
		servizioInternazionale.setTriggerAction(TriggerAction.ALL);
		servizioInternazionale.setEditable(false);
		servizioInternazionale.setFireChangeEventOnSetValue(true);
		servizioInternazionale.add("Si");
		servizioInternazionale.add("No");
		servizioInternazionale.setSimpleValue("No");

		CellEditor editorInternazionale = new CellEditor(servizioInternazionale) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return servizioInternazionale.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig columnServizioInternazionale = new ColumnConfig();

		columnServizioInternazionale.setId("internazionale");
		columnServizioInternazionale.setHeader("Internazionale");
		columnServizioInternazionale.setWidth(130);
		columnServizioInternazionale.setEditor(editorInternazionale);

		configs.add(columnServizioInternazionale);


		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<ServiziRiproduzioniModel> re = new RowEditorCustom<ServiziRiproduzioniModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<ServiziRiproduzioniModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		//Servizio per il caricamento dei dati nello store della griglia
		RpcProxy<List<ServiziRiproduzioniModel>> serviziRiproduzioniProxy = new RpcProxy<List<ServiziRiproduzioniModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<ServiziRiproduzioniModel>> callback) {

				bibliotecheService.getServiziRiproduzioniFinitureByIdBiblio(id_biblioteca, callback);
			}

		};

		ModelReader serviziRiproduzioniReader = new ModelReader();

		serviziRiproduzioniLoader  = new BaseListLoader<ListLoadResult<ServiziRiproduzioniModel>>(
				serviziRiproduzioniProxy, serviziRiproduzioniReader);

		serviziRiproduzioniStore = new ListStore<ServiziRiproduzioniModel>(serviziRiproduzioniLoader);


		grid = new Grid<ServiziRiproduzioniModel>(
				serviziRiproduzioniStore, cm);
		grid.setBorders(true);
		grid.setStripeRows(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Inserisci una riproduzione:"));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());

		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				ServiziRiproduzioniModel newServizio = new ServiziRiproduzioniModel();
				newServizio.set("locale", "No");
				newServizio.set("nazionale", "No");
				newServizio.set("internazionale","No");
				re.stopEditing(false);
				serviziRiproduzioniStore.insert(newServizio, 0);
				re.startEditing(serviziRiproduzioniStore.indexOf(newServizio), false);
				remove.disable();
				
				tipoRiproduzioneField.clearInvalid();
			}
		});

		toolBar.add(add);

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int idRemove= grid.getSelectionModel().getSelectedItem().getIdTipo();
							bibliotecheService.removeServiziRiproduzioniForniture(idRemove,id_biblioteca,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									serviziRiproduzioniLoader.load();
									remove.disable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										serviziRiproduzioniLoader.load();
										remove.disable();
									}
								}

							});

						} else {

						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);
				if (grid.getStore().getCount() == 0) {
					ce.<Component> getComponent().disable();
				}
			}

		});
		remove.disable();


		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

		grid.addListener(Events.RowClick,new Listener<GridEvent<ServiziRiproduzioniModel>>() {

			public void handleEvent(GridEvent<ServiziRiproduzioniModel> be) {

				remove.enable();
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<ServiziRiproduzioniModel>>() {

			public void handleEvent(GridEvent<ServiziRiproduzioniModel> be) {
				tipoRiproduzioneField.disable();
				modifica=true;
				remove.disable();
			}
		});

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				tipoRiproduzioneField.enable();
				modifica=false;
				serviziRiproduzioniLoader.load();
			}
		});
		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						ServiziRiproduzioniModel tmpServizio = new ServiziRiproduzioniModel();
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							
							if(servizioLocale.getValue()!=null){
								tmpServizio.setLocale(servizioLocale.getValue().getValue());
							}
							else tmpServizio.setLocale("No");
							
							if(servizioNazionale.getValue()!=null){
								tmpServizio.setNazionale(servizioNazionale.getValue().getValue());
							}
							else tmpServizio.setNazionale("No");

							if(servizioInternazionale.getValue()!=null){
								tmpServizio.setInternazionale(servizioInternazionale.getValue().getValue());
							}
							else tmpServizio.setInternazionale("No");

							if (modifica==false) {
								tmpServizio.setIdTipo(grid.getStore().getAt(0).getIdTipo());
								tmpServizio.setTipoDescr(grid.getStore().getAt(0).getTipoDescr());
							}else{
								tmpServizio.setIdTipo(grid.getSelectionModel().getSelectedItem().getIdTipo());
								tmpServizio.setTipoDescr(grid.getSelectionModel().getSelectedItem().getTipoDescr());
							}
							bibliotecheService.addServiziRiproduzioniForniture(id_biblioteca, tmpServizio,	modifica,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									serviziRiproduzioniLoader.load();
									modifica = false;
									tipoRiproduzioneField.enable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										tipoRiproduzioneField.enable();
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										serviziRiproduzioniLoader.load();
										modifica = false;
									}
								}

							});
						} else {
							if (modifica == false) {
								serviziRiproduzioniStore.remove(0);
							} else {
								modifica = false;
							}
							serviziRiproduzioniLoader.load();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	/**
	 * Ritorna l'oggeto di tipo loader per il caricamento dei dati nello
	 * store della Grid
	 * */
	public BaseListLoader<ListLoadResult<ServiziRiproduzioniModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.serviziRiproduzioniLoader;
	}
	/**
	 * Setta va variabile id_biblioteca
	 * */
	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}
}
