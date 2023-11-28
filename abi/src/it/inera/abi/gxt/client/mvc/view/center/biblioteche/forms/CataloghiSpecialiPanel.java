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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaCataloghiSpecialiPanel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai cataloghi speciali
 *
 */
public class CataloghiSpecialiPanel extends ContentPanelForTabItem {
	
	private int id_biblio;
	private ListaCataloghiSpecialiPanel listaCataloghiSpecialiPanel;
	
	public CataloghiSpecialiPanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);
		LayoutContainer cataloghiSpeciali = new LayoutContainer();
		cataloghiSpeciali.setStyleAttribute("padding", "5px");
 
		FieldSet cataloghiSpecialiSet = new FieldSet();
		Utils.setFieldSetProperties(cataloghiSpecialiSet, "Cataloghi Speciali");

		listaCataloghiSpecialiPanel = new ListaCataloghiSpecialiPanel();
		listaCataloghiSpecialiPanel.setGrid();
		
	
		cataloghiSpecialiSet.add(listaCataloghiSpecialiPanel);
		cataloghiSpeciali.add(cataloghiSpecialiSet);
		add(cataloghiSpeciali);
	}
	
	public void setFieldsValues(){

		listaCataloghiSpecialiPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaCataloghiSpecialiPanel.setIdNuovoCatalogoPanel();
		listaCataloghiSpecialiPanel.setIdModificaCatalogoPanel();
		listaCataloghiSpecialiPanel.getLoader().load();

	}


	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
}
