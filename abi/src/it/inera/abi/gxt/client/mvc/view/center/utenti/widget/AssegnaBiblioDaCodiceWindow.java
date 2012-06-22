package it.inera.abi.gxt.client.mvc.view.center.utenti.widget;

import java.util.List;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AssegnaBiblioDaCodiceWindow extends Window {
	private FormPanel formDaCodice;
	private Window _instance;
	
	private UtentiServiceAsync utentiService = null;
	private Grid<BiblioModel> grid;
	private List<Integer> bibSel;
	private int id_utente = 0;
	
	public AssegnaBiblioDaCodiceWindow() {
		
		utentiService = Registry.get(Abi.UTENTI_SERVICE);
		
		setSize(350, 185);  
		setModal(true);  
		setHeading("Assegnazione biblioteca tramite codice");  
		setLayout(new FitLayout());  
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		createForm();  
		add(formDaCodice, fitData);
		
		addListener(Events.Hide, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formDaCodice.reset();
			}
		});
		
		_instance = this;
		
	}
	
	public void createForm() {
		FormData formData = new FormData("-20");  
		formDaCodice = new FormPanel();

		formDaCodice.setHeading("Form assegna da codice"); 
		formDaCodice.setWidth(400);  
		formDaCodice.setBorders(false);
		formDaCodice.setHeaderVisible(false);

		
		final TextField<String> prov = new TextField<String>();
		final NumberField numero =  new NumberField(); 
		
		prov.setFieldLabel("Prov.");  
		prov.setAllowBlank(false); 
		prov.setMaxLength(2);
		
		formDaCodice.add(prov, formData);  

		
		numero.setFieldLabel("Numero");  
		numero.setAllowBlank(false);
		formDaCodice.add(numero, formData);    

		Button cancel = new Button("Annulla");
		cancel.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				_instance.hide();
			}
		});
		formDaCodice.addButton(cancel); 


		Button reset = new Button("Reset");
		reset.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				formDaCodice.reset();
			}
		});
		formDaCodice.addButton(reset);

		Button assegna = new Button("Assegna");  
		assegna.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				
				final MessageBox box = new MessageBox();
                box.setIcon(MessageBox.QUESTION);
                box.setMessage("Assegnare la biblioteca all'utente?");
                box.setButtons(MessageBox.YESNO);
                box.show();

                final Listener<MessageBoxEvent> msgBoxListner = new Listener<MessageBoxEvent>() {

                    public void handleEvent(final MessageBoxEvent be) {
                        if (be.getButtonClicked() != null && Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                        	utentiService.assegnaBiblioDaCodice(id_utente, prov.getValue(), ((Integer) numero.getValue().intValue()).toString(), new AsyncCallback<Integer>() {
								
								@Override
								public void onSuccess(final Integer result) {
									final MessageBox box = new MessageBox();
					                
					                if (result == -1) {
					                	box.setIcon(MessageBox.WARNING);
					                	box.setMessage("Biblioteca già presente!");
					                	
					                }
					                else if (result == 0) {
					                	box.setIcon(MessageBox.WARNING);
					                	box.setMessage("Biblioteca inesistente!");
					                }
					                else {
					                	box.setIcon(MessageBox.INFO);
					                	
						                if (((Integer) numero.getValue().intValue()).toString().length() < 4) {
						        			if (((Integer) numero.getValue().intValue()).toString().length() == 1)
						        				box.setMessage("Biblioteca IT-".concat(prov.getValue().toUpperCase()).concat("000").concat(((Integer) numero.getValue().intValue()).toString())+" assegnata.");
						        			else if (((Integer) numero.getValue().intValue()).toString().length() == 2)
						        				box.setMessage("Biblioteca IT-".concat(prov.getValue().toUpperCase()).concat("00").concat(((Integer) numero.getValue().intValue()).toString())+" assegnata.");
						        			else box.setMessage("Biblioteca IT-".concat(prov.getValue().toUpperCase()).concat("0").concat(((Integer) numero.getValue().intValue()).toString())+" assegnata.");
						        		}
						        		else box.setMessage("Biblioteca IT-".concat(prov.getValue().toUpperCase()).concat(((Integer) numero.getValue().intValue()).toString())+" assegnata.");
					                }
					                
					                box.setButtons(MessageBox.OK);
					                box.addCallback(new Listener<MessageBoxEvent>() {
										@Override
										public void handleEvent(MessageBoxEvent be) {
											bibSel.remove(result);
											final PagingLoadConfig config = new BasePagingLoadConfig();
											config.setOffset(0);
											config.setLimit(CostantiGestioneBiblio.BIBLIO_GRID_LIMIT);
											config.set("id_utente", id_utente);
											grid.getStore().getLoader().load(config);
											_instance.hide();
										}
									});
					                box.show();
								}
								
								@Override
								public void onFailure(Throwable caught) {
									UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
								}
							});
                        }
                    }
                };
                box.addCallback(msgBoxListner);
				
			}
		});
		formDaCodice.addButton(assegna);  


		formDaCodice.setButtonAlign(HorizontalAlignment.CENTER);  

		FormButtonBinding binding = new FormButtonBinding(formDaCodice);  
		binding.addButton(assegna);
		
	}
	
	public void setIdUtente(int id) {
		this.id_utente = id;
	}
	
	public void setConfig(Grid<BiblioModel> grid, List<Integer> bibSel) {
		this.grid = grid;
		this.bibSel = bibSel;
		
	}
}
