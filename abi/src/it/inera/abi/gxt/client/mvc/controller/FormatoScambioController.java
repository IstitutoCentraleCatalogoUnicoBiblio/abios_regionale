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

package it.inera.abi.gxt.client.mvc.controller;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.AppEvents;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.costants.CostantiFormatoScambio;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.ExportSchedulatiView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.FileCaricatiControllatiView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.FileCaricatiNonControllatiView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.ImportSchedulatiView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.RicercaBiblioPerExportView;
import it.inera.abi.gxt.client.mvc.view.center.formatoscambio.UploadFileScambioView;
import it.inera.abi.gxt.client.mvc.view.menu.FormatoScambioMenuView;
import it.inera.abi.gxt.client.services.FormatoScambioServiceAsync;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Controller per il formato di scambio (upload, lista 
 * file non controllati / controllati, ricerca per export, ecc...)
 *
 */
public class FormatoScambioController extends Controller {

	private FormatoScambioMenuView menuView;
	private RicercaBiblioPerExportView ricercaBiblioPerExportView;
	private UploadFileScambioView uploadFileScambioView;
	private FileCaricatiNonControllatiView fileCaricatiNonControllatiView;
	private FileCaricatiControllatiView fileCaricatiControllatiView;
	private ExportSchedulatiView exportSchedulatiView;
	private ImportSchedulatiView importSchedulatiView;

	
	// servizio async formato di scambio
	private FormatoScambioServiceAsync formatoScambioService = Registry.get(Abi.FORMATO_SCAMBIO);
	
	
	public FormatoScambioController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.RicercaPerCreazioneExport);
		registerEventTypes(AppEvents.UploadFileDiScambio);
		registerEventTypes(AppEvents.FileCaricatiNonControllati);
		registerEventTypes(AppEvents.FileCaricatiControllati);
		registerEventTypes(AppEvents.ExportSchedulati);
		registerEventTypes(AppEvents.ImportSchedulati);
		registerEventTypes(AppEvents.VisualizzaRisultatiFormatoDiScambio);
		registerEventTypes(AppEvents.VisualizzaFormFormatoDiScambio);
		registerEventTypes(AppEvents.VisualizzaStampaFormatoDiScambio);
	}

	@Override
	public void initialize() {
		menuView = new FormatoScambioMenuView(this);
		ricercaBiblioPerExportView = new RicercaBiblioPerExportView(this);
		uploadFileScambioView = new UploadFileScambioView(this);
		fileCaricatiNonControllatiView = new FileCaricatiNonControllatiView(this);
		fileCaricatiControllatiView = new FileCaricatiControllatiView(this);
		exportSchedulatiView = new ExportSchedulatiView(this);
		importSchedulatiView = new ImportSchedulatiView(this);
	}

	@Override
	public void handleEvent(final AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Init) {
			forwardToView(menuView, event);
		} else if (type == AppEvents.RicercaPerCreazioneExport) {
			forwardToView(ricercaBiblioPerExportView, event);
		} else if (type == AppEvents.UploadFileDiScambio) {
			forwardToView(uploadFileScambioView, event);
		} else if (type == AppEvents.FileCaricatiNonControllati) {
			menuView.selectMenuEntryByIndex(1);
			formatoScambioService.browseUncheckedFileImport(new AsyncCallback<List<FileCaricatiListModel>>() {
				@Override
				public void onSuccess(List<FileCaricatiListModel> result) {
					event.setData(CostantiFormatoScambio.DATI_FILE, result);
					forwardToView(fileCaricatiNonControllatiView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
				}
			});
		} else if (type == AppEvents.FileCaricatiControllati) {
			menuView.selectMenuEntryByIndex(2);
			formatoScambioService.browseCheckedFileImport(new AsyncCallback<List<FileCaricatiListModel>>() {
				@Override
				public void onSuccess(List<FileCaricatiListModel> result) {
					event.setData(CostantiFormatoScambio.DATI_FILE, result);
					forwardToView(fileCaricatiControllatiView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
				}
			});
		} else if (type == AppEvents.ExportSchedulati) {
			formatoScambioService.browseScheduledExport(new AsyncCallback<List<FileCaricatiListModel>>() {
				@Override
				public void onSuccess(List<FileCaricatiListModel> result) {
					event.setData(CostantiFormatoScambio.DATI_FILE, result);
					forwardToView(exportSchedulatiView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
				}
			});
		} else if (type == AppEvents.ImportSchedulati) {
			menuView.selectMenuEntryByIndex(3);
			formatoScambioService.browseScheduledImport(new AsyncCallback<List<FileCaricatiListModel>>() {
				@Override
				public void onSuccess(List<FileCaricatiListModel> result) {
					event.setData(CostantiFormatoScambio.DATI_FILE, result);
					forwardToView(importSchedulatiView, event);
				}
				@Override
				public void onFailure(Throwable caught) {
					UIAuth.checkIsLogin(caught.toString()); // controllo se l'errore è dovuto alla richiesta di login
				}
			});
			
		}
		else if (type == AppEvents.VisualizzaFormFormatoDiScambio) {
			forwardToView(ricercaBiblioPerExportView, event);
			
		}
		else if (type == AppEvents.VisualizzaRisultatiFormatoDiScambio) {
			forwardToView(ricercaBiblioPerExportView, event);
			
		}
		else if (type == AppEvents.VisualizzaStampaFormatoDiScambio) {
			forwardToView(ricercaBiblioPerExportView, event);
		}
	}

}
