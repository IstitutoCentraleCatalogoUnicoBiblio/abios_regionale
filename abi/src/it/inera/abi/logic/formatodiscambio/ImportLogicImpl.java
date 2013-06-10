package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.commons.Utility;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteca;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;
import it.inera.abi.logic.formatodiscambio.imports.CheckImportFile;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;
import it.inera.abi.logic.formatodiscambio.imports.Importer;
import it.inera.abi.logic.formatodiscambio.imports.ReportImport;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
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

/**
 * Implementazione delle operazioni di import del formato di scambio
 *
 */
public class ImportLogicImpl implements ImportLogic {

	private Log log = LogFactory.getLog(ImportLogicImpl.class);

	private Importer importer = null;
	//	private CheckImportFile checkImportFile = null;

	private int maxImporter; // = config.getInt(Constants.MAX_IMPORT);

	private String importUncheckedDir = null; //-config.getString(Constants.IMPORT_UNCHECKED_DIR);
	private String importCheckedDir = null; //-config.getString(Constants.IMPORT_CHECKED_DIR)
	private String importScheduledDir = null;//-config.getString(Constants.IMPORT_SCHEDULED_DIR);

	private String emailHostAddress = null; //config.getString(Constants.EMAIL_HOSTNAME_ADDRESS);
	private String emailHostUsername = null; //config.getString(Constants.EMAIL_HOSTNAME_USERNAME);
	private String emailHostPassword = null; //config.getString(Constants.EMAIL_HOSTNAME_PASSWORD);
	private String emailBounceAddress = null; //config.getString(Constants.EMAIL_BOUNCE_ADDRESS);
	private String emailImportAddress = null; //config.getString(Constants.EMAIL_IMPORT_ADDRESS);
	private String emailImportName = null; //config.getString(Constants.EMAIL_IMPORT_NAME);

	private String emailCheckSubject = null;
	private String emailCheckMsg = null;
	
	private String emailImportSubject = null; //config.getString(Constants.EMAIL_IMPORT_SUBJECT);
	private String emailImportMsg = null; //config.getString(Constants.EMAIL_IMPORT_MSG);

	/* Base photo url */
	private String basePhotoUrl = null;


	@Override
	public void upload(String utente, String email, String filename) throws Exception {
		log.info("Start import biblioteche");

		log.info("Utente:" + utente + " Email: " + email);

		// recupero il file uplodato
		File file = new File(filename);
		if (file.exists()) {
			log.info("File caricato con successo..." + file.getAbsolutePath());
			log.info("Utente: " + utente);
		} else {
			log.fatal("Impossibile caricare il file");
			throw new Exception("Impossibile caricare il file");
		}
		
		// controllo il charset del file di input
		String charsetOfFile = Utility.retrieveCharset(file); // default ritorna ISO-8859-1
		
		// crea un file con un nome temporaneo fittizio per spostare e cancellare l uplodato		
		File targetFile = new File(importUncheckedDir + File.separator + Utility.createTempFileName(utente, email, file.getName())  + ".xml");		
		try {
			//FileUtils.copyFile(file, targetFile);
			String tempContent = FileUtils.readFileToString(file, charsetOfFile);
			FileUtils.writeStringToFile(targetFile, tempContent);
			if (file.delete()) {
				log.debug("File temporaneo cancellato");
			} else {
				log.warn("Impossibile cancellare il file temporaneo");
			}
		} catch (IOException e) {
			log.fatal("Impossibile caricare il file", e);
			throw new Exception("Impossibile caricare il file", e);
		}
		
	}

	@Override
	public ImportFileBean[] browseUncheckedFileImport() throws Exception {

		// carico i file che stanno nella directory
		File uncheckedDir = new File(importUncheckedDir);

		File[] importFiles = uncheckedDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("xml".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});
		ImportFileBean[] importFileBeans = new ImportFileBean[importFiles.length];

