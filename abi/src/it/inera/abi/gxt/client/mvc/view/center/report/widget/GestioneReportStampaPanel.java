package it.inera.abi.gxt.client.mvc.view.center.report.widget;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AbiMessageBox;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.Utils;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.services.FormatoScambioServiceAsync;
import it.inera.abi.gxt.client.services.ReportServiceAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Classe per la visualizzazione delle stampe dei report scaricabili 
 * in formato pdf e/o excel
 *
 */
public class GestioneReportStampaPanel extends ContentPanel {

	private int tipoRicerca = 0;
	ReportServiceAsync reportService = Registry.get(Abi.REPORTSERVICE);
	FormatoScambioServiceAsync formatoScambioService = Registry.get(Abi.FORMATO_SCAMBIO);

	private String label = null;
	private String param = null;
	private HashMap<String, Object> labels = null;
	private List<Integer> idbibs = null;
//	private LayoutContainer excelButton;
	private Text formatoStampaLabel;
	private HtmlContainer link = new HtmlContainer();

	public GestioneReportStampaPanel() {
		super();
		setHeaderVisible(false);
//		setLayout(new FitLayout());
	}

	public void setPanel() {

		LayoutContainer datiBiblioRaccolti = new LayoutContainer();
		datiBiblioRaccolti.setStyleAttribute("padding", "5px");

		/* NUOVO LAYOUT 2 COLONNE */
		FieldSet tipoStampaSet = new FieldSet();		

		Utils.setFieldSetProperties(tipoStampaSet, "Dati biblioteche raccolti");
		tipoStampaSet.setCollapsible(false);
		tipoStampaSet.setExpanded(true);

		LayoutContainer tipoStampaTreColonne = new LayoutContainer(new TableLayout(2));

		TableData primaColonna = new TableData();
		primaColonna.setWidth("50%");
		primaColonna.setMargin(5);
		primaColonna.setPadding(10);

		TableData secondaColonna = new TableData();
		secondaColonna.setWidth("50%");
		secondaColonna.setMargin(5);
		secondaColonna.setPadding(10);

//		LayoutContainer pdfButton = new LayoutContainer(new FlowLayout());
//		pdfButton.setAutoWidth(true);	
//
//		excelButton = new LayoutContainer(new FlowLayout());
//		excelButton.setAutoWidth(true);

		LayoutContainer backButton = new LayoutContainer(new CenterLayout());
		backButton.setStyleAttribute("marginBottom","5px");
		backButton.setBorders(true);

		backButton.setHeight(50);

		if (tipoRicerca == 1) { // && idbibs != null && labels != null) {
//			final Button pdf = new Button("Scarica il pdf per la stampa", Resources.ICONS.page_white_acrobat());
//			pdf.addSelectionListener(new SelectionListener<ButtonEvent>() {
//
//				@Override
//				public void componentSelected(ButtonEvent ce) {
//					reportService.retrieveBiblioReport(idbibs, labels, new AsyncCallback<Void>() {
//
//						@Override
//						public void onSuccess(Void result) {
//							Window.open(GWT.getHostPageBaseURL() + "abi/stampe.pdf?FORMATO=" + label, "_blank", "");
//						}
//
//						@Override
//						public void onFailure(Throwable caught) {
//							
//							if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
//								AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nella generazione " +
//										"del file pdf per il report.", "Generazione Pdf Report");
//							}
//
//						}
//					});
//
//				}
//			});
//
//			pdfButton.add(pdf);
//
//			final Button excel = new Button("Scarica il file excel", Resources.ICONS.page_excel());
//			excel.addSelectionListener(new SelectionListener<ButtonEvent>() {
//
//				@Override
//				public void componentSelected(ButtonEvent ce) {
//					reportService.retrieveBiblioReport(idbibs, labels, new AsyncCallback<Void>() {
//
//						@Override
//						public void onSuccess(Void result) {
//							//							Window.open(GWT.getHostPageBaseURL() + "abi/stampe.xls?FORMATO=" + label, "_blank", "");
//							Window.Location.assign(GWT.getHostPageBaseURL() + "abi/stampe.xls?FORMATO=" + label);
//
//						}
//
//						@Override
//						public void onFailure(Throwable caught) {
//							if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
//								AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nella generazione " +
//										"del file excel per il report.", "Generazione Excel Report");
//							}
//
//						}
//					});
//
//				}
//			});
//
//			excelButton.add(excel);
		}


		final Button back = new Button("Effettua nuova ricerca");
		back.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				if (tipoRicerca == 1) {
					AppEvent event = new AppEvent(AppEvents.VisualizzaFormReport);
					Dispatcher.forwardEvent(event);
				}
				else if (tipoRicerca == 2) {
					AppEvent event = new AppEvent(AppEvents.VisualizzaFormFormatoDiScambio);
					Dispatcher.forwardEvent(event);
				}
			}

		});

		backButton.add(back);		

		if (tipoRicerca == 1) {
			tipoStampaTreColonne.add(link, primaColonna);
			tipoStampaTreColonne.add(new LayoutContainer(), secondaColonna);

		} else if (tipoRicerca == 2) {
			formatoStampaLabel = new Text();
			formatoStampaLabel.setStyleAttribute("fontSize", "14px");
			tipoStampaTreColonne.add(formatoStampaLabel, primaColonna);
			tipoStampaTreColonne.add(new LayoutContainer(), secondaColonna);

		}

		tipoStampaSet.add(tipoStampaTreColonne);
		datiBiblioRaccolti.add(tipoStampaSet);
		add(datiBiblioRaccolti);
		setBottomComponent(backButton);

	}

	public void setTipoRicerca(int tipo) {
		tipoRicerca = tipo;
	}

	public void setLabel(String lab) {
		this.label = lab;
		if (this.label != null) {
			if (this.label.equals("lista")) {
				link.setHtml("<a href=\"abi/stampe.pdf?FORMATO=" + this.label + "\" target=\"_blank\"><img border=\"0\" src=\"images/icons/page_white_acrobat.png\">Scarica il pdf per la stampa</a>&nbsp;&nbsp;&nbsp;<a href=\"abi/stampe.xls?FORMATO=" + this.label + "\" target=\"_blank\"><img border=\"0\" src=\"images/icons/page_excel.png\">Scarica il file excel</a>");

			} else if (this.label.equals("etichetta")) {
				link.setHtml("<a href=\"abi/stampe.pdf?FORMATO=" + this.label + "\" target=\"_blank\"><img border=\"0\" src=\"images/icons/page_white_acrobat.png\">Scarica il pdf per la stampa</a>");

			} else {
				formatoScambioService.retrieveBiblioFormatoDiScambio(idbibs, labels, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						StringBuffer msg = new StringBuffer();
						boolean differito = ((labels.get("differito") != null && ("true".equalsIgnoreCase(labels.get("differito").toString())) ? true : false));
						if (differito) {
							msg.append("L'export è stato schedulato. Non appena sarà eseguito, verrà inviata una mail all'indirizzo ");
						} else {
							msg.append("Appena l'export sarà terminato, verrà inviata una mail all'indirizzo ");
						}
						if (param != null) {
							msg.append(param);
						}
						formatoStampaLabel.setText(msg.toString());
					}

					@Override
					public void onFailure(Throwable caught) {
						if (UIAuth.checkIsLogin(caught.toString())) {// controllo se l'errore è dovuto alla richiesta di login
							AbiMessageBox.messageErrorAlertBox("Si è verificato un errore nella generazione " +	
									"del file xml per l'export.", "Generazione Xml Export");
							formatoStampaLabel.setText("Si è verificato un errore nella generazione del file xml per l'export.");
						}
					}
				});
			}

		}


	}

	public void setParam(String email) {
		this.param = email;
	}

	public void setLabels(HashMap<String, Object> lbls) {
		this.labels = new HashMap<String, Object>();
		this.labels = lbls;
	}

	public void setBiblioteche(List<Integer> bibs) {
		this.idbibs = new ArrayList<Integer>();
		this.idbibs = bibs;
	}
	
}