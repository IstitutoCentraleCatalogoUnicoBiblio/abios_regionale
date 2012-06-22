<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="it.inera.solr.configuration.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/fn" prefix="fn" %>
<html>
<head>
<title>Anagrafe Biblioteche Italiane -   Risultati della ricerca</title>
<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css">
</head>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  topmargin="0" marginheight="0" marginwidth="0" leftmargin="0">
<solr:SolrSearch 
	formFields="regione,provincia,comune,descrizione_ufficiale,denominazione_forsearch,destinazioni_sociali,accesso_handicap,accesso_riservato,orario_ufficiale_fasce,gestisce_servizio_bibliografico_interno,gestisce_servizio_bibliografico_esterno,riproduzioni,posti_internet,prestito_locale,prestito_interbiblio_nazionale,prestito_interbiblio_internazionale,ente_denominazione,id_tipologia_amministrativa,tipologia_funzionale,descrizione_libera,codice_dewey,sbn,rism,acnp,sistemi_biblioteche,sezioni_speciali,spogli_bibliografici,fondi_speciali_descrizione,fondi_speciali_denominazione,patrimonio_librario_descr,catalogo_generale_tipo,cataloghi_speciali_descr,cataloghi_collettivi_descr"
	formRangeFields="DEWEYDA:0:DEWEYA:*:codice_dewey" 
	formNoEscapeFields="posti_internet,rism,id_tipologia_amministrativa" 
	sortField="isil_provincia asc, isil_numero asc" 
	returnFields="id_biblioteca,id_comune,denominazione_ufficiale,indirizzo,cap,comune,provincia_sigla,isil_stato,isil_provincia,isil_numero,latitudine,longitudine,stato_catalogazione" 
	start="0" row="50" />
<div align="center">

<table BORDER="0" width="100%" BGCOLOR="white" align="center" height="100%" cellspacing="0">
<tr>
	<td width="10%">&nbsp;</td>
	<td align="center" valign="top">
<!---------------------L O G O   &   A N A G R A F E ------------------------------------------------->
<div align="center">
<TABLE BORDER="0" WIDTH="80%">
<TR>
	<TD ALIGN="center" VALIGN="top" width="5%" class="logoL"><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"><BR>ICCU</TD>
	<TD ALIGN="center" width="70%">
		<FONT color='#e27e10' face='Arial' size='5'><B>ANAGRAFE BIBLIOTECHE ITALIANE</B></FONT>
	</TD>
	<TD width="5%">&nbsp;</TD>
</TR>
<TR><TD colspan="3" ALIGN="CENTER"><HR ALIGN="CENTER" SIZE="3" WIDTH="100%"></TD></TR>
</TABLE>
<!---------------------F I N E   L O G O   &   A N A G R A F E ------------------------------------------------->
<TABLE BORDER="0" WIDTH="600" BGCOLOR="white">
	<TR>
		<TD colspan="3" ALIGN="CENTER"  BGCOLOR="white">
			<TABLE BORDER="0">
				<tr>
					<td bgcolor="#E6E6E6" colspan="3">
						<FONT SIZE="3" COLOR="#000099" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>&nbsp; Risultati della ricerca &nbsp;</b>
					</td>
				</tr>
			</table>
		</td>
	</TR>
	<tr><td colspan="3" >&nbsp;</td></tr>