		// visualizzo le info dei file
		for (int i = 0; i < importFiles.length; i++) {
			importFileBeans[i] = Utility.getInfo(importFiles[i].getAbsolutePath());
		}

		return importFileBeans;
	}

	@Override
	public ImportFileBean[] browseCheckedFileImport() throws Exception {

		// carico i file che stanno nella directory
		File checkedDir = new File(importCheckedDir);

		// implementa un filtro per scartare i file che non sono della biblioteca
		File[] importFiles = checkedDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("xml".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});

		ImportFileBean[] importFileBeans = new ImportFileBean[importFiles.length];

		// visualizzo le info dei file
		for (int i = 0; i < importFiles.length; i++) {
			importFileBeans[i] = Utility.getInfo(importFiles[i].getAbsolutePath());
			
			FileInputStream fis = new FileInputStream(FilenameUtils.removeExtension(importFiles[i].getAbsolutePath()) + ".result");
			ObjectInputStream ois = new ObjectInputStream(fis); 
			ImportFileBean tmp =  (ImportFileBean)ois.readObject();	
			importFileBeans[i].isError = tmp.isError;
			
		}
		return importFileBeans;
	}

	@Override
	public ImportFileBean[] browseScheduledImport() throws Exception {

		// carico i file che stanno nella directory
		File scheduledDir = new File(importScheduledDir);


		// implementa un filtro per scartare i file che non sono della biblioteca
		File[] importFiles = scheduledDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("xml".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});
		ImportFileBean[] importFileBeans = new ImportFileBean[importFiles.length];

		// visualizzo le info dei file
		for (int i = 0; i < importFiles.length; i++) {
			importFileBeans[i] = Utility.getInfo(importFiles[i].getAbsolutePath());
		}
		return importFileBeans;
	}

	@Override
	public ImportFileBean dettaglioChecked(String filename) throws Exception {

		// check dati e controllo errori
		String resultNameFile = FilenameUtils.removeExtension(filename) + ".result";
		ImportFileBean importFileBean = new ImportFileBean();
		try {
			FileInputStream fis = new FileInputStream(importCheckedDir + File.separator + resultNameFile);
			ObjectInputStream ois = new ObjectInputStream(fis); 
			importFileBean =  (ImportFileBean)ois.readObject();			
			ois.close();
		} catch (FileNotFoundException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}
		return importFileBean;
	}

	@Override
	public ImportFileBean dettaglioScheduled(String filename) throws Exception {

		// check dati e controllo errori
		String resultNameFile = FilenameUtils.removeExtension(filename) + ".result";
		ImportFileBean importFileBean = new ImportFileBean();
		try {
			FileInputStream fis = new FileInputStream(importScheduledDir + File.separator + resultNameFile);
			ObjectInputStream ois = new ObjectInputStream(fis); 
			importFileBean =  (ImportFileBean)ois.readObject();			
			ois.close();
		} catch (FileNotFoundException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}

		return importFileBean;
	}

	@Override
	public void checkFile(String filename) throws Exception {
		log.info("Richiesta controllo file " + filename);

		// carico il file richiesto
		File importFile = new File(importUncheckedDir + File.separator + filename);

		// inizio il check asincrono del file
		CheckImportFile checkImportFile = new CheckImportFile();
		checkImportFile.setTargetFile(importFile);

		checkImportFile.setMaxImporter(maxImporter); // = config.getInt(Constants.MAX_IMPORT);
		checkImportFile.setImportCheckedDir(importCheckedDir); //config.getString(Constants.IMPORT_CHECKED_DIR)
		checkImportFile.setEmailHostAddress(emailHostAddress); //config.getString(Constants.EMAIL_HOSTNAME_ADDRESS);
		checkImportFile.setEmailHostUsername(emailHostUsername); //config.getString(Constants.EMAIL_HOSTNAME_USERNAME);
		checkImportFile.setEmailHostPassword(emailHostPassword); //config.getString(Constants.EMAIL_HOSTNAME_PASSWORD);
		checkImportFile.setEmailBounceAddress(emailBounceAddress); //config.getString(Constants.EMAIL_BOUNCE_ADDRESS);

		checkImportFile.setEmailImportAddress(emailImportAddress); //config.getString(Constants.EMAIL_IMPORT_ADDRESS);
		checkImportFile.setEmailImportName(emailImportName); //config.getString(Constants.EMAIL_IMPORT_NAME);
		checkImportFile.setEmailCheckSubject(emailCheckSubject); 
		checkImportFile.setEmailCheckMsg(emailCheckMsg);

		checkImportFile.start();
	}

	@Override
	public void importFile(String filename) throws Exception {
		log.info("Richiesta import file " + filename);
		// carico il file richiesto

		File importFile = new File(importCheckedDir + File.separator + filename);

		// caricamento delle biblioteche che stanno nel file in un oggetto castor
		Biblioteche biblioteche = null;
		try {
			biblioteche = Biblioteche.unmarshal(new FileReader(importFile));
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
		for (int i = 0; i < biblioteche.getBibliotecaCount(); i++) {

			ReportImport reportImport = new ReportImport();

			int n = i + 1;
			it.inera.abi.logic.formatodiscambio.castor.Biblioteca biblioteca = biblioteche.getBiblioteca(i);

			//controllo codice ABI
			String codiceAbi = null;
			if (biblioteca != null) if (biblioteca.getAnagrafica() != null) if (biblioteca.getAnagrafica().getCodici() != null) 
				codiceAbi = biblioteca.getAnagrafica().getCodici().getIsil();
			if (codiceAbi != null && !"".equalsIgnoreCase(codiceAbi)) {
				log.info("*************************************************************************");
				log.info("Inizio import biblioteca " + n + " di " + biblioteche.getBibliotecaCount());
				log.debug("Codice ABI presente nel DB: si procede con l'import della biblioteca...");		
				try {
					importer.doImport(biblioteca, dataExport, reportImport, basePhotoUrl);
				} catch (Exception e) {
					log.error("Si è verificato un errore che non ha permesso di importare nessun dato della biblioteca", e);
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

		String reportfileName = System.getProperty("java.io.tmpdir") + "/report_".concat(String.valueOf(System.currentTimeMillis())).concat("_").concat(filename).concat(".txt");
		Utility.createReportFile(reportfileName, reports, prologo);
		log.info("File elaborato correttamente, sarà spedito il file con il report dell'import");

		String email = Utility.extractEmailFromFileName(filename);



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

	}

	@Override
	public boolean importFileDifferito(String filename)	throws Exception {
		log.info("Richiesta import file " + filename);


		File sourceFile = new File(importCheckedDir + File.separator +  filename);
		
		ImportFileBean beanImport = Utility.getInfo(sourceFile.getAbsolutePath());
		String newFilename = Utility.createTempFileName(beanImport.utente, beanImport.email, beanImport.originalFilename);
		
		File destFile = new File(importScheduledDir + File.separator +  newFilename + ".xml");
		String resultFileName = newFilename + ".result";
		
		File sourceResultFile = new File(importCheckedDir + File.separator +  FilenameUtils.removeExtension(filename) + ".result");
		File destResultFile = new File(importScheduledDir + File.separator +  resultFileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			FileUtils.copyFile(sourceResultFile, destResultFile);
		} catch (IOException e) {
			throw new Exception(e);
		}
		boolean ok = false;
		if (sourceFile.delete() && sourceResultFile.delete()) {
			log.info("File cancellato correttamente");
			ok = true;
		} else {
			log.info("Impossibile cancellare il file");
		}

		log.info("File schedulato correttamente");

		return ok;
	}

	@Override
	public boolean deleteFile(String filename) throws Exception {

		log.info("Richiesta cancellazione file " + filename);

		// cancello il file richiesto
		File importFile = new File(importCheckedDir + File.separator + filename);
		filename = FilenameUtils.removeExtension(importFile.getName());
		File resultImportFile = new File(importCheckedDir + File.separator + filename + ".result");

		boolean ok = false;
		if (importFile.delete() && resultImportFile.delete()) {
			log.info("File cancellato correttamente");
			ok = true;
		} else {
			log.info("Impossibile cancellare il file");
		}

		return ok;
	}

	@Override
	public boolean deleteUncheckedFile(String filename) throws Exception {
		log.info("Richiesta cancellazione file " + filename);

		// cancello il file richiesto
		File importFile = new File(importUncheckedDir + File.separator + filename);

		boolean ok = false;
		if (importFile.delete()) {
			log.info("File cancellato correttamente");
			ok = true;
		} else {
			log.info("Impossibile cancellare il file");
		}

		return ok;
	}

	@Override
	public boolean deleteScheduledFile(String filename, boolean definitivo) throws Exception {
		log.info("Richiesta cancellazione file " + filename);

		File sourceFile = new File(importScheduledDir + File.separator +  filename);
		File destFile = new File(importCheckedDir + File.separator +  filename);
		String resultFileName = FilenameUtils.removeExtension(filename) + ".result";
		File sourceResultFile = new File(importScheduledDir + File.separator +  resultFileName);
		File destResultFile = new File(importCheckedDir + File.separator +  resultFileName);
		try {
			if (!definitivo) {
				FileUtils.copyFile(sourceFile, destFile);
				FileUtils.copyFile(sourceResultFile, destResultFile);
				log.info("File copiato nella cartella dei file controllati");
			}
		} catch (IOException e) {
			throw new Exception(e);
		}
		boolean ok = false;
		if (sourceFile.delete() && sourceResultFile.delete()) {
			log.info("File cancellato correttamente");
			ok = true;
		} else {
			log.info("Impossibile cancellare il file");			
		}

		return ok;
	}

	public void setImporter(Importer importer) {
		this.importer = importer;
	}

	public void setImportUncheckedDir(String importUncheckedDir) {
		this.importUncheckedDir = importUncheckedDir;
	}

	public void setImportCheckedDir(String importCheckedDir) {
		this.importCheckedDir = importCheckedDir;
	}

	public void setImportScheduledDir(String importScheduledDir) {
		this.importScheduledDir = importScheduledDir;
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
	public void setMaxImporter(int maxImporter) {
		this.maxImporter = maxImporter;
	}
	public void setEmailCheckMsg(String emailCheckMsg) {
		this.emailCheckMsg = emailCheckMsg;
	}
	public void setEmailCheckSubject(String emailCheckSubject) {
		this.emailCheckSubject = emailCheckSubject;
	}
	public void setEmailImportMsg(String emailImportMsg) {
		this.emailImportMsg = emailImportMsg;
	}
	public void setEmailImportSubject(String emailImportSubject) {
		this.emailImportSubject = emailImportSubject;
	}
	public void setBasePhotoUrl(String basePhotoUrl) {
		this.basePhotoUrl = basePhotoUrl;
	}
	

	@Override
	public byte[] download(String type, String filename) throws Exception {
		// in base al tipo decido in quale dir
		String path = "";
		if ("checked".equalsIgnoreCase(type)) {
			path = importCheckedDir;
		} else if ("unchecked".equalsIgnoreCase(type)) {
			path = importUncheckedDir;
		} else if ("scheduled".equalsIgnoreCase(type)) {
			path = importScheduledDir;
		}
		// crea un file con un nome temporaneo fittizio per spostare e cancellare l uplodato		
		File targetFile = new File(path + File.separator + filename);		
		if (!targetFile.exists()) {
			log.warn("Impossibile cancellare il file temporaneo");
			throw new Exception("Impossibile trovare il file");
		}
		return FileUtils.readFileToByteArray(targetFile);
	}

}
