package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato database table.
 * 
 */
@Entity
@Table(name="stato")
public class Stato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stato", unique=true, nullable=false)
	private Integer idStato;

	@Column(nullable=false, length=100)
	private String denominazione;

	@Column(length=2)
	private String sigla;

    public Stato() {
    }

	public Integer getIdStato() {
		return this.idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}