package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.ComuniModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.extjs.gxt.ui.client.event.FieldEvent;
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
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista dei punti decentrati
 *
 */
public class ListaPuntiDecentratiPanel extends ContentPanel {

	private int id_biblio;
	private boolean modifica;
	private int old_id_biblio;
	private BaseListLoader<ListLoadResult<ComuniModel>> puntiServizioDecentratiLoader;


	private ToolBar toolBar = null;
	private Button remove = null;
	private Button add = null;
	private Grid<BiblioModel> grid = null;

	private ComboBox<BiblioModel> denominazioneUfficiale;
	private ComboBox<VoceUnicaModel> codiceIsilProvinciaField;
	private TextFieldCustom<String> codiceIsilNumeroField;

	public ListaPuntiDecentratiPanel() {

		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
		modifica = false;

	}

	public Grid<BiblioModel> getGrid() {
		return this.grid;
	}

	public void setGrid() {

		final BibliotecheServiceAsync bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);
		final RowEditorCustom<BiblioModel> re = new RowEditorCustom<BiblioModel>();
		re.setErrorSummary(false);
		re.disable();

		RowEditor<BiblioModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		denominazioneUfficiale = new ComboBox<BiblioModel>();
		denominazioneUfficiale.setDisplayField("denominazione");
		denominazioneUfficiale.setFieldLabel("denominazione");
		denominazioneUfficiale.setEmptyText("Seleziona denominazione...");
		denominazioneUfficiale.setFireChangeEventOnSetValue(true);
		denominazioneUfficiale.setLazyRender(false);
		denominazioneUfficiale.setTriggerAction(TriggerAction.ALL);
		denominazioneUfficiale.setForceSelection(false);
		denominazioneUfficiale.setEditable(true);
		denominazioneUfficiale.setAllowBlank(false);
		denominazioneUfficiale.setTypeAhead(false);
		denominazioneUfficiale.setMinChars(1);
		denominazioneUfficiale.setPageSize(10);
		denominazioneUfficiale.disable();

