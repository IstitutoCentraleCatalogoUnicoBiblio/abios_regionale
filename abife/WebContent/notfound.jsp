<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anagrafe Biblioteche Italiane - <FONT FACE='Arial' COLOR='Red' SIZE='3'>ATTENZIONE!</title>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
<script language="JavaScript1.2">
function flash()
{
	if (document.layers) return;
	if (myexample.style.color=="red") myexample.style.color="white";
	else myexample.style.color="red";
};
</script>
</HEAD>

<BODY link="#006666" alink="#006666" vlink="#006666">
<FORM NAME="form" ACTION="" METHOD="POST">
<div align="center">
<TABLE BORDER="0" CELLSPACING="0" width="100%" height="100%">
<TR>
	<TD ALIGN="center">
	<!------------------------------------------------->
	<TABLE BORDER="0" BGCOLOR="#339966" CELLSPACING="0">
	<TR>
		<TD ALIGN="center">
		<TABLE BORDER="0" BGCOLOR="White" CELLSPACING="0" cellpadding="6">
		<TR><TD NOWRAP ALIGN="center" BGCOLOR="#339966"><FONT COLOR="White" SIZE="5" FACE="Times"><b>I C C U</b></FONT><br>
			<FONT COLOR="White" SIZE="4" FACE="Arial"><b><i>Anagrafe Biblioteche Italiane</i></b></FONT></TD></TR>
<!---		
		<TR><TD NOWRAP align="center">&nbsp;</TD></TR>
--->
		<TR><TD NOWRAP align="center">
			<FONT SIZE="4" COLOR="Red" FACE="Arial, Verdana, Helvetica, sans-serif" id="myexample">
			<BLINK><B><FONT FACE='Arial' COLOR='Red' SIZE='3'>ATTENZIONE!</B></BLINK></TD></TR>
		<TR><TD NOWRAP align="center"><FONT SIZE="2" COLOR="navy" FACE="Arial, Verdana">
				<B><FONT FACE='Arial' COLOR='Black' SIZE='3'>Biblioteca inesistente!!!</B></TD></TR>
		<TR><TD NOWRAP>&nbsp;</TD></TR>
		<TR><TD NOWRAP align="center">
			<INPUT TYPE="Button" VALUE="Indietro" onclick="window.history.back()">
			<TABLE BORDER="0" width="500"><tr><td>&nbsp;</td></tr></table>
		</TD></TR>
		</TABLE>
		</TD>
	</TR>
	</TABLE>		
	</TD>
</TR>
</TABLE>
</div></body></html>