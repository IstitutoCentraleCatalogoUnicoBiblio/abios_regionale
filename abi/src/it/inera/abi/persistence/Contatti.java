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
 * The persistent class for the contatti database table.
 * 
 */
@Entity
@Table(name="contatti")
public class Contatti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contatti", unique=true, nullable=false)
	private Integer idContatti;

	@Column(length=100)
	private String note;

	@Column(nullable=false, length=255)
	private String valore;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to ContattiTipo
    @ManyToOne
	@JoinColumn(name="id_contatti_tipi", nullable=false)
	private ContattiTipo contattiTipo;

    public Contatti() {
    }

	public Integer getIdContatti() {
		return this.idContatti;
	}

	public void setIdContatti(Integer idContatti) {
		this.idContatti = idContatti;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public ContattiTipo getContattiTipo() {
		return this.contattiTipo;
	}

	public void setContattiTipo(ContattiTipo contattiTipo) {
		this.contattiTipo = contattiTipo;
	}
	
}