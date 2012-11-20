package it.inera.abi.logic;

/**
 * Classe contenente la logica delle principali operazioni riguardanti
 * il trasferimento di una biblioteca (salva, ripristina, backup xml)
 *
 */
public interface TrasferimentoBiblioteca {
	
	public void salva(int idbiblio) throws Exception ;
	
	public void ripristina(int idbiblio) throws Exception ;
	
	public void backupXml(int idbiblio) throws Exception ;
	
}