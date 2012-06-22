package it.inera.abi.gxt.client.mvc.view.center.report.widget;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.ui.RadioButton;

public class GestioneReportFormTipoStampaPanel extends GestioneReportFormBasePanel {
	protected FieldSet tipoStampaFields;
	protected final RadioButton formatoStampaEtichetta;
	protected final RadioButton formatoStampaLista;
	
	public GestioneReportFormTipoStampaPanel() {
		super();
		
		LayoutContainer tipoStampaTitle = new LayoutContainer();
		tipoStampaTitle.setStyleAttribute("padding", "5px");
		
		tipoStampaFields = new FieldSet();
		Utils.setFieldSetProperties(tipoStampaFields, "Tipo di stampa");
		tipoStampaFields.setExpanded(true);
		
		LayoutContainer tipoStampaTreColonne = new LayoutContainer(new TableLayout(3));

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
		
		/*FORMATO */		
		Text formatoStampaLabel = new Text("Formato:");
		formatoStampaLabel.setStyleAttribute("fontSize", "14px");
		LayoutContainer formatoStampaRadioButton = new LayoutContainer(new FlowLayout());
		formatoStampaRadioButton.setAutoWidth(true);
		formatoStampaEtichetta = new RadioButton("formatoStampa");
		formatoStampaEtichetta.setHTML("<label style=\"font-size:12px;\"> Etichetta <label>");
//		formatoStampaEtichetta.setFocus(true);
//		formatoStampaEtichetta.setValue(true);
		formatoStampaLista = new RadioButton("formatoStampa");
		formatoStampaLista.setHTML("<label style=\"font-size:12px;\"> Lista <label>");
		formatoStampaLista.setFocus(true);
		formatoStampaLista.setValue(true);

		formatoStampaRadioButton.add(formatoStampaEtichetta);
		formatoStampaRadioButton.add(formatoStampaLista);
		
		tipoStampaTreColonne.add(formatoStampaLabel, dataCaratteristicheTreColonne1);
		tipoStampaTreColonne.add(formatoStampaRadioButton, dataCaratteristicheTreColonne2);
		tipoStampaTreColonne.add(new LayoutContainer(), dataCaratteristicheTreColonne3);
				
		tipoStampaFields.add(tipoStampaTreColonne);
		tipoStampaTitle.add(tipoStampaFields);
		add(tipoStampaTitle);
		
		LayoutContainer centerButtons = new LayoutContainer(new CenterLayout());

		centerButtons.setStyleAttribute("marginBottom","5px");
		centerButtons.setBorders(true);

		centerButtons.setHeight(50);
		Button avviaRicerca = new Button("AVVIA RICERCA");
		Utils.setStylesButton(avviaRicerca);

		avviaRicerca.setSize(110, 30);
		avviaRicerca.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				
				setSelectedParameters();
				
				if (formatoStampaEtichetta.getValue()) {
					keys.put("tipoDiStampa", "etichetta");
					labels.put("tipoDiStampa", "etichetta");
				}
				else if (formatoStampaLista.getValue()) {
					keys.put("tipoDiStampa", "lista");
					labels.put("tipoDiStampa", "lista");
					
				}
				
				/* Avviso se l'utente sbaglia nell'inserimento delle date aggiornamento;
				 * si è scelto di commentarlo e far vedere all'utente la lista delle biblioteche vuota. */
				
//				if (((Date)dalGiorno.getValue()).after((Date)alGiorno.getValue())) {
//					MessageBox.alert("Avviso", "Date inserite non corrette", null);
//					return;
//				}	
				
				AppEvent event = new AppEvent(AppEvents.VisualizzaRisultatiReport);
				event.setData("filtriRisultati", keys);
				event.setData("filtriRisultatiLabel", labels);

				Dispatcher.forwardEvent(event);
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
		buttons.add(avviaRicerca);
		buttons.add(reset);
		
		
		
		centerButtons.add(buttons);

		add(tipoStampaTitle);	
		setBottomComponent(centerButtons);


	}
	
	public void resetAll() {
		resetForms();
		
		/* Parametri formato di stampa */
		formatoStampaEtichetta.setValue(false);
		formatoStampaLista.setValue(true);
		if (tipoStampaFields != null)
			tipoStampaFields.setExpanded(true);
	}
	
}