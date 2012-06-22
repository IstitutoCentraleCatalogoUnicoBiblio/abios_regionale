package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regione database table.
 * 
 */
@Entity
@Table(name="regione")
public class Regione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_regione", unique=true, nullable=false)
	private Integer idRegione;

	@Column(nullable=false, length=100)
	private String denominazione;

	@Column(length=2)
	private String sigla;

	//uni-directional one-to-one association to DatiRegioniIstat
	@OneToOne
	@JoinColumn(name="id_regione", nullable=false, insertable=false, updatable=false)
	private DatiRegioniIstat datiRegioniIstat;

	//uni-directional many-to-one association to Stato
    @ManyToOne
	@JoinColumn(name="id_stato", nullable=false)
	private Stato stato;

    public Regione() {
    }

	public Integer getIdRegione() {
		return this.idRegione;
	}

	public void setIdRegione(Integer idRegione) {
		this.idRegione = idRegione;
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

	public DatiRegioniIstat getDatiRegioniIstat() {
		return this.datiRegioniIstat;
	}

	public void setDatiRegioniIstat(DatiRegioniIstat datiRegioniIstat) {
		this.datiRegioniIstat = datiRegioniIstat;
	}
	
	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
}