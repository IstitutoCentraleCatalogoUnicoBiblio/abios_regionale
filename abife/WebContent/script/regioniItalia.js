//<!---
// Regioni Italia: 20, province: 110
var ALL = "Tutte le province";

var VUOTO = new Array("","","AGRIGENTO","AGRIGENTO","ALESSANDRIA","ALESSANDRIA","ANCONA","ANCONA","AOSTA","AOSTA","AREZZO","AREZZO","ASCOLI PICENO","ASCOLI PICENO","ASTI","ASTI","AVELLINO","AVELLINO","BARI","BARI","BARLETTA-ANDRIA-TRANI","BARLETTA-ANDRIA-TRANI","BELLUNO","BELLUNO","BENEVENTO","BENEVENTO","BERGAMO","BERGAMO","BIELLA","BIELLA","BOLOGNA","BOLOGNA","BOLZANO","BOLZANO","BRESCIA","BRESCIA","BRINDISI","BRINDISI","CAGLIARI","CAGLIARI","CALTANISSETTA","CALTANISSETTA","CAMPOBASSO","CAMPOBASSO","CARBONIA-IGLESIAS","CARBONIA-IGLESIAS","CASERTA","CASERTA","CATANIA","CATANIA","CATANZARO","CATANZARO","CHIETI","CHIETI","COMO","COMO","COSENZA","COSENZA","CREMONA","CREMONA","CROTONE","CROTONE","CUNEO","CUNEO","ENNA","ENNA","FERMO","FERMO","FERRARA","FERRARA","FIRENZE","FIRENZE","FOGGIA","FOGGIA","FORLI'-CESENA","FORLI'-CESENA","FROSINONE","FROSINONE","GENOVA","GENOVA","GORIZIA","GORIZIA","GROSSETO","GROSSETO","IMPERIA","IMPERIA","ISERNIA","ISERNIA","L'AQUILA","L'AQUILA","LA SPEZIA","LA SPEZIA","LATINA","LATINA","LECCE","LECCE","LECCO","LECCO","LIVORNO","LIVORNO","LODI","LODI","LUCCA","LUCCA","MACERATA","MACERATA","MANTOVA","MANTOVA","MASSA CARRARA","MASSA CARRARA","MATERA","MATERA","MEDIO CAMPIDANO","MEDIO CAMPIDANO","MESSINA","MESSINA","MILANO","MILANO","MODENA","MODENA","MONZA E DELLA BRIANZA","MONZA E DELLA BRIANZA","NAPOLI","NAPOLI","NOVARA","NOVARA","NUORO","NUORO","OGLIASTRA","OGLIASTRA","OLBIA-TEMPIO","OLBIA-TEMPIO","ORISTANO","ORISTANO","PADOVA","PADOVA","PALERMO","PALERMO","PARMA","PARMA","PAVIA","PAVIA","PERUGIA","PERUGIA","PESARO E URBINO","PESARO E URBINO","PESCARA","PESCARA","PIACENZA","PIACENZA","PISA","PISA","PISTOIA","PISTOIA","PORDENONE","PORDENONE","POTENZA","POTENZA","PRATO","PRATO","RAGUSA","RAGUSA","RAVENNA","RAVENNA","REGGIO CALABRIA","REGGIO CALABRIA","REGGIO EMILIA","REGGIO EMILIA","RIETI","RIETI","RIMINI","RIMINI","ROMA","ROMA","ROVIGO","ROVIGO","SALERNO","SALERNO","SASSARI","SASSARI","SAVONA","SAVONA","SIENA","SIENA","SIRACUSA","SIRACUSA","SONDRIO","SONDRIO","TARANTO","TARANTO","TERAMO","TERAMO","TERNI","TERNI","TORINO","TORINO","TRAPANI","TRAPANI","TRENTO","TRENTO","TREVISO","TREVISO","TRIESTE","TRIESTE","UDINE","UDINE","VARESE","VARESE","VENEZIA","VENEZIA","VERBANIA","VERBANIA","VERCELLI","VERCELLI","VERONA","VERONA","VIBO VALENTIA","VIBO VALENTIA","VICENZA","VICENZA","VITERBO","VITERBO");
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
		case  1: regione = ABRUZZO;		break;
		case  2: regione = BASILICATA;		break;
		case  3: regione = CALABRIA;		break;
		case  4: regione = CAMPANIA;		break;
		case  5: regione = EMILIA_ROMAGNA;		break;
		case  6: regione = FRIULI_VENEZIA_GIULIA;		break;
		case  7: regione = LAZIO;		break;
		case  8: regione = LIGURIA;		break;
		case  9: regione = LOMBARDIA;		break;
		case  10: regione = MARCHE;		break;
		case  11: regione = MOLISE;		break;
		case  12: regione = PIEMONTE;		break;
		case  13: regione = PUGLIA;		break;
		case  14: regione = SARDEGNA;		break;
		case  15: regione = SICILIA;		break;
		case  16: regione = TOSCANA;		break;
		case  17: regione = TRENTINO_ALTO_ADIGE;		break;
		case  18: regione = UMBRIA;		break;
		case  19: regione = VALLE_D_AOSTA;		break;
		case  20: regione = VENETO;		break;
		default: alert ("Attenzione! La regione selezionata e' inesistente"); break;
	}
	var counter=0;
	for(i = 0; i < regione.length; i+=2)
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
	for(i = 0; i < regioneVuota.length; i+=2)
	{
		// ------------------------------------------------Option:--TESTO-------VALUE
		document.formOptions.provincia.options[counter] = new Option(regioneVuota[i], regioneVuota[i+1]);
		counter++;
	}
}
// --->