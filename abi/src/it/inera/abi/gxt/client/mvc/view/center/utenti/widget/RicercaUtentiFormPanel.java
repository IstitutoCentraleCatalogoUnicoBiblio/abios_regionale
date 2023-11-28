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
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.model.VoceUnicaModel;
import it.inera.abi.gxt.client.mvc.model.auth.ProfiliModel;
import it.inera.abi.gxt.client.services.UtentiServiceAsync;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Form che permette di filtrare gli utenti visualizzati in base a 
 * determinati filtri di ricerca (username, incarico, email, ruolo, ecc...) 
 *
 */
public class RicercaUtentiFormPanel extends FormPanel {

	private EventType eventToForwordOnSearch = null;

	protected final TextField<String> username;
	protected final TextField<String> nome;
	protected final TextField<String> incarico;
	protected final TextField<String> cognome;
	protected final TextField<String> email;
	protected final ComboBox<ProfiliModel> ruolo;
	protected ComboBox<VoceUnicaModel> enabled;
	protected Button ricerca; 
	protected HashMap<String, Object> keys = new HashMap<String, Object>();
	
	public RicercaUtentiFormPanel() {
		setHeaderVisible(false);
		setLayout(new CenterLayout());
		setHeight(250);
		setStyleAttribute("background-color", "white");
		setBodyBorder(false);
		setBorders(false);
		LayoutContainer wrapperMain = new LayoutContainer();
		wrapperMain.setLayout(new CenterLayout());

		LayoutContainer main = new LayoutContainer(new TableLayout(3));		

		TableData dataCaratteristicheTreColonne1 = new TableData();

		dataCaratteristicheTreColonne1.setWidth("50%");
		dataCaratteristicheTreColonne1.setMargin(5);
		dataCaratteristicheTreColonne1.setPadding(10);
		dataCaratteristicheTreColonne1.setVerticalAlign(VerticalAlignment.TOP);
		LayoutContainer right = new LayoutContainer();

		FormLayout layoutLeft = new FormLayout();
		layoutLeft.setLabelAlign(LabelAlign.TOP);
		
		right.setLayout(layoutLeft);
		
		ListStore<VoceUnicaModel> listStoreStati = new ListStore<VoceUnicaModel>();
		listStoreStati.insert(new VoceUnicaModel(-1, ""), 0);
		listStoreStati.insert(new VoceUnicaModel(0, "Tutti"), 1);
		listStoreStati.insert(new VoceUnicaModel(1, "Abilitati"), 2);
		listStoreStati.insert(new VoceUnicaModel(2, "Disabilitati"), 3);
		
		enabled = new ComboBox<VoceUnicaModel>();
		enabled.setFieldLabel("Stato account");
		enabled.setDisplayField("entry");
		enabled.setStore(listStoreStati);
		enabled.setFireChangeEventOnSetValue(true);
		enabled.setEmptyText("Stato...");		
		enabled.setLazyRender(false);
		enabled.setTriggerAction(TriggerAction.ALL);
		enabled.setForceSelection(false);
		enabled.setEditable(false);
		enabled.setTemplate(getTemplateEmptyRowStatiModel());
		
		LayoutContainer left = new LayoutContainer();

		FormLayout layoutCenter = new FormLayout();
		layoutCenter.setLabelAlign(LabelAlign.TOP);
		
		left.setLayout(layoutCenter);

		username = new TextField<String>();
		username.setFieldLabel("Username");
//		center.add(username);

		nome = new TextField<String>();
		nome.setFieldLabel("Nome");
//		center.add(nome);
		
		email = new TextField<String>();
		email.setFieldLabel("Email");		
//		center.add(email);

		LayoutContainer center = new LayoutContainer();

		FormLayout layoutRight = new FormLayout();
		layoutRight.setLabelAlign(LabelAlign.TOP);
		center.setLayout(layoutRight);

		incarico = new TextField<String>();
		incarico.setFieldLabel("Incarico");
//		right.add(incarico);
		
		cognome = new TextField<String>();
		cognome.setFieldLabel("Cognome");
//		right.add(cognome);
		
		final UtentiServiceAsync utentiServiceAsync = Registry.get(Abi.UTENTI_SERVICE);
		
		RpcProxy<List<ProfiliModel>> proxyRuoli = new RpcProxy<List<ProfiliModel>>() {
			
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<ProfiliModel>> callback) {
				utentiServiceAsync.getRuoli(callback);
			}
			
		};
		ModelReader ruoliReader = new ModelReader();

