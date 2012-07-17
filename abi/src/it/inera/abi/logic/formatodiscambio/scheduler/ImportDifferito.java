package it.inera.abi.logic.formatodiscambio.scheduler;


import it.inera.abi.commons.Utility;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;
import it.inera.abi.logic.formatodiscambio.imports.Importer;
import it.inera.abi.logic.formatodiscambio.imports.ReportImport;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

public class ImportDifferito {

	private Log log = LogFactory.getLog(ImportDifferito.class);
	
	private String importScheduledDir = null; //config.getString(Constants.IMPORT_SCHEDULED_DIR);
	private Importer importer; 
	private String emailHostAddress = null;
	private String emailHostUsername = null;
	private String emailHostPassword = null;
	private String emailImportAddress = null;
	private String emailImportName = null;
	private String emailImportSubject = null;
	private String emailImportMsg = null;
	private String emailBounceAddress = null;
	private String importCheckedDir = null;
	
	public void execute() throws Exception {
		log.info("Start job per l'import differito");
		
		// legge la directory per cercare schedulazioni
		File dirScheduled = new File(importScheduledDir);
		File[] scheduled = dirScheduled.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("xml".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});
		
		// analizza, se trovati, i file
		log.info("Trovate " + scheduled.length + " richieste");
		for (int i = 0; i < scheduled.length; i++) {
			log.info("Partenza primo import nome file " + scheduled[i].getName());
			
			// caricamento delle biblioteche che stanno nel file in un oggetto castor
			Biblioteche biblioteche = null;
			try {
				biblioteche = Biblioteche.unmarshal(new FileReader(scheduled[i]));
				
			} catch (MarshalException e) {
				log.error("File non valido, in questo caso non dovrebbe mai capitare in quanto il file è già stato validato", e);
				
			} catch (ValidationException e) {
				log.error("File non valido, in questo caso non dovrebbe mai capitare in quanto il file è già stato validato", e);
				
			} catch (FileNotFoundException e) {
				log.error("File non trovato", e);
			}

			/**
			 * Inizio import file
			 */
			log.info("Numero di biblioteche da importare : " + biblioteche.getBibliotecaCount());

			HashMap<Biblioteca, ReportImport> reports = new HashMap<Biblioteca, ReportImport> ();

			String startDate = DateFormatUtils.format(new Date(), "HH:mm:ss dd-MM-yyyy");

			Date dataExport = biblioteche.getDataExport();
			for (int j = 0; j < biblioteche.getBibliotecaCount(); j++) {

				ReportImport reportImport = new ReportImport();

				int n = j + 1;
				it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca = biblioteche.getBiblioteca(j);

				//controllo codice ABI
				String codiceAbi = null;
				if (biblioteca != null) if (biblioteca.getAnagrafica() != null) if (biblioteca.getAnagrafica().getCodici() != null) 
					codiceAbi = biblioteca.getAnagrafica().getCodici().getIsil();
				
				if (codiceAbi != null && !"".equalsIgnoreCase(codiceAbi)) {
					log.info("*************************************************************************");
					log.info("Inizio import biblioteca " + n + " di " + biblioteche.getBibliotecaCount());
					log.debug("Codice ABI presente nel DB: si procede con l'import della biblioteca...");		
					try {
						String username = Utility.extractUserNameFromFileName(scheduled[i].getName());
						importer.doImport(biblioteca, dataExport, reportImport, username, true);
						
					} catch (Exception e) {
						log.error("Si è verificato un errore che non ha permesso di importare nessun dato della bilblioteca", e);
						reportImport.addError("Errore che non ha permesso di importare nessun dato della biblioteca: " + e.getMessage());
					}
					reports.put(biblioteca, reportImport);
					log.info("Fine import biblioteca " + n + " di " + biblioteche.getBibliotecaCount());
					log.info("*************************************************************************");
				}
			}
			String endDate = DateFormatUtils.format(new Date(), "HH:mm:ss dd-MM-yyyy");

			/*
			 * Creazione report file
			 */
			String prologo = "Partenza procedura: ".concat(startDate).concat(" termine procedura ").concat(endDate).concat("\n")
			.concat("Biblioteche elaborate: ").concat(String.valueOf(biblioteche.getBiblioteca().length).concat("\n"));

			String reportfileName = System.getProperty("java.io.tmpdir") + "/report_".concat(String.valueOf(System.currentTimeMillis())).concat("_").concat(scheduled[i].getName()).concat(".txt");
			Utility.createReportFile(reportfileName, reports, prologo);
			log.info("File elaborato correttamente, sarà spedito il file con il report dell'import");

			String email = Utility.extractEmailFromFileName(scheduled[i].getName());



			// Prepara e invia la mail al utente con attachment del report
			// Crea l'attach
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(reportfileName);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Report import");
			attachment.setName(FilenameUtils.getName(reportfileName));


			// Create the email message
			MultiPartEmail multipartEmail = new MultiPartEmail();
			try {
				multipartEmail.setHostName(emailHostAddress);
				if (StringUtils.isNotBlank(emailHostUsername))
					multipartEmail.setAuthentication(emailHostUsername, emailHostPassword);
				multipartEmail.addTo(email, email);
				multipartEmail.setFrom(emailImportAddress, emailImportName);
				multipartEmail.setSubject(emailImportSubject);
				multipartEmail.setMsg(emailImportMsg);
				multipartEmail.setBounceAddress(emailBounceAddress);
				multipartEmail.setCharset("UTF-8");
				// add the attachment
				multipartEmail.attach(attachment);

				log.info("Invio email con report in attachment.");
				// send the email
				multipartEmail.send();
				log.debug("Inviata...");

			} catch (EmailException e) {
				log.fatal("Impossibile inviare la email", e);
			}

			FileUtils.deleteQuietly(new File(reportfileName));
			
			
			/* Copio il file appena importato nella directory dei file controllati  */
			File destination = new File(importCheckedDir + File.separator +  scheduled[i].getName());
			
			String resultFileName = FilenameUtils.removeExtension(scheduled[i].getName()) + ".result";
			File sourceResult = new File(dirScheduled + File.separator + resultFileName);
			File destinationResult = new File(importCheckedDir + File.separator + resultFileName);
			
			try {
				FileUtils.copyFile(scheduled[i], destination);
				FileUtils.copyFile(sourceResult, destinationResult);
				
			} catch (IOException e) {
				throw new Exception(e);
			}
			
		}
		
