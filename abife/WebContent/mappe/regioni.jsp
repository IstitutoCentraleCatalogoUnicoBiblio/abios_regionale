<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<html>
<head>
<title>Mappa</title>
<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css"></link>
<link rel="stylesheet" href="<c:url value="/mappe/style.css" />" type="text/css"></link>

</head>
<body>
<table border=0 style="width: 100%;">
	<tr>
		<td colspan="2">
			<h1 style="FONT-WEIGHT: bold; width:100%; FONT-SIZE: 18; COLOR: #f2b117; FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif" align="right">Ricerca Geografica Biblioteche</h1>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="geocrumbs">
				<a style="font-size: 10px; " href='<c:url value="/mappe/regioni.jsp?id_biblioteca=*"/>'><b> ITALIA </b></a>
			</div>			
		</td>
	</tr>
	<tr >
		<td colspan="2" align="left" style="border-top: 1px solid #597291">
			<table border="0" cellpadding="5" cellspacing="5">
				 <tr> 
				    <td align="left" valign="middle">
				    	<div class="labelmenu"><b>REGIONI</b></div>
				    	<div class="geomenu">
				    		<c:url value="/" var="path"></c:url>
				    		<solr:SolrSearch formFields="id_biblioteca" formNoEscapeFields="id_biblioteca" start="0" row="0" facetField="regione" facetMinCount="1" facetSort="index" facetLimit="-1"/>
				    		<solr:SolrDisplay xsl="mappa_regioni" parameters="path:${path}" />					    	
					    </div>
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
</table>

</body>
</html>