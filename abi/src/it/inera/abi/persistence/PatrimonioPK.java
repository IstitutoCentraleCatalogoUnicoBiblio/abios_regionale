package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the patrimonio database table.
 * 
 */
@Embeddable
public class PatrimonioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_patrimonio_specializzazione", unique=true, nullable=false)
	private Integer idPatrimonioSpecializzazione;

    public PatrimonioPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdPatrimonioSpecializzazione() {
		return this.idPatrimonioSpecializzazione;
	}
	public void setIdPatrimonioSpecializzazione(Integer idPatrimonioSpecializzazione) {
		this.idPatrimonioSpecializzazione = idPatrimonioSpecializzazione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PatrimonioPK)) {
			return false;
		}
		PatrimonioPK castOther = (PatrimonioPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idPatrimonioSpecializzazione.equals(castOther.idPatrimonioSpecializzazione);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idPatrimonioSpecializzazione.hashCode();
		
		return hash;
    }
}