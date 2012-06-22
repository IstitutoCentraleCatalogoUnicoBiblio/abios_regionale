package it.inera.abife.jobs;

import it.inera.solr.solr.ServerInstanceManager;
import it.inera.solr.solr.SolrCmd;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * @author r.eschini
 *
 */
public class CountBiblioJob implements Job {

	private static Log _log = LogFactory.getLog(CountBiblioJob.class);

	public CountBiblioJob() {}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		_log.info("Start html count record generation job......");
		
		String htmltemplate = context.getJobDetail().getJobDataMap().getString("htmltemplate");
		String htmlpath = context.getJobDetail().getJobDataMap().getString("htmlpath");
		
		String countQuery1 = "id_biblioteca:*";
		String countQuery2 = "-tipologia_funzionale:NON\\ SPECIFICATA AND -tipologia_amministrativa:NON\\ SPECIFICATA AND -stato_catalogazione:*";
		
		
		try {
			_log.info("Query Solr......");
			
			SolrServer server = ServerInstanceManager.getServer();
			
			SolrQuery query = new SolrQuery();
		    query.setRows(0);
		    
		    query.setQuery(countQuery1);
			QueryResponse rsp = server.query(query);
			long count1  = rsp.getResults().getNumFound();
			
			query.setQuery(countQuery2);
			rsp = server.query(query);
			long count2  = rsp.getResults().getNumFound();
			
			
			String htmlCount = FileUtils.readFileToString(new File(htmltemplate));
			htmlCount = StringUtils.replaceOnce(htmlCount, "$numBiblio", String.valueOf(count1));
			htmlCount = StringUtils.replaceOnce(htmlCount, "$numCensite", String.valueOf(count2));
			
			FileUtils.writeStringToFile(new File(htmlpath), htmlCount);
			
			_log.info("End html count record generation job......");
		} catch (Exception e) {
			_log.fatal(e);
		}
	}	

}