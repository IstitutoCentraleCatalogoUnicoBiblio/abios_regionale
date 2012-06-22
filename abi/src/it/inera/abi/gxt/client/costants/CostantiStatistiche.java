package it.inera.abi.gxt.client.costants;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene le costanti relative alla voce statistiche del menu utente ICCU
 */
public class CostantiStatistiche {
	/* LISTA NOMI */
	public static final String TAV_01 = "Biblioteche in anagrafe";
	public static final String TAV_01a = "Biblioteche per provincia";
	public static final String TAV_01b = "Biblioteche censite";
	public static final String TAV_01c = "Biblioteche censite per provincia";

	public static final String TAV_02 = "Biblioteche per tip. funzionale (somme)";
	public static final String TAV_02a = "Biblioteche per tip. funzionale (%)";
	public static final String TAV_02b = "Biblioteche per tip. funz. e amm.";
	public static final String TAV_02c = "Biblioteche per tip. amministrativa";

	public static final String TAV_03 = "Biblioteche per secolo di fond.";

	public static final String TAV_04 = "Biblioteche per anno di fond. (XX-XXI sec)";
	
	public static final String TAV_05 = "Biblioteche per mod. di accesso";
	public static final String TAV_05a = "Biblioteche per mod. accesso e tip. funzionale";
	public static final String TAV_05b = "Biblioteche per ore di apertura sett.";
	
	public static final String TAV_06 = "Biblioteche per servizi offerti";
	public static final String TAV_07 = "Consistenza patrimonio";
	public static final String TAV_08 = "Biblioteche per consistenza patr.";
	public static final String TAV_09 = "Biblioteche per tip. funz. e consistenza patr.";
	public static final String TAV_10 = "Biblioteche per tip. amm. e consistenza patr.";
	public static final String GRAF_01 = "Grafico: Biblioteche per tip. amministrativa";
	public static final String GRAF_02 = "Grafico: Biblioteche per zona e accesso";
	public static final String GRAF_03 = "Grafico: Biblioteche per catal. di consultazione";

	/* LISTA URL */

	public static String TAVOLA01 = "TAVOLA01.html";
	public static String TAVOLA01a = "TAVOLA01a.html";
	public static String TAVOLA01b = "TAVOLA01b.html";
	public static String TAVOLA01c = "TAVOLA01c.html";

	public static String TAVOLA02 = "TAVOLA02.html";
	public static String TAVOLA02a = "TAVOLA02a.html";
	public static String TAVOLA02b = "TAVOLA02b.html";
	public static String TAVOLA02c = "TAVOLA02c.html";

	public static String TAVOLA03 = "TAVOLA03.html";
	public static String TAVOLA04 = "TAVOLA04.html";

	public static String TAVOLA05 = "TAVOLA05.html";
	public static String TAVOLA05a = "TAVOLA05a.html";
	public static String TAVOLA05b = "TAVOLA05b.html";

	public static String TAVOLA06 = "TAVOLA06.html";
	public static String TAVOLA07 = "TAVOLA07.html";
	public static String TAVOLA08 = "TAVOLA08.html";
	public static String TAVOLA09 = "TAVOLA09.html";
	public static String TAVOLA10 = "TAVOLA10.html";

	public static String GRAFICO01 = "GRAFICO01.html";
	public static String GRAFICO02 = "GRAFICO02.html";
	public static String GRAFICO03 = "GRAFICO03.html";

	public static List<String> getListaNomiTabelle() {

		List<String> listaNomiTabelle = new ArrayList<String>();
		listaNomiTabelle.add(TAV_01);
		listaNomiTabelle.add(TAV_01a);
		listaNomiTabelle.add(TAV_01b);
		listaNomiTabelle.add(TAV_01c);

		listaNomiTabelle.add(TAV_02);
		listaNomiTabelle.add(TAV_02a);
		listaNomiTabelle.add(TAV_02b);
		listaNomiTabelle.add(TAV_02c);

		listaNomiTabelle.add(TAV_03);
		listaNomiTabelle.add(TAV_04);

		listaNomiTabelle.add(TAV_05);
		listaNomiTabelle.add(TAV_05a);
		listaNomiTabelle.add(TAV_05b);

		listaNomiTabelle.add(TAV_06);
		listaNomiTabelle.add(TAV_07);
		listaNomiTabelle.add(TAV_08);
		listaNomiTabelle.add(TAV_09);
		listaNomiTabelle.add(TAV_10);

		listaNomiTabelle.add(GRAF_01);
		listaNomiTabelle.add(GRAF_02);
		listaNomiTabelle.add(GRAF_03);

		return listaNomiTabelle;
	}

	public static List<String> getListaUrlTabelle() {
		List<String> listaUrlTabelle = new ArrayList<String>();
		listaUrlTabelle.add(TAVOLA01);
		listaUrlTabelle.add(TAVOLA01a);
		listaUrlTabelle.add(TAVOLA01b);
		listaUrlTabelle.add(TAVOLA01c);

		listaUrlTabelle.add(TAVOLA02);
		listaUrlTabelle.add(TAVOLA02a);
		listaUrlTabelle.add(TAVOLA02b);
		listaUrlTabelle.add(TAVOLA02c);

		listaUrlTabelle.add(TAVOLA03);
		listaUrlTabelle.add(TAVOLA04);

		listaUrlTabelle.add(TAVOLA05);
		listaUrlTabelle.add(TAVOLA05a);
		listaUrlTabelle.add(TAVOLA05b);

		listaUrlTabelle.add(TAVOLA06);
		listaUrlTabelle.add(TAVOLA07);
		listaUrlTabelle.add(TAVOLA08);
		listaUrlTabelle.add(TAVOLA09);
		listaUrlTabelle.add(TAVOLA10);

		listaUrlTabelle.add(GRAFICO01);
		listaUrlTabelle.add(GRAFICO02);
		listaUrlTabelle.add(GRAFICO03);
		return listaUrlTabelle;
	}

}
