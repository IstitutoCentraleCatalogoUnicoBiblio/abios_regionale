package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.OrarioUfficaleVariazioniPanel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
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
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaVariazioniOrarioPanel extends ContentPanel {

	private ToolBar toolBar;
	private BaseListLoader<ListLoadResult<OrariModel>> loaderVariazioniOrari;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private int id_biblioteca;
	private boolean modifica =false;

	private Button add;
	private Button remove;
	private Grid<OrariModel> grid;

	public ListaVariazioniOrarioPanel() {

		bibliotecheServiceAsync=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);

		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.NONE);
		setLayout(new FitLayout());
	}

	public void setGrid(){
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig periodoVariazioneColumn = new ColumnConfig();
		periodoVariazioneColumn.setAlignment(HorizontalAlignment.CENTER);
		periodoVariazioneColumn.setId("periodo");
		periodoVariazioneColumn.setHeader("Periodo variazione");
		periodoVariazioneColumn.setWidth(320);

		final TextField<String> periodoField = new TextField<String>();
		periodoField.setAllowBlank(false);
		periodoField.setEmptyText("Periodo di variazione...");
		periodoVariazioneColumn.setEditor(new CellEditor(periodoField));
		configs.add(periodoVariazioneColumn);

		final SimpleComboBox<String> giorniField = new SimpleComboBox<String>();
		giorniField.setForceSelection(true);
		giorniField.setWidth(34);
		giorniField.setEmptyText("Giorno...");
		giorniField.setTriggerAction(TriggerAction.ALL);
		giorniField.setEditable(false);
		giorniField.setAllowBlank(false);
		giorniField.setFireChangeEventOnSetValue(true);
		giorniField.add("Lunedì");
		giorniField.add("Martedì");
		giorniField.add("Mercoledì");
		giorniField.add("Giovedì");
		giorniField.add("Venerdì");
		giorniField.add("Sabato");
		giorniField.add("Domenica");

		CellEditor giorniEditor = new CellEditor(giorniField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return giorniField.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig giorniColumn = new ColumnConfig();

		giorniColumn.setId("nomeGiorno");
		giorniColumn.setAlignment(HorizontalAlignment.CENTER);
		giorniColumn.setHeader("Giorno");
		giorniColumn.setWidth(100);
		giorniColumn.setEditor(giorniEditor);

		configs.add(giorniColumn);

		final SimpleComboBox<String> startOreField = new SimpleComboBox<String>();
		startOreField.setAllowBlank(false);
		startOreField.setEmptyText("hh");
		startOreField.setHideTrigger(true);
		startOreField.setForceSelection(true);
		startOreField.setWidth(34);
		startOreField.setTriggerAction(TriggerAction.ALL);
		startOreField.setEditable(false);
		startOreField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i <= 24; i++) {
			if(i<10){
			startOreField.add("0" + i);
			}else{
				startOreField.add("" + i);
			}
		}

		CellEditor startOreEditor = new CellEditor(startOreField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return startOreField.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig startOreColumn = new ColumnConfig();

		startOreColumn.setId("startOre");
		startOreColumn.setAlignment(HorizontalAlignment.RIGHT);
		startOreColumn.setHeader("H");
		startOreColumn.setWidth(34);
		startOreColumn.setEditor(startOreEditor);

		configs.add(startOreColumn);

		final SimpleComboBox<String> startMinField = new SimpleComboBox<String>();
		startMinField.setAllowBlank(false);
		startMinField.setEmptyText("mm");
		startMinField.setHideTrigger(true);
		startMinField.setForceSelection(true);
		startMinField.setWidth(34);
		startMinField.setTriggerAction(TriggerAction.ALL);
		startMinField.setEditable(false);
		startMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				startMinField.add("" + i + j);
			}

		}

		startOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				startMinField.focus();
			}
		});
		
		
		CellEditor startMinEditor = new CellEditor(startMinField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return startMinField.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig startMinColumn = new ColumnConfig();
		startMinColumn.setAlignment(HorizontalAlignment.LEFT);
		startMinColumn.setId("startMin");
		startMinColumn.setHeader("M");
		startMinColumn.setWidth(34);
		startMinColumn.setEditor(startMinEditor);

		configs.add(startMinColumn);

		final SimpleComboBox<String> stopOreField = new SimpleComboBox<String>();
		stopOreField.setAllowBlank(false);
		stopOreField.setEmptyText("hh");
		stopOreField.setHideTrigger(true);
		stopOreField.setForceSelection(true);
		stopOreField.setWidth(34);
		stopOreField.setTriggerAction(TriggerAction.ALL);
		stopOreField.setEditable(false);
		stopOreField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i <= 24; i++) {
			if(i<10){
				stopOreField.add("0" + i);
			}else{
				stopOreField.add("" + i);
			}
		}

		startMinField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopOreField.focus();
			}
		});
		
		CellEditor stopOreEditor = new CellEditor(stopOreField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return stopOreField.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig stopOreColumn = new ColumnConfig();
		stopOreColumn.setAlignment(HorizontalAlignment.RIGHT);
		stopOreColumn.setId("stopOre");
		stopOreColumn.setHeader("H");
		stopOreColumn.setWidth(34);
		stopOreColumn.setEditor(stopOreEditor);

		configs.add(stopOreColumn);

		final SimpleComboBox<String> stopMinField = new SimpleComboBox<String>();
		stopMinField.setAllowBlank(false);
		stopMinField.setEmptyText("mm");
		stopMinField.setHideTrigger(true);
		stopMinField.setWidth(34);
		stopMinField.setForceSelection(true);
		stopMinField.setTriggerAction(TriggerAction.ALL);
		stopMinField.setEditable(false);
		stopMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				stopMinField.add("" + i + j);
			}

		}

		stopOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopMinField.focus();
			}
		});
		
		CellEditor stopMinEditor = new CellEditor(stopMinField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return stopMinField.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig stopMinColumn = new ColumnConfig();
		stopMinColumn.setAlignment(HorizontalAlignment.LEFT);
		stopMinColumn.setId("stopMin");
		stopMinColumn.setHeader("M");
		stopMinColumn.setWidth(34);
		stopMinColumn.setEditor(stopMinEditor);

		configs.add(stopMinColumn);


		ColumnModel cm = new ColumnModel(configs);

		cm.addHeaderGroup(0, 2, new HeaderGroupConfig("Dalle:", 1, 2));
		cm.addHeaderGroup(0, 4, new HeaderGroupConfig("alle:", 1, 2));

		final RowEditorCustom<OrariModel> re = new RowEditorCustom<OrariModel>();
		re.setButtonAlign(HorizontalAlignment.CENTER);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<OrariModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		
		RpcProxy<List<OrariModel>> proxyVariazioniOrari = new RpcProxy<List<OrariModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<OrariModel>> callback) {
				bibliotecheServiceAsync.getVariazioniOrari( id_biblioteca, callback);
			}

		};
		ModelReader readerVariazioniOrari = new ModelReader();

		loaderVariazioniOrari = new BaseListLoader<ListLoadResult<OrariModel>>(proxyVariazioniOrari, readerVariazioniOrari);

		final ListStore<OrariModel> variazioniOrariStore = new ListStore<OrariModel>(loaderVariazioniOrari);


		grid = new Grid<OrariModel>(variazioniOrariStore, cm);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		giorniField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				if (se.getSelectedItem() != null) {
					int	id_day=OrariModel.selectIdDay(se.getSelectedItem().getValue());
					if (modifica==false){
						variazioniOrariStore.getAt(0).setGiorno(id_day);
					}else{
						Integer o=	grid.getSelectionModel().getSelection().indexOf(grid.getSelectionModel().getSelectedItem());
						variazioniOrariStore.getAt(o).setGiorno(id_day);
					}
				}
			}
		});
		toolBar = new ToolBar();
		toolBar.setBorders(false);
		toolBar.setStyleAttribute("marginTop", "20");
		toolBar.add(new Text("Variazione orario"));
		add = new Button(" Aggiungi ");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				OrariModel newVariazione = new OrariModel();
				re.stopEditing(false);
				variazioniOrariStore.insert(newVariazione, 0);
				re.startEditing(variazioniOrariStore.indexOf(newVariazione), false);
				remove.disable();
				
				periodoField.clearInvalid();
				giorniField.clearInvalid();
				startOreField.clearInvalid();
				startMinField.clearInvalid();
				stopOreField.clearInvalid();
				stopMinField.clearInvalid();
			}
		});
		toolBar.add(add);

		remove = new Button(" Rimuovi ");

		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_variazioneOrarioToRemove = grid.getSelectionModel().getSelectedItem().getIdOrario();
							bibliotecheServiceAsync.removeVariazioneOrario( id_variazioneOrarioToRemove, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									loaderVariazioniOrari.load();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}
							});
						} else {

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
				modifica=false;
				remove.enable();
			}
		});
		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<VoceUnicaModel>>() {

			public void handleEvent(GridEvent<VoceUnicaModel> be) {
				modifica=true;
				remove.disable();
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
					variazioniOrariStore.remove(0);
				}
				modifica = false;
			}	

		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				OrariModel tmpSave=null;
				if(modifica==false){
					tmpSave = variazioniOrariStore.getAt(0);
				}
				else {	 tmpSave =grid.getSelectionModel().getSelectedItem();
				}

				if(		OrarioUfficaleVariazioniPanel.checkOrarioFormat(tmpSave)){

					final OrariModel toSave =tmpSave;

					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

						@Override
						public void handleEvent(MessageBoxEvent ce) {

							Button btn = ce.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {



								bibliotecheServiceAsync.addNuovaVariazioneOrario( id_biblioteca, toSave, modifica, new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loaderVariazioniOrari.load();	
										modifica = false;
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
											modifica = false;
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}
									}
								});
							} else {
								if(modifica==false){
									variazioniOrariStore.remove(0);
								}else{
									variazioniOrariStore.rejectChanges();
								}
							}
							modifica=false;
						}

					};
					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

				}else{
					if(modifica==false){
						variazioniOrariStore.remove(0);
					}else{
						variazioniOrariStore.remove(grid.getSelectionModel().getSelectedItem());
					}
					modifica = false;
				}
			}


		});
	}

	public void setIdBiblioteca(int idBiblioteca){
		this.id_biblioteca=idBiblioteca;
	}

	public BaseListLoader<ListLoadResult<OrariModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.loaderVariazioniOrari;	
	}

}
