/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.logic.stampe;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Servlet per la generazione delle stampe in formato XLS
 *
 */
@SuppressWarnings({"rawtypes", "unchecked","deprecation"})
public class XlsGeneratorServlet extends HttpServlet {

	private static final long serialVersionUID = -1010503769400276382L;

	private String XLS_MIME_TYPE = "application/vnd.ms-excel"; //application/xls";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = response.getOutputStream(); // prendo lo stream di uscita dalla servlet
		try {

			int rowCount = 0;
			int columnParametriRicerca = 0;
			int columnRegione = 0;
			int columnProvincia = 1;
			int columnComune = 2;
			int columnCodABIBiblio = 3;
			int columnDenBiblio = 4;
			int columnIndirizzoBiblio = 5;
			int columnCapBiblio = 6;
			int columnTelBiblio = 7;
			int columnFaxBiblio = 8;
			int columnEmailBiblio = 9;
			int columnUrlBiblio = 10;
			int columnTipFunzBiblio = 11;
			int columnTipAmmBiblio = 12;
			int columnPatrimonio = 13;
			Vector vociPatr = new Vector();

			// Tipo di stampa:
			String type = request.getParameter("FORMATO");
			if (type == null) type = "ETICHETTA";
			HttpSession session = request.getSession();
			Vector biblioSel = (Vector) session.getAttribute("BIBLIOTECHE");
			/*
			 * CREAZIONE EXCEL
			 */		
			// decido in che formato in base alla scelta del utente stampare il excel		
			if ("ETICHETTA".equalsIgnoreCase(type)) {

			} else {
				response.setContentType(XLS_MIME_TYPE); // setto il content-type a xls
				

				// Init document
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("stampe.xls");

				// Create a row and put some cells in it. Rows are 0 based.
				HSSFRow row = null;
				HSSFCellStyle cellStyle = wb.createCellStyle();

				//cellStyle.setAlignment(HSSFCellStyle.ALIGN_FILL);

				/* Data report */
				row = sheet.createRow((short)0);
				HSSFCell cell1 = row.createCell((short)columnParametriRicerca);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(StampeUtils.currentDate().trim());

				/* Parametri di ricerca */
				HashMap<String, Object> labels = (HashMap<String, Object>) session.getAttribute("LABELS");
				String paramQuery = createSqlQuery(labels);
				if (paramQuery != null && paramQuery.length() > 0) {
					//				row = sheet.createRow((short)0);
					//				HSSFCell cell = row.createCell((short)columnParametriRicerca);
					//				cell.setCellStyle(cellStyle);
					//				cell.setCellValue(StampeUtils.currentDate().trim());
					row = sheet.createRow((short)1);
					cell1 = row.createCell((short)columnParametriRicerca);
					cell1.setCellStyle(cellStyle);
					cell1.setCellValue("PARAMETRI DI SELEZIONE PER IL REPORT:");

					StringTokenizer stringTokenizer = new StringTokenizer(paramQuery, "\n");
					while (stringTokenizer.hasMoreTokens()) {
						String nextParams = stringTokenizer.nextToken();
						if(nextParams.indexOf("PATRIMONIO LIBRARIO:")!= -1){
							String[] t = nextParams.split(":");
							String[] t2 = t[1].split(", ");
							if(t2 != null){
								for(int i=0; i<t2.length; i++){
									vociPatr.add(t2[i].replaceAll("\\r", ""));
								}
							}
						}

						row = sheet.createRow((short)2 + rowCount);
						cell1 = row.createCell((short)columnParametriRicerca);
						cell1.setCellStyle(cellStyle);
						cell1.setCellValue(nextParams.trim());
						rowCount++;
					}
				}

				// inizio a stampare le biblioteche, metto un offeset tra l header e le biblioteche
				rowCount =+ 5;

				//INTESTAZIONE TABELLA BIBLIOTECHE
				row = sheet.createRow((short) rowCount);
				HSSFCell cell = row.createCell((short)columnRegione);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				cell.setCellStyle(cellStyle);
				cell.setCellValue("REGIONE");

				cell = row.createCell((short)columnProvincia);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("PROVINCIA");

				cell = row.createCell((short)columnComune);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("COMUNE");

				cell = row.createCell((short)columnCodABIBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("CODICE ABI");

				cell = row.createCell((short)columnDenBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("DENOMINAZIONI");

				cell = row.createCell((short)columnIndirizzoBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("INDIRIZZO");

				cell = row.createCell((short)columnCapBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("CAP");

				cell = row.createCell((short)columnTelBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("TEL.");

				cell = row.createCell((short)columnFaxBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("FAX");

				cell = row.createCell((short)columnEmailBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("E-MAIL");

				cell = row.createCell((short)columnUrlBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("URL");

				cell = row.createCell((short)columnTipFunzBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("TIP. FUNZIONALE");

				cell = row.createCell((short)columnTipAmmBiblio);
				cell.setCellStyle(cellStyle);
				cell.setCellValue("TIP. AMMINISTRATIVA");

				int indexcolumn = columnTipAmmBiblio + 1; 
				for(int i=0; i<vociPatr.size(); i++){
					indexcolumn = indexcolumn + i;
					cell = row.createCell((short)indexcolumn);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(((String)vociPatr.get(i)).trim().toUpperCase());
				}
				rowCount++;

				cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				Hashtable raggruppate = raggruppaBiblioteche(biblioSel);
				Vector raggOrd = new Vector(raggruppate.keySet());
				Collections.sort(raggOrd);
				Iterator itRagg = raggOrd.iterator();
				while (itRagg.hasNext()) {
					String regioneNome = itRagg.next().toString();
					Hashtable regione = (Hashtable) raggruppate.get(regioneNome);
					Vector regOrd = new Vector(regione.keySet());
					Collections.sort(regOrd);
					Iterator itReg = regOrd.iterator();
					while (itReg.hasNext()) {
						String provinciaNome = (String) itReg.next();
						Hashtable provincie = (Hashtable) regione.get(provinciaNome);
						Vector provOrd = new Vector(provincie.keySet());
						Collections.sort(provOrd);
						Iterator itProv = provOrd.iterator();
						while (itProv.hasNext()) {
							String comuneNome = (String) itProv.next();
							Hashtable comuni = (Hashtable) provincie.get(comuneNome);
							Vector comOrd = new Vector(comuni.keySet());
							Collections.sort(comOrd);
							Iterator itCom = comOrd.iterator();
							while (itCom.hasNext()) {
								String idBiblio = (String) itCom.next();						
								BiblioStampe tmp = (BiblioStampe) comuni.get(idBiblio);
								//String theString = String.valueOf( (char)(10) );  
								//short rowInit = (short)rowCount;
								//short rowEnd = maxRows(tmp);
								//REGIONE
								row = sheet.createRow((short) rowCount);
								cell = row.createCell((short) columnRegione);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(regioneNome);

								//PROVINCIA
								cell = row.createCell((short) columnProvincia);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(provinciaNome);

								//COMUNE
								cell = row.createCell((short) columnComune);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(comuneNome);

								//CODICE ABI
								//							String codice = tmp.isil_st +"-" + tmp.isil_pr + tmp.isil_nr;
								String codice = tmp.isil_st +"-" + tmp.isil_pr;
								if (tmp.isil_nr.length() > 0 && tmp.isil_nr.length() < 4) {
									if (tmp.isil_nr.length() == 1) {
										codice = codice.concat("000").concat(tmp.isil_nr);
									}
									else if (tmp.isil_nr.length() == 2) {
										codice = codice.concat("00").concat(tmp.isil_nr);
									}
									else {/* tmp.isil_nr.length() == 3 */ 
										codice = codice.concat("0").concat(tmp.isil_nr);
									}
								}
								else {
									codice = codice.concat(tmp.isil_nr);
								}
								cell = row.createCell((short) columnCodABIBiblio);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(codice);

								//DENOMINAZIONE
								String daInserire = tmp.denominazione;

								//DENOMINAZIONI
								if ((tmp.denominazione_anche!=null)) {	
									for (int i=0; i < tmp.denominazione_anche.size(); i++){
										String den = tmp.denominazione_anche.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if (den.trim().length() > 0){ 
											// metto in xls la denominazione anche
											daInserire += ("\n anche " + den);
										}
									}
								}

								if ((tmp.denominazione_gia != null)) {
									for (int i=0; i < tmp.denominazione_gia.size(); i++){
										String den = tmp.denominazione_gia.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if (den.trim().length()>0){ 
											// metto in xls la denominazione già
											daInserire += ("\n già " + den);
										}
									}
								}

								HSSFCell cell_rigaMultipla = row.createCell((short) columnDenBiblio);
								HSSFCellStyle cellStyle_rigaMultipla = wb.createCellStyle();
								cellStyle_rigaMultipla.setWrapText(true);
								cell_rigaMultipla.setCellStyle(cellStyle_rigaMultipla);
								cell_rigaMultipla.setCellValue(daInserire);

								//INDIRIZZO
								if ((tmp.indirizzo != null)) {
									if ((!"NULL".equals(tmp.indirizzo.toUpperCase()))&&(tmp.indirizzo.trim().length()!=0)){
										cell = row.createCell((short) columnIndirizzoBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(tmp.indirizzo);
									}
								}
								if ((tmp.frazione != null)) {
									if ((!"NULL".equals(tmp.frazione.toUpperCase()))&&(tmp.frazione.trim().length()!=0)){

										// controllo se ho già messo l'indirizzo...ticket #5555
										cell = row.getCell((short) columnIndirizzoBiblio);

										String tmpValue = "";
										if (cell != null) {
											tmpValue = cell.getStringCellValue(); 
											if (StringUtils.isNotEmpty(tmpValue) ) {
												tmpValue += ", Fraz. " + tmp.frazione;
											}
										} else {
											tmpValue += "Fraz. " + tmp.frazione;
										}


										cell = row.createCell((short) columnIndirizzoBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(tmpValue);

									}
								}
								//CAP
								if ((tmp.cap != null)) {
									if ((!"NULL".equals(tmp.cap.toUpperCase()))&&(tmp.cap.trim().length()!=0)){
										cell = row.createCell((short) columnCapBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(tmp.cap.trim());
									} 
								}
								//TEL
								if ((tmp.tel!=null)) {
									String daInserireTel = "";
									for (int i = 0; i<tmp.tel.size(); i++){
										String den = tmp.tel.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if(den.trim().length()>0){ 
											daInserireTel += den + "\n";
											/*						
										cell = row.createCell((short) columnTelBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(den);*/
										}
									}
									HSSFCell cell_rigaMultiplaTel = row.createCell((short) columnTelBiblio);
									HSSFCellStyle cellStyle_rigaMultiplaTel = wb.createCellStyle();
									cellStyle_rigaMultiplaTel.setWrapText(true);
									cell_rigaMultiplaTel.setCellStyle(cellStyle_rigaMultiplaTel);
									cell_rigaMultiplaTel.setCellValue(daInserireTel);
								}
								//FAX
								if ((tmp.fax != null)) {
									String daInserireFax = "";
									for (int i = 0; i < tmp.fax.size(); i++){
										String den = tmp.fax.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if(den.trim().length()>0){
											daInserireFax += den + "\n";
											/*cell = row.createCell((short) columnFaxBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(den);*/
										}
									}
									HSSFCell cell_rigaMultiplaFax = row.createCell((short) columnFaxBiblio);
									HSSFCellStyle cellStyle_rigaMultiplaFax = wb.createCellStyle();
									cellStyle_rigaMultiplaFax.setWrapText(true);
									cell_rigaMultiplaFax.setCellStyle(cellStyle_rigaMultiplaFax);
									cell_rigaMultiplaFax.setCellValue(daInserireFax);
								}
								//E-MAIL
								if ((tmp.email != null)) {
									String daInserireEmail = "";
									for (int i = 0; i < tmp.email.size(); i++){
										String den = tmp.email.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if(den.trim().length()>0){
											daInserireEmail += den + "\n";
											/*cell = row.createCell((short) columnEmailBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(den + " -");*/
										}
									}
									HSSFCell cell_rigaMultiplaEmail = row.createCell((short) columnEmailBiblio);
									HSSFCellStyle cellStyle_rigaMultiplaEmail = wb.createCellStyle();
									cellStyle_rigaMultiplaEmail.setWrapText(true);
									cell_rigaMultiplaEmail.setCellStyle(cellStyle_rigaMultiplaEmail);
									cell_rigaMultiplaEmail.setCellValue(daInserireEmail);
								}
								//URL
								if ((tmp.url != null)) {
									String daInserireUrl = "";
									for (int i = 0; i < tmp.url.size(); i++){
										String den = tmp.url.get(i).toString();
										den = den.replaceAll("\\[", "");
										den = den.replaceAll("]", "");
										if(den.trim().length()!=0){
											daInserireUrl += den + "\n";
											/*cell = row.createCell((short) columnUrlBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(den + " -");*/
										}
									}
									HSSFCell cell_rigaMultiplaUrl = row.createCell((short) columnUrlBiblio);
									HSSFCellStyle cellStyle_rigaMultiplaUrl = wb.createCellStyle();
									cellStyle_rigaMultiplaUrl.setWrapText(true);
									cell_rigaMultiplaUrl.setCellStyle(cellStyle_rigaMultiplaUrl);
									cell_rigaMultiplaUrl.setCellValue(daInserireUrl);
								}
								//TIP. FUNZIONALE
								if ((tmp.tipologiaFunzionale != null)) {
									if ((!"NULL".equals(tmp.tipologiaFunzionale.toUpperCase()))&&(tmp.tipologiaFunzionale.trim().length()!=0)){
										cell = row.createCell((short) columnTipFunzBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(tmp.tipologiaFunzionale);
									} 
								}
								//TIP. AMMINISTRATIVA
								if ((tmp.tipologiaAmministrativa != null)) {
									if ((!"NULL".equals(tmp.tipologiaAmministrativa.toUpperCase()))&&(tmp.tipologiaAmministrativa.trim().length()!=0)){
										cell = row.createCell((short) columnTipAmmBiblio);
										cell.setCellStyle(cellStyle);
										cell.setCellValue(tmp.tipologiaAmministrativa);
									} 
								}
								//PATRIMONIO
								if ((tmp.patrimonio.size() > 0)) {
									/*for(Enumeration e=tmp.getPatrimonio().keys();e.hasMoreElements();){
									String k = (String)e.nextElement();
									String v = (String)tmp.getPatrimonio().get(k);
									indexcolumn = columnTipAmmBiblio + 1;
									int i = 0;
									boolean trovato = false;
									while((!trovato)&&(i<vociPatr.size())){
										String c = ((String)vociPatr.get(i)).toUpperCase();
										if(c.indexOf(k.toUpperCase()) != -1){
											int idx = indexcolumn + i;
											HSSFCell cell_rigaMultiplaPatr = row.createCell((short) idx);
											HSSFCellStyle cellStyle_rigaMultiplaPatr = wb.createCellStyle();
											cellStyle_rigaMultiplaPatr.setWrapText(true);
											cell_rigaMultiplaPatr.setCellStyle(cellStyle_rigaMultiplaPatr);
											int n = Integer.parseInt(v);
											cell_rigaMultiplaPatr.setCellValue(n);
											trovato = true;
										}
										i++;

									}
								} */
									for(int i=0; i<vociPatr.size(); i++){
										String c = ((String)vociPatr.get(i)).trim();
										String k = (String)(tmp.getPatrimonio().get(c));
										indexcolumn = columnTipAmmBiblio + 1;
										int idx = indexcolumn + i;
										if(k != null){
											HSSFCell cell_rigaMultiplaPatr = row.createCell((short) idx);
											HSSFCellStyle cellStyle_rigaMultiplaPatr = wb.createCellStyle();
											cellStyle_rigaMultiplaPatr.setWrapText(true);
											cell_rigaMultiplaPatr.setCellStyle(cellStyle_rigaMultiplaPatr);
											int n = Integer.parseInt(k);
											cell_rigaMultiplaPatr.setCellValue(n);
										}
										else{
											HSSFCell cell_rigaMultiplaPatr = row.createCell((short) idx);
											HSSFCellStyle cellStyle_rigaMultiplaPatr = wb.createCellStyle();
											cellStyle_rigaMultiplaPatr.setWrapText(true);
											cellStyle_rigaMultiplaPatr.setAlignment(HSSFCellStyle.ALIGN_CENTER);
											cell_rigaMultiplaPatr.setCellStyle(cellStyle_rigaMultiplaPatr);
											//int n = Integer.parseInt("0");
											cell_rigaMultiplaPatr.setCellValue("-");
										}
									}
								}
								rowCount++;
							} //-- fine while comune

						} //-- fine while provincia

					} //-- fine while regione	
				}

				// Write the output to a file
				wb.write(os);
			} 
		} catch (Throwable th) {

		} finally {
			os.flush();
			os.close();
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