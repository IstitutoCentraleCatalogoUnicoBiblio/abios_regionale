package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the thesaurus database table.
 * 
 */
@Entity
@Table(name="thesaurus")
public class Thesaurus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thesaurus", unique=true, nullable=false)
	private Integer idThesaurus;

	@Column(name="nome", length=255)
	private String descrizione;

    public Thesaurus() {
    }

	public Integer getIdThesaurus() {
		return this.idThesaurus;
	}

	public void setIdThesaurus(Integer idThesaurus) {
		this.idThesaurus = idThesaurus;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}