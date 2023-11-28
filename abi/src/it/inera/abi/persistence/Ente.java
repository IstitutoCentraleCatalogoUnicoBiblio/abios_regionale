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
 * The persistent class for the ente database table.
 * 
 */
@Entity
@Table(name="ente")
public class Ente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ente", unique=true, nullable=false)
	private Integer idEnte;

	@Column(name="asia_asip", length=11)
	private String asiaAsip;

	@Column(name="codice_fiscale", length=16)
	private String codiceFiscale;

	@Column(length=255)
	private String denominazione;

	@Column(name="partita_iva", length=11)
	private String partitaIva;

	//uni-directional many-to-one association to EnteTipologiaAmministrativa
    @ManyToOne
	@JoinColumn(name="id_ente_tipologia_amministrativa", nullable=false)
	private EnteTipologiaAmministrativa enteTipologiaAmministrativa;

	//uni-directional many-to-one association to Stato
    @ManyToOne
	@JoinColumn(name="id_stato", nullable=false)
	private Stato stato;

    public Ente() {
    }

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getAsiaAsip() {
		return this.asiaAsip;
	}

	public void setAsiaAsip(String asiaAsip) {
		this.asiaAsip = asiaAsip;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public EnteTipologiaAmministrativa getEnteTipologiaAmministrativa() {
		return this.enteTipologiaAmministrativa;
	}

	public void setEnteTipologiaAmministrativa(EnteTipologiaAmministrativa enteTipologiaAmministrativa) {
		this.enteTipologiaAmministrativa = enteTipologiaAmministrativa;
	}
	
	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
}