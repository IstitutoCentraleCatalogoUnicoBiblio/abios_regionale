package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the depositi_legali database table.
 * 
 */
@Embeddable
public class DepositiLegaliPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_depositi_legali_tipo", unique=true, nullable=false)
	private Integer idDepositiLegaliTipo;

    public DepositiLegaliPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdDepositiLegaliTipo() {
		return this.idDepositiLegaliTipo;
	}
	public void setIdDepositiLegaliTipo(Integer idDepositiLegaliTipo) {
		this.idDepositiLegaliTipo = idDepositiLegaliTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DepositiLegaliPK)) {
			return false;
		}
		DepositiLegaliPK castOther = (DepositiLegaliPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idDepositiLegaliTipo.equals(castOther.idDepositiLegaliTipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idDepositiLegaliTipo.hashCode();
		
		return hash;
    }
}