package it.inera.gmap.markersgenereator;

import it.inera.gmap.GmapProperties;

import java.io.IOException;
import java.util.Properties;

/**
 * Factory per la creazione dei markers
 * @author r.eschini
 */
public class MarkersCreatorFactory {
	
	private static String markers_creator_impl;
	
	/**
	 * Ritorna l'implementazione del creatore di markers
	 * @return Istanza del MarkersCreator
	 */
	public static MarkersCreator getMarkersCreator() {
		
		markers_creator_impl =  GmapProperties.getProperty(GmapProperties.MARKERS_CREATOR_PROPS_KEY);
		
		MarkersCreator impl = null;
		try {
			impl = (MarkersCreator) Class.forName(markers_creator_impl).newInstance();
		} catch (InstantiationException e) {
			impl = getDefaultInstance();
		} catch (IllegalAccessException e) {
			impl = getDefaultInstance();
		} catch (ClassNotFoundException e) {
			impl = getDefaultInstance();
		}		
		return impl;
	}
	
	/**
	 * Ritorna l'istanza di default della classe
	 * @return Null o istanza di default della classe, 
	 */
	private static MarkersCreator getDefaultInstance() {
		return null;
	}
}
