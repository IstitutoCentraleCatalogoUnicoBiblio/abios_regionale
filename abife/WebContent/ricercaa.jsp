<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/fn" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anagrafe Biblioteche Italiane - Ricerca avanzata</TITLE>
<LINK REL="stylesheet" HREF="<c:url value="/style/styleIccu.css" />" TYPE="text/css">
<!--  SCRIPT LANGUAGE="JAVASCRIPT" SRC="<c:url value="/script/regioniItalia.js" />"></SCRIPT -->
<SCRIPT language="JavaScript">
numeri = /\d{1}/;
function check()
{
	var valore = document.formOptions.BIBLIDENUF.value;
	OK = numeri.exec(valore)

	if ( (!OK &&  (valore.length >= 5)) )
	{
		document.formOptions.Method.value='GetList';
		document.formOptions.submit();
	}
	else if ( valore == "") alert("Campo Denominazione obbligatorio");
	else alert("Il campo Denominazione deve contenere almeno 5 caratteri non numerici");
}
//<A HREF="#" onclick="helpMessage(0, 400, 200)"><IMG SRC="..." ALIGN="CENTER" BORDER="0"></A>
//<A HREF="#" onclick="helpMessage(0, 400, 200)"><IMG SRC="$INFO" ALIGN="CENTER" BORDER="0"></A>
function helpMessage(index, w, h)
{
	var message = new Array ();
	message [0] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Tipologia funzionale.</b><BR>"
		+ "La tipologia funzionale delle biblioteche deve essere scelta tra le opzioni proposte nel menù a tendina (corrispondenti alla norma UNI EN ISO 2789/96).<BR>"
		+ "");
	message [1] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Tipologia amministrativa.</b><BR>"
		+ "La tipologia amministrativa (dell'ente di appartenenza) delle biblioteche deve essere scelta tra le opzioni proposte nel menù a tendina."
		+ "");
	message [2] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Specializzazione della biblioteca.</b><BR>"
		+ "Per selezionare biblioteche in base alla loro specializzazione (che è rappresentata mediante la Classificazione Decimale Dewey, fino a un massimo di tre cifre dopo il punto) conviene utilizzare uno solo dei quattro canali disponibili, per evitare il rischio di incongruenze che porterebbero a un risultato nullo.<BR>"
		+ "");
	message [3] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Destinazione sociale.</b><BR>"
		+ "La destinazione sociale delle biblioteche deve essere scelta tra le opzioni proposte nel menù a tendina<BR>"
		+ "");
	message [4] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Fondi speciali.</b><BR>"
		+ "XXX<BR>"
		+ "");
	message [5] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Codice Dewey.</b><BR>"
		+ "XXX<BR>"
		+ "");
	message [6] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Catalogo speciale.</b><BR>"
		+ "I cataloghi speciali presenti nelle biblioteche sono descritti per tipologia del materiale o della raccolta.<BR>"
		+ "Per selezionare le biblioteche che hanno un certo tipo di catalogo speciale, si deve digitare in maniera esatta e completa una descrizione presente nella relativa tabella.<BR>"
		+ "Pertanto si consiglia di avvalersi sempre della lista di aiuto, che si attiva con un clic sul nome di campo a campo vuoto o, meglio, dopo avere digitato una sequenza di caratteri; in tal caso sarannno prospettate le voci che contengono quella sequenza di caratteri; le grandi voci con tutte le rispettive voci di dettaglio e le voci di dettaglio con la rispettiva grande voce.<BR>"
		+ "Dalla lista di aiuto con un clic si può \"catturare\" la voce di interesse; sarà effettuata una ricerca puntuale.<BR>"
		+ "");
	message [7] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Catalogo collettivo.</b><BR>"
		+ "I cataloghi collettivi ai quali le singole biblioteche partecipano per almeno una parte del loro patrimonio librario sono descritti con la loro denominazione ufficiale, in qualche caso normalizzata per evitare possibili ambiguità.<br> Per selezionare le biblioteche che partecipano a un catalogo collettivo, si deve digitare in maniera esatta e completa una descrizione presente nella relativa tabella.<BR>"
		+ "Pertanto si consiglia di avvalersi sempre della lista di aiuto, che si attiva con un clic sul nome di campo a campo vuoto, o, meglio, dopo avere digitato una sequenza di caratteri; in tal caso saranno prospettate le denominazioni dei cataloghi collettivi che contengono quella sequenza sequenza di caratteri.<BR>"
		+ "Dalla lista di aiuto con un clic si può \"catturare\" la voce di interesse; sarà effettuata una ricerca puntuale.<BR>"
		+ "");
	message [8] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Spogli materiale bibliografico.</b><BR>"
		+ "Per selezionare biblioteche in relazione ai tipi di spoglio che effettua, si deve scegliere una delle opzioni prospettate dal menù a tendina.<BR>"
		+ "");
	message [9] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>SBN</b><BR>"
		+ "I codici identificativi delle biblioteche presenti nel sistema gestionale del Servizio Bibliotecario Nazionale (raggruppate per Poli) sono elencati a partire  dalla pagina<BR><a href=http://www.iccu.sbn.it//bipolsbn.html target=_blank>http://www.iccu.sbn.it/bipolsbn.html</a>"
		+ "");
	message [10] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>RISM</b><BR>"
		+ "I codici identificavi nel sistema RISM (Répertoire International des Sources Musicales) delle biblioteche italiane che possiedono musica scritta sono elencati alla pagina<BR><a href=http://www.cilea.it/music/mussigle.htm target=_blank>http://www.cilea.it/music/mussigle.htm</a> <br>dove però sono privi del segmento iniziale I-."
		+ "");
	message [11] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>ACNP</b><BR>"
		+ "Per i codici identificativi delle biblioteche nel sistema ACNP (Archivio Collettivo Nazionale delle pubblicazioni Periodiche) si veda la relativa base dati<BR><a href=http://acnp.cib.unibo.it/cgi-ser/start/it/cnr/fp.html target=_blank>http://acnp.cib.unibo.it/cgi-ser/start/it/cnr/fp.html</a>"
		+ "");
	message [12] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Comune</b><br>"
		+ "Se si conosce il nome esatto del comune, lo si digita in forma completa e si avvia direttamente la ricerca.<BR>"
		+ "Se non si è sicuri della forma esatta del nome del comune, con un clic sul nome del campo si attiva la lista d'aiuto dopo avere digitato la sequenza di caratteri noti;saranno prospettati i nomi di comune che comprendono quella sequenza di caratteri noti; saranno prospettati i nomi di comune che comprendono quella sequenza di caratteri, con l'indicazione della provincia di appartenenza.<BR>"
		+ "Lasciando il campo vuoto e attivando la lista d'aiuto dopo aver selezionato una regione o una provincia, si otterrà invece la lista completa dei comuni di quell\'area territoriale.<BR>"
		+ "Dalla lista con un clic si 'cattura' la voce di interesse, che verrà riportata sulla maschera.<BR>"
		+ "La ricerca è puntuale."
		+"");
	message [13] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Denominazione</b><BR>"
		+ "La ricerca per denominazione della biblioteca è una ricerca per parole.<BR>"
		+ "Da questo campo è possibile avviare una ricerca dopo avere digitato alcune parole e avere definito, mediante il menù a tendina, se queste parole devono essere compresenti indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza [Frase esatta].<BR>"
		+ "Attenzione: il nome del comune in cui la biblioteca ha sede non fa parte della denominazione, ma deve essere definito nel campo apposito.<BR>"
		+ "");
	message [14] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Codice Dewey</b><BR>"
		+ "E' possibile digitare anche una sola cifra iniziale della codifica Dewey e avviare la ricerca: in questo caso verranno selezionate tutte le biblioteche la cui specializzazione è definita con un codice che comincia con quella/e cifra/e.<BR>"
		+ "Con un clic sul nome del campo si può attivare una lista d'aiuto; se si è già digitata nel campo qualche cifra, sarà prospettata una lista che comprende i codici Dewey che cominciano con quelle cifre, accompagnati dalla descrizione in chiaro; se si attiva la lista a campo vuoto, saranno prospettati su più pagine tutti i codici Dewey presenti nella tabella delle base dati.<BR>"
		+ "Con un clic è possibile \"catturare\" una voce della lista; sarà effettuata una ricerca puntuale su quel codice.<BR>"
		+ "");
	message [15] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Da codice Dewey... a codice Dewey</b><BR>"
		+ "I due campi permettono di definire gli estremi di una sequenza di codici sui quali verrà impostata la ricerca.<br>Anche qui sono disponibili le liste di aiuto.<BR>"
		+ "");
	message [16] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Descrizione ufficiale.</b><BR>"
		+ "In questo campo è possibile lanciare la ricerca dopo avere digitato alcune parole della descrizione in chiaro di uno o più codici e dopo avere definito, mediante il menù a tendina, se queste parole devono essere compresenti, indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza [Frase Esatta].<BR>"
		+ "Anche per questo campo è disponibile la lista di aiuto, che si può attivare con un clic sul nome del campo a campo vuoto o, meglio, dopo avere digitato una sequenza di caratteri; in tal caso saranno prospettate le descrizioni ufficiali che contengono quella sequenza di caratteri, abbinate ai codici corrispondenti.<BR>"
		+ "Dalla lista di aiuto con un clic si può \"catturare\" la voce di interesse; sarò effettuata una ricerca puntuale.<BR>"
		+ "Questa lista può essere utile anche come orientamento in vista di una ricerca nel canale dei codici Dewey.<BR>"
		+ "");
	message [17] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Descrizione libera.</b><BR>"
		+ "Poichè è previsto che le biblioteche possano descrivere in forma libera la loro specializzazione (anche se questa descrizione libera viene comunque fatta corrispondere a un codice Dewey), è possibile digitare in questo alcune parole e definire, mediante il menù a tendina, se queste parole devono essere compresenti, indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza esatta [Frase esatta].<BR>"
		+ "Verranno selezionate le biblioteche che hanno, all'interno della descrizione libera della specializzazione, i termini utilizzati per la ricerca (ricerca per parole).<BR>"
		+ "");
	message [18] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Patrimonio librario.</b><BR>"
		+ "Il patrimonio librario delle biblioteche è descritto secondo le categorie previste dalla norma UNI EN ISO 2789/96; all'interno delle voci di questo schema le biblioteche possono specificare voci di dettaglio per descrivere in maniera più precisa il loro posseduto.<BR>"
		+ "Per la ricerca si deve digitare in maniera esatta e completa una voce della tabella;pertanto si consiglia di avvalersi sempre della lista di aiuto, che si attiva con un clic sul nome di campo a campo vuoto (per vedere la tabella completata, ordinata per grandi voci e relative voci di dettaglio) o, meglio, dopo avere digitato una sequenza di caratteri; in tal caso saranno prospettate le voci che contengono quella sequenza di caratteri, le grandi voci con tutte le rispettive coci di dettaglio e le voci di dettaglio con la rispettiva grande voce.<BR>"
		+ "Dalla lista di aiuto con un clic si può \"catturare\" la voce di interesse; sarà effettuata una ricerca puntuale.<BR>"
		+ "");
	message [19] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Denominazione fondo.</b><BR>"
		+ "La denominazione di un fondo speciale (testo libero) contiene generalmente il nome di una persona o di una istituzione legate alla sua costituzione(raccoglitore, donatore, dedicatario) o l'argomento centrale del fondo stesso.<BR>"
		+ "Da questo campo è possibile lanciare una ricerca dopo avere digitato alcune parole e dopo avere definito, mediante il menù a tendina, se queste parole devono essere compresenti indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza esatta [Frase esatta].<BR>"
		+ "");
	message [20] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Descrizione fondo.</b><BR>"
		+ "La descrizione di un fondo speciale (testo libero) contiene generalmente "
		+ "i tipi di materiali presenti nel fondo e la relativa consistenza.<br>Da questo campo è possibile lanciare una ricerca dopo aver digitato alcune parole e dopo aver definito, mediante il menù a tendina, se queste parole devono essere compresenti indipendentemente dall'ordine [Tutte le parole], alternative [Qualsiasi parola] o nella sequenza esatta [Frase esatta].<BR>"
		+ "");
	var s1 = "<HTML><HEAD><TITLE>Informazioni Banche Dati</TITLE></HEAD>"
		   + "<BODY TOPMARGIN=\"2\" MARGINHEIGHT=\"2\"  onBlur=\"self.focus()\" link=\"001063\" vlink=\"#001063\" alink=\"blue\" bgcolor=\"#c6dce1\">"
		   + "<TABLE BORDER=\"0\"><TR>"
		   + "<TD WIDTH=90% HEIGHT=90 VALIGN=TOP ALIGN=LEFT>" ;
	var s2 = "</FONT></TD><TD WIDTH=10%></TD></TR><TR><TD></TD>"
		   + "<TD VALIGN=\"TOP\" ALIGN=\"RIGHT\">"
		   + "<FORM><INPUT TYPE='BUTTON' VALUE='Chiudi'"
		   + "onClick='self.close()'>"
		   + "</FORM></TD></TR></TABLE></BODY><\HTML>";
	popup = window.open("","popDialog","width=" + w + ",height=" + h + ",scrollbars=1");
	popup.document.write(s1 + message[index] + s2);
	popup.document.close();
}

