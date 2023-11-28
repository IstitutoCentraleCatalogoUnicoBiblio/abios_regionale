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
