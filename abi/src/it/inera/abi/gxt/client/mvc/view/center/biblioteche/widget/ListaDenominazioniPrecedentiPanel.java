package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;
//import it.inera.abi.gxt.client.workflow.UIWorkflow;

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

public class ListaDenominazioniPrecedentiPanel extends ContentPanel {
	private int id_biblio;
	private boolean modifica;
	private BaseListLoader<ListLoadResult<ModelData>> loaderDenominazioni;
	private TextField<String> giaDenominazione;
	private ToolBar toolBar = null;
	private Button remove = null;
	private Button add = null;
	private Grid<VoceUnicaModel> grid = null;
	
	public ListaDenominazioniPrecedentiPanel() {

		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}

	public void setGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("entry");
		column.setHeader("Denominazione precedente");
		column.setWidth(500);

		giaDenominazione = new TextField<String>();
		giaDenominazione.setEmptyText("Inserisci una denominazione...");
		giaDenominazione.setAllowBlank(false);
		column.setEditor(new CellEditor(giaDenominazione));
		configs.add(column);

		final BibliotecheServiceAsync bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		RpcProxy<List<VoceUnicaModel>> denominazioniProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getDenominazioniPrecedenti(id_biblio, callback);

			}
		};

		ModelReader denominazioniReader = new ModelReader();

		loaderDenominazioni = 	new BaseListLoader<ListLoadResult<ModelData>>(denominazioniProxy, denominazioniReader);

		final ListStore<VoceUnicaModel> store = new ListStore<VoceUnicaModel>(loaderDenominazioni);
		loaderDenominazioni.load();

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		grid = new Grid<VoceUnicaModel>(store, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);
		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (modifica == false) {
					store.remove(0);
				}
				modifica=false;
				loaderDenominazioni.load();
			}

		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							VoceUnicaModel nuovaDenominazione =null;
							if(modifica){
								nuovaDenominazione=	grid.getSelectionModel().getSelectedItem();
							}else{
								nuovaDenominazione=	grid.getStore().getAt(0);
							}

							bibliotecheServiceAsync.saveDenominazionePrecendente(nuovaDenominazione, modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									modifica = false;
									loaderDenominazioni.load();

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										modifica = false;
									}
								}

							});
						}

					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});

		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Denominazioni precedenti "));
		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				VoceUnicaModel newDen = new VoceUnicaModel();
				newDen.setIdBiblioteca(id_biblio);
				re.stopEditing(false);
				store.insert(newDen, 0); 
				re.startEditing(store.indexOf(newDen), false);
				modifica = false;
				
				giaDenominazione.clearInvalid();
			}

		});

		remove = new Button("Rimuovi");
		remove.addSelectionListener( new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(final ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(final MessageBoxEvent me) {
						remove.disable();
						Button btn = me.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_denominazione = grid.getSelectionModel().getSelectedItem().getIdRecord();


							bibliotecheServiceAsync.removeDenominazionePrecedente(id_denominazione, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									grid.getStore().remove(grid.getStore().indexOf(grid.getSelectionModel().getSelectedItem()));
									me.<Component> getComponent().disable();
									loaderDenominazioni.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Errore durante la rimozione della voce!", "ERRORE RIMOZIONE");
								}
							});
							if (grid.getStore().getCount() == 0) {
								me.<Component> getComponent().disable();
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
				modifica = false;
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<VoceUnicaModel>>() {
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.disable();
				modifica = true;
			}
		});

		remove.setIcon(Resources.ICONS.delete());

		add(grid);
		setTopComponent(toolBar);
	}

	public void setIdBiblioteca(int id_biblio) {
		this.id_biblio = id_biblio;
	}

	public BaseListLoader<ListLoadResult<ModelData>> getLoader(){
		UIWorkflow.gridEnableEvent(grid);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		
		return this.loaderDenominazioni;
	}

}
