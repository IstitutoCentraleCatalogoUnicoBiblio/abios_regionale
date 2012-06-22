package it.inera.abi.logic;

public interface UserActionLog {

	public abstract void logAction(String user, String actionDescription);

	public abstract void logActionCatalogazione(String user, String actionDescription);
	public abstract void logActionCatalogazioneBiblioDefaultUser(String actionDescription);

	public abstract	void logActionStatoBiblioteca(String user, String actionDescription);
	public abstract void logActionStatoBibliotecaDefaultUser(String actionDescription);
	public abstract void logActionStatoBibliotecaDefaultUser(String actionDescription, String username);
	
	public abstract	void logActionNuovaBiblioteca(String user, String actionDescription);
	public abstract void logActionNuovaBibliotecaDefaultUser(String actionDescription);
	
	public abstract	void logActionTabelleDinamiche(String user, String actionDescription);
	public abstract void logActionTabelleDinamicheDefaultUser(String actionDescription);
	
	public abstract	void logActionUtentiLogic(String user, String actionDescription);
	public abstract void logActionUtentiLogicDefaultUser(String actionDescription);

}