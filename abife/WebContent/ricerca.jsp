<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<html>
<head>
	<TITLE>Anagrafe Biblioteche Italiane - Ricerca</TITLE>
	<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css">	
	<script language="javascript" src="<c:url value="/script/checkLibrary.js" />"></script>
<SCRIPT LANGUAGE="JavaScript">
// Questo script abilita il focus a seconda del valore dell' hidden(webMacro)
function togliFocus(oggetto)
{
	if (document.form.ABILITA.value == 'false')
	{
		eval('document.form.' + oggetto + '.blur()');
	}
}
function controllaIsil()
{
	ret = true;

	if (isEmptyOrAllBlank(document.form.isil_provincia, 2, "codice anagrafe della provincia") ||
		isONEBlank(document.form.isil_provincia, 2, "codice anagrafe della provincia") ||
		noAlphabeticString(document.form.isil_provincia, "codice anagrafe della provincia") ||
		isEmptyOrAllBlank(document.form.isil_numero, 4, "codice anagrafe numerico") ||
		isONEBlank(document.form.isil_numero, 4, "codice anagrafe numerico") ||
		noNumbersString(document.form.isil_numero, "codice anagrafe numerico"))
	{
		ret = false;
	}

	if (ret) {
		document.form.submit();
	}
}
</SCRIPT>
</HEAD>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  onload="document.form.isil_provincia.focus()">
<FORM name="form" ACTION="<c:url value="/ricercaisil.jsp" />" METHOD="get">
<div align="center">
<table border="0" width="750" height="100%">
<tr>

	<td valign="middle" align="center" colspan="3" width="750" height="96%">
		<TABLE BORDER="0" WIDTH="80%">
		<TR>
			<TD ALIGN="center" VALIGN="top" width="5%" class="logoL"><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"><BR>ICCU</TD>
			<TD ALIGN="CENTER" COLSPAN="2">
			<FONT color='#e27e10' face='Arial' size='5'><B>ANAGRAFE BIBLIOTECHE ITALIANE</B></FONT>
			</TD>

		</TR>
		<TR><TD ALIGN="CENTER" COLSPAN="3"><HR ALIGN="CENTER" SIZE="3" WIDTH="100%"></TD></TR>
		<TR>
			<TD ALIGN="CENTER" COLSPAN="3"><font size="1" color="#000099" face="Arial">
				L'interrogazione permette di ricercare le biblioteche con il codice
				di biblioteca, oppure sulla base dei seguenti criteri:
				denominazione, collocazione territoriale, amministrazione di appartenenza,
				destinazione sociale, specializzazione, patrimonio librario, fondi speciali,
				cataloghi e servizi al pubblico.
				Risultato finale dell'interrogazione è una descrizione analitica con
				informazioni dettagliate sulle caratteristiche e i servizi di ciascuna
				biblioteca selezionata<p>
			</TD>
		</TR>
		<TR>

			<TD ALIGN="CENTER" COLSPAN="3">
				<TABLE BORDER="0" BGCOLOR="white" width="300">
				<TR><TD CLASS="verdeCenter2">Ricerca per Codice di biblioteca</TD></TR>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="CENTER" COLSPAN="3"><P><FONT SIZE="1" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">

				Se si conosce il codice della biblioteca,<br>
				digitarlo in questi campi e lanciare la ricerca</P></font>
			</TD>
		</TR>
		<TR>
			<TD width="50%" align="center" colspan="3"><FONT SIZE="2" COLOR="Blue" FACE="Arial">
				<b>Codice :</b>&nbsp;
				<INPUT TYPE="TEXT" NAME="isil_stato" SIZE="2" MAXLENGTH="2" value="IT">&nbsp;&#45;&nbsp;
				<INPUT TYPE="TEXT" NAME="isil_provincia" SIZE="2" MAXLENGTH="2" value="">&nbsp;&nbsp;
				<INPUT TYPE="TEXT" NAME="isil_numero" SIZE="5" MAXLENGTH="4" value="">
			</TD>
		</TR>
		<TR><TD ALIGN="center" colspan="3"><INPUT TYPE="button" NAME="AVVIA" VALUE="Avvia ricerca" onclick="controllaIsil()"></TD></TR>
		<TR><TD ALIGN="CENTER" COLSPAN="3"><P>&nbsp;</P></TD></TR>
		<TR>
			<TD ALIGN="CENTER" COLSPAN="3">
				<TABLE BORDER="0" BGCOLOR="white" width="300">
					<TR><TD CLASS="verdeCenter2">Tipo di Ricerca</TD></TR>
				</TABLE>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="CENTER" COLSPAN="3"><P>

				<FONT SIZE="1" COLOR="black" FACE="Verdana, Arial, Helvetica, sans-serif">
				Se non si conosce il codice della biblioteca,<br>
				premere il tasto relativo alla ricerca desiderata</P>
			</TD>
		</TR>
		<TR>
			<TD ALIGN="center" colspan="3">
				<INPUT TYPE="BUTTON" NAME="LIGHT" VALUE="Ricerca semplice" onclick="window.location='<c:url value="ricercas.jsp" />'">&nbsp;&nbsp;&nbsp;
				<INPUT TYPE="BUTTON" NAME="HEAVY" VALUE="Ricerca avanzata" onclick="window.location='<c:url value="ricercaa.jsp" />'">
			</TD>
		</TR>
		<tr>
		<TD align=right width="15%" height="1%" COLSPAN="3">
			<a href="<c:url value="/index.jsp" />">
				<FONT SIZE="2" COLOR="#FF8040" FACE="Arial Black">
				HomePage
				</font>
			</a>
		</TD>
		</tr>
		<TR><TD ALIGN="center" colspan="3">&nbsp;</TD></TR>
		<TR><TD ALIGN="center" colspan="3">
				<font size="1" color="#000099" face="Arial">
				Per segnalazioni di eventuali errori o lacune: <a href="mailto:anagrafe@iccu.sbn.it">
				anagrafe@iccu.sbn.it</a>
		</TD></TR>
		<TR><TD ALIGN="CENTER" COLSPAN="3"><HR ALIGN="CENTER" SIZE="3" WIDTH="100%"></TD></TR>
		
		<TR>
			<td align="center" colspan="3">
				<jsp:include page="footer.jsp"></jsp:include>
			</td>
		</TR>
		
		</TABLE>
	</td>
</TR>
</TABLE>
</DIV></FORM></BODY></HTML>