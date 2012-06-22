package it.inera.abi.commons;

import org.apache.commons.lang.time.DateFormatUtils;

import it.inera.abi.persistence.OrarioUfficiali;

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
