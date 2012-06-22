package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
@Table(name="provincia")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_provincia", unique=true, nullable=false)
	private Integer idProvincia;

	@Column(name="codice_istat", length=10)
	private String codiceIstat;

	@Column(nullable=false, length=100)
	private String denominazione;

	@Column(length=2)
	private String sigla;

	//uni-directional one-to-one association to DatiProvinceIstat
	@OneToOne
	@JoinColumn(name="id_provincia", nullable=false, insertable=false, updatable=false)
	private DatiProvinceIstat datiProvinceIstat;

	//uni-directional many-to-one association to Regione
    @ManyToOne
	@JoinColumn(name="id_regione", nullable=false)
	private Regione regione;

    public Provincia() {
    }

	public Integer getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public DatiProvinceIstat getDatiProvinceIstat() {
		return this.datiProvinceIstat;
	}

	public void setDatiProvinceIstat(DatiProvinceIstat datiProvinceIstat) {
		this.datiProvinceIstat = datiProvinceIstat;
	}
	
	public Regione getRegione() {
		return this.regione;
	}

	public void setRegione(Regione regione) {
		this.regione = regione;
	}
	
}