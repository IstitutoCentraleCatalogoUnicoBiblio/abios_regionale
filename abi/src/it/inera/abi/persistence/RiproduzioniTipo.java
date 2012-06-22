package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the riproduzioni_tipo database table.
 * 
 */
@Entity
@Table(name="riproduzioni_tipo")
public class RiproduzioniTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_riproduzioni_tipo", unique=true, nullable=false)
	private Integer idRiproduzioniTipo;

	@Column(length=255)
	private String descrizione;

    public RiproduzioniTipo() {
    }

	public Integer getIdRiproduzioniTipo() {
		return this.idRiproduzioniTipo;
	}

	public void setIdRiproduzioniTipo(Integer idRiproduzioniTipo) {
		this.idRiproduzioniTipo = idRiproduzioniTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}