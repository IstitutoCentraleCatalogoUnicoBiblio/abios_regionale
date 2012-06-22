package it.inera.gmap;

import it.inera.gmap.markersgenereator.MarkersCreatorFactory;

import java.io.IOException;
import java.util.Properties;

public class GmapProperties {
	
	private static GmapProperties _instance = null;
	private static Properties properties = null;
	
	private final static String PROPERTIES = "gmap.properties";
	public final static String MARKERS_CREATOR_PROPS_KEY = "markerscreator";
	public final static String QUERY_PARAMS_PROPS_KEY = "queryparams";
	
	private GmapProperties() {
		properties = new Properties();
		try {
			properties.load(MarkersCreatorFactory.class.getClassLoader().getResourceAsStream(PROPERTIES));
		} catch (IOException e) {
			
		}
	}
	
	private synchronized static GmapProperties getInstance() {
		if (_instance == null) {
			_instance = new GmapProperties();
		}
		return _instance;
	}
	
	public static String getProperty(String key) {
		getInstance();
		return properties.getProperty(key);
	}
	
}
