package it.inera.abi.gxt.client.mvc.view.center.biblioteche;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.RicercaBiblioViaCodicePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaBibliotechePanel;
import it.inera.abi.gxt.client.resources.Resources;

import java.util.HashMap;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.user.client.Window;

public class RicercaBiblioViaCodiceView extends View {

	private ContentPanel mainPanel;
	private ListaBibliotechePanel listaBiblioPanel = null;
	private RicercaBiblioViaCodicePanel formRicercaBiblio = null;
	private CardLayout layout;
	
	public RicercaBiblioViaCodiceView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setHeading("Ricerca via codice");
		mainPanel.setIcon(Resources.ICONS.find());
		
		layout = new CardLayout();
		
		formRicercaBiblio = new RicercaBiblioViaCodicePanel();
		
		listaBiblioPanel = new ListaBibliotechePanel();
		listaBiblioPanel.setRicerca(true, false);
		
		mainPanel.add(formRicercaBiblio);
		mainPanel.add(listaBiblioPanel);
		
		mainPanel.setLayout(layout);		
		layout.setActiveItem(mainPanel.getItem(0));
	}
	
	@Override
	protected void handleEvent(AppEvent event) {
		LayoutContainer wrapper = null;
		if (event.getType() == AppEvents.RicercaBiblioCodice) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			formRicercaBiblio.resetAll();
			layout.setActiveItem(formRicercaBiblio);
			wrapper.add(mainPanel);
			wrapper.layout(true);
		}
		else if (event.getType() == AppEvents.VisualizzaRicercaBiblioViaCodice) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			layout.setActiveItem(formRicercaBiblio);
			wrapper.layout(true);
		}
		else if (event.getType() == AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);

			Boolean fromModifica = event.getData(CostantiGestioneBiblio.FROMMODIFICA);
			if (fromModifica == null) {/* Questo evento è stato lanciato dal pannello della form di Ricerca Via Codice */
				final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("codice"));
				listaBiblioPanel.setKeys(true, keys);

			}
			else {/* Questo evento è stato lanciato dal pannello della modifica della biblioteca selezionata */
				wrapper.removeAll();
				listaBiblioPanel.getGrid().getStore().getLoader().load();
				wrapper.add(mainPanel);
			
			}
			
			layout.setActiveItem(listaBiblioPanel);
			wrapper.layout(true);
		}
		if (wrapper != null) {
			wrapper.fireEvent(Events.Resize);
		}
		
	}
	
}