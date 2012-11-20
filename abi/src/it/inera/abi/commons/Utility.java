package it.inera.abi.commons;

import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;
import it.inera.abi.logic.formatodiscambio.imports.ReportImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Classe di utility che permette di effettuare modifiche e controlli di vario genere
 * 
 */
public class Utility {

	public static void createReportFile (String filename, HashMap<Biblioteca, ReportImport> reports, String prologo) {
		StringBuffer sb = new StringBuffer();
		sb.append("********************************************************************************\n");			
		sb.append(prologo);
		sb.append("********************************************************************************\n\n");			
		Iterator<Biblioteca> bibliotecaIterator = reports.keySet().iterator();
		while (bibliotecaIterator.hasNext()) {
			Biblioteca biblioteca = (Biblioteca) bibliotecaIterator.next();
			sb.append("********************************************************************************\n");			
			sb.append("Codice ABI   : " + biblioteca.getAnagrafica().getCodici().getIsil()).append("\n");
			sb.append("Denominazione: " + biblioteca.getAnagrafica().getNomi().getAttuale()).append("\n");
			sb.append("--------------------------------------------------------------------------------\n");
			ReportImport report = reports.get(biblioteca);
			if (report.getErrors().size() == 0 && report.getWarning().size() == 0 ) {
				sb.append("Nessun errore").append("\n");;
				sb.append("Nessun warning").append("\n");;
			} else {
				for (int i = 0; i < report.getWarning().size(); i++) {
					if (i == 0) sb.append("WARNINGS\n").append("\n");;
					sb.append(report.getWarning().get(i)).append("\n");;
				}
				for (int i = 0; i < report.getErrors().size(); i++) {
					if (i == 0) sb.append("ERRORS\n").append("\n");;
					sb.append(report.getErrors().get(i)).append("\n");;
				}
			}
			sb.append("********************************************************************************\n");
		}
		try {
			FileUtils.writeStringToFile(new File(filename), sb.toString());
		} catch (IOException e) {
			// nessuna gestione
			e.printStackTrace();
		}
	}

	public static boolean isAcnp(String acnp) {
		if (acnp == null) return false;
		if (acnp.equalsIgnoreCase("NULL")) return false;
		if (acnp.length() != 5) return false; 
		if (!StringUtils.isAlpha(acnp.substring(0, 2)) || !StringUtils.isNumeric(acnp.substring(3, acnp.length()))) return false;
		return true;
	}

	public static boolean isCei(String cei) {
		if (cei == null) return false;
		if (cei.equalsIgnoreCase("NULL")) return false;
		if (cei.length() != 20) return false; 
		return true;
	}

	public static boolean isCmbs(String cmbs) {
		if (cmbs == null) return false;
		if (cmbs.equalsIgnoreCase("NULL")) return false;
		if (cmbs.length() != 20) return false; 
		return true;
	}

	public static boolean isSbn(String sbn) {
		if (sbn == null) return false;
		if (sbn.equalsIgnoreCase("NULL")) return false;
		if (sbn.length() != 5) return false; 
		if (!StringUtils.isAlpha(sbn.substring(0, 2)) || !StringUtils.isAlphanumeric(sbn.substring(3, sbn.length()))) return false;
		return true;
	}

	/**
	 * Ritorna lo stato da ISIL
	 * @param codABI codice ISIL
	 * @return sigla dello stato
	 */
	public static String getIsilSt(String codABI) {
		return codABI.substring(0, 2);
	}

	/**
	 * Ritorna la provincia da ISIL
	 * @param codABI codice ISIL
	 * @return sigla della provincia
	 */
	public static String getIsilPr(String codABI) {
		return codABI.substring(3, 5);
	}

	public static Integer getIsilNr(String codABI) {
		Integer tmp = Integer.valueOf(codABI.substring(5, 9));
		return tmp;
	}

	/**
	 * Ritorna la sigla della provincia da codice ISIL
	 * @param codABI Codice ISIL
	 * @return Sigla provincia
	 */
	public static String getIsilPr6CharsCode(String codABI) {
		return codABI.substring(0, 2);
	}

	public static Integer getIsilNr6CharsCode(String codABI) {
		Integer tmp = Integer.valueOf(codABI.substring(2, 6));
		return tmp;
	}

	public static String buildIsil(String isilSt, String isilPr, String isilNr) {
		StringBuilder isil = new StringBuilder();
		if (StringUtils.isNotBlank(isilSt)) {
			isil.append(isilSt);
			isil.append("-");
		}
		isil.append(isilPr);
		isil.append(StringUtils.leftPad(String.valueOf(isilNr), 4, '0'));
		return isil.toString();
	}

