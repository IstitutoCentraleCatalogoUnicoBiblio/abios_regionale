package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiTabelleDinamiche;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaPubblicazioniPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaSpogliMaterialeBibliografico;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class InfoCatalogazionePanel extends ContentPanelForTabItem {

	private int id_biblio;
	private ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel listaIndicizzazioneClassificataPanel;
	private ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel listaIndicizzazionePerSoggettoPanel;
	private ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel listaNormeCatalogazionePanel;
	private	ListaSpogliMaterialeBibliografico listaSpogliMaterialeBibliografico; 
	private ListaPubblicazioniPanel listaPubblicazioniPanel;

	private Button bibliografiaReset;
	private Button bibliografiaAggiorna;
	private TextArea bibliografiaBox; 
	
	private BibliotecheServiceAsync bibliotecheService;
	
	private FormPanel bibliografiaForm;
	public InfoCatalogazionePanel() {
		super();
		setMonitorWindowResize(true);

		setHeaderVisible(false);
		setBodyBorder(false);
		/* SISTEMI INDICIZZAZIONE */
		LayoutContainer sistemiIndicizzaizione = new LayoutContainer();
		sistemiIndicizzaizione.setStyleAttribute("padding", "5px");

		FieldSet sistemiIndicizzaizioneSet = new FieldSet();
		Utils.setFieldSetProperties(sistemiIndicizzaizioneSet, "Sistemi di indicizzazione");
		sistemiIndicizzaizioneSet.setCollapsible(true);
		/*Indicizzazione Classificata*/
		listaIndicizzazioneClassificataPanel = new ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA_INDEX,CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_CLASSIFICATA);
		listaIndicizzazioneClassificataPanel.setGrid();
		sistemiIndicizzaizioneSet.add(listaIndicizzazioneClassificataPanel);

		/*INdicizzazione Per Soggetto*/
		listaIndicizzazionePerSoggettoPanel = new ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel(CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO_INDEX, CostantiTabelleDinamiche.INDICIZZAZIONE_SISTEMI_IND_PER_SOGGETTO);
		listaIndicizzazionePerSoggettoPanel.setGrid();
		sistemiIndicizzaizioneSet.add(listaIndicizzazionePerSoggettoPanel);

		sistemiIndicizzaizione.add(sistemiIndicizzaizioneSet);
		add(sistemiIndicizzaizione);
		/* FINE---SISTEMI INDICIZZAZIONE */

		/* NORME DI CATALOGAZIONE */
		LayoutContainer normeCatalogazione = new LayoutContainer();
		normeCatalogazione.setStyleAttribute("padding", "5px");

		FieldSet normeCatalogazioneSet = new FieldSet();
		Utils.setFieldSetProperties(normeCatalogazioneSet, "Norme di catalogazione");
		normeCatalogazioneSet.setCollapsible(true);
		listaNormeCatalogazionePanel = new ListaPerCaricamentoDatiTabelleDinamicheAVoceSingolaPanel( CostantiTabelleDinamiche.CATALOGAZIONE_NORME_INDEX, CostantiTabelleDinamiche.CATALOGAZIONE_NORME);
		listaNormeCatalogazionePanel.setGrid();
		normeCatalogazioneSet.add(listaNormeCatalogazionePanel);

		normeCatalogazione.add(normeCatalogazioneSet);
		add(normeCatalogazione);
		/* FINE---NORME DI CATALOGAZIONE */

		/* SPOGLI MATERIALE BIBLIOGRAFICO */
		LayoutContainer spogliMaterialeBibliografico = new LayoutContainer();
		spogliMaterialeBibliografico.setStyleAttribute("padding", "5px");

		FieldSet spogliMaterialeBibliograficoSet = new FieldSet();
		Utils.setFieldSetProperties(spogliMaterialeBibliograficoSet, "Spogli materiale bibliografico");
		spogliMaterialeBibliograficoSet.setCollapsible(true);
		listaSpogliMaterialeBibliografico = new ListaSpogliMaterialeBibliografico();
		listaSpogliMaterialeBibliografico.setGrid();
		spogliMaterialeBibliograficoSet.add(listaSpogliMaterialeBibliografico);

		spogliMaterialeBibliografico.add(spogliMaterialeBibliograficoSet);
		add(spogliMaterialeBibliografico);
		/* FINE---SPOGLI MATERIALE BIBLIOGRAFICO */

		/* PUBBLICAZIONI */
		LayoutContainer pubblicazioni = new LayoutContainer();
		pubblicazioni.setStyleAttribute("padding", "5px");

		FieldSet pubblicazioniSet = new FieldSet();
		Utils.setFieldSetProperties(pubblicazioniSet, "Pubblicazioni");
		pubblicazioniSet.setCollapsible(true);

		listaPubblicazioniPanel	= new ListaPubblicazioniPanel();
		listaPubblicazioniPanel.setGrid();

		pubblicazioniSet.add(listaPubblicazioniPanel);
		pubblicazioni.add(pubblicazioniSet);
		add(pubblicazioni);
		/* FINE---PUBBLICAZIONI */

		/* BIBLIOGRAFIA */
		LayoutContainer bibliografia = new LayoutContainer();
		bibliografia.setStyleAttribute("padding", "5px");
		
		FieldSet bibliografiaSet = new FieldSet();
		Utils.setFieldSetProperties(bibliografiaSet, "Bibliografia");
		bibliografiaSet.setCollapsible(true);

		bibliografiaForm = new FormPanel();
		bibliografiaForm.setWidth(750);
		bibliografiaForm.setHeaderVisible(false);
		bibliografiaForm.setBorders(false);
		bibliografiaForm.setBodyBorder(false);
		bibliografiaForm.setButtonAlign(HorizontalAlignment.RIGHT);
		bibliografiaForm.setAnimCollapse(true);


		FormLayout bibliografiaFormLayout = new FormLayout();
		bibliografiaFormLayout.setLabelAlign(LabelAlign.TOP);

		bibliografiaForm.setLayout(bibliografiaFormLayout);

		FormButtonBinding bibliografiaBind = new FormButtonBinding(bibliografiaForm);	
		bibliografiaBox = new TextArea();
		
//		bibliografiaBox.setPreventScrollbars(true);
		bibliografiaBox.setFieldLabel("Bibliografia");
		bibliografiaBox.setEmptyText("Inserisci bibliografia...");
		bibliografiaBox.setHeight("100px");
		bibliografiaBox.setWidth(750);
		bibliografiaBox.setBorders(true);
		Utils.addListenerToChangeLabelColorIfModifiedTextArea(bibliografiaBox, "Bibliografia");
		
		bibliografiaAggiorna = new Button("Aggiorna");
		Utils.setStylesButton(bibliografiaAggiorna);
		bibliografiaAggiorna.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				bibliotecheService = Registry.get(Abi.BIBLIOTECHE_SERVICE);

				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

					@Override
					public void handleEvent(MessageBoxEvent ce) {

						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {

							bibliotecheService.inserisciBibliograficaInfoCatalogazione(id_biblio,bibliografiaBox.getValue(), new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) // controllo se l'errore è dovuto alla richiesta di login
									AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_CREAZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
								}
 
								@Override
								public void onSuccess(Void result) {
									bibliografiaBox.setOriginalValue(bibliografiaBox.getValue());
									Utils.setFontColorStyleBlackTextArea(bibliografiaBox, "Bibliografia");
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_CREAZIONE_SUCCESS_VOCE_MESSAGE, AbiMessageBox.ESITO_CREAZIONE_VOCE_TITLE);
									fireReleoadbiblioDataEvent();
								}
							});
						} 
						
					}
				};

				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_CREAZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_CREAZIONE_VOCE_TITLE, l);
			}

		});

		bibliografiaReset = new Button("Reset");
		Utils.setStylesButton(bibliografiaReset);
		bibliografiaReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				bibliografiaForm.reset();
				Utils.setFontColorStyleBlackTextArea(bibliografiaBox, "Bibliografia");
			}
		});
		
		bibliografiaBind.addButton(bibliografiaAggiorna);
		bibliografiaBind.addButton(bibliografiaReset);

		bibliografiaForm.add(bibliografiaBox,new FormData("-20"));
		
		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(5);
		LayoutContainer buttons = new LayoutContainer(tableLayout);
		buttons.add(bibliografiaAggiorna);
		buttons.add(bibliografiaReset);
		
		bibliografiaForm.add(buttons);

		bibliografiaSet.add(bibliografiaForm);

		bibliografia.add(bibliografiaSet);
		add(bibliografia);
		/* FINE---BIBLIOGRAFIA */
	}
	public void setFieldsValues() {
		listaIndicizzazioneClassificataPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaIndicizzazioneClassificataPanel.getLoader().load();
		listaIndicizzazionePerSoggettoPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaIndicizzazionePerSoggettoPanel.getLoader().load();
		listaNormeCatalogazionePanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaNormeCatalogazionePanel.getLoader().load();
		listaSpogliMaterialeBibliografico.setIdBiblioteca(biblioteca.getIdBiblio());
		listaSpogliMaterialeBibliografico.getLoader().load();
		listaPubblicazioniPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaPubblicazioniPanel.getLoader().load();
	
		
		if (biblioteca.getBibliografia() != null) {
			bibliografiaBox.setValue(biblioteca.getBibliografia());
			bibliografiaBox.setOriginalValue(biblioteca.getBibliografia());
		}
		else {
			bibliografiaBox.setValue(null);
			bibliografiaBox.setOriginalValue(null);
		}
		Utils.setFontColorStyleBlackTextArea(bibliografiaBox, "Bibliografia");
		
		UIWorkflow.hideView(bibliografiaReset);
		UIWorkflow.hideView(bibliografiaAggiorna);
		UIWorkflow.setReadOnly(bibliografiaBox);
//		if(UIWorkflow.isReadOnly()==false){
//			addKeyListenerForEnter();
//		}else{
//			removeKeyListenerForEnter();
//		}
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;
	}
	
//	protected void addKeyListenerForEnter() {
//		Utils.addKeyListenerForEnter(bibliografiaAggiorna,  bibliografiaForm);
//	}
//	
//	private void removeKeyListenerForEnter() {
//		Utils.removeKeyListenerForEnter( bibliografiaForm);
//	}
}
