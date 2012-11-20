package it.inera.abi.gxt.client.mvc.view;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

/**
 * Estensione custom della classe <code>TextField</code> che permette, utilizzando 
 * il metodo setMaxLength(int maxLength), di limitare i caratteri digitati 
 * dall'utente all'interno di un campo testuale, oltre a farne la validazione 
 * del campo se associato al bottone della form
 * 
 */
public class TextFieldCustom<E> extends TextField<E> {

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
		setMaxLength(getMaxLength());
	}
}