	public static String buildIsil(it.inera.abi.persistence.NuovaBiblioteca nuovaBiblioteca) {
		return buildIsil(nuovaBiblioteca.getIsilStato(), nuovaBiblioteca.getIsilProvincia(), nuovaBiblioteca.getIsilNumero().toString());
	}
	public static String buildIsil(it.inera.abi.persistence.Biblioteca biblioteca) {
		return buildIsil(biblioteca.getIsilStato(), biblioteca.getIsilProvincia(), biblioteca.getIsilNumero().toString());
	}
	public static String buildIsil6Chars(it.inera.abi.persistence.Biblioteca biblioteca) {
		return buildIsil(null, biblioteca.getIsilProvincia(), biblioteca.getIsilNumero().toString());
	}
	public static String buildIsil6Chars(it.inera.abi.persistence.NuovaBiblioteca nuovaBiblioteca) {
		return buildIsil(null, nuovaBiblioteca.getIsilProvincia(), nuovaBiblioteca.getIsilNumero().toString());
	}

	/**
	 * Riempe la stringa di zeri
	 * @param input La stringa da riempire
	 * @param max La lunghezza della stringa riempita
	 * @return Stringa con 0
	 */
	public static String zeroFill(String input, int max) {
		int fill = max - input.length();
		String tmp = "";
		for (int i = 0; i < fill; i++) {
			tmp += "0";
		}
		return tmp + input;
	}

	/**
	 * Crea un nome temporaneo del file con intrinseche info di
	 * utente, timestamp di upload...
	 * @param utente
	 * @param email
	 * @param originalFilename
	 * @return Il nome del file generato
	 */
	public static  String createTempFileName(String utente, String email, String originalFilename) {
		StringBuffer tmpFileName = new StringBuffer();
		tmpFileName.append(utente);
		tmpFileName.append(Constants.FORMATOSCAMBIO_INFO_SEP);
		long time = System.currentTimeMillis();
		tmpFileName.append(time);
		tmpFileName.append(Constants.FORMATOSCAMBIO_INFO_SEP);
		tmpFileName.append(email);
		tmpFileName.append(Constants.FORMATOSCAMBIO_INFO_SEP);
		tmpFileName.append(originalFilename);
		return tmpFileName.toString();
	}

	/**
	 * Crea un nome temporaneo del file con intrinseche info di
	 * utente, timestamp di upload...
	 * @param utente
	 * @return Il nome del file generato
	 */
	public static  String createTempFileName(String utente) {
		StringBuffer tmpFileName = new StringBuffer();
		tmpFileName.append(utente);
		tmpFileName.append(Constants.FORMATOSCAMBIO_INFO_SEP);
		long time = System.currentTimeMillis();
		tmpFileName.append(time);
		return tmpFileName.toString();
	}

	public static String extractUserNameFromFileName(String fileName) {
		StringTokenizer tokenizer = new StringTokenizer(fileName, Constants.FORMATOSCAMBIO_INFO_SEP);
		if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
		return "";
	}

