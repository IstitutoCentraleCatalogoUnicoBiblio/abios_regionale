<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/fn" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anagrafe Biblioteche Italiane - Ricerca semplice</TITLE>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
<!-- SCRIPT LANGUAGE="JAVASCRIPT" SRC="<c:url value="/script/regioniItalia.js" />"></SCRIPT-->

<SCRIPT language="JavaScript">
	numeri = /\d{1}/;
	var today = new Date();

	function check()
	{
		var valore = document.formOptions.BIBLIDENUF.value;
		OK = numeri.exec(valore)
	
		if ( (!OK && (valore.length >= 5)) )
		{
			document.formOptions.Method.value='GetList';
			document.formOptions.submit();
		}
		else if ( valore == "") alert("Campo Denominazione obbligatorio");
		else alert("Il campo Denominazione deve contenere almeno 5 caratteri non numerici");
	}
	
	//<A HREF="#" onclick="helpMessage(0, 300, 160)"><IMG SRC="..." ALIGN="CENTER" border="0"></A>
	function helpMessage(index, w, h)
	{
		var message = new Array ();
		message [0] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Destinazione sociale.</b><BR>"
			+ "La destinazione sociale delle biblioteche deve essere scelta tra le opzioni proposte nel menù a tendina<BR>"
			+ "");
		message [1] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Specializzazione della Biblioteca.</b><BR>"
			+ "La specializzazione della biblioteca è rappresentata mediante la Classificazione Decimale Dewey.<BR>"
			+ "La ricerca si imposta digitando una stringa completa corrispondente alla descrizione ufficiale di un codice Dewey.<BR>"
			+ "Con un clic sul nome di campo si può attivare la lista di aiuto: se si è digitata una sequenza di caratteri saranno prospettate le descrizioni ufficiali che la contengono, abbinate al relativo codice Dewey; a campo vuoto saranno prospettate, su più pagine, tutte le descrizioni presenti nella tabella.<BR>"
			+ "Dalla lista con un clic si 'cattura' la voce di interesse, che verrà riportata sulla maschera.<BR>"
			+ "La ricerca sarà effettuata in maniera puntuale.<BR>"
			+ "Per modalità di ricerca più articolate sulla specializzazione si vada Ricerca avanzata.<BR>"
			+ "");
			message [3] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Comune</b><br>"
			+ "Se si conosce il nome esatto del comune, lo si digita in forma completa e si avvia direttamente la ricerca.<BR>"
			+ "Se non si è sicuri della forma esatta del nome del comune, con un clic sul nome del campo si attiva la lista d'aiuto dopo avere digitato la sequenza di caratteri noti;saranno prospettati i nomi di comune che comprendono quella sequenza di caratteri noti; saranno prospettati i nomi di comune che comprendono quella sequenza di caratteri, con l'indicazione della provincia di appartenenza.<BR>"
			+ "Lasciando il campo vuoto e attivando la lista d'aiuto dopo aver selezionato una regione o una provincia, si otterrà invece la lista completa dei comuni di quell\'area territoriale.<BR>"
			+ "Dalla lista con un clic si 'cattura' la voce di interesse, che verrà riportata sulla maschera.<BR>"
			+ "La ricerca è puntuale."
			+"");
				message [4] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Denominazione</b><BR>"
			+ "La ricerca per denominazione della biblioteca è una ricerca per parole.<BR>"
			+ "Da questo campo è possibile avviare una ricerca dopo avere digitato alcune parole e avere definito, mediante il menù a tendina, se queste parole devono essere compresenti indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza [Frase esatta].<BR>"
			+ "Attenzione: il nome del comune in cui la biblioteca ha sede non fa parte della denominazione, ma deve essere definito nel campo apposito.<BR>"
			+ "");
	
		var s1 = "<HTML><HEAD><TITLE>Informazioni Banche Dati</TITLE></HEAD>"
			   + "<BODY TOPMARGIN=\"2\" MARGINHEIGHT=\"2\" onBlur=\"self.focus()\" link=\"001063\" vlink=\"#001063\" alink=\"blue\" bgcolor=\"#c6dce1\">"
			   + "<TABLE BORDER=\"0\"><TR>"
			   + "<TD WIDTH=90% HEIGHT=90 VALIGN=TOP ALIGN=LEFT>" ;
		var s2 = "</FONT></TD><TD WIDTH=10%></TD></TR><TR><TD></TD>"
			   + "<TD VALIGN=\"TOP\" ALIGN=\"RIGHT\">"
			   + "<FORM><INPUT TYPE='BUTTON' VALUE='Chiudi'"
			   + "onClick='self.close()'>"
			   + "</FORM>&nbsp;&nbsp;&nbsp;</TD></TR></TABLE></BODY><\HTML>";
		popup = window.open("","popDialog","width=" + w + ",height=" + h + ",scrollbars=1");
		popup.document.write(s1 + message[index] + s2);
		popup.document.close();
	}

	function helpComune()
	{
		var reg = document.formOptions.regione.options[document.formOptions.regione.selectedIndex].value;
		var prov = document.formOptions.provincia.options[document.formOptions.provincia.selectedIndex].value;
		var comu = document.formOptions.comune.value;

		var url = "<c:url value="/help/helpComuni.jsp" />?regione=" + reg	+ '&provincia=' + prov + '&comune=*' + comu;
		if (comu) {
			url = url + "*";
		}
		var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,menubar=yes,toolbar=yes,resizable=yes,scrollbars=yes,location=yes");
		//var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
		if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
	}

	function helpDewey()
	{
		var dew = document.formOptions.descrizione_ufficiale.value
		var url = "<c:url value="/help/helpDewey.jsp" />?descrizione=*" + escape(dew);
		if (dew) {
			url = url + "*";
		}
		var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=700,height=500,resizable=yes,scrollbars=yes,status=yes");
		if ((document.window != null) && (!hWnd.opener))
			hWnd.opener = document.window;
	}

	function updateDescr(selectObj, hiddenObj)
	{
		hiddenObj.value = selectObj.options[selectObj.selectedIndex].text
	}
