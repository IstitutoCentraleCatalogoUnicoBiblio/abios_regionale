package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.FondiSpecialiModel;
import it.inera.abi.gxt.client.resources.Resources;
import it.inera.abi.gxt.client.services.BibliotecheServiceAsync;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe widget per il caricamento / aggiornamento delle informazioni relative
 * ai fondi speciali
 *
 */
public class TestoFondiSpecialiPanel extends LayoutContainer {

	private int idBiblio;
	private FondiSpecialiModel model;
	boolean modifica;
	//	private Window modificaWindow;
	private BibliotecheServiceAsync bibliotecheService;

	private static final String FONT_SIZE="12px";
	private static final String FONT_WEIGHT="bold";
	private static final String FONT_COLOR_EX="#385F95";

	private Text denominazioneFondoField ;
	private	Text descrizioneFondoField;
	private Text fondoDepositatoField;
	private Text catalogoInventarioTipoField;
	private	Text urlOnline;
	private	Text dewey;
	private	Text descrUfficialeDeweyField; 

	private	Button aggiornaButton;
	private	Button rimuoviButton;

	private TableData labelTableData;

	public TestoFondiSpecialiPanel(){
		super();

		bibliotecheService= Registry.get(Abi.BIBLIOTECHE_SERVICE);

		setStyleAttribute("marginTop", "10px");
		setStyleAttribute("marginBottom", "10px");
		setStyleAttribute("marginLeft", "10px");
		setHeight(215);
		setWidth(750);
		setMonitorWindowResize(true);
		setLayout(new FitLayout());
		setBorders(true);

		createFondiSpecialiDispay();
	}

