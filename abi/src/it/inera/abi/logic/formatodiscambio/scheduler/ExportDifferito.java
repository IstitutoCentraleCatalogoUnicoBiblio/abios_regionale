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

package it.inera.abi.logic.formatodiscambio.scheduler;

import it.inera.abi.logic.formatodiscambio.exports.Exporter;
import it.inera.abi.logic.formatodiscambio.exports.email.AsyncEmailExporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe che esegue il job per l'export differito 
 *
 */
public class ExportDifferito {

	private Log log = LogFactory.getLog(ExportDifferito.class);
	
	private String exportScheduledDir = null; //config.getString(Constants.EXPORT_SCHEDULED_DIR);
	
	private Exporter exporter = null;
	
	public void execute() throws Exception {
		log.info("Start job per l'export differito");
		
		// legge la directory per cercare schedulazioni
		File dirScheduled = new File(exportScheduledDir);
		
		File[] scheduled = dirScheduled.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("export".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});
		
		
		// analizza, se trovati, i file
		log.info("Trovate " + scheduled.length + " richieste");
		for (int i = 0; i < scheduled.length; i++) {
			log.info("Partenza primo export nome file " + scheduled[i].getName());
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(scheduled[i]));
				String email = bufferedReader.readLine();
				String username = bufferedReader.readLine();
				String line = bufferedReader.readLine();
				Vector<String> biblioV = new Vector<String>();
				while (line != null) {
					String[] biblioInfo = line.split("\\|\\|\\|"); //idbiblio:codiccu:denominazione
					biblioV.add(biblioInfo[0]);
					line = bufferedReader.readLine();
				}				
				bufferedReader.close();
				scheduled[i].delete();
				
				String[] idBib = new String[biblioV.size()];
				idBib = (String[]) biblioV.toArray(idBib); 
				
				// genera export online
				AsyncEmailExporter  producer = new AsyncEmailExporter(idBib, email, exporter, true);
				producer.start();
				
				
			} catch (FileNotFoundException e) {
				log.fatal("Impossibile leggere il file di schedulazione", e);
			} catch (IOException e) {
				log.fatal("Impossibile leggere il file di schedulazione", e);
			}
		}
	}

	/**
	 * 
	 */
	public void setExporter(Exporter exporter) {
		this.exporter = exporter;
	}
	
	public void setExportScheduledDir(String exportScheduledDir) {
		this.exportScheduledDir = exportScheduledDir;
	}
}