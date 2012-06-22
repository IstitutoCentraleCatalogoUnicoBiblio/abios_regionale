package it.inera.solr.configuration;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.inera.solr.configuration.Configuration;

import it.inera.solr.configuration.Configuration;

/**
 * Singleton per gestione configurazione
 * Usa la libreria commons-configuration di Apache-Jakarta
 * @author Renato Eschini r.eschini@inera.it
 *
 */
@SuppressWarnings("rawtypes")
public class Configuration {
	
	private static Configuration _instance = null;
	private static XMLConfiguration configuration = null;
	private static Log log = null;
	
	private final static String CONFIG_FILENAME = "configuration.xml";
	
	private Configuration() {
		log = LogFactory.getLog(Configuration.class);
		try {
			 configuration = new XMLConfiguration();
			 configuration.setDelimiterParsingDisabled(true);
			 configuration.load(CONFIG_FILENAME);
			 configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			log.fatal("Fatal load configuration error", e);
		}
	}
	
	private final static synchronized Configuration getInstance() {
		if (_instance == null) {
			_instance = new Configuration();
		}
		return _instance;
	}

	/*
	 *	Solr: connessione 
	 */
	public final static String getSolrUrl() {
		getInstance();
		return configuration.getString("solr.solr-url");
	}
	
	public final static int getSolrClientTimout() {
		getInstance();
		return configuration.getInt("solr.solr-client-timeout", 60000);
	}
	
	public final static boolean getSolrClientCommit() {
		getInstance();
		return configuration.getBoolean("solr.solr-client-commit", true);
	}
	
	public final static int getSolrClientSoTimout() {
		getInstance();
		return configuration.getInt("solr.solr-client-soTimeout", 60000);
	}
	
	public final static int getSolrClientConnectionTimout() {
		getInstance();
		return configuration.getInt("solr.solr-client-connectionTimeout", 60000);
	}
	
	public final static int getSolrClientDefaultMaxConnectionsPerHost() {
		getInstance();
		return configuration.getInt("solr.solr-client-defaultMaxConnectionsPerHost", 100);
	}
	
	public final static int getSolrClientMaxTotalConnections() {
		getInstance();
		return configuration.getInt("solr.solr-client-maxTotalConnections", 100);
	}
	
	public final static boolean getSolrClientFollowRedirects() {
		getInstance();
		return configuration.getBoolean("solr.solr-client-followRedirects", false);
	}
	
	public final static boolean getSolrClientAllowCompression() {
		getInstance();
		return configuration.getBoolean("solr.solr-client-allowCompression", true);
	}
	
	public final static int getSolrClientMaxRetries() {
		getInstance();
		return configuration.getInt("solr.solr-client-maxRetries", 0);
	}
	public final static String getSolrDefaultCore() {
		getInstance();
		return configuration.getString("solr.default-core");
	}
	
	/*
	 *  Solr query config
	 */
	public final static String getXslUrl(String name) {
		getInstance();
		List xsltNames = configuration.getList("xslt.xsl.[@name]");
		return configuration.getString("xslt.xsl(" + xsltNames.lastIndexOf(name) + ").[@url]");
	}

	public final static String getSourceUrl(String name) {
		getInstance();
		List sourceNames = configuration.getList("sources.source.[@name]");
		return configuration.getString("sources.source(" + sourceNames.lastIndexOf(name) + ").[@url]");
	}
	
	public final static String getGmapKey() {
		getInstance();
		return configuration.getString("gmap.key");
	}

	public static void main(String[] args) {
		System.out.println(getGmapKey());
	}
}