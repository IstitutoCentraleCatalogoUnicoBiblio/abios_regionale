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