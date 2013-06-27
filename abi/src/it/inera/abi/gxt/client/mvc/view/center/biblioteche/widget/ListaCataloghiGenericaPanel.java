package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.CataloghiUrlModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiCollettiviModel;
import it.inera.abi.gxt.client.mvc.model.PartecipaCataloghiGenericaModel;
import it.inera.abi.gxt.client.mvc.view.RowEditorCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
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
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione / modifica della lista dei cataloghi
 *
 */
public class ListaCataloghiGenericaPanel extends ContentPanel {
	protected boolean modifica;
	protected boolean modificaUrl;
	protected int id_biblioteca;
	protected BibliotecheServiceAsync bibliotecheService;
	protected TabelleDinamicheServiceAsync tabelleDinamicheService;
	protected GridCellRenderer<PartecipaCataloghiGenericaModel> bottoneUrlRenderer;
	protected Grid<PartecipaCataloghiGenericaModel> tmpGrid;
	/*tipoCatalogo: definisce il tipo di catalogo collettivo, generale, speciale
	 * Viene settato nelle rispettive sottoclassi;
	 * tipoCatalogo==1 CataloghiGenerali
	 * tipoCatalogo==2 CataloghiCollettivi
	 * tipoCatalogo==3 CataloghiSpecuali
	 * 	 * */
	protected int tipoCatalogo;

	public ListaCataloghiGenericaPanel() {

		this.modifica=false;
		this.bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		this.tabelleDinamicheService=Registry.get(Abi.TABELLE_DINAMICHE_SERVICE);
		setBodyStyle("padding-bottom:10px");
		setBodyBorder(false);
		setBorders(false);
		setHeaderVisible(false);
		setWidth(790);
		setHeight(600);
		setScrollMode(Scroll.AUTOY);
		setLayout(new FitLayout());

	}


	public List<ColumnConfig> getBaseConfigs() {

		List<ColumnConfig> baseConfigs = new ArrayList<ColumnConfig>();


		ColumnConfig schedeColumn = new ColumnConfig();

		schedeColumn.setId("schede");

		schedeColumn.setHeader("Schede");
		schedeColumn.setWidth(50);

		baseConfigs.add(schedeColumn);

		ColumnConfig percentSchedeColumn = new ColumnConfig();
		percentSchedeColumn.setId("percentSchede");
		percentSchedeColumn.setHeader("%");
		percentSchedeColumn.setWidth(30);


		baseConfigs.add(percentSchedeColumn);

		ColumnConfig volumeColumn = new ColumnConfig();
		volumeColumn.setId("volume");
		volumeColumn.setHeader("Volume");
		volumeColumn.setWidth(50);

		baseConfigs.add(volumeColumn);

		ColumnConfig percentVolumeColumn = new ColumnConfig();
		percentVolumeColumn.setId("percentVolume");
		percentVolumeColumn.setHeader("%");
		percentVolumeColumn.setWidth(30);

		baseConfigs.add(percentVolumeColumn);

		ColumnConfig citazioneBiblioColumn = new ColumnConfig();
		citazioneBiblioColumn.setId("citazioneBiblio");
		citazioneBiblioColumn.setHeader("Cit. bib.");
		citazioneBiblioColumn.setWidth(80);

		baseConfigs.add(citazioneBiblioColumn);

		ColumnConfig microformeColumn = new ColumnConfig();

		microformeColumn.setId("microforme");

		microformeColumn.setHeader("Micr.");
		microformeColumn.setWidth(50);

		baseConfigs.add(microformeColumn);

		ColumnConfig percentMicroformeColumn = new ColumnConfig();
		percentMicroformeColumn.setId("percentMicroforme");
		percentMicroformeColumn.setHeader("%");
		percentMicroformeColumn.setWidth(30);

		baseConfigs.add(percentMicroformeColumn);

		ColumnConfig informatizzatoColumn = new ColumnConfig();

		informatizzatoColumn.setId("informatizzato");
		informatizzatoColumn.setHeader("Inf.");
		informatizzatoColumn.setWidth(50);

		baseConfigs.add(informatizzatoColumn);

		ColumnConfig urlColumn = new ColumnConfig();
		urlColumn.setId("url");
		urlColumn.setHeader("Url");
		urlColumn.setWidth(50);
		urlColumn.setRenderer(getRendererurlButton());

		baseConfigs.add(urlColumn);

		ColumnConfig percentInformatizzatoColumn = new ColumnConfig();
		percentInformatizzatoColumn.setId("percentInformatizzato");
		percentInformatizzatoColumn.setHeader("%");
		percentInformatizzatoColumn.setWidth(30);

		baseConfigs.add(percentInformatizzatoColumn);

		ColumnConfig annoDaColumn = new ColumnConfig();
		annoDaColumn.setId("annoDa");
		annoDaColumn.setHeader("Anno da");
		annoDaColumn.setWidth(60);

		baseConfigs.add(annoDaColumn);

		ColumnConfig annoAColumn = new ColumnConfig();
		annoAColumn.setId("annoA");
		annoAColumn.setHeader("Anno a");
		annoAColumn.setWidth(50);

		baseConfigs.add(annoAColumn);

		return baseConfigs;
	}

