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
