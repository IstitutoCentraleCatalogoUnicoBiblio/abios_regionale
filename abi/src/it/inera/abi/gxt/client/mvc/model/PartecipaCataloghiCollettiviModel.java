package it.inera.abi.gxt.client.mvc.model;

import java.util.List;

public class PartecipaCataloghiCollettiviModel extends PartecipaCataloghiGenericaModel {
	/**Variabili per serializzazione*/
	VoceUnicaModel zonaEspansione;
	List<String> urlList;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4146088434688813504L;

	public PartecipaCataloghiCollettiviModel() {
//		set("zonaIsSet",false);
//		set("denominazioneCatalogoIsSet", false);
//		set("entryIsSet", false);
	}
	
	public PartecipaCataloghiCollettiviModel(String denominazioneCatalogo,
			VoceUnicaModel zonaEspansione,String denominazioneMateriale, String zona, String schede,
			int percentSchede, String volume, int percentVolume,
			String citazioneBiblio, String microforme, int percentMicroforme,
			String informatizzato, List<String> urlList, int percentInformatizzato,
			int annoDa, int annoA) {
		super(schede, percentSchede, volume, percentVolume, citazioneBiblio,
				microforme, percentMicroforme, informatizzato, urlList,
				percentInformatizzato, annoDa, annoA);

//		set("denominazioneCatalogo", denominazioneCatalogo);
//		set("zonaEspansione", zonaEspansione);
//		set("zona", zona);
//		set("denominazioneMateriale",denominazioneMateriale);
	}

	public void setDenominazioneCatalogo(String denominazione) {
		set("denominazioneCatalogo", denominazione);
//		set("denominazioneCatalogoIsSet", true);
	}

	public void setZonaEspansioneDescr(String zonaEspansioneDescr) {
		set("entry", zonaEspansioneDescr);
//		set("entryIsSet", true);
	}
	
	public void setZonaEspansione(VoceUnicaModel zonaEspansione) {
		set("zonaEspansioneModel", zonaEspansione);
	//	set("entry", zonaEspansione.getEntry());
	}
	
	public void setIdZonaEspansione(Integer idZonaEspansione) {
		set("idZonaEspansione", idZonaEspansione);
	
	}
	
	public Integer getIdZonaEspansione() {
		return get("idZonaEspansione");
	
	}

	public void setDettaglioZona(String zona) {
		set("zona", zona);
//		set("zonaIsSet",true);
	}
	
//	public boolean zonaIsSet(){
//		return get("zonaIsSet");
//	}
//	
//	public boolean denominazioneCatalogoIsSet(){
//		return get("denominazioneCatalogoIsSet");
//	}
//	
//	public boolean entryIsSet(){
//		return get("entryIsSet");
//	}
/**/
	public String getDenominazioneCatalogo() {
		return get("denominazioneCatalogo");
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
	
	public String getZonaEspansioneDescr() {
		return get("entry");
	}
	
	public VoceUnicaModel getZonaEspansione() {
		return get("zonaEspansioneModel");
	}

	public String getDettaglioZona() {
		return get("zona");
	}
	
	public String getDenominazioneComposta() {
		return get("denominazioneComposta");
	}
	
	public String getIntestazioneComposta(){
		return	get("intestazioneComposta");
	}
	
	public void setIntestazioneComposta(){
//		set("intestazioneComposta","Denominazione catalogo:"+getDenominazioneCatalogo()+" - "+"Zona espansione:"+getZonaEspansioneDescr()+" - "+"Dettaglio:"+(getDettaglioZona()!=null?getDettaglioZona():"non specificato"));
		set("intestazioneComposta",getDenominazioneCatalogo()+" - "+"Zona espansione:"+getZonaEspansioneDescr()+(getDettaglioZona()!=null?" - Dettaglio:"+getDettaglioZona():""));

	}

	
}
