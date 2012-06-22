package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dati_province_istat database table.
 * 
 */
@Entity
@Table(name="dati_province_istat")
public class DatiProvinceIstat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_provincia", unique=true, nullable=false)
	private Integer idProvincia;

	@Column(name="totale_abitanti", nullable=false)
	private Integer totaleAbitanti;

    public DatiProvinceIstat() {
    }

	public Integer getIdProvincia() {
		return this.idProvincia;
	}

	public void setIdProvincia(Integer idProvincia) {
		this.idProvincia = idProvincia;
	}

	public Integer getTotaleAbitanti() {
		return this.totaleAbitanti;
	}

	public void setTotaleAbitanti(Integer totaleAbitanti) {
		this.totaleAbitanti = totaleAbitanti;
	}

}