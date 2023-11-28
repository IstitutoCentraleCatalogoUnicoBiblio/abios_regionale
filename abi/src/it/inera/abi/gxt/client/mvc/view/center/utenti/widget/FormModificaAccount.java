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
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
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
 * Form per la modifica dei principali dati dell'utente
 * attualmente loggato
 *
 */
public class FormModificaAccount extends LayoutContainer {

	private UtentiServiceAsync utentiService;
	
	private TextFieldCustom<String> password;
	private TextFieldCustom<String> confirmPassword;
	private TextFieldCustom<String> telefono;
	private TextFieldCustom<String> fax;
	private TextFieldCustom<String> email;
	
	private UtenteCompletoFormModel userFromDB = null;
	
	private Button modifica = null;
	private Button reset = null;
	
	private FormPanel mainPanel = null;
	
	private LayoutContainer left;
	private LayoutContainer right;
	private LayoutContainer telFax;

	public FormModificaAccount() {
		utentiService = (UtentiServiceAsync) Registry.get(Abi.UTENTI_SERVICE);
		
		mainPanel = new FormPanel();
		mainPanel.setIcon(Resources.ICONS.user_add());
		mainPanel.setHeading("Dati utente");
		mainPanel.setSize(400, -1);
		mainPanel.setLabelAlign(LabelAlign.TOP);
		mainPanel.setButtonAlign(HorizontalAlignment.CENTER);
		mainPanel.setScrollMode(Scroll.AUTOY);
		mainPanel.setStyleAttribute("background-color", "white");
		
		setLayout(new FitLayout());
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

	}

