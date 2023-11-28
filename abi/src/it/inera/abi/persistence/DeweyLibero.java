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
 * The persistent class for the dewey_libero database table.
 * 
 */
@Entity
@Table(name="dewey_libero")
public class DeweyLibero implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DeweyLiberoPK id;

	@Column(length=255)
	private String descrizione;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to Dewey
    @ManyToOne
	@JoinColumn(name="id_dewey", nullable=false, insertable=false, updatable=false)
	private Dewey dewey;

    public DeweyLibero() {
    }

	public DeweyLiberoPK getId() {
		return this.id;
	}

	public void setId(DeweyLiberoPK id) {
		this.id = id;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public Dewey getDewey() {
		return this.dewey;
	}

	public void setDewey(Dewey dewey) {
		this.dewey = dewey;
	}
	
}