<!----------------------------------------TABELLA---------------------------------------->
	<tr>
		<td align="center">
			<TABLE BORDER="0" width="100%">
				<TR>
					<td BGCOLOR="#339966" colspan="4" align="center">
						<FONT SIZE="3" COLOR="white" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>&nbsp; Parametri impostati &nbsp;</b>
					</td>
				</TR>
				<c:if test="${not empty param.regione}">
				<!-- tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Regione :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.regione}"/>
					</td>
				</tr-->
				</c:if>
				<c:if test="${not empty param.provincia}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Provincia :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.provincia}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.comune}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Comune :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.comune}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.denominazione_forsearch}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Denominazione :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.denominazione_forsearch}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.codice_dewey}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Codice Dewey :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.codice_dewey}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.descrizione_ufficiale}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Specializzazione :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.descrizione_ufficiale}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.DEWEYDA}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Da codice Dewey :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.DEWEYDA}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.DEWEYA}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>A codice Dewey :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.DEWEYA}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.descrizione_libera}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Descrizione libera :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.descrizione_libera}"/>
					</td>
				</tr>
				</c:if>				
				<c:if test="${not empty param.ente_denominazione}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Ente di appartenenza :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.ente_denominazione}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.tipologia_amministrativa}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Tipologia amministrativa :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:choose>
							<c:when test="${fn:indexOf(param.id_tipologia_amministrativa, '*') == -1}">
								<c:out value="${fn:substring(param.tipologia_amministrativa, 5, fn:length(param.tipologia_amministrativa))}"/>								
							</c:when>
							<c:otherwise>
								<c:out value="${param.tipologia_amministrativa}"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.tipologia_funzionale}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Tipologia funzionale :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.tipologia_funzionale}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.destinazioni_sociali}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Destinazione sociale :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<c:out value="${param.destinazioni_sociali}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.accesso_handicap}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Accessibilità portatori di handicap :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.accesso_riservato}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Accesso :</b>
					</td>					
					<c:choose>
						<c:when test="${param.accesso_riservato == 'false'}">
							<td colspan="2" BGCOLOR="#E6E6E6">
								<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Aperta a tutti
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="2" BGCOLOR="#E6E6E6">
								<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Riservata
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
				<c:if test="${not empty param.orario_ufficiale_fasce}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Orario :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.orario_ufficiale_fasce}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.sistemi_biblioteche}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Sistema di biblioteche :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.sistemi_biblioteche}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.sezioni_speciali}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Sezione speciale :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.sezioni_speciali}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.patrimonio_librario_descr}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Patrimonio librario :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.patrimonio_librario_descr}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.catalogo_generale_tipo}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Catalogo generale :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.catalogo_generale_tipo}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.cataloghi_speciali_descr}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Catalogo speciale :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.cataloghi_speciali_descr}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.cataloghi_collettivi_descr}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Catalogo collettivo :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.cataloghi_collettivi_descr}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.fondi_speciali_denominazione}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Denominazione fondi speciali :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.fondi_speciali_denominazione}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.fondi_speciali_descrizione}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Descrizione fondi speciali :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.fondi_speciali_descrizione}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.spogli_bibliografici}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Spogli materiale bibliografico :</b>
					</td>
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.spogli_bibliografici}"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.gestisce_servizio_bibliografico_interno}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Servizio Interno Informazioni bibliografiche :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.gestisce_servizio_bibliografico_esterno}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Servizio Esterno Informazioni bibliografiche :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.riproduzioni}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Riproduzioni :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.posti_internet}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Postazioni INTERNET per gli utenti :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.prestito_locale}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Prestito locale :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.prestito_interbiblio_nazionale}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Prestito nazionale :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.prestito_interbiblio_internazionale}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>Prestito internazionale :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">Si
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.sbn}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>SBN :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.sbn}" />
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.rism}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>RISM :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.rism}" />
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty param.acnp}">
				<tr>
					<td colspan="2" BGCOLOR="#a0efa5">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
						<b>ACNP :</b>
					</td>					
					<td colspan="2" BGCOLOR="#E6E6E6">
						<FONT SIZE="2" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
							<c:out value="${param.acnp}" />
					</td>
				</tr>
				</c:if>
				<tr><td colspan="4">&nbsp;</td></tr>
			</table>
		</td>
	</tr>
<!----------------------------------------TABELLA---------------------------------------->
<%
String queryString = request.getQueryString();
String queryStringEncoded = URLEncoder.encode(queryString, "UTF-8");
request.setAttribute("queryStringEncoded", queryStringEncoded);
%>

<solr:SolrDisplay xsl="lista_parametri" parameters="pagina_url:${pageContext.request.requestURL}|query_url:${pageContext.request.queryString}|torna:${requestScope.queryStringEncoded}" />
	
<!----------------------------------------TABELLA---------------------------------------->
	<tr>
		<td>
			<TABLE BORDER="0" WIDTH="600" BGCOLOR="white" align="center">
				<tr><td colspan="3">&nbsp;</td></tr>
				<TR>
					<TD colspan="1" align="right" width="200">
						<INPUT TYPE="BUTTON" VALUE="Nuova ricerca" onclick="window.location='<c:url value="ricerca.jsp" />'">
					</TD>
					<TD colspan="1" align="center" width="200">
						<c:choose>
							<c:when test="${param.ricerca_tipo=='semplice'}">
								<INPUT TYPE="BUTTON" VALUE="Restringi ricerca" onclick="window.location='<c:url value="ricercas.jsp" />?${pageContext.request.queryString}'">							
							</c:when>
							<c:otherwise>
								<INPUT TYPE="BUTTON" VALUE="Restringi ricerca" onclick="window.location='<c:url value="ricercaa.jsp" />?${pageContext.request.queryString}'">
							</c:otherwise>
						</c:choose>
					</TD>
					<TD colspan="1" align="left" width="200">	
						<%
							String solrResponse = pageContext.getAttribute(Constants.SOLR_RESPONSE).toString();
							String numFound = StringUtils.substringBetween(solrResponse, "numFound=\"", "\"");
							if (!"0".equals(numFound)) {
						%>
							<INPUT TYPE="SUBMIT" VALUE="Visualizza elenco" onclick="window.location='<c:url value="risultati.jsp" />?${pageContext.request.queryString}'">
						<%} %>
					</TD>
				</TR>
				<tr><td colspan="3">&nbsp;</td></tr>
				<tr>
					<TD colspan="3" ALIGN="CENTER" >
						<HR ALIGN="CENTER" SIZE="3" WIDTH="100%">
					</TD>
				</tr>
				<TR>
					<td align="center" colspan="3">
						<jsp:include page="footer.jsp"></jsp:include>
					</td>
				</TR>			
				<tr>
					<TD colspan="3" ALIGN="Right">
						<a href="<c:url value="/index.jsp" />"><FONT SIZE="2" COLOR="#FF8040" FACE="Arial Black">HomePage</font></a>
					</TD>
				</tr>
			</table>
		</td>
	</tr>
	</td>
</tr>
</table>
</td><TD background="" width="10%">&nbsp;</td>
</table>
</form></div></body></html>