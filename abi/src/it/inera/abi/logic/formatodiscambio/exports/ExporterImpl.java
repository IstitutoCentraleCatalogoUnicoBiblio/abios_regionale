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

package it.inera.abi.logic.formatodiscambio.exports;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.logic.formatodiscambio.castor.Biblioteche;
import it.inera.abi.persistence.Biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe che implementa l'export online e differito 
 *
 */
@Component
public class ExporterImpl implements Exporter {
	
	private Log log = LogFactory.getLog(ExporterImpl.class);
	
	private BiblioDao biblioDao;
	
	/**
	 * Configurazione
	 */
	// eseguo l'export con il numero massimo di biblioteche consentite
	private int maxExport;// = config.getInt(Constants.MAX_EXPORT);
	private String tmpDir = null; //config.getString(Constants.TMP_DIR);
	
	private String emailHostAddress = null; //config.getString(Constants.EMAIL_HOSTNAME_ADDRESS);
	private String emailHostUsername = null; //config.getString(Constants.EMAIL_HOSTNAME_USERNAME);
	private String emailHostPassword = null; //config.getString(Constants.EMAIL_HOSTNAME_PASSWORD);
	private String emailBounceAddress = null; //config.getString(Constants.EMAIL_BOUNCE_ADDRESS);
	
	private String emailExportStartAddress = null; //config.getString(Constants.EMAIL_EXPORT_START_ADDRESS);
	private String emailExportStartName = null; //config.getString(Constants.EMAIL_EXPORT_START_NAME);
	private String emailExportStartSubject = null; //config.getString(Constants.EMAIL_EXPORT_START_SUBJECT);
	private String emailExportStartMsg = null; //config.getString(Constants.EMAIL_EXPORT_START_MSG);
	
