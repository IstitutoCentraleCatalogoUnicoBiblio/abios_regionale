package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sistemi_prestito_interbibliotecario database table.
 * 
 */
@Entity
@Table(name="sistemi_prestito_interbibliotecario")
public class SistemiPrestitoInterbibliotecario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sistemi_prestito_interbibliotecario", unique=true, nullable=false)
	private Integer idSistemiPrestitoInterbibliotecario;

	@Column(nullable=false, length=255)
	private String descrizione;

    public SistemiPrestitoInterbibliotecario() {
    }

	public Integer getIdSistemiPrestitoInterbibliotecario() {
		return this.idSistemiPrestitoInterbibliotecario;
	}

	public void setIdSistemiPrestitoInterbibliotecario(Integer idSistemiPrestitoInterbibliotecario) {
		this.idSistemiPrestitoInterbibliotecario = idSistemiPrestitoInterbibliotecario;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}