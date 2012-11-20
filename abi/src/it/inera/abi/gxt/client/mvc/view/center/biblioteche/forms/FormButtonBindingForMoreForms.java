package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.Timer;

/**
 * Classe utilizzata per il monitoraggio dello stato di una lista di form
 * e dei relativi bottoni abilita / disabilita
 * 
 */
public class FormButtonBindingForMoreForms /*xtends FormButtonBinding */{

	private List<FormPanel> panels;
	private Timer timer;
	private int interval = 500;
	private Listener<ComponentEvent> listener;
	private List<Button> buttons;

	public FormButtonBindingForMoreForms(List<FormPanel> panels) {
//		super(new FormPanel());
		this.panels =panels;

		buttons = new ArrayList<Button>();
		timer = new Timer() {
			@Override
			public void run() {
				FormButtonBindingForMoreForms.this.checkPanel();
			}
		};
		listener = new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				if (be.getType() == Events.Attach) {
					FormButtonBindingForMoreForms.this.startMonitoring();
				} else if (be.getType() == Events.Detach) {
					FormButtonBindingForMoreForms.this.stopMonitoring();
				}
			}
		};
		Iterator<FormPanel> it=panels.iterator();
		while (it.hasNext()) {
			FormPanel formPanel = (FormPanel) it.next();
			formPanel.addListener(Events.Attach, listener);
			formPanel.addListener(Events.Detach, listener);

			if (formPanel.isAttached()) {
				startMonitoring();
			}
		}

	}
	
	public void addButton(Button button) {
	    buttons.add(button);
	  }

	  public int getInterval() {
	    return interval;
	  }

	  public void removeButton(Button button) {
	    buttons.remove(button);
	  }

	  public void setInterval(int interval) {
	    this.interval = interval;
	  }
	public void startMonitoring() {
		Iterator<FormPanel> it=panels.iterator();
		while (it.hasNext()) {
			FormPanel formPanel = (FormPanel) it.next();
			if (formPanel.isAttached()) {
				timer.run();
				timer.scheduleRepeating(interval);
			}
		}
	}
	protected boolean checkPanel() {
		boolean valid=true;
		Iterator<FormPanel> it=panels.iterator();
		while (it.hasNext()) {
			FormPanel formPanel = (FormPanel) it.next();
			if(formPanel.isValid(true)==false){
				valid=formPanel.isValid(true);
				break;
			}

		}
		for (Button button : buttons) {
			if (valid != button.isEnabled()) {
				button.setEnabled(valid);
			}
		}
		return valid;

	}
	public void stopMonitoring() {
	    timer.cancel();
	  }


}
