package it.inera.abi.solr.transformer;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.Transformer;

public class FasceTransformer extends Transformer {
	
	
	@Override
	public Object transformRow(Map<String, Object> row, Context context) {
		List<String> fasce = new ArrayList<String>();
		
		
		Integer giorno = (Integer) row.get("giorno");
		
	
		Time dalle = (Time) row.get("dalle");
		Time alle = (Time) row.get("alle");
		
		Calendar da = Calendar.getInstance();
		da.setTime(dalle);
		Calendar a = Calendar.getInstance();
		a.setTime(alle);
		
		/* String fascia = FasceOrarie.getFascia(da, a); */
		fasce = FasceOrarie.getFascia(da, a);
		if (giorno == 7) fasce.add(FasceOrarie.FESTIVO);
		
		row.put("orario_ufficiale_fasce", fasce);
		
		return row;
	}

}
