package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sistemi_biblioteche database table.
 * 
 */
@Entity
@Table(name="sistemi_biblioteche")
public class SistemiBiblioteche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sistemi_biblioteche", unique=true, nullable=false)
	private Integer idSistemiBiblioteche;

	@Column(length=255)
	private String descrizione;

    public SistemiBiblioteche() {
    }

	public Integer getIdSistemiBiblioteche() {
		return this.idSistemiBiblioteche;
	}

	public void setIdSistemiBiblioteche(Integer idSistemiBiblioteche) {
		this.idSistemiBiblioteche = idSistemiBiblioteche;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}