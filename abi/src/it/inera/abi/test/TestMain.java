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

package it.inera.abi.test;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.persistence.Biblioteca;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe di test per il caricamento delle biblioteche in base a criteri di ricerca
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext appCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		BiblioDao biblioDao = (BiblioDao)appCtxt.getBean(BiblioDao.class);
		List<Biblioteca> listaBiblioteche = biblioDao.ricercaBiblio(null, 0, -1, "utenteUltimaModifica", "ASC");
		//System.out.println(listaBiblioteche.size());
		
	}

}
