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

package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.FormatoScambioServiceAsync;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica della lista dei file caricati
 * da importare in schedulazione 
 *
 */
public class ImportSchedulatiListPanel extends ContentPanel {

	private Grid<FileCaricatiListModel> grid = null;

	protected DettaglioFileWindow dettaglioFileWindow;
	
	private FormatoScambioServiceAsync formatoScambioService = (FormatoScambioServiceAsync) Registry.get(Abi.FORMATO_SCAMBIO);

	private Button visualizza = new Button("Visualizza", Resources.ICONS.eye());
	private Button cancellaSchedulato = new Button("Elimina schedulazione", Resources.ICONS.clock_delete());
	private Button cancella = new Button("Cancella", Resources.ICONS.delete());
	private Button xml = new Button("XML", Resources.ICONS.xml());
	
	public ImportSchedulatiListPanel() {
		super();
		setHeaderVisible(false);
		setBorders(false);
		
		setTopToolbar();
		
		// init della finestra di dettaglio dei file
		dettaglioFileWindow = new DettaglioFileWindow() {
			@Override
			public void setProxy() {
				proxy =  new RpcProxy<PagingLoadResult<BiblioModel>>() {
					@Override
					protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
						formatoScambioService.dettaglioScheduledFileImport(filename, (PagingLoadConfig) loadConfig, callback);
					}  
				};
			}
		};
		

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		List<ColumnConfig> tmp = ColumnConfigFileCaricatiBaseList.getFormatoScambioBaseColumnConfig(true, true, true);
		java.util.Iterator<ColumnConfig> it = tmp.iterator();
		while (it.hasNext()) {
			configs.add(it.next());
		}

		setLayout(new FitLayout());

		ListStore<FileCaricatiListModel> store = new ListStore<FileCaricatiListModel>();
		grid = new Grid<FileCaricatiListModel>(store, new ColumnModel(configs));
		grid.setStripeRows(true);
		grid.setColumnLines(true);
		grid.setAutoWidth(true);
		grid.setColumnReordering(true);
		grid.getView().setAutoFill(true);
		grid.addListener(Events.RowClick, new Listener<GridEvent<FileCaricatiListModel>>() {
			public void handleEvent(GridEvent<FileCaricatiListModel> be) {
				enableButtons();
			}
		});
		
		add(grid);
	}

	public Grid<FileCaricatiListModel> getGrid() {
		return grid;
	}
	
	public void disableButtons() {
		xml.disable();
		visualizza.disable();
		cancellaSchedulato.disable();
		cancella.disable();
	}
	public void enableButtons() {
		xml.enable();
		visualizza.enable();
		cancellaSchedulato.enable();
		cancella.enable();
	}
	
	private void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		
		xml.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				Window.open(GWT.getHostPageBaseURL() + "abi/downloadservlet?type=scheduled&filename=" + model.getFilename(), "_blank", "");
			}
		});
		
		visualizza.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
//				AbiMessageBox.messageConfirmOperationAlertBox("Visualizzare il file selezionato?", "Visualizza file", new Listener<MessageBoxEvent>(){
//					@Override
//					public void handleEvent(MessageBoxEvent be) {
//						Button btn = be.getButtonClicked();
//						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							dettaglioFileWindow.setFilename(filename);
							dettaglioFileWindow.loadAndView();
//						}
//					}
//				});
			}
		});
		cancellaSchedulato.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				AbiMessageBox.messageConfirmOperationAlertBox("Rimuovere il file dalla schedulazione e riportarlo nella lista dei file controllati?", "Eliminazione file in schedulazione", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							formatoScambioService.deleteScheduledImportFile(filename, false, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox("File schedulato cancellato", "Cancellazione");
									Dispatcher.get().dispatch(AppEvents.FileCaricatiControllati);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore nella rimozione del file", "Errore");
								}
							});
						}
					}
				});
			}
		});
		cancella.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				AbiMessageBox.messageConfirmOperationAlertBox("Eliminare il file definitivamente?", "Eliminazione file in schedulazione", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							formatoScambioService.deleteScheduledImportFile(filename, true, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox("File cancellato", "Cancellazione");
								}
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore nella rimozione del file", "Errore");
								}
							});
						}
					}
				});
			}
		});
		topToolBar.add(xml);
		topToolBar.add(visualizza);
		topToolBar.add(cancellaSchedulato);
		topToolBar.add(cancella);
		setTopComponent(topToolBar);
		
		disableButtons();
	}

}