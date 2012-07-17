package it.inera.abi.gxt.server;

import it.inera.abi.gxt.client.mvc.model.BiblioModel;
import it.inera.abi.gxt.client.mvc.model.FileCaricatiListModel;
import it.inera.abi.gxt.client.services.FormatoScambioService;
import it.inera.abi.logic.auth.AuthLogic;
import it.inera.abi.logic.formatodiscambio.ExportLogic;
import it.inera.abi.logic.formatodiscambio.ImportLogic;
import it.inera.abi.logic.formatodiscambio.exports.ExportBean;
import it.inera.abi.logic.formatodiscambio.imports.ImportFileBean;
import it.inera.abi.logic.formatodiscambio.imports.InfoBiblioBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 * Endpoint per i servizi del formato di scambio
 * 
 * @author reschini
 * 
 */
public class FormatoScambioServiceImpl extends
AutoinjectingRemoteServiceServlet implements FormatoScambioService {

	private static final long serialVersionUID = -8546192049442268912L;

	private Log log = LogFactory.getLog(FormatoScambioServiceImpl.class);

	private ImportLogic importLogic;
	private ExportLogic exportLogic;

	@Autowired protected AuthLogic authLogic;

	@Autowired
	@Required
	public void setImportLogic(ImportLogic importLogic) {
		this.importLogic = importLogic;
	}

	@Autowired
	@Required
	public void setExportLogic(ExportLogic exportLogic) {
		this.exportLogic = exportLogic;
	}

	protected void sortBiblioModel(List<BiblioModel> biblioModels, final String property, String dir) {
		Collections.sort(biblioModels, new Comparator<BiblioModel>() {
			@Override
			public int compare(BiblioModel o1, BiblioModel o2) {
				return (o1.get(property).toString().compareTo(o2.get(property).toString()));
			}
		});
		if ("DESC".equalsIgnoreCase(dir)) {
			Collections.reverse(biblioModels);
		}
	}

	public PagingLoadResult<BiblioModel> pageBiblioModels(List<BiblioModel> biblioModels, PagingLoadConfig config) {
		String property = StringUtils.defaultIfEmpty(config.getSortField(), "denominazione");
		sortBiblioModel(biblioModels, property, config.getSortDir().toString());

		int fromIndex = config.getOffset();
		int toIndex = fromIndex + config.getLimit();
		if (toIndex >= biblioModels.size()) {
			toIndex = biblioModels.size();
		}
		List<BiblioModel> pagedBiblioModels = biblioModels.subList(fromIndex, toIndex);
		// la sublist mette randomaccesslist, allora lo rimetto in un list come prima
		List<BiblioModel> results = new  ArrayList<BiblioModel>();
		results.addAll(pagedBiblioModels);

		PagingLoadResult<BiblioModel> result = new BasePagingLoadResult<BiblioModel>(results, fromIndex, biblioModels.size());

		return result;
	}

	@Override
	public PagingLoadResult<BiblioModel> dettaglioCheckedFileImport(String filename, PagingLoadConfig config) {

		// carico info biblioteche
		List<BiblioModel> biblioModels = new ArrayList<BiblioModel>();
		try {
			ImportFileBean importFileBeans = importLogic.dettaglioChecked(filename);

			if (!importFileBeans.isError) {

				Iterator<InfoBiblioBean> iterator = importFileBeans.biblioteche.iterator();
				while (iterator.hasNext()) {
					InfoBiblioBean infoBiblioBean = (InfoBiblioBean) iterator.next();
					BiblioModel biblioModel = new BiblioModel();
					biblioModel.setCodice(infoBiblioBean.codiceIsil);
					biblioModel.setDenominazione(infoBiblioBean.nome);
					biblioModels.add(biblioModel);
				}
			} else {
				BiblioModel biblioModel = new BiblioModel();
				biblioModel.setCodice("Errore");
				biblioModel.setDenominazione(importFileBeans.esito);
				biblioModels.add(biblioModel);
			}

		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}

		return pageBiblioModels(biblioModels, config);
	}
	@Override
	public PagingLoadResult<BiblioModel> dettaglioScheduledFileImport(String filename, PagingLoadConfig config) {
		// carico info biblioteche
		List<BiblioModel> biblioModels = new ArrayList<BiblioModel>();
		try {
			ImportFileBean dettaglio = importLogic.dettaglioScheduled(filename);
			Iterator<InfoBiblioBean> iterator = dettaglio.biblioteche.iterator();
			while (iterator.hasNext()) {
				InfoBiblioBean infoBiblioBean = (InfoBiblioBean) iterator.next();
				BiblioModel biblioModel = new BiblioModel();
				biblioModel.setCodice(infoBiblioBean.codiceIsil);
				biblioModel.setDenominazione(infoBiblioBean.nome);
				biblioModels.add(biblioModel);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}

		return pageBiblioModels(biblioModels, config);
	}

	@Override
	public PagingLoadResult<BiblioModel> dettaglioScheduledFileExport(String filename, PagingLoadConfig config) {
		// carico info biblioteche
		List<BiblioModel> biblioModels = new ArrayList<BiblioModel>();
		try {
			ExportBean exportBean =  exportLogic.dettaglioScheduled(filename);
			Iterator<InfoBiblioBean> iterator = exportBean.biblioteche.iterator();
			while (iterator.hasNext()) {
				InfoBiblioBean infoBiblioBean = (InfoBiblioBean) iterator.next();
				BiblioModel biblioModel = new BiblioModel();
				biblioModel.setCodice(infoBiblioBean.codiceIsil);
				biblioModel.setDenominazione(infoBiblioBean.nome);
				biblioModels.add(biblioModel);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		return pageBiblioModels(biblioModels, config);
	}
	
	public Date getDateFromModel(String oraUpload, String dataUpload) throws ParseException {
		String dateTime = oraUpload.concat(" ").concat(dataUpload);
		Date date = DateUtils.parseDate(dateTime, new String[]{"HH:mm:ss dd/MM/yyyy"});
		return date;
	}

	/*
	 * SERVIZI PER IMPORT
	 */
	/**
	 * 
	 */
	@Override
	public List<FileCaricatiListModel> browseUncheckedFileImport() {
		List<FileCaricatiListModel> fileUncheckedList = new ArrayList<FileCaricatiListModel>();
		try {
			ImportFileBean[] importFileBeans = importLogic.browseUncheckedFileImport();
			for (int i = 0; i < importFileBeans.length; i++) {
				FileCaricatiListModel temp = new FileCaricatiListModel();
				temp.setUserName(importFileBeans[i].utente);
				temp.setEmail(importFileBeans[i].email);
				Date date = getDateFromModel(importFileBeans[i].oraUpload, importFileBeans[i].dataUpload);
				temp.setDataUpload(date);
				temp.setDimensione(importFileBeans[i].dimensione);
				temp.setFilename(importFileBeans[i].fileName);
				temp.setOriginalFilename(importFileBeans[i].originalFilename);
				fileUncheckedList.add(temp);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		return fileUncheckedList;
	}

	@Override
	public List<FileCaricatiListModel> browseCheckedFileImport() {
		List<FileCaricatiListModel> fileCheckedList = new ArrayList<FileCaricatiListModel>();
		try {
			ImportFileBean[] importFileBeans = importLogic.browseCheckedFileImport();
			for (int i = 0; i < importFileBeans.length; i++) {
				FileCaricatiListModel temp = new FileCaricatiListModel();
				temp.setUserName(importFileBeans[i].utente);
				temp.setEmail(importFileBeans[i].email);
				Date date = getDateFromModel(importFileBeans[i].oraUpload, importFileBeans[i].dataUpload);
				temp.setDataUpload(date);
				temp.setDimensione(importFileBeans[i].dimensione);
				temp.setFilename(importFileBeans[i].fileName);
				temp.setNbib(importFileBeans[i].nbib);
				temp.setError(importFileBeans[i].isError);
				temp.setOriginalFilename(importFileBeans[i].originalFilename);
				fileCheckedList.add(temp);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		return fileCheckedList;
	}



	@Override
	public List<FileCaricatiListModel> browseScheduledImport() {
		List<FileCaricatiListModel> fileScheduledList = new ArrayList<FileCaricatiListModel>();
		try {
			ImportFileBean[] importFileBeans = importLogic.browseScheduledImport();
			for (int i = 0; i < importFileBeans.length; i++) {
				FileCaricatiListModel temp = new FileCaricatiListModel();
				temp.setUserName(importFileBeans[i].utente);
				temp.setEmail(importFileBeans[i].email);
				Date date = getDateFromModel(importFileBeans[i].oraUpload, importFileBeans[i].dataUpload);
				temp.setDataUpload(date);
				temp.setDimensione(importFileBeans[i].dimensione);
				temp.setFilename(importFileBeans[i].fileName);
				temp.setNbib(importFileBeans[i].nbib);
				temp.setOriginalFilename(importFileBeans[i].originalFilename);
				fileScheduledList.add(temp);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		return fileScheduledList;
	}

	@Override
	public void deleteFile(String filename) {
		try {
			if (!importLogic.deleteFile(filename)) {
				log.warn("File non cancellato");	
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteUncheckedFile(String filename) {
		try {
			if (!importLogic.deleteUncheckedFile(filename)) {
				log.warn("File non cancellato");	
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteScheduledImportFile(String filename, boolean definitivo) {
		try {
			if (!importLogic.deleteScheduledFile(filename, definitivo)) {
				log.warn("File schedulato non cancellato");	
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void checkFile(String filename) {
		try {
			importLogic.checkFile(filename);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void importFile(String filename, boolean differito) {
		try {
			if (differito) {
				importLogic.importFileDifferito(filename);	
			} else {
				importLogic.importFile(filename);	
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	/*
	 * SERVIZI PER EXPORT
	 */
	@Override
	public List<FileCaricatiListModel> browseScheduledExport() {
		List<FileCaricatiListModel> fileScheduledList = new ArrayList<FileCaricatiListModel>();
		try {
			ExportBean[] exportBeans = exportLogic.browseScheduledExport();
			for (int i = 0; i < exportBeans.length; i++) {
				FileCaricatiListModel temp = new FileCaricatiListModel();
				temp.setUserName(exportBeans[i].username);
				temp.setEmail(exportBeans[i].email);
				Date date = getDateFromModel(exportBeans[i].ora, exportBeans[i].data);
				temp.setDataUpload(date);
				temp.setNbib(String.valueOf(exportBeans[i].nBib));
				temp.setFilename(exportBeans[i].fileName);

				fileScheduledList.add(temp);
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		return fileScheduledList;
	}

	@Override
	public void deleteScheduledExportFile(String filename) {
		try {
			if (!exportLogic.deleteScheduledFile(filename)) {
				log.warn("File non cancellato");	
			}
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}


	/* Metodo per il servizio del formato di scambio */
	@Override
	public void retrieveBiblioFormatoDiScambio(List<Integer> idbibselected, HashMap<String, Object> labels) {

		String username = authLogic.retrieveLoggedUser().getUsername();

		String[] idBiblios = new String[idbibselected.size()];
		String email = null;
		Boolean differito = false;

		int i = 0;
		for (Integer entry : idbibselected) {
			idBiblios[i] = entry.toString();
			i++;
		}

		if (labels.containsKey("emailNotifica") && labels.get("emailNotifica") != null)
			email = new String((String)labels.get("emailNotifica"));

		if (labels.containsKey("differito") && labels.get("differito") != null) {
			if (labels.get("differito").equals("true"))
				differito = true;

		}

		try {
			exportLogic.doExport(idBiblios, username, email, differito);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}

	}


}
