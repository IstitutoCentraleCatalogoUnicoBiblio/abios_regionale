package it.inera.gmap.markersgenereator;

import it.inera.gmap.GmapProperties;
import it.inera.gmap.markersgenereator.castor.Markers;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

/**
 * Serlvet che genera l'XML per la creazione dei markers sula mappa
 * @author r.eschini
 */
public class MarkersGeneratorServlet extends HttpServlet {

	private Log log = LogFactory.getLog(MarkersGeneratorServlet.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.debug("Richiesta");
		
		// lettura della lista parametri		
		String listQueryParams = GmapProperties.getProperty(GmapProperties.QUERY_PARAMS_PROPS_KEY);
		String[] paramsName = listQueryParams.split(",");		
		String[] parameters = new String[paramsName.length];
		for (int i = 0; i < paramsName.length; i++) {
			parameters[i] = req.getParameter(paramsName[i]);
		}
		
		MarkersCreator markersCreator = MarkersCreatorFactory.getMarkersCreator();
		Markers markers = markersCreator.getMarkers(parameters);
		
		try {
			resp.setContentType("text/plain; charset=UTF-8");
			resp.setHeader("Pragma", "no-cache");
			resp.addHeader("Cache-Control", "must-revalidate");
			resp.addHeader("Cache-Control", "no-cache");
			resp.addHeader("Cache-Control", "no-store");
			resp.setDateHeader("Expires", 0); 
			resp.setHeader("Pragma", "no-cache");
			markers.marshal(resp.getWriter());
			resp.getWriter().flush();
			resp.getWriter().close();
			
		} catch (MarshalException e) {
			throw new ServletException(e);
		} catch (ValidationException e) {
			throw new ServletException(e);
		}
	}

	
	
	
}
