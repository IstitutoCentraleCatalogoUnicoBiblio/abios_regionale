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

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGeneraliModel;
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
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe, estensione di <code>ListaCataloghiGenericaPanel</code>, 
 * per la visualizzazione / modifica della lista dei cataloghi generali
 *
 */
public class ListaCataloghiGeneraliPanel extends ListaCataloghiGenericaPanel {

	private ToolBar toolBar;
	private Button add;
	private Button remove;
	private Button modifica;
	private Grid<PartecipaCataloghiGeneraliModel> grid;
	private CreazioneModificaCataloghiGeneraliPanel creaNuovoCatalogoGeneraleFormPanel;
	private CreazioneModificaCataloghiGeneraliPanel modificaCatalogoGeneraleFormPanel;

	private ListStore<PartecipaCataloghiGeneraliModel> partecipaCataloghiGeneraliGrigliaStore;
	private BaseListLoader<ListLoadResult<PartecipaCataloghiGeneraliModel>> partecipaCataloghiGeneraliLoader;
	public ListaCataloghiGeneraliPanel() {
		super();
		//Setto il tipo di catalogo
		this.tipoCatalogo=1;

		creaNuovoCatalogoGeneraleFormPanel = new CreazioneModificaCataloghiGeneraliPanel(false);
		modificaCatalogoGeneraleFormPanel = new CreazioneModificaCataloghiGeneraliPanel(true);
	}

	public void setGrid(){
		//Servizio per il caricamento dei dati nello store della griglia
		RpcProxy<List<PartecipaCataloghiGeneraliModel>> partecipaCataloghiSpecialiProxy = new RpcProxy<List<PartecipaCataloghiGeneraliModel>>() {

			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<PartecipaCataloghiGeneraliModel>> callback) {

				bibliotecheService.getPartecipaCataloghiGeneraliByIdBiblio(id_biblioteca, callback);
			}

		};

		ModelReader partecipaCataloghiSpecialiReader = new ModelReader();

		partecipaCataloghiGeneraliLoader  = new BaseListLoader<ListLoadResult<PartecipaCataloghiGeneraliModel>>(
				partecipaCataloghiSpecialiProxy, partecipaCataloghiSpecialiReader);

		partecipaCataloghiGeneraliGrigliaStore = new ListStore<PartecipaCataloghiGeneraliModel>(partecipaCataloghiGeneraliLoader);


		//Creo le configurazioni per le colonne relative ai cataloghi speciali
		List<ColumnConfig> configs = getSpecializedColumnConfig();
		//Vi aggiungo le configurazioni comuni a tutti i cataloghi
		configs.addAll(getBaseConfigs());
		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<PartecipaCataloghiGeneraliModel>(partecipaCataloghiGeneraliGrigliaStore, cm);
		grid.setBorders(true);
		grid.setStripeRows(true);
		grid.getView().setAutoFill(true);
		toolBar = new ToolBar();
		/*setta il font-weight del testo a bold*/
		toolBar.addStyleName("font-weight-style");
		toolBar.setWidth(300);
		toolBar.setBorders(false);

		toolBar.add(new Text("Cataloghi generali "));
		add = new Button("Aggiungi");
		add.setIcon(Resources.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				modifica.disable();
				remove.disable();

				final Window window= new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 450);
				window.setModal(true);
				window.setHeading("Nuovo Catalogo Generale");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				window.add(creaNuovoCatalogoGeneraleFormPanel);
				creaNuovoCatalogoGeneraleFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

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

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							modifica.disable();
							int idRemove= grid.getSelectionModel().getSelectedItem().getIdPartecipaCatalogo();
							bibliotecheService.removePartecipaCatalogoGenerale(idRemove,id_biblioteca,	new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									partecipaCataloghiGeneraliLoader.load();
									remove.disable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
										partecipaCataloghiGeneraliLoader.load();
										remove.disable();
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
			}

		});
		remove.disable();
		grid.addListener(Events.RowClick,
				new Listener<GridEvent<PartecipaCataloghiGeneraliModel>>() {

			public void handleEvent(GridEvent<PartecipaCataloghiGeneraliModel> be) {
				modifica.enable();
				remove.enable();
			}
		});

		remove.setIcon(Resources.ICONS.delete());

		modifica = new Button("Modifica");
		modifica.setIcon(Resources.ICONS.edit());
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				remove.disable();
				final Window window= new Window();
				window.setScrollMode(Scroll.AUTOY);
				window.setSize(650, 450);
				window.setModal(true);
				window.setHeading("Nuovo Catalogo Generale");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/

				if(grid.getSelectionModel().getSelectedItem()!=null){
					modificaCatalogoGeneraleFormPanel.setValues(grid.getSelectionModel().getSelectedItem());
				}
				modificaCatalogoGeneraleFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}
				});
				window.add(modificaCatalogoGeneraleFormPanel);

				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}

		});
		modifica.disable();
		toolBar.add(modifica);
		toolBar.add(remove);

		add(grid);
		setTopComponent(toolBar);
	}

	public List<ColumnConfig> getSpecializedColumnConfig() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig tipoCatalogoColumn = new ColumnConfig();
		tipoCatalogoColumn.setId("tipoCatalogoDescr");
		tipoCatalogoColumn.setHeader("Tipo catalogo");
		tipoCatalogoColumn.setWidth(100);

		configs.add(tipoCatalogoColumn);

		return configs;
	}

	/**
	 * Ritorna l'oggeto di tipo loader per il caricamento dei dati nello
	 * store della Grid
	 * */
	public BaseListLoader<ListLoadResult<PartecipaCataloghiGeneraliModel>> getLoader() {

		UIWorkflow.addOrRemoveFromToolbar(toolBar, add);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, modifica);
		UIWorkflow.addOrRemoveFromToolbar(toolBar, remove);
		UIWorkflow.gridEnableEvent(grid);

		return this.partecipaCataloghiGeneraliLoader;
	}

	public void setIdModificaCatalogoPanel() {
		modificaCatalogoGeneraleFormPanel.setIdBiblio(id_biblioteca);
		modificaCatalogoGeneraleFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiGeneraliLoader.load();
			}

		});		
	}

	public void setIdNuovoCatalogoPanel(){
		creaNuovoCatalogoGeneraleFormPanel.setIdBiblio(id_biblioteca);
		creaNuovoCatalogoGeneraleFormPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				partecipaCataloghiGeneraliLoader.load();
			}

		});
	}
}
