package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;

public class ContentPanelForTabItem extends ContentPanel {
	
	protected BiblioModel biblioteca;
	
	public ContentPanelForTabItem() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
	}
	
	public void setBiblioteca(BiblioModel biblioteca) {
		
		this.biblioteca = biblioteca;
	}
	
	public void fireReleoadbiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,biblioteca.getIdBiblio());
	}
	
	
}
