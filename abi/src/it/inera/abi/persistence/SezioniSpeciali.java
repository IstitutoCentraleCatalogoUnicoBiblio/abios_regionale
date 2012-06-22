package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sezioni_speciali database table.
 * 
 */
@Entity
@Table(name="sezioni_speciali")
public class SezioniSpeciali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sezioni_speciali", unique=true, nullable=false)
	private Integer idSezioniSpeciali;

	@Column(length=255)
	private String descrizione;

    public SezioniSpeciali() {
    }

	public Integer getIdSezioniSpeciali() {
		return this.idSezioniSpeciali;
	}

	public void setIdSezioniSpeciali(Integer idSezioniSpeciali) {
		this.idSezioniSpeciali = idSezioniSpeciali;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}