package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato_biblioteca_workflow database table.
 * 
 */
@Entity
@Table(name="stato_biblioteca_workflow")
public class StatoBibliotecaWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_stato", unique=true, nullable=false)
	private Integer idStato;

	@Column(length=255)
	private String descrizione;

	@Column(nullable=false, length=64)
	private String label;

    public StatoBibliotecaWorkflow() {
    }

	public Integer getIdStato() {
		return this.idStato;
	}

	public void setIdStato(Integer idStato) {
		this.idStato = idStato;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}