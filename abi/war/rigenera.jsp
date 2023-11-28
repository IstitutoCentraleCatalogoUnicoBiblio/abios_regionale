<%@ include file="header.jsp" %>
<%--
  ~ Author Inera srl https://www.inera.it
  ~ Copyright (C) 2023  Inera srl https://www.inera.it/
  ~
  ~ European Union Public Licence V. 1.2
  ~ EUPL (c) the European Community 2017
  ~
  ~ This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
  ~ Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
  ~ The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
  ~ Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
  ~
  ~ You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
  --%>

<div id="contenitore">
<div id="contenuto">
<h1>Rigenerazione password</h1>
<div class="center" style="text-align: center;">
<form action="rigeneraPassword" method="post" name="rigeneraPassword">
<table style="margin: 5% auto;">
	<tr>
		<td><strong>Nome Utente:</strong></td>
		<td><input type="text" name="username"></input></td>
	</tr>
	<tr>
		<td></td>
		<td style="text-align: right;"><input type="submit" value="Rigenera" /></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
		<%
			Object noInsertObj = request.getAttribute("noInsert");
			String noInsert = new String();
			if (noInsertObj != null) {
				noInsert = noInsertObj.toString();
			}
			Object userNotFoundObj = request.getAttribute("userNotFound");
			String userNotFound = new String();
			if (userNotFoundObj != null) {
				userNotFound = userNotFoundObj.toString();
			}
		%> <%
		if ("true".equalsIgnoreCase(noInsert)) {
		%> <b style="color: red; font-size: 120%;">Username non inserito</b> <%}%> <%
		if ("false".equalsIgnoreCase(noInsert)) {
		%> <b style="color: green; font-size: 120%;">Codice rigenerazione inviato all'email dell'utente!</b> <%}%> <%
		if ("true".equalsIgnoreCase(userNotFound)) {
		%> <b style="color: red; font-size: 120%;">Username non trovato</b> <%}%>
		</td>
	</tr>
</table>
</form>
<table style="margin: 5% auto;">
	<tr>
		<td colspan="2" align="center"><b style="font-size: 100%;"><a href="login.jsp" target="blank">Torna alla pagina di login</a></b></td>
	</tr>
</table>
</div>
<center>
Per ulteriori informazioni visita il sito <a href="http://www.iccu.sbn.it/genera.jsp?id=78" target="blank">Anagrafe delle biblioteche italiane</a> oppure scrivi a <a onclick="document.location.href='mailto:anagrafe@iccu.sbn.it'">anagrafe@iccu.sbn.it</a>
</center>
</div>
</div>
<%@ include file="footer.jsp" %>