</SCRIPT>


<script>

	<jsp:include page="dynaselect/regioniItalia.jsp"></jsp:include>

</script>

</HEAD>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  onload="calcolo(false)" LINK="BLUE" ALINK="BLUE" VLINK="BLUE" MARGINWIDTH="0" LEFTMARGIN="0" bgproperties="fixed">
<FORM ACTION="parametri.jsp" METHOD="GET" NAME="formOptions">

<P><DIV align="center">
<!----------------------------------------TABELLA--------------------------------------------->
<TABLE border="0" WIDTH="100%" align="center" height="100%" cellspacing="0">
<TR><TD align="center">
		<TABLE border="1" WIDTH="700">
		<TR><TD WIDTH="100%"><CENTER>
<!----------------------------------------CENTRALE--------------------------------------------->
<div align="center">
<TABLE border="0" WIDTH="80%">
<TR>
	<TD ALIGN="center" VALIGN="top" width="5%" class="logoL"><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"><BR>ICCU</TD>
	<TD ALIGN="center" VALIGN="middle" width="70%">
		<FONT color='#e27e10' face='Arial' size='5'><B>ANAGRAFE BIBLIOTECHE ITALIANE</B></font><br>
		<FONT color='#e27e10' face='Arial' size='4'><b>- Ricerca semplice -</b></font></TD>
	<TD width="5%">&nbsp;</TD>
</TR>

</TABLE>
</div><HR ALIGN="CENTER">
<!-------------------------------------------------------------------------------------------->
<TABLE border="0" width="100%">
<!--tr>
<td align="left"><font SIZE ="2" COLOR = "RED"</font>&nbsp;I dati per i campi contrassegnati con *&nbsp;saranno disponibili con i successivi aggiornamenti.</td>
</tr-->
</TABLE>
<TABLE border="0" WIDTH="100%">
<TR><TD align="right" height="1"><font size="1">&nbsp;</font></TD></TR>
</TABLE>
<TABLE border="0" WIDTH="100%">
<TR>
	<!-- TD CLASS="filtriRight2" width="30%">Regione&nbsp;</TD-->

	<TD ALIGN="LEFT" width="40%">
		<SELECT NAME="regione" style="display:none;" onchange="calcolo(true)">
			<OPTION VALUE="PUGLIA"></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="regione"/></jsp:include>
		</SELECT>
		<INPUT TYPE="HIDDEN" NAME="REGIONE:OP" VALUE="ONLY" />
		<INPUT TYPE="HIDDEN" NAME="ricerca_tipo" VALUE="semplice" />
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">Provincia&nbsp;</TD>
	<TD ALIGN="LEFT" colspan="2">
		<SELECT NAME="provincia">
			<c:choose>
				<c:when test="${fn:length(param.provincia) > 0}">
					<option value="${param.provincia}" SELECTED></option>
				</c:when>
				<c:otherwise>
					<option value=""></option>
				</c:otherwise>
			</c:choose>
		</SELECT>
	</TD>
	<TD align="right" width="30%" nowrap>
		<INPUT TYPE="SUBMIT" NAME="avvia" VALUE="Avvia ricerca">&nbsp;&nbsp;
		<INPUT TYPE="BUTTON" NAME="CANCELLA" VALUE="Cancella" onClick="cancella()"><!--r.eschini per ripristinare tutte le regioni/province -->
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">
     <a href="Javascript:helpComune()">
    	Comune</a>&nbsp;
	 <A HREF="#" onclick="helpMessage(12, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></TD>
     <TD colspan="3" ALIGN="LEFT">
     	<c:choose>
			<c:when test="${fn:length(param.comune) > 0}">
				<INPUT TYPE="TEXT" NAME="comune" SIZE="55" MAXLENGTH="150" VALUE="${param.comune}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="comune" SIZE="55" MAXLENGTH="150">			
			</c:otherwise>
		</c:choose>
	 </TD>
