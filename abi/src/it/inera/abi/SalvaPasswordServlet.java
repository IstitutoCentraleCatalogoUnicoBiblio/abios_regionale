package it.inera.abi;

import java.io.IOException;

import it.inera.abi.logic.AbiUtentiLogic;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet per il salvataggio e la validazione della password dell'utente
 *
 */
public class SalvaPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = -7175912593873560505L;
	
	private AbiUtentiLogic abiUtentiLogic;

	@Autowired
	@Required
	public void setAbiUtentiService(AbiUtentiLogic abiUtentiLogic) {
		this.abiUtentiLogic = abiUtentiLogic;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userLogin = request.getParameter("userlogin");
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("password2");
		
		request.setAttribute("login", userLogin);
		if (password != null && password.length() > 0 && confermaPassword != null && confermaPassword.length() > 0) {
			/* I due campi sono entrambi valorizzati; verifico che siano >= 6 caratteri e uguali */
			if (password.length() >= 6 && confermaPassword.length() >= 6) {
				/* I due campi sono >= 6 caratteri; verifico che siano uguali */
				if (password.equals(confermaPassword)) {
					abiUtentiLogic.changePassword(userLogin, password);
					request.setAttribute("save", "true");
					
				} else {/* Le password inserite non corrispondono */
					request.setAttribute("save", "false");
				}
				
				request.getRequestDispatcher("/validate.jsp").forward(request, response);
				
			} else {
				/* Almeno un campo non soddisfa il vincolo di almeno 6 caratteri */
				request.setAttribute("minlimit", "true");
				request.getRequestDispatcher("/validate.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("minlimit", "true");
			request.getRequestDispatcher("/validate.jsp").forward(request, response);
		}
	}
}