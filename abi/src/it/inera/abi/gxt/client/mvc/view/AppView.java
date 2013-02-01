package it.inera.abi.gxt.client.mvc.view;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.mvc.view.south.UserPanel;
import it.inera.abi.gxt.client.services.AuthServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * View utilizzata per istanziare i pannelli principali dell'applicativo
 * (north, south, west, center)
 *
 */
public class AppView extends View {
	
	public static final String VIEWPORT = "viewport";
	public static final String WEST_PANEL = "west";
	public static final String CENTER_PANEL = "center";
	public static final String NORTH_PANEL = "north";
	public static final String SOUTH_PANEL = "south";

	private AuthServiceAsync authService;
	private Viewport viewport;
	private ContentPanel west;
	private LayoutContainer center;
	private HtmlContainer northPanel;
	private UserPanel southPanel;

	public AppView(Controller controller) {
		super(controller);
		authService = Registry.get(Abi.AUTHSERVICE);
	}

	@Override
	protected void initialize() {

	}

	private void initUI() {
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());

		createNorth();
		createSouth();
		createWest();
		createCenter();

		
		// registry serves as a global context
		Registry.register(VIEWPORT, viewport);
		Registry.register(WEST_PANEL, west);
		Registry.register(CENTER_PANEL, center);
		Registry.register(NORTH_PANEL, northPanel);
		Registry.register(SOUTH_PANEL, southPanel);

		RootPanel.get().add(viewport);
	}

	private void createNorth() {
				HtmlContainer northPanel = new HtmlContainer();
				northPanel.setUrl(GWT.getHostPageBaseURL() + "title_header.html");
				northPanel.setStateful(false);

				BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH,30);
				data.setMargins(new Margins());
				viewport.add(northPanel, data);
	}

	private void createWest() {

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 165, 150, 350);
		data.setMargins(new Margins(5, 0, 5, 5));
		data.setCollapsible(true);
		data.setFloatable(true);
		data.setSplit(true);
//		data.setSplit(false);

		west = new ContentPanel();
		west.setBodyBorder(false);
		west.setLayout(new AccordionLayout());
		west.setLayoutOnChange(true);
		west.setHeading("Menù");

		Timer timer = new Timer() {
			public void run() {
				authService.checkLogin(new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						// do nothing...
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.Location.assign(GWT.getHostPageBaseURL() + "login.jsp?timeout=1");
					}
				});
			}
		};
		timer.scheduleRepeating(300000);//5 minuti
		
		viewport.add(west, data);
	}

	private void createCenter() {
		center = new LayoutContainer();
		center.setLayout(new FitLayout());
		center.addStyleName("font-weight-button");
		center.addStyleName("font-weight-label-checkbox");
		

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 5));

		viewport.add(center, data);
	}

	private void createSouth() {
		southPanel = new UserPanel();
		southPanel.addStyleName("font-weight-button");
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.SOUTH, 33);
		data.setMargins(new Margins());
		viewport.add(southPanel, data);

	}

	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}

}
