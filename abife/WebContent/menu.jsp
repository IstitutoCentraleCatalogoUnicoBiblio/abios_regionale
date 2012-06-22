<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/fn" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
	<script language="javascript" src="<c:url value="/script/gmap/commons.js" />"></script>
	<style type="text/css">
		a:link { text-decoration: none; }
		a:visited {color:Blue; text-decoration: none; } 
		a:hover { color: red; } 
	</style>
</head>
<body>

<jsp:include page="mappe/jsglobalparams.jsp"></jsp:include>

<%
String torna = StringUtils.defaultIfEmpty(request.getParameter("torna"), "");
String queryStringEncoded = URLEncoder.encode(torna, "UTF-8");
request.setAttribute("queryStringEncoded", queryStringEncoded);
%>

	<table width="160" cellpadding="0" cellspacing="1">
		<tbody>
			<tr>
				<td><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"></td>
				<td align="center"><font size="2" color="#009966" face="Arial"><b>Istituto Centrale per il Catalogo Unico</b></font></td>
			</tr>			
			<tr><td colspan="2"><font size="1" face="Arial"><hr size="1" width="140" color="#009966"></font></td></tr>
		</tbody>
	</table>
	<table width="138" align="center" border="1" cellspacing="1">
		<tbody>
			<c:if test="${fn:length(param.torna) > 0}">
				<tr>
					<td colspan="2" align="left"><a href="risultati.jsp?<c:out value="${param.torna}" />" target="_top"><font size="1.5" color="Red" face="Verdana, Arial, Helvetica, sans-serif">Torna alla lista</font></a>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=zero&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Dati anagrafici</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=tip&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Tipologia amministrativa e funzionale</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=prof&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Profilo storico e sede - Sistemi di biblioteche</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=acc&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Accesso - Destinazione sociale</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=ora&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Orario ufficiale</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=var&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Variazioni d'orario</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=patr&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Patrimonio librario - Specializzazioni - Fondi speciali</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=catgen&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Cataloghi generali</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=catspec&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Cataloghi speciali</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=catcoll&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Cataloghi collettivi</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=sez&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Sezioni speciali - Servizi</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=prest&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Prestito</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=suppl&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Informazioni supplementari</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="home.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=pers&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />&id_comune=<c:out value="${param.id_comune}" />" target="_top"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Personale - Bilancio - Deposito legale</font></a>
				</td>
			</tr>
			<c:if test="${param.mappe=='true'}">
				<tr>
					<td colspan="2" align="left"><a href="#" onclick="viewMap('<c:out value="${param.id_biblioteca}" />');"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Mappa</font></a>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left"><a href="#" onclick="viewListaComuneMap('<c:out value="${param.id_comune}" />');"><font size="1.5" face="Verdana, Arial, Helvetica, sans-serif">Mappa biblioteche comune</font></a>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="left"><a href="ricerca.jsp" target="_top"><font size="1.5" color="Red" face="Verdana, Arial, Helvetica, sans-serif">Nuova ricerca</font></a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><a href="index.jsp" target="_top"><font size="1.5" color="Red" face="Verdana, Arial, Helvetica, sans-serif">Home page</font></a>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>