package it.inera.abi.gxt.client.mvc.view.center.tabelledinamiche.widget;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;

public class CheckBoxSelectionModelCustom<M extends ModelData> extends CheckBoxSelectionModel<M> {
	public CheckBoxSelectionModelCustom(){
		super();
	}
	@Override
	protected void onHeaderClick(GridEvent<M> e) {  
	        super.onHeaderClick(e);
	        fireEvent(Events.HeaderClick);
	}  
}
