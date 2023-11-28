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

package it.inera.abi.logic.formatodiscambio.imports;

import it.inera.abi.commons.Utility;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

/**
 * Thread che esegue il controllo del file importato 
 *
 */
public class CheckImportFile extends Thread {

	private Log log = LogFactory.getLog(CheckImportFile.class);
	private File targetFile = null;
	//private String email = null;
	
	/**
	 * Configurazione
	 */
	private int maxImporter; // = config.getInt(Constants.MAX_IMPORT);
	private String importCheckedDir = null; //config.getString(Constants.IMPORT_CHECKED_DIR)
	private String emailHostAddress = null; //config.getString(Constants.EMAIL_HOSTNAME_ADDRESS);
	private String emailHostUsername = null; //config.getString(Constants.EMAIL_HOSTNAME_USERNAME);
	private String emailHostPassword = null; //config.getString(Constants.EMAIL_HOSTNAME_PASSWORD);
	private String emailBounceAddress = null; //config.getString(Constants.EMAIL_BOUNCE_ADDRESS);
	
	private String emailImportAddress = null; //config.getString(Constants.EMAIL_IMPORT_ADDRESS);
	private String emailImportName = null; //config.getString(Constants.EMAIL_IMPORT_NAME);
	private String emailCheckSubject = null; //config.getString(Constants.EMAIL_IMPORT_SUBJECT);
	private String emailCheckMsg = null; //config.getString(Constants.EMAIL_IMPORT_MSG);
	
	
	
	
	public CheckImportFile() {				
	}
	
	
	public void run() {
		ImportFileBean importFileBean = checkImportFile();		
		// scrive il risultato del check (serializzazione di importFileBean) in un file associato al xml importato
		try {
			String fileName = FilenameUtils.removeExtension(targetFile.getName());
			FileOutputStream fos = new FileOutputStream (importCheckedDir + File.separator + fileName + ".result");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(importFileBean);
			oos.flush();
   			oos.close();
		} catch (FileNotFoundException e) {
			log.fatal("Errore nella scrittura del oggetto serializzato", e);
		} catch (IOException e) {
			log.fatal("Errore nella scrittura del oggetto serializzato", e);
		} 
		
		// sposta il file nella cartella dei file controllati
		try {
			File destFile = new File(importCheckedDir + File.separator +  targetFile.getName());
			FileUtils.copyFile(targetFile, destFile);
			if (targetFile.delete()) {
				log.debug("File temporaneo cancellato");
			} else {
				log.warn("Impossibile cancellare il file temporaneo");
			}
		} catch (IOException e) {
			log.fatal("Impossibile caricare il file");
		}
		
		sendResultsEmail(importFileBean);
	}
	
	
	public String writeResult(ImportFileBean importFileBean) {
		StringBuffer stringBuffer = new StringBuffer();
		
		String dataUpload = importFileBean.dataUpload;
		if (StringUtils.isNotBlank(dataUpload)) {
			stringBuffer.append("Data upload: ");
			stringBuffer.append(dataUpload + "\n");
		}
		
		String oraUpload = importFileBean.oraUpload;
		if (StringUtils.isNotBlank(oraUpload)) {
			stringBuffer.append("Ora upload: ");	
			stringBuffer.append(oraUpload + "\n");	
		}
		
		String filename = importFileBean.fileName;
		if (StringUtils.isNotBlank(filename)) {
			stringBuffer.append("Filename locale: ");
			stringBuffer.append(filename + "\n");	
		}
		
		String dimensione = importFileBean.dimensione;
		if (StringUtils.isNotBlank(dimensione)) {
			stringBuffer.append("Dimensione: ");
			stringBuffer.append(dimensione + "\n");
		}
		
		
		String utente = importFileBean.utente;
		if (StringUtils.isNotBlank(utente)) {
			stringBuffer.append("Utente che ha richiesto il controllo: ");
			stringBuffer.append(utente + "\n");
		}
		
		boolean isFileError = importFileBean.isError;
		if (isFileError) {
			stringBuffer.append("Si è verificato un errore" + "\n");	
		} else {
			stringBuffer.append("Non si sono verificati errori" + "\n");	
		}
		
		String esito = importFileBean.esito;
		if (StringUtils.isNotBlank(esito)) {
			stringBuffer.append("Esito: ");
			stringBuffer.append(esito + "\n");	
		}
		
		stringBuffer.append("\n\n");	
		Vector<InfoBiblioBean> biblioteche = importFileBean.biblioteche;
		stringBuffer.append("Lista biblioteche controllate: numero totale = " + biblioteche.size() +  "\n");	
		stringBuffer.append("\n\n");
		
		
		for (int i = 0; i < biblioteche.size(); i++) {
			InfoBiblioBean infoBiblioBean = biblioteche.get(i);
			String codiceIccu = infoBiblioBean.codiceIsil;
			stringBuffer.append("\t" + codiceIccu + "\n");
			
			String nome = infoBiblioBean.nome;
			stringBuffer.append("\t" + nome + "\n");
			
			Vector<String> errori = infoBiblioBean.errori;		
			for (int j = 0; j < errori.size(); j++) {
				stringBuffer.append("\t\t" + errori.get(j) + "\n");
			}
			stringBuffer.append("\n");
		}
		
		
		
		
		return stringBuffer.toString();
	}
	

