package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the riproduzioni database table.
 * 
 */
@Entity
@Table(name="riproduzioni")
public class Riproduzioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RiproduzioniPK id;

	private Boolean internazionale;

	private Boolean locale;

	private Boolean nazionale;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to RiproduzioniTipo
    @ManyToOne
	@JoinColumn(name="id_riproduzioni_tipo", nullable=false, insertable=false, updatable=false)
	private RiproduzioniTipo riproduzioniTipo;

    public Riproduzioni() {
    }

	public RiproduzioniPK getId() {
		return this.id;
	}

	public void setId(RiproduzioniPK id) {
		this.id = id;
	}
	
	public Boolean getInternazionale() {
		return this.internazionale;
	}

	public void setInternazionale(Boolean internazionale) {
		this.internazionale = internazionale;
	}

	public Boolean getLocale() {
		return this.locale;
	}

	public void setLocale(Boolean locale) {
		this.locale = locale;
	}

	public Boolean getNazionale() {
		return this.nazionale;
	}

	public void setNazionale(Boolean nazionale) {
		this.nazionale = nazionale;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public RiproduzioniTipo getRiproduzioniTipo() {
		return this.riproduzioniTipo;
	}

	public void setRiproduzioniTipo(RiproduzioniTipo riproduzioniTipo) {
		this.riproduzioniTipo = riproduzioniTipo;
	}
	
}