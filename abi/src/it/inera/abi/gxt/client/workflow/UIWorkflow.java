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

package it.inera.abi.gxt.client.workflow;

import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.PhotoModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.ButtonBase;

/**
 * Gestisce il workflow, i widget delle form in base allo stato della biblioteca.
 * In caso di cambiamento di stato, modifica / abilita bottoni, mette readOnly 
 * i textField, ecc...
 *
 */
public class UIWorkflow {

	public static final String DEFINITIVA = "Definitiva";
	public static final String OCCUPATA = "Occupata";
	public static final String REVISIONE = "Revisione";
	public static final String CANCELLATA = "Cancellata";

	/**
	 * Controlla se la biblioteca è occupabile da questo utente
	 * @param biblioModel Modello della biblioteca
	 * @return Booleano che indica se è occupabile dall'utente loggato
	 */
	public static boolean checkIsOccupabile(BiblioModel biblioModel) {
		UtentiAuthModel utentiAuthModel = UIAuth.getUtentiAuthModel();
		// se è definitiva la posso occupare
		if (DEFINITIVA.equals(biblioModel.getStatoCatalogazione())) {
			return true;
		}
		// se è occupata e sono lo stesso utente la posso occupare (in questo caso continuare a mettere in modifica)
		if (OCCUPATA.equals(biblioModel.getStatoCatalogazione()) 
				&& utentiAuthModel.getUserLogin().equalsIgnoreCase(biblioModel.getUtenteUltimaModifica())) {
			return true;
		}
		// se è in revisione
		if (REVISIONE.equals(biblioModel.getStatoCatalogazione()) && Roles.isUserInRole(Roles.REVISIONE)) {
			return false;
		}
		if (CANCELLATA.equals(biblioModel.getStatoCatalogazione())) {
			return true;
		}
		return false;
	}
	
	public static boolean isOccupata(BiblioModel biblioModel) {
		return OCCUPATA.equalsIgnoreCase(biblioModel.getStatoCatalogazione());
	}
	public static boolean isRevisione(BiblioModel biblioModel) {
		return REVISIONE.equalsIgnoreCase(biblioModel.getStatoCatalogazione());
	}
	
	
	/**
	 * Controlla se la biblioteca è revisionabile
	 * @param biblioModel Modello della biblioteca
	 * @return Booleano che dice se è revisionabile
	 */
	public static boolean checkIsRevisionabile(BiblioModel biblioModel) {
		// se è in revisione
		if (REVISIONE.equals(biblioModel.getStatoCatalogazione()) && Roles.isUserInRole(Roles.REVISIONE)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isRevisione() {
		Boolean revisione = (Registry.get(BiblioModel.REVISIONE) == null ? false : (Boolean) Registry.get(BiblioModel.REVISIONE));
		return revisione;
	}
	
	/**
	 * 
	 * METODI PER IL CONTROLLO DEL READONLY (VISUALIZZAZIONE) DELLA UI DELLE BIBLIOTECHE
	 */
	public static Boolean isReadOnly() {
		Boolean readOnly = (Registry.get(BiblioModel.READONLY) == null ? false : (Boolean) Registry.get(BiblioModel.READONLY));
		if (readOnly) return true;
		
		Boolean revisione = (Registry.get(BiblioModel.REVISIONE) == null ? false : (Boolean) Registry.get(BiblioModel.REVISIONE));
		if (revisione) return true;
		
		return false;
	}
	public static void setReadOnly(Field<?> field) {
		if (isReadOnly()) {
			field.setReadOnly(true);
		} else {
			field.setReadOnly(false);
		}
	}
	
	public static void enableDisable(ButtonBase component) {
		if (isReadOnly()) {
			component.setEnabled(false);
		} else {
			component.setEnabled(true);
		}
	}
	public static void enableDisable(Radio component) {
		if (isReadOnly()) {
			component.setEnabled(false);
		} 
		else {
			component.setEnabled(true);
		}
	}
	
	public static void hideView(Component obj) {
		if (isReadOnly()) {
			obj.hide();
		} else {
			obj.show();
		}
	}
	public static void addOrRemoveFromToolbar(ToolBar toolBar, Button button) {
		if (!isReadOnly()) {
			toolBar.add(button);
		} else {
			int test = toolBar.indexOf(button);
			if (test != -1) toolBar.remove(button);
		}
	}
	public static void gridEnableEvent(Grid grid) {
		if (!isReadOnly()) {
			grid.disableEvents(false);
		} else {
			grid.disableEvents(true);
		}
	}
	
	public static void listViewEnableEvent(ListView<PhotoModel> listView) {
		if (!isReadOnly()) {
			listView.disableEvents(false);
			
		} else {
			listView.disableEvents(true);
		}
	}
	
}
