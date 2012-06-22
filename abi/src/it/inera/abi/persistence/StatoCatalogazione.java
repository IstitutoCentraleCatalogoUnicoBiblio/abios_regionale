package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato_catalogazione database table.
 * 
 */
@Entity
@Table(name="stato_catalogazione")
public class StatoCatalogazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StatoCatalogazionePK id;

	//uni-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca_target")
	private Biblioteca bibliotecaTarget;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to StatoCatalogazioneTipo
    @ManyToOne
	@JoinColumn(name="id_stato_catalogazione", nullable=false, insertable=false, updatable=false)
	private StatoCatalogazioneTipo statoCatalogazioneTipo;

    public StatoCatalogazione() {
    }

	public StatoCatalogazionePK getId() {
		return this.id;
	}

	public void setId(StatoCatalogazionePK id) {
		this.id = id;
	}
	
	public Biblioteca getBibliotecaTarget() {
		return this.bibliotecaTarget;
	}

	public void setBibliotecaTarget(Biblioteca bibliotecaTarget) {
		this.bibliotecaTarget = bibliotecaTarget;
	}
	
	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public StatoCatalogazioneTipo getStatoCatalogazioneTipo() {
		return this.statoCatalogazioneTipo;
	}

	public void setStatoCatalogazioneTipo(StatoCatalogazioneTipo statoCatalogazioneTipo) {
		this.statoCatalogazioneTipo = statoCatalogazioneTipo;
	}
	
}