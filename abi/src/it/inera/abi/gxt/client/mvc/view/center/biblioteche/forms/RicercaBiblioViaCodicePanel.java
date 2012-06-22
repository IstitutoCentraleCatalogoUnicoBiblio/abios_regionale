package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Element;

public class RicercaBiblioViaCodicePanel extends ContentPanel {
	protected HashMap<String, Object> keys = new HashMap<String, Object>();
	protected FormPanel formViaCodice;
	protected TextField statoField;
	protected TextField provinciaField;
	protected NumberField numeroField;
	
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
				if (statoField.getValue() != null) {
					keys.put("codiceStato", (String) statoField.getValue());
				}
				if (provinciaField.getValue() != null) {
					keys.put("codiceProvincia", (String) provinciaField.getValue());
				}
				if (numeroField.getValue() != null) {
					keys.put("codiceNumero", ((Integer)((Number) numeroField.getValue()).intValue()).toString()); 
				}
				
				AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
				event.setData("codice", keys);
				Dispatcher.forwardEvent(event);
				
			}
		});
		Button reset = new Button("Reset");
		Utils.setStylesButton(reset);
		reset.setSize(100, 20);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				statoField.clear();
				statoField.setValue("IT");
				provinciaField.clear();
				numeroField.clear();
				
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
		
		TableLayout t =new TableLayout(6);
		t.setWidth("100%");
		
		LayoutContainer ricercaViaCodiceSeiColonne = new LayoutContainer(t);
		ricercaViaCodiceSeiColonne.setWidth("100%");
		
		TableData ricercaViaCodiceSeiColonne1 = new TableData();
		ricercaViaCodiceSeiColonne1.setWidth("10%");
		ricercaViaCodiceSeiColonne1.setHorizontalAlign(HorizontalAlignment.RIGHT);
		ricercaViaCodiceSeiColonne1.setMargin(3);
		ricercaViaCodiceSeiColonne1.setPadding(10);

		TableData ricercaViaCodiceSeiColonne2 = new TableData();
		ricercaViaCodiceSeiColonne2.setWidth("10%");
		ricercaViaCodiceSeiColonne2.setMargin(3);
		ricercaViaCodiceSeiColonne2.setPadding(10);
				
		/* STATO */
		Text statoLabel = new Text("Stato:");
		statoLabel.setStyleAttribute("fontSize", "14px");
		statoField = new TextField();
		statoField.setFieldLabel("STATO");
		statoField.setWidth(50);
		statoField.setMinLength(2);
		statoField.setMaxLength(2);
		statoField.setAllowBlank(false);
		statoField.setValue("IT");
		ricercaViaCodiceSeiColonne.add(statoLabel, ricercaViaCodiceSeiColonne1);
		ricercaViaCodiceSeiColonne.add(statoField, ricercaViaCodiceSeiColonne2);
		

		/* PROVINCIA */
		Text provinciaLabel = new Text("Provincia: ");
		provinciaLabel.setStyleAttribute("fontSize", "14px");
		provinciaField = new TextField();
		provinciaField.setWidth(50);
		provinciaField.setMinLength(2);
		provinciaField.setMaxLength(2);
		provinciaField.setAllowBlank(false);
		ricercaViaCodiceSeiColonne.add(provinciaLabel, ricercaViaCodiceSeiColonne1);
		ricercaViaCodiceSeiColonne.add(provinciaField, ricercaViaCodiceSeiColonne2);
		
		
		/* NUMERO */
		Text numeroLabel = new Text("Numero: ");
		numeroLabel.setStyleAttribute("fontSize", "14px");
		numeroField = new NumberField();
		numeroField.setWidth(50);
		numeroField.setMinLength(1);
		numeroField.setMaxLength(4);
		numeroField.setAllowBlank(false);
		ricercaViaCodiceSeiColonne.add(numeroLabel, ricercaViaCodiceSeiColonne1);
		ricercaViaCodiceSeiColonne.add(numeroField, ricercaViaCodiceSeiColonne2);
				
		FormButtonBinding f = new FormButtonBinding(formViaCodice);
		f.addButton(ricerca);		
		formViaCodice.add(ricercaViaCodiceSeiColonne);
		
		fields.add(formViaCodice);
	}
	
	public void resetAll() {
		statoField.clear();
		statoField.setValue("IT");
		provinciaField.clear();
		numeroField.clear();
	}

	
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		Utils.addKeyListenerForEnter(ricerca, formViaCodice); 
	}
}