var today = new Date();
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

function helpSistemi()
{
	var sist = document.formOptions.sistemi_biblioteche.value
	var url = "<c:url value="/help/helpSistemi.jsp" />?descrizione=*" + sist
	if (sist) {
		url = url + "*";
	}
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
}

function helpSezSpez()
{
	var sezspec = document.formOptions.sezioni_speciali.value
	var url = "<c:url value="/help/helpSezioni.jsp" />?descrizione=*" + sezspec
	if (sezspec) {
		url = url + "*";
	}
	
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
}

function helpPatrimonio()
{
	var cod = document.formOptions.patrimonio_librario_descr.value

	var url = "<c:url value="/help/helpPatr.jsp" />?catspec=false&descrizione=*" + cod;
	if (cod) {
		url = url + "*";
	}
	
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	
}

function helpCatalogoSpec()
{
	var cod = document.formOptions.cataloghi_speciali_descr.value
	//document.formOptions.CATALOGO_FIELD.value = obj.name

	var url = "<c:url value="/help/helpPatr.jsp" />?catspec=true&descrizione=*" + cod;
	if (cod) {
		url = url + "*";
	}
	
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
}

function helpCatalogoColl()
{
	var cod = document.formOptions.cataloghi_collettivi_descr.value;

	var url = "<c:url value="/help/helpCatColl.jsp" />?descrizione=*" + cod;
	if (cod) {
		url = url + "*";
	}
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
}

function helpCodiceDewey(field)
{
	var cod = eval('document.formOptions.' + field + '.value');

	var url = "<c:url value="/help/helpCodiceDewey.jsp" />?id_dewey=*" + cod;
	if (cod) {
		url = url + "*";
	}
	url = url + "&field=" + field;
	
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=790,height=500,resizable=yes,scrollbars=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
}

function helpDeweyUff()
{
	var dew = document.formOptions.descrizione_ufficiale.value
	var url = "<c:url value="/help/helpDewey.jsp" />?descrizione=*" + dew;
	if (dew) {
		url = url + "*";
	}
	var hWnd = window.open(url,"HelpWindow" + today.getMilliseconds(),"width=700,height=500,resizable=yes,scrollbars=yes,status=yes");
	if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;

}
function setTipAmmDescrizione() {
	document.formOptions.tipologia_amministrativa.value = document.formOptions.id_tipologia_amministrativa.options[document.formOptions.id_tipologia_amministrativa.selectedIndex].text;
}
</SCRIPT>

<script>
<jsp:include page="dynaselect/regioniItalia.jsp"></jsp:include>
</script>

</head>

<BODY TOPMARGIN="2" MARGINHEIGHT="2"  onload="calcolo(false);setTipAmmDescrizione();" LINK="BLUE" ALINK="BLUE" VLINK="BLUE" TOPMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" LEFTMARGIN="0" bgproperties="fixed">
<FORM ACTION="parametri.jsp" METHOD="GET" NAME="formOptions">


<P><DIV align="center">
<!----------------------------------------TABELLA--------------------------------------------->

<TABLE BORDER="0" WIDTH="100%" align="center" height="100%" cellspacing="0">
<TR><TD align="center">
		<TABLE border="1" WIDTH="80%"><!--- 70 --->
		<TR><TD WIDTH="100%"><CENTER>
<div align="center">
<TABLE BORDER="0" WIDTH="80%">

<TR>	<TD ALIGN="center" VALIGN="top" width="5%" class="logoL"><img src="<c:url value="/images/iccuLogoLittle.gif" />" alt="" border="0"><BR>ICCU</TD>
<TD ALIGN="center" VALIGN="middle" width="70%">
		<FONT color='#e27e10' face='Arial' size='5'><B>ANAGRAFE BIBLIOTECHE ITALIANE</B></font><br>

		<FONT color='#e27e10' face='Arial' size='4'><b>- Ricerca avanzata -</b></font></TD>
<TD width="5%">&nbsp;</TD>
</TABLE>
</div><HR ALIGN="CENTER">
<TABLE BORDER="0" WIDTH="100%">
<TR><TD align="right" height="1"><font size="1">&nbsp;</font></TD></TR>
</TABLE>
<TABLE BORDER="0" width="100%">
<!--tr>
<td align="left"><font SIZE ="2" COLOR = "RED"</font>&nbsp;I dati per i campi contrassegnati con *&nbsp;saranno disponibili con i successivi aggiornamenti.</td>
</tr-->
</TABLE>
<!-------------------------------------------------------------------------------------------->

<TABLE border="0" width="100%">
<TR>
	<!-- TD CLASS="filtriRight2" width="25%">Regione&nbsp;</TD-->

    <TD COLSPAN="1" ALIGN="LEFT" width="25%">
		<SELECT NAME="regione" style="display:none;" onchange="calcolo(true)">
			<OPTION VALUE="PUGLIA" SELECTED></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="regione"/></jsp:include>
		</SELECT>
	</TD>
    <!-- <TD COLSPAN="1" ALIGN="LEFT" width="1%">&nbsp;</TD> -->
