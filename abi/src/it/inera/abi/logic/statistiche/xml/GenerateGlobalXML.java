package it.inera.abi.logic.statistiche.xml;

import it.inera.abi.commons.XslTransformer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ranjan.xml.RanjanXMLResultSet;
import org.ranjan.xml.RanjanXMLResultSetException;

/**
 * Classe per la generazione dell'xml delle statistiche
 * 
 */
public class GenerateGlobalXML {

	private Log _log = LogFactory.getLog(GenerateGlobalXML.class);
	
	private DataSource dataSource;

	private	int range_geo_query = 100;
	private int num_geo_query = 160;

	private String statisticheSqlProperties = null;
	private String statSourceDir = null;
	private String statTargetDir = null;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setStatisticheSqlProperties(String statisticheSqlProperties) {
		this.statisticheSqlProperties = statisticheSqlProperties;
	}

	public void setStatSourceDir(String statSourceDir) {
		this.statSourceDir = statSourceDir;
	}

	public void setStatTargetDir(String statTargetDir) {
		this.statTargetDir = statTargetDir;
	}

	public Hashtable<String, Vector<String>> generateAllXML() throws IOException, ClassNotFoundException, SQLException, RanjanXMLResultSetException {

		InputStream is = new FileInputStream(statisticheSqlProperties);
		Properties queryTavole = new Properties();
		queryTavole.load(is);


		Enumeration<?> query = queryTavole.keys();
		Vector<String> tempTavole = new Vector<String>();
		while (query.hasMoreElements()) {
			String queryTavolaKey = query.nextElement().toString();
			tempTavole.add(queryTavolaKey);
		}
		String[] temp = new String[tempTavole.size()];
		temp = tempTavole.toArray(temp);

		Arrays.sort(temp, new OrdinaChiavi());
		// ho l'array ordinato delle chiavi

		Hashtable<String, Vector<String>> global = new Hashtable<String, Vector<String>>();
		int i = 0;

		while (i < temp.length) {
			// creo i raggruppamenti per le tavole
			StringTokenizer st = new StringTokenizer(temp[i], "_");
			String tavola = st.nextToken();
			StringTokenizer nextSt = new StringTokenizer(temp[i + 1], "_");
			String tavolaNext = nextSt.nextToken();
			String queryGeo = new String();
			if ((!tavola.equalsIgnoreCase(tavolaNext)) && (tavola.indexOf("GEOREFERENZIAZIONE")==-1)) { 
				//query appartenenti a due tavole diverse
				Vector<String> tempQuery = new Vector<String>();
				String res = getXml(queryTavole.getProperty(temp[i]));
				_log.debug(res);
				tempQuery.add(res.replaceAll(",", ""));

				global.put(tavola, tempQuery);
				i++;
			} 
			else {
				if((tavola.equalsIgnoreCase(tavolaNext))&&(tavola.indexOf("GEOREFERENZIAZIONE")==-1)){
					Vector<String> tempQuery = new Vector<String>();
					String filename = FilenameUtils.concat(statTargetDir, "prova_" + i + ".txt");
					File f1 = new File(filename);
					boolean primavolta = true;
					while (tavola.equalsIgnoreCase(tavolaNext)) {//query per la stessa tavola
						scriviFileTemp(getXml(queryTavole.getProperty(temp[i])), f1, new Boolean(primavolta));
						primavolta = false;
						tempQuery.add(getXml(queryTavole.getProperty(temp[i])));
						if (i < temp.length - 1) {
							nextSt = new StringTokenizer(temp[i + 1], "_");
							tavolaNext = nextSt.nextToken();
						} else {
							tavolaNext = "";
						}
						i++;
					}
					scriviFileTemp("</ROWSETS>", f1, null);
					global.put(tavola, tempQuery);
				} else {
					if (tavola.indexOf("GEOREFERENZIAZIONE")!=-1) {
						queryGeo = queryGeo + queryTavole.getProperty(temp[i]) + "||";
						getXmlFile(queryGeo);
						i++;
					}
				}
			}
		}
		return global;
	}

	public String getXml(String query) throws ClassNotFoundException, SQLException, RanjanXMLResultSetException  {
		Connection conn = dataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(query);
		RanjanXMLResultSet test = new RanjanXMLResultSet(rs);
		test.setLowerCase();
		String result = test.getXML();
		conn.close();
		return result;
	}

