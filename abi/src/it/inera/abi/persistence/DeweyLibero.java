package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dewey_libero database table.
 * 
 */
@Entity
@Table(name="dewey_libero")
public class DeweyLibero implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DeweyLiberoPK id;

	@Column(length=255)
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to Dewey
    @ManyToOne
	@JoinColumn(name="id_dewey", nullable=false, insertable=false, updatable=false)
	private Dewey dewey;

    public DeweyLibero() {
    }

	public DeweyLiberoPK getId() {
		return this.id;
	}

	public void setId(DeweyLiberoPK id) {
		this.id = id;
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
	
	public Dewey getDewey() {
		return this.dewey;
	}

	public void setDewey(Dewey dewey) {
		this.dewey = dewey;
	}
	
}