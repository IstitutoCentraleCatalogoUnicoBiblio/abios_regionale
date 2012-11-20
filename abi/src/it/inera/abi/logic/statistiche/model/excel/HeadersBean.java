package it.inera.abi.logic.statistiche.model.excel;

import java.util.Vector;

/**
 * Classe per la rappresentazione degli header excel
 *
 */
public class HeadersBean {

	private Vector<String> headers;

	public HeadersBean() {
		this.headers = new Vector<String>();
	}

	public void addHeader(String header) {
		this.headers.add(header);
	}

	public Vector<String> getHeaders() {
		return headers;
	}

}
