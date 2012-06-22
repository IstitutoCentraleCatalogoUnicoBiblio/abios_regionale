package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ErroreFileControllatoWindow extends Window {
	
	protected HtmlContainer info = null;
	
	public ErroreFileControllatoWindow() {
		info = new HtmlContainer();
		setSize(250, 100);
		setPlain(true);
		setModal(true);
		setHeading("Errore nel file di import");
		setLayout(new FitLayout());
		setClosable(true);
		add(info);
	}
	
	public void setError(String error) {
		info.setHtml(error);
	}
}
