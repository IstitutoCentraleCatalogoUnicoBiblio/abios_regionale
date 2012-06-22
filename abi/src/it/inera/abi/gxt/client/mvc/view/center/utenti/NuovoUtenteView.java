package it.inera.abi.gxt.client.mvc.view.center.utenti;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.costants.CostantiGestioneUtenti;
import it.inera.abi.gxt.client.mvc.model.forms.ProfiliUtente;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.FormCompletoUtenteCreazioneModifica;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class NuovoUtenteView extends View {

	private LayoutContainer nuovoUtentePanel = null;
	private FormCompletoUtenteCreazioneModifica nuovoUtenteFormPanel = null;

	public NuovoUtenteView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
		nuovoUtentePanel = new LayoutContainer();
		nuovoUtentePanel.setLayout(new FitLayout());
		createNuovoUtenteForm();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.NuovoUtente) {
			nuovoUtenteFormPanel.refresh();
			nuovoUtenteFormPanel.setProfili((ArrayList<ProfiliUtente>) event.getData("profili"));
			nuovoUtenteFormPanel.setModifica(false);
			nuovoUtenteFormPanel.riorganizzaFieldPerModificaByAdmin();
			
			LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
			wrapper.removeAll();
			nuovoUtenteFormPanel.layout();
			wrapper.add(nuovoUtentePanel);
			wrapper.layout();
			return;
		}
	}

	private void createNuovoUtenteForm() {

		nuovoUtenteFormPanel = new FormCompletoUtenteCreazioneModifica();
		nuovoUtenteFormPanel.setStyleAttribute("marginTop", "0px");
		nuovoUtentePanel.add(nuovoUtenteFormPanel);
	}
}
