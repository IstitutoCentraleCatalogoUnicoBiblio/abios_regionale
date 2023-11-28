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

package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.OrariModel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;

import java.util.Vector;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * Classe che permette la visualizzazione della form di inserimento
 * di una variazione di orario
 *
 */
public class VariazioneOrarioWindow extends Window {

	private int id_biblio = 0;

	private BibliotecheServiceAsync bibliotecheServiceAsync;

	private FormPanel mainPanel = null;

	private Vector<CheckBox> daysChecked;

	private FormButtonBinding buttonBinding = null;

	private TextField<String> periodoVarField = null;
	private SimpleComboBox<String> startOreField = null;
	private SimpleComboBox<String> startMinField = null;
	private SimpleComboBox<String> stopOreField = null;
	private SimpleComboBox<String> stopMinField = null;

	private RadioButton tuttiGiorniRadio = null;
	private HorizontalPanel dayBox = null;

	private CheckBox lun = null;
	private CheckBox mar = null;
	private CheckBox mer = null;
	private CheckBox gio = null;
	private CheckBox ven = null;
	private CheckBox sab = null;
	private CheckBox dom = null;

	private Button save = null; 
	private Button cancel = null;

	public VariazioneOrarioWindow() {
		bibliotecheServiceAsync = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);

		setSize(350, 350);
		setModal(true);
		setScrollMode(Scroll.NONE);
		setResizable(false);
		setClosable(false);
		setLayout(new FitLayout());
		setHeading("Inserimento variazione orario");
		addStyleName("font-weight-button");

		mainPanel = new FormPanel();
		mainPanel.setBodyBorder(false);
		mainPanel.setBorders(false);
		mainPanel.setHeaderVisible(false);

		this.daysChecked = new Vector<CheckBox>();

		Text periodoVarLabel = new Text("Periodo variazione:");
		periodoVarLabel.setStyleAttribute("fontSize", "12px");

		periodoVarField = new TextField<String>();
		periodoVarField.setWidth(150);
		periodoVarField.setMinLength(1);
		periodoVarField.setAllowBlank(false);

		final Text startOreLabel = new Text("Dalle ore:");
		startOreLabel.setStyleAttribute("fontSize", "12px");

		startOreField = new SimpleComboBox<String>();
		startOreField.setEmptyText("hh");

		startOreField.setAllowBlank(true);

