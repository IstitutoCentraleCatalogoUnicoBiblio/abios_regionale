package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPuntiDecentratiePanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaSistemiDiBibliotechePanel;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

public class SistemiDiBibliotechePanel extends ContentPanelForTabItem{
	
	private ListaPuntiDecentratiePanel altreBibliotechePanel;
	private ListaSistemiDiBibliotechePanel sistemiDiBibliotechePanel;
	private int id_biblio;
	
	public SistemiDiBibliotechePanel() {
		super();	// TODO Auto-generated constructor stub
		createForms();
	}

	public void createForms() {
		/* FORM SISTEMI BIBLIOTECHE */
		LayoutContainer sistemiBiblio = new LayoutContainer();
		sistemiBiblio.setStyleAttribute("padding", "5px");

		FieldSet sistemiBiblioSet = new FieldSet();
		Utils.setFieldSetProperties(sistemiBiblioSet, "Sistemi e reti di biblioteche");	

		altreBibliotechePanel = new ListaPuntiDecentratiePanel();
		altreBibliotechePanel.setGrid();
		sistemiBiblioSet.add(altreBibliotechePanel);

		sistemiDiBibliotechePanel = new ListaSistemiDiBibliotechePanel();
		sistemiDiBibliotechePanel.setGrid();
		sistemiBiblioSet.add(sistemiDiBibliotechePanel);

		sistemiBiblio.add(sistemiBiblioSet);
		add(sistemiBiblio);
		/* FINE----FORM SISTEMI BIBLIOTECHE */
		
	}
	
	public void setValueFields(){
		altreBibliotechePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		altreBibliotechePanel.getLoader().load();

		sistemiDiBibliotechePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		sistemiDiBibliotechePanel.getLoader().load();
	}
	
	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
}