		/* Rimuovo i file appena processati e già copiati nella directory dei file controllati */
		for (int i = 0; i < scheduled.length; i++) {
			FileUtils.deleteQuietly(scheduled[i]);
			String resultFileName = FilenameUtils.removeExtension(scheduled[i].getName()) + ".result";
			File result = new File(dirScheduled + File.separator + resultFileName);
			FileUtils.deleteQuietly(result);
		}
	}

	/**
	 * 
	 */
	public void setImportScheduledDir(String importScheduledDir) {
		this.importScheduledDir = importScheduledDir;
	}
	
	public void setImporter(Importer importer) {
		this.importer = importer;
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
	
	public void setEmailImportAddress(String emailImportAddress) {
		this.emailImportAddress = emailImportAddress;
	}
	
	public void setEmailImportName(String emailImportName) {
		this.emailImportName = emailImportName;
	}
	
	public void setEmailImportSubject(String emailImportSubject) {
		this.emailImportSubject = emailImportSubject;
	}
	
	public void setEmailImportMsg(String emailImportMsg) {
		this.emailImportMsg = emailImportMsg;
	}
	
	public void setEmailBounceAddress(String emailBounceAddress) {
		this.emailBounceAddress = emailBounceAddress;
	}
	
	public void setImportCheckedDir(String importCheckedDir) {
		this.importCheckedDir = importCheckedDir;
	}
}