</TR>
<TR><TD CLASS="filtriRight2">Denominazione&nbsp;
		<A HREF="#" onclick="helpMessage(4, 400, 200);return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" border="0"></A>
</TD>

	<TD colspan="2" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.denominazione_forsearch) > 0}">
				<INPUT TYPE="TEXT" NAME="denominazione_forsearch" SIZE="45" MAXLENGTH="150" VALUE="${param.denominazione_forsearch}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="denominazione_forsearch" SIZE="45" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>				
 		<SELECT NAME="denominazione_forsearch:tipo">
 			<%
 				String denominazione_forsearch_tipo = request.getParameter("denominazione_forsearch:tipo");
 				if ("AND".equalsIgnoreCase(denominazione_forsearch_tipo)) {
 					%>
 					<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
					<OPTION VALUE="OR">Qualsiasi parola</OPTION>
					<OPTION VALUE="ONLY">Frase esatta</OPTION>
					<%
 				} else if ("OR".equalsIgnoreCase(denominazione_forsearch_tipo)) {
 					%>
 					<OPTION VALUE="AND">Tutte le parole</OPTION>
 					<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
 					<OPTION VALUE="ONLY">Frase esatta</OPTION>
 					<%
 				} else if ("ONLY".equalsIgnoreCase(denominazione_forsearch_tipo)) {
 					%>
 					<OPTION VALUE="AND">Tutte le parole</OPTION>
 					<OPTION VALUE="OR">Qualsiasi parola</OPTION>
 					<OPTION VALUE="ONLY" SELECTED>Frase esatta</OPTION>
 					<%
 				} else {
 					%>
 					<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
					<OPTION VALUE="OR">Qualsiasi parola</OPTION>
					<OPTION VALUE="ONLY">Frase esatta</OPTION>
					<%
 				}
 			%>
		</SELECT>

	</TD>
</TR>
<TR>
	<TD COLSPAN="1" width="30%" align="right">
		<P><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
			<a href="Javascript:helpDewey()">
			Specializzazione</a>&nbsp;</B>
		<A HREF="#" onclick="helpMessage(1, 400, 200);return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" border="0"></A>
	</TD>

	<TD COLSPAN="2" ALIGN="LEFT" width="70%" nowrap>
		<c:choose>
			<c:when test="${fn:length(param.descrizione_ufficiale) > 0}">
				<INPUT TYPE="TEXT" NAME="descrizione_ufficiale" SIZE="65" VALUE="${param.descrizione_ufficiale}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="descrizione_ufficiale" SIZE="65">
			</c:otherwise>
		</c:choose>
		<input type="hidden" name="descrizione_ufficiale:tipo" value="ONLY" />
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" nowrap><font color="Red">&nbsp;</font>Destinazione sociale&nbsp;
	<A HREF="#" onclick="helpMessage(0, 400, 200);return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" border="0"></A></TD>
	<TD ALIGN="LEFT" colspan="2">
		<!-- input type="hidden" name="destinazioni_sociali" value=""   onChange="updateDescr(this, document.formOptions.destinazioni_sociali)"-->
		<SELECT NAME="destinazioni_sociali">
			<OPTION VALUE="" SELECTED></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="destinazioni_sociali_tipo"/></jsp:include>
		</SELECT>

	</TD>
