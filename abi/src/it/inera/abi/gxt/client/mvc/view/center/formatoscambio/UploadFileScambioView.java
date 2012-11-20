package it.inera.abi.gxt.client.mvc.view.center.formatoscambio;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget.UploadFileDiScambioPanel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;

/**
 * View utilizzata per l'inizializzazione del pannello contenente la form
 * per il caricamento del file da validare e importare e la relativa gestione degli eventi  
 *
 */
public class UploadFileScambioView extends View {

	private ContentPanel mainPanel;
	private UploadFileDiScambioPanel uploadFileScambioForm;

	public UploadFileScambioView(Controller controller) {
		super(controller);

	}

	@Override
	protected void initialize() {
		mainPanel = new ContentPanel();
		createFormUploadFileScambio();
	}

	private void createFormUploadFileScambio() {
		uploadFileScambioForm = new UploadFileDiScambioPanel();

	}

	@Override
	protected void handleEvent(AppEvent event) {
		LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);
		wrapper.removeAll();

		if (event.getType() == AppEvents.UploadFileDiScambio) {
			// setto email e pwd dell'utente loggato
			String email = UIAuth.getUtentiAuthModel().getEmail();
			String username = UIAuth.getUtentiAuthModel().getUserLogin();
	
			uploadFileScambioForm.setEmail(email);
			uploadFileScambioForm.setUsername(username);
			uploadFileScambioForm.resetFileField();
			
			mainPanel.setLayout(new CenterLayout());
			mainPanel.setHeading(CostantiFormatoScambio.FORM_UPLOAD);
			mainPanel.add(uploadFileScambioForm);
		}
		wrapper.add(mainPanel);
		wrapper.layout();

	}

}
