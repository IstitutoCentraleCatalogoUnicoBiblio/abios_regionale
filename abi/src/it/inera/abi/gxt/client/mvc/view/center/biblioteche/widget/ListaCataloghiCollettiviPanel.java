package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PatrimonioSpecializzazioneModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

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
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ListaCataloghiCollettiviPanel extends ListaCataloghiGenericaPanel {
	private GroupingStore<PartecipaCataloghiCollettiviModel> partecipaCataloghiCollettiviGrigliaStore;

	private BaseListLoader<ListLoadResult<PartecipaCataloghiCollettiviModel>> partecipaCataloghiCollettiviLoader;

	private	CreazioneModificaCataloghiCollettiviPanel creaNuovoCatalogoCollettivoFormPanel;
	private	CreazioneModificaCataloghiCollettiviPanel modificaCatalogoCollettivoFormPanel;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Button modifica;
	private Grid<PartecipaCataloghiCollettiviModel> grid;

	public ListaCataloghiCollettiviPanel() {
		super();
		//Setto il tipo di catalogo
		this.tipoCatalogo=2;
		creaNuovoCatalogoCollettivoFormPanel = new CreazioneModificaCataloghiCollettiviPanel(false);
		modificaCatalogoCollettivoFormPanel= new CreazioneModificaCataloghiCollettiviPanel(true);
	}

	public void setGrid() {
		//Servizio per il caricamento dei dati nello store della griglia
		RpcProxy<List<PartecipaCataloghiCollettiviModel>> partecipaCataloghiCollettiviProxy = new RpcProxy<List<PartecipaCataloghiCollettiviModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<PartecipaCataloghiCollettiviModel>> callback) {

				bibliotecheService.getPartecipaCataloghiCollettiviByIdBiblio(id_biblioteca, callback);
			}

		};

		ModelReader partecipaCataloghiCollettiviReader = new ModelReader();

		partecipaCataloghiCollettiviLoader  = new BaseListLoader<ListLoadResult<PartecipaCataloghiCollettiviModel>>(
				partecipaCataloghiCollettiviProxy, partecipaCataloghiCollettiviReader);



		partecipaCataloghiCollettiviGrigliaStore = new GroupingStore<PartecipaCataloghiCollettiviModel>(partecipaCataloghiCollettiviLoader);

		partecipaCataloghiCollettiviGrigliaStore.groupBy("intestazioneComposta");  


		// Creo le configurazioni per le colonne relative ai cataloghi
		// collettivi
		List<ColumnConfig> configs = getSpecializedColumnConfig();
		// Vi aggiungo le configurazioni comuni a tutti i cataloghi
		configs.addAll(getBaseConfigs());

		final	ColumnModel cm = new ColumnModel(configs);

		final	GroupingView view = new GroupingView();  
		view.setShowGroupedColumn(false);  
		view.setForceFit(true);  
		view.setGroupRenderer(new GridGroupRenderer() {  
			public String render(GroupColumnData data) {  
				String l = data.models.size() == 1 ? "Catalogo" : "Cataloghi";  
				return data.group + " (" + data.models.size() + " " + l + ")";  
			}  
		});  

		grid = new Grid<PartecipaCataloghiCollettiviModel>(partecipaCataloghiCollettiviGrigliaStore, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.setView(view);
		grid.getView().setAutoFill(true);
		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Cataloghi collettivi "));
	
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 550);
				window.setModal(true);
				window.setHeading("Nuovo Catalogo Collettivo");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				window.add(creaNuovoCatalogoCollettivoFormPanel);

				creaNuovoCatalogoCollettivoFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {


					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
						remove.disable();
						modifica.disable();
					}
				});
				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}
		});
		toolBar.add(add);

		modifica = new Button("Modifica");
		modifica.setIcon(Resources.ICONS.edit());
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 550);
				window.setModal(true);
				window.setHeading("Modifica Catalogo Collettivo");
				window.setLayout(new FitLayout());
				window.setClosable(true);
				if(grid.getSelectionModel().getSelectedItem()!=null){
					modificaCatalogoCollettivoFormPanel.setValues(grid.getSelectionModel().getSelectedItem());
					modificaCatalogoCollettivoFormPanel.disableDenominazione();
				}
				/*CONTENUTO FINESTRA*/
				window.add(modificaCatalogoCollettivoFormPanel);

				modificaCatalogoCollettivoFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {
			
					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
						remove.disable();
						modifica.disable();
					}
				});
				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}
		});
		modifica.disable();
		toolBar.add(modifica);
		
		remove = new Button("Rimuovi");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							modifica.disable();
							int idRemove= grid.getSelectionModel().getSelectedItem().getIdPartecipaCatalogo();
							bibliotecheService.removePartecipaCatalogoCollettivo(idRemove,id_biblioteca,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									partecipaCataloghiCollettiviLoader.load();
									remove.disable();

									MessageBox
									.info("Esito rimozione",
											"Rimozione effettuata con successo!",
											null).show();		
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox("Si è verificato un errore durante la rimozione della voce!", "ERRORE RIMOZIONE");
										partecipaCataloghiCollettiviLoader.load();
										remove.disable();
									}
								}

							});

						} else {

						}
					}
				};
				MessageBox.confirm("Rimozione voce",
						"La modifica verrà applicata al database! Continuare?",
						l).show();
				if (grid.getStore().getCount() == 0) {
					ce.<Component> getComponent().disable();
				}
			}
		});
		remove.disable();
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

			public void handleEvent(GridEvent<PartecipaCataloghiCollettiviModel> be) {
				remove.enable();
				modifica.enable();
			}
		});

		remove.setIcon(Resources.ICONS.delete());
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);
	}

	public List<ColumnConfig> getSpecializedColumnConfig() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		//		//MATERIALE
		ColumnConfig denominazioneMaterialeColumn = new ColumnConfig();
		denominazioneMaterialeColumn.setId("denominazioneMateriale");
		denominazioneMaterialeColumn.setHeader("Tipo materiale");
		denominazioneMaterialeColumn.setWidth(120);
		configs.add(denominazioneMaterialeColumn);
		return configs;
	}

	/**
	 * Ritorna l'oggeto di tipo loader per il caricamento dei dati nello
	 * store della Grid
	 * */
	public BaseListLoader<ListLoadResult<PartecipaCataloghiCollettiviModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, modifica);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.partecipaCataloghiCollettiviLoader;
	}

	public void setIdModificaCatalogoPanel() {
		modificaCatalogoCollettivoFormPanel.setIdBiblio(id_biblioteca);
		modificaCatalogoCollettivoFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiCollettiviLoader.load();
			}

		});		
	}

	public void setIdNuovoCatalogoPanel(){
		creaNuovoCatalogoCollettivoFormPanel.setIdBiblio(id_biblioteca);
		creaNuovoCatalogoCollettivoFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiCollettiviLoader.load();
			}

		});
	}


}