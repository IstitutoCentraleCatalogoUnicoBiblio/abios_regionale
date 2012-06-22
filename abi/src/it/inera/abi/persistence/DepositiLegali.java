package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the depositi_legali database table.
 * 
 */
@Entity
@Table(name="depositi_legali")
public class DepositiLegali implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepositiLegaliPK id;

	@Column(name="da_anno", length=10)
	private String daAnno;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to DepositiLegaliTipo
    @ManyToOne
	@JoinColumn(name="id_depositi_legali_tipo", nullable=false, insertable=false, updatable=false)
	private DepositiLegaliTipo depositiLegaliTipo;

    public DepositiLegali() {
    }

	public DepositiLegaliPK getId() {
		return this.id;
	}

	public void setId(DepositiLegaliPK id) {
		this.id = id;
	}
	
	public String getDaAnno() {
		return this.daAnno;
	}

	public void setDaAnno(String daAnno) {
		this.daAnno = daAnno;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public DepositiLegaliTipo getDepositiLegaliTipo() {
		return this.depositiLegaliTipo;
	}

	public void setDepositiLegaliTipo(DepositiLegaliTipo depositiLegaliTipo) {
		this.depositiLegaliTipo = depositiLegaliTipo;
	}
	
}