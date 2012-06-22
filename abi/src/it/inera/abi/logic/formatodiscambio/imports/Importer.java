package it.inera.abi.logic.formatodiscambio.imports;


import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;

import java.util.Date;

public interface Importer {

	public void doImport(Biblioteca biblioteca,	Date dataExport, ReportImport reportImport) throws Exception;
	public void doImport(Biblioteca biblioteca,	Date dataExport, ReportImport reportImport, String username, boolean differito) throws Exception;
	
}