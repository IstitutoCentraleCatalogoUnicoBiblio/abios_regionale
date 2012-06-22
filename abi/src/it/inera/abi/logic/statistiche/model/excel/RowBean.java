package it.inera.abi.logic.statistiche.model.excel;

import java.util.Vector;

public class RowBean {

	private Vector<String> columns;
	private int num;

	public RowBean() {
		this.columns = new Vector<String>();
	}

	public void addColumns(String column) {
		this.columns.add(column);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Vector<String> getColumns() {
		return columns;
	}
}
