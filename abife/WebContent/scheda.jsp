<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css">
	<script language="javascript" src="<c:url value="/script/gmap/commons.js" />"></script>
</head>
<body>

<jsp:include page="mappe/jsglobalparams.jsp"></jsp:include>

<%
String torna = request.getParameter("torna");
String queryStringEncoded = URLEncoder.encode(torna, "UTF-8");
request.setAttribute("queryStringEncoded", queryStringEncoded);
%>
<c:choose>
	<c:when test="${param.page=='zero'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,id_comune,denominazione_ufficiale,codice_fiscale,partita_iva,indirizzo,frazione,cap,comune,provincia,regione,isil_stato,isil_provincia,isil_numero,contatti,sbn,rism,acnp,cei,cmbs,stato_catalogazione,denominazioni_precedenti,denominazioni_alternative,catalogazione_data_modifica,catalogazione_data_censimento,latitudine,longitudine"/>
		<solr:SolrDisplay xsl="lista_anagrafica" />
	</c:when>
	<c:when test="${param.page=='tip'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,ente_denominazione,tipologia_amministrativa,autonomia_amministrativa,struttura_gerarchica_sovraordinata,tipologia_funzionale,data_fondazione,data_istituzione"/>
		<solr:SolrDisplay xsl="lista_tipologia" />
	</c:when>
	<c:when test="${param.page=='prof'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,edificio_monumentale,edificio_denominazione,edificio_appositamente_costruito,edificio_data_costruzione,sistemi_biblioteche"/>
		<solr:SolrDisplay xsl="lista_profilo_storico_sede" />
	</c:when>
	<c:when test="${param.page=='acc'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,accesso_riservato,accesso_handicap,accesso_limite_eta_min,accesso_limite_eta_max,accesso_modalita,destinazioni_sociali_display,regolamento"/>
		<solr:SolrDisplay xsl="lista_accesso_dest_soc" />
	</c:when>
	<c:when test="${param.page=='ora'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,orario_ufficiale,n_ore_settimanali,n_ore_settimanali_pom,n_settim_apertura"/>
		<solr:SolrDisplay xsl="lista_orario_uff" />
	</c:when>
	<c:when test="${param.page=='var'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,orario_chiusura,orario_variazioni_periodo,orario_variazioni"/>
		<solr:SolrDisplay xsl="lista_orario_var" />
	</c:when>
	<c:when test="${param.page=='patr'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,inventario_cartaceo,inventario_informatizzato,catalogo_topografico_cartaceo,catalogo_topografico_informatizzato,id_fondi_antichi_consistenza,dewey_libero_display,fondi_speciali_display,patrimonio_librario_display,patrimonio_librario_tipologia"/>
		<solr:SolrDisplay xsl="lista_patrimonio" parameters="torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='dew'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,dewey_display"/>
		<solr:SolrDisplay xsl="lista_descr_dewey" parameters="id_dewey:${param.id_dewey}|torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='catgen'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_generali_display"/>
		<solr:SolrDisplay xsl="lista_cat_gen" parameters="torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='curl' and param.type=='gen'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_generali_url"/>
		<solr:SolrDisplay xsl="lista_descr_url" parameters="type:${param.type}|id_cat_url:${param.id_cat_url}|torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='catspec'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_speciali_display"/>
		<solr:SolrDisplay xsl="lista_cat_spec" parameters="torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='curl' and param.type=='spec'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_speciali_materiale_url"/>
		<solr:SolrDisplay xsl="lista_descr_url" parameters="type:${param.type}|id_cat_url:${param.id_cat_url}|torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='catcoll'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_collettivi_display,cataloghi_collettivi_partecipa"/>
		<solr:SolrDisplay xsl="lista_cat_coll" parameters="torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='curl' and param.type=='coll'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,cataloghi_collettivi_materiale_url"/>
		<solr:SolrDisplay xsl="lista_descr_url" parameters="type:${param.type}|id_cat_url:${param.id_cat_url}|torna:${requestScope.queryStringEncoded}|mappe:${param.mappe}"/>
	</c:when>
	<c:when test="${param.page=='sez'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,sezioni_speciali,info_riproduzioni,gestisce_servizio_bibliografico_interno,gestisce_servizio_bibliografico_esterno,servizi_informazioni_bibliografiche_modalita,accesso_internet_pagamento,accesso_internet_tempo,accesso_internet_proxy"/>
		<solr:SolrDisplay xsl="lista_sezioni" />
	</c:when>
	<c:when test="${param.page=='prest'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,prestito_locale_display,materiale_escluso,utenti_ammessi,prestito_interbiblio_nazionale,prestito_interbiblio_internazionale,procedure_ill_automatizzate,prestito_interbibliotecario_display,sistemi_prestito_interbibliotecario"/>
		<solr:SolrDisplay xsl="lista_prestito" />
	</c:when>
	<c:when test="${param.page=='suppl'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,indicizzazione_classificata,indicizzazione_soggetto,norme_catalogazione,pubblicazioni,bibliografia,mq_totali,mq_pubblici,ml_magazzini,ml_aperti,posti_lettura,posti_video,posti_audio,posti_internet,utenti,utenti_iscritti,comunicazioni"/>
		<solr:SolrDisplay xsl="lista_supplementari" />
	</c:when>
	<c:when test="${param.page=='pers'}">
		<solr:SolrSearch formFields="id_biblioteca" row="1" returnFields="id_biblioteca,denominazione_ufficiale,comune,isil_stato,isil_provincia,isil_numero,personale_totale,personale_part_time,personale_temporaneo,personale_esterno,bilancio_uscite,bilancio_uscite_personale,bilancio_uscite_funzionamento,bilancio_uscite_incremento_patrimonio,bilancio_uscite_automazione,bilancio_uscite_varie,bilancio_entrate,deposito_legale"/>
		<solr:SolrDisplay xsl="lista_personale" />
	</c:when>
</c:choose>

</body>
</html>