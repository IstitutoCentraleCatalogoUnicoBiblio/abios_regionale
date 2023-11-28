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


/**
 * The persistent class for the fondi_speciali database table.
 * 
 */
@Entity
@Table(name="fondi_speciali")
public class FondiSpeciali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fondi_speciali", unique=true, nullable=false)
	private Integer idFondiSpeciali;

	@Column(name="catalogazione_inventario_url", length=255)
	private String catalogazioneInventarioUrl;

    @Lob()
	@Column(name="citazione_bibliografica")
	private String citazioneBibliografica;

	@Column(nullable=false, length=255)
	private String denominazione;

	@Column(length=255)
	private String descrizione;

	@Column(name="fondo_depositato")
	private Boolean fondoDepositato;

	//uni-directional many-to-one association to Dewey
    @ManyToOne
	@JoinColumn(name="id_dewey")
	private Dewey dewey;

	//bi-directional many-to-one association to FondiSpecialiCatalogazioneInventario
    @ManyToOne
	@JoinColumn(name="id_fondi_speciali_catalogazione_inventario", nullable=false)
	private FondiSpecialiCatalogazioneInventario fondiSpecialiCatalogazioneInventario;

    public FondiSpeciali() {
    }

	public Integer getIdFondiSpeciali() {
		return this.idFondiSpeciali;
	}

	public void setIdFondiSpeciali(Integer idFondiSpeciali) {
		this.idFondiSpeciali = idFondiSpeciali;
	}

	public String getCatalogazioneInventarioUrl() {
		return this.catalogazioneInventarioUrl;
	}

	public void setCatalogazioneInventarioUrl(String catalogazioneInventarioUrl) {
		this.catalogazioneInventarioUrl = catalogazioneInventarioUrl;
	}

	public String getCitazioneBibliografica() {
		return this.citazioneBibliografica;
	}

	public void setCitazioneBibliografica(String citazioneBibliografica) {
		this.citazioneBibliografica = citazioneBibliografica;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Boolean getFondoDepositato() {
		return this.fondoDepositato;
	}

	public void setFondoDepositato(Boolean fondoDepositato) {
		this.fondoDepositato = fondoDepositato;
	}

	public Dewey getDewey() {
		return this.dewey;
	}

	public void setDewey(Dewey dewey) {
		this.dewey = dewey;
	}
	
	public FondiSpecialiCatalogazioneInventario getFondiSpecialiCatalogazioneInventario() {
		return this.fondiSpecialiCatalogazioneInventario;
	}

	public void setFondiSpecialiCatalogazioneInventario(FondiSpecialiCatalogazioneInventario fondiSpecialiCatalogazioneInventario) {
		this.fondiSpecialiCatalogazioneInventario = fondiSpecialiCatalogazioneInventario;
	}
	
}