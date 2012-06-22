package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accesso_modalita database table.
 * 
 */
@Entity
@Table(name="accesso_modalita")
public class AccessoModalita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_accesso_modalita", unique=true, nullable=false)
	private Integer idAccessoModalita;

	@Column(nullable=false, length=255)
	private String descrizione;

    public AccessoModalita() {
    }

	public Integer getIdAccessoModalita() {
		return this.idAccessoModalita;
	}

	public void setIdAccessoModalita(Integer idAccessoModalita) {
		this.idAccessoModalita = idAccessoModalita;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}