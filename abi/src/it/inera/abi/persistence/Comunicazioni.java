package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comunicazioni database table.
 * 
 */
@Entity
@Table(name="comunicazioni")
public class Comunicazioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_comunicazioni", unique=true, nullable=false)
	private Integer idComunicazioni;

    @Lob()
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public Comunicazioni() {
    }

	public Integer getIdComunicazioni() {
		return this.idComunicazioni;
	}

	public void setIdComunicazioni(Integer idComunicazioni) {
		this.idComunicazioni = idComunicazioni;
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