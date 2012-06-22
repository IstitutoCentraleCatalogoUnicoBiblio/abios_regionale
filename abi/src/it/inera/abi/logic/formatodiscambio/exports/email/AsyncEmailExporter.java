package it.inera.abi.logic.formatodiscambio.exports.email;

import it.inera.abi.logic.formatodiscambio.exports.Exporter;
import it.inera.abi.logic.formatodiscambio.exports.ExporterImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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