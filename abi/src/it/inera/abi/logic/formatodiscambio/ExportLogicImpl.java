package it.inera.abi.logic.formatodiscambio;

import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.logic.formatodiscambio.exports.ExportBean;
import it.inera.abi.logic.formatodiscambio.exports.Exporter;
import it.inera.abi.logic.formatodiscambio.exports.email.AsyncEmailExporter;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;
import it.inera.abi.logic.formatodiscambio.imports.InfoBiblioBean;
import it.inera.abi.persistence.Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class ExportLogicImpl implements ExportLogic {

	private Log _log = LogFactory.getLog(ExportLogicImpl.class);

	private String exportScheduledDir = null; //config.getString(Constants.EXPORT_SCHEDULED_DIR)

	private BiblioDao biblioDao;
	
	private Exporter exporter;
	
	@Override
	public ExportBean[] browseScheduledExport() throws Exception {
		// carico i file che stanno nella directory
		File scheduledDir = new File(exportScheduledDir);
		// implementa un filtro per scartare i file che non sono della biblioteca
		File[] exportFiles = scheduledDir.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if ("export".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()))) {
					return true;
				}
				return false;
			}
		});
		ExportBean[] exportBeans = new ExportBean[exportFiles.length]; 
		// legge le info dal file
		for (int i = 0; i < exportFiles.length; i++) {
//			exportBeans[i] = Utility.getScheduledExport(exportFiles[i].getAbsolutePath());
			
			// visualizzo le info dei file
			exportBeans[i] = new ExportBean();
			File exportFile = new File(exportFiles[i].getAbsolutePath());
			exportBeans[i].fileName = exportFile.getName(); 
			exportBeans[i].data = DateFormatUtils.format(Long.parseLong(FilenameUtils.removeExtension(exportFile.getName())), "dd/MM/yyyy");
			exportBeans[i].ora = DateFormatUtils.format(Long.parseLong(FilenameUtils.removeExtension(exportFile.getName())), "HH:mm:ss");
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(exportFile));
				exportBeans[i].email = bufferedReader.readLine();
				exportBeans[i].username = bufferedReader.readLine();
				String line = bufferedReader.readLine();
//				Vector<String> biblioV = new Vector<String>();
				int nBib = 0;
				while (line != null) {
					nBib++;
//					String[] biblioInfo = line.split("\\|\\|\\|"); //idbiblio|||codiccu|||denominazione
//					InfoBiblioBean infoBiblioBean = new InfoBiblioBean();
//					infoBiblioBean.codiceIccu = biblioInfo[1];
//					infoBiblioBean.nome = biblioInfo[2];
//					exportBeans[i].biblioteche.add(infoBiblioBean);
//					biblioV.add(biblioInfo[0]);
					line = bufferedReader.readLine();
					
				}				
				bufferedReader.close();
				exportBeans[i].nBib = nBib;
			} catch (FileNotFoundException e) {
				_log.error(e);
			} catch (IOException e) {
				_log.error(e);
			}
		}
		return exportBeans;
	}
	
	@Override
	public ExportBean dettaglioScheduled(String filename) throws Exception {
		File exportFile = new File(exportScheduledDir + File.separator + filename);
		BufferedReader bufferedReader = null;
		ExportBean exportBean = new ExportBean();
		try {
			bufferedReader = new BufferedReader(new FileReader(exportFile));
			exportBean.email = bufferedReader.readLine();
			exportBean.username = bufferedReader.readLine();
			String line = bufferedReader.readLine();
			Vector<String> biblioV = new Vector<String>();
			while (line != null) {
				String[] biblioInfo = line.split("\\|\\|\\|"); //idbiblio|||codiccu|||denominazione
				InfoBiblioBean infoBiblioBean = new InfoBiblioBean();
				infoBiblioBean.codiceIsil = biblioInfo[1];
				infoBiblioBean.nome = biblioInfo[2];
				exportBean.biblioteche.add(infoBiblioBean);
				biblioV.add(biblioInfo[0]);
				line = bufferedReader.readLine();
			}				
			bufferedReader.close();
			exportBean.nBib = biblioV.size();
		} catch (FileNotFoundException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
		}
		return exportBean;
	}

	@Override
	public boolean deleteScheduledFile(String filename) throws Exception {
		_log.info("Richiesta cancellazione file " + filename);
		File sourceFile = new File(exportScheduledDir + File.separator +  filename);
		boolean ok = false;
		if (ok = sourceFile.delete()) {
			_log.info("File cancellato correttamente");
		} else {
			_log.warn("Impossibile cancellare il file");
		}
		return ok;
	}

	@Override
	public void doExport(String[] idBiblios, String username, String email , boolean differito) throws Exception  {
		/**
		 * Controllo se l export è online o differito
		 */
		if (differito) {
			// crea uno scheduler nella directory configurata
			File scheduled = new File(exportScheduledDir + File.separator + System.currentTimeMillis() + ".export");
			FileWriter fileWriter = new FileWriter(scheduled);
			fileWriter.write(email + "\n");
			fileWriter.write(username + "\n");
			for (int i = 0; i < idBiblios.length; i++) {
				Biblioteca biblioteca = biblioDao.getBibliotecaById(Integer.valueOf(idBiblios[i]));
				String iccuCod = Utility.createIccuCode(biblioteca.getIsilStato(), biblioteca.getIsilProvincia(), String.valueOf(biblioteca.getIsilNumero()));
				fileWriter.write(idBiblios[i] + "|||" + iccuCod + "|||" + biblioteca.getDenominazioneUfficiale() + "\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} else {
			// genera export online
			AsyncEmailExporter  producer = new AsyncEmailExporter(idBiblios, email, exporter, false);
			producer.start();
		}	
	}
	
	public void setExporter(Exporter exporter) {
		this.exporter = exporter;
	}
	public void setExportScheduledDir(String exportScheduledDir) {
		this.exportScheduledDir = exportScheduledDir;
	}
	
	@Required
	@Autowired
	public void setBiblioDao(BiblioDao biblioDao) {
		this.biblioDao = biblioDao;
	}
}