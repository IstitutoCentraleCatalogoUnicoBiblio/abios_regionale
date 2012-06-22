package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the destinazioni_sociali database table.
 * 
 */
@Embeddable
public class DestinazioniSocialiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_destinazioni_sociali_tipo", unique=true, nullable=false)
	private Integer idDestinazioniSocialiTipo;

    public DestinazioniSocialiPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdDestinazioniSocialiTipo() {
		return this.idDestinazioniSocialiTipo;
	}
	public void setIdDestinazioniSocialiTipo(Integer idDestinazioniSocialiTipo) {
		this.idDestinazioniSocialiTipo = idDestinazioniSocialiTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DestinazioniSocialiPK)) {
			return false;
		}
		DestinazioniSocialiPK castOther = (DestinazioniSocialiPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idDestinazioniSocialiTipo.equals(castOther.idDestinazioniSocialiTipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idDestinazioniSocialiTipo.hashCode();
		
		return hash;
    }
}