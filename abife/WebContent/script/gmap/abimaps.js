var map = null;
var directions = null;
var panel = null;

var biblioteche = new Array();

function parseXML(xml) {
    if (window.ActiveXObject && window.GetObject) {
        var dom = new ActiveXObject('Microsoft.XMLDOM');
        dom.loadXML(xml);
        return dom;
    }
    if (window.DOMParser) return new DOMParser().parseFromString(xml, 'text/xml');
    throw new Error('No XML parser available');
}

jQuery.parseXML = function(xml) {
    return jQuery(parseXML(xml));
};

function loadViewMap() {
	if (!GBrowserIsCompatible()) {
		$('#map').html("<p id=\"warning\">I'm sorry, your browser is not compatible with Google Maps.</p><p id=\"warning\">Please visit <a href='http://local.google.com/support/bin/answer.py?answer=16532&topic=1499' target='_blank'>Google Maps</a> to view a list of compatible browsers.</p>");
		return;
	} else {
		map = new GMap2($("#map").get(0));  
		map.addControl(new GLargeMapControl());
		map.addControl(new GMapTypeControl());
		map.addControl(new GScaleControl());
		map.addControl(new GOverviewMapControl());
		map.enableScrollWheelZoom();
		map.setCenter(new GLatLng(42.05713822211053, 12.59619140625), 6); // italia
		
		
		
		loadDati();
		
		panel = $("#dirpanel").get(0);
		gdir = new GDirections(map, panel);
		
		GEvent.addListener(gdir, "load", onGDirectionsLoad);
		GEvent.addListener(gdir, "error", handleDirectionsErrors);
	}
}

function loadDati() {
	showLoading (true);
	var params;
	if (id_biblioteca) {
		params = { id_biblioteca: id_biblioteca};
	}
	if (id_comune) {
		params = { id_comune: id_comune};
	}
	
    jQuery.ajax({
            type: "POST",
            url: ctxAbife + "markersgenerator",
            data:params,
            success:function(data){
                    showLoading (false);
                    displayBiblioteche (data);
            },
            error:function (xhr, ajaxOptions, thrownError){
                    alert(xhr.statusText);
                    alert(xhr.status);
                    alert(thrownError);
                    alert(ajaxOptions);
            }
    });

}

function parseData (data) {
	var jqxml = $.parseXML(data);
	createGeoCrumbs(jqxml);
	var xml = jqxml.get(0);
	var xmlMarkers  = xml.getElementsByTagName('markers')[0];
    var xmlMarkerIterator = xmlMarkers.getElementsByTagName('marker');
    biblioteche = new Array();
    if (xmlMarkerIterator.length > 0) {
    	for (var index = 0; index < xmlMarkerIterator.length; index++) {
	    	var xmlMarker = xmlMarkerIterator[index];
			// parse news
			var biblioteca = new Biblioteca();
			biblioteca.id_biblioteca = getField(xmlMarker, 'id_bib');
			biblioteca.isil_pr = getField(xmlMarker, 'isil_pr');
			biblioteca.isil_nr = getField(xmlMarker, 'isil_nr');
			biblioteca.denominazione = getField(xmlMarker, 'denominazione');
			biblioteca.indirizzo = getField(xmlMarker, 'indirizzo');
			biblioteca.cap = getField(xmlMarker, 'cap');
			biblioteca.comune = getField(xmlMarker, 'comune');
			biblioteca.provincia = getField(xmlMarker, 'provincia');
			biblioteca.stato = getField(xmlMarker, 'stato');
			biblioteca.lat = getField(xmlMarker, 'lat');
			biblioteca.lng = getField(xmlMarker, 'lng');
			biblioteche[index] = biblioteca;
    	}
    }
}

function Biblioteca () {
	var id_biblioteca;
	var isil_pr;
	var isil_nr;
	var denominazione;
	var indirizzo;
	var cap;
	var comune;
	var provincia;
	var stato;
	var lat;
	var lng;
}



function displayBiblioteche (data) {
	var searchZoomLevel = 12;
	$('#listabiblio').empty();
	parseData (data);
	$('#label').html('<b>Biblioteche visualizzate: ' + biblioteche.length + '</b>');
	if (biblioteche.length > 0) {
		for (var index = 0; index < biblioteche.length; index++) {
			// creazione marker
			var marker = createMarker(ctxAbife + '/images/biblio_marker.gif', biblioteche[index]);
			createBiblioEntry(biblioteche[index], index);
		}
		if (id_biblioteca) {
			GEvent.trigger(biblioteche[biblioteche.length - 1]['marker'], "click");
			map.setZoom(searchZoomLevel);
		} else {
			map.setCenter(new GLatLng(biblioteche[biblioteche.length - 1].lat, biblioteche[biblioteche.length - 1].lng), searchZoomLevel); // italia
		}
	}
}

