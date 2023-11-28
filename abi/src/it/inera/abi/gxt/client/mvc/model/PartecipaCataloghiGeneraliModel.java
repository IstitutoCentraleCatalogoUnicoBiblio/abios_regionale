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
 * Estensione di <code>PartecipaCataloghiGenericaModel</code> per i cataloghi generali
 *
 */
public class PartecipaCataloghiGeneraliModel extends PartecipaCataloghiGenericaModel {

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
