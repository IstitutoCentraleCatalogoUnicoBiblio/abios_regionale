package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.UserModel;
import it.inera.abi.gxt.client.mvc.model.forms.UtenteCompletoFormModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