function createBiblioEntry(biblioteca, index) {
	var html = $("#identrymenu").html();
	
	if (index % 2 == 0) {
		html = replaceAll(html, "%css%", "biblioentrypair");
	} else {
		html = replaceAll(html, "%css%", "biblioentryodd");
	}
	// creazione link anagrafe
	var link = replaceAll(linkAnagrafe, "%ID_BIBLIOTECA%", biblioteca.id_biblioteca);
	// se vengo dalla scheda della biblio no metto il link all'anagrafe
	if (!gup("fromana")) {
		html = replaceAll(html, "%linkanagrafeentrymenu%", $("#linkanagrafeentrymenu").html());
		html = replaceAll(html, "%linkanagrafe%", link);
		html = replaceAll(html, "%fromana%", "");
	} else {
		html = replaceAll(html, "%linkanagrafeentrymenu%", "");
		html = replaceAll(html, "%fromana%", "&fromana=true");
	}
	
	html = replaceAll(html, "%denominazione%", biblioteca.denominazione);
	var indirizzo = "";
	if (biblioteca.indirizzo) {
		indirizzo += biblioteca.indirizzo + " ";
	}
	if (biblioteca.cap) {
		indirizzo += biblioteca.cap + " ";
	}
	if (biblioteca.comune) {
		indirizzo += biblioteca.comune + " ";
	}
	if (biblioteca.provincia) {
		indirizzo += "(" + biblioteca.provincia + ")";
	}
	
	html = replaceAll(html, "%id_biblioteca%", biblioteca.id_biblioteca);
	html = replaceAll(html, "%indirizzo%", indirizzo);
	html = replaceAll(html, "%mindex%", index);
	html = replaceAll(html, "%index%", index + 1);
	$(html).appendTo("#listabiblio");
}

function createGeoCrumbs($xml) {
	var $id_reg;
	var $label_reg;
	var $id_pro;
	var $label_pro;
	var $id_com;
	var $label_com;
	$xml.find('markers').find('params').each(function(){
		if ($(this).attr('name') == 'ID_REG') {$id_reg = $(this).attr('value');}
		if ($(this).attr('name') == 'LABEL_REG') {$label_reg = $(this).attr('value');}
		if ($(this).attr('name') == 'ID_PRO') {$id_pro = $(this).attr('value');}
		if ($(this).attr('name') == 'LABEL_PRO') {$label_pro = $(this).attr('value');}
		if ($(this).attr('name') == 'ID_COM') {$id_com = $(this).attr('value');}
		if ($(this).attr('name') == 'LABEL_COM') {$label_com = $(this).attr('value');}
	});
	
	var $pid_reg = '&ID_REG=' + $id_reg;
	var $plabel_reg = '&LABEL_REG=' + $label_reg;
	var $pid_pro = '&ID_PRO=' + $id_pro;
	var $plabel_pro = '&LABEL_PRO=' + $label_pro;
	var $pid_com = '&ID_COM=' + $id_com;
	var $plabel_com = '&LABEL_COM=' + $label_com;
	
	
	$('#geocrumbs').empty();

	$('<a style="font-size: 10px; " href="' + ctxAbife + 'mappe/regioni.jsp?id_biblioteca=*"><b>- ITALIA </b></a>').appendTo('#geocrumbs');
	$('<a style="font-size: 10px; " href="' + ctxAbife + 'mappe/province.jsp?id_biblioteca=*&regione=' + $label_reg + '"><b> -> ' + $label_reg + '</b></a>').appendTo('#geocrumbs');
	$('<a style="font-size: 10px; " href="' + ctxAbife + 'mappe/comuni.jsp?id_biblioteca=*&regione=' + $label_reg + '&provincia=' + $label_pro.toLowerCase() + '"><b> -> ' + $label_pro + '</b></a>').appendTo('#geocrumbs');
	
	$('<a style="font-size: 10px;" href="' + ctxAbife + 'mappe/mappa.jsp?id_comune=' + $id_com + '"><b> -> ' + $label_com + ' </b></a>').appendTo('#geocrumbs');
}

function createIcon(urlIcon) {
	var icon = new GIcon(); 
	icon.image = urlIcon;
	return icon;
}

