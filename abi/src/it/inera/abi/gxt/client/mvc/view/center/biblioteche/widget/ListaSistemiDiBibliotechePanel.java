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
 * relative alla lista dei sistemi di biblioteche
 *
 */
public class ListaSistemiDiBibliotechePanel extends ContentPanel {

	private int id_biblioteca;

	private BaseListLoader<ListLoadResult<VoceUnicaModel>> sistemiBiblioByIdBiblioLoader;

	private Grid<VoceUnicaModel> grid;
	private ToolBar toolBar = null;
	private Button remove = null;
	private Button add = null;

	public ListaSistemiDiBibliotechePanel() {

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
		final BibliotecheServiceAsync bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);
		final TabelleDinamicheServiceAsync tdsa = (TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		re.disable();

		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("entry");
		column.setHeader("Descrizione");
		column.setWidth(300);

		final ComboBox<VoceUnicaModel> denominazione = new ComboBox<VoceUnicaModel>();
		denominazione.setDisplayField("entry");
		denominazione.setFieldLabel("entry");
		denominazione.setFireChangeEventOnSetValue(true);
		denominazione.setEmptyText("Sistema di biblioteche...");
		denominazione.setLazyRender(false);
		denominazione.setTriggerAction(TriggerAction.ALL);
		denominazione.setAllowBlank(false);
		denominazione.setForceSelection(true);
		denominazione.setEditable(true);
		denominazione.setTypeAhead(false);
		denominazione.setMinChars(1);
		denominazione.setPageSize(10);

		CellEditor editor = new CellEditor(denominazione) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				
				return "Sistema di biblioteche...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("entry");
			}
		};

		ColumnConfig columnDenominazione = new ColumnConfig();
		columnDenominazione.setId("entry");
		columnDenominazione.setHeader("Denominazione");
		columnDenominazione.setWidth(400);
		columnDenominazione.setEditor(editor);
		configs.add(columnDenominazione);

		RpcProxy<PagingLoadResult<VoceUnicaModel>> sistemiBiblioProxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				tdsa.getListaVociFiltratePerPaginazioneCombobox(CostantiTabelleDinamiche.SISTEMI_RETI_BIBLITOECHE_INDEX,
						(ModelData) loadConfig, callback);
			}
		};
		
		ModelReader sistemiBiblioReader = new ModelReader();

		PagingLoader<PagingLoadResult<VoceUnicaModel>> sistemiBiblioLoader = new BasePagingLoader<PagingLoadResult<VoceUnicaModel>>(
				sistemiBiblioProxy, sistemiBiblioReader);
		sistemiBiblioLoader.setLimit(10);

		final ListStore<VoceUnicaModel> sistemiBiblioComboboxStore = new ListStore<VoceUnicaModel>(
				sistemiBiblioLoader);

		denominazione.setStore(sistemiBiblioComboboxStore);

		ColumnModel cm = new ColumnModel(configs);
		RpcProxy<List<VoceUnicaModel>> puntiServizioDecentratiProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getSistemiBibliotecheByIdBiblioteca(id_biblioteca, callback);
			}
		};

		ModelReader sistemiBiblioByIdBiblioReader = new ModelReader();

		sistemiBiblioByIdBiblioLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(
				puntiServizioDecentratiProxy, sistemiBiblioByIdBiblioReader);

		final ListStore<VoceUnicaModel> sistemiBiblioByIdBiblioStore = new ListStore<VoceUnicaModel>(
				sistemiBiblioByIdBiblioLoader);


		denominazione.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					grid.getStore().getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
				}
			}
		});
		
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				re.disable();
				sistemiBiblioByIdBiblioLoader.load();
			}
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_nuovoSistema = sistemiBiblioByIdBiblioStore.getAt(0).getIdRecord();

							bibliotecheServiceAsync.addSistemaBiblioteca(id_biblioteca, id_nuovoSistema, new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									re.disable();
									sistemiBiblioByIdBiblioLoader.load();
									
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
										re.disable();
										sistemiBiblioByIdBiblioLoader.load();
									}
								}

							});
							
						} else {
							re.disable();
							sistemiBiblioByIdBiblioLoader.load();
						}

					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		grid = new Grid<VoceUnicaModel>(sistemiBiblioByIdBiblioStore, cm);
		grid.setAutoExpandColumn("entry");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		
		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Sistemi di biblioteche "));
		
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel v = new VoceUnicaModel();
				re.enable();
				re.stopEditing(false);
				sistemiBiblioByIdBiblioStore.insert(v, 0);
				re.startEditing(sistemiBiblioByIdBiblioStore.indexOf(v), false);

				denominazione.clearInvalid();
			}
		});
		
		remove = new Button("Rimuovi");
		remove.setIcon(Resources.ICONS.delete());
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(final ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(final MessageBoxEvent me) {
						remove.disable();
						Button btn = me.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_sistema = grid.getSelectionModel().getSelectedItem().getIdRecord();
							
							bibliotecheServiceAsync.removeSistemaBiblioteca(id_biblioteca, id_sistema,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									sistemiBiblioByIdBiblioLoader.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
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
		
		grid.addListener(Events.RowClick, new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.enable();
			}
		});
		
		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.disable();
			}
		});

		add(grid);
		setTopComponent(toolBar);

	}

	public void setIdBiblioteca(int idBiblio) {
		id_biblioteca = idBiblio;

	}
	
	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader() {
		UIWorkflow.gridEnableEvent(grid);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		
		return this.sistemiBiblioByIdBiblioLoader;
	}
}
