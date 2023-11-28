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

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

/**
 * Estensione custom della classe <code>RowEditor</code> che permette di cambiare
 * l'ordine dei bottoni di salvataggio e reset delle modifiche effettuate
 *
 */
public class RowEditorCustom<M extends ModelData> extends RowEditor<M> {
	
	public RowEditorCustom() {
		super();
	}

	@Override
	protected void createButtons() {
		btns = new ContentPanel() {
		      protected void createStyles(String baseStyle) {
		        baseStyle = "x-plain";
		        headerStyle = baseStyle + "-header";
		        headerTextStyle = baseStyle + "-header-text";
		        bwrapStyle = baseStyle + "-bwrap";
		        tbarStyle = baseStyle + "-tbar";
		        bodStyle = baseStyle + "-body";
		        bbarStyle = baseStyle + "-bbar";
		        footerStyle = baseStyle + "-footer";
		        collapseStyle = baseStyle + "-collapsed";
		      }
		    };

		    btns.setHeaderVisible(false);
		    btns.addStyleName("x-btns");
		    btns.setLayout(new TableLayout(2));

		    saveBtn = new Button(getMessages().getSaveText(), new SelectionListener<ButtonEvent>() {		      
		    	@Override		      
		    	public void componentSelected(ButtonEvent ce) {		        
		    		stopEditing(true);		      
		    	}		    
		    });
		    saveBtn.setMinWidth(getMinButtonWidth());
		    btns.add(saveBtn);

		    cancelBtn = new Button(getMessages().getCancelText(), new SelectionListener<ButtonEvent>() {			   
		    	@Override			    
		    	public void componentSelected(ButtonEvent ce) {
			        stopEditing(false);			      
		    	}			    
		    });
		    cancelBtn.setMinWidth(getMinButtonWidth());
		    btns.add(cancelBtn);
		    
		    btns.render(getElement("bwrap"));
		    btns.layout();

		    btns.getElement().removeAttribute("tabindex");
		    btns.getAriaSupport().setIgnore(true);
	}
	
	public Button getSaveButton(){
		return this.saveBtn;
	}
}
