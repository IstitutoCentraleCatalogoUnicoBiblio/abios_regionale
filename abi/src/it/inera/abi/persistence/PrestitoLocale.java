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