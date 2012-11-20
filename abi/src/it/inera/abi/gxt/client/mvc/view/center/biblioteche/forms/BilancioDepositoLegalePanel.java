package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaDepositiLegaliPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * al bilancio (entrate e uscite) ed al deposito legale
 *
 */
public class BilancioDepositoLegalePanel extends ContentPanelForTabItem {
	
	private int id_biblio;
	private NumberField usciteTotaleField;
	private NumberField uscitePersonaleField;
	private NumberField usciteCorrentiFunzionamentoField;
	private NumberField usciteIncrementoPatrimonioField;
	private NumberField usciteAutomazioneField;
	private NumberField usciteAltreField;
	private NumberField entrateTotaliField;
	
	private Button bilancioAggiorna;
	private Button resetBilancio;
	
	private BibliotecheServiceAsync bibliotecheService;
	
	private Button attivoDepositoLegaleAggiorna;
	private Button resetAttivoDepositoLegale;
	
	private Text attivoDepositoLegaleLabel;
	private SimpleComboBox<String> attivoDepositoLegaleField;
	
	private ListaDepositiLegaliPanel listaDepositiLegaliPanel;
	
	private Text totaleUsciteLabel;
	private Text spesePersonaleLabel;
	private Text speseCorrentiFunzionamentoLabel;
	private Text speseIncrementoPatrimonioLabel;
	private Text speseAutomazioneLabel;
	private Text speseAltreLabel;
	private Text totEntrateLabel;

	private FormPanel bilancioForm;
	
	public BilancioDepositoLegalePanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);
		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		/* BILANCIO */
		LayoutContainer bilancio = new LayoutContainer();
		bilancio.setStyleAttribute("padding", "5px");

		FieldSet bilancioSet = new FieldSet();
		Utils.setFieldSetProperties(bilancioSet, "Bilancio");
		bilancioSet.setCollapsible(true);

		bilancioForm = new FormPanel();
		bilancioForm.setHeaderVisible(false);
		bilancioForm.setBorders(false);
		bilancioForm.setBodyBorder(false);

		FormLayout bilancioFormLayout = new FormLayout();
		bilancioFormLayout.setLabelAlign(LabelAlign.TOP);

		bilancioForm.setLayout(bilancioFormLayout);

		FormData data = new FormData();

		LayoutContainer bilancioTable = new LayoutContainer(new TableLayout(2));

		TableData d = new TableData();
		d.setWidth("20%");
		d.setMargin(5);
		d.setPadding(10);

		TableData d2 = new TableData();
		d2.setWidth("30%");
		d2.setMargin(5);
		d2.setPadding(10);

		totaleUsciteLabel = new Text("Totale uscite:");
		totaleUsciteLabel.setStyleAttribute("fontSize", "14px");

		usciteTotaleField = new NumberField();
		usciteTotaleField.setWidth(100);
		usciteTotaleField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(usciteTotaleField, totaleUsciteLabel);

		bilancioTable.add(totaleUsciteLabel, d);
		bilancioTable.add(usciteTotaleField, d2);

		Text diCuiLabel = new Text("di cui");
		diCuiLabel.setStyleAttribute("fontSize", "14px");

		bilancioTable.add(diCuiLabel, d);
		bilancioTable.add(new LayoutContainer(), d2);

		spesePersonaleLabel = new Text("Spese per il personale (se gestito dalla biblioteca):");
		spesePersonaleLabel.setStyleAttribute("fontSize", "14px");

		uscitePersonaleField = new NumberField();
		uscitePersonaleField.setWidth(100);
		uscitePersonaleField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(uscitePersonaleField, spesePersonaleLabel);
		
		bilancioTable.add(spesePersonaleLabel, d);
		bilancioTable.add(uscitePersonaleField, d2);

		speseCorrentiFunzionamentoLabel = new Text("Spese correnti per il funzionamento:");
		speseCorrentiFunzionamentoLabel.setStyleAttribute("fontSize", "14px");

		usciteCorrentiFunzionamentoField = new NumberField();
		usciteCorrentiFunzionamentoField.setWidth(100);
		usciteCorrentiFunzionamentoField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(usciteCorrentiFunzionamentoField, speseCorrentiFunzionamentoLabel);
		
		bilancioTable.add(speseCorrentiFunzionamentoLabel, d);
		bilancioTable.add(usciteCorrentiFunzionamentoField, d2);

		speseIncrementoPatrimonioLabel = new Text("Spese acquisto per incremento patrimonio librario e documentario:");
		speseIncrementoPatrimonioLabel.setStyleAttribute("fontSize", "14px");

		usciteIncrementoPatrimonioField = new NumberField();
		usciteIncrementoPatrimonioField.setWidth(100);
		usciteIncrementoPatrimonioField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(usciteIncrementoPatrimonioField, speseIncrementoPatrimonioLabel);
				
		bilancioTable.add(speseIncrementoPatrimonioLabel, d);
		bilancioTable.add(usciteIncrementoPatrimonioField, d2);

		speseAutomazioneLabel = new Text("Spese automazione:");
		speseAutomazioneLabel.setStyleAttribute("fontSize", "14px");

		usciteAutomazioneField = new NumberField();
		usciteAutomazioneField.setWidth(100);
		usciteAutomazioneField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(usciteAutomazioneField, speseAutomazioneLabel);
		
		bilancioTable.add(speseAutomazioneLabel, d);
		bilancioTable.add(usciteAutomazioneField, d2);

		speseAltreLabel = new Text("Altre spese:");
		speseAltreLabel.setStyleAttribute("fontSize", "14px");

		usciteAltreField = new NumberField();
		usciteAltreField.setWidth(100);
		usciteAltreField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(usciteAltreField, speseAltreLabel);
		
		bilancioTable.add(speseAltreLabel, d);
		bilancioTable.add(usciteAltreField, d2);

		totEntrateLabel = new Text("Totale entrate:");
		totEntrateLabel.setStyleAttribute("fontSize", "14px");

		entrateTotaliField = new NumberField();
		entrateTotaliField.setWidth(100);
		entrateTotaliField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(entrateTotaliField, totEntrateLabel);
		
		bilancioTable.add(totEntrateLabel, d);
		bilancioTable.add(entrateTotaliField, d2);

		bilancioAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(bilancioAggiorna);
		bilancioAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
			
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {						
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String,Object> bilancioValues = new HashMap<String, Object>();
							
							if (usciteTotaleField.getValue() != null) {	
								bilancioValues.put("usciteTotali", usciteTotaleField.getValue().intValue());
							}
							else bilancioValues.put("usciteTotali", null);
							
							if (usciteAltreField.getValue() != null) {	
								bilancioValues.put("usciteAltro", usciteAltreField.getValue().intValue());
							}
							else bilancioValues.put("usciteAltro", null);
							
							if (usciteAutomazioneField.getValue() != null) {	
								bilancioValues.put("usciteAutomazione", usciteAutomazioneField.getValue().intValue());
							}
							else bilancioValues.put("usciteAutomazione", null);
							
							if (usciteCorrentiFunzionamentoField.getValue() != null) {	
								bilancioValues.put("usciteFunzionamento", usciteCorrentiFunzionamentoField.getValue().intValue());
							}
							else bilancioValues.put("usciteFunzionamento", null);
							
							if (usciteIncrementoPatrimonioField.getValue() != null) {	
								bilancioValues.put("usciteIncrementoPatrimonio", usciteIncrementoPatrimonioField.getValue().intValue());
							}
							else bilancioValues.put("usciteIncrementoPatrimonio", null);
							
							if (uscitePersonaleField.getValue() != null) {	
								bilancioValues.put("uscitePersonale", uscitePersonaleField.getValue().intValue());
							}
							else bilancioValues.put("uscitePersonale", null);
							
							if (entrateTotaliField.getValue() != null) {	
								bilancioValues.put("entrateTotali", entrateTotaliField.getValue().intValue());
							}
							else bilancioValues.put("entrateTotali", null);
							
							bibliotecheService.setInfoBilancio(id_biblio,bilancioValues,new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											usciteTotaleField.setOriginalValue(usciteTotaleField.getValue());
											uscitePersonaleField.setOriginalValue(uscitePersonaleField.getValue());
											usciteCorrentiFunzionamentoField.setOriginalValue(usciteCorrentiFunzionamentoField.getValue());
											usciteIncrementoPatrimonioField.setOriginalValue(usciteIncrementoPatrimonioField.getValue());
											usciteAutomazioneField.setOriginalValue(usciteCorrentiFunzionamentoField.getValue());
											usciteAltreField.setOriginalValue(usciteAltreField.getValue());
											entrateTotaliField.setOriginalValue(entrateTotaliField.getValue());											
											Utils.setFontColorStyleBlack(totaleUsciteLabel);
											Utils.setFontColorStyleBlack(spesePersonaleLabel);
											Utils.setFontColorStyleBlack(speseCorrentiFunzionamentoLabel);
											Utils.setFontColorStyleBlack(speseIncrementoPatrimonioLabel);
											Utils.setFontColorStyleBlack(speseAutomazioneLabel);
											Utils.setFontColorStyleBlack(speseAltreLabel);
											Utils.setFontColorStyleBlack(totEntrateLabel);
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
		
		resetBilancio = new Button("Reset");
		Utils.setStylesButton(resetBilancio);
		resetBilancio.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bilancioForm.reset();
				Utils.setFontColorStyleBlack(totaleUsciteLabel);
				Utils.setFontColorStyleBlack(spesePersonaleLabel);
				Utils.setFontColorStyleBlack(speseCorrentiFunzionamentoLabel);
				Utils.setFontColorStyleBlack(speseIncrementoPatrimonioLabel);
				Utils.setFontColorStyleBlack(speseAutomazioneLabel);
				Utils.setFontColorStyleBlack(speseAltreLabel);
				Utils.setFontColorStyleBlack(totEntrateLabel);				
			}
		});
		
		FormButtonBinding bilancioBinding = new FormButtonBinding(bilancioForm);
		bilancioBinding.addButton(bilancioAggiorna);
		bilancioBinding.addButton(resetBilancio);
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(bilancioAggiorna);
		buttons.add(resetBilancio);
		
		bilancioTable.add(new LayoutContainer(), d);
		bilancioTable.add(buttons, d2);

		bilancioForm.add(bilancioTable);
		bilancioSet.add(bilancioForm);
		bilancio.add(bilancioSet);
		add(bilancio);
		/* FINE---BILANCIO */
		/* DEPOSITO LEGALE */
		LayoutContainer depositoLegale = new LayoutContainer();
		depositoLegale.setStyleAttribute("padding", "5px");

		FieldSet depositoLegaleSet = new FieldSet();
		Utils.setFieldSetProperties(depositoLegaleSet, "Deposito legale");
		depositoLegaleSet.setCollapsible(true);

		LayoutContainer attivaDepositoLegaleTable = new LayoutContainer(new TableLayout(3));
		attivaDepositoLegaleTable.setWidth(500);

		attivoDepositoLegaleLabel = new Text("Attiva deposito legale:");
		attivoDepositoLegaleLabel.setStyleAttribute("fontSize", "14px");
		attivaDepositoLegaleTable.add(attivoDepositoLegaleLabel, d);

		attivoDepositoLegaleField = new SimpleComboBox<String>();
		attivoDepositoLegaleField.setTriggerAction(TriggerAction.ALL);
		attivoDepositoLegaleField.setEditable(false);
		attivoDepositoLegaleField.setFireChangeEventOnSetValue(true);
		attivoDepositoLegaleField.setWidth(200);
		attivoDepositoLegaleField.add("Si");
		attivoDepositoLegaleField.add("No");
		attivoDepositoLegaleField.add("Non specificato");
		attivoDepositoLegaleField.setSimpleValue("Non specificato");
		
		attivoDepositoLegaleField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				Utils.setFontColorStyleRed(attivoDepositoLegaleLabel);

				if ("Si".equals(se.getSelectedItem().getValue())) {
					listaDepositiLegaliPanel.enable();

				} else if ("No".equals(se.getSelectedItem().getValue())) {
					listaDepositiLegaliPanel.disable();

				} else {/* Non specificato */
					listaDepositiLegaliPanel.disable();
				}

			}
		});

		attivaDepositoLegaleTable.add(attivoDepositoLegaleField, d);
		
		attivoDepositoLegaleAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(attivoDepositoLegaleAggiorna);
		attivoDepositoLegaleAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							Boolean hasAttivoDepositoLegale = null;

							if (attivoDepositoLegaleField.getValue() != null) {
								if ("Si".equals(attivoDepositoLegaleField.getValue().getValue())) {
									hasAttivoDepositoLegale = true;

								} else if ("No".equals(attivoDepositoLegaleField.getValue().getValue())) {
									hasAttivoDepositoLegale = false;

								} else {/* Non specificato */
									hasAttivoDepositoLegale = null;
								}

								attivoDepositoLegaleField.setSimpleValue(attivoDepositoLegaleField.getValue().getValue());
							}

							bibliotecheService.setAttivoDepositoLegale(id_biblio, hasAttivoDepositoLegale, new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									Utils.setFontColorStyleBlack(attivoDepositoLegaleLabel);

									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									listaDepositiLegaliPanel.getLoader().load();
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
		
		resetAttivoDepositoLegale = new Button("Reset");
		Utils.setStylesButton(resetAttivoDepositoLegale);
		resetAttivoDepositoLegale.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (biblioteca.getAttivoDepositoLegale() == null) {
					attivoDepositoLegaleField.setSimpleValue("Non specificato");

				} else {
					if (biblioteca.getAttivoDepositoLegale().booleanValue() == true) {
						attivoDepositoLegaleField.setSimpleValue("Si");

					} else if  (biblioteca.getAttivoDepositoLegale().booleanValue() == false) {
						attivoDepositoLegaleField.setSimpleValue("No");
					}
				} 

				Utils.setFontColorStyleBlack(attivoDepositoLegaleLabel);

			}
		});

		TableLayout deplegButtonsTableLayout = new TableLayout(2);
		deplegButtonsTableLayout.setCellPadding(5);
		LayoutContainer deplegButtons = new LayoutContainer(deplegButtonsTableLayout);
		deplegButtons.add(attivoDepositoLegaleAggiorna);
		deplegButtons.add(resetAttivoDepositoLegale);

		attivaDepositoLegaleTable.add(deplegButtons, d);
		
		depositoLegaleSet.add(attivaDepositoLegaleTable);
		
		listaDepositiLegaliPanel = new ListaDepositiLegaliPanel();
		listaDepositiLegaliPanel.setGrid();

		depositoLegaleSet.add(listaDepositiLegaliPanel);
		depositoLegale.add(depositoLegaleSet);
		add(depositoLegale);
		/* FINE---DEPOSITO LEGALE */
	}
	
	public void setFieldsValues() {
		/* Deposito Legale */
		listaDepositiLegaliPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaDepositiLegaliPanel.getLoader().load();
		
		if (biblioteca.getAttivoDepositoLegale() == null) {
			attivoDepositoLegaleField.setSimpleValue("Non specificato");
			listaDepositiLegaliPanel.disable();

		} else if (biblioteca.getAttivoDepositoLegale().booleanValue() == true) {
			attivoDepositoLegaleField.setSimpleValue("Si");
			listaDepositiLegaliPanel.enable();

		} else if (biblioteca.getAttivoDepositoLegale().booleanValue() == false) {
			attivoDepositoLegaleField.setSimpleValue("No");
			listaDepositiLegaliPanel.disable();

		}
		Utils.setFontColorStyleBlack(attivoDepositoLegaleLabel);
		UIWorkflow.setReadOnly(attivoDepositoLegaleField);
		
		UIWorkflow.hideView(attivoDepositoLegaleAggiorna);
		UIWorkflow.hideView(resetAttivoDepositoLegale);
		/* FINE -> Deposito Legale */
		
		usciteTotaleField.setValue(biblioteca.getUsciteTotali());
		usciteTotaleField.setOriginalValue(biblioteca.getUsciteTotali());
		Utils.setFontColorStyleBlack(totaleUsciteLabel);
		
		usciteAltreField.setValue(biblioteca.getUsciteAltro());
		usciteAltreField.setOriginalValue(biblioteca.getUsciteAltro());
		Utils.setFontColorStyleBlack(speseAltreLabel);
				
		usciteAutomazioneField.setValue(biblioteca.getUsciteAutomazione());
		usciteAutomazioneField.setOriginalValue(biblioteca.getUsciteAutomazione());
		Utils.setFontColorStyleBlack(speseAutomazioneLabel);
				
		usciteCorrentiFunzionamentoField.setValue(biblioteca.getUsciteFunzionamento());
		usciteCorrentiFunzionamentoField.setOriginalValue(biblioteca.getUsciteFunzionamento());
		Utils.setFontColorStyleBlack(speseCorrentiFunzionamentoLabel);
				
		usciteIncrementoPatrimonioField.setValue(biblioteca.getUsciteAcquistoPatrimonio());
		usciteIncrementoPatrimonioField.setOriginalValue(biblioteca.getUsciteAcquistoPatrimonio());
		Utils.setFontColorStyleBlack(speseIncrementoPatrimonioLabel);
				
		uscitePersonaleField.setValue(biblioteca.getUscitePersonale());
		uscitePersonaleField.setOriginalValue(biblioteca.getUscitePersonale());
		Utils.setFontColorStyleBlack(spesePersonaleLabel);
				
		entrateTotaliField.setValue(biblioteca.getEntrateTotali());	
		entrateTotaliField.setOriginalValue(biblioteca.getEntrateTotali());
		Utils.setFontColorStyleBlack(totEntrateLabel);
		
		UIWorkflow.setReadOnly(usciteTotaleField);
		UIWorkflow.setReadOnly(usciteAltreField);
		UIWorkflow.setReadOnly(usciteAutomazioneField);
		UIWorkflow.setReadOnly(usciteCorrentiFunzionamentoField);
		UIWorkflow.setReadOnly(usciteIncrementoPatrimonioField);
		UIWorkflow.setReadOnly(uscitePersonaleField);
		UIWorkflow.setReadOnly(entrateTotaliField);
		UIWorkflow.hideView(bilancioAggiorna);
		UIWorkflow.hideView(resetBilancio);
		
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
		Utils.addKeyListenerForEnter(bilancioAggiorna, bilancioForm);
	}
	
	protected void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter(bilancioForm);
	}

}
