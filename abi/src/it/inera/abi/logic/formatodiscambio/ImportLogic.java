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

package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;

/**
 * Interfaccia per le operazioni di import del formato di scambio
 *
 */
public interface ImportLogic {

	/**
	 * Esegue l'upload del file
	 * @param utente
	 * @param email
	 * @param filename
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
	 * @return Lista dei file di import
	 * @throws Exception
	 */
	public ImportFileBean[] browseUncheckedFileImport() throws Exception;

	/**
	 * Visualizza i file che sono stati checkati
	 * @return Lista dei file di import
	 * @throws Exception
	 */
	public ImportFileBean[] browseCheckedFileImport() throws Exception;

	/**
	 * Visualizza i file che sono stati checkati
	 * @return Lista dei file di import
	 * @throws Exception
	 */
	public ImportFileBean[] browseScheduledImport() throws Exception;

	/**
	 * Visualizza i dettagli dei file checked all AMMINISTRATORE
	 * @param filename
	 * @return file di import
	 * @throws Exception
	 */
	public ImportFileBean dettaglioChecked(String filename) throws Exception;

	/**
	 * Visualizza i dettagli dei file scheduled all AMMINISTRATORE
	 * @param filename
	 * @return file di import
	 * @throws Exception
	 */
	public ImportFileBean dettaglioScheduled(String filename) throws Exception;

	/**
	 * Esegue i controlli sui file
	 * @param filename
	 * @throws Exception
	 */
	public void checkFile(String filename) throws Exception;

	/**
	 * Esegue l import del file in banca dati
	 * @param filename
	 * @throws Exception
	 */
	public void importFile(String filename) throws Exception;

	/**
	 * Esegue l import del file in banca dati
	 * @param filename
	 * @return esito dell'import
	 * @throws Exception
	 */
	public boolean importFileDifferito(String filename)	throws Exception;
	
	
	/**
	 * Cancella non controllato il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param filename
	 * @return esito della cancellazione
	 * @throws Exception
	 */
	public boolean deleteUncheckedFile(String filename) throws Exception;

	/**
	 * Cancella il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param filename
	 * @return esito della cancellazione
	 * @throws Exception
	 */
	public boolean deleteFile(String filename) throws Exception;

	/**
	 * Cancella il file passato come FILE_NAME ritorna sul browse del file opportuno
	 * @param filename
	 * @param definitivo
	 * @return esito della cancellazione
	 * @throws Exception
	 */
	public boolean deleteScheduledFile(String filename, boolean definitivo) throws Exception;
}
