package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.ContattiModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
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
 * Classe per la visualizzazione / modifica della lista dei contatti
 *
 */
public class ListaContattiPanel extends ContentPanel {
	private int id_biblio;
	private ListStore<ContattiModel> store;
	private RowEditorCustom<ContattiModel> re;
	private boolean modifica;
	private BibliotecheServiceAsync bibliotecheServiceAsync;
	private BaseListLoader<ListLoadResult<ModelData>> loaderContatti;

	private ToolBar toolBar = null;
	private Button remove = null;
	private Button add = null;
	private Grid<ContattiModel> grid = null;
	private ComboBox<VoceUnicaModel> tipo; 
	private TextField<String> valore;
	public ListaContattiPanel() {
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
		createToolbar();
		modifica = false;
	}

	public void createToolbar() {
		toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Recapiti: e-mail, telex o url "));
		add = new Button("Aggiungi recapito");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				ContattiModel newRecapito = new ContattiModel();
				newRecapito.setIdBiblioteca(id_biblio);
				re.stopEditing(true);
				store.insert(newRecapito, 0);
				re.startEditing(store.indexOf(newRecapito), false);
				modifica = false;
				remove.disable();
				
				tipo.clearInvalid();
				valore.clearInvalid();
			}

		});

		remove = new Button("Rimuovi", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(final ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {				

							int id_contatto = grid.getSelectionModel().getSelectedItem().getIdContatti();

							bibliotecheServiceAsync.removeContatto(id_contatto,
									new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {

									loaderContatti.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										loaderContatti.load();
									}
								}
							});

						}
					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

				if (grid.getStore().getCount() == 0) {
					ce.<Component> getComponent().disable();
				}
			}

		});
		remove.disable();

		remove.setIcon(Resources.ICONS.delete());

		setTopComponent(toolBar);
	}

	public void setGrid() {

		bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		/**/
		RpcProxy<List<VoceUnicaModel>> tipoContattiProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {

				bibliotecheServiceAsync.getTipologieContatti(callback);
			}
		};

		ModelReader tipoContattiReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderTipoContatti = new BaseListLoader<ListLoadResult<ModelData>>(
				tipoContattiProxy, tipoContattiReader);

		ListStore<VoceUnicaModel> tipoContattistore = new ListStore<VoceUnicaModel>(
				loaderTipoContatti);
		loaderTipoContatti.load();
		/**/

		tipo = new ComboBox<VoceUnicaModel>();
		tipo.setDisplayField("entry");

		tipo.setStore(tipoContattistore);
		tipo.setForceSelection(true);
		tipo.setTriggerAction(TriggerAction.ALL);
		tipo.setEditable(false);
		tipo.setFireChangeEventOnSetValue(true);

		tipo.setLazyRender(false);

		tipo.setAllowBlank(false);
		tipo.setEmptyText("Tipo...");

		tipo.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {

					if (modifica) {
						grid.getSelectionModel().getSelectedItem().setContattiTipo(se.getSelectedItem().getIdRecord());
						
					} else {
						grid.getStore().getAt(0).setContattiTipo(se.getSelectedItem().getIdRecord());
						if (se.getSelectedItem().getIdRecord().intValue() == 1 || se.getSelectedItem().getIdRecord().intValue() == 2) {
							if (valore != null) {
								valore.setValue("+39 ");
							}
							
						} else {
							if (valore != null) {
								valore.clear();
							}
						}
					}

				}
			}
		});
		CellEditor editor = new CellEditor(tipo) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					return tipo.getStore().findModel("entry", value.toString());

				} else
					return "Tipo...";
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

		columnTipo.setId("contattiTipoLabel");
		columnTipo.setHeader("Tipo");
		columnTipo.setWidth(130);
		columnTipo.setEditor(editor);

		configs.add(columnTipo);



		valore = new TextField<String>();
		valore.setAllowBlank(false);
		valore.setEmptyText("Digita il valore...");

		ColumnConfig columnValore = new ColumnConfig();
		columnValore.setId("valore");
		columnValore.setHeader("Valore");
		columnValore.setWidth(200);
		columnValore.setEditor(new CellEditor(valore));
		configs.add(columnValore);


		TextField<String> descrizione = new TextField<String>();
		descrizione.setEmptyText("Inserisci una descrizione...");

		ColumnConfig columnDescr = new ColumnConfig();
		columnDescr.setId("descr");
		columnDescr.setHeader("Descrizione");
		columnDescr.setWidth(400);

		columnDescr.setEditor(new CellEditor(descrizione));
		configs.add(columnDescr);

		ColumnModel cm = new ColumnModel(configs);

		re = new RowEditorCustom<ContattiModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.enableEvents(true);
		re.setErrorSummary(false);
		
		RowEditor<ContattiModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<ContattiModel>> recapitiProxy = new RpcProxy<List<ContattiModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<ContattiModel>> callback) {

				bibliotecheServiceAsync.getContattiBibliotecaById(id_biblio,
						callback);
			}
		};

		ModelReader contattiReader = new ModelReader();

		loaderContatti = new BaseListLoader<ListLoadResult<ModelData>>(
				recapitiProxy, contattiReader);

		store = new ListStore<ContattiModel>(loaderContatti);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);
				}

				modifica=false;
				loaderContatti.load();
			}

		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							ContattiModel nuovoContatto =null;
							if (modifica) {
								nuovoContatto = grid.getSelectionModel().getSelectedItem();
								
							} else {
								nuovoContatto = grid.getStore().getAt(0);
							}

							bibliotecheServiceAsync.saveContatti(nuovoContatto,
									modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									modifica = false;
									loaderContatti.load();

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										modifica = false;
										loaderContatti.load();
									}
								}

							});
						}

					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		grid = new Grid<ContattiModel>(store, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<ContattiModel>>() {

			public void handleEvent(GridEvent<ContattiModel> be) {

				remove.enable();
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<ContattiModel>>() {

			public void handleEvent(GridEvent<ContattiModel> be) {

				remove.disable();
				modifica = true;
			}
		});

		add(grid);

	}

	public void setIdBiblioteca(int id_biblio) {
		this.id_biblio = id_biblio;
	}

	public BaseListLoader<ListLoadResult<ModelData>> getLoader(){
		UIWorkflow.gridEnableEvent(grid);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);

		return this.loaderContatti;
	}
}
