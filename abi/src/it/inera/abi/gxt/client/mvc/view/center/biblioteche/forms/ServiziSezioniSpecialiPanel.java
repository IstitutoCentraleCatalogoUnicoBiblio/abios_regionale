package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaModalitaComunicazioneInformazioniBilbiografiche;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaRiproduzioniFinitureDocumentiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaSezioniSpecialiPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServiziSezioniSpecialiPanel extends ContentPanelForTabItem {

	private int id_biblio;

	private ListaRiproduzioniFinitureDocumentiPanel listaRiproduzioniFinitureDocumentiPanel;
	private ListaModalitaComunicazioneInformazioniBilbiografiche listaModalitaComunicazioniInformazioniBilbiografiche;
	private ListaSezioniSpecialiPanel listaSezioniSpecialiPanel;

	private RadioGroup rgservInSede;
	private Radio servizioInSedeSI;
	private Radio servizioInSedeNO;
	private Radio servizioInSedeNonSpecificato;

	private RadioGroup rgservEsterno;
	private	Radio servizioEsternoSI;
	private Radio servizioEsternoNO;
	private Radio servizioEsternoNonSpecificato;

	SimpleComboBox<String> accessoAPagamentoField;
	SimpleComboBox<String> accessoATempoField;
	SimpleComboBox<String> accessoConProxyField;

	private	TableData d;
	private TableData d1;
	private	TableData d2;
	private	TableData d3;

	private Button attivoRiproduzioniAggiorna;
	private Button resetAttivoRiproduzioni;

	private	Button accessoInternetAggiorna;
	private	Button resetInternet;
	private	Button servizioInternoEsternoAggiorna;
	private	Button resetServizi;

	private BibliotecheServiceAsync bibliotecheService;

	private Text attivoRiproduzioniLabel;
	private SimpleComboBox<String> attivoRiproduzioniField;

	private Text attivoLabel;
	private SimpleComboBox<String> attivoField;

	private Text servizioInSedeLabel;
	private Text servizioEsternoLabel;

	private Text attivaAccessoLabel;
	private SimpleComboBox<String> attivaAccessoField;

	private Text accessoApagamentoLabel;
	private Text accessoATempoLabel;
	private Text accessoProxyLabel;

	/* Form */
	private FormPanel servizioInternoEsternoForm;
	private FormPanel accessoInternetForm;

	public ServiziSezioniSpecialiPanel() {
		super();

		bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);

		d1 = new TableData();
		d1.setWidth("5%");
		d1.setMargin(5);
		d1.setPadding(10);

		d = new TableData();
		d.setWidth("10%");
		d.setMargin(5);
		d.setPadding(10);

		d2 = new TableData();
		d2.setWidth("15%");
		d2.setMargin(5);
		d2.setPadding(10);

		d3 = new TableData();
		d3.setWidth("40%");
		d3.setMargin(5);
		d3.setPadding(10);

		/* RIPRODUZIONE-FINITURE DOCUMENTI */

		LayoutContainer servizi = new LayoutContainer();
		servizi.setStyleAttribute("padding", "5px");

		FieldSet serviziSet = new FieldSet();
		Utils.setFieldSetProperties(serviziSet, "Tipologie di riproduzione");

		LayoutContainer attivaRiproduzioniTable = new LayoutContainer(new TableLayout(3));
		attivaRiproduzioniTable.setWidth(500);

		attivoRiproduzioniLabel = new Text("Attiva riproduzioni:");
		attivoRiproduzioniLabel.setStyleAttribute("fontSize", "14px");
		attivaRiproduzioniTable.add(attivoRiproduzioniLabel, d);

		attivoRiproduzioniField = new SimpleComboBox<String>();
		attivoRiproduzioniField.setTriggerAction(TriggerAction.ALL);
		attivoRiproduzioniField.setEditable(false);
		attivoRiproduzioniField.setFireChangeEventOnSetValue(true);
		attivoRiproduzioniField.setWidth(200);
		attivoRiproduzioniField.add("Si");
		attivoRiproduzioniField.add("No");
		attivoRiproduzioniField.add("Non specificato");
		attivoRiproduzioniField.setSimpleValue("Non specificato");

		attivoRiproduzioniField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				Utils.setFontColorStyleRed(attivoRiproduzioniLabel);

				if ("Si".equals(se.getSelectedItem().getValue())) {
					listaRiproduzioniFinitureDocumentiPanel.enable();

				} else if ("No".equals(se.getSelectedItem().getValue())) {
					listaRiproduzioniFinitureDocumentiPanel.disable();

				} else {/* Non specificato */
					listaRiproduzioniFinitureDocumentiPanel.disable();
				}

			}
		});

		attivaRiproduzioniTable.add(attivoRiproduzioniField, d);

		attivoRiproduzioniAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(attivoRiproduzioniAggiorna);
		attivoRiproduzioniAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							Boolean hasAttivoRiproduzioni = null;
							
							if (attivoRiproduzioniField.getValue() != null) {
								if ("Si".equals(attivoRiproduzioniField.getValue().getValue())) {
									hasAttivoRiproduzioni = true;
									
								} else if ("No".equals(attivoRiproduzioniField.getValue().getValue())) {
									hasAttivoRiproduzioni = false;
									
								} else {/* Non specificato */
									hasAttivoRiproduzioni = null;
								}
								
								attivoRiproduzioniField.setSimpleValue(attivoRiproduzioniField.getValue().getValue());
							}
							
							bibliotecheService.setAttivoRiproduzioni(id_biblio, hasAttivoRiproduzioni, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
//									attivoRiproduzioniField.setOriginalValue(attivoRiproduzioniField.getValue());		
									Utils.setFontColorStyleBlack(attivoRiproduzioniLabel);

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									listaRiproduzioniFinitureDocumentiPanel.getLoader().load();
									fireReleoadbiblioDataEvent();
									
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetAttivoRiproduzioni = new Button("Reset");
		Utils.setStylesButton(resetAttivoRiproduzioni);
		resetAttivoRiproduzioni.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (biblioteca.getAttivoRiproduzioni() == null) {
//					attivoRiproduzioniField.setRawValue("Non specificato");
					attivoRiproduzioniField.setSimpleValue("Non specificato");

				} else {
					if (biblioteca.getAttivoRiproduzioni().booleanValue() == true) {
//						attivoRiproduzioniField.setRawValue("Si");
						attivoRiproduzioniField.setSimpleValue("Si");

					} else if  (biblioteca.getAttivoRiproduzioni().booleanValue() == false) {
//						attivoRiproduzioniField.setRawValue("No");
						attivoRiproduzioniField.setSimpleValue("No");
					}
				} 

//				attivoRiproduzioniField.setOriginalValue(attivoRiproduzioniField.getValue());
				Utils.setFontColorStyleBlack(attivoRiproduzioniLabel);

			}
		});

		TableLayout riproButtonsTableLayout = new TableLayout(2);
		riproButtonsTableLayout.setCellPadding(5);
		LayoutContainer riproButtons = new LayoutContainer(riproButtonsTableLayout);
		riproButtons.add(attivoRiproduzioniAggiorna);
		riproButtons.add(resetAttivoRiproduzioni);

		attivaRiproduzioniTable.add(riproButtons, d);

		serviziSet.add(attivaRiproduzioniTable);


		listaRiproduzioniFinitureDocumentiPanel = new ListaRiproduzioniFinitureDocumentiPanel();
		listaRiproduzioniFinitureDocumentiPanel.setGrid();

		serviziSet.add(listaRiproduzioniFinitureDocumentiPanel);

		servizi.add(serviziSet);
		add(servizi);
		/* FINE---RIPRODUZIONE-FINITURE DOCUMENTI */

		/* INFORMAZIONI BIBLIOGRAFICHE */

		LayoutContainer informazioniBibliografiche = new LayoutContainer();
		informazioniBibliografiche.setStyleAttribute("padding", "5px");

		FieldSet informazioniBibliograficheSet = new FieldSet();
		Utils.setFieldSetProperties(informazioniBibliograficheSet, "Informazioni bibliografiche");

		servizioInternoEsternoForm = new FormPanel();
		Utils.setFormStyleProperties(servizioInternoEsternoForm);
		servizioInternoEsternoForm.setWidth(750);

		FormLayout servizioInternoEsternoFormLayout = new FormLayout();
		servizioInternoEsternoFormLayout.setLabelAlign(LabelAlign.RIGHT);

		servizioInternoEsternoForm.setLayout(servizioInternoEsternoFormLayout);

		LayoutContainer servizioInternoEsternoTable = new LayoutContainer(new TableLayout(2));
		servizioInternoEsternoTable.setWidth(500);

		attivoLabel = new Text("Attiva informazioni bibliografiche:");
		attivoLabel.setStyleAttribute("fontSize", "14px");
		servizioInternoEsternoTable.add(attivoLabel, d);

		attivoField = new SimpleComboBox<String>();
		attivoField.setTriggerAction(TriggerAction.ALL);
		attivoField.setEditable(false);
		attivoField.setFireChangeEventOnSetValue(true);
		attivoField.setWidth(200);
		attivoField.add("Si");
		attivoField.add("No");
		attivoField.add("Non specificato");
		attivoField.setSimpleValue("Non specificato");

		servizioInternoEsternoTable.add(attivoField, d);

		servizioInSedeLabel = new Text("Servizio in sede:");
		servizioInSedeLabel.setStyleAttribute("fontSize", "14px");
		servizioInternoEsternoTable.add(servizioInSedeLabel, d1);

		LayoutContainer servizioInSedeRadioButton = new LayoutContainer(new FlowLayout());
		servizioInSedeRadioButton.setAutoWidth(true);

		rgservInSede = new RadioGroup("servizioInSede");
		rgservInSede.setStyleAttribute("position", "static");

		servizioInSedeSI = new Radio();
		servizioInSedeSI.setBoxLabel("SI");

		servizioInSedeNO = new Radio();
		servizioInSedeNO.setBoxLabel("NO");

		servizioInSedeNonSpecificato = new Radio();
		servizioInSedeNonSpecificato.setBoxLabel("Non specificato");

		rgservInSede.add(servizioInSedeSI);
		rgservInSede.add(servizioInSedeNO);
		rgservInSede.add(servizioInSedeNonSpecificato);

		servizioInSedeRadioButton.add(rgservInSede);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgservInSede, servizioInSedeLabel);

		servizioInternoEsternoTable.add(servizioInSedeRadioButton, d);

		servizioEsternoLabel = new Text("Servizio esterno:");
		servizioEsternoLabel.setStyleAttribute("fontSize", "14px");
		servizioInternoEsternoTable.add(servizioEsternoLabel, d1);

		LayoutContainer servizioEsternoRadioButton = new LayoutContainer(new FlowLayout());
		servizioEsternoRadioButton.setAutoWidth(true);

		rgservEsterno = new RadioGroup("servizioEsterno");
		rgservEsterno.setStyleAttribute("position", "static");

		servizioEsternoSI = new Radio();
		servizioEsternoSI.setBoxLabel("SI");

		servizioEsternoNO = new Radio();
		servizioEsternoNO.setBoxLabel("NO");

		servizioEsternoNonSpecificato = new Radio();
		servizioEsternoNonSpecificato.setBoxLabel("Non Specificato");

		rgservEsterno.add(servizioEsternoSI);
		rgservEsterno.add(servizioEsternoNO);
		rgservEsterno.add(servizioEsternoNonSpecificato);

		servizioEsternoRadioButton.add(rgservEsterno);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgservEsterno, servizioEsternoLabel);

		servizioInternoEsternoTable.add(servizioEsternoRadioButton, d);

		attivoField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				Utils.setFontColorStyleRed(attivoLabel);

				if ("Si".equalsIgnoreCase(se.getSelectedItem().getValue())) {
					servizioInSedeSI.setValue(true);
					servizioInSedeNO.setValue(false);
					servizioInSedeNonSpecificato.setValue(false);
					rgservInSede.setValue(servizioInSedeSI);
					enableDisableInfoBibliograficheInSede(true);

					servizioEsternoSI.setValue(true);
					servizioEsternoNO.setValue(false);
					servizioEsternoNonSpecificato.setValue(false);
					rgservEsterno.setValue(servizioEsternoSI);
					enableDisableInfoBibliograficheEsterne(true);

				} else if ("No".equalsIgnoreCase(se.getSelectedItem().getValue())) {
					servizioInSedeSI.setValue(false);
					servizioInSedeNO.setValue(true);
					servizioInSedeNonSpecificato.setValue(false);
					rgservInSede.setValue(servizioInSedeNO);
					enableDisableInfoBibliograficheInSede(false);

					servizioEsternoSI.setValue(false);
					servizioEsternoNO.setValue(true);
					servizioEsternoNonSpecificato.setValue(false);
					rgservEsterno.setValue(servizioEsternoNO);
					enableDisableInfoBibliograficheEsterne(false);

				} else {/* Non specificato */
					servizioInSedeSI.setValue(false);
					servizioInSedeNO.setValue(false);
					servizioInSedeNonSpecificato.setValue(true);
					rgservInSede.setValue(servizioInSedeNonSpecificato);
					enableDisableInfoBibliograficheInSede(false);

					servizioEsternoSI.setValue(false);
					servizioEsternoNO.setValue(false);
					servizioEsternoNonSpecificato.setValue(true);
					rgservEsterno.setValue(servizioEsternoNonSpecificato);
					enableDisableInfoBibliograficheEsterne(false);
				}

			}
		});

		servizioInternoEsternoAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(servizioInternoEsternoAggiorna);
		servizioInternoEsternoAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							Boolean hasAttivoInformazioniBibliografiche = null;
							Boolean hasServizioBibliograficoInterno = null;
							Boolean hasServizioBibliograficoEsterno = null;

							if (attivoField.getValue() != null) {
								if ("Si".equalsIgnoreCase(attivoField.getValue().getValue())) {
									hasAttivoInformazioniBibliografiche = new Boolean(true);

								} else if ("No".equalsIgnoreCase(attivoField.getValue().getValue())) {
									hasAttivoInformazioniBibliografiche = new Boolean(false);

								} else {/* Non specificato */
									hasAttivoInformazioniBibliografiche = null;
								}
							}

							if (servizioInSedeNonSpecificato.getValue() == false) {
								hasServizioBibliograficoInterno = servizioInSedeSI.getValue();
							}
							else {
								hasServizioBibliograficoInterno = null;
							}

							if (servizioEsternoNonSpecificato.getValue() == false) {
								hasServizioBibliograficoEsterno = servizioEsternoSI.getValue();
							}
							else {
								hasServizioBibliograficoEsterno = null;
							}

							bibliotecheService.setServizioBibliograficoInternoEsterno(id_biblio, hasAttivoInformazioniBibliografiche, hasServizioBibliograficoInterno, hasServizioBibliograficoEsterno, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									attivoField.setOriginalValue(attivoField.getValue());

									if (servizioInSedeNonSpecificato.getValue() == false) {										
										servizioInSedeSI.setOriginalValue(servizioInSedeSI.getValue());
										servizioInSedeNO.setOriginalValue(servizioInSedeNO.getValue());
										servizioInSedeNonSpecificato.setOriginalValue(servizioInSedeNonSpecificato.getValue());

										rgservInSede.setOriginalValue(servizioInSedeSI.getValue()?servizioInSedeSI:servizioInSedeNO);
									}
									else {
										servizioInSedeSI.setOriginalValue(false);
										servizioInSedeNO.setOriginalValue(false);
										servizioInSedeNonSpecificato.setOriginalValue(true);

										rgservInSede.setOriginalValue(servizioInSedeNonSpecificato);
									}

									if (servizioEsternoNonSpecificato.getValue() == false) {
										servizioEsternoSI.setOriginalValue(servizioEsternoSI.getValue());
										servizioEsternoNO.setOriginalValue(servizioEsternoNO.getValue());
										servizioEsternoNonSpecificato.setOriginalValue(servizioEsternoNonSpecificato.getValue());

										rgservEsterno.setValue(servizioEsternoSI.getValue()?servizioEsternoSI:servizioEsternoNO);
									}
									else {										
										servizioEsternoSI.setOriginalValue(false);
										servizioEsternoNO.setOriginalValue(false);
										servizioEsternoNonSpecificato.setOriginalValue(true);

										rgservEsterno.setOriginalValue(servizioEsternoNonSpecificato);
									}
									Utils.setFontColorStyleBlack(attivoLabel);
									Utils.setFontColorStyleBlack(servizioInSedeLabel);
									Utils.setFontColorStyleBlack(servizioEsternoLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
							});
						} 
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetServizi = new Button("Reset");
		Utils.setStylesButton(resetServizi);
		resetServizi.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				servizioInternoEsternoForm.reset();
				resetServizioBibliograficoInternoEsterno();
				Utils.setFontColorStyleBlack(attivoLabel);
				Utils.setFontColorStyleBlack(servizioInSedeLabel);
				Utils.setFontColorStyleBlack(servizioEsternoLabel);
			}
		});

		FormButtonBinding servizioInternoEsternoBind = new FormButtonBinding(servizioInternoEsternoForm);
		servizioInternoEsternoBind.addButton(servizioInternoEsternoAggiorna);
		servizioInternoEsternoBind.addButton(resetServizi);

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(servizioInternoEsternoAggiorna);
		buttons.add(resetServizi);

		servizioInternoEsternoTable.add(new LayoutContainer(), d);
		servizioInternoEsternoTable.add(buttons, d);

		servizioInternoEsternoForm.add(servizioInternoEsternoTable);
		informazioniBibliograficheSet.add(servizioInternoEsternoForm);
		/**/
		/*MODALITA COMUNICAZIONI BIBLIOGRAFICHE*/
		listaModalitaComunicazioniInformazioniBilbiografiche = new ListaModalitaComunicazioneInformazioniBilbiografiche();
		listaModalitaComunicazioniInformazioniBilbiografiche.setGrid();
		informazioniBibliograficheSet.add(listaModalitaComunicazioniInformazioniBilbiografiche);

		informazioniBibliografiche.add(informazioniBibliograficheSet);
		add(informazioniBibliografiche);
		/* FINE---INFORMAZIONI BIBLIOGRAFICHE */

		/*SEZIONI SPECIALI*/
		LayoutContainer sezioniSpeciali = new LayoutContainer();
		sezioniSpeciali.setStyleAttribute("padding", "5px");

		FieldSet sezioniSpecialiSet = new FieldSet();
		Utils.setFieldSetProperties(sezioniSpecialiSet, "Sezioni speciali");

		listaSezioniSpecialiPanel = new ListaSezioniSpecialiPanel();
		listaSezioniSpecialiPanel.setGrid();

		sezioniSpecialiSet.add(listaSezioniSpecialiPanel);
		sezioniSpeciali.add(sezioniSpecialiSet);
		add(sezioniSpeciali);


		/*FINE---SEZIONI SPEICIALI*/

		/*ACCESSO INTERNET*/
		LayoutContainer accessoInternet = new LayoutContainer();
		accessoInternet.setStyleAttribute("padding", "5px");

		FieldSet accessoInternetSet = new FieldSet();
		Utils.setFieldSetProperties(accessoInternetSet, "Accesso internet");

		accessoInternetForm = new FormPanel();
		accessoInternetForm.setHeaderVisible(false);
		accessoInternetForm.setBorders(false);
		accessoInternetForm.setBodyBorder(false);
		accessoInternetForm.setWidth(750);
		FormLayout accessoInternetFormLayout = new FormLayout();
		accessoInternetFormLayout.setLabelAlign(LabelAlign.TOP);

		accessoInternetForm.setLayout(accessoInternetFormLayout);

		LayoutContainer accessoInternetTable = new LayoutContainer(new TableLayout(4));
		accessoInternetTable.setWidth(750);
		attivaAccessoLabel = new Text("Attiva accesso internet:");
		attivaAccessoLabel.setStyleAttribute("fontSize", "14px");

		attivaAccessoField = new SimpleComboBox<String>();
		attivaAccessoField.setTriggerAction(TriggerAction.ALL);
		attivaAccessoField.setEditable(false);
		attivaAccessoField.setFireChangeEventOnSetValue(true);
		attivaAccessoField.add("Si");
		attivaAccessoField.add("No");
		attivaAccessoField.add("Non specificato");

		attivaAccessoField.setSimpleValue("Non specificato");
		Utils.addListenerToChangeLabelColorIfModifiedSimpleComboboxString(attivaAccessoField, attivaAccessoLabel);

		attivaAccessoField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(
					SelectionChangedEvent<SimpleComboValue<String>> se) {
				Utils.setFontColorStyleRed(attivaAccessoLabel);

				if ("Si".equalsIgnoreCase(se.getSelectedItem().getValue())) {
					accessoATempoField.setSimpleValue("Si");
					accessoAPagamentoField.setSimpleValue("Si");
					accessoConProxyField.setSimpleValue("Si");
					accessoATempoField.enable();
					accessoAPagamentoField.enable();
					accessoConProxyField.enable();

				} else if ("No".equalsIgnoreCase(se.getSelectedItem().getValue())) {
					accessoATempoField.setSimpleValue("No");
					accessoAPagamentoField.setSimpleValue("No");
					accessoConProxyField.setSimpleValue("No");
					accessoATempoField.disable();
					accessoAPagamentoField.disable();
					accessoConProxyField.disable();

				} else {/* Non specificato */
					accessoATempoField.setSimpleValue("Non specificato");
					accessoAPagamentoField.setSimpleValue("Non specificato");
					accessoConProxyField.setSimpleValue("Non specificato");
					accessoATempoField.disable();
					accessoAPagamentoField.disable();
					accessoConProxyField.disable();

				}

			}
		});


		accessoApagamentoLabel = new Text("A pagamento:");
		accessoApagamentoLabel.setStyleAttribute("fontSize", "14px");

		accessoAPagamentoField = new SimpleComboBox<String>();
		accessoAPagamentoField.setTriggerAction(TriggerAction.ALL);
		accessoAPagamentoField.setEditable(false);
		accessoAPagamentoField.setFireChangeEventOnSetValue(true);
		accessoAPagamentoField.add("Si");
		accessoAPagamentoField.add("No");
		accessoAPagamentoField.add("Non specificato");

		accessoAPagamentoField.setSimpleValue("Non specificato");
		Utils.addListenerToChangeLabelColorIfModifiedSimpleComboboxString(accessoAPagamentoField, accessoApagamentoLabel);

		accessoATempoLabel = new Text("A tempo:");
		accessoATempoLabel.setStyleAttribute("fontSize", "14px");

		accessoATempoField = new SimpleComboBox<String>();

		accessoATempoField.setForceSelection(true);
		accessoATempoField.setEmptyText("Si/No...");
		accessoATempoField.setTriggerAction(TriggerAction.ALL);
		accessoATempoField.setEditable(false);
		accessoATempoField.setFireChangeEventOnSetValue(true);
		accessoATempoField.add("Si");
		accessoATempoField.add("No");
		accessoATempoField.add("Non specificato");

		accessoATempoField.setSimpleValue("Non specificato");
		Utils.addListenerToChangeLabelColorIfModifiedSimpleComboboxString(accessoATempoField,accessoATempoLabel );


		accessoProxyLabel = new Text("Con proxy:");
		accessoProxyLabel.setStyleAttribute("fontSize", "14px");

		accessoConProxyField = new SimpleComboBox<String>();

		accessoConProxyField.setForceSelection(true);
		accessoConProxyField.setEmptyText("Si/No...");
		accessoConProxyField.setTriggerAction(TriggerAction.ALL);
		accessoConProxyField.setEditable(false);
		accessoConProxyField.setFireChangeEventOnSetValue(true);
		accessoConProxyField.add("Si");
		accessoConProxyField.add("No");
		accessoConProxyField.add("Non specificato");
		accessoConProxyField.setSimpleValue("Non specificato");
		Utils.addListenerToChangeLabelColorIfModifiedSimpleComboboxString(accessoConProxyField,accessoProxyLabel );

		accessoInternetTable.add(attivaAccessoLabel, d1);
		accessoInternetTable.add(attivaAccessoField, d1);
		accessoInternetTable.add(new LayoutContainer(), d1);
		accessoInternetTable.add(new LayoutContainer(), d1);
		accessoInternetTable.add(accessoApagamentoLabel, d1);
		accessoInternetTable.add(accessoATempoLabel, d1);
		accessoInternetTable.add(accessoProxyLabel, d1);
		accessoInternetTable.add(new LayoutContainer(), d1);
		accessoInternetTable.add(accessoAPagamentoField, d1);
		accessoInternetTable.add(accessoATempoField, d1);
		accessoInternetTable.add(accessoConProxyField, d1);

		accessoInternetAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(accessoInternetAggiorna);
		accessoInternetAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String,String> keys = new HashMap<String, String>(); 

							if (attivaAccessoField.getValue() != null) {
								if ("Si".equalsIgnoreCase(attivaAccessoField.getValue().getValue())) {
									keys.put("attivoAccesso", "true");

								} else if ("No".equalsIgnoreCase(attivaAccessoField.getValue().getValue())) {
									keys.put("attivoAccesso", "false");

								} else {/* Non specificato */
									keys.put("attivoAccesso", "null");
								}

							}

							keys.put("accessoPagamento", accessoAPagamentoField.getValue().getValue());
							keys.put("accessoTempo", accessoATempoField.getValue().getValue());
							keys.put("accessoProxy", accessoConProxyField.getValue().getValue());

							bibliotecheService.updateModalitaAccessoInternet(id_biblio, keys, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									attivaAccessoField.setOriginalValue(attivaAccessoField.getValue());
									accessoAPagamentoField.setOriginalValue(accessoAPagamentoField.getValue());
									accessoATempoField.setOriginalValue(accessoATempoField.getValue());
									accessoConProxyField.setOriginalValue(accessoConProxyField.getValue());

									Utils.setFontColorStyleBlack(attivaAccessoLabel);
									Utils.setFontColorStyleBlack(accessoApagamentoLabel);
									Utils.setFontColorStyleBlack(accessoATempoLabel);
									Utils.setFontColorStyleBlack(accessoProxyLabel);
									fireReleoadbiblioDataEvent();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										accessoInternetForm.reset();
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}
								}

							});
						} else {

						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});

		resetInternet = new Button("Reset");
		Utils.setStylesButton(resetInternet);
		resetInternet.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				accessoInternetForm.reset();
				Utils.setFontColorStyleBlack(attivaAccessoLabel);
				Utils.setFontColorStyleBlack(accessoApagamentoLabel);
				Utils.setFontColorStyleBlack(accessoATempoLabel);
				Utils.setFontColorStyleBlack(accessoProxyLabel);
			}
		});

		FormButtonBinding accessoInternetFormBinding = new FormButtonBinding(accessoInternetForm);
		accessoInternetFormBinding.addButton(accessoInternetAggiorna);
		accessoInternetFormBinding.addButton(resetInternet);

		TableLayout tableLayoutButtons = new TableLayout(2);
		tableLayoutButtons.setCellPadding(5);
		LayoutContainer aggiornaInternetButtons = new LayoutContainer(tableLayoutButtons);
		aggiornaInternetButtons.add(accessoInternetAggiorna);
		aggiornaInternetButtons.add(resetInternet);

		accessoInternetTable.add(aggiornaInternetButtons, d1);

		accessoInternetForm.add(accessoInternetTable);
		accessoInternetSet.add(accessoInternetForm);
		accessoInternet.add(accessoInternetSet);
		add(accessoInternet);
		/*FINE---ACCESSO INTERNET*/
		}

		public void setFieldsValues() {

			listaRiproduzioniFinitureDocumentiPanel.setIdBiblioteca(biblioteca.getIdBiblio());
			listaRiproduzioniFinitureDocumentiPanel.getLoader().load();

			if (biblioteca.getAttivoRiproduzioni() == null) {
				attivoRiproduzioniField.setSimpleValue("Non specificato");
				listaRiproduzioniFinitureDocumentiPanel.disable();

			} else if (biblioteca.getAttivoRiproduzioni().booleanValue() == true) {
//				attivoRiproduzioniField.setRawValue("Si");
				attivoRiproduzioniField.setSimpleValue("Si");
				listaRiproduzioniFinitureDocumentiPanel.enable();

			} else if (biblioteca.getAttivoRiproduzioni().booleanValue() == false) {
//				attivoRiproduzioniField.setRawValue("No");
				attivoRiproduzioniField.setSimpleValue("No");
				listaRiproduzioniFinitureDocumentiPanel.disable();

			}
//			attivoRiproduzioniField.setOriginalValue(attivoRiproduzioniField.getValue());
			Utils.setFontColorStyleBlack(attivoRiproduzioniLabel);
			UIWorkflow.setReadOnly(attivoRiproduzioniField);

			listaModalitaComunicazioniInformazioniBilbiografiche.setIdBiblioteca(biblioteca.getIdBiblio());
			listaModalitaComunicazioniInformazioniBilbiografiche.getLoader().load();

			listaSezioniSpecialiPanel.setIdBiblioteca(biblioteca.getIdBiblio());
			listaSezioniSpecialiPanel.getLoader().load();

			if (biblioteca.getAttivoInformazioniBibliografiche() == null) {
				attivoField.setRawValue("Non specificato");
				enableDisableInfoBibliograficheInSede(false);
				enableDisableInfoBibliograficheEsterne(false);

			} else if (biblioteca.getAttivoInformazioniBibliografiche().booleanValue() == true) {
				attivoField.setRawValue("Si");
				attivoField.setSimpleValue("Si");
				enableDisableInfoBibliograficheInSede(true);
				enableDisableInfoBibliograficheEsterne(true);

			} else if (biblioteca.getAttivoInformazioniBibliografiche().booleanValue() == false) {
				attivoField.setRawValue("No");
				attivoField.setSimpleValue("No");
				enableDisableInfoBibliograficheInSede(false);
				enableDisableInfoBibliograficheEsterne(false);
			}

			attivoField.setOriginalValue(attivoField.getValue());
			Utils.setFontColorStyleBlack(attivoLabel);
			UIWorkflow.setReadOnly(attivoField);

			if (biblioteca.getServizioBibliograficoInterno() != null) {
				servizioInSedeSI.setValue(biblioteca.getServizioBibliograficoInterno());
				servizioInSedeNO.setValue(!biblioteca.getServizioBibliograficoInterno());
				servizioInSedeNonSpecificato.setValue(false);
				servizioInSedeSI.setOriginalValue(biblioteca.getServizioBibliograficoInterno());
				servizioInSedeNO.setOriginalValue(!biblioteca.getServizioBibliograficoInterno());
				servizioInSedeNonSpecificato.setOriginalValue(false);

				rgservInSede.setOriginalValue(biblioteca.getServizioBibliograficoInterno()?servizioInSedeSI:servizioInSedeNO);
			}
			else {
				servizioInSedeSI.setValue(false);
				servizioInSedeNO.setValue(false);
				servizioInSedeNonSpecificato.setValue(true);
				servizioInSedeSI.setOriginalValue(false);
				servizioInSedeNO.setOriginalValue(false);
				servizioInSedeNonSpecificato.setOriginalValue(true);

				rgservInSede.setOriginalValue(servizioInSedeNonSpecificato);
			}
			Utils.setFontColorStyleBlack(servizioInSedeLabel);

			if (biblioteca.getServizioBibliograficoEsterno() != null) {
				servizioEsternoSI.setValue(biblioteca.getServizioBibliograficoEsterno());
				servizioEsternoNO.setValue(!biblioteca.getServizioBibliograficoEsterno());
				servizioEsternoNonSpecificato.setValue(false);
				servizioEsternoSI.setOriginalValue(biblioteca.getServizioBibliograficoEsterno());
				servizioEsternoNO.setOriginalValue(!biblioteca.getServizioBibliograficoEsterno());
				servizioEsternoNonSpecificato.setOriginalValue(false);

				rgservEsterno.setOriginalValue(biblioteca.getServizioBibliograficoEsterno()?servizioEsternoSI:servizioEsternoNO);
			}
			else {
				servizioEsternoSI.setValue(false);
				servizioEsternoNO.setValue(false);
				servizioEsternoNonSpecificato.setValue(true);
				servizioEsternoSI.setOriginalValue(false);
				servizioEsternoNO.setOriginalValue(false);
				servizioEsternoNonSpecificato.setOriginalValue(true);

				rgservEsterno.setOriginalValue(servizioEsternoNonSpecificato);
			}
			Utils.setFontColorStyleBlack(servizioEsternoLabel);

			if (biblioteca.getAttivoAccessoInternet() == null) {
				attivaAccessoField.setRawValue("Non specificato");
				accessoATempoField.disable();
				accessoAPagamentoField.disable();
				accessoConProxyField.disable();

			} else if (biblioteca.getAttivoAccessoInternet().booleanValue() == true) {
				attivaAccessoField.setRawValue("Si");
				attivaAccessoField.setSimpleValue("Si");
				accessoATempoField.enable();
				accessoAPagamentoField.enable();
				accessoConProxyField.enable();

			} else if (biblioteca.getAttivoAccessoInternet().booleanValue() == false) {
				attivaAccessoField.setRawValue("No");
				attivaAccessoField.setSimpleValue("No");
				accessoATempoField.disable();
				accessoAPagamentoField.disable();
				accessoConProxyField.disable();
			}

			attivaAccessoField.setOriginalValue(attivaAccessoField.getValue());
			Utils.setFontColorStyleBlack(attivaAccessoLabel);
			UIWorkflow.setReadOnly(attivaAccessoField);

			accessoAPagamentoField.setRawValue(biblioteca.getAccessoInternetAPagamento());
			accessoATempoField.setRawValue(biblioteca.getAccessoInternetATempo());
			accessoConProxyField.setRawValue(biblioteca.getAccessoInternetAProxy());

			if (biblioteca.getAccessoInternetAPagamento() != null) {
				if (biblioteca.getAccessoInternetAPagamento().equalsIgnoreCase("Non specificato")) {
					accessoAPagamentoField.setSimpleValue("Non specificato");			
				}
				else if (biblioteca.getAccessoInternetAPagamento().equalsIgnoreCase("Si")) {
					accessoAPagamentoField.setSimpleValue("Si");
				}
				else {
					accessoAPagamentoField.setSimpleValue("No");
				}
			}

			if (biblioteca.getAccessoInternetATempo() != null) {
				if (biblioteca.getAccessoInternetATempo().equalsIgnoreCase("Non specificato")) {
					accessoATempoField.setSimpleValue("Non specificato");
				}
				else if (biblioteca.getAccessoInternetATempo().equalsIgnoreCase("Si")) {
					accessoATempoField.setSimpleValue("Si");
				}
				else {
					accessoATempoField.setSimpleValue("No");
				}
			}

			if (biblioteca.getAccessoInternetAProxy() != null) {
				if (biblioteca.getAccessoInternetAProxy().equalsIgnoreCase("Non specificato")) {
					accessoConProxyField.setSimpleValue("Non specificato");
				}
				else if (biblioteca.getAccessoInternetAProxy().equalsIgnoreCase("Si")) {
					accessoConProxyField.setSimpleValue("Si");
				}
				else {
					accessoConProxyField.setSimpleValue("No");
				}
			}

			accessoAPagamentoField.setOriginalValue(accessoAPagamentoField.getValue());
			accessoATempoField.setOriginalValue(accessoATempoField.getValue());
			accessoConProxyField.setOriginalValue(accessoConProxyField.getValue());

			Utils.setFontColorStyleBlack(attivoLabel);

			Utils.setFontColorStyleBlack(accessoApagamentoLabel);
			Utils.setFontColorStyleBlack(accessoATempoLabel);
			Utils.setFontColorStyleBlack(accessoProxyLabel);

			UIWorkflow.setReadOnly(attivoField);

			UIWorkflow.enableDisable(servizioInSedeSI);
			UIWorkflow.enableDisable(servizioInSedeNO);
			UIWorkflow.enableDisable(servizioInSedeNonSpecificato);

			UIWorkflow.enableDisable(servizioEsternoSI);
			UIWorkflow.enableDisable(servizioEsternoNO);
			UIWorkflow.enableDisable(servizioEsternoNonSpecificato);

			UIWorkflow.setReadOnly(accessoAPagamentoField);
			UIWorkflow.setReadOnly(accessoATempoField);
			UIWorkflow.setReadOnly(accessoConProxyField);

			UIWorkflow.hideView(attivoRiproduzioniAggiorna);
			UIWorkflow.hideView(resetAttivoRiproduzioni);
			
			UIWorkflow.hideView(accessoInternetAggiorna);
			UIWorkflow.hideView(resetInternet);
			UIWorkflow.hideView(servizioInternoEsternoAggiorna);
			UIWorkflow.hideView(resetServizi);

			if (UIWorkflow.isReadOnly() == false) {
				addKeyListenerForEnter();

			} else {
				removeKeyListenerForEnter();
			}
		}

		public void setIdBiblio(int idBiblio) {
			this.id_biblio = idBiblio;
		}

		protected void addKeyListenerForEnter() {
			Utils.addKeyListenerForEnter(servizioInternoEsternoAggiorna, servizioInternoEsternoForm);
			Utils.addKeyListenerForEnter(accessoInternetAggiorna, accessoInternetForm);
		}

		protected void removeKeyListenerForEnter() {
			Utils.removeKeyListenerForEnter(servizioInternoEsternoForm);
			Utils.removeKeyListenerForEnter(accessoInternetForm);
		}

		protected void resetServizioBibliograficoInternoEsterno() {
			if (biblioteca != null) {
				/* Servizio bibliografico in sede */
				if (biblioteca.getServizioBibliograficoInterno() != null) {
					servizioInSedeSI.setValue(biblioteca.getServizioBibliograficoInterno());
					servizioInSedeNO.setValue(!biblioteca.getServizioBibliograficoInterno());
					servizioInSedeNonSpecificato.setValue(false);
					servizioInSedeSI.setOriginalValue(biblioteca.getServizioBibliograficoInterno());
					servizioInSedeNO.setOriginalValue(!biblioteca.getServizioBibliograficoInterno());
					servizioInSedeNonSpecificato.setOriginalValue(false);

					rgservInSede.setOriginalValue(biblioteca.getServizioBibliograficoInterno()?servizioInSedeSI:servizioInSedeNO);

					if (biblioteca.getServizioBibliograficoInterno().booleanValue()) {
						enableDisableInfoBibliograficheInSede(true);

					} else {
						enableDisableInfoBibliograficheInSede(false);
					}
				}
				else {
					servizioInSedeSI.setValue(false);
					servizioInSedeNO.setValue(false);
					servizioInSedeNonSpecificato.setValue(true);
					servizioInSedeSI.setOriginalValue(false);
					servizioInSedeNO.setOriginalValue(false);
					servizioInSedeNonSpecificato.setOriginalValue(true);

					rgservInSede.setOriginalValue(servizioInSedeNonSpecificato);
					enableDisableInfoBibliograficheInSede(false);
				}


				/* Servizio bibliografico esterno */
				if (biblioteca.getServizioBibliograficoEsterno() != null) {
					servizioEsternoSI.setValue(biblioteca.getServizioBibliograficoEsterno());
					servizioEsternoNO.setValue(!biblioteca.getServizioBibliograficoEsterno());
					servizioEsternoNonSpecificato.setValue(false);
					servizioEsternoSI.setOriginalValue(biblioteca.getServizioBibliograficoEsterno());
					servizioEsternoNO.setOriginalValue(!biblioteca.getServizioBibliograficoEsterno());
					servizioEsternoNonSpecificato.setOriginalValue(false);

					rgservEsterno.setOriginalValue(biblioteca.getServizioBibliograficoEsterno()?servizioEsternoSI:servizioEsternoNO);

					if (biblioteca.getServizioBibliograficoEsterno().booleanValue()) {
						enableDisableInfoBibliograficheEsterne(true);

					} else {
						enableDisableInfoBibliograficheEsterne(false);
					}
				}
				else {
					servizioEsternoSI.setValue(false);
					servizioEsternoNO.setValue(false);
					servizioEsternoNonSpecificato.setValue(true);
					servizioEsternoSI.setOriginalValue(false);
					servizioEsternoNO.setOriginalValue(false);
					servizioEsternoNonSpecificato.setOriginalValue(true);

					rgservEsterno.setOriginalValue(servizioEsternoNonSpecificato);

					enableDisableInfoBibliograficheEsterne(false);
				}
			}
		}

		protected void enableDisableInfoBibliograficheInSede(boolean isEnable) {
			if (isEnable) {/* Enable */
				servizioInSedeSI.enable();
				servizioInSedeNO.enable();
				servizioInSedeNonSpecificato.enable();
				rgservInSede.enable();

			} else {/* Disable */
				servizioInSedeSI.disable();
				servizioInSedeNO.disable();
				servizioInSedeNonSpecificato.disable();
				rgservInSede.disable();

			}
		}

		protected void enableDisableInfoBibliograficheEsterne(boolean isEnable) {
			if (isEnable) {/* Enable */
				servizioEsternoSI.enable();
				servizioEsternoNO.enable();
				servizioEsternoNonSpecificato.enable();
				rgservEsterno.enable();

			} else {/* Disable */
				servizioEsternoSI.disable();
				servizioEsternoNO.disable();
				servizioEsternoNonSpecificato.disable();
				rgservEsterno.disable();
			}
		}

	}

