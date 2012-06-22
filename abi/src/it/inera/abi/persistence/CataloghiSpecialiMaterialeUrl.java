package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cataloghi_speciali_materiale_url database table.
 * 
 */
@Entity
@Table(name="cataloghi_speciali_materiale_url")
public class CataloghiSpecialiMaterialeUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_speciali_materiale_url", unique=true, nullable=false)
	private Integer idCataloghiSpecialiMaterialeUrl;

	@Column(length=255)
	private String descrizione;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to PartecipaCataloghiSpecialiMateriale
    @ManyToOne
	@JoinColumn(name="id_cataloghi_speciali_materiale", nullable=false)
	private PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale;

    public CataloghiSpecialiMaterialeUrl() {
    }

	public Integer getIdCataloghiSpecialiMaterialeUrl() {
		return this.idCataloghiSpecialiMaterialeUrl;
	}

	public void setIdCataloghiSpecialiMaterialeUrl(Integer idCataloghiSpecialiMaterialeUrl) {
		this.idCataloghiSpecialiMaterialeUrl = idCataloghiSpecialiMaterialeUrl;
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

	public PartecipaCataloghiSpecialiMateriale getPartecipaCataloghiSpecialiMateriale() {
		return this.partecipaCataloghiSpecialiMateriale;
	}

	public void setPartecipaCataloghiSpecialiMateriale(PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale) {
		this.partecipaCataloghiSpecialiMateriale = partecipaCataloghiSpecialiMateriale;
	}
	
}