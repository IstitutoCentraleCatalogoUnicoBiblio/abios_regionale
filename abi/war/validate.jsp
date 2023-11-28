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

	<%
		Object validObj = request.getAttribute("valid");
		String valid = new String();
		if (validObj != null) {
			valid = validObj.toString();
		}
		Object loginObj = request.getAttribute("login");
		String login = new String();
		if (loginObj != null) {
			login = loginObj.toString();
		}
		Object saveObj = request.getAttribute("save");
		String save = new String();
		if (saveObj != null) {
			save = saveObj.toString();
		}
		Object minlimitObj = request.getAttribute("minlimit");
		String minlimit = new String();
		if (minlimitObj != null) {
			minlimit = minlimitObj.toString();
		}
	%> <%
	if ("false".equalsIgnoreCase(valid)) {
	%>	<table style="margin: 5% auto;">
		<tr>
			<td colspan="2"><b style="color: red; font-size: 200%;">Codice non valido!</b></td>
		</tr>
		</table>
	<%}%> <%
	if ("true".equalsIgnoreCase(valid) || "false".equalsIgnoreCase(save) || "true".equalsIgnoreCase(minlimit)) {
	%>	<form action="salvaPassword" method="post" name="salvaPassword">
		<input type="hidden" name="userlogin" value="<%= login %>" />
		<table style="margin: 5% auto;">
		<tr>
			<td><strong>Nome Utente:</strong></td>
			<td><b style="font-size: 120%;"><%= login %></b></td>
		</tr>
		<tr>
			<td><strong>Nuova Password (min 6, max 12):</strong></td>
			<td><input type="password" name="password" maxlength="12"></input></td>
		</tr>
		<tr>
			<td><strong>Conferma Nuova Password:</strong></td>
			<td><input type="password" name="password2" maxlength="12"></input></td>
		</tr>
		<tr>
			<td></td>
			<td style="text-align: right;"><input type="submit" value="Salva nuova password" /></td>
		</tr>
		<%
		if("false".equalsIgnoreCase(save)) {
		%> <tr>
				<td colspan="2" style="text-align: center; color: red; font-size: 150%;">Password non corrispondenti!</td>
			</tr>
		<%}%> <%
		if("true".equalsIgnoreCase(minlimit)) {
		%> <tr>
				<td colspan="2" style="text-align: center; color: red; font-size: 150%;">La password deve contenere almeno 6 caratteri</td>
			</tr>
		<%}%>
		</table>
		</form>
	<%}%> <%
	if ("true".equalsIgnoreCase(save)) {
	%> <table style="margin: 5% auto;">
		<tr>
			<td colspan="2" align="center"><b style="color: green; font-size: 150%;">Nuova password salvata!</b></td>
		</tr>
		</table>
	<%}%>
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