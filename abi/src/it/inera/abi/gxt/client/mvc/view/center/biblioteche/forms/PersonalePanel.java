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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * al personale (iscritti, ingressi registrati, part-time, ecc...)
 *
 */
public class PersonalePanel extends ContentPanelForTabItem {  
	
	private int id_biblio;
	private NumberField personaleField;
	private NumberField partTimeField;
	private NumberField temporaneoField;
	private NumberField esternoField;
	private NumberField ingressiRegistraUltimi12MesiField;
	private NumberField iscrittiPrestitoUltimi12MesiField;
	// Tolto in seguito al ticket mantis : 4499
//	private NumberField utentiIscrittiField;
	private BibliotecheServiceAsync bibliotecheService;

	private Button personaleAggiorna;
	private Button resetPersonale;
	private Button utentiAggiorna;
	private Button resetUtenti;
	
	private Text personaleLabel;
	private Text partTimeLabel;
	private Text temporaneoLabel;
	private Text esternoLabel;
	private Text ingressiRegistratiUltimi12MesiLabel;
	private Text iscrittiPrestitoUltimi12MesiLabel;
	// Tolto in seguito al ticket mantis : 4499
//	private Text utentiIscrittiLabel;
	
	private FormPanel personaleForm;
	private FormPanel utentiForm;
	