		denominazioneUfficiale.addListener(Events.OnBlur,
				new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				denominazioneUfficiale.setEnabled(false);
			}
		});

		CellEditor editor = new CellEditor(denominazioneUfficiale) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					return denominazioneUfficiale.getStore().findModel("denominazione",
							value.toString());

				} else
					return "Denominazione...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("denominazione");
			}
		};

		ColumnConfig columnDenominazione = new ColumnConfig();
		columnDenominazione.setId("denominazione");
		columnDenominazione.setHeader("Denominazione");
		columnDenominazione.setWidth(400);
		columnDenominazione.setEditor(editor);
		configs.add(columnDenominazione);

		RpcProxy<PagingLoadResult<BiblioModel>> puntiDecPossibiliProxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
				((ModelData) loadConfig).remove("isil_provincia");
				if (codiceIsilProvinciaField != null && codiceIsilProvinciaField.getValue() != null) {
					((ModelData) loadConfig).set("isil_provincia", codiceIsilProvinciaField.getValue().getEntry());
				}
				bibliotecheServiceAsync.getPuntiDiServizioDecentratiPossibili((ModelData) loadConfig, callback);
			}

		};
		ModelReader puntiDecPossibiliReader = new ModelReader();

		final PagingLoader<PagingLoadResult<BiblioModel>> puntiDecPossibiliLoader = new BasePagingLoader<PagingLoadResult<BiblioModel>>(
				puntiDecPossibiliProxy, puntiDecPossibiliReader);
		puntiDecPossibiliLoader.setLimit(10);

		final ListStore<BiblioModel> puntiDecPossibiliStore = new ListStore<BiblioModel>(
				puntiDecPossibiliLoader);

		denominazioneUfficiale.setStore(puntiDecPossibiliStore);
		puntiDecPossibiliLoader.load();

		ColumnConfig columnCodiceIsilProvincia = new ColumnConfig();
		columnCodiceIsilProvincia.setId("isil_provincia");
		columnCodiceIsilProvincia.setHeader("Isil Provincia");
		columnCodiceIsilProvincia.setWidth(80);

		codiceIsilProvinciaField = new ComboBox<VoceUnicaModel>();
		codiceIsilProvinciaField.setFieldLabel("Isil Provincia");
		codiceIsilProvinciaField.setDisplayField("entry");
		codiceIsilProvinciaField.setUseQueryCache(false);
		codiceIsilProvinciaField.setEmptyText("Isil provincia...");
		codiceIsilProvinciaField.setLazyRender(false);
		codiceIsilProvinciaField.setTriggerAction(TriggerAction.ALL);
		codiceIsilProvinciaField.setForceSelection(true);
		codiceIsilProvinciaField.setTypeAhead(false);
		codiceIsilProvinciaField.setEditable(true);

		codiceIsilProvinciaField.setMinChars(1);
		codiceIsilProvinciaField.setMaxLength(2);

		RpcProxy<List<VoceUnicaModel>> isilProvinciaProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getListaIsilProvincia((ModelData) loadConfig, callback);
			}

		};

		ModelReader isilProvinciaReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> isilProvinciaLoader = new BaseListLoader<ListLoadResult<ModelData>>(isilProvinciaProxy, isilProvinciaReader);

		final ListStore<VoceUnicaModel> isilProvinciaStore = new ListStore<VoceUnicaModel>(isilProvinciaLoader);

		codiceIsilProvinciaField.setStore(isilProvinciaStore);

		isilProvinciaLoader.load();

		columnCodiceIsilProvincia.setEditor(new CellEditor(codiceIsilProvinciaField));
		configs.add(columnCodiceIsilProvincia);

		codiceIsilProvinciaField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					denominazioneUfficiale.setAllowBlank(false);
					denominazioneUfficiale.enable();
					denominazioneUfficiale.clear();
					codiceIsilNumeroField.enable();
					codiceIsilNumeroField.clear();
					puntiDecPossibiliLoader.load();

				}
			}
		});

		denominazioneUfficiale.addSelectionChangedListener(new SelectionChangedListener<BiblioModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<BiblioModel> se) {
				if (se.getSelectedItem() != null) {
					codiceIsilNumeroField.setValue(se.getSelectedItem().getIsilNumero());
					codiceIsilNumeroField.disable();
				}

			}
		});
		
		
		ColumnConfig columnCodiceIsilNumero = new ColumnConfig();
		columnCodiceIsilNumero.setId("isil_numero");
		columnCodiceIsilNumero.setHeader("Isil Numero");
		columnCodiceIsilNumero.setWidth(80);

		codiceIsilNumeroField = new TextFieldCustom<String>();
		codiceIsilNumeroField.setMinLength(4);
		codiceIsilNumeroField.setMaxLength(4);
		codiceIsilNumeroField.setEmptyText("Isil numero...");
		columnCodiceIsilNumero.setEditor(new CellEditor(codiceIsilNumeroField));
		configs.add(columnCodiceIsilNumero);

		codiceIsilNumeroField.addListener(Events.OnBlur, new Listener<FieldEvent>() {
			@Override
			public void handleEvent(FieldEvent be) {
				if (codiceIsilNumeroField.getValue() != null && codiceIsilNumeroField.getValue().length() == 4) {
					denominazioneUfficiale.setAllowBlank(true);
					denominazioneUfficiale.disable();
				}

			}

		});

		/* SERVIZIO CARICAMENTO PUNTI DECENTRATI */
		RpcProxy<List<BiblioModel>> puntiServizioDecentratiProxy = new RpcProxy<List<BiblioModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<BiblioModel>> callback) {
				bibliotecheServiceAsync.getPuntiDiServizioDecentratiByIdBiblioteca(id_biblio, callback);
			}
		};

		ModelReader puntiServizioDecentratiReader = new ModelReader();

		puntiServizioDecentratiLoader = new BaseListLoader<ListLoadResult<ComuniModel>>(puntiServizioDecentratiProxy, puntiServizioDecentratiReader);

		final ListStore<BiblioModel> puntiServizioDecentratiStore = new ListStore<BiblioModel>(puntiServizioDecentratiLoader);

		puntiServizioDecentratiLoader.load();

		/* END---SERVIZIO CARICAMENTO PUNTI DECENTRATI */
		ColumnModel cm = new ColumnModel(configs);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					puntiServizioDecentratiStore.remove(0);
				}
			}

		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				/*
				 * Nel caso della modifica di un punto di servizio viene rimosso
				 * quello precedente e soltanto in seguito eseguitop il
				 * salvataggio di quello aggiornato
				 */
				if (modifica == true) {
					bibliotecheServiceAsync.removePuntoDiServizioDecentrato(
							old_id_biblio, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {

									modifica = false;
									re.disable();
									// puntiServizioDecentratiLoader.load();
									denominazioneUfficiale.disable();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Si è verificato un errore durante il salvataggio del contatto!", "ERRORE SALVATAGGIO");
										modifica = false;
										re.disable();
										denominazioneUfficiale.disable();
									}
									return;
								}

							});
				}
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String isilPR = new String();
							String isilNR = new String();

							if (denominazioneUfficiale.getValue() != null) {
								/* E' stata selezionata una biblioteca dal menu di scelta */
								isilPR = ((BiblioModel) denominazioneUfficiale.getValue()).getIsilProvincia();
								isilNR = ((BiblioModel) denominazioneUfficiale.getValue()).getIsilNumero();

							} else {
								isilPR = codiceIsilProvinciaField.getValue().getEntry();
								isilNR = codiceIsilNumeroField.getValue();
							}

							if ((isilPR != null && isilPR.length() > 0) && (isilNR != null && isilNR.length() > 0)) {

								bibliotecheServiceAsync.addPuntoDiServizioDecentrato(id_biblio, isilPR, isilNR, new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

										modifica = false;
										puntiServizioDecentratiLoader.load();
										denominazioneUfficiale.disable();

									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											modifica = false;
											denominazioneUfficiale.disable();
										}
									}

								});
							}

						} else {
							if (modifica == false) {
								re.disable();
								puntiServizioDecentratiStore.remove(0);
							}
						}

					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});



		grid = new Grid<BiblioModel>(puntiServizioDecentratiStore, cm);
		grid.getView().setAutoFill(true);

		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);

		toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);

		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Inserisci il punto di servizio decentrato:"));

		add = new Button("Aggiungi");
		remove = new Button("Rimuovi");

		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				BiblioModel bm = new BiblioModel();

				bm.setDenominazione("Denominazione...");

				ListaPuntiDecentratiModel newPsd = new ListaPuntiDecentratiModel();
				newPsd.setPuntoDecentrato(bm);

				newPsd.setIsilNumero("Codice...");
				re.enable();
				re.stopEditing(false);
				puntiServizioDecentratiStore.insert(newPsd, 0);
				re.startEditing(puntiServizioDecentratiStore.indexOf(newPsd),false);
				modifica = false;
				remove.disable();

				denominazioneUfficiale.clearInvalid();
				codiceIsilProvinciaField.clearInvalid();

				denominazioneUfficiale.reset();
				codiceIsilProvinciaField.reset();

				denominazioneUfficiale.disable();

			}

		});

		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(final ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(final MessageBoxEvent me) {
						remove.disable();
						Button btn = me.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_biblioteca_da_rimuovere = grid.getSelectionModel().getSelectedItem().getIdBiblio();

							bibliotecheServiceAsync.removePuntoDiServizioDecentrato(id_biblioteca_da_rimuovere,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									remove.disable();
									puntiServizioDecentratiLoader.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

									denominazioneUfficiale.disable();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										remove.disable();
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										denominazioneUfficiale.disable();
									}
								}
							});

							if (grid.getStore().getCount() == 0) {
								ce.<Component> getComponent().disable();
							}
						}

					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

			}

		});
		remove.disable();
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<BiblioModel>>() {

			public void handleEvent(GridEvent<BiblioModel> be) {

				remove.enable();
				modifica = true;
				re.disable();
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<BiblioModel>>() {

			public void handleEvent(GridEvent<BiblioModel> be) {

				modifica = true;
				old_id_biblio = grid.getSelectionModel().getSelectedItem().getIdBiblio();
				re.disable();
			}
		});

		remove.setIcon(Resources.ICONS.delete());

		add(grid);
		setTopComponent(toolBar);
	}

	public void setIdBiblioteca(int id_biblio) {
		this.id_biblio = id_biblio;
	}

	public class ListaPuntiDecentratiModel extends BiblioModel implements	Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5101078736618793359L;
		BiblioModel b;

		public ListaPuntiDecentratiModel() {

		}

		public ListaPuntiDecentratiModel(BiblioModel puntoDecentrato,String isilProvincia, String isilNumero) {
			set("puntoDecentrato", puntoDecentrato);
			set("isilProvincia", isilProvincia);
			set("isilNumero", isilNumero);
		}

		public void setIsilProvincia(String isilProvincia) {
			set("isilProvincia", isilProvincia);
		}

		public String getIsilProvincia() {
			return get("isilProvincia");
		}

		public String getIsilNumero() {
			return get("isilNumero");
		}

		public void setIsilNumero(String isilNumero) {
			set("isilNumero", isilNumero);
		}

		public void setPuntoDecentrato(BiblioModel puntoDecentrato) {
			set("puntoDecentrato", puntoDecentrato);
		}

		public BiblioModel getPuntoDecentrato() {
			return get("puntoDecentrato");
		}
	}

	public BaseListLoader<ListLoadResult<ComuniModel>> getLoader() {
		UIWorkflow.gridEnableEvent(grid);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);

		return this.puntiServizioDecentratiLoader;
	}
}
