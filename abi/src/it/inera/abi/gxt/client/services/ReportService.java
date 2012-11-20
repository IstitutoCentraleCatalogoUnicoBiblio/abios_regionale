package it.inera.abi.gxt.client.services;

import it.inera.abi.gxt.client.Abi;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interfaccia dei servizi relativi ai report (lato client)
 * 
 */
@RemoteServiceRelativePath(Abi.REPORTSERVICE)
public interface ReportService extends RemoteService {

	public void retrieveBiblioReport(List<Integer> idbibselected, HashMap<String, Object> labels);
	
}
