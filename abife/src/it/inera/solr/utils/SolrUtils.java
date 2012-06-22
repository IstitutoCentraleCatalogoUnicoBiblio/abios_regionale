package it.inera.solr.utils;

import java.io.StringReader;

import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;

public class SolrUtils {
	
	protected static XMLResponseParser xmlResponseParser = new XMLResponseParser();
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	public static QueryResponse getQueryResponse(String response) {
		NamedList<Object> o = xmlResponseParser.processResponse(new StringReader(response));
		QueryResponse queryResponse = new  QueryResponse();
		queryResponse.setResponse(o);
		return queryResponse;
	}
	
	public static String doEscaping(String value) {
		
		String[] toEscape = {"\\\\", "\\+","-","!","\\(","\\)","\\{" ,"\\}" ,"\\[" ,"\\]","\\^","\"" ,"\\~" ,"\\*" ,":"};
		String[] escaped = {"\\\\\\\\", "\\\\+","\\\\-","\\\\!","\\\\(","\\\\)","\\\\{" ,"\\\\}" ,"\\\\[" ,"\\\\]","\\\\^","\\\\\"" ,"\\\\~" ,"\\\\*", "\\\\:"};
		
		for (int i = 0; i < toEscape.length; i++) {
			value = value.replaceAll(toEscape[i], escaped[i]);
		}
		return value;
	}
}
