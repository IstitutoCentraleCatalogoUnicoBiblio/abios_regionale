package it.inera.solr.solrtaglib.tag;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.inera.solr.configuration.Configuration;
import it.inera.solr.configuration.Constants;
import it.inera.solr.xslt.XsltTransformer;

/**
 * Tag che esegue il display dei risultati di Solr attraverso una trasformata XSL
 * @author Renato Eschini r.eschini@inera.it
 *
 */
public class SolrDisplay extends TagSupport implements Serializable {

	private static final long serialVersionUID = 8839575512713454611L;
	
	private Log _log = LogFactory.getLog(SolrDisplay.class);
	
	private String xsl;
	private String parameters;
	
	
	@Override
	public int doStartTag() throws JspException {
		
		if (StringUtils.isBlank(xsl)) xsl = Constants.DEFAULTXSL; 
		
		String xslUrl = Configuration.getXslUrl(xsl);
		
		// lettura della risposta di SOLR dall'attributo di pagina
		String solrResponse = "";
		Object solrResponseObject = pageContext.getAttribute(Constants.SOLR_RESPONSE);
		if (solrResponseObject != null) solrResponse = (String) solrResponseObject;
		
		if (StringUtils.isBlank(solrResponse)) _log.warn("Risposta SOLR nulla"); 
		
		// trasformazione della risposta di SOLR tramite xslt
		String transformResult = "";
		try {
			transformResult = XsltTransformer.transform(solrResponse, xslUrl,parameters);
			pageContext.getOut().write(transformResult);
		} catch (TransformerException e) {
			_log.fatal("Trasformazione xsl", e);
		} catch (IOException e) {
			_log.fatal("IO write risultato transformazione", e);
		}
		
		return EVAL_PAGE;
	}

	public String getXsl() {
		return xsl;
	}

	public void setXsl(String xsl) {
		this.xsl = xsl;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	
}