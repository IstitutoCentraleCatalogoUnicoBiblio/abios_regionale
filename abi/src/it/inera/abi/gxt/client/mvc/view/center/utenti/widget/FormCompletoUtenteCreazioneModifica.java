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

package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.DuplicatedEntryClientSideException;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Form che permette la creazione / modifica di un utente
 *
 */
public class FormCompletoUtenteCreazioneModifica extends LayoutContainer {

	private UtentiServiceAsync utentiService;

	private TextField<String> nome;
	private TextField<String> cognome;
	private TextFieldCustom<String> username;
	private TextFieldCustom<String> password;
	private TextFieldCustom<String> confirmPassword;
	private TextFieldCustom<String> telefono;
	private TextFieldCustom<String> fax;
	private TextFieldCustom<String> email;
	private TextFieldCustom<String> incarico;
	private TextArea note;
	private FieldSet profiliSet;

	private List<ProfiliUtente> profili;
	private boolean modifica = false;
	private UtenteCompletoFormModel userFromDB = null;
	private Button generaPswButton = null;
	private Button annulla = null;
	private Button modificaButton = null;
	private Button esci = null;
	private Button crea = null;
	private FormPanel mainPanel = null;
	//	private ListaBiblioUtentePanel listaBiblioUtente = null;

	private LayoutContainer left;
	private LayoutContainer right;
	private LayoutContainer telFax;

	private boolean modificaByUser;

	private Vector<CheckBox> checkboxItems = null;

	public FormCompletoUtenteCreazioneModifica() {
		utentiService = (UtentiServiceAsync) Registry.get(Abi.UTENTI_SERVICE);

		mainPanel = new FormPanel();
		mainPanel.setIcon(Resources.ICONS.user_add());
		mainPanel.setHeading("Dati utente");
		mainPanel.setSize(600, -1);
		mainPanel.setLabelAlign(LabelAlign.TOP);
		mainPanel.setButtonAlign(HorizontalAlignment.CENTER);
		mainPanel.setScrollMode(Scroll.AUTOY);
		mainPanel.setStyleAttribute("background-color", "white");
		modificaByUser=false;
		setLayout(new FitLayout());
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		//		if (this.modifica == true) {
		//			mainPanel.addButton(indietro);
		//			mainPanel.addButton(reset);
		//			mainPanel.addButton(modificaButton);
		//
		//		} else {
		//			mainPanel.addButton(reset);
		//			mainPanel.addButton(salva);
		//		}

	}

	public void setUtenteModifica(UtenteCompletoFormModel userFromDB) {
		this.userFromDB = userFromDB;
		mainPanel.setHeading("Modifica utente "+userFromDB.getUserName());
		mainPanel.setIcon(Resources.ICONS.user_edit());

		//		listaBiblioUtente = new ListaBiblioUtentePanel();
		//		listaBiblioUtente.setService();
		//		listaBiblioUtente.setGrid();
		//		listaBiblioUtente.setBottomToolbar();
		//		listaBiblioUtente.setTopToolbar(userFromDB.getIdUser());
		//		listaBiblioUtente.setUser(userFromDB);

	}

	public void setProfili(List<ProfiliUtente> profili) {
		this.profili = profili;
	}

	public void setModifica(boolean modifica) {
		this.modifica = modifica;
		createFormFields(this.userFromDB);
	}

	public void createFormFields(final UtenteCompletoFormModel userFromDB) {

		FormData formData = new FormData("90%");
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new ColumnLayout());

