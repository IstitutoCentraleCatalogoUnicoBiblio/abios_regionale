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

package it.inera.abi.commons;

import org.apache.commons.lang.time.DateFormatUtils;

import it.inera.abi.persistence.OrarioUfficiali;

/**
 * Classe di utility per la comparazione tra oggetti
 * 
 */
public class CompareUtils {
	

	public final static boolean equals(String s1, String s2) {
		String toTest1 = org.apache.commons.lang.StringUtils.defaultIfEmpty(s1, "");
		String toTest2 = org.apache.commons.lang.StringUtils.defaultIfEmpty(s2, "");
		return org.apache.commons.lang.StringUtils.equals(toTest1, toTest2);
	}
	
	public final static boolean equals(Integer s1, Integer s2) {
		if (s1 == null && s2 == null) return true;
		if (s1 == null && s2 != null) return false;
		if (s1 != null && s2 == null) return false;
		return s1.intValue() == s2.intValue();
	}
	
	
	public final static boolean equals(OrarioUfficiali s1, OrarioUfficiali s2) {
		if (s1 == null && s2 == null) return true;
		if (s1 == null && s2 != null) return false;
		if (s1 != null && s2 == null) return false;
		
		if (!equals(s1.getGiorno(), s2.getGiorno())) return false;
		
		if (!equals(DateFormatUtils.format(s1.getAlle(), "hh:mm"), DateFormatUtils.format(s2.getAlle(), "hh:mm"))) return false;
		
		if (!equals(DateFormatUtils.format(s1.getDalle(), "hh:mm"), DateFormatUtils.format(s2.getDalle(), "hh:mm"))) return false;
		
		return true;
	}
	
}