	/**
	 * Setta va variabile id_biblioteca
	 * */
	public void setIdBiblioteca(int idBiblioteca) {
		this.id_biblioteca = idBiblioteca;
	}

	public GridCellRenderer<PartecipaCataloghiGenericaModel> getRendererurlButton(){
		/**Render per bottone Visualizza url*/
		bottoneUrlRenderer = new GridCellRenderer<PartecipaCataloghiGenericaModel>() {

			private boolean init;
			public Object render(final PartecipaCataloghiGenericaModel model,
					String property, ColumnData config, final int rowIndex,
					final int colIndex, ListStore<PartecipaCataloghiGenericaModel> store,
					final Grid<PartecipaCataloghiGenericaModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.ColumnResize,
							new Listener<GridEvent<PartecipaCataloghiGenericaModel>>() {

						public void handleEvent(GridEvent<PartecipaCataloghiGenericaModel> be) {
							for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
								if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
										&& be.getGrid().getView().getWidget(i,be.getColIndex()) instanceof BoxComponent) {
									((BoxComponent) be.getGrid().getView().getWidget(i,be.getColIndex())).setWidth(be.getWidth() - 10);
								}
							}
						}
					});
				}

				final Button aggiungiModificaButton = new Button((String) model.get(property),
						new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {		

						final Window window = new Window();
						window.setSize(600, 300);
						window.setModal(true);
						window.setHeading("Lista url");
						window.setLayout(new FitLayout());
						window.setClosable(false);



						//Panel PER CONTENUTO griglia URL
						ContentPanel listaUrlsPanel = new ContentPanel(new TableLayout(4));
						listaUrlsPanel.setBodyStyle("padding-bottom:10px");
						listaUrlsPanel.setBodyBorder(false);
						listaUrlsPanel.setBorders(false);
						listaUrlsPanel.setHeaderVisible(false);
						listaUrlsPanel.setWidth(590);
						listaUrlsPanel.setHeight(200);
						listaUrlsPanel.setScrollMode(Scroll.AUTOY);
						listaUrlsPanel.setLayout(new FitLayout());

						List<ColumnConfig> configs =new ArrayList<ColumnConfig>();

						final	TextField<String> url = new  TextField<String>();
						url.setEmptyText("Inserisci url...");
						url.setAllowBlank(false);
						
						ColumnConfig urlColumnConfig = new ColumnConfig();
						urlColumnConfig.setId("url");
						urlColumnConfig.setHeader("Url");
						urlColumnConfig.setWidth(300);
						urlColumnConfig.setEditor(new CellEditor(url));
						configs.add(urlColumnConfig);


						final	TextField<String> descrizioneUrl = new TextField<String>();
						descrizioneUrl.setEmptyText("Inserisci descrizione url...");

						ColumnConfig descrizioneUrlColumnConfig = new ColumnConfig();
						descrizioneUrlColumnConfig.setId("descrizioneUrl");
						descrizioneUrlColumnConfig.setHeader("Descrizione url");
						descrizioneUrlColumnConfig.setWidth(250);
						descrizioneUrlColumnConfig.setEditor(new CellEditor(descrizioneUrl));
						configs.add(descrizioneUrlColumnConfig);

						RpcProxy<List<CataloghiUrlModel>> cataloghiUrlProxy = new RpcProxy<List<CataloghiUrlModel>>() {

							@Override
							protected void load(
									Object loadConfig,
									AsyncCallback<List<CataloghiUrlModel>> callback) {
								//tipoCatalogo== tipologia di catalogo in modifica
								/*idPartecipaCatalogo== id dell'associazione Biblioteca --->Cataloghi in modifica
								 * da cui caricare la lista url*/
								int idPartecipaCatalogo=grid.getStore().getAt(rowIndex).getIdPartecipaCatalogo();
								tabelleDinamicheService.getCataloghiUrls(tipoCatalogo,idPartecipaCatalogo, callback);

							}
						};

						ModelReader urlReader = new ModelReader();

						final			BaseListLoader<ListLoadResult<CataloghiUrlModel>> urlLoader=new 	BaseListLoader<ListLoadResult<CataloghiUrlModel>>(
								cataloghiUrlProxy, urlReader);

						final		ListStore<CataloghiUrlModel>  cataloghiUrlStore=new ListStore<CataloghiUrlModel>(urlLoader);
						urlLoader.load();

						cataloghiUrlStore.add(grid.getStore().getAt(rowIndex).getUrls());

						final	ColumnModel cm = new ColumnModel(configs);

						final RowEditorCustom<CataloghiUrlModel> reUrls = new RowEditorCustom<CataloghiUrlModel>();
						reUrls.setClicksToEdit(ClicksToEdit.TWO);
						reUrls.setErrorSummary(false);
						
						RowEditor<CataloghiUrlModel>.RowEditorMessages rowEditorMessages = reUrls.getMessages();
		                rowEditorMessages.setCancelText("Annulla");
		                rowEditorMessages.setSaveText("Salva");
		                reUrls.setMessages(rowEditorMessages);
						
						final Grid<CataloghiUrlModel> gridUrls = new Grid<CataloghiUrlModel>(cataloghiUrlStore,cm);
						gridUrls.addPlugin(reUrls);
						gridUrls.setStripeRows(true);
						gridUrls.getView().setAutoFill(true);
						/*TOOLBAR*/
						ToolBar windowToolBar = new ToolBar();
						/*setta il font-weight del testo a bold*/
						windowToolBar.addStyleName("font-weight-style");
						
						windowToolBar.setWidth(300);
						windowToolBar.setBorders(false);

						windowToolBar.add(new Text("Materiale url "));
						Button add = new Button("Aggiungi");
						add.setIcon(Resources.ICONS.add());
						add.addSelectionListener(new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								CataloghiUrlModel newCatalogo = new CataloghiUrlModel();

								reUrls.stopEditing(false);
								cataloghiUrlStore.insert(newCatalogo, 0);
								reUrls.startEditing(cataloghiUrlStore.indexOf(newCatalogo), false);

							}


						});
						windowToolBar.add(add);

						final Button remove = new Button("Rimuovi");
						remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {

								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(final MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {


											int	id_removeUrl=gridUrls.getSelectionModel().getSelectedItem().getIdCataloghiUrl();

											tabelleDinamicheService.removeCataloghiMaterialeUrl(id_removeUrl,tipoCatalogo,new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													modificaUrl = false;
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
													urlLoader.load();
													{
														gridUrls.getStore().remove(gridUrls.getStore().indexOf(gridUrls.getSelectionModel().getSelectedItem()));
														ce.<Component> getComponent().disable();
													}
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
														ce.<Component> getComponent().disable();
														urlLoader.load();
														modificaUrl = false;
													}
												}

											});

										} else {
											//											
										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);

								if (gridUrls.getStore().getCount() == 0) {
									ce.<Component> getComponent().disable();
								}
								remove.disable();
							}

						});
						remove.disable();

						UIWorkflow.gridEnableEvent(gridUrls);
						
						if(UIWorkflow.isReadOnly()==false){
							add.show();
							remove.show();
						}else{
							add.hide();
							remove.hide();
						}
						reUrls.addListener(Events.CancelEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								if(modificaUrl==false){
									cataloghiUrlStore.remove(0);	
								}
								remove.disable();

							}
						});

						reUrls.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {

								final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

									@Override
									public void handleEvent(MessageBoxEvent ce) {

										Button btn = ce.getButtonClicked();
										if (btn.getText().equalsIgnoreCase("Si")) {
											CataloghiUrlModel newUrl=new CataloghiUrlModel();

											if (modificaUrl == false) {
												newUrl.setIdPartecipa(model.getIdPartecipaCatalogo());
												newUrl.setUrl(url.getValue());
												if(descrizioneUrl.getValue()!=null){
													newUrl.setDescrUrl(descrizioneUrl.getValue());
												}

											}

											else {
												newUrl.setIdCataloghiUrl(gridUrls.getSelectionModel().getSelectedItem().getIdCataloghiUrl());
												newUrl.setIdPartecipa(gridUrls.getSelectionModel().getSelectedItem().getIdPartecipa());
												newUrl.setUrl(url.getValue());
												if(descrizioneUrl.getValue()!=null){
													newUrl.setDescrUrl(descrizioneUrl.getValue());
												}

											}
											tabelleDinamicheService.addCataloghiMaterialeUrl(newUrl,modificaUrl,tipoCatalogo,new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
													modificaUrl = false;
													AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
													urlLoader.load();
												}

												@Override
												public void onFailure(Throwable caught) {
													if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
														AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
														if (modificaUrl == false) {
															cataloghiUrlStore.remove(0);
														}

														modificaUrl = false;
													}

												}

											});

										} else {
											if (modificaUrl == false) {
												cataloghiUrlStore.remove(0);

											} else {
												modificaUrl = false;
											}
											urlLoader.load();										}
									}
								};
								AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
							}
						});
						gridUrls.addListener(Events.RowClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {

								remove.enable();
							}
						});
						gridUrls.addListener(Events.RowDoubleClick,
								new Listener<GridEvent<PartecipaCataloghiCollettiviModel>>() {

							public void handleEvent(
									GridEvent<PartecipaCataloghiCollettiviModel> be) {
								modificaUrl=true;
								remove.disable();
							}
						});

						remove.setIcon(Resources.ICONS.delete());
						windowToolBar.add(remove);

						listaUrlsPanel.add(gridUrls);
						listaUrlsPanel.setTopComponent(windowToolBar);
						/*FINE TOOLBAR*/


						final Button save = new Button("Ok");
						save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

							@Override
							public void handleEvent(BaseEvent be) {
								window.close();

							}

						});


						window.add(listaUrlsPanel);
						//	window.add(getUrlsPanel(grid, rowIndex, model,window));
						window.addButton(save);
						//window.addButton(cancel);
						window.show();


					}
				});

				aggiungiModificaButton.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
				aggiungiModificaButton.setToolTip("Clicca per aggiungere/modificare <br> la lista url;");
				/*
				 * Se l'oggetto CataloghiSupportoDigitaleTipo è diverso da 4
				 * (online) il bottone Visualizza/Modifica viene disabilitato
				 */

				if(grid.getStore().getAt(rowIndex).getIdInformatizzatoTipo()!=null && grid.getStore().getAt(rowIndex).getIdInformatizzatoTipo()!=4){
					aggiungiModificaButton.disable();
				}
				else{
					aggiungiModificaButton.enable();
				}

				return aggiungiModificaButton;
			}
		};
		return bottoneUrlRenderer;
	}
}
