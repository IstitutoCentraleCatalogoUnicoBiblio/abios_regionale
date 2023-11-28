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
 * The persistent class for the patrimonio_specializzazione database table.
 * 
 */
@Entity
@Table(name="patrimonio_specializzazione")
public class PatrimonioSpecializzazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_patrimonio_specializzazione", unique=true, nullable=false)
	private Integer idPatrimonioSpecializzazione;

    @Lob()
	private String descrizione;

	@Column(name="id_sort")
	private Integer idSort;

	//uni-directional many-to-one association to PatrimonioSpecializzazioneCategoria
    @ManyToOne
	@JoinColumn(name="id_patrimonio_specializzazione_categoria", nullable=false)
	private PatrimonioSpecializzazioneCategoria patrimonioSpecializzazioneCategoria;

    public PatrimonioSpecializzazione() {
    }

	public Integer getIdPatrimonioSpecializzazione() {
		return this.idPatrimonioSpecializzazione;
	}

	public void setIdPatrimonioSpecializzazione(Integer idPatrimonioSpecializzazione) {
		this.idPatrimonioSpecializzazione = idPatrimonioSpecializzazione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getIdSort() {
		return this.idSort;
	}

	public void setIdSort(Integer idSort) {
		this.idSort = idSort;
	}

	public PatrimonioSpecializzazioneCategoria getPatrimonioSpecializzazioneCategoria() {
		return this.patrimonioSpecializzazioneCategoria;
	}

	public void setPatrimonioSpecializzazioneCategoria(PatrimonioSpecializzazioneCategoria patrimonioSpecializzazioneCategoria) {
		this.patrimonioSpecializzazioneCategoria = patrimonioSpecializzazioneCategoria;
	}
	
}