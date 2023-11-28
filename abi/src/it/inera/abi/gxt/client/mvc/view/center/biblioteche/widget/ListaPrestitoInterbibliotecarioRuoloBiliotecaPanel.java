/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PrestitoInterbibliotecarioRuoloModel;
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
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
 * relative alla lista dei ruoli del prestito interbibliotecario
 *
 */
public class ListaPrestitoInterbibliotecarioRuoloBiliotecaPanel extends ContentPanel {
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
	private BaseListLoader<ListLoadResult<PrestitoInterbibliotecarioRuoloModel>> prestitiInterLoader;

	private ListStore<PrestitoInterbibliotecarioRuoloModel>  prestitiInterStore;
	/**
	 * Flag che indica se si sta modificando una voce o se ne sta creando una nuova
	 * */
	private boolean modifica;

	private Grid<PrestitoInterbibliotecarioRuoloModel> grid;
	private ToolBar windowToolBar;
	private Button add;
	private Button remove;

	public ListaPrestitoInterbibliotecarioRuoloBiliotecaPanel(){
		bibliotecheService=(BibliotecheServiceAsync)Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tabelleDinamicheService=(TabelleDinamicheServiceAsync) Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(true);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(200);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

	}
	/**
	 * Crea gli oggetti per la costruzione grafica della Grid e carica i dati
	 * */
	public void setGrid(){

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();


		RpcProxy<List<VoceUnicaModel>> ruoloProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<VoceUnicaModel>> callback) {

				tabelleDinamicheService.getListaVoci(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_MODO_INDEX, callback);
			}
		};
		ModelReader ruoloReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> ruoloLoader = new BaseListLoader<ListLoadResult<ModelData>>(
				ruoloProxy, ruoloReader);

		final	ListStore<VoceUnicaModel> ruoloStore = new ListStore<VoceUnicaModel>(
				ruoloLoader);
		final ComboBox<VoceUnicaModel> ruoloField = new ComboBox<VoceUnicaModel>();
		ruoloField.setDisplayField("entry");
		ruoloField.setFieldLabel("Ruolo");
		ruoloField.setFireChangeEventOnSetValue(true);
		ruoloField.setWidth(300);
		ruoloField.setStore(ruoloStore);
		ruoloField.setAllowBlank(false);
		ruoloField.setEmptyText("Ruolo...");
		ruoloField.setForceSelection(false);
		ruoloField.setLazyRender(false);
		ruoloField.setTriggerAction(TriggerAction.ALL);
		ruoloField.setEditable(false);


		CellEditor editor = new CellEditor(ruoloField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				PrestitoInterbibliotecarioRuoloModel tmp= prestitiInterStore.findModel("ruoloDescr",value.toString());
				VoceUnicaModel tmpRuolo=new VoceUnicaModel();
				tmpRuolo.setIdRecord(tmp.getIdRuolo());
				tmpRuolo.setEntry(tmp.getRuoloDescr());

				return tmpRuolo;
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					if(ruoloField.getRawValue()!=null){
						return ruoloField.getRawValue();
					}
					return value;
				}
				VoceUnicaModel tmp = (VoceUnicaModel)value;
				return tmp.getEntry();
			}
		};
		ColumnConfig materialeEsclusoColumn = new ColumnConfig();

		materialeEsclusoColumn.setId("ruoloDescr");
		materialeEsclusoColumn.setHeader("Ruolo");
		materialeEsclusoColumn.setWidth(300);
		materialeEsclusoColumn.setEditor(editor);
		configs.add(materialeEsclusoColumn);

		final	SimpleComboBox<String> nazionaleField = new SimpleComboBox<String>();

		nazionaleField.setForceSelection(true);
		nazionaleField.setTriggerAction(TriggerAction.ALL);
		nazionaleField.setEmptyText("Si/No...");
		nazionaleField.setEditable(false);
		nazionaleField.setAllowBlank(false);
		nazionaleField.setFireChangeEventOnSetValue(true);
		nazionaleField.add("Si");
		nazionaleField.add("No");
		nazionaleField.setSimpleValue("No");

		CellEditor nazionaleEditor = new CellEditor(nazionaleField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return nazionaleField.findModel(value.toString());

			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		ColumnConfig nazionaleColumn = new ColumnConfig();

		nazionaleColumn.setId("nazionale");

		nazionaleColumn.setHeader("Nazionale");
		nazionaleColumn.setWidth(100);
		nazionaleColumn.setEditor(nazionaleEditor);
		nazionaleColumn.setAlignment(HorizontalAlignment.CENTER);
		configs.add(nazionaleColumn);

		final	SimpleComboBox<String> internazionaleField = new SimpleComboBox<String>();

		internazionaleField.setForceSelection(true);
		internazionaleField.setTriggerAction(TriggerAction.ALL);
		internazionaleField.setEmptyText("Si/No...");
		internazionaleField.setEditable(false);
		internazionaleField.setAllowBlank(false);
		internazionaleField.setFireChangeEventOnSetValue(true);
		internazionaleField.add("Si");
		internazionaleField.add("No");
		internazionaleField.setSimpleValue("No");

		CellEditor internazionaleEditor = new CellEditor(internazionaleField) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return internazionaleField.findModel(value.toString());

			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
				//return schedeField.getValue();
			}
		};

		ColumnConfig internazionaleColumn = new ColumnConfig();

		internazionaleColumn.setId("internazionale");
		internazionaleColumn.setHeader("Internazionale");
		internazionaleColumn.setWidth(100);
		internazionaleColumn.setEditor(internazionaleEditor);
		internazionaleColumn.setAlignment(HorizontalAlignment.CENTER);
		configs.add(internazionaleColumn);

		RpcProxy<List<PrestitoInterbibliotecarioRuoloModel>> prestitiInterProxy= new RpcProxy<List<PrestitoInterbibliotecarioRuoloModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<PrestitoInterbibliotecarioRuoloModel>> callback) {
				bibliotecheService.getPrestitoInterbibliotecarioERuoloByIdBiblio(id_biblioteca,callback);

			}
		};

		ModelReader prestitiInterReader = new ModelReader();

		prestitiInterLoader=new 	BaseListLoader<ListLoadResult<PrestitoInterbibliotecarioRuoloModel>>(
				prestitiInterProxy, prestitiInterReader);

		prestitiInterStore=new ListStore<PrestitoInterbibliotecarioRuoloModel>(prestitiInterLoader);

		final ColumnModel cm = new ColumnModel(configs);

		final RowEditorCustom<PrestitoInterbibliotecarioRuoloModel> prestitiInterRe = new RowEditorCustom<PrestitoInterbibliotecarioRuoloModel>();
		prestitiInterRe.setClicksToEdit(ClicksToEdit.TWO);
		prestitiInterRe.setErrorSummary(false);

		RowEditor<PrestitoInterbibliotecarioRuoloModel>.RowEditorMessages rowEditorMessages = prestitiInterRe.getMessages();
        rowEditorMessages.setCancelText("Annulla");
        rowEditorMessages.setSaveText("Salva");
        prestitiInterRe.setMessages(rowEditorMessages);
		
		grid = new Grid<PrestitoInterbibliotecarioRuoloModel>(prestitiInterStore,cm);
		grid.addPlugin(prestitiInterRe);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		
		/*TOOLBAR*/
		windowToolBar = new ToolBar();

		windowToolBar.setWidth(300);
		windowToolBar.setBorders(false);

		windowToolBar.add(new Text("Ruolo della biblioteca "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				ruoloField.enable();
				PrestitoInterbibliotecarioRuoloModel newPrestito = new PrestitoInterbibliotecarioRuoloModel();
				prestitiInterRe.stopEditing(false);
				prestitiInterStore.insert(newPrestito, 0);
				prestitiInterRe.startEditing(prestitiInterStore.indexOf(newPrestito), false);
			
				ruoloField.clearInvalid();
				nazionaleField.clearInvalid();
				internazionaleField.clearInvalid();
			}
		});
		windowToolBar.add(add);

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(final MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							int	id_removePrestito=grid.getSelectionModel().getSelectedItem().getIdPrestitoInterbibliotecario();
							bibliotecheService.removePrestitoInterbibliotecarioFromBiblio(id_biblioteca,id_removePrestito,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									prestitiInterLoader.load();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										ce.<Component> getComponent().disable();
										prestitiInterLoader.load();
									}
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
				remove.disable();
			}

		});
		remove.disable();		


		remove.setIcon(Resources.ICONS.delete());
		windowToolBar.add(remove);
		add(grid);
		setTopComponent(windowToolBar);
		/*FINE TOOLBAR*/

		grid.addListener(Events.RowClick,
				new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

			public void handleEvent(
					GridEvent<PartecipaCataloghiCollettiviModel> be) {
				modifica=false;
				remove.enable();
			}
		});

		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

			public void handleEvent(
					GridEvent<PartecipaCataloghiCollettiviModel> be) {
				modifica=true;
				remove.disable();
				ruoloField.disable();
			}
		});

		prestitiInterRe.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if(modifica==false){
					prestitiInterStore.remove(0);	
				}
				remove.disable();
			}
		});

		prestitiInterRe.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						PrestitoInterbibliotecarioRuoloModel tmpPrestito = new PrestitoInterbibliotecarioRuoloModel();
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {


							if (modifica==false) {
								tmpPrestito.setNazionale(nazionaleField.getValue().getValue());
								tmpPrestito.setInternazionale(internazionaleField.getValue().getValue());
								tmpPrestito.setIdRuolo(ruoloField.getValue().getIdRecord());
							}else{

								tmpPrestito.setIdPrestitoInterbibliotecario(grid.getSelectionModel().getSelectedItem().getIdPrestitoInterbibliotecario());
								tmpPrestito.setNazionale(grid.getSelectionModel().getSelectedItem().getNazionale());
								tmpPrestito.setInternazionale(grid.getSelectionModel().getSelectedItem().getInternazionale());
								tmpPrestito.setIdRuolo(grid.getSelectionModel().getSelectedItem().getIdRuolo());
							}
							bibliotecheService.addPrestitoInterbibliotecarioToBiblio(id_biblioteca, tmpPrestito,	modifica,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									prestitiInterLoader.load();
									modifica = false;
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}										prestitiInterLoader.load();
										modifica = false;
									}
								}
							});
						} else {
							if (modifica == false) {
								prestitiInterStore.remove(0);
							} else {
								modifica = false;
							}
							prestitiInterLoader.load();
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
	public BaseListLoader<ListLoadResult<PrestitoInterbibliotecarioRuoloModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(windowToolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(windowToolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.prestitiInterLoader;	
	}


}
