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
 * Modello utilizzato per i dati relativi al deposito legale
 *
 */
public class DepositiLegaliModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = 8707529681960661555L;

	public DepositiLegaliModel() {

	}
	
	public DepositiLegaliModel(String depositoDescr, String depositoAnno) {
		set("depositoDescr", depositoDescr);
		set("depositoAnno", depositoAnno);
	}
	
	public DepositiLegaliModel(int idDepositoTipo, String depositoDescr, String depositoAnno) {
		set("idDepositoTipo",idDepositoTipo);
		set("depositoDescr", depositoDescr);
		set("depositoAnno", depositoAnno);
	}
	
	public void setIdDepositoTipo(int idDepositoTipo){
		set("idDepositoTipo",idDepositoTipo);
	}
	
	public int getIdDepositoTipo(){
		return (Integer) get("idDepositoTipo");
	}
	
	public void setDepositoDescr(String depositoDescr){
		set("depositoDescr", depositoDescr);
	}
	
	public String getDepositoDescr(){
		return get("depositoDescr");
	}
	
	public void setDepositoAnno(String depositoAnno){
		set("depositoAnno",depositoAnno);
	}
	
	public String getDepositoAnno(){
		return get("depositoAnno");
	}
	
}
