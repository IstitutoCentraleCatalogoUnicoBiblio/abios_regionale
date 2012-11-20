package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ChangeEventSource;

/**
 * Modello generico per la rappresentazione dell'associazione tra la biblioteca 
 * ed i cataloghi
 *
 */
public class PartecipaCataloghiGenericaModel extends BaseModel implements
		Serializable ,ChangeEventSource {
	/**
	 * Variabili per serializzazione
	 */
	CataloghiUrlModel cataloghiUrlModel;
	List<CataloghiUrlModel> urlList;
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2481632626607841470L;

	public PartecipaCataloghiGenericaModel() {

	}

	public PartecipaCataloghiGenericaModel(String schede, Integer percentSchede,
			String volume, Integer percentVolume, String citazioneBiblio,
			String microforme, Integer percentMicroforme, String informatizzato,
			List<String> urlList, Integer percentInformatizzato, Integer annoDa, Integer annoA) {
		set("schede", schede);
		set("percentSchede", percentSchede);
		set("volume", volume);
		set("percentVolume", percentVolume);
		set("citazioneBiblio", citazioneBiblio);
		set("microforme", microforme);
		set("percentMicroforme", percentMicroforme);
		set("informatizzato", informatizzato);
		set("url", "Urls");
		set("urlList",urlList);
		set("percentInformatizzato", percentInformatizzato);
		set("annoDa", annoDa);
		set("annoA", annoA);
		// set("",);
		// set("",);
		//
	}





	public void setSchede(String schede) {
		set("schede", schede);
	}

	public void setPercentSchede(Integer percentSchede) {
		set("percentSchede", percentSchede);
	}

	public void setVolume(String volume) {
		set("volume", volume);
	}

	public void setPercentVolume(Integer percentVolume) {
		set("percentVolume", percentVolume);
	}

	public void setCitazioneBiblio(String citazioneBiblio) {
		set("citazioneBiblio", citazioneBiblio);
	}

	public void setMicroforme(String microforme) {
		set("microforme", microforme);
	}

	public void setPercentMicroforme(Integer percentMicroforme) {
		set("percentMicroforme", percentMicroforme);
	}

	public void setInformatizzatoDescr(String informatizzato) {
		set("informatizzato", informatizzato);
	}

	public void setUrl() {
		set("url", "<b>Url</b>");
	}

	public void setPercentInformatizzato(Integer percentInformatizzato) {
		set("percentInformatizzato", percentInformatizzato);
	}

	public void setDaAnno(Integer annoDa) {
		set("annoDa", annoDa);
	}

	public void setAAnno(Integer annoA) {
		set("annoA", annoA);
	}

	/**/

	public String getSchede() {
		return get("schede");
	}

	public Integer getPercentSchede() {
		return get("percentSchede");
	}

	public String getVolume() {
		return get("volume");
	}

	public Integer getPercentVolume() {
		return get("percentVolume");
	}

	public String getCitazioneBiblio() {
		return get("citazioneBiblio");
	}

	public String getMicroforme() {
		return get("microforme");
	}

	public Integer getPercentMicroforme() {
		return get("percentMicroforme");
	}

	public String getInformatizzato() {
		return get("informatizzato");
	}

	public String getUrl() {
		return get("url");
	}

	public Integer getPercentInformatizzato() {
		return get("percentInformatizzato");
	}

	public Integer getAnnoDa() {
		return get("annoDa");
	}

	public Integer getAnnoA() {
		return get("annoA");
	}
	
	public List<CataloghiUrlModel> getUrls(){
		return get("urlList");
	}
	
	public void setUrls(List<CataloghiUrlModel> urlList){
		set("urlList",urlList);
	}
	
	public void setIdPartecipaCatalogo(Integer idPartecipaCatalogo){
		set("idPartecipaCatalogo",idPartecipaCatalogo);
	}
	
	public Integer getIdPartecipaCatalogo(){
		return 	get("idPartecipaCatalogo");
	}
	
	public void setIdCatalogo(Integer idCatalogoCollettivo){
		set("idCatalogo",idCatalogoCollettivo);
	}
	
	public Integer getIdCatalogo(){
		return get("idCatalogo");
	}
	
	
	public void setIdInformatizzatoTipo(Integer idInformatizzatoTipo) {
		set("idInformatizzatoTipo",idInformatizzatoTipo);
		
	}
	public Integer getIdInformatizzatoTipo() {
		return get("idInformatizzatoTipo");
		
	}

	
	   public void setCondition(int condition) {
           set("condition", condition);
           
   }
   
   public int getCondition() {
           return (Integer) get("condition");
           
   }
}
