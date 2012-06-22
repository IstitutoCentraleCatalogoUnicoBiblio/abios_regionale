package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profili database table.
 * 
 */
@Entity
@Table(name="profili")
public class Profili implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_profili", unique=true, nullable=false)
	private Integer idProfili;

	@Column(length=255)
	private String denominazione;

    public Profili() {
    }

	public Integer getIdProfili() {
		return this.idProfili;
	}

	public void setIdProfili(Integer idProfili) {
		this.idProfili = idProfili;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

}