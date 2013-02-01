package it.inera.abi.logic.stampe;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet per la generazione delle stampe in formato PDF
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PdfGeneratorServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1304667606950091060L;
	
	private String PDF_MIME_TYPE = "application/pdf";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// Tipo di stampa:
		String type = request.getParameter("FORMATO");
		if (type == null) type = "ETICHETTA";
		HttpSession session = request.getSession();
		Vector biblioSel = (Vector) session.getAttribute("BIBLIOTECHE");
		/*
		 * CREAZIONE PDF
		 */		
		// decido in che formato in base alla scelta del utente stampare il pdf		
		if ("ETICHETTA".equalsIgnoreCase(type)) {
			//Init document
			Document document = new Document(PageSize.A4, 0,0,0,0 );
			response.setContentType(PDF_MIME_TYPE); // setto il content-type a pdf
			OutputStream os = response.getOutputStream(); // prendo lo stream di uscitadalla servlet
			PdfWriter.getInstance(document,	os); // creo l istanza per scrivere il doc pdf sul browser
			document.open();
//			Prima pagina con parametri di ricerca
//			String paramQuery = (String)request.getParameter("sql_query");
			HashMap<String, Object> labels = (HashMap<String, Object>) session.getAttribute("LABELS");
			String paramQuery = createSqlQuery(labels);
			if (paramQuery != null && paramQuery.length() > 0) {
					Font font = new Font(Font.HELVETICA, 18, Font.BOLDITALIC);
					Chunk high = new Chunk(StampeUtils.currentDate(), font);
					high.setBackground(new Color(0xCD, 0xFF, 0xFF));
					Paragraph pp = new Paragraph();
					pp.add(high);
					document.add(pp);
					high = new Chunk("PARAMETRI DI SELEZIONE PER IL REPORT:\n", font);
					high.setBackground(new Color(0xCD, 0xFF, 0xFF));
					pp = new Paragraph();
					pp.add(high);
					document.add(pp);
					high = new Chunk(paramQuery, font);
					high.setBackground(new Color(0xCD, 0xFF, 0xFF));
					pp = new Paragraph();
					pp.add(high);
					document.add(pp);
					high = new Chunk("\n\n\n", font);
					high.setBackground(new Color(0xCD, 0xFF, 0xFF));
					pp = new Paragraph();
					pp.add(high);
					document.add(pp);
				
			}
			document.newPage();
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setExtendLastRow(true);
			if(biblioSel.size()%2!=0){
				biblioSel.add(new BiblioStampe());
			}
			for (int i = 0; i < biblioSel.size(); i++) {
				// ad ogni iterazione stampo la biblioteca
				BiblioStampe tmp = (BiblioStampe) biblioSel.get(i);
				String elemDaStampare= "";
				if((tmp.denominazione!=null)){
					if((!"NULL".equals(tmp.denominazione.toUpperCase()))&& tmp.denominazione.trim().length()!=0){
						elemDaStampare = elemDaStampare + tmp.denominazione + "\n";
					}
				}
				if((tmp.indirizzo!=null)){
					if((!"NULL".equals(tmp.indirizzo.toUpperCase()))&&(tmp.indirizzo.trim().length()!=0)){
						elemDaStampare = elemDaStampare + tmp.indirizzo + "\n";
					}
				}
				if((tmp.frazione!=null)){
					if((!"NULL".equals(tmp.frazione.toUpperCase()))&&(tmp.frazione.trim().length()!=0)){
						//System.out.println(" trim tmp.frazione=" + tmp.frazione.trim() + " lung="  +tmp.frazione.trim().length());
						elemDaStampare = elemDaStampare + tmp.frazione + "\n";
					}
				}
				if((tmp.cap!=null)){
					if((!"NULL".equals(tmp.cap.toUpperCase()))&&(tmp.cap.trim().length()!=0)){
						elemDaStampare = elemDaStampare + tmp.cap + "  " + tmp.comune;
					}	
					else{//Per i casi in cui cap="NULL"
						elemDaStampare = elemDaStampare + tmp.comune;
					}
				}
				if((tmp.provincia!=null)){
					if((!"NULL".equals(tmp.provincia.toUpperCase()))&&(!tmp.provincia.equals(tmp.comune)))
						elemDaStampare = elemDaStampare + " (" + tmp.siglaProvincia + ")\n";
				}
				elemDaStampare = elemDaStampare + "\n";
				PdfPCell cell = new PdfPCell(new Paragraph(elemDaStampare));
				cell.setPaddingLeft(20);
				cell.setPaddingTop(15);
				cell.setPaddingRight(8);
				cell.setFixedHeight(105);
				cell.disableBorderSide(com.lowagie.text.Rectangle.LEFT);
				cell.disableBorderSide(com.lowagie.text.Rectangle.RIGHT);
				cell.disableBorderSide(com.lowagie.text.Rectangle.TOP); 
				cell.disableBorderSide(com.lowagie.text.Rectangle.BOTTOM);
				table.addCell(cell);
				System.out.println("Elem da stampare: " + elemDaStampare);
			}
			document.add(table);
			document.close();
		} else {
			//Init document
			Document document = new Document(PageSize.A4, 20,20,40,40 );
			response.setContentType(PDF_MIME_TYPE); // setto il content-type a pdf
			OutputStream os = response.getOutputStream(); // prendo lo stream di uscitadalla servlet
			PdfWriter.getInstance(document,	os); // creo l istanza per scrivere il doc pdf sul browser
			document.open();
			//Settaggi iniziali
			HeaderFooter hf = new HeaderFooter(new Phrase("Page: "), true);
			document.setFooter(hf);
			//Prima pagina con parametri di ricerca
//			String paramQuery = (String)request.getParameter("sql_query");
			HashMap<String, Object> labels = (HashMap<String, Object>) session.getAttribute("LABELS");
			String paramQuery = createSqlQuery(labels);
			if (paramQuery != null && paramQuery.length() > 0) {
				Font font = new Font(Font.HELVETICA, 18, Font.BOLDITALIC);
				Chunk high = new Chunk(StampeUtils.currentDate(), font);
				high.setBackground(new Color(0xCD, 0xFF, 0xFF));
				Paragraph pp = new Paragraph();
				pp.add(high);
				document.add(pp);
				high = new Chunk("PARAMETRI DI SELEZIONE PER IL REPORT:\n", font);
				high.setBackground(new Color(0xCD, 0xFF, 0xFF));
				pp = new Paragraph();
				pp.add(high);
				document.add(pp);
				high = new Chunk(paramQuery, font);
				high.setBackground(new Color(0xCD, 0xFF, 0xFF));
				pp = new Paragraph();
				pp.add(high);
				document.add(pp);
				high = new Chunk("\n\n\n", font);
				high.setBackground(new Color(0xCD, 0xFF, 0xFF));
				pp = new Paragraph();
				pp.add(high);
				document.add(pp);
				
			}
			//document.newPage();
			//Biblioteche
			Hashtable raggruppate = raggruppaBiblioteche(biblioSel);
			/*
			//Enumeration raggruppateKeys = raggruppate.keys();
			//while (raggruppateKeys.hasMoreElements()) {
				//String regioneNome = raggruppateKeys.nextElement().toString();
			* 
			*/
			Vector raggOrd = new Vector(raggruppate.keySet());
			Collections.sort(raggOrd);
			Iterator itRagg = raggOrd.iterator();
			while (itRagg.hasNext()) {
				String regioneNome = itRagg.next().toString();
				Hashtable regione = (Hashtable) raggruppate.get(regioneNome);
				
				Paragraph p = new Paragraph();
				
				Font fontRegione = new Font(Font.HELVETICA, 20, Font.BOLDITALIC);
				Font fontProvincia = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC);
				Font fontComune = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC);
				Chunk high = new Chunk(regioneNome, fontRegione);
				high.setBackground(new Color(0xCD, 0xFF, 0xFF));
				p.add(high);
				document.add(p);
				/*
				//Enumeration regioneKeys = regione.keys();
				//while (regioneKeys.hasMoreElements()) {
					//String provinciaNome = regioneKeys.nextElement().toString();
				* 
				*/
				Vector regOrd = new Vector(regione.keySet());
				Collections.sort(regOrd);
				Iterator itReg = regOrd.iterator();
				while (itReg.hasNext()) {
					String provinciaNome = (String)itReg.next();
					Hashtable provincie = (Hashtable)regione.get(provinciaNome);
					document.add(new Paragraph("    " + provinciaNome + ")", fontProvincia));
					Vector provOrd = new Vector(provincie.keySet());
					Collections.sort(provOrd);
					Iterator itProv = provOrd.iterator();
					while (itProv.hasNext()) {
						String comuneNome = (String)itProv.next();
					/*Enumeration provincieKeys = provincie.keys();
					while (provincieKeys.hasMoreElements()) {
						String comuneNome = provincieKeys.nextElement().toString();*/
						Hashtable comuni = (Hashtable)provincie.get(comuneNome);	
						hf = new HeaderFooter(new Phrase(regioneNome + " - " + provinciaNome + " - " + comuneNome), false);
						document.setHeader(hf);
						document.add(new Paragraph("                     " + comuneNome + ")\n\n", fontComune));
						PdfPTable table = new PdfPTable(1);
						Vector comOrd = new Vector(comuni.keySet());
						Collections.sort(comOrd);
						Iterator itCom = comOrd.iterator();
						while (itCom.hasNext()) {
							String idBiblio = (String)itCom.next();
						/*
						Enumeration comuniKeys = comuni.keys();
						while (comuniKeys.hasMoreElements()) {
							String idBiblio = comuniKeys.nextElement().toString();
							*/
							BiblioStampe tmp = (BiblioStampe)comuni.get(idBiblio);
							// ad ogni iterazione stampo la biblioteca
//							String elemDaStampare=  "Codice: " + tmp.isil_st +"-" + tmp.isil_pr + tmp.isil_nr+ "\n";
							String elemDaStampare=  "Codice: " + tmp.isil_st +"-" + tmp.isil_pr;
							if (tmp.isil_nr.length() > 0 && tmp.isil_nr.length() < 4) {
								 if (tmp.isil_nr.length() == 1) {
									 elemDaStampare = elemDaStampare.concat("000").concat(tmp.isil_nr).concat("\n");
								 }
								 else if (tmp.isil_nr.length() == 2) {
									 elemDaStampare = elemDaStampare.concat("00").concat(tmp.isil_nr).concat("\n");
								 }
								 else {/* tmp.isil_nr.length() == 3 */ 
									 elemDaStampare = elemDaStampare.concat("0").concat(tmp.isil_nr).concat("\n");
								 }
							}
							else {
								elemDaStampare = elemDaStampare.concat(tmp.isil_nr).concat("\n");
							}
							
							elemDaStampare= elemDaStampare +  tmp.denominazione + "\n";
							if((tmp.denominazione_anche!=null)){
								for(int i=0; i<tmp.denominazione_anche.size(); i++){
									String den = tmp.denominazione_anche.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()>0){ 
										elemDaStampare = elemDaStampare +  "  anche " + den + "\n";
									}
								}
							}
							if((tmp.denominazione_gia!=null)){
								for(int i=0; i<tmp.denominazione_gia.size(); i++){
									String den = tmp.denominazione_gia.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()>0){ 
										elemDaStampare = elemDaStampare +  "  già " + den + "\n";
									}
								}
							}
							if((tmp.indirizzo!=null)){
								if((!"NULL".equals(tmp.indirizzo.toUpperCase()))&&(tmp.indirizzo.trim().length()!=0)){
									elemDaStampare = elemDaStampare + tmp.indirizzo + "\n";
								}
							}
							if((tmp.frazione!=null)){
								if((!"NULL".equals(tmp.frazione.toUpperCase()))&&(tmp.frazione.trim().length()!=0)){
									//System.out.println(" trim tmp.frazione=" + tmp.frazione.trim() + " lung="  +tmp.frazione.trim().length());
									elemDaStampare = elemDaStampare + tmp.frazione + "\n";
								}
							}
							if((tmp.cap!=null)){
								if((!"NULL".equals(tmp.cap.toUpperCase()))&&(tmp.cap.trim().length()!=0)){
									elemDaStampare = elemDaStampare + tmp.cap;
								}	
							}
							if((tmp.provincia!=null)&&(tmp.comune!=null)){
								if((!"NULL".equals(tmp.provincia.toUpperCase()))&&(!tmp.provincia.equals(tmp.comune))&& 
								   (!"NULL".equals(tmp.comune.toUpperCase()))&&(tmp.comune.trim().length()!=0)){
										elemDaStampare = elemDaStampare + "  " + tmp.comune + " (" + tmp.siglaProvincia + ")\n";
								}
								else{
									if((!"NULL".equals(tmp.provincia.toUpperCase()))&&(tmp.provincia.equals(tmp.comune))&& 
											   (!"NULL".equals(tmp.comune.toUpperCase()))&&(tmp.comune.trim().length()!=0)){
										elemDaStampare = elemDaStampare + "  " + tmp.comune + "\n";
									}
								}
							}
							else{
								
								if(tmp.provincia==null)
									elemDaStampare = elemDaStampare +  tmp.comune + "\n";
							}
							if((tmp.tel!=null)){
								for(int i=0; i<tmp.tel.size(); i++){
									String den = tmp.tel.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()>0){ 
										elemDaStampare = elemDaStampare +  "  Tel. " + ": " + den + "\n";
									}
								}
							}	
							if((tmp.fax!=null)){
								for(int i=0; i<tmp.fax.size(); i++){
									String den = tmp.fax.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()>0){
										elemDaStampare = elemDaStampare +  "  Fax. " + ": " + den + "\n";
									}
								}
							}
							if((tmp.email!=null)){
								for(int i=0; i<tmp.email.size(); i++){
									String den = tmp.email.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()>0){
										elemDaStampare = elemDaStampare +  "  E-mail " + ": " + den + "\n";
									}
								}
							}
							if((tmp.url!=null)){
								for(int i=0; i<tmp.url.size(); i++){
									String den = tmp.url.get(i).toString();
									den = den.replaceAll("\\[", "");
									den = den.replaceAll("]", "");
									if(den.trim().length()!=0){
										elemDaStampare = elemDaStampare + "  Url " + ": " + den + "\n";
									}
								}
							}
							if(tmp.tipologiaAmministrativa != null){
								if(!"NULL".equalsIgnoreCase(tmp.tipologiaAmministrativa)&& tmp.tipologiaAmministrativa.length()!=0){
									
									elemDaStampare = elemDaStampare + "Tipologia Amministrativa: " + tmp.tipologiaAmministrativa + "\n";
								}
							}
							if(tmp.tipologiaFunzionale != null){
								if(!"NULL".equalsIgnoreCase(tmp.tipologiaFunzionale)&& tmp.tipologiaFunzionale.length()!=0){

									elemDaStampare = elemDaStampare + "Tipologia Funzionale: " + tmp.tipologiaFunzionale + "\n";
								}
							}
							if (labels != null && labels.containsKey("patrimonioLibrario")) {
								List<String> patrlibs = (List<String>) labels.get("patrimonioLibrario");
								if (patrlibs != null && patrlibs.size() > 0) {
									elemDaStampare = elemDaStampare + "PATRIMONIO LIBRARIO:\n";
									for (String entry : patrlibs) {
										for (Enumeration e=tmp.getPatrimonio().keys();e.hasMoreElements();) {
											String k = (String)e.nextElement();
											if (k.equalsIgnoreCase(entry)) {
												String v = (String)tmp.getPatrimonio().get(k);
												elemDaStampare = elemDaStampare + k + ": " + v + "\n";
											}
										}
									}
								}
//								if(tmp.getPatrimonio().size()>0)
//									elemDaStampare = elemDaStampare + "PATRIMONIO LIBRARIO:\n";
//								for(Enumeration e=tmp.getPatrimonio().keys();e.hasMoreElements();){
//									String k = (String)e.nextElement();
//									String v = (String)tmp.getPatrimonio().get(k);
//									elemDaStampare = elemDaStampare + k + ": " + v + "\n";
//								}
							}
							elemDaStampare = elemDaStampare + "\n";
							PdfPCell cell = new PdfPCell(new Paragraph(elemDaStampare));
							cell.setPaddingLeft(50);
							cell.disableBorderSide(com.lowagie.text.Rectangle.LEFT);
							cell.disableBorderSide(com.lowagie.text.Rectangle.RIGHT);
							cell.disableBorderSide(com.lowagie.text.Rectangle.TOP); 
							cell.disableBorderSide(com.lowagie.text.Rectangle.BOTTOM);
							table.addCell(cell);
							/*
							
							//System.out.println(elemDaStampare);
							//System.out.println("Cod. ABI: " + tmp.isil_st +"-" + tmp.isil_pr + tmp.isil_nr + " " + tmp.id_bib + "-" + tmp.regione + "-" + tmp.provincia + "-" + tmp.comune);
							Paragraph pp = new Paragraph(elemDaStampare);
							p.setKeepTogether(true);
							document.add(pp);
							*/					   
						}
						document.add(table);
					}
				}
			}
			document.close();
		}
		response.setContentType(PDF_MIME_TYPE);
		/*
		 * FINE CREAZIONE PDF
		 */		
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}


	}
	
	
	private Hashtable raggruppaBiblioteche(Vector biblioSel) {
		System.out.println(biblioSel.size());
		Hashtable raggruppate = new Hashtable();
		for (int i = 0; i < biblioSel.size(); i++) {
			BiblioStampe tmp = (BiblioStampe) biblioSel.get(i);
			Hashtable regione =  (Hashtable) raggruppate.get(tmp.regione);
			if (regione != null) {
				Hashtable provincia =  (Hashtable) regione.get(tmp.provincia);
				if (provincia != null) {
					Hashtable comune =  (Hashtable) provincia.get(tmp.comune);
					if (comune != null) {
						comune.put(tmp.id_bib, tmp);
					} else {
						comune = new Hashtable();
						comune.put(tmp.id_bib, tmp);
						provincia.put(tmp.comune, comune);
					}
				} else {
					Hashtable comune = new Hashtable();
					comune.put(tmp.id_bib, tmp);
					provincia = new Hashtable();
					provincia.put(tmp.comune, comune);
					regione.put(tmp.provincia, provincia);
				}
			} else {
				Hashtable comune = new Hashtable();
				comune.put(tmp.id_bib, tmp);
				Hashtable provincia = new Hashtable();
				provincia.put(tmp.comune, comune);
				regione = new Hashtable();
				regione.put(tmp.provincia, provincia);
				raggruppate.put(tmp.regione, regione);
			}
			
		}
		//raggruppate = sort(raggruppate);
		return raggruppate;
	}

	
	public void getNomeValoriParametro(String ss, Hashtable h){
		//System.out.println(ss + "\n");
		String s1 = ss.replaceAll("\\(", " ");
		s1 = s1.replaceAll("\\)", " ");
		s1 = s1.replaceAll("UPPER", " ");
		//System.out.println(s1 + "\n");
		String tmp[]=s1.split("\\.");
		String tt = tmp[0].trim();
		//System.out.println(tt + "\n");
		String ttt = s1.substring((s1.indexOf(".")),s1.length()-1 );
		//System.out.println(ttt + "\n");
		String key = "";
		String value = "";
		
		if(tt.equals("R")){
			key = "Denominazione REGIONE: ";
		}
		if(tt.equals("P")){
			key = "Denominazione PROVINCIA: ";
		}
		if(tt.equals("C")){
			key = "Denominazione COMUNE: ";
		}
		if(tt.equals("DS")){
			key = "Descrizione DENOMINAZIONE SOCIALE: ";
		}
		if(tt.equals("TF")){
			key = "Descrizione TIPOLOGIA FUNZIONALE: ";
		}
		if(tt.equals("STATCAT")){
			key = "Descrizione STATO CATALOGAZIONE: ";
		}
		if(tt.equals("DW")){
			key = "Codice DEWEY: ";
		}
		if(tt.equals("E")){
			key = "Identificativo ENTE: ";
		}
		
		if(tt.equals("CCC")){
			key = "Descrizione CATALOGO COLLETTIVO: ";
		}
		if(tt.equals("SB")){
			key = "Descrizione SISTEMA BIBLIOTECARIO: ";
		}
		if(tt.equals("FS")){
			key = "Descrizione SISTEMA BIBLIOTECARIO: ";
		}
		value = getValues(ttt);
		if(tt.equals("B")){
			if(tmp[1].equals("EDIF_MONUM")){
				key = "EDIFICIO MONUMENTALE: ";
			}
			if(tmp[1].equals("USER_NAME")){
				key = "UTENTI ultima modifica: ";
			}
			if(tmp[1].equals("DATA >")){
				key = "DA data: ";
			}
			if(tmp[1].equals("DATA <")){
				key = "A data";
			}
			if(tmp[1].indexOf("NOT IN ")!=-1){
				key = "Biblioteche CORRELATE: ";
				value = "NO";
			}
			else{
				if(tmp[1].indexOf(" IN ")!=-1){
					key = "Biblioteche CORRELATE: ";
					value = "SI";
				}
			}
		}
		//System.out.println("Aggiunto alla tabella Hash il parametro (" + key + ", " + value + ")");
		h.put((String)key, (String)value);
	}
	
	public String getValues(String param){
		
		String[] tmp_2 = param.split("'");
		String value = "";
		for(int i=1; i<tmp_2.length; i=i+2){
			if(i>1)
				value += ", " + tmp_2[i];
			else
				value += tmp_2[i];
		}
		//System.out.println("Value: "+ value);
		return value;
	}
	
	public Hashtable sort(Hashtable ragg){
		Hashtable ragnew = new Hashtable();
		Hashtable regnew = new Hashtable();
		Hashtable pronew = new Hashtable();
		Hashtable comnew = new Hashtable();
		Vector raggOrd = new Vector(ragg.keySet());
		Collections.sort(raggOrd);
		Iterator itRagg = raggOrd.iterator();
		while (itRagg.hasNext()) {
			String denReg = (String)itRagg.next();
			Hashtable reg = (Hashtable)ragg.get(denReg);
			Vector regOrd = new Vector(reg.keySet());
			Collections.sort(regOrd);
			Iterator itReg = regOrd.iterator();
			while (itReg.hasNext()) {
				String denProv = (String)itReg.next();
				Hashtable prov = (Hashtable)reg.get(denProv);
				Vector provOrd = new Vector(prov.keySet());
				Collections.sort(provOrd);
				Iterator itProv = provOrd.iterator();
				while (itProv.hasNext()) {
					String denCom = (String)itProv.next();
					Hashtable com = (Hashtable)prov.get(denCom);
					Vector comOrd = new Vector(com.keySet());
					Collections.sort(comOrd);
					Iterator itCom = comOrd.iterator();
					while (itCom.hasNext()) {
						String idBib = (String)itCom.next();
						BiblioStampe bs = (BiblioStampe) com.get(idBib);
						comnew.put(idBib, bs);
					}
					pronew.put(denCom, comnew);
				}
				regnew.put(denProv, pronew);
			}
			ragnew.put(denReg, regnew);
		}
		return ragnew;
	}
	
	public String createSqlQuery(HashMap<String, Object> labels) {
		StringBuffer stmp = new StringBuffer();
	
		if (labels != null) {
			if (labels.containsKey("regioni")) {
				/* Inserito filtro 'regione' */
				stmp.append("REGIONI: ");
				List<String> tmp = (List<String>) labels.get("regioni");
				
				stmp.append(appendString(tmp));
				stmp.append("\n");
			}
			
			if (labels.containsKey("province")) {
				/* Inserito filtro 'provincia' */
				stmp.append("PROVINCE: ");
				List<String> tmp = (List<String>) labels.get("province");
				
				stmp.append(appendString(tmp));
				stmp.append("\n");
				
			}
			
			if (labels.containsKey("comune")) {
				/* Inserito filtro 'comune' */
				stmp.append("COMUNE: "+labels.get("comune"));
				stmp.append("\n");
				
			}
			
			if (labels.containsKey("tipAmministrativa")) {
				/* Inserito filtro 'tipAmministrativa' */
				stmp.append("TIPOLOGIA AMMINISTRATIVA: ");
				List<String> tmp = (List<String>) labels.get("tipAmministrativa");
				
				stmp.append(appendString(tmp));	
				stmp.append("\n");
			}
			
			if (labels.containsKey("tipFunzionale")) {
				/* Inserito filtro 'tipFunzionale' */
				stmp.append("TIPOLOGIA FUNZIONALE: ");
				List<String> tmp = (List<String>) labels.get("tipFunzionale");
				
				stmp.append(appendString(tmp));	
				stmp.append("\n");
			}
			
			if (labels.containsKey("destSociale")) {
				/* Inserito filtro 'destSociale' */
				stmp.append("DESTINAZIONE SOCIALE: ");
				List<String> tmp = (List<String>) labels.get("destSociale");
				
				stmp.append(appendString(tmp));	
				stmp.append("\n");
			}
			
			if (labels.containsKey("statoCatalogazione")) {
				/* Inserito filtro 'statoCatalogazione' */
				stmp.append("STATO CATALOGAZIONE: ");
				List<String> tmp = (List<String>) labels.get("statoCatalogazione");
				
				stmp.append(appendString(tmp));	
				stmp.append("\n");
			}
			

			if (labels.containsKey("cataloghiCollettivi")) {
				/* Inserito filtro 'cataloghiCollettivi' */
				stmp.append("CATALOGHI COLLETTIVI: ");
				List<String> tmp = (List<String>) labels.get("cataloghiCollettivi");
				
				stmp.append(appendString(tmp));				
				stmp.append("\n");
			}
			

			if (labels.containsKey("sistemiBiblioteche")) {
				/* Inserito filtro 'sistemiBiblioteche' */
				stmp.append("SISTEMI DI BIBLIOTECHE: ");
				List<String> tmp = (List<String>) labels.get("sistemiBiblioteche");
				
				stmp.append(appendString(tmp));			
				stmp.append("\n");
			}
			

			if (labels.containsKey("codiciDewey")) {
				/* Inserito filtro 'codiciDewey' */
				stmp.append("CODICI DEWEY: ");
				List<String> tmp = (List<String>) labels.get("codiciDewey");
				
				stmp.append(appendString(tmp));			
				stmp.append("\n");
			}
			

			if (labels.containsKey("depositoLegale") && labels.get("depositoLegale") != null && 
					((String)labels.get("depositoLegale")).equals("true")) {
				if (labels.containsKey("depositoLegaleTipi") && labels.get("depositoLegaleTipi") != null) {
					/* Inserito filtro 'depositoLegaleTipi' */
					stmp.append("DEPOSITO LEGALE: ");
					List<String> tmp = (List<String>) labels.get("depositoLegaleTipi");
					
					stmp.append(appendString(tmp));			
					stmp.append("\n");
				}
			}
			

			if (labels.containsKey("edificioMonumentale") && labels.get("edificioMonumentale") != null) {
				/* Inserito filtro 'edificioMonumentale' */
					stmp.append("EDIFICIO MONUMENTALE: ");
					if (((String)labels.get("edificioMonumentale")).equals("true"))
						stmp.append("Si");
					else stmp.append("No");
					stmp.append("\n");
			}
			

			if (labels.containsKey("bibliotecheCorrelate") && labels.get("bibliotecheCorrelate") != null) {
				/* Inserito filtro 'bibliotecheCorrelate' */
					stmp.append("BIBLIOTECHE CORRELATE: ");
					if (((String)labels.get("bibliotecheCorrelate")).equals("true"))
						stmp.append("Si");
					else stmp.append("No");
					stmp.append("\n");
			}
			
			if (labels.containsKey("denominazioneFondo") && labels.get("denominazioneFondo") != null) {
				/* Inserito filtro 'denominazioneFondo' */
				stmp.append("DENOMINAZIONE FONDO ("+((String)labels.get("tipoRicercaDenominazioneFondo"))+"): ");
				stmp.append((String)labels.get("denominazioneFondo"));
				stmp.append("\n");
			}
			
			if (labels.containsKey("descrizioneFondo") && labels.get("descrizioneFondo") != null) {
				/* Inserito filtro 'denominazioneFondo' */
				stmp.append("DESCRIZIONE FONDO ("+((String)labels.get("tipoRicercaDescrizioneFondo"))+"): ");
				stmp.append((String)labels.get("descrizioneFondo"));
				stmp.append("\n");
			}
			
			if (labels.containsKey("patrimonioLibrario") && labels.get("patrimonioLibrario") != null) {
				/* Inserito filtro 'patrimonioLibrario' */
				stmp.append("PATRIMONIO LIBRARIO: ");
				List<String> tmp = (List<String>) labels.get("patrimonioLibrario");
				
				stmp.append(appendString(tmp));
				stmp.append("\n");
			}
			
			if (labels.containsKey("prestitoLocale")) {
				stmp.append("PRESTITO LOCALE: Si\n");
			}
			
			if (labels.containsKey("prestitoNazionale")) {
				stmp.append("PRESTITO NAZIONALE: Si\n");
			}
			
			if (labels.containsKey("prestitoInternazionale")) {
				stmp.append("PRESTITO INTERNAZIONALE: Si\n");
			}
			
			if (labels.containsKey("dateAggiornamento") && labels.get("dateAggiornamento") != null) {
				List<String> tmp = (List<String>) labels.get("dateAggiornamento");
				
				if (tmp != null && tmp.size() != 0) {
					if (tmp.get(0) != null && tmp.get(1) != null) {
						Calendar d0 = new GregorianCalendar();
						d0.setTimeInMillis((Long.valueOf(tmp.get(0))).longValue());
						
						stmp.append("DATA DI AGGIORNAMENTO DA: "+getFormatDate(d0)+"\n");
						
						Calendar d1 = new GregorianCalendar();
						d1.setTimeInMillis((Long.valueOf(tmp.get(1))).longValue());
						
						stmp.append("DATA DI AGGIORNAMENTO A: "+getFormatDate(d1)+"\n");
						
					} else {
						if (tmp.get(0) != null) {
							/* Si tratta del campo DAL */
							Calendar d0 = new GregorianCalendar();
							d0.setTimeInMillis((Long.valueOf(tmp.get(0))).longValue());
							
							stmp.append("DATA DI AGGIORNAMENTO DA: "+getFormatDate(d0)+"\n");
						
						} else {
							/* Si tratta del campo AL */
							Calendar d1 = new GregorianCalendar();
							d1.setTimeInMillis((Long.valueOf(tmp.get(1))).longValue());
							
							stmp.append("DATA DI AGGIORNAMENTO A: "+getFormatDate(d1)+"\n");
						}
					}
					
				}				
			}
			
			if (labels.containsKey("utentiUltimaModifica")) {
				List<String> tmp = (List<String>) labels.get("utentiUltimaModifica");
				stmp.append("MODIFICATE DAGLI UTENTI: ");
				stmp.append(appendString(tmp));			
				stmp.append("\n");
			}
			
			return stmp.toString();
			
		}
		else return null;
			
	}
	
	private String appendString(List<String> tmp) {
		StringBuffer sb = new StringBuffer();
		
		if (tmp != null) {
			if (tmp.size() == 1)
				sb.append(tmp.get(0));
			else {
				int i = 0;
				for (String entry : tmp) {
					if (i == (tmp.size()-1))
						sb.append(entry);
					else sb.append(entry+", ");
					i++;	
				}
			}
		}
		return sb.toString();
	}
	
	private String getFormatDate(Calendar date) {
		String formatDate = new String();

		formatDate = formatDate.concat(date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ITALY) + " ");

		if (String.valueOf(date.get(Calendar.DATE)).length() == 1) {
			formatDate = formatDate.concat("0"+ date.get(Calendar.DATE) + " ");
		} else {
			formatDate = formatDate.concat(date.get(Calendar.DATE) + " ");
		}

		formatDate = formatDate.concat(date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ITALY) + " ");
		
		formatDate = formatDate.concat(String.valueOf(date.get(Calendar.YEAR)));

		return formatDate;
	}
}
