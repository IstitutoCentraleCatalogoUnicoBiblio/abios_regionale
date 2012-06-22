package it.inera.solr.solrquery;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;

import it.inera.solr.configuration.Constants;
import it.inera.solr.solr.ServerInstanceManager;
import it.inera.solr.solr.SolrCmd;
import it.inera.solr.solr.SolrServerException;

/**
 * Classe per la costruzione della query di ricerca di Solr sulla base dei parametri passati 
 * @author Maria Grazia toro m.g.toro@inera.it
 *
 */
public class Query {


	private Log _log = LogFactory.getLog(Query.class);
	private Log _logSearchString = LogFactory.getLog("LOGSEARCHSTRING"); // log delle query solr sul frontend

	private String core = null;

	private int start;
	private int row;
	private String sortField;
	private String fields;
	private int facetLimit; 		
	private String facetField;
	protected String facetSort;
	protected int facetMinCount; 

	protected String extendedQuery; // estensione in AND alla query creata

	private String hlField;

	private Map<String, String> fieldsQueryMap;
	private List<String> fieldsNoEscapeQueryList;
	private List<String> valuesNoEscapeQueryList;
	private Map<String, String> fieldsQueryOpMap; 
	private Map<String, String> filtersQueryMap;
	protected Map<String, String> fieldsCaseQueryMap;

	protected List<String> rangesQueryList;

	private String queryString;

	public Query() {
		start = 0;
		row = 0;
		fields = "*";
		facetLimit = 0;
	}

