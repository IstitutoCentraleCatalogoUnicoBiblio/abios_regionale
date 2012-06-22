package it.inera.solr.solr;

import it.inera.solr.configuration.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

/**
 * Classe che astrae l'accesso al server Solr
 * @author Renato Eschini r.eschini@inera.it
 * 
 */
public class ServerInstanceManager {

	private static Log _log = LogFactory.getLog(ServerInstanceManager.class);
	private static CommonsHttpSolrServer server = null;
	private static HttpClient _http_client = null;

	static {
		
		try {
			/**
			 * client solrj
			 */
			server = new CommonsHttpSolrServer(Configuration.getSolrUrl().concat("/").concat(Configuration.getSolrDefaultCore()));
			server.setSoTimeout(Configuration.getSolrClientSoTimout());  // socket read timeout
			server.setConnectionTimeout(Configuration.getSolrClientTimout());
			server.setDefaultMaxConnectionsPerHost(Configuration.getSolrClientDefaultMaxConnectionsPerHost());
			server.setMaxTotalConnections(Configuration.getSolrClientMaxTotalConnections());
			server.setFollowRedirects(false);  // defaults to false
			// allowCompression defaults to false.
			// Server side must support gzip or deflate for this to have any effect.
			server.setAllowCompression(true);
			server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
			
			
			/**
			 *  client http
			 */
			_http_client = server.getHttpClient();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static SolrServer getServer() throws Exception {
		return server;
	}
	
	public static String sendGetCommand(String solrCommand, String queryString) throws SolrServerException {
		return sendGetCommand(Configuration.getSolrDefaultCore(), solrCommand, queryString);
	}
	
	/**
	 * Invia un comando in GET a Solr
	 * @param urlSolr Url solr (url + comando + core)
	 * @param queryString La query string da passare a Solr
	 * @return Risposta di Solr al comando
	 * @throws SolrServerException Errore di accesso a Solr
	 */
	public static String sendGetCommand(String solrCore, String solrCommand, String queryString) throws SolrServerException {
		GetMethod getmethod = null;
		StringBuffer responseBuffer = new StringBuffer();
		Reader reader = null;
		try {
			getmethod = new GetMethod(Configuration.getSolrUrl().concat("/").concat(solrCore).concat("/").concat(solrCommand));
			getmethod.setQueryString(queryString);
			int statusCode = _http_client.executeMethod(getmethod);

			if (statusCode != HttpStatus.SC_OK) {
				_log.error("Http connection failed: " + getmethod.getStatusLine());
				throw new HttpException("Http connection failed: " + getmethod.getStatusLine());
			}

			reader = new InputStreamReader(getmethod.getResponseBodyAsStream(), getmethod.getResponseCharSet()); 
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			while (line != null) {
				responseBuffer.append(line);
				line = bufferedReader.readLine();
			}
		} catch (HttpException e) {
			getmethod.abort();
			_log.fatal("Fatal protocol violation", e);
			throw new SolrServerException("Fatal protocol violation", e);
		} catch (IOException e) {
			getmethod.abort();
			_log.error("Fatal transport error", e);
			throw new SolrServerException("Fatal transport error", e);
		} finally {
			IOUtils.closeQuietly(reader);
			if (getmethod != null) getmethod.releaseConnection();
			getmethod = null;
		}
		return responseBuffer.toString();
	}


}