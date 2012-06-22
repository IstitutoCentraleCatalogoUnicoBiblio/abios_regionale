package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cataloghi_supporto_digitale_tipo database table.
 * 
 */
@Entity
@Table(name="cataloghi_supporto_digitale_tipo")
public class CataloghiSupportoDigitaleTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_supporto_digitale_tipo", unique=true, nullable=false)
	private Integer idCataloghiSupportoDigitaleTipo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public CataloghiSupportoDigitaleTipo() {
    }

	public Integer getIdCataloghiSupportoDigitaleTipo() {
		return this.idCataloghiSupportoDigitaleTipo;
	}

	public void setIdCataloghiSupportoDigitaleTipo(Integer idCataloghiSupportoDigitaleTipo) {
		this.idCataloghiSupportoDigitaleTipo = idCataloghiSupportoDigitaleTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}