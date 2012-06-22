package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patrimonio_specializzazione_categoria database table.
 * 
 */
@Entity
@Table(name="patrimonio_specializzazione_categoria")
public class PatrimonioSpecializzazioneCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_patrimonio_specializzazione_categoria", unique=true, nullable=false)
	private Integer idPatrimonioSpecializzazioneCategoria;

    @Lob()
	private String descrizione;

	@Column(name="id_sort")
	private Integer idSort;

	//uni-directional many-to-one association to PatrimonioSpecializzazioneCategoria
    @ManyToOne
	@JoinColumn(name="id_patrimonio_specializzazione_categoria_madre")
	private PatrimonioSpecializzazioneCategoria patrimonioSpecializzazioneCategoria;

    public PatrimonioSpecializzazioneCategoria() {
    }

	public Integer getIdPatrimonioSpecializzazioneCategoria() {
		return this.idPatrimonioSpecializzazioneCategoria;
	}

	public void setIdPatrimonioSpecializzazioneCategoria(Integer idPatrimonioSpecializzazioneCategoria) {
		this.idPatrimonioSpecializzazioneCategoria = idPatrimonioSpecializzazioneCategoria;
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