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
 * The persistent class for the regione database table.
 * 
 */
@Entity
@Table(name="regione")
public class Regione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_regione", unique=true, nullable=false)
	private Integer idRegione;

	@Column(nullable=false, length=100)
	private String denominazione;

	@Column(length=2)
	private String sigla;

	//uni-directional one-to-one association to DatiRegioniIstat
	@OneToOne
	@JoinColumn(name="id_regione", nullable=false, insertable=false, updatable=false)
	private DatiRegioniIstat datiRegioniIstat;

	//uni-directional many-to-one association to Stato
    @ManyToOne
	@JoinColumn(name="id_stato", nullable=false)
	private Stato stato;

    public Regione() {
    }

	public Integer getIdRegione() {
		return this.idRegione;
	}

	public void setIdRegione(Integer idRegione) {
		this.idRegione = idRegione;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public DatiRegioniIstat getDatiRegioniIstat() {
		return this.datiRegioniIstat;
	}

	public void setDatiRegioniIstat(DatiRegioniIstat datiRegioniIstat) {
		this.datiRegioniIstat = datiRegioniIstat;
	}
	
	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
}