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

/**
 * Modello utilizzato per rappresentare il patrimonio librario
 *
 */
public class PatrimonioLibrarioModel extends PatrimonioSpecializzazioneModel {

	private static final long serialVersionUID = 8121364809168350275L;

	public PatrimonioLibrarioModel() {
		
	}
	public PatrimonioLibrarioModel(int id_tipologia, String tipologia, double quantita) {
		
		set("id_tipologia", id_tipologia);
		set("tipologia", tipologia);
		set("quantita", quantita);
	}
	public PatrimonioLibrarioModel(int id_tipologia, String tipologia, int quantita, int quantitaUltimoAnno, int id_biblio) {
		
		set("id_tipologia", id_tipologia);
		set("tipologia", tipologia);
		set("quantita", quantita);
		set("quantitaUltimoAnno", quantitaUltimoAnno);
		set("id_biblio", id_biblio);
		
	}
	public void setIdTipologia(int id_tipologia) {
		set("id_tipologia", id_tipologia);
	}
	
	public int getIdTipologia() {
		return (Integer) get("id_tipologia");
	}
	
	public Integer getQuantita() {
		return (Integer) get("quantita");
	}

	public void setQuantita(int d) {
		set("quantita", d);
	}
	
	public Integer getQuantitaUltimoAnno() {
		return (Integer) get("quantitaUltimoAnno");
	}

	public void setQuantitaUltimoAnno(int quantitaUltimoAnno) {
		set("quantitaUltimoAnno", quantitaUltimoAnno);
	}
	
	public void setIdBiblioteca(int id_biblio) {
		set("id_biblio", id_biblio);
	}

	public Integer getIdBiblioteca() {
		return get("id_biblio");
	}
	
	
}