	public PersonalePanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);
		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		/* PERSONALE */
		LayoutContainer personale = new LayoutContainer();
		personale.setStyleAttribute("padding", "5px");

		FieldSet personaleSet = new FieldSet();
		Utils.setFieldSetProperties(personaleSet, "Personale");
		personaleSet.setCollapsible(true);

		personaleForm = new FormPanel();
		personaleForm.setHeaderVisible(false);
		personaleForm.setBorders(false);
		personaleForm.setBodyBorder(false);

		FormLayout personaleFormLayout = new FormLayout();
		personaleFormLayout.setLabelAlign(LabelAlign.TOP);

		personaleForm.setLayout(personaleFormLayout);

		LayoutContainer personaleTable = new LayoutContainer(new TableLayout(2));

		TableData d = new TableData();
		d.setWidth("20%");
		d.setMargin(5);
		d.setPadding(10);

		TableData d2 = new TableData();
		d2.setWidth("30%");
		d2.setMargin(5);
		d2.setPadding(10);

		personaleLabel = new Text("Totale:");
		personaleLabel.setStyleAttribute("fontSize", "14px");

		personaleField = new NumberField();
		personaleField.setWidth(100);
		personaleField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(personaleField, personaleLabel);
		
		personaleTable.add(personaleLabel, d);
		personaleTable.add(personaleField, d2);

		Text diCuiLabel = new Text("di cui");
		diCuiLabel.setStyleAttribute("fontSize", "12px");

		personaleTable.add(diCuiLabel, d);
		personaleTable.add(new LayoutContainer(), d2);

		partTimeLabel = new Text("Personale part-time:");
		partTimeLabel.setStyleAttribute("fontSize", "14px");

		partTimeField = new NumberField();
		partTimeField.setWidth(100);
		partTimeField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(partTimeField, partTimeLabel);
		
		personaleTable.add(partTimeLabel, d);
		personaleTable.add(partTimeField, d2);

		temporaneoLabel = new Text("Personale temporaneo (obiettori, volontari, etc.):");
		temporaneoLabel.setStyleAttribute("fontSize", "14px");

		temporaneoField = new NumberField();
		temporaneoField.setWidth(100);
		temporaneoField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(temporaneoField, temporaneoLabel);

		personaleTable.add(temporaneoLabel, d);
		personaleTable.add(temporaneoField, d2);

		esternoLabel = new Text("Personale esterno:");
		esternoLabel.setStyleAttribute("fontSize", "14px");
		
		esternoField = new NumberField();
		esternoField.setWidth(100);
		esternoField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(esternoField, esternoLabel);
		
		personaleTable.add(esternoLabel, d);
		personaleTable.add(esternoField, d2);

		personaleAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(personaleAggiorna);

		personaleAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						ServiziRiproduzioniModel tmpServizio = new ServiziRiproduzioniModel();
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String,Object> personaleValues = new HashMap<String, Object>();
							
							if (personaleField.getValue() != null) {
								personaleValues.put("personaleTotale", personaleField.getValue().intValue());
							}
							else personaleValues.put("personaleTotale", null);
							
							if (partTimeField.getValue() != null) {
								personaleValues.put("personalePartTime", partTimeField.getValue().intValue());
							}
							else personaleValues.put("personalePartTime", null);
							
							if (esternoField.getValue() != null) {
								personaleValues.put("personaleEsterno", esternoField.getValue().intValue());
							}
							else personaleValues.put("personaleEsterno", null);
							
							if (temporaneoField.getValue() != null) {
								personaleValues.put("personaleTemp", temporaneoField.getValue().intValue());
							}
							else personaleValues.put("personaleTemp",null);
							
							bibliotecheService.setInfoPersonale(id_biblio,personaleValues,new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									personaleField.setOriginalValue(personaleField.getValue());
									partTimeField.setOriginalValue(partTimeField.getValue());
									esternoField.setOriginalValue(esternoField.getValue());
									temporaneoField.setOriginalValue(temporaneoField.getValue());
									Utils.setFontColorStyleBlack(personaleLabel);
									Utils.setFontColorStyleBlack(partTimeLabel);
									Utils.setFontColorStyleBlack(esternoLabel);
									Utils.setFontColorStyleBlack(temporaneoLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReloadBiblioDataEvent();
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

		resetPersonale = new Button("Reset");
		Utils.setStylesButton(resetPersonale);
		resetPersonale.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				personaleForm.reset();
				Utils.setFontColorStyleBlack(personaleLabel);
				Utils.setFontColorStyleBlack(partTimeLabel);
				Utils.setFontColorStyleBlack(esternoLabel);
				Utils.setFontColorStyleBlack(temporaneoLabel);				
			}
		});
		
		FormButtonBinding binding = new FormButtonBinding(personaleForm);
		binding.addButton(personaleAggiorna);
		binding.addButton(resetPersonale);
		
		TableLayout tableLayoutButtons = new TableLayout(2);
		tableLayoutButtons.setCellPadding(5);
		LayoutContainer personaleButtons = new LayoutContainer(tableLayoutButtons);
		personaleButtons.add(personaleAggiorna);
		personaleButtons.add(resetPersonale);
		
		personaleTable.add(new LayoutContainer(), d);
		personaleTable.add(personaleButtons, d2);

		personaleForm.add(personaleTable);
		personaleSet.add(personaleForm);
		personale.add(personaleSet);
		add(personale);
		/* FINE---PERSONALE */

		/* INFO SUPPLEMENTARI UTENTI */
		LayoutContainer utenti = new LayoutContainer();
		utenti.setStyleAttribute("padding", "5px");

		FieldSet utentiSet = new FieldSet();
		Utils.setFieldSetProperties(utentiSet, "Informazioni supplementari utenti");
		utentiSet.setCollapsible(true);

		utentiForm = new FormPanel();
		utentiForm.setHeaderVisible(false);
		utentiForm.setBorders(false);
		utentiForm.setBodyBorder(false);

		FormLayout utentiFormLayout = new FormLayout();
		utentiFormLayout.setLabelAlign(LabelAlign.TOP);

		utentiForm.setLayout(utentiFormLayout);

		LayoutContainer utentiTable = new LayoutContainer(new TableLayout(2));

		ingressiRegistratiUltimi12MesiLabel = new Text("Ingressi registrati ultimi 12 mesi:");
		ingressiRegistratiUltimi12MesiLabel.setStyleAttribute("fontSize", "14px");

		ingressiRegistraUltimi12MesiField = new NumberField();
		ingressiRegistraUltimi12MesiField.setWidth(100);
		ingressiRegistraUltimi12MesiField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(ingressiRegistraUltimi12MesiField, ingressiRegistratiUltimi12MesiLabel);

		utentiTable.add(ingressiRegistratiUltimi12MesiLabel, d);
		utentiTable.add(ingressiRegistraUltimi12MesiField, d2);

		iscrittiPrestitoUltimi12MesiLabel = new Text("Iscritti al prestito negli ultimi 12 mesi:");
		iscrittiPrestitoUltimi12MesiLabel.setStyleAttribute("fontSize", "14px");

		iscrittiPrestitoUltimi12MesiField = new NumberField();
		iscrittiPrestitoUltimi12MesiField.setWidth(100);
		iscrittiPrestitoUltimi12MesiField.setFormat(NumberFormat.getDecimalFormat());
		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(iscrittiPrestitoUltimi12MesiField, iscrittiPrestitoUltimi12MesiLabel);

		utentiTable.add(iscrittiPrestitoUltimi12MesiLabel, d);
		utentiTable.add(iscrittiPrestitoUltimi12MesiField, d2);

		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> INIZIO
