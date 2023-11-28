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

import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.ajaxloader.client.Properties.TypeException;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.MapEventType;
import com.google.gwt.maps.client.events.MapHandlerRegistration;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dragend.DragEndMapEvent;
import com.google.gwt.maps.client.events.dragend.DragEndMapHandler;
import com.google.gwt.maps.client.events.dragstart.DragStartMapEvent;
import com.google.gwt.maps.client.events.dragstart.DragStartMapHandler;
import com.google.gwt.maps.client.overlays.Animation;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.InfoWindowOptions;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerOptions;
import com.google.gwt.maps.client.services.Geocoder;
import com.google.gwt.maps.client.services.GeocoderRequest;
import com.google.gwt.maps.client.services.GeocoderRequestHandler;
import com.google.gwt.maps.client.services.GeocoderResult;
import com.google.gwt.maps.client.services.GeocoderStatus;
import com.google.gwt.user.client.Element;

/**
 * Classe per la geolocalizzazione di una biblioteca (google maps)
 *
 */
public class NewGoogleGeoLocalizePanel extends Dialog {

	private String indirizzo;
	private String frazione;
	private String comune; 
	private String cap;
	private String codiceCitta;
	private	String stato;

	private Double latitudine;
	private Double longitudine;
	private Boolean modified = false;

	private TextField<String> indirizzoField;

	private TextField<String> frazioneField;
	private TextField<String> capField;
	private	TextField<String> comuneField;
	private TextField<String> statoField;

	private TextField<Double> latField;
	private TextField<Double> lngField;

	private VerticalPanel vp;
	private MapOptions mapOptions;
	private MapWidget mapWidget;
	private Marker marker;
	private Geocoder geocoder;
	private InfoWindow info;

	private Button geolocalizzaButton;
	private Button update;
	private Button reset;

	public NewGoogleGeoLocalizePanel() {

		this.indirizzo = null;
		this.frazione = null;
		this.cap = null;
		this.latitudine = null;
		this.longitudine = null;

		geocoder = Geocoder.newInstance();

		initialize();
	}

	public NewGoogleGeoLocalizePanel(String ruolo, String via, String numero, String localita, String cap, String citta, Double lat, Double lng) {

		this.indirizzo = via;
		this.frazione = (localita != null)?localita:"";
		this.cap = cap;
		this.codiceCitta = citta;
		this.latitudine = lat;
		this.longitudine = lng;

		geocoder = Geocoder.newInstance();

		initialize();
	}

