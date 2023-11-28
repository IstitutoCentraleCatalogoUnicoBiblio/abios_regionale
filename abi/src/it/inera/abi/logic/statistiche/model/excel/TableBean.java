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
