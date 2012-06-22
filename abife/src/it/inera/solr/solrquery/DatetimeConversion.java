package it.inera.solr.solrquery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeConversion {
	
	public static final String DATETIME_PATTERN = "ddMMyyyyHHmmss"; // TODO: mettere in configurazione 
	private static final String SOLR_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
		
	/**
	 *	Trasforma DDMMYYYY in YYYY-MM-01Thh:mm:ssZ 
	 *	2008-10-01T04:00:00Z 
	 * @throws Exception 
	 * 	
	 */
	public static String getSolrDatetime(String input)   {
		DateFormat formatterInput = new SimpleDateFormat(DATETIME_PATTERN);
		Date date = null;
		try {
			date = formatterInput.parse(input);
			DateFormat formatterOutput = new SimpleDateFormat(SOLR_DATETIME_PATTERN);
			String output = formatterOutput.format(date);
			return output;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static String getSolrDatetime(Date input) {
		DateFormat formatterOutput = new SimpleDateFormat(SOLR_DATETIME_PATTERN);
		String output = formatterOutput.format(input);
		return output;
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(getSolrDatetime("01122008"));
	}
	
	
}

