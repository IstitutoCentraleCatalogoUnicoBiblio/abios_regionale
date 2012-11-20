package it.inera.abi;

import it.inera.abi.commons.Utility;
import it.inera.abi.logic.AbiUtentiLogic;
import it.inera.abi.persistence.Utenti;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet per la rigenerazione della password
 *
 */
public class RigeneraPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = -8331625851378932893L;

	private @Value("${email.host.address}") String emailHostAddress;
	private @Value("${email.host.username}") String emailHostUsername;
	private @Value("${email.host.password}") String emailHostPassword;
	private @Value("${email.bounce.address}") String emailBounceAddress;
	private @Value("${email.rigenera.subject}") String emailRigeneraSbj;
	private @Value("${email.rigenera.address}") String emailRigeneraAddress;
	private @Value("${email.rigenera.name}") String emailRigeneraName;
	private @Value("${email.rigenera.msg}") String emailRigeneraMsg;
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");

		Utenti user = abiUtentiLogic.findUtenteByValidateCode(code);
		
		if (user != null) {
			request.setAttribute("login", user.getLogin());
			request.setAttribute("valid", "true");			
			
		} else {
			/* Non è stato trovato alcun utente cui è assegnato il codice 'code' */
			request.setAttribute("valid", "false");
		}
		
		request.getRequestDispatcher("/validate.jsp").forward(request, response);	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String username = request.getParameter("username");
		if (username == null || (username != null && username.length() == 0)) {
			/* Non è stato inserito alcun username */
			request.setAttribute("noInsert", "true");
			request.getRequestDispatcher("/rigenera.jsp").forward(request, response);

		} else { 
			/* Verifica che esista un utente con username inserito */
			boolean found = abiUtentiLogic.findUtenteByUsername(username);
			
			if (found) {/* esiste sul DB un utente 'username' */
				String validateCode = RandomStringUtils.random(20, true, true);
				
				/* Aggiorno il campo 'codice_validazione' */
				Utenti user = abiUtentiLogic.saveCodiceValidazione(username, validateCode);
				
				String message = emailRigeneraMsg;
				message = StringUtils.replace(message, "$$$username$$$", username);
				message = StringUtils.replace(message, "$$$validationcode$$$", validateCode);
				
				try {
					Utility.sendEmail(emailRigeneraSbj, message, user.getEmail(), username, emailRigeneraAddress, emailRigeneraName, emailHostAddress, emailHostUsername , emailHostPassword, emailBounceAddress);
					request.setAttribute("noInsert", "false");
					request.getRequestDispatcher("/rigenera.jsp").forward(request, response);
					
				} catch (EmailException e) {
					System.out.println("Errore durante l'invio dell'email...");
				}
				
			} else {
				request.setAttribute("userNotFound", "true");
				request.getRequestDispatcher("/rigenera.jsp").forward(request, response);
			}
			
		}
	}

}