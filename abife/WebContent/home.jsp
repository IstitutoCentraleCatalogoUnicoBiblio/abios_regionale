<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="content-type" CONTENT="text/html;charset=UTF-8">
<META NAME="resource-type" CONTENT="document">
<c:choose>
<c:when test="${param.page=='zero'}">
<title>Dati Anagrafici</title>
</c:when>
<c:when test="${param.page=='tip'}">
<title>Tipologia amministrativa e funzionale</title>
</c:when>
<c:when test="${param.page=='prof'}">
<title>Profilo storico e sede - Sistemi di biblioteche</title>
</c:when>
<c:when test="${param.page=='acc'}">
<title>Accesso - Destinazione sociale</title>
</c:when>
<c:when test="${param.page=='ora'}">
<title>Orario ufficiale</title>
</c:when>
<c:when test="${param.page=='var'}">
<title>Variazioni d'orario</title>
</c:when>
<c:when test="${param.page=='patr'}">
<title>Patrimonio librario - Specializzazioni - Fondi speciali</title>
</c:when>
<c:when test="${param.page=='dew'}">
<title>Patrimonio librario - Specializzazioni - Fondi speciali</title>
</c:when>
<c:when test="${param.page=='catgen'}">
<title>Cataloghi generali</title>
</c:when>
<c:when test="${param.page=='curl' and param.type=='gen'}">
<title>Cataloghi generali</title>
</c:when>
<c:when test="${param.page=='catspec'}">
<title>Cataloghi speciali</title>
</c:when>
<c:when test="${param.page=='curl' and param.type=='spec'}">
<title>Cataloghi speciali</title>
</c:when>
<c:when test="${param.page=='catcoll'}">
<title>Cataloghi collettivi</title>
</c:when>
<c:when test="${param.page=='curl' and param.type=='coll'}">
<title>Cataloghi collettivi</title>
</c:when>
<c:when test="${param.page=='sez'}">
<title>Sezioni speciali - Servizi</title>
</c:when>
<c:when test="${param.page=='prest'}">
<title>Prestito</title>
</c:when>
<c:when test="${param.page=='suppl'}">
<title>Informazioni supplementari</title>
</c:when>
<c:when test="${param.page=='pers'}">
<title>Personale - Bilancio - Deposito legale</title>
</c:when>
</c:choose>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
</head>
<%
String torna = StringUtils.defaultIfEmpty(request.getParameter("torna"), "");
String queryStringEncoded = URLEncoder.encode(torna, "UTF-8");
request.setAttribute("queryStringEncoded", queryStringEncoded);
%>
<frameset cols="170,*" framespacing="0" border="1" bordercolor="#009966">
	<frame name="menu" src="menu.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&id_comune=<c:out value="${param.id_comune}" />&mappe=<c:out value="${param.mappe}" />&torna=<c:out value="${requestScope.queryStringEncoded}" />">
	<c:choose>
	<c:when test="${param.page=='dew'}">
		<frameset rows="65,*" framespacing="0" border="1" bordercolor="#009966">
			<frame name="header" src="header.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />">
			<frame name="scheda" src="scheda.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=<c:out value="${param.page}" />&id_dewey=<c:out value="${param.id_dewey}" />&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />">
			<!--frame name="footer" src="footer.jsp"-->
		</frameset>
	</c:when>
	<c:when test="${param.page=='curl'}">
		<frameset rows="65,*" framespacing="0" border="1" bordercolor="#009966">
			<frame name="header" src="header.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />">
			<frame name="scheda" src="scheda.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=<c:out value="${param.page}" />&type=<c:out value="${param.type}" />&id_cat_url=<c:out value="${param.id_cat_url}" />&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />">
			<!--frame name="footer" src="footer.jsp"-->
		</frameset>
	</c:when>	
	<c:otherwise>
		<frameset rows="65,*" framespacing="0" border="1" bordercolor="#009966">
			<frame name="header" src="header.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />">
			<frame name="scheda" src="scheda.jsp?id_biblioteca=<c:out value="${param.id_biblioteca}" />&page=<c:out value="${param.page}" />&torna=<c:out value="${requestScope.queryStringEncoded}" />&mappe=<c:out value="${param.mappe}" />">
			<!-- frame name="footer" src="footer.jsp"-->
		</frameset>
	</c:otherwise>
	</c:choose>
</frameset>

</html>