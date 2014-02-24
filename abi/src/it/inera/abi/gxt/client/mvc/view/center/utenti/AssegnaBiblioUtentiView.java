package it.inera.abi.gxt.client.mvc.view.center.utenti;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms.RicercaBiblioFormPanel;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.AssegnaBiblioPanel;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.AssegnaUtentiPanel;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * View utilizzata per l'inizializzazione dei pannelli per
 * assegnare biblioteche agli utenti e la relativa gestione degli eventi
 *
 */
public class AssegnaBiblioUtentiView extends View {

	private ContentPanel mainPanel;
	private LayoutContainer biblioToUtentiPanel = null;	
	private AssegnaUtentiPanel assegnaUtentiPanel = null;
	private AssegnaBiblioPanel assegnaBiblioPanel = null;
	private RicercaBiblioFormPanel formRicercaBiblio = null;
	private CardLayout layout = new CardLayout();

	public AssegnaBiblioUtentiView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		mainPanel.setHeading("Assegna biblioteche ad utenti");
		createAssegnaBiblioToUtenti();
	}

	private void createAssegnaBiblioToUtenti() {
		layout = new CardLayout();
		
		assegnaUtentiPanel = new AssegnaUtentiPanel();
		assegnaUtentiPanel.setService();
		assegnaUtentiPanel.setGrid();
		assegnaUtentiPanel.setTopToolbar();
		assegnaUtentiPanel.setBottomToolbar();
		
		formRicercaBiblio = new RicercaBiblioFormPanel();
		formRicercaBiblio.setForwardEventType(AppEvents.FiltraListaBiblioInAssegnazioneUtentiBiblio);		
		
		assegnaBiblioPanel = new AssegnaBiblioPanel();
		assegnaBiblioPanel.setService();
		assegnaBiblioPanel.setGrid();
		assegnaBiblioPanel.setTopToolbar();
		assegnaBiblioPanel.setBottomToolbar();
		
		biblioToUtentiPanel = new LayoutContainer();
		
		mainPanel.add(assegnaUtentiPanel);
		mainPanel.add(formRicercaBiblio);
		mainPanel.add(assegnaBiblioPanel);
		mainPanel.add(biblioToUtentiPanel);
		
		mainPanel.setLayout(layout);
   
	    layout.setActiveItem(mainPanel.getItem(0));
	    
	}
	
	
	@Override
	protected void handleEvent(AppEvent event) {
		//Entro nella lista utenti la prima volta, rimuovo gli utenti selezionati
		if (event.getType() == AppEvents.AssegnaListeAUtenti) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			assegnaUtentiPanel.clearUsersSelected();
			layout.setActiveItem(assegnaUtentiPanel);
			wrapper.add(mainPanel);
			wrapper.layout();
			
		}
		//Torno indietro o dallal lista biblioteche o dalla ricerca e mantenfo gli utenti slezionati
		else if (event.getType() == AppEvents.VisualizzaListaUtentiInModifica) {
			/* CASO tornaIndietro dal pannello lista biblioteche per un'ulteriore aggiunta alla lista utenti */
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			layout.setActiveItem(assegnaUtentiPanel);
			wrapper.layout();			
			
		}
		//Entro nel form di ricerca la prima volta e resetto il form 
		else if (event.getType() == AppEvents.RicercaListaBiblioInAssegna) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			
			List<Integer> userslist = (List<Integer>) event.getData("idUsers");
			formRicercaBiblio.setIdUsers(userslist);
			formRicercaBiblio.reset();
			formRicercaBiblio.layout();
	
			layout.setActiveItem(formRicercaBiblio);
			wrapper.layout();
		}
		//Torno indietro dalla lista biblioteche al form di ricerca (non resetto il form)
		else if (event.getType() == AppEvents.RicercaListaBiblioInAssegnaIndietro) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			
			List<Integer> userslist = (List<Integer>) event.getData("idUsers");
			formRicercaBiblio.setIdUsers(userslist);
			formRicercaBiblio.layout();

			layout.setActiveItem(formRicercaBiblio);
			wrapper.layout();
		}
		//Entro nella lista biblioteche dopo la ricerca
		else if (event.getType() == AppEvents.FiltraListaBiblioInAssegnazioneUtentiBiblio) {
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
						
			List<Integer> userslist = (List<Integer>) event.getData("idUsers");
			assegnaBiblioPanel.setUsersList(userslist);
			final HashMap<String, Object> keys = (HashMap<String, Object>) (event.getData("parametriRicerca"));
			assegnaBiblioPanel.setKeys(keys);
			assegnaBiblioPanel.clearBib();
			
			layout.setActiveItem(assegnaBiblioPanel);
			wrapper.layout();
		}

	}

}
