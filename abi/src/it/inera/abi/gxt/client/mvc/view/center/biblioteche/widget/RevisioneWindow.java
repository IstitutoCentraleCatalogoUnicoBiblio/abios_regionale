package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;

/**
 * Permette di inviare un msg all'utente in fase di accettazione o respinta della revisione
 * @author reschini
 *
 */
public abstract class RevisioneWindow extends Window {

	private FormPanel formMessaggioRevisione;
	protected Window _instance;

	protected BiblioModel biblioModel;

	protected Button submit;
	
	protected TextArea messaggio;
	
	protected boolean ricercaGenerica;
	


	public RevisioneWindow(String header, String textButton) {
		setSize(450, 400);  
		setFrame(true);  
		setModal(true);  
		setLayout(new FitLayout());  
		setHeading(header);
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		createForm();  
		submit.setText(textButton);
		add(formMessaggioRevisione, fitData);  
		setResizable(false);
		addListener(Events.Hide, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formMessaggioRevisione.reset();
			}
		});
		_instance = this;
	}
	
	@Override
	protected void onShow() {
		super.onShow();
		messaggio.clear();
	}

	private void createForm() {  

		FormData formData = new FormData("-20");  
		formMessaggioRevisione = new FormPanel();

		formMessaggioRevisione.setBorders(false);
		formMessaggioRevisione.setHeaderVisible(false);
		
		Text alertDataModificaText = new Text(AbiMessageBox.MESSAGGIO_WARNING_DATA_MODIFICA_REVISIONE);
		
		formMessaggioRevisione.add(alertDataModificaText);
		
		messaggio = new TextArea();  
		messaggio.setAllowBlank(true);  
		messaggio.setFieldLabel("Motivo");
		messaggio.setSize(400, 200);
		messaggio.setStyleAttribute("margin-top", "30px");
		
		formMessaggioRevisione.add(messaggio, formData);  

		Button cancel = new Button("Annulla");
		cancel.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				_instance.hide();
			}
		});
		formMessaggioRevisione.addButton(cancel); 


		Button reset = new Button("Reset");
		reset.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formMessaggioRevisione.reset();
			}
		});
		formMessaggioRevisione.addButton(reset);

		submit = new Button();
		formMessaggioRevisione.addButton(submit);  

		formMessaggioRevisione.setButtonAlign(HorizontalAlignment.CENTER);  

//		FormButtonBinding binding = new FormButtonBinding(formMessaggioRevisione);  
//		binding.addButton(submit);  
	}	
	
	public void setBiblioModel(BiblioModel biblioModel) {
		this.biblioModel = biblioModel;
	}
	
	public void setRicerca(boolean ricerca) {
		this.ricercaGenerica = ricerca;
	}
	
	public abstract void bind();

}