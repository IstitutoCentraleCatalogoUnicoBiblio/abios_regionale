package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class VoceUnicaModel extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoceUnicaModel() {

	}


	public VoceUnicaModel(Integer id_record,String entry) {
		set("id_record", id_record);
		set("entry", entry);
	}

	public VoceUnicaModel(Integer id_record,Integer id_biblio,String entry) {
		set("id_record", id_record);
		set("id_biblio", id_biblio);
		set("entry", entry);
	}

	public VoceUnicaModel(String entry) {
		set("entry", entry);
	}


	public String getEntry() {
		return get("entry");
	}

	public void setEntry(String entry) {
		set("entry", entry);
	}

	public void setIdRecord(Integer id_record) {
		set("id_record", id_record);
	}

	public Integer getIdRecord() {
		return get("id_record");
	}

	public void setIdBiblioteca(Integer id_biblio) {
		set("id_biblio", id_biblio);
	}

	public Integer getIdBiblioteca() {
		return get("id_biblio");
	}

	/*Utilizzati nella differenziazione delle tabelle dinamiche nella lista 
	 * generica a voce singola*/
	public void setIdTabella(int tipoTabella) {
		set("tipoTabella",tipoTabella);	
	}

	public int getIdTabella() {
		return (Integer) get("tipoTabella");	
	}
	//******fine******//

	/*Utilizzati nello stato catalogazione*/
	public String getIsilStato(){
		return get("isilStato");
	}

	public String getIsilProvincia(){
		return get("isilProvincia");
	}

	public Integer getIsilNumero(){
		return get("isilNumero");
	}

	public void getIsilStato(String isilStato){
		set("isilStato",isilStato);
	}

	public void getIsilProvincia(String isilProvincia){
		set("isilProvincia",isilProvincia);
	}

	public void getIsilNumero(Integer isilNumero){
		set("isilNumero",isilNumero);
	}
	/*fine --Utilizzati nello stato catalogazione*/
}
