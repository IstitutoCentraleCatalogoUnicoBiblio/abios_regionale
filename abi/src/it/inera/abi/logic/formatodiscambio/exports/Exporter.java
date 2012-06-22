package it.inera.abi.logic.formatodiscambio.exports;

public interface Exporter {

	public void doExport(String[] idBib, String email);
	public void doExport(String[] idBib, String email, boolean differito);
	
}
