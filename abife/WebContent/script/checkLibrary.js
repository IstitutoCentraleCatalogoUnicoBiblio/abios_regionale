/********************************************************************************************
SCRIPT TESTATO 24-08-2000 CON:
- EXPLORER Versione 5.00
- NETSCAPE Versione 4.7 - Inglese

REGULAR EXPRESSION
Es: L'istruzione OKcap ritorna TRUE solo se l'utente ha inserito 5 caratteri e tutti numerici
Quindi non c'e' bisogno dei seguenti controlli sulla lunghezza della stringa inserita:
- if (form.email.value == "" || form.email.value == " " || form.email.value.length > 5) ...
- CoseDaFare: <FORM NAME="..." ONSUBMIT="return ctrlDatiForm(this)">
********************************************************************************************/

MAIL	 = /[@]/;
PUNTO	 = /[.]/;
CAPCheck = /\d{5}/; 
PI		 = /\d{11}/; 
// Formato CODICE FISCALE: LLLLLLNNLNNLNNNL
CF		 = /[A-Za-z]{6}\d{2}[A-Za-z]\d{2}[A-Za-z]\d{3}[A-Za-z]/;

/*__________________________________________________________________
RITORNA TRUE SE field E' null O COMPLETAMENTE VUOTA
maxlengthCampo serve per controllare se il campo e' pieno di blank
- <INPUT TYPE="..." NAME="EMAIL" maxlength="5">
- CHIAMATA: isEmptyOrAllBlank (document.form.EMAIL, 20, "EMAIL") ...
*/
function isEmptyOrAllBlank (field, maxlengthCampo, message)
{
	blank = 0;
	for (i = 0; i < maxlengthCampo; i++) 
		if ((field.value.charAt(i) == "") || (field.value.charAt(i) == " ")) blank++;
	if ((field.value.length == 0) || (field.value == null)) 
	{
		alert("E' necessario riempire il campo \"" + message + "\".\nGrazie.");
		field.focus();
		return (true);
	}
	else if (blank == maxlengthCampo) 
	{
		alert("Il campo \"" + message + "\" non puo' contenere solo 'blank'.\nGrazie.");
		field.focus();
		return (true);
	}
	else if (field.value.indexOf("\"") != -1) 
	{
		alert("Il campo \"" + message + "\" non puo' contenere il carattere \" (doppi apici).\nGrazie.");
		field.focus();
		return (true);
	}
	else return (false);
};
/*_____________________________________________________________________________
RITORNA TRUE SE field E' null O COMPLETAMENTE VUOTA; SCELTA focus E SENZA ALERT
Il flag 'flagFocus' accetta Maiuscolo, Minuscolo e pone il focus in field
maxlengthCampo serve per controllare se il campo e' pieno di blank
- <INPUT TYPE="..." NAME="EMAIL" maxlength="5">
- CHIAMATA: isEmptyOrAllBlankNoAlert (document.form.EMAIL, 20, "yes") ...
*/
function isEmptyOrAllBlankNoAlert (field, maxlengthCampo, flagFocus)
{
	blank = 0;
	for (i = 0; i < maxlengthCampo; i++) 
		if ((field.value.charAt(i) == "") || (field.value.charAt(i) == " ")) blank++;
	if ((field.value.length == 0) || (field.value == null) || (blank == maxlengthCampo)) 
	{
		if (flagFocus.toLowerCase () == "yes") 
		{
			field.focus();
		}
		return (true);
	}
	else return (false);
};
/*_________________________________________________________
VISUALIZZA UN ALERT RITORNANDO UN VALORE BOOLEANO IMPOSTATO
Se il campo fieldFocus
- <INPUT TYPE="..." NAME="F1">
- CHIAMATA: viewAlert ("...", "TRUE", "document.form.F1") oppure
- CHIAMATA: viewAlert ("...", "TRUE", "no") Senza focus.
*/
function viewAlert (message, cosaDeveRitornare, fieldFocus)
{
	if (cosaDeveRitornare.toLowerCase () == "true") 
	{
		alert(message);
		if (fieldFocus.toLowerCase () != "no") 
		{
			eval(fieldFocus).focus();
		}
		return (true);
	}
	else return (false);
};
/*_________________________________________________________
RITORNA TRUE SE field CONTIENE ALMENO UN BLANK
- <INPUT TYPE="..." NAME="vuoto" maxlength="5">
- CHIAMATA: if (isONEBlank (oggetto.vuoto, 5, "VUOTO")) ...
*/
function isONEBlank (field, maxlengthCampo, message)
{
	blank = 0;
	for (i = 0; i < maxlengthCampo; i++) 
		if (field.value.charAt(i) == " ") blank++;
	if (blank != 0) 
	{
		alert("Il campo \"" + message + "\" non deve contenere caratteri 'blank'.\nGrazie.");
		field.focus();
		return (true);
	}
	else return (false);
};
/*__________________________________________________
RITORNA TRUE SE field CONTIENE IL CARATTERE '@'
- <INPUT TYPE="..." NAME="EMAIL" maxlength="5">
- CHIAMATA: if (ctrlEMail (document.form.EMAIL)) ...
*/
function ctrlEMail (field)
{
	OK_MAIL	 = MAIL.exec(field.value);
	OK_PUNTO = PUNTO.exec(field.value);
	if (!OK_MAIL || !OK_PUNTO)
	{
		alert("E-Mail non valida");
		field.focus();
		return false
	}
	else return true;
};
/*_____________________________________________________________________________________________
RITORNA TRUE SE field NON CONTIENE IL FORMATO CORRETTO PER EMAIL
Il metodo lastIndexOf restituisce l'ultima occorrenza di una sottostringa in un oggetto String.
- oggstringa.lastIndexOf(substring[, startindex])
startindex e' un valore intero facoltativo che specifica l'indice da cui iniziare la ricerca 
nell'oggetto String. Se omesso, la ricerca verr� eseguita a partire dalla fine della stringa.
- <INPUT TYPE="..." NAME="EMAIL" maxlength="13">
- CHIAMATA: if (noEMAILintoField (document.form.EMAIL, 13)) ...
*/
function noEMAILintoField (field, maxlengthCampo)
{
	email = 0;
	points = 0;
	//noValidCharacters = 0;

	if ((field.value.indexOf("@", 0) == -1) || 
		(field.value.indexOf(".", 0) == -1)) 
		alert("I caratteri \"@\" o \".\" non sono presenti");
	else if (field.value.charAt(0) == "@" || field.value.charAt(maxlengthCampo-1) == "@" || 
			 field.value.charAt(0) == "." || field.value.charAt(maxlengthCampo-1) == ".") 
		alert("Il campo \"E-Mail\" non � nel formato corretto\no l'email inserita non � valida");
	else
	{
		for (i = 0; i < maxlengthCampo; i++) 
		{
			if (field.value.charAt(i) == "@") email++;
			if (field.value.charAt(i) == ".") 
			{
				if ((field.value.charAt(i-1) == ".") || 
					(field.value.charAt(i+1) == ".")) points++;
			}
			//if (field.value.charAt(i) == "caratteri speciali") noValidCharacters++;
		}
		if (/*noValidCharacters > 0 || */points > 0 || email > 1 || 
		   ((field.value.indexOf(".", 0) - field.value.indexOf("@", 0)) == 1) || 
		   ((field.value.charAt((field.value.indexOf(".", 0)) + 1)) == "") || 
		   (((field.value.length-1)-field.value.lastIndexOf(".")) < 2)) 
		   alert("Il campo \"E-Mail\" non � nel formato corretto\no l'email inserita non � valida");
		else return (false);
	}
	field.focus();
	return (true);
};
/*____________________________________________________________
RITORNA TRUE SE field CONTIENE TUTTI NUMERI
stringa.charAt(P) - estrae un solo carattere nella posizione P
ACCORGIMENTO: !parseFloat(0-zero) == NaN [Not a Number]
- <INPUT TYPE="..." NAME="TEL" maxlength="5">
- CHIAMATA: if (isNumeric (document.form.TEL, "TEL")) ...
*/
function isNumeric (field, message)
{
	var number = 0;
	for (var i = 0; i < field.value.length; i++) 
	{
		number = field.value.charAt(i);
		if ( (number != "0") && (!parseFloat(number)) ) 
		{
			alert("Il campo \"" + message + "\" deve contenere solo caratteri numerici.\nGrazie.");
			field.focus();
			return false;	
		}
	}
	return true;
};
/*_______________________________________________________________________
RITORNA TRUE SE field NON CONTIENE i 5 NUMERI DEL CodiceAvviamentoPostale
- <INPUT TYPE="..." NAME="CAP" maxlength="5">
- CHIAMATA: if (noCAPintoField(document.form.CAP)) ...
*/
function noCAPintoField (field)
{
	OK_CAP = CAPCheck.exec(field.value)
	if (!OK_CAP)
	{
		alert("Il Codice Avviamento Postale inserito non e\' corretto");
		field.focus();
		return true
	}
	else return false;
};
/*________________________________________________________________
RITORNA TRUE SE field NON CONTIENE gli 11 NUMERI DELLA Partita Iva
- <INPUT TYPE="..." NAME="IVA" maxlength="5">
- CHIAMATA: if (noIVAintoField(document.form.IVA)) ...
*/
function noIVAintoField (field)
{
	OK_IVA = PI.exec(field.value);
	if (!OK_IVA)
	{
		alert("Il Codice della Partita Iva inserito non e\' corretto");
		field.focus();
		return true
	}
	else return false;
};
function noIVAintoField2 (field, message)
{
	OK_IVA = PI.exec(field.value);
	if (!OK_IVA)
	{
		alert(message);
		field.focus();
		return true
	}
	else return false;
};
/*________________________________________________________
RITORNA TRUE SE field NON CONTIENE i 16 CARATTERI DEL 
CodiceFiscale NEL SEGUENTE FORMATO: LLLLLLNNLNNLNNNL
- <INPUT TYPE="..." NAME="CODFIS" maxlength="5">
- CHIAMATA: if (noCodiceFiscale(document.form.CODFIS)) ...
*/
function noCodiceFiscale (field)
{
	OK_CF = CF.exec(field.value)
	 if (!OK_CF)
	{
		alert("Inserire  il Codice Fiscale nel formato corretto : LLLLLLNNLNNLNNNL.\nGrazie.");
		field.focus();
		return true
	}
	else return false;
};
/*____________________________________________________________________
RITORNA TRUE SE LA LUNGHEZZA di field SUPERA LA LUNGHEZZA DI maxLength
- <INPUT TYPE="..." NAME="NOTE" maxlength="5">
- CHIAMATA: if (overLength(document.form.NOTE, "NOTE", 255)) ...
*/
function overLength (field, fieldName, maxLength)
{
	if (field.value.length > maxLength)
	{
		alert("Il campo \"" + fieldName + "\" non pu� contenere pi� di \"" 
			  + maxLength + "\" caratteri.\nGrazie.");
		field.focus();
		return true;
	}
	else return false;
};
/*___________________________________________________________________________
RITORNA TRUE SE LA LUNGHEZZA DI field E' DIVERSA DALLA LUNGHEZZA DI maxLength
- <INPUT TYPE="..." NAME="NOTE" maxlength="5">
- CHIAMATA: if (noFullLength(document.form.ISIL_ST, "ISIL_ST", 2)) ...
*/
function noFullLength (field, fieldName, maxLength)
{
	if (field.value.length != maxLength)
	{
		alert("Il campo \"" + fieldName + "\" deve contenere \"" 
			  + maxLength + "\" caratteri.\nGrazie.");
		field.focus();
		return true;
	}
	else return false;
};
/*_____________________________________________________________________________
RITORNA TRUE SE LA LUNGHEZZA DI field NON E' COMPRESA TRA minLength e maxLength
- <INPUT TYPE="..." NAME="PWD" maxlength="5">
- CHIAMATA: if (overRange(document.form.PWD, "PASSWORD", 6, 10)) ...
*/
function overRange (field, fieldName, minLength, maxLength)
{
	if (field.value.length < minLength || field.value.length > maxLength)
	{
		alert("Il campo \"" + fieldName + "\" deve essere compreso tra \"" 
			  + minLength + "\" e \"" 
			  + maxLength + "\" caratteri.\nGrazie.");
		field.focus();
		return true;
	}
	else return false;
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE fieldChar NON CONTIENE UN CARATTERE ALFABETICO
* indexOf cerca una stringa specifica contenuta in un'altra stringa.
* SINTASSI: stringa.indexOf(valoreRicerca, daIndice)
* ESEMPIO:  var pippo = "mimmo morelli".indexOf("morelli",0); alert(pippo); --> 6
* Restituisce se esiste l'indice della stringa originaria, altrimenti -1
- <INPUT TYPE="..." NAME="CAR" maxlength="1">
- CHIAMATA: if (noAlphabeticChar (document.form.CAR, "CARATTERE")) ...
*/
function noAlphabeticChar (fieldChar, nameField)
{
	if (fieldChar.value.length != 1) return (true);
	fieldCharApp = fieldChar.value.toLowerCase ();
	alfabeto = "abcdefghijklmnopqrstuvwxyz -/�����簧�'@#";
	if (alfabeto.indexOf (fieldCharApp, 0) == -1) 
	{
		alert("Il campo \"" + nameField + "\" deve contenere un carattere.\nGrazie.");
		fieldChar.focus();
		return (true);
	}
	return (false);
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE fieldChar NON CONTIENE UN CARATTERE NUMERICO
- <INPUT TYPE="..." NAME="CAR" maxlength="1">
- CHIAMATA: if (noNumberChar (document.form.CAR, "CARATTERE")) ...
*/
function noNumberChar (fieldChar, nameField)
{
	if (fieldChar.value.length != 1) return (true);
	fieldCharApp = fieldChar.value.toLowerCase ();
	numeri = "1234567890";
	if (numeri.indexOf (fieldCharApp, 0) == -1) 
	{
		alert("Il campo \"" + nameField + "\" deve contenere un carattere.\nGrazie.");
		fieldChar.focus();
		return (true);
	}
	return (false);
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE field NON CONTIENE TUTTI CARATTERE ALFABETICI
- <INPUT TYPE="..." NAME="STR" maxlength="1">
- CHIAMATA: if (noAlphabeticString(document.form.STR, "CARATTERI")) ...
*/
function noAlphabeticString (field, nameField)
{
	len = field.value.length;
	if (len == 0) return (true);
	fieldApp = field.value.toLowerCase();
	Alfabeto = "abcdefghijklmnopqrstuvwxyz -/�����簧�'@#";
	for (Count = 0; Count < len; Count++)
	{
		TempChar = fieldApp.substring(Count, Count+1);
		if (Alfabeto.indexOf(TempChar, 0) == -1) 
		{
			alert("Il campo \"" + nameField + "\" deve contenere solo caratteri alfabetici.\nGrazie.");
			field.focus();
			return (true);
		}
	}
	return (false);
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE field NON CONTIENE TUTTI CARATTERE NUMERICI
- <INPUT TYPE="..." NAME="N_STR" maxlength="1">
- CHIAMATA: if (noNumbersString(document.form.N_STR, "NUMERI")) ...
*/
function noNumbersString (field, nameField)
{
	len = field.value.length;
	if (len == 0) return (true);
	Numeri = "1234567890";
	for (Count = 0; Count < len; Count++)
	{
		TempChar = field.value.substring (Count, Count+1);
		if (Numeri.indexOf (TempChar, 0) == -1) 
		{
			alert("Il campo \"" + nameField + "\" deve contenere tutti caratteri numerici.\nGrazie.");
			field.focus();
			return (true);
		}
	}
	return (false);
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE field NON CONTIENE TUTTI CARATTERE NUMERICI
- <INPUT TYPE="..." NAME="N_STR" maxlength="1">
- CHIAMATA: if (noNumbersString(document.form.N_STR, "NUMERI")) ...
*/
function noNumbersStringSimple (fieldString, field, nameField)
{
	len = fieldString.length;
	if (len == 0) return (true);
	Numeri = "1234567890";
	for (Count = 0; Count < len; Count++)
	{
		TempChar = fieldString.substring (Count, Count+1);
		if (Numeri.indexOf (TempChar, 0) == -1) 
		{
			alert("Il campo \"" + nameField + "\" deve contenere tutti caratteri numerici.\nGrazie.");
			field.focus();
			return (true);
		}
	}
	return (false);
};
/*____________________ISTRUZIONI CLASSICHE, COMPATIBILITA' QUASI ASSOLUTA CON TUTTI I BROWSER
RITORNA TRUE SE DENTRO field E' PRESENTE IL CARATTERE carattere
- <INPUT TYPE="..." NAME="SEARCH_CHAR" maxlength="1">
- CHIAMATA: if (noCharIntoField(document.form.SEARCH_CHAR, "SEARCH_CARATTERE", "#")) ...
*/
function noCharIntoField (field, nameField, carattere)
{
	len = field.value.length;
	if (len == 0) return (true);
	carattereApp = carattere.toLowerCase ();
	fieldApp = field.value.toLowerCase ();
	if (fieldApp.indexOf(carattereApp, 0) == -1) 
	{
			alert("Il campo \"" + nameField + "\" non contiene il carattere " + carattere);
			field.focus();
			return (true);
	}
	return (false);
};
/*_________________________________________________________________________________________
RITORNA TRUE SE field NON E' UNA DATA VALIDA CONTROLLANDO ANCHE SE BISESTILE E DATA SISTEMA
- <INPUT TYPE="..." NAME="DATA" maxlength="1">
- CHIAMATA: if (noDate(document.form.DATA, "/")) ... Il separatore puo' essere diverso!
ATTENZIONE: 
*/
function noDate (field, separatore)
{
	var dt = new Date();
	dt.setDate(field.value.substr(0, 2));
	dt.setMonth(field.value.substr(3, 2)-1);
	dt.setYear(field.value.substr(6, 4));
	y = dt.getYear();

	if( y < 1900) 
		y = y + 1900;

	//Controllo browser in uso e data immessa dall'utente se e' superiore a quella di sistema
	ns4  = (document.layers)? true:false
	ie4  = (document.all)? true:false
	OGGI = new Date();
	aaSistemaExp = (OGGI.getYear()).toString();
	aaSistemaNet = ((OGGI.toString()).substring(54,58));
	if ((ie4 && (parseInt(field.value.substr(6, 4), 10) > parseInt(aaSistemaExp, 10))) || 
		(ns4 && (parseInt(field.value.substr(6, 4), 10) > parseInt(aaSistemaNet, 10)))) 
    {
		alert("Data immessa e' superiore alla data di sistema.");
		field.focus();
		return true;
	}
	else if (field.value.length != 10 || 
			 field.value.charAt(2) != separatore || field.value.charAt(5) != separatore || 
			 dt.getDate() != field.value.substr(0, 2) || 
			 (dt.getMonth()+1) != field.value.substr(3, 2) || 
			 y != field.value.substr(6, 4) || field.value.substr(6, 4) < 1900)
    {
		alert("Il campo data non � nel formato corretto o la data inserita non � valida");
		field.focus();
		return true;
	}
	return false;
};
/*____________________________________________________________________
RITORNA TRUE SE field E' BISESTILE
Un Anno e' BISESTILE se questo/4 da come resto 0 e /100 e' !=0, oppure
anno/400 da come resto 0.
L'anno bisestile si ripete ogni 4 anni, quindi Febbraio, un mese con 
28 giorni, in questa occasione ne ha 29
Esempio anno bisestile: 2000, 1996, 1992, 1988, ...
- <INPUT TYPE="..." NAME="DATA" maxlength="1">
- CHIAMATA: if (bisestile(document.form.DATA)) ...
*/
function bisestile (field) 
{
	var anno = parseInt(field.value, 10);//Conversione di una stringa in intero in base decimale
	alert(anno)
	return ( (anno % 4 == 0 && anno % 100 != 0) || (anno % 400 == 0) );
};
/*___________________________________SELECT______________________________________________
RITORNA TRUE SE IL CAMPO field E' RIMASTO SELEZIONATO CON L'OPZIONE: 'Seleziona una voce'
BISOGNA PASSARE ALLA FUNZIONE IL VALORE DI QUESTA OPZIONE, IN QUESTO CASO: XX
- <SELECT NAME="TIPO_VIA">
	<OPTION selected value="XX">Seleziona una voce</OPTION>
	<OPTION value="1">Via</OPTION>
	...
  </SELECT>
- CHIAMATA: if (noOptionSelected(document.form.TIPO_VIA, "XX", "TIPO_VIA")) ...
*/
function noOptionSelected (field, valueDefault, nameField)
{
	if (field.options[field.selectedIndex].value == valueDefault)
	{
		alert("Nel campo \"" + nameField + "\" deve essere selezionata una opzione.\nGrazie.");
		field.focus();
		return (true);
	}		
};
/*___________________________________RADIO___________________________________________________
RITORNA TRUE SE NEL CAMPO field NON E' STATA SELEZIONATA ALCUNA OPZIONE
BISOGNA PASSARE ALLA FUNZIONE IL NUMERO DI CAMPI RADIO, I NOMI DEI QUALI DEVONO ESSERE UGUALI
- M<INPUT TYPE="RADIO" VALUE="M" NAME="SEX">
- F<INPUT TYPE="RADIO" VALUE="F" NAME="SEX">
...
- CHIAMATA: if (noRadioSelected(document.form.SEX, 2, "SESSO")) ...
*/
function noRadioSelected (field, numberFields, nameField)
{
	numberTrue = 0;
	var conditions = new Array();
	for (i = 0; i < numberFields; i++)
   	{
		if (field[i].checked == true) numberTrue++;
   	}
	if (numberTrue == 0) 
	{
		alert("Nel campo \"" + nameField + "\" deve essere selezionata una opzione.\nGrazie.");
		return (true);
	}
	else return (false);		
};
/*___________________________________CHECKBOX____________________________________________________
RITORNA TRUE SE NON E' STATA SELEZIONATA IL NUMERO fieldsChecked DI OPZIONI.
BISOGNA PASSARE ALLA FUNZIONE LA FORM COME STRINGA PER COSTRUIRE I CAMPI DINAMICAMENTE, IL
NUMERO DEI CAMPI checkbox SELEZIONABILI E IL NUMERO ESATTO DI CAMPI CHE DEVONO ESSERE SELEZIONATI
ATTENZIONE: Il nome dei checkbox devono essere tutti uguali e differire soltanto con un numero
che deve partire obbligatoriamente da 1, perche' il for inizia da 1 
Il flag puo' assumere i seguenti valori:
... "ESATTO", 3 : selezionate per forza 3 
... "MIN", 3    : selezionate da 3 in poi
... "MAX", 3    : selezionate da 1 a 3
- Sport   <INPUT TYPE="checkbox" NAME="ck1" VALUE="">
- Libri   <INPUT TYPE="checkbox" NAME="ck2" VALUE="">
- Musica  <INPUT TYPE="checkbox" NAME="ck3" VALUE="">
- Tv      <INPUT TYPE="checkbox" NAME="ck4" VALUE="">
- Internet<INPUT TYPE="checkbox" NAME="ck5" VALUE="">
- Cinema  <INPUT TYPE="checkbox" NAME="ck6" VALUE="">
- CHIAMATA: if (noCheckboxSelected("document.form.ck", 6, "INTERESSI", "ESATTO", 3)) ...
*/
function noCheckboxSelected (field, numberFields, nameField, flag, fieldsChecked)
{
	numberTrue = 0;
	var conditions = new Array();
	for (i = 1; i <= numberFields; i++)
   	{
		if (eval(field + i).checked == true) numberTrue++;
   	}
	if ((flag == "ESATTO") && (numberTrue != fieldsChecked)) //selezionate per forza 3
	{
		alert("Nel campo \"" + nameField + "\" devono essere selezionate \"" 
			  + fieldsChecked + "\" opzioni.\nGrazie.");
		return (true);
	}
	else if ((flag == "MIN") && (numberTrue < fieldsChecked)) //selezionate da 3 in poi
	{
		alert("Nel campo \"" + nameField + "\" devono essere selezionate almeno \"" 
			  + fieldsChecked + "\" opzioni.\nGrazie.");
		return (true);
	}
	else if ((flag == "MAX") && ((numberTrue < 1) || (numberTrue > fieldsChecked))) //selezionate da 1 a 3
	{
		alert("Nel campo \"" + nameField + "\" devono essere selezionate 1 o massimo \"" 
			  + fieldsChecked + "\" opzioni.\nGrazie.");
		return (true);
	}
	else return (false);		
};
//___________________________________UTILITY______________________________________________
//VISUALIZZA message NELLA STATUS BAR
function statusBarWrite (message)
{
	window.status = message;
};
//CANCELLA QUALSIASI COSA NELLA STATUS BAR
function statusBarErase ()
{
	window.status = "";
};
//FINESTRA DI CONFERMA CANCELLAZIONE
function AskYesNo()
{
  errwin= window.open("","errwin","width=500,height=200");
  errwin.document.open();
  errwin.document.writeln("<HTML><HEAD><TITLE></TITLE></HEAD>");
  errwin.document.writeln("<BODY BGCOLOR=\"#95DFBO\" BACKGROUND=\"/images/sf_dcp.jpg\">");
  errwin.document.writeln("<CENTER><TABLE BORDER=1 CELLSPACING=0 CELLPADDING=2><TR>");
  errwin.document.writeln("<TD BGCOLOR=\"#5baf6f\"><FONT SIZE=5 COLOR=\"white\"><B>CONFERMI LA CANCELLAZIONE?</B></FONT></TD></TR></TABLE>");
  errwin.document.writeln("<HR>");
  errwin.document.writeln("<TABLE><TR><TD><INPUT TYPE=\"image\" SRC=\"images/b_si.jpg\" onClick=\"return (true);self.close()\"></TD>");
  errwin.document.writeln("<TD><INPUT TYPE=\"image\" SRC=\"images/b_no.jpg\" onClick=\"return (false);self.close()\"></TR></TABLE></CENTER>");
  errwin.document.writeln("</BODY></HTML>");
};
//- CHIAMATA: <A HREF="#" onclick="helpMessage(0, 300, 160)"><IMG SRC="..." ALIGN="CENTER" BORDER="0"></A>
function helpMessage(index, w, h)
{
	var message = new Array ();
	message [0] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Destinazione sociale.</b><BR>" 
		+ "XXX<BR>" 
		+ "<A HREF=\"http://www.iccu.sbn.it\">http://www.iccu.sbn.it</A>");
	message [1] = new Array ("<FONT SIZE=\"2\" COLOR=\"Black\" FACE=\"Arial\"><b>Specializzazione della Biblioteca.</b><BR>" 
		+ "XXX<BR>" 
		+ "<A HREF=\"http://www.iccu.sbn.it\">http://www.iccu.sbn.it</A>");
	
	var s1 = "<HTML><HEAD><TITLE>Informazioni Banche Dati</TITLE></HEAD>" 
		   + "<BODY onBlur=\"self.focus()\" link=\"001063\" vlink=\"#001063\" alink=\"blue\" bgcolor=\"#c6dce1\">" 
		   + "<TABLE BORDER=\"0\"><TR>" 
		   + "<TD WIDTH=90% HEIGHT=90 VALIGN=TOP ALIGN=LEFT>" ;
	var s2 = "</FONT></TD><TD WIDTH=10%></TD></TR><TR><TD></TD>" 
		   + "<TD VALIGN=\"TOP\" ALIGN=\"RIGHT\">" 
		   + "<FORM><INPUT TYPE='BUTTON' VALUE='Chiudi'" 
		   + "onClick='self.close()'>" 
		   + "</FORM></TD></TR></TABLE></BODY><\HTML>";
	popup = window.open("","popDialog","width=" + w + ",height=" + h + ",scrollbars=no");
	popup.document.write(s1 + message[index] + s2);
	popup.document.close();
}
//--->