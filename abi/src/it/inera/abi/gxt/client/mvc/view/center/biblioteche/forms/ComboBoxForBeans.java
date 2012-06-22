package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class ComboBoxForBeans<M extends ModelData> extends ComboBox<M> {
	 @Override
	    public M getValue() {
	      if (value != null) {
	        Object val = value.get(getDisplayField());
	        if (getRawValue().equals(val != null ? val.toString() : null)) {
	          return value;
	        }

	      }
	      return super.getValue();
	    }
}