	public void createFondiSpecialiDispay(){
		labelTableData = new TableData();
		labelTableData.setWidth("120px");
		//		labelTableData.setMargin(3);
		labelTableData.setPadding(5);
		//		
		TableLayout table =new TableLayout(2);
		table.setWidth("500");
		table.setCellHorizontalAlign(HorizontalAlignment.LEFT);
		LayoutContainer tableContainer= new LayoutContainer(table);

		Text denominazioneFondoLabel = new Text("Denominazione:");
		denominazioneFondoLabel.setStyleAttribute("fontSize", "12px");

		denominazioneFondoField = new Text();
		Utils.setFontSizeWeightStylesCustom(denominazioneFondoField,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		Text descrizioneFondoLabel = new Text("Descrizione:");
		descrizioneFondoLabel.setStyleAttribute("fontSize", "12px");

		descrizioneFondoField = new Text();
		Utils.setFontSizeWeightStylesCustom(descrizioneFondoField,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		Text fondoDepositatoLabel = new Text("Fondo depositato:");
		fondoDepositatoLabel.setStyleAttribute("fontSize", "12px");

		fondoDepositatoField = new Text();
		Utils.setFontSizeWeightStylesCustom(fondoDepositatoField,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);


		Text catalogoInventarioTipoLabel = new Text("Catalogo inventario:");
		catalogoInventarioTipoLabel.setStyleAttribute("fontSize", "12px");

		catalogoInventarioTipoField = new Text();
		Utils.setFontSizeWeightStylesCustom(catalogoInventarioTipoField,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		Text urlOnlineLabel = new Text("Url/Citazione biblio:");
		urlOnlineLabel.setStyleAttribute("fontSize", "12px");

		urlOnline = new Text();
		Utils.setFontSizeWeightStylesCustom(urlOnline,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		Text deweyLabel = new Text("Dewey:");
		deweyLabel.setStyleAttribute("fontSize", "12px");

		dewey = new Text();
		Utils.setFontSizeWeightStylesCustom(dewey,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		Text descrUfficialeDeweyLabel = new Text("Descrizione Dewey:");
		descrUfficialeDeweyLabel.setStyleAttribute("fontSize", "12px");

		descrUfficialeDeweyField = new Text();
		Utils.setFontSizeWeightStylesCustom(descrUfficialeDeweyField,FONT_SIZE,FONT_WEIGHT,FONT_COLOR_EX);

		aggiornaButton = new Button("Modifica");
		aggiornaButton.setIcon(Resources.ICONS.edit());
		aggiornaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				fireEvent(Events.StartEdit);
			}
		});

		rimuoviButton = new Button("Rimuovi");
		rimuoviButton.setIcon(Resources.ICONS.delete());
		rimuoviButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>(){

					@Override
					public void handleEvent(MessageBoxEvent ce) {
						Button btn = ce.getButtonClicked();
						if (btn.getText().equalsIgnoreCase("Si")) {
							bibliotecheService.removeFondiSpeciali(idBiblio, model.getIdFondiSpeciali(), new AsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									fireRemoveMeEvent();
									AbiMessageBox.messageSuccessAlertBox(AbiMessageBox.ESITO_RIMOZIONE_SUCCESS_VOCE_MESSAGE,AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
								}

								@Override
								public void onFailure(Throwable caught) {
									if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
										AbiMessageBox.messageErrorAlertBox(AbiMessageBox.ESITO_RIMOZIONE_FAILURE_VOCE_MESSAGE, AbiMessageBox.ESITO_RIMOZIONE_VOCE_TITLE);
									}
								}
							});
						}
					}
				};
				AbiMessageBox.messageConfirmOperationAlertBox(AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_MESSAGE, AbiMessageBox.CONFERMA_RIMOZIONE_VOCE_TITLE, l);
			}
		});

		/*AGGIUNGO I FIELD*/

		tableContainer.add(denominazioneFondoLabel,labelTableData);
		tableContainer.add(denominazioneFondoField);

		tableContainer.add(descrizioneFondoLabel,labelTableData);
		tableContainer.add(descrizioneFondoField);

		tableContainer.add(fondoDepositatoLabel,labelTableData);
		tableContainer.add(fondoDepositatoField);

		tableContainer.add(catalogoInventarioTipoLabel,labelTableData);
		tableContainer.add(catalogoInventarioTipoField);

		tableContainer.add(urlOnlineLabel,labelTableData);
		tableContainer.add(urlOnline);

		tableContainer.add(deweyLabel,labelTableData);
		tableContainer.add(dewey);

		tableContainer.add(descrUfficialeDeweyLabel,labelTableData);
		tableContainer.add(descrUfficialeDeweyField);

		tableContainer.add(aggiornaButton,labelTableData);
		tableContainer.add(rimuoviButton);
		add(tableContainer);
	}

	public void setValues(){

		denominazioneFondoField.setText(model.getDenominazione());
		if(model.getDescrizione()!=null)
			descrizioneFondoField.setText(model.getDescrizione());
		else descrizioneFondoField.setText("");

		if (model.getFondoDepositato() != null) {
			if (model.getFondoDepositato().equalsIgnoreCase("Si")) {
				fondoDepositatoField.setText("Si");
				
			} else if (model.getFondoDepositato().equalsIgnoreCase("No")) {
				fondoDepositatoField.setText("No");
				
			} else {
				fondoDepositatoField.setText("Non specificato");
			}
			
		} else {
			fondoDepositatoField.setText("Non specificato");
		}

		if(model.getCatalogoInventarioDescr()!=null)
			catalogoInventarioTipoField.setText(model.getCatalogoInventarioDescr());
		else {
			catalogoInventarioTipoField.setText("");
		}

		if(model.getUrlOnline()!=null){
			urlOnline.setText(model.getUrlOnline());
		}else urlOnline.setText("");

		if(model.getDewey()!=null){
			dewey.setText(model.getDewey());

			if(model.getDeweyDescr()!=null){
				descrUfficialeDeweyField.setText( model.getDeweyDescr());
			}else{
				descrUfficialeDeweyField.setText("");
			}
		}
		else {
			dewey.setText("");
		}

		if(UIWorkflow.isReadOnly()){
			aggiornaButton.hide();
			rimuoviButton.hide();
		}else{
			aggiornaButton.show();
			rimuoviButton.show();
		}
	}

	public void setIdBiblio(int idBiblio){
		this.idBiblio=idBiblio;
	}

	public void fireReleoadbiblioDataEvent() {
		Dispatcher.get().dispatch(AppEvents.ReloadBiblioData,this.idBiblio);
	}

	public void setModel(FondiSpecialiModel model){
		this.model=model;
	}

	public FondiSpecialiModel getModel(){
		return model;
	}


	public void fireRemoveMeEvent() {
			fireEvent(Events.AfterEdit);

	}
	
	public void fireRefreshMeEvent() {
		setValues();
		layout();
		fireEvent(Events.Refresh);

}

}
