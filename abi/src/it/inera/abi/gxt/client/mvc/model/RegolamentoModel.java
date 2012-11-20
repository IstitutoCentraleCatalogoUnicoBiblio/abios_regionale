package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per rappresentare i regolamenti associati alla biblioteca
 *
 */
public class RegolamentoModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 2155928509043707435L;

	public RegolamentoModel() {
		
	}

	public RegolamentoModel(int id_regolamento, int id_biblitoeca,
			String riferimentoNormativa, String url) {
		set("id_regolamento", id_regolamento);
		set("id_biblitoeca", id_biblitoeca);
		set("riferimentoNormativa", riferimentoNormativa);
		set("url", url);

	}

	public int getIdRegolamento() {
		return (Integer) get("id_regolamento");
	}

	public void setIdRegolamento(int id_regolamento) {
		set("id_regolamento", id_regolamento);
	}

	public String getRiferimentoNormativa() {
		return get("riferimentoNormativa");
	}

	public void setRiferimentoNormativa(String riferimentoNormativa) {
		set("riferimentoNormativa", riferimentoNormativa);
	}

	public String getUrl() {
		return get("url");
	}

	public void setUrl(String url) {
		set("url", url);
	}

	public int getIdBiblioteca() {
		return (Integer) get("id_biblitoeca");
	}

	public void setIdBiblioteca(int id_biblitoeca) {
		set("id_biblitoeca", id_biblitoeca);
	}

}
