package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class MenuItem extends BaseTreeModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1349156147793122388L;
	private static int ID = 0;

	public MenuItem() {
		set("id", ID++);
	}

	public MenuItem(String name) {
		set("id", ID++);
		set("name", name);
	}
	
	public MenuItem(String name,Integer idTabDin) {
		set("idTabDin",idTabDin);
		set("name", name);
	}
	
	public MenuItem(String name, String iconStyle) {
		set("id", ID++);
		set("name", name);
		set("icon", iconStyle);
	}

	public MenuItem(String name, BaseTreeModel[] children) {
		this(name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public Integer getId() {
		return (Integer) get("id");
	}

	public String getName() {
		return (String) get("name");
	}

	public String toString() {
		return getName();
	}
	
	public Integer getIdTabDin() {
		return (Integer)  get("idTabDin");
	}
	
	public void setIdTabDin(Integer idTabDin) {
		set("idTabDin",idTabDin);
	}

	

}
