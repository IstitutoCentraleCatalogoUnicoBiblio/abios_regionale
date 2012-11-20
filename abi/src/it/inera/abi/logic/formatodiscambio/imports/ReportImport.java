package it.inera.abi.logic.formatodiscambio.imports;

import java.util.Vector;

/**
 * Classe che rappresenta gli errori e warning relativi all'import 
 *
 */
public class ReportImport {
	
	private Vector<String> errors = new Vector<String>();
	private Vector<String> warning = new Vector<String>();
	
	public void addError(String error) {
		errors.add(error);
	}
	
	public void addWarn(String warn) {
		warning.add(warn);
	}
	
	public Vector<String> getWarning() {
		return warning;
	}
	
	public Vector<String> getErrors() {
		return errors;
	}
	
	
}
