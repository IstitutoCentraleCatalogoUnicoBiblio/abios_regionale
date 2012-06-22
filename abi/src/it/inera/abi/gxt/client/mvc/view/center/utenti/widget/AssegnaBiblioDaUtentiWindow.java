package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AssegnaBiblioDaUtentiWindow extends Window {
	private FormPanel formDaUtente;
	private Window _instance;
	private int id_utente = 0;
	private Grid<BiblioModel> grid;
	protected UtentiServiceAsync utentiService = null;

	public AssegnaBiblioDaUtentiWindow() {

		utentiService = Registry.get(Abi.UTENTI_SERVICE);

		setSize(400, 150);  
		setModal(true);  
		setHeading("Copia biblioteche assegnate ad un altro utente"); 
		setLayout(new FitLayout());  
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		createForm();  
		add(formDaUtente, fitData);

		addListener(Events.Hide, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formDaUtente.reset();
			}
		});

		_instance = this;
	}

	public void createForm() {
		FormData formData = new FormData("-20");
		formDaUtente = new FormPanel();

		formDaUtente.setHeading("Form assegna da codice"); 
		formDaUtente.setWidth(400);  
		formDaUtente.setBorders(false);
		formDaUtente.setHeaderVisible(false);

		Text scegliUtenteLabel = new Text("Scegliere l'utente:");
		scegliUtenteLabel.setStyleAttribute("fontSize", "12px");

		RpcProxy<PagingLoadResult<UserModel>> utentiProxy = new RpcProxy<PagingLoadResult<UserModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<UserModel>> callback) {
				utentiService.getListaUtentiPaginata((ModelData) loadConfig, id_utente, callback);

			}

		};

		ModelReader utentiReader = new ModelReader();

		final PagingLoader<PagingLoadResult<UserModel>> utentiLoader = new BasePagingLoader<PagingLoadResult<UserModel>>(
				utentiProxy, utentiReader);
		utentiLoader.setLimit(10);

		final ListStore<UserModel> utentiStore = new ListStore<UserModel>(utentiLoader);

		final ComboBox<UserModel> scegliUtenti = new ComboBox<UserModel>();
		scegliUtenti.setWidth(400);
		scegliUtenti.setHideLabel(true);
		scegliUtenti.setDisplayField("login");
		scegliUtenti.setFireChangeEventOnSetValue(true);
		scegliUtenti.setEmptyText("Login Utente");
		scegliUtenti.setForceSelection(true);
		scegliUtenti.setLazyRender(false);
		scegliUtenti.setTriggerAction(TriggerAction.ALL);
		scegliUtenti.setAllowBlank(true);
		scegliUtenti.setEditable(true);
		scegliUtenti.setSimpleTemplate("{login}");
		scegliUtenti.setTypeAhead(false);
		scegliUtenti.setMinChars(1);
		scegliUtenti.setPageSize(10);
		scegliUtenti.setMinListWidth(400);
		scegliUtenti.setStore(utentiStore);


		formDaUtente.add(scegliUtenteLabel, formData);
		formDaUtente.add(scegliUtenti, formData);

		Button cancel = new Button("Annulla");
		cancel.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				_instance.hide();
			}
		});
		formDaUtente.addButton(cancel); 

		Button assegna = new Button("Assegna");  
		assegna.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {

				final MessageBox box = new MessageBox();
				box.setIcon(MessageBox.QUESTION);
				box.setMessage("Assegnare le biblioteche dell'utente selezionato?");
				box.setButtons(MessageBox.YESNO);
				box.addCallback(new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							utentiService.assegnaBiblioDaUtente(scegliUtenti.getSelection().get(0).getIdUser(), id_utente, new AsyncCallback<Integer>() {

								@Override
								public void onSuccess(Integer result) {
									final MessageBox box = new MessageBox();
									if (result == -1) {
										box.setIcon(MessageBox.WARNING);
										box.setMessage("Nessuna biblioteca assegnata all'utente "+scegliUtenti.getSelection().get(0).getUserName()+".");
									}
									else {
										box.setIcon(MessageBox.INFO);
										box.setMessage("Biblioteche dell'utente "+scegliUtenti.getSelection().get(0).getUserName()+" assegnate.");
									}
									box.setButtons(MessageBox.OK);
									box.addCallback(new Listener<MessageBoxEvent>() {

										@Override
										public void handleEvent(MessageBoxEvent be) {
											final PagingLoadConfig config = new BasePagingLoadConfig();
											config.setOffset(0);
											config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
											config.set("id_utente", id_utente);
											grid.getStore().getLoader().load(config);
											_instance.hide();

										}
									});
									box.show();
								}

								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
								}
							});
						}

					}
				});

				box.show();                
			}
		});		
		formDaUtente.addButton(assegna);  


		formDaUtente.setButtonAlign(HorizontalAlignment.CENTER);  

		FormButtonBinding binding = new FormButtonBinding(formDaUtente);  
		binding.addButton(assegna);
	}

	public void setIdUtente(int id) {
		this.id_utente = id;
	}

	public void setConfig(Grid<BiblioModel> grid) {
		this.grid = grid;
	}
}
