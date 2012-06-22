package it.inera.abi.dao;

import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.ContattiTipo;
import it.inera.abi.persistence.Stato;

import java.util.List;

public interface ContattiDao {

	public void saveContatti(Contatti contatto);
	
	public List<Contatti> loadContattiByIdBiblioteca(int id_biblio);
	
	public List<Contatti> loadContattiByIdContatti(int id_contatti);
	
	public List<ContattiTipo> getTipologieContatti();
	
	public void removeContatto(int id_contatto);
	
}
