package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the indicizzazione_soggetto database table.
 * 
 */
@Entity
@Table(name="indicizzazione_soggetto")
public class IndicizzazioneSoggetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_indicizzazione_soggetto", unique=true, nullable=false)
	private Integer idIndicizzazioneSoggetto;

	@Column(length=255)
	private String descrizione;

    public IndicizzazioneSoggetto() {
    }

	public Integer getIdIndicizzazioneSoggetto() {
		return this.idIndicizzazioneSoggetto;
	}

	public void setIdIndicizzazioneSoggetto(Integer idIndicizzazioneSoggetto) {
		this.idIndicizzazioneSoggetto = idIndicizzazioneSoggetto;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}