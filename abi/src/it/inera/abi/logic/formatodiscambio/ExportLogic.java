package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.exports.ExportBean;

/**
 * Interfaccia per le operazioni di export del formato di scambio
 *
 */
public interface ExportLogic {
	
	/**
	 * Ritorna i file che sono stati schedulati
	 * @return Lista dei file di export
	 * @throws Exception
	 */
	public ExportBean[] browseScheduledExport() throws Exception;

	/**
	 * Ritorna le biblioteche contenute nel file di export schedulato
	 * @param filename Nome del file
	 * @return File di export
	 * @throws Exception
	 */
	public ExportBean dettaglioScheduled(String filename) throws Exception;
	
	/**
	 * Cancella il file passato come FILE_NAME
	 * @param filename Il file da cancellare
	 * @return Esito della cancellazione
	 * @throws Exception
	 */
	public boolean deleteScheduledFile(String filename) throws Exception;
	
	/**
	 * Esegue l'export
	 * @param idBiblios
	 * @param username
	 * @param email
	 * @param differito
	 * @throws Exception
	 */
	public void doExport(String[] idBiblios, String username, String email , boolean differito) throws Exception;
	
	
}
