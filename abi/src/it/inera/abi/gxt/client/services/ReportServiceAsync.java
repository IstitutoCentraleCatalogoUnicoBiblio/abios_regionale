package it.inera.abi.gxt.client.services;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interfaccia Async di <code>ReportService</code>
 * 
 */
public interface ReportServiceAsync {

	void retrieveBiblioReport(List<Integer> idbibselected, HashMap<String, Object> labels, AsyncCallback<Void> callback);
	
}
