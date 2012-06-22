/*
 * Apertura finestre popup 
 */

/**
 * Apre finestra mappa per internet
 * @param id id biblioteca
 * @return
 */
function viewMap(id_biblioteca) {
	var urlMappaBiblio = ctxAbife + "mappe/mappa.jsp?id_biblioteca=" + id_biblioteca + "&fromana=true";
	var hWind = window.open(urlMappaBiblio,"GMappa","width=850,height=600,resizable=yes,scrollbars=yes");
    if ((document.window != null) && (!hWind.opener)) hWind.opener = document.window;
}


function viewListaComuneMap(id_comune) {
	var urlMappaComune = ctxAbife + "mappe/mappa.jsp?id_comune=" + id_comune;
	var hWind = window.open(urlMappaComune,"GMappa","width=850,height=600,resizable=yes,scrollbars=yes");
    if ((document.window != null) && (!hWind.opener)) hWind.opener = document.window;
}

/*
 * Funzioni comuni per framework xml/gmap
 */


function getField(obj, field) {
	if (obj.getElementsByTagName(field)[0] && obj.getElementsByTagName(field)[0].firstChild) {
		return obj.getElementsByTagName(field)[0].firstChild.nodeValue;	
	} else {
		return;
	}
}

function showLoading (show) {
	if (show) {
		$("#load_wait").css("display","block"); 
	} else {
		$("#load_wait").css("display","none");
	}
}

function replaceAll(input, searchValue, replaceValue) {
	while (input.indexOf(searchValue) != -1) {
		input = input.replace(searchValue, replaceValue);
	}
	return input;
}

function gup(name) {
	  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	  var regexS = "[\\?&]"+name+"=([^&#]*)";
	  var regex = new RegExp( regexS );
	  var results = regex.exec( window.location.href );
	  if( results == null )
	    return "";
	  else
	    return results[1];
	}