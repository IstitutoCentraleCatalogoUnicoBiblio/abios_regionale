/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.SistemiPrestitoInterbibliotecarioModel;
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
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
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
 * relative alla lista dei sistemi di prestito interbibliotecario
 *
 */
public class ListaSistemiPrestitoInterbibliotecarioPanel extends ContentPanel {
	
	private BibliotecheServiceAsync bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
	private TabelleDinamicheServiceAsync tabelleDinamicheService = (TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

	private boolean modifica = false;
	private boolean descrizioneSelected = false;

	private int id_biblioteca;

	private BaseListLoader<ListLoadResult<SistemiPrestitoInterbibliotecarioModel>> sistPrestInterbibLoader;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<SistemiPrestitoInterbibliotecarioModel> grid;

	public ListaSistemiPrestitoInterbibliotecarioPanel() {
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

		final ComboBox<SistemiPrestitoInterbibliotecarioModel> descrizione = new ComboBox<SistemiPrestitoInterbibliotecarioModel>();

		descrizione.setDisplayField("descrizione");
		descrizione.setFieldLabel("descrizione");
		descrizione.setFireChangeEventOnSetValue(true);
		descrizione.setEmptyText("Seleziona una descrizione...");
		descrizione.setWidth(280);
		descrizione.setLazyRender(false);
		descrizione.setTriggerAction(TriggerAction.ALL);
		descrizione.setAllowBlank(false);
		descrizione.setForceSelection(true);
		descrizione.setEditable(true);
		descrizione.setTypeAhead(false);
		descrizione.setMinChars(1);
		descrizione.setPageSize(10);

		CellEditor editor = new CellEditor(descrizione) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica) {
					return descrizione.getStore().findModel("descrizione", value.toString());

				} else {
					return  "Seleziona una descrizione...";
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (modifica == false) {
					if (value == null) {
						return value;
					} else {
						return ((ModelData) value).get("descrizione");
					}

				} else {
					return grid.getSelectionModel().getSelectedItem().getDescrizione();
				}
			} 
		};

