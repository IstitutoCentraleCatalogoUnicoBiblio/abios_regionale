package it.inera.abi.gxt.client.mvc.model.auth;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class ProfiliModel extends BaseModelData {

	private static final long serialVersionUID = -9097049998905234640L;

	public ProfiliModel() {
	}
	
	public ProfiliModel(Integer id, String denominazione) {
		set("id", id);
		set("denominazione", denominazione);
	}
	
	public Integer getId() {
		return get("id");
	}
	
	public String getDenominazione() {
		return get("denominazione");
	}
}
