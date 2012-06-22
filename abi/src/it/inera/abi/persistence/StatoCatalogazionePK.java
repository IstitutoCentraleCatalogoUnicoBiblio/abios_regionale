package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the stato_catalogazione database table.
 * 
 */
@Embeddable
public class StatoCatalogazionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_stato_catalogazione", unique=true, nullable=false)
	private Integer idStatoCatalogazione;

    public StatoCatalogazionePK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdStatoCatalogazione() {
		return this.idStatoCatalogazione;
	}
	public void setIdStatoCatalogazione(Integer idStatoCatalogazione) {
		this.idStatoCatalogazione = idStatoCatalogazione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StatoCatalogazionePK)) {
			return false;
		}
		StatoCatalogazionePK castOther = (StatoCatalogazionePK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idStatoCatalogazione.equals(castOther.idStatoCatalogazione);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idStatoCatalogazione.hashCode();
		
		return hash;
    }
}