	@Override
	protected void createButtons() {
		super.createButtons();
		getButtonBar().removeAll();

		geolocalizzaButton = new Button("Geolocalizza");
		geolocalizzaButton.setId(Dialog.CLOSE);
		geolocalizzaButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				modified = true;
				showAddress(makeAddressString());
			}
		});		
		addButton(geolocalizzaButton);	


		update = new Button("Update");
		update.setId(Dialog.OK);
		update.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if(modified) {
					saveForm();
				}
			}
		});
		addButton(update);

		reset = new Button("Reset");
		reset.setId(Dialog.CANCEL);
		reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				loadForm();
				if (latitudine != null && latitudine != 0 && longitudine != null && longitudine != 0) {
					mapWidget.setCenter(LatLng.newInstance(latitudine, longitudine));
					mapWidget.setZoom(14);

					marker.setPosition(LatLng.newInstance(latitudine, longitudine));
					mapWidget.triggerResize();

				} else { 
					showAddress(makeStartAddressString());
				}
				

				modified = false;
			}

		});
		addButton(reset);

		Button close = new Button("Chiudi");
		close.setId(Dialog.CLOSE);
		close.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				modified = false;
				hide();
			}
		});
		addButton(close);	


	}

	@Override
	protected void onShow() {
		super.onShow();

		if (!modified) {
			loadForm();

			if (latitudine != null && latitudine != 0 && longitudine != null && longitudine != 0) {
				LatLng latLong = LatLng.newInstance(this.latitudine, this.longitudine);

				mapWidget.setCenter(latLong);
				mapWidget.setZoom(14);
				
				placeMarker(latLong);
				mapWidget.triggerResize();			
			} 
			else {
				showAddress(makeStartAddressString());
			}
			
		}

		if (UIWorkflow.isReadOnly()) {
			indirizzoField.setEnabled(false);
			frazioneField.setEnabled(false);
			capField.setEnabled(false);
			comuneField.setEnabled(false);
			statoField.setEnabled(false);
			geolocalizzaButton.hide();
			update.hide();
			reset.hide();

		} else {
			indirizzoField.setEnabled(true);
			frazioneField.setEnabled(true);
			capField.setEnabled(true);
			comuneField.setEnabled(true);
			statoField.setEnabled(true);
			geolocalizzaButton.show();
			update.show();
			reset.show();
		}

	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
	}

	protected void initialize() {
		setModal(true);
		setHeading("Gestione Indirizzo e Localizzazione");
		setLayout(new RowLayout(Orientation.VERTICAL));
		setBodyStyle("backgroundColor:#ffffff;");
		setSize(700, -1);
		setClosable(false);
		setHideOnButtonClick(true);

		this.vp = new VerticalPanel();
		this.vp.setSpacing(10);

		addMap();

		addForm();

		add(this.vp);
	}


	public void addMap() {
		mapOptions = MapOptions.newInstance();
		mapOptions.setMapTypeId(MapTypeId.ROADMAP);
		mapOptions.setZoomControl(true);
		mapOptions.setDisableDefaultUi(true);

		mapWidget = new MapWidget(mapOptions);
		mapWidget.setWidth("660px");
		mapWidget.setHeight("210px");

//		//INERA
		LatLng inera = LatLng.newInstance(43.684927, 10.439283);
		mapWidget.setCenter(inera);
		mapWidget.setZoom(14);

		final MarkerOptions options = MarkerOptions.newInstance();
		marker = Marker.newInstance(options);
		marker.setAnimation(Animation.DROP);
		marker.setPosition(inera);


		marker.addDragStartHandler(new DragStartMapHandler() {

			@Override
			public void onEvent(DragStartMapEvent event) {
				if(info != null) {
					info.close();
					info = null;
				}
			}
		});

		marker.addDragEndHandler(new DragEndMapHandler() {

			@Override
			public void onEvent(DragEndMapEvent event) {
				LatLng point = null;
				try {
					point = (LatLng) event.getProperties().getObject("latLng");
					latField.setValue(point.getLatitude());
					lngField.setValue(point.getLongitude());
					mapWidget.setCenter(point);
					mapWidget.setZoom(14);
					
					modified = true;

				} catch (TypeException e) {
					e.printStackTrace();
				}

			}
		});

		marker.addClickHandler(new ClickMapHandler() {

			@Override
			public void onEvent(ClickMapEvent event) {
				LatLng point = event.getMouseEvent().getLatLng();

				InfoWindowOptions infoOptions = InfoWindowOptions.newInstance();
				info = InfoWindow.newInstance(infoOptions);
				info.setContent("<span style=\"font-weight:bold;font-size:1.3em;color:007DBC;padding-bottom:5px;\">Posizione Corrente ...</span><p><span style=\"font-style:italic;\">Lat:" + point.getLatitude() + " Long: " + point.getLongitude() + "</span></p>");
				info.setPosition(point);
				info.open(mapWidget);

			}
		});

		marker.setMap(mapWidget);

		MapHandlerRegistration.trigger(mapWidget, MapEventType.RESIZE);

		this.vp.add(mapWidget);
	}

	public void addForm() {
		FormPanel formIndirizzo = new FormPanel();
		formIndirizzo.setHeaderVisible(false);
		formIndirizzo.setBorders(false);
		formIndirizzo.setBodyBorder(false);
		FormLayout formLayoutindirizzo = new FormLayout();
		formLayoutindirizzo.setLabelAlign(LabelAlign.TOP);
		formIndirizzo.setLayout(formLayoutindirizzo);

		FormData data = new FormData();

		//Tabella Indirizzo
		LayoutContainer addressTable = new LayoutContainer(new TableLayout(3));
		addressTable.setWidth("660px");

		TableData colonna1 = new TableData();
		colonna1.setWidth("33%");
		colonna1.setPadding(5);

		TableData colonnaSpan3 = new TableData();
		colonnaSpan3.setColspan(2);
		colonnaSpan3.setPadding(5);

		FormLayout dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);

		LayoutContainer viaPiazzaContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		viaPiazzaContainer.setLayout(dataLayout);

		indirizzoField = new TextField<String>();
		indirizzoField.setFieldLabel("Via/Piazza n°civico");
		indirizzoField.setEmptyText("Digita l'indirizzo...");
		indirizzoField.setWidth(200);
		indirizzoField.addListener(Events.Change, new Listener<FieldEvent>(){
			public void handleEvent(FieldEvent be) {
				modified = true;
			}});
		viaPiazzaContainer.add(indirizzoField, data);
		addressTable.add(viaPiazzaContainer, colonna1);


		LayoutContainer frazioneContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		frazioneContainer.setLayout(dataLayout);

		frazioneField = new TextField<String>();
		frazioneField.setFieldLabel("Localit&agrave;/Frazione");
		frazioneField.setEmptyText("Digita la Località/Frazione...");
		frazioneField.setWidth(200);
		frazioneField.addListener(Events.Change, new Listener<FieldEvent>(){
			public void handleEvent(FieldEvent be) {
				modified = true;
			}});
		frazioneContainer.add(frazioneField, data);
		addressTable.add(frazioneContainer, colonna1);

		LayoutContainer capContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		capContainer.setLayout(dataLayout);

		capField = new TextField<String>();
		capField.setEmptyText("Digita il CAP...");
		capField.setFieldLabel("CAP");
		capField.setWidth(200);
		capField.addListener(Events.Change, new Listener<FieldEvent>(){
			public void handleEvent(FieldEvent be) {
				modified = true;
			}});
		capContainer.add(capField, data);
		addressTable.add(capContainer, colonna1);

		LayoutContainer cittaContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		cittaContainer.setLayout(dataLayout);

		comuneField = new TextField<String>();
		comuneField.setFieldLabel("Comune");
		comuneField.setEmptyText("Digita il comune...");
		comuneField.setEnabled(false);
		comuneField.setReadOnly(true);			

		comuneField.setWidth(200);
