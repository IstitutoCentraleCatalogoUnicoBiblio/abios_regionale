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