</TR>
<TR><td colspan="2"></td></tr>
</TABLE>
<!-------------------------------------------------------------------------------------------->
<TABLE border="0" WIDTH="100%">
<TR>
	<TD COLSPAN="2"><FONT SIZE="1" FACE="Verdana">&nbsp;
<!--- E' possibile indicare un codice con completamento a destra</FONT>  --->
	</TD>
</TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
<TABLE border="0" WIDTH="100%">

<TR>
	<TD CLASS="filtriRight2" nowrap><font color="Red">&nbsp;</font>Access. portatori di handicap</TD>
	<c:choose>
		<c:when test="${param.accesso_handicap=='true'}">
			<TD ALIGN="LEFT" colspan="2"><input type="checkbox" name="accesso_handicap" value="true" CHECKED></TD>
		</c:when>
		<c:otherwise>
			<TD ALIGN="LEFT" colspan="2"><input type="checkbox" name="accesso_handicap" value="true"></TD>			
		</c:otherwise>
	</c:choose>
</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1" width="40%">Accesso&nbsp;</TD>
	<TD COLSPAN="3" ALIGN="LEFT" width="60%"><FONT SIZE="1" FACE="Verdana">
	<c:choose>
		<c:when test="${param.accesso_riservato=='false'}">
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false" CHECKED><b>Aperta a tutti</b>
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true"><b>Riservata</b>
		</c:when>
		<c:when test="${param.accesso_riservato=='true'}">
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false"><b>Aperta a tutti</b>
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true" CHECKED><b>Riservata</b>
		</c:when>
		<c:otherwise>
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false"><b>Aperta a tutti</b>
			<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true"><b>Riservata</b>
		</c:otherwise>
	</c:choose>
		
	</TD>
</TR>
<TR>
	<TD COLSPAN="1">
		<P ALIGN="RIGHT"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		Orario di apertura&nbsp;</FONT></B>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">

	<SELECT NAME="orario_ufficiale_fasce">orario_ufficiale_fasce
		<c:choose>
			<c:when test="${param.orario_ufficiale_fasce=='Mattina'}">
				<OPTION VALUE=""></OPTION>
				<OPTION VALUE="Mattina" SELECTED>Mattina</OPTION>
				<OPTION VALUE="Pomeriggio">Pomeriggio</OPTION>
				<OPTION VALUE="Sera">Sera</OPTION>
				<OPTION VALUE="Festivo">Festivo</OPTION>
			</c:when>
			<c:when test="${param.orario_ufficiale_fasce=='Pomeriggio'}">
				<OPTION VALUE=""></OPTION>
				<OPTION VALUE="Mattina">Mattina</OPTION>
				<OPTION VALUE="Pomeriggio" SELECTED>Pomeriggio</OPTION>
				<OPTION VALUE="Sera">Sera</OPTION>
				<OPTION VALUE="Festivo">Festivo</OPTION>
			</c:when>
			<c:when test="${param.orario_ufficiale_fasce=='Sera'}">
				<OPTION VALUE=""></OPTION>
				<OPTION VALUE="Mattina">Mattina</OPTION>
				<OPTION VALUE="Pomeriggio">Pomeriggio</OPTION>
				<OPTION VALUE="Sera" SELECTED>Sera</OPTION>
				<OPTION VALUE="Festivo">Festivo</OPTION>
			</c:when>
			<c:when test="${param.orario_ufficiale_fasce=='Festivo'}">
				<OPTION VALUE=""></OPTION>
				<OPTION VALUE="Mattina">Mattina</OPTION>
				<OPTION VALUE="Pomeriggio">Pomeriggio</OPTION>
				<OPTION VALUE="Sera">Sera</OPTION>
				<OPTION VALUE="Festivo" SELECTED>Festivo</OPTION>
			</c:when>
			<c:otherwise>
				<OPTION VALUE="" SELECTED></OPTION>
				<OPTION VALUE="Mattina">Mattina</OPTION>
				<OPTION VALUE="Pomeriggio">Pomeriggio</OPTION>
				<OPTION VALUE="Sera">Sera</OPTION>
				<OPTION VALUE="Festivo">Festivo</OPTION>
			</c:otherwise>
		</c:choose>
	</SELECT>

	</TD>
