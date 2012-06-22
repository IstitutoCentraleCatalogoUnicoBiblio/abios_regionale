package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ente_obiettivo database table.
 * 
 */
@Entity
@Table(name="ente_obiettivo")
public class EnteObiettivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ente_obiettivo", unique=true, nullable=false)
	private Integer idEnteObiettivo;

	@Column(nullable=false, length=100)
	private String descrizione;

    public EnteObiettivo() {
    }

	public Integer getIdEnteObiettivo() {
		return this.idEnteObiettivo;
	}

	public void setIdEnteObiettivo(Integer idEnteObiettivo) {
		this.idEnteObiettivo = idEnteObiettivo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}