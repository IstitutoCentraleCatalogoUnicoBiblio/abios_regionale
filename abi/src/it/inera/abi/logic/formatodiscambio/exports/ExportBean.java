package it.inera.abi.logic.formatodiscambio.exports;

import it.inera.abi.logic.formatodiscambio.imports.InfoBiblioBean;

import java.util.Vector;

public class ExportBean {
	
	public String fileName;
	public String data;
	public String ora;
	public int nBib;
	public String email;
	public String username;
	public Vector<InfoBiblioBean> biblioteche = new Vector<InfoBiblioBean>();

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExportBean [fileName=");
		builder.append(fileName);
		builder.append(", data=");
		builder.append(data);
		builder.append(", ora=");
		builder.append(ora);
		builder.append(", nBib=");
		builder.append(nBib);
		builder.append(", email=");
		builder.append(email);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	
	
}
