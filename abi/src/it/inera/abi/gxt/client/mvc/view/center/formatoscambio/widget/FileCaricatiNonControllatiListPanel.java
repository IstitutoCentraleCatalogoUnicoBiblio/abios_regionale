package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.FormatoScambioServiceAsync;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
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

public class FileCaricatiNonControllatiListPanel extends ContentPanel {

	private Grid<FileCaricatiListModel> grid = null;

	private FormatoScambioServiceAsync formatoScambioService = (FormatoScambioServiceAsync) Registry.get(Abi.FORMATO_SCAMBIO);

	private Button controlla = new Button("Controlla", Resources.ICONS.control_play_blue());
	private Button cancella = new Button("Cancella", Resources.ICONS.delete());
	private Button xml = new Button("XML", Resources.ICONS.xml());
	
	public FileCaricatiNonControllatiListPanel() {
		super();
		setHeaderVisible(false);
		setBorders(false);
		
		setTopToolbar();

		List<ColumnConfig> configs = ColumnConfigFileCaricatiBaseList.getFormatoScambioBaseColumnConfig(false, true, false);

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
		controlla.disable();
		cancella.disable();
	}
	public void enableButtons() {
		xml.enable();
		controlla.enable();
		cancella.enable();
	}
	
	private void setTopToolbar() {
		ToolBar topToolBar = new ToolBar();
		
		xml.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				Window.open(GWT.getHostPageBaseURL() + "abi/downloadservlet?type=unchecked&filename=" + model.getFilename(), "_blank", "");
			}
		});
		
		controlla.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final FileCaricatiListModel model = (FileCaricatiListModel) grid.getSelectionModel().getSelectedItem();
				AbiMessageBox.messageConfirmOperationAlertBox("Il file selezionato sarà controllato.<br>Sarà inviata una notifica all'email<br><b>" + model.getEmail() + "</b>&nbsp;relativa all'utente<br><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
						+ model.getUserName() + "</b>.", "Controllo file caricato", 700, new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						Button btn = be.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String filename = model.getFilename();
							final MessageBox messageBox = MessageBox.wait("Formato di scambio", "Controllo file", "verifica in corso...");
							formatoScambioService.checkFile(filename, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									messageBox.close();
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									AbiMessageBox.messageSuccessAlertBox("Controllo terminato. E' stata inviata una notifica all'email<br><b>" + model.getEmail() + "</b>.", "Controllo file caricato", new Listener<MessageBoxEvent>() {
										@Override
										public void handleEvent(MessageBoxEvent be) {
											Dispatcher.get().dispatch(AppEvents.FileCaricatiControllati);
										}
									});
								}
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore nel controllo del file", "Errore");
										messageBox.close();
									}
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
							formatoScambioService.deleteUncheckedFile(filename, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(model);
									disableButtons(); // disabilito i pulsanti
									
									/* Messaggio ridondante */
//									AbiMessageBox.messageSuccessAlertBox("File cancellato", "Eliminazione file caricato");
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
		topToolBar.add(controlla);
		topToolBar.add(cancella);
		setTopComponent(topToolBar);
		
		disableButtons(); // disabilito i pulsanti
	}

}