	public ImportFileBean checkImportFile() {
		
		
		// caricamento dati del file di import
		ImportFileBean importFileBean = new ImportFileBean();
		importFileBean.fileName = targetFile.getName();
		importFileBean.dataUpload = Utility.extractDateUploadFromFileName(targetFile.getName());
		importFileBean.dimensione = targetFile.length() / 1024 + "Kb";
		importFileBean.oraUpload = Utility.extractTimeUploadFromFileName(targetFile.getName());
		importFileBean.utente = Utility.extractUserNameFromFileName(targetFile.getName());
		importFileBean.email = Utility.extractEmailFromFileName(targetFile.getName());
		
		// controllo che il file nn contenga piu' del numero massimo di biblioteche consentito, e nel contempo che sia valido
		try {
			FileReader fr = new FileReader(targetFile);
			String contenuto = fr.getEncoding();
			System.out.println("Contenuto: " + contenuto);
			Biblioteche biblioteche = Biblioteche.unmarshal(new FileReader(targetFile));
			int numBiblio = biblioteche.getBibliotecaCount();
			if (numBiblio > maxImporter) {
				log.fatal("Il file contiene un numero di biblioteche superiore a quelle consentite");
				importFileBean.esito = "ERRORE: File non valido\n" +
						"Il file contiene un numero di biblioteche superiore a quelle consentite ("
						.concat(String.valueOf(maxImporter)).concat(")");
				importFileBean.isError = true;
				return importFileBean;
			}
		} catch (Exception e) {
			log.fatal("Il file non è valido", e);
			}
		// caricamento file: controllo validazione e correttezza
		Biblioteche biblioteche = null;
		try {
			FileReader fr = new FileReader(targetFile);
			String contenuto = fr.getEncoding();
			System.out.println("Contenuto: " + contenuto);
			biblioteche = Biblioteche.unmarshal(new FileReader(targetFile));
		} catch (MarshalException e) {
			importFileBean.esito = "ERRORE: File non valido\n" + e.getMessage();
			importFileBean.isError = true;
			return importFileBean;
		} catch (ValidationException e) {
			importFileBean.esito = "ERRORE: File non valido\n" + e.getMessage();
			importFileBean.isError = true;
			return importFileBean;
		} catch (FileNotFoundException e) {
			importFileBean.esito = "ERRORE: File non trovato";
			importFileBean.isError = true;
			return importFileBean;
		}
		

		// controllo algoritmo per la validità delle biblioteche passate
		log.info("controllo algoritmo per la validità delle biblioteche passate");
		Biblioteca biblioteca = null;
		for (int i = 0; i < biblioteche.getBibliotecaCount(); i++) {

			InfoBiblioBean infoBiblioBean = new InfoBiblioBean();
			biblioteca = biblioteche.getBiblioteca(i);
			// copia delle info della biblioteca
			infoBiblioBean.codiceIsil = biblioteca.getAnagrafica().getCodici().getIsil();
			infoBiblioBean.nome = biblioteca.getAnagrafica().getNomi().getAttuale();

			// controllo codice ABI
			String codiceAbi = null;
			if (biblioteca != null && biblioteca.getAnagrafica() != null && biblioteca.getAnagrafica().getCodici() != null) 
				codiceAbi = biblioteca.getAnagrafica().getCodici().getIsil();
			log.debug("codiceAbi" + codiceAbi);
			if (codiceAbi != null && !"".equalsIgnoreCase(codiceAbi)) {
				// controllo se esiste questo codice sulla banca dati, se non esiste ritorno errore
				log.info("Codice ABI : " + codiceAbi);
			} else {
				// controllo se sulla banca dati esiste una biblioteca individuata dalla denominazione e dall indirizzo
				// se esiste torno errore
				log.info("Codice ABI inesistente");
			}
			importFileBean.biblioteche.add(infoBiblioBean);
			// -- fine ciclo verifica biblioteca
		}
		return importFileBean;
	}
	
