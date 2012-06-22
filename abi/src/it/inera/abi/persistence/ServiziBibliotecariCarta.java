package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servizi_bibliotecari_carta database table.
 * 
 */
@Entity
@Table(name="servizi_bibliotecari_carta")
public class ServiziBibliotecariCarta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id__servizi_bibliotecari_carta", unique=true, nullable=false)
	private Integer idServiziBibliotecariCarta;

	@Column(nullable=false, length=255)
	private String descrizione;

    public ServiziBibliotecariCarta() {
    }

	public Integer getIdServiziBibliotecariCarta() {
		return this.idServiziBibliotecariCarta;
	}

	public void setIdServiziBibliotecariCarta(Integer idServiziBibliotecariCarta) {
		this.idServiziBibliotecariCarta = idServiziBibliotecariCarta;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}