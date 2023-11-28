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

package it.inera.abi.logic.formatodiscambio.exports.email;

import it.inera.abi.logic.formatodiscambio.exports.Exporter;
import it.inera.abi.logic.formatodiscambio.exports.ExporterImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Thread per l'export online 
 *
 */
public class AsyncEmailExporter extends Thread {

	private Log log = LogFactory.getLog(AsyncEmailExporter.class);

	private String[] idBib = null;
	private String email = null;
	private boolean differito = false;
	
	private Exporter exporter;
	
	public AsyncEmailExporter(String[] idBib, String email, Exporter exporter, boolean differito) {
		this.idBib = idBib;
		this.email = email;
		this.exporter = exporter;
		this.differito = differito;
	}

	public void run() {
		log.info("Inizio");
		exporter.doExport(idBib, email, differito);
		log.info("Fine");
	}

	
	

}