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
import java.util.List;


/**
 * The persistent class for the fondi_speciali_catalogazione_inventario database table.
 * 
 */
@Entity
@Table(name="fondi_speciali_catalogazione_inventario")
public class FondiSpecialiCatalogazioneInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fondi_speciali_catalogazione_inventario", unique=true, nullable=false)
	private Integer idFondiSpecialiCatalogazioneInventario;

	@Column(nullable=false, length=255)
	private String descrizione;

	//bi-directional many-to-one association to FondiSpeciali
	@OneToMany(mappedBy="fondiSpecialiCatalogazioneInventario")
	private List<FondiSpeciali> fondiSpecialis;

    public FondiSpecialiCatalogazioneInventario() {
    }

	public Integer getIdFondiSpecialiCatalogazioneInventario() {
		return this.idFondiSpecialiCatalogazioneInventario;
	}

	public void setIdFondiSpecialiCatalogazioneInventario(Integer idFondiSpecialiCatalogazioneInventario) {
		this.idFondiSpecialiCatalogazioneInventario = idFondiSpecialiCatalogazioneInventario;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<FondiSpeciali> getFondiSpecialis() {
		return this.fondiSpecialis;
	}

	public void setFondiSpecialis(List<FondiSpeciali> fondiSpecialis) {
		this.fondiSpecialis = fondiSpecialis;
	}
	
}