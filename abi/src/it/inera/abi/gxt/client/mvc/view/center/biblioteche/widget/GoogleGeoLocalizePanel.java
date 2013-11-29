package it.inera.abi.gxt.client.mvc.view.center.biblioteche.widget;

import it.inera.abi.gxt.client.mvc.view.AppView;
import it.inera.abi.gxt.client.workflow.UIWorkflow;

import com.extjs.gxt.ui.client.Registry;
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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.MapEventType;
import com.google.gwt.maps.client.events.MapHandlerRegistration;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.events.dblclick.DblClickMapEvent;
import com.google.gwt.maps.client.events.dblclick.DblClickMapHandler;
import com.google.gwt.maps.client.events.dragend.DragEndMapEvent;
import com.google.gwt.maps.client.events.dragend.DragEndMapHandler;
import com.google.gwt.maps.client.overlays.Animation;
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
public class GoogleGeoLocalizePanel extends Dialog {

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

	private Button geolocalizzaButton;
	private Button update;
	private Button reset;

	private MapWidget mapWidget;
	private Marker marker;
	private Geocoder geocoder;

	LayoutContainer wrapper = (LayoutContainer) Registry.get(AppView.CENTER_PANEL);

	public GoogleGeoLocalizePanel() {
		this.indirizzo = null;
		this.frazione = null;
		this.cap = null;
		this.latitudine = null;
		this.longitudine = null;

		initialize();
	}