	public void setUtenteModifica(UtenteCompletoFormModel userFromDB) {
		this.userFromDB = userFromDB;
		mainPanel.setHeading("Modifica utente "+userFromDB.getUserName());
		mainPanel.setIcon(Resources.ICONS.user_edit());

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

		password = new TextFieldCustom<String>();
		password.setWidth(20);
		confirmPassword = new TextFieldCustom<String>();
		
		password.setFieldLabel("Password (min 6, max 12)");
		password.setMinLength(CostantiGestioneUtenti.PASSWORD_MIN_LENGTH);
		password.setMaxLength(CostantiGestioneUtenti.PASSWORD_MAX_LENGTH);
		password.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				if (password.getValue() != null && confirmPassword.getValue() != null) {
					confirmPassword.setAllowBlank(true);
					password.setAllowBlank(true);
					confirmPassword.isValid();
				}
				else {
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
		fl.setDefaultWidth(120);
		
		LayoutContainer l = new LayoutContainer();
		l.setWidth(150);
		l.setLayout(fl);

		TableData telFaxTableData = new TableData();
		telFaxTableData.setWidth("100px");

		telefono = new TextFieldCustom<String>();
		telefono.setWidth(100);
		telefono.setFieldLabel("Telefono");
		telefono.setMaxLength(50);
		l.add(telefono);
		telFax.add(l,telFaxTableData);

		FormLayout fr = new FormLayout();
		fr.setLabelAlign(LabelAlign.TOP);
		fr.setDefaultWidth(120);
		
		LayoutContainer r = new LayoutContainer();
		r.setWidth(150);
		r.setLayout(fr);

		fax = new TextFieldCustom<String>();
		fax.setWidth(100);
		fax.setMaxLength(50);
		fax.setFieldLabel("Fax");

		r.add(fax);
		telFax.add(r,telFaxTableData);

		

		FormData f = new FormData(100, 50);
		f.setMargins(new Margins(25, 0, 25, 0));
		
		right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);
		right.setLayout(layout);

		email = new TextFieldCustom<String>();
		email.setFieldLabel("Email (max 50)");
		email.setWidth(270);
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
		right.add(telFax, formData);

		main.add(left, new ColumnData(.4));
		main.add(right, new ColumnData(.6));

		mainPanel.add(main, new FormData("100%"));
		
		setFieldValues(userFromDB);

		this.reset = new Button("Reset");
		Utils.setStylesButton(reset);
		this.reset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				mainPanel.reset();
			}
		});
		
		modifica = new Button("Modifica");
		Utils.setStylesButton(modifica);
		FormButtonBinding binding = new FormButtonBinding(this.mainPanel);
		binding.addButton(modifica);
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				UtenteCompletoFormModel nuovoUtente = new UtenteCompletoFormModel();
				
				nuovoUtente.setIdUser(userFromDB.getIdUser());
				nuovoUtente.setPassword(password.getValue());
				nuovoUtente.setPassword2(confirmPassword.getValue());
				nuovoUtente.setTelefono(telefono.getValue());
				nuovoUtente.setFax(fax.getValue());
				nuovoUtente.setEmail(email.getValue());

				/** CARICA PROFILI **/
				List<ProfiliUtente> l = userFromDB.getProfili();
				nuovoUtente.setProfili(l);
				
				utentiService.saveUtenteFromModificaAccount(nuovoUtente, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						AbiMessageBox.messageSuccessAlertBox("Utente modificato con successo", AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE);
						setOriginalFieldValuesModifieds();
						password.clear();
						confirmPassword.clear();
						
					}
	
					@Override
					public void onFailure(Throwable caught) {
						if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
	
					}
				});
				
			}
			
		});

		
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

		buttons.add(modifica, tdataR);
		buttons.add(reset, tdataL);
		buttons.add(new LayoutContainer(), tdataR);
		
		
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
			password.setValue(userFromDB.getPassword());
			confirmPassword.setValue(userFromDB.getPassword());
			telefono.setValue(userFromDB.getTelefono());
			fax.setValue(userFromDB.getFax());
			email.setValue(userFromDB.getEmail());
			
			setOriginalFieldValues(userFromDB);
	}

	/**
	 * @param userFromDB
	 */
	private void setOriginalFieldValues(final UtenteCompletoFormModel userFromDB) {
		telefono.setOriginalValue(userFromDB.getTelefono());
		fax.setOriginalValue(userFromDB.getFax());
		email.setOriginalValue(userFromDB.getEmail());
	}
	
	private void setOriginalFieldValuesModifieds() {
		telefono.setOriginalValue(telefono.getValue());
		fax.setOriginalValue(fax.getValue());
		email.setOriginalValue(email.getValue());
	}

	public void removeButtons() {
		if (reset != null)
			remove(reset);
		if (modifica != null)
			remove(modifica);
	}

	public void refresh() {
		mainPanel.reset();
		mainPanel.removeAll();
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
	
	public void setParameters() {
		createFormFields(this.userFromDB);
		
		if (!mainPanel.isRendered()) {
			mainPanel.setHeaderVisible(false);
		}
	}
	
	public void setClosingWindowOnModifica(final Window w) {
		setScrollMode(Scroll.NONE);
		
		modifica.removeAllListeners();
		modifica.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				UtenteCompletoFormModel nuovoUtente = new UtenteCompletoFormModel();
				nuovoUtente.setIdUser(userFromDB.getIdUser());
				nuovoUtente.setPassword(password.getValue());
				nuovoUtente.setPassword2(confirmPassword.getValue());
				nuovoUtente.setTelefono(telefono.getValue());
				nuovoUtente.setFax(fax.getValue());
				nuovoUtente.setEmail(email.getValue());
				
				/** CARICA PROFILI **/
				List<ProfiliUtente> l = userFromDB.getProfili();
				nuovoUtente.setProfili(l);
				
				utentiService.saveUtenteFromModificaAccount(nuovoUtente, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						AbiMessageBox.messageSuccessAlertBox("Utente modificato con successo", AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE);
						setOriginalFieldValuesModifieds();
						password.clear();
						confirmPassword.clear();
						w.close();						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
					}
				});

//				utentiService.saveUtente(nuovoUtente, true,	new AsyncCallback<Boolean>() {
//					@Override
//					public void onSuccess(Boolean result) {
//						AbiMessageBox.messageSuccessAlertBoxWorking("Utente modificato con successo", AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE);
//						setOriginalFieldValuesModifieds();
//						password.clear();
//						confirmPassword.clear();
//						w.close();
//						
//					}
//
//					@Override
//					public void onFailure(Throwable caught) {
//						if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
//							AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
//					}
//				
//				});
			}
		});
	}
}