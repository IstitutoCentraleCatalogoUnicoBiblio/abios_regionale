package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prestito_interbibliotecario_modo database table.
 * 
 */
@Entity
@Table(name="prestito_interbibliotecario_modo")
public class PrestitoInterbibliotecarioModo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_interbibliotecario_modo", unique=true, nullable=false)
	private Integer idPrestitoInterbibliotecarioModo;

	@Column(nullable=false, length=255)
	private String descrizione;

    public PrestitoInterbibliotecarioModo() {
    }

	public Integer getIdPrestitoInterbibliotecarioModo() {
		return this.idPrestitoInterbibliotecarioModo;
	}

	public void setIdPrestitoInterbibliotecarioModo(Integer idPrestitoInterbibliotecarioModo) {
		this.idPrestitoInterbibliotecarioModo = idPrestitoInterbibliotecarioModo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}