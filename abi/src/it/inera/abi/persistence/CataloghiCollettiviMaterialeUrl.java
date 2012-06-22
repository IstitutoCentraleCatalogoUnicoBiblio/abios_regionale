package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cataloghi_collettivi_materiale_url database table.
 * 
 */
@Entity
@Table(name="cataloghi_collettivi_materiale_url")
public class CataloghiCollettiviMaterialeUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_collettivi_materiale_url", unique=true, nullable=false)
	private Integer idCataloghiCollettiviMaterialeUrl;

	@Column(length=255)
	private String descrizione;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to PartecipaCataloghiCollettiviMateriale
    @ManyToOne
	@JoinColumn(name="id_cataloghi_collettivi_materiale", nullable=false)
	private PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale;

    public CataloghiCollettiviMaterialeUrl() {
    }

	public Integer getIdCataloghiCollettiviMaterialeUrl() {
		return this.idCataloghiCollettiviMaterialeUrl;
	}

	public void setIdCataloghiCollettiviMaterialeUrl(Integer idCataloghiCollettiviMaterialeUrl) {
		this.idCataloghiCollettiviMaterialeUrl = idCataloghiCollettiviMaterialeUrl;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PartecipaCataloghiCollettiviMateriale getPartecipaCataloghiCollettiviMateriale() {
		return this.partecipaCataloghiCollettiviMateriale;
	}

	public void setPartecipaCataloghiCollettiviMateriale(PartecipaCataloghiCollettiviMateriale partecipaCataloghiCollettiviMateriale) {
		this.partecipaCataloghiCollettiviMateriale = partecipaCataloghiCollettiviMateriale;
	}
	
}