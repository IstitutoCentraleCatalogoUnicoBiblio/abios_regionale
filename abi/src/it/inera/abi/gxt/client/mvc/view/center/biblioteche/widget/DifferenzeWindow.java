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

import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * Classe widget per la visualizzazione delle differenze di una biblioteca
 *
 */
public class DifferenzeWindow extends Window {

	protected Window _instance;
	protected Html html = null; 
	
	public DifferenzeWindow() {
		setSize(800, 600);  
		setFrame(true);  
		setModal(true);  
		setLayout(new FitLayout());  
		setHeading("Differenze");
		FitData fitData = new FitData();
		fitData.setMargins(new Margins(5));
		setResizable(false);

		html = new Html();
		
		add(html);
		_instance = this;
	}
	
	public void setDifferenze(String differenze) {
		html.setStyleAttribute("background-color", "white");
		html.setStyleAttribute("overflow", "auto");
		html.setHtml(differenze);
		
	}
}
