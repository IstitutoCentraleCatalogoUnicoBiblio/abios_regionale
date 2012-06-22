package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comune database table.
 * 
 */
@Entity
@Table(name="comune")
public class Comune implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_comune", unique=true, nullable=false)
	private Integer idComune;

	@Column(name="codice_finanze", length=6)
	private String codiceFinanze;

	@Column(name="codice_istat", length=6)
	private String codiceIstat;

	@Column(nullable=false, length=100)
	private String denominazione;

	//uni-directional many-to-one association to Provincia
    @ManyToOne
	@JoinColumn(name="id_provincia", nullable=false)
	private Provincia provincia;

    public Comune() {
    }

	public Integer getIdComune() {
		return this.idComune;
	}

	public void setIdComune(Integer idComune) {
		this.idComune = idComune;
	}

	public String getCodiceFinanze() {
		return this.codiceFinanze;
	}

	public void setCodiceFinanze(String codiceFinanze) {
		this.codiceFinanze = codiceFinanze;
	}

	public String getCodiceIstat() {
		return this.codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
}