package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.model.ServiziRiproduzioniModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPrestitoInterbibliotecarioRuoloBiliotecaPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPrestitoLocalePanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

public class PrestitoPanel extends ContentPanelForTabItem {

	private int id_biblio;
	private ListaPrestitoLocalePanel listaPrestitiLocaliPanel;
	private ListaPrestitoInterbibliotecarioRuoloBiliotecaPanel listaPrestitoInterbibliotecarioRuoloBiliotecaPanel;
	private ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel listaSistemiPrestitoInterbibliotecarioPanel;

	private Button prestitoAggiorna;
	private Button resetPrestiti;
	
	private RadioGroup rgPrestNaz;
	private Radio prestitoNazionaleSI;
	private Radio prestitoNazionaleNO;
	private Radio prestitoNazionaleNonSpecificato;
	
	private RadioGroup rgPrestInternaz;
	private Radio prestitoInternazionaleSI;
	private Radio prestitoInternazionaleNO;
	private Radio prestitoInternazionaleNonSpecificato;
	
	private RadioGroup rgProcedure;
	private Radio procedureAutoSI;
	private Radio procedureAutoNO;
	private Radio procedureAutoNonSpecificato;

	private	TableData d;
	private	TableData d2;

	private BibliotecheServiceAsync bibliotecheService;
	
	private Text prestitoNazionaleLabel;
	private Text prestitoInternazionaleLabel;
	private Text procedureAutomatizzateLabel;
	
	private FormPanel prestitoNazionaleInternazionaleForm;
	public PrestitoPanel() {
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setBodyBorder(false);

		bibliotecheService= Registry.get(Abi.BIBLIOTECHE_SERVICE);

		d = new TableData();
		d.setWidth("20%");
		d.setMargin(5);
		d.setPadding(10);

		d2 = new TableData();
		d2.setWidth("20%");
		d2.setMargin(5);
		d2.setPadding(10);

		/* PRESTITO LOCALE MATERIALE ESCLUSO */
		LayoutContainer sistemiIndicizzaizione = new LayoutContainer();
		sistemiIndicizzaizione.setStyleAttribute("padding", "5px");

		FieldSet materialeEsclusoSet = new FieldSet();
		Utils.setFieldSetProperties(materialeEsclusoSet, "Prestito Locale");
		materialeEsclusoSet.setCollapsible(true);

		listaPrestitiLocaliPanel = new ListaPrestitoLocalePanel();
		listaPrestitiLocaliPanel.setGrid();
		materialeEsclusoSet.add(listaPrestitiLocaliPanel);

		sistemiIndicizzaizione.add(materialeEsclusoSet);
		add(sistemiIndicizzaizione);
		/* FINE---PRESTITO LOCALE */

		/* PRESTITO INTERBIBLIOTECARIO */
		LayoutContainer prestitoInterbibliotecario = new LayoutContainer();
		prestitoInterbibliotecario.setStyleAttribute("padding", "5px");

		FieldSet prestitoInterbibliotecarioSet = new FieldSet();
		Utils.setFieldSetProperties(prestitoInterbibliotecarioSet, "Prestito interbibliotecario");
		prestitoInterbibliotecarioSet.setCollapsible(true);

		/*Form prestito nazionale/internazionale procedure auto*/
		prestitoNazionaleInternazionaleForm = new FormPanel();
		Utils.setFormStyleProperties(prestitoNazionaleInternazionaleForm);
		prestitoNazionaleInternazionaleForm.setWidth("auto");
		prestitoNazionaleInternazionaleForm.setWidth(750);
		FormLayout prestitoNazionaleInternazionaleFormLayout = new FormLayout();
		prestitoNazionaleInternazionaleFormLayout.setLabelAlign(LabelAlign.TOP);

		prestitoNazionaleInternazionaleForm.setLayout(prestitoNazionaleInternazionaleFormLayout);
		
		LayoutContainer prestitoNazionaleInternazionaleTable = new LayoutContainer(	new TableLayout(2));
		prestitoNazionaleInternazionaleTable.setWidth(500);

		prestitoNazionaleLabel = new Text("Prestito nazionale:");
		prestitoNazionaleLabel.setStyleAttribute("fontSize", "14px");
		prestitoNazionaleInternazionaleTable.add(prestitoNazionaleLabel, d2);

		LayoutContainer prestitoNazionaleRadioButton = new LayoutContainer(new FlowLayout());
		prestitoNazionaleRadioButton.setAutoWidth(true);

		rgPrestNaz = new RadioGroup("prestitoNazionale");
		rgPrestNaz.setStyleAttribute("position", "static");

		prestitoNazionaleSI = new Radio();
		prestitoNazionaleSI.setBoxLabel("SI");

		prestitoNazionaleNO = new Radio();
		prestitoNazionaleNO.setBoxLabel("NO");

		prestitoNazionaleNonSpecificato = new Radio();
		prestitoNazionaleNonSpecificato.setBoxLabel("Non specificato");

		rgPrestNaz.add(prestitoNazionaleSI);
		rgPrestNaz.add(prestitoNazionaleNO);
		rgPrestNaz.add(prestitoNazionaleNonSpecificato);
		
		prestitoNazionaleRadioButton.add(rgPrestNaz);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgPrestNaz, prestitoNazionaleLabel);
				
