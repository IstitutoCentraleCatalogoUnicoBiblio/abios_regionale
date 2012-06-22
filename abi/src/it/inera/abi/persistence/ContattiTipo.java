package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contatti_tipo database table.
 * 
 */
@Entity
@Table(name="contatti_tipo")
public class ContattiTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contatti_tipo", unique=true, nullable=false)
	private Integer idContattiTipo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public ContattiTipo() {
    }

	public Integer getIdContattiTipo() {
		return this.idContattiTipo;
	}

	public void setIdContattiTipo(Integer idContattiTipo) {
		this.idContattiTipo = idContattiTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}