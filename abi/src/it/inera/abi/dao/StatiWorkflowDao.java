package it.inera.abi.dao;

import it.inera.abi.persistence.StatoBibliotecaWorkflow;

import java.util.List;

/** 
 * Interfaccia DAO per l'entità Stato Biblioteca Workflow
 *
 */
public interface StatiWorkflowDao {
	
	public List<StatoBibliotecaWorkflow> getAll();
	
}
