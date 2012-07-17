package it.inera.abi.logic.statistiche;

import it.inera.abi.logic.statistiche.model.excel.HeadersBean;
import it.inera.abi.logic.statistiche.model.excel.RowBean;
import it.inera.abi.logic.statistiche.model.excel.TableBean;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.digester.Digester;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.xml.sax.SAXException;

/**
 * 
 * @author reschini
 *
 */
public class GenerateExcel {

	private Log _log = LogFactory.getLog(GenerateExcel.class);
	
	private Digester digester = null;
	private String targetDir;

	public GenerateExcel(String targetDir) {
		digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("table", TableBean.class);
		digester.addSetProperties("table");
		digester.addObjectCreate("table/HEADERS", HeadersBean.class);      
		digester.addCallMethod("table/HEADERS/column", "addHeader", 0);
		digester.addSetNext("table/HEADERS", "addHeaders");
		digester.addObjectCreate("table/ROWS/ROW", RowBean.class);      
		digester.addSetProperties("table/ROWS/ROW");
		digester.addCallMethod("table/ROWS/ROW/column", "addColumns", 0);
		digester.addSetNext("table/ROWS/ROW", "addRows"); 

		this.targetDir = targetDir;
	}

	public void generate(Hashtable<String, String> results) throws IOException, SAXException {


		for (Enumeration<String> e = results.keys(); e.hasMoreElements();) {

			String key = e.nextElement().toString();
			String risultato = results.get(key);

			StringReader sr = new StringReader(risultato);


			TableBean tavola = (TableBean) digester.parse(sr);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(tavola.getName());

			// Create a row and put some cells in it. Rows are 0 based.
			HSSFRow row = null;

			HSSFCellStyle cellStyle = wb.createCellStyle();
			//cellStyle.setAlignment(HSSFCellStyle.ALIGN_FILL);

			// Print header
			Vector<HeadersBean> h = tavola.getHeaders();
			Vector<RowBean> r = tavola.getRows();
			for (int i = 0; i < h.size(); i++) {
				HeadersBean hb = (HeadersBean) h.get(i);
				Vector<String> v = hb.getHeaders();
				row = sheet.createRow((short)i);
				for (int j = 0, k=0; j < v.size(); j++, k++) {

					if (j==0 && i>0) {
						row.createCell((short) k).setCellValue("");
						k++;
					}
					row.createCell((short)k).setCellValue(v.get(j).toString().toUpperCase());

				}	    
			}

			// Print value
			for (int i = 0; i < r.size(); i++) {
				RowBean rb = (RowBean) r.get(i);
				Vector<String> v = rb.getColumns();
				row = sheet.createRow((short)i + h.size());
				for (int j = 0; j < v.size(); j++) {
					HSSFCell cell = row.createCell((short) j);
					cell.setCellStyle(cellStyle);
					//cell.setCellValue(v.get(j).toString());
					String tmp = v.get(j).toString();
					if(tmp.indexOf(".")!=-1)
						tmp = tmp.replaceAll("\\.", "");
					//tmp = tmp.trim();
					_log.debug("TMP = " + tmp + "    v.get(j).toString() = " + v.get(j).toString());
					try{
						//Creo le celle come Interi
						int n = Integer.parseInt(tmp);
						cell.setCellValue(n);
						_log.debug("Sono entrato in INT...");
					} catch(NumberFormatException ee){
						try{
							//		    				Creo le celle come Float
							float n = Float.parseFloat(tmp);
							cell.setCellValue(n);
							_log.debug("Sono entrato in FLOAT...");
						}
						catch(NumberFormatException eee){
							//		    				Creo le celle come Stringhe
							cell.setCellValue(tmp);
							_log.debug("Sono entrato in STRING...");
						}
					}
				}
			}  	      
			// Write the output to a file
			String filename = FilenameUtils.concat(targetDir, tavola.getSummary() + ".xls");
			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();

		}
	}
}
