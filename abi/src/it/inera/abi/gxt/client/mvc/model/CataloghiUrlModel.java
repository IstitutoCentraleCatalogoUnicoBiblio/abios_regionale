package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;
/**Modello utilizzato per caricare i dati relativi alle tabelle cataloghi_collettivi_materiale_url oppure
	 * cataloghi_speciali_materiale_url oppure cataloghi_generali_url*/
public class CataloghiUrlModel extends BaseModel implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3660228898372966185L;

	public CataloghiUrlModel() {
	}
	
	public CataloghiUrlModel(Integer idPartecipa,Integer idCataloghi,String url,String descrizioneUrl) {
	
		set("idPartecipa",idPartecipa);	
		set("idCataloghi",idCataloghi);	
		set("url",url);
		set("descrizioneUrl",descrizioneUrl);
	}
	
	/**Setta l'id relativo alla tabella partecipa_cataloghi_collettivi_materiale oppure
	 * partecipa_cataloghi_speciali_materiale oppure partecipa_cataloghi_generali
	 * @param idPartecipa*/
	public void setIdPartecipa(Integer idPartecipa){
		set("idPartecipa",idPartecipa);	
	}
	/**Ritorna l'id relativo alla tabella partecipa_cataloghi_collettivi_materiale oppure
	 * partecipa_cataloghi_speciali_materiale oppure partecipa_cataloghi_generali
	 * @return idPartecipa
	 * */
	public Integer getIdPartecipa(){
		return get("idPartecipa");	
	}
	/**Setta l'id relativo alla tabella cataloghi_collettivi_materiale_url oppure
	 * cataloghi_speciali_materiale_url oppure cataloghi_generali_url
	 * @param idCataloghi
	 * */
	public void setIdCataloghiUrl(Integer idCataloghi){
		set("idCataloghi",idCataloghi);	
	}
	/**Ritorna l'id relativo alla tabella cataloghi_collettivi_materiale_url oppure
	 * cataloghi_speciali_materiale_url oppure cataloghi_generali_url
	 * @return idCataloghi
	 * */
	public Integer getIdCataloghiUrl(){
		return get("idCataloghi");	
	}
	/**Setta l'indirizzo url
	 * @param url
	 * */
	public void setUrl(String url){
		set("url",url);
	}
	/**Ritorna l'indirizzo url
	 * * @return url
	 * */
	public String getUrl(){
		return get("url");
	}
	/**
	 * Setta la descrizione relativa all'url
	 * @param descrizioneUrl
	 * */
	public void setDescrUrl(String descrizioneUrl){
		set("descrizioneUrl",descrizioneUrl);
	}
	
	/**
	 * Ritorna la descrizione relativa all'url
	 * @return descrizioneUrl
	 * */
	public String getDescr(){
		return get("descrizioneUrl");
	}
	
	
}
