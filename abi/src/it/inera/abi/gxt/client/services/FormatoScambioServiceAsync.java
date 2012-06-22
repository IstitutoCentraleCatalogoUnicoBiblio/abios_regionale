package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FormatoScambioServiceAsync {

	/*
	 * IMPORT
	 */	
	public void browseUncheckedFileImport(AsyncCallback<List<FileCaricatiListModel>> callback);

	void browseCheckedFileImport(AsyncCallback<List<FileCaricatiListModel>> callback);
	
	void dettaglioCheckedFileImport(String filename, PagingLoadConfig config, AsyncCallback<PagingLoadResult<BiblioModel>> callback);
	void dettaglioScheduledFileImport(String filename, PagingLoadConfig config, AsyncCallback<PagingLoadResult<BiblioModel>> callback);
	void dettaglioScheduledFileExport(String filename, PagingLoadConfig config, AsyncCallback<PagingLoadResult<BiblioModel>> callback);

	void browseScheduledImport(AsyncCallback<List<FileCaricatiListModel>> callback);

	void deleteFile(String filename, AsyncCallback<Void> callback);
	
	void deleteUncheckedFile(String filename, AsyncCallback<Void> callback);

	void deleteScheduledImportFile(String filename, boolean definitivo, AsyncCallback<Void> callback);

	void checkFile(String filename, AsyncCallback<Void> callback);

	void importFile(String filename, boolean differito,	AsyncCallback<Void> callback);
	
	/*
	 * EXPORT
	 */
	void browseScheduledExport(AsyncCallback<List<FileCaricatiListModel>> callback);

	void deleteScheduledExportFile(String filename, AsyncCallback<Void> callback);
	
	
	/* Metodo per il servizio del formato di scambio */
	public void retrieveBiblioFormatoDiScambio(List<Integer> idbibselected, HashMap<String, Object> labels, AsyncCallback<Void> callback);

	
}