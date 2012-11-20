package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;

/**
 * Classe per il caricamento del file da validare e importare
 * (online o in differita)
 *
 */
public class UploadFileDiScambioPanel extends FormPanel {

	private TextField<String> email = null;
	private TextField<String> username = null;
	private FileUploadField file = null;
	
	public UploadFileDiScambioPanel() {
		
		setHeading("Upload file di scambio");
		setHeaderVisible(true);
		setAction(GWT.getModuleBaseURL() + "uploadservlet");
		setEncoding(Encoding.MULTIPART);
		setMethod(Method.POST);
		setButtonAlign(HorizontalAlignment.CENTER);
		setWidth(350);

		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.TOP);

		email = new TextField<String>();
		email.setAllowBlank(false);
		email.setName("email");
		email.setToolTip("Inserire l'e-mail per la notifica e scegliere il file di scambio");
		email.setFieldLabel("Email (per la notifica)");
		final String emailReg  = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}"; 
		final String errMsg = "Inserire un indirizzo email corretto!";
		email.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				if (!value.toUpperCase().matches(emailReg)) { 
					return errMsg; 
				} 
				return null; 
			}
		});
		add(email, new FormData("90%"));
		
		username = new TextField<String>();
		username.setAllowBlank(false);
		username.setName("username");
		username.setVisible(false);
		add(username);

		file = new FileUploadField();
		file.setAllowBlank(false);
		file.setName("uploadedfile");
		file.setFieldLabel("File");
		add(file, new FormData("90%"));

		Button btnSubmit = new Button("Carica");
		Utils.setStylesButton(btnSubmit);
		btnSubmit.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!isValid()) {
					return;
				}
				submit();
				reset();
			}
		});
		
		/* 
		 * Submit : FormEvent(this, resultHtml)
		 * Fires after the form has been submitted. Only applies when using HTML submits.
		 * formPanel : this
		 * resultHtml : the response html
		 * 
		 */
		addListener(Events.Submit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				FormEvent formEvent = (FormEvent) be;
				if (formEvent.getResultHtml() != null && formEvent.getResultHtml().equalsIgnoreCase("ok")) {
					AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.UPLOAD_MESSAGE, AbiMessageBox.UPLOAD_TITLE, new Listener<MessageBoxEvent>() {
						@Override
						public void handleEvent(MessageBoxEvent be) {
							Dispatcher.get().dispatch(AppEvents.FileCaricatiNonControllati);
						}
					});
					
				} else if (formEvent.getResultHtml() != null && formEvent.getResultHtml().equalsIgnoreCase("error")) {
					AbiMessageBox.messageErrorAlertBox(AbiMessageBox.UPLOAD_ERROR_MESSAGE, AbiMessageBox.UPLOAD_TITLE);
				}
			}
		});
		addButton(btnSubmit);

		Button btnReset = new Button("Reset");
		Utils.setStylesButton(btnReset);
		btnReset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				reset();
			}
		});
		addButton(btnReset);
		setLayout(layout);
	}
	
	public void setEmail(String email) {
		this.email.setValue(email);
	}
	
	public void setUsername(String username) {
		this.username.setValue(username);
	}
	
	public void resetFileField() {
		file.clear();
	}
	
	@Override
	protected void onDetach() {
		super.onDetach();
		reset();
	}
}