		startOreField.setForceSelection(true);
		startOreField.setWidth(50);
		startOreField.setTriggerAction(TriggerAction.ALL);
		startOreField.setEditable(false);
		startOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if (i < 10) {
				startOreField.add("0" + i);

			} else {
				startOreField.add("" + i);
			}
		}

		startMinField = new SimpleComboBox<String>();
		startMinField.setEmptyText("mm");

		startMinField.setAllowBlank(true);

		startMinField.setWidth(50);
		startMinField.setForceSelection(true);
		startMinField.setTriggerAction(TriggerAction.ALL);
		startMinField.setEditable(false);
		startMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				startMinField.add("" + i + j);
			}

		}

		startOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				startMinField.focus();
			}
		});

		final Text stopOreLabel = new Text("Alle ore:");
		stopOreLabel.setStyleAttribute("fontSize", "12px");

		stopOreField = new SimpleComboBox<String>();
		stopOreField.setEmptyText("hh");

		stopOreField.setAllowBlank(true);

		stopOreField.setForceSelection(true);
		stopOreField.setWidth(50);
		stopOreField.setTriggerAction(TriggerAction.ALL);
		stopOreField.setEditable(false);
		stopOreField.setFireChangeEventOnSetValue(true);

		for (int i = 0; i <= 24; i++) {
			if (i < 10) {
				stopOreField.add("0" + i);

			} else {
				stopOreField.add("" + i);
			}
		}

		startMinField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopOreField.focus();
			}
		});

		stopMinField = new SimpleComboBox<String>();
		stopMinField.setEmptyText("mm");

		stopMinField.setAllowBlank(true);

		stopMinField.setWidth(50);
		stopMinField.setForceSelection(true);
		stopMinField.setTriggerAction(TriggerAction.ALL);
		stopMinField.setEditable(false);
		stopMinField.setFireChangeEventOnSetValue(true);
		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 10; j++) {
				stopMinField.add("" + i + j);
			}

		}

		stopOreField.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

			@Override
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				stopMinField.focus();
			}
		});

		/* CONTENUTO FINESTRA */
		LayoutContainer windowContainer = new LayoutContainer();

		/* TIME PICKER */
		LayoutContainer timePicker = new LayoutContainer(new TableLayout(2));
		TableData d = new TableData();
		d.setWidth("15%");
		d.setMargin(5);
		d.setPadding(10);

		TableData d2 = new TableData();
		d2.setWidth("30%");
		d2.setMargin(5);
		d2.setPadding(10);

		timePicker.add(periodoVarLabel, d);
		timePicker.add(periodoVarField, d2);

		timePicker.add(startOreLabel, d);

		HorizontalPanel h = new HorizontalPanel();

		h.add(startOreField);

		h.add(startMinField);

		timePicker.add(h, d2);

		timePicker.add(stopOreLabel, d);

		HorizontalPanel h2 = new HorizontalPanel();

		h2.add(stopOreField);

		h2.add(stopMinField);

		timePicker.add(h2, d2);

		/* FINE TIME PICKER */

		/* ESTENSIONE AI GIORNI: */
		dayBox = new HorizontalPanel();
		dayBox.setStyleAttribute("paddingLeft", "50px");
		TableData d3 = new TableData();
		d3.setWidth("15%");
		d3.setMargin(5);
		d3.setPadding(10);

		Text tuttiGiorniLabel = new Text("Tutti i giorni:");
		tuttiGiorniLabel.setStyleAttribute("fontSize", "12px");
		timePicker.add(tuttiGiorniLabel, d3);

		tuttiGiorniRadio = new RadioButton("estensione");
		tuttiGiorniRadio.setValue(true);
		tuttiGiorniRadio.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dayBox.hide();

			}
		});
		timePicker.add(tuttiGiorniRadio, d3);

		Text giorniCustomLabel = new Text("Personalizzati:");
		giorniCustomLabel.setStyleAttribute("fontSize", "12px");
		timePicker.add(giorniCustomLabel, d3);

		final RadioButton giorniCustomRadio = new RadioButton("estensione");

		giorniCustomRadio.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dayBox.show();

			}
		});
		timePicker.add(giorniCustomRadio, d3);

		Text lunLabel = new Text("L");
		lunLabel.setStyleAttribute("fontSize", "12px");
		lunLabel.setStyleAttribute("margin", "5px");

		lun = new CheckBox();
		daysChecked.add(lun);
		dayBox.add(lunLabel);
		dayBox.add(lun);

		Text marLabel = new Text("M");
		marLabel.setStyleAttribute("fontSize", "12px");
		marLabel.setStyleAttribute("margin", "5px");

		mar = new CheckBox();
		daysChecked.add(mar);
		dayBox.add(marLabel);
		dayBox.add(mar);

		Text merLabel = new Text("M");
		merLabel.setStyleAttribute("fontSize", "12px");
		merLabel.setStyleAttribute("margin", "5px");

		mer = new CheckBox();
		daysChecked.add(mer);
		dayBox.add(merLabel);
		dayBox.add(mer);

		Text gioLabel = new Text("G");
		gioLabel.setStyleAttribute("fontSize", "12px");
		gioLabel.setStyleAttribute("margin", "5px");

		gio = new CheckBox();
		daysChecked.add(gio);
		dayBox.add(gioLabel);
		dayBox.add(gio);

		Text venLabel = new Text("V");
		venLabel.setStyleAttribute("fontSize", "12px");
		venLabel.setStyleAttribute("margin", "5px");

		ven = new CheckBox();
		daysChecked.add(ven);
		dayBox.add(venLabel);
		dayBox.add(ven);

		Text sabLabel = new Text("S");
		sabLabel.setStyleAttribute("fontSize", "12px");
		sabLabel.setStyleAttribute("margin", "5px");

		sab = new CheckBox();
		daysChecked.add(sab);
		dayBox.add(sabLabel);
		dayBox.add(sab);

		Text domLabel = new Text("D");
		domLabel.setStyleAttribute("fontSize", "12px");
		domLabel.setStyleAttribute("margin", "5px");

		dom = new CheckBox();
		daysChecked.add(dom);
		dayBox.add(domLabel);
		dayBox.add(dom);
		dayBox.hide();

		windowContainer.add(timePicker);
		windowContainer.add(dayBox);
		/* FINE ESTENSIONE AI GIORNI: */

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);

		final LayoutContainer buttons = new LayoutContainer(tableLayout);

		save = new Button("Salva");
		save.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				final Vector<Integer> id_days = new Vector<Integer>();
				final OrariModel toSave = new OrariModel();

				/* Verifico che sia stato specificato un periodo variazione */
				if (periodoVarField.getValue() != null && periodoVarField.getValue().length() > 0) {
					toSave.setPeriodo(periodoVarField.getValue());
				}

				toSave.setStartOre(startOreField.getSimpleValue());

				toSave.setStartMin(startMinField.getSimpleValue());

				toSave.setStopOre(stopOreField.getSimpleValue());

				toSave.setStopMin(stopMinField.getSimpleValue());

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							if (checkOrarioFormat(toSave)) {

								if (tuttiGiorniRadio.getValue().booleanValue() == true) {
									for (int i = 0; i < 7; i++) {
										id_days.add(i+1);		
									}

								} else {
									if (lun.getValue()) {
										id_days.add(1);
									}
									if (mar.getValue()) {
										id_days.add(2);
									}
									if (mer.getValue()) {
										id_days.add(3);
									}
									if (gio.getValue()) {
										id_days.add(4);
									}
									if (ven.getValue()) {
										id_days.add(5);
									}
									if (sab.getValue()) {
										id_days.add(6);
									}
									if (dom.getValue()) {
										id_days.add(7);
									}

								}

								if (tuttiGiorniRadio.getValue().booleanValue() == false && id_days.size() == 0) {
									AbiMessageBox.messageErrorAlertBox("Selezionare almeno un giorno corrispondente all'orario inserito!", "ERRORE SELEZIONE GIORNI");
									hide();

								} else {
									bibliotecheServiceAsync.addNuovaVariazioneOrarioCustom(id_biblio, id_days, toSave, new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											hide();
											AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);

										}

										@Override
										public void onFailure(Throwable caught) {
											if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
												hide();
												AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
											}
										}
									});
								}

							} else {
								AbiMessageBox.messageErrorAlertBox("L'orario di apertura deve essere precedente all'orario di chiusura!", "ERRORE FORMATO ORARIO");
								hide();
							}

						} else {
							hide();
						}
					}
				});

			}
		});

		cancel = new Button("Annulla");
		cancel.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				periodoVarField.focus();
				hide();
			}
		});

		buttonBinding = new FormButtonBinding(mainPanel);
		buttonBinding.addButton(save);

		buttons.add(save);
		buttons.add(cancel);

		LayoutContainer c = new LayoutContainer(new CenterLayout());
		c.setStyleAttribute("margin", "30px");
		c.add(buttons);
		windowContainer.add(c);

		mainPanel.add(windowContainer);
		add(mainPanel);

	}

	public void resetForm() {
		periodoVarField.clear();
		startOreField.clear();
		startMinField.clear();
		stopOreField.clear();
		stopMinField.clear();
		tuttiGiorniRadio.setValue(true);
		dayBox.hide();
		mainPanel.reset();
	}

	public void setIdBiblio(int idBib) {
		this.id_biblio = idBib;
	}

	public static boolean checkOrarioFormat(OrariModel tmpSave) {

		Integer startMin = null;
		Integer endMin = null;

		if (tmpSave.getStartOre() != null && tmpSave.getStartOre().length() > 0 && tmpSave.getStartMin() != null && tmpSave.getStartMin().length() > 0) {
			int startOreInt = Integer.parseInt(tmpSave.getStartOre());
			int startMinInt = Integer.parseInt(tmpSave.getStartMin());

			startMin = new Integer((startOreInt*60)+startMinInt);
		}

		if (tmpSave.getStopOre() != null && tmpSave.getStopOre().length() > 0 && tmpSave.getStopMin() != null && tmpSave.getStopMin().length() > 0) {
			int stopOreInt = Integer.parseInt(tmpSave.getStopOre());
			int stopMinInt = Integer.parseInt(tmpSave.getStopMin());

			endMin = new Integer((stopOreInt*60)+stopMinInt);
		}

		if (startMin != null && endMin != null) {
			if (endMin.intValue() > startMin.intValue()) {
				return true;

			} else {
				return false;
			}

		} else {
			return true;
		}
	}

}
