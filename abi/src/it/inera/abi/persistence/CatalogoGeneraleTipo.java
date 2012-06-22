package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catalogo_generale_tipo database table.
 * 
 */
@Entity
@Table(name="catalogo_generale_tipo")
public class CatalogoGeneraleTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_catalogo_generale_tipo", unique=true, nullable=false)
	private Integer idCatalogoGeneraleTipo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public CatalogoGeneraleTipo() {
    }

	public Integer getIdCatalogoGeneraleTipo() {
		return this.idCatalogoGeneraleTipo;
	}

	public void setIdCatalogoGeneraleTipo(Integer idCatalogoGeneraleTipo) {
		this.idCatalogoGeneraleTipo = idCatalogoGeneraleTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}