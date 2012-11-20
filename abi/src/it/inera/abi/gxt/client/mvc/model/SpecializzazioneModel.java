package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare il dewey
 *
 */
public class SpecializzazioneModel extends BaseModel implements Serializable{
	
	public SpecializzazioneModel() {
	
	}

	public SpecializzazioneModel(String dewey, String descrizione){
		
		set("dewey", dewey);
		set("descrizioneDewey", descrizione);
		
	}
	
	public SpecializzazioneModel(String dewey, String descrizione, String descrLibera){
	
		set("dewey", dewey);
		set("descrizioneDewey", descrizione);
		set("descrLibera", descrLibera);
		
	}
	
	
	public SpecializzazioneModel(int idBiblioteca,String dewey,String descrizione , String descrLibera){
	
		set("idBiblioteca",idBiblioteca);
		set("dewey",dewey);
		set("descrizioneDewey",descrizione);
		set("descrLibera",descrLibera); 
		
	}
	
	public void setIdBiblioteca(int idBiblioteca){
		set("idBiblioteca",idBiblioteca);
	}
	
	public int getIdBiblioteca(){
		return (Integer) get("idBiblioteca");
	}
	

	public String getDewey(){
		return get("dewey");
	}

	public void setDewey(String q){
		set("dewey",q);
	}
	
	public String getDecrizione(){
		return get("descrizioneDewey");
	}

	public void setDecrizione(String descrizione){
		set("descrizioneDewey",descrizione);
	}
	
	public String getDecrLibera(){
		return get("descrLibera");
	}

	public void setDecrLibera(String descrLibera){
		set("descrLibera",descrLibera);
	}
	
	
	
}
