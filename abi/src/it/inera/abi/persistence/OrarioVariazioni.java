package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orario_variazioni database table.
 * 
 */
@Entity
@Table(name="orario_variazioni")
public class OrarioVariazioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orario_variazioni", unique=true, nullable=false)
	private Integer idOrarioVariazioni;

    @Temporal( TemporalType.TIMESTAMP)
	private Date alle;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dalle;

	@Column(nullable=false, length=255)
	private String descrizione;

	private Integer giorno;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public OrarioVariazioni() {
    }

	public Integer getIdOrarioVariazioni() {
		return this.idOrarioVariazioni;
	}

	public void setIdOrarioVariazioni(Integer idOrarioVariazioni) {
		this.idOrarioVariazioni = idOrarioVariazioni;
	}

	public Date getAlle() {
		return this.alle;
	}

	public void setAlle(Date alle) {
		this.alle = alle;
	}

	public Date getDalle() {
		return this.dalle;
	}

	public void setDalle(Date dalle) {
		this.dalle = dalle;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getGiorno() {
		return this.giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}