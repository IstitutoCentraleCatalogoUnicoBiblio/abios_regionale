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
 * Modello utilizzato per caricare i dati relativi alle tabelle 
 * - cataloghi_collettivi_materiale_url
 * - cataloghi_speciali_materiale_url
 * - cataloghi_generali_url
 * 
 */
public class CataloghiUrlModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3660228898372966185L;

	public CataloghiUrlModel() {
	}
	
	public CataloghiUrlModel(Integer idPartecipa,Integer idCataloghi,String url,String descrizioneUrl) {
	
		set("idPartecipa",idPartecipa);	
		set("idCataloghi",idCataloghi);	
		set("url",url);
		set("descrizioneUrl",descrizioneUrl);
	}
	
	/**
	 * Setta l'id relativo alla tabella partecipa_cataloghi_collettivi_materiale oppure
	 * partecipa_cataloghi_speciali_materiale oppure partecipa_cataloghi_generali
	 * @param idPartecipa
	 * 
	 */
	public void setIdPartecipa(Integer idPartecipa) {
		set("idPartecipa",idPartecipa);	
	}
	
	/**
	 * Ritorna l'id relativo alla tabella partecipa_cataloghi_collettivi_materiale oppure
	 * partecipa_cataloghi_speciali_materiale oppure partecipa_cataloghi_generali
	 * @return idPartecipa
	 * 
	 */
	public Integer getIdPartecipa() {
		return get("idPartecipa");	
	}
	
	/**
	 * Setta l'id relativo alla tabella cataloghi_collettivi_materiale_url oppure
	 * cataloghi_speciali_materiale_url oppure cataloghi_generali_url
	 * @param idCataloghi
	 * 
	 */
	public void setIdCataloghiUrl(Integer idCataloghi) {
		set("idCataloghi",idCataloghi);	
	}
	
	/**
	 * Ritorna l'id relativo alla tabella cataloghi_collettivi_materiale_url oppure
	 * cataloghi_speciali_materiale_url oppure cataloghi_generali_url
	 * @return idCataloghi
	 * 
	 */
	public Integer getIdCataloghiUrl() {
		return get("idCataloghi");	
	}
	
	/**
	 * Setta l'indirizzo url
	 * @param url
	 * 
	 */
	public void setUrl(String url) {
		set("url",url);
	}
	
	/**
	 * Ritorna l'indirizzo url
	 * @return url
	 * 
	 */
	public String getUrl() {
		return get("url");
	}
	
	/**
	 * Setta la descrizione relativa all'url
	 * @param descrizioneUrl
	 * 
	 */
	public void setDescrUrl(String descrizioneUrl) {
		set("descrizioneUrl",descrizioneUrl);
	}
	
	/**
	 * Ritorna la descrizione relativa all'url
	 * @return descrizioneUrl
	 * 
	 */
	public String getDescr() {
		return get("descrizioneUrl");
	}
	
}