		left = new LayoutContainer();
		left.setStyleAttribute("paddingRight", "20px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		left.setLayout(layout);

		nome = new TextField<String>();
		nome.setFieldLabel("Nome");
		left.add(nome, formData);

		password = new TextFieldCustom<String>();
		confirmPassword = new TextFieldCustom<String>();

		password.setFieldLabel("Password (lunghezza caratteri:min 6 max 12)");
		password.setMinLength(CostantiGestioneUtenti.PASSWORD_MIN_LENGTH);
		password.setMaxLength(CostantiGestioneUtenti.PASSWORD_MAX_LENGTH);
		password.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				if (password.getValue()!=null && confirmPassword.getValue() != null) {
					confirmPassword.setAllowBlank(true);
					password.setAllowBlank(true);
					confirmPassword.isValid();
				}else{
					confirmPassword.setAllowBlank(false);
					password.setAllowBlank(false);
				}
				return null;
			}
		});
		password.setPassword(true);
		left.add(password, formData);

		confirmPassword.setFieldLabel("Conferma password");
		confirmPassword.setMinLength(CostantiGestioneUtenti.PASSWORD_MIN_LENGTH);
		confirmPassword.setMaxLength(CostantiGestioneUtenti.PASSWORD_MAX_LENGTH);
		confirmPassword.setPassword(true);
		confirmPassword.setValidator(new PasswordConfirmValidate(password));

		left.add(confirmPassword, formData);

		telFax = new LayoutContainer(new TableLayout(2));
		telFax.setWidth("100%");

		FormLayout fl = new FormLayout();
		fl.setLabelAlign(LabelAlign.TOP);
		fl.setDefaultWidth(160);

		LayoutContainer l = new LayoutContainer();
		l.setWidth(160);
		l.setLayout(fl);

		TableData telFaxTableData = new TableData();
		telFaxTableData.setWidth("160px");

		telefono = new TextFieldCustom<String>();
		telefono.setWidth(160);
		telefono.setFieldLabel("Telefono");
		telefono.setMaxLength(50);
		l.add(telefono);
		telFax.add(l,telFaxTableData);

		FormLayout fr = new FormLayout();
		fr.setLabelAlign(LabelAlign.TOP);
		fr.setDefaultWidth(160);

		LayoutContainer r = new LayoutContainer();
		r.setWidth(160);
		r.setStyleAttribute("paddingLeft", "21px");
		r.setLayout(fr);

		fax = new TextFieldCustom<String>();
		fax.setWidth(160);
		fax.setMaxLength(50);
		fax.setFieldLabel("Fax");

		r.add(fax);
		telFax.add(r,telFaxTableData);

		left.add(telFax,formData);

		generaPswButton = new Button("Rigenera password");
		Utils.setStylesButton(generaPswButton);

		generaPswButton.setToolTip("La nuova password verrà rigenerata ed inviata automaticamente all'utente per email.");

		generaPswButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				utentiService.resetPassword(userFromDB.getIdUser(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						AbiMessageBox.messageSuccessAlertBox("Password generata con successo!", "ESITO CREAZIONE");
						setOriginalFieldValuesModifieds();
						password.clear();
						confirmPassword.clear();
						Dispatcher.forwardEvent(AppEvents.ListaUtenti);	
					}

					@Override
					public void onFailure(Throwable caught) {
						if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox("Errore durante la generazione della password!","ESITO CREAZIONE");
						}
					}
				});
			}
		}); 

		FormData f =new FormData(100, 50);
		f.setMargins(new Margins(25, 0, 25, 0));
		left.add(generaPswButton,f);


		right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		right.setLayout(layout);

		cognome = new TextField<String>();
		cognome.setFieldLabel("Cognome");
		cognome.setAllowBlank(true);
		right.add(cognome, formData);

		username = new TextFieldCustom<String>();
		username.setFieldLabel("Username (lunghezza caratteri:min 2 max 20)");
		username.setAllowBlank(false);
		username.setMinLength(CostantiGestioneUtenti.USERNAME_MIN_LENGTH);
		username.setMaxLength(CostantiGestioneUtenti.USERNAME_MAX_LENGTH);

		if(this.modifica){
			username.disable();
		}else{
			username.enable();
		}

		right.add(username, formData);

		email = new TextFieldCustom<String>();
		email.setFieldLabel("Email (lunghezza caratteri:max 50)");
		email.setMaxLength(50);
		email.setAllowBlank(false);

		/* Validazione email */
		email.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				String res = null;
				if (!validateEmail(value)) {
					res = "Inserire una email valida";
				}
				return res;
			}
		});

		right.add(email, formData);

		incarico = new TextFieldCustom<String>();
		incarico.setMaxLength(50);
		incarico.setFieldLabel("Incarico");
		right.add(incarico, formData);


		main.add(left, new ColumnData(.5));
		main.add(right, new ColumnData(.5));

		mainPanel.add(main, new FormData("100%"));

		note = new TextArea();
		note.setFieldLabel("Note");
		note.setHeight(50);

		mainPanel.add(note, new FormData("95%"));

		setFieldValues(userFromDB);

		profiliSet = new FieldSet();
		Utils.setFieldSetProperties(profiliSet, "Seleziona profilo utente:");
		profiliSet.setStyleAttribute("marginTop", "10px");
		profiliSet.setCollapsible(false);

		LayoutContainer profileCheckBox = new LayoutContainer(new TableLayout(3));
		TableData t = new TableData();
		t.setWidth("25%");
		t.setMargin(5);
		t.setPadding(5);

		checkboxItems = new Vector<CheckBox>();

		generaCheckBoxDaVociDB(profileCheckBox, t, checkboxItems);

		profiliSet.add(profileCheckBox);
		mainPanel.add(profiliSet);


		this.esci = new Button("Esci");
		Utils.setStylesButton(this.esci);
		esci.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {								
							Dispatcher.forwardEvent(AppEvents.IndietroDaModificaModificaUtente);
						}
					}
				};
				AbiMessageBox.messageAlertConfirmBox("Proseguendo con l'operazione verranno perse le modifiche non ancora salvate. Continuare?", "ATTENZIONE!",l );
			}
		});

		this.annulla = new Button("Annulla");
		Utils.setStylesButton(this.annulla);
		this.annulla.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				mainPanel.reset();
			}
		});
		if (this.modifica == false) {
			this.crea = new Button("Crea");
			Utils.setStylesButton(this.crea);
			FormButtonBinding binding = new FormButtonBinding(this.mainPanel);
			binding.addButton(crea);
			crea.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					//					MessageBox box = new MessageBox();
					//					box.setIcon(MessageBox.QUESTION);
					//					box.setMessage("Salvare il nuovo utente?");
					//					box.setButtons(MessageBox.YESNO);
					//					box.addCallback(new Listener<MessageBoxEvent>() {
					//						@Override
					//						public void handleEvent(MessageBoxEvent be) {
					//							if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {								
					UtenteCompletoFormModel nuovoUtente = new UtenteCompletoFormModel();
					nuovoUtente.setNome(nome.getValue());
					nuovoUtente.setCognome(cognome.getValue());
					nuovoUtente.setUserName(username.getValue());
					nuovoUtente.setPassword(password.getValue());
					nuovoUtente.setPassword2(confirmPassword.getValue());
					nuovoUtente.setTelefono(telefono.getValue());
					nuovoUtente.setFax(fax.getValue());

					nuovoUtente.setEmail(email.getValue());

					nuovoUtente.setIncarico(incarico.getValue());
					nuovoUtente.setNote(note.getValue());

					nuovoUtente.setProfili(getProfiliAttivi(checkboxItems));

					utentiService.saveUtente(nuovoUtente, modifica, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean result) {
							/* Messaggio ridondante */
//							AbiMessageBox.messageSuccessAlertBox("Utente creato con successo", AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE);
							
							setOriginalFieldValuesModifieds();
							password.clear();
							confirmPassword.clear();
							Dispatcher.forwardEvent(AppEvents.ListaUtenti);
						}

						@Override
						public void onFailure(Throwable caught) {
							if (caught instanceof DuplicatedEntryClientSideException){
								AbiMessageBox.messageAlertBox(caught.getMessage(), AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
							}else{
								if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
									AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									Dispatcher.forwardEvent(AppEvents.ListaUtenti);
								}	
							}
						}
					});

					//							}
					//						}
					//					});
					//					box.show();

				}

				/**
				 * @param checkboxItems
				 */
				private ArrayList<ProfiliUtente> getProfiliAttivi(final Vector<CheckBox> checkboxItems) {
					ArrayList<ProfiliUtente> profiliAttivi = new ArrayList<ProfiliUtente>();
					Iterator<ProfiliUtente> it = profili.iterator();
					for (int i = 0; i < checkboxItems.size(); i++) {
						if (checkboxItems.elementAt(i).getValue() == true) {
							profiliAttivi.add(it.next());
						} else
							it.next();

					}
					return profiliAttivi;
				}
			});
		} else {
			this.modificaButton = new Button("Modifica");
			Utils.setStylesButton(this.modificaButton);
			FormButtonBinding binding = new FormButtonBinding(this.mainPanel);
			binding.addButton(modificaButton);
			modificaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent ce) {
					//					MessageBox box = new MessageBox();
					//					box.setIcon(MessageBox.QUESTION);
					//					box.setMessage("Modificare l'utente "+userFromDB.getUserName()+"?");
					//					box.setButtons(MessageBox.YESNO);
					//					box.addCallback(new Listener<MessageBoxEvent>() {
					//						@Override
					//						public void handleEvent(MessageBoxEvent be) {
					//							if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
					UtenteCompletoFormModel nuovoUtente = new UtenteCompletoFormModel();
					nuovoUtente.setIdUser(userFromDB.getIdUser());
					nuovoUtente.setNome(nome.getValue());
					nuovoUtente.setCognome(cognome.getValue());
					nuovoUtente.setUserName(username.getValue());
					nuovoUtente.setPassword(password.getValue());
					nuovoUtente.setPassword2(confirmPassword.getValue());
					nuovoUtente.setTelefono(telefono.getValue());
					nuovoUtente.setFax(fax.getValue());
					nuovoUtente.setEmail(email.getValue());
					nuovoUtente.setIncarico(incarico.getValue());
					nuovoUtente.setNote(note.getValue());

					nuovoUtente.setProfili(getProfiliAttivi(checkboxItems));

					UtentiServiceAsync utentiService = (UtentiServiceAsync) Registry.get(Abi.UTENTI_SERVICE);
					utentiService.saveUtente(nuovoUtente, modifica,	new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean result) {
							AbiMessageBox.messageSuccessAlertBox("Utente modificato con successo", AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE);
							setOriginalFieldValuesModifieds();
							password.clear();
							confirmPassword.clear();
							if(!modificaByUser){
								Dispatcher.forwardEvent(AppEvents.IndietroDaModificaModificaUtente);		
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
								AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

						}
					});
					//							}
					//						}
					//					});
					//					box.show();

				}


			});

		}

		TableLayout tableLayout = new TableLayout(3);
		tableLayout.setCellPadding(5);
		tableLayout.setWidth("100%");

		TableData tdataR = new TableData();
		tdataR.setHorizontalAlign(HorizontalAlignment.RIGHT);

		TableData tdataC = new TableData();
		tdataC.setHorizontalAlign(HorizontalAlignment.CENTER);

		TableData tdataL = new TableData();
		tdataL.setHorizontalAlign(HorizontalAlignment.LEFT);

		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.setId("ID1");
		buttons.setWidth("100%");

		if (this.modifica == true) {
			buttons.add(modificaButton, tdataR);
			buttons.add(annulla, tdataC);
			buttons.add(esci, tdataL);
		} 
		else {
			buttons.add(crea, tdataR);
			buttons.add(annulla);
		}
		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setHeight(50);
		c.setWidth("100%");
		c.setId("ID2");
		c.add(buttons);
		mainPanel.add(c);

		add(mainPanel);
	}

	/**
	 * @param userFromDB
	 */
	public void setFieldValues(final UtenteCompletoFormModel userFromDB) {
		if (this.modifica == true) {
			nome.setValue(userFromDB.getNome());
			cognome.setValue(userFromDB.getCognome());
			password.setValue(userFromDB.getPassword());
			confirmPassword.setValue(userFromDB.getPassword());
			telefono.setValue(userFromDB.getTelefono());
			fax.setValue(userFromDB.getFax());
			username.setValue(userFromDB.getUserName());
			email.setValue(userFromDB.getEmail());
			incarico.setValue(userFromDB.getIncarico());
			note.setValue(userFromDB.getNote());

			setOriginalFieldValues(userFromDB);
		} else {
			/* Non sono in modifica, la password è obbiligatoria e deve essere uguale alla conferma */
			password.setAllowBlank(false);
			confirmPassword.setAllowBlank(false);
		}
	}

	/**
	 * @param userFromDB
	 */
	private void setOriginalFieldValues(final UtenteCompletoFormModel userFromDB) {
		nome.setOriginalValue(userFromDB.getNome());
		cognome.setOriginalValue(userFromDB.getCognome());
		telefono.setOriginalValue(userFromDB.getTelefono());
		fax.setOriginalValue(userFromDB.getFax());
		username.setOriginalValue(userFromDB.getUserName());
		email.setOriginalValue(userFromDB.getEmail());
		incarico.setOriginalValue(userFromDB.getIncarico());
		note.setOriginalValue(userFromDB.getNote());
	}

	private void setOriginalFieldValuesModifieds() {
		nome.setOriginalValue(nome.getValue());
		cognome.setOriginalValue(cognome.getValue());
		telefono.setOriginalValue(telefono.getValue());
		fax.setOriginalValue(fax.getValue());
		username.setOriginalValue(username.getValue());
		email.setOriginalValue(email.getValue());
		incarico.setOriginalValue(incarico.getValue());
		note.setOriginalValue(note.getValue());
	}

	private void generaCheckBoxDaVociDB(LayoutContainer profileCheckBox, TableData t, Vector<CheckBox> checkboxItems) {

		Iterator<ProfiliUtente> it = profili.iterator();
		int i = 0;
		while (it.hasNext()) {
			ProfiliUtente p = it.next();
			CheckBox tmp = new CheckBox();
			tmp.setBoxLabel(p.getDenominazione());
			tmp.setId("i");
			if (p.isValore()) {
				tmp.setValue(true);
			}
			checkboxItems.add(tmp);
			i++;
		}

		for (int j = 0; j < checkboxItems.size(); j++) {
			profileCheckBox.add(checkboxItems.elementAt(j), t);
		}
	}

	public void removeButtons() {
		if (annulla != null)
			remove(annulla);
		if (modificaButton != null)
			remove(modificaButton);
		if (esci != null)
			remove(esci);
		if (crea != null)
			remove(crea);

	}

	public void refresh() {
		mainPanel.reset();
		mainPanel.removeAll();
		//		mainPanel.layout();
	}

	private boolean validateEmail(String email) {
		final String EMAIL_VALIDATION_REGEX = "[a-z0-9!#$%&'*+/" +  
		"=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-" +  
		"z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"; 
		if (email.matches(EMAIL_VALIDATION_REGEX))
			return true;
		else return false;
	}


	public void resetForm() {
		mainPanel.reset();
	}

	public void setParametriModificaByUser() {
		modificaByUser = true;
		if (!mainPanel.isRendered()) {
			mainPanel.setHeaderVisible(false);
		}
	}

	public void riorganizzaFieldPerModificaByAdmin() {
		if (modifica == false) {
			generaPswButton.disable();
		}
		else{
			generaPswButton.enable();
		}

		password.setAllowBlank(true);
		confirmPassword.setAllowBlank(true);
		password.setValue(null);
		confirmPassword.setValue(null);
		password.hide();
		confirmPassword.hide();
		right.remove(username);
		left.insert(username, 1);
	}


	/**
	 * @param checkboxItems
	 */
	private ArrayList<ProfiliUtente> getProfiliAttivi(final Vector<CheckBox> checkboxItems) {
		ArrayList<ProfiliUtente> profiliAttivi = new ArrayList<ProfiliUtente>();
		Iterator<ProfiliUtente> it = profili.iterator();
		for (int i = 0; i < checkboxItems.size(); i++) {
			if (checkboxItems.elementAt(i).getValue() == true) {
				profiliAttivi.add(it.next());
			} else
				it.next();

		}
		return profiliAttivi;
	}

}