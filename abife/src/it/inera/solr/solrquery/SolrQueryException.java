package it.inera.solr.solrquery;

public class SolrQueryException extends Exception {

	private static final long serialVersionUID = 6830255942721178104L;

	public SolrQueryException() {
	}

	public SolrQueryException(String message) {
		super(message);
	}

	public SolrQueryException(Throwable cause) {
		super(cause);
	}

	public SolrQueryException(String message, Throwable cause) {
		super(message, cause);
	}

}
