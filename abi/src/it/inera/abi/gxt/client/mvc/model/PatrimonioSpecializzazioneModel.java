package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

public class PatrimonioSpecializzazioneModel extends BaseModel implements Serializable {

	/**
	 * 
	 */
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
  
    public static String getTemplateMateriali(){
		  
        String tpl = "<tpl for=\".\">"
                + "<tpl if=\"condition == 1 \"><b>{denominazioneMateriale}</b><br></tpl>"
                + "<tpl if=\"condition == 2 \">&nbsp;&nbsp;&nbsp;<b>{denominazioneMateriale}</b><br></tpl>"
                + "<tpl if=\"condition == 3 \">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{denominazioneMateriale}<br></tpl>"
                + "</tpl>";
        

		return tpl;
	}

}
