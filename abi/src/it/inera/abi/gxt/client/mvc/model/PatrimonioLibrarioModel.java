package it.inera.abi.gxt.client.mvc.model;

public class PatrimonioLibrarioModel extends PatrimonioSpecializzazioneModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8121364809168350275L;

	public PatrimonioLibrarioModel() {
		
	}
	public PatrimonioLibrarioModel(int id_tipologia,String tipologia, double quantita) {
		
		set("id_tipologia", id_tipologia);
		set("tipologia", tipologia);
		set("quantita", quantita);
	}
	public PatrimonioLibrarioModel(int id_tipologia,String tipologia, int quantita,int quantitaUltimoAnno,int id_biblio) {
		
		set("id_tipologia", id_tipologia);
		set("tipologia", tipologia);
		set("quantita", quantita);
		set("quantitaUltimoAnno", quantitaUltimoAnno);
		set("id_biblio", id_biblio);
		
	}
	public void setIdTipologia(int id_tipologia){
		set("id_tipologia", id_tipologia);
	}
	
	public int getIdTipologia(){
		return (Integer) get("id_tipologia");
	}
	
	public int getQuantita(){
		return (Integer) get("quantita");
	}

	public void setQuantita(int d){
		set("quantita",d);
	}
	
	public int getQuantitaUltimoAnno(){
		return (Integer) get("quantitaUltimoAnno");
	}

	public void setQuantitaUltimoAnno(int quantitaUltimoAnno){
		set("quantitaUltimoAnno",quantitaUltimoAnno);
	}
	
	public void setIdBiblioteca(int id_biblio) {
		set("id_biblio", id_biblio);
	}

	public Integer getIdBiblioteca() {
		return get("id_biblio");
	}
	
	
}
