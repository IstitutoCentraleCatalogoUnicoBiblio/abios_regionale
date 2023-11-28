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
import it.inera.abi.logic.formatodiscambio.ImportLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet per il caricamento dei file contenenti le biblioteche da importare
 *
 */
public class UploaderServlet extends HttpServlet {

	private static final long serialVersionUID = 4247784319391733203L;
	
	private ImportLogic importLogic;

	private Log log = LogFactory.getLog(UploaderServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");

		String pathUploadedFile = null;
		String username = null;
		String email = null;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<?> items = upload.parseRequest(req);
			Iterator<?> it = items.iterator();

			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (!item.isFormField() && "uploadedfile".equals(item.getFieldName())) {
					File uploadedFile = new File(System.getProperty("java.io.tmpdir") + File.separator + item.getName());
					
					// clean BOM
					InputStream input = Utility.getCleanInputStream(item.getInputStream());
					String contentEncoded = IOUtils.toString(input);
					input.close();
					
					FileWriter wr = new FileWriter(uploadedFile);
					wr.write(contentEncoded);
					wr.flush();
					
					/* sostituito con clean del BOM
					String contentEncoded = item.getString("UTF-8").replace("ISO-8859-1", "UTF-8");
					FileWriter wr = new FileWriter(uploadedFile);
					wr.write(contentEncoded);
					wr.flush();*/
					pathUploadedFile = uploadedFile.getAbsolutePath();
				} else if (item.isFormField()) {
					String name = item.getFieldName();
					if ("username".equalsIgnoreCase(name)) {
						username = item.getString();
					}
					if ("email".equalsIgnoreCase(name)) {
						email = item.getString();
					}
				}
			}

			if (StringUtils.isBlank(username) || StringUtils.isBlank(email) || StringUtils.isBlank(pathUploadedFile)) {
				resp.getWriter().write("NO-SCRIPT-DATA");
				return;
			}
			
			// carico il file nelle cartelle del formato di scambio
			importLogic.upload(username, email, pathUploadedFile);
			resp.getWriter().write("ok");

		} catch (Exception e) {
			log.error("Errore nel caricamento del file del formato di scambio", e);
			resp.getWriter().write("error");
			
		}
	}


	@Required
	@Autowired
	public void setImportLogic(ImportLogic importLogic) {
		this.importLogic = importLogic;
	}

}