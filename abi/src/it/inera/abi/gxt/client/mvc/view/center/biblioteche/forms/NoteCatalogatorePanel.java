package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
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
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * alle note del catalogatore
 *
 */
public class NoteCatalogatorePanel extends ContentPanelForTabItem {

	private int id_biblio;
	private BibliotecheServiceAsync bibliotecheService;

	private LayoutContainer infoCatalogazione;
	private FieldSet infoCatalogazioneSet;

	private static final String dataCensimentoLabel="Data censimento import:&nbsp;";
	private static final String dataImportLabel="Data import:&nbsp;";
	private static final String dataModificaRemotaLabel="Data modifica remota:&nbsp;";
	private static final String dataModificaLabel="Data modifica:&nbsp;";
	private static final String utenteUltimaModificaLabel="Utente ultima modifica:&nbsp;";
	private static final String fonteLabel="Fonte:&nbsp;";

	private Text dataCensimentoText;
	private Text dataImportText;
	private Text dataModificaRemotaText;
	private Text dataModificaText;
	private Text utenteUltimaModificaText;
	private Text fonteText;

	private TextArea noteBox;
	private TextArea comunicazioniBox;

	private Button comunicazioniReset;
	private Button comunicazioniAggiorna;
	private Button noteReset;
	private Button noteAggiorna;

	/*Forms*/
	private FormPanel noteForm;
	private FormPanel comunicazioniForm;

