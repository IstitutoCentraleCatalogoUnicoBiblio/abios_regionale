package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;

public interface ImportLogic {

	/**
	 * Esegue l upload del file
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void upload(String utente, String email, String filename) throws Exception;
	
	/**
	 * Esegue download del file
	 * @return Contenuto del file
	 * @throws Exception
	 */
	public byte[] download(String type, String filename) throws Exception;

	/**
	 * Visualizza i file che non sono stati checkati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ImportFileBean[] browseUncheckedFileImport() throws Exception;

	/**
	 * Visualizza i file che sono stati checkati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ImportFileBean[] browseCheckedFileImport() throws Exception;

	/**
	 * Visualizza i file che sono stati checkati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ImportFileBean[] browseScheduledImport() throws Exception;

	/**
	 * Visualizza i dettagli dei file checked all AMMINISTRATORE
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ImportFileBean dettaglioChecked(String filename) throws Exception;

	/**
	 * Visualizza i dettagli dei file scheduled all AMMINISTRATORE
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public ImportFileBean dettaglioScheduled(String filename) throws Exception;

	/**
	 * Esegue i controlli sui file
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void checkFile(String filename) throws Exception;

	/**
	 * Esegue l import del file in banca dati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void importFile(String filename) throws Exception;

	/**
	 * Esegue l import del file in banca dati
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean importFileDifferito(String filename)	throws Exception;
	
	
	/**
	 * Cancella non controllato il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUncheckedFile(String filename) throws Exception;

	/**
	 * Cancella il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFile(String filename) throws Exception;

	/**
	 * Cancella il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean deleteScheduledFile(String filename, boolean definitivo) throws Exception;
}
