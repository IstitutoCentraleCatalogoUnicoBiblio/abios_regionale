package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patrimonio database table.
 * 
 */
@Entity
@Table(name="patrimonio")
public class Patrimonio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PatrimonioPK id;

	@Column(nullable=false)
	private Integer quantita;

	@Column(name="quantita_ultimo_anno", nullable=false)
	private Integer quantitaUltimoAnno;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to PatrimonioSpecializzazione
    @ManyToOne
	@JoinColumn(name="id_patrimonio_specializzazione", nullable=false, insertable=false, updatable=false)
	private PatrimonioSpecializzazione patrimonioSpecializzazione;

    public Patrimonio() {
    }

	public PatrimonioPK getId() {
		return this.id;
	}

	public void setId(PatrimonioPK id) {
		this.id = id;
	}
	
	public Integer getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Integer getQuantitaUltimoAnno() {
		return this.quantitaUltimoAnno;
	}

	public void setQuantitaUltimoAnno(Integer quantitaUltimoAnno) {
		this.quantitaUltimoAnno = quantitaUltimoAnno;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public PatrimonioSpecializzazione getPatrimonioSpecializzazione() {
		return this.patrimonioSpecializzazione;
	}

	public void setPatrimonioSpecializzazione(PatrimonioSpecializzazione patrimonioSpecializzazione) {
		this.patrimonioSpecializzazione = patrimonioSpecializzazione;
	}
	
}