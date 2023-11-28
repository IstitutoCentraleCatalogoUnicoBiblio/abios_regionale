/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the partecipa_cataloghi_generali database table.
 * 
 */
@Entity
@Table(name="partecipa_cataloghi_generali")
public class PartecipaCataloghiGenerali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_generali", unique=true, nullable=false)
	private Integer idCataloghiGenerali;

	@Column(name="a_anno")
	private Integer aAnno;

	@Column(name="da_anno")
	private Integer daAnno;

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

	//bi-directional many-to-one association to CataloghiGeneraliUrl
	@OneToMany(mappedBy="partecipaCataloghiGenerali")
	private List<CataloghiGeneraliUrl> cataloghiGeneraliUrls;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to CataloghiSupportoDigitaleTipo
    @ManyToOne
	@JoinColumn(name="id_cataloghi_supporto_digitale_tipo", nullable=false)
	private CataloghiSupportoDigitaleTipo cataloghiSupportoDigitaleTipo;

	//uni-directional many-to-one association to CatalogoGeneraleTipo
    @ManyToOne
	@JoinColumn(name="id_catalogo_generale_tipo", nullable=false)
	private CatalogoGeneraleTipo catalogoGeneraleTipo;

    public PartecipaCataloghiGenerali() {
    }

	public Integer getIdCataloghiGenerali() {
		return this.idCataloghiGenerali;
	}

	public void setIdCataloghiGenerali(Integer idCataloghiGenerali) {
		this.idCataloghiGenerali = idCataloghiGenerali;
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

	public List<CataloghiGeneraliUrl> getCataloghiGeneraliUrls() {
		return this.cataloghiGeneraliUrls;
	}

	public void setCataloghiGeneraliUrls(List<CataloghiGeneraliUrl> cataloghiGeneraliUrls) {
		this.cataloghiGeneraliUrls = cataloghiGeneraliUrls;
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
	
	public CatalogoGeneraleTipo getCatalogoGeneraleTipo() {
		return this.catalogoGeneraleTipo;
	}

	public void setCatalogoGeneraleTipo(CatalogoGeneraleTipo catalogoGeneraleTipo) {
		this.catalogoGeneraleTipo = catalogoGeneraleTipo;
	}
	
}