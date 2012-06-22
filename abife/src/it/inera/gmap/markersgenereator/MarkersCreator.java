package it.inera.gmap.markersgenereator;

import it.inera.gmap.markersgenereator.castor.Markers;

/**
 * Classe per la generazione dei markers
 * @author r.eschini
 */
public abstract class MarkersCreator {
	
	/**
	 * Attraverso dei parametri, il metodo genera l'oggetto markers di 
	 * Castor per per la 
	 * mappa di google
	 * @param parameters
	 * @return
	 */
	public abstract Markers getMarkers(String[] parameters);

}