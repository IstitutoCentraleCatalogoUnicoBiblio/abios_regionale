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
import java.util.Iterator;
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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica della lista di file caricati
 * e controllati da importare
 *
 */
public class FileCaricatiControllatiListPanel extends ContentPanel {

	private Grid<FileCaricatiListModel> grid = null;

	// servizio async formato di scambio
	private FormatoScambioServiceAsync formatoScambioService = Registry.get(Abi.FORMATO_SCAMBIO);

	protected DettaglioFileWindow dettaglioFileWindow;
	
	private Button visualizza = new Button("Visualizza", Resources.ICONS.eye());
	private Button importa = new Button("Importa", Resources.ICONS.upload());
	private Button differito = new Button("Differito", Resources.ICONS.clock_add());
	private Button cancella = new Button("Cancella", Resources.ICONS.delete());
	private Button xml = new Button("XML", Resources.ICONS.xml());
	
	public FileCaricatiControllatiListPanel() {
		super();
		setHeaderVisible(false);
		setBorders(false);
		
		setTopToolbar();
		
		// init della finestra di dettaglio dei file
		dettaglioFileWindow = new DettaglioFileWindow() {
			@Override
			public void setProxy() {
				proxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {
					@Override
					protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
						formatoScambioService.dettaglioCheckedFileImport(filename, (PagingLoadConfig) loadConfig, callback);
					}  
				};
			}
		};
		
		GridCellRenderer<FileCaricatiListModel> esitoCellRenderer = new GridCellRenderer<FileCaricatiListModel>() {  
			public String render(FileCaricatiListModel model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<FileCaricatiListModel> store, Grid<FileCaricatiListModel> grid) {  
				String msg = null;
				String style = null;
				if (model.getError()) {
					style="red";
					msg = "Errore";
				} else {
					style="green";
					msg = "Valido";
				}
				return "<span style='color:" + style + "'>" + msg + "</span>";  
			}  
		};  

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		List<ColumnConfig> tmp = ColumnConfigFileCaricatiBaseList.getFormatoScambioBaseColumnConfig(true, true, false);
		Iterator<ColumnConfig> it = tmp.iterator();
		while (it.hasNext()) {
			configs.add(it.next());
		}
		
		ColumnConfig columnEsito = new ColumnConfig();
		columnEsito.setId("esito");
		columnEsito.setHeader("Esito");
		columnEsito.setWidth(90);
		columnEsito.setRenderer(esitoCellRenderer);
		configs.add(columnEsito);
		
		
		
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
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				enableButtons();
				if (model.getError()) {
					importa.disable();
					differito.disable();
				}
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
		importa.disable();
		differito.disable();
		cancella.disable();
	}
	public void enableButtons() {
		xml.enable();
		visualizza.enable();
		importa.enable();
		differito.enable();
		cancella.enable();
	}
	
	private void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		
		xml.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				Window.open(GWT.getHostPageBaseURL() + "abi/downloadservlet?type=checked&filename=" + model.getFilename(), "_blank", "");
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
		importa.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				AbiMessageBox.messageConfirmOperationAlertBox("Importare il file selezionato?", "Import file", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							final MessageBox messageBox = MessageBox.wait("Formato di scambio", "Import file", "import...");
							formatoScambioService.importFile(filename, false, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									messageBox.close();
									AbiMessageBox.messageSuccessAlertBox("Procedura di import terminata. E' stata inviata una notifica all'email<br><b>"
											+ model.getEmail() + "</b>.", "Import file");
								}
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore nell'import del file", "Errore");
										messageBox.close();
									}
								}
							});
						}
					}
				});
			}
		});
		differito.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				AbiMessageBox.messageConfirmOperationAlertBox("Avviare l'import differito del file selezionato?", "Import differito", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							formatoScambioService.importFile(filename, true, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									AbiMessageBox.messageSuccessAlertBox("Procedura import differito avviata. Il file è ora in schedulazione e verrà elaborato alle ore 02:00.", "Import differito", new Listener<MessageBoxEvent>() {
										
										@Override
										public void handleEvent(MessageBoxEvent be) {
											Dispatcher.get().dispatch(AppEvents.ImportSchedulati);
										}
									});
									
								}
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore durante la schedulazione del file", "Errore");
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
				AbiMessageBox.messageConfirmOperationAlertBox("Eliminare il file selezionato?", "Eliminazione file caricato", new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							formatoScambioService.deleteFile(filename, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox("File cancellato", "Eliminazione file caricato");
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
		
		topToolBar.add(xml);
		topToolBar.add(visualizza);
		topToolBar.add(importa);
		topToolBar.add(differito);
		topToolBar.add(cancella);
		setTopComponent(topToolBar);
		
		disableButtons();
	}

}
