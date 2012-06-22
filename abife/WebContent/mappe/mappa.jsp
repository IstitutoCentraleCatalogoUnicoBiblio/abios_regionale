<html>
<head>
<title>Mappa</title>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<jsp:include page="googlekey.jsp"></jsp:include>
<script language="javascript" src="<c:url value="/script/jquery-1.2.6.pack.js" />"></script>
<script language="javascript" src="<c:url value="/script/gmap/commons.js" />"></script>
<script language="javascript" src="<c:url value="/script/gmap/abimaps.js" />"></script>
<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css"></link>
<link rel="stylesheet" href="<c:url value="/mappe/style.css" />" type="text/css"></link>

<jsp:include page="jsglobalparams.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		loadViewMap();

	});
</script>
<style>
#googledir {
	font-size: 10px;
}
</style>
</head>
<body>

<table border=0 style="width: 100%;">
	<tr>
		<td colspan="3">
			<h1 style="font-weight: bold; width:100%; font-size: 18; color: #f2b117; font-family: verdana, arial, helvetica, sans-serif" >Ricerca Geografica Biblioteche</h1>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<div class="geocrumbs" id="geocrumbs"></div>			
		</td>
	</tr>
	<tr>
		<td colspan="3" style="width: 100%;">
			<div id="drivingdirections" style="display:none;">
				<jsp:include page="percorsi.html"></jsp:include>
			</div>			
		</td>
		
	</tr>
	<tr>		
		<td colspan="2" style="border-top: 1px solid #597291">
			<div id="map" style="width: 550px; height: 350px"></div>
			<div id="load_wait"><img src="<c:url value="/images/ajax-loader.gif" />" /></div>			
			<div id="label"></div>
			<div id="listabiblio" style="width: 100%; height: 120px; overflow: auto;">
			</div>
		</td>
		<td style="width: 850px;" valign="top">
			<div id="dirpanel" style="height: 450px; overflow: auto;">
			</div>
		</td>
	</tr>
</table>

<div id="idbubble" style="display: none;">
	<div class="bubble">
		<ul class="bubble-geo">
	    	<li>lat: %lat% n</li>
			<li>|</li>
			<li>lon: %lng% e</li>
		</ul>
		<div class="bubble-content">
	    	%bubble-content%
	    	<p class="location">%indirizzo%</p>
	  	</div>
	  	<div class="bubble-footer">
			%bubble-footer%
		</div>
	</div>
</div>
<div id="idbubble-footer" style="display: none;">
	<a href="%linkanagrafe%" class="bubble-link">Vai all'Anagrafe &raquo;</a>
</div>
<div id="idbubble-content-link" style="display: none;">
	<h3><a href="%linkanagrafe%">%denominazione%</a></h3>	
</div>
<div id="idbubble-content-nolink" style="display: none;">
	<h3>%denominazione%</h3>	
</div>


<div id="identrymenu" style="display: none;">
	<div class="%css%">
		<table style="font-size: 12px;">
			<tr>
				<td>
					%index%
				</td>
				<td width="100%">
					<a href="javascript:clickMarker(%mindex%)">
					<b>%denominazione%</b></a><br>
					%indirizzo%
				</td>
				<td width="25">
					<a href="mappa.jsp?id_biblioteca=%id_biblioteca%%fromana%" target="_self">
						<img alt="Visualizza solo questa biblioteca" title="Visualizza solo questa biblioteca" src="<c:url value="/images/b.gif" />" align="left" border="0" >
					</a>
				</td>
				<td width="25">
					<a href="javascript:clickDirection(%mindex%);">
						<img alt="Calcola percorso" title="Calcola percorso" src="<c:url value="/images/p.gif" />" align="left" border="0" >
					</a>
				</td>
				<td width="25">
					%linkanagrafeentrymenu%
				</td>
			</tr>
		</table>			 		
	</div>
</div>

<div id="linkanagrafeentrymenu" style="display: none;">
	<a href="%linkanagrafe%" target="_self">
		<img alt="Anagrafe" title="Anagrafe" src="<c:url value="/images/a.gif" />" align="left" border="0">
	</a>
</div>
</body>
</html>