package it.inera.abi.gxt.client.mvc.view.center.biblioteche.forms;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ContenitoreFondiSpecialiPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.ListaSpecializzazioniPanel;
import it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget.TestoFondiSpecialiPanel;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per l'inserimento / modifica delle informazioni relative
 * ai fondi speciali e alle sue specializzazioni
 *
 */
public class SpecializzazioniFondiSpecialiPanel extends  ContentPanelForTabItem {
	private int id_biblio;
//	private List<ContenitoreSpecializzazionePanel> listaSpecializzazioniPanels;
	private List<TestoFondiSpecialiPanel> listaFondiSpecialiPanels;

	private ListaSpecializzazioniPanel listaSpecializzazioniPanel ;

//	private ContenitoreSpecializzazionePanel creazioneSpecializzazioneFormPanel;

	private ContenitoreFondiSpecialiPanel creazioneFondiSpecialiFormPanel;
	private ContenitoreFondiSpecialiPanel modificaFondiSpecialiFormPanel;

	private BibliotecheServiceAsync bibliotecheService;

	//	private	TestoFondiSpecialiPanel tmpPanel;
	boolean specializzazioniCaricate;
	boolean fondiSpecialiCaricati;
//	private Listener<BaseEvent> listener;

	private	FieldSet specializzazioniSet;
	private	FieldSet fondiSpecialiSet;

	private Button addSpecializzazioneButton = new Button("Aggiungi nuova specializzazione");
	private Button addFondoSpecialeButton = new Button("Aggiungi nuovo fondo speciale");