function createMarker(iconName, biblioteca) {
	var myIcon = new GIcon(); 
	myIcon.image = iconName;
	myIcon.iconSize = new GSize(16, 16);
	myIcon.shadowSize = new GSize(37, 34);
	myIcon.iconAnchor = new GPoint(16, 33);
	myIcon.infoWindowAnchor = new GPoint(9, 2);
	myIcon.infoShadowAnchor = new GPoint(18, 25);
	var point = new GLatLng(parseFloat(biblioteca.lat), parseFloat(biblioteca.lng));
	var marker = new GMarker(point,  {icon: myIcon}); 
	// creazione fumetto google map
	var html = createHtmlMarker(biblioteca);
	GEvent.addListener(marker, "click", function() {
		marker.openInfoWindowHtml(html);
	});
	biblioteca['marker'] = marker;
	if (marker)	{
		map.addOverlay(marker);
		marker.show();
	}
	
	return marker;
}

function clickMarker(index) {
   GEvent.trigger(biblioteche[index]['marker'], "click");
}

function createHtmlMarker(biblioteca) {
	var html = $("#idbubble").html();
	
	// creazione link anagrafe
	var link = replaceAll(linkAnagrafe, "%ID_BIBLIOTECA%", id_biblioteca);
		
	// creazione fumetto
	if (!gup("fromana")) {
		html = replaceAll(html, "%bubble-content%", $("#idbubble-content-link").html());
		html = replaceAll(html, "%bubble-footer%", $("#idbubble-footer").html());
		html = replaceAll(html, "%linkanagrafe%", link);
	} else {
		html = replaceAll(html, "%bubble-content%", $("#idbubble-content-nolink").html());
		html = replaceAll(html, "%bubble-footer%", "");
	}
	
	html = replaceAll(html, "%lat%", biblioteca.lat.substring(0,4));
	html = replaceAll(html, "%lng%", biblioteca.lng.substring(0,4));
	html = replaceAll(html, "%denominazione%", biblioteca.denominazione);
	var indirizzo = "";
	if (biblioteca.indirizzo) {
		indirizzo += (" " + biblioteca.indirizzo); 
	}
	if (biblioteca.cap) {
		indirizzo += (" " + biblioteca.cap); 
	}
	if (biblioteca.comune) {
		indirizzo += (" " + biblioteca.comune);  
	}
	if (biblioteca.provincia) {
		indirizzo += (" (" + biblioteca.provincia + ")"); 
	}
	
	html = replaceAll(html, "%indirizzo%", indirizzo);
	return html;
}

function clickDirection(i) {
	if (!$("#idx").val()) {
		$('#drivingdirections').slideToggle('slow');
	} else if ($("#idx").val() == i) {
		$('#drivingdirections').slideToggle('slow');
	}
	$("#idx").val(i);
	var ind = "";
	if (biblioteche[i].indirizzo) {
		ind += biblioteche[i].indirizzo;
	}
	if (biblioteche[i].comune) {
		ind += " " + biblioteche[i].comune;
	}
	$("#aaddress").val(ind);
	
	$("#alat").val(biblioteche[i].lat);
	$("#alng").val(biblioteche[i].lng);
}

/*
 * TEST per problema milazzo
 */
var start;
var end;

function calcolaPercorso() {
	$("#dirpanel").empty();
	
	var fromAddress = $("#saddress").val();
	var toAddress = $("#aaddress").val();
	
	var toLat = $("#alat").val();
	var toLng = $("#alng").val();
	
	var geocoder = new GClientGeocoder();
	
	if (geocoder) {
		geocoder.getLatLng(
				fromAddress,
				function(point) {
					if (!point) {
						$("#dirpanel").html(fromAddress + " non è stato trovato.");
					} else {						
						start = point;
						
						geocoder.getLatLng(
								toAddress,
								function(point2) {
									if (!point2) {
										$("#dirpanel").html(toAddress + " non è stato trovato.");
									} else {						
										end = point2;
										
										
										var locale = "it";
										gdir.load("from: " + start.lat() + "," + start.lng()  + " to: " + toLat + "," + toLng, { "locale": locale });

									}
								}
						);
						
						
						
					}
				}
		);
	}
	
	
}

function handleDirectionsErrors() {
   if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)
   		$("#dirpanel").html("Uno degli indirizzi specificati non è stato trovato.");
   else 
	   $("#dirpanel").html("Errore del server di google.");
}

function onGDirectionsLoad(){ 
    // Use this function to access information about the latest load()
    // results.

    // e.g.
// document.getElementById("getStatus").innerHTML = gdir.getStatus().code;
// and yada yada yada...
}
