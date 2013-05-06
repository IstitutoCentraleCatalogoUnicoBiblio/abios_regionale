package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.DepositiLegaliModel;
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
import com.extjs.gxt.ui.client.widget.form.TextField;
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
 * relative alla lista di depositi legali
 *
 */
public class ListaDepositiLegaliPanel extends ContentPanel {
	
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private int id_biblioteca;
	private BaseListLoader<ListLoadResult<DepositiLegaliModel>> loaderDepositoLegaleGriglia;
	private ListStore<DepositiLegaliModel> storeGriglia;
	private boolean modifica;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<DepositiLegaliModel> grid;

	public ListaDepositiLegaliPanel() {
		bibliotecheServiceAsync = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService = (TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

		modifica = false;
	}
	
	public void setGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<VoceUnicaModel>> proxyDepositoLegaleCombo = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.DEPOSITI_LEGALI_TIPOLOGIE_INDEX, callback);
			}
		};
		
		ModelReader readerDepositoLegaleCombo = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderDepositoLegaleCombo = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyDepositoLegaleCombo, readerDepositoLegaleCombo);

		final ListStore<VoceUnicaModel> listStoreDepositoLegaleCombo = new ListStore<VoceUnicaModel>(loaderDepositoLegaleCombo);
		final ComboBox<VoceUnicaModel> depositoLegaleType = new ComboBox<VoceUnicaModel>();
		depositoLegaleType.setDisplayField("entry");
		depositoLegaleType.setFireChangeEventOnSetValue(true);
		depositoLegaleType.setStore(listStoreDepositoLegaleCombo);
		depositoLegaleType.setAllowBlank(false);
		depositoLegaleType.setEmptyText("Deposito tipo...");
		depositoLegaleType.setForceSelection(false);
		depositoLegaleType.setLazyRender(false);
		depositoLegaleType.setTriggerAction(TriggerAction.ALL);
		depositoLegaleType.setEditable(false);
		loaderDepositoLegaleCombo.load();

		CellEditor editor = new CellEditor(depositoLegaleType) {
			@Override
			public Object preProcessValue(Object value) {
				if (modifica && value != null) {
					return depositoLegaleType.getStore().findModel("entry",	value.toString());
					
				} else {
					return null;
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("entry");
			}
		};

		ColumnConfig columnTipo = new ColumnConfig();

		columnTipo.setId("depositoDescr");
		columnTipo.setHeader("Tipo");
		columnTipo.setWidth(200);
		columnTipo.setEditor(editor);

		configs.add(columnTipo);

		TextField<String> daAnno = new TextField<String>();
		ColumnConfig columnDaAnno = new ColumnConfig();
		columnDaAnno.setId("depositoAnno");
		columnDaAnno.setHeader("Da anno");
		columnDaAnno.setWidth(200);
		columnDaAnno.setEditor(new CellEditor(daAnno));

		configs.add(columnDaAnno);

		RpcProxy<List<DepositiLegaliModel>> proxyDepositoLegaleGriglia = new RpcProxy<List<DepositiLegaliModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<DepositiLegaliModel>> callback) { 
				bibliotecheServiceAsync.getDepositiLegaliByIdBiblio( id_biblioteca, callback);
			}
		};
		
		ModelReader readerDepositoLegaleGriglia = new ModelReader();

		loaderDepositoLegaleGriglia = new BaseListLoader<ListLoadResult<DepositiLegaliModel>>(proxyDepositoLegaleGriglia, readerDepositoLegaleGriglia);

		storeGriglia = new ListStore<DepositiLegaliModel>(loaderDepositoLegaleGriglia);

		depositoLegaleType.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					grid.getStore().getAt(0).setIdDepositoTipo(se.getSelectedItem().getIdRecord());
				}
			}
		});
		
		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<DepositiLegaliModel>(storeGriglia, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);
		toolBar = new ToolBar();
		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Depositi legali "));

		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				DepositiLegaliModel newDep = new DepositiLegaliModel();
				re.stopEditing(false);
				storeGriglia.insert(newDep, 0);
				re.startEditing(storeGriglia.indexOf(newDep), false);
				
				depositoLegaleType.clearInvalid();
			}
		});
		toolBar.add(add);

		remove = new Button("Rimuovi");
		remove.setIcon(Resources.ICONS.delete());
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_rimuoviDepositoLegale = grid.getSelectionModel().getSelectedItem().getIdDepositoTipo();
							
							bibliotecheServiceAsync.removeDepositoLegaleFromBiblio(id_biblioteca, id_rimuoviDepositoLegale,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											loaderDepositoLegaleGriglia.load();
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

									});
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
		
		grid.addListener(Events.RowClick, new Listener<GridEvent<DepositiLegaliModel>>() {

			public void handleEvent(GridEvent<DepositiLegaliModel> be) {
				remove.enable();
				modifica = false;
			}
		});
		
		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<DepositiLegaliModel>>() {

			public void handleEvent(GridEvent<DepositiLegaliModel> be) {
				depositoLegaleType.disable();
				remove.disable();
				modifica = true;
			}
		});

		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					storeGriglia.remove(0);
				}
				modifica = false;
				depositoLegaleType.enable();
				remove.disable();
			}	
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_nuovoTipoDeposito;
							String anno = null;
							
							if (modifica == false) {
								id_nuovoTipoDeposito = grid.getStore().getAt(0).getIdDepositoTipo();
								anno = grid.getStore().getAt(0).getDepositoAnno();
								
							} else {
								id_nuovoTipoDeposito = grid.getSelectionModel().getSelectedItem().getIdDepositoTipo();
								anno = grid.getSelectionModel().getSelectedItem().getDepositoAnno();
							}
							
							bibliotecheServiceAsync.addDepositoLegaleToBiblio(id_biblioteca, modifica, id_nuovoTipoDeposito,
									anno, new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									loaderDepositoLegaleGriglia.load();	
									modifica = false;
									depositoLegaleType.enable();
									if (result.booleanValue()) {
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										
									} else {
										AbiMessageBox.messageAlertBox(AbiMessageBox.ESITO_VOCE_GIA_PRESENTE_MESSAGE, AbiMessageBox.ESITO_VOCE_GIA_PRESENTE_TITLE);
									}
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										loaderDepositoLegaleGriglia.load();	
										modifica = false;
										depositoLegaleType.enable();
									}
								}


							});
							
						} else {
							if (modifica == false) {
								storeGriglia.remove(0);
							}
							depositoLegaleType.enable();
							loaderDepositoLegaleGriglia.load();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}

	public BaseListLoader<ListLoadResult<DepositiLegaliModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderDepositoLegaleGriglia;	
	}
}
