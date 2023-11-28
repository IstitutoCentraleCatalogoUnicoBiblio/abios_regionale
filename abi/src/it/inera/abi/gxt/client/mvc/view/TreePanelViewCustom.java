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

package it.inera.abi.gxt.client.mvc.view;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.Joint;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanelView;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Estensione custom della classe <code>TreePanelView</code> che modifica
 * l'ampiezza dell'immagine di apertura del TreePanel
 *
 */
public class TreePanelViewCustom extends TreePanelView {
	private static final int intervalloBr = 21;

	@Override
	public String getTemplate(ModelData m, String id, String text, AbstractImagePrototype icon, boolean checkable,
			boolean checked, Joint joint, int level, TreeViewRenderMode renderMode) {
		if (renderMode == TreeViewRenderMode.CONTAINER) {
			return "<div unselectable=on class=\"x-tree3-node-ct\" role=\"group\" ></div>";
		}
		StringBuilder sb = new StringBuilder();
		if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.MAIN) {
			sb.append("<div unselectable=on id=\"");
			sb.append(id);
			sb.append("\"");

			sb.append(" class=\"x-tree3-node\"  role=\"presentation\">");

			String cls = "x-tree3-el";
			if (GXT.isHighContrastMode) {
				switch (joint) {
				case COLLAPSED:
					cls += " x-tree3-node-joint-collapse";
					break;
				case EXPANDED:
					cls += " x-tree3-node-joint-expand";
					break;
				}
			}
			/*Rimozione br*/
//			String insertHeight=null;
//			int lunghezzaTesto=text.length();
//			if((lunghezzaTesto/intervalloBr)==1){
//				insertHeight =" style=\"height:30px;\"";
//			}else if((lunghezzaTesto/intervalloBr)==2){
//				insertHeight =" style=\"height:44px;\"";
//			}else if((lunghezzaTesto/intervalloBr)==3){
//				insertHeight =" style=\"height:50px;\"";
//			}else{
//				insertHeight ="";
//			}
			sb.append("<div unselectable=on class=\"" + cls + "\" id=\"" + tree.getId() + "__" + id + "\" role=\"treeitem\" ");
			/*Rimozione br*/
//			sb.append(" aria-level=\"" + (level + 1) + "\" "+insertHeight+"  >");
			sb.append(" aria-level=\"" + (level + 1) + "\" >");
		}
		if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.BODY) {
			Element jointElement = null;
			switch (joint) {
			case COLLAPSED:
				jointElement = (Element) tree.getStyle().getJointCollapsedIcon().createElement().cast();
				break;
			case EXPANDED:
				jointElement = (Element) tree.getStyle().getJointExpandedIcon().createElement().cast();
				break;
			}

			if (jointElement != null) {
				El.fly(jointElement).addStyleName("x-tree3-node-joint");
			}

			sb.append("<img src=\"");
			sb.append(GXT.BLANK_IMAGE_URL);
			sb.append("\" style=\"height: 18px; width: ");
			sb.append(level * 18);
			sb.append("px;\" />");
			sb.append(jointElement == null ? "<img src=\"" + GXT.BLANK_IMAGE_URL
					+ "\" style=\"width: 2px\" class=\"x-tree3-node-joint\" />" : DOM.toString(jointElement));
			if (checkable) {
				Element e = (Element) (checked ? GXT.IMAGES.checked().createElement().cast()
						: GXT.IMAGES.unchecked().createElement().cast());
				El.fly(e).addStyleName("x-tree3-node-check");
				sb.append(DOM.toString(e));
			} else {
				sb.append("<span class=\"x-tree3-node-check\"></span>");
			}
			if (icon != null) {
				Element e = icon.createElement().cast();
				El.fly(e).addStyleName("x-tree3-node-icon");
				sb.append(DOM.toString(e));
			} else {
				sb.append("<span class=\"x-tree3-node-icon\"></span>");
			}
			sb.append("<span  unselectable=on class=\"x-tree3-node-text\" style=\"font-weight:bold;\">");
/*Rimozione br*/
			//			sb.append(Utils.insertBRtagMenuItems(text, intervalloBr));
			sb.append(text);
			sb.append("</span>");
		}

		if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.MAIN) {
			sb.append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}

}