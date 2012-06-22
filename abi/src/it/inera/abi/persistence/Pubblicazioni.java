package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pubblicazioni database table.
 * 
 */
@Entity
@Table(name="pubblicazioni")
public class Pubblicazioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pubblicazioni", unique=true, nullable=false)
	private Integer idPubblicazioni;

	@Column(nullable=false, length=255)
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public Pubblicazioni() {
    }

	public Integer getIdPubblicazioni() {
		return this.idPubblicazioni;
	}

	public void setIdPubblicazioni(Integer idPubblicazioni) {
		this.idPubblicazioni = idPubblicazioni;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}