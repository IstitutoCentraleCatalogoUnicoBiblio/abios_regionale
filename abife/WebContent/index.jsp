<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/c" prefix="c" %>
<html>
<head>
	<title>Anagrafe Biblioteche Italiane - Home Page</title>
	<link rel="stylesheet" href="<c:url value="/style/styleIccu.css" />" type="text/css">
</head>
<BODY>
<CENTER>
<DIV align=center>
<TABLE align=center border="0" cellSpacing=0 height="100%" width="100%">
<TR>
    <TD background="" width="10%">&nbsp;</TD>
    <TD align=middle vAlign=top>
      <TABLE align=center border=0 width="100%">
        <TBODY>
        <TR>

          <TD align=middle colSpan=2>
		  	<a href="http://www.beniculturali.it/"><FONT color=#000099 face="Arial, Times, Verdana, Arial, Helvetica, sans-serif" size=2>
			<B><I>Ministero per i Beni e le Attività Culturali</B></I></FONT></a><BR>
		  	<a href="http://www.librari.beniculturali.it/genera.jsp"><FONT color=#000099 face="Arial, Times, Verdana, Arial, Helvetica, sans-serif" size=2>
			<B><I>Direzione Generale per le Biblioteche, gli Istituti Culturali e il Diritto d'Autore</B></I></FONT></a>
		</TD>
		</TR></TBODY></TABLE>
      <TABLE align=center border=0 width="100%">

        <TBODY>
        <TR>
          <TD width="10%">&nbsp;</TD>
          <TD align=middle vAlign=center width="80%"><FONT color=#000099
            face="Arial, Times, Verdana, Arial, Helvetica, sans-serif"
            size=4><B><I>&nbsp;Istituto Centrale per il Catalogo Unico delle
            biblioteche<BR>italiane e per le informazioni
            bibliografiche</I>&nbsp;-&nbsp;</I><A
            href="http://www.iccu.sbn.it/"><font color="green">ICCU</font></A></B> </FONT></TD>
          <TD align=right width="10%">

		  <IMG align=right border=0 src="<c:url value="/images/prova2.gif" />">
          </TD></TR></TBODY></TABLE>
      <TABLE border=0 width="80%">
        <TBODY>
        <TR>
          <TD align=middle colSpan=3>
            <HR align=center SIZE=3 width="100%">
          </TD></TR>
        <TR>

          <TD align=middle>&nbsp;&nbsp;&nbsp;</TD><!---riga vuota--->
          <TD align=middle><FONT color=#e27e10 face="Arial, Times, Verdana, Arial, Helvetica, sans-serif" size=5><B>
            <P>ANAGRAFE BIBLIOTECHE ITALIANE</B></P></FONT></TD>
          <TD align=middle>&nbsp;&nbsp;&nbsp;</TD><!---riga vuota---></TR>
        <TR>
          <TD align=middle colSpan=3>&nbsp;</TD></TR><!---riga vuota--->
        <TR>
        </TR>

        <TR>
          <TD align=middle>&nbsp;&nbsp;&nbsp;</TD><!---riga vuota--->
          <TD align=middle><FONT color=#000099
            face="Verdana, Arial, Helvetica, sans-serif" size=2>La base dati
            offre informazioni su circa 15.000 biblioteche italiane<BR>La
            raccolta e la verifica dei dati è continua grazie anche al
            contributo delle regioni, delle università, degli enti e delle
            istituzioni culturali che collaborano con l'ICCU
            <P></P></FONT></TD>
          <TD align=middle>&nbsp;&nbsp;&nbsp;</TD><!---riga vuota---></TR>
        <TR>
          <TD align=middle colSpan=3><FONT color=#000099
            face="Verdana, Arial, Helvetica, sans-serif" size=1>Per ulteriori
            informazioni sulla base dati vedi: <A
            href="http://www.iccu.sbn.it/genera.jsp?id=78"><B><font color="green">http://www.iccu.sbn.it/anagrafe</font></B></A>
            <P><BR></P></FONT></TD></TR>

        <TR>
          <TD align=middle colSpan=3>
		  	<INPUT type="button" name="internet" value="Interroga la base dati" onclick="window.location='<c:url value="ricerca.jsp" />'">&nbsp;&nbsp;&nbsp;
			<P>
				<jsp:include page="count.html"></jsp:include>
			</P>
		 </TD>
		</TD>
		</TR>

		</TBODY></TABLE>
      <TABLE border=0 width="100%">
        <TBODY>
        <TR>
          <TD align=middle colSpan=3 width="70%">
          <br/>
        </TD></TR></TBODY></TABLE>
      <TABLE border=0 width="80%">

        <TBODY>
        <TR>
          <TD align=middle colSpan=3>
            <HR align=center SIZE=3 width="100%">
          </TD>
		 </TR>
        <TR>
          <TD align=middle colSpan=3>
			<jsp:include page="footer.jsp"></jsp:include>

		</td></TR></table>
    <TD background="" width="10%">&nbsp;</TD></TR></TBODY>
</TABLE>
</DIV></CENTER></BODY></HTML>
