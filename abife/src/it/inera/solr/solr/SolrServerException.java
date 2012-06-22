package it.inera.solr.solr;

import java.io.IOException;

/**
 * Errore generico di accesso al Server Solr
 * @author Renato Eschini r.eschini@inera.it
 *
 */
public class SolrServerException extends IOException {

	private static final long serialVersionUID = -4959325000175483074L;

	public SolrServerException() {
	}

	public SolrServerException(String message) {
		super(message);
	}

	public SolrServerException(Throwable cause) {
		super(cause);
	}

	public SolrServerException(String message, Throwable cause) {
		super(message, cause);
	}

}
