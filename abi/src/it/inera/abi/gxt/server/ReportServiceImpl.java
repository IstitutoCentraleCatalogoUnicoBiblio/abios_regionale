package it.inera.abi.gxt.server;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import it.inera.abi.gxt.client.services.ReportService;
import it.inera.abi.logic.stampe.BiblioStampe;
import it.inera.abi.logic.stampe.ReportLogic;

/**
 * Implementazione dei servizi relativi ai report (lato server)
 * 
 */
public class ReportServiceImpl extends AutoinjectingRemoteServiceServlet implements ReportService {
	
	private static final long serialVersionUID = 5148926030802732305L;
	protected @Autowired ReportLogic reportLogic;

	@Override
	public void retrieveBiblioReport(List<Integer> idbibs, HashMap<String, Object> labels) {
		
		Vector<BiblioStampe> bibvector = reportLogic.retrieveBiblioReport(idbibs);
		
		if (bibvector != null) {		
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute("BIBLIOTECHE", bibvector);
			session.setAttribute("LABELS", labels);
		}
		
	}

}
