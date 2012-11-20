package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaCataloghiCollettiviPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.services.TabelleDinamicheServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai cataloghi collettivi
 *
 */
public class CataloghiCollettiviPanel extends ContentPanelForTabItem {
	private	ListaCataloghiCollettiviPanel listaCataloghiCollettiviPanel;

	private int id_biblio;
	protected BibliotecheServiceAsync bibliotecheService;
	protected TabelleDinamicheServiceAsync tdsa;
	
	private FieldSet cataloghiCollettiviSet;

	public CataloghiCollettiviPanel() {
		super();
		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setScrollMode(Scroll.AUTOY);
		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);
		tdsa= Registry.get(Abi.TABELLE_DINAMICHE_SERVICE); 
		LayoutContainer cataloghiCollettivi = new LayoutContainer();
		cataloghiCollettivi.setStyleAttribute("padding", "5px");

		cataloghiCollettiviSet = new FieldSet();
		Utils.setFieldSetProperties(cataloghiCollettiviSet, "Cataloghi collettivi");

		listaCataloghiCollettiviPanel = new ListaCataloghiCollettiviPanel();
		listaCataloghiCollettiviPanel.setGrid();
		createForm();
	}

	public void createForm() {
		LayoutContainer cataloghiCollettivi = new LayoutContainer();
		cataloghiCollettivi.setStyleAttribute("padding", "5px");

		cataloghiCollettiviSet.add(listaCataloghiCollettiviPanel);
		/**/
		cataloghiCollettivi.add(cataloghiCollettiviSet);
		add(cataloghiCollettivi);
	}

	public void setFieldsValues(){
		listaCataloghiCollettiviPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaCataloghiCollettiviPanel.setIdNuovoCatalogoPanel();
		listaCataloghiCollettiviPanel.setIdModificaCatalogoPanel();
		listaCataloghiCollettiviPanel.getLoader().load();
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;
	}
}