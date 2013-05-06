package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista di tipi di document delivery
 *
 */
public class ListaDocumentDeliveryPanel extends ContentPanel {

	private int id_biblioteca;
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheService;

	private Grid<VoceUnicaModel> grid;
	private ToolBar toolBar;
	private Button add;
	private Button remove;

	private	ListStore<VoceUnicaModel> documentDeliveryStore ;
	private  BaseListLoader<ListLoadResult<VoceUnicaModel>> documentDeliveryLoader;
	
	public ListaDocumentDeliveryPanel() {
		bibliotecheService = (BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService = (TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}

	public void setGrid() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RpcProxy<List<VoceUnicaModel>> documentDeliveryComboProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.RIPRODUZIONI_TIPI_INDEX, callback);
			}
		};

		ModelReader documentDeliveryComboReader = new ModelReader();

		final BaseListLoader<ListLoadResult<VoceUnicaModel>> documentDeliveryComboLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				documentDeliveryComboProxy, documentDeliveryComboReader);

		final ListStore<VoceUnicaModel> documentDeliveryComboStore = new ListStore<VoceUnicaModel>(documentDeliveryComboLoader);

		/*
		 * ComboBox per caricamento lista
		 * document delivery possibili
		 */
		final ComboBox<VoceUnicaModel> documentDeliveryField = new ComboBox<VoceUnicaModel>();
		documentDeliveryField.setForceSelection(true);
		documentDeliveryField.setDisplayField("entry");
		documentDeliveryField.setTriggerAction(TriggerAction.ALL);
		documentDeliveryField.setEditable(false);
		documentDeliveryField.setEmptyText("Document delivery...");
		documentDeliveryField.setAllowBlank(false);
		documentDeliveryField.setFireChangeEventOnSetValue(true);
		documentDeliveryField.setStore(documentDeliveryComboStore);


		CellEditor documentDeliveryComboEditor = new CellEditor(documentDeliveryField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return "Document delivery...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				VoceUnicaModel tmp = (VoceUnicaModel) value;
				return tmp.getEntry();
			}
		};

		ColumnConfig documentDeliveryColumn = new ColumnConfig();
		documentDeliveryColumn.setId("entry");
		documentDeliveryColumn.setHeader("Document delivery");
		documentDeliveryColumn.setWidth(300);
		documentDeliveryColumn.setEditor(documentDeliveryComboEditor);

		configs.add(documentDeliveryColumn);

		documentDeliveryField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {

				if(se.getSelectedItem()!=null){
					grid.getStore().getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
				}
			}
		});

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.disable();
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<VoceUnicaModel>> documentDeliveryProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheService.getDocumentDeliveryByIdBiblio(id_biblioteca, callback);
			}
		};

		ModelReader documentDeliveryReader = new ModelReader();

		documentDeliveryLoader  = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				documentDeliveryProxy, documentDeliveryReader);

		documentDeliveryStore = new ListStore<VoceUnicaModel>(documentDeliveryLoader);


		grid = new Grid<VoceUnicaModel>(documentDeliveryStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		
		toolBar = new ToolBar();


		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Inserisci document delivery"));

		remove = new Button("Rimuovi");
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel newDocDel = new VoceUnicaModel();
				re.enable();
				re.stopEditing(false);
				documentDeliveryStore.insert(newDocDel, 0);
				re.startEditing(documentDeliveryStore.indexOf(newDocDel), false);

				documentDeliveryField.clearInvalid();
			}
		});
		toolBar.add(add);

		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						remove.disable();
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int idRemove= grid.getSelectionModel().getSelectedItem().getIdRecord();
							bibliotecheService.removeDocumentDelivery(id_biblioteca, idRemove, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									documentDeliveryLoader.load();
									remove.disable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										documentDeliveryLoader.load();
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
		grid.addListener(Events.RowClick,new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.enable();
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<VoceUnicaModel>>() {
			
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.disable();
			}
		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				re.disable();
				documentDeliveryLoader.load();
			}
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int idDocumentDelivery = grid.getStore().getAt(0).getIdRecord();
							bibliotecheService.addDocumentDelivery(id_biblioteca, idDocumentDelivery, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									documentDeliveryLoader.load();
									re.disable();
									documentDeliveryField.enable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										documentDeliveryField.enable();
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										documentDeliveryLoader.load();
										re.disable();
									}
								}

							});
						} else {
							re.disable();
							documentDeliveryLoader.load();
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
	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.documentDeliveryLoader;
	}
	/**
	 * Setta la variabile id_biblioteca
	 * */
	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}
}
