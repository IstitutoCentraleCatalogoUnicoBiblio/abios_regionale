package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare le categorie delle specializzazioni del patrimonio
 *
 */
public class PatrimoniCategorieTabelleDinamicheModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = -7344501152185004205L;

	public PatrimoniCategorieTabelleDinamicheModel() {

	}

	public void setIdCategoria(Integer idCategoria) {
		set("idCategoria",idCategoria);
	}

	public Integer getIdCategoria() {
		return get("idCategoria");
	}

	public void setCategoriaDescrizione(String categoriaDescrizione) {
		set("categoriaDescrizione",categoriaDescrizione);
	}

	public String getCategoriaDescrizione() {
		return	get("categoriaDescrizione");
	}

	public void setIdCategoriaMadre(Integer idCategoriaMadre) {
		set("idCategoriaMadre",idCategoriaMadre);
	}
	
	public Integer getIdCategoriaMadre() {
		return get("idCategoriaMadre");
	}

	public void setCategoriaMadreDescrizione(String categoriaMadreDescrizione) {
		set("categoriaMadreDescrizione",categoriaMadreDescrizione);
	}

	public String getCategoriaMadreDescrizione() {
		return	get("categoriaMadreDescrizione");
	}

}
