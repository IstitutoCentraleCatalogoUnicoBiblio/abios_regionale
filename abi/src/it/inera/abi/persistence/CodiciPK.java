package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the codici database table.
 * 
 */
@Embeddable
public class CodiciPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_codici", unique=true, nullable=false)
	private Integer idCodici;

    public CodiciPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdCodici() {
		return this.idCodici;
	}
	public void setIdCodici(Integer idCodici) {
		this.idCodici = idCodici;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CodiciPK)) {
			return false;
		}
		CodiciPK castOther = (CodiciPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idCodici.equals(castOther.idCodici);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idCodici.hashCode();
		
		return hash;
    }
}