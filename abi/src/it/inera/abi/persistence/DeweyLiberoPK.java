package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dewey_libero database table.
 * 
 */
@Embeddable
public class DeweyLiberoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_dewey", unique=true, nullable=false, length=6)
	private String idDewey;

    public DeweyLiberoPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public String getIdDewey() {
		return this.idDewey;
	}
	public void setIdDewey(String idDewey) {
		this.idDewey = idDewey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DeweyLiberoPK)) {
			return false;
		}
		DeweyLiberoPK castOther = (DeweyLiberoPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idDewey.equals(castOther.idDewey);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idDewey.hashCode();
		
		return hash;
    }
}