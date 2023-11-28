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

package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe che permette la visualizzazione della form di modifica delle 
 * informazioni principali dell'utente attualmente loggato
 *
 */
public class ModificaAccountWindow extends Window {

	private UtentiServiceAsync utentiServiceAsync;
	private Window _instance;
	
	FormModificaAccount formModificaAccount;
	
	public ModificaAccountWindow() {
		utentiServiceAsync = Registry.get(Abi.UTENTI_SERVICE);
		
		formModificaAccount = new FormModificaAccount();
		
		setScrollMode(Scroll.NONE);
		setSize(550, 200);  
		setModal(true);  
		setResizable(false);
		setClosable(true);
		setLayout(new FitLayout());  
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		
		addListener(Events.Hide, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formModificaAccount.resetForm();
			}
		});
		addStyleName("font-weight-button");
		_instance = this;
	}


	@Override
	protected void onShow() {
		utentiServiceAsync.getDataUtente(UIAuth.getUtentiAuthModel().getUserId(), new AsyncCallback<UtenteCompletoFormModel>() {
			@Override
			public void onSuccess(final UtenteCompletoFormModel result) {
				DeferredCommand.addCommand(new Command() {
					
					@Override
					public void execute() {
						formModificaAccount.setUtenteModifica(result);
						
						setHeading("Modifica utente "+result.getUserName());
						setIcon(Resources.ICONS.user_edit());
						
						if (!formModificaAccount.isRendered()) {
							formModificaAccount.setParameters();
							formModificaAccount.setClosingWindowOnModifica(ModificaAccountWindow.this);

							add(formModificaAccount);
							
						}
						else {
							formModificaAccount.setFieldValues(result);
						}
												
						layout();
						onShowDeferred();
					}
				});
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if (UIAuth.checkIsLogin(caught.toString())) {/* controllo se l'errore è dovuto alla richiesta di login */
					AbiMessageBox.messageErrorAlertBox("Impossibile caricare i dati dell'utente!", "ESITO CARICAMENTO");				
				}
			}
		});
	}
	
	public void onShowDeferred() {
		super.onShow();
	}
}
