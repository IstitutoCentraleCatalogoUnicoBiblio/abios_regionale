/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

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
