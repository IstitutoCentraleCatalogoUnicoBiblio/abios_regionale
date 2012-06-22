package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiSpecialiModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.List;

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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ListaCataloghiSpecialiPanel extends ListaCataloghiGenericaPanel {
	private ListStore<PartecipaCataloghiSpecialiModel> partecipaCataloghiSpecialiGrigliaStore;

	private BaseListLoader<ListLoadResult<PartecipaCataloghiSpecialiModel>> partecipaCataloghiSpecialiLoader;

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Button modifica;

	private Grid<PartecipaCataloghiSpecialiModel> grid;

	
	private CreazioneModificaCataloghiSpecialiPanel	creaNuovoCatalogoSpecialeFormPanel;
	private CreazioneModificaCataloghiSpecialiPanel	modificaCatalogoSpecialeFormPanel;
	public ListaCataloghiSpecialiPanel() {
		super();
		//Setto il tipo di catalogo
		this.tipoCatalogo=3;
		creaNuovoCatalogoSpecialeFormPanel =new CreazioneModificaCataloghiSpecialiPanel(false);
		modificaCatalogoSpecialeFormPanel =new CreazioneModificaCataloghiSpecialiPanel(true);
	}
	public void setGrid(){

		//Creo le configurazioni per le colonne relative ai cataloghi speciali
		List<ColumnConfig> configs = getSpecializedColumnConfig();
		//Vi aggiungo le configurazioni comuni a tutti i cataloghi
		configs.addAll(getBaseConfigs());
		ColumnModel cm = new ColumnModel(configs);


		//Servizio per il caricamento dei dati nello store della griglia
		RpcProxy<List<PartecipaCataloghiSpecialiModel>> partecipaCataloghiSpecialiProxy = new RpcProxy<List<PartecipaCataloghiSpecialiModel>>() {

			@Override
			protected void load(Object loadConfig,AsyncCallback<List<PartecipaCataloghiSpecialiModel>> callback) {

				bibliotecheService.getPartecipaCataloghiSpecialiByIdBiblio(id_biblioteca, callback);
			}

		};

		ModelReader partecipaCataloghiSpecialiReader = new ModelReader();

		partecipaCataloghiSpecialiLoader  = new BaseListLoader<ListLoadResult<PartecipaCataloghiSpecialiModel>>(
				partecipaCataloghiSpecialiProxy, partecipaCataloghiSpecialiReader);

		partecipaCataloghiSpecialiGrigliaStore = new ListStore<PartecipaCataloghiSpecialiModel>(partecipaCataloghiSpecialiLoader);

		grid = new Grid<PartecipaCataloghiSpecialiModel>(partecipaCataloghiSpecialiGrigliaStore, cm);
		grid.setStripeRows(true);
		grid.setBorders(true);
		grid.getView().setAutoFill(true);
		toolBar = new ToolBar();

		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Cataloghi speciali "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 500);
				window.setModal(true);
				window.setHeading("Nuovo Catalogo Speciale");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				window.add(creaNuovoCatalogoSpecialeFormPanel);

				creaNuovoCatalogoSpecialeFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

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
		remove.setIcon(Resources.ICONS.delete());
		
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				modifica.disable();
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							int idRemove= grid.getSelectionModel().getSelectedItem().getIdPartecipaCatalogo();
							bibliotecheService.removePartecipaCatalogoSpeciale(idRemove,id_biblioteca,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									partecipaCataloghiSpecialiLoader.load();
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
										partecipaCataloghiSpecialiLoader.load();
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
		
		modifica = new Button("Modifica");
		modifica.setIcon(Resources.ICONS.edit());
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 500);
				window.setModal(true);
				window.setHeading("Modifica Catalogo Speciale");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				if(grid.getSelectionModel().getSelectedItem()!=null){
					modificaCatalogoSpecialeFormPanel.setValues(grid.getSelectionModel().getSelectedItem());
				}
				window.add(modificaCatalogoSpecialeFormPanel);

				modificaCatalogoSpecialeFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}
				});
				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}
		});
		modifica.disable();
		toolBar.add(modifica);
		toolBar.add(remove);
		
		grid.addListener(Events.RowClick,new Listener<GridEvent<PartecipaCataloghiSpecialiModel>>() {

			public void handleEvent(GridEvent<PartecipaCataloghiSpecialiModel> be) {
				modifica.enable();
				remove.enable();
			}
		});

		add(grid);
		setTopComponent(toolBar);
	}

	public List<ColumnConfig> getSpecializedColumnConfig() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig denominazioneColumn = new ColumnConfig();
		denominazioneColumn.setId("denominazione");
		denominazioneColumn.setHeader("Denominazione");
		denominazioneColumn.setWidth(120);
		configs.add(denominazioneColumn);
		
		ColumnConfig denominazioneMaterialeColumn = new ColumnConfig();
		denominazioneMaterialeColumn.setId("denominazioneMateriale");
		denominazioneMaterialeColumn.setHeader("Tipo materiale");
		denominazioneMaterialeColumn.setWidth(90);

		configs.add(denominazioneMaterialeColumn);

		return configs;
	}

	/**
	 * Ritorna l'oggeto di tipo loader per il caricamento dei dati nello
	 * store della Grid
	 * */
	public BaseListLoader<ListLoadResult<PartecipaCataloghiSpecialiModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, modifica);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.partecipaCataloghiSpecialiLoader;
	}
	

	public void setIdModificaCatalogoPanel() {
		modificaCatalogoSpecialeFormPanel.setIdBiblio(id_biblioteca);
		modificaCatalogoSpecialeFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiSpecialiLoader.load();
			}

		});		
	}

	public void setIdNuovoCatalogoPanel(){
		creaNuovoCatalogoSpecialeFormPanel.setIdBiblio(id_biblioteca);
		creaNuovoCatalogoSpecialeFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiSpecialiLoader.load();
			}

		});
	}

}
