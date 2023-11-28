/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

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

/**
 * Controller per l'inizializzazione dell'applicativo e l'autenticazione da parte dell'utente 
 *
 */
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