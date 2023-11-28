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
 * The primary key class for the destinazioni_sociali database table.
 * 
 */
@Embeddable
public class DestinazioniSocialiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="id_destinazioni_sociali_tipo", unique=true, nullable=false)
	private Integer idDestinazioniSocialiTipo;

    public DestinazioniSocialiPK() {
    }
	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}
	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Integer getIdDestinazioniSocialiTipo() {
		return this.idDestinazioniSocialiTipo;
	}
	public void setIdDestinazioniSocialiTipo(Integer idDestinazioniSocialiTipo) {
		this.idDestinazioniSocialiTipo = idDestinazioniSocialiTipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DestinazioniSocialiPK)) {
			return false;
		}
		DestinazioniSocialiPK castOther = (DestinazioniSocialiPK)other;
		return 
			this.idBiblioteca.equals(castOther.idBiblioteca)
			&& this.idDestinazioniSocialiTipo.equals(castOther.idDestinazioniSocialiTipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idBiblioteca.hashCode();
		hash = hash * prime + this.idDestinazioniSocialiTipo.hashCode();
		
		return hash;
    }
}