	public SpecializzazioniFondiSpecialiPanel() {
		super();

		bibliotecheService=Registry.get(Abi.BIBLIOTECHE_SERVICE);

		setMonitorWindowResize(true);
		setHeaderVisible(false);
		setBodyBorder(false);
		specializzazioniCaricate=false;
		fondiSpecialiCaricati=false;
		creazioneFondiSpecialiFormPanel=new ContenitoreFondiSpecialiPanel(false);
		creazioneFondiSpecialiFormPanel.addListener(Events.Submit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				addSingleFondiSpecialiPanel(creazioneFondiSpecialiFormPanel.getModelToAdd());
			}
		});

		Utils.setStylesButton(addFondoSpecialeButton);
		addFondoSpecialeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {

				final Window window = new Window();

				window.setSize(650, 450);
				window.setModal(true);
				window.setHeading("Nuovo fondo speciale");
				window.setLayout(new FitLayout());
				window.setClosable(true);

				/*CONTENUTO FINESTRA*/
				creazioneFondiSpecialiFormPanel.reset();
				creazioneFondiSpecialiFormPanel.setIdBiblio(id_biblio);
				window.add(creazioneFondiSpecialiFormPanel);

				creazioneFondiSpecialiFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						window.close();
					}
				});
				/*FINE---CONTENUTO FINESTRA*/

				window.show();
			}
		});

		modificaFondiSpecialiFormPanel=new ContenitoreFondiSpecialiPanel(true);
		modificaFondiSpecialiFormPanel.setIdBiblio(id_biblio);
		modificaFondiSpecialiFormPanel.addListener(Events.Add, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

			}
		});

	}

	public void createForm(){

		/*SPECIALIZZAZIONI*/
		LayoutContainer specializzazioni = new LayoutContainer();
		specializzazioni.setStyleAttribute("padding", "5px");

		specializzazioniSet = new FieldSet();
		Utils.setFieldSetProperties(specializzazioniSet, "Specializzazioni");
		/*FINE---NUOVO CONTENITORE SPECIALIZZAZIONI*/

		/*VECCHIA LISTA SPECIALIZZAZIONI*/
		listaSpecializzazioniPanel =new ListaSpecializzazioniPanel();
		listaSpecializzazioniPanel.setGrid();
		specializzazioniSet.add(listaSpecializzazioniPanel);
		/*FINE----VECCHIA LISTA SPECIALIZZAZIONI*/

		specializzazioni.add(specializzazioniSet);
		add(specializzazioni);
		/*FINE---SPECIALIZZAZIONI*/
		/*FONDI SPECIALI*/
		LayoutContainer fondiSpeciali = new LayoutContainer();
		fondiSpeciali.setStyleAttribute("padding", "5px");

		fondiSpecialiSet = new FieldSet();
		Utils.setFieldSetProperties(fondiSpecialiSet, "Fondi speciali");

		fondiSpecialiSet.add(addFondoSpecialeButton);

		Iterator<TestoFondiSpecialiPanel> itFondiSpeciali= listaFondiSpecialiPanels.iterator();
		while (itFondiSpeciali.hasNext()) {
			TestoFondiSpecialiPanel tmpPanel =(TestoFondiSpecialiPanel) itFondiSpeciali.next();
			fondiSpecialiSet.add(tmpPanel);
		}
		/*FINE---NUOVO CONTENITORE FONDI SPECIALI*/

		/*FINE--FONDI SPECIALI*/

		fondiSpeciali.add(fondiSpecialiSet);

		add(fondiSpeciali);

	}

	public void setFieldsValues() {
		listaSpecializzazioniPanel.setIdBiblioteca(biblioteca.getIdBiblio());
		listaSpecializzazioniPanel.setIdModificaSpecializzazionePanel();
		listaSpecializzazioniPanel.setIdNuovaSpecializzazionePanel();
		listaSpecializzazioniPanel.getLoader().load();

		if(UIWorkflow.isReadOnly()==false){
			addSpecializzazioneButton.show();
			addFondoSpecialeButton.show();
		}else{
			addSpecializzazioneButton.hide();
			addFondoSpecialeButton.hide();
		}
	}

	public void setIdBiblio(int idBiblio) {
		this.id_biblio = idBiblio;

	}
	public void createSpecializzazioniEFondiSpecialiPanels(){

		/*CREAZIONE PANNELLI FONDI SPECIALI*/
		RpcProxy<List<FondiSpecialiModel>> fondiSpecialiProxy = new RpcProxy<List<FondiSpecialiModel>>() {

			@Override
			protected void load(Object loadConfig,AsyncCallback<List<FondiSpecialiModel>> callback) {
				bibliotecheService.getFondiSpecialiByIdBiblioteca(id_biblio,callback);
			}
		};
		ModelReader fondiSpecialiReader = new ModelReader();

		final BaseListLoader<ListLoadResult<FondiSpecialiModel>>	fondiSpecialiLoader = new BaseListLoader<ListLoadResult<FondiSpecialiModel>>(
				fondiSpecialiProxy, fondiSpecialiReader);

		final ListStore<FondiSpecialiModel> fondiSpecialiStore = new ListStore<FondiSpecialiModel>(fondiSpecialiLoader);

		listaFondiSpecialiPanels =new ArrayList<TestoFondiSpecialiPanel>();
		fondiSpecialiLoader.load(); 


		fondiSpecialiLoader.addListener(Loader.Load, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				List<FondiSpecialiModel> listModels = fondiSpecialiStore.getModels();
				Iterator<FondiSpecialiModel> it= listModels.iterator();
				while (it.hasNext()) {
					FondiSpecialiModel fondiSpecialiModel = (FondiSpecialiModel) it.next();
					final	TestoFondiSpecialiPanel	tmpPanel= new TestoFondiSpecialiPanel();
					tmpPanel.setIdBiblio(id_biblio);
					tmpPanel.setModel(fondiSpecialiModel);
					tmpPanel.setValues();
					tmpPanel.setId(fondiSpecialiModel.getIdCatalogoInventario()+"");

					tmpPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

						@Override
						public void handleEvent(BaseEvent be) {
							tmpPanel.layout();
							fondiSpecialiSet.layout();
						}
					});
					tmpPanel.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

						@Override
						public void handleEvent(BaseEvent be) {
							fondiSpecialiSet.remove(tmpPanel);
							fondiSpecialiSet.layout();
						}
					});
					tmpPanel.addListener(Events.StartEdit, new Listener<BaseEvent>() {

						@Override
						public void handleEvent(BaseEvent be) {

							showModifyWindow(	(TestoFondiSpecialiPanel) be.getSource());
						}
					});
					listaFondiSpecialiPanels.add(tmpPanel);

				}
				removeAll();
				setBiblioteca(biblioteca);
				createForm();
				setFieldsValues();
				layout();
			}

		});

		/*FINE---CREAZIONE PANNELLI FONDI SPECIALI*/
	}

	public void showModifyWindow(TestoFondiSpecialiPanel testoFondiSpecialiPanel){
		final Window window = new Window();

		window.setSize(650, 420);
		window.setModal(true);
		window.setHeading("Nuovo fondo speciale");
		window.setLayout(new FitLayout());
		window.setClosable(true);

		/*CONTENUTO FINESTRA*/
		modificaFondiSpecialiFormPanel.reset();
		modificaFondiSpecialiFormPanel.setIdBiblio(id_biblio);
		modificaFondiSpecialiFormPanel.setValues(testoFondiSpecialiPanel.getModel());
		modificaFondiSpecialiFormPanel.setPaneltToModifyUpdate(testoFondiSpecialiPanel);
		window.add(modificaFondiSpecialiFormPanel);


		modificaFondiSpecialiFormPanel.addListener(Events.Close, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				window.close();
			}
		});
		/*FINE---CONTENUTO FINESTRA*/

		window.show();
	}

	public void addSingleFondiSpecialiPanel(FondiSpecialiModel newModel){
		final	TestoFondiSpecialiPanel tmpPanel= new TestoFondiSpecialiPanel();
		setModelToPanel(newModel, tmpPanel);
		fondiSpecialiSet.add(tmpPanel);
		fondiSpecialiSet.layout();
	}

	/**
	 * @param newModel
	 * @param tmpPanel
	 */
	private void setModelToPanel(FondiSpecialiModel newModel,	final TestoFondiSpecialiPanel tmpPanel) {
		tmpPanel.setIdBiblio(id_biblio);
		tmpPanel.setModel(newModel);
		tmpPanel.setValues();
		tmpPanel.setId(newModel.getIdCatalogoInventario()+"");

		tmpPanel.addListener(Events.Refresh, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				tmpPanel.layout();
			}
		});
		tmpPanel.addListener(Events.AfterEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				fondiSpecialiSet.remove(tmpPanel);
				fondiSpecialiSet.layout();
			}
		});
		tmpPanel.addListener(Events.StartEdit, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {

				showModifyWindow(	(TestoFondiSpecialiPanel) be.getSource());
			}
		});
	}


}