	/**
	 * 
	 * @param fieldsMap
	 * @return
	 * @throws SolrQueryException
	 */
	public void buildQuery() {

		// costruzione query solr
		StringBuffer solrQuery = new StringBuffer();

		// fields
		Set<String> setFieldsMap = fieldsQueryMap.keySet();
		for (Iterator<String> iterator = setFieldsMap.iterator(); iterator.hasNext();) {

			String field = iterator.next();
			String value = fieldsQueryMap.get(field);

			// controllo se è stato settata un operazione per il campo
			String op = fieldsQueryOpMap.get(field);

			if (StringUtils.isNotBlank(value)) {

				// controllo il case
				if (fieldsCaseQueryMap != null) {
					String fieldCase = fieldsCaseQueryMap.get(field);
					if (StringUtils.isNotBlank(fieldCase)) {
						if (Constants.UPPERCASE.equalsIgnoreCase(fieldCase)) {
							value = value.toUpperCase();
						} else if (Constants.LOWERCASE.equalsIgnoreCase(fieldCase)) {
							value = value.toLowerCase();
						}
					}
				}

				if (solrQuery.length() > 0)
					solrQuery.append(Constants.QUERY_AND); // di default metto in AND i campi


				if (field.equalsIgnoreCase(Constants.SOLR_QUERY)) { // caso in cui passo direttamente la solrquery
					solrQuery.append(value);			
				} else { // caso in cui il campo va in query
					StringBuffer sb = new StringBuffer();
					// calcolo in base all'operatore se specificato la query relativa al campo
					if (Constants.OP_AND.equals(op) || Constants.OP_OR.equals(op)) {
						String[] terms = value.split("\\s+|\\'+|\\-+|\\.+|\\?+|\\/+");
						sb.append("(");
						for (int i = 0; i < terms.length; i++) {
							if (StringUtils.isNotBlank(terms[i].trim())) {
								sb.append(field);
								sb.append(":");
								if (fieldsNoEscapeQueryList == null || !fieldsNoEscapeQueryList.contains(field)) {
									terms[i] = doEscaping(terms[i], valuesNoEscapeQueryList);
								}

								sb.append(terms[i]);
								sb.append(" ");
								sb.append(op);
								sb.append(" ");	
							}
						}
						sb.delete(sb.length() - op.length() - 2, sb.length());
						sb.append(")");
					} else if (Constants.OP_ONLY.equals(op)) {
						if (sb.length() > 0) sb.append(" ");
						sb.append(field);
						if ("descrizione_ufficiale".equalsIgnoreCase(field)) {
							sb.append("_esatta");
						}
						sb.append(":");
						sb.append("\"");
						sb.append(value);
						sb.append("\"");
					} else {
						if (sb.length() > 0) sb.append(" ");
						sb.append(field);
						sb.append(":");
						if (fieldsNoEscapeQueryList == null || !fieldsNoEscapeQueryList.contains(field)) {
							value = doEscaping(value, valuesNoEscapeQueryList);
						}

						if ("codice_dewey".equalsIgnoreCase(field)) {
							sb.append(value).append("*");
						}
						else {
							sb.append(value);
						}
					}

					solrQuery.append(sb.toString());
				}
			}
		}

		// ranges
		if (rangesQueryList != null) {
			for (int i = 0; i < rangesQueryList.size(); i++) {
				String[] singleRange = rangesQueryList.get(i).split(":");
				String target = singleRange[4];
				String rangeQuery = null;

				if ("codice_dewey".equalsIgnoreCase(target)) {
					rangeQuery = rangeCodiceDewey(singleRange);
				}
				else {
					rangeQuery = rangeQuery(singleRange);
				}

				if (solrQuery.length() > 0)
					solrQuery.append(Constants.QUERY_AND); // di default metto in AND i campi
				solrQuery.append(rangeQuery);
			}
		}

		// estensione di query
		if (extendedQuery != null) {
			if (solrQuery.length() > 0)
				solrQuery.append(Constants.QUERY_AND); // di default metto in AND i campi
			solrQuery.append(extendedQuery);
		}

		/**
		 *  OGGETTO QUERY
		 **/
		SolrQuery query = new SolrQuery();
		if (solrQuery.length() != 0) {
			query.setQuery(solrQuery.toString());
		} else {
			query.setQuery("*");
		}

		query.setRows(row);
		query.setStart(start);

		// sort field
		if (StringUtils.isNotBlank(sortField)) {
			String[] singleSortFieldArray = sortField.trim().split(",");
			for (int i = 0; i < singleSortFieldArray.length; i++) {
				String[] singleSortField = singleSortFieldArray[i].trim().split(" ");
				String singleSortOrder = "desc";
				if (singleSortField.length > 1) {
					singleSortOrder = singleSortField[1];
					sortField = singleSortField[0];
				}
				query.addSortField(sortField, ORDER.valueOf(singleSortOrder));
			}
		}

		// faceting
		if (StringUtils.isNotBlank(facetField) ) {
			query.setFacet(true);
			query.setFacetLimit(facetLimit);
			query.setFacetMinCount(facetMinCount);
			query.setFacetSort(facetSort);
			String[] singleFacetFieldArray = facetField.trim().split(",");			
			for (int i = 0; i < singleFacetFieldArray.length; i++) {
				query.addFacetField(singleFacetFieldArray[i]);	
			}
		}

		// HighLigths
		if (StringUtils.isNotBlank(hlField) ) {
			query.setHighlight(true);
			query.setHighlightRequireFieldMatch(true);
			String[] singleHlFieldArray = hlField.trim().split(",");			
			for (int i = 0; i < singleHlFieldArray.length; i++) {
				query.addHighlightField(singleHlFieldArray[i]);	
			}
		}

		// filter
		Set<String> setFiltersMap = filtersQueryMap.keySet();
		for (Iterator<String> iterator = setFiltersMap.iterator(); iterator.hasNext();) {
			String field = iterator.next();				
			query.addFilterQuery(field + ":\""  + filtersQueryMap.get(field) + "\"");			
		}

		// return field
		if (StringUtils.isNotBlank(fields) ) {			
			String[] singleFieldsArray = fields.trim().split(",");			
			for (int i = 0; i < singleFieldsArray.length; i++) {
				query.addField(singleFieldsArray[i]);
			}
		}


		// QUERY STRING PER SOLR
		queryString = ClientUtils.toQueryString(query, false).concat("&indent=on");

	}

