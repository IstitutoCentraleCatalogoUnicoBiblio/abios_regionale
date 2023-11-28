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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * Permette di visualizzare gli utenti che gestiscono la biblioteca selezionata
 *
 */
public class ListaUtentiGestoriWindow extends Window {

	protected Window _instance;
	protected BiblioModel biblioModel;

	protected ListaUtentiGestoriPanel listaUtentiGestoriPanel;
	
	public ListaUtentiGestoriWindow() {
		setSize(690, 450);  
		setFrame(true);  
		setModal(true);  
		setLayout(new FitLayout());  
		setHeading("Utenti gestori");
		
		listaUtentiGestoriPanel = new ListaUtentiGestoriPanel();
		add(listaUtentiGestoriPanel);
		
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		Button chiudi = new Button("Chiudi");
		chiudi.addListener(Events.OnClick, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				_instance.hide();
			}
		});
		addButton(chiudi); 
		setResizable(false);
		addListener(Events.Hide, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
			}
		});
		
		_instance = this;
	}
	
	@Override
	protected void onShow() {
//		final PagingLoadConfig config = new BasePagingLoadConfig();
//		config.setOffset(0);
//		config.setLimit(20);
//		config.set("idBiblio", this.biblioModel.getIdBiblio());
//		listaUtentiGestoriPanel.getLoader().load(config);
		
//		listaUtentiGestoriPanel.resetPaging();
		listaUtentiGestoriPanel.setKeys(this.biblioModel.getIdBiblio());
		
	}

	
	public void setBiblioModel(BiblioModel biblioModel) {
		this.biblioModel = biblioModel;
	}

}