package it.inera.gmap.geocoder.google;


public class GeoException extends Exception{

	public GeoException() {
		super();
	}

	public GeoException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeoException(String message) {
		super(message);
	}

	public GeoException(Throwable cause) {
		super(cause);
	}
	
}