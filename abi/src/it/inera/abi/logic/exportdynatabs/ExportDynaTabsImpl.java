package it.inera.abi.logic.exportdynatabs;

import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Stato;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.exolab.castor.xml.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExportDynaTabsImpl implements ExportDynaTabs {

	@Value("${export.dynatab.path.to.export}")
	private String pathToExport;
	@Value("${export.dynatab.compress}")
	private boolean compress;
	@Value("${export.dynatab.compress.filename}")
	private String compressFileName;
	@Value("${export.dynatab.compress.remove.xml}")
	private boolean removeXml;
	@Autowired
	private DynaTabDao dynaTabDao;

	@Override
	public void doExport() throws Exception {
		try {
			DtoJpaMapping.getDynaClass(-1);
			Hashtable<Integer, Class<?>> dynaClasses = DtoJpaMapping.getDynaClasses();
			dynaClasses.put(DtoJpaMapping.COMUNI_INDEX, Comune.class);
			dynaClasses.put(DtoJpaMapping.STATI_INDEX, Stato.class);
			dynaClasses.put(DtoJpaMapping.PROVINCE_INDEX, Provincia.class);
			dynaClasses.put(DtoJpaMapping.REGIONI_INDEX, Regione.class);
			Iterator<Class<?>> iter = dynaClasses.values().iterator();
			while (iter.hasNext()) {
				Class<?> dynaClass = iter.next();
				List<?> list = dynaTabDao.loadAll(dynaClass);
				for (int i = 0; i < list.size(); i++) {
					avoidHibernateProxy(list.get(i));
					if ("Comune".equals(dynaClass.getSimpleName())) {
						manageComune(list.get(i));
					}
					if ("Provincia".equals(dynaClass.getSimpleName())) {
						manageProvincia(list.get(i));
					}
					if ("Regione".equals(dynaClass.getSimpleName())) {
						manageRegione(list.get(i));
					}
				}

				String fileName = generateFileName(dynaClass);
				Marshaller marshaller = new Marshaller(new FileWriter(pathToExport + "/" + fileName + ".xml"));
				marshaller.setSuppressNamespaces(true);
				marshaller.setSuppressXSIType(true);
				marshaller.setSupressXMLDeclaration(true);
				marshaller.setRootElement("tabella_" + fileName);
				marshaller.marshal(list);
			}

			if (compress) doZip(pathToExport, compressFileName); 

			if (removeXml) doRemoveXml(pathToExport);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void doZip(String pathToExport, String compressFileName) {
		FileOutputStream fileOutputStream = null;
		ZipOutputStream out = null;
		try {
			fileOutputStream = new FileOutputStream(pathToExport + "/" + compressFileName);
			out = new ZipOutputStream(fileOutputStream);

			Iterator<File> iter = FileUtils.iterateFiles(new File(pathToExport), new String[] {"xml"}, false);
			while (iter.hasNext()) {
				File file = iter.next();
				out.putNextEntry(new ZipEntry(file.getName()));
				// Transfer bytes from the file to the ZIP file
				out.write(IOUtils.toByteArray(new FileInputStream(file)));
			}

		} catch (Exception ex) {

		} finally {
			if (out != null) {
				try {
					out.closeEntry();
					out.flush();
					out.close();
				} catch (Exception ex) {

				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (Exception ex) {

				}
			}

		}
	}

	private void doRemoveXml(String pathToExport) {
		Iterator<File> iter = FileUtils.iterateFiles(new File(pathToExport), new String[] {"xml"}, false);
		while (iter.hasNext()) {
			File file = iter.next();
			FileUtils.deleteQuietly(file);
		}
	}

	private void avoidHibernateProxy(Object obj) throws Exception {
		Class<?> c = obj.getClass();
		Method[] methods = c.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.startsWith("get")) {
				Object test = methods[i].invoke(obj);
				if (test instanceof org.hibernate.collection.PersistentBag) {
					String setMethodName = methodName.replaceFirst("get", "set");
					Class<?> cls = methods[i].getReturnType();
					Method setter = c.getDeclaredMethod(setMethodName, cls);
					Object empty = null;
					setter.invoke(obj, empty);
				}

			}
		}

	}

	private String generateFileName(Class<?> clazz) {
		return clazz.getSimpleName().replaceAll("\\B([A-Z])", "_$1").toLowerCase();
	}

	private void manageComune(Object obj) {
		int idProvincia = ((Comune) obj).getProvincia().getIdProvincia();
		Provincia temp = new Provincia();
		temp.setIdProvincia(idProvincia);
		((Comune) obj).setProvincia(temp);
	}

	private void manageRegione(Object obj) {
		((Regione) obj).setStato(null);
		((Regione) obj).setDatiRegioniIstat(null);
	}

	private void manageProvincia(Object obj) {
		int idRegione = ((Provincia) obj).getRegione().getIdRegione();
		Regione temp = new Regione();
		temp.setIdRegione(idRegione);
		((Provincia) obj).setRegione(temp);
		((Provincia) obj).setDatiProvinceIstat(null);
	}
}