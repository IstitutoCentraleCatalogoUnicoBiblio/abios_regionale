<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.solr.common.SolrDocument"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.solr.common.SolrDocumentList"%>
<%@page import="org.apache.solr.client.solrj.response.QueryResponse"%>
<%@page import="it.inera.solr.utils.SolrUtils"%>
<%@page import="it.inera.solr.configuration.Constants"%>
<%@ taglib uri="http://solr.inera.it/taglibs/solrtaglib" prefix="solr" %>
<solr:SolrSearch
	core="provincia"
	formFields="id_provincia"
	returnFields="*"
	sortField="id_regione asc,provincia asc"
	start="0" 
	row="1000"
	debug="false" 
	/>

<%
Object solrResponseObj = pageContext.getAttribute(Constants.SOLR_RESPONSE);
String solrResponse = (solrResponseObj != null) ? (String) solrResponseObj : "";
QueryResponse queryResponse = SolrUtils.getQueryResponse(solrResponse);
SolrDocumentList docList = queryResponse.getResults();
Iterator<SolrDocument> iter = docList.iterator();

// placeholder per la stampa del JS
StringBuffer VUOTO = new StringBuffer("var VUOTO = new Array(\"\",\"\"");
LinkedHashMap<String, StringBuffer> regioniProvince = new LinkedHashMap<String, StringBuffer>();


Vector<String> provinceVector = new Vector<String>(); 
while (iter.hasNext()) {
	SolrDocument provincia = (SolrDocument) iter.next();
	//provincia.getFieldValue("id_provincia");
	String provinciaNome = (String) provincia.getFieldValue("provincia");
	//provincia.getFieldValue("id_regione");
	String regioneNome = (String) provincia.getFieldValue("regione");
	regioneNome = StringUtils.replace(regioneNome, " ", "_");
	regioneNome = StringUtils.replace(regioneNome, "'", "_");
	
	provinceVector.add(provinciaNome);
	
	StringBuffer tmpRegione = regioniProvince.get(regioneNome);
	if (tmpRegione == null) {
		tmpRegione = new StringBuffer("var " + regioneNome + " = new Array(ALL,\"\"");
		regioniProvince.put(regioneNome, tmpRegione);
	}
	tmpRegione.append(",\"").append(provinciaNome.toUpperCase()).append("\"");
	tmpRegione.append(",\"").append(provinciaNome.toUpperCase()).append("\"");
}


//ARRAY VUOTO
Collections.sort(provinceVector);
for (int i = 0; i < provinceVector.size(); i++) {
	VUOTO.append(",\"").append(provinceVector.get(i).toUpperCase()).append("\"");
	VUOTO.append(",\"").append(provinceVector.get(i).toUpperCase()).append("\"");
}


%>
//Regioni Italia: 20, province: 110
var ALL = "Tutte le province";

<%=VUOTO.toString()%>);
<%
Iterator<String> iterRegioni = regioniProvince.keySet().iterator();
while (iterRegioni.hasNext()) {
	String tmpName = iterRegioni.next();
	StringBuffer tmp = regioniProvince.get(tmpName);
	out.write(tmp.toString().concat(");").concat("\n"));
}
%>

