package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.Abi;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;

import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi relativi al formato di scambio (lato client)
 * 
 */
@RemoteServiceRelativePath(Abi.FORMATO_SCAMBIO)
public interface FormatoScambioService extends RemoteService {

	/* IMPORT */	
	public List<FileCaricatiListModel> browseUncheckedFileImport();
	
	public List<FileCaricatiListModel> browseCheckedFileImport();
	
	public PagingLoadResult<BiblioModel> dettaglioCheckedFileImport(String filename, PagingLoadConfig config);
	
	public PagingLoadResult<BiblioModel> dettaglioScheduledFileImport(String filename, PagingLoadConfig config);
	
	public PagingLoadResult<BiblioModel> dettaglioScheduledFileExport(String filename, PagingLoadConfig config);

	public List<FileCaricatiListModel> browseScheduledImport();
	
	public void deleteFile(String filename);
	
	public void deleteUncheckedFile(String filename);
	
	public void deleteScheduledImportFile(String filename, boolean definitivo);
	
	public void checkFile(String filename);
	
	public void importFile(String filename, boolean differito);
	
	/* EXPORT */	
	public List<FileCaricatiListModel> browseScheduledExport();	
	
	public void deleteScheduledExportFile(String filename);
	
	/* Metodo per il servizio del formato di scambio */
	public void retrieveBiblioFormatoDiScambio(List<Integer> idbibselected, HashMap<String, Object> labels);
}
