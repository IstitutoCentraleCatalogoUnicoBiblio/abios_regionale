package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prestito_interbibliotecario database table.
 * 
 */
@Entity
@Table(name="prestito_interbibliotecario")
public class PrestitoInterbibliotecario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito_interbibliotecario", unique=true, nullable=false)
	private Integer idPrestitoInterbibliotecario;

	private Boolean internazionale;

	private Boolean nazionale;

	//uni-directional many-to-one association to PrestitoInterbibliotecarioModo
    @ManyToOne
	@JoinColumn(name="id_prestito_interbibliotecario_modo", nullable=false)
	private PrestitoInterbibliotecarioModo prestitoInterbibliotecarioModo;

    public PrestitoInterbibliotecario() {
    }

	public Integer getIdPrestitoInterbibliotecario() {
		return this.idPrestitoInterbibliotecario;
	}

	public void setIdPrestitoInterbibliotecario(Integer idPrestitoInterbibliotecario) {
		this.idPrestitoInterbibliotecario = idPrestitoInterbibliotecario;
	}

	public Boolean getInternazionale() {
		return this.internazionale;
	}

	public void setInternazionale(Boolean internazionale) {
		this.internazionale = internazionale;
	}

	public Boolean getNazionale() {
		return this.nazionale;
	}

	public void setNazionale(Boolean nazionale) {
		this.nazionale = nazionale;
	}

	public PrestitoInterbibliotecarioModo getPrestitoInterbibliotecarioModo() {
		return this.prestitoInterbibliotecarioModo;
	}

	public void setPrestitoInterbibliotecarioModo(PrestitoInterbibliotecarioModo prestitoInterbibliotecarioModo) {
		this.prestitoInterbibliotecarioModo = prestitoInterbibliotecarioModo;
	}
	
}