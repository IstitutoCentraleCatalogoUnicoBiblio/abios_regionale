package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.SpecializzazioneModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe widget per la visualizzazione / modifica delle informazioni relative
 * ai dewey
 *
 */
public class ContenitoreSpecializzazionePanel extends FormPanel {
	private int idBiblio;
	private SpecializzazioneModel model;
	boolean modifica;

	private BibliotecheServiceAsync bibliotecheService;
	private TabelleDinamicheServiceAsync tdsa;

	private	ComboBox<SpecializzazioneModel> dewey;
	private	TextField<String> descrUfficiale;
	private	TextField<String>  descrLibera;
	private TableData labelTableData;
	
	private	Button aggiornaButton;
	private	Button aggiungiButton;
	

	public ContenitoreSpecializzazionePanel(boolean modifica){
		super();

		this.modifica=modifica;

		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE); 
		setStyleAttribute("marginTop", "5px");
		setStyleAttribute("marginBottom", "5px");
		setHeight(200);
		setWidth(650);
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setLayout(new FitLayout());

		createSpecializzazione();
	}

	public void createSpecializzazione(){
		labelTableData = new TableData();
		labelTableData.setWidth("10%");
		labelTableData.setMargin(5);
		labelTableData.setPadding(10);

		LayoutContainer tableContainer= new LayoutContainer(new TableLayout(2));

		Text deweyLabel = new Text("Dewey:");
		deweyLabel.setStyleAttribute("fontSize", "12px");

		RpcProxy<PagingLoadResult<SpecializzazioneModel>> puntiDewey = new RpcProxy<PagingLoadResult<SpecializzazioneModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<SpecializzazioneModel>> callback) {

				tdsa.getDeweyFiltratePerPaginazioneCombobox((ModelData) loadConfig, callback);
			}
		};
		ModelReader puntiDeweyReader = new ModelReader();

		final PagingLoader<PagingLoadResult<SpecializzazioneModel>> puntiDeweyLoader = new BasePagingLoader<PagingLoadResult<SpecializzazioneModel>>(
				puntiDewey, puntiDeweyReader);
		puntiDeweyLoader.setLimit(10);

		final ListStore<SpecializzazioneModel> puntiDeweyStore = new ListStore<SpecializzazioneModel>(puntiDeweyLoader);

		dewey = new ComboBox<SpecializzazioneModel>();
		dewey.setWidth(500);
		dewey.setDisplayField("dewey");
		dewey.setFieldLabel("dewey");
		dewey.setFireChangeEventOnSetValue(true);
		dewey.setEmptyText("Codice Dewey...");
		dewey.setForceSelection(true);
		dewey.setLazyRender(false);
		dewey.setTriggerAction(TriggerAction.ALL);
		dewey.setAllowBlank(false);
		dewey.setEditable(true);
		dewey.setTypeAhead(false);
		dewey.setMinChars(1);
		dewey.setPageSize(10);
		dewey.setStore(puntiDeweyStore);
		dewey.setSimpleTemplate("{dewey} - {descrizioneDewey}");
		if(modifica){
			dewey.disable();
		}else{
			dewey.enable();
		}
		dewey.addSelectionChangedListener(new SelectionChangedListener<SpecializzazioneModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<SpecializzazioneModel> se) {
				if (se.getSelectedItem()!=null){
					descrUfficiale.setValue(se.getSelectedItem().getDecrizione());
				}
			}
		});

		Text descrizioneUfficialeLable = new Text("Descrizione ufficiale:");
		descrizioneUfficialeLable.setStyleAttribute("fontSize", "12px");

		descrUfficiale = new TextField<String>();
		descrUfficiale.setEnabled(false);
		descrUfficiale.setWidth(500);
		descrUfficiale.setReadOnly(true);
		descrUfficiale.setEmptyText("Descrizione ufficiale...");

		Text descrizioneLiberaLabel = new Text("Descrizione libera:");
		descrizioneLiberaLabel.setStyleAttribute("fontSize", "12px");

		descrLibera = new TextField<String>();
		descrLibera.setWidth(500);
		descrLibera.setEmptyText("Descrizione libera...");

		tableContainer.add(deweyLabel,labelTableData);
		tableContainer.add(dewey,labelTableData);

		tableContainer.add(descrizioneUfficialeLable,labelTableData);
		tableContainer.add(descrUfficiale,labelTableData);

		tableContainer.add(descrizioneLiberaLabel,labelTableData);
		tableContainer.add(descrLibera,labelTableData);

		FormButtonBinding buttonBind = new FormButtonBinding(this);
		
		if(modifica==false){
			initAggiungiButton();
			buttonBind.addButton(aggiungiButton);
			
			LayoutContainer tmp = new LayoutContainer();
			tmp.setLayout(new CenterLayout());
			tmp.setWidth(640);
			tmp.setHeight(40);
		
			HorizontalPanel addButtons = new HorizontalPanel();
			addButtons.add(aggiungiButton);

			tmp.add(addButtons);
			setBottomComponent(tmp);

		}else{
			initAggiornaButton();
			buttonBind.addButton(aggiornaButton);
			tableContainer.add(aggiornaButton,labelTableData);
		}
		add(tableContainer);
	}

	/**
	 * 
	 */
	private void initAggiungiButton() {
		aggiungiButton = new Button("Aggiungi");
		aggiungiButton.setIcon(Resources.ICONS.add());
		aggiungiButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							SpecializzazioneModel modelToSave=new SpecializzazioneModel();

							modelToSave.setDewey(dewey.getValue().getDewey());
							modelToSave.setDecrizione(descrUfficiale.getValue());
							modelToSave.setDecrLibera(descrLibera.getValue());
							modelToSave.setIdBiblioteca(idBiblio);

							bibliotecheService.addSpecializzazionePatrimonio(modelToSave, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReleoadPanel();
									fireCloseWindow();
									reset();
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
										}										modifica = false;
										reset();
									}
								}
							});
						} 
						else{
							fireCloseWindow();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
	}

	/**
	 * 
	 */
	private void initAggiornaButton() {
		aggiornaButton = new Button("Aggiorna");
		aggiornaButton.setIcon(Resources.ICONS.arrowsRefresh());
		
		aggiornaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							SpecializzazioneModel modelToSave=new SpecializzazioneModel();

							modelToSave.setDewey(model.getDewey());
							modelToSave.setDecrizione(descrUfficiale.getValue());
							modelToSave.setDecrLibera(descrLibera.getValue());
							modelToSave.setIdBiblioteca(idBiblio);

							bibliotecheService.addSpecializzazionePatrimonio(modelToSave, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireReleoadPanel();
									fireCloseWindow();
									reset();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										if (caught instanceof DuplicatedEntryClientSideException){
											AbiMessageBox.messageErrorAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}else{
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
										}										
										reset();
									}
								}
							});



						} 
						else{
							fireCloseWindow();
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);

			}
		});
	}

	public void setValues(SpecializzazioneModel model){
		this.model=model;

		dewey.setValue(model);
		descrUfficiale.setValue(model.getDecrizione());
		if(model.getDecrLibera()!=null)
			descrLibera.setValue(model.getDecrLibera());
		else descrLibera.setValue(null);
		
		if(UIWorkflow.isReadOnly()==false){
			descrLibera.enable();
			
			aggiornaButton.show();
		}else{
			descrLibera.disable();
			aggiornaButton.hide();
		}
	}

	public void setIdBiblio(int idBiblio){
		this.idBiblio=idBiblio;
	}

	public void fireReleoadbiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,this.idBiblio);
	}

	public void fireReleoadPanel() {
		fireEvent(Events.Refresh);		
	}

	public void fireCloseWindow() {
		fireEvent(Events.Close);		
	}
	
	public SpecializzazioneModel getModel(){
		return this.model;
	}
}
