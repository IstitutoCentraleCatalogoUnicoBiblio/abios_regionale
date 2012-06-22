<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="text" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">
//Regioni Italia: 20, province: 110
var ALL = "Tutte le province";
var VUOTO = new Array("",""<xsl:for-each select="//result/doc">,"<xsl:value-of select="translate(str[@name = 'provincia'],'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />"</xsl:for-each>);

var ABRUZZO = new Array(ALL,"","CHIETI","CHIETI","L'AQUILA","L'AQUILA","PESCARA","PESCARA","TERAMO","TERAMO");
var BASILICATA = new Array(ALL,"","MATERA","MATERA","POTENZA","POTENZA");
var CALABRIA = new Array(ALL,"","CATANZARO","CATANZARO","COSENZA","COSENZA","CROTONE","CROTONE","REGGIO CALABRIA","REGGIO CALABRIA","VIBO VALENTIA","VIBO VALENTIA");
var CAMPANIA = new Array(ALL,"","AVELLINO","AVELLINO","BENEVENTO","BENEVENTO","CASERTA","CASERTA","NAPOLI","NAPOLI","SALERNO","SALERNO");
var EMILIA_ROMAGNA = new Array(ALL,"","BOLOGNA","BOLOGNA","FERRARA","FERRARA","FORLI'-CESENA","FORLI'-CESENA","MODENA","MODENA","PARMA","PARMA","PIACENZA","PIACENZA","RAVENNA","RAVENNA","REGGIO EMILIA","REGGIO EMILIA","RIMINI","RIMINI");
var FRIULI_VENEZIA_GIULIA = new Array(ALL,"","GORIZIA","GORIZIA","PORDENONE","PORDENONE","TRIESTE","TRIESTE","UDINE","UDINE");
var LAZIO = new Array(ALL,"","FROSINONE","FROSINONE","LATINA","LATINA","RIETI","RIETI","ROMA","ROMA","VITERBO","VITERBO");
var LIGURIA = new Array(ALL,"","GENOVA","GENOVA","IMPERIA","IMPERIA","LA SPEZIA","LA SPEZIA","SAVONA","SAVONA");
var LOMBARDIA = new Array(ALL,"","BERGAMO","BERGAMO","BRESCIA","BRESCIA","COMO","COMO","CREMONA","CREMONA","LECCO","LECCO","LODI","LODI","MANTOVA","MANTOVA","MILANO","MILANO","MONZA E DELLA BRIANZA","MONZA E DELLA BRIANZA","PAVIA","PAVIA","SONDRIO","SONDRIO","VARESE","VARESE");
var MARCHE = new Array(ALL,"","ANCONA","ANCONA","ASCOLI PICENO","ASCOLI PICENO","FERMO","FERMO","MACERATA","MACERATA","PESARO E URBINO","PESARO E URBINO");
var MOLISE = new Array(ALL,"","CAMPOBASSO","CAMPOBASSO","ISERNIA","ISERNIA");
var PIEMONTE = new Array(ALL,"","ALESSANDRIA","ALESSANDRIA","ASTI","ASTI","BIELLA","BIELLA","CUNEO","CUNEO","NOVARA","NOVARA","TORINO","TORINO","VERBANIA","VERBANIA","VERCELLI","VERCELLI");
var PUGLIA = new Array(ALL,"","BARI","BARI","BARLETTA-ANDRIA-TRANI","BARLETTA-ANDRIA-TRANI","BRINDISI","BRINDISI","FOGGIA","FOGGIA","LECCE","LECCE","TARANTO","TARANTO");
var SARDEGNA = new Array(ALL,"","CAGLIARI","CAGLIARI","CARBONIA-IGLESIAS","CARBONIA-IGLESIAS","MEDIO CAMPIDANO","MEDIO CAMPIDANO","NUORO","NUORO","OGLIASTRA","OGLIASTRA","OLBIA-TEMPIO","OLBIA-TEMPIO","ORISTANO","ORISTANO","SASSARI","SASSARI");
var SICILIA = new Array(ALL,"","AGRIGENTO","AGRIGENTO","CALTANISSETTA","CALTANISSETTA","CATANIA","CATANIA","ENNA","ENNA","MESSINA","MESSINA","PALERMO","PALERMO","RAGUSA","RAGUSA","SIRACUSA","SIRACUSA","TRAPANI","TRAPANI");
var TOSCANA = new Array(ALL,"","AREZZO","AREZZO","FIRENZE","FIRENZE","GROSSETO","GROSSETO","LIVORNO","LIVORNO","LUCCA","LUCCA","MASSA CARRARA","MASSA CARRARA","PISA","PISA","PISTOIA","PISTOIA","PRATO","PRATO","SIENA","SIENA");
var TRENTINO_ALTO_ADIGE = new Array(ALL,"","BOLZANO","BOLZANO","TRENTO","TRENTO");
var UMBRIA = new Array(ALL,"","PERUGIA","PERUGIA","TERNI","TERNI");
var VALLE_D_AOSTA = new Array(ALL,"","AOSTA","AOSTA");
var VENETO = new Array(ALL,"","BELLUNO","BELLUNO","PADOVA","PADOVA","ROVIGO","ROVIGO","TREVISO","TREVISO","VENEZIA","VENEZIA","VERONA","VERONA","VICENZA","VICENZA");


function calcolo(onChange) {
	var index =  document.formOptions.regione.selectedIndex;
	var indexPro =  document.formOptions.provincia.selectedIndex; // r.eschini x ricaricare la provinca con Restringi ricerca
	// Inizializza la select
	document.formOptions.provincia.options.length = 0;

	var regione = new Array();

	switch (index)	{
		case  0: regione = VUOTO;		break;
		<xsl:variable name="tmpRegioneId" select="-1" />
		<xsl:for-each select="//result/doc">
			<xsl:if test="int[@name = 'id_regione'] != $tmpRegioneId">
			case  <xsl:value-of select="int[@name = 'id_regione']" />: regione = <xsl:value-of select="translate(str[@name = 'regione'],'abcdefghijklmnopqrstuvwxyz ','ABCDEFGHIJKLMNOPQRSTUVWXYZ_')" />;		break;
			<xsl:variable name="tmpRegioneId" select="int[@name = 'id_regione']" />
			</xsl:if>
		</xsl:for-each>
		default: alert ("Attenzione! La regione selezionata e' inesistente"); break;
	}
	var counter=0;
	for(i = 0; i &lt; regione.length; i+=2)
	{
		// ------------------------------------------------Option:--TESTO-------VALUE
		document.formOptions.provincia.options[counter] = new Option(regione[i], regione[i+1]);
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
	for(i = 0; i &lt; regioneVuota.length; i+=2)
	{
		// ------------------------------------------------Option:--TESTO-------VALUE
		document.formOptions.provincia.options[counter] = new Option(regioneVuota[i], regioneVuota[i+1]);
		counter++;
	}
}
	</xsl:template>
</xsl:stylesheet>