package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per i contatti
 *
 */
public class ContattiModel extends BaseModel implements Serializable{
	
	public ContattiModel() {
	
	}
	
	public ContattiModel(int id_contatti, int id_biblio,String contattiTipoLabel, String descr, String valore) {
		set("id_biblio",id_biblio);
		set("id_contatti",id_contatti);
		set("contattiTipoLabel", contattiTipoLabel);
		set("descr",descr );
		set("valore", valore );

	
	}
	
	public ContattiModel(int id_contatti, int id_biblio,String contattiTipoLabel, String descr, String valore,int contattiTipo) {
		set("id_biblio",id_biblio);
		set("id_contatti",id_contatti);
		set("contattiTipoLabel", contattiTipoLabel);
		set("descr",descr );
		set("valore", valore );
		set("contattiTipo", contattiTipo);
		
	}
	public void setIdContatti(int id_contatti){
		set("id_contatti",id_contatti);
	}
	public int getIdContatti(){
		return (Integer) get("id_contatti");
	}
	
	public void setIdBiblioteca(int id_biblio){
		set("id_biblio",id_biblio);
	}
	public int getIdBiblioteca(){
		return (Integer) get("id_biblio");
	}
	
	public void setContattiTipoLabel(String contattiTipoLabel){
		set("contattiTipoLabel", contattiTipoLabel);
	}
	
	public void setDescr(String descr){
		set("descr",descr );
	}
	
	public void setTelefono(int telefono){
		set("telefono", telefono );
	}
	
	public String getContattiTipoLabel(){
		return get("contattiTipoLabel");
	}
	
	public String getDescr(){
		return get("descr");
	}

	public int getTelefono(){
		return (Integer) get("telefono");
	}
	
	public void setContattiTipo(int contattiTipo){
		set("contattiTipo", contattiTipo);
	}
	
	public void setValore(String valore){
		set("valore", valore);
	}
	public String getValore(){
		return get("valore");
	}
	
	public int getContattiTipo(){
		return (Integer) get("contattiTipo");
	}
	
	
	
}
