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

package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per la rappresentazione degli orari
 *
 */
public class OrariModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1459898121650310103L;

	public OrariModel() {

	}

	public OrariModel(int giorno, String startOre, String stopOre,
			String startMin, String stopMin) {
		set("giorno", giorno);
		set("startOre", startOre);
		set("stopOre", stopOre);
		set("startMin", startMin);
		set("stopMin", stopMin);
	}
	
	public OrariModel(Integer id_orario,String periodo, int giorno, String startOre, String stopOre,
			String startMin, String stopMin) {
		set("id_orario", id_orario);
		set("periodo", periodo);
		set("giorno", giorno);
		set("startOre", startOre);
		set("stopOre", stopOre);
		set("startMin", startMin);
		set("stopMin", stopMin);
	}

	public void setIdOrario(Integer id_orario) {
		set("id_orario", id_orario);
	}

	public Integer getIdOrario() {
		return get("id_orario");
	}
	
	public void setPeriodo(String periodo) {
		set("periodo", periodo);
	}

	public String getPeriodo() {
		return get("periodo");
	}
	
	public void setGiorno(int giorno) {
		set("giorno", giorno);
	}

	public int getGiorno() {
		return (Integer) get("giorno");
	}

	public void setStart(String start) {
		set("start", start);
	}

	public String getStart() {
		return get("start");
	}

	public void setStop(String stop) {
		set("start", stop);
	}

	public String getStop() {
		return get("stop");
	}

	public void setStartOre(String startOre) {
		set("startOre", startOre);
	}

	public String getStartOre() {
		return get("startOre");
	}

	public void setStopOre(String stopOre) {
		set("stopOre", stopOre);
	}

	public String getStopOre() {
		return get("stopOre");
	}

	public void setStartMin(String startMin) {
		set("startMin", startMin);
	}

	public String getStartMin() {
		return get("startMin");
	}

	public void setStopMin(String stopMin) {
		set("stopMin", stopMin);
	}

	public String getStopMin() {
		return get("stopMin");
	}

	public String getNomeGiorno() {
		return get("nomeGiorno");
	}
	
	public void setNomeGiorno(){
		set("nomeGiorno",selectDayName(getGiorno()));
	}

	public  static String selectDayName(int id_day) {
		String tmpDay = "";
		switch (id_day) {
	
		case 1: {
			tmpDay = "Lunedì";
			break;
		}
		case 2: {
			tmpDay = "Martedì";
			break;
		}
		case 3: {
			tmpDay = "Mercoledì";
			break;
		}
		case 4: {
			tmpDay = "Giovedì";
			break;
		}
		case 5: {
			tmpDay = "Venerdì";
			break;
		}
		case 6: {
			tmpDay = "Sabato";
			break;
		}
		case 7: {
			tmpDay = "Domenica";
			break;
		}
		}
		return tmpDay;
	
	}



	public static int selectIdDay(String nomeGiorno) {
		 
		if(nomeGiorno.compareToIgnoreCase("Lunedì")==0){
			return 1;
		}else
		if(nomeGiorno.compareToIgnoreCase("Martedì")==0){
			return 2;
		}else
		if(nomeGiorno.compareToIgnoreCase("Mercoledì")==0){
			return 3;
		}else
		if(nomeGiorno.compareToIgnoreCase("Giovedì")==0){
			return 4;
		}else
		if(nomeGiorno.compareToIgnoreCase("Venerdì")==0){
			return 5;
		}else
		if(nomeGiorno.compareToIgnoreCase("Sabato")==0){
			return 6;
		}else
		if(nomeGiorno.compareToIgnoreCase("Domenica")==0){
			return 7;
		}else
			return 0;

		
	}

}
