package it.inera.abi.logic.impl;

import java.util.Vector;

/**
 * Classe per la gestione delle differenze tra una biblioteca 
 * ed una sua precedente versione 
 *
 */
public class Differenze {
	
	private String property;
	private Vector<String> oldNew = null;
	
	public Differenze() {
	}
	
	public Differenze(String property) {
		this.property = property;
	}
	
	public void put(Vector<String> oldNew) {
		this.oldNew = oldNew;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(property);
		sb.append("=Definitivo:[");
		sb.append(oldNew.get(0));
		sb.append("]");
		sb.append(" Modifica:[");
		sb.append(oldNew.get(1));
		sb.append("]");
		return sb.toString();
	}
	
	public String toFormatString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<b>");
		sb.append(property);
		sb.append("</b></br>");
		sb.append("Definitivo:[");
		sb.append(oldNew.get(0));
		sb.append("]</br>");
		sb.append("Modifica:[");
		sb.append(oldNew.get(1));
		sb.append("]</br></br></br>");
		return sb.toString();
	}
}