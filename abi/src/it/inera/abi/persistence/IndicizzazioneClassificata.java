package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the indicizzazione_classificata database table.
 * 
 */
@Entity
@Table(name="indicizzazione_classificata")
public class IndicizzazioneClassificata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_indicizzazione_classificata", unique=true, nullable=false)
	private Integer idIndicizzazioneClassificata;

	@Column(length=255)
	private String descrizione;

    public IndicizzazioneClassificata() {
    }

	public Integer getIdIndicizzazioneClassificata() {
		return this.idIndicizzazioneClassificata;
	}

	public void setIdIndicizzazioneClassificata(Integer idIndicizzazioneClassificata) {
		this.idIndicizzazioneClassificata = idIndicizzazioneClassificata;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}