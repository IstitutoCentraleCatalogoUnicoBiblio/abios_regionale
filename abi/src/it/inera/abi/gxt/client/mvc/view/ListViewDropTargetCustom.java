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

import java.util.List;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.dnd.Insert;
import com.extjs.gxt.ui.client.dnd.ListViewDropTarget;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.util.Rectangle;
import com.extjs.gxt.ui.client.widget.ListView;
import com.google.gwt.user.client.Element;

public class ListViewDropTargetCustom extends ListViewDropTarget {

	@SuppressWarnings("rawtypes")
	public ListViewDropTargetCustom(ListView listView) {
		super(listView);
	}
	
	@Override
	protected void showFeedback(DNDEvent event) {
		event.getStatus().setStatus(true);
	    if (feedback == Feedback.INSERT) {
	      event.getStatus().setStatus(true);
	      Element row = listView.findElement(event.getTarget()).cast();

	      if (row == null && listView.getStore().getCount() > 0) {
	        row = listView.getElement(listView.getStore().getCount() - 1).cast();
	      }

	      if (row != null) {
	        int height = row.getOffsetHeight();
	        int mid = height / 2;
	        mid += row.getAbsoluteTop();
	        int y = event.getClientY();
	        before = y < mid;
	        int idx = listView.findElementIndex(row);

	        activeItem = listView.getStore().getAt(idx);
	        insertIndex = adjustIndex(event, idx);

//	        showInsert(event, row);
	      } else {
	        insertIndex = 0;
	      }
	    }
	  }

	  private int adjustIndex(DNDEvent event, int index) {
	    Object data = event.getData();
	    int i = index;
	    List<ModelData> models = prepareDropData(data, true);
	    for (ModelData m : models) {
	      int idx = listView.getStore().indexOf(m);
	      if (idx > -1 && (before ? idx < index : idx <= index)) {
	        i--;
	      }
	    }
	    return before ? i : i + 1;
	  }

	  private void showInsert(DNDEvent event, Element row) {
	    Insert insert = Insert.get();
	    insert.show(row);
	    Rectangle rect = El.fly(row).getBounds();
	    int y = !before ? (rect.y + rect.height - 4) : rect.y - 2;
	    insert.el().setBounds(rect.x, y, rect.width, 6);
	  }
}
