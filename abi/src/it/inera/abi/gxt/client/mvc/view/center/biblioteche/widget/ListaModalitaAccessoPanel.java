package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
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
 * relative alla lista delle modalità d'accesso
 *
 */
public class ListaModalitaAccessoPanel extends ContentPanel{ 

	private int id_biblioteca;
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> modalitaGrigliaByIdBiblioLoader;
	private ComboBox<VoceUnicaModel> modalitaAccessoField;
	private RowEditorCustom<VoceUnicaModel> re; 
	private Grid<VoceUnicaModel> grid;

	private ToolBar toolBar;
	private Button add;
	private Button remove;

	public ListaModalitaAccessoPanel() {
		bibliotecheServiceAsync=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService=(TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(700);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}

	public void setGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		RpcProxy<List<VoceUnicaModel>> proxymodalitaAccesso = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.ACCESSO_MODALITA_INDEX, callback);
			}

		};
		ModelReader modalitaAccessoReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderModalitaAccesso = new BaseListLoader<ListLoadResult<ModelData>>(
				proxymodalitaAccesso, modalitaAccessoReader);

		ListStore<VoceUnicaModel> listStoreModalitaAccesso = new ListStore<VoceUnicaModel>(loaderModalitaAccesso);
		modalitaAccessoField = new ComboBox<VoceUnicaModel>();
		modalitaAccessoField.setFieldLabel("modalità accesso");
		modalitaAccessoField.setWidth(250);
		modalitaAccessoField.setDisplayField("entry");
		modalitaAccessoField.setStore(listStoreModalitaAccesso);
		modalitaAccessoField.setAllowBlank(false);
		modalitaAccessoField.setFireChangeEventOnSetValue(true);
		modalitaAccessoField.setEmptyText("Scegli una modalità...");
		modalitaAccessoField.setForceSelection(true);
		modalitaAccessoField.setLazyRender(false);
		modalitaAccessoField.setTriggerAction(TriggerAction.ALL);
		modalitaAccessoField.setEditable(false);

		re = new RowEditorCustom<VoceUnicaModel>();
		re.setErrorSummary(false);
		re.disable();
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<VoceUnicaModel>> modalitaGrigliaProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getModalitaAccessoByIdBiblioteca(id_biblioteca, callback);
			}

		};

		ModelReader modalitaGrigliaByIdBiblioReader = new ModelReader();

		modalitaGrigliaByIdBiblioLoader =
			new BaseListLoader<ListLoadResult<VoceUnicaModel>>(modalitaGrigliaProxy, modalitaGrigliaByIdBiblioReader);
		final ListStore<VoceUnicaModel> storeModalitaGriglia  = new ListStore<VoceUnicaModel>(
				modalitaGrigliaByIdBiblioLoader);

		CellEditor editor = new CellEditor(modalitaAccessoField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return modalitaAccessoField.getStore().findModel("entry", value.toString());
			}


			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("entry");
			}
		};

		final ColumnConfig columnTipo = new ColumnConfig();

		columnTipo.setId("entry");
		columnTipo.setHeader("Tipo");
		columnTipo.setWidth(400);
		columnTipo.setEditor(editor);
		
		configs.add(columnTipo);

		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<VoceUnicaModel>(storeModalitaGriglia,cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		
		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.setStyleAttribute("marginTop", "5");
		toolBar.add(new Text("Modalità di accesso"));
		
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				re.enable();
				VoceUnicaModel v = new VoceUnicaModel();
				re.stopEditing(false);
				storeModalitaGriglia.insert(v, 0);
				
				re.startEditing(storeModalitaGriglia.indexOf(v), false);
			
				modalitaAccessoField.clearInvalid();
			}
		});

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_rimuoviModalita = grid.
							getSelectionModel().
							getSelectedItem().
							getIdRecord();
							bibliotecheServiceAsync.removeModalitaAccesso(
									id_biblioteca, id_rimuoviModalita,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											modalitaGrigliaByIdBiblioLoader.load();
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
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

		grid.addListener(Events.RowClick, new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				re.disable();
				remove.enable();
			}
		});
		remove.setIcon(Resources.ICONS.delete());


		add(grid);
		setTopComponent(toolBar);

		modalitaAccessoField
		.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					grid.getStore().getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
					
				}
				
			}
		});


		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

				@Override
				public void handleEvent(MessageBoxEvent ce) {
					Button btn = ce.getButtonClicked();
					if (btn.getText().equalsIgnoreCase("Si")) {
						int id_nuovaModalita = storeModalitaGriglia
						.getAt(0).getIdRecord();
						bibliotecheServiceAsync.addModalitaAccesso(
								id_biblioteca, id_nuovaModalita,
								new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										modalitaGrigliaByIdBiblioLoader.load();
										re.disable();
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										re.disable();
									}

								});
					} else {
						storeModalitaGriglia.remove(0);
						re.disable();
					}
				}

			};
			AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				storeModalitaGriglia.remove(0);
				re.disable();
			}

		});
	}
	
	public void setIdBiblioteca(int idBiblio) {
		id_biblioteca = idBiblio;

	}
	
	public  BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);


		return modalitaGrigliaByIdBiblioLoader;
	}
}