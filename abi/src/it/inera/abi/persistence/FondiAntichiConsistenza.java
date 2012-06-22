package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fondi_antichi_consistenza database table.
 * 
 */
@Entity
@Table(name="fondi_antichi_consistenza")
public class FondiAntichiConsistenza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fondi_antichi_consistenza", unique=true, nullable=false)
	private Integer idFondiAntichiConsistenza;

	@Column(nullable=false, length=255)
	private String descrizione;

    public FondiAntichiConsistenza() {
    }

	public Integer getIdFondiAntichiConsistenza() {
		return this.idFondiAntichiConsistenza;
	}

	public void setIdFondiAntichiConsistenza(Integer idFondiAntichiConsistenza) {
		this.idFondiAntichiConsistenza = idFondiAntichiConsistenza;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}