package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orario_chiusure database table.
 * 
 */
@Entity
@Table(name="orario_chiusure")
public class OrarioChiusure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_orario_chiusure", unique=true, nullable=false)
	private Integer idOrarioChiusure;

	@Column(length=255)
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public OrarioChiusure() {
    }

	public Integer getIdOrarioChiusure() {
		return this.idOrarioChiusure;
	}

	public void setIdOrarioChiusure(Integer idOrarioChiusure) {
		this.idOrarioChiusure = idOrarioChiusure;
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