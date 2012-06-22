package it.inera.gmap.geocoder.google;

import java.text.MessageFormat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;

public class GeoAddressStandardizer{
	private static final String BASE_URL = "http://maps.google.com/maps/geo?q={0}&output={1}&key={2}";
	private static final String XML = "xml", CSV = "csv";
	private String _apiKey;
	private long _rateLimitInterval = 5769L;
	private long _lastRequestTime = System.currentTimeMillis() - _rateLimitInterval;
	private HttpClientParams _httpClientParams = null;

	private static HttpConnectionManager _connectionManager = new MultiThreadedHttpConnectionManager();

	/**
	 * The httpClient in combination with the {@link MultiThreadedHttpConnectionManager} is
	 * thread-safe. See: <a href="http://hc.apache.org/httpclient-3.x/threading.html">HttpClient - Threading</a>
	 */
	private static HttpClient _httpClient = new HttpClient(_connectionManager);


	/**
	 * Sets the {@link HttpConnectionManager} to be used for connecting to the geocoding service
	 */
	public static synchronized void setConnectionManager(HttpConnectionManager manager) {
		_connectionManager = manager;
		_httpClient = new HttpClient(_connectionManager);
	}
	/**
	 * Sets the {@link HttpClient} to be used for connecting to the geocoding service
	 * @param client
	 */
	public static synchronized void setHttpClient(HttpClient client) {
		_httpClient = client;
	}
	/**
	 * Parameters for controlling the http connection.
	 * http://jakarta.apache.org/commons/httpclient/preference-api.html#HTTP_parameters
	 * @return
	 */
	public HttpClientParams getHttpClientParams() {
		return _httpClientParams;
	}
	public void setHttpClientParams(HttpClientParams httpClientParams) {
		_httpClientParams = httpClientParams;
		if(_httpClientParams != null && _httpClient != null){
			_httpClient.setParams(_httpClientParams);
		}
	}
	/**
	 * Register a google geocoding API key at 
	 * http://www.google.com/apis/maps/signup.html
	 */
	public GeoAddressStandardizer(String apiKey){
		_apiKey = apiKey;
	}
	/**
	 * Register a google geocoding API key at 
	 * http://www.google.com/apis/maps/signup.html
	 */
	public GeoAddressStandardizer(String apiKey, long rateIntervalInMillis){
		this(apiKey);
		if(rateIntervalInMillis < 0){
			throw new IllegalArgumentException("rateInterval cannot be negative");
		}
		_rateLimitInterval = rateIntervalInMillis;
	}

	/**
	 * Standardize an address using google's geocoding service;
	 * @param mappingFunction - a mapping function that converts the kml string returned by google's 
	 * geocoding service to any other object type.
	 * @throws GeoException Indicates something unexpected occurs.
	 * It also includes a {@link GeoStatusCode} to signal problems about the status of the geocoding request. 
	 */
	public String standardize(String addressLine) throws GeoException{
		try {
			String[] values = {addressLine, CSV, _apiKey};
			HttpURL url = new HttpURL(MessageFormat.format(BASE_URL, values));
			String res = getServerResponse(url.toString());
			return res;
		}
		catch (RuntimeException re){
			throw re;
		}catch (GeoException e) {
			throw e;
		}catch (Exception e) {
			throw new GeoException(e.getMessage());
		}
	}
	private synchronized String getServerResponse(String url) throws Exception{	  
		GetMethod get = null; 
		try {
			long timeTilNextStart = _rateLimitInterval - ( System.currentTimeMillis() - _lastRequestTime);
			if(timeTilNextStart > 0){
				Thread.sleep(timeTilNextStart); //sleep for some time
			}
			_lastRequestTime = System.currentTimeMillis();
			get = new GetMethod(url);
			get.setFollowRedirects(true);
			_httpClient.executeMethod(get);
			return IOUtils.toString(get.getResponseBodyAsStream(), get.getRequestCharSet());
		} finally {
			if (get != null) get.releaseConnection();
		}
	}

	public String getApiKey() {
		return _apiKey;
	}

	public void setApiKey(String apiKey) {
		_apiKey = apiKey;
	}

	public long getRateLimitInterval() {
		return _rateLimitInterval;
	}

	public void setRateLimitInterval(long rateLimitInterval) {
		_rateLimitInterval = rateLimitInterval;
	}


}
