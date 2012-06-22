package it.inera.abi.logic;


public interface TrasferimentoBiblioteca {
	
	public void salva(int idbiblio) throws Exception ;
	
	public void ripristina(int idbiblio) throws Exception ;
	
	public void backupXml(int idbiblio) throws Exception ;
	
}