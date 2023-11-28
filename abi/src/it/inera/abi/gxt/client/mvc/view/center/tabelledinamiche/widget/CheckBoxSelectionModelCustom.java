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

package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;

/**
 * Estensione della classe <code>CheckBoxSelectionModel</code>, che permette di 
 * personalizzare la selezione multipla all'interno della tabella dinamica dei comuni 
 *
 */
public class CheckBoxSelectionModelCustom<M extends ModelData> extends CheckBoxSelectionModel<M> {
	
	public CheckBoxSelectionModelCustom() {
		super();
	}
	
	@Override
	protected void onHeaderClick(GridEvent<M> e) {  
	        super.onHeaderClick(e);
	        fireEvent(Events.HeaderClick);
	}  
}
