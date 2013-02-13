package it.inera.abi.logic.formatodiscambio.imports;

import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;

import java.util.Date;

/**
 * Interfaccia per l'import online e differito 
 *
 */
public interface Importer {

	public void doImport(Biblioteca biblioteca,	Date dataExport, ReportImport reportImport, String basePhotoUrl) throws Exception;
	public void doImport(Biblioteca biblioteca,	Date dataExport, ReportImport reportImport, String username, boolean differito, String basePhotoUrl) throws Exception;
	
}