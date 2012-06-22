package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaCataloghiSpecialiPanel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

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
