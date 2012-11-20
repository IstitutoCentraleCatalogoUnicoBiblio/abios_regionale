package it.inera.abi.gxt.client.workflow;

import it.inera.abi.gxt.client.auth.Roles;
import it.inera.abi.gxt.client.auth.UIAuth;
import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.auth.UtentiAuthModel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Component;
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
	 * @param biblioModel
	 * @return boolean
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
	 * @param biblioModel
	 * @return boolean
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
	
}
