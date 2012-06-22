package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contatti database table.
 * 
 */
@Entity
@Table(name="contatti")
public class Contatti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contatti", unique=true, nullable=false)
	private Integer idContatti;

	@Column(length=100)
	private String note;

	@Column(nullable=false, length=255)
	private String valore;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca", nullable=false)
	private Biblioteca biblioteca;

	//uni-directional many-to-one association to ContattiTipo
    @ManyToOne
	@JoinColumn(name="id_contatti_tipi", nullable=false)
	private ContattiTipo contattiTipo;

    public Contatti() {
    }

	public Integer getIdContatti() {
		return this.idContatti;
	}

	public void setIdContatti(Integer idContatti) {
		this.idContatti = idContatti;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public Biblioteca getBiblioteca() {
		return this.biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public ContattiTipo getContattiTipo() {
		return this.contattiTipo;
	}

	public void setContattiTipo(ContattiTipo contattiTipo) {
		this.contattiTipo = contattiTipo;
	}
	
}