package it.inera.abi.gxt.client.mvc.view.center.formatoscambio.widget;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.google.gwt.i18n.client.DateTimeFormat;

public class ColumnConfigFileCaricatiBaseList {
	
	public static List<ColumnConfig> getFormatoScambioBaseColumnConfig(boolean nbib, boolean dimensione, boolean scheduling) {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnFilename = new ColumnConfig();
		columnFilename.setId("originalFilename");
		columnFilename.setHeader("Nome File");
		columnFilename.setWidth(80);
		configs.add(columnFilename);
		
		ColumnConfig columnUtente = new ColumnConfig();
		columnUtente.setId("userName");
		columnUtente.setHeader("Utente");
		columnUtente.setWidth(100);
		configs.add(columnUtente);

		ColumnConfig columnEmail = new ColumnConfig();
		columnEmail.setId("email");
		columnEmail.setHeader("Email");
		columnEmail.setWidth(150);
		configs.add(columnEmail);

		if (scheduling) {
			ColumnConfig columnDataUpload = new ColumnConfig();
			columnDataUpload.setId("dataUpload");
			columnDataUpload.setHeader("Data di schedulazione");
			columnDataUpload.setWidth(130);
			columnDataUpload.setDateTimeFormat(DateTimeFormat.getFormat("HH:mm:ss dd/MM/yyyy"));  
			configs.add(columnDataUpload);
			
		} else {
			ColumnConfig columnDataUpload = new ColumnConfig();
			columnDataUpload.setId("dataUpload");
			columnDataUpload.setHeader("Data di upload");
			columnDataUpload.setWidth(130);
			columnDataUpload.setDateTimeFormat(DateTimeFormat.getFormat("HH:mm:ss dd/MM/yyyy"));  
			configs.add(columnDataUpload);
		}
		
		if (nbib) {
			ColumnConfig columnNbib = new ColumnConfig();
			columnNbib.setId("nBib");
			columnNbib.setHeader("n. Bib.");
			columnNbib.setWidth(50);
			configs.add(columnNbib);
		}
		
		if (dimensione) {
			ColumnConfig columnDimensione = new ColumnConfig();
			columnDimensione.setId("dimensione");
			columnDimensione.setHeader("Dimensione");
			columnDimensione.setWidth(90);
			configs.add(columnDimensione);
		}
		
		return configs;
	}
	
	public static String getXTemplateImport() {
		return "<tpl if=\"(error)\">{esito}</tpl><tpl if=\"(!error)\"><p><b>Biblioteche</b><tpl for=\"biblios\"><p>{#}. {codice} {denominazione}</p></tpl></p></tpl>";
	}
	public static String getXTemplateExport() {
		return "<b>Biblioteche</b><tpl for=\"biblios\"><p>{#}. {codice} {denominazione}</p></tpl>";
	}

}
