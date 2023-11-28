/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.logic.exportdynatabs;

import it.inera.abi.dao.DynaTabDao;
import it.inera.abi.dao.mapping.DtoJpaMapping;
import it.inera.abi.persistence.CataloghiCollettivi;
import it.inera.abi.persistence.Comune;
import it.inera.abi.persistence.Dewey;
import it.inera.abi.persistence.PatrimonioSpecializzazione;
import it.inera.abi.persistence.PatrimonioSpecializzazioneCategoria;
import it.inera.abi.persistence.Provincia;
import it.inera.abi.persistence.Regione;
import it.inera.abi.persistence.Stato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Implementazione della procedura di export delle Tabelle Dinamiche
 * (rivisto secondo la riunione del 2 Agosto 2012)
 *
 */
public class ExportDynaTabs2Impl implements ExportDynaTabs {

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
			String basepXPath = "//biblioteche/biblioteca";
			HashMap<Class<?>, String> clazz = new HashMap<Class<?>, String>();

			clazz.put(it.inera.abi.persistence.ContattiTipo.class, basepXPath + "/anagrafica/contatti/*/@tipo");
			clazz.put(it.inera.abi.persistence.CatalogoGeneraleTipo.class, basepXPath + "/cataloghi/catalogo-generale/@tipo");
			clazz.put(it.inera.abi.persistence.PatrimonioSpecializzazione.class, basepXPath + "/*/materiali/materiale/@nome"); // /catalogo-speciale|/patrimonio
			clazz.put(it.inera.abi.persistence.SezioniSpeciali.class, basepXPath + "/servizi/sezioni-speciali/sezione");
			clazz.put(it.inera.abi.persistence.TipologiaFunzionale.class, basepXPath + "/amministrativa/ente/tipologia-funzionale");
			clazz.put(it.inera.abi.persistence.RiproduzioniTipo.class, basepXPath + "/servizi/riproduzioni/riproduzione/tipo");
			clazz.put(it.inera.abi.persistence.Dewey.class, basepXPath + "/specializzazione/descrizione-libera");
			clazz.put(it.inera.abi.persistence.SistemiBiblioteche.class, basepXPath + "/servizi/sistemi/sistema");
			clazz.put(it.inera.abi.persistence.AccessoModalita.class, basepXPath + "/servizi/accesso/condizioni-accesso/documenti/tipo");
			clazz.put(it.inera.abi.persistence.DestinazioniSocialiTipo.class, basepXPath + "/accesso/destinazioni-sociali/destinazione/valore");
			clazz.put(it.inera.abi.persistence.CataloghiCollettivi.class, basepXPath + "/cataloghi/catalogo-collettivo/nome");
			clazz.put(it.inera.abi.persistence.DepositiLegaliTipo.class, basepXPath + "/amministrativa/depositi-legali/deposito-legale/tipo");
			clazz.put(it.inera.abi.persistence.ServiziInformazioniBibliograficheModalita.class, basepXPath + "/servizi/informazioni-bibliografiche/servizio-esterno/modo");
			clazz.put(it.inera.abi.persistence.PrestitoLocaleUtentiAmmessi.class, basepXPath + "/servizi/prestito/locale/utenti-ammessi");
			clazz.put(it.inera.abi.persistence.PrestitoInterbibliotecarioModo.class, basepXPath + "s/ervizi/prestito/interbibliotecario/tipo-prestito/ruolo");
			clazz.put(it.inera.abi.persistence.SistemiPrestitoInterbibliotecario.class, basepXPath + "/servizi/prestito/interbibliotecario/sistema-ill/nome");
			clazz.put(it.inera.abi.persistence.PrestitoLocaleMaterialeEscluso.class, basepXPath + "/servizi/prestito/materiali-esclusi-locale/materiale");
			
			
			
/**
 * questi sono quelli che sono rimappati in valori tipo le percentuali o i si/notype
 */
//			clazz.put(it.inera.abi.persistence.CataloghiSupportoDigitaleTipo.class, basepXPath + "/CataloghiSupportoDigitaleTipo");
//			clazz.put(it.inera.abi.persistence.FondiSpecialiCatalogazioneInventario.class, basepXPath + "/FondiSpecialiCatalogazioneInventario");
			
			
//			clazz.put(it.inera.abi.persistence.CataloghiCollettiviZonaTipo.class, basepXPath + "/"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.PatrimonioSpecializzazioneCategoria.class, basepXPath + "/PatrimonioSpecializzazioneCategoria"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.IndicizzazioneSoggetto.class, basepXPath + "/IndicizzazioneSoggetto"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.IndicizzazioneClassificata.class, basepXPath + "/IndicizzazioneClassificata"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.NormeCatalogazione.class, basepXPath + "/NormeCatalogazione"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.StatoCatalogazioneTipo.class, basepXPath + "/StatoCatalogazioneTipo"); // non va nel formato di scambio
//			clazz.put(it.inera.abi.persistence.EnteTipologiaAmministrativa.class, basepXPath + "/EnteTipologiaAmministrativa"); // non va nel formato di scambio
			
			
			
			Tabelle tabelle = new Tabelle();
			ArrayList<Tabella> tabs = new ArrayList<Tabella>();
			
			Iterator<Class<?>> iter = clazz.keySet().iterator();
			while (iter.hasNext()) {
				Class<?> dynaClass = iter.next();
				List<?> list = dynaTabDao.loadAll(dynaClass);
				
				Tabella tempTabella = new Tabella();
				tempTabella.setElemento(clazz.get(dynaClass));

				ArrayList<String> voci = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					Object obj = list.get(i);
					Class<?> c = obj.getClass();
					Method getter = c.getDeclaredMethod("getDescrizione");
					Object descrizione = getter.invoke(obj);
					voci.add(descrizione.toString());
				}
				
				Collections.sort(voci);
				
				tempTabella.setVoce(voci);
				tabs.add(tempTabella);
				tabelle.setTabelle(tabs);
			}

			String fileName = "tabelle";
			
			JAXBContext jc = JAXBContext.newInstance(Tabelle.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			marshaller.marshal(tabelle, new FileWriter(pathToExport + "/" + fileName + ".xml"));
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


}