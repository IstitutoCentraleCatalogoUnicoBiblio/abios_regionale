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
