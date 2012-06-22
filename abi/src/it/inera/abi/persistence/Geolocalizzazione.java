package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the geolocalizzazione database table.
 * 
 */
@Entity
@Table(name="geolocalizzazione")
public class Geolocalizzazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

    @Lob()
	private String error;

	private Double latitudine;

	private Double longitudine;

	@Column(length=50)
	private String precisione;

    @Lob()
	private String warning;

    public Geolocalizzazione() {
    }

	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Double getLatitudine() {
		return this.latitudine;
	}

	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}

	public Double getLongitudine() {
		return this.longitudine;
	}

	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}

	public String getPrecisione() {
		return this.precisione;
	}

	public void setPrecisione(String precisione) {
		this.precisione = precisione;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}