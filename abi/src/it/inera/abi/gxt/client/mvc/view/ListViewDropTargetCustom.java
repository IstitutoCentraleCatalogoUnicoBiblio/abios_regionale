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