</TR>
<TR>
     <TD CLASS="filtriRight2">Provincia&nbsp;</TD>
     <TD  colspan="2" ALIGN="LEFT">

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
     <TD COLSPAN="2" ALIGN="RIGHT" width="50%"><INPUT TYPE="SUBMIT" NAME="AVVIA" VALUE="Avvia ricerca">&nbsp;<INPUT TYPE="BUTTON" NAME="CANCELLA" VALUE="Cancella" onClick="cancella()"></TD><!--r.eschini per ripristinare tutte le regioni/province -->
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
<TR>
	<TD CLASS="filtriRight2">Denominazione&nbsp;

		 <A HREF="#" onclick="helpMessage(13, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></TD></TD>
	<TD colspan="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.denominazione_forsearch) > 0}">
				<INPUT TYPE="TEXT" NAME="denominazione_forsearch" SIZE="55" MAXLENGTH="150" VALUE="${param.denominazione_forsearch}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="denominazione_forsearch" SIZE="55" MAXLENGTH="150">
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
	<TD CLASS="filtriRight2">Ente di appartenenza&nbsp;</TD>
	<TD colspan="3" ALIGN="LEFT"> 
		<c:choose>
			<c:when test="${fn:length(param.ente_denominazione) > 0}">
				<INPUT TYPE="TEXT" NAME="ente_denominazione" SIZE="55" MAXLENGTH="150" VALUE="${param.ente_denominazione}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="ente_denominazione" SIZE="55" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>	
	
		<SELECT NAME="ente_denominazione:tipo">
		<%
			String ente_denominazione_tipo = request.getParameter("ente_denominazione:tipo");
			if ("AND".equalsIgnoreCase(ente_denominazione_tipo)) {
				%>
				<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
				<OPTION VALUE="OR">Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("OR".equalsIgnoreCase(ente_denominazione_tipo)) {
				%>
				<OPTION VALUE="AND">Tutte le parole</OPTION>
				<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("ONLY".equalsIgnoreCase(ente_denominazione_tipo)) {
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
	<TD CLASS="filtriRight2" nowrap>Tipologia amministrativa&nbsp;
		<A HREF="#" onclick="helpMessage(1, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>

	</TD>
	<TD colspan="3" ALIGN="LEFT">
		<INPUT TYPE="HIDDEN" NAME="tipologia_amministrativa" />
		<SELECT NAME="id_tipologia_amministrativa" onchange="setTipAmmDescrizione()">
			<OPTION VALUE=""></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="ente_tipologia_amministrativa"/></jsp:include>
		</SELECT>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">Tipologia funzionale&nbsp;
		<A HREF="#" onclick="helpMessage(0, 400, 200);return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>

	</TD>
	<TD colspan="3" ALIGN="LEFT">
		
		<SELECT NAME="tipologia_funzionale">
			<OPTION VALUE=""></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="tipologia_funzionale"/></jsp:include>
		</SELECT>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" nowrap><font color="Red">&nbsp;</font>Destinazione sociale&nbsp;

		<A HREF="#" onclick="helpMessage(3, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<SELECT NAME="destinazioni_sociali">
			<OPTION VALUE="" SELECTED></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="destinazioni_sociali_tipo"/></jsp:include>
		</SELECT>
	</TD>
</TR>
<TR>
	<TD colspan="4">
	<table width="100%" border="0">
		<TR>

			<TD CLASS="filtriRight2" nowrap width="40%"><font color="Red">&nbsp;</font>Access. port. di handicap</TD>
				<c:choose>
					<c:when test="${param.accesso_handicap=='true'}">
						<TD COLSPAN="3" ALIGN="LEFT" width="60%">&nbsp;<input type="checkbox" name="accesso_handicap" value="true" CHECKED>
						</TD>
					</c:when>
					<c:otherwise>
						<TD COLSPAN="3" ALIGN="LEFT" width="60%">&nbsp;<input type="checkbox" name="accesso_handicap" value="true">
						</TD>			
					</c:otherwise>
				</c:choose>
		</TR>
		<tr>
			<TD CLASS="filtriRight2" COLSPAN="1" width="40%">Accesso</TD>

			<TD COLSPAN="3" ALIGN="LEFT" width="60%"><FONT SIZE="1" FACE="Verdana">
				<c:choose>
					<c:when test="${param.accesso_riservato=='false'}">
						&nbsp;<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false" CHECKED><b>Aperta a tutti</b>
						<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true"><b>Riservata</b>
					</c:when>
					<c:when test="${param.accesso_riservato=='true'}">
						&nbsp;<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false"><b>Aperta a tutti</b>
						<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true" CHECKED><b>Riservata</b>
					</c:when>
					<c:otherwise>
						&nbsp;<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="false"><b>Aperta a tutti</b>
						<INPUT TYPE="RADIO" NAME="accesso_riservato" VALUE="true"><b>Riservata</b>
					</c:otherwise>
				</c:choose>
			</TD>
		</TR>
		<TR>
			<TD COLSPAN="1">
				<P ALIGN="RIGHT"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">

				Orario di apertura</FONT></B>
			</TD>
			<TD COLSPAN="3" ALIGN="LEFT">&nbsp;
			<SELECT NAME="orario_ufficiale_fasce">
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
			</td>
		</tr>
		</table>
	</TD>
</TR>

<TR><td colspan="4">&nbsp;</td></tr>
<TR>
	<TD bgcolor="#E6E6E6" colspan="4"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		Specializzazione della biblioteca</B></FONT>&nbsp;
		<A HREF="#" onclick="helpMessage(2, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
</TR>
<TR>
	<TD>
		<DIV ALIGN="RIGHT">

		<P><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
			<a href="Javascript:helpCodiceDewey('codice_dewey')">
				Codice Dewey</a>
<A HREF="#" onclick="helpMessage(14, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>

			  </FONT></B>
		</DIV>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.codice_dewey) > 0}">
				<INPUT TYPE="TEXT" NAME="codice_dewey" SIZE="10" maxlength="7" VALUE="${param.codice_dewey}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="codice_dewey" SIZE="10" maxlength="7">
			</c:otherwise>
		</c:choose>
	</TD>

</TR>
<TR>
	<TD WIDTH="25%" align="right"><B><FONT SIZE="1" COLOR="blue" FACE="Verdana">
			<a href="Javascript:helpCodiceDewey('DEWEYDA')">
				da codice Dewey</a></FONT></B>
			<A HREF="#" onclick="helpMessage(15, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD width="25%" align="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.DEWEYDA) > 0}">
				<INPUT TYPE="TEXT" NAME="DEWEYDA" SIZE="10" maxlength="7" VALUE="${param.DEWEYDA}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="DEWEYDA" SIZE="10" maxlength="7">
			</c:otherwise>
		</c:choose>
	</TD>

	<TD width="15%" align="right" nowrap>
	<B><FONT SIZE="1" COLOR="blue" FACE="Verdana">
			<a href="Javascript:helpCodiceDewey('DEWEYA')">
				a codice Dewey</a></FONT></B>
	</TD>
	<TD WIDTH="35%" align="Left">
		<c:choose>
			<c:when test="${fn:length(param.DEWEYA) > 0}">
				<INPUT TYPE="TEXT" NAME="DEWEYA" SIZE="10" maxlength="7" VALUE="${param.DEWEYA}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="DEWEYA" SIZE="10" maxlength="7">
			</c:otherwise>
		</c:choose>
	</TD>
