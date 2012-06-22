package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipologia_funzionale database table.
 * 
 */
@Entity
@Table(name="tipologia_funzionale")
public class TipologiaFunzionale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipologia_funzionale", unique=true, nullable=false)
	private Integer idTipologiaFunzionale;

	@Column(nullable=false, length=255)
	private String descrizione;

    public TipologiaFunzionale() {
    }

	public Integer getIdTipologiaFunzionale() {
		return this.idTipologiaFunzionale;
	}

	public void setIdTipologiaFunzionale(Integer idTipologiaFunzionale) {
		this.idTipologiaFunzionale = idTipologiaFunzionale;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}