package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cataloghi_collettivi_zona_tipo database table.
 * 
 */
@Entity
@Table(name="cataloghi_collettivi_zona_tipo")
public class CataloghiCollettiviZonaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_collettivi_zona_tipo", unique=true, nullable=false)
	private Integer idCataloghiCollettiviZonaTipo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public CataloghiCollettiviZonaTipo() {
    }

	public Integer getIdCataloghiCollettiviZonaTipo() {
		return this.idCataloghiCollettiviZonaTipo;
	}

	public void setIdCataloghiCollettiviZonaTipo(Integer idCataloghiCollettiviZonaTipo) {
		this.idCataloghiCollettiviZonaTipo = idCataloghiCollettiviZonaTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}