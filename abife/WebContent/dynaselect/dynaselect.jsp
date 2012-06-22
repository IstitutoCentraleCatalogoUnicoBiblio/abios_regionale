<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<c:if test="${param.select == 'regione'}">
	<solr:SolrSearch core="regione" formFields="" formNoEscapeValues="*" sortField="regione asc" returnFields="regione" start="0" row="50" />
	<solr:SolrDisplay xsl="select_regioni" parameters="regione_select:${param.regione}"/>		
</c:if>
<c:if test="${param.select == 'destinazioni_sociali_tipo'}">
	<solr:SolrSearch core="destinazioni_sociali_tipo" formFields="id_destinazioni_sociali" formNoEscapeValues="*" sortField="id_destinazioni_sociali asc" returnFields="descrizione" start="0" row="50" />
	<solr:SolrDisplay xsl="select_destinazione_sociale" parameters="dest_soc_select:${param.destinazioni_sociali}" />
</c:if>
<c:if test="${param.select == 'ente_tipologia_amministrativa'}">
	<solr:SolrSearch core="ente_tipologia_amministrativa" formFields="id_ente_tipologia_amministrativa" formNoEscapeValues="*" sortField="id_ente_tipologia_amministrativa asc" returnFields="id_ente_tipologia_amministrativa,descrizione" start="0" row="50" />
	<solr:SolrDisplay xsl="select_ente_tipologia_amministrativa" parameters="tip_amm_select:${param.id_tipologia_amministrativa}" />
</c:if>
<c:if test="${param.select == 'catalogo_generale_tipo'}">
	<solr:SolrSearch core="catalogo_generale_tipo" formFields="id_catalogo_generale_tipo" formNoEscapeValues="*" sortField="id_catalogo_generale_tipo asc" returnFields="descrizione" start="0" row="50" />
	<solr:SolrDisplay xsl="select_catalogo_generale_tipo" parameters="cat_gen_select:${param.catalogo_generale_tipo}"/>
</c:if>
<c:if test="${param.select == 'spogli_bibliografici'}">
	<solr:SolrSearch core="spogli_bibliografici" formFields="id_spogli_bibliografici" formNoEscapeValues="*" sortField="descrizione_bibliografica asc" returnFields="descrizione_bibliografica" start="0" row="2000" />
	<solr:SolrDisplay xsl="select_spogli_bibliografici" parameters="spogli_select:${param.spogli_bibliografici}"/>
</c:if>
<c:if test="${param.select == 'tipologia_funzionale'}">
	<solr:SolrSearch core="tipologia_funzionale" formFields="id_tipologia_funzionale" formNoEscapeValues="*" sortField="id_tipologia_funzionale asc" returnFields="id_tipologia_funzionale,descrizione" start="0" row="50" />
	<solr:SolrDisplay xsl="select_tipologia_funzionale" parameters="tip_funz_select:${param.tipologia_funzionale}"/>
</c:if>