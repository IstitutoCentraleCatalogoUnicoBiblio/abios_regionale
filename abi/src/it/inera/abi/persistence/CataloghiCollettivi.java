package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cataloghi_collettivi database table.
 * 
 */
@Entity
@Table(name="cataloghi_collettivi")
public class CataloghiCollettivi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_collettivi", unique=true, nullable=false)
	private Integer idCataloghiCollettivi;

	@Column(length=255)
	private String descrizione;

	@Column(name="dettaglio_zona", length=50)
	private String dettaglioZona;

	//uni-directional many-to-one association to CataloghiCollettiviZonaTipo
    @ManyToOne
	@JoinColumn(name="id_cataloghi_collettivi_zona_tipo", nullable=false)
	private CataloghiCollettiviZonaTipo cataloghiCollettiviZonaTipo;

	//bi-directional many-to-one association to PartecipaCataloghiCollettiviMateriale
	@OneToMany(mappedBy="cataloghiCollettivi")
	private List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettiviMateriales;

    public CataloghiCollettivi() {
    }

	public Integer getIdCataloghiCollettivi() {
		return this.idCataloghiCollettivi;
	}

	public void setIdCataloghiCollettivi(Integer idCataloghiCollettivi) {
		this.idCataloghiCollettivi = idCataloghiCollettivi;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDettaglioZona() {
		return this.dettaglioZona;
	}

	public void setDettaglioZona(String dettaglioZona) {
		this.dettaglioZona = dettaglioZona;
	}

	public CataloghiCollettiviZonaTipo getCataloghiCollettiviZonaTipo() {
		return this.cataloghiCollettiviZonaTipo;
	}

	public void setCataloghiCollettiviZonaTipo(CataloghiCollettiviZonaTipo cataloghiCollettiviZonaTipo) {
		this.cataloghiCollettiviZonaTipo = cataloghiCollettiviZonaTipo;
	}
	
	public List<PartecipaCataloghiCollettiviMateriale> getPartecipaCataloghiCollettiviMateriales() {
		return this.partecipaCataloghiCollettiviMateriales;
	}

	public void setPartecipaCataloghiCollettiviMateriales(List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettiviMateriales) {
		this.partecipaCataloghiCollettiviMateriales = partecipaCataloghiCollettiviMateriales;
	}
	
}