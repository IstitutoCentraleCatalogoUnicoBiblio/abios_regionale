package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

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
//		addListener(Events.Hide, new Listener<ComponentEvent>() {
//			public void handleEvent(ComponentEvent be) {
//		
//			}
//		});
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
