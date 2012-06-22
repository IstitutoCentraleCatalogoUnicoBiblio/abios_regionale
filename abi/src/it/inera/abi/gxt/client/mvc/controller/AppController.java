package it.inera.abi.gxt.client.mvc.controller;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.services.AuthServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AppController extends Controller {

	private AppView appView;

	public AppController() {
		registerEventTypes(AppEvents.AuthRequest);
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Error);
	}

	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.AuthRequest) {
			onAuth();
		} else if (type == AppEvents.Init) {
			onInit(event);
		} else if (type == AppEvents.Error) {
			onError(event);
		}
	}

	public void initialize() {
		appView = new AppView(this);
	}

	protected void onError(AppEvent ae) {
		System.out.println("error: " + ae.<Object> getData());
	}

	private void onInit(final AppEvent event) {
		forwardToView(appView, event);
		UIAuth.dispatchByRoles();
	}
	
	private void onAuth() {
		/**
		 * carico i dati dell'utente da mettere nel registry
		 */
		AuthServiceAsync authService =  Registry.get(Abi.AUTHSERVICE);
		authService.retrieveUser(new AsyncCallback<UtentiAuthModel>() {

			@Override
			public void onFailure(Throwable caught) {
				UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(UtentiAuthModel result) {
				// memorizzo l'utente nel registry
				Registry.register(Abi.USERLOGGED, result);
   
				Dispatcher.forwardEvent(AppEvents.Init);
			}
			
		});
	}

}