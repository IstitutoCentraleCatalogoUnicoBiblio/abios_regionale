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
 * The persistent class for the prestito_locale database table.
 * 
 */
@Entity
@Table(name="prestito_locale")
public class PrestitoLocale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_locale", unique=true, nullable=false)
	private Integer idPrestitoLocale;

	private Boolean automatizzato;

	@Column(name="durata_giorni")
	private Integer durataGiorni;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-many association to PrestitoLocaleMaterialeEscluso
    @ManyToMany
	@JoinTable(
		name="prestito_locale_has_materiale_escluso"
		, joinColumns={
			@JoinColumn(name="id_prestito_locale", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_prestito_locale_materiale_escluso", nullable=false)
			}
		)
	private List<PrestitoLocaleMaterialeEscluso> prestitoLocaleMaterialeEscluso;

	//uni-directional many-to-many association to PrestitoLocaleUtentiAmmessi
    @ManyToMany
	@JoinTable(
		name="prestito_locale_has_utenti_ammessi"
		, joinColumns={
			@JoinColumn(name="id_prestito_locale", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_prestito_locale_utenti_ammessi", nullable=false)
			}
		)
	private List<PrestitoLocaleUtentiAmmessi> prestitoLocaleUtentiAmmessis;

    public PrestitoLocale() {
    }

	public Integer getIdPrestitoLocale() {
		return this.idPrestitoLocale;
	}

	public void setIdPrestitoLocale(Integer idPrestitoLocale) {
		this.idPrestitoLocale = idPrestitoLocale;
	}

	public Boolean getAutomatizzato() {
		return this.automatizzato;
	}

	public void setAutomatizzato(Boolean automatizzato) {
		this.automatizzato = automatizzato;
	}

	public Integer getDurataGiorni() {
		return this.durataGiorni;
	}

	public void setDurataGiorni(Integer durataGiorni) {
		this.durataGiorni = durataGiorni;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public List<PrestitoLocaleMaterialeEscluso> getPrestitoLocaleMaterialeEscluso() {
		return this.prestitoLocaleMaterialeEscluso;
	}

	public void setPrestitoLocaleMaterialeEscluso(List<PrestitoLocaleMaterialeEscluso> prestitoLocaleMaterialeEscluso) {
		this.prestitoLocaleMaterialeEscluso = prestitoLocaleMaterialeEscluso;
	}
	
	public List<PrestitoLocaleUtentiAmmessi> getPrestitoLocaleUtentiAmmessis() {
		return this.prestitoLocaleUtentiAmmessis;
	}

	public void setPrestitoLocaleUtentiAmmessis(List<PrestitoLocaleUtentiAmmessi> prestitoLocaleUtentiAmmessis) {
		this.prestitoLocaleUtentiAmmessis = prestitoLocaleUtentiAmmessis;
	}
	
}