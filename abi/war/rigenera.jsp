<%@ include file="header.jsp" %>
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
Per ulteriori informazioni visita il sito <a href="http://www.iccu.sbn.it/genera.jsp?id=78" target="blank">Anagrafe delle biblioteche italiane</a> oppure scrivi a <a onclick="document.location.href='mailto:anagrafe@iccu.isbn.it'">anagrafe@iccu.isbn.it</a>
</center>
</div>
</div>
<%@ include file="footer.jsp" %>