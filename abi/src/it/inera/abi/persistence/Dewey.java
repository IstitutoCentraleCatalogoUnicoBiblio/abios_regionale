package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dewey database table.
 * 
 */
@Entity
@Table(name="dewey")
public class Dewey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_dewey", unique=true, nullable=false, length=6)
	private String idDewey;

	@Column(nullable=false, length=255)
	private String descrizione;

    public Dewey() {
    }

	public String getIdDewey() {
		return this.idDewey;
	}

	public void setIdDewey(String idDewey) {
		this.idDewey = idDewey;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}