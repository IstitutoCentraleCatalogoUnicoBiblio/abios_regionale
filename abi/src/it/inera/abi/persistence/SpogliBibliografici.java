package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spogli_bibliografici database table.
 * 
 */
@Entity
@Table(name="spogli_bibliografici")
public class SpogliBibliografici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spogli_bibliografici", unique=true, nullable=false)
	private Integer idSpogliBibliografici;

    @Lob()
	@Column(name="descrizione_bibliografica", nullable=false)
	private String descrizioneBibliografica;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public SpogliBibliografici() {
    }

	public Integer getIdSpogliBibliografici() {
		return this.idSpogliBibliografici;
	}

	public void setIdSpogliBibliografici(Integer idSpogliBibliografici) {
		this.idSpogliBibliografici = idSpogliBibliografici;
	}

	public String getDescrizioneBibliografica() {
		return this.descrizioneBibliografica;
	}

	public void setDescrizioneBibliografica(String descrizioneBibliografica) {
		this.descrizioneBibliografica = descrizioneBibliografica;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}