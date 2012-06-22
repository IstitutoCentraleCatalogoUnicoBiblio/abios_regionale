<%@page import="java.net.URLEncoder"%>
<%@page import="it.inera.solr.configuration.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title> Anagrafe Biblioteche Italiane - Lista biblioteche selezionate</title>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">

</HEAD>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  link="Blue" vlink="Blue" alink="Blue">
<solr:SolrSearch 
	formFields="regione,provincia,comune,descrizione_ufficiale,denominazione_forsearch,destinazioni_sociali,accesso_handicap,accesso_riservato,orario_ufficiale_fasce,gestisce_servizio_bibliografico_interno,gestisce_servizio_bibliografico_esterno,riproduzioni,posti_internet,prestito_locale,prestito_interbiblio_nazionale,prestito_interbiblio_internazionale,ente_denominazione,id_tipologia_amministrativa,tipologia_funzionale,descrizione_libera,codice_dewey,sbn,rism,acnp,sistemi_biblioteche,sezioni_speciali,spogli_bibliografici,fondi_speciali_descrizione,fondi_speciali_denominazione,patrimonio_librario_descr,catalogo_generale_tipo,cataloghi_speciali_descr,cataloghi_collettivi_descr"
	formRangeFields="DEWEYDA:0:DEWEYA:*:codice_dewey" 
	formNoEscapeFields="posti_internet,rism,id_tipologia_amministrativa" 
	sortField="isil_provincia asc, isil_numero asc" 
	returnFields="id_biblioteca,id_comune,denominazione_ufficiale,indirizzo,cap,comune,provincia_sigla,isil_stato,isil_provincia,isil_numero,latitudine,longitudine,stato_catalogazione" 
	start="0" row="50" />

<DIV align="center">
<!------TABELLA ESTERNA----------------------------------------------------------------------->
<table border="0" width="650" height="100%">
<tr>
	<td valign="top" align="center" colspan="3" width="650" height="96%">
		<!---------------------L O G O   &   A N A G R A F E ------------------------------------------------->
		<div align="center">
		<TABLE BORDER="0" WIDTH="100%">
		<TR>

			<TD width="5%" class="logoL"><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"><BR>ICCU</TD>
			<TD ALIGN="center" width="90%">
				<FONT color='#e27e10' face='Arial' size='5'><B>ANAGRAFE BIBLIOTECHE ITALIANE</B></FONT>
			</TD>
			<TD width="5%">&nbsp;</TD>
		</TR>
		<TR><TD colspan="3" ALIGN="CENTER"><HR ALIGN="CENTER" SIZE="3" WIDTH="100%"></TD></TR>
		<TR><TD colspan="3" ALIGN="CENTER">

			<table border="0"><tr><TD bgcolor="#E6E6E6" align="center" colspan="2"><font face="arial" size="+1" font color="Navy">
			LISTA BIBLIOTECHE SELEZIONATE</font></td></tr></table>
		</TD></TR>
		</TABLE>
		<!---------------------F I N E   L O G O   &   A N A G R A F E ------------------------------------------------->
<table border="0" width="650" cellspacing="3" cellpadding="3">

<TR>
	<TD class="verdinoCenter2" width="80"><b>Codice</b></td>
	<TD class="verdinoCenter2" width="260"><b>Denominazione</b></td>
	<TD class="verdinoCenter2" width="210"><b>Indirizzo - Frazione</b></td>
	<TD class="verdinoCenter2" width="30"><b>CAP</b></td>
	<TD class="verdinoCenter2" width="110"><b>Comune</b></td>
	<TD class="verdinoCenter2" width="110"><b>Provincia</b></td>
</tr>

<%
String queryString = request.getQueryString();
String queryStringEncoded = URLEncoder.encode(queryString, "UTF-8");
request.setAttribute("queryStringEncoded", queryStringEncoded);
%>

<solr:SolrDisplay xsl="lista_risultati" parameters="pagina_url:risultati.jsp|query_url:${pageContext.request.queryString}|torna:${requestScope.queryStringEncoded}" />
<tr>
	<td align="Left" colspan="6">
		<font color="Red" face="Arial" size="2">
			* = Le biblioteche contrassegnate da asterisco (*) non hanno dati completi
		</font>
	</td>
</tr>
<!-- TR><TD align="center" colspan="5">&nbsp;</td></tr-->
<TR>
		<TD align="left" colspan="3"><a href="" onclick="window.history.back();return false;"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
			Indietro</font></a></TD>
		<TD align="right" colspan="3"><a href="index.jsp"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
			HomePage</font></a></TD>
	</TR>

<TR><TD ALIGN="CENTER" COLSPAN="6"><HR ALIGN="CENTER" SIZE="2" WIDTH="100%"></TD></TR>

<TR>
	<td align="center" colspan="6">
		<jsp:include page="footer.jsp"></jsp:include>
	</td>
</TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
</td>
</tr>
<!---
<TR>
	<TD align="left" width="300" height="1%"><a href="" onclick="window.history.back();return false;"><FONT SIZE="2" COLOR="#FF8040" FACE="Arial Black">
		Indietro</font></a></TD>
	<TD align="center" width="150">&nbsp;</TD>
	<TD align="right" width="300" height="1%">&nbsp;</TD>
</TR>
<TR><TD align="center" colspan="3" width="750" height="1%"><HR size="1" color="Navy" width="100%"></TD></TR>
 --->
 </TABLE>
</div></body></html>