</TR>
<TR>

	<TD NOWRAP><P ALIGN="RIGHT"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		<a href="Javascript:helpDeweyUff()">
		Descrizione ufficiale</a></FONT></B>
		 <A HREF="#" onclick="helpMessage(16, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.descrizione_ufficiale) > 0}">
				<INPUT TYPE="TEXT" NAME="descrizione_ufficiale" SIZE="55" MAXLENGTH="150" VALUE="${param.descrizione_ufficiale}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="descrizione_ufficiale" SIZE="55" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>
		<SELECT NAME="descrizione_ufficiale:tipo">
			<%
			String descrizione_ufficiale_tipo = request.getParameter("descrizione_ufficiale:tipo");
			if ("AND".equalsIgnoreCase(descrizione_ufficiale_tipo)) {
				%>
				<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
				<OPTION VALUE="OR">Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("OR".equalsIgnoreCase(descrizione_ufficiale_tipo)) {
				%>
				<OPTION VALUE="AND">Tutte le parole</OPTION>
				<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("ONLY".equalsIgnoreCase(descrizione_ufficiale_tipo)) {
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
	<TD NOWRAP>

		<P ALIGN="RIGHT"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">Descrizione libera </FONT></B>
				 <A HREF="#" onclick="helpMessage(17, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN=3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.descrizione_libera) > 0}">
				<INPUT TYPE="TEXT" NAME="descrizione_libera" SIZE="55" MAXLENGTH="150" VALUE="${param.descrizione_libera}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="descrizione_libera" SIZE="55" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>
		<SELECT NAME="descrizione_libera:tipo">
			<%
			String descrizione_libera_tipo = request.getParameter("descrizione_libera:tipo");
			if ("AND".equalsIgnoreCase(descrizione_libera_tipo)) {
				%>
				<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
				<OPTION VALUE="OR">Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("OR".equalsIgnoreCase(descrizione_libera_tipo)) {
				%>
				<OPTION VALUE="AND">Tutte le parole</OPTION>
				<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("ONLY".equalsIgnoreCase(descrizione_libera_tipo)) {
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
<TR><TD colspan="4">&nbsp;</td></tr>
<TR>
	<TD bgcolor="#E6E6E6" colspan="4">&nbsp;</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" nowrap><a href="Javascript:helpSistemi()">
		Sistema di biblioteche</a>&nbsp;</TD>

	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.sistemi_biblioteche) > 0}">
				<INPUT TYPE="TEXT" NAME="sistemi_biblioteche" SIZE="55" MAXLENGTH="150" VALUE="${param.sistemi_biblioteche}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="sistemi_biblioteche" SIZE="55" MAXLENGTH="150">		
			</c:otherwise>
		</c:choose>
		
	
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">
		<a href="Javascript:helpSezSpez()">
		Sezione speciale</a>&nbsp;</TD>
	<TD colspan="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.sezioni_speciali) > 0}">
				<INPUT TYPE="TEXT" NAME="sezioni_speciali" SIZE="55" MAXLENGTH="150" VALUE="${param.sezioni_speciali}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="sezioni_speciali" SIZE="55" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>
	</TD>
</TR>
<TR><TD COLSPAN="4">&nbsp;</TD></TR>

<TR>
	<TD bgcolor="#E6E6E6" colspan="4"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		Patrimonio librario - Cataloghi - Fondi speciali</B></FONT>&nbsp;
		<!--A HREF="#" onclick="helpMessage(4, 400, 200); return false"><IMG SRC="/imagesOAS/../images/info.gif" ALIGN="CENTER" BORDER="0"></A-->
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" nowrap>
		<a href="Javascript:helpPatrimonio()">Patrimonio librario</a>

		<A HREF="#" onclick="helpMessage(18, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>

	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.patrimonio_librario_descr) > 0}">
				<INPUT TYPE="TEXT" NAME="patrimonio_librario_descr" SIZE="55" MAXLENGTH="150" VALUE="${param.patrimonio_librario_descr}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="patrimonio_librario_descr" SIZE="55" MAXLENGTH="150">
			</c:otherwise>
		</c:choose>		
	</TD>

</TR>
<TR>
	<TD CLASS="filtriRight2">
		Catalogo generale
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<SELECT NAME="catalogo_generale_tipo">
			<OPTION VALUE=""></OPTION>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="catalogo_generale_tipo"/></jsp:include>
		</SELECT>
	</TD>
</TR>

<TR>
	<TD CLASS="filtriRight2">
		<a href="Javascript:helpCatalogoSpec()">
		Catalogo speciale</a>&nbsp;
		<A HREF="#" onclick="helpMessage(6, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.cataloghi_speciali_descr) > 0}">
				<INPUT TYPE="TEXT" NAME="cataloghi_speciali_descr" SIZE="55" VALUE="${param.cataloghi_speciali_descr}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="cataloghi_speciali_descr" SIZE="55">
			</c:otherwise>
		</c:choose>
	</TD>

</TR>
<TR>
	<TD CLASS="filtriRight2">
		<a href="Javascript:helpCatalogoColl()">
		Catalogo collettivo</a>&nbsp;
		<A HREF="#" onclick="helpMessage(7, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.cataloghi_collettivi_descr) > 0}">
				<INPUT TYPE="TEXT" NAME="cataloghi_collettivi_descr" SIZE="55" VALUE="${param.cataloghi_collettivi_descr}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="cataloghi_collettivi_descr" SIZE="55">
			</c:otherwise>
		</c:choose>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2">Denominazione fondo
	<A HREF="#" onclick="helpMessage(19, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.fondi_speciali_denominazione) > 0}">
				<INPUT TYPE="TEXT" NAME="fondi_speciali_denominazione" SIZE="55" VALUE="${param.fondi_speciali_denominazione}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="fondi_speciali_denominazione" SIZE="55">
			</c:otherwise>
		</c:choose>
		<SELECT NAME="fondi_speciali_denominazione:tipo">
			<%
			String fondi_speciali_denominazione_tipo = request.getParameter("fondi_speciali_denominazione:tipo");
			if ("AND".equalsIgnoreCase(fondi_speciali_denominazione_tipo)) {
				%>
				<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
				<OPTION VALUE="OR">Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("OR".equalsIgnoreCase(fondi_speciali_denominazione_tipo)) {
				%>
				<OPTION VALUE="AND">Tutte le parole</OPTION>
				<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("ONLY".equalsIgnoreCase(fondi_speciali_denominazione_tipo)) {
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
	<TD CLASS="filtriRight2">Descrizione fondo
	<A HREF="#" onclick="helpMessage(20, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A>
	</TD>

	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.fondi_speciali_descrizione) > 0}">
				<INPUT TYPE="TEXT" NAME="fondi_speciali_descrizione" SIZE="55" VALUE="${param.fondi_speciali_descrizione}">
			</c:when>
			<c:otherwise>
				<INPUT TYPE="TEXT" NAME="fondi_speciali_descrizione" SIZE="55">
			</c:otherwise>
		</c:choose>
		
		<SELECT NAME="fondi_speciali_descrizione:tipo">
			<%
			String fondi_speciali_descrizione_tipo = request.getParameter("fondi_speciali_descrizione:tipo");
			if ("AND".equalsIgnoreCase(fondi_speciali_descrizione_tipo)) {
				%>
				<OPTION VALUE="AND" SELECTED>Tutte le parole</OPTION>
				<OPTION VALUE="OR">Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("OR".equalsIgnoreCase(fondi_speciali_descrizione_tipo)) {
				%>
				<OPTION VALUE="AND">Tutte le parole</OPTION>
				<OPTION VALUE="OR" SELECTED>Qualsiasi parola</OPTION>
				<OPTION VALUE="ONLY">Frase esatta</OPTION>
				<%
			} else if ("ONLY".equalsIgnoreCase(fondi_speciali_descrizione_tipo)) {
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
<!--TR>
	<TD CLASS="filtriRight2">
		<a href="Javascript:helpCodiceDewey(document.formOptions.CODDEWEYFONDI)">
		Codice Dewey</a>&nbsp;
		<A HREF="#" onclick="helpMessage(5, 400, 200); return false"><IMG SRC="/imagesOAS/../images/info.gif" ALIGN="CENTER" BORDER="0"></A>
	</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<INPUT TYPE="TEXT" NAME="CODDEWEYFONDI" SIZE="10" maxlength="7">
	</TD>
</TR-->
<TR>
	<TD CLASS="filtriRight2" nowrap>Spogli materiale bibliografico&nbsp;<A HREF="#" onclick="helpMessage(8, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<SELECT NAME="spogli_bibliografici">
			<OPTION VALUE=""></OPTION>
			<%--
			<OPTION VALUE="56593 articoli di periodici e monografie riguardanti la geologia">56593 articoli di periodici e monografie riguardanti la geologia</OPTION>
			<OPTION VALUE="Annali della Facoltà di agraria di Perugia">Annali della Facoltà di agraria di Perugia</OPTION>
			<OPTION VALUE="annali della Facoltà di lettere e filosofia">annali della Facoltà di lettere e filosofia</OPTION>
			<OPTION VALUE="antologie">antologie</OPTION>
			<OPTION VALUE="Antologie e raccolte">Antologie e raccolte</OPTION>
			<OPTION VALUE="Antologie musicali">Antologie musicali</OPTION>
			<OPTION VALUE="Argomenti di interesse locale">Argomenti di interesse locale</OPTION>
			<OPTION VALUE="Argomenti di storia locale">Argomenti di storia locale</OPTION>
			<OPTION VALUE="articoli dai periodici nazionali d'interesse locale e regionale">articoli dai periodici nazionali d'interesse locale e regionale</OPTION>
			<OPTION VALUE="articoli da periodici nazionali d'interesse locale e regionale">articoli da periodici nazionali d'interesse locale e regionale</OPTION>
			<OPTION VALUE="articoli da quotidiani su atletica leggera e olimpiadi">articoli da quotidiani su atletica leggera e olimpiadi</OPTION>
			<OPTION VALUE="articoli di cronaca locale da riviste e quotidiani">articoli di cronaca locale da riviste e quotidiani</OPTION>
			<OPTION VALUE="articoli di epatologia">articoli di epatologia</OPTION>
			<OPTION VALUE="articoli di periodici">articoli di periodici</OPTION>
			<OPTION VALUE="Articoli di riviste e capitoli di miscellanee di particolare interesse per">Articoli di riviste e capitoli di miscellanee di particolare interesse per</OPTION>
			<OPTION VALUE="articoli di riviste teologiche">articoli di riviste teologiche</OPTION>
			<OPTION VALUE="articoli di statistica">articoli di statistica</OPTION>
			<OPTION VALUE="articoli e interventi di Fernanda Pivano">articoli e interventi di Fernanda Pivano</OPTION>
			<OPTION VALUE="articoli sui beni culturali e ambientali nell'area milanese">articoli sui beni culturali e ambientali nell'area milanese</OPTION>
			<OPTION VALUE="Articoli su Luciano Minguzzi">Articoli su Luciano Minguzzi</OPTION>
			<OPTION VALUE="atti">atti</OPTION>
			<OPTION VALUE="Atti convegni; periodici">Atti convegni; periodici</OPTION>
			<OPTION VALUE="atti di congressi">atti di congressi</OPTION>
			<OPTION VALUE="Atti di congressi">Atti di congressi</OPTION>
			<OPTION VALUE="Atti di congressi, miscellanee, opere di più autori">Atti di congressi, miscellanee, opere di più autori</OPTION>
			<OPTION VALUE="atti di congressi, periodici">atti di congressi, periodici</OPTION>
			<OPTION VALUE="atti di congressi; scritti in onore di; miscellanee">atti di congressi; scritti in onore di; miscellanee</OPTION>
			<OPTION VALUE="atti di congressi, studi in onore">atti di congressi, studi in onore</OPTION>
			<OPTION VALUE="atti di convegni">atti di convegni</OPTION>
			<OPTION VALUE="Atti di convegni">Atti di convegni</OPTION>
			<OPTION VALUE="Atti di convegni e conferenze">Atti di convegni e conferenze</OPTION>
			<OPTION VALUE="atti di convegni in periodici o numeri monografici di periodici">atti di convegni in periodici o numeri monografici di periodici</OPTION>
			<OPTION VALUE="atti di convegni; opere collettive">atti di convegni; opere collettive</OPTION>
			<OPTION VALUE="atti di convegni: storia locale">atti di convegni: storia locale</OPTION>
			<OPTION VALUE="atti di convegno">atti di convegno</OPTION>
			<OPTION VALUE="Atti e memorie dell'Accademia delle Scienze sino al 1970">Atti e memorie dell'Accademia delle Scienze sino al 1970</OPTION>
			<OPTION VALUE="Banche dati biomediche">Banche dati biomediche</OPTION>
			<OPTION VALUE="Bibliografia francescana">Bibliografia francescana</OPTION>
			<OPTION VALUE="bibliografie">bibliografie</OPTION>
			<OPTION VALUE="Bibliografie: gesuiti">Bibliografie: gesuiti</OPTION>
			<OPTION VALUE="bibliografie: museologia">bibliografie: museologia</OPTION>
			<OPTION VALUE="Biblioteca Francescana Sarde 1987-1997">Biblioteca Francescana Sarde 1987-1997</OPTION>
			<OPTION VALUE="Bollettino del Museo regionale di scienze naturali">Bollettino del Museo regionale di scienze naturali</OPTION>
			<OPTION VALUE="Carta Braille">Carta Braille</OPTION>
			<OPTION VALUE="cataloghi">cataloghi</OPTION>
			<OPTION VALUE="Cataloghi">Cataloghi</OPTION>
			<OPTION VALUE="cataloghi d'asta">cataloghi d'asta</OPTION>
			<OPTION VALUE="Cataloghi di editori cinesi e di altri paesi">Cataloghi di editori cinesi e di altri paesi</OPTION>
			<OPTION VALUE="collane">collane</OPTION>
			<OPTION VALUE="Collane">Collane</OPTION>
			<OPTION VALUE="composizioni">composizioni</OPTION>
			<OPTION VALUE="Conferenze; congressi; quaderni.">Conferenze; congressi; quaderni.</OPTION>
			<OPTION VALUE="corriere dell'UNESCO">corriere dell'UNESCO</OPTION>
			<OPTION VALUE="corsi e concorsi musicali">corsi e concorsi musicali</OPTION>
			<OPTION VALUE="Current contents on diskette J1200">Current contents on diskette J1200</OPTION>
			<OPTION VALUE="Current contents periodici attivi (selezioni)">Current contents periodici attivi (selezioni)</OPTION>
			<OPTION VALUE="dossier">dossier</OPTION>
			<OPTION VALUE="enciclopedie">enciclopedie</OPTION>
			<OPTION VALUE="enciclopedie tematiche">enciclopedie tematiche</OPTION>
			<OPTION VALUE="Festschriftes">Festschriftes</OPTION>
			<OPTION VALUE="fondo messano-calabrese">fondo messano-calabrese</OPTION>
			<OPTION VALUE="Gazzette Ufficiali della Repubblica, Gazzette Ufficiali della Regione">Gazzette Ufficiali della Repubblica, Gazzette Ufficiali della Regione</OPTION>
			<OPTION VALUE="giornali">giornali</OPTION>
			<OPTION VALUE="Giornali">Giornali</OPTION>
			<OPTION VALUE="giornali e periodici">giornali e periodici</OPTION>
			<OPTION VALUE="Giornali e periodici">Giornali e periodici</OPTION>
			<OPTION VALUE="giornali e riviste">giornali e riviste</OPTION>
			<OPTION VALUE="giornali (Gazzetta del Mezzogiorno): storia e cultura di Ceglie Messapica">giornali (Gazzetta del Mezzogiorno): storia e cultura di Ceglie Messapica</OPTION>
			<OPTION VALUE="Giornali; periodici; atti di congressi.">Giornali; periodici; atti di congressi.</OPTION>
			<OPTION VALUE="Il Santuario di Montevergine : bollettino mensile illustrato. - A. 1, n. 1(15 maggio 1920)-; Vanità :  supplemento settimanale del quotidiano irpino Ottopagine.">Il Santuario di Montevergine : bollettino mensile illustrato. - A. 1, n. 1(15 maggio 1920)-; Vanità :  supplemento settimanale del quotidiano irpino Ottopagine.</OPTION>
			<OPTION VALUE="Index medicus">Index medicus</OPTION>
			<OPTION VALUE="Indici Bergomum">Indici Bergomum</OPTION>
			<OPTION VALUE="Indici degli atti dell'Ateneo">Indici degli atti dell'Ateneo</OPTION>
			<OPTION VALUE="Indici della Rivista di Bergamo">Indici della Rivista di Bergamo</OPTION>
			<OPTION VALUE="indici di riviste">indici di riviste</OPTION>
			<OPTION VALUE="Indici e sommari delle riviste di architettura">Indici e sommari delle riviste di architettura</OPTION>
			<OPTION VALUE="Inserti speciali e numeri unici.">Inserti speciali e numeri unici.</OPTION>
			<OPTION VALUE="la biblioteca">la biblioteca</OPTION>
			<OPTION VALUE="Le francaise dans le monde">Le francaise dans le monde</OPTION>
			<OPTION VALUE="Le Monde des livres">Le Monde des livres</OPTION>
			<OPTION VALUE="letteratura grigia">letteratura grigia</OPTION>
			<OPTION VALUE="letteratura specializzata nazionale e internazionale">letteratura specializzata nazionale e internazionale</OPTION>
			<OPTION VALUE="Liberation (livres)">Liberation (livres)</OPTION>
			<OPTION VALUE="libri e riviste specializzate, monografie,ABSTRACT">libri e riviste specializzate, monografie,ABSTRACT</OPTION>
			<OPTION VALUE="Libri; riviste; documenti; quotidiani">Libri; riviste; documenti; quotidiani</OPTION>
			<OPTION VALUE="Maggiori periodici italiani di scienze della terra ed ambientali ed Atti di Congressi attinenti alle materie già indicate">Maggiori periodici italiani di scienze della terra ed ambientali ed Atti di Congressi attinenti alle materie già indicate</OPTION>
			<OPTION VALUE="materiale della sezione locale">materiale della sezione locale</OPTION>
			<OPTION VALUE="Materiale di interesse ligure">Materiale di interesse ligure</OPTION>
			<OPTION VALUE="materiale di interesse locale">materiale di interesse locale</OPTION>
			<OPTION VALUE="materiale d'interesse regionale">materiale d'interesse regionale</OPTION>
			<OPTION VALUE="materiale locale">materiale locale</OPTION>
			<OPTION VALUE="Materiale riguardante la sezione di Storia locale">Materiale riguardante la sezione di Storia locale</OPTION>
			<OPTION VALUE="Materiale specializzato">Materiale specializzato</OPTION>
			<OPTION VALUE="Materiale specializzato relativo a manoscritti e opere rare possedute">Materiale specializzato relativo a manoscritti e opere rare possedute</OPTION>
			<OPTION VALUE="materiale sulla storia locale">materiale sulla storia locale</OPTION>
			<OPTION VALUE="Materiale vario di storia locale">Materiale vario di storia locale</OPTION>
			<OPTION VALUE="materiali per la bibliografia veronese e per BIBMAN">materiali per la bibliografia veronese e per BIBMAN</OPTION>
			<OPTION VALUE="Medline CD">Medline CD</OPTION>
			<OPTION VALUE="Medline INTERNET">Medline INTERNET</OPTION>
			<OPTION VALUE="miscellane">miscellane</OPTION>
			<OPTION VALUE="miscellanea">miscellanea</OPTION>
			<OPTION VALUE="miscellanea e saggistica">miscellanea e saggistica</OPTION>
			<OPTION VALUE="miscellanee">miscellanee</OPTION>
			<OPTION VALUE="Miscellanee">Miscellanee</OPTION>
			<OPTION VALUE="miscellanee, antologie, atti, riviste">miscellanee, antologie, atti, riviste</OPTION>
			<OPTION VALUE="miscellanee; atti di congressi">miscellanee; atti di congressi</OPTION>
			<OPTION VALUE="Miscellanee, atti di convegni, periodici">Miscellanee, atti di convegni, periodici</OPTION>
			<OPTION VALUE="miscellanee, cataloghi di esposizioni d'arte">miscellanee, cataloghi di esposizioni d'arte</OPTION>
			<OPTION VALUE="miscellanee di argomento scientifico del sec. 19">miscellanee di argomento scientifico del sec. 19</OPTION>
			<OPTION VALUE="miscellanee di carattere teologico e filosofico">miscellanee di carattere teologico e filosofico</OPTION>
			<OPTION VALUE="miscellanee di letteratura musicale e musica">miscellanee di letteratura musicale e musica</OPTION>
			<OPTION VALUE="miscellanee di linguistica">miscellanee di linguistica</OPTION>
			<OPTION VALUE="miscellanee specializzate">miscellanee specializzate</OPTION>
			<OPTION VALUE="Miscellanee specializzate">Miscellanee specializzate</OPTION>
			<OPTION VALUE="Miscellanee, studi in onore">Miscellanee, studi in onore</OPTION>
			<OPTION VALUE="monografie">monografie</OPTION>
			<OPTION VALUE="Monografie">Monografie</OPTION>
			<OPTION VALUE="monografie, atti di congressi">monografie, atti di congressi</OPTION>
			<OPTION VALUE="Monografie collettanee">Monografie collettanee</OPTION>
			<OPTION VALUE="monografie di argomento padovano">monografie di argomento padovano</OPTION>
			<OPTION VALUE="monografie di interesse locale">monografie di interesse locale</OPTION>
			<OPTION VALUE="Monografie di interesse locale; Periodici">Monografie di interesse locale; Periodici</OPTION>
			<OPTION VALUE="Monografie: Donna">Monografie: Donna</OPTION>
			<OPTION VALUE="monografie e periodici">monografie e periodici</OPTION>
			<OPTION VALUE="Monografie e periodici">Monografie e periodici</OPTION>
			<OPTION VALUE="Monografie e periodici: antifascismo e Piero Gobetti">Monografie e periodici: antifascismo e Piero Gobetti</OPTION>
			<OPTION VALUE="Monografie e periodici: informatica">Monografie e periodici: informatica</OPTION>
			<OPTION VALUE="Monografie e periodici: Rosmini, Sciacca, Rebora">Monografie e periodici: Rosmini, Sciacca, Rebora</OPTION>
			<OPTION VALUE="monografie e periodici specializzati">monografie e periodici specializzati</OPTION>
			<OPTION VALUE="monografie e periodici : speleologia">monografie e periodici : speleologia</OPTION>
			<OPTION VALUE="monografie: interesse locale e letteratura">monografie: interesse locale e letteratura</OPTION>
			<OPTION VALUE="monografie: manoscritti riccardiani">monografie: manoscritti riccardiani</OPTION>
			<OPTION VALUE="monografie, periodici">monografie, periodici</OPTION>
			<OPTION VALUE="Monografie, periodici">Monografie, periodici</OPTION>
			<OPTION VALUE="monografie; periodici">monografie; periodici</OPTION>
			<OPTION VALUE="Monografie; periodici">Monografie; periodici</OPTION>
			<OPTION VALUE="Monografie, periodici e miscellanee di musica">Monografie, periodici e miscellanee di musica</OPTION>
			<OPTION VALUE="monografie, periodici: storia locale">monografie, periodici: storia locale</OPTION>
			<OPTION VALUE="Monografie prodotte dall'Istituto">Monografie prodotte dall'Istituto</OPTION>
			<OPTION VALUE="monografie sezione locale">monografie sezione locale</OPTION>
			<OPTION VALUE="monografie: spettacolo; periodici">monografie: spettacolo; periodici</OPTION>
			<OPTION VALUE="monografie: storia locale">monografie: storia locale</OPTION>
			<OPTION VALUE="normative regionali e nazionali">normative regionali e nazionali</OPTION>
			<OPTION VALUE="notizie locali">notizie locali</OPTION>
			<OPTION VALUE="nuove acquisizioni">nuove acquisizioni</OPTION>
			<OPTION VALUE="opere collettanee">opere collettanee</OPTION>
			<OPTION VALUE="Opere collettanee">Opere collettanee</OPTION>
			<OPTION VALUE="opere collettive">opere collettive</OPTION>
			<OPTION VALUE="Opere collettive">Opere collettive</OPTION>
			<OPTION VALUE="opere di autori vari">opere di autori vari</OPTION>
			<OPTION VALUE="opere di consultazione">opere di consultazione</OPTION>
			<OPTION VALUE="Opere di interesse locale.">Opere di interesse locale.</OPTION>
			<OPTION VALUE="opere di più autori">opere di più autori</OPTION>
			<OPTION VALUE="Opere di più autori">Opere di più autori</OPTION>
			<OPTION VALUE="opere di più autori, atti di congressi, resoconti di ricerche, periodici">opere di più autori, atti di congressi, resoconti di ricerche, periodici</OPTION>
			<OPTION VALUE="opere miscellanee di interesse calabro">opere miscellanee di interesse calabro</OPTION>
			<OPTION VALUE="Opere poligrafe">Opere poligrafe</OPTION>
			<OPTION VALUE="opere poligrafe; miscellanee; monografie">opere poligrafe; miscellanee; monografie</OPTION>
			<OPTION VALUE="parte delle pubblicazioni periodiche">parte delle pubblicazioni periodiche</OPTION>
			<OPTION VALUE="periodici">periodici</OPTION>
			<OPTION VALUE="Periodici">Periodici</OPTION>
			<OPTION VALUE="Periodici abruzzesi di interesse locale">Periodici abruzzesi di interesse locale</OPTION>
			<OPTION VALUE="Periodici a carattere religioso">Periodici a carattere religioso</OPTION>
			<OPTION VALUE="Periodici: Ambiente, Aree protette">Periodici: Ambiente, Aree protette</OPTION>
			<OPTION VALUE="Periodici anglistica, francesistica, germanistica dal 1970 al 1982">Periodici anglistica, francesistica, germanistica dal 1970 al 1982</OPTION>
			<OPTION VALUE="Periodici: architettura">Periodici: architettura</OPTION>
			<OPTION VALUE="Periodici: archivistica">Periodici: archivistica</OPTION>
			<OPTION VALUE="periodici: aree protette">periodici: aree protette</OPTION>
			<OPTION VALUE="periodici: arte">periodici: arte</OPTION>
			<OPTION VALUE="Periodici: articoli sull'edilizia bibliotecaria">Periodici: articoli sull'edilizia bibliotecaria</OPTION>
			<OPTION VALUE="Periodici: ascensioni extraeuropee">Periodici: ascensioni extraeuropee</OPTION>
			<OPTION VALUE="Periodici; Atti di convegni">Periodici; Atti di convegni</OPTION>
			<OPTION VALUE="Periodici; cataloghi; monografie specializzate">Periodici; cataloghi; monografie specializzate</OPTION>
			<OPTION VALUE="periodici: cinema">periodici: cinema</OPTION>
			<OPTION VALUE="periodici di ambito educativo">periodici di ambito educativo</OPTION>
			<OPTION VALUE="Periodici di archivistica 1965-1997">Periodici di archivistica 1965-1997</OPTION>
			<OPTION VALUE="periodici di argomento musicale">periodici di argomento musicale</OPTION>
			<OPTION VALUE="periodici di economia">periodici di economia</OPTION>
			<OPTION VALUE="periodici di economia e scienze sociali">periodici di economia e scienze sociali</OPTION>
			<OPTION VALUE="periodici di interesse locale">periodici di interesse locale</OPTION>
			<OPTION VALUE="Periodici di interesse locale">Periodici di interesse locale</OPTION>
			<OPTION VALUE="Periodici di interesse locale; monografie">Periodici di interesse locale; monografie</OPTION>
			<OPTION VALUE="Periodici di storia economica e sociale locale dal 1815 ad oggi">Periodici di storia economica e sociale locale dal 1815 ad oggi</OPTION>
			<OPTION VALUE="periodici di storia locale">periodici di storia locale</OPTION>
			<OPTION VALUE="Periodici e antologie">Periodici e antologie</OPTION>
			<OPTION VALUE="periodici e atti di convegni">periodici e atti di convegni</OPTION>
			<OPTION VALUE="Periodici educativi">Periodici educativi</OPTION>
			<OPTION VALUE="Periodici e giorali: interesse storico-artistico">Periodici e giorali: interesse storico-artistico</OPTION>
			<OPTION VALUE="periodici e giornali artistico-letterari e politico-letterari">periodici e giornali artistico-letterari e politico-letterari</OPTION>
			<OPTION VALUE="periodici e giornali sardi">periodici e giornali sardi</OPTION>
			<OPTION VALUE="periodici e giornali specializzati">periodici e giornali specializzati</OPTION>
			<OPTION VALUE="Periodici e miscellanea di interesse locale 1978-1997">Periodici e miscellanea di interesse locale 1978-1997</OPTION>
			<OPTION VALUE="periodici e miscellanee d'interesse locale">periodici e miscellanee d'interesse locale</OPTION>
			<OPTION VALUE="periodici e monografie">periodici e monografie</OPTION>
			<OPTION VALUE="periodici e monografie di interesse locale">periodici e monografie di interesse locale</OPTION>
			<OPTION VALUE="Periodici e monografie di interesse locale">Periodici e monografie di interesse locale</OPTION>
			<OPTION VALUE="periodici e monografie d'interesse locale">periodici e monografie d'interesse locale</OPTION>
			<OPTION VALUE="periodici ESSPER">periodici ESSPER</OPTION>
			<OPTION VALUE="periodici (ESSPER)">periodici (ESSPER)</OPTION>
			<OPTION VALUE="periodici e volumi su Napoli e Regno di Napoli">periodici e volumi su Napoli e Regno di Napoli</OPTION>
			<OPTION VALUE="Periodici: Flora e Fauna">Periodici: Flora e Fauna</OPTION>
			<OPTION VALUE="periodici, giornali">periodici, giornali</OPTION>
			<OPTION VALUE="Periodici; giornali">Periodici; giornali</OPTION>
			<OPTION VALUE="Periodici; giornali.">Periodici; giornali.</OPTION>
			<OPTION VALUE="periodici giuridici italiani">periodici giuridici italiani</OPTION>
			<OPTION VALUE="Periodici: interesse locale">Periodici: interesse locale</OPTION>
			<OPTION VALUE="Periodici: interesse locale e autori abruzzesi">Periodici: interesse locale e autori abruzzesi</OPTION>
			<OPTION VALUE="periodici (ISI)">periodici (ISI)</OPTION>
			<OPTION VALUE="periodici (ISI, ESSPER, CIB)">periodici (ISI, ESSPER, CIB)</OPTION>
			<OPTION VALUE="periodici italiani">periodici italiani</OPTION>
			<OPTION VALUE="periodici italiani di architettura e urbanistica anteriori al 1989">periodici italiani di architettura e urbanistica anteriori al 1989</OPTION>
			<OPTION VALUE="periodici italiani di storia contemporanea">periodici italiani di storia contemporanea</OPTION>
			<OPTION VALUE="Periodici italiani in campo biomedico; Leggi in ambito sanitario">Periodici italiani in campo biomedico; Leggi in ambito sanitario</OPTION>
			<OPTION VALUE="periodici locali">periodici locali</OPTION>
			<OPTION VALUE="Periodici locali">Periodici locali</OPTION>
			<OPTION VALUE="periodici, materiale minore">periodici, materiale minore</OPTION>
			<OPTION VALUE="Periodici: Metalli">Periodici: Metalli</OPTION>
			<OPTION VALUE="Periodici, miscellanee">Periodici, miscellanee</OPTION>
			<OPTION VALUE="Periodici, Miscellanee">Periodici, Miscellanee</OPTION>
			<OPTION VALUE="periodici; miscellanee">periodici; miscellanee</OPTION>
			<OPTION VALUE="Periodici miscellanee di interesse locale">Periodici miscellanee di interesse locale</OPTION>
			<OPTION VALUE="periodici, miscellanee specializzate">periodici, miscellanee specializzate</OPTION>
			<OPTION VALUE="periodici, monografie">periodici, monografie</OPTION>
			<OPTION VALUE="Periodici; monografie; pubblicazioni in piu' volumi">Periodici; monografie; pubblicazioni in piu' volumi</OPTION>
			<OPTION VALUE="periodici, monografie sezione locale">periodici, monografie sezione locale</OPTION>
			<OPTION VALUE="Periodici: musica">Periodici: musica</OPTION>
			<OPTION VALUE="periodici musicali">periodici musicali</OPTION>
			<OPTION VALUE="Periodici non abruzzesi: articoli di interesse abruzzese">Periodici non abruzzesi: articoli di interesse abruzzese</OPTION>
			<OPTION VALUE="periodici, opere complessive e miscellanee">periodici, opere complessive e miscellanee</OPTION>
			<OPTION VALUE="periodici; opere di più autori">periodici; opere di più autori</OPTION>
			<OPTION VALUE="periodici, opere di più autori, atti di congressi">periodici, opere di più autori, atti di congressi</OPTION>
			<OPTION VALUE="periodici padovani: arte">periodici padovani: arte</OPTION>
			<OPTION VALUE="Periodici per BIBMAN">Periodici per BIBMAN</OPTION>
			<OPTION VALUE="periodici posseduti con riferimento alla storia linguistica della Sicilia">periodici posseduti con riferimento alla storia linguistica della Sicilia</OPTION>
			<OPTION VALUE="periodici, quotidiani">periodici, quotidiani</OPTION>
			<OPTION VALUE="periodici sardi">periodici sardi</OPTION>
			<OPTION VALUE="periodici sardi anni 1990-94">periodici sardi anni 1990-94</OPTION>
			<OPTION VALUE="Periodici sardi: Quaderni Oristanesi 1982-1994;">Periodici sardi: Quaderni Oristanesi 1982-1994;</OPTION>
			<OPTION VALUE="Periodici scientifici">Periodici scientifici</OPTION>
			<OPTION VALUE="Periodici scientifici e di cultura varia">Periodici scientifici e di cultura varia</OPTION>
			<OPTION VALUE="periodici: scienze pure e applicate">periodici: scienze pure e applicate</OPTION>
			<OPTION VALUE="Periodici; Sezione locale">Periodici; Sezione locale</OPTION>
			<OPTION VALUE="Periodici significativi per la storia del Piemonte">Periodici significativi per la storia del Piemonte</OPTION>
			<OPTION VALUE="Periodici: sinologia,buddhologia">Periodici: sinologia,buddhologia</OPTION>
			<OPTION VALUE="periodici socio-politici">periodici socio-politici</OPTION>
			<OPTION VALUE="periodici specializzati">periodici specializzati</OPTION>
			<OPTION VALUE="Periodici specializzati">Periodici specializzati</OPTION>
			<OPTION VALUE="periodici specializzati in materie economiche e commerciali">periodici specializzati in materie economiche e commerciali</OPTION>
			<OPTION VALUE="periodici specializzati nel campo dell'educazione">periodici specializzati nel campo dell'educazione</OPTION>
			<OPTION VALUE="Periodici specifici dell'area di interesse del Centro">Periodici specifici dell'area di interesse del Centro</OPTION>
			<OPTION VALUE="Periodici: storia locale">Periodici: storia locale</OPTION>
			<OPTION VALUE="periodici storico-culturali">periodici storico-culturali</OPTION>
			<OPTION VALUE="periodici su arte e architettura locale">periodici su arte e architettura locale</OPTION>
			<OPTION VALUE="Periodici: sud-est asiatico">Periodici: sud-est asiatico</OPTION>
			<OPTION VALUE="periodici sui trasporti">periodici sui trasporti</OPTION>
			<OPTION VALUE="periodici sul francescanesimo">periodici sul francescanesimo</OPTION>
			<OPTION VALUE="Periodici: Telecomunicazioni">Periodici: Telecomunicazioni</OPTION>
			<OPTION VALUE="periodici, volumi miscellanei">periodici, volumi miscellanei</OPTION>
			<OPTION VALUE="Periodici, volumi miscellanei">Periodici, volumi miscellanei</OPTION>
			<OPTION VALUE="Periodici; volumi miscellanei">Periodici; volumi miscellanei</OPTION>
			<OPTION VALUE="Piani di assestamento forestale">Piani di assestamento forestale</OPTION>
			<OPTION VALUE="Poligrafie di storia locale">Poligrafie di storia locale</OPTION>
			<OPTION VALUE="pubblicazioni a carattere locale">pubblicazioni a carattere locale</OPTION>
			<OPTION VALUE="Pubblicazioni a carattere locali">Pubblicazioni a carattere locali</OPTION>
			<OPTION VALUE="pubblicazioni collettanee">pubblicazioni collettanee</OPTION>
			<OPTION VALUE="pubblicazioni collettive">pubblicazioni collettive</OPTION>
			<OPTION VALUE="Pubblicazioni di argomento locale">Pubblicazioni di argomento locale</OPTION>
			<OPTION VALUE="Pubblicazioni di interesse locale">Pubblicazioni di interesse locale</OPTION>
			<OPTION VALUE="pubblicazioni d'interesse locale">pubblicazioni d'interesse locale</OPTION>
			<OPTION VALUE="pubblicazioni locali">pubblicazioni locali</OPTION>
			<OPTION VALUE="pubblicazioni specializzate">pubblicazioni specializzate</OPTION>
			<OPTION VALUE="Pubblicazioni specializzate">Pubblicazioni specializzate</OPTION>
			<OPTION VALUE="Pubblicazioni: storia locale">Pubblicazioni: storia locale</OPTION>
			<OPTION VALUE="pubblicazioni sul teatro veneto">pubblicazioni sul teatro veneto</OPTION>
			<OPTION VALUE="pubblicazioni varie">pubblicazioni varie</OPTION>
			<OPTION VALUE="Pubblicazioni varie">Pubblicazioni varie</OPTION>
			<OPTION VALUE="Pubblicazioni varie: Arma della Cavalleria">Pubblicazioni varie: Arma della Cavalleria</OPTION>
			<OPTION VALUE="pubblicazioni varie: cinematografia mondiale">pubblicazioni varie: cinematografia mondiale</OPTION>
			<OPTION VALUE="pubblicazioni varie d'interesse locale">pubblicazioni varie d'interesse locale</OPTION>
			<OPTION VALUE="Pubblicazioni varie d'interesse locale">Pubblicazioni varie d'interesse locale</OPTION>
			<OPTION VALUE="Pubblicazioni varie: interesse locale">Pubblicazioni varie: interesse locale</OPTION>
			<OPTION VALUE="Quaderni Bolotanesi 1980-1990">Quaderni Bolotanesi 1980-1990</OPTION>
			<OPTION VALUE="Quaderni del Museo delle Genti d'Abruzzo">Quaderni del Museo delle Genti d'Abruzzo</OPTION>
			<OPTION VALUE="quisiti ed inseriti nella Banca Dati Trasporti INFODOC">quisiti ed inseriti nella Banca Dati Trasporti INFODOC</OPTION>
			<OPTION VALUE="quotidiani">quotidiani</OPTION>
			<OPTION VALUE="Quotidiani">Quotidiani</OPTION>
			<OPTION VALUE="quotidiani dal 1900 al 1940">quotidiani dal 1900 al 1940</OPTION>
			<OPTION VALUE="Quotidiani e periodici che trattano argomenti di interesse locale.">Quotidiani e periodici che trattano argomenti di interesse locale.</OPTION>
			<OPTION VALUE="quotidiani, periodici, normativa di settore">quotidiani, periodici, normativa di settore</OPTION>
			<OPTION VALUE="raccolte di saggi">raccolte di saggi</OPTION>
			<OPTION VALUE="Raccolte di saggi">Raccolte di saggi</OPTION>
			<OPTION VALUE="rassegna stampa">rassegna stampa</OPTION>
			<OPTION VALUE="Rassegna stampa cinematografica">Rassegna stampa cinematografica</OPTION>
			<OPTION VALUE="rassegna stampa da quotidiani e periodici in materia di traffico">rassegna stampa da quotidiani e periodici in materia di traffico</OPTION>
			<OPTION VALUE="rassegna stampa degli spettacoli e delle iniziative del CRT">rassegna stampa degli spettacoli e delle iniziative del CRT</OPTION>
			<OPTION VALUE="Rassegna stampa estera sull'Italia">Rassegna stampa estera sull'Italia</OPTION>
			<OPTION VALUE="rassegna stampa locale">rassegna stampa locale</OPTION>
			<OPTION VALUE="rassegna stampa, quotidiani">rassegna stampa, quotidiani</OPTION>
			<OPTION VALUE="Recensione con abstract di 66 riviste specializzate e dei volumi tecnici ac">Recensione con abstract di 66 riviste specializzate e dei volumi tecnici ac</OPTION>
			<OPTION VALUE="recensioni cinematografiche sulla stampa locale e nazionale">recensioni cinematografiche sulla stampa locale e nazionale</OPTION>
			<OPTION VALUE="repertori">repertori</OPTION>
			<OPTION VALUE="Repertori">Repertori</OPTION>
			<OPTION VALUE="ritagli stampa">ritagli stampa</OPTION>
			<OPTION VALUE="rivista Leggere donna">rivista Leggere donna</OPTION>
			<OPTION VALUE="riviste">riviste</OPTION>
			<OPTION VALUE="Riviste">Riviste</OPTION>
			<OPTION VALUE="riviste, atti di congressi, pubblicazioni di più autori">riviste, atti di congressi, pubblicazioni di più autori</OPTION>
			<OPTION VALUE="Riviste cinesi e bollettini cinesi di bibliografia">Riviste cinesi e bollettini cinesi di bibliografia</OPTION>
			<OPTION VALUE="Riviste: copioni teatrali">Riviste: copioni teatrali</OPTION>
			<OPTION VALUE="Riviste correnti con argomento di interesse locale">Riviste correnti con argomento di interesse locale</OPTION>
			<OPTION VALUE="riviste correnti (cosmesi e farmaci)">riviste correnti (cosmesi e farmaci)</OPTION>
			<OPTION VALUE="riviste di agraria">riviste di agraria</OPTION>
			<OPTION VALUE="Riviste di argomento culturale francese dal 2001">Riviste di argomento culturale francese dal 2001</OPTION>
			<OPTION VALUE="riviste di biblioteconomia">riviste di biblioteconomia</OPTION>
			<OPTION VALUE="riviste di economia e scienze sociali">riviste di economia e scienze sociali</OPTION>
			<OPTION VALUE="riviste di educazione sanitaria">riviste di educazione sanitaria</OPTION>
			<OPTION VALUE="riviste di elettronica ed elettrotecnica">riviste di elettronica ed elettrotecnica</OPTION>
			<OPTION VALUE="riviste di filosofia, pubblicazioni su Vico">riviste di filosofia, pubblicazioni su Vico</OPTION>
			<OPTION VALUE="Riviste di interesse locale">Riviste di interesse locale</OPTION>
			<OPTION VALUE="riviste di psicoanalisi">riviste di psicoanalisi</OPTION>
			<OPTION VALUE="Riviste e atti di convegni">Riviste e atti di convegni</OPTION>
			<OPTION VALUE="Riviste e miscellanee">Riviste e miscellanee</OPTION>
			<OPTION VALUE="Riviste e quotidiani">Riviste e quotidiani</OPTION>
			<OPTION VALUE="riviste e quotidiani sui movimenti anarchici">riviste e quotidiani sui movimenti anarchici</OPTION>
			<OPTION VALUE="riviste e studi di argomento giuridico">riviste e studi di argomento giuridico</OPTION>
			<OPTION VALUE="riviste giuridiche">riviste giuridiche</OPTION>
			<OPTION VALUE="Riviste giuridiche">Riviste giuridiche</OPTION>
			<OPTION VALUE="riviste Il Giappone e Archipel">riviste Il Giappone e Archipel</OPTION>
			<OPTION VALUE="riviste infermieristiche">riviste infermieristiche</OPTION>
			<OPTION VALUE="riviste medico scientifiche">riviste medico scientifiche</OPTION>
			<OPTION VALUE="riviste ornitologiche europee dal 1989">riviste ornitologiche europee dal 1989</OPTION>
			<OPTION VALUE="riviste, quotidiani">riviste, quotidiani</OPTION>
			<OPTION VALUE="Riviste, saggi, articoli">Riviste, saggi, articoli</OPTION>
			<OPTION VALUE="riviste scientifiche nazionali e internazionali">riviste scientifiche nazionali e internazionali</OPTION>
			<OPTION VALUE="riviste specialistiche">riviste specialistiche</OPTION>
			<OPTION VALUE="riviste specializzate">riviste specializzate</OPTION>
			<OPTION VALUE="Riviste specializzate; listini di vendita; opuscoli; cataloghi di mostre">Riviste specializzate; listini di vendita; opuscoli; cataloghi di mostre</OPTION>
			<OPTION VALUE="riviste turistiche e geografiche">riviste turistiche e geografiche</OPTION>
			<OPTION VALUE="saggi">saggi</OPTION>
			<OPTION VALUE="saggistica">saggistica</OPTION>
			<OPTION VALUE="Salento">Salento</OPTION>
			<OPTION VALUE="scritti di autori menenini e su Mineo">scritti di autori menenini e su Mineo</OPTION>
			<OPTION VALUE="scritti di e su G. Bonaviri">scritti di e su G. Bonaviri</OPTION>
			<OPTION VALUE="scritti di e su Luigi Capuana">scritti di e su Luigi Capuana</OPTION>
			<OPTION VALUE="scritti in onore">scritti in onore</OPTION>
			<OPTION VALUE="scritti in onore di">scritti in onore di</OPTION>
			<OPTION VALUE="settimanali">settimanali</OPTION>
			<OPTION VALUE="sezione locale">sezione locale</OPTION>
			<OPTION VALUE="Sezione locale">Sezione locale</OPTION>
			<OPTION VALUE="spogli da pubblicazioni specializzate">spogli da pubblicazioni specializzate</OPTION>
			<OPTION VALUE="Spogli da riviste e miscellanee in onore">Spogli da riviste e miscellanee in onore</OPTION>
			<OPTION VALUE="spoglio di periodici: argomenti siciliani e autori siciliani">spoglio di periodici: argomenti siciliani e autori siciliani</OPTION>
			<OPTION VALUE="spoglio di periodici specializzati">spoglio di periodici specializzati</OPTION>
			<OPTION VALUE="Spoglio parziale delle riviste per argomento">Spoglio parziale delle riviste per argomento</OPTION>
			<OPTION VALUE="spoglio periodici, dal 1992 ad oggi">spoglio periodici, dal 1992 ad oggi</OPTION>
			<OPTION VALUE="storia locale">storia locale</OPTION>
			<OPTION VALUE="Storia locale">Storia locale</OPTION>
			<OPTION VALUE="storia locale, periodici storici locali">storia locale, periodici storici locali</OPTION>
			<OPTION VALUE="Studi in onore">Studi in onore</OPTION>
			<OPTION VALUE="teatro (chiuso nel 1985)">teatro (chiuso nel 1985)</OPTION>
			<OPTION VALUE="terza pagina della Gazzetta del Mezzogiorno">terza pagina della Gazzetta del Mezzogiorno</OPTION>
			<OPTION VALUE="Terza pagina La nuova nuova Sardegna anni 1950-1970; Terza pagina Unione sarda anni 1950-190">Terza pagina La nuova nuova Sardegna anni 1950-1970; Terza pagina Unione sarda anni 1950-190</OPTION>
			<OPTION VALUE="Tesi">Tesi</OPTION>
			<OPTION VALUE="Vanità :  supplemento settimanale del quotidiano irpino Ottopagine">Vanità :  supplemento settimanale del quotidiano irpino Ottopagine</OPTION>
			<OPTION VALUE="volumi">volumi</OPTION>
			<OPTION VALUE="volumi e periodici specializzati">volumi e periodici specializzati</OPTION>
			<OPTION VALUE="volumi e riviste di arte sacra">volumi e riviste di arte sacra</OPTION>
			 --%>
			<jsp:include page="dynaselect/dynaselect.jsp"><jsp:param name="select" value="spogli_bibliografici"/></jsp:include>
		</SELECT>
	</TD>
</TR>
</TABLE>
</CENTER>
<!-------------------------------------------------------------------------------------------->
<CENTER>
<TABLE width="100%" border="0">
<tr><td colspan="4">&nbsp;</td></tr>
<TR>
	<TD bgcolor="#E6E6E6" colspan="4"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">

		Servizi</B></FONT>
	</TD>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1" width="30%">Informazioni bibliografiche</TD>
	<TD COLSPAN="3" align="left"><FONT SIZE="1" FACE="Verdana">
		<c:choose>
			<c:when test="${param.gestisce_servizio_bibliografico_interno=='true'}">
				&nbsp;<b>Per utenti in sede</b><input type="checkbox" name="gestisce_servizio_bibliografico_interno" value="true" CHECKED>			
			</c:when>
			<c:otherwise>
				&nbsp;<b>Per utenti in sede</b><input type="checkbox" name="gestisce_servizio_bibliografico_interno" value="true">
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${param.gestisce_servizio_bibliografico_esterno=='true'}">
				&nbsp;&nbsp;<b>Per utenti esterni</b><input type="checkbox" name="gestisce_servizio_bibliografico_esterno" value="true" CHECKED>&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<b>Per utenti esterni</b><input type="checkbox" name="gestisce_servizio_bibliografico_esterno" value="true">&nbsp;&nbsp;		
			</c:otherwise>
		</c:choose>
	</TD>

</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1">Riproduzioni</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${param.riproduzioni=='Si'}">
				&nbsp;<input type="checkbox" name="riproduzioni" value="Si" CHECKED>
			</c:when>
			<c:otherwise>
				&nbsp;<input type="checkbox" name="riproduzioni" value="Si">
			</c:otherwise>
		</c:choose>
	</TD>
</TR>
<TR>
	<TD CLASS="filtriRight2" COLSPAN="1"><font color="Red">&nbsp;</font>Postazioni INTERNET per gli utenti</TD>
	<TD COLSPAN="3" ALIGN="LEFT">
		<c:choose>
			<c:when test="${fn:length(param.posti_internet) > 0}">
				&nbsp;<input type="checkbox" name="posti_internet" value="[1 TO *]" CHECKED>
			</c:when>
			<c:otherwise>
				&nbsp;<input type="checkbox" name="posti_internet" value="[1 TO *]">	
			</c:otherwise>
		</c:choose>
	</TD>
</TR>
<TR>

	<TD CLASS="filtriRight2">Prestito</TD>
	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">
		<c:choose>
			<c:when test="${param.prestito_locale=='Si'}">
				&nbsp;<INPUT TYPE="CHECKBOX" NAME="prestito_locale" VALUE="Si" CHECKED><b>locale</b>
			</c:when>
			<c:otherwise>
				&nbsp;<INPUT TYPE="CHECKBOX" NAME="prestito_locale" VALUE="Si"><b>locale</b>	
			</c:otherwise>
		</c:choose>
	</font></TD>
	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">&nbsp;&nbsp;
		<c:choose>
			<c:when test="${param.prestito_interbiblio_nazionale=='true'}">
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_nazionale" VALUE="true" CHECKED><b>nazionale</b>
			</c:when>
			<c:otherwise>
				<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_nazionale" VALUE="true"><b>nazionale</b>
			</c:otherwise>
		</c:choose>
	</font></TD>

	<TD ALIGN="LEFT"><FONT SIZE="1" FACE="Verdana">
		<c:choose>
			<c:when test="${param.prestito_interbiblio_internazionale=='true'}">
			<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_internazionale" VALUE="true" CHECKED><b>internazionale</b>
			</c:when>
			<c:otherwise>
				<INPUT TYPE="CHECKBOX" NAME="prestito_interbiblio_internazionale" VALUE="true"><b>internazionale</b>
			</c:otherwise>
		</c:choose>
	</font></TD>
</TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
<TABLE BORDER="0" WIDTH="100%">
<TR><TD colspan="4">&nbsp;</TD></TR>
<TR>
	<TD bgcolor="#E6E6E6" colspan="4"><B><FONT SIZE="2" COLOR="blue" FACE="Verdana">
		Ricerca per altri codici</B></FONT>

	</TD>
<TR>
<TR>
	<td width="22%">&nbsp;</td>
	 <TD CLASS="filtriLeft3"  width="5%" ><font color="Red">&nbsp;</font>SBN&nbsp;</TD>
     <td width="1%"><A HREF="#" onclick="helpMessage(9, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></td>
     <TD ALIGN="LEFT">
     	<c:choose>
			<c:when test="${fn:length(param.sbn) > 0}">
				<INPUT TYPE="TEXT" NAME="sbn" SIZE="9" MAXLENGTH="6" VALUE="${param.sbn}">
			</c:when>
			<c:otherwise>
     			<INPUT TYPE="TEXT" NAME="sbn" SIZE="9" MAXLENGTH="6">
     		</c:otherwise>
     	</c:choose>     	
     </TD>
</TR>
<TR>
	<td>&nbsp;</td>

    <TD CLASS="filtriLeft3"><font color="Red">&nbsp;</font>RISM&nbsp;</TD>
     <td>	 	<A HREF="#" onclick="helpMessage(10, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></td>
     <TD ALIGN="LEFT">
     	<c:choose>
			<c:when test="${fn:length(param.rism) > 0}">
				<INPUT TYPE="TEXT" NAME="rism" SIZE="23" MAXLENGTH="20" VALUE="${param.rism}">
			</c:when>
			<c:otherwise>
     			<INPUT TYPE="TEXT" NAME="rism" SIZE="23" MAXLENGTH="20">
     		</c:otherwise>
     	</c:choose>
     </TD>

</TR>
<TR>
	<td>&nbsp;</td>
     <TD CLASS="filtriLeft3"><font color="Red">&nbsp;</font>ACNP&nbsp;

	 </TD>
      <td><A HREF="#" onclick="helpMessage(11, 400, 200); return false"><IMG SRC="<c:url value="/images/info.gif" />" ALIGN="CENTER" BORDER="0"></A></td>
     <TD ALIGN="LEFT">
     	<c:choose>
			<c:when test="${fn:length(param.acnp) > 0}">
				<INPUT TYPE="TEXT" NAME="acnp" SIZE="8" MAXLENGTH="5" VALUE="${param.acnp}">
			</c:when>
			<c:otherwise>
     			<INPUT TYPE="TEXT" NAME="acnp" SIZE="8" MAXLENGTH="5">
     		</c:otherwise>
     	</c:choose>
     </TD>

</tr>
<TR><TD colspan="4">&nbsp;</TD></TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
<TABLE BORDER="0" CELLSPACING="5" WIDTH="100%">
<TR>
	<TD align="left"><a href="" onclick="window.history.back();return false;"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
		Indietro</font></a></TD>

	<TD align="right"><a href="index.jsp"><FONT SIZE="2" COLOR="#e27e10" FACE="Arial Black">
		HomePage</font></a></TD>
</TR>
<!--tr>
	<td align="LEFT"><font SIZE ="2" COLOR = "RED"</font>&nbsp;I dati per i campi contrassegnati con *&nbsp;saranno disponibili con i successivi aggiornamenti.</td>
</tr-->
<TR><TD align="center" colspan="2" width="100%" height="1%"><HR size="1" color="Navy" width="100%"></TD></TR>
<TR>
	<TD COLSPAN="3" ALIGN="center">
	    <INPUT TYPE="SUBMIT" NAME="AVVIA" VALUE="Avvia ricerca">&nbsp;

	    <INPUT TYPE="BUTTON" NAME="CANCELLA" VALUE="Cancella" onClick="cancella()"><!--r.eschini per ripristinare tutte le regioni/province -->
	</TD>
</TR>
</TABLE>
<!-------------------------------------------------------------------------------------------->
</TD></TR></TABLE></TD></TR>
	<tr><td align="center" colspan="3">&nbsp;</td></tr>
	<TR>
		<td align="center" colspan="3">
			<jsp:include page="footer.jsp"></jsp:include>
		</td>
	</TR>
</table>
</FORM></BODY></HTML>
