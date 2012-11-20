package it.inera.abi.logic.exportdynatabs;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe che rappresenta l'insieme delle tabelle dinamiche e delle relative voci
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tabelle {
	
	@XmlElement(name="tabella")
	private List<Tabella> tabelle;
	
	public Tabelle() {
	}

	public List<Tabella> getTabelle() {
		return tabelle;
	}

	public void setTabelle(List<Tabella> tabelle) {
		this.tabelle = tabelle;
	}
	
	
	
}
