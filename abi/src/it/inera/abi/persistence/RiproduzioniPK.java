package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the riproduzioni database table.
 * 
 */
@Embeddable
public class RiproduzioniPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_riproduzioni_tipo", unique=true, nullable=false)
	private Integer idRiproduzioniTipo;

    public RiproduzioniPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdRiproduzioniTipo() {
		return this.idRiproduzioniTipo;
	}
	public void setIdRiproduzioniTipo(Integer idRiproduzioniTipo) {
		this.idRiproduzioniTipo = idRiproduzioniTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RiproduzioniPK)) {
			return false;
		}
		RiproduzioniPK castOther = (RiproduzioniPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idRiproduzioniTipo.equals(castOther.idRiproduzioniTipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idRiproduzioniTipo.hashCode();
		
		return hash;
    }
}