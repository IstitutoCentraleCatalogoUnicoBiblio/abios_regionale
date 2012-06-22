package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servizi_informazioni_bibliografiche_modalita database table.
 * 
 */
@Entity
@Table(name="servizi_informazioni_bibliografiche_modalita")
public class ServiziInformazioniBibliograficheModalita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_servizi_informazioni_bibliografiche_modalita", unique=true, nullable=false)
	private Integer idServiziInformazioniBibliograficheModalita;

	@Column(nullable=false, length=255)
	private String descrizione;

    public ServiziInformazioniBibliograficheModalita() {
    }

	public Integer getIdServiziInformazioniBibliograficheModalita() {
		return this.idServiziInformazioniBibliograficheModalita;
	}

	public void setIdServiziInformazioniBibliograficheModalita(Integer idServiziInformazioniBibliograficheModalita) {
		this.idServiziInformazioniBibliograficheModalita = idServiziInformazioniBibliograficheModalita;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}