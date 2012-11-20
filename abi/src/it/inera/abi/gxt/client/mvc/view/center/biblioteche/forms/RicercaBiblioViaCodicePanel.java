package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.TextFieldCustom;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;

/**
 * Classe per la ricerca via codice delle biblioteche
 *
 */
public class RicercaBiblioViaCodicePanel extends ContentPanel {
	
	protected HashMap<String, Object> keys = new HashMap<String, Object>();
	protected FormPanel formViaCodice;
	protected TextFieldCustom<String> codiceIsilField;

	private FieldSet fields;
	private Button ricerca;

	public RicercaBiblioViaCodicePanel() {
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);

		ricerca = new Button("Ricerca");
		Utils.setStylesButton(ricerca);
		ricerca.setSize(100, 20);

		LayoutContainer caratteristicheBiblio = new LayoutContainer();
		caratteristicheBiblio.setLayout(new FlowLayout());
		caratteristicheBiblio.setStyleAttribute("padding", "5px");

		fields = new FieldSet();
		Utils.setFieldSetProperties(fields, "Codice biblioteca");
		fields.setCollapsible(false);

		/* Crea i fields Stato - Provincia - Numero */
		createFields();		
		caratteristicheBiblio.add(fields);	

		add(caratteristicheBiblio);		

		ricerca.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (codiceIsilField.getValue() != null) {
					if (codiceIsilField.getValue().length() == 9) {
						keys.put("codiceIsil", ((String) codiceIsilField.getValue()).toUpperCase());

						AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
						event.setData("codice", keys);
						Dispatcher.forwardEvent(event);

					} else {
						AbiMessageBox.messageAlertBox(AbiMessageBox.CHECK_RICERCA_VIA_CODICE_MESSAGE, AbiMessageBox.CHECK_RICERCA_VIA_CODICE_TITLE);
					}

				}

			}
		});
		Button reset = new Button("Reset");
		Utils.setStylesButton(reset);
		reset.setSize(100, 20);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				codiceIsilField.clear();
				codiceIsilField.setValue("IT-");

			}
		});

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer d = new LayoutContainer(tableLayout);
		d.add(ricerca);
		d.add(reset);

		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setHeight(50);
		c.add(d);
		add(c);
	}

	public void createFields() {
		formViaCodice = new FormPanel();
		formViaCodice.setHeaderVisible(false);
		formViaCodice.setBorders(false);
		formViaCodice.setBodyBorder(false);
		formViaCodice.setHeight(50);
		formViaCodice.setLayout(new CenterLayout());	

		TableLayout t =new TableLayout(2);
		t.setWidth("100%");

		LayoutContainer ricercaViaCodiceDueColonne = new LayoutContainer(t);
		ricercaViaCodiceDueColonne.setWidth("100%");

		TableData ricercaViaCodiceDueColonne1 = new TableData();
		ricercaViaCodiceDueColonne1.setWidth("40%");
		ricercaViaCodiceDueColonne1.setHorizontalAlign(HorizontalAlignment.RIGHT);
		ricercaViaCodiceDueColonne1.setMargin(3);
		ricercaViaCodiceDueColonne1.setPadding(10);

		TableData ricercaViaCodiceDueColonne2 = new TableData();
		ricercaViaCodiceDueColonne2.setWidth("40%");
		ricercaViaCodiceDueColonne2.setMargin(3);
		ricercaViaCodiceDueColonne2.setPadding(10);

		/* Codice Isil */
		Text codiceIsilLabel = new Text("Codice Isil:");
		codiceIsilLabel.setStyleAttribute("fontSize", "14px");
		codiceIsilField = new TextFieldCustom<String>();
		codiceIsilField.setWidth(100);
		codiceIsilField.setMinLength(3);
		codiceIsilField.setMaxLength(9);
		codiceIsilField.setAllowBlank(false);

		codiceIsilField.setValidator(new Validator() {

			@Override
			public String validate(Field<?> field, String value) {
				String res = null;

				if (codiceIsilField.getValue() != null && codiceIsilField.getValue().length() == 9) {

					String isilStato = (codiceIsilField.getValue()).substring(0, 2);
					if (!isilStato.equalsIgnoreCase("IT")) {
						res = "Inserire un codice isil per lo stato valido";
					}

					if (!((codiceIsilField.getValue()).substring(2, 3)).equalsIgnoreCase("-")) {
						res = "Formato codice isil non valido";
					}

					String isilProvincia = (codiceIsilField.getValue()).substring(3, 5);
					if (!isilProvincia.matches("[a-zA-Z]{2}")) {
						res = "Inserire un codice isil per la provincia valido";
					}

					try {
						String isilNumeroString = (codiceIsilField.getValue()).substring(5, codiceIsilField.getValue().length());
						Integer isilNumero = Integer.valueOf(isilNumeroString);
					}
					catch (Exception e) {
						System.out.println(codiceIsilField.getValue());
						res = "Inserire un numero isil valido";
						return res;
					}

				}
				return res;
			}
		});

		ricercaViaCodiceDueColonne.add(codiceIsilLabel, ricercaViaCodiceDueColonne1);
		ricercaViaCodiceDueColonne.add(codiceIsilField, ricercaViaCodiceDueColonne2);

		FormButtonBinding f = new FormButtonBinding(formViaCodice);
		f.addButton(ricerca);		
		formViaCodice.add(ricercaViaCodiceDueColonne);

		fields.add(formViaCodice);
	}

	public void resetAll() {
		codiceIsilField.clear();
		codiceIsilField.setValue("IT-");
	}


	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		Utils.addKeyListenerForEnter(ricerca, formViaCodice); 
	}

}
