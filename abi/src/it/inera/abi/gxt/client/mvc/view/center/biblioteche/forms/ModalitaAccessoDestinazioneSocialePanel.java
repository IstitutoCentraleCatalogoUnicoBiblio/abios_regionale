package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaDestinazioneSocialePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaModalitaAccesoPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

public class ModalitaAccessoDestinazioneSocialePanel extends
ContentPanelForTabItem {
	private TableData d;
	private TableData d2;
	private TableData d3;
	private TableData d4;
	private SimpleComboBox<String> accessoRiservatoField;
	private NumberField limiteEtaMinField;
	private NumberField limiteEtaMaxField;

	private RadioGroup rgHandicap;
	private Radio portatoriHandicapSI;
	private Radio portatoriHandicapNO;
	private Radio portatoriHandicapNonSpecificato;

	private TextField<String> riferimentoNormativoField;
	private TextField<String> urlField;
	private ListaModalitaAccesoPanel listaModalitaAccesoPanel;
	private	ListaDestinazioneSocialePanel listaDestinazioneSocialePanel;
	public int id_biblio;

	private Button modalitaAccessoAggiorna;
	private Button resetAccesso;

	private Button limiteEtaAggiorna;
	private Button resetLimiteEta;
	private Button portatoriHandicapAggiorna;
	private Button resetHandicap;
	private Button regolamentoAggiorna;
	private Button resetRegolamento;

	private BibliotecheServiceAsync bibliotecheService;

	private Text accessoLabel;
	private Text limiteEtaMinLabel;
	private Text limiteEtaMaxLabel;
	private Text portatoriHandicapLabel;
	private Text riferimentoNormativoLabel;
	private Text urlLabel;

	/*forms*/
	private FormPanel modalitaAccessoForm;
	private FormPanel limiteEtaEListaForm;
	private FormPanel portatoriHandicapForm;
	private FormPanel regolamentoForm;

	public ModalitaAccessoDestinazioneSocialePanel() {
		super();
		bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		d = new TableData();
		d.setWidth("15%");
		d.setMargin(5);
		d.setPadding(10);

		d2 = new TableData();
		d2.setWidth("70%");
		d2.setMargin(5);
		d2.setPadding(10);
		
		d3 = new TableData();
		d3.setWidth("22%");
		d3.setMargin(5);
		d3.setPadding(10);
		
		d4 = new TableData();
		d4.setWidth("30%");
		d4.setMargin(5);
		d4.setPadding(10);

		createForms();
	}

	public void createForms() {
		createModealitaAccessoForm();
		createDestinazioneSocialeForm();
		createRegolamentoForm();
	}

	public void createModealitaAccessoForm() {
		/* MODALITA' ACCESSO */

		LayoutContainer modalitaAccesso = new LayoutContainer();
		modalitaAccesso.setStyleAttribute("padding", "5px");

		FieldSet modalitaAccessoSet = new FieldSet();
		Utils.setFieldSetProperties(modalitaAccessoSet, "Modalità di accesso");

		modalitaAccessoForm = new FormPanel();
		Utils.setFormStyleProperties(modalitaAccessoForm);

		FormLayout modalitaAccessoFormLayout = new FormLayout();
		modalitaAccessoFormLayout.setLabelAlign(LabelAlign.TOP);

		modalitaAccessoForm.setLayout(modalitaAccessoFormLayout);
		TableLayout t = new TableLayout(3);
		t.setWidth("600px");
		LayoutContainer modalitaAccessoTable = new LayoutContainer(t);

		accessoLabel = new Text("Accesso:");
		// Inserire auto completamento
		accessoLabel.setStyleAttribute("fontSize", "14px");
		modalitaAccessoTable.add(accessoLabel, d);

		accessoRiservatoField = new SimpleComboBox<String>();
		accessoRiservatoField.setTriggerAction(TriggerAction.ALL);
		accessoRiservatoField.setEditable(false);
		accessoRiservatoField.setFireChangeEventOnSetValue(true);
		accessoRiservatoField.setWidth(200);
		accessoRiservatoField.add("Aperta a tutti");
		accessoRiservatoField.add("Riservata");
		accessoRiservatoField.add("Informazione non disponibile");
		accessoRiservatoField.setSimpleValue("Informazione non disponibile");
		accessoRiservatoField.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Utils.setFontColorStyleRed(accessoLabel);
			}
		});

		modalitaAccessoTable.add(accessoRiservatoField, d);

		modalitaAccessoAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(modalitaAccessoAggiorna);

		modalitaAccessoAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String, Object> params = new HashMap<String, Object>();

							String tmpAccessoRiservato = accessoRiservatoField.getValue().getValue();
							if (tmpAccessoRiservato.compareToIgnoreCase("Riservata") == 0) {
								params.put("accessoRiservato", true);
							}
							else if(tmpAccessoRiservato.compareToIgnoreCase("Aperta a tutti") == 0) {
								params.put("accessoRiservato", false);
							}
							else params.put("accessoRiservato", null);

							bibliotecheService.setModalitaAccesso(biblioteca.getIdBiblio(),	params, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									accessoRiservatoField.setOriginalValue(accessoRiservatoField.getValue());
									Utils.setFontColorStyleBlack(accessoLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetAccesso = new Button("Reset");
		Utils.setStylesButton(resetAccesso);

		resetAccesso.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				modalitaAccessoForm.reset();
				Utils.setFontColorStyleBlack(accessoLabel);
			}
		});

		FormButtonBinding modalitaAccessoBind = new FormButtonBinding(modalitaAccessoForm);

		modalitaAccessoBind.addButton(modalitaAccessoAggiorna);
		modalitaAccessoBind.addButton(resetAccesso);

		TableLayout tableLayoutButtons = new TableLayout(2);
		tableLayoutButtons.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayoutButtons);
		buttons.add(modalitaAccessoAggiorna);
		buttons.add(resetAccesso);

		modalitaAccessoTable.add(buttons);

		modalitaAccessoForm.add(modalitaAccessoTable);
		modalitaAccessoSet.add(modalitaAccessoForm);

		/*LIMITE ETA' LISTA MODALITA' ACCESSO*/
		limiteEtaEListaForm = new FormPanel();
		Utils.setFormStyleProperties(limiteEtaEListaForm);

		FormLayout limiteEtaEListaFormLayout = new FormLayout();
		limiteEtaEListaFormLayout.setLabelAlign(LabelAlign.TOP);

		limiteEtaEListaForm.setLayout(limiteEtaEListaFormLayout);

		listaModalitaAccesoPanel = new ListaModalitaAccesoPanel();
		listaModalitaAccesoPanel.setGrid();

		limiteEtaEListaForm.add(listaModalitaAccesoPanel);

		/*LIMITE ETA*/
		TableLayout limiteEtaTableLayout = new TableLayout(3);
		limiteEtaTableLayout.setWidth("700px");
		LayoutContainer limiteEtaTable = new LayoutContainer(limiteEtaTableLayout);

		Text limiteEtaLabel = new Text("Limite di età:");
		// Inserire auto completamento
		limiteEtaLabel.setStyleAttribute("fontSize", "14px");
		limiteEtaTable.add(limiteEtaLabel, d3);

		TableLayout tableLayout = new TableLayout(4);
		tableLayout.setCellPadding(5);

		LayoutContainer minMaxContainer = new LayoutContainer(tableLayout);
		minMaxContainer.setAutoWidth(true);

		limiteEtaMinLabel = new Text("Min:");
		limiteEtaMinLabel.setStyleAttribute("fontSize", "14px");

		limiteEtaMinField = new NumberField();
		limiteEtaMinField.setWidth(40);
		limiteEtaMinField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(limiteEtaMinField, limiteEtaMinLabel);

		limiteEtaMaxLabel = new Text("Max:");
		limiteEtaMaxLabel.setStyleAttribute("fontSize", "14px");

		limiteEtaMaxField = new NumberField();
		limiteEtaMaxField.setWidth(40);
		limiteEtaMaxField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(limiteEtaMaxField, limiteEtaMaxLabel);

		minMaxContainer.add(limiteEtaMinLabel);
		minMaxContainer.add(limiteEtaMinField);
		minMaxContainer.add(limiteEtaMaxLabel);
		minMaxContainer.add(limiteEtaMaxField);

		limiteEtaTable.add(minMaxContainer, d);

		limiteEtaAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(limiteEtaAggiorna);

		limiteEtaAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String, Object> params = new HashMap<String, Object>();

							if (limiteEtaMinField.getValue() != null) {
								params.put("limiteEtaMin",limiteEtaMinField.getValue().intValue());
							}

							if (limiteEtaMaxField.getValue() != null) {
								params.put("limiteEtaMax", limiteEtaMaxField.getValue().intValue());
							}

							bibliotecheService.setLimiteEtaAccesso(biblioteca.getIdBiblio(),	params, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									limiteEtaMinField.setOriginalValue(limiteEtaMinField.getValue());
									limiteEtaMaxField.setOriginalValue(limiteEtaMaxField.getValue());
									Utils.setFontColorStyleBlack(limiteEtaMinLabel);
									Utils.setFontColorStyleBlack(limiteEtaMaxLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetLimiteEta = new Button("Reset");
		Utils.setStylesButton(resetLimiteEta);

		resetLimiteEta.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				limiteEtaEListaForm.reset();
				Utils.setFontColorStyleBlack(limiteEtaMinLabel);
				Utils.setFontColorStyleBlack(limiteEtaMaxLabel);
			}
		});

		FormButtonBinding limiteEtaBind = new FormButtonBinding(limiteEtaEListaForm);

		limiteEtaBind.addButton(limiteEtaAggiorna);
		limiteEtaBind.addButton(resetLimiteEta);

		TableLayout limiteEtaTableLayoutButtons = new TableLayout(2);
		limiteEtaTableLayoutButtons.setCellPadding(5);
		LayoutContainer limiteEtaButtons = new LayoutContainer(limiteEtaTableLayoutButtons);
		limiteEtaButtons.add(limiteEtaAggiorna);
		limiteEtaButtons.add(resetLimiteEta);
		
		limiteEtaTable.add(limiteEtaButtons);

		limiteEtaEListaForm.add(limiteEtaTable);
		/*FINE LIMITE ETA*/

		modalitaAccessoSet.add(limiteEtaEListaForm);
		modalitaAccesso.add(modalitaAccessoSet);
		add(modalitaAccesso);

		/*LIMITE ETA' LISTA MODALITA' ACCESSO*/

		/*ACCESSO HANDICAP*/	
		portatoriHandicapForm = new FormPanel();
		portatoriHandicapForm.setHeaderVisible(false);
		portatoriHandicapForm.setBorders(true);
		portatoriHandicapForm.setBodyBorder(true);
		Utils.setFormStyleProperties(portatoriHandicapForm);

		FormLayout portatoriHandicapFormLayout = new FormLayout();
		portatoriHandicapFormLayout.setLabelAlign(LabelAlign.TOP);

		portatoriHandicapForm.setLayout(portatoriHandicapFormLayout);

		TableLayout tAccessoHandicap = new TableLayout(3);
		tAccessoHandicap.setWidth("600px");

		LayoutContainer portatoriHandicapTable = new LayoutContainer(tAccessoHandicap);
		portatoriHandicapTable.setWidth(600);
		portatoriHandicapLabel = new Text("Accesso portatori handicap:");
		portatoriHandicapLabel.setStyleAttribute("fontSize", "14px");
		portatoriHandicapTable.add(portatoriHandicapLabel, d4);

		LayoutContainer portatoriHandicapLabeleRadioButton = new LayoutContainer(new FlowLayout());
		portatoriHandicapLabeleRadioButton.setAutoWidth(true);

		rgHandicap = new RadioGroup("portatoriHandicap");
		rgHandicap.setStyleAttribute("position", "static");

		portatoriHandicapSI = new Radio();
		portatoriHandicapSI.setBoxLabel("SI");

		portatoriHandicapNO = new Radio();
		portatoriHandicapNO.setBoxLabel("NO");

		portatoriHandicapNonSpecificato = new Radio();
		portatoriHandicapNonSpecificato	.setBoxLabel("Non specificato");

		rgHandicap.add(portatoriHandicapSI);
		rgHandicap.add(portatoriHandicapNO);
		rgHandicap.add(portatoriHandicapNonSpecificato);

		portatoriHandicapLabeleRadioButton.add(rgHandicap);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgHandicap, portatoriHandicapLabel);

		portatoriHandicapTable.add(portatoriHandicapLabeleRadioButton, d);

		portatoriHandicapAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(portatoriHandicapAggiorna);

		portatoriHandicapAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							Boolean accessoHandicap = null;

							if (portatoriHandicapNonSpecificato.getValue() == false) {
								accessoHandicap = portatoriHandicapSI.getValue()?true:false;								
							} 
							else {
								accessoHandicap = null;
							}

							bibliotecheService.setAccessoHandicap(biblioteca.getIdBiblio(),accessoHandicap, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									if (portatoriHandicapNonSpecificato.getValue() == false) {
										portatoriHandicapSI.setOriginalValue(portatoriHandicapSI.getValue());
										portatoriHandicapNO.setOriginalValue(portatoriHandicapNO.getValue());
										portatoriHandicapNonSpecificato.setOriginalValue(false);

										rgHandicap.setOriginalValue(portatoriHandicapSI.getValue()?portatoriHandicapSI:portatoriHandicapNO);
									}
									else {
										portatoriHandicapSI.setOriginalValue(false);
										portatoriHandicapNO.setOriginalValue(false);
										portatoriHandicapNonSpecificato.setOriginalValue(true);

										rgHandicap.setOriginalValue(portatoriHandicapNonSpecificato);
									}
									Utils.setFontColorStyleBlack(portatoriHandicapLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetHandicap = new Button("Reset");
		Utils.setStylesButton(resetHandicap);

		resetHandicap.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				rgHandicap.reset();
				Utils.setFontColorStyleBlack(portatoriHandicapLabel);

			}
		});

		FormButtonBinding portatoriHandicapBind = new FormButtonBinding(portatoriHandicapForm);
		portatoriHandicapBind.addButton(portatoriHandicapAggiorna);
		portatoriHandicapBind.addButton(resetHandicap);

		TableLayout tableLayoutAccessoHandicap = new TableLayout(2);
		tableLayoutAccessoHandicap.setCellPadding(5);
		LayoutContainer buttonsAccassoHandicap = new LayoutContainer(tableLayoutAccessoHandicap);
		buttonsAccassoHandicap.add(portatoriHandicapAggiorna);
		buttonsAccassoHandicap.add(resetHandicap);

		portatoriHandicapTable.add(buttonsAccassoHandicap, d);

		portatoriHandicapForm.add(portatoriHandicapTable);
		modalitaAccessoSet.add(portatoriHandicapForm);
		/*FINE ACCESSO HANDICAP*/
		/* FINE---MODALITA' ACCESSO */
	}

	public void createDestinazioneSocialeForm() {
		/* DESTINAZIONE SOCIALE */
		LayoutContainer destinazioneSociale = new LayoutContainer();
		destinazioneSociale.setStyleAttribute("padding", "5px");

		FieldSet destinazioneSocialeSet = new FieldSet();
		Utils.setFieldSetProperties(destinazioneSocialeSet, "Destinazione sociale");

		listaDestinazioneSocialePanel = new ListaDestinazioneSocialePanel();
		listaDestinazioneSocialePanel.setGrid();

		destinazioneSocialeSet.add(listaDestinazioneSocialePanel);

		destinazioneSociale.add(destinazioneSocialeSet);
		add(destinazioneSociale);

		/* FINE_DESTINAZIONE SOCIALE */
	}

	public void createRegolamentoForm() {
		/* REGOLAMENTO */
		LayoutContainer regolamento = new LayoutContainer();

		regolamento.setStyleAttribute("padding", "5px");

		FieldSet regolamentoSet = new FieldSet();
		Utils.setFieldSetProperties(regolamentoSet, "Regolamento");

		regolamentoForm = new FormPanel();
		regolamentoForm.setHeaderVisible(false);
		regolamentoForm.setBorders(false);
		regolamentoForm.setBodyBorder(false);
		regolamentoForm.setWidth(750);

		FormLayout regolamentoFormLayout = new FormLayout();
		regolamentoFormLayout.setLabelAlign(LabelAlign.TOP);

		regolamentoForm.setLayout(regolamentoFormLayout);

		LayoutContainer regolamentoTable = new LayoutContainer(new TableLayout(3));

		riferimentoNormativoLabel = new Text("Riferimento normativo:");
		// Inserire auto completamento
		riferimentoNormativoLabel.setStyleAttribute("fontSize", "14px");
		regolamentoTable.add(riferimentoNormativoLabel, d4);

		riferimentoNormativoField = new TextField<String>();
		riferimentoNormativoField.setWidth(400);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(riferimentoNormativoField, riferimentoNormativoLabel);

		regolamentoTable.add(riferimentoNormativoField, d);
		regolamentoTable.add(new LayoutContainer(), d);

		urlLabel = new Text("URL:");
		// Inserire auto completamento
		urlLabel.setStyleAttribute("fontSize", "14px");
		regolamentoTable.add(urlLabel, d);

		urlField = new TextField<String>();
		urlField.setWidth(400);
		Utils.addListenerToChangeLabelColorIfModifiedTextField(urlField, urlLabel);

		regolamentoTable.add(urlField, d);
		regolamentoTable.add(new LayoutContainer(), d);

		regolamentoAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(regolamentoAggiorna);

		regolamentoAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {


							HashMap<String,String> params = new HashMap<String,String>();
							params.put("riferimentoNormativo", riferimentoNormativoField.getValue());
							params.put("url", urlField.getValue());

							bibliotecheService.setRegolamento(params,biblioteca.getIdBiblio(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									riferimentoNormativoField.setOriginalValue(riferimentoNormativoField.getValue());
									urlField.setOriginalValue(urlField.getValue());
									Utils.setFontColorStyleBlack(riferimentoNormativoLabel);
									Utils.setFontColorStyleBlack(urlLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});

						}

					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetRegolamento = new Button("Reset");
		Utils.setStylesButton(resetRegolamento);

		resetRegolamento.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				regolamentoForm.reset();
				Utils.setFontColorStyleBlack(riferimentoNormativoLabel);
				Utils.setFontColorStyleBlack(urlLabel);
			}
		});

		FormButtonBinding regolamentoBind = new FormButtonBinding(regolamentoForm);
		regolamentoBind.addButton(regolamentoAggiorna);
		regolamentoBind.addButton(resetRegolamento);

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(regolamentoAggiorna);
		buttons.add(resetRegolamento);
		regolamentoTable.add(new LayoutContainer(), d);
		regolamentoTable.add(buttons, d);
		regolamentoTable.add(new LayoutContainer(), d);

		regolamentoForm.add(regolamentoTable);
		regolamentoSet.add(regolamentoForm);
		regolamento.add(regolamentoSet);
		add(regolamento);
		/* FINE---REGOLAMENTO */
	}

	public void setFieldsValues() {

		// MODALITA' ACCESSO
		if (biblioteca.getAccessoRiservato() == null) {
			accessoRiservatoField.setRawValue("Informazione non disponibile");
		} 
		else if (biblioteca.getAccessoRiservato() == true) {
			accessoRiservatoField.setRawValue("Riservata");
			accessoRiservatoField.setSimpleValue("Riservata");
		} 
		else if (biblioteca.getAccessoRiservato() == false) {
			accessoRiservatoField.setRawValue("Aperta a tutti");
			accessoRiservatoField.setSimpleValue("Aperta a tutti");
		}

		accessoRiservatoField.setOriginalValue(accessoRiservatoField.getValue());
		Utils.setFontColorStyleBlack(accessoLabel);
		UIWorkflow.setReadOnly(accessoRiservatoField);

		if (biblioteca.getLimiteEtaMin() != null) {
			limiteEtaMinField.setValue(biblioteca.getLimiteEtaMin());
			limiteEtaMinField.setOriginalValue(biblioteca.getLimiteEtaMin());
		}
		else {
			limiteEtaMinField.setValue(null);
			limiteEtaMinField.setOriginalValue(null);
		}

		if (biblioteca.getLimiteEtaMax() != null) {
			limiteEtaMaxField.setValue(biblioteca.getLimiteEtaMax());
			limiteEtaMaxField.setOriginalValue(biblioteca.getLimiteEtaMax());
		}
		else {
			limiteEtaMaxField.setValue(null);
			limiteEtaMaxField.setOriginalValue(null);
		}
		Utils.setFontColorStyleBlack(limiteEtaMinLabel);
		Utils.setFontColorStyleBlack(limiteEtaMaxLabel);

		//setto l'della biblioteca nella griglia
		UIWorkflow.setReadOnly(limiteEtaMinField);
		UIWorkflow.setReadOnly(limiteEtaMaxField);

		listaModalitaAccesoPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		//carico lo store nella griglia
		listaModalitaAccesoPanel.getLoader().load();
		//DESTINAZIONE SOCIALE

		listaDestinazioneSocialePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaDestinazioneSocialePanel.getLoader().load();

		if (biblioteca.getAccessoHandicap() != null) {
			portatoriHandicapSI.setValue(biblioteca.getAccessoHandicap());
			portatoriHandicapNO.setValue(!biblioteca.getAccessoHandicap());
			portatoriHandicapNonSpecificato.setValue(false);
			portatoriHandicapSI.setOriginalValue(biblioteca.getAccessoHandicap());
			portatoriHandicapNO.setOriginalValue(!biblioteca.getAccessoHandicap());
			portatoriHandicapNonSpecificato.setOriginalValue(false);

			rgHandicap.setOriginalValue(biblioteca.getAccessoHandicap()?portatoriHandicapSI:portatoriHandicapNO);
		} 
		else {
			portatoriHandicapSI.setValue(false);
			portatoriHandicapNO.setValue(false);
			portatoriHandicapNonSpecificato.setValue(true);
			portatoriHandicapSI.setOriginalValue(false);
			portatoriHandicapNO.setOriginalValue(false);
			portatoriHandicapNonSpecificato.setOriginalValue(true);

			rgHandicap.setOriginalValue(portatoriHandicapNonSpecificato);
		}
		Utils.setFontColorStyleBlack(portatoriHandicapLabel);

		UIWorkflow.enableDisable(portatoriHandicapSI);
		UIWorkflow.enableDisable(portatoriHandicapNO);
		UIWorkflow.enableDisable(portatoriHandicapNonSpecificato);

		/* REGOLAMENTO */
		if (biblioteca.getRegolamento() != null) {
			riferimentoNormativoField.setValue(biblioteca.getRegolamento().getRiferimentoNormativa());
			urlField.setValue(biblioteca.getRegolamento().getUrl());
			riferimentoNormativoField.setOriginalValue(biblioteca.getRegolamento().getRiferimentoNormativa());
			urlField.setOriginalValue(biblioteca.getRegolamento().getUrl());
		} 
		else {
			riferimentoNormativoField.setValue(null);
			urlField.setValue(null);
			riferimentoNormativoField.setOriginalValue(null);
			urlField.setOriginalValue(null);
		}
		Utils.setFontColorStyleBlack(riferimentoNormativoLabel);
		Utils.setFontColorStyleBlack(urlLabel);

		UIWorkflow.setReadOnly(riferimentoNormativoField);
		UIWorkflow.setReadOnly(urlField);

		// se visualizza tolgo i bottoni
		UIWorkflow.hideView(modalitaAccessoAggiorna);
		UIWorkflow.hideView(resetAccesso);
		UIWorkflow.hideView(portatoriHandicapAggiorna);
		UIWorkflow.hideView(resetHandicap);
		UIWorkflow.hideView(regolamentoAggiorna);
		UIWorkflow.hideView(resetRegolamento);
		
		UIWorkflow.hideView(limiteEtaAggiorna);
		UIWorkflow.hideView(resetLimiteEta);
		if(UIWorkflow.isReadOnly()==false){
			addKeyListenerForEnter();
		}else{
			removeKeyListenerForEnter();
		}
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;
	}

	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(modalitaAccessoAggiorna, modalitaAccessoForm);
		Utils.addKeyListenerForEnter(portatoriHandicapAggiorna, portatoriHandicapForm);
		Utils.addKeyListenerForEnter(regolamentoAggiorna, regolamentoForm);
		Utils.addKeyListenerForEnter(limiteEtaAggiorna, limiteEtaEListaForm);
	}
	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( modalitaAccessoForm);
		Utils.removeKeyListenerForEnter( portatoriHandicapForm);
		Utils.removeKeyListenerForEnter( regolamentoForm);
		Utils.removeKeyListenerForEnter(limiteEtaEListaForm);
	}

}
