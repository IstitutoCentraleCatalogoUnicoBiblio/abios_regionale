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
<h1>Autenticazione</h1>
<div class="center" style="text-align: center;">
<form action="<c:url value="/j_spring_security_check" />" method="post"
	name="login">
<table style="margin: 5% auto;">
	<tr>
		<td><strong>Nome Utente:</strong></td>
		<td><input type="text" name="j_username"></input></td>
	</tr>
	<tr>
		<td><strong>Password:</strong></td>
		<td><input type="password" name="j_password"></input></td>
	</tr>
	<tr>
		<td></td>
		<td align="right"><a href="rigenera.jsp">Rigenera password</a></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: right;"><input type="submit" value="Login" /><input name="reset"
			type="reset" value="Reset" /> 
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
		<%
			String test_error = request.getParameter("login_error");
			String timeout = request.getParameter("timeout");
			String test_logout = request.getParameter("logout");
		%> <%
		if ("1".equalsIgnoreCase(test_error)) {
		%> <b style="color: red; font-size: 120%;">Accesso non eseguito</b> <%}%> <%
		if ("1".equalsIgnoreCase(timeout)) {
		%> <b style="color: red; font-size: 120%;">Sessione scaduta</b> <%}%> <%
		if ("1".equalsIgnoreCase(test_logout)) {
		%> <b style="color: green; font-size: 120%;">Logout eseguito con
		successo</b> <%}%>
		</td>
	</tr>
</table>
</form>
</div>
<center>
Per ulteriori informazioni visita il sito <a href="http://www.iccu.sbn.it/genera.jsp?id=78" target="blank">Anagrafe delle biblioteche italiane</a> oppure scrivi a <a onclick="document.location.href='mailto:anagrafe@iccu.sbn.it'">anagrafe@iccu.sbn.it</a>
</center>
</div>
</div>
<%@ include file="footer.jsp" %>