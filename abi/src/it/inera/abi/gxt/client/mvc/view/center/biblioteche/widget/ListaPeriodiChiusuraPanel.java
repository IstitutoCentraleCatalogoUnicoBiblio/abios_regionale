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
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
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
 * relative alla lista dei periodi di chiusura
 *
 */
public class ListaPeriodiChiusuraPanel extends ContentPanel {

	private int id_biblioteca;
	private RowEditorCustom<VoceUnicaModel> re;
	private boolean modifica = false;
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> loaderPeriodiChiusura;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<VoceUnicaModel> grid;

	private TextField<String> chiusuraDenominazione;

	public ListaPeriodiChiusuraPanel() {
		bibliotecheServiceAsync = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);
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


		chiusuraDenominazione = new TextField<String>();
		chiusuraDenominazione.setAllowBlank(false);
		chiusuraDenominazione.setEmptyText("Inserisci periodo di chiusura...");

		ColumnConfig periodoChiusuraColumn = new ColumnConfig();
		periodoChiusuraColumn.setId("entry");
		periodoChiusuraColumn.setHeader("Periodo di chiusura");
		periodoChiusuraColumn.setWidth(300);

		periodoChiusuraColumn.setEditor(new CellEditor(chiusuraDenominazione));
		configs.add(periodoChiusuraColumn);

		RpcProxy<List<VoceUnicaModel>> proxyPeriodiChiusura = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getPeriodiChiusuraByIdBiblio(id_biblioteca, callback);
			}

		};
		ModelReader readerPeriodiChiusura = new ModelReader();

		loaderPeriodiChiusura = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(proxyPeriodiChiusura, readerPeriodiChiusura);

		final ListStore<VoceUnicaModel> storePeriodiChiusura = new ListStore<VoceUnicaModel>(loaderPeriodiChiusura);


		ColumnModel cm = new ColumnModel(configs);

		re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);

		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
		rowEditorMessages.setCancelText("Annulla");
		rowEditorMessages.setSaveText("Salva");
		re.setMessages(rowEditorMessages);

		grid = new Grid<VoceUnicaModel>(storePeriodiChiusura, cm);
		grid.setAutoExpandColumn("entry");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.setStyleAttribute("color", "#ffffff");
		toolBar.add(new Text("Periodi chiusura "));
		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel newVoce = new VoceUnicaModel();
				re.stopEditing(false);
				storePeriodiChiusura.insert(newVoce, 0);
				re.startEditing(storePeriodiChiusura.indexOf(newVoce), false);

				chiusuraDenominazione.clearInvalid();
			}

		});
		toolBar.add(add);

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

							int id_chiusuraToRemove = grid.getSelectionModel().getSelectedItem().getIdRecord();
							bibliotecheServiceAsync.removePeriodoChiusura(
									id_chiusuraToRemove,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {

											loaderPeriodiChiusura.load();

											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

									});
						} else{
							if(modifica==false){
								storePeriodiChiusura.remove(0);
							}
							loaderPeriodiChiusura.load();
							modifica=false;
						}
						remove.disable();
					}

				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

				if (grid.getStore().getCount() == 0) {
					ce.<Component> getComponent().disable();
				}
			}

		});
		remove.disable();
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.enable();
				modifica=false;
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.disable();
				modifica=true;
			}
		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					storePeriodiChiusura.remove(0);

				}
				modifica = false;
				loaderPeriodiChiusura.load();
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
							VoceUnicaModel tmpSave = null;

							if (modifica) {
								tmpSave = grid.getSelectionModel().getSelectedItem();

							} else {
								tmpSave = storePeriodiChiusura.getAt(0);
								tmpSave.setEntry(chiusuraDenominazione.getValue());
							}
							
							bibliotecheServiceAsync.addNuovoPeriodoChiusura(id_biblioteca, tmpSave, modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									loaderPeriodiChiusura.load();	
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}


							});
							
						} else {
							if (modifica == false) {
								storePeriodiChiusura.remove(0);
								
							} else {
								storePeriodiChiusura.rejectChanges();
							}
						}
						
						modifica = false;
					}

				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});

	}
	
	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}

	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderPeriodiChiusura;	
	}

}
