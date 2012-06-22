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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
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

public class ListaDigitalizzazioneFondiPanel extends ContentPanel {

	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private int id_biblioteca;
	private int id_newRecord;
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> digitalizzazioneFondiLoader;
	private boolean modifica;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<VoceUnicaModel> grid;

	public ListaDigitalizzazioneFondiPanel() {
		bibliotecheServiceAsync= Registry.get(Abi.BIBLIOTECHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

	}

	public void setGrid(){
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final	TextField<String> descrizioneDigitalizzazione = new TextField<String>();
		descrizioneDigitalizzazione.setAllowBlank(false);
		descrizioneDigitalizzazione.setEmptyText("Descrizione...");

		ColumnConfig column = new ColumnConfig();
		column.setId("entry");
		column.setHeader("Descrizione");
		column.setWidth(350);
		column.setEditor(new CellEditor(descrizioneDigitalizzazione));
		configs.add(column);

		RpcProxy<List<VoceUnicaModel>> digitalizzazioneFondiProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {

				bibliotecheServiceAsync.getDigitalizzazioneFondiByIdBiblio(id_biblioteca, callback);
			}

		};
		ModelReader digitalizzazioneFondiReader = new ModelReader();

		digitalizzazioneFondiLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>( digitalizzazioneFondiProxy, digitalizzazioneFondiReader);

		final ListStore<VoceUnicaModel> digitalizzazioneFondiStore = new ListStore<VoceUnicaModel>(digitalizzazioneFondiLoader);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setErrorSummary(false);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		grid = new Grid<VoceUnicaModel>(digitalizzazioneFondiStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		toolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolBar.addStyleName("font-weight-style");
		toolBar.setWidth(300);
		toolBar.setBorders(false);
		toolBar.add(new Text("Digitalizzazione fondi "));

		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel newDen = new VoceUnicaModel();
				re.stopEditing(false);
				digitalizzazioneFondiStore.insert(newDen, 0);
				re.startEditing(digitalizzazioneFondiStore.indexOf(newDen), false);

				descrizioneDigitalizzazione.clearInvalid();
			}

		});
		toolBar.add(add);

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_rimuoviFondo = grid.getSelectionModel().getSelectedItem().getIdRecord();
							bibliotecheServiceAsync.removeFondiDigitali(id_rimuoviFondo,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									remove.disable();
									digitalizzazioneFondiLoader.load();

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										remove.disable();
										digitalizzazioneFondiLoader.load();
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
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {

				remove.enable();
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				id_newRecord = be.getGrid().getSelectionModel().getSelectedItem().getIdRecord();
				remove.disable();
				modifica = true;
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
					digitalizzazioneFondiStore.remove(0);
				}
				modifica = false;
				digitalizzazioneFondiLoader.load();
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

							String derscrizione=null;
							if(modifica==false){
								derscrizione= digitalizzazioneFondiStore.getAt(0).getEntry();
							}
							else {
								derscrizione=grid.getSelectionModel().getSelectedItem().getEntry();
							}
							bibliotecheServiceAsync.addDigitalizzazioneFondo(id_biblioteca, id_newRecord,derscrizione, modifica,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											digitalizzazioneFondiLoader.load();													
											modifica = false;
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login

												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
												digitalizzazioneFondiLoader.load();
											}
										}


									});
						} else {
							if(modifica==false){
								digitalizzazioneFondiStore.remove(0);
							}
						}
					}

				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	public void setIdBiblioteca(int idBiblioteca){
		this.id_biblioteca=idBiblioteca;
	}


	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.digitalizzazioneFondiLoader;	
	}



}