	public GoogleGeoLocalizePanel(String ruolo, String via, String numero, String localita, String cap, String citta, Double lat, Double lng) {
		this.indirizzo = via;
		this.frazione = (localita != null) ? localita : "";
		this.cap = cap;
		this.codiceCitta = citta;
		this.latitudine = lat;
		this.longitudine = lng;

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
				if (modified) {
					showAddress(makeAddressString());
				}
			}
		});		
		addButton(geolocalizzaButton);	

		update = new Button("Update");
		update.setId(Dialog.OK);
		update.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (modified) {
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
				if (latitudine != null && longitudine != null) {
					LatLng latLong = LatLng.newInstance(latitudine.doubleValue(), longitudine.doubleValue());
					mapWidget.setCenter(latLong);
					placeMarker(latLong);
					
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
		loadForm();

		if (this.latitudine != null &&  this.longitudine != null) {
			LatLng latLong = LatLng.newInstance(this.latitudine.doubleValue(), this.longitudine.doubleValue());
			mapWidget.setCenter(latLong);
			placeMarker(latLong);
			
		} else {
			showAddress(makeStartAddressString());
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

		MapHandlerRegistration.trigger(mapWidget, MapEventType.RESIZE);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
	}

	protected void initialize() {
		setModal(true);
		setHeading("Gestione Indirizzo e Localizzazione");
		setLayout(new RowLayout(Orientation.VERTICAL));
		setBodyStyle("padding:10px;backgroundColor:#ffffff;");
		setSize(700, -1);
		setClosable(false);

		this.vp = new VerticalPanel();
		//this.vp.setSpacing(10);
		addMap();
		addForm();

		add(this.vp);
	}


	public void addMap() {
		MapOptions opts = MapOptions.newInstance();
		opts.setMapTypeId(MapTypeId.ROADMAP);
		opts.setZoom(10);
		opts.setMapTypeControl(true);
		LatLng center = LatLng.newInstance(41.892916,12.48252);
		opts.setCenter(center);
		mapWidget = new MapWidget(opts);
		mapWidget.setWidth("660px");
		mapWidget.setHeight("210px");

		mapWidget.addDblClickHandler(new DblClickMapHandler() {

			@Override
			public void onEvent(DblClickMapEvent event) {
				// TODO
			}
		});

		mapWidget.addClickHandler(new ClickMapHandler() {

			@Override
			public void onEvent(ClickMapEvent event) {
				// TODO
			}
		});


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

		// Tabella Indirizzo
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
		indirizzoField.addListener(Events.OnKeyUp, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				modified = true;
			}
		});

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
		frazioneField.addListener(Events.OnKeyUp, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				modified = true;
			}
		});

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
		capField.addListener(Events.OnKeyUp, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				modified = true;
			}
		});
		
		capContainer.add(capField, data);
		addressTable.add(capContainer, colonna1);

		LayoutContainer cittaContainer = new LayoutContainer();
		dataLayout = new FormLayout();
		dataLayout.setLabelAlign(LabelAlign.TOP);
		cittaContainer.setLayout(dataLayout);

		comuneField = new TextField<String>();
		comuneField.setFieldLabel("Comune");
		comuneField.setEmptyText("Digita il comune...");
		comuneField.setWidth(200);
		comuneField.addListener(Events.OnKeyUp, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				modified = true;
			}
		});
		
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
		statoField.addListener(Events.OnKeyUp, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent fe) {
				modified = true;
			};
		});
		
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
		latitudine = (latField.getRawValue() != null) ? new Double(latField.getRawValue()) : 0;
		longitudine = (lngField.getRawValue() != null) ? new Double(lngField.getRawValue()) : 0;
		fireEvent(Events.Update);
		// comune = comuneField.getValue();
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
		res += ((frazione != null) ? frazione + ", " : "");
		res += ((cap != null) ? cap + ", " : "");
		res += ((comune != null) ? comune + ", " : "");
		res += ((stato != null) ? stato + ", " : "");
		return res;
	}

	private String makeAddressString() {
		String res = "";
		res += (indirizzoField.getValue() != null) ? indirizzoField.getValue() + ", " : "";
		res +=((frazioneField.getValue() != null) ? frazioneField.getValue() + ", " : "");
		res +=((capField.getValue() != null) ? capField.getValue() + ", " : "");
		res +=((comuneField.getValue() != null) ? comuneField.getValue() + ", " : "");
		res +=((statoField.getValue() != null) ? statoField.getValue() + ", " : "");
		return res;
	}

	private final GoogleGeoLocalizePanel thistmp = this;

	private void showAddress(final String address) {
		GeocoderRequest geocoderRequest = GeocoderRequest.newInstance();
		geocoderRequest.setAddress(address);
		geocoder = Geocoder.newInstance();
		thistmp.mask("Attendere...", "x-mask-loading");
		geocoder.geocode(geocoderRequest, new GeocoderRequestHandler() {
			@Override
			public void onCallback(JsArray<GeocoderResult> results,	GeocoderStatus status) {
				if (GeocoderStatus.OK.compareTo(status) == 0 && results != null && results.length() > 0) {
					GeocoderResult geocoderResult = results.get(0);
					LatLng point = geocoderResult.getGeometry().getLocation();
					latField.setValue(point.getLatitude());
					lngField.setValue(point.getLongitude());
					mapWidget.setCenter(point);
					placeMarker(point);

				} else {
					MessageBox.alert("Attenzione", "L'indirizzo " + address + " non &egrave; stato trovato.", null);
				}
				thistmp.unmask();
			}
		});
	}

	private void placeMarker(LatLng center) {
		if (marker == null) {
			MarkerOptions options = MarkerOptions.newInstance();
			options.setAnimation(Animation.DROP);
			options.setPosition(center);

			marker = Marker.newInstance(options);

			marker.setMap(mapWidget);

			marker.addDragEndHandler(new DragEndMapHandler() {

				@Override
				public void onEvent(DragEndMapEvent event) {
					latField.setValue(marker.getPosition().getLatitude());
					lngField.setValue(marker.getPosition().getLongitude());
					modified = true;

				}
			});

		} else {
			marker.setPosition(center);
		}

		mapWidget.setCenter(center);

		// Se la geolocalizzazione è in sola lettura il marker non può essere spostato dalla posizione salvata.
		if (UIWorkflow.isReadOnly()) {
			marker.setDraggable(false);
		}
		else {
			marker.setDraggable(true);
		}
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

}