//		comuneField.addListener(Events.Change, new Listener<FieldEvent>(){
//			public void handleEvent(FieldEvent be) {
//				modified = true;
//			}});
		cittaContainer.add(comuneField, data);
		addressTable.add(cittaContainer, colonna1);



		LayoutContainer statoContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		statoContainer.setLayout(dataLayout);

		statoField = new TextField<String>();
		statoField.setEmptyText("Digita lo stato...");
		statoField.setFieldLabel("Stato");
		statoField.setWidth(200);
		statoContainer.add(statoField, data);
		addressTable.add(statoContainer, colonna1);

		//Tabella Coordinate
		LayoutContainer geoLocationTable = new LayoutContainer(new TableLayout(2));
		geoLocationTable.setWidth("660px");

		TableData geoLocData = new TableData();
		geoLocData.setWidth("50%");
		geoLocData.setPadding(5);

		LayoutContainer geoLoc1 = new LayoutContainer();
		FormLayout locLayout = new FormLayout();
		locLayout.setLabelAlign(LabelAlign.TOP);
		geoLoc1.setLayout(locLayout);

		latField = new TextField<Double>();
		latField.setFieldLabel("Latitudine");
		latField.setEnabled(false);
		latField.setWidth(300);
		geoLoc1.add(latField, new FormData("100%"));
		geoLocationTable.add(geoLoc1, geoLocData);

		LayoutContainer geoLoc2 = new LayoutContainer();
		locLayout = new FormLayout();
		locLayout.setLabelAlign(LabelAlign.TOP);
		geoLoc2.setLayout(locLayout);

		lngField = new TextField<Double>();
		lngField.setFieldLabel("Longitudine");
		lngField.setWidth(300);
		lngField.setEnabled(false);
		geoLoc2.add(lngField, new FormData("100%"));
		geoLocationTable.add(geoLoc2, geoLocData);

		formIndirizzo.add(addressTable, data);
		formIndirizzo.add(geoLocationTable, data);
		this.vp.add(formIndirizzo);

	}

	private void loadForm() {
		indirizzoField.setValue((indirizzo != null) ? indirizzo : "");
		frazioneField.setValue((frazione != null) ? frazione : "");
		capField.setValue((cap != null) ? cap : "");
		comuneField.setValue(comune != null ? comune : "");
		statoField.setValue(stato != null ? stato : "");
		latField.setValue(latitudine);
		lngField.setValue(longitudine);

	}

	private void saveForm() {
		indirizzo = indirizzoField.getValue();
		frazione = frazioneField.getValue();
		cap = capField.getValue();
		latitudine = (latField.getRawValue() != null) ? new Double(latField.getRawValue()) : null;
		longitudine = (lngField.getRawValue() != null)? new Double(lngField.getRawValue()) : null;
		fireEvent(Events.Update);
		//		comune = comuneField.getValue();
		
		modified = false;
	}

	public void cleanUp() {
		indirizzo = null;
		frazione = null;
		cap = null;
		latitudine = null;
		longitudine = null;
		comune = null;
		indirizzoField.setValue(null);
		frazioneField.setValue(null);
		capField.setValue(null);
		latField.setValue(null);
		lngField.setValue(null);
		comuneField.setValue(null);
	}


	private String makeStartAddressString() {
		String res = "";
		res += (indirizzo != null) ? indirizzo + ", " : "";
		res += ((frazione != null)? frazione + ", " : "");
		res += ((cap != null) ? cap + ", " : "");
		res += ((comune != null) ? comune + ", " : "");
		res += ((stato != null) ? stato + ", " : "");
		return res;
	}

	private String makeAddressString() {
		String res = "";
		res += (indirizzoField.getValue() != null) ? indirizzoField.getValue() + ", " : "";
		res += ((frazioneField.getValue() != null) ? frazioneField.getValue() + ", " : "");
		res += ((capField.getValue() != null) ? capField.getValue() + ", " : "");
		res += ((comuneField.getValue() != null) ? comuneField.getValue() + ", " : "");
		res += ((statoField.getValue() != null) ? statoField.getValue() + ", " : "");

		return res;
	}

	/**
	 * Nel caso in cui cambi uno dei parametri dell'indirizzo viene effettuato il
	 * calcolo del punto corrispondente e ricentrata la mappa.
	 * 
	 * @param address
	 */
	private void showAddress(final String address) {
		GeocoderRequest geocoderRequest = GeocoderRequest.newInstance();
		geocoderRequest.setAddress(address);

		geocoder.geocode(geocoderRequest, new GeocoderRequestHandler() {

			@Override
			public void onCallback(JsArray<GeocoderResult> results,
					GeocoderStatus status) {

				if (status.compareTo(GeocoderStatus.ERROR) == 0) {
					//--> calback di google maps, nn passa il Throwable? UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
					MessageBox.alert("Attenzione", "L'indirizzo " + address + " non &egrave; stato trovato.", null);

				} else if (status.compareTo(GeocoderStatus.OK) == 0) {
					if (results != null && results.length() > 0) {
						if (results.get(0) != null && results.get(0).getGeometry() != null && results.get(0).getGeometry().getLocation() != null) {
							LatLng point = LatLng.newInstance(results.get(0).getGeometry().getLocation().getLatitude(), results.get(0).getGeometry().getLocation().getLongitude());
							latField.setValue(point.getLatitude());
							lngField.setValue(point.getLongitude());
							mapWidget.setCenter(point);
							mapWidget.setZoom(14);

							marker.setMap((MapWidget) null);
							placeMarker(point);
							
						}
					}
				}
			}
		});

	}

	private void placeMarker(LatLng point) {
		marker.setPosition(point);
		marker.setMap(mapWidget);

		/* Se la geolocalizzazione è in sola lettura il marker non può essere spostato dalla posizione salvata. */
		if (UIWorkflow.isReadOnly()) {
			marker.setDraggable(false);

		} else {
			marker.setDraggable(true);
		}

		marker.setVisible(true);
		
		mapWidget.triggerResize();
	}

	public Double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}

	public Double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}


	public String getViaPiazza() {
		return indirizzo;
	}

	public void setViaPiazza(String viaPiazza) {
		this.indirizzo = viaPiazza;
	}

	public String getLocalita() {
		return frazione;
	}

	public void setLocalita(String localita) {
		this.frazione = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return comune;
	}

	public void setCitta(String citta) {
		this.comune = citta;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return this.stato;
	}

	public String getCodiceCitta() {
		return codiceCitta;
	}

	public void setCodiceCitta(String codiceCitta) {
		this.codiceCitta = codiceCitta;
	}

	public Boolean isModified() {
		return modified;
	}

	public void setModified(Boolean modified) {
		this.modified = modified;
	}

}