</TR>
<TR><TD colspan="4">&nbsp;</TD></TR>
<TR>
	<TD bgcolor="#E6E6E6" colspan="4"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		Servizi</B></FONT>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1">Informazioni bibliografiche&nbsp;</TD>

	<TD COLSPAN="3" align="left"><FONT SIZE="1" FACE="Verdana">
	<c:choose>
		<c:when test="${param.gestisce_servizio_bibliografico_interno=='true'}">
			<b>Per utenti in sede</b><input type="checkbox" name="gestisce_servizio_bibliografico_interno" value="true" CHECKED>&nbsp;&nbsp;			
		</c:when>
		<c:otherwise>
			<b>Per utenti in sede</b><input type="checkbox" name="gestisce_servizio_bibliografico_interno" value="true">&nbsp;&nbsp;
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${param.gestisce_servizio_bibliografico_esterno=='true'}">
			<b>Per utenti esterni</b><input type="checkbox" name="gestisce_servizio_bibliografico_esterno" value="true" CHECKED>&nbsp;&nbsp;
		</c:when>
		<c:otherwise>
			<b>Per utenti esterni</b><input type="checkbox" name="gestisce_servizio_bibliografico_esterno" value="true">&nbsp;&nbsp;		
		</c:otherwise>
	</c:choose>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1">Riproduzioni&nbsp;</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
	<c:choose>
		<c:when test="${param.riproduzioni=='Si'}">
			<input type="checkbox" name="riproduzioni" value="Si" CHECKED>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="riproduzioni" value="Si">
		</c:otherwise>
	</c:choose>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1"><font color="Red">&nbsp;</font>Postazioni INTERNET per gli utenti&nbsp;</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
	<c:choose>
		<c:when test="${fn:length(param.posti_internet) > 0}">
			<input type="checkbox" name="posti_internet" value="[1 TO *]" CHECKED>
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="posti_internet" value="[1 TO *]">	
		</c:otherwise>
	</c:choose>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">Prestito&nbsp;</TD>
	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">
	<c:choose>
		<c:when test="${param.prestito_locale=='Si'}">
			<INPUT TYPE="CHECKBOX" NAME="prestito_locale" VALUE="Si" CHECKED><b>locale</b>
		</c:when>
		<c:otherwise>
			<INPUT TYPE="CHECKBOX" NAME="prestito_locale" VALUE="Si"><b>locale</b>	
		</c:otherwise>
	</c:choose>
	</TD>	
	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">
	<c:choose>
		<c:when test="${param.prestito_interbiblio_nazionale=='true'}">
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_nazionale" VALUE="true" CHECKED><b>nazionale</b>
		</c:when>
		<c:otherwise>
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_nazionale" VALUE="true"><b>nazionale</b>
		</c:otherwise>
	</c:choose>
	</TD>
	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">
	<c:choose>
		<c:when test="${param.prestito_interbiblio_internazionale=='true'}">
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_internazionale" VALUE="true" CHECKED><b>internazionale</b>
		</c:when>
		<c:otherwise>
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_internazionale" VALUE="true"><b>internazionale</b>	
		</c:otherwise>
	</c:choose>
	</TD>
</TR>
</TABLE><BR>
<!-------------------------------------------------------------------------------------------->
<TABLE border="0" CELLSPACING="1" WIDTH="100%">
 	<TR>
		<TD align="left"><a href="" onclick="window.history.back();return false;"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
			Indietro</font></a></TD>
		<TD align="right"><a href="index.jsp"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
			HomePage</font></a></TD>

	</TR>
	<!--tr>
	   <td align="LEFT" colspan="2"><font SIZE ="2" COLOR = "RED"</font>&nbsp;I dati per i campi contrassegnati con *&nbsp;saranno disponibili con i successivi aggiornamenti.</td>
    </tr-->
	<TR><TD align="center" colspan="2" width="750" height="1%"><HR size="1" color="Navy" width="100%"></TD></TR>
 	<TR>
		<TD align="center" colspan="2">
			<INPUT TYPE="SUBMIT" NAME="avvia" VALUE="Avvia ricerca">&nbsp;&nbsp;

			<INPUT TYPE="BUTTON" NAME="CANCELLA" VALUE="Cancella" onClick="cancella()"><!--r.eschini per ripristinare tutte le regioni/province -->
		</TD>
	</TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
</TD></TR></TABLE></TD></TR>
	<TABLE border="0" WIDTH="700">
	<TR><TD ALIGN="center" colspan="2">&nbsp;</TD></TR>
	<TR>
		<td align="center" colspan="2">
			<jsp:include page="footer.jsp"></jsp:include>
		</td>
	</TR>
	</TABLE>
</table>
</CENTER></FORM></BODY>
</html>
