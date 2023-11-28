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
 * The persistent class for the prestito_interbibliotecario database table.
 * 
 */
@Entity
@Table(name="prestito_interbibliotecario")
public class PrestitoInterbibliotecario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_interbibliotecario", unique=true, nullable=false)
	private Integer idPrestitoInterbibliotecario;

	private Boolean internazionale;

	private Boolean nazionale;

	//uni-directional many-to-one association to PrestitoInterbibliotecarioModo
    @ManyToOne
	@JoinColumn(name="id_prestito_interbibliotecario_modo", nullable=false)
	private PrestitoInterbibliotecarioModo prestitoInterbibliotecarioModo;

    public PrestitoInterbibliotecario() {
    }

	public Integer getIdPrestitoInterbibliotecario() {
		return this.idPrestitoInterbibliotecario;
	}

	public void setIdPrestitoInterbibliotecario(Integer idPrestitoInterbibliotecario) {
		this.idPrestitoInterbibliotecario = idPrestitoInterbibliotecario;
	}

	public Boolean getInternazionale() {
		return this.internazionale;
	}

	public void setInternazionale(Boolean internazionale) {
		this.internazionale = internazionale;
	}

	public Boolean getNazionale() {
		return this.nazionale;
	}

	public void setNazionale(Boolean nazionale) {
		this.nazionale = nazionale;
	}

	public PrestitoInterbibliotecarioModo getPrestitoInterbibliotecarioModo() {
		return this.prestitoInterbibliotecarioModo;
	}

	public void setPrestitoInterbibliotecarioModo(PrestitoInterbibliotecarioModo prestitoInterbibliotecarioModo) {
		this.prestitoInterbibliotecarioModo = prestitoInterbibliotecarioModo;
	}
	
}