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

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * Classe che rappresenta una entry del menu delle tabelle dinamiche
 *
 */
public class MenuItem extends BaseTreeModel implements Serializable {

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
