package it.inera.solr.xslt;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.inera.solr.xslt.XsltTransformer;

/**
 * Classe per la trasformazione XSLT di xml
 * @author Renato Eschini r.eschini@inera.it
 *
 */
public class XsltTransformer {

	private static Log _log = LogFactory.getLog(XsltTransformer.class);

	/**
	 * Esegue la trasformazione xml
	 * @param source Il sorgente in formato stringa dell'XML 
	 * @param urlTemplate L'url (file://, http://, ... protocollo supportato da URL) del file xsl
	 * @return Il risultato della trasformazione
	 * @throws TransformerException Rilanciata in caso di problemi
	 */
	public static String transform(String source, String urlTemplate) throws TransformerException {
		return transform(source, urlTemplate, null);
	}

	/**
	 * Esegue la trasformazione xml
	 * @param source Il sorgente in formato stringa dell'XML 
	 * @param urlTemplate L'url (file://, http://, ... protocollo supportato da URL) del file xsl
	 * @param parameters Parametri da passare alla trasformata
	 * @return Il risultato della trasformazione
	 * @throws TransformerException Rilanciata in caso di problemi
	 */
	public static String transform(String source, String urlTemplate, String parameters) throws TransformerException {
		try {
			return transform(source, new URL(urlTemplate).openStream(),parameters);
		} catch (MalformedURLException e) {
			_log.fatal("Url del file xsl non corretta: ", e);
			throw new TransformerException(e);
		} catch (IOException e) {
			_log.error("Errore nella creazione dello stream di transformazione: ", e);
			throw new TransformerException(e);
		}		
	}
	/**
	 * Esegue la trasformazione xml
	 * @param source Il sorgente in formato stringa dell'XML 
	 * @param is InpuStream del file xsl
	 * @param parameters Parametri da passare alla trasformata
	 * @return Il risultato della trasformazione
	 * @throws TransformerException Rilanciata in caso di problemi
	 */
	public static String transform(String source, InputStream is, String parameters)  throws TransformerException {
		Source xslSource = new StreamSource(is);
		

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Source xmlSource = new StreamSource(new StringReader(source));

		try {
			// Generate the transformer.
			Transformer transformer = tFactory.newTransformer(xslSource);
			if (StringUtils.isNotBlank(parameters)) {
				String [] params = parameters.split("\\|");
				for (int i=0; i< params.length ; i++) {
					String paramName = params[i].substring(0,params[i].indexOf(":"));
					String paramValue = params[i].substring(params[i].indexOf(":") + 1);
					transformer.setParameter(paramName, paramValue);
					
				}
			}
			
			// Perform the transformation, sending the output to the response.
			StringWriter stringWriter = new StringWriter();

			transformer.transform(xmlSource, new StreamResult(stringWriter));


			return stringWriter.getBuffer().toString();
		} catch (TransformerException e) {
			_log.error("Errore nella transformazione: ", e);
			throw e;
		}

	}
	
	/**
	 * Esegue la trasformazione xml
	 * @param source Il sorgente in formato stringa dell'XML 
	 * @param is InpuStream del file xsl
	 * @return Il risultato della trasformazione
	 * @throws TransformerException Rilanciata in caso di problemi
	 */
	public static String transform(String source, InputStream is)  throws TransformerException {
		return transform(source, is, null);
	}


}