	public static String extractTimeUploadFromFileName(String fileName) {		
		StringTokenizer tokenizer = new StringTokenizer(fileName, Constants.FORMATOSCAMBIO_INFO_SEP);
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) {
			String timemills = tokenizer.nextToken();
			return DateFormatUtils.format(Long.parseLong(timemills), "HH:mm:ss");
		}
		return "";
	}

	public static String extractDateUploadFromFileName(String fileName) {
		StringTokenizer tokenizer = new StringTokenizer(fileName, Constants.FORMATOSCAMBIO_INFO_SEP);
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) {
			String timemills = tokenizer.nextToken();
			return DateFormatUtils.format(Long.parseLong(timemills), "dd/MM/yyyy");
		}
		return "";
	}

	public static String extractEmailFromFileName(String fileName) {
		fileName = fileName.substring(0, fileName.length() - 4);
		StringTokenizer tokenizer = new StringTokenizer(fileName, Constants.FORMATOSCAMBIO_INFO_SEP);
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
		return "";
	}

	public static String extractOriginalFilenameFromFileName(String fileName) {
		fileName = fileName.substring(0, fileName.length() - 4);
		StringTokenizer tokenizer = new StringTokenizer(fileName, Constants.FORMATOSCAMBIO_INFO_SEP);
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) tokenizer.nextToken();
		if (tokenizer.hasMoreTokens()) return tokenizer.nextToken();
		return "";
	}





	/**
	 * Prende un array di codici ISIL e li ritorna nel formato IT-0001PR
	 * @param codiciAbi Codice ISIL
	 * @return Codice ISIL formattato
	 */
	public static String[] normalizzaCodiciAbi(String[] codiciAbi) {
		String[] normalizzati = new String[codiciAbi.length];
		for (int i = 0; i < codiciAbi.length; i++) {
			normalizzati[i] = normalizzaCodiciAbi(codiciAbi[i]);
		}
		return normalizzati;
	}

	public static String normalizzaCodiciAbi(String codiciAbi) {
		if (codiciAbi.indexOf("-") != -1) {
			return codiciAbi;
		}
		return "IT-" + codiciAbi;
	}

	public static String formattaOrario(String o) {
		String orario = o;
		if((o.length()==4)&&(o.indexOf(":")==1))
			orario = "0" + orario;
		return orario;
	}
	public static String formattaOrario(Time time) {
		String test = time.toString(); //hh:mm:ss
		return test.substring(0,5);
	}
	/**
	 * Recupera le info sui file di import
	 * @param nomeFile
	 * @return Info sui file di import
	 */
	public static ImportFileBean getInfo(String nomeFile) {
		// visualizzo le info dei file
		ImportFileBean importFileBean = new ImportFileBean();
		File importFile = new File(nomeFile);

		importFileBean.fileName = importFile.getName();
		importFileBean.dataUpload = Utility.extractDateUploadFromFileName(importFile.getName());
		importFileBean.dimensione = importFile.length() / 1024 + "Kb";
		importFileBean.oraUpload = Utility.extractTimeUploadFromFileName(importFile.getName());
		importFileBean.utente = Utility.extractUserNameFromFileName(importFile.getName());
		importFileBean.email = Utility.extractEmailFromFileName(importFile.getName());
		importFileBean.originalFilename = Utility.extractOriginalFilenameFromFileName(importFile.getName());

		try {
			String content = FileUtils.readFileToString(importFile);
			importFileBean.nbib = String.valueOf(StringUtils.countMatches(content, "<biblioteca>"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return importFileBean;
	}

	/**
	 * Ritorna l intestazione del charset del file XML
	 * @param xmlFile Contenuto testo del file XML
	 * @return Charset
	 * @throws IOException 
	 */
	public static String retrieveCharset(File xmlFile) throws IOException {
		// if "<?xml version="1.0"?>" then encoding = "ISO-8859-1"
		// else if "<?xml version="1.0" encoding="UTF-8"?>" then "UTF-8"
		// else "ISO-8859-1"
		String xmlTextFile = FileUtils.readFileToString(xmlFile);
		
		String encodingNotFound = "<?xml version=\"1.0\"?>";
		String encodingISO = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
		String encodingUTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

		int indexEcodingNotFound = xmlTextFile.indexOf(encodingNotFound);
		if (indexEcodingNotFound != -1) {
			/* il file comincia con "<?xml version="1.0"?>", l'encoding non è specificato */
			return "ISO-8859-1";

		} else {
			int indexEncodingISO = xmlTextFile.indexOf(encodingISO);
			if (indexEncodingISO != -1) {
				/* il file comincia con "<?xml version="1.0" encoding="ISO-8859-1"?>", l'encoding è ISO-8859-1 */
				return "ISO-8859-1";

			} else {
				/* il file comincia con "<?xml version="1.0" encoding="UTF-8"?>", l'encoding è UTF-8 */
				return "UTF-8";

			}
		} 
	}

	/**
	 * Costruisce il path completo del file XML su cui salvare la biblioteca
	 * @param idbiblio Id della biblioteca
	 * @param saveDir
	 * @return Path del file
	 */
	public static String getSavedFilename(int idbiblio, String saveDir) {
		String filename = String.valueOf(idbiblio).concat(".xml");
		String fullFilename = FilenameUtils.concat(saveDir, filename);
		return fullFilename;
	}

	/**
	 * Costruisce il path completo del file XML per il backup
	 * @param idbiblio Id della biblioteca
	 * @param backupDir
	 * @return Path del file
	 */
	public static String getBackupFilename(int idbiblio, String backupDir) {
		long time = System.currentTimeMillis();
		String filename = String.valueOf(idbiblio).concat("_").concat(String.valueOf(time)).concat(".xml");
		String fullFilename = FilenameUtils.concat(backupDir, filename);
		return fullFilename;
	}

	/**
	 * Estrae il nome della classe dal dalla stringa restituita dal metodo getName() 
	 * richiamato su un oggetto .class
	 * Es: it.inera.persistence.NomeClasse --> NomeClasse 
	 * @param nomeClasseTmp
	 * @return Nome della classe
	 * 
	 */
	public static String extractClassName(String nomeClasseTmp) {
		StringTokenizer tokenizer = new StringTokenizer(nomeClasseTmp, ".");
		int numberTokens=tokenizer.countTokens();
		int i =0;
		while (i!=numberTokens){
			i++;
			if(i==numberTokens)
				nomeClasseTmp=(String)tokenizer.nextToken();
			else tokenizer.nextToken();
		}
		return nomeClasseTmp;
	}

	public static void sendEmail(String subject, String message, String emailTo, String nameTo, String emailFrom, String nameFrom, String emailHostAddress, String emailHostUsername,String emailHostPassword, String emailBounceAddress) throws EmailException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setHostName(emailHostAddress);
		if (StringUtils.isNotBlank(emailHostUsername)) simpleEmail.setAuthentication(emailHostUsername, emailHostPassword);
		simpleEmail.addTo(emailTo, nameTo);
		simpleEmail.setFrom(emailFrom, nameFrom);
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(message);				
		simpleEmail.setBounceAddress(emailBounceAddress);
		simpleEmail.setCharset("UTF-8");
		simpleEmail.send();
	}


}