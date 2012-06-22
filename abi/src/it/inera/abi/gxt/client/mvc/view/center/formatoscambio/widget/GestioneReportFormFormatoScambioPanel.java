package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.view.center.report.widget.GestioneReportFormBasePanel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

public class GestioneReportFormFormatoScambioPanel extends GestioneReportFormBasePanel {
	
	private TextField<String> emailNotifica = null;
	protected FieldSet formatoScambioFields;
	protected final CheckBox differito;
	
	public GestioneReportFormFormatoScambioPanel() {
		super();

		LayoutContainer formatoScambioTitle = new LayoutContainer();
		formatoScambioTitle.setStyleAttribute("padding", "5px");
		
		LayoutContainer formatoScambioTreColonne = new LayoutContainer(new TableLayout(3));

		TableData dataCaratteristicheTreColonne1 = new TableData();
		
		dataCaratteristicheTreColonne1.setWidth("10%");
		dataCaratteristicheTreColonne1.setMargin(5);
		dataCaratteristicheTreColonne1.setPadding(10);

		TableData dataCaratteristicheTreColonne2 = new TableData();
		dataCaratteristicheTreColonne2.setWidth("20%");
		dataCaratteristicheTreColonne2.setMargin(5);
		dataCaratteristicheTreColonne2.setPadding(10);

		TableData dataCaratteristicheTreColonne3 = new TableData();
		dataCaratteristicheTreColonne3.setWidth("20%");
		dataCaratteristicheTreColonne3.setMargin(5);
		dataCaratteristicheTreColonne3.setPadding(10);
		
		formatoScambioFields = new FieldSet();
		Utils.setFieldSetProperties(formatoScambioFields, "Formato di scambio");
		formatoScambioFields.setExpanded(true);
		
		
		FormData data = new FormData();
		data.setMargins(new Margins(5));
		FormLayout formEmail = new FormLayout();
		formEmail.setLabelAlign(LabelAlign.TOP);
		
		/* FORMATO */
		Text formatoScambioLabel = new Text("Formato:");
		formatoScambioLabel.setStyleAttribute("fontSize", "12px");
		LayoutContainer formatoScambioRadioButton = new LayoutContainer(new FlowLayout());
		formatoScambioRadioButton.setAutoWidth(true);

		differito = new CheckBox();
		differito.setBoxLabel("Differito");
		
		formatoScambioRadioButton.add(differito, data);

		LayoutContainer notificaEmail = new LayoutContainer(formEmail);

		emailNotifica = new TextField<String>();
		emailNotifica.setFieldLabel("Indirizzo E-Mail (per notifica)");
		emailNotifica.setAllowBlank(false);
		
		notificaEmail.add(emailNotifica);
		
		formatoScambioTreColonne.add(formatoScambioLabel, dataCaratteristicheTreColonne1);
		formatoScambioTreColonne.add(formatoScambioRadioButton, dataCaratteristicheTreColonne2);
		formatoScambioTreColonne.add(notificaEmail, dataCaratteristicheTreColonne3);
		
		formatoScambioFields.add(formatoScambioTreColonne);
		formatoScambioTitle.add(formatoScambioFields);
		add(formatoScambioTitle);
		
		LayoutContainer avviaRicerca = new LayoutContainer(new CenterLayout());

		avviaRicerca.setStyleAttribute("marginBottom","5px");
		avviaRicerca.setBorders(true);
	
		avviaRicerca.setHeight(50);
		Button avviaRicercaButton = new Button("AVVIA RICERCA");
		Utils.setStylesButton(avviaRicercaButton);

		avviaRicercaButton.setSize(110, 30);
		avviaRicercaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				setSelectedParameters();
				
				if (differito.getValue()) {
					keys.put("differito", "true");
					labels.put("differito", "true");
				}
				else {
					keys.put("differito", "false");
					labels.put("differito", "false");
				}
				
				if (emailNotifica.getValue() != null && ((String)emailNotifica.getValue()).length() != 0) {
					if (validateEmail(emailNotifica.getValue())) {
						keys.put("emailNotifica", (String)emailNotifica.getValue());
						labels.put("emailNotifica", (String)emailNotifica.getValue());
						
						AppEvent event = new AppEvent(AppEvents.VisualizzaRisultatiFormatoDiScambio);
						event.setData("filtriRisultati", keys);
						event.setData("filtriRisultatiLabel", labels);
	
						Dispatcher.forwardEvent(event);
					}
					else AbiMessageBox.messageErrorAlertBox("L'indirizzo inserito non è corretto. " +
							"La procedura dovrà essere ripetuta.", "Avviso E-mail notifica");
				}
				else if (emailNotifica.getValue() == null)
				AbiMessageBox.messageErrorAlertBox("Inserire un indirizzo e-mail.", "Avviso E-mail notifica");
				
			}
		});
	
		
		Button reset = new Button("RESET");
		Utils.setStylesButton(reset);
		reset.setSize(110, 30);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				resetAll();
			}
		});
		
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(avviaRicercaButton);
		buttons.add(reset);
		
		avviaRicerca.add(buttons);
		
		add(formatoScambioTitle);
		setBottomComponent(avviaRicerca);
	
	}
	
	private boolean validateEmail(String email) {
		final String EMAIL_VALIDATION_REGEX = "[a-z0-9!#$%&'*+/" +  
			"=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-" +  
			"z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"; 
		if (email.matches(EMAIL_VALIDATION_REGEX))
			return true;
		else return false;
	}
	
	public void setEmail(String email) {
		this.emailNotifica.setValue(email);
	}
	
	public void resetAll() {
		resetForms();
		
		differito.setValue(false);
		String userEmail = UIAuth.getUtentiAuthModel().getEmail();
		setEmail(userEmail);
		if (formatoScambioFields != null)
			formatoScambioFields.setExpanded(true);
		
		
	}

}