	public String doQuery() throws SolrQueryException  {
		String response = "";
		try {
			if (StringUtils.isNotBlank(core)) {
				response = ServerInstanceManager.sendGetCommand(core, SolrCmd.SELECT, queryString);
			} else {
				response = ServerInstanceManager.sendGetCommand(SolrCmd.SELECT, queryString);	
			}
			_logSearchString.info("SOLR QUERY:[".concat(queryString).concat("]"));
		} catch (SolrServerException e) {
			_log.error("Errore nell'esecuzione della query su SOLR", e);
			throw new SolrQueryException("Errore nell'esecuzione della query su SOLR", e);
		}

		//<str name="q">
		StringBuffer temp = new StringBuffer();

		String start = "<str name=\"q\">";
		String end = "</str>";

		int idxStart = response.indexOf(start);
		temp.append(response.substring(0, idxStart + start.length()));


		String responseEnd = response.substring(idxStart + start.length());
		int idxEnd = responseEnd.indexOf(end);
		String queryTemp = responseEnd.substring(0, idxEnd);
		queryTemp = doUnescaping(queryTemp);

		temp.append(queryTemp);

		temp.append(responseEnd.substring(idxEnd));

		return temp.toString();
	}


	protected String getDateRangeQuery(String fieldName, String fromDate, String toDate)  {

		// controllo se costruire o tornare vuoto...
		if (StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate)) {
			return null;
		}

		StringBuffer dateRangeQuery = new StringBuffer(fieldName);
		dateRangeQuery.append(":[");
		if (StringUtils.isNotBlank(fromDate)) {
			fromDate = fromDate + "000000";
			dateRangeQuery.append(DatetimeConversion.getSolrDatetime(fromDate));
		} else {
			dateRangeQuery.append("*");
		}

		dateRangeQuery.append(" TO ");

		if (StringUtils.isNotBlank(toDate)) {
			toDate = toDate + "235959";
			dateRangeQuery.append(DatetimeConversion.getSolrDatetime(toDate));
		} else {
			dateRangeQuery.append("*");
		}


		dateRangeQuery.append("]");


