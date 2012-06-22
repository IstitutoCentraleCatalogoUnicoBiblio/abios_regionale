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
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
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

public class ListaOrariUfficialiPanel extends ContentPanel {
	private ToolBar toolBar;
	private String day;
	private int id_day;
	private BaseListLoader<ListLoadResult<OrariModel>> loaderOrariUfficiali;
	private BibliotecheServiceAsync bibliotecheServiceAsync ;
	private int id_biblioteca;
	private RowEditorCustom<OrariModel> re;
	private boolean modifica =false;

	private Button add;
	private Button remove;
	private Grid<OrariModel> grid;

	public ListaOrariUfficialiPanel(int id_day) {
		bibliotecheServiceAsync=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		this.id_day=id_day;
		this.day = OrariModel.selectDayName(id_day);
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(id_day!=7?113:119);
		setHeight(200);
		setScrollMode(Scroll.NONE);
		setLayout(new FitLayout());

	}

	public void setGrid() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final SimpleComboBox<String> startOreField = new SimpleComboBox<String>();
		startOreField.setEmptyText("hh");
		startOreField.setAllowBlank(false);
		startOreField.setHideTrigger(true);
		startOreField.setForceSelection(true);
		startOreField.setWidth(27);
		startOreField.setTriggerAction(TriggerAction.ALL);
		startOreField.setEditable(false);
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
		startOreColumn.setWidth(27);
		startOreColumn.setEditor(startOreEditor);

		configs.add(startOreColumn);

		final SimpleComboBox<String> startMinField = new SimpleComboBox<String>();
		startMinField.setEmptyText("mm");
		startMinField.setAllowBlank(false);
		startMinField.setHideTrigger(true);
		startMinField.setForceSelection(true);
		startMinField.setWidth(27);
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
		startMinColumn.setWidth(27);
		startMinColumn.setEditor(startMinEditor);

		configs.add(startMinColumn);

		final SimpleComboBox<String> stopOreField = new SimpleComboBox<String>();
		stopOreField.setEmptyText("hh");
		stopOreField.setAllowBlank(false);
		stopOreField.setHideTrigger(true);
		stopOreField.setForceSelection(true);
		stopOreField.setWidth(27);
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
		stopOreColumn.setWidth(27);
		stopOreColumn.setEditor(stopOreEditor);

		configs.add(stopOreColumn);

		final SimpleComboBox<String> stopMinField = new SimpleComboBox<String>();
		stopMinField.setEmptyText("mm");
		stopMinField.setAllowBlank(false);
		stopMinField.setHideTrigger(true);
		stopMinField.setWidth(27);
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
		stopMinColumn.setWidth(27);
		stopMinColumn.setEditor(stopMinEditor);

		configs.add(stopMinColumn);


		ColumnModel cm = new ColumnModel(configs);

		cm.addHeaderGroup(0, 2, new HeaderGroupConfig("alle:", 1, 2));
		cm.addHeaderGroup(0, 0, new HeaderGroupConfig("Dalle:", 1, 2));

		re = new RowEditorCustom<OrariModel>();
		re.setButtonAlign(HorizontalAlignment.CENTER);
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setErrorSummary(false);
		
		RowEditor<OrariModel>.RowEditorMessages rowEditorMessages = re.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        re.setMessages(rowEditorMessages);
		re.addStyleName("font-weight-button-normal");
	
		RpcProxy<List<OrariModel>> proxyOrariUfficiali = new RpcProxy<List<OrariModel>>() {



			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<OrariModel>> callback) {
				bibliotecheServiceAsync.getOrariUfficialiByDay( id_biblioteca,id_day, callback);
			}

		};
		ModelReader readerDestinazioneSocialeGriglia = new ModelReader();

		loaderOrariUfficiali = new BaseListLoader<ListLoadResult<OrariModel>>(proxyOrariUfficiali, readerDestinazioneSocialeGriglia);

		final ListStore<OrariModel> storeOrariUfficiali = new ListStore<OrariModel>(loaderOrariUfficiali);



		grid = new Grid<OrariModel>(storeOrariUfficiali, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.addPlugin(re);
		grid.getView().setAutoFill(true);
		
		toolBar = new ToolBar();

		toolBar.setBorders(false);

		Text dayTitle = new Text(day);
		dayTitle.setStyleAttribute("fontSize", "14");
		toolBar.add(dayTitle);
		add = new Button();
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				OrariModel newOrario = new OrariModel();
				re.stopEditing(false);
				storeOrariUfficiali.insert(newOrario, 0);
				re.startEditing(storeOrariUfficiali.indexOf(newOrario), false);
				modifica =false;
				remove.disable();
				
				startOreField.clearInvalid();
				startMinField.clearInvalid();
				stopOreField.clearInvalid();
				stopMinField.clearInvalid();
			}
		});
		toolBar.add(add);

		remove = new Button();
		remove.addSelectionListener(	new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int id_orarioToRemove = grid.getSelectionModel().getSelectedItem().getIdOrario();
							bibliotecheServiceAsync.removeOrarioUfficiale(
									id_orarioToRemove,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {

											loaderOrariUfficiali.load();

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
						remove.disable();
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
					storeOrariUfficiali.remove(0);
				}
				modifica = false;
				loaderOrariUfficiali.load();
			}	

		});

		re.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				OrariModel tmpSave=null;

				if(modifica==false){
					tmpSave = storeOrariUfficiali.getAt(0);
				}
				else {	
					tmpSave =grid.getSelectionModel().getSelectedItem();
				}

				if(	OrarioUfficaleVariazioniPanel.checkOrarioFormat(tmpSave)){

					final OrariModel toSave =tmpSave;

					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

						@Override
						public void handleEvent(MessageBoxEvent ce) {

							Button btn = ce.getButtonClicked();
							if (btn.getText().equalsIgnoreCase("Si")) {



								bibliotecheServiceAsync.addNuovoOrarioGiornaliero(	id_biblioteca, id_day,toSave,modifica,
										new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										loaderOrariUfficiali.load();	
										modifica = false;
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}


								});
							} else {
								if(modifica==false){
									storeOrariUfficiali.remove(0);
								}else{
									storeOrariUfficiali.rejectChanges();
								}
							}
//							loaderOrariUfficiali.load();	
							modifica=false;
						}

					};
					AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
				}else{
					if(modifica==false){
						storeOrariUfficiali.remove(0);
					}
					else {
						storeOrariUfficiali.remove(grid.getSelectionModel().getSelectedItem());
					}
					modifica=false;
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

		return this.loaderOrariUfficiali;	
	}
}
