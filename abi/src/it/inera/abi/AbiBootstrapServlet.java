package it.inera.abi;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet per il controllo e l'eventuale creazione delle directory 'saved'
 * e 'backup' per il salvataggio delle biblioteche e delle directory relative 
 * ai file del formato di scambio
 *
 */
public class AbiBootstrapServlet extends HttpServlet {

	private @Value("${formatodiscambio.tmpdir}") String formatodiscambioTmpdir;
	private @Value("${formatodiscambio.export.scheduled.dir}") String formatodiscambioExportScheduledDir;
	private @Value("${formatodiscambio.import.checked.dir}") String formatodiscambioImportCheckedDir;
	private @Value("${formatodiscambio.import.scheduled.dir}") String formatodiscambioImportDcheduledDir;
	private @Value("${formatodiscambio.import.unchecked.dir}") String formatodiscambioImportUncheckedDir;
	private @Value("${abi.saved.dir}") String abiSaveDir;
	private @Value("${abi.backup.dir}") String abiBackupDir;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
		
		checkBackupDir();
		checkSavedDir();
		checkFormatoScambioDir();
	}
	
	private void checkBackupDir() {
		File checkDir = new File(abiBackupDir);
		checkDir.mkdirs();
	}
	private void checkSavedDir() {
		File checkDir = new File(abiSaveDir);
		checkDir.mkdirs();
	}
	private void checkFormatoScambioDir() {
		File checkDir = new File(formatodiscambioTmpdir);
		checkDir.mkdirs();
		checkDir = new File(formatodiscambioExportScheduledDir);
		checkDir.mkdirs();
		checkDir = new File(formatodiscambioImportCheckedDir);
		checkDir.mkdirs();
		checkDir = new File(formatodiscambioImportDcheduledDir);
		checkDir.mkdirs();
		checkDir = new File(formatodiscambioImportUncheckedDir);
		checkDir.mkdirs();
	}
	
}
