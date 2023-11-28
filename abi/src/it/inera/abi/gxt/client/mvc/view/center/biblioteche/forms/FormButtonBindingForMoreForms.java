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
public class FormButtonBindingForMoreForms /*extends FormButtonBinding */{

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
