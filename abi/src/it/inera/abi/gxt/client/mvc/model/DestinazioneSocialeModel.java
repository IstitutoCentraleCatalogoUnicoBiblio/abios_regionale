package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per le destinazioni sociali
 *
 */
public class DestinazioneSocialeModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = 8707529681960661555L;

	public DestinazioneSocialeModel() {

	}
	
	public DestinazioneSocialeModel(String descrizione, String note) {
		set("descrizione", descrizione);
		set("note", note);
	}
	
	public DestinazioneSocialeModel(int id_record, String descrizione, String note) {
		set("id_record",id_record);
		set("descrizione", descrizione);
		set("note", note);
	}
	
	public void setIdRecord(int id_record) {
		set("id_record",id_record);
	}
	
	public int getIdRecord() {
		return (Integer) get("id_record");
	}
	
	public void setDescrizione(String descrizione) {
		set("descrizione", descrizione);
	}
	
	public String getDescrizione() {
		return get("descrizione");
	}
	
	public void setNote(String note) {
		set("note", note);
	}
	
	public String getNote() {
		return get("note");
	}
}