		return dateRangeQuery.toString();
	}



	private String doEscaping(String value, List<String> valuesNoEscapeQueryList) {
		String[] toEscape = {"\\", "+","-","!","(",")","{" ,"}" ,"[" ,"]","^","\"" ,"~" ,"*" ,"?",":"," "};
		String[] escaped = {"\\\\", "\\+","\\-","\\!","\\(","\\)","\\{" ,"\\}" ,"\\[" ,"\\]","\\^","\\\"" ,"\\~" ,"\\*" ,"\\?", "\\:","\\ "};
		for (int i = 0; i < toEscape.length; i++) {

			if (valuesNoEscapeQueryList == null || !valuesNoEscapeQueryList.contains(toEscape[i])) { 
				value = StringUtils.replace(value, toEscape[i], escaped[i]);
			}

		}
		return value;
	}


	/**
	 * Escapa i caratteri speciali Lucene dal termine di ricerca inserito dall'utente
	 * @param value Il termine di ricerca inserito dall'utente
	 * @return Il termine con caratteri speciali Lucene escapati
	 */
	private String doEscaping(String value) {
		return doEscaping(value, null);
	}

	private String doUnescaping(String value) {
		String[] toUnscape = {"\\\\","\\!","\\(","\\)","\\{" ,"\\}" ,"\\[" ,"\\]","\\^","\\\"" ,"\\~" ,"\\*" ,"\\?", "\\:", "%", "#", "&amp;", "\\%252B", "\\%2D"};
		String[] unescaped = {"\\","!","(",")","{" ,"}" ,"[" ,"]","^","\"" ,"~" ,"*" ,"?",":", "%25", "%23", "%26", "%2B","-"};

		for (int i = 0; i < toUnscape.length; i++) {
			value = StringUtils.replace(value, toUnscape[i], unescaped[i]);
		}
		return value;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getFacetLimit() {
		return facetLimit;
	}

	public void setFacetLimit(int facetLimit) {
		this.facetLimit = facetLimit;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getFacetField() {
		return facetField;
	}

	public void setFacetField(String facetField) {
		this.facetField = facetField;
	}


	public Map<String, String> getFieldsQueryMap() {
		return fieldsQueryMap;
	}

	public void setFieldsQueryMap(Map<String, String> fieldsQueryMap) {
		this.fieldsQueryMap = fieldsQueryMap;
	}

	public Map<String, String> getFieldsQueryOpMap() {
		return fieldsQueryOpMap;
	}
	public void setFieldsQueryOpMap(Map<String, String> fieldsQueryOpMap) {
		this.fieldsQueryOpMap = fieldsQueryOpMap;
	}

	public Map<String, String> getFiltersQueryMap() {
		return filtersQueryMap;
	}

	public void setFiltersQueryMap(Map<String, String> filtersQueryMap) {
		this.filtersQueryMap = filtersQueryMap;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getHlField() {
		return hlField;
	}

	public void setHlField(String hlField) {
		this.hlField = hlField;
	}
	public void setFieldsNoEscapeQueryList(List<String> fieldsNoEscapeQueryList) {
		this.fieldsNoEscapeQueryList = fieldsNoEscapeQueryList;
	}
	public List<String> getFieldsNoEscapeQueryList() {
		return fieldsNoEscapeQueryList;
	}
	public String getFacetSort() {
		return facetSort;
	}
	public void setFacetSort(String facetSort) {
		this.facetSort = facetSort;
	}
	public int getFacetMinCount() {
		return facetMinCount;
	}
	public void setFacetMinCount(int facetMinCount) {
		this.facetMinCount = facetMinCount;
	}
	public void setRangesQueryList(List<String> rangesQueryList) {
		this.rangesQueryList = rangesQueryList;
	}
	public List<String> getRangesQueryList() {
		return rangesQueryList;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getCore() {
		return core;
	}
	public void setExtendedQuery(String extendedQuery) {
		this.extendedQuery = extendedQuery;
	}
	public void setValuesNoEscapeQueryList(List<String> valuesNoEscapeQueryList) {
		this.valuesNoEscapeQueryList = valuesNoEscapeQueryList;
	}
	public void setFieldsCaseQueryMap(Map<String, String> fieldsCaseQueryMap) {
		this.fieldsCaseQueryMap = fieldsCaseQueryMap;
	}

	public String rangeCodiceDewey(String[] singleRange) {
		String startName = singleRange[0];
		String startValue = singleRange[1];
		String endName = singleRange[2];
		String endValue = singleRange[3];
		if (endValue.length() == 3) {
			endValue = endValue.concat(".999");
		}
		else if (endValue.length() == 4) {
			if (endValue.endsWith(".")) {
				endValue = endValue.concat("999");
			}
		}
		else if (endValue.length() == 5) {
			endValue = endValue.concat("99");
		}
		else if (endValue.length() == 6) {
			endValue = endValue.concat("9");
		}

		String target = singleRange[4];
		String rangeQuery = target.concat(":[").concat(startValue).concat(" TO ").concat(endValue).concat("]");
		return rangeQuery;
	}
	public String rangeQuery(String[] singleRange) {
		String startName = singleRange[0];
		String startValue = singleRange[1];
		String endName = singleRange[2];
		String endValue = singleRange[3];
		String target = singleRange[4];
		String rangeQuery = target.concat(":[").concat(startValue).concat(" TO ").concat(endValue).concat("]");
		return rangeQuery;
	}

	public static void main(String[] args) {
		String value = "prova   casa biblio";
		String[] terms = value.split("\\s+");
		System.out.println();
	}

}