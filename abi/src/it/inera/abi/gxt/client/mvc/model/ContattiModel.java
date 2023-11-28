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
