package it.inera.solr.solrtaglib.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.inera.solr.configuration.Constants;
import it.inera.solr.solrquery.Query;
import it.inera.solr.solrquery.SolrQueryException;

/**
 * Tag per la ricerca su SOLR
 * @author Renato Eschini r.eschini@inera.it 
 *
 */
public class SolrSearch extends TagSupport {

	private static final long serialVersionUID = -1115462743689673180L;

	private Log _log = LogFactory.getLog(SolrSearch.class);

	/**
	 * Parametri Tag
	 */
	protected String core;

	protected String formFields;
	protected String formNoEscapeFields;
	protected String formNoEscapeValues;
	// formRangeFields="STARTFIELD:DEFVALUE:ENDFIELD:DEFVALUE:TARGETFIELD,STARTFIELD2:DEFVALUE2:ENDFIELD2:DEFVALUE2:TARGETFIELD" -> formRangeFields="from:0:to:*:codice_dewey"
	protected String formRangeFields; 
	protected String hlField;
	protected String sortField;
	protected String returnFields;	
	protected int start;
	protected int row;

	protected String facetField;
	protected int facetLimit;
	protected String facetSort;
	protected int facetMinCount; //facet.sort=index&facet.mincount=1

	protected String fieldsCase;
	
	protected String extendedQuery;

	/**
	 * Campi e filtri di ricerca
	 */
	protected HashMap<String, String> fieldsQueryMap;
	protected List<String> fieldsNoEscapeQueryList;
	protected List<String> valuesNoEscapeQueryList;
	protected HashMap<String, String> fieldsQueryOpMap;
	protected Map<String, String> filtersQueryMap;
	protected Map<String, String> fieldsCaseQueryMap;
	
	protected List<String> rangesQueryList;

	protected boolean debug = false;
	
	/**
	 * Query
	 */
	protected Query query = null;

	@Override
	public final int doStartTag() throws JspException {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		/**
		 * PARAMETRI DI RICERCA DA WEB
		 */
		// controllo i parametri per i range
		rangesQueryList = new ArrayList<String>();
		if (StringUtils.isNotBlank(formRangeFields)) {
			String[] ranges = formRangeFields.split(",");
			for (int i = 0; i < ranges.length; i++) {
				String[] singleRange = ranges[i].split(":");
				String startName = singleRange[0];
				String endName = singleRange[2];

				String startParamValue = request.getParameter(startName);
				String endParamValue = request.getParameter(endName);

				if (StringUtils.isNotBlank(startParamValue) || StringUtils.isNotBlank(endParamValue)) { // se sono nulli entrambi non faccio la query per range
					String startValue = StringUtils.defaultIfEmpty(startParamValue, singleRange[1]);
					String endValue = StringUtils.defaultIfEmpty(endParamValue, singleRange[3]);
					String target = singleRange[4];
					String result = startName.concat(":").concat(startValue).concat(":").concat(endName).concat(":").concat(endValue).concat(":").concat(target);
					rangesQueryList.add(result);
				}
			}
		}

		// campi da form + controllo se c'e' l'operazione sul campo della form: parole in OR, AND o frase esatta (ONLY)
		fieldsQueryMap = new HashMap<String, String>();
		fieldsQueryOpMap = new HashMap<String, String>();
		String[] formParams = formFields.split(",");
		for (int i = 0; i < formParams.length; i++) {
			// prendo valori del campo passato
			String v = request.getParameter(formParams[i]);
			fieldsQueryMap.put(formParams[i], v);

			// controllo se c'e' un operazione di combinazione dei valori del campo
			String op = request.getParameter(formParams[i].concat(":tipo"));
			fieldsQueryOpMap.put(formParams[i], op);
		}

		if (StringUtils.isNotBlank(formNoEscapeFields)) {
			fieldsNoEscapeQueryList = new ArrayList<String>();
			String[] formNoEscapeParams = formNoEscapeFields.split(",");
			for (int i = 0; i < formNoEscapeParams.length; i++) {
				fieldsNoEscapeQueryList.add(formNoEscapeParams[i]);
			}
		}
		
		if (StringUtils.isNotBlank(formNoEscapeValues)) {
			valuesNoEscapeQueryList = new ArrayList<String>();
			String[] formNoEscapeValuesParams = formNoEscapeValues.split(",");
			for (int i = 0; i < formNoEscapeValuesParams.length; i++) {
				valuesNoEscapeQueryList.add(formNoEscapeValuesParams[i]);
			}
		}
		
		// dati paginazione
		String startP = request.getParameter(Constants.START);
		if (StringUtils.isNotBlank(startP) && NumberUtils.isNumber(startP)) {
			start = Integer.parseInt(startP);
		}

		// filtri di ricerca
		filtersQueryMap = new HashMap<String, String>();
		String[] filters = request.getParameterValues(Constants.FL);
		if (filters != null) {
			for (int i = 0; i < filters.length; i++) {
				if (StringUtils.isNotBlank(filters[i])) {
					String[] filter = filters[i].split(":");
					if (filter.length == 2) {
						filtersQueryMap.put(filter[0], filter[1]);
					}
				}
			}
		}
		
		//fieldsCaseQueryMap
		if (StringUtils.isNotBlank(fieldsCase)) {
			fieldsCaseQueryMap = new HashMap<String, String>();
			String[] fieldsCaseParams = fieldsCase.split(",");
			for (int i = 0; i < fieldsCaseParams.length; i++) {
				String[] fc = fieldsCaseParams[i].split(":");
				if (fc.length == 2) {
					fieldsCaseQueryMap.put(fc[0], fc[1]);
				}
			}
		}
		
		// costruzione query
		constructQuery();

		// esecuzione query
		String solrResponse = "";
		try {
			query.buildQuery();
			solrResponse = query.doQuery();

		} catch (SolrQueryException e) {
			_log.error("Errore nella creazione/esecuzione della query", e);
		}
		
		if (debug) {
			_log.debug("********************************* SOLR RESPONSE *********************************");
			_log.debug(solrResponse);
			_log.debug("********************************* SOLR RESPONSE *********************************");
		}
		
		pageContext.setAttribute(Constants.SOLR_RESPONSE, solrResponse);
		return EVAL_PAGE;
	}


