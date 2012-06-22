package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaSpecializzazioniPanel extends ContentPanel {
	private BibliotecheServiceAsync bibliotecheService;
	private TabelleDinamicheServiceAsync tdsa;
	private int id_biblioteca;

	private String id_newDewey;
	private String idr_removeRecord;

	private BaseListLoader<ListLoadResult<SpecializzazioneModel>> specializzazioniLoader;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Button modifica;
	private Grid<SpecializzazioneModel> grid;

	private ContenitoreSpecializzazionePanel creazioneSpecializzazioneFormPanel;
	private ContenitoreSpecializzazionePanel modificaSpecializzazioneFormPanel;
	public ListaSpecializzazioniPanel() {
		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(750);
		setHeight(400);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

		creazioneSpecializzazioneFormPanel= new ContenitoreSpecializzazionePanel(false);
		modificaSpecializzazioneFormPanel= new ContenitoreSpecializzazionePanel(true);
	}

	public void setGrid() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();


		ColumnConfig columnDewey = new ColumnConfig();
		columnDewey.setId("dewey");
		columnDewey.setHeader("Codice Dewey");
		columnDewey.setWidth(110);

		configs.add(columnDewey);

		ColumnConfig columnDescrUfficiale = new ColumnConfig();
		columnDescrUfficiale.setId("descrizioneDewey");
		columnDescrUfficiale.setHeader("Descrizione ufficiale");
		columnDescrUfficiale.setWidth(350);
		columnDescrUfficiale.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return  Utils.insertBRtag((String)(model.get("descrizioneDewey")!=null?model.get("descrizioneDewey"):"") ,66) ;  
			}  

		});  
		configs.add(columnDescrUfficiale);

		ColumnConfig columnDescrLibera = new ColumnConfig();
		columnDescrLibera.setId("descrLibera");
		columnDescrLibera.setHeader("Descrizione libera");
		columnDescrLibera.setWidth(330);
		columnDescrLibera.setRenderer(new GridCellRenderer<ModelData>() {  

			public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex,  
					ListStore<ModelData> store, Grid<ModelData> grid) {  
				return  Utils.insertBRtag((String)(model.get("descrLibera")!=null?model.get("descrLibera"):"") ,55) ;  
			}  

		});  
		configs.add(columnDescrLibera);

		ColumnModel cm = new ColumnModel(configs);

		RpcProxy<List<SpecializzazioneModel>> specializzazioniProxy = new RpcProxy<List<SpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<SpecializzazioneModel>> callback) {
				bibliotecheService.getSpecializzazioniByIdBiblioteca(id_biblioteca,	callback);
			}
		};
		ModelReader specializzazioniReader = new ModelReader();

		specializzazioniLoader = new BaseListLoader<ListLoadResult<SpecializzazioneModel>>(
				specializzazioniProxy, specializzazioniReader);

		final ListStore<SpecializzazioneModel> store = new ListStore<SpecializzazioneModel>(specializzazioniLoader);


		grid = new Grid<SpecializzazioneModel>(store, cm);
		grid.setBorders(true);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);

		toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Specializzazioni:"));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				modifica.disable();
				remove.disable();
				final Window window = new Window();
				window.setSize(650, 250);
				window.setModal(true);
				window.setHeading("Nuova specializzazione");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				window.add(creazioneSpecializzazioneFormPanel);

				creazioneSpecializzazioneFormPanel.reset();
				creazioneSpecializzazioneFormPanel.setIdBiblio(id_biblioteca);
				creazioneSpecializzazioneFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}
				});
				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}

		});

		toolBar.add(add);

		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// grid.getStore().remove(grid.getStore().getAt(0));
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							modifica.disable();
							bibliotecheService.removeSpecializzazionePatrimonio(
									id_biblioteca, idr_removeRecord,
									new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {

											specializzazioniLoader.load();
											remove.disable();
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
												remove.disable();
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
												specializzazioniLoader.load();
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

		modifica = new Button("Modifica");
		modifica.setIcon(Resources.ICONS.edit());
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				modifica.disable();
				final Window window = new Window();
				window.setSize(650, 250);
				window.setModal(true);
				window.setHeading("Modifica specializzazione");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				modificaSpecializzazioneFormPanel.reset();
				modificaSpecializzazioneFormPanel.setIdBiblio(id_biblioteca);
				if(grid.getSelectionModel().getSelectedItem()!=null){
					modificaSpecializzazioneFormPanel.setValues(grid.getSelectionModel().getSelectedItem());
				}
				modificaSpecializzazioneFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}
				});
				window.add(modificaSpecializzazioneFormPanel);

				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}

		});
		modifica.disable();
		toolBar.add(modifica);

		grid.addListener(Events.RowClick,
				new Listener<GridEvent<SpecializzazioneModel>>() {

			public void handleEvent(GridEvent<SpecializzazioneModel> be) {
				idr_removeRecord= be.getGrid().getSelectionModel().getSelectedItem().getDewey();
				modifica.enable();
				remove.enable();
			}
		});


		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);

	}

	public void setIdBiblioteca(int idBiblioteca){
		this.id_biblioteca=idBiblioteca;
	}


	public BaseListLoader<ListLoadResult<SpecializzazioneModel>> getLoader(){

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, modifica);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.specializzazioniLoader;	
	}


	public void setIdModificaSpecializzazionePanel() {
		modificaSpecializzazioneFormPanel.setIdBiblio(id_biblioteca);
		modificaSpecializzazioneFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				specializzazioniLoader.load();
			}

		});		
	}

	public void setIdNuovaSpecializzazionePanel(){
		creazioneSpecializzazioneFormPanel.setIdBiblio(id_biblioteca);
		creazioneSpecializzazioneFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				specializzazioniLoader.load();
			}

		});
	}
}

