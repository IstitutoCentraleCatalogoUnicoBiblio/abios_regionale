<%@page import="it.inera.solr.configuration.Configuration"%>
<%
String googlekey = Configuration.getGmapKey();
%>
<script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=<%=googlekey%>" type="text/javascript"></script>