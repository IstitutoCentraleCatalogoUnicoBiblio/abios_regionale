package it.inera.abi.gxt.client.mvc.view;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

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
