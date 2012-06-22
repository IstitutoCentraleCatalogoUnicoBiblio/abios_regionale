package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the norme_catalogazione database table.
 * 
 */
@Entity
@Table(name="norme_catalogazione")
public class NormeCatalogazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_norme_catalogazione", unique=true, nullable=false)
	private Integer idNormeCatalogazione;

	@Column(nullable=false, length=255)
	private String descrizione;

    public NormeCatalogazione() {
    }

	public Integer getIdNormeCatalogazione() {
		return this.idNormeCatalogazione;
	}

	public void setIdNormeCatalogazione(Integer idNormeCatalogazione) {
		this.idNormeCatalogazione = idNormeCatalogazione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}