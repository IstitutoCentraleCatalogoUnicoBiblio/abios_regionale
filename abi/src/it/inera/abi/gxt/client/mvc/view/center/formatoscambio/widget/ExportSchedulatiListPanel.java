package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica della lista di export
 * in schedulazione
 *
 */
public class ExportSchedulatiListPanel extends ContentPanel {

	private Grid<FileCaricatiListModel> grid = null;

	protected DettaglioFileWindow dettaglioFileWindow;
	
	final FormatoScambioServiceAsync formatoScambioService = Registry.get(Abi.FORMATO_SCAMBIO);
	
	private Button visualizza = new Button("Visualizza", Resources.ICONS.eye());
	private Button cancellaSchedulato = new Button("Elimina schedulazione", Resources.ICONS.clock_delete());
	
	public ExportSchedulatiListPanel() {
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
						formatoScambioService.dettaglioScheduledFileExport(filename, (PagingLoadConfig) loadConfig, callback);
					}  
				};
			}
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig columnUtente = new ColumnConfig();
		columnUtente.setId("userName");
		columnUtente.setHeader("Utente");
		columnUtente.setWidth(100);
		configs.add(columnUtente);

		ColumnConfig columnEmail = new ColumnConfig();
		columnEmail.setId("email");
		columnEmail.setHeader("Email");
		columnEmail.setWidth(200);
		configs.add(columnEmail);

		ColumnConfig columnDataUpload = new ColumnConfig();
		columnDataUpload.setId("dataUpload");
		columnDataUpload.setHeader("Data di schedulazione");
		columnDataUpload.setWidth(130);
		columnDataUpload.setDateTimeFormat(DateTimeFormat.getFormat("HH:mm:ss dd/MM/yyyy"));  
		configs.add(columnDataUpload);
		
		ColumnConfig columnNbib = new ColumnConfig();
		columnNbib.setId("nBib");
		columnNbib.setHeader("n. Bib.");
		columnNbib.setWidth(50);
		configs.add(columnNbib);
		

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
		visualizza.disable();
		cancellaSchedulato.disable();
	}
	public void enableButtons() {
		visualizza.enable();
		cancellaSchedulato.enable();
	}
	
	private void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		
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
				AbiMessageBox.messageConfirmOperationAlertBox("Eliminare l'export in schedulazione?", "Eliminazione export", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							formatoScambioService.deleteScheduledExportFile(model.getFilename(), new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox("File cancellato", "Cancellazione");
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore nella rimozione del file", "Errore");
									}
								}
							});	
						}
					}
				});
			}
		});
		
		topToolBar.add(visualizza);
		topToolBar.add(cancellaSchedulato);
		setTopComponent(topToolBar);
		
		disableButtons();
	}

}
