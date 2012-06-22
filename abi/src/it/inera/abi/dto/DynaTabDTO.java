package it.inera.abi.dto;

import java.io.Serializable;

public class DynaTabDTO implements Serializable {
	
	private static final long serialVersionUID = -9170184921370355285L;
	
	private int idTabella; // identifica la tabella
	
	/**
	 * identificano il record
	 */
	private int id;
	private String value;
	
	public DynaTabDTO() {
	}
	
	
	
	public DynaTabDTO(int idTabella, int id, String value) {
		this.idTabella = idTabella;
		this.id = id;
		this.value = value;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getIdTabella() {
		return idTabella;
	}

	public void setIdTabella(int idTabella) {
		this.idTabella = idTabella;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DynaTabDTO [idTabella=");
		builder.append(idTabella);
		builder.append(", id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
