package it.inera.abi.dto;

import java.io.Serializable;

public class PatrimonioSubCategoryDTO implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 8177396526854678074L;

	private int id_patrimonio_specializzazione;
	
	private  String descrizioneTipologia;
	
	private String descrizioneCat;
	
	private int id_cat;
	
	private Integer id_cat_madre;
	
	public PatrimonioSubCategoryDTO() {
	
	}
	/**
	 * @param id_patrimonio_specializzazione
	 * @param descrizione_composta
	 */
	public PatrimonioSubCategoryDTO(int id_patrimonio_specializzazione,
			String descrizione_composta) {
		super();
		this.id_patrimonio_specializzazione = id_patrimonio_specializzazione;
		this.descrizioneTipologia = descrizione_composta;
		
	}
	/**
	 * @return the id_patrimonio_specializzazione
	 */
	public int getId_patrimonio_specializzazione() {
		return id_patrimonio_specializzazione;
	}
	/**
	 * @param id_patrimonio_specializzazione the id_patrimonio_specializzazione to set
	 */
	public void setId_patrimonio_specializzazione(int id_patrimonio_specializzazione) {
		this.id_patrimonio_specializzazione = id_patrimonio_specializzazione;
	}
	/**
	 * @return the descrizione_composta
	 */
	public String getDescrizioneTipologia() {
		return descrizioneTipologia;
	}
	/**
	 * @param descrizione_composta the descrizione_composta to set
	 */
	public void setDescrizioneTipologia(String descrizione_composta) {
		this.descrizioneTipologia = descrizione_composta;
	}
	
	/**
	 * @return the descrizioneCat
	 */
	public String getDescrizioneCat() {
		return descrizioneCat;
	}
	/**
	 * @param descrizioneCat the descrizioneCat to set
	 */
	public void setDescrizioneCat(String descrizioneCat) {
		this.descrizioneCat = descrizioneCat;
	}
	/**
	 * @return the id_cat
	 */
	public int getId_cat() {
		return id_cat;
	}
	/**
	 * @param id_cat the id_cat to set
	 */
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	
	/**
	 * @return the id_cat_madre
	 */
	public Integer getIdCatMadre() {
	
		return id_cat_madre;
	
	}
	
	/**
	 * @param id_cat_madre the id_cat_madre to set
	 */
	public void setIdCatMadre(Integer id_cat_madre) {
		
		this.id_cat_madre = id_cat_madre;
		
	}
	
}
