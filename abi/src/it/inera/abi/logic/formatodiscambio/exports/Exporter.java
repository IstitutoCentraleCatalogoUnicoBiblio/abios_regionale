package it.inera.abi.logic.formatodiscambio.exports;

/**
 * Interfaccia per l'export online e differito 
 *
 */
public interface Exporter {

	public void doExport(String[] idBib, String email);
	public void doExport(String[] idBib, String email, boolean differito);
	
}