	public void getXmlFile(String q) throws ClassNotFoundException, SQLException {
		_log.debug("************Query--->  " + q);
		Vector<String> v = new Vector<String>();
		int inizio = 1;
		int fine = range_geo_query;
		for (int i = 0;i<num_geo_query;i++) {
			StringTokenizer st = new StringTokenizer(q, "||");
			while (st.hasMoreElements()) {
				String tmp = st.nextToken();
				String tmp1 = tmp.replaceAll("'X'", Integer.toString(inizio));
				String tmp2 = tmp1.replaceAll("'Y'", Integer.toString(fine)) + ";";
				v.add(tmp2);
			}
			inizio += range_geo_query;
			fine += range_geo_query;
		}
		_log.debug("Query per la georeferenziazione: " + v.toString());
		for (int i=0; i<v.size(); i++) {
			try {
				//SCRITTURA DEL FILE XML
				String filename = FilenameUtils.concat(statTargetDir, "georeferenziazione_" + i + ".xml");
				File f = new File(filename);
				_log.debug("************Sto scrivendo il file: " + f.getName());
				FileWriter fileout = null;
				String result = "";
				fileout = new FileWriter(f, false);

				result = getGeoResults((String)v.get(i), false);
				result = result.concat("\n</RESULTSET>");
				String fResult = result;
				fileout.write((String)result);		
				fileout.close();
				_log.debug("************Fine scrittura del file: " + f.getName());

				//SCRITTURA FILE IN FORMATO GEOREFERENZIABILE	
				String xsltFile = "GEOREFERENZIAZIONE01.xsl";
				_log.debug("************Sto eseguendo la trasformazione in formato georeferenziabile... ");
				_log.debug("sulla stringa -->  " + fResult);
				try {
					fResult = fResult.replaceAll("&", "&amp;");
					String finalFormat = XslTransformer.transform(fResult, statSourceDir + "/" + xsltFile);
					String filenameGeoref = FilenameUtils.concat(statTargetDir, "georeferenziazione_" + i + ".txt");
					File f1 = new File(filenameGeoref);
					_log.debug("************Sto scrivendo il file: " + f1.getName() + "\n");
					FileWriter fileout1 = null;
					fileout1 = new FileWriter(f1, false);
					fileout1.write((String)finalFormat + "\n");		
					fileout1.close();
				} catch(Exception e) {
					_log.debug("************ECCEZIONE: " + e);
				}

			} catch(Exception e){
				_log.debug("************ECCEZIONE: " + e);
			}
		}
		createGeoFile();
	}

	private void scriviFileTemp(String s, File f1, Boolean primavolta){
		try {
			if (s.indexOf("&")!=-1) {
				s = s.replaceAll("&", "&amp;");
			}
			int i = s.indexOf(">");
			if (primavolta != null) {
				if (!(primavolta.booleanValue())) {
					s = s.substring(i+1);
				} else {
					s = s.substring(0, i+1) + "<ROWSETS>" + s.substring(i+1); 
				}
			}
			_log.debug("************Sto scrivendo il file: " + f1.getName() + "\n");
			FileWriter fileout1 = null;
			fileout1 = new FileWriter(f1, true);
			fileout1.write((String)s + "\n");		
			fileout1.close();
		} catch (Exception e) {
			_log.debug("************ECCEZIONE: " + e);
		}
	}

	//Eseguo e restituisco un XML con i risultati della query
	public String getGeoResults(String q, boolean daSostituire) throws ClassNotFoundException, SQLException, RanjanXMLResultSetException  {

		Connection conn = dataSource.getConnection();

		ResultSet rs1 = conn.createStatement().executeQuery(q);
		RanjanXMLResultSet test1 = new RanjanXMLResultSet(rs1);
		test1.setLowerCase();
		String result = test1.getXML();
		int da = result.indexOf(">");
		if (daSostituire) {
			result = result.substring(da+1);
		} else {
			result = result.substring(0, da+1) + "\n<RESULTSET>\n" + result.substring(da+1); 
		}
		result = result.replaceAll("\"", "\\\"");
		conn.close();
		return result;
	}

	public void createGeoFile() {
		File dir = new File(statTargetDir);
		String nameF = "geo_" + currentDate() + ".txt";
		String filenameF = FilenameUtils.concat(statTargetDir, nameF);
		File toWrite = new File(filenameF);
		FileWriter fileout = null;
		String[] children = dir.list();
		if (children == null) {

		} else  {
			try {
				fileout = new FileWriter(toWrite, false);
				for (int i=0; i<children.length; i++) {
					String filename = children[i];
					if (filename.indexOf("georeferenziazione_")!=-1) {
						if (filename.indexOf(".txt")!=-1) {
							String filenameToRead = FilenameUtils.concat(statTargetDir, filename);
							File toRead = new File(filenameToRead);
							_log.debug("******Sto concatenando il file: " + filename);
							BufferedReader filebuf =  
								new BufferedReader(new java.io.FileReader(toRead)); 
							String nextStr;
							nextStr = filebuf.readLine(); // legge una riga del file 
							while (nextStr != null) { 
								nextStr = nextStr.trim();
								if(nextStr.length()!=0){
									fileout.write(nextStr+ "\n");
								}
								nextStr = filebuf.readLine(); // legge la prossima riga 
							} 	 
							filebuf.close(); // chiude il file   
							toRead.delete();
							String filenameToDelete = FilenameUtils.concat(statTargetDir, filename.replaceAll("txt", "xml"));
							File toDelete = new File(filenameToDelete);
							toDelete.delete();

						}
					}
				}
				fileout.close();

			}
			catch(Exception e){
				_log.debug("******Eccezione :" + e);
			}
		}
		_log.debug("******HO SCRITTO IL FILE: " + statTargetDir + nameF);
	}

	//YYYY+MM+DD
	public static String currentDate() {
		GregorianCalendar cal = new GregorianCalendar(Locale.ITALY);

		String anno    = Integer.toString(cal.get(Calendar.YEAR));
		String mese    = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String giorno  = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String ora     = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		String minuto  = Integer.toString(cal.get(Calendar.MINUTE));

		if (mese.length()   == 1) mese   = "0" + mese;
		if (giorno.length() == 1) giorno = "0" + giorno;
		if (ora.length()    == 1) ora    = "0" + ora;
		if (minuto.length() == 1) minuto = "0" + minuto;

		return anno + mese + giorno;
	}

}

class OrdinaChiavi implements Comparator<String> {
	public int compare(String o1, String o2) {
		String t1 = o1.toString();
		String t2 = o2.toString();
		return t1.compareTo(t2);
	}
}
