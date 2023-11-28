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
 * Modello utilizzato per rappresentare le specializzazioni del patrimonio
 *
 */
public class PatrimonioSpecializzazioneModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public PatrimonioSpecializzazioneModel() {

	}

	public PatrimonioSpecializzazioneModel(Integer id_patrimonio,String denominazioneMateriale, int condition) {
		set("id_record", id_patrimonio);
		set("denominazioneMateriale", denominazioneMateriale);
		set("condition", condition);
	}
	
	public PatrimonioSpecializzazioneModel(Integer id_patrimonio,String denominazioneMateriale) {
		set("id_record", id_patrimonio);
		set("denominazioneMateriale", denominazioneMateriale);
	
	}


	
	public PatrimonioSpecializzazioneModel(String denominazioneMateriale) {
		set("denominazioneMateriale", denominazioneMateriale);
	}

	
	public String getEntry() {
		return get("denominazioneMateriale");
	}
	
	

	public void setEntry(String denominazioneMateriale) {
		set("denominazioneMateriale", denominazioneMateriale);
	}
	public void setIdRecord(Integer id_patrimonio) {
		set("id_record", id_patrimonio);
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

	/* Metodi per settare/verificare una condizione; nello specifico, questi metodi sono
     * utilizzati nella form relativa ai report per poter distinguere all'interno
     * della combobox del patrimonio librario le categorie dalle categorie 'madri' */
    public void setCondition(int condition) {
            set("condition", condition);
            
    }
    
    public int getCondition() {
            return (Integer) get("condition");
            
    }
  
    public static String getTemplateMateriali() {
		  
    	String tpl = "<tpl for=\".\">"
            + "<tpl if=\"condition == 1 \"><b>{denominazioneMateriale}</b><br></tpl>"
            + "<tpl if=\"condition == 2 \">&nbsp;&nbsp;&nbsp;<b>{denominazioneMateriale}</b><br></tpl>"
            + "<tpl if=\"condition == 3 \">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>{denominazioneMateriale}</b><br></tpl>"
            + "</tpl>";
    
    	return tpl;
	}
    
}
