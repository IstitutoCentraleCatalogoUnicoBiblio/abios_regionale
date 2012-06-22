package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cataloghi_generali_url database table.
 * 
 */
@Entity
@Table(name="cataloghi_generali_url")
public class CataloghiGeneraliUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_generali_url", unique=true, nullable=false)
	private Integer idCataloghiGeneraliUrl;

	@Column(length=255)
	private String descrizione;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to PartecipaCataloghiGenerali
    @ManyToOne
	@JoinColumn(name="id_cataloghi_generali", nullable=false)
	private PartecipaCataloghiGenerali partecipaCataloghiGenerali;

    public CataloghiGeneraliUrl() {
    }

	public Integer getIdCataloghiGeneraliUrl() {
		return this.idCataloghiGeneraliUrl;
	}

	public void setIdCataloghiGeneraliUrl(Integer idCataloghiGeneraliUrl) {
		this.idCataloghiGeneraliUrl = idCataloghiGeneraliUrl;
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

	public PartecipaCataloghiGenerali getPartecipaCataloghiGenerali() {
		return this.partecipaCataloghiGenerali;
	}

	public void setPartecipaCataloghiGenerali(PartecipaCataloghiGenerali partecipaCataloghiGenerali) {
		this.partecipaCataloghiGenerali = partecipaCataloghiGenerali;
	}
	
}