		RpcProxy<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>> descrSistPrestInterbibProxy = new RpcProxy<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>> callback) {
				tabelleDinamicheService.getDescrizioneSistemiPrestitoInterbibliotecarioFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);

			}
		};

		ModelReader descrSistPrestInterbibReader = new ModelReader();

		PagingLoader<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>> descrSistPrestInterbibLoader = 
			new BasePagingLoader<PagingLoadResult<SistemiPrestitoInterbibliotecarioModel>>(descrSistPrestInterbibProxy, descrSistPrestInterbibReader);
		descrSistPrestInterbibLoader.setLimit(10);
		final ListStore<SistemiPrestitoInterbibliotecarioModel> descrSistPrestInterbibComboboxStore = 
			new ListStore<SistemiPrestitoInterbibliotecarioModel>(descrSistPrestInterbibLoader);

		descrizione.setStore(descrSistPrestInterbibComboboxStore);
		descrSistPrestInterbibLoader.load();

		ColumnConfig descrizioneColumn = new ColumnConfig();
		descrizioneColumn.setId("descrizione");
		descrizioneColumn.setHeader("Descrizione");
		descrizioneColumn.setWidth(280);
		descrizioneColumn.setEditor(editor);

		configs.add(descrizioneColumn);

		final TextField<String> urlField = new TextField<String>();
		urlField.setEmptyText("Url...");
		urlField.disable();
		ColumnConfig url = new ColumnConfig();
		url.setId("url");
		url.setHeader("Url");
		url.setWidth(400);

		url.setEditor(new CellEditor(urlField));
		configs.add(url);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<SistemiPrestitoInterbibliotecarioModel> re = new RowEditorCustom<SistemiPrestitoInterbibliotecarioModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);

		RowEditor<SistemiPrestitoInterbibliotecarioModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		RpcProxy<List<SistemiPrestitoInterbibliotecarioModel>> proxySistPrestInterbib = new RpcProxy<List<SistemiPrestitoInterbibliotecarioModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<SistemiPrestitoInterbibliotecarioModel>> callback) {
				bibliotecheService.getListaSistemiPrestitoInterbibliotecario(id_biblioteca, callback);
			}

		};

		ModelReader readerSistPrestInterbib = new ModelReader();
		sistPrestInterbibLoader = new BaseListLoader<ListLoadResult<SistemiPrestitoInterbibliotecarioModel>>(proxySistPrestInterbib, readerSistPrestInterbib);
		final ListStore<SistemiPrestitoInterbibliotecarioModel> store = new ListStore<SistemiPrestitoInterbibliotecarioModel>(sistPrestInterbibLoader);

		grid = new Grid<SistemiPrestitoInterbibliotecarioModel>(store, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		descrizione.addSelectionChangedListener(new SelectionChangedListener<SistemiPrestitoInterbibliotecarioModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SistemiPrestitoInterbibliotecarioModel> se) {
				if (se.getSelectedItem() != null && grid.getStore().getAt(0) != null) {
					if (modifica == false) {
						grid.getStore().getAt(0).setIdSistemiPrestitoInterbibliotecario(se.getSelectedItem().getIdSistemiPrestitoInterbibliotecario());
						grid.getStore().getAt(0).setUrl(se.getSelectedItem().getUrl());

					} else {
						grid.getSelectionModel().getSelectedItem().setIdSistemiPrestitoInterbibliotecario(se.getSelectedItem().getIdSistemiPrestitoInterbibliotecario());
						grid.getSelectionModel().getSelectedItem().setUrl(se.getSelectedItem().getUrl());
					}
					
					if (se.getSelectedItem().getUrl() != null) {
						urlField.setValue(se.getSelectedItem().getUrl());
						
					} else {
						urlField.clear();
					}
				}
			}
		});

		toolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolBar.addStyleName("font-weight-style");

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Prestito interbibliotecario: sistemi "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				re.enable();
				descrizione.clear();
				descrizione.setEditable(true);
				descrizione.setEnabled(true);
				urlField.clear();
				urlField.disable();
				remove.disable();

				SistemiPrestitoInterbibliotecarioModel newSist = new SistemiPrestitoInterbibliotecarioModel();

				re.stopEditing(false);
				store.insert(newSist, 0);
				re.startEditing(store.indexOf(newSist), false);

				/*Rimuove il problema del bordo rosso alla visualizzazione del roweditor*/
				descrizione.clearInvalid();

			}
		});

		toolBar.add(add);

		remove = new Button("Rimuovi", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				modifica = false;
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							bibliotecheService.removeSistemaPrestitoInterbibliotecario(id_biblioteca, grid.getSelectionModel().getSelectedItem().getIdSistemiPrestitoInterbibliotecario(), new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									sistPrestInterbibLoader.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}
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

		grid.addListener(Events.RowClick, new Listener<GridEvent<SistemiPrestitoInterbibliotecarioModel>>() {

			public void handleEvent(GridEvent<SistemiPrestitoInterbibliotecarioModel> be) {
				remove.enable();
				modifica = false;
			}
		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<SistemiPrestitoInterbibliotecarioModel>>() {

			public void handleEvent(GridEvent<SistemiPrestitoInterbibliotecarioModel> be) {
				re.disable();
				remove.disable();
			}
		});
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);
				}
				modifica = false;
				sistPrestInterbibLoader.load();
			}
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (descrizioneSelected) {
					be.setCancelled(true);
					descrizioneSelected = false;
					return;
				}

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_nuovoSist;

							if (modifica == false) {
								id_nuovoSist = store.getAt(0).getIdSistemiPrestitoInterbibliotecario();

							} else {
								id_nuovoSist = grid.getSelectionModel().getSelectedItem().getIdSistemiPrestitoInterbibliotecario();
							}
							
							modifica = false;

							bibliotecheService.addSistemaPrestitoInterbibliotecario(id_biblioteca, id_nuovoSist, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									sistPrestInterbibLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

										} else {
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}	
										sistPrestInterbibLoader.load();
										modifica = false;
									}
								}

							});
							
						} else {
							if (modifica == false) {
								store.remove(0);
							} else {
								modifica = false;
							}
							sistPrestInterbibLoader.load();
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

	public BaseListLoader<ListLoadResult<SistemiPrestitoInterbibliotecarioModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.sistPrestInterbibLoader;
	}

}
