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
import it.inera.abi.gxt.client.mvc.model.PatrimonioLibrarioModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioLibrarioModelForCombo;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
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
import com.extjs.gxt.ui.client.widget.form.NumberField;
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
 * relative alla lista di patrimoni librari
 *
 */
public class ListaPatrimonioLibrarioPanel extends ContentPanel {

	private TabelleDinamicheServiceAsync tabelleDinamicheService = Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
	private boolean modifica = false;
	private boolean categoriaSelected = false;
	private BibliotecheServiceAsync bibliotecheService;
	private int id_biblioteca;
	private BaseListLoader<ListLoadResult<PatrimonioLibrarioModel>> loaderPatrimonioGriglia;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<PatrimonioLibrarioModel> grid;

	public ListaPatrimonioLibrarioPanel() {

		bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
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

		final ComboBox<PatrimonioSpecializzazioneModel> tipologia = new ComboBox<PatrimonioSpecializzazioneModel>();

		tipologia.setDisplayField("denominazioneMateriale");
		tipologia.setFieldLabel("denominazioneMateriale");
		tipologia.setFireChangeEventOnSetValue(true);
		tipologia.setEmptyText("Seleziona una tipologia...");
		tipologia.setWidth(280);
		tipologia.setLazyRender(false);
		tipologia.setTriggerAction(TriggerAction.ALL);
		tipologia.setAllowBlank(false);
		tipologia.setForceSelection(true);
		tipologia.setEditable(true);
		tipologia.setTypeAhead(false);
		tipologia.setMinChars(1);
		tipologia.setPageSize(10);

		tipologia.setSimpleTemplate(PatrimonioSpecializzazioneModel.getTemplateMateriali());

		CellEditor editor = new CellEditor(tipologia) {
			@Override
			public Object preProcessValue(Object value) {
				if (modifica) {
					return grid.getSelectionModel().getSelectedItem();

				} else {
					return null;
				}
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				} 
				return ((ModelData) value).get("denominazioneMateriale");
			}
		};

		RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>> tipologiaPatrimonioProxy = new RpcProxy<PagingLoadResult<PatrimonioSpecializzazioneModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<PatrimonioSpecializzazioneModel>> callback) {
				tabelleDinamicheService.getTipologiePatrimonioFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);
			}
		};

		ModelReader tipologiaPatrimonioReader = new ModelReader();

		final PagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>> tipologiaPatrimonioLoader = new BasePagingLoader<PagingLoadResult<PatrimonioSpecializzazioneModel>>(
				tipologiaPatrimonioProxy, tipologiaPatrimonioReader);
		tipologiaPatrimonioLoader.load();
		final ListStore<PatrimonioSpecializzazioneModel> tipologiaPatrimonioComboboxStore = new ListStore<PatrimonioSpecializzazioneModel>(
				tipologiaPatrimonioLoader);

		tipologia.setStore(tipologiaPatrimonioComboboxStore);

		ColumnConfig tipologiaColumn = new ColumnConfig();
		tipologiaColumn.setId("denominazioneMateriale");
		tipologiaColumn.setHeader("Tipologia");
		tipologiaColumn.setWidth(280);
		tipologiaColumn.setEditor(editor);

		configs.add(tipologiaColumn);

		final NumberField quantitaField = new NumberField();
		quantitaField.setEmptyText("0");
		quantitaField.setPropertyEditorType(Integer.class);
		quantitaField.setEnabled(true);

		ColumnConfig quantitaColumn = new ColumnConfig();

		quantitaColumn.setId("quantita");
		quantitaColumn.setHeader("Quantita");
		quantitaColumn.setEditor(new CellEditor(quantitaField));
		quantitaColumn.setWidth(150);
		configs.add(quantitaColumn);

		final NumberField quantitaUltimoAnnoField = new NumberField();
		quantitaUltimoAnnoField.setEmptyText("0");
		quantitaUltimoAnnoField.setPropertyEditorType(Integer.class);
		quantitaUltimoAnnoField.setEnabled(true);

		ColumnConfig quantitaUltimoAnnoColumn = new ColumnConfig();
		quantitaUltimoAnnoColumn.setId("quantitaUltimoAnno");
		quantitaUltimoAnnoColumn.setHeader("Q. ultimo anno");
		quantitaUltimoAnnoColumn.setEditor(new CellEditor(quantitaUltimoAnnoField));
		quantitaUltimoAnnoColumn.setWidth(150);

		configs.add(quantitaUltimoAnnoColumn);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<PatrimonioLibrarioModel> re = new RowEditorCustom<PatrimonioLibrarioModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);

		RowEditor<PatrimonioLibrarioModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		RpcProxy<List<PatrimonioLibrarioModel>> proxyPatrimonioLibrario = new RpcProxy<List<PatrimonioLibrarioModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<PatrimonioLibrarioModel>> callback) {
				bibliotecheService.getListaPatrimonioSpecializzazione(id_biblioteca, callback);
			}
		};

		ModelReader readerPatrimonioGriglia = new ModelReader();
		loaderPatrimonioGriglia = new BaseListLoader<ListLoadResult<PatrimonioLibrarioModel>>(
				proxyPatrimonioLibrario, readerPatrimonioGriglia);
		final ListStore<PatrimonioLibrarioModel> store = new ListStore<PatrimonioLibrarioModel>(
				loaderPatrimonioGriglia);

		grid = new Grid<PatrimonioLibrarioModel>(store, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		tipologia.addSelectionChangedListener(new SelectionChangedListener<PatrimonioSpecializzazioneModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<PatrimonioSpecializzazioneModel> se) {
				if (se.getSelectedItem() != null) {
					if (se.getSelectedItem().getIdRecord() == -1) {
						se.setCancelled(true);
						AbiMessageBox.messageAlertBox(AbiMessageBox.INFO_SELECTION_MESSAGE, AbiMessageBox.INFO_SELECTION_TITLE);
						grid.getStore().remove(0);
						categoriaSelected = true;
						re.stopEditing(true);
						loaderPatrimonioGriglia.load();

					} else {
						grid.getStore().getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
					}
				}
			}
		});

		toolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolBar.addStyleName("font-weight-style");

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Tipologie "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				PatrimonioLibrarioModelForCombo newPatr = new PatrimonioLibrarioModelForCombo();
				newPatr.setQuantita(0);
				newPatr.setQuantitaUltimoAnno(0);

				re.stopEditing(false);
				store.insert(newPatr, 0);
				re.startEditing(store.indexOf(newPatr), false);

				/*Rimuove il problema del bordo rosso alla visualizzazione del roweditor*/
				tipologia.clearInvalid();
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
							int idRecordToRemove = grid.getSelectionModel().getSelectedItem().getIdRecord();
							bibliotecheService.removePatrimonioSpeciale(id_biblioteca, idRecordToRemove, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									tipologia.enable();
									loaderPatrimonioGriglia.load();
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
		grid.addListener(Events.RowClick, new Listener<GridEvent<PatrimonioLibrarioModel>>() {

			public void handleEvent(GridEvent<PatrimonioLibrarioModel> be) {
				remove.enable();
			}
		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);
		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<PatrimonioLibrarioModel>>() {

			public void handleEvent(GridEvent<PatrimonioLibrarioModel> be) {
				tipologia.disable();

				remove.disable();
				modifica = true;
			}
		});
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);
				}
				modifica = false;
				tipologia.enable();
				loaderPatrimonioGriglia.load();
			}
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (categoriaSelected) {
					be.setCancelled(true);
					categoriaSelected = false;
					return;
				}

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int id_nuovoPatr;
							int quantita;
							int quantitaUltimoAnno;

							if (modifica == false) {
								id_nuovoPatr = store.getAt(0).getIdRecord();

								if (quantitaField.getValue() != null && quantitaField.getValue().intValue() != 0) {
									quantita = quantitaField.getValue().intValue();
									
								} else {
									quantita = 0;
								}
								
								if (quantitaUltimoAnnoField.getValue() != null && quantitaUltimoAnnoField.getValue().intValue() != 0) {
									quantitaUltimoAnno = quantitaUltimoAnnoField.getValue().intValue();
									
								} else {
									quantitaUltimoAnno = 0;
								}
								
							} else {
								id_nuovoPatr = grid.getSelectionModel().getSelectedItem().getIdRecord();

								if (grid.getSelectionModel().getSelectedItem().getQuantita() != null && grid.getSelectionModel().getSelectedItem().getQuantita().intValue() != 0) {
									quantita = grid.getSelectionModel().getSelectedItem().getQuantita().intValue();

								} else {
									quantita = 0;
								}

								if (grid.getSelectionModel().getSelectedItem().getQuantitaUltimoAnno() != null && grid.getSelectionModel().getSelectedItem().getQuantitaUltimoAnno().intValue() != 0) {
									quantitaUltimoAnno = grid.getSelectionModel().getSelectedItem().getQuantitaUltimoAnno().intValue();

								} else {
									quantitaUltimoAnno = 0;
								}
							}

							bibliotecheService.addPatrimonioSpeciale(id_biblioteca, modifica, id_nuovoPatr, quantita, quantitaUltimoAnno, new AsyncCallback<Boolean>() {

								@Override
								public void onSuccess(Boolean result) {
									loaderPatrimonioGriglia.load();
									modifica = false;
									tipologia.enable();
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
										loaderPatrimonioGriglia.load();
										modifica = false;
										tipologia.enable();
									}
								}

							});
							
						} else {
							if (modifica == false) {
								store.remove(0);

							}
							tipologia.enable();
							loaderPatrimonioGriglia.load();
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

	public BaseListLoader<ListLoadResult<PatrimonioLibrarioModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderPatrimonioGriglia;
	}

}
