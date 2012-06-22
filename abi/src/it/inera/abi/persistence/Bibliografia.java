package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bibliografia database table.
 * 
 */
@Entity
@Table(name="bibliografia")
public class Bibliografia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bibliografia", unique=true, nullable=false)
	private Integer idBibliografia;

    @Lob()
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public Bibliografia() {
    }

	public Integer getIdBibliografia() {
		return this.idBibliografia;
	}

	public void setIdBibliografia(Integer idBibliografia) {
		this.idBibliografia = idBibliografia;
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