//		utentiIscrittiLabel = new Text("Utenti iscritti:");
//		utentiIscrittiLabel.setStyleAttribute("fontSize", "14px");
//
//		utentiIscrittiField = new NumberField();
//		utentiIscrittiField.setWidth(100);
//		utentiIscrittiField.setFormat(NumberFormat.getDecimalFormat());
//		Utils.addListenerToChangeLabelColorIfModifiedNumberFieldInt(utentiIscrittiField, utentiIscrittiLabel);
//		
//		utentiTable.add(utentiIscrittiLabel, d);
//		utentiTable.add(utentiIscrittiField, d2);
		// Tolto in seguito al ticket mantis : 4499 -> FINE

		utentiAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(utentiAggiorna);
		
		utentiAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							HashMap<String,Object> utentiValues = new HashMap<String, Object>();
							if (ingressiRegistraUltimi12MesiField.getValue() != null) {	
								utentiValues.put("ingressiUltimi12Mesi", ingressiRegistraUltimi12MesiField.getValue().intValue());
							}
							else utentiValues.put("ingressiUltimi12Mesi", null);
							
							if (iscrittiPrestitoUltimi12MesiField.getValue() != null) {
								utentiValues.put("iscrittiPrestitoUltimi12Mesi", iscrittiPrestitoUltimi12MesiField.getValue().intValue());
							}
							else utentiValues.put("iscrittiPrestitoUltimi12Mesi", null);
							
							// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> INIZIO
