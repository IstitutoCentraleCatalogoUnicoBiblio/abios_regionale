package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the codici database table.
 * 
 */
@Entity
@Table(name="codici")
public class Codici implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CodiciPK id;

	@Column(nullable=false, length=100)
	private String valore;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//bi-directional many-to-one association to CodiciTipo
    @ManyToOne
	@JoinColumn(name="id_codici", nullable=false, insertable=false, updatable=false)
	private CodiciTipo codiciTipo;

    public Codici() {
    }

	public CodiciPK getId() {
		return this.id;
	}

	public void setId(CodiciPK id) {
		this.id = id;
	}
	
	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public CodiciTipo getCodiciTipo() {
		return this.codiciTipo;
	}

	public void setCodiciTipo(CodiciTipo codiciTipo) {
		this.codiciTipo = codiciTipo;
	}
	
}