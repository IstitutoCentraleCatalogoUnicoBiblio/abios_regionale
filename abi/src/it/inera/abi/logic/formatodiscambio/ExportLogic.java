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
