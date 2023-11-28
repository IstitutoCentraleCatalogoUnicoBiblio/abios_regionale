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
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.DestinazioneSocialeModel;
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
 * relative alla lista di destinazioni sociali
 *
 */
public class ListaDestinazioneSocialePanel extends ContentPanel {
	
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private int id_biblioteca;
	private BaseListLoader<ListLoadResult<DestinazioneSocialeModel>> loaderDestinazioneSocialeGriglia;
	private boolean modifica;

	private Grid<DestinazioneSocialeModel> grid;
	private Button add;
	private Button remove;
	private ToolBar toolBar;

	public ListaDestinazioneSocialePanel() {
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
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages =re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<VoceUnicaModel>> proxyDestinazioneSocialeCombo = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.DESTINAZIONI_SOCIALI_TIPOLOGIE_INDEX, callback);
			}
		};
		
		ModelReader readerDestinazioneSocialeCombo = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderDestinazioneSocialeCombo = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyDestinazioneSocialeCombo, readerDestinazioneSocialeCombo);

		final ListStore<VoceUnicaModel> listStoreDestinazioneSocialeCombo = new ListStore<VoceUnicaModel>(loaderDestinazioneSocialeCombo);
		
		final ComboBox<VoceUnicaModel> destinazioneSocialeType = new ComboBox<VoceUnicaModel>();
		destinazioneSocialeType.setDisplayField("entry");
		destinazioneSocialeType.setFieldLabel("modalità accesso");
		destinazioneSocialeType.setFireChangeEventOnSetValue(true);
		destinazioneSocialeType.setWidth(300);
		destinazioneSocialeType.setStore(listStoreDestinazioneSocialeCombo);
		destinazioneSocialeType.setAllowBlank(false);
		destinazioneSocialeType.setEmptyText("Tipo...");
		destinazioneSocialeType.setForceSelection(false);
		destinazioneSocialeType.setLazyRender(false);
		destinazioneSocialeType.setTriggerAction(TriggerAction.ALL);
		destinazioneSocialeType.setEditable(false);
		loaderDestinazioneSocialeCombo.load();

		CellEditor editor = new CellEditor(destinazioneSocialeType) {
			@Override
			public Object preProcessValue(Object value) {
				if (modifica && value != null) {
					VoceUnicaModel entry = destinazioneSocialeType.getStore().findModel("entry", value.toString());
					return entry;

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

		columnTipo.setId("descrizione");
		columnTipo.setHeader("Tipo");
		columnTipo.setWidth(200);
		columnTipo.setEditor(editor);

		configs.add(columnTipo);

		ColumnConfig columnNote = new ColumnConfig();
		columnNote.setId("note");
		columnNote.setHeader("Note");
		columnNote.setWidth(350);

		final TextField<String> note = new TextField<String>();
		columnNote.setEditor(new CellEditor(note));

		configs.add(columnNote);

		RpcProxy<List<DestinazioneSocialeModel>> proxyDestinazioneSocialeGriglia = new RpcProxy<List<DestinazioneSocialeModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<DestinazioneSocialeModel>> callback) {
				bibliotecheServiceAsync.getDestinazioniSociali( id_biblioteca, callback);
			}
		};
		
		ModelReader readerDestinazioneSocialeGriglia = new ModelReader();

		loaderDestinazioneSocialeGriglia = new BaseListLoader<ListLoadResult<DestinazioneSocialeModel>>(proxyDestinazioneSocialeGriglia, readerDestinazioneSocialeGriglia);

		final ListStore<DestinazioneSocialeModel> storeGriglia = new ListStore<DestinazioneSocialeModel>(loaderDestinazioneSocialeGriglia);

		destinazioneSocialeType.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					storeGriglia.getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
				}
			}
		});
		
		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<DestinazioneSocialeModel>(storeGriglia, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();
		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Destinazioni sociali "));

		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				DestinazioneSocialeModel newDest = new DestinazioneSocialeModel();
				re.stopEditing(false);
				storeGriglia.insert(newDest, 0);
				re.startEditing(storeGriglia.indexOf(newDest), false);

				destinazioneSocialeType.clearInvalid();
			}
		});
		toolBar.add(add);

		remove = new Button("Rimuovi");
		remove.setIcon(Resources.ICONS.delete());
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_rimuoviModalita = grid.getSelectionModel().getSelectedItem().getIdRecord();
							
							bibliotecheServiceAsync.removeDestinazioneSociale(id_biblioteca, id_rimuoviModalita, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									remove.disable();
									loaderDestinazioneSocialeGriglia.load();
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
		toolBar.add(remove);
		
		remove.disable();
		grid.addListener(Events.RowClick, new Listener<GridEvent<DestinazioneSocialeModel>>() {

			public void handleEvent(GridEvent<DestinazioneSocialeModel> be) {
				remove.enable();
			}
		});
		grid.addListener(Events.RowDoubleClick,	new Listener<GridEvent<DestinazioneSocialeModel>>() {

			public void handleEvent(GridEvent<DestinazioneSocialeModel> be) {
				destinazioneSocialeType.disable();
				remove.disable();
				modifica = true;
			}
		});

		add(grid);
		setTopComponent(toolBar);
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					storeGriglia.remove(0);
				}
				modifica = false;
				destinazioneSocialeType.enable();
				loaderDestinazioneSocialeGriglia.load();
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
							int id_nuovaDestinazione;
							String noteVal = null;
							if (modifica == false) {
								id_nuovaDestinazione = storeGriglia.getAt(0).getIdRecord();
								noteVal = note.getValue();
								
							} else {
								id_nuovaDestinazione = grid.getSelectionModel().getSelectedItem().getIdRecord();
								noteVal = grid.getSelectionModel().getSelectedItem().getNote();
							}
							
							bibliotecheServiceAsync.addDestinazioneSociale(id_biblioteca, modifica, id_nuovaDestinazione, noteVal, new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									loaderDestinazioneSocialeGriglia.load();
									modifica = false;
									destinazioneSocialeType.enable();
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
										loaderDestinazioneSocialeGriglia.load();
										modifica = false;
										destinazioneSocialeType.enable();
									}
								}
							});
							
						} else {
							if (modifica == false) {
								storeGriglia.remove(0);
							}
							
							destinazioneSocialeType.enable();
							loaderDestinazioneSocialeGriglia.load();
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


	public BaseListLoader<ListLoadResult<DestinazioneSocialeModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderDestinazioneSocialeGriglia;	
	}

}