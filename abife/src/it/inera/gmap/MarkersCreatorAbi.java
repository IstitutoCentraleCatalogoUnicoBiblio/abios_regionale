package it.inera.gmap;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import it.inera.gmap.markersgenereator.MarkersCreator;
import it.inera.gmap.markersgenereator.castor.Marker;
import it.inera.gmap.markersgenereator.castor.Markers;
import it.inera.gmap.markersgenereator.castor.Params;
import it.inera.solr.solr.ServerInstanceManager;

public class MarkersCreatorAbi extends MarkersCreator {
	
	private Log log = null;
	
	public Markers getMarkers(String[] parameters) {
		log = LogFactory.getLog(MarkersCreatorAbi.class);
		log.info("Generazione markers");
		Markers markers = new Markers();
		
		
		log.debug("Parametri " + StringUtils.join(parameters, ","));
		
		String id_bib = parameters[0];
		String id_com = parameters[1];
		
		if (StringUtils.isNotBlank(id_bib)) {
			return getBiblioById(id_bib, markers);
		} 
		
		if (StringUtils.isNotBlank(id_com)) {
			return getBiblioByIdComune(id_com, markers);
		}
		
		return new Markers();
		
	}
	
	protected Markers getBiblioById(String id_bib, Markers markers) {
	    SolrQuery query = new SolrQuery();
	    query.setQuery("id_biblioteca:".concat(id_bib));
	    SolrServer server = null;
		try {
			server = ServerInstanceManager.getServer();
			QueryResponse rsp = server.query(query);
			SolrDocumentList docs = rsp.getResults();
			SolrDocument biblio = docs.get(0); // ho una sola biblioteca....
			
			setParams(markers, biblio);
			
			Marker marker = new Marker();
			marker.setLat(getStringFromObj(biblio.getFieldValue("latitudine")));
			marker.setLng(getStringFromObj(biblio.getFieldValue("longitudine")));
			marker.setCap(getStringFromObj(biblio.getFieldValue("cap")));
			marker.setDenominazione(getStringFromObj(biblio.getFieldValue("denominazione_ufficiale")));
			marker.setId_bib(getStringFromObj(biblio.getFieldValue("id_biblioteca")));
			marker.setIndirizzo(getStringFromObj(biblio.getFieldValue("indirizzo")));
			marker.setIsil_nr(getStringFromObj(biblio.getFieldValue("isil_numero")));
			marker.setIsil_pr(getStringFromObj(biblio.getFieldValue("isil_provincia")));
			marker.setStato(getStringFromObj(biblio.getFieldValue("isil_stato")));
			marker.setComune(getStringFromObj(biblio.getFieldValue("comune")));
			marker.setRegione(getStringFromObj(biblio.getFieldValue("regione")));
			marker.setProvincia(getStringFromObj(biblio.getFieldValue("provincia")));
			markers.addMarker(marker);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return markers;
	}

	protected Markers getBiblioByIdComune(String id_comune, Markers markers) {
		
		SolrQuery query = new SolrQuery();
	    query.setQuery("id_comune:".concat(id_comune).concat(" AND -longitudine:0.0 AND -latitudine:0.0"));
	    query.setRows(Integer.MAX_VALUE);
	    SolrServer server = null;
		try {
			server = ServerInstanceManager.getServer();
			QueryResponse rsp = server.query(query);
			SolrDocumentList docList = rsp.getResults();
			
			Iterator<SolrDocument> iter = docList.iterator();
			while (iter.hasNext()) {
				SolrDocument biblio = (SolrDocument) iter.next();
				
				setParams(markers, biblio);
				
				Marker marker = new Marker();
				marker.setLat(getStringFromObj(biblio.getFieldValue("latitudine")));
				marker.setLng(getStringFromObj(biblio.getFieldValue("longitudine")));
				marker.setCap(getStringFromObj(biblio.getFieldValue("cap")));
				marker.setDenominazione(getStringFromObj(biblio.getFieldValue("denominazione_ufficiale")));
				marker.setId_bib(getStringFromObj(biblio.getFieldValue("id_biblioteca")));
				marker.setIndirizzo(getStringFromObj(biblio.getFieldValue("indirizzo")));
				marker.setIsil_nr(getStringFromObj(biblio.getFieldValue("isil_numero")));
				marker.setIsil_pr(getStringFromObj(biblio.getFieldValue("isil_provincia")));
				marker.setStato(getStringFromObj(biblio.getFieldValue("isil_stato")));
				marker.setComune(getStringFromObj(biblio.getFieldValue("comune")));
				marker.setRegione(getStringFromObj(biblio.getFieldValue("regione")));
				marker.setProvincia(getStringFromObj(biblio.getFieldValue("provincia")));
				
				marker.setId_reg(getStringFromObj(biblio.getFieldValue("id_regione")));
				marker.setId_pro(getStringFromObj(biblio.getFieldValue("id_provincia")));
				marker.setId_com(getStringFromObj(biblio.getFieldValue("id_comune")));
				markers.addMarker(marker);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DBHomeBiblio homeBiblio = new DBHomeBiblio();
//		
//		try {
//			homeBiblio.init(m_startContext);
//			
//			SelectResult srLocalita = homeBiblio.getAllDatiLocalitaByIdComune(id_com);
//			setParams(markers, homeBiblio, srLocalita);
//			
//			String query = "SELECT " + "b." + Biblioteca.ID_BIB + ", " + "b."
//				+ Biblioteca.ISIL_ST + ", " + "b." + Biblioteca.ISIL_NR + ", "
//				+ "b." + Biblioteca.ISIL_PR + ", " + "b."
//				+ Biblioteca.DENOMINAZIONE + ", " + "b." + Biblioteca.INDIRIZZO
//				+ ", b." + Biblioteca.CAP + ", " 
//				+ "c." + Comune.DESCRIZIONE + " AS COMUNE" + ", c.ID_COM AS ID_COM," 
//				+ "r." + Regione.DESCRIZIONE + " AS REGIONE" + ", r.ID_REG AS ID_REG,"
//				+ "p." + Provincia.SIGLA + " AS PROVINCIA" + ", p.ID_PRO AS ID_PRO,"
//				+ " GB.LATITUDINE, GB.LONGITUDINE " 
//				+ " FROM " + TableInformix.BIBLIOTECA
//				+ " B , " + TableInformix.GEOCODIFICA + " as GB, COMUNE C, REGIONE R, PROVINCIA P, LOCALITA L" + " WHERE b."
//				+ Biblioteca.ID_LOC + " = l." + Localita.ID_LOC + " AND l."
//				+ Localita.ID_COM + " = c." + Comune.ID_COM + " AND c." + Comune.ID_COM + " = " + id_com
//				+ " AND l.ID_REG = r.ID_REG  AND l.ID_PRO = p.ID_PRO AND GB.ID_ENTITY = b." + Biblioteca.ID_BIB
//				+ " AND b." + Biblioteca.STATO + " != '" + StatoBiblio.CANCELLATA.toUpperCase() + "'"
//				+ " ORDER BY ID_BIB"; // r.eschini
//			query = query.toUpperCase();
//			
//			SelectResult sr = homeBiblio.getList(query, 0, 0);
//			
//			markers.setTotal(sr.getNumRows());
//			markers.setFrom(0);
//			markers.setNentries(0);
//			int size = sr.getNumRows();
//			for (int i = 0; i < size; i++) {
//				
//				Marker marker = new Marker();
//				
//				marker.setLat((String)sr.get(i, Geocodifica.LATITUDINE));
//				marker.setLng((String)sr.get(i, Geocodifica.LONGITUDINE));
//				marker.setCap((String)sr.get(i, Biblioteca.CAP));
//				marker.setDenominazione((String)sr.get(i, Biblioteca.DENOMINAZIONE));
//				marker.setId_bib((String)sr.get(i, Biblioteca.ID_BIB));
//				marker.setIndirizzo((String)sr.get(i, Biblioteca.INDIRIZZO));
//				marker.setIsil_nr((String)sr.get(i, Biblioteca.ISIL_NR));
//				marker.setIsil_pr((String)sr.get(i, Biblioteca.ISIL_PR));
//				marker.setRegione(sr.getValue("REGIONE"));
//				marker.setId_reg(sr.getValue("ID_REG"));
//				marker.setComune(sr.getValue("COMUNE"));
//				marker.setId_com(sr.getValue("ID_COM"));
//				marker.setProvincia(sr.getValue("PROVINCIA"));
//				marker.setId_pro(sr.getValue("ID_PRO"));
//				markers.addMarker(marker);
//			}
//			
//			final String ROOT = "[DBHomeBiblio::getLista()] ";
//			
//		} catch (OAS_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return markers;
	}

	private void setParams(Markers markers, SolrDocument biblio) {
		
		
		Params LABEL_REG = new Params();
		LABEL_REG.setName("LABEL_REG");
		LABEL_REG.setValue(getStringFromObj(biblio.getFieldValue("regione")).toUpperCase());
		markers.addParams(LABEL_REG);
		Params ID_REG = new Params();
		ID_REG.setName("ID_REG");
		ID_REG.setValue(getStringFromObj(biblio.getFieldValue("id_regione")).toUpperCase());
		markers.addParams(ID_REG);
		Params LABEL_PRO = new Params();
		LABEL_PRO.setName("LABEL_PRO");
		LABEL_PRO.setValue(getStringFromObj(biblio.getFieldValue("provincia")).toUpperCase());
		markers.addParams(LABEL_PRO);
		Params ID_PRO = new Params();
		ID_PRO.setName("ID_PRO");
		ID_PRO.setValue(getStringFromObj(biblio.getFieldValue("id_provincia")).toUpperCase());
		markers.addParams(ID_PRO);
		Params LABEL_COM = new Params();
		LABEL_COM.setName("LABEL_COM");
		LABEL_COM.setValue(getStringFromObj(biblio.getFieldValue("comune")).toUpperCase());
		markers.addParams(LABEL_COM);
		Params ID_COM = new Params();
		ID_COM.setName("ID_COM");
		ID_COM.setValue(getStringFromObj(biblio.getFieldValue("id_comune")).toUpperCase());
		markers.addParams(ID_COM);
	}
	
	
	public String getStringFromObj(Object obj) {
		if (obj != null & obj instanceof String) return obj.toString();
		if (obj != null & obj instanceof Double) return ((Double) obj).toString();
		if (obj != null & obj instanceof Integer) return ((Integer) obj).toString();
		return "";
	}
}
