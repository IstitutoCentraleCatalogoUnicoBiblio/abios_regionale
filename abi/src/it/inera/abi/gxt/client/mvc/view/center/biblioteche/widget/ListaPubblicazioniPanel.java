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
 * relative alla lista delle pubblicazioni
 *
 */
public class ListaPubblicazioniPanel extends ContentPanel {
	
	private boolean modifica;
	private int id_biblioteca;
	private BibliotecheServiceAsync bibliotecheService;
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> pubblicazioniGrigliaLoader;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<VoceUnicaModel> grid;
	private TextField<String> descrizioneField;
	
	public ListaPubblicazioniPanel() {
		this.modifica=false;
		this.bibliotecheService= Registry.get(Abi.BIBLIOTECHE_SERVICE);
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

		ColumnConfig descrizione = new ColumnConfig();
		descrizione.setId("entry");
		descrizione.setHeader("Descrizione");
		descrizione.setWidth(300);

		descrizioneField = new TextField<String>();
		descrizioneField.setEmptyText("Descrizione...");
		descrizioneField.setAllowBlank(false);
		descrizione.setEditor(new CellEditor(descrizioneField));
		configs.add(descrizione);

		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<VoceUnicaModel>> pubblicazioniGrigliaProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheService.getlistaPubblicazioniByIdBiblio(id_biblioteca, callback);
			}

		};
		ModelReader pubblicazioniGrigliaReader = new ModelReader();

		pubblicazioniGrigliaLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(pubblicazioniGrigliaProxy, pubblicazioniGrigliaReader);

		final ListStore<VoceUnicaModel> pubblicazioniStore = new ListStore<VoceUnicaModel>(pubblicazioniGrigliaLoader);

		grid = new Grid<VoceUnicaModel>(pubblicazioniStore, cm);
		grid.setAutoExpandColumn("entry");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolBar.addStyleName("font-weight-style");
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Pubblicazioni "));
		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel newDescr = new VoceUnicaModel();

				re.stopEditing(false);
				pubblicazioniStore.insert(newDescr, 0);
				re.startEditing(pubblicazioniStore.indexOf(newDescr), false);

				descrizioneField.clearInvalid();
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

							int id_rimuoviPubblicazione = grid.getSelectionModel().getSelectedItem().getIdRecord();
							bibliotecheService.removePubblicazioni(id_rimuoviPubblicazione,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									remove.disable();
									pubblicazioniGrigliaLoader.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
										pubblicazioniGrigliaLoader.load();
										remove.disable();
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
				remove.disable();
				
				if (modifica == false) {
					pubblicazioniStore.remove(0);
				}
				
				pubblicazioniGrigliaLoader.load();
			}
		});
		
		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				remove.disable();

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							VoceUnicaModel modelToSave = new VoceUnicaModel();
							if (modifica == false) {
								modelToSave = pubblicazioniStore.getAt(0);
								modelToSave.setEntry(descrizioneField.getValue());
								
							} else {
								modelToSave = grid.getSelectionModel().getSelectedItem();
							}
							
							bibliotecheService.addPubblicazioni(modelToSave, id_biblioteca, modifica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {

									pubblicazioniGrigliaLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										pubblicazioniGrigliaLoader.load();
									}

								}
							});



						} 
						else{
							if (modifica==false) pubblicazioniStore.remove(0);

							pubblicazioniGrigliaLoader.load();
							modifica=false;

						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	/**
	 * Setta va variabile id_biblioteca
	 * */
	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}

	/**
	 * Ritorna il l'oggeto di tipo loader per il caricamento dei dati nello
	 * store della Grid
	 * */
	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.pubblicazioniGrigliaLoader;
	}

}
