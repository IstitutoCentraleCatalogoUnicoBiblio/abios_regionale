package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ente_tipologia_amministrativa database table.
 * 
 */
@Entity
@Table(name="ente_tipologia_amministrativa")
public class EnteTipologiaAmministrativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ente_tipologia_amministrativa", unique=true, nullable=false)
	private Integer idEnteTipologiaAmministrativa;

	@Column(nullable=false, length=255)
	private String descrizione;

    public EnteTipologiaAmministrativa() {
    }

	public Integer getIdEnteTipologiaAmministrativa() {
		return this.idEnteTipologiaAmministrativa;
	}

	public void setIdEnteTipologiaAmministrativa(Integer idEnteTipologiaAmministrativa) {
		this.idEnteTipologiaAmministrativa = idEnteTipologiaAmministrativa;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}