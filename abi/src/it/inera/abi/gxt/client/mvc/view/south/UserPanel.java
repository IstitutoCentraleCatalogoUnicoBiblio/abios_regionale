package it.inera.abi.gxt.client.mvc.view.south;

import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiApplicativo;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;
import it.inera.abi.gxt.client.mvc.view.center.utenti.widget.ModificaAccountWindow;
import it.inera.abi.gxt.client.resources.Resources;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

/**
 * Classe per la modifica dei dati dell'utente attualmente loggato
 * e per il logout
 *
 */
public class UserPanel extends LayoutContainer {

	protected Label userDisplay = null;
	protected ModificaAccountWindow modificaAccountWindow = null;

	public UserPanel() {
		modificaAccountWindow = new ModificaAccountWindow();
		ToolBar toolbar = getToolbar();
		/* setta il font-weight del testo a bold */
		toolbar.addStyleName("font-weight-style");
		add(toolbar);
	}

	protected ToolBar getToolbar() {
		ToolBar toolBar = new ToolBar();  
		
		userDisplay = new Label("Utente:");

		UtentiAuthModel utentiAuthModel = UIAuth.getUtentiAuthModel();
		/* aggiorno la label con il nome dell'utente loggato */
		if (utentiAuthModel != null) {
			/* metto solo la login */
			String userLabel = "<B>Utente operativo:</B>&nbsp;" + utentiAuthModel.getUserLogin();
			userDisplay.setText(userLabel);
		}

		toolBar.add(userDisplay);
		toolBar.add(new SeparatorToolItem());
		
		Button accountButton = new Button("Modifica account", Resources.ICONS.user_edit()); 
		accountButton.addStyleName("my-button");
		accountButton.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				modificaAccountWindow.show();
			}
		});
		
		toolBar.add(accountButton);
		toolBar.add(new SeparatorToolItem());

		Button logoutButton = new Button("Logout", Resources.ICONS.user_logout()); 
		
		logoutButton.addStyleName("my-button");
		logoutButton.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {

				final MessageBox box = new MessageBox();
				box.setIcon(MessageBox.QUESTION);
				box.setTitle("Conferma logout");
				box.setMessage("Uscire dall'applicazione?");
				box.setButtons(MessageBox.YESNO);
				box.show();

				final Listener<MessageBoxEvent> msgBoxListner = new Listener<MessageBoxEvent>() {

					public void handleEvent(final MessageBoxEvent be) {
						if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
							Window.Location.assign(GWT.getHostPageBaseURL() + "j_spring_security_logout");
						}
					}
				};
				box.addCallback(msgBoxListner);
			}
		});
		toolBar.add(logoutButton);
		
		Label version = new Label();
		String versionLabel = "Versione: " + CostantiApplicativo.VERSION_NUMBER + "&nbsp;&nbsp;&nbsp;";
		version.setWidth(200);
		version.setText(versionLabel);
		
		toolBar.add(new FillToolItem());
		toolBar.add(version);
		return toolBar;
	}

	public void updateModel(UtentiAuthModel utentiAuthModel) {
		String login = utentiAuthModel.getUserLogin();
		if (utentiAuthModel != null) userDisplay.setText("Utente:" + login);
	}

}
