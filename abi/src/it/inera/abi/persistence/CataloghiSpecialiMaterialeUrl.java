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
 * The persistent class for the cataloghi_speciali_materiale_url database table.
 * 
 */
@Entity
@Table(name="cataloghi_speciali_materiale_url")
public class CataloghiSpecialiMaterialeUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cataloghi_speciali_materiale_url", unique=true, nullable=false)
	private Integer idCataloghiSpecialiMaterialeUrl;

	@Column(length=255)
	private String descrizione;

	@Column(length=255)
	private String url;

	//bi-directional many-to-one association to PartecipaCataloghiSpecialiMateriale
    @ManyToOne
	@JoinColumn(name="id_cataloghi_speciali_materiale", nullable=false)
	private PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale;

    public CataloghiSpecialiMaterialeUrl() {
    }

	public Integer getIdCataloghiSpecialiMaterialeUrl() {
		return this.idCataloghiSpecialiMaterialeUrl;
	}

	public void setIdCataloghiSpecialiMaterialeUrl(Integer idCataloghiSpecialiMaterialeUrl) {
		this.idCataloghiSpecialiMaterialeUrl = idCataloghiSpecialiMaterialeUrl;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PartecipaCataloghiSpecialiMateriale getPartecipaCataloghiSpecialiMateriale() {
		return this.partecipaCataloghiSpecialiMateriale;
	}

	public void setPartecipaCataloghiSpecialiMateriale(PartecipaCataloghiSpecialiMateriale partecipaCataloghiSpecialiMateriale) {
		this.partecipaCataloghiSpecialiMateriale = partecipaCataloghiSpecialiMateriale;
	}
	
}