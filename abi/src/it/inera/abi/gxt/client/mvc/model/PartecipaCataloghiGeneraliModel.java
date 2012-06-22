package it.inera.abi.gxt.client.mvc.model;

import java.util.List;

public class PartecipaCataloghiGeneraliModel extends PartecipaCataloghiGenericaModel {
	/**
	 * 
	 */
	List<String> urlList;
	private static final long serialVersionUID = -6234254875891143715L;

	public PartecipaCataloghiGeneraliModel() {
		
	}

	public PartecipaCataloghiGeneraliModel(Integer idTipoCatalogo,String tipoCatalogoDescr, String schede, int percentSchede,
			String volume, int percentVolume, String citazioneBiblio,
			String microforme, int percentMicroforme, String informatizzato,
			List<String> urlList, int percentInformatizzato, int annoDa, int annoA) {

		super(schede, percentSchede, volume, percentVolume, citazioneBiblio,
				microforme, percentMicroforme, informatizzato, urlList,
				percentInformatizzato, annoDa, annoA);
		set("idTipoCatalogo", idTipoCatalogo);
		set("tipoCatalogoDescr", tipoCatalogoDescr);
		
	}

	
	public void setIdTipoCatalogo(Integer idTipoCatalogo) {
		set("idTipoCatalogo", idTipoCatalogo);
	}

	public Integer getIdTipoCatalogo() {
	return	get("idTipoCatalogo");
	}

	
	
	public void setTipoCatalogoDescr(String tipoCatalogoDescr) {
		set("tipoCatalogoDescr", tipoCatalogoDescr);
	}

	public String getTipoCatalogoDescr() {
	return	get("tipoCatalogoDescr");
	}

}
