<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anagrafe Biblioteche Italiane - Specializzazione</TITLE>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
<SCRIPT language="JavaScript">
function replaceApice(elemento)
{
	//alert(elemento);
	var result = '';
	var i=0;
	
	for(i=0; i<elemento.length; i++)
	{
		if(elemento.charAt(i)=="'")
			result += "\\'"
		else
			result += elemento.charAt(i)
	}
	//alert(result);
	return result;
}


function createRef(elemento, elemento2)
{
	document.write("<A HREF=\"javascript:seleziona('" + replaceApice(elemento) +"','" + replaceApice(elemento2) + "')\">"
			+ "<FONT size=1 color=Blue face=Arial>" + elemento + "</font></a>");
}

function substHTMLBiachi(inCad)
{
	if(inCad.charCodeAt(0)==160)//Codice del &nbsp;
		inCad = '';
	return inCad;
}

function seleziona(DEWEY, DESCR)
{
	window.opener.document.formOptions.<c:out value="${param.field}"/>.value = substHTMLBiachi(DEWEY);
	var param = '<c:out value="${param.field}"/>';
	if (param == 'codice_dewey')
		window.opener.document.formOptions.descrizione_ufficiale.value = substHTMLBiachi(DESCR);
	window.close();
	
}
</script>
<!---
--->
</HEAD>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  link="Blue" vlink="Blue" alink="Blue" leftmargin="70" marginwidth="70">

<solr:SolrSearch 
	core="dewey"
	formFields="id_dewey"
	formNoEscapeValues="*"
	sortField="id_dewey asc" 
	returnFields="id_dewey,descrizione"
	start="0" row="50" />


<FORM ACTION="/iccu/abi" METHOD="POST" NAME="formHelp">

<table border="0" width="600" cellpadding="3" cellspacing="3">
<TR>
	<TD align="left" width="600" height="1%" colspan="2"><a href="" onclick="window.close();return false;"><FONT SIZE="2" COLOR="#FF8040" FACE="Arial Black">Chiudi</font></a></TD>
</TR>

<TR><TD CLASS="verdeCenter3" align="center" colspan="2">CODICE DEWEY</TD></TR>
<TR>
	<TD class="verdinoLeft2" width="100"><b>Codice Dewey</b></TD>
	<TD class="verdinoLeft2"><b>Descrizione</b></TD>
</TR>

<solr:SolrDisplay xsl="helpCodiceDewey" parameters="pagina_url:${pageContext.request.requestURL}|query_url:${pageContext.request.queryString}|torna:${requestScope.queryStringEncoded}" />

<TR>
	<TD align="left" width="600" height="1%" colspan="2"><a href="" onclick="window.close();return false;"><FONT SIZE="2" COLOR="#FF8040" FACE="Arial Black">Chiudi</font></a></TD>
</TR>
</table>
</form></body></html>