	public NoteCatalogatorePanel() {
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setBodyBorder(false);
		setScrollMode(Scroll.AUTOY);
		bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

		TableData d = new TableData();
		d.setWidth("40%");
		d.setMargin(5);
		d.setPadding(10);

		TableData d2 = new TableData();
		d2.setWidth("60%");
		d2.setMargin(5);
		d2.setPadding(10);


		/*INFO CATALOGAZIONE*/
		infoCatalogazione = new LayoutContainer();
		infoCatalogazione.setStyleAttribute("padding", "5px");
		infoCatalogazione.hide();

		infoCatalogazioneSet = new FieldSet();
		Utils.setFieldSetProperties(infoCatalogazioneSet, CostantiGestioneBiblio.INFORMAZIONI_CATALOGAZIORE_FIELDSET_LABEL);
		infoCatalogazioneSet.setCollapsible(true);

		LayoutContainer infoCatalogazioneContainer = new LayoutContainer(new RowLayout());

		dataCensimentoText = new Text();
		dataCensimentoText.setStyleAttribute("fontSize", "14px");
		dataCensimentoText.hide();

		dataImportText = new Text();
		dataImportText.setStyleAttribute("fontSize", "14px");
		dataImportText.hide();

		dataModificaRemotaText = new Text();
		dataModificaRemotaText.setStyleAttribute("fontSize", "14px");
		dataModificaRemotaText.hide();

		dataModificaText = new Text();
		dataModificaText.setStyleAttribute("fontSize", "14px");
		dataModificaText.hide();

		utenteUltimaModificaText = new Text();
		utenteUltimaModificaText.setStyleAttribute("fontSize", "14px");
		utenteUltimaModificaText.hide();

		fonteText = new Text();
		fonteText.setStyleAttribute("fontSize", "14px");
		fonteText.hide();


		infoCatalogazioneContainer.add(dataCensimentoText);
		infoCatalogazioneContainer.add(dataImportText);
		infoCatalogazioneContainer.add(dataModificaRemotaText);
		infoCatalogazioneContainer.add(dataModificaText);
		infoCatalogazioneContainer.add(utenteUltimaModificaText);
		infoCatalogazioneContainer.add(fonteText);

		infoCatalogazioneSet.add(infoCatalogazioneContainer);
		infoCatalogazione.add(infoCatalogazioneSet);
		add(infoCatalogazione);
		/*BIBLio*/
		LayoutContainer note = new LayoutContainer();
		note.setStyleAttribute("padding", "5px");

		FieldSet noteSet = new FieldSet();
		Utils.setFieldSetProperties(noteSet, CostantiGestioneBiblio.NOTE_CATALOGATORE_FIELDSET_LABEL);
		noteSet.setCollapsible(true);

		LayoutContainer noteTable = new LayoutContainer(new TableLayout(1));

		noteForm = new FormPanel();
		noteForm.setHeaderVisible(false);
		noteForm.setBorders(false);
		noteForm.setBodyBorder(false);
		noteForm.setButtonAlign(HorizontalAlignment.RIGHT);
		noteForm.setAnimCollapse(true);

		FormLayout noteFormLayout = new FormLayout();
		noteFormLayout.setLabelAlign(LabelAlign.TOP);

		noteForm.setLayout(noteFormLayout);

		FormButtonBinding noteBind = new FormButtonBinding(noteForm);	

		noteBox = new TextArea();
		noteBox.setFieldLabel("Note");
		noteBox.setEmptyText("Inserisci note...");
		noteBox.setHeight("100px");
		noteBox.setWidth("700px");
		noteBox.setBorders(true);
		Utils.addListenerToChangeLabelColorIfModifiedTextArea(noteBox, "Note");

		noteAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(noteAggiorna);

		noteAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);


				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							bibliotecheService.inserisciNoteCatalogazione(id_biblio,noteBox.getValue(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									noteBox.setOriginalValue(noteBox.getValue());
									Utils.setFontColorStyleBlackTextArea(noteBox, "Note");
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});

						} 

					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}

		});

		noteReset = new Button("Reset");
		Utils.setStylesButton(noteReset);

		noteReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				noteForm.reset();
				Utils.setFontColorStyleBlackTextArea(noteBox, "Note");
			}
		});

		noteBind.addButton(noteAggiorna);
		noteBind.addButton(noteReset);

		noteForm.add(noteBox,new FormData("-20"));

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(noteAggiorna);
		buttons.add(noteReset);

		noteForm.add(buttons);

		noteTable.add(noteForm, d2);		
		noteSet.add(noteTable);

		note.add(noteSet);
		add(note);
		/* FINE---NOTE */
		/* COMUNICAZIONI */
		LayoutContainer comunicazioni = new LayoutContainer();
		comunicazioni.setStyleAttribute("padding", "5px");

		FieldSet comunicazioniSet = new FieldSet();
		Utils.setFieldSetProperties(comunicazioniSet, CostantiGestioneBiblio.COMUNCAZIONI_FIELDSET_LABEL);
		comunicazioniSet.setCollapsible(true);

		LayoutContainer comunicazioniTable = new LayoutContainer(new TableLayout(1));

		comunicazioniForm = new FormPanel();
		comunicazioniForm.setHeaderVisible(false);
		comunicazioniForm.setBorders(false);
		comunicazioniForm.setBodyBorder(false);
		comunicazioniForm.setButtonAlign(HorizontalAlignment.RIGHT);
		comunicazioniForm.setAnimCollapse(true);


		FormLayout comunicazioniFormLayout = new FormLayout();
		comunicazioniFormLayout.setLabelAlign(LabelAlign.TOP);

		comunicazioniForm.setLayout(comunicazioniFormLayout);

		FormButtonBinding comunicazioniBind = new FormButtonBinding(comunicazioniForm);	

		comunicazioniBox = new TextArea();
		comunicazioniBox.setFieldLabel("Comunicazioni");
		comunicazioniBox.setEmptyText("Inserisci comunicazioni...");
		comunicazioniBox.setHeight("100px");
		comunicazioniBox.setWidth("700px");
		comunicazioniBox.setBorders(true);
		Utils.addListenerToChangeLabelColorIfModifiedTextArea(comunicazioniBox, "Comunicazioni");

		comunicazioniAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(comunicazioniAggiorna);

		comunicazioniAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							bibliotecheService.inserisciComunicazioniCatalogazione(id_biblio,comunicazioniBox.getValue(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}

								@Override
								public void onSuccess(Void result) {
									comunicazioniBox.setOriginalValue(comunicazioniBox.getValue());
									Utils.setFontColorStyleBlackTextArea(comunicazioniBox, "Comunicazioni");
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});
						} 
						else{
						}
					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}

		});

		comunicazioniReset = new Button("Reset");
		Utils.setStylesButton(comunicazioniReset);

		comunicazioniReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				comunicazioniForm.reset();
				Utils.setFontColorStyleBlackTextArea(comunicazioniBox, "Comunicazioni");
			}
		});

		comunicazioniBind.addButton(comunicazioniAggiorna);
		comunicazioniBind.addButton(comunicazioniReset);

		comunicazioniForm.add(comunicazioniBox,new FormData("-20"));

		TableLayout tableLayoutComunicazioni = new TableLayout(2);
		tableLayoutComunicazioni.setCellPadding(5);
		LayoutContainer comunicazioniButtons = new LayoutContainer(tableLayoutComunicazioni);
		comunicazioniButtons.add(comunicazioniAggiorna);
		comunicazioniButtons.add(comunicazioniReset);
		comunicazioniForm.add(comunicazioniButtons);

		comunicazioniTable.add(comunicazioniForm,d2);		
		comunicazioniSet.add(comunicazioniTable);


		comunicazioni.add(comunicazioniSet);
		add(comunicazioni);
		/* FINE---comunicazioni */
	}
	
	public void setFieldsValues() {
		if (biblioteca.getDataCensimento() != null || biblioteca.getDataImport() != null || biblioteca.getDataModificaRemota() != null || biblioteca.getDataModifica() != null || biblioteca.getUtenteUltimaModifica() != null || biblioteca.getFonte() != null) {
			infoCatalogazione.show();

			if (biblioteca.getDataCensimento() != null) {
				dataCensimentoText.setText(dataCensimentoLabel+biblioteca.getDataCensimento());
				dataCensimentoText.show();
				
			} else {
				dataCensimentoText.hide();
			}

			if (biblioteca.getDataImport() != null) {
				dataImportText.setText(dataImportLabel+biblioteca.getDataImport());
				dataImportText.show();
				
			} else {
				dataImportText.hide();
			}

			if (biblioteca.getDataModificaRemota() != null) {
				dataModificaRemotaText.setText(dataModificaRemotaLabel+biblioteca.getDataModificaRemota());
				dataModificaRemotaText.show();
				
			} else {
				dataModificaRemotaText.hide();
			}

			if (biblioteca.getDataModifica() != null) {
				dataModificaText.setText(dataModificaLabel+biblioteca.getDataModifica());
				dataModificaText.show();
				
			} else {
				dataModificaText.hide();
			}

			if (biblioteca.getUtenteUltimaModifica() != null) {
				utenteUltimaModificaText.setText(utenteUltimaModificaLabel+biblioteca.getUtenteUltimaModifica());
				utenteUltimaModificaText.show();
				
			} else {
				utenteUltimaModificaText.hide();
			}
			
			if (biblioteca.getFonte() != null) {
				fonteText.setText(fonteLabel+biblioteca.getFonte());
				fonteText.show();
				
			} else {
				fonteText.hide();
			}
			
			
		} else {
			infoCatalogazione.hide();
		}

		if (biblioteca.getNoteCatalogatore() != null) {
			noteBox.setValue(biblioteca.getNoteCatalogatore());
			noteBox.setOriginalValue(biblioteca.getNoteCatalogatore());
		}
		else {
			noteBox.setValue(null);
			noteBox.setOriginalValue(null);
		}
		Utils.setFontColorStyleBlackTextArea(noteBox, "Note");

		if (biblioteca.getComunicazioni() != null) {
			comunicazioniBox.setValue(biblioteca.getComunicazioni());
			comunicazioniBox.setOriginalValue(biblioteca.getComunicazioni());
		}
		else {
			comunicazioniBox.setValue(null);
			comunicazioniBox.setOriginalValue(null);
		}
		Utils.setFontColorStyleBlackTextArea(comunicazioniBox, "Comunicazioni");

		UIWorkflow.setReadOnly(noteBox);
		UIWorkflow.setReadOnly(comunicazioniBox);
		UIWorkflow.hideView(comunicazioniReset);
		UIWorkflow.hideView(comunicazioniAggiorna);
		UIWorkflow.hideView(noteReset);
		UIWorkflow.hideView(noteAggiorna);
		//		if(UIWorkflow.isReadOnly()==false){
		//			addKeyListenerForEnter();
		//		}else{
		//			removeKeyListenerForEnter();
		//		}
	}

	//	private void removeKeyListenerForEnter() {
	//		Utils.removeKeyListenerForEnter( noteForm);
	//		Utils.removeKeyListenerForEnter( comunicazioniForm);
	//	}
	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;
	}

	//	protected void addKeyListenerForEnter() {
	//		Utils.addKeyListenerForEnter(noteAggiorna, noteForm);
	//		Utils.addKeyListenerForEnter(comunicazioniAggiorna, comunicazioniForm);
	//	}
}
