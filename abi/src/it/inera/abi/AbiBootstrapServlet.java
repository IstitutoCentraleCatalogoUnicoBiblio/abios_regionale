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
