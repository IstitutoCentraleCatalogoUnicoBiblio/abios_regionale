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
 * The persistent class for the spogli_bibliografici database table.
 * 
 */
@Entity
@Table(name="spogli_bibliografici")
public class SpogliBibliografici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spogli_bibliografici", unique=true, nullable=false)
	private Integer idSpogliBibliografici;

    @Lob()
	@Column(name="descrizione_bibliografica", nullable=false)
	private String descrizioneBibliografica;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

    public SpogliBibliografici() {
    }

	public Integer getIdSpogliBibliografici() {
		return this.idSpogliBibliografici;
	}

	public void setIdSpogliBibliografici(Integer idSpogliBibliografici) {
		this.idSpogliBibliografici = idSpogliBibliografici;
	}

	public String getDescrizioneBibliografica() {
		return this.descrizioneBibliografica;
	}

	public void setDescrizioneBibliografica(String descrizioneBibliografica) {
		this.descrizioneBibliografica = descrizioneBibliografica;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}