	private String emailExportStartDifferitoSubject = null;
	private String emailExportStartDifferitoMsg = null; 
	
	
	private String emailExportErrorAddress = null; //config.getString(Constants.EMAIL_EXPORT_ERROR_ADDRESS);
	private String emailExportErrorName = null; //config.getString(Constants.EMAIL_EXPORT_ERROR_NAME);
	private String emailExportErrorSubject = null; //config.getString(Constants.EMAIL_EXPORT_ERROR_SUBJECT);
	private String emailExportErrorMsg = null; //config.getString(Constants.EMAIL_EXPORT_ERROR_MSG);
	
	
	private String emailExportAttachAddress = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_ADDRESS);
	private String emailExportAttachName = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_NAME);
	private String emailExportAttachSubject = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_SUBJECT);
	private String emailExportAttachMsg = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_MSG);
	
	private String emailExportAttachFiledescription = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_FILEDESCRIPTION);
	private String emailExportAttachFilename = null; //config.getString(Constants.EMAIL_EXPORT_ATTACH_FILENAME);
	
	/* Base photo url */
	private String basePhotoUrl = null;
	
	@Override
	@Transactional
	public void doExport(String[] idBib, String email) {
		doExport(idBib, email, false);	
	}
	
	
	@Override
	@Transactional
	public void doExport(String[] idBib, String email, boolean differito) {
		log.info("Start export biblioteche");
		try {
			// Email start export
			SimpleEmail simpleEmail = new SimpleEmail();
			try {
				simpleEmail.setHostName(emailHostAddress);
				if (StringUtils.isNotBlank(emailHostUsername)) simpleEmail.setAuthentication(emailHostUsername, emailHostPassword);
				simpleEmail.addTo(email, email);
				simpleEmail.setFrom(emailExportStartAddress, emailExportStartName);
				
				if (differito) {
					simpleEmail.setSubject(emailExportStartDifferitoSubject);
					simpleEmail.setMsg(emailExportStartDifferitoMsg);
					
				} else {
					simpleEmail.setSubject(emailExportStartSubject);
					simpleEmail.setMsg(emailExportStartMsg);				
				}
				
				
				simpleEmail.setBounceAddress(emailBounceAddress);
				log.info("Invio email di notifica partenza.");
				simpleEmail.setCharset("UTF-8");
				simpleEmail.send();
				log.debug("Inviata...");
			} catch (EmailException e1) {
				log.fatal("Impossibile inviare la email di start export", e1);
			}
			
			// numero di file in cui sarà suddiviso l export
			int maxFileExport = (idBib.length / maxExport) + 1;
			
			// query e creazione dei file di export paginati per il numero massimo di biblioteche esportabili
			String[] fileNameExport = new String[maxFileExport];
			for (int i = 0; i < maxFileExport; i++) {
				
				// recupero delle biblioteche richieste
				log.info("Start export biblioteche tramite id");
				Biblioteca[] bibliotecheDb = biblioDao.getBibliotecheByIdBib(idBib, i * maxExport, maxExport);
				log.info("Trovate " + bibliotecheDb.length + " biblioteche.");
				// Mappa tra hibernate e castor
				Biblioteche biblioteche = new Biblioteche();
				biblioteche.setDataExport(new Date());
				
				it.inera.abi.logic.formatodiscambio.castor.Biblioteca bibliotecaCastor = null;
				log.debug("******************************************************************************************");
				log.debug("STARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTARTSTART");
				log.debug("******************************************************************************************");
				for (int j = 0; j < bibliotecheDb.length; j++) {
					log.debug("START EXPORT BIBLIOTECA " + j);
					log.info("Mapping biblioteca con id = " + bibliotecheDb[j].getIdBiblioteca());			
					bibliotecaCastor = new it.inera.abi.logic.formatodiscambio.castor.Biblioteca();
					DatabaseToCastorMapper.doDatabaseToCastorMapper(bibliotecheDb[j], bibliotecaCastor, basePhotoUrl);
					biblioteche.addBiblioteca(bibliotecaCastor);
					log.debug("FINE EXPORT BIBLIOTECA " + j);
				}
				log.debug("********************************************************************************************");
				log.debug("FINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINEFINE");
				log.debug("********************************************************************************************");
				
				
				//Data export
				GregorianCalendar dataXML = new GregorianCalendar();
				biblioteche.setDataExport(dataXML.getTime());
				
				// Ritorno xml dal oggetto castor: creo la stringa di xml
				StringWriter sw = new StringWriter();
				biblioteche.marshal(sw);
				
				// crea il file temporaneo con l xml
				fileNameExport[i] = tmpDir + File.separator + System.currentTimeMillis() + "_part" + i + ".xml";
				File tmpFile = new File(fileNameExport[i]);
				try {
					tmpFile.createNewFile();
				} catch (IOException e) {
					log.fatal("Impossibile creare il file temporaeno", e);
				}
				// scrive l xml di export nel file temporaneo
				FileWriter fileWriter = new FileWriter(tmpFile);
				// Ticket mantis: 0004292 su nazionale, problema relativo al charset, commentato qui di seguito e sostituito con la scrittura del solo sw.toString()
//				fileWriter.write(sw.toString().replaceAll("UTF-8", "ISO-8859-1"));
				fileWriter.write(sw.toString());
				fileWriter.flush();
				fileWriter.close();
			}
			
			sendExportFile(fileNameExport, email);

			
		} catch (Throwable t) {
			log.error("Errore email exporter", t);
			sendEmailError(email, "", t);
		}
	}
	
	private void sendExportFile(String[] fileName, String email) {
		for (int i = 0; i < fileName.length; i++) {
			// zippa il file prima di inviarlo
			String zipFileName = createZipFile(fileName[i]);
			
			
			// Prepara e invia la mail al utente con attachment del xml export
			// Crea l'attach
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(zipFileName);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription(emailExportAttachFiledescription);
			attachment.setName(emailExportAttachFilename + "_part" + i + ".zip");
			
			
			// Create the email message
			MultiPartEmail multipartEmail = new MultiPartEmail();
			try {
				multipartEmail.setHostName(emailHostAddress);
				if (StringUtils.isNotBlank(emailHostUsername))
					multipartEmail.setAuthentication(emailHostUsername, emailHostPassword);
				multipartEmail.addTo(email, email);
				multipartEmail.setFrom(emailExportAttachAddress, emailExportAttachName);
				multipartEmail.setSubject(emailExportAttachSubject);
				multipartEmail.setMsg(emailExportAttachMsg);
				multipartEmail.setBounceAddress(emailBounceAddress);

				// add the attachment
				multipartEmail.attach(attachment);

				log.info("Invio email con export in attachment.");
				// send the email
				multipartEmail.send();
				log.debug("Inviata...");

			} catch (EmailException e) {
				log.fatal("Impossibile inviare la email", e);
			}
			
			// Cancella il file temporaneo
			File file = new File(fileName[i]); 
			file.delete();
			
			// Cancella il file zippato
			file = new File(zipFileName); 
			file.delete();
		}
	}

	
	private void sendEmailError(String email, String msg, Throwable t) {
		// Email errore avvenuto.....
		SimpleEmail simpleEmail = new SimpleEmail();
		try {
			simpleEmail.setHostName(emailHostAddress);
			if (StringUtils.isNotBlank(emailHostUsername))
				simpleEmail.setAuthentication(emailHostUsername, emailHostPassword);
			simpleEmail.addTo(email, email);
			simpleEmail.setFrom(emailExportErrorAddress, emailExportErrorName);
			simpleEmail.setSubject(emailExportErrorSubject);
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			printWriter.write(msg + "\n\n");
			if (t != null) {
				t.printStackTrace(printWriter);
			}
			simpleEmail.setMsg(emailExportErrorMsg + stringWriter.toString());
			simpleEmail.setBounceAddress(emailBounceAddress);
			log.info("Invio email con stacktrace dell'errore.");
			simpleEmail.setCharset("UTF-8");
			simpleEmail.send();
			log.debug("Inviata...");
		} catch (EmailException e1) {
			log.fatal("Impossibile inviare la email di errore", e1);
		}
	}
	
	private String createZipFile(String fileNameToZip) {
		// crea il nome del file zippato
		String fileNameZipTemp = FilenameUtils.removeExtension(fileNameToZip)  + ".zip";
		
		
		// These are the files to include in the ZIP file
	    String[] filenames = new String[]{fileNameToZip};
	    
	    // Create a buffer for reading the files
	    byte[] buf = new byte[1024];
	    
	    try {
	        // Create the ZIP file
	        String outFilename = fileNameZipTemp;
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
	    
	        // Compress the files
	        for (int i = 0; i < filenames.length; i++) {
	            FileInputStream in = new FileInputStream(filenames[i]);
	    
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(FilenameUtils.getName(filenames[i])));
	    
	            // Transfer bytes from the file to the ZIP file
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	    
	            // Complete the entry
	            out.closeEntry();
	            in.close();
	        }
	    
	        // Complete the ZIP file
	        out.close();
	    } catch (IOException e) {
	    	log.fatal("Errore nella creazione del file zip");
	    }
	    return fileNameZipTemp;
	}

	/**
	 * 
	 */
	@Autowired
	@Required
	public void setBiblioDao(BiblioDao biblioDao) {
		this.biblioDao = biblioDao;
	}
	
	public void setMaxExport(int maxExport) {
		this.maxExport = maxExport;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
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

	public void setEmailExportStartAddress(String emailExportStartAddress) {
		this.emailExportStartAddress = emailExportStartAddress;
	}

	public void setEmailExportStartName(String emailExportStartName) {
		this.emailExportStartName = emailExportStartName;
	}

	public void setEmailExportStartSubject(String emailExportStartSubject) {
		this.emailExportStartSubject = emailExportStartSubject;
	}

	public void setEmailExportStartMsg(String emailExportStartMsg) {
		this.emailExportStartMsg = emailExportStartMsg;
	}
	
	public void setEmailExportStartDifferitoSubject(String emailExportStartDifferitoSubject) {
		this.emailExportStartDifferitoSubject = emailExportStartDifferitoSubject;
	}

	public void setEmailExportStartDifferitoMsg(String emailExportStartDifferitoMsg) {
		this.emailExportStartDifferitoMsg = emailExportStartDifferitoMsg;
	}

	public void setEmailExportErrorAddress(String emailExportErrorAddress) {
		this.emailExportErrorAddress = emailExportErrorAddress;
	}

	public void setEmailExportErrorName(String emailExportErrorName) {
		this.emailExportErrorName = emailExportErrorName;
	}

	public void setEmailExportErrorSubject(String emailExportErrorSubject) {
		this.emailExportErrorSubject = emailExportErrorSubject;
	}

	public void setEmailExportErrorMsg(String emailExportErrorMsg) {
		this.emailExportErrorMsg = emailExportErrorMsg;
	}

	public void setEmailExportAttachAddress(String emailExportAttachAddress) {
		this.emailExportAttachAddress = emailExportAttachAddress;
	}

	public void setEmailExportAttachName(String emailExportAttachName) {
		this.emailExportAttachName = emailExportAttachName;
	}

	public void setEmailExportAttachSubject(String emailExportAttachSubject) {
		this.emailExportAttachSubject = emailExportAttachSubject;
	}

	public void setEmailExportAttachMsg(String emailExportAttachMsg) {
		this.emailExportAttachMsg = emailExportAttachMsg;
	}

	public void setEmailExportAttachFiledescription(String emailExportAttachFiledescription) {
		this.emailExportAttachFiledescription = emailExportAttachFiledescription;
	}

	public void setEmailExportAttachFilename(String emailExportAttachFilename) {
		this.emailExportAttachFilename = emailExportAttachFilename;
	}
	
	public void setBasePhotoUrl(String basePhotoUrl) {
		this.basePhotoUrl = basePhotoUrl;
	}
	
}
