package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
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
 * relative alla lista di contenuti delle tabelle dinamiche con struttura
 * (id_record, descrizione)
 *
 */
public class ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel extends ContentPanel {
	/**
	 * Variabile per il servizio di operazioni delle tabelle dinamiche
	 * */
	private TabelleDinamicheServiceAsync tabelleDinamicheService;
	/**
	 * Variabile per il servizio di operazioni della biblioteca
	 * */
	private BibliotecheServiceAsync bibliotecheService ;
	/**
	 *  Id della bibliteca in modifica
	 * */
	private int id_biblioteca;
	/**
	 * Loader per il caricamento dei dati nello store della griglia
	 * */
	private BaseListLoader<ListLoadResult<VoceUnicaModel>> loaderGriglia;
	/**
	 * Flag che indica se si sta modificando una voce o se ne sta creando una nuova
	 * */
	private boolean modifica;
	/**
	 * Id della tabella dinamica di cui si caricheranno i dati
	 * */
	private int idTabellaDinamica;

	/**
	 * Descrizione/nome della tabella dinamica
	 * */
	private String descrTabellaDinamica;


	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Grid<VoceUnicaModel> grid;


	/**
	 * Il costruttore si preoccupa di applicare alcuni elementi di layout, di inizializzare i
	 * servizi bibliotecheService,tabelleDinamicheService recuperandoli dal Registry e inizializzare
	 * le variabili istanza con i relativi parametri passati per argomento.
	 * 
	 * @param idTabellaDinamica id della tabella dinamica da cui caricare i dati tramite servizio RPC
	 * @param descrTabellaDinamica breve descrizione/nome della tabella dinamica relativa alla variabile idTabellaDinamica
	 * */
	public ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel(int idTabellaDinamica, String descrTabellaDinamica) {
		bibliotecheService=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService=(TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		this.idTabellaDinamica=idTabellaDinamica;
		this.descrTabellaDinamica=descrTabellaDinamica;
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(130);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());
	}
	/**
	 * Crea gli oggetti per la costruzione grafica della Grid e carica i dati
	 * */
	public void setGrid(){

		//  Lista configurazioni colonne Grid

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();



		/*descrizioneVoceProxy
		 * RPCProxy,ModelReader e Loader per il caricamento paginato asincrono dei dati della tabella dinamica relativa all'id 
		 * idTabellaDinamica all'interno dello store della Combobox per oggetti di tipo VoceUnicaModel 
		 * */
		RpcProxy<PagingLoadResult<VoceUnicaModel>> descrizioneVoceProxy = new RpcProxy<PagingLoadResult<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<VoceUnicaModel>> callback) {
				tabelleDinamicheService.getListaVociFiltratePerPaginazioneCombobox(idTabellaDinamica,(ModelData)loadConfig, callback);
			}

		};

		ModelReader descrizioneVoceReader = new ModelReader();

		final PagingLoader<PagingLoadResult<VoceUnicaModel>> descrizioneVoceLoader = new BasePagingLoader<PagingLoadResult<VoceUnicaModel>>(
				descrizioneVoceProxy, descrizioneVoceReader);
		descrizioneVoceLoader.setLimit(10);

		final ListStore<VoceUnicaModel> descrizioneVoceStore = new ListStore<VoceUnicaModel>(
				descrizioneVoceLoader);
		//ComboBox paginata con autocompletamento 
		final	ComboBox<VoceUnicaModel> descrizioneVoceField = new ComboBox<VoceUnicaModel>();
		descrizioneVoceField.setDisplayField("entry");
		descrizioneVoceField.setFieldLabel("entry");
		descrizioneVoceField.setFireChangeEventOnSetValue(true);
		descrizioneVoceField.setEmptyText("Descrizione...");
		descrizioneVoceField.setForceSelection(true);
		descrizioneVoceField.setLazyRender(false);
		descrizioneVoceField.setTriggerAction(TriggerAction.ALL);
		descrizioneVoceField.setAllowBlank(false);
		descrizioneVoceField.setEditable(true);
		descrizioneVoceField.setTypeAhead(false);
		descrizioneVoceField.setMinChars(1);
		descrizioneVoceField.setPageSize(10);
		descrizioneVoceField.setStore(descrizioneVoceStore);


		CellEditor descrizioneVoceComboEditor = new CellEditor(descrizioneVoceField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				if (modifica == true) {
					return descrizioneVoceField.getStore().findModel("entry",
							value.toString());

				} else
					return "Descrizione...";
			}

			@Override
			public Object postProcessValue(Object value) {

				if (value == null) {
					if(descrizioneVoceField.getRawValue()!=null){
						return descrizioneVoceField.getRawValue();
					}
					return value;
				}
				VoceUnicaModel tmp=(VoceUnicaModel) value;
				return tmp.getEntry();
			}
		};

		// Config per colonna descrizione
		ColumnConfig descrizioneColumn = new ColumnConfig();
		descrizioneColumn.setId("entry");
		descrizioneColumn.setHeader("Descrizione");
		descrizioneColumn.setWidth(300);
		descrizioneColumn.setEditor(descrizioneVoceComboEditor);

		configs.add(descrizioneColumn);


		ColumnModel cm = new ColumnModel(configs);
		/*RPCProxy, Reader e Loader per il caricamento nello store della griglia dei dati in 
		 * base all'id della biblioteca in visione e all'id della tabella dinamica
		 * */
		RpcProxy<List<VoceUnicaModel>> proxyDestinazioneSocialeGriglia = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheService.getEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica( id_biblioteca,idTabellaDinamica, callback);
			}

		};
		ModelReader readerDestinazioneSocialeGriglia = new ModelReader();

		loaderGriglia = new BaseListLoader<ListLoadResult<VoceUnicaModel>>(proxyDestinazioneSocialeGriglia, readerDestinazioneSocialeGriglia);

		final ListStore<VoceUnicaModel> storeGriglia = new ListStore<VoceUnicaModel>(loaderGriglia);

		final RowEditorCustom<VoceUnicaModel> re = new RowEditorCustom<VoceUnicaModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		re.disable();
		
		RowEditor<VoceUnicaModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);

		grid = new Grid<VoceUnicaModel>(storeGriglia, cm);
		grid.setAutoExpandColumn("entry");
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		
		toolBar = new ToolBar();
		toolBar.setWidth(300);
		toolBar.setBorders(false);
		
		
		toolBar.add(new Text(descrTabellaDinamica));

		add = new Button("Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				re.enable();
				VoceUnicaModel newDescr = new VoceUnicaModel();

				re.stopEditing(false);
				grid.getStore().insert(newDescr, 0);
				re.startEditing(storeGriglia.indexOf(newDescr), false);

				descrizioneVoceField.clearInvalid();
			}

		});
		toolBar.add(add);

		remove = new Button("Rimuovi", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_rimuoviModalita = grid.getSelectionModel().getSelectedItem().	getIdRecord();
							bibliotecheService.removeEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(id_biblioteca, id_rimuoviModalita,idTabellaDinamica,
									new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									loaderGriglia.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
										loaderGriglia.load();
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
		descrizioneVoceField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if(se.getSelectedItem()!=null && 	grid.getStore().getCount()>0){
					grid.getStore().getAt(0).setIdRecord(se.getSelectedItem().getIdRecord());
				}
			}
		});

		re.addListener(Events.CancelEdit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				storeGriglia.remove(0);
			}	
		});

		/*Aggiungo un listener all RowEditor per la cattura dell'evento di salvataggio della nuova voce*/
		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				/*Controllo che l'eventuale stringa digitata sia contenuta nello store*/
				if(	descrizioneVoceStore.getModels().size()<=0){
					/*se lo store non è stato ancora caricato blocco l'evento e ricarico la lista
					 * e stampo un messaggio di errore*/
					be.setCancelled(true);
					loaderGriglia.load();
					AbiMessageBox.messageErrorAlertBox(AbiMessageBox.INSERIMENTO_PARAMETRO_FAILURE_MESSAGE, AbiMessageBox.INSERIMENTO_PARAMETRO_TITLE);
					return;
				}else{
					/*altrimenti itero gli elementi dello store e se l'elemento visualizzato è contenuto proseguo 
					 * altrimenti  blocco l'evento e ricarico la lista e stampo un messaggio di errore*/
					boolean trovato=false;
					for(VoceUnicaModel model :descrizioneVoceStore.getModels()){
						if(model.getEntry().compareToIgnoreCase(descrizioneVoceField.getRawValue())!=0){
							trovato =true;
							break;
						}
					}
					if(trovato==false){
						be.setCancelled(true);
						loaderGriglia.load();
						AbiMessageBox.messageErrorAlertBox(AbiMessageBox.INSERIMENTO_PARAMETRO_FAILURE_MESSAGE, AbiMessageBox.INSERIMENTO_PARAMETRO_TITLE);
						return;
					}
				}
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int idToSave=grid.getStore().getAt(0).getIdRecord();
							bibliotecheService.addEntryTabelleDinamicheByIdBiblioAndIdTabellaDinamica(idToSave,id_biblioteca,idTabellaDinamica, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									re.disable();
									loaderGriglia.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
										re.disable();

										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
										loaderGriglia.load();
									}
								}
							});
						} 
						else{
							loaderGriglia.load();		
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
	public void setIdBiblioteca(int idBiblioteca){
		this.id_biblioteca=idBiblioteca;
	}

	/**
	 * Ritorna il l'oggeto di tipo loader per il caricamento dei dati nello store della Grid 
	 * */
	public BaseListLoader<ListLoadResult<VoceUnicaModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderGriglia;	
	}
}