		prestitoNazionaleInternazionaleTable.add(prestitoNazionaleRadioButton, d);

		prestitoInternazionaleLabel = new Text("Prestito internazionale:");
		prestitoInternazionaleLabel.setStyleAttribute("fontSize", "14px");

		prestitoNazionaleInternazionaleTable.add(prestitoInternazionaleLabel, d2);

		LayoutContainer prestitoInternazionaleRadioButton = new LayoutContainer(new FlowLayout());
		prestitoInternazionaleRadioButton.setAutoWidth(true);

		rgPrestInternaz = new RadioGroup("prestitoInternazionale");
		rgPrestInternaz.setStyleAttribute("position", "static");

		prestitoInternazionaleSI = new Radio();
		prestitoInternazionaleSI.setBoxLabel("SI");

		prestitoInternazionaleNO = new Radio();
		prestitoInternazionaleNO.setBoxLabel("NO");

		prestitoInternazionaleNonSpecificato = new Radio();
		prestitoInternazionaleNonSpecificato.setBoxLabel("Non specificato");

		rgPrestInternaz.add(prestitoInternazionaleSI);
		rgPrestInternaz.add(prestitoInternazionaleNO);
		rgPrestInternaz.add(prestitoInternazionaleNonSpecificato);
		
		prestitoInternazionaleRadioButton.add(rgPrestInternaz);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgPrestInternaz, prestitoInternazionaleLabel);
		
		prestitoNazionaleInternazionaleTable.add(prestitoInternazionaleRadioButton, d);

		procedureAutomatizzateLabel = new Text("Procedure automatizzate:");
		procedureAutomatizzateLabel.setStyleAttribute("fontSize", "14px");
		prestitoNazionaleInternazionaleTable.add(procedureAutomatizzateLabel, d2);

		LayoutContainer procedureAutomatizzateRadioButton = new LayoutContainer(new FlowLayout());
		procedureAutomatizzateRadioButton.setAutoWidth(true);

		rgProcedure = new RadioGroup("procedureAuto");
		rgProcedure.setStyleAttribute("position", "static");

		procedureAutoSI = new Radio();
		procedureAutoSI.setBoxLabel("SI");

		procedureAutoNO = new Radio();
		procedureAutoNO.setBoxLabel("NO");

		procedureAutoNonSpecificato = new Radio();
		procedureAutoNonSpecificato.setBoxLabel("Non specificato");

		rgProcedure.add(procedureAutoSI);
		rgProcedure.add(procedureAutoNO);
		rgProcedure.add(procedureAutoNonSpecificato);

		procedureAutomatizzateRadioButton.add(rgProcedure);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgProcedure, procedureAutomatizzateLabel);
		
		prestitoNazionaleInternazionaleTable.add(procedureAutomatizzateRadioButton, d);

		prestitoAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(prestitoAggiorna);
		prestitoAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							Boolean hasPrestitoNazionale=null;
							Boolean hasPrestitoInternazionale=null;
							Boolean hasProcedureAutomatizzate=null;

							if (prestitoNazionaleNonSpecificato.getValue() != true) {
								hasPrestitoNazionale = prestitoNazionaleSI.getValue();
							} 
							else hasPrestitoNazionale = null;

							if (prestitoInternazionaleNonSpecificato.getValue() != true) {
								hasPrestitoInternazionale = prestitoInternazionaleSI.getValue();
							} 
							else hasPrestitoInternazionale = null;

							if (procedureAutoNonSpecificato.getValue() != true) {
								hasProcedureAutomatizzate = procedureAutoSI.getValue();
							}
							else hasProcedureAutomatizzate = null;

							bibliotecheService.setPrestitoInterbibliotecareNazionaleInternazionaleAutomatizzato(id_biblio,	hasPrestitoNazionale,	hasPrestitoInternazionale,	hasProcedureAutomatizzate, new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									if (prestitoNazionaleNonSpecificato.getValue() == false) {
										prestitoNazionaleSI.setOriginalValue(prestitoNazionaleSI.getValue());
										prestitoNazionaleNO.setOriginalValue(prestitoNazionaleNO.getValue());
										prestitoNazionaleNonSpecificato.setOriginalValue(prestitoNazionaleNonSpecificato.getValue());
										
										rgPrestNaz.setOriginalValue(prestitoNazionaleSI.getValue()?prestitoNazionaleSI:prestitoNazionaleNO);
									}
									else {
										prestitoNazionaleSI.setOriginalValue(false);
										prestitoNazionaleNO.setOriginalValue(false);
										prestitoNazionaleNonSpecificato.setOriginalValue(true);
										
										rgPrestNaz.setOriginalValue(prestitoNazionaleNonSpecificato);
									}
									
									if (prestitoInternazionaleNonSpecificato.getValue() == false) {
										prestitoInternazionaleSI.setOriginalValue(prestitoInternazionaleSI.getValue());
										prestitoInternazionaleNO.setOriginalValue(prestitoInternazionaleNO.getValue());
										prestitoInternazionaleNonSpecificato.setOriginalValue(prestitoInternazionaleNonSpecificato.getValue());
										
										rgPrestInternaz.setOriginalValue(prestitoInternazionaleSI.getValue()?prestitoInternazionaleSI:prestitoInternazionaleNO);
									}
									else {
										prestitoInternazionaleSI.setOriginalValue(false);
										prestitoInternazionaleNO.setOriginalValue(false);
										prestitoInternazionaleNonSpecificato.setOriginalValue(true);
										
										rgPrestInternaz.setOriginalValue(prestitoInternazionaleNonSpecificato);										
									}
									
									if (procedureAutoNonSpecificato.getValue() == false) {
										procedureAutoSI.setOriginalValue(procedureAutoSI.getValue());
										procedureAutoNO.setOriginalValue(procedureAutoNO.getValue());
										procedureAutoNonSpecificato.setOriginalValue(procedureAutoNonSpecificato.getValue());
										
										rgProcedure.setOriginalValue(procedureAutoSI.getValue()?procedureAutoSI:procedureAutoNO);
									}
									else {
										procedureAutoSI.setOriginalValue(false);
										procedureAutoNO.setOriginalValue(false);
										procedureAutoNonSpecificato.setOriginalValue(true);
										
										rgProcedure.setOriginalValue(procedureAutoNonSpecificato);
									}
									
									Utils.setFontColorStyleBlack(prestitoNazionaleLabel);
									Utils.setFontColorStyleBlack(prestitoInternazionaleLabel);
									Utils.setFontColorStyleBlack(procedureAutomatizzateLabel);
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
		
		resetPrestiti = new Button("Reset");
		Utils.setStylesButton(resetPrestiti);
		resetPrestiti.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				prestitoNazionaleInternazionaleForm.reset();
				Utils.setFontColorStyleBlack(prestitoNazionaleLabel);
				Utils.setFontColorStyleBlack(prestitoInternazionaleLabel);
				Utils.setFontColorStyleBlack(procedureAutomatizzateLabel);
			}
		});
		
		FormButtonBinding prestitoNazionaleInternazionaleBind = new FormButtonBinding(
				prestitoNazionaleInternazionaleForm);
		prestitoNazionaleInternazionaleBind.addButton(prestitoAggiorna);
		prestitoNazionaleInternazionaleBind.addButton(resetPrestiti);
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(prestitoAggiorna);
		buttons.add(resetPrestiti);

		prestitoNazionaleInternazionaleTable.add(new LayoutContainer(), d2);
		prestitoNazionaleInternazionaleTable.add(buttons, d);

		prestitoNazionaleInternazionaleForm.add(prestitoNazionaleInternazionaleTable);
		prestitoInterbibliotecarioSet.add(prestitoNazionaleInternazionaleForm);
		/*FINE--Form servizio nazionale/internazionale procedure auto*/

		listaPrestitoInterbibliotecarioRuoloBiliotecaPanel = new ListaPrestitoInterbibliotecarioRuoloBiliotecaPanel();
		listaPrestitoInterbibliotecarioRuoloBiliotecaPanel.setGrid();
		prestitoInterbibliotecarioSet.add(listaPrestitoInterbibliotecarioRuoloBiliotecaPanel);

		prestitoInterbibliotecario.add(prestitoInterbibliotecarioSet);
		add(prestitoInterbibliotecario);
		/* FINE---PRESTITO INTERBIBLIOTECARIO */

		/* SISTEMI PRESTITO INTERBIBLIOTECARIO */
		LayoutContainer sistemiPrestitoInterbibliotecario = new LayoutContainer();
		sistemiPrestitoInterbibliotecario.setStyleAttribute("padding", "5px");

		FieldSet sistemiPrestitoInterbibliotecarioSet = new FieldSet();
		Utils.setFieldSetProperties(sistemiPrestitoInterbibliotecarioSet, "Sistemi di prestito interbibliotecario");
		sistemiPrestitoInterbibliotecarioSet.setCollapsible(true);

		listaSistemiPrestitoInterbibliotecarioPanel = new ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel(CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_SISTEMI_INDEX, CostantiTabelleDinamiche.PRESTITO_INTERBIBLIOTECARIO_SISTEMI);
		listaSistemiPrestitoInterbibliotecarioPanel.setGrid();
		sistemiPrestitoInterbibliotecarioSet.add(listaSistemiPrestitoInterbibliotecarioPanel);

		sistemiPrestitoInterbibliotecario.add(sistemiPrestitoInterbibliotecarioSet);
		add(sistemiPrestitoInterbibliotecario);
		/* FINE---SISTEMI PRESTITO INTERBIBLIOTECARIO */

	}
	public void setFieldsValues() {
		listaPrestitiLocaliPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaPrestitiLocaliPanel.getLoader().load();

		listaPrestitoInterbibliotecarioRuoloBiliotecaPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaPrestitoInterbibliotecarioRuoloBiliotecaPanel.getLoader().load();

		listaSistemiPrestitoInterbibliotecarioPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaSistemiPrestitoInterbibliotecarioPanel.getLoader().load();

		if (biblioteca.getPrestitoNazionale() != null) {
			prestitoNazionaleSI.setValue(biblioteca.getPrestitoNazionale());
			prestitoNazionaleNO.setValue(!biblioteca.getPrestitoNazionale());
			prestitoNazionaleNonSpecificato.setValue(false);
			prestitoNazionaleSI.setOriginalValue(biblioteca.getPrestitoNazionale());
			prestitoNazionaleNO.setOriginalValue(!biblioteca.getPrestitoNazionale());
			prestitoNazionaleNonSpecificato.setOriginalValue(false);
			
			rgPrestNaz.setOriginalValue(biblioteca.getPrestitoNazionale()?prestitoNazionaleSI:prestitoNazionaleNO);
		}
		else {
			prestitoNazionaleSI.setValue(false);
			prestitoNazionaleNO.setValue(false);
			prestitoNazionaleNonSpecificato.setValue(true);
			prestitoNazionaleSI.setOriginalValue(false);
			prestitoNazionaleNO.setOriginalValue(false);
			prestitoNazionaleNonSpecificato.setOriginalValue(true);
			
			rgPrestNaz.setOriginalValue(prestitoNazionaleNonSpecificato);
		}
		
		if (biblioteca.getPrestitoInternazionale() != null) {
			prestitoInternazionaleSI.setValue(biblioteca.getPrestitoInternazionale());
			prestitoInternazionaleNO.setValue(!biblioteca.getPrestitoInternazionale());
			prestitoInternazionaleNonSpecificato.setValue(false);
			prestitoInternazionaleSI.setOriginalValue(biblioteca.getPrestitoInternazionale());
			prestitoInternazionaleNO.setOriginalValue(!biblioteca.getPrestitoInternazionale());
			prestitoInternazionaleNonSpecificato.setOriginalValue(false);
			
			rgPrestInternaz.setOriginalValue(biblioteca.getPrestitoInternazionale()?prestitoInternazionaleSI:prestitoInternazionaleNO);
		}
		else {
			prestitoInternazionaleSI.setValue(false);
			prestitoInternazionaleNO.setValue(false);
			prestitoInternazionaleNonSpecificato.setValue(true);
			prestitoInternazionaleSI.setOriginalValue(false);
			prestitoInternazionaleNO.setOriginalValue(false);
			prestitoInternazionaleNonSpecificato.setOriginalValue(true);
			
			rgPrestInternaz.setOriginalValue(prestitoInternazionaleNonSpecificato);	
		}
		
		if (biblioteca.getProcedureAutomatizzate() != null) {
			procedureAutoSI.setValue(biblioteca.getProcedureAutomatizzate());
			procedureAutoNO.setValue(!biblioteca.getProcedureAutomatizzate());
			procedureAutoNonSpecificato.setValue(false);
			procedureAutoSI.setOriginalValue(biblioteca.getProcedureAutomatizzate());
			procedureAutoNO.setOriginalValue(!biblioteca.getProcedureAutomatizzate());
			procedureAutoNonSpecificato.setOriginalValue(false);
			
			rgProcedure.setOriginalValue(biblioteca.getProcedureAutomatizzate()?procedureAutoSI:procedureAutoNO);
		}
		else {
			procedureAutoSI.setValue(false);
			procedureAutoNO.setValue(false);
			procedureAutoNonSpecificato.setValue(true);
			procedureAutoSI.setOriginalValue(false);
			procedureAutoNO.setOriginalValue(false);
			procedureAutoNonSpecificato.setOriginalValue(true);
			
			rgProcedure.setOriginalValue(procedureAutoNonSpecificato);

		}
		Utils.setFontColorStyleBlack(prestitoNazionaleLabel);
		Utils.setFontColorStyleBlack(prestitoInternazionaleLabel);
		Utils.setFontColorStyleBlack(procedureAutomatizzateLabel);

		UIWorkflow.hideView(prestitoAggiorna);
		UIWorkflow.hideView(resetPrestiti);
		UIWorkflow.enableDisable(prestitoNazionaleSI);
		UIWorkflow.enableDisable(prestitoNazionaleNO);
		UIWorkflow.enableDisable(prestitoNazionaleNonSpecificato);

		
		UIWorkflow.enableDisable(prestitoInternazionaleSI);
		UIWorkflow.enableDisable(prestitoInternazionaleNO);
		UIWorkflow.enableDisable(prestitoInternazionaleNonSpecificato);

		
		UIWorkflow.enableDisable(procedureAutoSI);
		UIWorkflow.enableDisable(procedureAutoNO);
		UIWorkflow.enableDisable(procedureAutoNonSpecificato);
		
		if(UIWorkflow.isReadOnly()==false){
			addKeyListenerForEnter();
		}else{
			removeAddKeyListenerForEnter();
		}
	}


	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
	
	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(prestitoAggiorna, prestitoNazionaleInternazionaleForm);
	}
	
	protected void removeAddKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter(prestitoNazionaleInternazionaleForm);
	}
}
