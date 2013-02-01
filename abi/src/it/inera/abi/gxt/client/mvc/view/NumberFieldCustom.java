package it.inera.abi.gxt.client.mvc.view;

import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.user.client.Element;

/**
 * Estensione custom della classe <code>NumberField</code> che permette, utilizzando 
 * i metodi setMinLength(int minLength) e setMaxLength(int maxLength), di limitare le cifre digitate 
 * dall'utente all'interno di un campo numerico, oltre a farne la validazione 
 * se associato al bottone della form
 * 
 */
public class NumberFieldCustom extends NumberField {
	
	@Override
	public void setMinLength(int minLength) {
		super.setMinLength(minLength);
		
		if (isRendered()) {
			getInputEl().setElementAttribute("minLength", minLength);
		}
	}
	
	@Override
	public void setMaxLength(int maxLength) {
		super.setMaxLength(maxLength);
		
		if (isRendered()) {
			getInputEl().setElementAttribute("maxLength", maxLength);
		}
	}
	
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		setMinLength(getMinLength());
		setMaxLength(getMaxLength());
	}
}