function calcolo(onChange) {

	var index =  document.formOptions.regione.selectedIndex;
	var indexPro =  document.formOptions.provincia.selectedIndex; // r.eschini x ricaricare la provinca con Restringi ricerca
	// Inizializza la select
	document.formOptions.provincia.options.length = 0;
	
	var regione = new Array();

	switch (index)	{
		case  0: regione = VUOTO;		break;
		<%
		int idx = 1;
		iterRegioni = regioniProvince.keySet().iterator();
		while (iterRegioni.hasNext()) {
			String tmpName = iterRegioni.next();
			out.write("\t\tcase  " + idx + ": regione = " + tmpName +";		break;\n");
			idx++;
		}
		%>
		default: alert ("Attenzione! La regione selezionata e' inesistente"); break;
	}
	var counter=0;
	for(i = 0; i < regione.length; i+=2)
	{
		// ------------------------------------------------Option:--TESTO-------VALUE
		document.formOptions.provincia.options[counter] = new Option(regione[i], regione[i+1]);
		var paramsProv = '<%=StringEscapeUtils.escapeJavaScript(request.getParameter("provincia"))%>';
		if (regione[i+1] == paramsProv) {
			indexPro = i/2;
		}
		
		counter++;
	}
	if (onChange) indexPro = 0; // r.eschini x ricaricare la provinca con Restringi ricerca
	// Inizializza indice select delle province
	document.formOptions.provincia.selectedIndex = indexPro;	// r.eschini x ricaricare la provinca con Restringi ricerca
	// r.eschini x ricaricare la provinca con Restringi ricerca modificati anche templates\iccu2\internet\avanzata.html e templates\iccu2\internet\semplice.html
};
//r.eschini per ripristinare tutte le regioni/province
function cancella()
{
	var regioneVuota = new Array();
	regioneVuota = VUOTO;
	var counter=0;
	for(i = 0; i < regioneVuota.length; i+=2)
	{
		// ------------------------------------------------Option:--TESTO-------VALUE
		document.formOptions.provincia.options[counter] = new Option(regioneVuota[i], regioneVuota[i+1]);
		counter++;
	}
	if (document.formOptions.regione) document.formOptions.regione.selectedIndex = 0;
	if (document.formOptions.comune) document.formOptions.comune.value = '';
	if (document.formOptions.denominazione_forsearch) document.formOptions.denominazione_forsearch.value = '';
	if (document.formOptions.elements['denominazione_forsearch:tipo']) document.formOptions.elements['denominazione_forsearch:tipo'].selectedIndex = 0;
	if (document.formOptions.descrizione_ufficiale) document.formOptions.descrizione_ufficiale.value = '';
	if (document.formOptions.elements['descrizione_ufficiale:tipo']) document.formOptions.elements['descrizione_ufficiale:tipo'].selectedIndex = 0;
	if (document.formOptions.ente_denominazione) document.formOptions.ente_denominazione.value = '';
	if (document.formOptions.elements['ente_denominazione:tipo']) document.formOptions.elements['ente_denominazione:tipo'].selectedIndex = 0;
	if (document.formOptions.id_tipologia_amministrativa) {
		document.formOptions.id_tipologia_amministrativa.selectedIndex = 0;
		document.formOptions.tipologia_amministrativa.value = '';
	}
	if (document.formOptions.tipologia_funzionale) document.formOptions.tipologia_funzionale.selectedIndex = 0;
	if (document.formOptions.destinazioni_sociali) document.formOptions.destinazioni_sociali.selectedIndex = 0;
	
	if (document.formOptions.accesso_handicap) document.formOptions.accesso_handicap.checked = false;
	for (i=0; i<document.formOptions.accesso_riservato.length; i++) 
	{
		document.formOptions.accesso_riservato[i].checked = false;
	}
	if (document.formOptions.orario_ufficiale_fasce) document.formOptions.orario_ufficiale_fasce.selectedIndex = 0;
	
	//DEWEY
	if (document.formOptions.codice_dewey) document.formOptions.codice_dewey.value = '';
	if (document.formOptions.DEWEYDA) document.formOptions.DEWEYDA.value = '';
	if (document.formOptions.DEWEYA) document.formOptions.DEWEYA.value = '';
	if (document.formOptions.descrizione_libera) document.formOptions.descrizione_libera.value = '';
	if (document.formOptions.elements['descrizione_libera:tipo']) document.formOptions.elements['descrizione_libera:tipo'].selectedIndex = 0;

	if (document.formOptions.sistemi_biblioteche) document.formOptions.sistemi_biblioteche.value = '';
	if (document.formOptions.sezioni_speciali) document.formOptions.sezioni_speciali.value = '';
	
	//PATRIMONIO - CATALOGHI - FONDI
	if (document.formOptions.patrimonio_librario_descr) document.formOptions.patrimonio_librario_descr.value = '';
	if (document.formOptions.catalogo_generale_tipo) document.formOptions.catalogo_generale_tipo.selectedIndex = 0;
	if (document.formOptions.cataloghi_speciali_descr) document.formOptions.cataloghi_speciali_descr.value = '';
	if (document.formOptions.cataloghi_collettivi_descr) document.formOptions.cataloghi_collettivi_descr.value = '';
	if (document.formOptions.fondi_speciali_denominazione) document.formOptions.fondi_speciali_denominazione.value = '';
	if (document.formOptions.elements['fondi_speciali_denominazione:tipo']) document.formOptions.elements['fondi_speciali_denominazione:tipo'].selectedIndex = 0;
	if (document.formOptions.fondi_speciali_descrizione) document.formOptions.fondi_speciali_descrizione.value = '';
	if (document.formOptions.elements['fondi_speciali_descrizione:tipo']) document.formOptions.elements['fondi_speciali_descrizione:tipo'].selectedIndex = 0;
	if (document.formOptions.spogli_bibliografici) document.formOptions.spogli_bibliografici.selectedIndex = 0;
	
	if (document.formOptions.gestisce_servizio_bibliografico_interno) document.formOptions.gestisce_servizio_bibliografico_interno.checked = false;
	if (document.formOptions.gestisce_servizio_bibliografico_esterno) document.formOptions.gestisce_servizio_bibliografico_esterno.checked = false;
	if (document.formOptions.riproduzioni) document.formOptions.riproduzioni.checked = false;
	if (document.formOptions.posti_internet) document.formOptions.posti_internet.checked = false;
	if (document.formOptions.prestito_locale) document.formOptions.prestito_locale.checked = false;
	if (document.formOptions.prestito_interbiblio_nazionale) document.formOptions.prestito_interbiblio_nazionale.checked = false;
	if (document.formOptions.prestito_interbiblio_internazionale) document.formOptions.prestito_interbiblio_internazionale.checked = false;
	
	//CODICI
	if (document.formOptions.sbn) document.formOptions.sbn.value = '';
	if (document.formOptions.rism) document.formOptions.rism.value = '';
	if (document.formOptions.acnp) document.formOptions.acnp.value = '';
	
}
<%-- 
<solr:SolrDisplay xsl="regioniItalia" />
--%>