	private void sendResultsEmail(ImportFileBean importFileBean) {
		// Email check import
		SimpleEmail simpleEmail = new SimpleEmail();
		try {
			simpleEmail.setHostName(emailHostAddress);
			if (StringUtils.isNotBlank(emailHostUsername))
				simpleEmail.setAuthentication(emailHostUsername, emailHostPassword);
			
			simpleEmail.addTo(importFileBean.email, importFileBean.email);
			simpleEmail.setFrom(emailImportAddress, emailImportName);
			simpleEmail.setSubject(emailCheckSubject);
			
			StringBuffer risultati = new StringBuffer();
			risultati.append(emailCheckMsg);
			risultati.append("\n\n");
			risultati.append(writeResult(importFileBean));
			simpleEmail.setMsg(risultati.toString());
			simpleEmail.setBounceAddress(emailBounceAddress);
			simpleEmail.setCharset("UTF-8");
			log.info("Invio email di notifica risultati.");
			simpleEmail.send();
			log.debug("Inviata...");
		} catch (EmailException e1) {
			log.fatal("Impossibile inviare la email di check file", e1);
		}
	}

	/**
	 * 
	 */
	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	public void setMaxImporter(int maxImporter) {
		this.maxImporter = maxImporter;
	}

	public void setImportCheckedDir(String importCheckedDir) {
		this.importCheckedDir = importCheckedDir;
	}

	public void setEmailHostAddress(String emailHostAddress) {
		this.emailHostAddress = emailHostAddress;
	}

	public void setEmailHostUsername(String emailHostUsername) {
		this.emailHostUsername = emailHostUsername;
	}

	public void setEmailHostPassword(String emailHostPassword) {
		this.emailHostPassword = emailHostPassword;
	}

	public void setEmailBounceAddress(String emailBounceAddress) {
		this.emailBounceAddress = emailBounceAddress;
	}

	public void setEmailImportAddress(String emailImportAddress) {
		this.emailImportAddress = emailImportAddress;
	}

	public void setEmailImportName(String emailImportName) {
		this.emailImportName = emailImportName;
	}

	public void setEmailCheckSubject(String emailCheckSubject) {
		this.emailCheckSubject = emailCheckSubject;
	}

	public void setEmailCheckMsg(String emailCheckMsg) {
		this.emailCheckMsg = emailCheckMsg;
	}
	
}