//							if (utentiIscrittiField.getValue() != null) {
//								utentiValues.put("utentiIscritti", utentiIscrittiField.getValue().intValue());
//							}
//							else utentiValues.put("utentiIscritti", null);
							// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> FINE
							
							bibliotecheService.setInfoUtenti(id_biblio,utentiValues,new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									ingressiRegistraUltimi12MesiField.setOriginalValue(ingressiRegistraUltimi12MesiField.getValue());
									iscrittiPrestitoUltimi12MesiField.setOriginalValue(iscrittiPrestitoUltimi12MesiField.getValue());
									// TOLTO IN SEGUITO AL TICKET MANTIS : 4499
//									utentiIscrittiField.setOriginalValue(utentiIscrittiField.getValue());
									Utils.setFontColorStyleBlack(ingressiRegistratiUltimi12MesiLabel);
									Utils.setFontColorStyleBlack(iscrittiPrestitoUltimi12MesiLabel);
									// TOLTO IN SEGUITO AL TICKET MANTIS : 4499
//									Utils.setFontColorStyleBlack(utentiIscrittiLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReloadBiblioDataEvent();
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
		
		resetUtenti = new Button("Reset");
		Utils.setStylesButton(resetUtenti);
		
		resetUtenti.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				utentiForm.reset();
				Utils.setFontColorStyleBlack(ingressiRegistratiUltimi12MesiLabel);
				Utils.setFontColorStyleBlack(iscrittiPrestitoUltimi12MesiLabel);
				// TOLTO IN SEGUITO AL TICKET MANTIS : 4499
//				Utils.setFontColorStyleBlack(utentiIscrittiLabel);								
			}
		});

		FormButtonBinding utentiBinding = new FormButtonBinding(utentiForm);
		utentiBinding.addButton(utentiAggiorna);

		TableLayout tableLayoutUtenti = new TableLayout(2);
		tableLayoutUtenti.setCellPadding(5);
		LayoutContainer utentiButtons = new LayoutContainer(tableLayoutUtenti);
		utentiButtons.add(utentiAggiorna);
		utentiButtons.add(resetUtenti);
		
		utentiTable.add(new LayoutContainer(), d);
		utentiTable.add(utentiButtons, d2);

		utentiForm.add(utentiTable);
		utentiSet.add(utentiForm);
		utenti.add(utentiSet);
		add(utenti);
		/* FINE---INFO SUPPLEMENTARI UTENTI */
	}


	public void setFieldsValues() {
		personaleField.setValue(biblioteca.getPersonaleTotale());
		personaleField.setOriginalValue(biblioteca.getPersonaleTotale());
		Utils.setFontColorStyleBlack(personaleLabel);
		
		partTimeField.setValue(biblioteca.getPersonalePartTime());
		partTimeField.setOriginalValue(biblioteca.getPersonalePartTime());
		Utils.setFontColorStyleBlack(partTimeLabel);
		
		esternoField.setValue(biblioteca.getPersonaleEsterno());
		esternoField.setOriginalValue(biblioteca.getPersonaleEsterno());
		Utils.setFontColorStyleBlack(esternoLabel);
		
		temporaneoField.setValue(biblioteca.getPersonaleTemporaneo());
		temporaneoField.setOriginalValue(biblioteca.getPersonaleTemporaneo());
		Utils.setFontColorStyleBlack(temporaneoLabel);
		
		iscrittiPrestitoUltimi12MesiField.setValue(biblioteca.getIscrittiPrestitoUltimi12Mesi());
		iscrittiPrestitoUltimi12MesiField.setOriginalValue(biblioteca.getIscrittiPrestitoUltimi12Mesi());
		Utils.setFontColorStyleBlack(iscrittiPrestitoUltimi12MesiLabel);
		
		ingressiRegistraUltimi12MesiField.setValue(biblioteca.getIngressiUltimi12Mesi());
		ingressiRegistraUltimi12MesiField.setOriginalValue(biblioteca.getIngressiUltimi12Mesi());
		Utils.setFontColorStyleBlack(ingressiRegistratiUltimi12MesiLabel);
		
		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> INIZIO
//		utentiIscrittiField.setValue(biblioteca.getUtentiIscrittii());
//		utentiIscrittiField.setOriginalValue(biblioteca.getUtentiIscrittii());
//		Utils.setFontColorStyleBlack(utentiIscrittiLabel);
		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499 -> FINE

		UIWorkflow.setReadOnly(personaleField);
		UIWorkflow.setReadOnly(partTimeField);
		UIWorkflow.setReadOnly(esternoField);
		UIWorkflow.setReadOnly(temporaneoField);
		UIWorkflow.setReadOnly(iscrittiPrestitoUltimi12MesiField);
		UIWorkflow.setReadOnly(ingressiRegistraUltimi12MesiField);
		// TOLTO IN SEGUITO AL TICKET MANTIS : 4499
//		UIWorkflow.setReadOnly(utentiIscrittiField);

		UIWorkflow.hideView(personaleAggiorna);
		UIWorkflow.hideView(resetPersonale);
		UIWorkflow.hideView(utentiAggiorna);
		UIWorkflow.hideView(resetUtenti);
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
		Utils.addKeyListenerForEnter(personaleAggiorna, personaleForm);
		Utils.addKeyListenerForEnter(utentiAggiorna, utentiForm);
	}
	
	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( personaleForm);
		Utils.removeKeyListenerForEnter( utentiForm);		
	}
}
