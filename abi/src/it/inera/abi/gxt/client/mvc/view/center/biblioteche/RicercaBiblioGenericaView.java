package it.inera.abi.gxt.client.mvc.view.center.biblioteche;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.RicercaBiblioGenericaPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaBibliotecheImportatePanel;
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

/**
 * View utilizzata per l'inizializzazione del pannello di ricerca generica
 * delle biblioteche, le liste dei risultati (con particolare riferimento alle
 * biblioteche importate) e la relativa gestione degli eventi
 *
 */
public class RicercaBiblioGenericaView extends View {

	private ContentPanel mainPanel;

	private CardLayout layout;
	
	private RicercaBiblioGenericaPanel formRicercaBiblio = null;
	private ListaBibliotechePanel listaBibliotechePanel = null;
	private ListaBibliotecheImportatePanel listaBibliotecheImportatePanel = null;
	
	public RicercaBiblioGenericaView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setHeading("Ricerca generica");
		mainPanel.setIcon(Resources.ICONS.find());
		
		layout = new CardLayout();

		formRicercaBiblio = new RicercaBiblioGenericaPanel();

		listaBibliotechePanel = new ListaBibliotechePanel();
		listaBibliotechePanel.setRicerca(true, true);
		
		listaBibliotecheImportatePanel = new ListaBibliotecheImportatePanel();
		listaBibliotecheImportatePanel.setRicerca(true, true);
		
		mainPanel.add(formRicercaBiblio);
		mainPanel.add(listaBibliotechePanel);
		mainPanel.add(listaBibliotecheImportatePanel);
		
		mainPanel.setLayout(layout);
		layout.setActiveItem(mainPanel.getItem(0));
	}

	@Override
	protected void handleEvent(AppEvent event) {
		LayoutContainer wrapper = null;
		if (event.getType() == AppEvents.RicercaBiblioGenerica) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			formRicercaBiblio.resetAll();
			
			listaBibliotechePanel.setViaUtenti(false);
			listaBibliotechePanel.setDirectList(new Boolean(false));
			listaBibliotechePanel.checkIndietroButton();
			
			listaBibliotecheImportatePanel.setViaUtenti(false);
			listaBibliotecheImportatePanel.setDirectList(new Boolean(false));
			listaBibliotecheImportatePanel.checkIndietroButton();
			
			layout.setActiveItem(formRicercaBiblio);
			wrapper.add(mainPanel);
			wrapper.layout(true);
		}
		else if(event.getType() == AppEvents.VisualizzaRicercaBiblioGenerica) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			layout.setActiveItem(formRicercaBiblio);
			wrapper.layout(true);
		}
		else if (event.getType() == AppEvents.FiltraListaBiblioInRicercaBiblio) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);

			Boolean fromModifica = event.getData(CostantiGestioneBiblio.FROMMODIFICA);
			if (fromModifica == null) {/* Questo evento è stato lanciato dal pannello della form di Ricerca Generica */
				final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("parametriRicerca"));
				
				if (keys != null && keys.containsKey("importate") && keys.get("importate").equals("true")) {
					listaBibliotecheImportatePanel.setKeys(false, keys);
					listaBibliotecheImportatePanel.getGrid().getSelectionModel().deselectAll();
					listaBibliotecheImportatePanel.setViaUtenti(false);
					listaBibliotecheImportatePanel.setDirectList(new Boolean(false));
					listaBibliotecheImportatePanel.checkIndietroButton();
					layout.setActiveItem(listaBibliotecheImportatePanel);
				} 
				else {
					listaBibliotechePanel.setKeys(false, keys);
					listaBibliotechePanel.getGrid().getSelectionModel().deselectAll();
					listaBibliotechePanel.setViaUtenti(false);
					listaBibliotechePanel.setDirectList(new Boolean(false));
					listaBibliotechePanel.checkIndietroButton();
					layout.setActiveItem(listaBibliotechePanel);
				}
				
			} 
			else if (fromModifica) {/* Questo evento è stato lanciato dal pannello della modifica dell'ultima biblioteca selezionata */
				wrapper.removeAll();
//				if (listaBibliotechePanel.getKeys() == null || (listaBibliotechePanel.getKeys() != null	&& listaBibliotechePanel.getKeys().containsKey("importate") && listaBibliotechePanel.getKeys().get("importate").equals("false"))){
				if (layout.getActiveItem() instanceof ListaBibliotechePanel) {
					listaBibliotechePanel.getGrid().getSelectionModel().deselectAll();
					listaBibliotechePanel.getGrid().getStore().getLoader().load();
					listaBibliotechePanel.setViaUtenti(false);
					wrapper.add(mainPanel);
					layout.setActiveItem(listaBibliotechePanel);
					
				} else {
					listaBibliotecheImportatePanel.getGrid().getSelectionModel().deselectAll();
					listaBibliotecheImportatePanel.getGrid().getStore().getLoader().load();
					listaBibliotecheImportatePanel.setViaUtenti(false);
					wrapper.add(mainPanel);
					layout.setActiveItem(listaBibliotecheImportatePanel);
				}
			}
			else {/* Questo evento è stato lanciato dal pannello della lista utenti tramite il pulsante vedi biblioteche di un dato utente */				
				wrapper.removeAll();
				wrapper.add(mainPanel);
				
				final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("parametriRicerca"));
				
				listaBibliotechePanel.setKeys(false, keys);
				listaBibliotechePanel.setViaUtenti(true);
				listaBibliotechePanel.setDirectList(new Boolean(false));
				listaBibliotechePanel.checkIndietroButton();
				listaBibliotechePanel.disableButtons();
				layout.setActiveItem(listaBibliotechePanel);
			}
			
			wrapper.layout(true);
			Boolean isMasked = event.getData(CostantiGestioneBiblio.ISMASKED);
			if (isMasked != null && isMasked.booleanValue() == true) {
				wrapper.unmask();
			}
			
		}
		
		else if (event.getType() == AppEvents.ListaBiblioteche) {
			wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("parametriRicerca"));
			
			
			if (keys != null && keys.containsKey("importate") && keys.get("importate").equals("true")) {
				listaBibliotecheImportatePanel.setKeys(false, keys);
				listaBibliotecheImportatePanel.getGrid().getSelectionModel().deselectAll();
				listaBibliotecheImportatePanel.setDirectList(new Boolean(true));
				listaBibliotecheImportatePanel.checkIndietroButton();
				layout.setActiveItem(listaBibliotecheImportatePanel);
			} else {
				listaBibliotechePanel.setKeys(false, keys);
				listaBibliotechePanel.getGrid().getSelectionModel().deselectAll();
				listaBibliotechePanel.setDirectList(new Boolean(true));
				listaBibliotechePanel.checkIndietroButton();
				layout.setActiveItem(listaBibliotechePanel);
			}
			
			wrapper.add(mainPanel);
			wrapper.layout(true);
		}
		if (wrapper != null) {
			wrapper.fireEvent(Events.Resize);
		}
	}

}