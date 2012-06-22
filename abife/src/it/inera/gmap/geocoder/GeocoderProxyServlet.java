package it.inera.gmap.geocoder;

import it.inera.gmap.geocoder.google.GeoAddressStandardizer;
import it.inera.gmap.geocoder.google.GeoException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.yahooapis.MapsService.geocode.YGWebService;
import com.yahooapis.MapsService.geocode.YahooGeocodeSearchCriteria;
import com.yahooapis.MapsService.geocode.GeocodeResponse.ResultSet;
import com.yahooapis.MapsService.geocode.GeocodeResponse.ResultType;

public class GeocoderProxyServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String geoService = req.getParameter("geoservice");
		if ("yahoo".equalsIgnoreCase(geoService)) {
			doYahooService(req, resp);
		} else {
			doGoogleService(req, resp);
		}
	}
	
	
	protected void doYahooService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String city = req.getParameter("comune");
		String state = req.getParameter("stato");
		String street = req.getParameter("indirizzo");
		
		YahooGeocodeSearchCriteria searchCriteria = new YahooGeocodeSearchCriteria();
		searchCriteria.setAppid("abi");
		if (StringUtils.isNotBlank(city)) {
			searchCriteria.setCity(city.replace('\'', ' '));
		}
		if (StringUtils.isNotBlank(state)) {
			searchCriteria.setState(state.replace('\'', ' '));
		}
		if (StringUtils.isNotBlank(street)) {
			searchCriteria.setStreet(street.replace('\'', ' '));
		}
		
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setHeader("Pragma", "no-cache");
		resp.addHeader("Cache-Control", "must-revalidate");
		resp.addHeader("Cache-Control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");
		resp.setDateHeader("Expires", 0); 
		resp.setHeader("Pragma", "no-cache");
		
		ResultSet resultSet = null;
		try {
			resultSet = YGWebService.search(searchCriteria);
		} catch (Exception e) {
			resp.getWriter().write("<error>");
			resp.getWriter().write(e.getMessage());
			resp.getWriter().write("</error>");
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}
		if (resultSet == null || 
				resultSet.getResult() == null || 
				resultSet.getResult().size() == 0 || 
				resultSet.getResult().get(0) == null) {
			resp.getWriter().write("<ResultType />");			
			return;
		}
		resp.getWriter().write(resultSet.getResult().get(0).toString());
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	protected void doGoogleService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setHeader("Pragma", "no-cache");
		resp.addHeader("Cache-Control", "must-revalidate");
		resp.addHeader("Cache-Control", "no-cache");
		resp.addHeader("Cache-Control", "no-store");
		resp.setDateHeader("Expires", 0); 
		resp.setHeader("Pragma", "no-cache");
		
		GeoAddressStandardizer standardizer = new GeoAddressStandardizer("AAABBC", 2000);
		
		String street = req.getParameter("indirizzo");
		String cap = req.getParameter("cap");
		String frazione = req.getParameter("frazione");
		String city = req.getParameter("comune");
		String stato = req.getParameter("stato");
		
		
		// query
		StringBuffer queryBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(street)) {
			queryBuffer.append(street);
		}
		if (StringUtils.isNotBlank(cap)) {
			queryBuffer.append(" ");
			queryBuffer.append(cap);
		}
		if (StringUtils.isNotBlank(city)) {
			queryBuffer.append(" ");
			queryBuffer.append(city);
		}
		if (StringUtils.isNotBlank(frazione)) {
			queryBuffer.append(" ");
			queryBuffer.append(frazione);
		}
		if (StringUtils.isNotBlank(stato)) {
			queryBuffer.append(" ");
			queryBuffer.append(stato);
		}
		
		
		
		ResultSet resultSet = new ResultSet();
		try {
			String res = standardizer.standardize(queryBuffer.toString());
			StringTokenizer tokenizer = new StringTokenizer(res, ",");
			while (tokenizer.hasMoreTokens()) {
				String status = tokenizer.nextToken();				
				String accurancy = tokenizer.nextToken();
				String lat = tokenizer.nextToken();
				String lng = tokenizer.nextToken();
				
				ResultType resultType = new ResultType();
				resultType.setLatitude(Double.parseDouble(lat));
				resultType.setLongitude(Double.parseDouble(lng));
				resultType.setPrecision(accurancy);
				resultSet.addResult(resultType);
				break;
			}
			
			
		} catch (GeoException e) {
			resp.getWriter().write("<error>");
			resp.getWriter().write(e.getMessage());
			resp.getWriter().write("</error>");
			resp.getWriter().flush();
			resp.getWriter().close();
		}

		
		
		
		
		if (resultSet == null || 
				resultSet.getResult() == null || 
				resultSet.getResult().size() == 0 || 
				resultSet.getResult().get(0) == null) {
			resp.getWriter().write("<ResultType />");			
			return;
		}
		resp.getWriter().write(resultSet.getResult().get(0).toString());
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
