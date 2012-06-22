<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="it.inera.solr.configuration.Constants"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>

<solr:SolrSearch 
	formFields="isil_stato,isil_provincia,isil_numero"
	returnFields="id_biblioteca,id_comune,latitudine,longitudine" 
	fieldsCase="isil_stato:uppercase,isil_provincia:uppercase,isil_numero:uppercase"
	start="0" row="1" />
	
	
<%
	Object solrResponseObj = pageContext.getAttribute(Constants.SOLR_RESPONSE);
	String solrResponse = (solrResponseObj != null) ? (String) solrResponseObj : "";
	
	String numFound = StringUtils.substringBetween(solrResponse, "numFound=\"", "\"");
	
	if (StringUtils.isNumeric(numFound) && Integer.valueOf(numFound) != 0) {
		String id_biblioteca = StringUtils.substringBetween(solrResponse, "<str name=\"id_biblioteca\">", "</str>");
		String id_comune = StringUtils.substringBetween(solrResponse, "<int name=\"id_comune\">", "</int>");
		String latitudine = StringUtils.substringBetween(solrResponse, "<double name=\"latitudine\">", "</double>");
		String longitudine = StringUtils.substringBetween(solrResponse, "<double name=\"longitudine\">", "</double>");
		
		
		StringBuffer url = new StringBuffer();
		url.append(request.getContextPath());
		url.append("/home.jsp?id_biblioteca=");
		url.append(id_biblioteca);
		url.append("&page=zero&id_comune=");
		url.append(id_comune);
		
		// ctrl mappe
		if (latitudine != null && longitudine != null && !"0.0".equalsIgnoreCase(latitudine) && !"0.0".equalsIgnoreCase(longitudine)) {
			url.append("&mappe=true");
		}
		
		response.sendRedirect(url.toString());
	} else {
		StringBuffer url = new StringBuffer();
		url.append(request.getContextPath());
		url.append("/notfound.jsp");
		response.sendRedirect(url.toString());
	}
%>
