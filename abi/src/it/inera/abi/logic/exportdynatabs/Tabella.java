package it.inera.abi.logic.exportdynatabs;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe che rappresenta la singola tabella dinamica con l'elenco di tutte le sue voci
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tabella {
	
	@XmlAttribute
	private String elemento;
	private List<String> voce;
	
	public Tabella() {
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public List<String> getVoce() {
		return voce;
	}

	public void setVoce(List<String> voce) {
		this.voce = voce;
	}
	
	
}
