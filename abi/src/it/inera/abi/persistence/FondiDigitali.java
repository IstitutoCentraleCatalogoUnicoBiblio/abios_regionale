package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fondi_digitali database table.
 * 
 */
@Entity
@Table(name="fondi_digitali")
public class FondiDigitali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fondi_digitali", unique=true, nullable=false)
	private Integer idFondiDigitali;

	@Column(length=255)
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public FondiDigitali() {
    }

	public Integer getIdFondiDigitali() {
		return this.idFondiDigitali;
	}

	public void setIdFondiDigitali(Integer idFondiDigitali) {
		this.idFondiDigitali = idFondiDigitali;
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