		final BaseListLoader<ListLoadResult<ModelData>> loaderRuoli = new BaseListLoader<ListLoadResult<ModelData>>(
				proxyRuoli, ruoliReader);

		final ListStore<ProfiliModel> listStoreRuoli = new ListStore<ProfiliModel>(loaderRuoli);
		
		ruolo = new ComboBox<ProfiliModel>();
		ruolo.setFieldLabel("Ruolo");
		ruolo.setDisplayField("denominazione");
		ruolo.setStore(listStoreRuoli);
		ruolo.setFireChangeEventOnSetValue(true);
		ruolo.setEmptyText("Ruolo...");		
		ruolo.setLazyRender(false);
		ruolo.setTriggerAction(TriggerAction.ALL);
		ruolo.setForceSelection(false);
		ruolo.setEditable(false);
		ruolo.setTemplate(getTemplateEmptyRowProfiliModel());
		
		loaderRuoli.addListener(Loader.Load, new Listener<LoadEvent>() {

			@Override
			public void handleEvent(LoadEvent be) {
				/*Inserisce una voce bianca al caricamento dello store*/
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute() {
						ProfiliModel emptyRowProfile = new ProfiliModel(-1,"");
						listStoreRuoli.insert(emptyRowProfile, 0);
					}
				});
			}
		});
		
		loaderRuoli.load();
		
//		right.add(ruolo);
	
		left.add(username);
		left.add(email);
		left.add(enabled);
		
		center.add(nome);
		center.add(incarico);
		
		
		right.add(cognome);
		right.add(ruolo);
		
		main.add(left, dataCaratteristicheTreColonne1);
		main.add(center, dataCaratteristicheTreColonne1);
		main.add(right, dataCaratteristicheTreColonne1);
		wrapperMain.add(main);
		add(wrapperMain);



		int buttonWidth = 55;
		Button reset = new Button("Reset");
		Utils.setStylesButton(reset);
		reset.setWidth(buttonWidth);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				reset();
				keys = new HashMap<String, Object>();
				AppEvent event = new AppEvent(eventToForwordOnSearch);
				event.setData("parametriRicerca", new HashMap<String, String>());
				Dispatcher.forwardEvent(event);
			}
		});

		ricerca = new Button("Ricerca");
		Utils.setStylesButton(ricerca);
		ricerca.setWidth(buttonWidth);
		ricerca.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
//				HashMap<String, Object> keys = new HashMap<String, Object>();
				keys = new HashMap<String, Object>();
				keys.put("nome", nome.getValue());
				keys.put("cognome", cognome.getValue());
				keys.put("incarico", incarico.getValue());
				keys.put("login", username.getValue());
				keys.put("email", email.getValue());
				if (ruolo.getValue() != null)
					keys.put("ruolo", ruolo.getValue().getId().toString());
				
				if (enabled.getValue() != null) {
					if (enabled.getValue().getIdRecord().intValue() > 0) {
						if (enabled.getValue().getEntry().equalsIgnoreCase("Abilitati")) {
							keys.put("enabled", true);
							
						} else if (enabled.getValue().getEntry().equalsIgnoreCase("Disabilitati")) {
							keys.put("enabled", false);
						}
					}
				}
				
				AppEvent event = new AppEvent(eventToForwordOnSearch);
				event.setData("parametriRicerca", keys);

				Dispatcher.forwardEvent(event);
				//	fireEvent(AppEvents.FiltraListaUtenti, event);
			}
		});

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer d = new LayoutContainer(tableLayout);
		d.add(ricerca);
		d.add(reset);

		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setHeight(50);
		c.add(d);

		setButtonAlign(HorizontalAlignment.CENTER);
		setBottomComponent(c);

		FormButtonBinding binding = new FormButtonBinding(this);
		binding.addButton(ricerca);
		binding.addButton(reset);
		
		addKeyListenerForEnter();
	}
	
	public HashMap<String, Object> getKeys() {
		return keys;
	}
	
	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(ricerca,  this);
	}

	public void setForwardEventType(EventType evt){
		this.eventToForwordOnSearch = evt;
	}

	public void resetForms() {
		nome.clear();
		cognome.clear();
		username.clear();
		incarico.clear();
		email.clear();
		ruolo.clear();
	}
	
	private native String getTemplateEmptyRowProfiliModel() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{denominazione}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/;
	
	private native String getTemplateEmptyRowStatiModel() /*-{ 
	return [ 
	'<tpl for=".">', 
	'<div class="x-combo-list-item" style="height:18px;">{entry}</div>', 
	'</tpl>' 
	].join(""); 
	}-*/;
}
