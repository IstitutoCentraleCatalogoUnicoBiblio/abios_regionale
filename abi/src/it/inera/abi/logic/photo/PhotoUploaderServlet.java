package it.inera.abi.logic.photo;

import java.io.File;
import java.io.IOException;
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
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet per il caricamento delle foto inserite dall'utente
 *
 */
public class PhotoUploaderServlet extends HttpServlet {

	private static final long serialVersionUID = 4247784319391733203L;
	
	private Log log = LogFactory.getLog(PhotoUploaderServlet.class);
	
	private @Value("${abi.photo.dir}") String basePhotoDir;
	
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
		String codiceIsil = null;
		boolean giaPresente = false;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<?> items = upload.parseRequest(req);
			Iterator<?> it = items.iterator();

			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (!item.isFormField() && "uploadedfile".equals(item.getFieldName())) {
					if (codiceIsil != null) {
						String dirPhotoPath = basePhotoDir + File.separator + 
						codiceIsil.substring(0, 2) + File.separator +
						codiceIsil.substring(3, 5) + File.separator +
						codiceIsil.substring(5);
						
						File dirPhoto = new File(dirPhotoPath);
						dirPhoto.mkdirs();
						
						File uploadedFile = new File(FilenameUtils.concat(dirPhotoPath, item.getName()));
						
						String tempName = item.getName();
						int counter = 1;
						while (uploadedFile.exists()) {
							/* Controllo che all'interno della directory non sia già presente il file da spostare;
							 * nel caso, costruisco un nuovo filename ed itero finchè non è presente alcun file con quel nome */
							giaPresente = true;
							String newName = FilenameUtils.getBaseName(item.getName());
							String ext = FilenameUtils.getExtension(item.getName());

							tempName = newName.concat("_").concat(String.valueOf(counter)).concat(".").concat(ext);

							uploadedFile = new File(FilenameUtils.concat(dirPhotoPath, tempName));
							counter++;
						}
						
						item.write(uploadedFile);
						pathUploadedFile = uploadedFile.getAbsolutePath();
					}
					
				} else if (item.isFormField()) {
					String name = item.getFieldName();
					if ("codiceIsil".equalsIgnoreCase(name)) {
						codiceIsil = item.getString();
					}
				}
			}

			if (StringUtils.isBlank(codiceIsil) || StringUtils.isBlank(pathUploadedFile)) {
				resp.getWriter().write("NO-SCRIPT-DATA");
				return;
			}
			
			String nameFoto = FilenameUtils.getName(pathUploadedFile);
			if (giaPresente) {
				resp.getWriter().write("ok$$$" + nameFoto);
				
			} else {
				resp.getWriter().write("ok");
			}
		} catch (Exception e) {
			log.error("Errore nel caricamento della foto", e);
			resp.getWriter().write("error");
			
		}
	}
}