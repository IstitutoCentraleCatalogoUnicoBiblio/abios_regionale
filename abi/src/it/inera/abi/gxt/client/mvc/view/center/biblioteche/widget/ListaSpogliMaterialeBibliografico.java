package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
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
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica delle informazioni
 * relative alla lista degli spogli del materiale bibliografico
 *
 */
public class ListaSpogliMaterialeBibliografico extends ContentPanel{
	
	private int id_biblioteca;
	private boolean modifica;
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> spogliMarerialeGrigliaLoader;
	private BibliotecheServiceAsync bibliotecheService;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<VoceUnicaModel> grid;

	public ListaSpogliMaterialeBibliografico() {

		this.modifica=false;
		this.bibliotecheService= Registry.get(Abi.BIBLIOTECHE_SERVICE);
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

		/*
		 * descrizioneVoceProxy RPCProxy,ModelReader e Loader per il caricamento
		 * paginato asincrono dei dati all'interno dello store della Combobox
		 * per oggetti di tipo VoceUnicaModel
		 */

		RpcProxy<PagingLoadResult<VoceUnicaModel>> descrizioneVoceProxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				bibliotecheService.getSpogliMaterialBibliograficoPerPaginazioneCombobox(id_biblioteca,(ModelData)loadConfig, callback);
			}

		};

		ModelReader descrizioneVoceReader = new ModelReader();

		final PagingLoader<PagingLoadResult<FondiSpecialiModel>> descrizioneSpogliLoader = new BasePagingLoader<PagingLoadResult<FondiSpecialiModel>>(
				descrizioneVoceProxy, descrizioneVoceReader);
		descrizioneSpogliLoader.setLimit(10);

		final ListStore<VoceUnicaModel> descrizioneSpogliStore = new ListStore<VoceUnicaModel>(
				descrizioneSpogliLoader);
		//ComboBox paginata con autocompletamento 
		final	ComboBox<VoceUnicaModel> descrizioneSpogliField = new ComboBox<VoceUnicaModel>();
		descrizioneSpogliField.setDisplayField("entry");
		descrizioneSpogliField.setFieldLabel("entry");
		descrizioneSpogliField.setFireChangeEventOnSetValue(true);
		descrizioneSpogliField.setEmptyText("Descrizione...");
		descrizioneSpogliField.setForceSelection(true);
		descrizioneSpogliField.setLazyRender(false);
		descrizioneSpogliField.setTriggerAction(TriggerAction.ALL);
		descrizioneSpogliField.setAllowBlank(false);
		descrizioneSpogliField.setEditable(true);
		descrizioneSpogliField.setTypeAhead(false);
		descrizioneSpogliField.setMinChars(1);
		descrizioneSpogliField.setPageSize(10);
		descrizioneSpogliField.setStore(descrizioneSpogliStore);


		CellEditor descrizioneVoceComboEditor = new CellEditor(descrizioneSpogliField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					return descrizioneSpogliField.getStore().findModel("entry",
							value.toString());

				} else
					return "Descrizione...";
			}

			@Override
			public Object postProcessValue(Object value) {
				if(value instanceof VoceUnicaModel){
					VoceUnicaModel tmp=(VoceUnicaModel) value;
					return tmp.getEntry();
				}else if(descrizioneSpogliField.getRawValue()!=null){
					return descrizioneSpogliField.getRawValue();
				}else return null;
			}
		};

		// Config per colonna descrizione
		ColumnConfig descrizioneColumn = new ColumnConfig();
		descrizioneColumn.setId("entry");
		descrizioneColumn.setHeader("Descrizione");
		descrizioneColumn.setWidth(300);
		descrizioneColumn.setEditor(descrizioneVoceComboEditor);

		configs.add(descrizioneColumn);

		RpcProxy<List<VoceUnicaModel>> spogliMarerialeGrigliaProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheService.getListaSpogliMarerialeBibliograficoByIdBiblio(id_biblioteca, callback);
			}

		};
		ModelReader spogliMarerialeGrigliaReader = new ModelReader();

		spogliMarerialeGrigliaLoader = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(spogliMarerialeGrigliaProxy, spogliMarerialeGrigliaReader);


		final ListStore<VoceUnicaModel> spogliMarerialeGrigliaStore = new ListStore<VoceUnicaModel>(spogliMarerialeGrigliaLoader);


		ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.disable();
		re.setErrorSummary(false);
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		grid = new Grid<VoceUnicaModel>(spogliMarerialeGrigliaStore, cm);
		grid.setAutoExpandColumn("entry");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();


		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Spogli materiale bibliografico "));
		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				VoceUnicaModel newDescr = new VoceUnicaModel();
				re.enable();
				re.stopEditing(false);
				spogliMarerialeGrigliaStore.insert(newDescr, 0);
				re.startEditing(spogliMarerialeGrigliaStore.indexOf(newDescr), false);

				descrizioneSpogliField.clearInvalid();
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

							int id_rimuoviSpoglio = grid.
							getSelectionModel().
							getSelectedItem().
							getIdRecord();
							bibliotecheService.removeSpogliMaterialeBibliografico( id_rimuoviSpoglio,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									remove.disable();
									spogliMarerialeGrigliaLoader.load();

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										spogliMarerialeGrigliaLoader.load();
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

		grid.addListener(Events.RowClick,new Listener<GridEvent<VoceUnicaModel>>() {
			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				remove.enable();
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
				spogliMarerialeGrigliaStore.remove(0);
			}	
		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							
							bibliotecheService.addSpoglioMaterialeBibliografico(descrizioneSpogliField.getRawValue(),id_biblioteca, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									re.disable();
									spogliMarerialeGrigliaLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										re.disable();
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
																			spogliMarerialeGrigliaLoader.load();
									}
								}
							});



						} 
						else{
							spogliMarerialeGrigliaLoader.load();		
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

		return this.spogliMarerialeGrigliaLoader;
	}
}
