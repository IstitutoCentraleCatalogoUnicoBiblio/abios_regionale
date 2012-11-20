package it.inera.abi.gxt.client.mvc.model;

import java.util.List;

/**
 * Estensione di <code>PartecipaCataloghiGenericaModel</code> per i cataloghi speciali
 *
 */
public class PartecipaCataloghiSpecialiModel extends PartecipaCataloghiGenericaModel {

	List<String> urlList;
	private static final long serialVersionUID = -2325734764512980625L;

	public PartecipaCataloghiSpecialiModel() {

	}

	public PartecipaCataloghiSpecialiModel(String denominazione,
			String tipiMateriali, String schede, int percentSchede,
			String volume, int percentVolume, String citazioneBiblio,
			String microforme, int percentMicroforme, String informatizzato,
			List<String> urlList, int percentInformatizzato, int annoDa, int annoA) {

		super(schede, percentSchede, volume, percentVolume, citazioneBiblio,
				microforme, percentMicroforme, informatizzato, urlList,
				percentInformatizzato, annoDa, annoA);
		set("denominazione", denominazione);
		set("tipiMateriali", tipiMateriali);
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public String getDenominazione() {
		return get("denominazione");
	}

	public void setDenominazioneMateriale(String denominazioneMateriale) {
		set("denominazioneMateriale", denominazioneMateriale);
	}
	

	public String getDenominazioneMateriale() {
		return get("denominazioneMateriale");
	}
	
	public Integer getIdMateriale(){
		return	get("idMateriale");
	}
	public void setIdMateriale(Integer idMateriale){
		set("idMateriale",idMateriale);
	}
	


}
