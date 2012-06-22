package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.exports.ExportBean;
import it.inera.abi.logic.formatodiscambio.imports.InfoBiblioBean;

public interface ExportLogic {
	
	/**
	 * Ritorna i file che sono stati schedulati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ExportBean[] browseScheduledExport() throws Exception;

	/**
	 * Ritorna le biblioteche contenute nel file di export schedulato
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public ExportBean dettaglioScheduled(String filename) throws Exception;
	
	/**
	 * Cancella il file passato come FILE_NAME
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean deleteScheduledFile(String filename) throws Exception;
	
	/**
	 * 
	 * @param idBiblios
	 * @param username
	 * @param email
	 * @param differito
	 * @throws Exception
	 */
	public void doExport(String[] idBiblios, String username, String email , boolean differito) throws Exception;
	
	
}
