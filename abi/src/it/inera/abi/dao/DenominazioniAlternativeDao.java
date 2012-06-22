package it.inera.abi.dao;

import it.inera.abi.persistence.DenominazioniAlternative;

import java.util.List;

public interface DenominazioniAlternativeDao {

	public List<DenominazioniAlternative> getDenominazioniAlternative(int id_biblioteca);
	
	public void setListaDenominazioniAlternative(int id_biblioteca,List<DenominazioniAlternative> listaDenominazioni);
	
	public void addDenominazioneAlternativa(DenominazioniAlternative denominazione);

	public void removeDenominazioneAlternativa(int id_record);
}
