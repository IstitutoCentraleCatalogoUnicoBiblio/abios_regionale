package it.inera.abi.solr.transformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FasceOrarie {
	
	public final static String MATTINA = "Mattina";
	public final static String POMERIGGIO = "Pomeriggio";
	public final static String SERA = "Sera";
	public final static String FESTIVO = "Festivo";	
	
	public static Calendar getLimite(int hours, int minutes) {
		Calendar limite  = Calendar.getInstance();
		limite.set(Calendar.YEAR, 1970);
		limite.set(Calendar.MONTH, 0);
		limite.set(Calendar.DAY_OF_MONTH, 1);
		limite.set(Calendar.HOUR_OF_DAY, hours);
		limite.set(Calendar.MINUTE, minutes);
		limite.set(Calendar.SECOND, 0);
		limite.set(Calendar.MILLISECOND, 0);
		return limite;
	}
	
	public static List<String> getFascia(Calendar da, Calendar a) {
		List<String> l = new ArrayList<String>();

		if (da.compareTo(getLimite(20, 0)) >= 0) {
			l.add(SERA);
		}
		else if (da.compareTo(getLimite(14, 0)) >= 0) {
			// Si tratta del caso fascia oraria 14:00 - x
			if(a.compareTo(getLimite(20, 0)) <= 0) {
				// Si tratta del caso fascia oraria 14:00 - x, dove x <= 20:00 
				l.add(POMERIGGIO);
			}
			else {
				// Si tratta del caso fascia oraria 14:00 - x, dove x > 20:00
				l.add(POMERIGGIO);
				l.add(SERA);
			}
		}
		else if (da.compareTo(getLimite(8, 0)) >= 0) {
			if (a.compareTo(getLimite(20, 0)) > 0) {
				// Si tratta del caso fascia oraria 08:00 - x, dove x > 20:00
				l.add(MATTINA);
				l.add(POMERIGGIO);
				l.add(SERA);
			}
			else if (a.compareTo(getLimite(14, 0)) > 0) {
				// Si tratta del caso fascia oraria 08:00 - x, dove 14:00 < x <= 20:00
				l.add(MATTINA);
				l.add(POMERIGGIO);
			}
			else {
				// Si tratta del caso fascia oraria 08:00 - x, dove x <= 14:00
				l.add(MATTINA);
			}
		}
		else {
			if (a.compareTo(getLimite(8, 0)) <= 0) {
				// Si tratta del caso fascia oraria 00:00 - x, dove x <= 08:00
				l.add(SERA);
			}
			else if (a.compareTo(getLimite(14, 0)) <= 0) {
				// Si tratta del caso fascia oraria 00:00 - x, dove 08:00 < x <= 14:00
				l.add(SERA);
				l.add(MATTINA);
			}
			else  if (a.compareTo(getLimite(20, 0)) <= 0) {
				// Si tratta del caso fascia oraria 00:00 - x, dove 14:00 < x <= 20:00
				l.add(SERA);
				l.add(MATTINA);
				l.add(POMERIGGIO);
			}
			else {
				// Si tratta del caso fascia oraria 00:00 - x, dove x > 20:00
				l.add(SERA);
				l.add(MATTINA);
				l.add(POMERIGGIO);
				l.add(SERA);
			}
		}
		
		/*if (a.compareTo(getLimite(8, 0)) >= 0 && a.compareTo(getLimite(14, 0)) < 0) {
			l.add(MATTINA);
		}
		if (a.compareTo(getLimite(14, 0)) >= 0 && a.compareTo(getLimite(20, 0)) < 0) {
			l.add(POMERIGGIO);
		}
		if (
				(a.compareTo(getLimite(20, 0)) >= 0 && a.compareTo(getLimite(24, 0)) < 0) ||
				(a.compareTo(getLimite(0, 0)) >= 0 && a.compareTo(getLimite(8, 0)) < 0)
			)
		{
			l.add(SERA);
		}*/
		return l;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		Map<String, List<String>> fasce = new HashMap<String, List<String>>();
		
		for (int z = 0; z < 100; z++) {
		
			for (int i = 0; i < 24; i++) {
				for (int j = -1; j <= 58; j = j +30) {
					Calendar toTest = getCalendarTest(i, j + 1 );
					Calendar toTest2 = getCalendarTest(i, j + 1);
					List<String> fascia = FasceOrarie.getFascia(toTest, toTest2);
					//if (StringUtils.isBlank(fascia)) {
						//System.out.println("ERRORE");
				//	} else {
						fasce.put("prova", fascia);
					//}
				}
			}
		
		}
		System.out.println(fasce.keySet().toString());
	}
	
	public static Calendar getCalendarTest(int hours, int minutes) {
		Calendar test  = Calendar.getInstance();
		test.set(Calendar.YEAR, 1970);
		test.set(Calendar.MONTH, 0);
		test.set(Calendar.DAY_OF_MONTH, 1);
		test.set(Calendar.HOUR, hours);
		test.set(Calendar.MINUTE, minutes);
		test.set(Calendar.SECOND, 0);
		test.set(Calendar.MILLISECOND, 0);
		return test;
	}

}
