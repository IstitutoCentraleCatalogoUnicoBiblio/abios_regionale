package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prestito_locale_utenti_ammessi database table.
 * 
 */
@Entity
@Table(name="prestito_locale_utenti_ammessi")
public class PrestitoLocaleUtentiAmmessi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_locale_utenti_ammessi", unique=true, nullable=false)
	private Integer idPrestitoLocaleUtentiAmmessi;

	@Column(nullable=false, length=255)
	private String descrizione;

    public PrestitoLocaleUtentiAmmessi() {
    }

	public Integer getIdPrestitoLocaleUtentiAmmessi() {
		return this.idPrestitoLocaleUtentiAmmessi;
	}

	public void setIdPrestitoLocaleUtentiAmmessi(Integer idPrestitoLocaleUtentiAmmessi) {
		this.idPrestitoLocaleUtentiAmmessi = idPrestitoLocaleUtentiAmmessi;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}