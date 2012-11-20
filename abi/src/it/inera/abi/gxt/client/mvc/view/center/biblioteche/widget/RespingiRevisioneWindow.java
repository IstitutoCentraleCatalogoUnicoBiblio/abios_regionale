package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Estensione della classe <code>RevisioneWindow</code> che permette di 
 * respingere la revisione di una biblioteca
 *
 */
public class RespingiRevisioneWindow extends RevisioneWindow {

	public RespingiRevisioneWindow(String header, String textButton) {
		super(header, textButton);
	}

	@Override
	public void bind() {
		submit.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(final ComponentEvent be) {
				_instance.hide();
				
				final LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
				wrapper.mask("Respingi Revisione in corso...", "x-mask-loading");
				
				final BibliotecheServiceAsync bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);
				bibliotecheService.respingiRevisione(biblioModel.getIdBiblio(), messaggio.getValue(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						AbiMessageBox.messageSuccessAlertBox("La revisione della biblioteca è stata respinta", "Esito respingi revisione");
						if (ricercaGenerica) {
							AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblio);
							event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
							event.setData(CostantiGestioneBiblio.ISMASKED, new Boolean(true));
							Dispatcher.forwardEvent(event);
						}							
						else {
							AppEvent event = new AppEvent(AppEvents.FiltraListaBiblioInRicercaBiblioViaCodice);
							event.setData(CostantiGestioneBiblio.FROMMODIFICA, new Boolean(true));
							event.setData(CostantiGestioneBiblio.ISMASKED, new Boolean(true));
							Dispatcher.forwardEvent(event);
						}
						
					}
					@Override
					public void onFailure(Throwable caught) {
						UIAuth.checkIsLogin(caught.toString()); {// controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nel respingere la revisione della biblioteca", "Errore");
						}
						
						wrapper.unmask();
					}
				});
			}
		});
	}

}
