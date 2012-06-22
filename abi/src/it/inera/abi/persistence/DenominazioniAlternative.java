package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the denominazioni_alternative database table.
 * 
 */
@Entity
@Table(name="denominazioni_alternative")
public class DenominazioniAlternative implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_denominazioni_alternative", unique=true, nullable=false)
	private Integer idDenominazioniAlternative;

	@Column(length=255)
	private String denominazione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public DenominazioniAlternative() {
    }

	public Integer getIdDenominazioniAlternative() {
		return this.idDenominazioniAlternative;
	}

	public void setIdDenominazioniAlternative(Integer idDenominazioniAlternative) {
		this.idDenominazioniAlternative = idDenominazioniAlternative;
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