	protected void constructQuery() {
		query = new Query();
		query.setCore(core);
		query.setRow(row);
		query.setStart(start);
		query.setSortField(sortField);

		query.setFacetField(facetField);
		query.setFacetLimit(facetLimit);
		query.setFacetMinCount(facetMinCount);
		query.setFacetSort(facetSort);

		query.setFields(returnFields);
		query.setHlField(hlField);
		query.setFieldsQueryMap(fieldsQueryMap);
		query.setFieldsNoEscapeQueryList(fieldsNoEscapeQueryList);
		query.setValuesNoEscapeQueryList(valuesNoEscapeQueryList);
		query.setFieldsCaseQueryMap(fieldsCaseQueryMap);
		
		query.setFieldsQueryOpMap(fieldsQueryOpMap);
		query.setFiltersQueryMap(filtersQueryMap);
		query.setRangesQueryList(rangesQueryList);
		query.setExtendedQuery(extendedQuery);
		
	}

	public void setCore(String core) {
		this.core = core;
	}
	public String getCore() {
		return core;
	}

	public String getFormFields() {
		return formFields;
	}

	public void setFormFields(String formFields) {
		this.formFields = formFields;
	}

	public String getFacetField() {
		return facetField;
	}

	public void setFacetField(String facetField) {
		this.facetField = facetField;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
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

	public String getReturnFields() {
		return returnFields;
	}

	public void setReturnFields(String returnFields) {
		this.returnFields = returnFields;
	}


	public String getHlField() {
		return hlField;
	}
	public void setHlField(String hlField) {
		this.hlField = hlField;
	}

	public void setFormNoEscapeFields(String formNoEscapeFields) {
		this.formNoEscapeFields = formNoEscapeFields;
	}
	public String getFormNoEscapeFields() {
		return formNoEscapeFields;
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
	public void setFormRangeFields(String formRangeFields) {
		this.formRangeFields = formRangeFields;
	}
	public String getFormRangeFields() {
		return formRangeFields;
	}
	public void setExtendedQuery(String extendedQuery) {
		this.extendedQuery = extendedQuery;
	}
	public void setFormNoEscapeValues(String formNoEscapeValues) {
		this.formNoEscapeValues = formNoEscapeValues;
	}
	public void setFieldsCase(String fieldsCase) {
		this.fieldsCase = fieldsCase;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
}