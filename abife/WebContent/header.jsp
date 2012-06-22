<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,isil_stato,isil_numero,isil_provincia,comune"/>
<solr:SolrDisplay xsl="header_scheda" />
</body>