package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dati_regioni_istat database table.
 * 
 */
@Entity
@Table(name="dati_regioni_istat")
public class DatiRegioniIstat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_regione", unique=true, nullable=false)
	private Integer idRegione;

	@Column(name="descrizione_zona", length=20)
	private String descrizioneZona;

	@Column(name="id_sort")
	private Integer idSort;

	@Column(name="totale_abitanti", nullable=false)
	private Integer totaleAbitanti;

    public DatiRegioniIstat() {
    }

	public Integer getIdRegione() {
		return this.idRegione;
	}

	public void setIdRegione(Integer idRegione) {
		this.idRegione = idRegione;
	}

	public String getDescrizioneZona() {
		return this.descrizioneZona;
	}

	public void setDescrizioneZona(String descrizioneZona) {
		this.descrizioneZona = descrizioneZona;
	}

	public Integer getIdSort() {
		return this.idSort;
	}

	public void setIdSort(Integer idSort) {
		this.idSort = idSort;
	}

	public Integer getTotaleAbitanti() {
		return this.totaleAbitanti;
	}

	public void setTotaleAbitanti(Integer totaleAbitanti) {
		this.totaleAbitanti = totaleAbitanti;
	}

}