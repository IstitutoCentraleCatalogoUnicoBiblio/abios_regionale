package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import java.util.HashMap;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPatrimonioLibrarioPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;


import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * al patrimonio librario
 *
 */
public class PatrimonioLibrarioPanel extends ContentPanelForTabItem {

	private TableData d;
	private int id_biblio;

	private Button aggiornaAll;
	private Button resetAll;
	
	private RadioGroup rgCatCartaceo;
	private Radio catTopCartaceoSI;
	private Radio catTopCartaceoNO;
	private Radio catTopCartaceoNonSpecificato;

	private RadioGroup rgCatInform;
	private Radio catTopInformatizzatoSI;
	private Radio catTopInformatizzatoNO;
	private Radio catTopInformatizzatoNonSpecificato;

	private RadioGroup rgInventCartaceo;
	private Radio inventarioCartaceoSI;
	private Radio inventarioCartaceoNO;
	private Radio inventarioCartaceoNonSpecificato;

	private RadioGroup rgInventInform;
	private Radio inventarioInforSI;
	private Radio inventarioInforNO;
	private Radio inventarioInforNonSpecificato;

	private ListaPatrimonioLibrarioPanel listaPatrimonio;
	private SimpleComboBox<String> fondiAntichiField;

	private BibliotecheServiceAsync bibliotecheService;
	
	private Text invCartaceoLabel;
	private Text invInformLabel;
	private Text catCartaceoLabel;
	private Text catInformLabel;
	private Text fondiAntichiLabel;

	/*Form*/
	private FormPanel invCatForm;
	
	public PatrimonioLibrarioPanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);

		this.bibliotecheService = (BibliotecheServiceAsync) Registry.get(Abi.BIBLIOTECHE_SERVICE);

		d = new TableData();
		d.setWidth("20%");
		d.setMargin(5);
		d.setPadding(10);

		createForms();
	}


	public void createForms() {
		createPatrimonioLibrarioForm();
		createInventarioCatalogoTopograficoForm();
	}
	
	public void createPatrimonioLibrarioForm() {
		/* PATRIMONIO LIBRARIO */
		LayoutContainer patrimonio = new LayoutContainer();
		patrimonio.setStyleAttribute("padding", "5px");

		FieldSet patrimonioSet = new FieldSet();
		Utils.setFieldSetProperties(patrimonioSet, "Patrimonio librario");
		
		listaPatrimonio = new ListaPatrimonioLibrarioPanel();
		listaPatrimonio.setGrid();
		patrimonioSet.add(listaPatrimonio);
		patrimonio.add(patrimonioSet);
		add(patrimonio);
		/* PATRIMONIO LIBRARIO */
	}

	public void createInventarioCatalogoTopograficoForm() {
		/* VARI PATRIMONIO */
		LayoutContainer inventario = new LayoutContainer();
		inventario.setStyleAttribute("padding", "5px");

		FieldSet variPatrimonioSet = new FieldSet();
		Utils.setFieldSetProperties(variPatrimonioSet, "Inventario - Catalogo topografico - Fondi antichi (fino al 1830)");
		variPatrimonioSet.setExpanded(true);

		invCatForm = new FormPanel();
		invCatForm.setHeaderVisible(false);
		invCatForm.setBorders(false);
		invCatForm.setBodyBorder(false);
		invCatForm.setWidth(600);

		FormLayout invCatFormLayout = new FormLayout();
		invCatFormLayout.setLabelAlign(LabelAlign.TOP);

		invCatForm.setLayout(invCatFormLayout);

		LayoutContainer invCatTable = new LayoutContainer(new TableLayout(2));
		invCatTable.setWidth(600);
		
		/* Inventario cartaceo */
		invCartaceoLabel = new Text("Inventario cartaceo:");
		invCartaceoLabel.setStyleAttribute("fontSize", "12px");
		invCatTable.add(invCartaceoLabel, d);

		LayoutContainer inventarioCartaceoRadioButton = new LayoutContainer(new FlowLayout());
		inventarioCartaceoRadioButton.setAutoWidth(true);
		
		rgInventCartaceo = new RadioGroup("inventarioCartaceo");
		rgInventCartaceo.setStyleAttribute("position", "static");

		inventarioCartaceoSI =  new Radio();
		inventarioCartaceoSI.setBoxLabel("SI");

		inventarioCartaceoNO =  new Radio();
		inventarioCartaceoNO.setBoxLabel("NO");

		inventarioCartaceoNonSpecificato = new Radio();
		inventarioCartaceoNonSpecificato.setBoxLabel("Non specificato");
		
		rgInventCartaceo.add(inventarioCartaceoSI);
		rgInventCartaceo.add(inventarioCartaceoNO);
		rgInventCartaceo.add(inventarioCartaceoNonSpecificato);

		inventarioCartaceoRadioButton.add(rgInventCartaceo);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgInventCartaceo, invCartaceoLabel);
				
		invCatTable.add(inventarioCartaceoRadioButton, d);
		
		/* Inventario informatizzato */		
		invInformLabel = new Text("Inventario informatizzato:");
		invInformLabel.setStyleAttribute("fontSize", "12px");
		invCatTable.add(invInformLabel, d);

		LayoutContainer inventarioInformatizzatoRadioButton = new LayoutContainer(new FlowLayout());
		inventarioInformatizzatoRadioButton.setAutoWidth(true);
		
		rgInventInform = new RadioGroup("inventarioInformatizzato");
		rgInventInform.setStyleAttribute("position", "static");

		inventarioInforSI = new Radio();
		inventarioInforSI.setBoxLabel("SI");

		inventarioInforNO = new Radio();
		inventarioInforNO.setBoxLabel("NO");

		inventarioInforNonSpecificato = new Radio();
		inventarioInforNonSpecificato.setBoxLabel("Non specificato");
		
		rgInventInform.add(inventarioInforSI);
		rgInventInform.add(inventarioInforNO);
		rgInventInform.add(inventarioInforNonSpecificato);
		
		inventarioInformatizzatoRadioButton.add(rgInventInform);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgInventInform, invInformLabel);
		
		invCatTable.add(inventarioInformatizzatoRadioButton, d);

		/* Catalogo topografico cartaceo */
		catCartaceoLabel = new Text("Catalogo topografico cartaceo:");
		catCartaceoLabel.setStyleAttribute("fontSize", "12px");
		invCatTable.add(catCartaceoLabel, d);

		LayoutContainer catTopCartaceoRadioButton = new LayoutContainer(new FlowLayout());
		catTopCartaceoRadioButton.setAutoWidth(true);

		rgCatCartaceo = new RadioGroup("catalogoTopograficoCartaceo");
		rgCatCartaceo.setStyleAttribute("position", "static");
		
		catTopCartaceoSI = new Radio();
		catTopCartaceoSI.setBoxLabel("SI");

		catTopCartaceoNO = new Radio();
		catTopCartaceoNO.setBoxLabel("NO");

		catTopCartaceoNonSpecificato = new Radio();
		catTopCartaceoNonSpecificato.setBoxLabel("Non specificato");
		
		rgCatCartaceo.add(catTopCartaceoSI);
		rgCatCartaceo.add(catTopCartaceoNO);
		rgCatCartaceo.add(catTopCartaceoNonSpecificato);

		catTopCartaceoRadioButton.add(rgCatCartaceo);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgCatCartaceo, catCartaceoLabel);
						
		invCatTable.add(catTopCartaceoRadioButton, d);
		
		/* Catalogo topografico cartaceo */
		catInformLabel = new Text("Catalogo topografico informatizzato:");
		catInformLabel.setStyleAttribute("fontSize", "12px");
		invCatTable.add(catInformLabel, d);

		LayoutContainer catTopInformatizzatoRadioButton = new LayoutContainer(new FlowLayout());
		catTopInformatizzatoRadioButton.setAutoWidth(true);

		rgCatInform = new RadioGroup("catalogoTopograficoInformatizzato");
		rgCatInform.setStyleAttribute("position", "static");
		
		catTopInformatizzatoSI = new Radio();
		catTopInformatizzatoSI.setBoxLabel("SI");

		catTopInformatizzatoNO = new Radio();
		catTopInformatizzatoNO.setBoxLabel("NO");

		catTopInformatizzatoNonSpecificato = new Radio();
		catTopInformatizzatoNonSpecificato.setBoxLabel("Non specificato");
		
		rgCatInform.add(catTopInformatizzatoSI);
		rgCatInform.add(catTopInformatizzatoNO);
		rgCatInform.add(catTopInformatizzatoNonSpecificato);

		catTopInformatizzatoRadioButton.add(rgCatInform);
		Utils.addListenerToChangeLabelColorIfModifiedRadioGroup(rgCatInform, catInformLabel);
		
		invCatTable.add(catTopInformatizzatoRadioButton, d);
		
		/* Fondi antichi (fino al 1830) */
		fondiAntichiLabel = new Text("Fondi antichi (fino al 1830):");
		fondiAntichiLabel.setStyleAttribute("fontSize", "12px");
		invCatTable.add(fondiAntichiLabel, d);

		fondiAntichiField = new SimpleComboBox<String>();
		fondiAntichiField.setTriggerAction(TriggerAction.ALL);
		fondiAntichiField.setEditable(false);
		fondiAntichiField.setFireChangeEventOnSetValue(true);
		fondiAntichiField.setWidth(200);
		fondiAntichiField.add(CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI);
		fondiAntichiField.add(CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI);
		fondiAntichiField.add(CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI);
		fondiAntichiField.add(CostantiGestioneBiblio.NON_SPECIFICATO);
		fondiAntichiField.setSimpleValue(CostantiGestioneBiblio.NON_SPECIFICATO);
		fondiAntichiField.addListener(Events.Change, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				Utils.setFontColorStyleRed(fondiAntichiLabel);
			}
		});
		
		invCatTable.add(fondiAntichiField, d);	

		aggiornaAll = new Button("Aggiorna");
		Utils.setStylesButton(aggiornaAll);

		aggiornaAll.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							HashMap<String, Object> invCat = new HashMap<String, Object>();
							
							int id_consistenza = BiblioModel.getIdbyNumeroFondiAntichi1830(fondiAntichiField.getRawValue());
							invCat.put("fondi", new Integer(id_consistenza));
							
							if (inventarioCartaceoNonSpecificato.getValue() == false) {
								if (inventarioCartaceoSI.getValue()) {
									invCat.put("invCartaceo", inventarioCartaceoSI.getValue());
								}
								else invCat.put("invCartaceo", new Boolean(false));									
							}
							else invCat.put("invCartaceo", null);
							
							if (inventarioInforNonSpecificato.getValue() == false) {
								if (inventarioInforSI.getValue()) {
									invCat.put("invInform", inventarioInforSI.getValue());
								}
								else invCat.put("invInform", new Boolean(false));
							}
							else invCat.put("invInform", null);
							
							if (catTopCartaceoNonSpecificato.getValue() == false) {
								if (catTopCartaceoSI.getValue()) {
									invCat.put("catCartaceo", catTopCartaceoSI.getValue());
								}
								else invCat.put("catCartaceo", new Boolean(false));
							}
							else invCat.put("catCartaceo", null);
							
							if (catTopInformatizzatoNonSpecificato.getValue() == false) {
								if (catTopInformatizzatoSI.getValue()) {
									invCat.put("catInform", catTopInformatizzatoSI.getValue());
								}
								else invCat.put("catInform", new Boolean(false));
							}
							else invCat.put("catInform", null);
								
							bibliotecheService.setCatInvFondi(id_biblio, invCat, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									if (inventarioCartaceoNonSpecificato.getValue() == false) {
										inventarioCartaceoSI.setOriginalValue(inventarioCartaceoSI.getValue());
										inventarioCartaceoNO.setOriginalValue(inventarioCartaceoNO.getValue());
										inventarioCartaceoNonSpecificato.setOriginalValue(false);
										
										rgInventCartaceo.setOriginalValue(inventarioCartaceoSI.getValue()?inventarioCartaceoSI:inventarioCartaceoNO);
									}
									else {
										inventarioCartaceoSI.setOriginalValue(false);
										inventarioCartaceoNO.setOriginalValue(false);
										inventarioCartaceoNonSpecificato.setOriginalValue(true);
										
										rgInventCartaceo.setOriginalValue(inventarioCartaceoNonSpecificato);										
									}
									
									if (inventarioInforNonSpecificato.getValue() == false) {
										inventarioInforSI.setOriginalValue(inventarioInforSI.getValue());
										inventarioInforNO.setOriginalValue(inventarioInforNO.getValue());
										inventarioInforNonSpecificato.setOriginalValue(false);
										
										rgInventInform.setOriginalValue(inventarioInforSI.getValue()?inventarioInforSI:inventarioInforNO);
									}
									else {
										inventarioInforSI.setOriginalValue(false);
										inventarioInforNO.setOriginalValue(false);
										inventarioInforNonSpecificato.setOriginalValue(true);
										
										rgInventInform.setOriginalValue(inventarioInforNonSpecificato);						
									}
									
									if (catTopCartaceoNonSpecificato.getValue() == false) {
										catTopCartaceoSI.setOriginalValue(catTopCartaceoSI.getValue());
										catTopCartaceoNO.setOriginalValue(catTopCartaceoNO.getValue());
										catTopCartaceoNonSpecificato.setOriginalValue(catTopCartaceoNonSpecificato.getValue());
										
										rgCatCartaceo.setOriginalValue(catTopCartaceoSI.getValue()?catTopCartaceoSI:catTopCartaceoNO);
									}
									else {
										catTopCartaceoSI.setOriginalValue(false);
										catTopCartaceoNO.setOriginalValue(false);
										catTopCartaceoNonSpecificato.setOriginalValue(true);
										
										rgCatCartaceo.setOriginalValue(catTopCartaceoNonSpecificato);
									}
									
									if (catTopInformatizzatoNonSpecificato.getValue() == false) {
										catTopInformatizzatoSI.setOriginalValue(catTopInformatizzatoSI.getValue());
										catTopInformatizzatoNO.setOriginalValue(catTopInformatizzatoNO.getValue());
										catTopInformatizzatoNonSpecificato.setOriginalValue(catTopInformatizzatoNonSpecificato.getValue());
										
										rgCatInform.setOriginalValue(catTopInformatizzatoSI.getValue()?catTopInformatizzatoSI:catTopInformatizzatoNO);
									}
									else {
										catTopInformatizzatoSI.setOriginalValue(false);
										catTopInformatizzatoNO.setOriginalValue(false);
										catTopInformatizzatoNonSpecificato.setOriginalValue(true);
										
										rgCatInform.setOriginalValue(catTopInformatizzatoNonSpecificato);
									}
									
									fondiAntichiField.setOriginalValue(fondiAntichiField.getValue());
									
									Utils.setFontColorStyleBlack(invCartaceoLabel);
									Utils.setFontColorStyleBlack(invInformLabel);
									Utils.setFontColorStyleBlack(catCartaceoLabel);
									Utils.setFontColorStyleBlack(catInformLabel);
									Utils.setFontColorStyleBlack(fondiAntichiLabel);
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								
									
								}
								
								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) { // controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									}									
								}
							});
							
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}
		});
		
		resetAll = new Button("Reset");
		Utils.setStylesButton(resetAll);
		
		resetAll.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				invCatForm.reset();
				Utils.setFontColorStyleBlack(invCartaceoLabel);
				Utils.setFontColorStyleBlack(invInformLabel);
				Utils.setFontColorStyleBlack(catCartaceoLabel);
				Utils.setFontColorStyleBlack(catInformLabel);
				Utils.setFontColorStyleBlack(fondiAntichiLabel);
			}
		});
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(aggiornaAll);
		buttons.add(resetAll);
		
		invCatTable.add(new LayoutContainer(), d);
		invCatTable.add(buttons, d);

		FormButtonBinding invCatBind = new FormButtonBinding(invCatForm);
		invCatBind.addButton(aggiornaAll);

		invCatForm.add(invCatTable);
		variPatrimonioSet.add(invCatForm);
		
		inventario.add(variPatrimonioSet);
		add(inventario);
		

	}
	
	public void setFieldsValues() {
		listaPatrimonio.setIdBiblioteca(biblioteca.getIdBiblio());
		listaPatrimonio.getLoader().load();
		
		if (biblioteca.getInventarioCartaceo() != null) {
			inventarioCartaceoSI.setValue(biblioteca.getInventarioCartaceo());
			inventarioCartaceoNO.setValue(!biblioteca.getInventarioCartaceo());
			inventarioCartaceoNonSpecificato.setValue(false);
			inventarioCartaceoSI.setOriginalValue(biblioteca.getInventarioCartaceo());
			inventarioCartaceoNO.setOriginalValue(!biblioteca.getInventarioCartaceo());
			inventarioCartaceoNonSpecificato.setOriginalValue(false);
			
			rgInventCartaceo.setOriginalValue(biblioteca.getInventarioCartaceo()?inventarioCartaceoSI:inventarioCartaceoNO);
		}
		else {
			inventarioCartaceoSI.setValue(false);
			inventarioCartaceoNO.setValue(false);
			inventarioCartaceoNonSpecificato.setValue(true);
			inventarioCartaceoSI.setOriginalValue(false);
			inventarioCartaceoNO.setOriginalValue(false);
			inventarioCartaceoNonSpecificato.setOriginalValue(true);
			
			rgInventCartaceo.setOriginalValue(inventarioCartaceoNonSpecificato);
		}
		Utils.setFontColorStyleBlack(invCartaceoLabel);
		
		if (biblioteca.getInventarioInformatizzato() != null) {
			inventarioInforSI.setValue(biblioteca.getInventarioInformatizzato());
			inventarioInforNO.setValue(!biblioteca.getInventarioInformatizzato());
			inventarioInforNonSpecificato.setValue(false);
			inventarioInforSI.setOriginalValue(biblioteca.getInventarioInformatizzato());
			inventarioInforNO.setOriginalValue(!biblioteca.getInventarioInformatizzato());
			inventarioInforNonSpecificato.setOriginalValue(false);
			
			rgInventInform.setOriginalValue(biblioteca.getInventarioInformatizzato()?inventarioInforSI:inventarioInforNO);
		}
		else {
			inventarioInforSI.setValue(false);
			inventarioInforNO.setValue(false);
			inventarioInforNonSpecificato.setValue(true);
			inventarioInforSI.setOriginalValue(false);
			inventarioInforNO.setOriginalValue(false);
			inventarioInforNonSpecificato.setOriginalValue(true);
			
			rgInventInform.setOriginalValue(inventarioInforNonSpecificato);
		}
		Utils.setFontColorStyleBlack(invInformLabel);
		
		if (biblioteca.getCatalogoCartaceo() != null) {
			catTopCartaceoSI.setValue(biblioteca.getCatalogoCartaceo());
			catTopCartaceoNO.setValue(!biblioteca.getCatalogoCartaceo());
			catTopCartaceoNonSpecificato.setValue(false);
			catTopCartaceoSI.setOriginalValue(biblioteca.getCatalogoCartaceo());
			catTopCartaceoNO.setOriginalValue(!biblioteca.getCatalogoCartaceo());
			catTopCartaceoNonSpecificato.setOriginalValue(false);
			
			rgCatCartaceo.setOriginalValue(biblioteca.getCatalogoCartaceo()?catTopCartaceoSI:catTopCartaceoNO);
		}
		else {
			catTopCartaceoSI.setValue(false);
			catTopCartaceoNO.setValue(false);
			catTopCartaceoNonSpecificato.setValue(true);
			catTopCartaceoSI.setOriginalValue(false);
			catTopCartaceoNO.setOriginalValue(false);
			catTopCartaceoNonSpecificato.setOriginalValue(true);
			
			rgCatCartaceo.setOriginalValue(catTopCartaceoNonSpecificato);
		}
		Utils.setFontColorStyleBlack(catCartaceoLabel);
		
		if (biblioteca.getCatalogoInformatizzato() != null) {
			catTopInformatizzatoSI.setValue(biblioteca.getCatalogoInformatizzato());
			catTopInformatizzatoNO.setValue(!biblioteca.getCatalogoInformatizzato());
			catTopInformatizzatoNonSpecificato.setValue(false);
			catTopInformatizzatoSI.setOriginalValue(biblioteca.getCatalogoInformatizzato());
			catTopInformatizzatoNO.setOriginalValue(!biblioteca.getCatalogoInformatizzato());
			catTopInformatizzatoNonSpecificato.setOriginalValue(false);
			
			rgCatInform.setOriginalValue(biblioteca.getCatalogoInformatizzato()?catTopInformatizzatoSI:catTopInformatizzatoNO);
		}
		else {
			catTopInformatizzatoSI.setValue(false);
			catTopInformatizzatoNO.setValue(false);
			catTopInformatizzatoNonSpecificato.setValue(true);
			catTopInformatizzatoSI.setOriginalValue(false);
			catTopInformatizzatoNO.setOriginalValue(false);
			catTopInformatizzatoNonSpecificato.setOriginalValue(true);
			
			rgCatInform.setOriginalValue(catTopInformatizzatoNonSpecificato);
		}
		Utils.setFontColorStyleBlack(catInformLabel);
		
		if (biblioteca.getFondiAntichiFinoAl1830().compareToIgnoreCase(CostantiGestioneBiblio.NON_SPECIFICATO) == 0) {
			fondiAntichiField.setRawValue(CostantiGestioneBiblio.NON_SPECIFICATO);
		}
		else if (biblioteca.getFondiAntichiFinoAl1830().compareToIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI) == 0) {
			fondiAntichiField.setRawValue(CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI);
			fondiAntichiField.setSimpleValue(CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI);
		}
		else if (biblioteca.getFondiAntichiFinoAl1830().compareToIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI) == 0) {
			fondiAntichiField.setRawValue(CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI);
			fondiAntichiField.setSimpleValue(CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI);
		}
		else if (biblioteca.getFondiAntichiFinoAl1830().compareToIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI) == 0) {
			fondiAntichiField.setRawValue(CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI);
			fondiAntichiField.setSimpleValue(CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI);
		}
		
		fondiAntichiField.setOriginalValue(fondiAntichiField.getValue());
		Utils.setFontColorStyleBlack(fondiAntichiLabel);
		
		UIWorkflow.enableDisable(inventarioCartaceoSI);
		UIWorkflow.enableDisable(inventarioCartaceoNO);
		UIWorkflow.enableDisable(inventarioCartaceoNonSpecificato);
		
		UIWorkflow.enableDisable(inventarioInforSI);
		UIWorkflow.enableDisable(inventarioInforNO);
		UIWorkflow.enableDisable(inventarioInforNonSpecificato);

		UIWorkflow.enableDisable(catTopCartaceoSI);
		UIWorkflow.enableDisable(catTopCartaceoNO);
		UIWorkflow.enableDisable(catTopCartaceoNonSpecificato);
		
		UIWorkflow.enableDisable(catTopInformatizzatoSI);
		UIWorkflow.enableDisable(catTopInformatizzatoNO);
		UIWorkflow.enableDisable(catTopInformatizzatoNonSpecificato);

		UIWorkflow.setReadOnly(fondiAntichiField);
		UIWorkflow.hideView(aggiornaAll);
		UIWorkflow.hideView(resetAll);
		
		if(UIWorkflow.isReadOnly()==false){
			addKeyListenerForEnter();
		}else{
			removeKeyListenerForEnter();
		}
	}

	protected void addKeyListenerForEnter() {
		Utils.addKeyListenerForEnter(aggiornaAll, invCatForm);
	}
	private void removeKeyListenerForEnter() {
		Utils.removeKeyListenerForEnter( invCatForm);
	}
	
	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;
	}
}
