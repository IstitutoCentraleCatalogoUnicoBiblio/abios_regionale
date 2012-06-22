package it.inera.abi.gxt.client.mvc.view;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;
/**
 * Classe che implementa un TextField generico ma che permette, utilizzando il
 * metodo setMaxLength(int maxLength), di limitare i caratteri digitati dall'utente
 * in un campo di testo, oltre a farne la validazione del campo se "bindato" al 
 * bottone del form
 * 
 *
</br>
</br>@Override
</br>public void setMaxLength(int maxLength) {
</br>&nbsp;	super.setMaxLength(maxLength);
</br>&nbsp;	if(isRendered()){
</br>&nbsp;	getInputEl().setElementAttribute("maxlength", maxLength);
</br>&nbsp;	}
</br>}  
</br>
</br>@Override
</br>protected void onRender(Element target, int index) {
</br>&nbsp;	super.onRender(target, index);
</br>&nbsp;	setMaxLength(getMaxLength());
</br>}
 * 
 * */
public class TextFieldCustom<E> extends TextField<E>{

	@Override
	public void setMaxLength(int maxLength) {
		super.setMaxLength(maxLength);
		if(isRendered()){
			getInputEl().setElementAttribute("maxLength", maxLength);
		}
	}  

	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		setMaxLength(getMaxLength());
	}
}
