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
 * The persistent class for the geolocalizzazione database table.
 * 
 */
@Entity
@Table(name="geolocalizzazione")
public class Geolocalizzazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

    @Lob()
	private String error;

	private Double latitudine;

	private Double longitudine;

	@Column(length=50)
	private String precisione;

    @Lob()
	private String warning;

    public Geolocalizzazione() {
    }

	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Double getLatitudine() {
		return this.latitudine;
	}

	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}

	public Double getLongitudine() {
		return this.longitudine;
	}

	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}

	public String getPrecisione() {
		return this.precisione;
	}

	public void setPrecisione(String precisione) {
		this.precisione = precisione;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}