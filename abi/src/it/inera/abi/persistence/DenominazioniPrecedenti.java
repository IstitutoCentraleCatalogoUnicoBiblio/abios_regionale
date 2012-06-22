package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the denominazioni_precedenti database table.
 * 
 */
@Entity
@Table(name="denominazioni_precedenti")
public class DenominazioniPrecedenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_denominazioni_precedenti", unique=true, nullable=false)
	private Integer idDenominazioniPrecedenti;

	@Column(length=255)
	private String denominazione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public DenominazioniPrecedenti() {
    }

	public Integer getIdDenominazioniPrecedenti() {
		return this.idDenominazioniPrecedenti;
	}

	public void setIdDenominazioniPrecedenti(Integer idDenominazioniPrecedenti) {
		this.idDenominazioniPrecedenti = idDenominazioniPrecedenti;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}