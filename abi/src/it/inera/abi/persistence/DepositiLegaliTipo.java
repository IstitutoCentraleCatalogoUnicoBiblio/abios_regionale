package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the depositi_legali_tipo database table.
 * 
 */
@Entity
@Table(name="depositi_legali_tipo")
public class DepositiLegaliTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_depositi_legali_tipo", unique=true, nullable=false)
	private Integer idDepositiLegaliTipo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public DepositiLegaliTipo() {
    }

	public Integer getIdDepositiLegaliTipo() {
		return this.idDepositiLegaliTipo;
	}

	public void setIdDepositiLegaliTipo(Integer idDepositiLegaliTipo) {
		this.idDepositiLegaliTipo = idDepositiLegaliTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}