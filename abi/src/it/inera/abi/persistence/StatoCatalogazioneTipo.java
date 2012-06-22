package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato_catalogazione_tipo database table.
 * 
 */
@Entity
@Table(name="stato_catalogazione_tipo")
public class StatoCatalogazioneTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stato_catalogazione_tipo", unique=true, nullable=false)
	private Integer idStatoCatalogazioneTipo;

	@Column(length=100)
	private String descrizione;

    public StatoCatalogazioneTipo() {
    }

	public Integer getIdStatoCatalogazioneTipo() {
		return this.idStatoCatalogazioneTipo;
	}

	public void setIdStatoCatalogazioneTipo(Integer idStatoCatalogazioneTipo) {
		this.idStatoCatalogazioneTipo = idStatoCatalogazioneTipo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}