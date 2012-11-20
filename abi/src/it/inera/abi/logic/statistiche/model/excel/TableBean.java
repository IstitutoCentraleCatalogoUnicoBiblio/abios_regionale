package it.inera.abi.logic.statistiche.model.excel;

import java.util.Vector;

/**
 * Classe per la rappresentazione tabellare excel delle statistiche
 *
 */
public class TableBean {

	private Vector<HeadersBean> headers;
	private Vector<RowBean> rows;
	private String name;
	private String summary;

	public TableBean() {
		this.headers = new Vector<HeadersBean>();
		this.rows = new Vector<RowBean>();
	}

	public void addHeaders(HeadersBean h) {
		this.headers.addElement(h);
	}

	public void addRows(RowBean row) {
		this.rows.addElement(row);
	}

	public Vector<HeadersBean> getHeaders() {
		return headers;
	}

	public Vector<RowBean> getRows() {
		return rows;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getSummary() {
		return this.summary;
	}
}
