package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPuntiDecentratiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaSistemiDiBibliotechePanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai sistemi di biblioteche
 *
 */
public class SistemiDiBibliotechePanel extends ContentPanelForTabItem {
	private TableData d;

	private Text denominazioneUfficialeLabel;
	private Text isilProvinciaLabel;
	private Text isilnumeroLabel;

	private ComboBox<BiblioModel> denominazioneUfficialeField;
	private ComboBox<VoceUnicaModel> isilProvinciaField;
	private TextFieldCustom<String> isilNumeroField;

	private Button inserisciPadre;
	private Button cancellaPadre;

	private ListaPuntiDecentratiPanel altreBibliotechePanel;
	private ListaSistemiDiBibliotechePanel sistemiDiBibliotechePanel;
	private int id_biblio;

	public SistemiDiBibliotechePanel() {
		super();
		
		d = new TableData();
		d.setWidth("25%");
		d.setMargin(5);
		d.setPadding(10);
		
		createForms();
	}

	public void createForms() {
		final BibliotecheServiceAsync bibliotecheServiceAsync = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		/* FORM SISTEMI BIBLIOTECHE */
		LayoutContainer sistemiBiblio = new LayoutContainer();
		sistemiBiblio.setStyleAttribute("padding", "5px");

		FieldSet collegamentiPadreSet = new FieldSet();
		Utils.setFieldSetProperties(collegamentiPadreSet, "E' un punto di servizio decentrato per");	

		/* E' un punto decentrato -> INIZIO */
		final LayoutContainer bibliotecaPadreTable = new LayoutContainer(new TableLayout(3));
		bibliotecaPadreTable.setWidth(750);

		/*
		 * denominazione ufficiale
		 */
		denominazioneUfficialeLabel = new Text("Denominazione:");
		denominazioneUfficialeLabel.setStyleAttribute("fontSize", "12px");
		bibliotecaPadreTable.add(denominazioneUfficialeLabel, d);


		denominazioneUfficialeField = new ComboBox<BiblioModel>();
		denominazioneUfficialeField.setDisplayField("denominazione");
		denominazioneUfficialeField.setFieldLabel("denominazione");
		denominazioneUfficialeField.setEmptyText("Seleziona denominazione...");
		denominazioneUfficialeField.setFireChangeEventOnSetValue(true);
		denominazioneUfficialeField.setLazyRender(false);
		denominazioneUfficialeField.setTriggerAction(TriggerAction.ALL);
		denominazioneUfficialeField.setForceSelection(false);
		denominazioneUfficialeField.setEditable(true);
		denominazioneUfficialeField.setAllowBlank(false);
		denominazioneUfficialeField.setTypeAhead(false);
		denominazioneUfficialeField.setMinChars(1);
		denominazioneUfficialeField.setPageSize(10);
		denominazioneUfficialeField.disable();
		denominazioneUfficialeField.setWidth(400);

		bibliotecaPadreTable.add(denominazioneUfficialeField, d);
		bibliotecaPadreTable.add(new LayoutContainer(), d);
		RpcProxy<PagingLoadResult<BiblioModel>> padriPossibiliProxy = new RpcProxy<PagingLoadResult<BiblioModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<BiblioModel>> callback) {
				((ModelData) loadConfig).remove("isil_provincia");
				if (isilProvinciaField != null && isilProvinciaField.getValue() != null) {
					((ModelData) loadConfig).set("isil_provincia", isilProvinciaField.getValue().getEntry());
				}
				bibliotecheServiceAsync.getPadriPossibiliServizioDecentrato((ModelData) loadConfig, callback);
			}

		};
		ModelReader padriPossibiliReader = new ModelReader();

		final PagingLoader<PagingLoadResult<BiblioModel>> padriPossibiliLoader = new BasePagingLoader<PagingLoadResult<BiblioModel>>(
				padriPossibiliProxy, padriPossibiliReader);
		padriPossibiliLoader.setLimit(10);

		final ListStore<BiblioModel> padriPossibiliStore = new ListStore<BiblioModel>(padriPossibiliLoader);

		denominazioneUfficialeField.setStore(padriPossibiliStore);
		padriPossibiliLoader.load();

		/*
		 * isil provincia
		 */
		isilProvinciaLabel = new Text("Isil Provincia:");
		isilProvinciaLabel.setStyleAttribute("fontSize", "12px");
		bibliotecaPadreTable.add(isilProvinciaLabel, d);

		isilProvinciaField = new ComboBox<VoceUnicaModel>();
		isilProvinciaField.setFieldLabel("Isil Provincia");
		isilProvinciaField.setDisplayField("entry");
		isilProvinciaField.setUseQueryCache(false);
		isilProvinciaField.setEmptyText("Isil provincia...");
		isilProvinciaField.setLazyRender(false);
		isilProvinciaField.setTriggerAction(TriggerAction.ALL);
		isilProvinciaField.setForceSelection(true);
		isilProvinciaField.setTypeAhead(false);
		isilProvinciaField.setEditable(true);
				
		isilProvinciaField.setMinChars(1);
		isilProvinciaField.setMaxLength(2);
		

		RpcProxy<List<VoceUnicaModel>> isilProvinciaProxy = new RpcProxy<List<VoceUnicaModel>>() {

			@Override
			protected void load(Object loadConfig, AsyncCallback<List<VoceUnicaModel>> callback) {
				bibliotecheServiceAsync.getListaIsilProvincia((ModelData) loadConfig, callback);
			}

		};

		ModelReader isilProvinciaReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> isilProvinciaLoader = new BaseListLoader<ListLoadResult<ModelData>>(isilProvinciaProxy, isilProvinciaReader);

		final ListStore<VoceUnicaModel> isilProvinciaStore = new ListStore<VoceUnicaModel>(isilProvinciaLoader);

		isilProvinciaField.setStore(isilProvinciaStore);

		isilProvinciaLoader.load();		

		bibliotecaPadreTable.add(isilProvinciaField, d);
		bibliotecaPadreTable.add(new LayoutContainer(), d);

		isilProvinciaField.addSelectionChangedListener(new SelectionChangedListener<VoceUnicaModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<VoceUnicaModel> se) {
				if (se.getSelectedItem() != null) {
					denominazioneUfficialeField.enable();
					denominazioneUfficialeField.clear();
					isilNumeroField.enable();
					isilNumeroField.clear();
					padriPossibiliLoader.load();
					
				}
			}
		});

		denominazioneUfficialeField.addSelectionChangedListener(new SelectionChangedListener<BiblioModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<BiblioModel> se) {
				if (se.getSelectedItem() != null) {
					isilNumeroField.setValue(se.getSelectedItem().getIsilNumero());
					isilNumeroField.disable();
				}

			}
		});

		/*
		 * isil numero
		 */
		isilnumeroLabel = new Text("Isil Numero:");
		isilnumeroLabel.setStyleAttribute("fontSize", "12px");
		bibliotecaPadreTable.add(isilnumeroLabel, d);

		isilNumeroField = new TextFieldCustom<String>();
		isilNumeroField.setEmptyText("Isil numero...");
		isilNumeroField.setMinLength(4);
		isilNumeroField.setMaxLength(4);
		bibliotecaPadreTable.add(isilNumeroField, d);

		/*
		 * bottoni
		 */
		inserisciPadre = new Button("Inserisci");
		Utils.setStylesButton(inserisciPadre);
		inserisciPadre.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							String isilPR = new String();
							String isilNR = new String();

							if (denominazioneUfficialeField.getValue() != null) {
								/* E' stata selezionata una biblioteca dal menu di scelta */
								isilPR = ((BiblioModel) denominazioneUfficialeField.getValue()).getIsilProvincia();
								isilNR = ((BiblioModel) denominazioneUfficialeField.getValue()).getIsilNumero();

							} else {
								isilPR = isilProvinciaField.getValue().getEntry();
								isilNR = isilNumeroField.getValue();
							}

							if ((isilPR != null && isilPR.length() > 0) && (isilNR != null && isilNR.length() > 0)) { 
								bibliotecheServiceAsync.addPadreServizioDecentrato(biblioteca.getIdBiblio(), isilPR, isilNR, new AsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										denominazioneUfficialeField.setOriginalValue(denominazioneUfficialeField.getValue());
										denominazioneUfficialeField.disable();
										isilProvinciaField.setOriginalValue(isilProvinciaField.getValue());
										isilNumeroField.setOriginalValue(isilNumeroField.getValue());
										isilNumeroField.disable();
										altreBibliotechePanel.disable();
										AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}

									@Override
									public void onFailure(Throwable caught) {
										if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}
								});

							} else {
								AbiMessageBox.messageAlertBox("Inserire tutti i campi", "Avviso");
							}

						}
					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		cancellaPadre = new Button("Cancella");
		Utils.setStylesButton(cancellaPadre);
		cancellaPadre.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							bibliotecheServiceAsync.removePadreServizioDecentrato(biblioteca.getIdBiblio(), new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									denominazioneUfficialeField.clear();
									isilProvinciaField.clear();
									isilNumeroField.clear();
									altreBibliotechePanel.enable();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);					
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
											AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									
								}
							});
						}
					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);
			}
		});

		TableLayout tableLayoutButtons = new TableLayout(2);
		tableLayoutButtons.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayoutButtons);
		buttons.add(inserisciPadre);
		buttons.add(cancellaPadre);

		bibliotecaPadreTable.add(buttons, d);
		collegamentiPadreSet.add(bibliotecaPadreTable);

		sistemiBiblio.add(collegamentiPadreSet);
		/* E' un punto decentrato -> FINE */


		/* 
		 * Possiede punti decentrati -> INIZIO 
		 */
		FieldSet collegamentiPuntiSet = new FieldSet();
		Utils.setFieldSetProperties(collegamentiPuntiSet, "Possiede punti di servizio decentrati");

		altreBibliotechePanel = new ListaPuntiDecentratiPanel();
		altreBibliotechePanel.setGrid();
		altreBibliotechePanel.getLoader().addListener(Loader.Load, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (altreBibliotechePanel.getGrid() != null && altreBibliotechePanel.getGrid().getStore() != null 
					&& altreBibliotechePanel.getGrid().getStore().getCount() == 0) {
					/* Griglia vuota; riabilito la sezione della biblioteca padre */
					denominazioneUfficialeField.enable();
					isilProvinciaField.enable();
					isilNumeroField.enable();
					inserisciPadre.enable();
					cancellaPadre.enable();
					
				} else if (altreBibliotechePanel.getGrid() != null && altreBibliotechePanel.getGrid().getStore() != null 
					&& altreBibliotechePanel.getGrid().getStore().getCount() == 1) {
					denominazioneUfficialeField.disable();
					isilProvinciaField.disable();
					isilNumeroField.disable();
					inserisciPadre.disable();
					cancellaPadre.disable();
				}
			}
		});

		collegamentiPuntiSet.add(altreBibliotechePanel);

		sistemiBiblio.add(collegamentiPuntiSet);
		/* Possiede punti decentrati -> FINE */



		/* Sistemi di biblioteche -> INIZIO */
		FieldSet sistemiBiblioSet = new FieldSet();
		Utils.setFieldSetProperties(sistemiBiblioSet, "Sistemi di biblioteche");

		sistemiDiBibliotechePanel = new ListaSistemiDiBibliotechePanel();
		sistemiDiBibliotechePanel.setGrid();

		sistemiBiblioSet.add(sistemiDiBibliotechePanel);

		sistemiBiblio.add(sistemiBiblioSet);
		/* Sistemi di biblioteche -> FINE */

		add(sistemiBiblio);
		/* FINE----FORM SISTEMI BIBLIOTECHE */
		
	}
	
	public void setValueFields() {
		if (biblioteca.getBibliotecaPadre() == null && !(biblioteca.getBibliotecaHasFigli().booleanValue())) {
			/* Non ha padre e non ha figli */
			denominazioneUfficialeField.clear();
			isilProvinciaField.clear();
			isilProvinciaField.enable();
			isilNumeroField.clear();
			isilNumeroField.enable();
			inserisciPadre.enable();
			cancellaPadre.enable();
			altreBibliotechePanel.enable();
			
		} else if (biblioteca.getBibliotecaPadre() != null) {
			/* Ha un padre, non ha figli */
			isilProvinciaField.setValue(new VoceUnicaModel(biblioteca.getBibliotecaPadre().getIsilProvincia()));
			isilNumeroField.setValue(biblioteca.getBibliotecaPadre().getIsilNumero());
			denominazioneUfficialeField.setValue(biblioteca.getBibliotecaPadre());
			denominazioneUfficialeField.setOriginalValue(biblioteca.getBibliotecaPadre());
			denominazioneUfficialeField.disable();
			/* Disabilito la griglia dei punti decentrati */
			altreBibliotechePanel.disable();
			
		} else {
			/* Non ha un padre, ha figli */
			denominazioneUfficialeField.clear();
			denominazioneUfficialeField.disable();
			isilProvinciaField.clear();
			isilProvinciaField.disable();
			isilNumeroField.clear();
			isilNumeroField.disable();
			inserisciPadre.disable();
			cancellaPadre.disable();
			altreBibliotechePanel.enable();
		
		}
		
		UIWorkflow.setReadOnly(denominazioneUfficialeField);
		UIWorkflow.setReadOnly(isilProvinciaField);
		UIWorkflow.setReadOnly(isilNumeroField);
		
		UIWorkflow.hideView(inserisciPadre);
		UIWorkflow.hideView(cancellaPadre);
	
		altreBibliotechePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		altreBibliotechePanel.getLoader().load();
		
		sistemiDiBibliotechePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		sistemiDiBibliotechePanel.getLoader().load();
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
}