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

package it.inera.abi.logic.stampe;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Classe di utility per la generazione delle stampe
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class StampeUtils {
	
	public static String currentDate() {
        GregorianCalendar cal = new GregorianCalendar(Locale.ITALY);

        String anno    = Integer.toString(cal.get(Calendar.YEAR));
        String mese    = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String giorno  = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        String ora     = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
        String minuto  = Integer.toString(cal.get(Calendar.MINUTE));

        if (mese.length()   == 1) mese   = "0" + mese;
        if (giorno.length() == 1) giorno = "0" + giorno;
        if (ora.length()    == 1) ora    = "0" + ora;
        if (minuto.length() == 1) minuto = "0" + minuto;

        return ("Data report: " + giorno + "/" + mese + "/" + anno + "\n");
    }

	public static String[] splitParameters(String params, String token) {
		StringTokenizer st = new StringTokenizer(params, token);
		Vector tmp = new Vector();
		while (st.hasMoreTokens()) {
			tmp.add(st.nextToken());
		}
		String[] result = new String[tmp.size()];
		result = (String[]) tmp.toArray(result);
		return result;
	}
}
