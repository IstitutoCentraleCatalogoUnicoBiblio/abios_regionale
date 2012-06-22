package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orario_ufficiali database table.
 * 
 */
@Entity
@Table(name="orario_ufficiali")
public class OrarioUfficiali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orario_ufficiali", unique=true, nullable=false)
	private Integer idOrarioUfficiali;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date alle;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dalle;

	@Column(nullable=false)
	private Integer giorno;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public OrarioUfficiali() {
    }

	public Integer getIdOrarioUfficiali() {
		return this.idOrarioUfficiali;
	}

	public void setIdOrarioUfficiali(Integer idOrarioUfficiali) {
		this.idOrarioUfficiali = idOrarioUfficiali;
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