package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaCataloghiGeneraliPanel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai cataloghi generali
 *
 */
public class CataloghiGeneraliPanel extends ContentPanelForTabItem {
	
	private ListaCataloghiGeneraliPanel listaCataloghiGeneraliPanel;
	private int id_biblio;
	
	public CataloghiGeneraliPanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);
		LayoutContainer cataloghiGenerali = new LayoutContainer();
		cataloghiGenerali.setStyleAttribute("padding", "5px");
 
		FieldSet cataloghiGeneraliSet = new FieldSet();
		Utils.setFieldSetProperties(cataloghiGeneraliSet, "Cataloghi generali");

		listaCataloghiGeneraliPanel = new ListaCataloghiGeneraliPanel();
		listaCataloghiGeneraliPanel.setGrid();
				
		cataloghiGeneraliSet.add(listaCataloghiGeneraliPanel);
		cataloghiGenerali.add(cataloghiGeneraliSet);
		add(cataloghiGenerali);
	}
	
	public void setFieldsValues(){

		listaCataloghiGeneraliPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaCataloghiGeneraliPanel.setIdNuovoCatalogoPanel();
		listaCataloghiGeneraliPanel.setIdModificaCatalogoPanel();
		listaCataloghiGeneraliPanel.getLoader().load();

	}


	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
}
