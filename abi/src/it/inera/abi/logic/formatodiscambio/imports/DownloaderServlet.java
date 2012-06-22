package it.inera.abi.logic.formatodiscambio.imports;

import it.inera.abi.logic.formatodiscambio.ImportLogic;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DownloaderServlet extends HttpServlet {

	@Autowired private ImportLogic importLogic;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String filename = req.getParameter("filename");
		try {
			byte[] content = importLogic.download(type, filename);
			resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
			OutputStream os = resp.getOutputStream(); // prendo lo stream di uscita dalla servlet
			os.write(content);
			os.flush();
			os.close();
		} catch (Exception e) {
			OutputStream os = resp.getOutputStream(); // prendo lo stream di uscita dalla servlet
			os.write((e.getMessage().concat(" ").concat(filename)).getBytes());
			os.flush();
			os.close();
		}
		
	}
}
