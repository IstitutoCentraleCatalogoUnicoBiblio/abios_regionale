package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the partecipa_cataloghi_speciali_materiale database table.
 * 
 */
@Entity
@Table(name="partecipa_cataloghi_speciali_materiale")
public class PartecipaCataloghiSpecialiMateriale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_speciali_materiale", unique=true, nullable=false)
	private Integer idCataloghiSpecialiMateriale;

	@Column(name="a_anno")
	private Integer aAnno;

	@Column(name="da_anno")
	private Integer daAnno;

	@Column(length=255)
	private String denominazione;

	@Column(name="descrizione_volume", length=255)
	private String descrizioneVolume;

	private Boolean microforme;

	@Column(name="percentuale_informatizzata")
	private Integer percentualeInformatizzata;

	@Column(name="percentuale_microforme")
	private Integer percentualeMicroforme;

	@Column(name="percentuale_schede")
	private Integer percentualeSchede;

	@Column(name="percentuale_volume")
	private Integer percentualeVolume;

	private Boolean schede;

	private Boolean volume;

	//bi-directional many-to-one association to CataloghiSpecialiMaterialeUrl
	@OneToMany(mappedBy="partecipaCataloghiSpecialiMateriale")
	private List<CataloghiSpecialiMaterialeUrl> cataloghiSpecialiMaterialeUrls;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to CataloghiSupportoDigitaleTipo
    @ManyToOne
	@JoinColumn(name="id_cataloghi_supporto_digitale_tipo", nullable=false)
	private CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo;

	//uni-directional many-to-one association to PatrimonioSpecializzazione
    @ManyToOne
	@JoinColumn(name="id_patrimonio_specializzazione", nullable=false)
	private PatrimonioSpecializzazione patrimonioSpecializzazione;

    public PartecipaCataloghiSpecialiMateriale() {
    }

	public Integer getIdCataloghiSpecialiMateriale() {
		return this.idCataloghiSpecialiMateriale;
	}

	public void setIdCataloghiSpecialiMateriale(Integer idCataloghiSpecialiMateriale) {
		this.idCataloghiSpecialiMateriale = idCataloghiSpecialiMateriale;
	}

	public Integer getAAnno() {
		return this.aAnno;
	}

	public void setAAnno(Integer aAnno) {
		this.aAnno = aAnno;
	}

	public Integer getDaAnno() {
		return this.daAnno;
	}

	public void setDaAnno(Integer daAnno) {
		this.daAnno = daAnno;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDescrizioneVolume() {
		return this.descrizioneVolume;
	}

	public void setDescrizioneVolume(String descrizioneVolume) {
		this.descrizioneVolume = descrizioneVolume;
	}

	public Boolean getMicroforme() {
		return this.microforme;
	}

	public void setMicroforme(Boolean microforme) {
		this.microforme = microforme;
	}

	public Integer getPercentualeInformatizzata() {
		return this.percentualeInformatizzata;
	}

	public void setPercentualeInformatizzata(Integer percentualeInformatizzata) {
		this.percentualeInformatizzata = percentualeInformatizzata;
	}

	public Integer getPercentualeMicroforme() {
		return this.percentualeMicroforme;
	}

	public void setPercentualeMicroforme(Integer percentualeMicroforme) {
		this.percentualeMicroforme = percentualeMicroforme;
	}

	public Integer getPercentualeSchede() {
		return this.percentualeSchede;
	}

	public void setPercentualeSchede(Integer percentualeSchede) {
		this.percentualeSchede = percentualeSchede;
	}

	public Integer getPercentualeVolume() {
		return this.percentualeVolume;
	}

	public void setPercentualeVolume(Integer percentualeVolume) {
		this.percentualeVolume = percentualeVolume;
	}

	public Boolean getSchede() {
		return this.schede;
	}

	public void setSchede(Boolean schede) {
		this.schede = schede;
	}

	public Boolean getVolume() {
		return this.volume;
	}

	public void setVolume(Boolean volume) {
		this.volume = volume;
	}

	public List<CataloghiSpecialiMaterialeUrl> getCataloghiSpecialiMaterialeUrls() {
		return this.cataloghiSpecialiMaterialeUrls;
	}

	public void setCataloghiSpecialiMaterialeUrls(List<CataloghiSpecialiMaterialeUrl> cataloghiSpecialiMaterialeUrls) {
		this.cataloghiSpecialiMaterialeUrls = cataloghiSpecialiMaterialeUrls;
	}
	
	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public CataloghiSupportoDigitaleTipo getCataloghiSupportoDigitaleTipo() {
		return this.cataloghiSupportoDigitaleTipo;
	}

	public void setCataloghiSupportoDigitaleTipo(CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo) {
		this.cataloghiSupportoDigitaleTipo = cataloghiSupportoDigitaleTipo;
	}
	
	public PatrimonioSpecializzazione getPatrimonioSpecializzazione() {
		return this.patrimonioSpecializzazione;
	}

	public void setPatrimonioSpecializzazione(PatrimonioSpecializzazione patrimonioSpecializzazione) {
		this.patrimonioSpecializzazione = patrimonioSpecializzazione;
	}
	
}