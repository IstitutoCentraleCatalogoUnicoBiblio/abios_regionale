package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prestito_locale_materiale_escluso database table.
 * 
 */
@Entity
@Table(name="prestito_locale_materiale_escluso")
public class PrestitoLocaleMaterialeEscluso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_locale_materiale_escluso", unique=true, nullable=false)
	private Integer idPrestitoLocaleMaterialeEscluso;

	@Column(nullable=false, length=255)
	private String descrizione;

    public PrestitoLocaleMaterialeEscluso() {
    }

	public Integer getIdPrestitoLocaleMaterialeEscluso() {
		return this.idPrestitoLocaleMaterialeEscluso;
	}

	public void setIdPrestitoLocaleMaterialeEscluso(Integer idPrestitoLocaleMaterialeEscluso) {
		this.idPrestitoLocaleMaterialeEscluso = idPrestitoLocaleMaterialeEscluso;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}